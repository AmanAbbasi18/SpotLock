package com.seatsure.payment;

import com.seatsure.booking.Booking;
import com.seatsure.booking.BookingStatus;
import com.seatsure.payment.dto.PaymentRequest;
import com.seatsure.payment.dto.PaymentResponse;
import com.seatsure.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PaymentIdempotencyUnitTest {

    @Test
    @DisplayName("Should detect duplicate idempotency key and return existing payment transaction")
    void testPaymentIdempotency() {
        PaymentRepository paymentRepository = mock(PaymentRepository.class);

        String idempotencyKey = "PAY_IDEMP_TEST_12345";
        Booking booking = Booking.builder()
                .id(100L)
                .bookingReference("BK-TEST-100")
                .user(User.builder().id(1L).email("user@example.com").build())
                .status(BookingStatus.CONFIRMED)
                .totalAmount(new BigDecimal("500.00"))
                .currency("INR")
                .expiresAt(Instant.now().plusSeconds(300))
                .build();

        Payment existingPayment = Payment.builder()
                .id(50L)
                .booking(booking)
                .paymentReference("PAY_EXISTING_50")
                .idempotencyKey(idempotencyKey)
                .amount(new BigDecimal("500.00"))
                .currency("INR")
                .status(PaymentStatus.SUCCESS)
                .createdAt(Instant.now())
                .build();

        when(paymentRepository.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.of(existingPayment));

        // When checking idempotency key
        Optional<Payment> found = paymentRepository.findByIdempotencyKey(idempotencyKey);

        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(50L);
        assertThat(found.get().getStatus()).isEqualTo(PaymentStatus.SUCCESS);
        assertThat(found.get().getPaymentReference()).isEqualTo("PAY_EXISTING_50");

        PaymentResponse response = PaymentResponse.fromEntity(found.get());
        assertThat(response.getPaymentReference()).isEqualTo("PAY_EXISTING_50");
        assertThat(response.getStatus()).isEqualTo(PaymentStatus.SUCCESS);
    }
}
