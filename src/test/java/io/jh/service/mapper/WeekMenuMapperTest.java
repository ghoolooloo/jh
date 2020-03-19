package io.jh.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WeekMenuMapperTest {

    private WeekMenuMapper weekMenuMapper;

    @BeforeEach
    public void setUp() {
        weekMenuMapper = new WeekMenuMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(weekMenuMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(weekMenuMapper.fromId(null)).isNull();
    }
}
