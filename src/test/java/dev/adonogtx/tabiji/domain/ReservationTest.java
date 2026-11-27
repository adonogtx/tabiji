package dev.adonogtx.tabiji.domain;

import dev.adonogtx.tabiji.commons.ReservationUtils;
import dev.adonogtx.tabiji.commons.ScheduledTripUtils;
import dev.adonogtx.tabiji.commons.SeatUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class ReservationTest {

    private final ReservationUtils reservationUtils = new ReservationUtils();
    private final ScheduledTripUtils scheduledTripUtils = new ScheduledTripUtils();
    private final SeatUtils seatUtils = new SeatUtils();

    @Test
    @DisplayName("Create reservation with valid data")
    void create_ReturnsReservation_WhenAllFieldsAreValid() {
        var trip = scheduledTripUtils.newScheduledTrip();
        var seat = seatUtils.newSeat();
        var createdAt = Instant.parse("2026-11-10T03:00:00Z");
        var id = 1L;
        var passengerName = "Hasegawa";
        trip.addSeat(seat);

        var reservation = Reservation
                .builder()
                .id(id)
                .trip(trip)
                .seat(seat)
                .passengerName(passengerName)
                .createdAt(createdAt)
                .build();

        assertThat(reservation)
                .extracting(
                        Reservation::getId,
                        Reservation::getTrip,
                        Reservation::getSeat,
                        Reservation::getPassengerName,
                        Reservation::getCreatedAt,
                        Reservation::getStatus
                )
                .containsExactly(id, trip, seat, passengerName, createdAt, ReservationStatus.ACTIVE);
    }

}
