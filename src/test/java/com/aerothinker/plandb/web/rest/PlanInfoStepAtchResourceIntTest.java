package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfoStepAtch;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.PlanInfoStep;
import com.aerothinker.plandb.repository.PlanInfoStepAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepAtchSearchRepository;
import com.aerothinker.plandb.service.PlanInfoStepAtchService;
import com.aerothinker.plandb.service.dto.PlanInfoStepAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepAtchMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoStepAtchCriteria;
import com.aerothinker.plandb.service.PlanInfoStepAtchQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
 * Test class for the PlanInfoStepAtchResource REST controller.
 *
 * @see PlanInfoStepAtchResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoStepAtchResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_DIR = "AAAAAAAAAA";
    private static final String UPDATED_STORE_DIR = "BBBBBBBBBB";

    private static final String DEFAULT_STORE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STORE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SORT_STRING = "AAAAAAAAAA";
    private static final String UPDATED_SORT_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_TYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DELETE_FLAG = false;
    private static final Boolean UPDATED_DELETE_FLAG = true;

    private static final String DEFAULT_STORE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_STORE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_VER = "AAAAAAAAAA";
    private static final String UPDATED_VER = "BBBBBBBBBB";

    private static final String DEFAULT_ENCRYPTED_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_ENCRYPTED_FLAG = "BBBBBBBBBB";

    private static final String DEFAULT_ENCRYPTED_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ENCRYPTED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_JSON_STRING = "AAAAAAAAAA";
    private static final String UPDATED_JSON_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_ATTACHMENT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENT_PATH = "BBBBBBBBBB";

    private static final byte[] DEFAULT_ATTACHMENT_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ATTACHMENT_BLOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_ATTACHMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT_BLOB = "AAAAAAAAAA";
    private static final String UPDATED_TEXT_BLOB = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE_BLOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_BLOB_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_IMAGE_BLOB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_BLOB_NAME = "BBBBBBBBBB";

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

    @Autowired
    private PlanInfoStepAtchRepository planInfoStepAtchRepository;

    @Autowired
    private PlanInfoStepAtchMapper planInfoStepAtchMapper;

    @Autowired
    private PlanInfoStepAtchService planInfoStepAtchService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoStepAtchSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoStepAtchSearchRepository mockPlanInfoStepAtchSearchRepository;

    @Autowired
    private PlanInfoStepAtchQueryService planInfoStepAtchQueryService;

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

    private MockMvc restPlanInfoStepAtchMockMvc;

    private PlanInfoStepAtch planInfoStepAtch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoStepAtchResource planInfoStepAtchResource = new PlanInfoStepAtchResource(planInfoStepAtchService, planInfoStepAtchQueryService);
        this.restPlanInfoStepAtchMockMvc = MockMvcBuilders.standaloneSetup(planInfoStepAtchResource)
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
    public static PlanInfoStepAtch createEntity(EntityManager em) {
        PlanInfoStepAtch planInfoStepAtch = new PlanInfoStepAtch()
            .name(DEFAULT_NAME)
            .storeDir(DEFAULT_STORE_DIR)
            .storeName(DEFAULT_STORE_NAME)
            .sortString(DEFAULT_SORT_STRING)
            .fileType(DEFAULT_FILE_TYPE)
            .deleteFlag(DEFAULT_DELETE_FLAG)
            .storeType(DEFAULT_STORE_TYPE)
            .ver(DEFAULT_VER)
            .encryptedFlag(DEFAULT_ENCRYPTED_FLAG)
            .encryptedType(DEFAULT_ENCRYPTED_TYPE)
            .jsonString(DEFAULT_JSON_STRING)
            .remarks(DEFAULT_REMARKS)
            .attachmentPath(DEFAULT_ATTACHMENT_PATH)
            .attachmentBlob(DEFAULT_ATTACHMENT_BLOB)
            .attachmentBlobContentType(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE)
            .attachmentName(DEFAULT_ATTACHMENT_NAME)
            .textBlob(DEFAULT_TEXT_BLOB)
            .imageBlob(DEFAULT_IMAGE_BLOB)
            .imageBlobContentType(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)
            .imageBlobName(DEFAULT_IMAGE_BLOB_NAME)
            .validType(DEFAULT_VALID_TYPE)
            .validBegin(DEFAULT_VALID_BEGIN)
            .validEnd(DEFAULT_VALID_END)
            .insertTime(DEFAULT_INSERT_TIME)
            .updateTime(DEFAULT_UPDATE_TIME);
        return planInfoStepAtch;
    }

    @Before
    public void initTest() {
        planInfoStepAtch = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfoStepAtch() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepAtchRepository.findAll().size();

        // Create the PlanInfoStepAtch
        PlanInfoStepAtchDTO planInfoStepAtchDTO = planInfoStepAtchMapper.toDto(planInfoStepAtch);
        restPlanInfoStepAtchMockMvc.perform(post("/api/plan-info-step-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepAtchDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfoStepAtch in the database
        List<PlanInfoStepAtch> planInfoStepAtchList = planInfoStepAtchRepository.findAll();
        assertThat(planInfoStepAtchList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfoStepAtch testPlanInfoStepAtch = planInfoStepAtchList.get(planInfoStepAtchList.size() - 1);
        assertThat(testPlanInfoStepAtch.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfoStepAtch.getStoreDir()).isEqualTo(DEFAULT_STORE_DIR);
        assertThat(testPlanInfoStepAtch.getStoreName()).isEqualTo(DEFAULT_STORE_NAME);
        assertThat(testPlanInfoStepAtch.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfoStepAtch.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testPlanInfoStepAtch.isDeleteFlag()).isEqualTo(DEFAULT_DELETE_FLAG);
        assertThat(testPlanInfoStepAtch.getStoreType()).isEqualTo(DEFAULT_STORE_TYPE);
        assertThat(testPlanInfoStepAtch.getVer()).isEqualTo(DEFAULT_VER);
        assertThat(testPlanInfoStepAtch.getEncryptedFlag()).isEqualTo(DEFAULT_ENCRYPTED_FLAG);
        assertThat(testPlanInfoStepAtch.getEncryptedType()).isEqualTo(DEFAULT_ENCRYPTED_TYPE);
        assertThat(testPlanInfoStepAtch.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfoStepAtch.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfoStepAtch.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfoStepAtch.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStepAtch.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepAtch.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfoStepAtch.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfoStepAtch.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfoStepAtch.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepAtch.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStepAtch.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfoStepAtch.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfoStepAtch.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfoStepAtch.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfoStepAtch.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);

        // Validate the PlanInfoStepAtch in Elasticsearch
        verify(mockPlanInfoStepAtchSearchRepository, times(1)).save(testPlanInfoStepAtch);
    }

    @Test
    @Transactional
    public void createPlanInfoStepAtchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepAtchRepository.findAll().size();

        // Create the PlanInfoStepAtch with an existing ID
        planInfoStepAtch.setId(1L);
        PlanInfoStepAtchDTO planInfoStepAtchDTO = planInfoStepAtchMapper.toDto(planInfoStepAtch);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoStepAtchMockMvc.perform(post("/api/plan-info-step-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepAtchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStepAtch in the database
        List<PlanInfoStepAtch> planInfoStepAtchList = planInfoStepAtchRepository.findAll();
        assertThat(planInfoStepAtchList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfoStepAtch in Elasticsearch
        verify(mockPlanInfoStepAtchSearchRepository, times(0)).save(planInfoStepAtch);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoStepAtchRepository.findAll().size();
        // set the field null
        planInfoStepAtch.setName(null);

        // Create the PlanInfoStepAtch, which fails.
        PlanInfoStepAtchDTO planInfoStepAtchDTO = planInfoStepAtchMapper.toDto(planInfoStepAtch);

        restPlanInfoStepAtchMockMvc.perform(post("/api/plan-info-step-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepAtchDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfoStepAtch> planInfoStepAtchList = planInfoStepAtchRepository.findAll();
        assertThat(planInfoStepAtchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtches() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList
        restPlanInfoStepAtchMockMvc.perform(get("/api/plan-info-step-atches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepAtch.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].storeDir").value(hasItem(DEFAULT_STORE_DIR.toString())))
            .andExpect(jsonPath("$.[*].storeName").value(hasItem(DEFAULT_STORE_NAME.toString())))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING.toString())))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].deleteFlag").value(hasItem(DEFAULT_DELETE_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].storeType").value(hasItem(DEFAULT_STORE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].ver").value(hasItem(DEFAULT_VER.toString())))
            .andExpect(jsonPath("$.[*].encryptedFlag").value(hasItem(DEFAULT_ENCRYPTED_FLAG.toString())))
            .andExpect(jsonPath("$.[*].encryptedType").value(hasItem(DEFAULT_ENCRYPTED_TYPE.toString())))
            .andExpect(jsonPath("$.[*].jsonString").value(hasItem(DEFAULT_JSON_STRING.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].attachmentPath").value(hasItem(DEFAULT_ATTACHMENT_PATH.toString())))
            .andExpect(jsonPath("$.[*].attachmentBlobContentType").value(hasItem(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].attachmentBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_ATTACHMENT_BLOB))))
            .andExpect(jsonPath("$.[*].attachmentName").value(hasItem(DEFAULT_ATTACHMENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].textBlob").value(hasItem(DEFAULT_TEXT_BLOB.toString())))
            .andExpect(jsonPath("$.[*].imageBlobContentType").value(hasItem(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB))))
            .andExpect(jsonPath("$.[*].imageBlobName").value(hasItem(DEFAULT_IMAGE_BLOB_NAME.toString())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getPlanInfoStepAtch() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get the planInfoStepAtch
        restPlanInfoStepAtchMockMvc.perform(get("/api/plan-info-step-atches/{id}", planInfoStepAtch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfoStepAtch.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.storeDir").value(DEFAULT_STORE_DIR.toString()))
            .andExpect(jsonPath("$.storeName").value(DEFAULT_STORE_NAME.toString()))
            .andExpect(jsonPath("$.sortString").value(DEFAULT_SORT_STRING.toString()))
            .andExpect(jsonPath("$.fileType").value(DEFAULT_FILE_TYPE.toString()))
            .andExpect(jsonPath("$.deleteFlag").value(DEFAULT_DELETE_FLAG.booleanValue()))
            .andExpect(jsonPath("$.storeType").value(DEFAULT_STORE_TYPE.toString()))
            .andExpect(jsonPath("$.ver").value(DEFAULT_VER.toString()))
            .andExpect(jsonPath("$.encryptedFlag").value(DEFAULT_ENCRYPTED_FLAG.toString()))
            .andExpect(jsonPath("$.encryptedType").value(DEFAULT_ENCRYPTED_TYPE.toString()))
            .andExpect(jsonPath("$.jsonString").value(DEFAULT_JSON_STRING.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.attachmentPath").value(DEFAULT_ATTACHMENT_PATH.toString()))
            .andExpect(jsonPath("$.attachmentBlobContentType").value(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.attachmentBlob").value(Base64Utils.encodeToString(DEFAULT_ATTACHMENT_BLOB)))
            .andExpect(jsonPath("$.attachmentName").value(DEFAULT_ATTACHMENT_NAME.toString()))
            .andExpect(jsonPath("$.textBlob").value(DEFAULT_TEXT_BLOB.toString()))
            .andExpect(jsonPath("$.imageBlobContentType").value(DEFAULT_IMAGE_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.imageBlob").value(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB)))
            .andExpect(jsonPath("$.imageBlobName").value(DEFAULT_IMAGE_BLOB_NAME.toString()))
            .andExpect(jsonPath("$.validType").value(DEFAULT_VALID_TYPE.toString()))
            .andExpect(jsonPath("$.validBegin").value(DEFAULT_VALID_BEGIN.toString()))
            .andExpect(jsonPath("$.validEnd").value(DEFAULT_VALID_END.toString()))
            .andExpect(jsonPath("$.insertTime").value(DEFAULT_INSERT_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()));
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where name equals to DEFAULT_NAME
        defaultPlanInfoStepAtchShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoStepAtchList where name equals to UPDATED_NAME
        defaultPlanInfoStepAtchShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoStepAtchShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoStepAtchList where name equals to UPDATED_NAME
        defaultPlanInfoStepAtchShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where name is not null
        defaultPlanInfoStepAtchShouldBeFound("name.specified=true");

        // Get all the planInfoStepAtchList where name is null
        defaultPlanInfoStepAtchShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByStoreDirIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where storeDir equals to DEFAULT_STORE_DIR
        defaultPlanInfoStepAtchShouldBeFound("storeDir.equals=" + DEFAULT_STORE_DIR);

        // Get all the planInfoStepAtchList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoStepAtchShouldNotBeFound("storeDir.equals=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByStoreDirIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where storeDir in DEFAULT_STORE_DIR or UPDATED_STORE_DIR
        defaultPlanInfoStepAtchShouldBeFound("storeDir.in=" + DEFAULT_STORE_DIR + "," + UPDATED_STORE_DIR);

        // Get all the planInfoStepAtchList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoStepAtchShouldNotBeFound("storeDir.in=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByStoreDirIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where storeDir is not null
        defaultPlanInfoStepAtchShouldBeFound("storeDir.specified=true");

        // Get all the planInfoStepAtchList where storeDir is null
        defaultPlanInfoStepAtchShouldNotBeFound("storeDir.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByStoreNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where storeName equals to DEFAULT_STORE_NAME
        defaultPlanInfoStepAtchShouldBeFound("storeName.equals=" + DEFAULT_STORE_NAME);

        // Get all the planInfoStepAtchList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoStepAtchShouldNotBeFound("storeName.equals=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByStoreNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where storeName in DEFAULT_STORE_NAME or UPDATED_STORE_NAME
        defaultPlanInfoStepAtchShouldBeFound("storeName.in=" + DEFAULT_STORE_NAME + "," + UPDATED_STORE_NAME);

        // Get all the planInfoStepAtchList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoStepAtchShouldNotBeFound("storeName.in=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByStoreNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where storeName is not null
        defaultPlanInfoStepAtchShouldBeFound("storeName.specified=true");

        // Get all the planInfoStepAtchList where storeName is null
        defaultPlanInfoStepAtchShouldNotBeFound("storeName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoStepAtchShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoStepAtchList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepAtchShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoStepAtchShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoStepAtchList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepAtchShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where sortString is not null
        defaultPlanInfoStepAtchShouldBeFound("sortString.specified=true");

        // Get all the planInfoStepAtchList where sortString is null
        defaultPlanInfoStepAtchShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByFileTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where fileType equals to DEFAULT_FILE_TYPE
        defaultPlanInfoStepAtchShouldBeFound("fileType.equals=" + DEFAULT_FILE_TYPE);

        // Get all the planInfoStepAtchList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoStepAtchShouldNotBeFound("fileType.equals=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByFileTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where fileType in DEFAULT_FILE_TYPE or UPDATED_FILE_TYPE
        defaultPlanInfoStepAtchShouldBeFound("fileType.in=" + DEFAULT_FILE_TYPE + "," + UPDATED_FILE_TYPE);

        // Get all the planInfoStepAtchList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoStepAtchShouldNotBeFound("fileType.in=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByFileTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where fileType is not null
        defaultPlanInfoStepAtchShouldBeFound("fileType.specified=true");

        // Get all the planInfoStepAtchList where fileType is null
        defaultPlanInfoStepAtchShouldNotBeFound("fileType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByDeleteFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where deleteFlag equals to DEFAULT_DELETE_FLAG
        defaultPlanInfoStepAtchShouldBeFound("deleteFlag.equals=" + DEFAULT_DELETE_FLAG);

        // Get all the planInfoStepAtchList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoStepAtchShouldNotBeFound("deleteFlag.equals=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByDeleteFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where deleteFlag in DEFAULT_DELETE_FLAG or UPDATED_DELETE_FLAG
        defaultPlanInfoStepAtchShouldBeFound("deleteFlag.in=" + DEFAULT_DELETE_FLAG + "," + UPDATED_DELETE_FLAG);

        // Get all the planInfoStepAtchList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoStepAtchShouldNotBeFound("deleteFlag.in=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByDeleteFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where deleteFlag is not null
        defaultPlanInfoStepAtchShouldBeFound("deleteFlag.specified=true");

        // Get all the planInfoStepAtchList where deleteFlag is null
        defaultPlanInfoStepAtchShouldNotBeFound("deleteFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByStoreTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where storeType equals to DEFAULT_STORE_TYPE
        defaultPlanInfoStepAtchShouldBeFound("storeType.equals=" + DEFAULT_STORE_TYPE);

        // Get all the planInfoStepAtchList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoStepAtchShouldNotBeFound("storeType.equals=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByStoreTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where storeType in DEFAULT_STORE_TYPE or UPDATED_STORE_TYPE
        defaultPlanInfoStepAtchShouldBeFound("storeType.in=" + DEFAULT_STORE_TYPE + "," + UPDATED_STORE_TYPE);

        // Get all the planInfoStepAtchList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoStepAtchShouldNotBeFound("storeType.in=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByStoreTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where storeType is not null
        defaultPlanInfoStepAtchShouldBeFound("storeType.specified=true");

        // Get all the planInfoStepAtchList where storeType is null
        defaultPlanInfoStepAtchShouldNotBeFound("storeType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByVerIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where ver equals to DEFAULT_VER
        defaultPlanInfoStepAtchShouldBeFound("ver.equals=" + DEFAULT_VER);

        // Get all the planInfoStepAtchList where ver equals to UPDATED_VER
        defaultPlanInfoStepAtchShouldNotBeFound("ver.equals=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByVerIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where ver in DEFAULT_VER or UPDATED_VER
        defaultPlanInfoStepAtchShouldBeFound("ver.in=" + DEFAULT_VER + "," + UPDATED_VER);

        // Get all the planInfoStepAtchList where ver equals to UPDATED_VER
        defaultPlanInfoStepAtchShouldNotBeFound("ver.in=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByVerIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where ver is not null
        defaultPlanInfoStepAtchShouldBeFound("ver.specified=true");

        // Get all the planInfoStepAtchList where ver is null
        defaultPlanInfoStepAtchShouldNotBeFound("ver.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByEncryptedFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where encryptedFlag equals to DEFAULT_ENCRYPTED_FLAG
        defaultPlanInfoStepAtchShouldBeFound("encryptedFlag.equals=" + DEFAULT_ENCRYPTED_FLAG);

        // Get all the planInfoStepAtchList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoStepAtchShouldNotBeFound("encryptedFlag.equals=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByEncryptedFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where encryptedFlag in DEFAULT_ENCRYPTED_FLAG or UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoStepAtchShouldBeFound("encryptedFlag.in=" + DEFAULT_ENCRYPTED_FLAG + "," + UPDATED_ENCRYPTED_FLAG);

        // Get all the planInfoStepAtchList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoStepAtchShouldNotBeFound("encryptedFlag.in=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByEncryptedFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where encryptedFlag is not null
        defaultPlanInfoStepAtchShouldBeFound("encryptedFlag.specified=true");

        // Get all the planInfoStepAtchList where encryptedFlag is null
        defaultPlanInfoStepAtchShouldNotBeFound("encryptedFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByEncryptedTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where encryptedType equals to DEFAULT_ENCRYPTED_TYPE
        defaultPlanInfoStepAtchShouldBeFound("encryptedType.equals=" + DEFAULT_ENCRYPTED_TYPE);

        // Get all the planInfoStepAtchList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoStepAtchShouldNotBeFound("encryptedType.equals=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByEncryptedTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where encryptedType in DEFAULT_ENCRYPTED_TYPE or UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoStepAtchShouldBeFound("encryptedType.in=" + DEFAULT_ENCRYPTED_TYPE + "," + UPDATED_ENCRYPTED_TYPE);

        // Get all the planInfoStepAtchList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoStepAtchShouldNotBeFound("encryptedType.in=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByEncryptedTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where encryptedType is not null
        defaultPlanInfoStepAtchShouldBeFound("encryptedType.specified=true");

        // Get all the planInfoStepAtchList where encryptedType is null
        defaultPlanInfoStepAtchShouldNotBeFound("encryptedType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoStepAtchShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoStepAtchList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepAtchShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoStepAtchShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoStepAtchList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepAtchShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where jsonString is not null
        defaultPlanInfoStepAtchShouldBeFound("jsonString.specified=true");

        // Get all the planInfoStepAtchList where jsonString is null
        defaultPlanInfoStepAtchShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoStepAtchShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoStepAtchList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepAtchShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoStepAtchShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoStepAtchList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepAtchShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where remarks is not null
        defaultPlanInfoStepAtchShouldBeFound("remarks.specified=true");

        // Get all the planInfoStepAtchList where remarks is null
        defaultPlanInfoStepAtchShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoStepAtchShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoStepAtchList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepAtchShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepAtchShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoStepAtchList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepAtchShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where attachmentPath is not null
        defaultPlanInfoStepAtchShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoStepAtchList where attachmentPath is null
        defaultPlanInfoStepAtchShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoStepAtchShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoStepAtchList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepAtchShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepAtchShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoStepAtchList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepAtchShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where attachmentName is not null
        defaultPlanInfoStepAtchShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoStepAtchList where attachmentName is null
        defaultPlanInfoStepAtchShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoStepAtchShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoStepAtchList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepAtchShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepAtchShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoStepAtchList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepAtchShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where imageBlobName is not null
        defaultPlanInfoStepAtchShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoStepAtchList where imageBlobName is null
        defaultPlanInfoStepAtchShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoStepAtchShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoStepAtchList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepAtchShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoStepAtchShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoStepAtchList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepAtchShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where validType is not null
        defaultPlanInfoStepAtchShouldBeFound("validType.specified=true");

        // Get all the planInfoStepAtchList where validType is null
        defaultPlanInfoStepAtchShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoStepAtchShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoStepAtchList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepAtchShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoStepAtchShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoStepAtchList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepAtchShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where validBegin is not null
        defaultPlanInfoStepAtchShouldBeFound("validBegin.specified=true");

        // Get all the planInfoStepAtchList where validBegin is null
        defaultPlanInfoStepAtchShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoStepAtchShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoStepAtchList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepAtchShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoStepAtchShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoStepAtchList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepAtchShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where validEnd is not null
        defaultPlanInfoStepAtchShouldBeFound("validEnd.specified=true");

        // Get all the planInfoStepAtchList where validEnd is null
        defaultPlanInfoStepAtchShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoStepAtchShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoStepAtchList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepAtchShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoStepAtchShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoStepAtchList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepAtchShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where insertTime is not null
        defaultPlanInfoStepAtchShouldBeFound("insertTime.specified=true");

        // Get all the planInfoStepAtchList where insertTime is null
        defaultPlanInfoStepAtchShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoStepAtchShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoStepAtchList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepAtchShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoStepAtchShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoStepAtchList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepAtchShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        // Get all the planInfoStepAtchList where updateTime is not null
        defaultPlanInfoStepAtchShouldBeFound("updateTime.specified=true");

        // Get all the planInfoStepAtchList where updateTime is null
        defaultPlanInfoStepAtchShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfoStepAtch.setCreatedBy(createdBy);
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);
        Long createdById = createdBy.getId();

        // Get all the planInfoStepAtchList where createdBy equals to createdById
        defaultPlanInfoStepAtchShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoStepAtchList where createdBy equals to createdById + 1
        defaultPlanInfoStepAtchShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfoStepAtch.setModifiedBy(modifiedBy);
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoStepAtchList where modifiedBy equals to modifiedById
        defaultPlanInfoStepAtchShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoStepAtchList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoStepAtchShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepAtchesByPlanInfoStepIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoStep planInfoStep = PlanInfoStepResourceIntTest.createEntity(em);
        em.persist(planInfoStep);
        em.flush();
        planInfoStepAtch.setPlanInfoStep(planInfoStep);
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);
        Long planInfoStepId = planInfoStep.getId();

        // Get all the planInfoStepAtchList where planInfoStep equals to planInfoStepId
        defaultPlanInfoStepAtchShouldBeFound("planInfoStepId.equals=" + planInfoStepId);

        // Get all the planInfoStepAtchList where planInfoStep equals to planInfoStepId + 1
        defaultPlanInfoStepAtchShouldNotBeFound("planInfoStepId.equals=" + (planInfoStepId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoStepAtchShouldBeFound(String filter) throws Exception {
        restPlanInfoStepAtchMockMvc.perform(get("/api/plan-info-step-atches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepAtch.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].storeDir").value(hasItem(DEFAULT_STORE_DIR.toString())))
            .andExpect(jsonPath("$.[*].storeName").value(hasItem(DEFAULT_STORE_NAME.toString())))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING.toString())))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].deleteFlag").value(hasItem(DEFAULT_DELETE_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].storeType").value(hasItem(DEFAULT_STORE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].ver").value(hasItem(DEFAULT_VER.toString())))
            .andExpect(jsonPath("$.[*].encryptedFlag").value(hasItem(DEFAULT_ENCRYPTED_FLAG.toString())))
            .andExpect(jsonPath("$.[*].encryptedType").value(hasItem(DEFAULT_ENCRYPTED_TYPE.toString())))
            .andExpect(jsonPath("$.[*].jsonString").value(hasItem(DEFAULT_JSON_STRING.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].attachmentPath").value(hasItem(DEFAULT_ATTACHMENT_PATH.toString())))
            .andExpect(jsonPath("$.[*].attachmentBlobContentType").value(hasItem(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].attachmentBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_ATTACHMENT_BLOB))))
            .andExpect(jsonPath("$.[*].attachmentName").value(hasItem(DEFAULT_ATTACHMENT_NAME.toString())))
            .andExpect(jsonPath("$.[*].textBlob").value(hasItem(DEFAULT_TEXT_BLOB.toString())))
            .andExpect(jsonPath("$.[*].imageBlobContentType").value(hasItem(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB))))
            .andExpect(jsonPath("$.[*].imageBlobName").value(hasItem(DEFAULT_IMAGE_BLOB_NAME.toString())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));

        // Check, that the count call also returns 1
        restPlanInfoStepAtchMockMvc.perform(get("/api/plan-info-step-atches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoStepAtchShouldNotBeFound(String filter) throws Exception {
        restPlanInfoStepAtchMockMvc.perform(get("/api/plan-info-step-atches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoStepAtchMockMvc.perform(get("/api/plan-info-step-atches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfoStepAtch() throws Exception {
        // Get the planInfoStepAtch
        restPlanInfoStepAtchMockMvc.perform(get("/api/plan-info-step-atches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfoStepAtch() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        int databaseSizeBeforeUpdate = planInfoStepAtchRepository.findAll().size();

        // Update the planInfoStepAtch
        PlanInfoStepAtch updatedPlanInfoStepAtch = planInfoStepAtchRepository.findById(planInfoStepAtch.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfoStepAtch are not directly saved in db
        em.detach(updatedPlanInfoStepAtch);
        updatedPlanInfoStepAtch
            .name(UPDATED_NAME)
            .storeDir(UPDATED_STORE_DIR)
            .storeName(UPDATED_STORE_NAME)
            .sortString(UPDATED_SORT_STRING)
            .fileType(UPDATED_FILE_TYPE)
            .deleteFlag(UPDATED_DELETE_FLAG)
            .storeType(UPDATED_STORE_TYPE)
            .ver(UPDATED_VER)
            .encryptedFlag(UPDATED_ENCRYPTED_FLAG)
            .encryptedType(UPDATED_ENCRYPTED_TYPE)
            .jsonString(UPDATED_JSON_STRING)
            .remarks(UPDATED_REMARKS)
            .attachmentPath(UPDATED_ATTACHMENT_PATH)
            .attachmentBlob(UPDATED_ATTACHMENT_BLOB)
            .attachmentBlobContentType(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE)
            .attachmentName(UPDATED_ATTACHMENT_NAME)
            .textBlob(UPDATED_TEXT_BLOB)
            .imageBlob(UPDATED_IMAGE_BLOB)
            .imageBlobContentType(UPDATED_IMAGE_BLOB_CONTENT_TYPE)
            .imageBlobName(UPDATED_IMAGE_BLOB_NAME)
            .validType(UPDATED_VALID_TYPE)
            .validBegin(UPDATED_VALID_BEGIN)
            .validEnd(UPDATED_VALID_END)
            .insertTime(UPDATED_INSERT_TIME)
            .updateTime(UPDATED_UPDATE_TIME);
        PlanInfoStepAtchDTO planInfoStepAtchDTO = planInfoStepAtchMapper.toDto(updatedPlanInfoStepAtch);

        restPlanInfoStepAtchMockMvc.perform(put("/api/plan-info-step-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepAtchDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfoStepAtch in the database
        List<PlanInfoStepAtch> planInfoStepAtchList = planInfoStepAtchRepository.findAll();
        assertThat(planInfoStepAtchList).hasSize(databaseSizeBeforeUpdate);
        PlanInfoStepAtch testPlanInfoStepAtch = planInfoStepAtchList.get(planInfoStepAtchList.size() - 1);
        assertThat(testPlanInfoStepAtch.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfoStepAtch.getStoreDir()).isEqualTo(UPDATED_STORE_DIR);
        assertThat(testPlanInfoStepAtch.getStoreName()).isEqualTo(UPDATED_STORE_NAME);
        assertThat(testPlanInfoStepAtch.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfoStepAtch.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testPlanInfoStepAtch.isDeleteFlag()).isEqualTo(UPDATED_DELETE_FLAG);
        assertThat(testPlanInfoStepAtch.getStoreType()).isEqualTo(UPDATED_STORE_TYPE);
        assertThat(testPlanInfoStepAtch.getVer()).isEqualTo(UPDATED_VER);
        assertThat(testPlanInfoStepAtch.getEncryptedFlag()).isEqualTo(UPDATED_ENCRYPTED_FLAG);
        assertThat(testPlanInfoStepAtch.getEncryptedType()).isEqualTo(UPDATED_ENCRYPTED_TYPE);
        assertThat(testPlanInfoStepAtch.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfoStepAtch.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfoStepAtch.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfoStepAtch.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStepAtch.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepAtch.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfoStepAtch.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfoStepAtch.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfoStepAtch.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepAtch.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStepAtch.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfoStepAtch.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfoStepAtch.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfoStepAtch.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfoStepAtch.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);

        // Validate the PlanInfoStepAtch in Elasticsearch
        verify(mockPlanInfoStepAtchSearchRepository, times(1)).save(testPlanInfoStepAtch);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfoStepAtch() throws Exception {
        int databaseSizeBeforeUpdate = planInfoStepAtchRepository.findAll().size();

        // Create the PlanInfoStepAtch
        PlanInfoStepAtchDTO planInfoStepAtchDTO = planInfoStepAtchMapper.toDto(planInfoStepAtch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoStepAtchMockMvc.perform(put("/api/plan-info-step-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepAtchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStepAtch in the database
        List<PlanInfoStepAtch> planInfoStepAtchList = planInfoStepAtchRepository.findAll();
        assertThat(planInfoStepAtchList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfoStepAtch in Elasticsearch
        verify(mockPlanInfoStepAtchSearchRepository, times(0)).save(planInfoStepAtch);
    }

    @Test
    @Transactional
    public void deletePlanInfoStepAtch() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);

        int databaseSizeBeforeDelete = planInfoStepAtchRepository.findAll().size();

        // Get the planInfoStepAtch
        restPlanInfoStepAtchMockMvc.perform(delete("/api/plan-info-step-atches/{id}", planInfoStepAtch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfoStepAtch> planInfoStepAtchList = planInfoStepAtchRepository.findAll();
        assertThat(planInfoStepAtchList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfoStepAtch in Elasticsearch
        verify(mockPlanInfoStepAtchSearchRepository, times(1)).deleteById(planInfoStepAtch.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfoStepAtch() throws Exception {
        // Initialize the database
        planInfoStepAtchRepository.saveAndFlush(planInfoStepAtch);
        when(mockPlanInfoStepAtchSearchRepository.search(queryStringQuery("id:" + planInfoStepAtch.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfoStepAtch), PageRequest.of(0, 1), 1));
        // Search the planInfoStepAtch
        restPlanInfoStepAtchMockMvc.perform(get("/api/_search/plan-info-step-atches?query=id:" + planInfoStepAtch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepAtch.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].storeDir").value(hasItem(DEFAULT_STORE_DIR)))
            .andExpect(jsonPath("$.[*].storeName").value(hasItem(DEFAULT_STORE_NAME)))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING)))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE)))
            .andExpect(jsonPath("$.[*].deleteFlag").value(hasItem(DEFAULT_DELETE_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].storeType").value(hasItem(DEFAULT_STORE_TYPE)))
            .andExpect(jsonPath("$.[*].ver").value(hasItem(DEFAULT_VER)))
            .andExpect(jsonPath("$.[*].encryptedFlag").value(hasItem(DEFAULT_ENCRYPTED_FLAG)))
            .andExpect(jsonPath("$.[*].encryptedType").value(hasItem(DEFAULT_ENCRYPTED_TYPE)))
            .andExpect(jsonPath("$.[*].jsonString").value(hasItem(DEFAULT_JSON_STRING)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].attachmentPath").value(hasItem(DEFAULT_ATTACHMENT_PATH)))
            .andExpect(jsonPath("$.[*].attachmentBlobContentType").value(hasItem(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].attachmentBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_ATTACHMENT_BLOB))))
            .andExpect(jsonPath("$.[*].attachmentName").value(hasItem(DEFAULT_ATTACHMENT_NAME)))
            .andExpect(jsonPath("$.[*].textBlob").value(hasItem(DEFAULT_TEXT_BLOB.toString())))
            .andExpect(jsonPath("$.[*].imageBlobContentType").value(hasItem(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB))))
            .andExpect(jsonPath("$.[*].imageBlobName").value(hasItem(DEFAULT_IMAGE_BLOB_NAME)))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoStepAtch.class);
        PlanInfoStepAtch planInfoStepAtch1 = new PlanInfoStepAtch();
        planInfoStepAtch1.setId(1L);
        PlanInfoStepAtch planInfoStepAtch2 = new PlanInfoStepAtch();
        planInfoStepAtch2.setId(planInfoStepAtch1.getId());
        assertThat(planInfoStepAtch1).isEqualTo(planInfoStepAtch2);
        planInfoStepAtch2.setId(2L);
        assertThat(planInfoStepAtch1).isNotEqualTo(planInfoStepAtch2);
        planInfoStepAtch1.setId(null);
        assertThat(planInfoStepAtch1).isNotEqualTo(planInfoStepAtch2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoStepAtchDTO.class);
        PlanInfoStepAtchDTO planInfoStepAtchDTO1 = new PlanInfoStepAtchDTO();
        planInfoStepAtchDTO1.setId(1L);
        PlanInfoStepAtchDTO planInfoStepAtchDTO2 = new PlanInfoStepAtchDTO();
        assertThat(planInfoStepAtchDTO1).isNotEqualTo(planInfoStepAtchDTO2);
        planInfoStepAtchDTO2.setId(planInfoStepAtchDTO1.getId());
        assertThat(planInfoStepAtchDTO1).isEqualTo(planInfoStepAtchDTO2);
        planInfoStepAtchDTO2.setId(2L);
        assertThat(planInfoStepAtchDTO1).isNotEqualTo(planInfoStepAtchDTO2);
        planInfoStepAtchDTO1.setId(null);
        assertThat(planInfoStepAtchDTO1).isNotEqualTo(planInfoStepAtchDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoStepAtchMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoStepAtchMapper.fromId(null)).isNull();
    }
}
