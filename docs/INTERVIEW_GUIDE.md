# SeatSure — Technical Interview Guide & Architectural Defense

This guide is designed for senior Java backend engineering interviews, technical architectural discussions, and system design defenses.

---

## 1. Architectural Justification & Technology Trade-offs

### Q1: Why did you choose a Modular Monolith instead of Microservices?
**Senior Engineering Answer**:
> "For a ticket booking platform, business transactions such as seat holding and payment confirmation span multiple aggregates: `ShowSeat`, `SeatHold`, `Booking`, and `Payment`. In a distributed microservices architecture, executing these operations requires distributed transactions (2PC or Saga patterns), introducing eventual consistency windows, network partitioning vulnerabilities, and complex compensating transactions.
> 
> By choosing a modular monolith with strict package and domain boundaries, we retain **local ACID guarantees** using relational database transactions (`READ_COMMITTED` + `SELECT ... FOR UPDATE`), achieving absolute prevention of inventory double-booking. Furthermore, by adopting the Transactional Outbox pattern and Apache Kafka, domain modules remain asynchronous and decoupled, allowing future migration of services (e.g., Notification or Payment) into independent microservices with zero architectural rewrites."

### Q2: Why did you choose Pessimistic Locking over Optimistic Locking for Double-Booking Prevention?
**Senior Engineering Answer**:
> "Optimistic locking (`@Version`) works well when collision probability is low. In a flash sale or premier movie release (e.g., BookMyShow releasing Avengers tickets), contention on prime seats is extremely high. With optimistic locking, dozens of concurrent transactions would read the same version, but only the first to commit would succeed, causing an 'optimistic concurrency exception storm' and wasting database CPU on retries.
> 
> Pessimistic locking (`SELECT ... FOR UPDATE`) acquires an exclusive row-level lock on the `ShowSeat` records at the start of the transaction. By ordering the seat IDs ascending (`ORDER BY seat_id ASC`), we eliminate cyclic deadlocks. The lock duration is kept minimal (sub-10ms) because the transaction only performs in-memory price summing and a fast status update."

### Q3: Why is MySQL the source of truth rather than Redis for seat inventory?
**Senior Engineering Answer**:
> "While Redis is exceptionally fast for atomic counters (`INCRBY`) and key expiration, maintaining complex relational seat layouts (e.g. adjacent seat selection, tiered pricing, and foreign-key constraints to bookings and users) inside Redis leads to dual-source-of-truth divergence. Redis key eviction, replication lag in sentinel/cluster setups, or failovers can lead to phantom availability.
> 
> MySQL InnoDB provides true ACID guarantees, versioned schema migrations via Flyway, and persistent transaction logs (WAL/redo log). We use Redis purely as a read-through cache for movie catalogs and cinema metadata, ensuring that even if Redis completely crashes, the core booking engine remains 100% operational."

### Q4: How does the Transactional Outbox pattern solve the Dual-Write Problem?
**Senior Engineering Answer**:
> "The Dual-Write Problem occurs when an application attempts to update a database and publish a message to a broker (like Kafka) in the same operation. If the message is sent to Kafka but the database transaction rolls back, downstream consumers receive a phantom event. Conversely, if the database commits but the Kafka network call times out, downstream consumers never learn of the event.
> 
> The Transactional Outbox pattern solves this by writing the domain event to an `outbox_events` table **inside the exact same database transaction** that creates the booking. A background poller (`OutboxPublisherService`) reads uncommitted events and dispatches them to Kafka with at-least-once delivery guarantees. Combined with consumer deduplication using a `processed_events` table, we achieve end-to-end idempotency without distributed transaction overhead."

### Q5: What happens if a payment confirmation succeeds at the exact moment a hold expires?
**Senior Engineering Answer**:
> "This is a classic time-of-check to time-of-use (TOCTOU) race condition. When payment confirmation arrives, `PaymentService` begins an isolated transaction and locks the booking and show seats. It checks whether the seats were already seized by another customer during the hold expiration window.
> 
> If the seats are still unallocated, the booking is confirmed safely. However, if another customer has already reserved the seat, SeatSure executes a **Safe Compensation Policy**: the payment status is marked as `REFUND_PENDING` / `REFUNDED` with reason `SEATS_UNAVAILABLE_AUTO_REFUND`, and the booking transitions to `EXPIRED`. Under no circumstances will the system confirm two tickets for the same seat."

---

## 2. Likely Interviewer Questions & Model Responses

| Question | Core Concept Tested | Model Response |
|---|---|---|
| **"How do you prevent deadlocks during multi-seat booking?"** | Lock Ordering Hierarchy | "We sort requested physical seat IDs in natural ascending order (`ORDER BY seat_id ASC`) prior to issuing `SELECT ... FOR UPDATE`. This creates a global lock hierarchy that guarantees no two transactions can wait for each other's locks cyclically." |
| **"How do you guarantee payment idempotency?"** | Duplicate Request Protection | "Every payment request requires an `idempotencyKey` generated by the client. The backend checks `paymentRepository.findByIdempotencyKey(key)`. If found, it immediately returns the existing payment transaction without charging again." |
| **"How is hold expiration handled if a customer closes their browser?"** | Eventual Consistency & Recovery | "The database hold record contains an authoritative `expires_at` timestamp. A scheduled background worker (`SeatHoldExpirationScheduler`) runs every 10 seconds, identifies expired active holds, and restores their inventory back to `AVAILABLE`." |
| **"Why is floating-point arithmetic banned for monetary calculations?"** | Numerical Accuracy | "Binary floating-point numbers (`float`, `double`) cannot accurately represent decimal fractions like 0.1, causing accumulated rounding errors. All prices in SeatSure use `BigDecimal` with explicit scale and `RoundingMode.HALF_UP`." |

---

## 3. Honest Status Matrix

| Component | Status | Implementation Details |
|---|---|---|
| **Double-Booking Concurrency** | **IMPLEMENTED & TESTED** | `SELECT ... FOR UPDATE` with ascending order; 10-thread concurrency integration test passing. |
| **Authentication & JWT** | **IMPLEMENTED & TESTED** | Stateless JWT with JJWT 0.12.6, refresh token rotation, BCrypt password hashing. |
| **Temporary Seat Holds** | **IMPLEMENTED & TESTED** | Authoritative MySQL holds with configurable TTL (5 mins) and automatic background expiration. |
| **Simulated Payment Gateway** | **IMPLEMENTED & TESTED** | Deterministic simulator with SUCCESS, FAILURE, TIMEOUT, and idempotency protection. |
| **Transactional Outbox & Messaging** | **IMPLEMENTED & TESTED** | Outbox pattern with asynchronous poller, local event bus fallback, and Kafka integration. |
| **Catalog & Booking REST APIs** | **IMPLEMENTED & TESTED** | Full CRUD and business workflows documented in OpenAPI 3.0 / Swagger UI. |
| **React + TypeScript Frontend** | **IMPLEMENTED & TESTED** | Cinematic dark mode UI, interactive SVG/CSS curved seat grid, live countdown timer, and admin portal. |
| **Docker Compose Infrastructure** | **IMPLEMENTED** | Configured for MySQL 8.4 LTS, Redis 7, Kafka 3.7 (KRaft), Backend, and Nginx Frontend. |
