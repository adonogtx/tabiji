package dev.adonogtx.tabiji.domain;

import dev.adonogtx.tabiji.commons.ScheduledTripUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScheduledTripTest {

    private final ScheduledTripUtils scheduledTripUtils = new ScheduledTripUtils();

    @Test
    @DisplayName("Create scheduled trip with valid data")
    void create_ReturnsScheduledTrip_WhenAllFieldsAreValid() {
        var origin = new Station("TYO", "Tokyo");
        var destination = new Station("OSA", "Osaka");
        var departure = LocalDateTime.of(2026, 11, 16, 9, 0);
        var arrival = LocalDateTime.of(2026, 11, 16, 10, 0);

        var trip = ScheduledTrip.builder()
                .id(1L)
                .origin(origin)
                .destination(destination)
                .departure(departure)
                .arrival(arrival)
                .build();

        assertThat(trip)
                .extracting(
                        ScheduledTrip::getId,
                        ScheduledTrip::getOrigin,
                        ScheduledTrip::getDestination,
                        ScheduledTrip::getDeparture,
                        ScheduledTrip::getArrival
                )
                .containsExactly(1L, origin, destination, departure, arrival);
    }

    @Test
    @DisplayName("Throw exception when origin and destination have the same code")
    void create_ThrowsDomainException_WhenOriginEqualsDestination() {
        assertThatThrownBy(() -> scheduledTripUtils.newScheduledTripBuilder()
                .origin(new Station("TYO", "Tokyo"))
                .destination(new Station("TYO", "東京"))
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The fields 'origin' and 'destination' must be different");
    }

    @Test
    @DisplayName("Throw exception when arrival is before departure")
    void create_ThrowsDomainException_WhenArrivalIsBeforeDeparture() {
        var departure = LocalDateTime.of(2026, 11, 16, 9, 0);

        assertThatThrownBy(() -> scheduledTripUtils.newScheduledTripBuilder()
                .departure(departure)
                .arrival(departure.minusHours(1))
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'departure' must be strictly before 'arrival'");
    }

    @Test
    @DisplayName("Throw exception when arrival equals departure")
    void create_ThrowsDomainException_WhenArrivalEqualsDeparture() {
        var departure = LocalDateTime.of(2026, 11, 16, 9, 0);

        assertThatThrownBy(() -> scheduledTripUtils.newScheduledTripBuilder()
                .departure(departure)
                .arrival(departure)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'departure' must be strictly before 'arrival'");
    }

    @Test
    @DisplayName("Throw exception when id is null")
    void create_ThrowsDomainException_WhenIdIsNull() {
        assertThatThrownBy(() -> scheduledTripUtils.newScheduledTripBuilder()
                .id(null)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'id' is required");
    }

    @Test
    @DisplayName("Throw exception when origin is null")
    void create_ThrowsDomainException_WhenOriginIsNull() {
        assertThatThrownBy(() -> scheduledTripUtils.newScheduledTripBuilder()
                .origin(null)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'origin' is required");
    }

    @Test
    @DisplayName("Throw exception when destination is null")
    void create_ThrowsDomainException_WhenDestinationIsNull() {
        assertThatThrownBy(() -> scheduledTripUtils.newScheduledTripBuilder()
                .destination(null)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'destination' is required");
    }

    @Test
    @DisplayName("Throw exception when departure is null")
    void create_ThrowsDomainException_WhenDepartureIsNull() {
        assertThatThrownBy(() -> scheduledTripUtils.newScheduledTripBuilder()
                .departure(null)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'departure' is required");
    }

    @Test
    @DisplayName("Throw exception when arrival is null")
    void create_ThrowsDomainException_WhenArrivalIsNull() {
        assertThatThrownBy(() -> scheduledTripUtils.newScheduledTripBuilder()
                .arrival(null)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'arrival' is required");
    }
}
