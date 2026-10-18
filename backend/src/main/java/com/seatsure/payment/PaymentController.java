package com.seatsure.payment;

import com.seatsure.common.response.ApiResponse;
import com.seatsure.payment.dto.PaymentRequest;
import com.seatsure.payment.dto.PaymentResponse;
import com.seatsure.payment.dto.WebhookPaymentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Payments & Checkout", description = "Simulated payment processing and gateway webhooks")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/bookings/{bookingId}/payments")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Process simulated payment for a booking with idempotency protection")
    public ResponseEntity<ApiResponse<PaymentResponse>> processPayment(
            @PathVariable Long bookingId,
            @Valid @RequestBody PaymentRequest request
    ) {
        PaymentResponse response = paymentService.processPayment(bookingId, request);
        return ResponseEntity.ok(ApiResponse.ok("Payment processed", response));
    }

    @GetMapping("/payments/{paymentId}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Get payment transaction details by ID")
    public ResponseEntity<ApiResponse<PaymentResponse>> getPaymentById(@PathVariable Long paymentId) {
        return ResponseEntity.ok(ApiResponse.ok(paymentService.getPaymentById(paymentId)));
    }

    @PostMapping("/payments/webhook/simulated")
    @Operation(summary = "Simulated webhook endpoint for asynchronous gateway events")
    public ResponseEntity<ApiResponse<Void>> handleWebhook(@Valid @RequestBody WebhookPaymentRequest request) {
        paymentService.processWebhook(request);
        return ResponseEntity.ok(ApiResponse.ok("Webhook processed successfully", null));
    }
}
