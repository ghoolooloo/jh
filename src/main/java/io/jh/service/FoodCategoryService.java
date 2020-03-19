package io.jh.service;

import io.jh.domain.FoodCategory;
import io.jh.repository.FoodCategoryRepository;
import io.jh.service.dto.FoodCategoryDTO;
import io.jh.service.mapper.FoodCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FoodCategory}.
 */
@Service
@Transactional
public class FoodCategoryService {

    private final Logger log = LoggerFactory.getLogger(FoodCategoryService.class);

    private final FoodCategoryRepository foodCategoryRepository;

    private final FoodCategoryMapper foodCategoryMapper;

    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository, FoodCategoryMapper foodCategoryMapper) {
        this.foodCategoryRepository = foodCategoryRepository;
        this.foodCategoryMapper = foodCategoryMapper;
    }

    /**
     * Save a foodCategory.
     *
     * @param foodCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public FoodCategoryDTO save(FoodCategoryDTO foodCategoryDTO) {
        log.debug("Request to save FoodCategory : {}", foodCategoryDTO);
        FoodCategory foodCategory = foodCategoryMapper.toEntity(foodCategoryDTO);
        foodCategory = foodCategoryRepository.save(foodCategory);
        return foodCategoryMapper.toDto(foodCategory);
    }

    /**
     * Get all the foodCategories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FoodCategoryDTO> findAll() {
        log.debug("Request to get all FoodCategories");
        return foodCategoryRepository.findAll().stream()
            .map(foodCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one foodCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FoodCategoryDTO> findOne(Long id) {
        log.debug("Request to get FoodCategory : {}", id);
        return foodCategoryRepository.findById(id)
            .map(foodCategoryMapper::toDto);
    }

    /**
     * Delete the foodCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FoodCategory : {}", id);
        foodCategoryRepository.deleteById(id);
    }
}
