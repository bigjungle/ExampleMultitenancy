package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.PlanInfoStepService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.PlanInfoStepDTO;
import com.aerothinker.plandb.service.dto.PlanInfoStepCriteria;
import com.aerothinker.plandb.service.PlanInfoStepQueryService;
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
 * REST controller for managing PlanInfoStep.
 */
@RestController
@RequestMapping("/api")
public class PlanInfoStepResource {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepResource.class);

    private static final String ENTITY_NAME = "planInfoStep";

    private final PlanInfoStepService planInfoStepService;

    private final PlanInfoStepQueryService planInfoStepQueryService;

    public PlanInfoStepResource(PlanInfoStepService planInfoStepService, PlanInfoStepQueryService planInfoStepQueryService) {
        this.planInfoStepService = planInfoStepService;
        this.planInfoStepQueryService = planInfoStepQueryService;
    }

    /**
     * POST  /plan-info-steps : Create a new planInfoStep.
     *
     * @param planInfoStepDTO the planInfoStepDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planInfoStepDTO, or with status 400 (Bad Request) if the planInfoStep has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plan-info-steps")
    @Timed
    public ResponseEntity<PlanInfoStepDTO> createPlanInfoStep(@Valid @RequestBody PlanInfoStepDTO planInfoStepDTO) throws URISyntaxException {
        log.debug("REST request to save PlanInfoStep : {}", planInfoStepDTO);
        if (planInfoStepDTO.getId() != null) {
            throw new BadRequestAlertException("A new planInfoStep cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanInfoStepDTO result = planInfoStepService.save(planInfoStepDTO);
        return ResponseEntity.created(new URI("/api/plan-info-steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plan-info-steps : Updates an existing planInfoStep.
     *
     * @param planInfoStepDTO the planInfoStepDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planInfoStepDTO,
     * or with status 400 (Bad Request) if the planInfoStepDTO is not valid,
     * or with status 500 (Internal Server Error) if the planInfoStepDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plan-info-steps")
    @Timed
    public ResponseEntity<PlanInfoStepDTO> updatePlanInfoStep(@Valid @RequestBody PlanInfoStepDTO planInfoStepDTO) throws URISyntaxException {
        log.debug("REST request to update PlanInfoStep : {}", planInfoStepDTO);
        if (planInfoStepDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanInfoStepDTO result = planInfoStepService.save(planInfoStepDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planInfoStepDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plan-info-steps : get all the planInfoSteps.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of planInfoSteps in body
     */
    @GetMapping("/plan-info-steps")
    @Timed
    public ResponseEntity<List<PlanInfoStepDTO>> getAllPlanInfoSteps(PlanInfoStepCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PlanInfoSteps by criteria: {}", criteria);
        Page<PlanInfoStepDTO> page = planInfoStepQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plan-info-steps");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /plan-info-steps/count : count all the planInfoSteps.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plan-info-steps/count")
    @Timed
    public ResponseEntity<Long> countPlanInfoSteps(PlanInfoStepCriteria criteria) {
        log.debug("REST request to count PlanInfoSteps by criteria: {}", criteria);
        return ResponseEntity.ok().body(planInfoStepQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plan-info-steps/:id : get the "id" planInfoStep.
     *
     * @param id the id of the planInfoStepDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planInfoStepDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plan-info-steps/{id}")
    @Timed
    public ResponseEntity<PlanInfoStepDTO> getPlanInfoStep(@PathVariable Long id) {
        log.debug("REST request to get PlanInfoStep : {}", id);
        Optional<PlanInfoStepDTO> planInfoStepDTO = planInfoStepService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planInfoStepDTO);
    }

    /**
     * DELETE  /plan-info-steps/:id : delete the "id" planInfoStep.
     *
     * @param id the id of the planInfoStepDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plan-info-steps/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanInfoStep(@PathVariable Long id) {
        log.debug("REST request to delete PlanInfoStep : {}", id);
        planInfoStepService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/plan-info-steps?query=:query : search for the planInfoStep corresponding
     * to the query.
     *
     * @param query the query of the planInfoStep search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/plan-info-steps")
    @Timed
    public ResponseEntity<List<PlanInfoStepDTO>> searchPlanInfoSteps(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PlanInfoSteps for query {}", query);
        Page<PlanInfoStepDTO> page = planInfoStepService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/plan-info-steps");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
