package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.PlanInfoStepDataAtchService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchDTO;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchCriteria;
import com.aerothinker.plandb.service.PlanInfoStepDataAtchQueryService;
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
 * REST controller for managing PlanInfoStepDataAtch.
 */
@RestController
@RequestMapping("/api")
public class PlanInfoStepDataAtchResource {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataAtchResource.class);

    private static final String ENTITY_NAME = "planInfoStepDataAtch";

    private final PlanInfoStepDataAtchService planInfoStepDataAtchService;

    private final PlanInfoStepDataAtchQueryService planInfoStepDataAtchQueryService;

    public PlanInfoStepDataAtchResource(PlanInfoStepDataAtchService planInfoStepDataAtchService, PlanInfoStepDataAtchQueryService planInfoStepDataAtchQueryService) {
        this.planInfoStepDataAtchService = planInfoStepDataAtchService;
        this.planInfoStepDataAtchQueryService = planInfoStepDataAtchQueryService;
    }

    /**
     * POST  /plan-info-step-data-atches : Create a new planInfoStepDataAtch.
     *
     * @param planInfoStepDataAtchDTO the planInfoStepDataAtchDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planInfoStepDataAtchDTO, or with status 400 (Bad Request) if the planInfoStepDataAtch has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plan-info-step-data-atches")
    @Timed
    public ResponseEntity<PlanInfoStepDataAtchDTO> createPlanInfoStepDataAtch(@Valid @RequestBody PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO) throws URISyntaxException {
        log.debug("REST request to save PlanInfoStepDataAtch : {}", planInfoStepDataAtchDTO);
        if (planInfoStepDataAtchDTO.getId() != null) {
            throw new BadRequestAlertException("A new planInfoStepDataAtch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanInfoStepDataAtchDTO result = planInfoStepDataAtchService.save(planInfoStepDataAtchDTO);
        return ResponseEntity.created(new URI("/api/plan-info-step-data-atches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plan-info-step-data-atches : Updates an existing planInfoStepDataAtch.
     *
     * @param planInfoStepDataAtchDTO the planInfoStepDataAtchDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planInfoStepDataAtchDTO,
     * or with status 400 (Bad Request) if the planInfoStepDataAtchDTO is not valid,
     * or with status 500 (Internal Server Error) if the planInfoStepDataAtchDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plan-info-step-data-atches")
    @Timed
    public ResponseEntity<PlanInfoStepDataAtchDTO> updatePlanInfoStepDataAtch(@Valid @RequestBody PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO) throws URISyntaxException {
        log.debug("REST request to update PlanInfoStepDataAtch : {}", planInfoStepDataAtchDTO);
        if (planInfoStepDataAtchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanInfoStepDataAtchDTO result = planInfoStepDataAtchService.save(planInfoStepDataAtchDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planInfoStepDataAtchDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plan-info-step-data-atches : get all the planInfoStepDataAtches.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of planInfoStepDataAtches in body
     */
    @GetMapping("/plan-info-step-data-atches")
    @Timed
    public ResponseEntity<List<PlanInfoStepDataAtchDTO>> getAllPlanInfoStepDataAtches(PlanInfoStepDataAtchCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PlanInfoStepDataAtches by criteria: {}", criteria);
        Page<PlanInfoStepDataAtchDTO> page = planInfoStepDataAtchQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plan-info-step-data-atches");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /plan-info-step-data-atches/count : count all the planInfoStepDataAtches.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plan-info-step-data-atches/count")
    @Timed
    public ResponseEntity<Long> countPlanInfoStepDataAtches(PlanInfoStepDataAtchCriteria criteria) {
        log.debug("REST request to count PlanInfoStepDataAtches by criteria: {}", criteria);
        return ResponseEntity.ok().body(planInfoStepDataAtchQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plan-info-step-data-atches/:id : get the "id" planInfoStepDataAtch.
     *
     * @param id the id of the planInfoStepDataAtchDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planInfoStepDataAtchDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plan-info-step-data-atches/{id}")
    @Timed
    public ResponseEntity<PlanInfoStepDataAtchDTO> getPlanInfoStepDataAtch(@PathVariable Long id) {
        log.debug("REST request to get PlanInfoStepDataAtch : {}", id);
        Optional<PlanInfoStepDataAtchDTO> planInfoStepDataAtchDTO = planInfoStepDataAtchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planInfoStepDataAtchDTO);
    }

    /**
     * DELETE  /plan-info-step-data-atches/:id : delete the "id" planInfoStepDataAtch.
     *
     * @param id the id of the planInfoStepDataAtchDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plan-info-step-data-atches/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanInfoStepDataAtch(@PathVariable Long id) {
        log.debug("REST request to delete PlanInfoStepDataAtch : {}", id);
        planInfoStepDataAtchService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/plan-info-step-data-atches?query=:query : search for the planInfoStepDataAtch corresponding
     * to the query.
     *
     * @param query the query of the planInfoStepDataAtch search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/plan-info-step-data-atches")
    @Timed
    public ResponseEntity<List<PlanInfoStepDataAtchDTO>> searchPlanInfoStepDataAtches(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PlanInfoStepDataAtches for query {}", query);
        Page<PlanInfoStepDataAtchDTO> page = planInfoStepDataAtchService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/plan-info-step-data-atches");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
