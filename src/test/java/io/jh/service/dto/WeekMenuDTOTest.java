package io.jh.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.jh.web.rest.TestUtil;

public class WeekMenuDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeekMenuDTO.class);
        WeekMenuDTO weekMenuDTO1 = new WeekMenuDTO();
        weekMenuDTO1.setId(1L);
        WeekMenuDTO weekMenuDTO2 = new WeekMenuDTO();
        assertThat(weekMenuDTO1).isNotEqualTo(weekMenuDTO2);
        weekMenuDTO2.setId(weekMenuDTO1.getId());
        assertThat(weekMenuDTO1).isEqualTo(weekMenuDTO2);
        weekMenuDTO2.setId(2L);
        assertThat(weekMenuDTO1).isNotEqualTo(weekMenuDTO2);
        weekMenuDTO1.setId(null);
        assertThat(weekMenuDTO1).isNotEqualTo(weekMenuDTO2);
    }
}
