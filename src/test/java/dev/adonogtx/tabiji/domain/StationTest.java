package dev.adonogtx.tabiji.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class StationTest {

    @Test
    @DisplayName("Create station with valid data")
    void createStation() {
        Station station = Station.builder()
                .name("Tokyo")
                .code("TYO")
                .build();

        assertThat(station)
                .isNotNull()
                .extracting(Station::getName, Station::getCode)
                .containsExactly("Tokyo", "TYO");
    }

    @Test
    @DisplayName("Throw exception when code is null")
    void create_ThrowsException_WhenCodeIsNull() {
        assertThatThrownBy(() -> Station.builder()
                .name("Shinjuku")
                .code(null)
                .build())
                .isInstanceOf(StationException.class)
                .hasMessage("Station code is required");
    }

    @Test
    @DisplayName("Throw exception when code is blank")
    void create_ThrowsException_WhenCodeIsBlank() {
        assertThatThrownBy(() -> Station.builder()
                .name("Shinjuku")
                .code("")
                .build())
                .isInstanceOf(StationException.class)
                .hasMessage("Station code is required");
    }

    @Test
    @DisplayName("Throw exception when name is null")
    void create_ThrowsException_WhenNameIsNull() {
        assertThatThrownBy(() -> Station.builder()
                .name(null)
                .code("SHI")
                .build())
                .isInstanceOf(StationException.class)
                .hasMessage("Station name is required");
    }

    @Test
    @DisplayName("Throw exception when name is blank")
    void create_ThrowsException_WhenNameIsBlank() {
        assertThatThrownBy(() -> Station.builder()
                .name("")
                .code("SHI")
                .build())
                .isInstanceOf(StationException.class)
                .hasMessage("Station name is required");
    }

}
