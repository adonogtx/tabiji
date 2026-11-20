package dev.adonogtx.tabiji.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SeatTest {

    @Test
    @DisplayName("Create seat with valid data")
    void create_ReturnsSeat_WhenAllFieldsAreValid() {

        var carNumber = 3;
        var seatCode = "12A";

        var seat = Seat.builder().carNumber(carNumber).seatCode(seatCode).build();

        assertThat(seat)
                .extracting(Seat::getCarNumber, Seat::getSeatCode)
                .containsExactly(carNumber, seatCode);
    }

    @Test
    @DisplayName("Throw exception when carNumber is negative")
    void create_ThrowsDomainException_WhenCarNumberIsNegative() {

        var carNumber = -1;
        var seatCode = "12A";

        assertThatThrownBy(() -> Seat.builder().carNumber(carNumber).seatCode(seatCode).build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'carNumber' must be greater than zero");
    }

    @Test
    @DisplayName("Throw exception when seatCode is null")
    void create_ThrowsDomainException_WhenSeatCodeIsNull() {

        var carNumber = 3;

        assertThatThrownBy(() -> Seat.builder().carNumber(carNumber).seatCode(null).build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'seatCode' is required");
    }

    @Test
    @DisplayName("Throw exception when seatCode is blank")
    void create_ThrowsDomainException_WhenSeatCodeIsBlank() {

        var carNumber = 3;
        var seatCode = " ";

        assertThatThrownBy(() -> Seat.builder().carNumber(carNumber).seatCode(seatCode).build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'seatCode' is required");
    }

}
