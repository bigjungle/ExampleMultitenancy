package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.RmsPersonService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.RmsPersonDTO;
import com.aerothinker.plandb.service.dto.RmsPersonCriteria;
import com.aerothinker.plandb.service.RmsPersonQueryService;
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
 * REST controller for managing RmsPerson.
 */
@RestController
@RequestMapping("/api")
public class RmsPersonResource {

    private final Logger log = LoggerFactory.getLogger(RmsPersonResource.class);

    private static final String ENTITY_NAME = "rmsPerson";

    private final RmsPersonService rmsPersonService;

    private final RmsPersonQueryService rmsPersonQueryService;

    public RmsPersonResource(RmsPersonService rmsPersonService, RmsPersonQueryService rmsPersonQueryService) {
        this.rmsPersonService = rmsPersonService;
        this.rmsPersonQueryService = rmsPersonQueryService;
    }

    /**
     * POST  /rms-people : Create a new rmsPerson.
     *
     * @param rmsPersonDTO the rmsPersonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rmsPersonDTO, or with status 400 (Bad Request) if the rmsPerson has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rms-people")
    @Timed
    public ResponseEntity<RmsPersonDTO> createRmsPerson(@Valid @RequestBody RmsPersonDTO rmsPersonDTO) throws URISyntaxException {
        log.debug("REST request to save RmsPerson : {}", rmsPersonDTO);
        if (rmsPersonDTO.getId() != null) {
            throw new BadRequestAlertException("A new rmsPerson cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RmsPersonDTO result = rmsPersonService.save(rmsPersonDTO);
        return ResponseEntity.created(new URI("/api/rms-people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rms-people : Updates an existing rmsPerson.
     *
     * @param rmsPersonDTO the rmsPersonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rmsPersonDTO,
     * or with status 400 (Bad Request) if the rmsPersonDTO is not valid,
     * or with status 500 (Internal Server Error) if the rmsPersonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rms-people")
    @Timed
    public ResponseEntity<RmsPersonDTO> updateRmsPerson(@Valid @RequestBody RmsPersonDTO rmsPersonDTO) throws URISyntaxException {
        log.debug("REST request to update RmsPerson : {}", rmsPersonDTO);
        if (rmsPersonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RmsPersonDTO result = rmsPersonService.save(rmsPersonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rmsPersonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rms-people : get all the rmsPeople.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of rmsPeople in body
     */
    @GetMapping("/rms-people")
    @Timed
    public ResponseEntity<List<RmsPersonDTO>> getAllRmsPeople(RmsPersonCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RmsPeople by criteria: {}", criteria);
        Page<RmsPersonDTO> page = rmsPersonQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rms-people");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /rms-people/count : count all the rmsPeople.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/rms-people/count")
    @Timed
    public ResponseEntity<Long> countRmsPeople(RmsPersonCriteria criteria) {
        log.debug("REST request to count RmsPeople by criteria: {}", criteria);
        return ResponseEntity.ok().body(rmsPersonQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /rms-people/:id : get the "id" rmsPerson.
     *
     * @param id the id of the rmsPersonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rmsPersonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rms-people/{id}")
    @Timed
    public ResponseEntity<RmsPersonDTO> getRmsPerson(@PathVariable Long id) {
        log.debug("REST request to get RmsPerson : {}", id);
        Optional<RmsPersonDTO> rmsPersonDTO = rmsPersonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rmsPersonDTO);
    }

    /**
     * DELETE  /rms-people/:id : delete the "id" rmsPerson.
     *
     * @param id the id of the rmsPersonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rms-people/{id}")
    @Timed
    public ResponseEntity<Void> deleteRmsPerson(@PathVariable Long id) {
        log.debug("REST request to delete RmsPerson : {}", id);
        rmsPersonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/rms-people?query=:query : search for the rmsPerson corresponding
     * to the query.
     *
     * @param query the query of the rmsPerson search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/rms-people")
    @Timed
    public ResponseEntity<List<RmsPersonDTO>> searchRmsPeople(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of RmsPeople for query {}", query);
        Page<RmsPersonDTO> page = rmsPersonService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/rms-people");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
