package io.jh.web.rest;

import io.jh.service.FoodCategoryService;
import io.jh.web.rest.errors.BadRequestAlertException;
import io.jh.service.dto.FoodCategoryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.jh.domain.FoodCategory}.
 */
@RestController
@RequestMapping("/api")
public class FoodCategoryResource {

    private final Logger log = LoggerFactory.getLogger(FoodCategoryResource.class);

    private static final String ENTITY_NAME = "foodCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FoodCategoryService foodCategoryService;

    public FoodCategoryResource(FoodCategoryService foodCategoryService) {
        this.foodCategoryService = foodCategoryService;
    }

    /**
     * {@code POST  /food-categories} : Create a new foodCategory.
     *
     * @param foodCategoryDTO the foodCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foodCategoryDTO, or with status {@code 400 (Bad Request)} if the foodCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/food-categories")
    public ResponseEntity<FoodCategoryDTO> createFoodCategory(@Valid @RequestBody FoodCategoryDTO foodCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save FoodCategory : {}", foodCategoryDTO);
        if (foodCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new foodCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FoodCategoryDTO result = foodCategoryService.save(foodCategoryDTO);
        return ResponseEntity.created(new URI("/api/food-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /food-categories} : Updates an existing foodCategory.
     *
     * @param foodCategoryDTO the foodCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foodCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the foodCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foodCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/food-categories")
    public ResponseEntity<FoodCategoryDTO> updateFoodCategory(@Valid @RequestBody FoodCategoryDTO foodCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update FoodCategory : {}", foodCategoryDTO);
        if (foodCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FoodCategoryDTO result = foodCategoryService.save(foodCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, foodCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /food-categories} : get all the foodCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foodCategories in body.
     */
    @GetMapping("/food-categories")
    public List<FoodCategoryDTO> getAllFoodCategories() {
        log.debug("REST request to get all FoodCategories");
        return foodCategoryService.findAll();
    }

    /**
     * {@code GET  /food-categories/:id} : get the "id" foodCategory.
     *
     * @param id the id of the foodCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foodCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/food-categories/{id}")
    public ResponseEntity<FoodCategoryDTO> getFoodCategory(@PathVariable Long id) {
        log.debug("REST request to get FoodCategory : {}", id);
        Optional<FoodCategoryDTO> foodCategoryDTO = foodCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foodCategoryDTO);
    }

    /**
     * {@code DELETE  /food-categories/:id} : delete the "id" foodCategory.
     *
     * @param id the id of the foodCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/food-categories/{id}")
    public ResponseEntity<Void> deleteFoodCategory(@PathVariable Long id) {
        log.debug("REST request to delete FoodCategory : {}", id);
        foodCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
