package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.PlanInfoStepAtchService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.PlanInfoStepAtchDTO;
import com.aerothinker.plandb.service.dto.PlanInfoStepAtchCriteria;
import com.aerothinker.plandb.service.PlanInfoStepAtchQueryService;
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
 * REST controller for managing PlanInfoStepAtch.
 */
@RestController
@RequestMapping("/api")
public class PlanInfoStepAtchResource {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepAtchResource.class);

    private static final String ENTITY_NAME = "planInfoStepAtch";

    private final PlanInfoStepAtchService planInfoStepAtchService;

    private final PlanInfoStepAtchQueryService planInfoStepAtchQueryService;

    public PlanInfoStepAtchResource(PlanInfoStepAtchService planInfoStepAtchService, PlanInfoStepAtchQueryService planInfoStepAtchQueryService) {
        this.planInfoStepAtchService = planInfoStepAtchService;
        this.planInfoStepAtchQueryService = planInfoStepAtchQueryService;
    }

    /**
     * POST  /plan-info-step-atches : Create a new planInfoStepAtch.
     *
     * @param planInfoStepAtchDTO the planInfoStepAtchDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planInfoStepAtchDTO, or with status 400 (Bad Request) if the planInfoStepAtch has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plan-info-step-atches")
    @Timed
    public ResponseEntity<PlanInfoStepAtchDTO> createPlanInfoStepAtch(@Valid @RequestBody PlanInfoStepAtchDTO planInfoStepAtchDTO) throws URISyntaxException {
        log.debug("REST request to save PlanInfoStepAtch : {}", planInfoStepAtchDTO);
        if (planInfoStepAtchDTO.getId() != null) {
            throw new BadRequestAlertException("A new planInfoStepAtch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanInfoStepAtchDTO result = planInfoStepAtchService.save(planInfoStepAtchDTO);
        return ResponseEntity.created(new URI("/api/plan-info-step-atches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plan-info-step-atches : Updates an existing planInfoStepAtch.
     *
     * @param planInfoStepAtchDTO the planInfoStepAtchDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planInfoStepAtchDTO,
     * or with status 400 (Bad Request) if the planInfoStepAtchDTO is not valid,
     * or with status 500 (Internal Server Error) if the planInfoStepAtchDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plan-info-step-atches")
    @Timed
    public ResponseEntity<PlanInfoStepAtchDTO> updatePlanInfoStepAtch(@Valid @RequestBody PlanInfoStepAtchDTO planInfoStepAtchDTO) throws URISyntaxException {
        log.debug("REST request to update PlanInfoStepAtch : {}", planInfoStepAtchDTO);
        if (planInfoStepAtchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanInfoStepAtchDTO result = planInfoStepAtchService.save(planInfoStepAtchDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planInfoStepAtchDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plan-info-step-atches : get all the planInfoStepAtches.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of planInfoStepAtches in body
     */
    @GetMapping("/plan-info-step-atches")
    @Timed
    public ResponseEntity<List<PlanInfoStepAtchDTO>> getAllPlanInfoStepAtches(PlanInfoStepAtchCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PlanInfoStepAtches by criteria: {}", criteria);
        Page<PlanInfoStepAtchDTO> page = planInfoStepAtchQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plan-info-step-atches");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /plan-info-step-atches/count : count all the planInfoStepAtches.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plan-info-step-atches/count")
    @Timed
    public ResponseEntity<Long> countPlanInfoStepAtches(PlanInfoStepAtchCriteria criteria) {
        log.debug("REST request to count PlanInfoStepAtches by criteria: {}", criteria);
        return ResponseEntity.ok().body(planInfoStepAtchQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plan-info-step-atches/:id : get the "id" planInfoStepAtch.
     *
     * @param id the id of the planInfoStepAtchDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planInfoStepAtchDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plan-info-step-atches/{id}")
    @Timed
    public ResponseEntity<PlanInfoStepAtchDTO> getPlanInfoStepAtch(@PathVariable Long id) {
        log.debug("REST request to get PlanInfoStepAtch : {}", id);
        Optional<PlanInfoStepAtchDTO> planInfoStepAtchDTO = planInfoStepAtchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planInfoStepAtchDTO);
    }

    /**
     * DELETE  /plan-info-step-atches/:id : delete the "id" planInfoStepAtch.
     *
     * @param id the id of the planInfoStepAtchDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plan-info-step-atches/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanInfoStepAtch(@PathVariable Long id) {
        log.debug("REST request to delete PlanInfoStepAtch : {}", id);
        planInfoStepAtchService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/plan-info-step-atches?query=:query : search for the planInfoStepAtch corresponding
     * to the query.
     *
     * @param query the query of the planInfoStepAtch search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/plan-info-step-atches")
    @Timed
    public ResponseEntity<List<PlanInfoStepAtchDTO>> searchPlanInfoStepAtches(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PlanInfoStepAtches for query {}", query);
        Page<PlanInfoStepAtchDTO> page = planInfoStepAtchService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/plan-info-step-atches");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
