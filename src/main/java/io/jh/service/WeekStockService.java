package io.jh.service;

import io.jh.domain.WeekStock;
import io.jh.repository.WeekStockRepository;
import io.jh.service.dto.WeekStockDTO;
import io.jh.service.mapper.WeekStockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WeekStock}.
 */
@Service
@Transactional
public class WeekStockService {

    private final Logger log = LoggerFactory.getLogger(WeekStockService.class);

    private final WeekStockRepository weekStockRepository;

    private final WeekStockMapper weekStockMapper;

    public WeekStockService(WeekStockRepository weekStockRepository, WeekStockMapper weekStockMapper) {
        this.weekStockRepository = weekStockRepository;
        this.weekStockMapper = weekStockMapper;
    }

    /**
     * Save a weekStock.
     *
     * @param weekStockDTO the entity to save.
     * @return the persisted entity.
     */
    public WeekStockDTO save(WeekStockDTO weekStockDTO) {
        log.debug("Request to save WeekStock : {}", weekStockDTO);
        WeekStock weekStock = weekStockMapper.toEntity(weekStockDTO);
        weekStock = weekStockRepository.save(weekStock);
        return weekStockMapper.toDto(weekStock);
    }

    /**
     * Get all the weekStocks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WeekStockDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WeekStocks");
        return weekStockRepository.findAll(pageable)
            .map(weekStockMapper::toDto);
    }

    /**
     * Get one weekStock by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WeekStockDTO> findOne(Long id) {
        log.debug("Request to get WeekStock : {}", id);
        return weekStockRepository.findById(id)
            .map(weekStockMapper::toDto);
    }

    /**
     * Delete the weekStock by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WeekStock : {}", id);
        weekStockRepository.deleteById(id);
    }
}
