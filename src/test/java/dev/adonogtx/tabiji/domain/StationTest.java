package dev.adonogtx.tabiji.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StationTest {

    @Test
    public void createStation() {
        Station station = Station.builder()
                .name("Tokyo")
                .code("TYO")
                .build();

        Assertions.assertThat(station.getName()).isEqualTo("Tokyo");
        Assertions.assertThat(station.getCode()).isEqualTo("TYO");
    }

    @Test
    public void createStationSameCodeDifferentName() {
        Station shinjuku = Station.builder()
                .name("Tokyo")
                .code("SHI")
                .build();

        Station shibuya = Station.builder()
                .name("Tokyo")
                .code("SHI")
                .build();

        Assertions.assertThat(shibuya.getCode()).isEqualTo(shinjuku.getCode());

    }


}
