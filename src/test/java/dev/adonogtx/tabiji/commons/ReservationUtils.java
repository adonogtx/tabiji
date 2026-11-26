package dev.adonogtx.tabiji.commons;

import dev.adonogtx.tabiji.domain.Reservation;

import java.time.Instant;

public class ReservationUtils {
    public static final Instant CREATED_AT = Instant.parse("2026-11-10T03:00:00Z");
    private final ScheduledTripUtils scheduledTripUtils = new ScheduledTripUtils();
    private final SeatUtils seatUtils = new SeatUtils();
    
    public Reservation.ReservationBuilder newReservationBuilder() {
        var trip = scheduledTripUtils.newScheduledTrip();
        var seat = seatUtils.newSeat();
        trip.addSeat(seat);

        return Reservation
                .builder()
                .id(1L)
                .trip(trip)
                .seat(seat)
                .passengerName("Hasegawa")
                .createdAt(CREATED_AT);
    }

    public Reservation newReservation() {
        return newReservationBuilder().build();
    }
}
