package dev.adonogtx.tabiji.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Reservation {
    @EqualsAndHashCode.Include
    private final Long id;
    private final ScheduledTrip trip;
    private final Seat seat;
    private final String passengerName;
    private final Instant createdAt;
    private final ReservationStatus status;

    @Builder
    public Reservation(Long id, ScheduledTrip trip, Seat seat, String passengerName, Instant createdAt) {
        if (id == null) {
            throw new DomainException("The field 'id' is required");
        }
        if (trip == null) {
            throw new DomainException("The field 'trip' is required");
        }
        if (seat == null) {
            throw new DomainException("The field 'seat' is required");
        }
        if (passengerName == null || passengerName.isBlank()) {
            throw new DomainException("The field 'passengerName' is required");
        }
        if (createdAt == null) {
            throw new DomainException("The field 'createdAt' is required");
        }
        if (!trip.hasSeat(seat)) {
            throw new DomainException("The seat does not belong to this trip");
        }

        this.id = id;
        this.trip = trip;
        this.seat = seat;
        this.passengerName = passengerName;
        this.createdAt = createdAt;
        this.status = ReservationStatus.ACTIVE;
    }
}
