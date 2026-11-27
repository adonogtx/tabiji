package dev.adonogtx.tabiji.commons;

import dev.adonogtx.tabiji.domain.Seat;

public class SeatUtils {
    public Seat.SeatBuilder newSeatBuilder() {
        return Seat.builder().carNumber(3).seatCode("12A");
    }

    public Seat newSeat() {
        return newSeatBuilder().build();
    }
}
