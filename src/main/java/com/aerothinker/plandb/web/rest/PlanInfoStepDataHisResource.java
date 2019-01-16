package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.PlanInfoStepDataHisService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataHisDTO;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataHisCriteria;
import com.aerothinker.plandb.service.PlanInfoStepDataHisQueryService;
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
 * REST controller for managing PlanInfoStepDataHis.
 */
@RestController
@RequestMapping("/api")
public class PlanInfoStepDataHisResource {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataHisResource.class);

    private static final String ENTITY_NAME = "planInfoStepDataHis";

    private final PlanInfoStepDataHisService planInfoStepDataHisService;

    private final PlanInfoStepDataHisQueryService planInfoStepDataHisQueryService;

    public PlanInfoStepDataHisResource(PlanInfoStepDataHisService planInfoStepDataHisService, PlanInfoStepDataHisQueryService planInfoStepDataHisQueryService) {
        this.planInfoStepDataHisService = planInfoStepDataHisService;
        this.planInfoStepDataHisQueryService = planInfoStepDataHisQueryService;
    }

    /**
     * POST  /plan-info-step-data-his : Create a new planInfoStepDataHis.
     *
     * @param planInfoStepDataHisDTO the planInfoStepDataHisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planInfoStepDataHisDTO, or with status 400 (Bad Request) if the planInfoStepDataHis has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plan-info-step-data-his")
    @Timed
    public ResponseEntity<PlanInfoStepDataHisDTO> createPlanInfoStepDataHis(@Valid @RequestBody PlanInfoStepDataHisDTO planInfoStepDataHisDTO) throws URISyntaxException {
        log.debug("REST request to save PlanInfoStepDataHis : {}", planInfoStepDataHisDTO);
        if (planInfoStepDataHisDTO.getId() != null) {
            throw new BadRequestAlertException("A new planInfoStepDataHis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanInfoStepDataHisDTO result = planInfoStepDataHisService.save(planInfoStepDataHisDTO);
        return ResponseEntity.created(new URI("/api/plan-info-step-data-his/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plan-info-step-data-his : Updates an existing planInfoStepDataHis.
     *
     * @param planInfoStepDataHisDTO the planInfoStepDataHisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planInfoStepDataHisDTO,
     * or with status 400 (Bad Request) if the planInfoStepDataHisDTO is not valid,
     * or with status 500 (Internal Server Error) if the planInfoStepDataHisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plan-info-step-data-his")
    @Timed
    public ResponseEntity<PlanInfoStepDataHisDTO> updatePlanInfoStepDataHis(@Valid @RequestBody PlanInfoStepDataHisDTO planInfoStepDataHisDTO) throws URISyntaxException {
        log.debug("REST request to update PlanInfoStepDataHis : {}", planInfoStepDataHisDTO);
        if (planInfoStepDataHisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanInfoStepDataHisDTO result = planInfoStepDataHisService.save(planInfoStepDataHisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planInfoStepDataHisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plan-info-step-data-his : get all the planInfoStepDataHis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of planInfoStepDataHis in body
     */
    @GetMapping("/plan-info-step-data-his")
    @Timed
    public ResponseEntity<List<PlanInfoStepDataHisDTO>> getAllPlanInfoStepDataHis(PlanInfoStepDataHisCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PlanInfoStepDataHis by criteria: {}", criteria);
        Page<PlanInfoStepDataHisDTO> page = planInfoStepDataHisQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plan-info-step-data-his");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /plan-info-step-data-his/count : count all the planInfoStepDataHis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plan-info-step-data-his/count")
    @Timed
    public ResponseEntity<Long> countPlanInfoStepDataHis(PlanInfoStepDataHisCriteria criteria) {
        log.debug("REST request to count PlanInfoStepDataHis by criteria: {}", criteria);
        return ResponseEntity.ok().body(planInfoStepDataHisQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plan-info-step-data-his/:id : get the "id" planInfoStepDataHis.
     *
     * @param id the id of the planInfoStepDataHisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planInfoStepDataHisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plan-info-step-data-his/{id}")
    @Timed
    public ResponseEntity<PlanInfoStepDataHisDTO> getPlanInfoStepDataHis(@PathVariable Long id) {
        log.debug("REST request to get PlanInfoStepDataHis : {}", id);
        Optional<PlanInfoStepDataHisDTO> planInfoStepDataHisDTO = planInfoStepDataHisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planInfoStepDataHisDTO);
    }

    /**
     * DELETE  /plan-info-step-data-his/:id : delete the "id" planInfoStepDataHis.
     *
     * @param id the id of the planInfoStepDataHisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plan-info-step-data-his/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanInfoStepDataHis(@PathVariable Long id) {
        log.debug("REST request to delete PlanInfoStepDataHis : {}", id);
        planInfoStepDataHisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/plan-info-step-data-his?query=:query : search for the planInfoStepDataHis corresponding
     * to the query.
     *
     * @param query the query of the planInfoStepDataHis search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/plan-info-step-data-his")
    @Timed
    public ResponseEntity<List<PlanInfoStepDataHisDTO>> searchPlanInfoStepDataHis(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PlanInfoStepDataHis for query {}", query);
        Page<PlanInfoStepDataHisDTO> page = planInfoStepDataHisService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/plan-info-step-data-his");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
