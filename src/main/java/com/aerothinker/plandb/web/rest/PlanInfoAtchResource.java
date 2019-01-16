package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.PlanInfoAtchService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.PlanInfoAtchDTO;
import com.aerothinker.plandb.service.dto.PlanInfoAtchCriteria;
import com.aerothinker.plandb.service.PlanInfoAtchQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing PlanInfoAtch.
 */
@RestController
@RequestMapping("/api")
public class PlanInfoAtchResource {

    private final Logger log = LoggerFactory.getLogger(PlanInfoAtchResource.class);

    private static final String ENTITY_NAME = "planInfoAtch";

    private final PlanInfoAtchService planInfoAtchService;

    private final PlanInfoAtchQueryService planInfoAtchQueryService;

    public PlanInfoAtchResource(PlanInfoAtchService planInfoAtchService, PlanInfoAtchQueryService planInfoAtchQueryService) {
        this.planInfoAtchService = planInfoAtchService;
        this.planInfoAtchQueryService = planInfoAtchQueryService;
    }

    /**
     * POST  /plan-info-atches : Create a new planInfoAtch.
     *
     * @param planInfoAtchDTO the planInfoAtchDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planInfoAtchDTO, or with status 400 (Bad Request) if the planInfoAtch has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plan-info-atches")
    @Timed
    public ResponseEntity<PlanInfoAtchDTO> createPlanInfoAtch(@Valid @RequestBody PlanInfoAtchDTO planInfoAtchDTO) throws URISyntaxException {
        log.debug("REST request to save PlanInfoAtch : {}", planInfoAtchDTO);
        if (planInfoAtchDTO.getId() != null) {
            throw new BadRequestAlertException("A new planInfoAtch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanInfoAtchDTO result = planInfoAtchService.save(planInfoAtchDTO);
        return ResponseEntity.created(new URI("/api/plan-info-atches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plan-info-atches : Updates an existing planInfoAtch.
     *
     * @param planInfoAtchDTO the planInfoAtchDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planInfoAtchDTO,
     * or with status 400 (Bad Request) if the planInfoAtchDTO is not valid,
     * or with status 500 (Internal Server Error) if the planInfoAtchDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plan-info-atches")
    @Timed
    public ResponseEntity<PlanInfoAtchDTO> updatePlanInfoAtch(@Valid @RequestBody PlanInfoAtchDTO planInfoAtchDTO) throws URISyntaxException {
        log.debug("REST request to update PlanInfoAtch : {}", planInfoAtchDTO);
        if (planInfoAtchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanInfoAtchDTO result = planInfoAtchService.save(planInfoAtchDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planInfoAtchDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plan-info-atches : get all the planInfoAtches.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of planInfoAtches in body
     */
    @GetMapping("/plan-info-atches")
    @Timed
    public ResponseEntity<List<PlanInfoAtchDTO>> getAllPlanInfoAtches(PlanInfoAtchCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PlanInfoAtches by criteria: {}", criteria);
        Page<PlanInfoAtchDTO> page = planInfoAtchQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plan-info-atches");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /plan-info-atches/count : count all the planInfoAtches.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plan-info-atches/count")
    @Timed
    public ResponseEntity<Long> countPlanInfoAtches(PlanInfoAtchCriteria criteria) {
        log.debug("REST request to count PlanInfoAtches by criteria: {}", criteria);
        return ResponseEntity.ok().body(planInfoAtchQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plan-info-atches/:id : get the "id" planInfoAtch.
     *
     * @param id the id of the planInfoAtchDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planInfoAtchDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plan-info-atches/{id}")
    @Timed
    public ResponseEntity<PlanInfoAtchDTO> getPlanInfoAtch(@PathVariable Long id) {
        log.debug("REST request to get PlanInfoAtch : {}", id);
        Optional<PlanInfoAtchDTO> planInfoAtchDTO = planInfoAtchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planInfoAtchDTO);
    }

    /**
     * DELETE  /plan-info-atches/:id : delete the "id" planInfoAtch.
     *
     * @param id the id of the planInfoAtchDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plan-info-atches/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanInfoAtch(@PathVariable Long id) {
        log.debug("REST request to delete PlanInfoAtch : {}", id);
        planInfoAtchService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/plan-info-atches?query=:query : search for the planInfoAtch corresponding
     * to the query.
     *
     * @param query the query of the planInfoAtch search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/plan-info-atches")
    @Timed
    public ResponseEntity<List<PlanInfoAtchDTO>> searchPlanInfoAtches(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PlanInfoAtches for query {}", query);
        Page<PlanInfoAtchDTO> page = planInfoAtchService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/plan-info-atches");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
