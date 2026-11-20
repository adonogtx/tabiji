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
    @DisplayName("Throw exception when carNumber is zero")
    void create_ThrowsDomainException_WhenCarNumberIsZero() {

        var carNumber = 0;
        var seatCode = "12A";

        assertThatThrownBy(() -> Seat.builder().carNumber(carNumber).seatCode(seatCode).build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'carNumber' must be greater than zero");
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

    @Test
    @DisplayName("Throw exception when seatCode is empty")
    void create_ThrowsDomainException_WhenSeatCodeIsEmpty() {

        var carNumber = 3;
        var seatCode = "";

        assertThatThrownBy(() -> Seat.builder().carNumber(carNumber).seatCode(seatCode).build())
                .isInstanceOf(DomainException.class)
                .hasMessage("The field 'seatCode' is required");
    }

    @Test
    @DisplayName("Seats with same car number and seat code are equal")
    void equals_ReturnsTrue_WhenCarNumberAndSeatCodeAreEqual() {

        var seat = Seat.builder().carNumber(3).seatCode("12A").build();
        var sameSeat = Seat.builder().carNumber(3).seatCode("12A").build();

        assertThat(seat)
                .isEqualTo(sameSeat)
                .hasSameHashCodeAs(sameSeat);
    }

    @Test
    @DisplayName("Seats with different seat codes are not equal")
    void equals_ReturnsFalse_WhenOnlyCarNumberIsEqual() {

        var seat = Seat.builder().carNumber(3).seatCode("12A").build();
        var otherSeat = Seat.builder().carNumber(3).seatCode("13A").build();

        assertThat(seat).isNotEqualTo(otherSeat);
    }

    @Test
    @DisplayName("Seats with different car numbers are not equal")
    void equals_ReturnsFalse_WhenOnlySeatCodeIsEqual() {

        var seat = Seat.builder().carNumber(2).seatCode("12A").build();
        var otherSeat = Seat.builder().carNumber(3).seatCode("12A").build();

        assertThat(seat).isNotEqualTo(otherSeat);
    }

}
