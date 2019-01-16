package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.RmsDepService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.RmsDepDTO;
import com.aerothinker.plandb.service.dto.RmsDepCriteria;
import com.aerothinker.plandb.service.RmsDepQueryService;
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
 * REST controller for managing RmsDep.
 */
@RestController
@RequestMapping("/api")
public class RmsDepResource {

    private final Logger log = LoggerFactory.getLogger(RmsDepResource.class);

    private static final String ENTITY_NAME = "rmsDep";

    private final RmsDepService rmsDepService;

    private final RmsDepQueryService rmsDepQueryService;

    public RmsDepResource(RmsDepService rmsDepService, RmsDepQueryService rmsDepQueryService) {
        this.rmsDepService = rmsDepService;
        this.rmsDepQueryService = rmsDepQueryService;
    }

    /**
     * POST  /rms-deps : Create a new rmsDep.
     *
     * @param rmsDepDTO the rmsDepDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rmsDepDTO, or with status 400 (Bad Request) if the rmsDep has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rms-deps")
    @Timed
    public ResponseEntity<RmsDepDTO> createRmsDep(@Valid @RequestBody RmsDepDTO rmsDepDTO) throws URISyntaxException {
        log.debug("REST request to save RmsDep : {}", rmsDepDTO);
        if (rmsDepDTO.getId() != null) {
            throw new BadRequestAlertException("A new rmsDep cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RmsDepDTO result = rmsDepService.save(rmsDepDTO);
        return ResponseEntity.created(new URI("/api/rms-deps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rms-deps : Updates an existing rmsDep.
     *
     * @param rmsDepDTO the rmsDepDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rmsDepDTO,
     * or with status 400 (Bad Request) if the rmsDepDTO is not valid,
     * or with status 500 (Internal Server Error) if the rmsDepDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rms-deps")
    @Timed
    public ResponseEntity<RmsDepDTO> updateRmsDep(@Valid @RequestBody RmsDepDTO rmsDepDTO) throws URISyntaxException {
        log.debug("REST request to update RmsDep : {}", rmsDepDTO);
        if (rmsDepDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RmsDepDTO result = rmsDepService.save(rmsDepDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rmsDepDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rms-deps : get all the rmsDeps.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of rmsDeps in body
     */
    @GetMapping("/rms-deps")
    @Timed
    public ResponseEntity<List<RmsDepDTO>> getAllRmsDeps(RmsDepCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RmsDeps by criteria: {}", criteria);
        Page<RmsDepDTO> page = rmsDepQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rms-deps");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /rms-deps/count : count all the rmsDeps.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/rms-deps/count")
    @Timed
    public ResponseEntity<Long> countRmsDeps(RmsDepCriteria criteria) {
        log.debug("REST request to count RmsDeps by criteria: {}", criteria);
        return ResponseEntity.ok().body(rmsDepQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /rms-deps/:id : get the "id" rmsDep.
     *
     * @param id the id of the rmsDepDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rmsDepDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rms-deps/{id}")
    @Timed
    public ResponseEntity<RmsDepDTO> getRmsDep(@PathVariable Long id) {
        log.debug("REST request to get RmsDep : {}", id);
        Optional<RmsDepDTO> rmsDepDTO = rmsDepService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rmsDepDTO);
    }

    /**
     * DELETE  /rms-deps/:id : delete the "id" rmsDep.
     *
     * @param id the id of the rmsDepDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rms-deps/{id}")
    @Timed
    public ResponseEntity<Void> deleteRmsDep(@PathVariable Long id) {
        log.debug("REST request to delete RmsDep : {}", id);
        rmsDepService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/rms-deps?query=:query : search for the rmsDep corresponding
     * to the query.
     *
     * @param query the query of the rmsDep search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/rms-deps")
    @Timed
    public ResponseEntity<List<RmsDepDTO>> searchRmsDeps(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of RmsDeps for query {}", query);
        Page<RmsDepDTO> page = rmsDepService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/rms-deps");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
