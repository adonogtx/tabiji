package dev.adonogtx.tabiji.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
public class Station {
    private String name;
    @EqualsAndHashCode.Include
    private String code;

}
