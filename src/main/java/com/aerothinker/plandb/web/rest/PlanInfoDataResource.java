package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.PlanInfoDataService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.PlanInfoDataDTO;
import com.aerothinker.plandb.service.dto.PlanInfoDataCriteria;
import com.aerothinker.plandb.service.PlanInfoDataQueryService;
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
 * REST controller for managing PlanInfoData.
 */
@RestController
@RequestMapping("/api")
public class PlanInfoDataResource {

    private final Logger log = LoggerFactory.getLogger(PlanInfoDataResource.class);

    private static final String ENTITY_NAME = "planInfoData";

    private final PlanInfoDataService planInfoDataService;

    private final PlanInfoDataQueryService planInfoDataQueryService;

    public PlanInfoDataResource(PlanInfoDataService planInfoDataService, PlanInfoDataQueryService planInfoDataQueryService) {
        this.planInfoDataService = planInfoDataService;
        this.planInfoDataQueryService = planInfoDataQueryService;
    }

    /**
     * POST  /plan-info-data : Create a new planInfoData.
     *
     * @param planInfoDataDTO the planInfoDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planInfoDataDTO, or with status 400 (Bad Request) if the planInfoData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plan-info-data")
    @Timed
    public ResponseEntity<PlanInfoDataDTO> createPlanInfoData(@Valid @RequestBody PlanInfoDataDTO planInfoDataDTO) throws URISyntaxException {
        log.debug("REST request to save PlanInfoData : {}", planInfoDataDTO);
        if (planInfoDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new planInfoData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanInfoDataDTO result = planInfoDataService.save(planInfoDataDTO);
        return ResponseEntity.created(new URI("/api/plan-info-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plan-info-data : Updates an existing planInfoData.
     *
     * @param planInfoDataDTO the planInfoDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planInfoDataDTO,
     * or with status 400 (Bad Request) if the planInfoDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the planInfoDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plan-info-data")
    @Timed
    public ResponseEntity<PlanInfoDataDTO> updatePlanInfoData(@Valid @RequestBody PlanInfoDataDTO planInfoDataDTO) throws URISyntaxException {
        log.debug("REST request to update PlanInfoData : {}", planInfoDataDTO);
        if (planInfoDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanInfoDataDTO result = planInfoDataService.save(planInfoDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planInfoDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plan-info-data : get all the planInfoData.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of planInfoData in body
     */
    @GetMapping("/plan-info-data")
    @Timed
    public ResponseEntity<List<PlanInfoDataDTO>> getAllPlanInfoData(PlanInfoDataCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PlanInfoData by criteria: {}", criteria);
        Page<PlanInfoDataDTO> page = planInfoDataQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plan-info-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /plan-info-data/count : count all the planInfoData.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plan-info-data/count")
    @Timed
    public ResponseEntity<Long> countPlanInfoData(PlanInfoDataCriteria criteria) {
        log.debug("REST request to count PlanInfoData by criteria: {}", criteria);
        return ResponseEntity.ok().body(planInfoDataQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plan-info-data/:id : get the "id" planInfoData.
     *
     * @param id the id of the planInfoDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planInfoDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plan-info-data/{id}")
    @Timed
    public ResponseEntity<PlanInfoDataDTO> getPlanInfoData(@PathVariable Long id) {
        log.debug("REST request to get PlanInfoData : {}", id);
        Optional<PlanInfoDataDTO> planInfoDataDTO = planInfoDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planInfoDataDTO);
    }

    /**
     * DELETE  /plan-info-data/:id : delete the "id" planInfoData.
     *
     * @param id the id of the planInfoDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plan-info-data/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanInfoData(@PathVariable Long id) {
        log.debug("REST request to delete PlanInfoData : {}", id);
        planInfoDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/plan-info-data?query=:query : search for the planInfoData corresponding
     * to the query.
     *
     * @param query the query of the planInfoData search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/plan-info-data")
    @Timed
    public ResponseEntity<List<PlanInfoDataDTO>> searchPlanInfoData(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PlanInfoData for query {}", query);
        Page<PlanInfoDataDTO> page = planInfoDataService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/plan-info-data");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
