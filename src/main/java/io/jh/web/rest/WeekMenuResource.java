package io.jh.web.rest;

import io.jh.service.WeekMenuService;
import io.jh.web.rest.errors.BadRequestAlertException;
import io.jh.service.dto.WeekMenuDTO;

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
 * REST controller for managing {@link io.jh.domain.WeekMenu}.
 */
@RestController
@RequestMapping("/api")
public class WeekMenuResource {

    private final Logger log = LoggerFactory.getLogger(WeekMenuResource.class);

    private static final String ENTITY_NAME = "weekMenu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WeekMenuService weekMenuService;

    public WeekMenuResource(WeekMenuService weekMenuService) {
        this.weekMenuService = weekMenuService;
    }

    /**
     * {@code POST  /week-menus} : Create a new weekMenu.
     *
     * @param weekMenuDTO the weekMenuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new weekMenuDTO, or with status {@code 400 (Bad Request)} if the weekMenu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/week-menus")
    public ResponseEntity<WeekMenuDTO> createWeekMenu(@Valid @RequestBody WeekMenuDTO weekMenuDTO) throws URISyntaxException {
        log.debug("REST request to save WeekMenu : {}", weekMenuDTO);
        if (weekMenuDTO.getId() != null) {
            throw new BadRequestAlertException("A new weekMenu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WeekMenuDTO result = weekMenuService.save(weekMenuDTO);
        return ResponseEntity.created(new URI("/api/week-menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /week-menus} : Updates an existing weekMenu.
     *
     * @param weekMenuDTO the weekMenuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated weekMenuDTO,
     * or with status {@code 400 (Bad Request)} if the weekMenuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the weekMenuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/week-menus")
    public ResponseEntity<WeekMenuDTO> updateWeekMenu(@Valid @RequestBody WeekMenuDTO weekMenuDTO) throws URISyntaxException {
        log.debug("REST request to update WeekMenu : {}", weekMenuDTO);
        if (weekMenuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WeekMenuDTO result = weekMenuService.save(weekMenuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, weekMenuDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /week-menus} : get all the weekMenus.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of weekMenus in body.
     */
    @GetMapping("/week-menus")
    public List<WeekMenuDTO> getAllWeekMenus() {
        log.debug("REST request to get all WeekMenus");
        return weekMenuService.findAll();
    }

    /**
     * {@code GET  /week-menus/:id} : get the "id" weekMenu.
     *
     * @param id the id of the weekMenuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the weekMenuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/week-menus/{id}")
    public ResponseEntity<WeekMenuDTO> getWeekMenu(@PathVariable Long id) {
        log.debug("REST request to get WeekMenu : {}", id);
        Optional<WeekMenuDTO> weekMenuDTO = weekMenuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(weekMenuDTO);
    }

    /**
     * {@code DELETE  /week-menus/:id} : delete the "id" weekMenu.
     *
     * @param id the id of the weekMenuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/week-menus/{id}")
    public ResponseEntity<Void> deleteWeekMenu(@PathVariable Long id) {
        log.debug("REST request to delete WeekMenu : {}", id);
        weekMenuService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
