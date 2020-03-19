package io.jh.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.jh.web.rest.TestUtil;

public class WeekStockDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeekStockDTO.class);
        WeekStockDTO weekStockDTO1 = new WeekStockDTO();
        weekStockDTO1.setId(1L);
        WeekStockDTO weekStockDTO2 = new WeekStockDTO();
        assertThat(weekStockDTO1).isNotEqualTo(weekStockDTO2);
        weekStockDTO2.setId(weekStockDTO1.getId());
        assertThat(weekStockDTO1).isEqualTo(weekStockDTO2);
        weekStockDTO2.setId(2L);
        assertThat(weekStockDTO1).isNotEqualTo(weekStockDTO2);
        weekStockDTO1.setId(null);
        assertThat(weekStockDTO1).isNotEqualTo(weekStockDTO2);
    }
}
