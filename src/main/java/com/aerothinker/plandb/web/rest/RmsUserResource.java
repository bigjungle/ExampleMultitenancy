package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.RmsUserService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.RmsUserDTO;
import com.aerothinker.plandb.service.dto.RmsUserCriteria;
import com.aerothinker.plandb.service.RmsUserQueryService;
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
 * REST controller for managing RmsUser.
 */
@RestController
@RequestMapping("/api")
public class RmsUserResource {

    private final Logger log = LoggerFactory.getLogger(RmsUserResource.class);

    private static final String ENTITY_NAME = "rmsUser";

    private final RmsUserService rmsUserService;

    private final RmsUserQueryService rmsUserQueryService;

    public RmsUserResource(RmsUserService rmsUserService, RmsUserQueryService rmsUserQueryService) {
        this.rmsUserService = rmsUserService;
        this.rmsUserQueryService = rmsUserQueryService;
    }

    /**
     * POST  /rms-users : Create a new rmsUser.
     *
     * @param rmsUserDTO the rmsUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rmsUserDTO, or with status 400 (Bad Request) if the rmsUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rms-users")
    @Timed
    public ResponseEntity<RmsUserDTO> createRmsUser(@Valid @RequestBody RmsUserDTO rmsUserDTO) throws URISyntaxException {
        log.debug("REST request to save RmsUser : {}", rmsUserDTO);
        if (rmsUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new rmsUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RmsUserDTO result = rmsUserService.save(rmsUserDTO);
        return ResponseEntity.created(new URI("/api/rms-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rms-users : Updates an existing rmsUser.
     *
     * @param rmsUserDTO the rmsUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rmsUserDTO,
     * or with status 400 (Bad Request) if the rmsUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the rmsUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rms-users")
    @Timed
    public ResponseEntity<RmsUserDTO> updateRmsUser(@Valid @RequestBody RmsUserDTO rmsUserDTO) throws URISyntaxException {
        log.debug("REST request to update RmsUser : {}", rmsUserDTO);
        if (rmsUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RmsUserDTO result = rmsUserService.save(rmsUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rmsUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rms-users : get all the rmsUsers.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of rmsUsers in body
     */
    @GetMapping("/rms-users")
    @Timed
    public ResponseEntity<List<RmsUserDTO>> getAllRmsUsers(RmsUserCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RmsUsers by criteria: {}", criteria);
        Page<RmsUserDTO> page = rmsUserQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rms-users");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /rms-users/count : count all the rmsUsers.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/rms-users/count")
    @Timed
    public ResponseEntity<Long> countRmsUsers(RmsUserCriteria criteria) {
        log.debug("REST request to count RmsUsers by criteria: {}", criteria);
        return ResponseEntity.ok().body(rmsUserQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /rms-users/:id : get the "id" rmsUser.
     *
     * @param id the id of the rmsUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rmsUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rms-users/{id}")
    @Timed
    public ResponseEntity<RmsUserDTO> getRmsUser(@PathVariable Long id) {
        log.debug("REST request to get RmsUser : {}", id);
        Optional<RmsUserDTO> rmsUserDTO = rmsUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rmsUserDTO);
    }

    /**
     * DELETE  /rms-users/:id : delete the "id" rmsUser.
     *
     * @param id the id of the rmsUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rms-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteRmsUser(@PathVariable Long id) {
        log.debug("REST request to delete RmsUser : {}", id);
        rmsUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/rms-users?query=:query : search for the rmsUser corresponding
     * to the query.
     *
     * @param query the query of the rmsUser search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/rms-users")
    @Timed
    public ResponseEntity<List<RmsUserDTO>> searchRmsUsers(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of RmsUsers for query {}", query);
        Page<RmsUserDTO> page = rmsUserService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/rms-users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
