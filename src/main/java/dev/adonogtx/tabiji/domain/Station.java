package dev.adonogtx.tabiji.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Station {
    @EqualsAndHashCode.Include

    private final String code;
    private final String name;

    @Builder
    public Station(String code, String name) {
        if (!StringUtils.hasText(code)) {
            throw new StationException("Station code is required");
        }
        if (!StringUtils.hasText(name)){
            throw new StationException("Station name is required");
        }
        this.code = code;
        this.name = name;
    }

}
