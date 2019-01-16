package com.aerothinker.plandb.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.aerothinker.plandb.service.RmsRoleService;
import com.aerothinker.plandb.web.rest.errors.BadRequestAlertException;
import com.aerothinker.plandb.web.rest.util.HeaderUtil;
import com.aerothinker.plandb.web.rest.util.PaginationUtil;
import com.aerothinker.plandb.service.dto.RmsRoleDTO;
import com.aerothinker.plandb.service.dto.RmsRoleCriteria;
import com.aerothinker.plandb.service.RmsRoleQueryService;
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
 * REST controller for managing RmsRole.
 */
@RestController
@RequestMapping("/api")
public class RmsRoleResource {

    private final Logger log = LoggerFactory.getLogger(RmsRoleResource.class);

    private static final String ENTITY_NAME = "rmsRole";

    private final RmsRoleService rmsRoleService;

    private final RmsRoleQueryService rmsRoleQueryService;

    public RmsRoleResource(RmsRoleService rmsRoleService, RmsRoleQueryService rmsRoleQueryService) {
        this.rmsRoleService = rmsRoleService;
        this.rmsRoleQueryService = rmsRoleQueryService;
    }

    /**
     * POST  /rms-roles : Create a new rmsRole.
     *
     * @param rmsRoleDTO the rmsRoleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rmsRoleDTO, or with status 400 (Bad Request) if the rmsRole has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rms-roles")
    @Timed
    public ResponseEntity<RmsRoleDTO> createRmsRole(@Valid @RequestBody RmsRoleDTO rmsRoleDTO) throws URISyntaxException {
        log.debug("REST request to save RmsRole : {}", rmsRoleDTO);
        if (rmsRoleDTO.getId() != null) {
            throw new BadRequestAlertException("A new rmsRole cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RmsRoleDTO result = rmsRoleService.save(rmsRoleDTO);
        return ResponseEntity.created(new URI("/api/rms-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rms-roles : Updates an existing rmsRole.
     *
     * @param rmsRoleDTO the rmsRoleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rmsRoleDTO,
     * or with status 400 (Bad Request) if the rmsRoleDTO is not valid,
     * or with status 500 (Internal Server Error) if the rmsRoleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rms-roles")
    @Timed
    public ResponseEntity<RmsRoleDTO> updateRmsRole(@Valid @RequestBody RmsRoleDTO rmsRoleDTO) throws URISyntaxException {
        log.debug("REST request to update RmsRole : {}", rmsRoleDTO);
        if (rmsRoleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RmsRoleDTO result = rmsRoleService.save(rmsRoleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rmsRoleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rms-roles : get all the rmsRoles.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of rmsRoles in body
     */
    @GetMapping("/rms-roles")
    @Timed
    public ResponseEntity<List<RmsRoleDTO>> getAllRmsRoles(RmsRoleCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RmsRoles by criteria: {}", criteria);
        Page<RmsRoleDTO> page = rmsRoleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rms-roles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /rms-roles/count : count all the rmsRoles.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/rms-roles/count")
    @Timed
    public ResponseEntity<Long> countRmsRoles(RmsRoleCriteria criteria) {
        log.debug("REST request to count RmsRoles by criteria: {}", criteria);
        return ResponseEntity.ok().body(rmsRoleQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /rms-roles/:id : get the "id" rmsRole.
     *
     * @param id the id of the rmsRoleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rmsRoleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rms-roles/{id}")
    @Timed
    public ResponseEntity<RmsRoleDTO> getRmsRole(@PathVariable Long id) {
        log.debug("REST request to get RmsRole : {}", id);
        Optional<RmsRoleDTO> rmsRoleDTO = rmsRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rmsRoleDTO);
    }

    /**
     * DELETE  /rms-roles/:id : delete the "id" rmsRole.
     *
     * @param id the id of the rmsRoleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rms-roles/{id}")
    @Timed
    public ResponseEntity<Void> deleteRmsRole(@PathVariable Long id) {
        log.debug("REST request to delete RmsRole : {}", id);
        rmsRoleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/rms-roles?query=:query : search for the rmsRole corresponding
     * to the query.
     *
     * @param query the query of the rmsRole search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/rms-roles")
    @Timed
    public ResponseEntity<List<RmsRoleDTO>> searchRmsRoles(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of RmsRoles for query {}", query);
        Page<RmsRoleDTO> page = rmsRoleService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/rms-roles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
