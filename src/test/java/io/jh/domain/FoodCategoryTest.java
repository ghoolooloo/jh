package io.jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.jh.web.rest.TestUtil;

public class FoodCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FoodCategory.class);
        FoodCategory foodCategory1 = new FoodCategory();
        foodCategory1.setId(1L);
        FoodCategory foodCategory2 = new FoodCategory();
        foodCategory2.setId(foodCategory1.getId());
        assertThat(foodCategory1).isEqualTo(foodCategory2);
        foodCategory2.setId(2L);
        assertThat(foodCategory1).isNotEqualTo(foodCategory2);
        foodCategory1.setId(null);
        assertThat(foodCategory1).isNotEqualTo(foodCategory2);
    }
}
