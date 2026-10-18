# SeatSure — REST API Reference Specification

All endpoints are prefixed with `/api/v1`.

Interactive Swagger documentation is available at `http://localhost:8080/swagger-ui.html`.

---

## 1. Authentication & User Management

### `POST /api/v1/auth/register`
Creates a new customer account and returns JWT tokens.
- **Request Body**:
  ```json
  {
    "name": "Jane Doe",
    "email": "jane@example.com",
    "password": "Password@123"
  }
  ```
- **Response**: `201 Created` with `AuthResponse` (`accessToken`, `refreshToken`, `user`).

### `POST /api/v1/auth/login`
Authenticates credentials and returns access and refresh tokens.
- **Request Body**:
  ```json
  {
    "email": "jane@example.com",
    "password": "Password@123"
  }
  ```
- **Response**: `200 OK` with `AuthResponse`.

### `POST /api/v1/auth/refresh`
Rotates refresh token and issues a fresh 15-minute access token.
- **Request Body**: `{"refreshToken": "uuid-token"}`
- **Response**: `200 OK`.

### `POST /api/v1/auth/logout`
Revokes active refresh token.
- **Response**: `200 OK`.

### `GET /api/v1/users/me`
Returns current authenticated user profile.
- **Headers**: `Authorization: Bearer <token>`
- **Response**: `200 OK` with `UserDto`.

---

## 2. Movies & Discovery

### `GET /api/v1/movies`
List movies with pagination, search, genre, language, and city filtering.
- **Query Params**:
  - `city` (optional): Filter movies with scheduled shows in city
  - `genre` (optional): e.g. "Sci-Fi", "Action"
  - `language` (optional): e.g. "English", "Hindi"
  - `search` (optional): Search query matching movie title
  - `page`, `size`, `sort`
- **Response**: `200 OK` with `PagedResponse<MovieDto>`.

### `GET /api/v1/movies/{movieId}`
Get detailed movie synopsis, runtime, rating, and poster.

---

## 3. Cinemas, Screens & Shows

### `GET /api/v1/cities`
Returns list of all active cinema cities (e.g. Mumbai, Bengaluru, Delhi NCR, Hyderabad).

### `GET /api/v1/cinemas`
Returns all cinemas, optionally filtered by `?city=Mumbai`.

### `GET /api/v1/movies/{movieId}/shows`
Returns all upcoming scheduled screenings for a movie in a specific city (`?city=Mumbai`).

### `GET /api/v1/shows/{showId}/seats`
Returns real-time seat availability and tier pricing without exposing private customer identifiers.
- **Response**:
  ```json
  {
    "showId": 1,
    "totalSeats": 40,
    "availableSeats": 38,
    "heldSeats": 2,
    "bookedSeats": 0,
    "seats": [
      {
        "showSeatId": 101,
        "seatId": 1,
        "rowLabel": "A",
        "seatNumber": 1,
        "seatType": "SILVER",
        "price": 250.00,
        "status": "AVAILABLE",
        "isHeldByCurrentUser": false
      }
    ]
  }
  ```

---

## 4. Seat Holds & Concurrency

### `POST /api/v1/shows/{showId}/holds`
Acquires a temporary exclusive hold on requested seats with pessimistic row-locking (`SELECT ... FOR UPDATE`).
- **Headers**: `Authorization: Bearer <token>`
- **Request Body**:
  ```json
  {
    "seatIds": [1, 2]
  }
  ```
- **Responses**:
  - `201 Created`: Hold acquired, returns `holdId`, `expiresAt`, `totalAmount`.
  - `409 Conflict`: `SEAT_UNAVAILABLE` — one or more seats are already held or booked.
  - `400 Bad Request`: Duplicate seat IDs in request or show not open.

### `GET /api/v1/holds/{holdId}`
Get remaining expiration seconds and details for a hold owned by current user.

### `DELETE /api/v1/holds/{holdId}`
Explicitly releases a seat hold and restores inventory back to `AVAILABLE`.

---

## 5. Bookings & Payments

### `POST /api/v1/bookings`
Converts an active seat hold into a pending booking order with authoritative price calculation.
- **Request Body**: `{"holdId": 10}`
- **Response**: `201 Created` with `BookingResponse` and unique `bookingReference`.

### `GET /api/v1/bookings`
Paginated booking history for authenticated user.

### `GET /api/v1/bookings/{bookingId}`
Get booking details and confirmed seat items.

### `GET /api/v1/bookings/reference/{reference}`
Retrieve booking by human-readable reference (e.g. `BK-3D90A1BC`).

### `POST /api/v1/bookings/{bookingId}/cancel`
Cancels a confirmed booking, restores seats to inventory, and triggers simulated refund.

### `POST /api/v1/bookings/{bookingId}/payments`
Executes simulated payment with idempotency protection.
- **Request Body**:
  ```json
  {
    "idempotencyKey": "PAY_IDEMP_984372",
    "simulationOutcome": "SUCCESS",
    "paymentMethod": "UPI"
  }
  ```
- **Response**: `200 OK` with `PaymentResponse` and updated booking status (`CONFIRMED`).

---

## 6. Admin Endpoints

- `GET /api/v1/admin/movies`: View all movies including inactive ones.
- `POST /api/v1/admin/movies`: Add a new movie to catalog.
- `PUT /api/v1/admin/movies/{id}`: Update movie metadata.
- `PATCH /api/v1/admin/movies/{id}/status`: Toggle active/inactive status.
- `POST /api/v1/admin/shows`: Schedule new screening and automatically generate seat inventory with overlap validation.
- `POST /api/v1/admin/cinemas`: Create new multiplex.
- `POST /api/v1/admin/cinemas/{cinemaId}/screens`: Create auditorium and generate tiered seat layout.
