package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.PlanInfoStepDataService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataDTO;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataCriteria;
import com.aerothinker.plandb.service.PlanInfoStepDataQueryService;
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
 * REST controller for managing PlanInfoStepData.
 */
@RestController
@RequestMapping("/api")
public class PlanInfoStepDataResource {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataResource.class);

    private static final String ENTITY_NAME = "planInfoStepData";

    private final PlanInfoStepDataService planInfoStepDataService;

    private final PlanInfoStepDataQueryService planInfoStepDataQueryService;

    public PlanInfoStepDataResource(PlanInfoStepDataService planInfoStepDataService, PlanInfoStepDataQueryService planInfoStepDataQueryService) {
        this.planInfoStepDataService = planInfoStepDataService;
        this.planInfoStepDataQueryService = planInfoStepDataQueryService;
    }

    /**
     * POST  /plan-info-step-data : Create a new planInfoStepData.
     *
     * @param planInfoStepDataDTO the planInfoStepDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planInfoStepDataDTO, or with status 400 (Bad Request) if the planInfoStepData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plan-info-step-data")
    @Timed
    public ResponseEntity<PlanInfoStepDataDTO> createPlanInfoStepData(@Valid @RequestBody PlanInfoStepDataDTO planInfoStepDataDTO) throws URISyntaxException {
        log.debug("REST request to save PlanInfoStepData : {}", planInfoStepDataDTO);
        if (planInfoStepDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new planInfoStepData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanInfoStepDataDTO result = planInfoStepDataService.save(planInfoStepDataDTO);
        return ResponseEntity.created(new URI("/api/plan-info-step-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plan-info-step-data : Updates an existing planInfoStepData.
     *
     * @param planInfoStepDataDTO the planInfoStepDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planInfoStepDataDTO,
     * or with status 400 (Bad Request) if the planInfoStepDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the planInfoStepDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plan-info-step-data")
    @Timed
    public ResponseEntity<PlanInfoStepDataDTO> updatePlanInfoStepData(@Valid @RequestBody PlanInfoStepDataDTO planInfoStepDataDTO) throws URISyntaxException {
        log.debug("REST request to update PlanInfoStepData : {}", planInfoStepDataDTO);
        if (planInfoStepDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanInfoStepDataDTO result = planInfoStepDataService.save(planInfoStepDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planInfoStepDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plan-info-step-data : get all the planInfoStepData.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of planInfoStepData in body
     */
    @GetMapping("/plan-info-step-data")
    @Timed
    public ResponseEntity<List<PlanInfoStepDataDTO>> getAllPlanInfoStepData(PlanInfoStepDataCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PlanInfoStepData by criteria: {}", criteria);
        Page<PlanInfoStepDataDTO> page = planInfoStepDataQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plan-info-step-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /plan-info-step-data/count : count all the planInfoStepData.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plan-info-step-data/count")
    @Timed
    public ResponseEntity<Long> countPlanInfoStepData(PlanInfoStepDataCriteria criteria) {
        log.debug("REST request to count PlanInfoStepData by criteria: {}", criteria);
        return ResponseEntity.ok().body(planInfoStepDataQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plan-info-step-data/:id : get the "id" planInfoStepData.
     *
     * @param id the id of the planInfoStepDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planInfoStepDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plan-info-step-data/{id}")
    @Timed
    public ResponseEntity<PlanInfoStepDataDTO> getPlanInfoStepData(@PathVariable Long id) {
        log.debug("REST request to get PlanInfoStepData : {}", id);
        Optional<PlanInfoStepDataDTO> planInfoStepDataDTO = planInfoStepDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planInfoStepDataDTO);
    }

    /**
     * DELETE  /plan-info-step-data/:id : delete the "id" planInfoStepData.
     *
     * @param id the id of the planInfoStepDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plan-info-step-data/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanInfoStepData(@PathVariable Long id) {
        log.debug("REST request to delete PlanInfoStepData : {}", id);
        planInfoStepDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/plan-info-step-data?query=:query : search for the planInfoStepData corresponding
     * to the query.
     *
     * @param query the query of the planInfoStepData search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/plan-info-step-data")
    @Timed
    public ResponseEntity<List<PlanInfoStepDataDTO>> searchPlanInfoStepData(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PlanInfoStepData for query {}", query);
        Page<PlanInfoStepDataDTO> page = planInfoStepDataService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/plan-info-step-data");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
