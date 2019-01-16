package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.PlanInfoDataHisService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.PlanInfoDataHisDTO;
import com.aerothinker.plandb.service.dto.PlanInfoDataHisCriteria;
import com.aerothinker.plandb.service.PlanInfoDataHisQueryService;
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
 * REST controller for managing PlanInfoDataHis.
 */
@RestController
@RequestMapping("/api")
public class PlanInfoDataHisResource {

    private final Logger log = LoggerFactory.getLogger(PlanInfoDataHisResource.class);

    private static final String ENTITY_NAME = "planInfoDataHis";

    private final PlanInfoDataHisService planInfoDataHisService;

    private final PlanInfoDataHisQueryService planInfoDataHisQueryService;

    public PlanInfoDataHisResource(PlanInfoDataHisService planInfoDataHisService, PlanInfoDataHisQueryService planInfoDataHisQueryService) {
        this.planInfoDataHisService = planInfoDataHisService;
        this.planInfoDataHisQueryService = planInfoDataHisQueryService;
    }

    /**
     * POST  /plan-info-data-his : Create a new planInfoDataHis.
     *
     * @param planInfoDataHisDTO the planInfoDataHisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planInfoDataHisDTO, or with status 400 (Bad Request) if the planInfoDataHis has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plan-info-data-his")
    @Timed
    public ResponseEntity<PlanInfoDataHisDTO> createPlanInfoDataHis(@Valid @RequestBody PlanInfoDataHisDTO planInfoDataHisDTO) throws URISyntaxException {
        log.debug("REST request to save PlanInfoDataHis : {}", planInfoDataHisDTO);
        if (planInfoDataHisDTO.getId() != null) {
            throw new BadRequestAlertException("A new planInfoDataHis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanInfoDataHisDTO result = planInfoDataHisService.save(planInfoDataHisDTO);
        return ResponseEntity.created(new URI("/api/plan-info-data-his/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plan-info-data-his : Updates an existing planInfoDataHis.
     *
     * @param planInfoDataHisDTO the planInfoDataHisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planInfoDataHisDTO,
     * or with status 400 (Bad Request) if the planInfoDataHisDTO is not valid,
     * or with status 500 (Internal Server Error) if the planInfoDataHisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plan-info-data-his")
    @Timed
    public ResponseEntity<PlanInfoDataHisDTO> updatePlanInfoDataHis(@Valid @RequestBody PlanInfoDataHisDTO planInfoDataHisDTO) throws URISyntaxException {
        log.debug("REST request to update PlanInfoDataHis : {}", planInfoDataHisDTO);
        if (planInfoDataHisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanInfoDataHisDTO result = planInfoDataHisService.save(planInfoDataHisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planInfoDataHisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plan-info-data-his : get all the planInfoDataHis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of planInfoDataHis in body
     */
    @GetMapping("/plan-info-data-his")
    @Timed
    public ResponseEntity<List<PlanInfoDataHisDTO>> getAllPlanInfoDataHis(PlanInfoDataHisCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PlanInfoDataHis by criteria: {}", criteria);
        Page<PlanInfoDataHisDTO> page = planInfoDataHisQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plan-info-data-his");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /plan-info-data-his/count : count all the planInfoDataHis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plan-info-data-his/count")
    @Timed
    public ResponseEntity<Long> countPlanInfoDataHis(PlanInfoDataHisCriteria criteria) {
        log.debug("REST request to count PlanInfoDataHis by criteria: {}", criteria);
        return ResponseEntity.ok().body(planInfoDataHisQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plan-info-data-his/:id : get the "id" planInfoDataHis.
     *
     * @param id the id of the planInfoDataHisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planInfoDataHisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plan-info-data-his/{id}")
    @Timed
    public ResponseEntity<PlanInfoDataHisDTO> getPlanInfoDataHis(@PathVariable Long id) {
        log.debug("REST request to get PlanInfoDataHis : {}", id);
        Optional<PlanInfoDataHisDTO> planInfoDataHisDTO = planInfoDataHisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planInfoDataHisDTO);
    }

    /**
     * DELETE  /plan-info-data-his/:id : delete the "id" planInfoDataHis.
     *
     * @param id the id of the planInfoDataHisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plan-info-data-his/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanInfoDataHis(@PathVariable Long id) {
        log.debug("REST request to delete PlanInfoDataHis : {}", id);
        planInfoDataHisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/plan-info-data-his?query=:query : search for the planInfoDataHis corresponding
     * to the query.
     *
     * @param query the query of the planInfoDataHis search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/plan-info-data-his")
    @Timed
    public ResponseEntity<List<PlanInfoDataHisDTO>> searchPlanInfoDataHis(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PlanInfoDataHis for query {}", query);
        Page<PlanInfoDataHisDTO> page = planInfoDataHisService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/plan-info-data-his");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
