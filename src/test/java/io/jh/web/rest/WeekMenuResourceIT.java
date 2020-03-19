package io.jh.web.rest;

import io.jh.JhApp;
import io.jh.domain.WeekMenu;
import io.jh.repository.WeekMenuRepository;
import io.jh.service.WeekMenuService;
import io.jh.service.dto.WeekMenuDTO;
import io.jh.service.mapper.WeekMenuMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.jh.domain.enumeration.Week;
/**
 * Integration tests for the {@link WeekMenuResource} REST controller.
 */
@SpringBootTest(classes = JhApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WeekMenuResourceIT {

    private static final Week DEFAULT_WEEK = Week.SUNDAY;
    private static final Week UPDATED_WEEK = Week.MONDAY;

    private static final Integer DEFAULT_SORT = 0;
    private static final Integer UPDATED_SORT = 1;

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    @Autowired
    private WeekMenuRepository weekMenuRepository;

    @Autowired
    private WeekMenuMapper weekMenuMapper;

    @Autowired
    private WeekMenuService weekMenuService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWeekMenuMockMvc;

    private WeekMenu weekMenu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeekMenu createEntity(EntityManager em) {
        WeekMenu weekMenu = new WeekMenu()
            .week(DEFAULT_WEEK)
            .sort(DEFAULT_SORT)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return weekMenu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeekMenu createUpdatedEntity(EntityManager em) {
        WeekMenu weekMenu = new WeekMenu()
            .week(UPDATED_WEEK)
            .sort(UPDATED_SORT)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return weekMenu;
    }

    @BeforeEach
    public void initTest() {
        weekMenu = createEntity(em);
    }

    @Test
    @Transactional
    public void createWeekMenu() throws Exception {
        int databaseSizeBeforeCreate = weekMenuRepository.findAll().size();

        // Create the WeekMenu
        WeekMenuDTO weekMenuDTO = weekMenuMapper.toDto(weekMenu);
        restWeekMenuMockMvc.perform(post("/api/week-menus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekMenuDTO)))
            .andExpect(status().isCreated());

        // Validate the WeekMenu in the database
        List<WeekMenu> weekMenuList = weekMenuRepository.findAll();
        assertThat(weekMenuList).hasSize(databaseSizeBeforeCreate + 1);
        WeekMenu testWeekMenu = weekMenuList.get(weekMenuList.size() - 1);
        assertThat(testWeekMenu.getWeek()).isEqualTo(DEFAULT_WEEK);
        assertThat(testWeekMenu.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testWeekMenu.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testWeekMenu.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createWeekMenuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = weekMenuRepository.findAll().size();

        // Create the WeekMenu with an existing ID
        weekMenu.setId(1L);
        WeekMenuDTO weekMenuDTO = weekMenuMapper.toDto(weekMenu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWeekMenuMockMvc.perform(post("/api/week-menus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekMenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WeekMenu in the database
        List<WeekMenu> weekMenuList = weekMenuRepository.findAll();
        assertThat(weekMenuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWeekIsRequired() throws Exception {
        int databaseSizeBeforeTest = weekMenuRepository.findAll().size();
        // set the field null
        weekMenu.setWeek(null);

        // Create the WeekMenu, which fails.
        WeekMenuDTO weekMenuDTO = weekMenuMapper.toDto(weekMenu);

        restWeekMenuMockMvc.perform(post("/api/week-menus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekMenuDTO)))
            .andExpect(status().isBadRequest());

        List<WeekMenu> weekMenuList = weekMenuRepository.findAll();
        assertThat(weekMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSortIsRequired() throws Exception {
        int databaseSizeBeforeTest = weekMenuRepository.findAll().size();
        // set the field null
        weekMenu.setSort(null);

        // Create the WeekMenu, which fails.
        WeekMenuDTO weekMenuDTO = weekMenuMapper.toDto(weekMenu);

        restWeekMenuMockMvc.perform(post("/api/week-menus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekMenuDTO)))
            .andExpect(status().isBadRequest());

        List<WeekMenu> weekMenuList = weekMenuRepository.findAll();
        assertThat(weekMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastModifiedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = weekMenuRepository.findAll().size();
        // set the field null
        weekMenu.setLastModifiedDate(null);

        // Create the WeekMenu, which fails.
        WeekMenuDTO weekMenuDTO = weekMenuMapper.toDto(weekMenu);

        restWeekMenuMockMvc.perform(post("/api/week-menus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekMenuDTO)))
            .andExpect(status().isBadRequest());

        List<WeekMenu> weekMenuList = weekMenuRepository.findAll();
        assertThat(weekMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastModifiedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = weekMenuRepository.findAll().size();
        // set the field null
        weekMenu.setLastModifiedBy(null);

        // Create the WeekMenu, which fails.
        WeekMenuDTO weekMenuDTO = weekMenuMapper.toDto(weekMenu);

        restWeekMenuMockMvc.perform(post("/api/week-menus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekMenuDTO)))
            .andExpect(status().isBadRequest());

        List<WeekMenu> weekMenuList = weekMenuRepository.findAll();
        assertThat(weekMenuList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWeekMenus() throws Exception {
        // Initialize the database
        weekMenuRepository.saveAndFlush(weekMenu);

        // Get all the weekMenuList
        restWeekMenuMockMvc.perform(get("/api/week-menus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(weekMenu.getId().intValue())))
            .andExpect(jsonPath("$.[*].week").value(hasItem(DEFAULT_WEEK.toString())))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }
    
    @Test
    @Transactional
    public void getWeekMenu() throws Exception {
        // Initialize the database
        weekMenuRepository.saveAndFlush(weekMenu);

        // Get the weekMenu
        restWeekMenuMockMvc.perform(get("/api/week-menus/{id}", weekMenu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(weekMenu.getId().intValue()))
            .andExpect(jsonPath("$.week").value(DEFAULT_WEEK.toString()))
            .andExpect(jsonPath("$.sort").value(DEFAULT_SORT))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    public void getNonExistingWeekMenu() throws Exception {
        // Get the weekMenu
        restWeekMenuMockMvc.perform(get("/api/week-menus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWeekMenu() throws Exception {
        // Initialize the database
        weekMenuRepository.saveAndFlush(weekMenu);

        int databaseSizeBeforeUpdate = weekMenuRepository.findAll().size();

        // Update the weekMenu
        WeekMenu updatedWeekMenu = weekMenuRepository.findById(weekMenu.getId()).get();
        // Disconnect from session so that the updates on updatedWeekMenu are not directly saved in db
        em.detach(updatedWeekMenu);
        updatedWeekMenu
            .week(UPDATED_WEEK)
            .sort(UPDATED_SORT)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        WeekMenuDTO weekMenuDTO = weekMenuMapper.toDto(updatedWeekMenu);

        restWeekMenuMockMvc.perform(put("/api/week-menus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekMenuDTO)))
            .andExpect(status().isOk());

        // Validate the WeekMenu in the database
        List<WeekMenu> weekMenuList = weekMenuRepository.findAll();
        assertThat(weekMenuList).hasSize(databaseSizeBeforeUpdate);
        WeekMenu testWeekMenu = weekMenuList.get(weekMenuList.size() - 1);
        assertThat(testWeekMenu.getWeek()).isEqualTo(UPDATED_WEEK);
        assertThat(testWeekMenu.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testWeekMenu.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testWeekMenu.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingWeekMenu() throws Exception {
        int databaseSizeBeforeUpdate = weekMenuRepository.findAll().size();

        // Create the WeekMenu
        WeekMenuDTO weekMenuDTO = weekMenuMapper.toDto(weekMenu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWeekMenuMockMvc.perform(put("/api/week-menus")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekMenuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WeekMenu in the database
        List<WeekMenu> weekMenuList = weekMenuRepository.findAll();
        assertThat(weekMenuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWeekMenu() throws Exception {
        // Initialize the database
        weekMenuRepository.saveAndFlush(weekMenu);

        int databaseSizeBeforeDelete = weekMenuRepository.findAll().size();

        // Delete the weekMenu
        restWeekMenuMockMvc.perform(delete("/api/week-menus/{id}", weekMenu.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WeekMenu> weekMenuList = weekMenuRepository.findAll();
        assertThat(weekMenuList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
