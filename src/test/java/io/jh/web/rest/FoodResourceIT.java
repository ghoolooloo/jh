package io.jh.web.rest;

import io.jh.JhApp;
import io.jh.domain.Food;
import io.jh.repository.FoodRepository;
import io.jh.service.FoodService;
import io.jh.service.dto.FoodDTO;
import io.jh.service.mapper.FoodMapper;

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

import io.jh.domain.enumeration.FeedingMode;
/**
 * Integration tests for the {@link FoodResource} REST controller.
 */
@SpringBootTest(classes = JhApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class FoodResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SN = "AAAAAAAAAA";
    private static final String UPDATED_SN = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRICE = 0;
    private static final Integer UPDATED_PRICE = 1;

    private static final Integer DEFAULT_ORIGINAL_PRICE = 0;
    private static final Integer UPDATED_ORIGINAL_PRICE = 1;

    private static final Integer DEFAULT_COST = 0;
    private static final Integer UPDATED_COST = 1;

    private static final Integer DEFAULT_PACKING_FEE = 0;
    private static final Integer UPDATED_PACKING_FEE = 1;

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORT = 0;
    private static final Integer UPDATED_SORT = 1;

    private static final FeedingMode DEFAULT_FEEDING_MODE = FeedingMode.DINNER;
    private static final FeedingMode UPDATED_FEEDING_MODE = FeedingMode.DESSERT;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private FoodService foodService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFoodMockMvc;

    private Food food;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Food createEntity(EntityManager em) {
        Food food = new Food()
            .name(DEFAULT_NAME)
            .sn(DEFAULT_SN)
            .thumbnail(DEFAULT_THUMBNAIL)
            .picture(DEFAULT_PICTURE)
            .price(DEFAULT_PRICE)
            .originalPrice(DEFAULT_ORIGINAL_PRICE)
            .cost(DEFAULT_COST)
            .packingFee(DEFAULT_PACKING_FEE)
            .desc(DEFAULT_DESC)
            .sort(DEFAULT_SORT)
            .feedingMode(DEFAULT_FEEDING_MODE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
        return food;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Food createUpdatedEntity(EntityManager em) {
        Food food = new Food()
            .name(UPDATED_NAME)
            .sn(UPDATED_SN)
            .thumbnail(UPDATED_THUMBNAIL)
            .picture(UPDATED_PICTURE)
            .price(UPDATED_PRICE)
            .originalPrice(UPDATED_ORIGINAL_PRICE)
            .cost(UPDATED_COST)
            .packingFee(UPDATED_PACKING_FEE)
            .desc(UPDATED_DESC)
            .sort(UPDATED_SORT)
            .feedingMode(UPDATED_FEEDING_MODE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        return food;
    }

    @BeforeEach
    public void initTest() {
        food = createEntity(em);
    }

    @Test
    @Transactional
    public void createFood() throws Exception {
        int databaseSizeBeforeCreate = foodRepository.findAll().size();

        // Create the Food
        FoodDTO foodDTO = foodMapper.toDto(food);
        restFoodMockMvc.perform(post("/api/foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodDTO)))
            .andExpect(status().isCreated());

        // Validate the Food in the database
        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeCreate + 1);
        Food testFood = foodList.get(foodList.size() - 1);
        assertThat(testFood.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFood.getSn()).isEqualTo(DEFAULT_SN);
        assertThat(testFood.getThumbnail()).isEqualTo(DEFAULT_THUMBNAIL);
        assertThat(testFood.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testFood.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testFood.getOriginalPrice()).isEqualTo(DEFAULT_ORIGINAL_PRICE);
        assertThat(testFood.getCost()).isEqualTo(DEFAULT_COST);
        assertThat(testFood.getPackingFee()).isEqualTo(DEFAULT_PACKING_FEE);
        assertThat(testFood.getDesc()).isEqualTo(DEFAULT_DESC);
        assertThat(testFood.getSort()).isEqualTo(DEFAULT_SORT);
        assertThat(testFood.getFeedingMode()).isEqualTo(DEFAULT_FEEDING_MODE);
        assertThat(testFood.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testFood.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFood.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testFood.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void createFoodWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = foodRepository.findAll().size();

        // Create the Food with an existing ID
        food.setId(1L);
        FoodDTO foodDTO = foodMapper.toDto(food);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFoodMockMvc.perform(post("/api/foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Food in the database
        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodRepository.findAll().size();
        // set the field null
        food.setName(null);

        // Create the Food, which fails.
        FoodDTO foodDTO = foodMapper.toDto(food);

        restFoodMockMvc.perform(post("/api/foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodDTO)))
            .andExpect(status().isBadRequest());

        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSnIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodRepository.findAll().size();
        // set the field null
        food.setSn(null);

        // Create the Food, which fails.
        FoodDTO foodDTO = foodMapper.toDto(food);

        restFoodMockMvc.perform(post("/api/foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodDTO)))
            .andExpect(status().isBadRequest());

        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodRepository.findAll().size();
        // set the field null
        food.setPrice(null);

        // Create the Food, which fails.
        FoodDTO foodDTO = foodMapper.toDto(food);

        restFoodMockMvc.perform(post("/api/foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodDTO)))
            .andExpect(status().isBadRequest());

        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSortIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodRepository.findAll().size();
        // set the field null
        food.setSort(null);

        // Create the Food, which fails.
        FoodDTO foodDTO = foodMapper.toDto(food);

        restFoodMockMvc.perform(post("/api/foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodDTO)))
            .andExpect(status().isBadRequest());

        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFeedingModeIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodRepository.findAll().size();
        // set the field null
        food.setFeedingMode(null);

        // Create the Food, which fails.
        FoodDTO foodDTO = foodMapper.toDto(food);

        restFoodMockMvc.perform(post("/api/foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodDTO)))
            .andExpect(status().isBadRequest());

        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodRepository.findAll().size();
        // set the field null
        food.setCreatedBy(null);

        // Create the Food, which fails.
        FoodDTO foodDTO = foodMapper.toDto(food);

        restFoodMockMvc.perform(post("/api/foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodDTO)))
            .andExpect(status().isBadRequest());

        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = foodRepository.findAll().size();
        // set the field null
        food.setCreatedDate(null);

        // Create the Food, which fails.
        FoodDTO foodDTO = foodMapper.toDto(food);

        restFoodMockMvc.perform(post("/api/foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodDTO)))
            .andExpect(status().isBadRequest());

        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFoods() throws Exception {
        // Initialize the database
        foodRepository.saveAndFlush(food);

        // Get all the foodList
        restFoodMockMvc.perform(get("/api/foods?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(food.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sn").value(hasItem(DEFAULT_SN)))
            .andExpect(jsonPath("$.[*].thumbnail").value(hasItem(DEFAULT_THUMBNAIL)))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)))
            .andExpect(jsonPath("$.[*].originalPrice").value(hasItem(DEFAULT_ORIGINAL_PRICE)))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST)))
            .andExpect(jsonPath("$.[*].packingFee").value(hasItem(DEFAULT_PACKING_FEE)))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)))
            .andExpect(jsonPath("$.[*].sort").value(hasItem(DEFAULT_SORT)))
            .andExpect(jsonPath("$.[*].feedingMode").value(hasItem(DEFAULT_FEEDING_MODE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)));
    }
    
    @Test
    @Transactional
    public void getFood() throws Exception {
        // Initialize the database
        foodRepository.saveAndFlush(food);

        // Get the food
        restFoodMockMvc.perform(get("/api/foods/{id}", food.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(food.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.sn").value(DEFAULT_SN))
            .andExpect(jsonPath("$.thumbnail").value(DEFAULT_THUMBNAIL))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE))
            .andExpect(jsonPath("$.originalPrice").value(DEFAULT_ORIGINAL_PRICE))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST))
            .andExpect(jsonPath("$.packingFee").value(DEFAULT_PACKING_FEE))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC))
            .andExpect(jsonPath("$.sort").value(DEFAULT_SORT))
            .andExpect(jsonPath("$.feedingMode").value(DEFAULT_FEEDING_MODE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
    }

    @Test
    @Transactional
    public void getNonExistingFood() throws Exception {
        // Get the food
        restFoodMockMvc.perform(get("/api/foods/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFood() throws Exception {
        // Initialize the database
        foodRepository.saveAndFlush(food);

        int databaseSizeBeforeUpdate = foodRepository.findAll().size();

        // Update the food
        Food updatedFood = foodRepository.findById(food.getId()).get();
        // Disconnect from session so that the updates on updatedFood are not directly saved in db
        em.detach(updatedFood);
        updatedFood
            .name(UPDATED_NAME)
            .sn(UPDATED_SN)
            .thumbnail(UPDATED_THUMBNAIL)
            .picture(UPDATED_PICTURE)
            .price(UPDATED_PRICE)
            .originalPrice(UPDATED_ORIGINAL_PRICE)
            .cost(UPDATED_COST)
            .packingFee(UPDATED_PACKING_FEE)
            .desc(UPDATED_DESC)
            .sort(UPDATED_SORT)
            .feedingMode(UPDATED_FEEDING_MODE)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
        FoodDTO foodDTO = foodMapper.toDto(updatedFood);

        restFoodMockMvc.perform(put("/api/foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodDTO)))
            .andExpect(status().isOk());

        // Validate the Food in the database
        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeUpdate);
        Food testFood = foodList.get(foodList.size() - 1);
        assertThat(testFood.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFood.getSn()).isEqualTo(UPDATED_SN);
        assertThat(testFood.getThumbnail()).isEqualTo(UPDATED_THUMBNAIL);
        assertThat(testFood.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testFood.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testFood.getOriginalPrice()).isEqualTo(UPDATED_ORIGINAL_PRICE);
        assertThat(testFood.getCost()).isEqualTo(UPDATED_COST);
        assertThat(testFood.getPackingFee()).isEqualTo(UPDATED_PACKING_FEE);
        assertThat(testFood.getDesc()).isEqualTo(UPDATED_DESC);
        assertThat(testFood.getSort()).isEqualTo(UPDATED_SORT);
        assertThat(testFood.getFeedingMode()).isEqualTo(UPDATED_FEEDING_MODE);
        assertThat(testFood.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testFood.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFood.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testFood.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingFood() throws Exception {
        int databaseSizeBeforeUpdate = foodRepository.findAll().size();

        // Create the Food
        FoodDTO foodDTO = foodMapper.toDto(food);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFoodMockMvc.perform(put("/api/foods")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(foodDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Food in the database
        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFood() throws Exception {
        // Initialize the database
        foodRepository.saveAndFlush(food);

        int databaseSizeBeforeDelete = foodRepository.findAll().size();

        // Delete the food
        restFoodMockMvc.perform(delete("/api/foods/{id}", food.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Food> foodList = foodRepository.findAll();
        assertThat(foodList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
