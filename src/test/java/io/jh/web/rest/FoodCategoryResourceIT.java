package io.jh.web.rest;

import io.jh.JhApp;
import io.jh.domain.FoodCategory;
import io.jh.repository.FoodCategoryRepository;
import io.jh.service.FoodCategoryService;
import io.jh.service.dto.FoodCategoryDTO;
import io.jh.service.mapper.FoodCategoryMapper;

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

/**
 * Integration tests for the {@link FoodCategoryResource} REST controller.
 */
@SpringBootTest(classes = JhApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class FoodCategoryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SN = "AAAAAAAAAA";
    private static final String UPDATED_SN = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT = 0;
    private static final Integer UPDATED_SORT = 1;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    @Autowired
    private FoodCategoryRepository foodCategoryRepository;

    @Autowired
    private FoodCategoryMapper foodCategoryMapper;

    @Autowired
    private FoodCategoryService foodCategoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFoodCategoryMockMvc;

    private FoodCategory foodCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoodCategory createEntity(EntityManager em) {
        FoodCategory foodCategory = new FoodCategory()
            .name(DEFAULT_NAME)
            .sn(DEFAULT_SN)
            .icon(DEFAULT_ICON)
            .sort(DEFAULT_SORT)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return foodCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoodCategory createUpdatedEntity(EntityManager em) {
        FoodCategory foodCategory = new FoodCategory()
            .name(UPDATED_NAME)
            .sn(UPDATED_SN)
            .icon(UPDATED_ICON)
            .sort(UPDATED_SORT)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return foodCategory;
    }

    @BeforeEach
    public void initTest() {
        foodCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createFoodCategory() throws Exception {
        int databaseSizeBeforeCreate = foodCategoryRepository.findAll().size();

        // Create the FoodCategory
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(foodCategory);
        restFoodCategoryMockMvc.perform(post("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the FoodCategory in the database
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        FoodCategory testFoodCategory = foodCategoryList.get(foodCategoryList.size() - 1);
        assertThat(testFoodCategory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFoodCategory.getSn()).isEqualTo(DEFAULT_SN);
        assertThat(testFoodCategory.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testFoodCategory.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testFoodCategory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFoodCategory.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFoodCategory.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFoodCategory.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createFoodCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = foodCategoryRepository.findAll().size();

        // Create the FoodCategory with an existing ID
        foodCategory.setId(1L);
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(foodCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFoodCategoryMockMvc.perform(post("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FoodCategory in the database
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodCategoryRepository.findAll().size();
        // set the field null
        foodCategory.setName(null);

        // Create the FoodCategory, which fails.
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(foodCategory);

        restFoodCategoryMockMvc.perform(post("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSnIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodCategoryRepository.findAll().size();
        // set the field null
        foodCategory.setSn(null);

        // Create the FoodCategory, which fails.
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(foodCategory);

        restFoodCategoryMockMvc.perform(post("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSortIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodCategoryRepository.findAll().size();
        // set the field null
        foodCategory.setSort(null);

        // Create the FoodCategory, which fails.
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(foodCategory);

        restFoodCategoryMockMvc.perform(post("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodCategoryRepository.findAll().size();
        // set the field null
        foodCategory.setCreatedBy(null);

        // Create the FoodCategory, which fails.
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(foodCategory);

        restFoodCategoryMockMvc.perform(post("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodCategoryRepository.findAll().size();
        // set the field null
        foodCategory.setCreatedDate(null);

        // Create the FoodCategory, which fails.
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(foodCategory);

        restFoodCategoryMockMvc.perform(post("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isBadRequest());

        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFoodCategories() throws Exception {
        // Initialize the database
        foodCategoryRepository.saveAndFlush(foodCategory);

        // Get all the foodCategoryList
        restFoodCategoryMockMvc.perform(get("/api/food-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(foodCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sn").value(hasItem(DEFAULT_SN)))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }
    
    @Test
    @Transactional
    public void getFoodCategory() throws Exception {
        // Initialize the database
        foodCategoryRepository.saveAndFlush(foodCategory);

        // Get the foodCategory
        restFoodCategoryMockMvc.perform(get("/api/food-categories/{id}", foodCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(foodCategory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.sn").value(DEFAULT_SN))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON))
            .andExpect(jsonPath("$.sort").value(DEFAULT_SORT))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    public void getNonExistingFoodCategory() throws Exception {
        // Get the foodCategory
        restFoodCategoryMockMvc.perform(get("/api/food-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFoodCategory() throws Exception {
        // Initialize the database
        foodCategoryRepository.saveAndFlush(foodCategory);

        int databaseSizeBeforeUpdate = foodCategoryRepository.findAll().size();

        // Update the foodCategory
        FoodCategory updatedFoodCategory = foodCategoryRepository.findById(foodCategory.getId()).get();
        // Disconnect from session so that the updates on updatedFoodCategory are not directly saved in db
        em.detach(updatedFoodCategory);
        updatedFoodCategory
            .name(UPDATED_NAME)
            .sn(UPDATED_SN)
            .icon(UPDATED_ICON)
            .sort(UPDATED_SORT)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(updatedFoodCategory);

        restFoodCategoryMockMvc.perform(put("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the FoodCategory in the database
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeUpdate);
        FoodCategory testFoodCategory = foodCategoryList.get(foodCategoryList.size() - 1);
        assertThat(testFoodCategory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFoodCategory.getSn()).isEqualTo(UPDATED_SN);
        assertThat(testFoodCategory.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testFoodCategory.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testFoodCategory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFoodCategory.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFoodCategory.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFoodCategory.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingFoodCategory() throws Exception {
        int databaseSizeBeforeUpdate = foodCategoryRepository.findAll().size();

        // Create the FoodCategory
        FoodCategoryDTO foodCategoryDTO = foodCategoryMapper.toDto(foodCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFoodCategoryMockMvc.perform(put("/api/food-categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FoodCategory in the database
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFoodCategory() throws Exception {
        // Initialize the database
        foodCategoryRepository.saveAndFlush(foodCategory);

        int databaseSizeBeforeDelete = foodCategoryRepository.findAll().size();

        // Delete the foodCategory
        restFoodCategoryMockMvc.perform(delete("/api/food-categories/{id}", foodCategory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAll();
        assertThat(foodCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
