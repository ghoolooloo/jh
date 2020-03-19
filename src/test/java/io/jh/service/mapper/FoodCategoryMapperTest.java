package io.jh.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FoodCategoryMapperTest {

    private FoodCategoryMapper foodCategoryMapper;

    @BeforeEach
    public void setUp() {
        foodCategoryMapper = new FoodCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(foodCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(foodCategoryMapper.fromId(null)).isNull();
    }
}
