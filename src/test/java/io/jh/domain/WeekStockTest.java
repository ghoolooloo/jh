package io.jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.jh.web.rest.TestUtil;

public class WeekStockTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeekStock.class);
        WeekStock weekStock1 = new WeekStock();
        weekStock1.setId(1L);
        WeekStock weekStock2 = new WeekStock();
        weekStock2.setId(weekStock1.getId());
        assertThat(weekStock1).isEqualTo(weekStock2);
        weekStock2.setId(2L);
        assertThat(weekStock1).isNotEqualTo(weekStock2);
        weekStock1.setId(null);
        assertThat(weekStock1).isNotEqualTo(weekStock2);
    }
}
