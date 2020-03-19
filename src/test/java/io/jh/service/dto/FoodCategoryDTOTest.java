package io.jh.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.jh.web.rest.TestUtil;

public class FoodCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FoodCategoryDTO.class);
        FoodCategoryDTO foodCategoryDTO1 = new FoodCategoryDTO();
        foodCategoryDTO1.setId(1L);
        FoodCategoryDTO foodCategoryDTO2 = new FoodCategoryDTO();
        assertThat(foodCategoryDTO1).isNotEqualTo(foodCategoryDTO2);
        foodCategoryDTO2.setId(foodCategoryDTO1.getId());
        assertThat(foodCategoryDTO1).isEqualTo(foodCategoryDTO2);
        foodCategoryDTO2.setId(2L);
        assertThat(foodCategoryDTO1).isNotEqualTo(foodCategoryDTO2);
        foodCategoryDTO1.setId(null);
        assertThat(foodCategoryDTO1).isNotEqualTo(foodCategoryDTO2);
    }
}
