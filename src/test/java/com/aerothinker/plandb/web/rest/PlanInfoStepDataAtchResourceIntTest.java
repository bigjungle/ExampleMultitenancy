package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfoStepDataAtch;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.PlanInfoStepAtch;
import com.aerothinker.plandb.domain.PlanInfoStepData;
import com.aerothinker.plandb.repository.PlanInfoStepDataAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataAtchSearchRepository;
import com.aerothinker.plandb.service.PlanInfoStepDataAtchService;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataAtchMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchCriteria;
import com.aerothinker.plandb.service.PlanInfoStepDataAtchQueryService;

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
 * Test class for the PlanInfoStepDataAtchResource REST controller.
 *
 * @see PlanInfoStepDataAtchResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoStepDataAtchResourceIntTest {

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
    private PlanInfoStepDataAtchRepository planInfoStepDataAtchRepository;

    @Autowired
    private PlanInfoStepDataAtchMapper planInfoStepDataAtchMapper;

    @Autowired
    private PlanInfoStepDataAtchService planInfoStepDataAtchService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoStepDataAtchSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoStepDataAtchSearchRepository mockPlanInfoStepDataAtchSearchRepository;

    @Autowired
    private PlanInfoStepDataAtchQueryService planInfoStepDataAtchQueryService;

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

    private MockMvc restPlanInfoStepDataAtchMockMvc;

    private PlanInfoStepDataAtch planInfoStepDataAtch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoStepDataAtchResource planInfoStepDataAtchResource = new PlanInfoStepDataAtchResource(planInfoStepDataAtchService, planInfoStepDataAtchQueryService);
        this.restPlanInfoStepDataAtchMockMvc = MockMvcBuilders.standaloneSetup(planInfoStepDataAtchResource)
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
    public static PlanInfoStepDataAtch createEntity(EntityManager em) {
        PlanInfoStepDataAtch planInfoStepDataAtch = new PlanInfoStepDataAtch()
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
        return planInfoStepDataAtch;
    }

    @Before
    public void initTest() {
        planInfoStepDataAtch = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfoStepDataAtch() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepDataAtchRepository.findAll().size();

        // Create the PlanInfoStepDataAtch
        PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO = planInfoStepDataAtchMapper.toDto(planInfoStepDataAtch);
        restPlanInfoStepDataAtchMockMvc.perform(post("/api/plan-info-step-data-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataAtchDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfoStepDataAtch in the database
        List<PlanInfoStepDataAtch> planInfoStepDataAtchList = planInfoStepDataAtchRepository.findAll();
        assertThat(planInfoStepDataAtchList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfoStepDataAtch testPlanInfoStepDataAtch = planInfoStepDataAtchList.get(planInfoStepDataAtchList.size() - 1);
        assertThat(testPlanInfoStepDataAtch.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfoStepDataAtch.getStoreDir()).isEqualTo(DEFAULT_STORE_DIR);
        assertThat(testPlanInfoStepDataAtch.getStoreName()).isEqualTo(DEFAULT_STORE_NAME);
        assertThat(testPlanInfoStepDataAtch.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfoStepDataAtch.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testPlanInfoStepDataAtch.isDeleteFlag()).isEqualTo(DEFAULT_DELETE_FLAG);
        assertThat(testPlanInfoStepDataAtch.getStoreType()).isEqualTo(DEFAULT_STORE_TYPE);
        assertThat(testPlanInfoStepDataAtch.getVer()).isEqualTo(DEFAULT_VER);
        assertThat(testPlanInfoStepDataAtch.getEncryptedFlag()).isEqualTo(DEFAULT_ENCRYPTED_FLAG);
        assertThat(testPlanInfoStepDataAtch.getEncryptedType()).isEqualTo(DEFAULT_ENCRYPTED_TYPE);
        assertThat(testPlanInfoStepDataAtch.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfoStepDataAtch.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfoStepDataAtch.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfoStepDataAtch.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStepDataAtch.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataAtch.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfoStepDataAtch.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfoStepDataAtch.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfoStepDataAtch.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataAtch.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStepDataAtch.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfoStepDataAtch.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfoStepDataAtch.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfoStepDataAtch.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfoStepDataAtch.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);

        // Validate the PlanInfoStepDataAtch in Elasticsearch
        verify(mockPlanInfoStepDataAtchSearchRepository, times(1)).save(testPlanInfoStepDataAtch);
    }

    @Test
    @Transactional
    public void createPlanInfoStepDataAtchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepDataAtchRepository.findAll().size();

        // Create the PlanInfoStepDataAtch with an existing ID
        planInfoStepDataAtch.setId(1L);
        PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO = planInfoStepDataAtchMapper.toDto(planInfoStepDataAtch);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoStepDataAtchMockMvc.perform(post("/api/plan-info-step-data-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataAtchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStepDataAtch in the database
        List<PlanInfoStepDataAtch> planInfoStepDataAtchList = planInfoStepDataAtchRepository.findAll();
        assertThat(planInfoStepDataAtchList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfoStepDataAtch in Elasticsearch
        verify(mockPlanInfoStepDataAtchSearchRepository, times(0)).save(planInfoStepDataAtch);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoStepDataAtchRepository.findAll().size();
        // set the field null
        planInfoStepDataAtch.setName(null);

        // Create the PlanInfoStepDataAtch, which fails.
        PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO = planInfoStepDataAtchMapper.toDto(planInfoStepDataAtch);

        restPlanInfoStepDataAtchMockMvc.perform(post("/api/plan-info-step-data-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataAtchDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfoStepDataAtch> planInfoStepDataAtchList = planInfoStepDataAtchRepository.findAll();
        assertThat(planInfoStepDataAtchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtches() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList
        restPlanInfoStepDataAtchMockMvc.perform(get("/api/plan-info-step-data-atches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepDataAtch.getId().intValue())))
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
    public void getPlanInfoStepDataAtch() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get the planInfoStepDataAtch
        restPlanInfoStepDataAtchMockMvc.perform(get("/api/plan-info-step-data-atches/{id}", planInfoStepDataAtch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfoStepDataAtch.getId().intValue()))
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
    public void getAllPlanInfoStepDataAtchesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where name equals to DEFAULT_NAME
        defaultPlanInfoStepDataAtchShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoStepDataAtchList where name equals to UPDATED_NAME
        defaultPlanInfoStepDataAtchShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoStepDataAtchShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoStepDataAtchList where name equals to UPDATED_NAME
        defaultPlanInfoStepDataAtchShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where name is not null
        defaultPlanInfoStepDataAtchShouldBeFound("name.specified=true");

        // Get all the planInfoStepDataAtchList where name is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByStoreDirIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where storeDir equals to DEFAULT_STORE_DIR
        defaultPlanInfoStepDataAtchShouldBeFound("storeDir.equals=" + DEFAULT_STORE_DIR);

        // Get all the planInfoStepDataAtchList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoStepDataAtchShouldNotBeFound("storeDir.equals=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByStoreDirIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where storeDir in DEFAULT_STORE_DIR or UPDATED_STORE_DIR
        defaultPlanInfoStepDataAtchShouldBeFound("storeDir.in=" + DEFAULT_STORE_DIR + "," + UPDATED_STORE_DIR);

        // Get all the planInfoStepDataAtchList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoStepDataAtchShouldNotBeFound("storeDir.in=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByStoreDirIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where storeDir is not null
        defaultPlanInfoStepDataAtchShouldBeFound("storeDir.specified=true");

        // Get all the planInfoStepDataAtchList where storeDir is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("storeDir.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByStoreNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where storeName equals to DEFAULT_STORE_NAME
        defaultPlanInfoStepDataAtchShouldBeFound("storeName.equals=" + DEFAULT_STORE_NAME);

        // Get all the planInfoStepDataAtchList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoStepDataAtchShouldNotBeFound("storeName.equals=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByStoreNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where storeName in DEFAULT_STORE_NAME or UPDATED_STORE_NAME
        defaultPlanInfoStepDataAtchShouldBeFound("storeName.in=" + DEFAULT_STORE_NAME + "," + UPDATED_STORE_NAME);

        // Get all the planInfoStepDataAtchList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoStepDataAtchShouldNotBeFound("storeName.in=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByStoreNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where storeName is not null
        defaultPlanInfoStepDataAtchShouldBeFound("storeName.specified=true");

        // Get all the planInfoStepDataAtchList where storeName is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("storeName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoStepDataAtchShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoStepDataAtchList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepDataAtchShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoStepDataAtchShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoStepDataAtchList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepDataAtchShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where sortString is not null
        defaultPlanInfoStepDataAtchShouldBeFound("sortString.specified=true");

        // Get all the planInfoStepDataAtchList where sortString is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByFileTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where fileType equals to DEFAULT_FILE_TYPE
        defaultPlanInfoStepDataAtchShouldBeFound("fileType.equals=" + DEFAULT_FILE_TYPE);

        // Get all the planInfoStepDataAtchList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoStepDataAtchShouldNotBeFound("fileType.equals=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByFileTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where fileType in DEFAULT_FILE_TYPE or UPDATED_FILE_TYPE
        defaultPlanInfoStepDataAtchShouldBeFound("fileType.in=" + DEFAULT_FILE_TYPE + "," + UPDATED_FILE_TYPE);

        // Get all the planInfoStepDataAtchList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoStepDataAtchShouldNotBeFound("fileType.in=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByFileTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where fileType is not null
        defaultPlanInfoStepDataAtchShouldBeFound("fileType.specified=true");

        // Get all the planInfoStepDataAtchList where fileType is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("fileType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByDeleteFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where deleteFlag equals to DEFAULT_DELETE_FLAG
        defaultPlanInfoStepDataAtchShouldBeFound("deleteFlag.equals=" + DEFAULT_DELETE_FLAG);

        // Get all the planInfoStepDataAtchList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoStepDataAtchShouldNotBeFound("deleteFlag.equals=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByDeleteFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where deleteFlag in DEFAULT_DELETE_FLAG or UPDATED_DELETE_FLAG
        defaultPlanInfoStepDataAtchShouldBeFound("deleteFlag.in=" + DEFAULT_DELETE_FLAG + "," + UPDATED_DELETE_FLAG);

        // Get all the planInfoStepDataAtchList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoStepDataAtchShouldNotBeFound("deleteFlag.in=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByDeleteFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where deleteFlag is not null
        defaultPlanInfoStepDataAtchShouldBeFound("deleteFlag.specified=true");

        // Get all the planInfoStepDataAtchList where deleteFlag is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("deleteFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByStoreTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where storeType equals to DEFAULT_STORE_TYPE
        defaultPlanInfoStepDataAtchShouldBeFound("storeType.equals=" + DEFAULT_STORE_TYPE);

        // Get all the planInfoStepDataAtchList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoStepDataAtchShouldNotBeFound("storeType.equals=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByStoreTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where storeType in DEFAULT_STORE_TYPE or UPDATED_STORE_TYPE
        defaultPlanInfoStepDataAtchShouldBeFound("storeType.in=" + DEFAULT_STORE_TYPE + "," + UPDATED_STORE_TYPE);

        // Get all the planInfoStepDataAtchList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoStepDataAtchShouldNotBeFound("storeType.in=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByStoreTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where storeType is not null
        defaultPlanInfoStepDataAtchShouldBeFound("storeType.specified=true");

        // Get all the planInfoStepDataAtchList where storeType is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("storeType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByVerIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where ver equals to DEFAULT_VER
        defaultPlanInfoStepDataAtchShouldBeFound("ver.equals=" + DEFAULT_VER);

        // Get all the planInfoStepDataAtchList where ver equals to UPDATED_VER
        defaultPlanInfoStepDataAtchShouldNotBeFound("ver.equals=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByVerIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where ver in DEFAULT_VER or UPDATED_VER
        defaultPlanInfoStepDataAtchShouldBeFound("ver.in=" + DEFAULT_VER + "," + UPDATED_VER);

        // Get all the planInfoStepDataAtchList where ver equals to UPDATED_VER
        defaultPlanInfoStepDataAtchShouldNotBeFound("ver.in=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByVerIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where ver is not null
        defaultPlanInfoStepDataAtchShouldBeFound("ver.specified=true");

        // Get all the planInfoStepDataAtchList where ver is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("ver.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByEncryptedFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where encryptedFlag equals to DEFAULT_ENCRYPTED_FLAG
        defaultPlanInfoStepDataAtchShouldBeFound("encryptedFlag.equals=" + DEFAULT_ENCRYPTED_FLAG);

        // Get all the planInfoStepDataAtchList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoStepDataAtchShouldNotBeFound("encryptedFlag.equals=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByEncryptedFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where encryptedFlag in DEFAULT_ENCRYPTED_FLAG or UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoStepDataAtchShouldBeFound("encryptedFlag.in=" + DEFAULT_ENCRYPTED_FLAG + "," + UPDATED_ENCRYPTED_FLAG);

        // Get all the planInfoStepDataAtchList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoStepDataAtchShouldNotBeFound("encryptedFlag.in=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByEncryptedFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where encryptedFlag is not null
        defaultPlanInfoStepDataAtchShouldBeFound("encryptedFlag.specified=true");

        // Get all the planInfoStepDataAtchList where encryptedFlag is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("encryptedFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByEncryptedTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where encryptedType equals to DEFAULT_ENCRYPTED_TYPE
        defaultPlanInfoStepDataAtchShouldBeFound("encryptedType.equals=" + DEFAULT_ENCRYPTED_TYPE);

        // Get all the planInfoStepDataAtchList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoStepDataAtchShouldNotBeFound("encryptedType.equals=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByEncryptedTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where encryptedType in DEFAULT_ENCRYPTED_TYPE or UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoStepDataAtchShouldBeFound("encryptedType.in=" + DEFAULT_ENCRYPTED_TYPE + "," + UPDATED_ENCRYPTED_TYPE);

        // Get all the planInfoStepDataAtchList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoStepDataAtchShouldNotBeFound("encryptedType.in=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByEncryptedTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where encryptedType is not null
        defaultPlanInfoStepDataAtchShouldBeFound("encryptedType.specified=true");

        // Get all the planInfoStepDataAtchList where encryptedType is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("encryptedType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoStepDataAtchShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoStepDataAtchList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepDataAtchShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoStepDataAtchShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoStepDataAtchList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepDataAtchShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where jsonString is not null
        defaultPlanInfoStepDataAtchShouldBeFound("jsonString.specified=true");

        // Get all the planInfoStepDataAtchList where jsonString is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoStepDataAtchShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoStepDataAtchList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepDataAtchShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoStepDataAtchShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoStepDataAtchList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepDataAtchShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where remarks is not null
        defaultPlanInfoStepDataAtchShouldBeFound("remarks.specified=true");

        // Get all the planInfoStepDataAtchList where remarks is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoStepDataAtchShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoStepDataAtchList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataAtchShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataAtchShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoStepDataAtchList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataAtchShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where attachmentPath is not null
        defaultPlanInfoStepDataAtchShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoStepDataAtchList where attachmentPath is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoStepDataAtchShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoStepDataAtchList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataAtchShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataAtchShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoStepDataAtchList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataAtchShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where attachmentName is not null
        defaultPlanInfoStepDataAtchShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoStepDataAtchList where attachmentName is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataAtchShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoStepDataAtchList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataAtchShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataAtchShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoStepDataAtchList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataAtchShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where imageBlobName is not null
        defaultPlanInfoStepDataAtchShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoStepDataAtchList where imageBlobName is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoStepDataAtchShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoStepDataAtchList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepDataAtchShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoStepDataAtchShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoStepDataAtchList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepDataAtchShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where validType is not null
        defaultPlanInfoStepDataAtchShouldBeFound("validType.specified=true");

        // Get all the planInfoStepDataAtchList where validType is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoStepDataAtchShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoStepDataAtchList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataAtchShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataAtchShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoStepDataAtchList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataAtchShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where validBegin is not null
        defaultPlanInfoStepDataAtchShouldBeFound("validBegin.specified=true");

        // Get all the planInfoStepDataAtchList where validBegin is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoStepDataAtchShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoStepDataAtchList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepDataAtchShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoStepDataAtchShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoStepDataAtchList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepDataAtchShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where validEnd is not null
        defaultPlanInfoStepDataAtchShouldBeFound("validEnd.specified=true");

        // Get all the planInfoStepDataAtchList where validEnd is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoStepDataAtchShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoStepDataAtchList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepDataAtchShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoStepDataAtchShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoStepDataAtchList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepDataAtchShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where insertTime is not null
        defaultPlanInfoStepDataAtchShouldBeFound("insertTime.specified=true");

        // Get all the planInfoStepDataAtchList where insertTime is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoStepDataAtchShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoStepDataAtchList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataAtchShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataAtchShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoStepDataAtchList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataAtchShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        // Get all the planInfoStepDataAtchList where updateTime is not null
        defaultPlanInfoStepDataAtchShouldBeFound("updateTime.specified=true");

        // Get all the planInfoStepDataAtchList where updateTime is null
        defaultPlanInfoStepDataAtchShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfoStepDataAtch.setCreatedBy(createdBy);
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);
        Long createdById = createdBy.getId();

        // Get all the planInfoStepDataAtchList where createdBy equals to createdById
        defaultPlanInfoStepDataAtchShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoStepDataAtchList where createdBy equals to createdById + 1
        defaultPlanInfoStepDataAtchShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfoStepDataAtch.setModifiedBy(modifiedBy);
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoStepDataAtchList where modifiedBy equals to modifiedById
        defaultPlanInfoStepDataAtchShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoStepDataAtchList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoStepDataAtchShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByPlanInfoStepAtchIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoStepAtch planInfoStepAtch = PlanInfoStepAtchResourceIntTest.createEntity(em);
        em.persist(planInfoStepAtch);
        em.flush();
        planInfoStepDataAtch.setPlanInfoStepAtch(planInfoStepAtch);
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);
        Long planInfoStepAtchId = planInfoStepAtch.getId();

        // Get all the planInfoStepDataAtchList where planInfoStepAtch equals to planInfoStepAtchId
        defaultPlanInfoStepDataAtchShouldBeFound("planInfoStepAtchId.equals=" + planInfoStepAtchId);

        // Get all the planInfoStepDataAtchList where planInfoStepAtch equals to planInfoStepAtchId + 1
        defaultPlanInfoStepDataAtchShouldNotBeFound("planInfoStepAtchId.equals=" + (planInfoStepAtchId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchesByPlanInfoStepDataIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoStepData planInfoStepData = PlanInfoStepDataResourceIntTest.createEntity(em);
        em.persist(planInfoStepData);
        em.flush();
        planInfoStepDataAtch.setPlanInfoStepData(planInfoStepData);
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);
        Long planInfoStepDataId = planInfoStepData.getId();

        // Get all the planInfoStepDataAtchList where planInfoStepData equals to planInfoStepDataId
        defaultPlanInfoStepDataAtchShouldBeFound("planInfoStepDataId.equals=" + planInfoStepDataId);

        // Get all the planInfoStepDataAtchList where planInfoStepData equals to planInfoStepDataId + 1
        defaultPlanInfoStepDataAtchShouldNotBeFound("planInfoStepDataId.equals=" + (planInfoStepDataId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoStepDataAtchShouldBeFound(String filter) throws Exception {
        restPlanInfoStepDataAtchMockMvc.perform(get("/api/plan-info-step-data-atches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepDataAtch.getId().intValue())))
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
        restPlanInfoStepDataAtchMockMvc.perform(get("/api/plan-info-step-data-atches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoStepDataAtchShouldNotBeFound(String filter) throws Exception {
        restPlanInfoStepDataAtchMockMvc.perform(get("/api/plan-info-step-data-atches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoStepDataAtchMockMvc.perform(get("/api/plan-info-step-data-atches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfoStepDataAtch() throws Exception {
        // Get the planInfoStepDataAtch
        restPlanInfoStepDataAtchMockMvc.perform(get("/api/plan-info-step-data-atches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfoStepDataAtch() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        int databaseSizeBeforeUpdate = planInfoStepDataAtchRepository.findAll().size();

        // Update the planInfoStepDataAtch
        PlanInfoStepDataAtch updatedPlanInfoStepDataAtch = planInfoStepDataAtchRepository.findById(planInfoStepDataAtch.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfoStepDataAtch are not directly saved in db
        em.detach(updatedPlanInfoStepDataAtch);
        updatedPlanInfoStepDataAtch
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
        PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO = planInfoStepDataAtchMapper.toDto(updatedPlanInfoStepDataAtch);

        restPlanInfoStepDataAtchMockMvc.perform(put("/api/plan-info-step-data-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataAtchDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfoStepDataAtch in the database
        List<PlanInfoStepDataAtch> planInfoStepDataAtchList = planInfoStepDataAtchRepository.findAll();
        assertThat(planInfoStepDataAtchList).hasSize(databaseSizeBeforeUpdate);
        PlanInfoStepDataAtch testPlanInfoStepDataAtch = planInfoStepDataAtchList.get(planInfoStepDataAtchList.size() - 1);
        assertThat(testPlanInfoStepDataAtch.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfoStepDataAtch.getStoreDir()).isEqualTo(UPDATED_STORE_DIR);
        assertThat(testPlanInfoStepDataAtch.getStoreName()).isEqualTo(UPDATED_STORE_NAME);
        assertThat(testPlanInfoStepDataAtch.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfoStepDataAtch.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testPlanInfoStepDataAtch.isDeleteFlag()).isEqualTo(UPDATED_DELETE_FLAG);
        assertThat(testPlanInfoStepDataAtch.getStoreType()).isEqualTo(UPDATED_STORE_TYPE);
        assertThat(testPlanInfoStepDataAtch.getVer()).isEqualTo(UPDATED_VER);
        assertThat(testPlanInfoStepDataAtch.getEncryptedFlag()).isEqualTo(UPDATED_ENCRYPTED_FLAG);
        assertThat(testPlanInfoStepDataAtch.getEncryptedType()).isEqualTo(UPDATED_ENCRYPTED_TYPE);
        assertThat(testPlanInfoStepDataAtch.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfoStepDataAtch.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfoStepDataAtch.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfoStepDataAtch.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStepDataAtch.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataAtch.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfoStepDataAtch.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfoStepDataAtch.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfoStepDataAtch.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataAtch.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStepDataAtch.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfoStepDataAtch.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfoStepDataAtch.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfoStepDataAtch.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfoStepDataAtch.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);

        // Validate the PlanInfoStepDataAtch in Elasticsearch
        verify(mockPlanInfoStepDataAtchSearchRepository, times(1)).save(testPlanInfoStepDataAtch);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfoStepDataAtch() throws Exception {
        int databaseSizeBeforeUpdate = planInfoStepDataAtchRepository.findAll().size();

        // Create the PlanInfoStepDataAtch
        PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO = planInfoStepDataAtchMapper.toDto(planInfoStepDataAtch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoStepDataAtchMockMvc.perform(put("/api/plan-info-step-data-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataAtchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStepDataAtch in the database
        List<PlanInfoStepDataAtch> planInfoStepDataAtchList = planInfoStepDataAtchRepository.findAll();
        assertThat(planInfoStepDataAtchList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfoStepDataAtch in Elasticsearch
        verify(mockPlanInfoStepDataAtchSearchRepository, times(0)).save(planInfoStepDataAtch);
    }

    @Test
    @Transactional
    public void deletePlanInfoStepDataAtch() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);

        int databaseSizeBeforeDelete = planInfoStepDataAtchRepository.findAll().size();

        // Get the planInfoStepDataAtch
        restPlanInfoStepDataAtchMockMvc.perform(delete("/api/plan-info-step-data-atches/{id}", planInfoStepDataAtch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfoStepDataAtch> planInfoStepDataAtchList = planInfoStepDataAtchRepository.findAll();
        assertThat(planInfoStepDataAtchList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfoStepDataAtch in Elasticsearch
        verify(mockPlanInfoStepDataAtchSearchRepository, times(1)).deleteById(planInfoStepDataAtch.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfoStepDataAtch() throws Exception {
        // Initialize the database
        planInfoStepDataAtchRepository.saveAndFlush(planInfoStepDataAtch);
        when(mockPlanInfoStepDataAtchSearchRepository.search(queryStringQuery("id:" + planInfoStepDataAtch.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfoStepDataAtch), PageRequest.of(0, 1), 1));
        // Search the planInfoStepDataAtch
        restPlanInfoStepDataAtchMockMvc.perform(get("/api/_search/plan-info-step-data-atches?query=id:" + planInfoStepDataAtch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepDataAtch.getId().intValue())))
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
        TestUtil.equalsVerifier(PlanInfoStepDataAtch.class);
        PlanInfoStepDataAtch planInfoStepDataAtch1 = new PlanInfoStepDataAtch();
        planInfoStepDataAtch1.setId(1L);
        PlanInfoStepDataAtch planInfoStepDataAtch2 = new PlanInfoStepDataAtch();
        planInfoStepDataAtch2.setId(planInfoStepDataAtch1.getId());
        assertThat(planInfoStepDataAtch1).isEqualTo(planInfoStepDataAtch2);
        planInfoStepDataAtch2.setId(2L);
        assertThat(planInfoStepDataAtch1).isNotEqualTo(planInfoStepDataAtch2);
        planInfoStepDataAtch1.setId(null);
        assertThat(planInfoStepDataAtch1).isNotEqualTo(planInfoStepDataAtch2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoStepDataAtchDTO.class);
        PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO1 = new PlanInfoStepDataAtchDTO();
        planInfoStepDataAtchDTO1.setId(1L);
        PlanInfoStepDataAtchDTO planInfoStepDataAtchDTO2 = new PlanInfoStepDataAtchDTO();
        assertThat(planInfoStepDataAtchDTO1).isNotEqualTo(planInfoStepDataAtchDTO2);
        planInfoStepDataAtchDTO2.setId(planInfoStepDataAtchDTO1.getId());
        assertThat(planInfoStepDataAtchDTO1).isEqualTo(planInfoStepDataAtchDTO2);
        planInfoStepDataAtchDTO2.setId(2L);
        assertThat(planInfoStepDataAtchDTO1).isNotEqualTo(planInfoStepDataAtchDTO2);
        planInfoStepDataAtchDTO1.setId(null);
        assertThat(planInfoStepDataAtchDTO1).isNotEqualTo(planInfoStepDataAtchDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoStepDataAtchMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoStepDataAtchMapper.fromId(null)).isNull();
    }
}
