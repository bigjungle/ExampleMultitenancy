package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.PlanInfoStepDataAtchHisService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchHisDTO;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchHisCriteria;
import com.aerothinker.plandb.service.PlanInfoStepDataAtchHisQueryService;
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
 * REST controller for managing PlanInfoStepDataAtchHis.
 */
@RestController
@RequestMapping("/api")
public class PlanInfoStepDataAtchHisResource {

    private final Logger log = LoggerFactory.getLogger(PlanInfoStepDataAtchHisResource.class);

    private static final String ENTITY_NAME = "planInfoStepDataAtchHis";

    private final PlanInfoStepDataAtchHisService planInfoStepDataAtchHisService;

    private final PlanInfoStepDataAtchHisQueryService planInfoStepDataAtchHisQueryService;

    public PlanInfoStepDataAtchHisResource(PlanInfoStepDataAtchHisService planInfoStepDataAtchHisService, PlanInfoStepDataAtchHisQueryService planInfoStepDataAtchHisQueryService) {
        this.planInfoStepDataAtchHisService = planInfoStepDataAtchHisService;
        this.planInfoStepDataAtchHisQueryService = planInfoStepDataAtchHisQueryService;
    }

    /**
     * POST  /plan-info-step-data-atch-his : Create a new planInfoStepDataAtchHis.
     *
     * @param planInfoStepDataAtchHisDTO the planInfoStepDataAtchHisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planInfoStepDataAtchHisDTO, or with status 400 (Bad Request) if the planInfoStepDataAtchHis has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plan-info-step-data-atch-his")
    @Timed
    public ResponseEntity<PlanInfoStepDataAtchHisDTO> createPlanInfoStepDataAtchHis(@Valid @RequestBody PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO) throws URISyntaxException {
        log.debug("REST request to save PlanInfoStepDataAtchHis : {}", planInfoStepDataAtchHisDTO);
        if (planInfoStepDataAtchHisDTO.getId() != null) {
            throw new BadRequestAlertException("A new planInfoStepDataAtchHis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanInfoStepDataAtchHisDTO result = planInfoStepDataAtchHisService.save(planInfoStepDataAtchHisDTO);
        return ResponseEntity.created(new URI("/api/plan-info-step-data-atch-his/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plan-info-step-data-atch-his : Updates an existing planInfoStepDataAtchHis.
     *
     * @param planInfoStepDataAtchHisDTO the planInfoStepDataAtchHisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planInfoStepDataAtchHisDTO,
     * or with status 400 (Bad Request) if the planInfoStepDataAtchHisDTO is not valid,
     * or with status 500 (Internal Server Error) if the planInfoStepDataAtchHisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plan-info-step-data-atch-his")
    @Timed
    public ResponseEntity<PlanInfoStepDataAtchHisDTO> updatePlanInfoStepDataAtchHis(@Valid @RequestBody PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO) throws URISyntaxException {
        log.debug("REST request to update PlanInfoStepDataAtchHis : {}", planInfoStepDataAtchHisDTO);
        if (planInfoStepDataAtchHisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanInfoStepDataAtchHisDTO result = planInfoStepDataAtchHisService.save(planInfoStepDataAtchHisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planInfoStepDataAtchHisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plan-info-step-data-atch-his : get all the planInfoStepDataAtchHis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of planInfoStepDataAtchHis in body
     */
    @GetMapping("/plan-info-step-data-atch-his")
    @Timed
    public ResponseEntity<List<PlanInfoStepDataAtchHisDTO>> getAllPlanInfoStepDataAtchHis(PlanInfoStepDataAtchHisCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PlanInfoStepDataAtchHis by criteria: {}", criteria);
        Page<PlanInfoStepDataAtchHisDTO> page = planInfoStepDataAtchHisQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plan-info-step-data-atch-his");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /plan-info-step-data-atch-his/count : count all the planInfoStepDataAtchHis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plan-info-step-data-atch-his/count")
    @Timed
    public ResponseEntity<Long> countPlanInfoStepDataAtchHis(PlanInfoStepDataAtchHisCriteria criteria) {
        log.debug("REST request to count PlanInfoStepDataAtchHis by criteria: {}", criteria);
        return ResponseEntity.ok().body(planInfoStepDataAtchHisQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plan-info-step-data-atch-his/:id : get the "id" planInfoStepDataAtchHis.
     *
     * @param id the id of the planInfoStepDataAtchHisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planInfoStepDataAtchHisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plan-info-step-data-atch-his/{id}")
    @Timed
    public ResponseEntity<PlanInfoStepDataAtchHisDTO> getPlanInfoStepDataAtchHis(@PathVariable Long id) {
        log.debug("REST request to get PlanInfoStepDataAtchHis : {}", id);
        Optional<PlanInfoStepDataAtchHisDTO> planInfoStepDataAtchHisDTO = planInfoStepDataAtchHisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planInfoStepDataAtchHisDTO);
    }

    /**
     * DELETE  /plan-info-step-data-atch-his/:id : delete the "id" planInfoStepDataAtchHis.
     *
     * @param id the id of the planInfoStepDataAtchHisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plan-info-step-data-atch-his/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanInfoStepDataAtchHis(@PathVariable Long id) {
        log.debug("REST request to delete PlanInfoStepDataAtchHis : {}", id);
        planInfoStepDataAtchHisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/plan-info-step-data-atch-his?query=:query : search for the planInfoStepDataAtchHis corresponding
     * to the query.
     *
     * @param query the query of the planInfoStepDataAtchHis search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/plan-info-step-data-atch-his")
    @Timed
    public ResponseEntity<List<PlanInfoStepDataAtchHisDTO>> searchPlanInfoStepDataAtchHis(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PlanInfoStepDataAtchHis for query {}", query);
        Page<PlanInfoStepDataAtchHisDTO> page = planInfoStepDataAtchHisService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/plan-info-step-data-atch-his");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
