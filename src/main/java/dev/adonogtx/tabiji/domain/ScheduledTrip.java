package dev.adonogtx.tabiji.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ScheduledTrip {
    @EqualsAndHashCode.Include
    private final Long id;
    private final Station origin;
    private final Station destination;
    private final LocalDateTime departure;
    private final LocalDateTime arrival;
    private final Set<Seat> seats = new HashSet<>();

    @Builder
    public ScheduledTrip(Long id, Station origin, Station destination, LocalDateTime departure, LocalDateTime arrival) {
        if (id == null) {
            throw new DomainException("The field 'id' is required");
        }
        if (origin == null) {
            throw new DomainException("The field 'origin' is required");
        }
        if (destination == null) {
            throw new DomainException("The field 'destination' is required");
        }
        if (departure == null) {
            throw new DomainException("The field 'departure' is required");
        }
        if (arrival == null) {
            throw new DomainException("The field 'arrival' is required");
        }
        if (origin.equals(destination)) {
            throw new DomainException("The fields 'origin' and 'destination' must be different");
        }
        if (!arrival.isAfter(departure)) {
            throw new DomainException("The field 'departure' must be strictly before 'arrival'");
        }

        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
    }

    public void addSeat(Seat seat) {
        if (seat == null) {
            throw new DomainException("The seat is required");
        }
        if (!seats.add(seat)) {
            throw new DomainException("The seat already exists in this trip");
        }
    }

    public Set<Seat> getSeats() {
        return Set.copyOf(seats);
    }

    public boolean hasSeat(Seat seat) {
        return seats.contains(seat);
    }
}
