package dev.adonogtx.tabiji.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScheduledTripTest {

    @Test
    void create_ScheduledTripe(){

        var trip = ScheduledTrip
                .builder()
                .id(1L)
                .origin(new Station("TYO","Tokyo"))
                .destination(new Station("OSA","Osaka"))
                .departure(LocalDateTime.now())
                .arrival(LocalDateTime.now().plusHours(1))
                .build();

        assertThat(trip).hasNoNullFieldsOrProperties();
    }

    @Test
    void create_ThrowsDomainException_WhenOriginEqualsDestination() {
        var tokyo = new Station("TYO", "Tokyo");
        var departure = LocalDateTime.of(2026, 11, 16, 9, 0);
        var arrival = LocalDateTime.of(2026, 11, 16, 11, 30);

        assertThrows(StationException.class,
                () -> new ScheduledTrip(1L, tokyo, tokyo, departure, arrival));
    }

}
