package io.jh.service;

import io.jh.domain.WeekMenu;
import io.jh.repository.WeekMenuRepository;
import io.jh.service.dto.WeekMenuDTO;
import io.jh.service.mapper.WeekMenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WeekMenu}.
 */
@Service
@Transactional
public class WeekMenuService {

    private final Logger log = LoggerFactory.getLogger(WeekMenuService.class);

    private final WeekMenuRepository weekMenuRepository;

    private final WeekMenuMapper weekMenuMapper;

    public WeekMenuService(WeekMenuRepository weekMenuRepository, WeekMenuMapper weekMenuMapper) {
        this.weekMenuRepository = weekMenuRepository;
        this.weekMenuMapper = weekMenuMapper;
    }

    /**
     * Save a weekMenu.
     *
     * @param weekMenuDTO the entity to save.
     * @return the persisted entity.
     */
    public WeekMenuDTO save(WeekMenuDTO weekMenuDTO) {
        log.debug("Request to save WeekMenu : {}", weekMenuDTO);
        WeekMenu weekMenu = weekMenuMapper.toEntity(weekMenuDTO);
        weekMenu = weekMenuRepository.save(weekMenu);
        return weekMenuMapper.toDto(weekMenu);
    }

    /**
     * Get all the weekMenus.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WeekMenuDTO> findAll() {
        log.debug("Request to get all WeekMenus");
        return weekMenuRepository.findAll().stream()
            .map(weekMenuMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one weekMenu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WeekMenuDTO> findOne(Long id) {
        log.debug("Request to get WeekMenu : {}", id);
        return weekMenuRepository.findById(id)
            .map(weekMenuMapper::toDto);
    }

    /**
     * Delete the weekMenu by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WeekMenu : {}", id);
        weekMenuRepository.deleteById(id);
    }
}
