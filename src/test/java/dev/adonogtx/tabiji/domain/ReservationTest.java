package dev.adonogtx.tabiji.domain;

import dev.adonogtx.tabiji.commons.ReservationUtils;
import dev.adonogtx.tabiji.commons.ScheduledTripUtils;
import dev.adonogtx.tabiji.commons.SeatUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("Throw exception when the seat does not belong to the trip")
    void create_ThrowsDomainException_WhenSeatDoesNotBelongToTrip() {
        var wrongSeat = seatUtils.newSeatBuilder().seatCode("99G").build();

        assertThatThrownBy(() -> reservationUtils.newReservationBuilder()
                .seat(wrongSeat)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The seat does not belong to this trip");
    }

    @Test
    @DisplayName("Throw exception when id is null")
    void create_ThrowsDomainException_WhenIdIsNull() {
        assertThatThrownBy(() -> reservationUtils.newReservationBuilder()
                .id(null)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'id' is required");
    }

    @Test
    @DisplayName("Throw exception when trip is null")
    void create_ThrowsDomainException_WhenTripIsNull() {
        assertThatThrownBy(() -> reservationUtils.newReservationBuilder()
                .trip(null)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'trip' is required");
    }

    @Test
    @DisplayName("Throw exception when seat is null")
    void create_ThrowsDomainException_WhenSeatIsNull() {
        assertThatThrownBy(() -> reservationUtils.newReservationBuilder()
                .seat(null)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'seat' is required");
    }

    @Test
    @DisplayName("Throw exception when passengerName is null")
    void create_ThrowsDomainException_WhenPassengerNameIsNull() {
        assertThatThrownBy(() -> reservationUtils.newReservationBuilder()
                .passengerName(null)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'passengerName' is required");
    }

    @Test
    @DisplayName("Throw exception when passengerName is blank")
    void create_ThrowsDomainException_WhenPassengerNameIsBlank() {
        assertThatThrownBy(() -> reservationUtils.newReservationBuilder()
                .passengerName(" ")
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'passengerName' is required");
    }

    @Test
    @DisplayName("Throw exception when passengerName is empty")
    void create_ThrowsDomainException_WhenPassengerNameIsEmpty() {
        assertThatThrownBy(() -> reservationUtils.newReservationBuilder()
                .passengerName("")
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'passengerName' is required");
    }

    @Test
    @DisplayName("Throw exception when createdAt is null")
    void create_ThrowsDomainException_WhenCreatedAtIsNull() {
        assertThatThrownBy(() -> reservationUtils.newReservationBuilder()
                .createdAt(null)
                .build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'createdAt' is required");
    }
}
