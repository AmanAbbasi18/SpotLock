package com.seatsure.hold;

import com.seatsure.common.response.ApiResponse;
import com.seatsure.hold.dto.CreateHoldRequest;
import com.seatsure.hold.dto.SeatHoldResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Seat Holds", description = "Temporary seat reservation and lock management")
public class HoldController {

    private final SeatHoldService seatHoldService;

    public HoldController(SeatHoldService seatHoldService) {
        this.seatHoldService = seatHoldService;
    }

    @PostMapping("/shows/{showId}/holds")
    @Operation(summary = "Acquire temporary hold on requested seats with double-booking prevention")
    public ResponseEntity<ApiResponse<SeatHoldResponse>> createHold(
            @PathVariable Long showId,
            @Valid @RequestBody CreateHoldRequest request
    ) {
        SeatHoldResponse response = seatHoldService.createHold(showId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Seat hold acquired successfully", response));
    }

    @GetMapping("/holds/{holdId}")
    @Operation(summary = "Get seat hold details and remaining time")
    public ResponseEntity<ApiResponse<SeatHoldResponse>> getHoldById(@PathVariable Long holdId) {
        return ResponseEntity.ok(ApiResponse.ok(seatHoldService.getHoldById(holdId)));
    }

    @DeleteMapping("/holds/{holdId}")
    @Operation(summary = "Explicitly release seat hold and return seats to inventory")
    public ResponseEntity<ApiResponse<Void>> releaseHold(@PathVariable Long holdId) {
        seatHoldService.releaseHold(holdId);
        return ResponseEntity.ok(ApiResponse.ok("Seat hold released successfully", null));
    }
}
