package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.PlanInfoDataAtchHisService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchHisDTO;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchHisCriteria;
import com.aerothinker.plandb.service.PlanInfoDataAtchHisQueryService;
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
 * REST controller for managing PlanInfoDataAtchHis.
 */
@RestController
@RequestMapping("/api")
public class PlanInfoDataAtchHisResource {

    private final Logger log = LoggerFactory.getLogger(PlanInfoDataAtchHisResource.class);

    private static final String ENTITY_NAME = "planInfoDataAtchHis";

    private final PlanInfoDataAtchHisService planInfoDataAtchHisService;

    private final PlanInfoDataAtchHisQueryService planInfoDataAtchHisQueryService;

    public PlanInfoDataAtchHisResource(PlanInfoDataAtchHisService planInfoDataAtchHisService, PlanInfoDataAtchHisQueryService planInfoDataAtchHisQueryService) {
        this.planInfoDataAtchHisService = planInfoDataAtchHisService;
        this.planInfoDataAtchHisQueryService = planInfoDataAtchHisQueryService;
    }

    /**
     * POST  /plan-info-data-atch-his : Create a new planInfoDataAtchHis.
     *
     * @param planInfoDataAtchHisDTO the planInfoDataAtchHisDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planInfoDataAtchHisDTO, or with status 400 (Bad Request) if the planInfoDataAtchHis has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plan-info-data-atch-his")
    @Timed
    public ResponseEntity<PlanInfoDataAtchHisDTO> createPlanInfoDataAtchHis(@Valid @RequestBody PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO) throws URISyntaxException {
        log.debug("REST request to save PlanInfoDataAtchHis : {}", planInfoDataAtchHisDTO);
        if (planInfoDataAtchHisDTO.getId() != null) {
            throw new BadRequestAlertException("A new planInfoDataAtchHis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanInfoDataAtchHisDTO result = planInfoDataAtchHisService.save(planInfoDataAtchHisDTO);
        return ResponseEntity.created(new URI("/api/plan-info-data-atch-his/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plan-info-data-atch-his : Updates an existing planInfoDataAtchHis.
     *
     * @param planInfoDataAtchHisDTO the planInfoDataAtchHisDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planInfoDataAtchHisDTO,
     * or with status 400 (Bad Request) if the planInfoDataAtchHisDTO is not valid,
     * or with status 500 (Internal Server Error) if the planInfoDataAtchHisDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plan-info-data-atch-his")
    @Timed
    public ResponseEntity<PlanInfoDataAtchHisDTO> updatePlanInfoDataAtchHis(@Valid @RequestBody PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO) throws URISyntaxException {
        log.debug("REST request to update PlanInfoDataAtchHis : {}", planInfoDataAtchHisDTO);
        if (planInfoDataAtchHisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanInfoDataAtchHisDTO result = planInfoDataAtchHisService.save(planInfoDataAtchHisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planInfoDataAtchHisDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plan-info-data-atch-his : get all the planInfoDataAtchHis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of planInfoDataAtchHis in body
     */
    @GetMapping("/plan-info-data-atch-his")
    @Timed
    public ResponseEntity<List<PlanInfoDataAtchHisDTO>> getAllPlanInfoDataAtchHis(PlanInfoDataAtchHisCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PlanInfoDataAtchHis by criteria: {}", criteria);
        Page<PlanInfoDataAtchHisDTO> page = planInfoDataAtchHisQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plan-info-data-atch-his");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /plan-info-data-atch-his/count : count all the planInfoDataAtchHis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plan-info-data-atch-his/count")
    @Timed
    public ResponseEntity<Long> countPlanInfoDataAtchHis(PlanInfoDataAtchHisCriteria criteria) {
        log.debug("REST request to count PlanInfoDataAtchHis by criteria: {}", criteria);
        return ResponseEntity.ok().body(planInfoDataAtchHisQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plan-info-data-atch-his/:id : get the "id" planInfoDataAtchHis.
     *
     * @param id the id of the planInfoDataAtchHisDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planInfoDataAtchHisDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plan-info-data-atch-his/{id}")
    @Timed
    public ResponseEntity<PlanInfoDataAtchHisDTO> getPlanInfoDataAtchHis(@PathVariable Long id) {
        log.debug("REST request to get PlanInfoDataAtchHis : {}", id);
        Optional<PlanInfoDataAtchHisDTO> planInfoDataAtchHisDTO = planInfoDataAtchHisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planInfoDataAtchHisDTO);
    }

    /**
     * DELETE  /plan-info-data-atch-his/:id : delete the "id" planInfoDataAtchHis.
     *
     * @param id the id of the planInfoDataAtchHisDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plan-info-data-atch-his/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanInfoDataAtchHis(@PathVariable Long id) {
        log.debug("REST request to delete PlanInfoDataAtchHis : {}", id);
        planInfoDataAtchHisService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/plan-info-data-atch-his?query=:query : search for the planInfoDataAtchHis corresponding
     * to the query.
     *
     * @param query the query of the planInfoDataAtchHis search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/plan-info-data-atch-his")
    @Timed
    public ResponseEntity<List<PlanInfoDataAtchHisDTO>> searchPlanInfoDataAtchHis(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PlanInfoDataAtchHis for query {}", query);
        Page<PlanInfoDataAtchHisDTO> page = planInfoDataAtchHisService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/plan-info-data-atch-his");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
