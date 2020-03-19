package io.jh.web.rest;

import io.jh.JhApp;
import io.jh.domain.WeekStock;
import io.jh.repository.WeekStockRepository;
import io.jh.service.WeekStockService;
import io.jh.service.dto.WeekStockDTO;
import io.jh.service.mapper.WeekStockMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link WeekStockResource} REST controller.
 */
@SpringBootTest(classes = JhApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WeekStockResourceIT {

    private static final String DEFAULT_SHOP_SN = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_SN = "BBBBBBBBBB";

    private static final String DEFAULT_SHOP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SHOP_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_FOOD_QUANTITY = 0;
    private static final Integer UPDATED_FOOD_QUANTITY = 1;

    @Autowired
    private WeekStockRepository weekStockRepository;

    @Autowired
    private WeekStockMapper weekStockMapper;

    @Autowired
    private WeekStockService weekStockService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWeekStockMockMvc;

    private WeekStock weekStock;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeekStock createEntity(EntityManager em) {
        WeekStock weekStock = new WeekStock()
            .shopSN(DEFAULT_SHOP_SN)
            .shopName(DEFAULT_SHOP_NAME)
            .foodQuantity(DEFAULT_FOOD_QUANTITY);
        return weekStock;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeekStock createUpdatedEntity(EntityManager em) {
        WeekStock weekStock = new WeekStock()
            .shopSN(UPDATED_SHOP_SN)
            .shopName(UPDATED_SHOP_NAME)
            .foodQuantity(UPDATED_FOOD_QUANTITY);
        return weekStock;
    }

    @BeforeEach
    public void initTest() {
        weekStock = createEntity(em);
    }

    @Test
    @Transactional
    public void createWeekStock() throws Exception {
        int databaseSizeBeforeCreate = weekStockRepository.findAll().size();

        // Create the WeekStock
        WeekStockDTO weekStockDTO = weekStockMapper.toDto(weekStock);
        restWeekStockMockMvc.perform(post("/api/week-stocks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekStockDTO)))
            .andExpect(status().isCreated());

        // Validate the WeekStock in the database
        List<WeekStock> weekStockList = weekStockRepository.findAll();
        assertThat(weekStockList).hasSize(databaseSizeBeforeCreate + 1);
        WeekStock testWeekStock = weekStockList.get(weekStockList.size() - 1);
        assertThat(testWeekStock.getShopSN()).isEqualTo(DEFAULT_SHOP_SN);
        assertThat(testWeekStock.getShopName()).isEqualTo(DEFAULT_SHOP_NAME);
        assertThat(testWeekStock.getFoodQuantity()).isEqualTo(DEFAULT_FOOD_QUANTITY);
    }

    @Test
    @Transactional
    public void createWeekStockWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = weekStockRepository.findAll().size();

        // Create the WeekStock with an existing ID
        weekStock.setId(1L);
        WeekStockDTO weekStockDTO = weekStockMapper.toDto(weekStock);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWeekStockMockMvc.perform(post("/api/week-stocks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekStockDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WeekStock in the database
        List<WeekStock> weekStockList = weekStockRepository.findAll();
        assertThat(weekStockList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkShopSNIsRequired() throws Exception {
        int databaseSizeBeforeTest = weekStockRepository.findAll().size();
        // set the field null
        weekStock.setShopSN(null);

        // Create the WeekStock, which fails.
        WeekStockDTO weekStockDTO = weekStockMapper.toDto(weekStock);

        restWeekStockMockMvc.perform(post("/api/week-stocks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekStockDTO)))
            .andExpect(status().isBadRequest());

        List<WeekStock> weekStockList = weekStockRepository.findAll();
        assertThat(weekStockList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkShopNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = weekStockRepository.findAll().size();
        // set the field null
        weekStock.setShopName(null);

        // Create the WeekStock, which fails.
        WeekStockDTO weekStockDTO = weekStockMapper.toDto(weekStock);

        restWeekStockMockMvc.perform(post("/api/week-stocks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekStockDTO)))
            .andExpect(status().isBadRequest());

        List<WeekStock> weekStockList = weekStockRepository.findAll();
        assertThat(weekStockList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFoodQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = weekStockRepository.findAll().size();
        // set the field null
        weekStock.setFoodQuantity(null);

        // Create the WeekStock, which fails.
        WeekStockDTO weekStockDTO = weekStockMapper.toDto(weekStock);

        restWeekStockMockMvc.perform(post("/api/week-stocks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekStockDTO)))
            .andExpect(status().isBadRequest());

        List<WeekStock> weekStockList = weekStockRepository.findAll();
        assertThat(weekStockList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWeekStocks() throws Exception {
        // Initialize the database
        weekStockRepository.saveAndFlush(weekStock);

        // Get all the weekStockList
        restWeekStockMockMvc.perform(get("/api/week-stocks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(weekStock.getId().intValue())))
            .andExpect(jsonPath("$.[*].shopSN").value(hasItem(DEFAULT_SHOP_SN)))
            .andExpect(jsonPath("$.[*].shopName").value(hasItem(DEFAULT_SHOP_NAME)))
            .andExpect(jsonPath("$.[*].foodQuantity").value(hasItem(DEFAULT_FOOD_QUANTITY)));
    }
    
    @Test
    @Transactional
    public void getWeekStock() throws Exception {
        // Initialize the database
        weekStockRepository.saveAndFlush(weekStock);

        // Get the weekStock
        restWeekStockMockMvc.perform(get("/api/week-stocks/{id}", weekStock.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(weekStock.getId().intValue()))
            .andExpect(jsonPath("$.shopSN").value(DEFAULT_SHOP_SN))
            .andExpect(jsonPath("$.shopName").value(DEFAULT_SHOP_NAME))
            .andExpect(jsonPath("$.foodQuantity").value(DEFAULT_FOOD_QUANTITY));
    }

    @Test
    @Transactional
    public void getNonExistingWeekStock() throws Exception {
        // Get the weekStock
        restWeekStockMockMvc.perform(get("/api/week-stocks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWeekStock() throws Exception {
        // Initialize the database
        weekStockRepository.saveAndFlush(weekStock);

        int databaseSizeBeforeUpdate = weekStockRepository.findAll().size();

        // Update the weekStock
        WeekStock updatedWeekStock = weekStockRepository.findById(weekStock.getId()).get();
        // Disconnect from session so that the updates on updatedWeekStock are not directly saved in db
        em.detach(updatedWeekStock);
        updatedWeekStock
            .shopSN(UPDATED_SHOP_SN)
            .shopName(UPDATED_SHOP_NAME)
            .foodQuantity(UPDATED_FOOD_QUANTITY);
        WeekStockDTO weekStockDTO = weekStockMapper.toDto(updatedWeekStock);

        restWeekStockMockMvc.perform(put("/api/week-stocks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekStockDTO)))
            .andExpect(status().isOk());

        // Validate the WeekStock in the database
        List<WeekStock> weekStockList = weekStockRepository.findAll();
        assertThat(weekStockList).hasSize(databaseSizeBeforeUpdate);
        WeekStock testWeekStock = weekStockList.get(weekStockList.size() - 1);
        assertThat(testWeekStock.getShopSN()).isEqualTo(UPDATED_SHOP_SN);
        assertThat(testWeekStock.getShopName()).isEqualTo(UPDATED_SHOP_NAME);
        assertThat(testWeekStock.getFoodQuantity()).isEqualTo(UPDATED_FOOD_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingWeekStock() throws Exception {
        int databaseSizeBeforeUpdate = weekStockRepository.findAll().size();

        // Create the WeekStock
        WeekStockDTO weekStockDTO = weekStockMapper.toDto(weekStock);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWeekStockMockMvc.perform(put("/api/week-stocks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weekStockDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WeekStock in the database
        List<WeekStock> weekStockList = weekStockRepository.findAll();
        assertThat(weekStockList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWeekStock() throws Exception {
        // Initialize the database
        weekStockRepository.saveAndFlush(weekStock);

        int databaseSizeBeforeDelete = weekStockRepository.findAll().size();

        // Delete the weekStock
        restWeekStockMockMvc.perform(delete("/api/week-stocks/{id}", weekStock.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WeekStock> weekStockList = weekStockRepository.findAll();
        assertThat(weekStockList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
