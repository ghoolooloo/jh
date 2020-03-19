package io.jh.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.jh.web.rest.TestUtil;

public class WeekMenuTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeekMenu.class);
        WeekMenu weekMenu1 = new WeekMenu();
        weekMenu1.setId(1L);
        WeekMenu weekMenu2 = new WeekMenu();
        weekMenu2.setId(weekMenu1.getId());
        assertThat(weekMenu1).isEqualTo(weekMenu2);
        weekMenu2.setId(2L);
        assertThat(weekMenu1).isNotEqualTo(weekMenu2);
        weekMenu1.setId(null);
        assertThat(weekMenu1).isNotEqualTo(weekMenu2);
    }
}
