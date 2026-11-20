package dev.adonogtx.tabiji.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Seat {
    private final int carNumber;
    private final String seatCode;

    @Builder
    public Seat(Integer carNumber, String seatCode) {

        if (carNumber <= 0) {
            throw new DomainException("The field 'carNumber' must be greater than zero");
        }
        if (seatCode == null || seatCode.isBlank()) {
            throw new DomainException("The field 'seatCode' is required");
        }

        this.carNumber = carNumber;
        this.seatCode = seatCode;
    }
}
