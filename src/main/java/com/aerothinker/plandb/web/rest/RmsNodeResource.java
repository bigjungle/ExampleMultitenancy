package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.RmsNodeService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.RmsNodeDTO;
import com.aerothinker.plandb.service.dto.RmsNodeCriteria;
import com.aerothinker.plandb.service.RmsNodeQueryService;
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
 * REST controller for managing RmsNode.
 */
@RestController
@RequestMapping("/api")
public class RmsNodeResource {

    private final Logger log = LoggerFactory.getLogger(RmsNodeResource.class);

    private static final String ENTITY_NAME = "rmsNode";

    private final RmsNodeService rmsNodeService;

    private final RmsNodeQueryService rmsNodeQueryService;

    public RmsNodeResource(RmsNodeService rmsNodeService, RmsNodeQueryService rmsNodeQueryService) {
        this.rmsNodeService = rmsNodeService;
        this.rmsNodeQueryService = rmsNodeQueryService;
    }

    /**
     * POST  /rms-nodes : Create a new rmsNode.
     *
     * @param rmsNodeDTO the rmsNodeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rmsNodeDTO, or with status 400 (Bad Request) if the rmsNode has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rms-nodes")
    @Timed
    public ResponseEntity<RmsNodeDTO> createRmsNode(@Valid @RequestBody RmsNodeDTO rmsNodeDTO) throws URISyntaxException {
        log.debug("REST request to save RmsNode : {}", rmsNodeDTO);
        if (rmsNodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new rmsNode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RmsNodeDTO result = rmsNodeService.save(rmsNodeDTO);
        return ResponseEntity.created(new URI("/api/rms-nodes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rms-nodes : Updates an existing rmsNode.
     *
     * @param rmsNodeDTO the rmsNodeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rmsNodeDTO,
     * or with status 400 (Bad Request) if the rmsNodeDTO is not valid,
     * or with status 500 (Internal Server Error) if the rmsNodeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rms-nodes")
    @Timed
    public ResponseEntity<RmsNodeDTO> updateRmsNode(@Valid @RequestBody RmsNodeDTO rmsNodeDTO) throws URISyntaxException {
        log.debug("REST request to update RmsNode : {}", rmsNodeDTO);
        if (rmsNodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RmsNodeDTO result = rmsNodeService.save(rmsNodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rmsNodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rms-nodes : get all the rmsNodes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of rmsNodes in body
     */
    @GetMapping("/rms-nodes")
    @Timed
    public ResponseEntity<List<RmsNodeDTO>> getAllRmsNodes(RmsNodeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RmsNodes by criteria: {}", criteria);
        Page<RmsNodeDTO> page = rmsNodeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rms-nodes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /rms-nodes/count : count all the rmsNodes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/rms-nodes/count")
    @Timed
    public ResponseEntity<Long> countRmsNodes(RmsNodeCriteria criteria) {
        log.debug("REST request to count RmsNodes by criteria: {}", criteria);
        return ResponseEntity.ok().body(rmsNodeQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /rms-nodes/:id : get the "id" rmsNode.
     *
     * @param id the id of the rmsNodeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rmsNodeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rms-nodes/{id}")
    @Timed
    public ResponseEntity<RmsNodeDTO> getRmsNode(@PathVariable Long id) {
        log.debug("REST request to get RmsNode : {}", id);
        Optional<RmsNodeDTO> rmsNodeDTO = rmsNodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rmsNodeDTO);
    }

    /**
     * DELETE  /rms-nodes/:id : delete the "id" rmsNode.
     *
     * @param id the id of the rmsNodeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rms-nodes/{id}")
    @Timed
    public ResponseEntity<Void> deleteRmsNode(@PathVariable Long id) {
        log.debug("REST request to delete RmsNode : {}", id);
        rmsNodeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/rms-nodes?query=:query : search for the rmsNode corresponding
     * to the query.
     *
     * @param query the query of the rmsNode search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/rms-nodes")
    @Timed
    public ResponseEntity<List<RmsNodeDTO>> searchRmsNodes(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of RmsNodes for query {}", query);
        Page<RmsNodeDTO> page = rmsNodeService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/rms-nodes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
