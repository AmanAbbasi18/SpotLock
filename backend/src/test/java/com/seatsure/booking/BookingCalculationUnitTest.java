package com.seatsure.booking;

import com.seatsure.cinema.SeatType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookingCalculationUnitTest {

    private BigDecimal calculateSeatPrice(BigDecimal basePrice, SeatType seatType) {
        return switch (seatType) {
            case SILVER -> basePrice;
            case GOLD -> basePrice.multiply(BigDecimal.valueOf(1.40)).setScale(2, RoundingMode.HALF_UP);
            case PLATINUM -> basePrice.multiply(BigDecimal.valueOf(2.00)).setScale(2, RoundingMode.HALF_UP);
        };
    }

    @Test
    @DisplayName("Should correctly compute tiered seat pricing based on base price")
    void testTieredPricingCalculation() {
        BigDecimal base = new BigDecimal("250.00");

        BigDecimal silverPrice = calculateSeatPrice(base, SeatType.SILVER);
        BigDecimal goldPrice = calculateSeatPrice(base, SeatType.GOLD);
        BigDecimal platinumPrice = calculateSeatPrice(base, SeatType.PLATINUM);

        assertThat(silverPrice).isEqualByComparingTo(new BigDecimal("250.00"));
        assertThat(goldPrice).isEqualByComparingTo(new BigDecimal("350.00"));
        assertThat(platinumPrice).isEqualByComparingTo(new BigDecimal("500.00"));
    }

    @Test
    @DisplayName("Should correctly aggregate total order amount across heterogeneous seat tiers")
    void testTotalOrderAmountAggregation() {
        BigDecimal base = new BigDecimal("200.00");

        List<BigDecimal> seatPrices = List.of(
                calculateSeatPrice(base, SeatType.SILVER),   // 200.00
                calculateSeatPrice(base, SeatType.SILVER),   // 200.00
                calculateSeatPrice(base, SeatType.GOLD),     // 280.00
                calculateSeatPrice(base, SeatType.PLATINUM)  // 400.00
        );

        BigDecimal total = seatPrices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        assertThat(total).isEqualByComparingTo(new BigDecimal("1080.00"));
    }
}
