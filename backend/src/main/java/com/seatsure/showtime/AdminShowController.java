package com.seatsure.showtime;

import com.seatsure.common.exception.ResourceNotFoundException;
import com.seatsure.common.response.ApiResponse;
import com.seatsure.showtime.dto.CreateShowRequest;
import com.seatsure.showtime.dto.ShowDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/shows")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin: Shows", description = "Administrator show schedule management")
@SecurityRequirement(name = "bearerAuth")
public class AdminShowController {

    private final ShowtimeService showtimeService;
    private final ShowRepository showRepository;

    public AdminShowController(ShowtimeService showtimeService, ShowRepository showRepository) {
        this.showtimeService = showtimeService;
        this.showRepository = showRepository;
    }

    @PostMapping
    @Operation(summary = "Schedule a new show and generate seat inventory")
    public ResponseEntity<ApiResponse<ShowDto>> createShow(@Valid @RequestBody CreateShowRequest request) {
        ShowDto show = showtimeService.createShow(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Show scheduled successfully", show));
    }

    @PatchMapping("/{showId}/cancel")
    @Operation(summary = "Cancel a scheduled show")
    @Transactional
    public ResponseEntity<ApiResponse<ShowDto>> cancelShow(@PathVariable Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResourceNotFoundException("Show", "id", showId));
        show.setStatus(ShowStatus.CANCELLED);
        show = showRepository.save(show);
        return ResponseEntity.ok(ApiResponse.ok("Show cancelled successfully", ShowDto.fromEntity(show)));
    }
}
