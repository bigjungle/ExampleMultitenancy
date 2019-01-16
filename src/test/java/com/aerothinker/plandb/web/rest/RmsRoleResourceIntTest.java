package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.RmsRole;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsRole;
import com.aerothinker.plandb.domain.RmsNode;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.repository.RmsRoleRepository;
import com.aerothinker.plandb.repository.search.RmsRoleSearchRepository;
import com.aerothinker.plandb.service.RmsRoleService;
import com.aerothinker.plandb.service.dto.RmsRoleDTO;
import com.aerothinker.plandb.service.mapper.RmsRoleMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.RmsRoleCriteria;
import com.aerothinker.plandb.service.RmsRoleQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static com.aerothinker.plandb.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.aerothinker.plandb.domain.enumeration.ValidType;
/**
 * Test class for the RmsRoleResource REST controller.
 *
 * @see RmsRoleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class RmsRoleResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SORT_STRING = "AAAAAAAAAA";
    private static final String UPDATED_SORT_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_DESC_STRING = "AAAAAAAAAA";
    private static final String UPDATED_DESC_STRING = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE_BLOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_BLOB_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_IMAGE_BLOB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_BLOB_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_USING_FLAG = false;
    private static final Boolean UPDATED_USING_FLAG = true;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final ValidType DEFAULT_VALID_TYPE = ValidType.LONG;
    private static final ValidType UPDATED_VALID_TYPE = ValidType.SCOPE;

    private static final Instant DEFAULT_VALID_BEGIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_BEGIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_VALID_END = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_END = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_INSERT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSERT_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_VERIFY_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VERIFY_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private RmsRoleRepository rmsRoleRepository;

    @Mock
    private RmsRoleRepository rmsRoleRepositoryMock;

    @Autowired
    private RmsRoleMapper rmsRoleMapper;

    @Mock
    private RmsRoleService rmsRoleServiceMock;

    @Autowired
    private RmsRoleService rmsRoleService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.RmsRoleSearchRepositoryMockConfiguration
     */
    @Autowired
    private RmsRoleSearchRepository mockRmsRoleSearchRepository;

    @Autowired
    private RmsRoleQueryService rmsRoleQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restRmsRoleMockMvc;

    private RmsRole rmsRole;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RmsRoleResource rmsRoleResource = new RmsRoleResource(rmsRoleService, rmsRoleQueryService);
        this.restRmsRoleMockMvc = MockMvcBuilders.standaloneSetup(rmsRoleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RmsRole createEntity(EntityManager em) {
        RmsRole rmsRole = new RmsRole()
            .name(DEFAULT_NAME)
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .sortString(DEFAULT_SORT_STRING)
            .descString(DEFAULT_DESC_STRING)
            .imageBlob(DEFAULT_IMAGE_BLOB)
            .imageBlobContentType(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)
            .imageBlobName(DEFAULT_IMAGE_BLOB_NAME)
            .usingFlag(DEFAULT_USING_FLAG)
            .remarks(DEFAULT_REMARKS)
            .validType(DEFAULT_VALID_TYPE)
            .validBegin(DEFAULT_VALID_BEGIN)
            .validEnd(DEFAULT_VALID_END)
            .insertTime(DEFAULT_INSERT_TIME)
            .updateTime(DEFAULT_UPDATE_TIME)
            .verifyTime(DEFAULT_VERIFY_TIME);
        return rmsRole;
    }

    @Before
    public void initTest() {
        rmsRole = createEntity(em);
    }

    @Test
    @Transactional
    public void createRmsRole() throws Exception {
        int databaseSizeBeforeCreate = rmsRoleRepository.findAll().size();

        // Create the RmsRole
        RmsRoleDTO rmsRoleDTO = rmsRoleMapper.toDto(rmsRole);
        restRmsRoleMockMvc.perform(post("/api/rms-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsRoleDTO)))
            .andExpect(status().isCreated());

        // Validate the RmsRole in the database
        List<RmsRole> rmsRoleList = rmsRoleRepository.findAll();
        assertThat(rmsRoleList).hasSize(databaseSizeBeforeCreate + 1);
        RmsRole testRmsRole = rmsRoleList.get(rmsRoleList.size() - 1);
        assertThat(testRmsRole.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRmsRole.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testRmsRole.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testRmsRole.getDescString()).isEqualTo(DEFAULT_DESC_STRING);
        assertThat(testRmsRole.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testRmsRole.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testRmsRole.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testRmsRole.isUsingFlag()).isEqualTo(DEFAULT_USING_FLAG);
        assertThat(testRmsRole.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testRmsRole.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testRmsRole.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testRmsRole.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testRmsRole.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testRmsRole.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testRmsRole.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);

        // Validate the RmsRole in Elasticsearch
        verify(mockRmsRoleSearchRepository, times(1)).save(testRmsRole);
    }

    @Test
    @Transactional
    public void createRmsRoleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rmsRoleRepository.findAll().size();

        // Create the RmsRole with an existing ID
        rmsRole.setId(1L);
        RmsRoleDTO rmsRoleDTO = rmsRoleMapper.toDto(rmsRole);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRmsRoleMockMvc.perform(post("/api/rms-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RmsRole in the database
        List<RmsRole> rmsRoleList = rmsRoleRepository.findAll();
        assertThat(rmsRoleList).hasSize(databaseSizeBeforeCreate);

        // Validate the RmsRole in Elasticsearch
        verify(mockRmsRoleSearchRepository, times(0)).save(rmsRole);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = rmsRoleRepository.findAll().size();
        // set the field null
        rmsRole.setName(null);

        // Create the RmsRole, which fails.
        RmsRoleDTO rmsRoleDTO = rmsRoleMapper.toDto(rmsRole);

        restRmsRoleMockMvc.perform(post("/api/rms-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsRoleDTO)))
            .andExpect(status().isBadRequest());

        List<RmsRole> rmsRoleList = rmsRoleRepository.findAll();
        assertThat(rmsRoleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRmsRoles() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList
        restRmsRoleMockMvc.perform(get("/api/rms-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING.toString())))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING.toString())))
            .andExpect(jsonPath("$.[*].imageBlobContentType").value(hasItem(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB))))
            .andExpect(jsonPath("$.[*].imageBlobName").value(hasItem(DEFAULT_IMAGE_BLOB_NAME.toString())))
            .andExpect(jsonPath("$.[*].usingFlag").value(hasItem(DEFAULT_USING_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllRmsRolesWithEagerRelationshipsIsEnabled() throws Exception {
        RmsRoleResource rmsRoleResource = new RmsRoleResource(rmsRoleServiceMock, rmsRoleQueryService);
        when(rmsRoleServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restRmsRoleMockMvc = MockMvcBuilders.standaloneSetup(rmsRoleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restRmsRoleMockMvc.perform(get("/api/rms-roles?eagerload=true"))
        .andExpect(status().isOk());

        verify(rmsRoleServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllRmsRolesWithEagerRelationshipsIsNotEnabled() throws Exception {
        RmsRoleResource rmsRoleResource = new RmsRoleResource(rmsRoleServiceMock, rmsRoleQueryService);
            when(rmsRoleServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restRmsRoleMockMvc = MockMvcBuilders.standaloneSetup(rmsRoleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restRmsRoleMockMvc.perform(get("/api/rms-roles?eagerload=true"))
        .andExpect(status().isOk());

            verify(rmsRoleServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getRmsRole() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get the rmsRole
        restRmsRoleMockMvc.perform(get("/api/rms-roles/{id}", rmsRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rmsRole.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.sortString").value(DEFAULT_SORT_STRING.toString()))
            .andExpect(jsonPath("$.descString").value(DEFAULT_DESC_STRING.toString()))
            .andExpect(jsonPath("$.imageBlobContentType").value(DEFAULT_IMAGE_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.imageBlob").value(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB)))
            .andExpect(jsonPath("$.imageBlobName").value(DEFAULT_IMAGE_BLOB_NAME.toString()))
            .andExpect(jsonPath("$.usingFlag").value(DEFAULT_USING_FLAG.booleanValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.validType").value(DEFAULT_VALID_TYPE.toString()))
            .andExpect(jsonPath("$.validBegin").value(DEFAULT_VALID_BEGIN.toString()))
            .andExpect(jsonPath("$.validEnd").value(DEFAULT_VALID_END.toString()))
            .andExpect(jsonPath("$.insertTime").value(DEFAULT_INSERT_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.verifyTime").value(DEFAULT_VERIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getAllRmsRolesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where name equals to DEFAULT_NAME
        defaultRmsRoleShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the rmsRoleList where name equals to UPDATED_NAME
        defaultRmsRoleShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where name in DEFAULT_NAME or UPDATED_NAME
        defaultRmsRoleShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the rmsRoleList where name equals to UPDATED_NAME
        defaultRmsRoleShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where name is not null
        defaultRmsRoleShouldBeFound("name.specified=true");

        // Get all the rmsRoleList where name is null
        defaultRmsRoleShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultRmsRoleShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the rmsRoleList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultRmsRoleShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllRmsRolesBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultRmsRoleShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the rmsRoleList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultRmsRoleShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllRmsRolesBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where serialNumber is not null
        defaultRmsRoleShouldBeFound("serialNumber.specified=true");

        // Get all the rmsRoleList where serialNumber is null
        defaultRmsRoleShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where sortString equals to DEFAULT_SORT_STRING
        defaultRmsRoleShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the rmsRoleList where sortString equals to UPDATED_SORT_STRING
        defaultRmsRoleShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllRmsRolesBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultRmsRoleShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the rmsRoleList where sortString equals to UPDATED_SORT_STRING
        defaultRmsRoleShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllRmsRolesBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where sortString is not null
        defaultRmsRoleShouldBeFound("sortString.specified=true");

        // Get all the rmsRoleList where sortString is null
        defaultRmsRoleShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesByDescStringIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where descString equals to DEFAULT_DESC_STRING
        defaultRmsRoleShouldBeFound("descString.equals=" + DEFAULT_DESC_STRING);

        // Get all the rmsRoleList where descString equals to UPDATED_DESC_STRING
        defaultRmsRoleShouldNotBeFound("descString.equals=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByDescStringIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where descString in DEFAULT_DESC_STRING or UPDATED_DESC_STRING
        defaultRmsRoleShouldBeFound("descString.in=" + DEFAULT_DESC_STRING + "," + UPDATED_DESC_STRING);

        // Get all the rmsRoleList where descString equals to UPDATED_DESC_STRING
        defaultRmsRoleShouldNotBeFound("descString.in=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByDescStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where descString is not null
        defaultRmsRoleShouldBeFound("descString.specified=true");

        // Get all the rmsRoleList where descString is null
        defaultRmsRoleShouldNotBeFound("descString.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultRmsRoleShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the rmsRoleList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultRmsRoleShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultRmsRoleShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the rmsRoleList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultRmsRoleShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where imageBlobName is not null
        defaultRmsRoleShouldBeFound("imageBlobName.specified=true");

        // Get all the rmsRoleList where imageBlobName is null
        defaultRmsRoleShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesByUsingFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where usingFlag equals to DEFAULT_USING_FLAG
        defaultRmsRoleShouldBeFound("usingFlag.equals=" + DEFAULT_USING_FLAG);

        // Get all the rmsRoleList where usingFlag equals to UPDATED_USING_FLAG
        defaultRmsRoleShouldNotBeFound("usingFlag.equals=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByUsingFlagIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where usingFlag in DEFAULT_USING_FLAG or UPDATED_USING_FLAG
        defaultRmsRoleShouldBeFound("usingFlag.in=" + DEFAULT_USING_FLAG + "," + UPDATED_USING_FLAG);

        // Get all the rmsRoleList where usingFlag equals to UPDATED_USING_FLAG
        defaultRmsRoleShouldNotBeFound("usingFlag.in=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByUsingFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where usingFlag is not null
        defaultRmsRoleShouldBeFound("usingFlag.specified=true");

        // Get all the rmsRoleList where usingFlag is null
        defaultRmsRoleShouldNotBeFound("usingFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where remarks equals to DEFAULT_REMARKS
        defaultRmsRoleShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the rmsRoleList where remarks equals to UPDATED_REMARKS
        defaultRmsRoleShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultRmsRoleShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the rmsRoleList where remarks equals to UPDATED_REMARKS
        defaultRmsRoleShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where remarks is not null
        defaultRmsRoleShouldBeFound("remarks.specified=true");

        // Get all the rmsRoleList where remarks is null
        defaultRmsRoleShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where validType equals to DEFAULT_VALID_TYPE
        defaultRmsRoleShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the rmsRoleList where validType equals to UPDATED_VALID_TYPE
        defaultRmsRoleShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultRmsRoleShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the rmsRoleList where validType equals to UPDATED_VALID_TYPE
        defaultRmsRoleShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where validType is not null
        defaultRmsRoleShouldBeFound("validType.specified=true");

        // Get all the rmsRoleList where validType is null
        defaultRmsRoleShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultRmsRoleShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the rmsRoleList where validBegin equals to UPDATED_VALID_BEGIN
        defaultRmsRoleShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultRmsRoleShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the rmsRoleList where validBegin equals to UPDATED_VALID_BEGIN
        defaultRmsRoleShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where validBegin is not null
        defaultRmsRoleShouldBeFound("validBegin.specified=true");

        // Get all the rmsRoleList where validBegin is null
        defaultRmsRoleShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where validEnd equals to DEFAULT_VALID_END
        defaultRmsRoleShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the rmsRoleList where validEnd equals to UPDATED_VALID_END
        defaultRmsRoleShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultRmsRoleShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the rmsRoleList where validEnd equals to UPDATED_VALID_END
        defaultRmsRoleShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where validEnd is not null
        defaultRmsRoleShouldBeFound("validEnd.specified=true");

        // Get all the rmsRoleList where validEnd is null
        defaultRmsRoleShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where insertTime equals to DEFAULT_INSERT_TIME
        defaultRmsRoleShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the rmsRoleList where insertTime equals to UPDATED_INSERT_TIME
        defaultRmsRoleShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultRmsRoleShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the rmsRoleList where insertTime equals to UPDATED_INSERT_TIME
        defaultRmsRoleShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where insertTime is not null
        defaultRmsRoleShouldBeFound("insertTime.specified=true");

        // Get all the rmsRoleList where insertTime is null
        defaultRmsRoleShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultRmsRoleShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the rmsRoleList where updateTime equals to UPDATED_UPDATE_TIME
        defaultRmsRoleShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultRmsRoleShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the rmsRoleList where updateTime equals to UPDATED_UPDATE_TIME
        defaultRmsRoleShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where updateTime is not null
        defaultRmsRoleShouldBeFound("updateTime.specified=true");

        // Get all the rmsRoleList where updateTime is null
        defaultRmsRoleShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultRmsRoleShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the rmsRoleList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultRmsRoleShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultRmsRoleShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the rmsRoleList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultRmsRoleShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsRolesByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        // Get all the rmsRoleList where verifyTime is not null
        defaultRmsRoleShouldBeFound("verifyTime.specified=true");

        // Get all the rmsRoleList where verifyTime is null
        defaultRmsRoleShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsRolesByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        rmsRole.setCreatedBy(createdBy);
        rmsRoleRepository.saveAndFlush(rmsRole);
        Long createdById = createdBy.getId();

        // Get all the rmsRoleList where createdBy equals to createdById
        defaultRmsRoleShouldBeFound("createdById.equals=" + createdById);

        // Get all the rmsRoleList where createdBy equals to createdById + 1
        defaultRmsRoleShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsRolesByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        rmsRole.setModifiedBy(modifiedBy);
        rmsRoleRepository.saveAndFlush(rmsRole);
        Long modifiedById = modifiedBy.getId();

        // Get all the rmsRoleList where modifiedBy equals to modifiedById
        defaultRmsRoleShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the rmsRoleList where modifiedBy equals to modifiedById + 1
        defaultRmsRoleShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsRolesByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        rmsRole.setVerifiedBy(verifiedBy);
        rmsRoleRepository.saveAndFlush(rmsRole);
        Long verifiedById = verifiedBy.getId();

        // Get all the rmsRoleList where verifiedBy equals to verifiedById
        defaultRmsRoleShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the rmsRoleList where verifiedBy equals to verifiedById + 1
        defaultRmsRoleShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsRolesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsRole parent = RmsRoleResourceIntTest.createEntity(em);
        em.persist(parent);
        em.flush();
        rmsRole.setParent(parent);
        rmsRoleRepository.saveAndFlush(rmsRole);
        Long parentId = parent.getId();

        // Get all the rmsRoleList where parent equals to parentId
        defaultRmsRoleShouldBeFound("parentId.equals=" + parentId);

        // Get all the rmsRoleList where parent equals to parentId + 1
        defaultRmsRoleShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }


    @Test
    @Transactional
    public void getAllRmsRolesByRmsNodeIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsNode rmsNode = RmsNodeResourceIntTest.createEntity(em);
        em.persist(rmsNode);
        em.flush();
        rmsRole.addRmsNode(rmsNode);
        rmsRoleRepository.saveAndFlush(rmsRole);
        Long rmsNodeId = rmsNode.getId();

        // Get all the rmsRoleList where rmsNode equals to rmsNodeId
        defaultRmsRoleShouldBeFound("rmsNodeId.equals=" + rmsNodeId);

        // Get all the rmsRoleList where rmsNode equals to rmsNodeId + 1
        defaultRmsRoleShouldNotBeFound("rmsNodeId.equals=" + (rmsNodeId + 1));
    }


    @Test
    @Transactional
    public void getAllRmsRolesByRmsUserIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser rmsUser = RmsUserResourceIntTest.createEntity(em);
        em.persist(rmsUser);
        em.flush();
        rmsRole.addRmsUser(rmsUser);
        rmsRoleRepository.saveAndFlush(rmsRole);
        Long rmsUserId = rmsUser.getId();

        // Get all the rmsRoleList where rmsUser equals to rmsUserId
        defaultRmsRoleShouldBeFound("rmsUserId.equals=" + rmsUserId);

        // Get all the rmsRoleList where rmsUser equals to rmsUserId + 1
        defaultRmsRoleShouldNotBeFound("rmsUserId.equals=" + (rmsUserId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultRmsRoleShouldBeFound(String filter) throws Exception {
        restRmsRoleMockMvc.perform(get("/api/rms-roles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING.toString())))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING.toString())))
            .andExpect(jsonPath("$.[*].imageBlobContentType").value(hasItem(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB))))
            .andExpect(jsonPath("$.[*].imageBlobName").value(hasItem(DEFAULT_IMAGE_BLOB_NAME.toString())))
            .andExpect(jsonPath("$.[*].usingFlag").value(hasItem(DEFAULT_USING_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())));

        // Check, that the count call also returns 1
        restRmsRoleMockMvc.perform(get("/api/rms-roles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultRmsRoleShouldNotBeFound(String filter) throws Exception {
        restRmsRoleMockMvc.perform(get("/api/rms-roles?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRmsRoleMockMvc.perform(get("/api/rms-roles/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingRmsRole() throws Exception {
        // Get the rmsRole
        restRmsRoleMockMvc.perform(get("/api/rms-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRmsRole() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        int databaseSizeBeforeUpdate = rmsRoleRepository.findAll().size();

        // Update the rmsRole
        RmsRole updatedRmsRole = rmsRoleRepository.findById(rmsRole.getId()).get();
        // Disconnect from session so that the updates on updatedRmsRole are not directly saved in db
        em.detach(updatedRmsRole);
        updatedRmsRole
            .name(UPDATED_NAME)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .sortString(UPDATED_SORT_STRING)
            .descString(UPDATED_DESC_STRING)
            .imageBlob(UPDATED_IMAGE_BLOB)
            .imageBlobContentType(UPDATED_IMAGE_BLOB_CONTENT_TYPE)
            .imageBlobName(UPDATED_IMAGE_BLOB_NAME)
            .usingFlag(UPDATED_USING_FLAG)
            .remarks(UPDATED_REMARKS)
            .validType(UPDATED_VALID_TYPE)
            .validBegin(UPDATED_VALID_BEGIN)
            .validEnd(UPDATED_VALID_END)
            .insertTime(UPDATED_INSERT_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .verifyTime(UPDATED_VERIFY_TIME);
        RmsRoleDTO rmsRoleDTO = rmsRoleMapper.toDto(updatedRmsRole);

        restRmsRoleMockMvc.perform(put("/api/rms-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsRoleDTO)))
            .andExpect(status().isOk());

        // Validate the RmsRole in the database
        List<RmsRole> rmsRoleList = rmsRoleRepository.findAll();
        assertThat(rmsRoleList).hasSize(databaseSizeBeforeUpdate);
        RmsRole testRmsRole = rmsRoleList.get(rmsRoleList.size() - 1);
        assertThat(testRmsRole.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRmsRole.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testRmsRole.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testRmsRole.getDescString()).isEqualTo(UPDATED_DESC_STRING);
        assertThat(testRmsRole.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testRmsRole.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testRmsRole.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testRmsRole.isUsingFlag()).isEqualTo(UPDATED_USING_FLAG);
        assertThat(testRmsRole.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testRmsRole.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testRmsRole.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testRmsRole.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testRmsRole.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testRmsRole.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testRmsRole.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);

        // Validate the RmsRole in Elasticsearch
        verify(mockRmsRoleSearchRepository, times(1)).save(testRmsRole);
    }

    @Test
    @Transactional
    public void updateNonExistingRmsRole() throws Exception {
        int databaseSizeBeforeUpdate = rmsRoleRepository.findAll().size();

        // Create the RmsRole
        RmsRoleDTO rmsRoleDTO = rmsRoleMapper.toDto(rmsRole);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRmsRoleMockMvc.perform(put("/api/rms-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RmsRole in the database
        List<RmsRole> rmsRoleList = rmsRoleRepository.findAll();
        assertThat(rmsRoleList).hasSize(databaseSizeBeforeUpdate);

        // Validate the RmsRole in Elasticsearch
        verify(mockRmsRoleSearchRepository, times(0)).save(rmsRole);
    }

    @Test
    @Transactional
    public void deleteRmsRole() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);

        int databaseSizeBeforeDelete = rmsRoleRepository.findAll().size();

        // Get the rmsRole
        restRmsRoleMockMvc.perform(delete("/api/rms-roles/{id}", rmsRole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RmsRole> rmsRoleList = rmsRoleRepository.findAll();
        assertThat(rmsRoleList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the RmsRole in Elasticsearch
        verify(mockRmsRoleSearchRepository, times(1)).deleteById(rmsRole.getId());
    }

    @Test
    @Transactional
    public void searchRmsRole() throws Exception {
        // Initialize the database
        rmsRoleRepository.saveAndFlush(rmsRole);
        when(mockRmsRoleSearchRepository.search(queryStringQuery("id:" + rmsRole.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(rmsRole), PageRequest.of(0, 1), 1));
        // Search the rmsRole
        restRmsRoleMockMvc.perform(get("/api/_search/rms-roles?query=id:" + rmsRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsRole.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING)))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING)))
            .andExpect(jsonPath("$.[*].imageBlobContentType").value(hasItem(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB))))
            .andExpect(jsonPath("$.[*].imageBlobName").value(hasItem(DEFAULT_IMAGE_BLOB_NAME)))
            .andExpect(jsonPath("$.[*].usingFlag").value(hasItem(DEFAULT_USING_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RmsRole.class);
        RmsRole rmsRole1 = new RmsRole();
        rmsRole1.setId(1L);
        RmsRole rmsRole2 = new RmsRole();
        rmsRole2.setId(rmsRole1.getId());
        assertThat(rmsRole1).isEqualTo(rmsRole2);
        rmsRole2.setId(2L);
        assertThat(rmsRole1).isNotEqualTo(rmsRole2);
        rmsRole1.setId(null);
        assertThat(rmsRole1).isNotEqualTo(rmsRole2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RmsRoleDTO.class);
        RmsRoleDTO rmsRoleDTO1 = new RmsRoleDTO();
        rmsRoleDTO1.setId(1L);
        RmsRoleDTO rmsRoleDTO2 = new RmsRoleDTO();
        assertThat(rmsRoleDTO1).isNotEqualTo(rmsRoleDTO2);
        rmsRoleDTO2.setId(rmsRoleDTO1.getId());
        assertThat(rmsRoleDTO1).isEqualTo(rmsRoleDTO2);
        rmsRoleDTO2.setId(2L);
        assertThat(rmsRoleDTO1).isNotEqualTo(rmsRoleDTO2);
        rmsRoleDTO1.setId(null);
        assertThat(rmsRoleDTO1).isNotEqualTo(rmsRoleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rmsRoleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rmsRoleMapper.fromId(null)).isNull();
    }
}
