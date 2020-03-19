package io.jh.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WeekStockMapperTest {

    private WeekStockMapper weekStockMapper;

    @BeforeEach
    public void setUp() {
        weekStockMapper = new WeekStockMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(weekStockMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(weekStockMapper.fromId(null)).isNull();
    }
}
