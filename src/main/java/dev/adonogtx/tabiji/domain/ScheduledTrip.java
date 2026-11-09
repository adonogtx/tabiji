package dev.adonogtx.tabiji.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class ScheduledTrip {
    @EqualsAndHashCode.Include
    Long id;
    Station origin;
    Station destination;
    LocalDateTime departure;
    LocalDateTime arrival;

    @Builder
    public ScheduledTrip(Long id, Station origin, Station destination, LocalDateTime departure, LocalDateTime arrival) {

        if (origin == null) {
            throw new StationException("Origin is required");
        }
        if (destination == null) {
            throw new StationException("Destination field is required");
        }
        if (departure == null) {
            throw new StationException("Departure field is required");
        }
        if (arrival == null) {
            throw new StationException("Arrival field is required");
        }

        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
    }

}
