package dev.adonogtx.tabiji.commons;

import dev.adonogtx.tabiji.domain.ScheduledTrip;
import dev.adonogtx.tabiji.domain.Station;

import java.time.LocalDateTime;

public class ScheduledTripUtils {

    public ScheduledTrip.ScheduledTripBuilder newScheduledTripBuilder() {
        return ScheduledTrip.builder()
                .id(1L)
                .origin(new Station("TYO", "Tokyo"))
                .destination(new Station("OSA", "Osaka"))
                .departure(LocalDateTime.of(2026, 11, 16, 9, 0))
                .arrival(LocalDateTime.of(2026, 11, 16, 10, 0));
    }

    public ScheduledTrip newScheduledTrip() {
        return newScheduledTripBuilder().build();
    }
}
