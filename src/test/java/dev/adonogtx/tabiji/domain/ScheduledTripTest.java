package dev.adonogtx.tabiji.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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

        System.out.println(trip);

        Assertions.assertThat(trip).hasNoNullFieldsOrProperties();
    }

}
