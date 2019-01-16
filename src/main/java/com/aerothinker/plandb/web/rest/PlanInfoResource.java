package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.PlanInfoService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.PlanInfoDTO;
import com.aerothinker.plandb.service.dto.PlanInfoCriteria;
import com.aerothinker.plandb.service.PlanInfoQueryService;
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
 * REST controller for managing PlanInfo.
 */
@RestController
@RequestMapping("/api")
public class PlanInfoResource {

    private final Logger log = LoggerFactory.getLogger(PlanInfoResource.class);

    private static final String ENTITY_NAME = "planInfo";

    private final PlanInfoService planInfoService;

    private final PlanInfoQueryService planInfoQueryService;

    public PlanInfoResource(PlanInfoService planInfoService, PlanInfoQueryService planInfoQueryService) {
        this.planInfoService = planInfoService;
        this.planInfoQueryService = planInfoQueryService;
    }

    /**
     * POST  /plan-infos : Create a new planInfo.
     *
     * @param planInfoDTO the planInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planInfoDTO, or with status 400 (Bad Request) if the planInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/plan-infos")
    @Timed
    public ResponseEntity<PlanInfoDTO> createPlanInfo(@Valid @RequestBody PlanInfoDTO planInfoDTO) throws URISyntaxException {
        log.debug("REST request to save PlanInfo : {}", planInfoDTO);
        if (planInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new planInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlanInfoDTO result = planInfoService.save(planInfoDTO);
        return ResponseEntity.created(new URI("/api/plan-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /plan-infos : Updates an existing planInfo.
     *
     * @param planInfoDTO the planInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planInfoDTO,
     * or with status 400 (Bad Request) if the planInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the planInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/plan-infos")
    @Timed
    public ResponseEntity<PlanInfoDTO> updatePlanInfo(@Valid @RequestBody PlanInfoDTO planInfoDTO) throws URISyntaxException {
        log.debug("REST request to update PlanInfo : {}", planInfoDTO);
        if (planInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlanInfoDTO result = planInfoService.save(planInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /plan-infos : get all the planInfos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of planInfos in body
     */
    @GetMapping("/plan-infos")
    @Timed
    public ResponseEntity<List<PlanInfoDTO>> getAllPlanInfos(PlanInfoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PlanInfos by criteria: {}", criteria);
        Page<PlanInfoDTO> page = planInfoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/plan-infos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /plan-infos/count : count all the planInfos.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/plan-infos/count")
    @Timed
    public ResponseEntity<Long> countPlanInfos(PlanInfoCriteria criteria) {
        log.debug("REST request to count PlanInfos by criteria: {}", criteria);
        return ResponseEntity.ok().body(planInfoQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /plan-infos/:id : get the "id" planInfo.
     *
     * @param id the id of the planInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/plan-infos/{id}")
    @Timed
    public ResponseEntity<PlanInfoDTO> getPlanInfo(@PathVariable Long id) {
        log.debug("REST request to get PlanInfo : {}", id);
        Optional<PlanInfoDTO> planInfoDTO = planInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planInfoDTO);
    }

    /**
     * DELETE  /plan-infos/:id : delete the "id" planInfo.
     *
     * @param id the id of the planInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/plan-infos/{id}")
    @Timed
    public ResponseEntity<Void> deletePlanInfo(@PathVariable Long id) {
        log.debug("REST request to delete PlanInfo : {}", id);
        planInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/plan-infos?query=:query : search for the planInfo corresponding
     * to the query.
     *
     * @param query the query of the planInfo search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/plan-infos")
    @Timed
    public ResponseEntity<List<PlanInfoDTO>> searchPlanInfos(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PlanInfos for query {}", query);
        Page<PlanInfoDTO> page = planInfoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/plan-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
