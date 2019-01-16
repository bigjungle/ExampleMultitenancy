package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfoAtch;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.PlanInfo;
import com.aerothinker.plandb.repository.PlanInfoAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoAtchSearchRepository;
import com.aerothinker.plandb.service.PlanInfoAtchService;
import com.aerothinker.plandb.service.dto.PlanInfoAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoAtchMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoAtchCriteria;
import com.aerothinker.plandb.service.PlanInfoAtchQueryService;

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
 * Test class for the PlanInfoAtchResource REST controller.
 *
 * @see PlanInfoAtchResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoAtchResourceIntTest {

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

    private static final Instant DEFAULT_PUBLISHED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLISHED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PlanInfoAtchRepository planInfoAtchRepository;

    @Autowired
    private PlanInfoAtchMapper planInfoAtchMapper;

    @Autowired
    private PlanInfoAtchService planInfoAtchService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoAtchSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoAtchSearchRepository mockPlanInfoAtchSearchRepository;

    @Autowired
    private PlanInfoAtchQueryService planInfoAtchQueryService;

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

    private MockMvc restPlanInfoAtchMockMvc;

    private PlanInfoAtch planInfoAtch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoAtchResource planInfoAtchResource = new PlanInfoAtchResource(planInfoAtchService, planInfoAtchQueryService);
        this.restPlanInfoAtchMockMvc = MockMvcBuilders.standaloneSetup(planInfoAtchResource)
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
    public static PlanInfoAtch createEntity(EntityManager em) {
        PlanInfoAtch planInfoAtch = new PlanInfoAtch()
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
            .updateTime(DEFAULT_UPDATE_TIME)
            .publishedTime(DEFAULT_PUBLISHED_TIME);
        return planInfoAtch;
    }

    @Before
    public void initTest() {
        planInfoAtch = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfoAtch() throws Exception {
        int databaseSizeBeforeCreate = planInfoAtchRepository.findAll().size();

        // Create the PlanInfoAtch
        PlanInfoAtchDTO planInfoAtchDTO = planInfoAtchMapper.toDto(planInfoAtch);
        restPlanInfoAtchMockMvc.perform(post("/api/plan-info-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoAtchDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfoAtch in the database
        List<PlanInfoAtch> planInfoAtchList = planInfoAtchRepository.findAll();
        assertThat(planInfoAtchList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfoAtch testPlanInfoAtch = planInfoAtchList.get(planInfoAtchList.size() - 1);
        assertThat(testPlanInfoAtch.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfoAtch.getStoreDir()).isEqualTo(DEFAULT_STORE_DIR);
        assertThat(testPlanInfoAtch.getStoreName()).isEqualTo(DEFAULT_STORE_NAME);
        assertThat(testPlanInfoAtch.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfoAtch.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testPlanInfoAtch.isDeleteFlag()).isEqualTo(DEFAULT_DELETE_FLAG);
        assertThat(testPlanInfoAtch.getStoreType()).isEqualTo(DEFAULT_STORE_TYPE);
        assertThat(testPlanInfoAtch.getVer()).isEqualTo(DEFAULT_VER);
        assertThat(testPlanInfoAtch.getEncryptedFlag()).isEqualTo(DEFAULT_ENCRYPTED_FLAG);
        assertThat(testPlanInfoAtch.getEncryptedType()).isEqualTo(DEFAULT_ENCRYPTED_TYPE);
        assertThat(testPlanInfoAtch.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfoAtch.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfoAtch.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfoAtch.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfoAtch.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoAtch.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfoAtch.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfoAtch.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfoAtch.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoAtch.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoAtch.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfoAtch.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfoAtch.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfoAtch.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfoAtch.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPlanInfoAtch.getPublishedTime()).isEqualTo(DEFAULT_PUBLISHED_TIME);

        // Validate the PlanInfoAtch in Elasticsearch
        verify(mockPlanInfoAtchSearchRepository, times(1)).save(testPlanInfoAtch);
    }

    @Test
    @Transactional
    public void createPlanInfoAtchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoAtchRepository.findAll().size();

        // Create the PlanInfoAtch with an existing ID
        planInfoAtch.setId(1L);
        PlanInfoAtchDTO planInfoAtchDTO = planInfoAtchMapper.toDto(planInfoAtch);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoAtchMockMvc.perform(post("/api/plan-info-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoAtchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoAtch in the database
        List<PlanInfoAtch> planInfoAtchList = planInfoAtchRepository.findAll();
        assertThat(planInfoAtchList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfoAtch in Elasticsearch
        verify(mockPlanInfoAtchSearchRepository, times(0)).save(planInfoAtch);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoAtchRepository.findAll().size();
        // set the field null
        planInfoAtch.setName(null);

        // Create the PlanInfoAtch, which fails.
        PlanInfoAtchDTO planInfoAtchDTO = planInfoAtchMapper.toDto(planInfoAtch);

        restPlanInfoAtchMockMvc.perform(post("/api/plan-info-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoAtchDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfoAtch> planInfoAtchList = planInfoAtchRepository.findAll();
        assertThat(planInfoAtchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtches() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList
        restPlanInfoAtchMockMvc.perform(get("/api/plan-info-atches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoAtch.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].publishedTime").value(hasItem(DEFAULT_PUBLISHED_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getPlanInfoAtch() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get the planInfoAtch
        restPlanInfoAtchMockMvc.perform(get("/api/plan-info-atches/{id}", planInfoAtch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfoAtch.getId().intValue()))
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
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.publishedTime").value(DEFAULT_PUBLISHED_TIME.toString()));
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where name equals to DEFAULT_NAME
        defaultPlanInfoAtchShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoAtchList where name equals to UPDATED_NAME
        defaultPlanInfoAtchShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoAtchShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoAtchList where name equals to UPDATED_NAME
        defaultPlanInfoAtchShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where name is not null
        defaultPlanInfoAtchShouldBeFound("name.specified=true");

        // Get all the planInfoAtchList where name is null
        defaultPlanInfoAtchShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByStoreDirIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where storeDir equals to DEFAULT_STORE_DIR
        defaultPlanInfoAtchShouldBeFound("storeDir.equals=" + DEFAULT_STORE_DIR);

        // Get all the planInfoAtchList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoAtchShouldNotBeFound("storeDir.equals=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByStoreDirIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where storeDir in DEFAULT_STORE_DIR or UPDATED_STORE_DIR
        defaultPlanInfoAtchShouldBeFound("storeDir.in=" + DEFAULT_STORE_DIR + "," + UPDATED_STORE_DIR);

        // Get all the planInfoAtchList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoAtchShouldNotBeFound("storeDir.in=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByStoreDirIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where storeDir is not null
        defaultPlanInfoAtchShouldBeFound("storeDir.specified=true");

        // Get all the planInfoAtchList where storeDir is null
        defaultPlanInfoAtchShouldNotBeFound("storeDir.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByStoreNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where storeName equals to DEFAULT_STORE_NAME
        defaultPlanInfoAtchShouldBeFound("storeName.equals=" + DEFAULT_STORE_NAME);

        // Get all the planInfoAtchList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoAtchShouldNotBeFound("storeName.equals=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByStoreNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where storeName in DEFAULT_STORE_NAME or UPDATED_STORE_NAME
        defaultPlanInfoAtchShouldBeFound("storeName.in=" + DEFAULT_STORE_NAME + "," + UPDATED_STORE_NAME);

        // Get all the planInfoAtchList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoAtchShouldNotBeFound("storeName.in=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByStoreNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where storeName is not null
        defaultPlanInfoAtchShouldBeFound("storeName.specified=true");

        // Get all the planInfoAtchList where storeName is null
        defaultPlanInfoAtchShouldNotBeFound("storeName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoAtchShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoAtchList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoAtchShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoAtchShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoAtchList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoAtchShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where sortString is not null
        defaultPlanInfoAtchShouldBeFound("sortString.specified=true");

        // Get all the planInfoAtchList where sortString is null
        defaultPlanInfoAtchShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByFileTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where fileType equals to DEFAULT_FILE_TYPE
        defaultPlanInfoAtchShouldBeFound("fileType.equals=" + DEFAULT_FILE_TYPE);

        // Get all the planInfoAtchList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoAtchShouldNotBeFound("fileType.equals=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByFileTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where fileType in DEFAULT_FILE_TYPE or UPDATED_FILE_TYPE
        defaultPlanInfoAtchShouldBeFound("fileType.in=" + DEFAULT_FILE_TYPE + "," + UPDATED_FILE_TYPE);

        // Get all the planInfoAtchList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoAtchShouldNotBeFound("fileType.in=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByFileTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where fileType is not null
        defaultPlanInfoAtchShouldBeFound("fileType.specified=true");

        // Get all the planInfoAtchList where fileType is null
        defaultPlanInfoAtchShouldNotBeFound("fileType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByDeleteFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where deleteFlag equals to DEFAULT_DELETE_FLAG
        defaultPlanInfoAtchShouldBeFound("deleteFlag.equals=" + DEFAULT_DELETE_FLAG);

        // Get all the planInfoAtchList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoAtchShouldNotBeFound("deleteFlag.equals=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByDeleteFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where deleteFlag in DEFAULT_DELETE_FLAG or UPDATED_DELETE_FLAG
        defaultPlanInfoAtchShouldBeFound("deleteFlag.in=" + DEFAULT_DELETE_FLAG + "," + UPDATED_DELETE_FLAG);

        // Get all the planInfoAtchList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoAtchShouldNotBeFound("deleteFlag.in=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByDeleteFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where deleteFlag is not null
        defaultPlanInfoAtchShouldBeFound("deleteFlag.specified=true");

        // Get all the planInfoAtchList where deleteFlag is null
        defaultPlanInfoAtchShouldNotBeFound("deleteFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByStoreTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where storeType equals to DEFAULT_STORE_TYPE
        defaultPlanInfoAtchShouldBeFound("storeType.equals=" + DEFAULT_STORE_TYPE);

        // Get all the planInfoAtchList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoAtchShouldNotBeFound("storeType.equals=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByStoreTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where storeType in DEFAULT_STORE_TYPE or UPDATED_STORE_TYPE
        defaultPlanInfoAtchShouldBeFound("storeType.in=" + DEFAULT_STORE_TYPE + "," + UPDATED_STORE_TYPE);

        // Get all the planInfoAtchList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoAtchShouldNotBeFound("storeType.in=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByStoreTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where storeType is not null
        defaultPlanInfoAtchShouldBeFound("storeType.specified=true");

        // Get all the planInfoAtchList where storeType is null
        defaultPlanInfoAtchShouldNotBeFound("storeType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByVerIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where ver equals to DEFAULT_VER
        defaultPlanInfoAtchShouldBeFound("ver.equals=" + DEFAULT_VER);

        // Get all the planInfoAtchList where ver equals to UPDATED_VER
        defaultPlanInfoAtchShouldNotBeFound("ver.equals=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByVerIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where ver in DEFAULT_VER or UPDATED_VER
        defaultPlanInfoAtchShouldBeFound("ver.in=" + DEFAULT_VER + "," + UPDATED_VER);

        // Get all the planInfoAtchList where ver equals to UPDATED_VER
        defaultPlanInfoAtchShouldNotBeFound("ver.in=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByVerIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where ver is not null
        defaultPlanInfoAtchShouldBeFound("ver.specified=true");

        // Get all the planInfoAtchList where ver is null
        defaultPlanInfoAtchShouldNotBeFound("ver.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByEncryptedFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where encryptedFlag equals to DEFAULT_ENCRYPTED_FLAG
        defaultPlanInfoAtchShouldBeFound("encryptedFlag.equals=" + DEFAULT_ENCRYPTED_FLAG);

        // Get all the planInfoAtchList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoAtchShouldNotBeFound("encryptedFlag.equals=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByEncryptedFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where encryptedFlag in DEFAULT_ENCRYPTED_FLAG or UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoAtchShouldBeFound("encryptedFlag.in=" + DEFAULT_ENCRYPTED_FLAG + "," + UPDATED_ENCRYPTED_FLAG);

        // Get all the planInfoAtchList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoAtchShouldNotBeFound("encryptedFlag.in=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByEncryptedFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where encryptedFlag is not null
        defaultPlanInfoAtchShouldBeFound("encryptedFlag.specified=true");

        // Get all the planInfoAtchList where encryptedFlag is null
        defaultPlanInfoAtchShouldNotBeFound("encryptedFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByEncryptedTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where encryptedType equals to DEFAULT_ENCRYPTED_TYPE
        defaultPlanInfoAtchShouldBeFound("encryptedType.equals=" + DEFAULT_ENCRYPTED_TYPE);

        // Get all the planInfoAtchList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoAtchShouldNotBeFound("encryptedType.equals=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByEncryptedTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where encryptedType in DEFAULT_ENCRYPTED_TYPE or UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoAtchShouldBeFound("encryptedType.in=" + DEFAULT_ENCRYPTED_TYPE + "," + UPDATED_ENCRYPTED_TYPE);

        // Get all the planInfoAtchList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoAtchShouldNotBeFound("encryptedType.in=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByEncryptedTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where encryptedType is not null
        defaultPlanInfoAtchShouldBeFound("encryptedType.specified=true");

        // Get all the planInfoAtchList where encryptedType is null
        defaultPlanInfoAtchShouldNotBeFound("encryptedType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoAtchShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoAtchList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoAtchShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoAtchShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoAtchList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoAtchShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where jsonString is not null
        defaultPlanInfoAtchShouldBeFound("jsonString.specified=true");

        // Get all the planInfoAtchList where jsonString is null
        defaultPlanInfoAtchShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoAtchShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoAtchList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoAtchShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoAtchShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoAtchList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoAtchShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where remarks is not null
        defaultPlanInfoAtchShouldBeFound("remarks.specified=true");

        // Get all the planInfoAtchList where remarks is null
        defaultPlanInfoAtchShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoAtchShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoAtchList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoAtchShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoAtchShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoAtchList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoAtchShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where attachmentPath is not null
        defaultPlanInfoAtchShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoAtchList where attachmentPath is null
        defaultPlanInfoAtchShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoAtchShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoAtchList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoAtchShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoAtchShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoAtchList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoAtchShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where attachmentName is not null
        defaultPlanInfoAtchShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoAtchList where attachmentName is null
        defaultPlanInfoAtchShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoAtchShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoAtchList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoAtchShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoAtchShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoAtchList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoAtchShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where imageBlobName is not null
        defaultPlanInfoAtchShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoAtchList where imageBlobName is null
        defaultPlanInfoAtchShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoAtchShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoAtchList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoAtchShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoAtchShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoAtchList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoAtchShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where validType is not null
        defaultPlanInfoAtchShouldBeFound("validType.specified=true");

        // Get all the planInfoAtchList where validType is null
        defaultPlanInfoAtchShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoAtchShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoAtchList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoAtchShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoAtchShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoAtchList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoAtchShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where validBegin is not null
        defaultPlanInfoAtchShouldBeFound("validBegin.specified=true");

        // Get all the planInfoAtchList where validBegin is null
        defaultPlanInfoAtchShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoAtchShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoAtchList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoAtchShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoAtchShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoAtchList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoAtchShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where validEnd is not null
        defaultPlanInfoAtchShouldBeFound("validEnd.specified=true");

        // Get all the planInfoAtchList where validEnd is null
        defaultPlanInfoAtchShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoAtchShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoAtchList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoAtchShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoAtchShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoAtchList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoAtchShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where insertTime is not null
        defaultPlanInfoAtchShouldBeFound("insertTime.specified=true");

        // Get all the planInfoAtchList where insertTime is null
        defaultPlanInfoAtchShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoAtchShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoAtchList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoAtchShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoAtchShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoAtchList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoAtchShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where updateTime is not null
        defaultPlanInfoAtchShouldBeFound("updateTime.specified=true");

        // Get all the planInfoAtchList where updateTime is null
        defaultPlanInfoAtchShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByPublishedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where publishedTime equals to DEFAULT_PUBLISHED_TIME
        defaultPlanInfoAtchShouldBeFound("publishedTime.equals=" + DEFAULT_PUBLISHED_TIME);

        // Get all the planInfoAtchList where publishedTime equals to UPDATED_PUBLISHED_TIME
        defaultPlanInfoAtchShouldNotBeFound("publishedTime.equals=" + UPDATED_PUBLISHED_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByPublishedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where publishedTime in DEFAULT_PUBLISHED_TIME or UPDATED_PUBLISHED_TIME
        defaultPlanInfoAtchShouldBeFound("publishedTime.in=" + DEFAULT_PUBLISHED_TIME + "," + UPDATED_PUBLISHED_TIME);

        // Get all the planInfoAtchList where publishedTime equals to UPDATED_PUBLISHED_TIME
        defaultPlanInfoAtchShouldNotBeFound("publishedTime.in=" + UPDATED_PUBLISHED_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByPublishedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        // Get all the planInfoAtchList where publishedTime is not null
        defaultPlanInfoAtchShouldBeFound("publishedTime.specified=true");

        // Get all the planInfoAtchList where publishedTime is null
        defaultPlanInfoAtchShouldNotBeFound("publishedTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoAtchesByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfoAtch.setCreatedBy(createdBy);
        planInfoAtchRepository.saveAndFlush(planInfoAtch);
        Long createdById = createdBy.getId();

        // Get all the planInfoAtchList where createdBy equals to createdById
        defaultPlanInfoAtchShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoAtchList where createdBy equals to createdById + 1
        defaultPlanInfoAtchShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoAtchesByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfoAtch.setModifiedBy(modifiedBy);
        planInfoAtchRepository.saveAndFlush(planInfoAtch);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoAtchList where modifiedBy equals to modifiedById
        defaultPlanInfoAtchShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoAtchList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoAtchShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoAtchesByPlanInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfo planInfo = PlanInfoResourceIntTest.createEntity(em);
        em.persist(planInfo);
        em.flush();
        planInfoAtch.setPlanInfo(planInfo);
        planInfoAtchRepository.saveAndFlush(planInfoAtch);
        Long planInfoId = planInfo.getId();

        // Get all the planInfoAtchList where planInfo equals to planInfoId
        defaultPlanInfoAtchShouldBeFound("planInfoId.equals=" + planInfoId);

        // Get all the planInfoAtchList where planInfo equals to planInfoId + 1
        defaultPlanInfoAtchShouldNotBeFound("planInfoId.equals=" + (planInfoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoAtchShouldBeFound(String filter) throws Exception {
        restPlanInfoAtchMockMvc.perform(get("/api/plan-info-atches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoAtch.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].publishedTime").value(hasItem(DEFAULT_PUBLISHED_TIME.toString())));

        // Check, that the count call also returns 1
        restPlanInfoAtchMockMvc.perform(get("/api/plan-info-atches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoAtchShouldNotBeFound(String filter) throws Exception {
        restPlanInfoAtchMockMvc.perform(get("/api/plan-info-atches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoAtchMockMvc.perform(get("/api/plan-info-atches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfoAtch() throws Exception {
        // Get the planInfoAtch
        restPlanInfoAtchMockMvc.perform(get("/api/plan-info-atches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfoAtch() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        int databaseSizeBeforeUpdate = planInfoAtchRepository.findAll().size();

        // Update the planInfoAtch
        PlanInfoAtch updatedPlanInfoAtch = planInfoAtchRepository.findById(planInfoAtch.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfoAtch are not directly saved in db
        em.detach(updatedPlanInfoAtch);
        updatedPlanInfoAtch
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
            .updateTime(UPDATED_UPDATE_TIME)
            .publishedTime(UPDATED_PUBLISHED_TIME);
        PlanInfoAtchDTO planInfoAtchDTO = planInfoAtchMapper.toDto(updatedPlanInfoAtch);

        restPlanInfoAtchMockMvc.perform(put("/api/plan-info-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoAtchDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfoAtch in the database
        List<PlanInfoAtch> planInfoAtchList = planInfoAtchRepository.findAll();
        assertThat(planInfoAtchList).hasSize(databaseSizeBeforeUpdate);
        PlanInfoAtch testPlanInfoAtch = planInfoAtchList.get(planInfoAtchList.size() - 1);
        assertThat(testPlanInfoAtch.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfoAtch.getStoreDir()).isEqualTo(UPDATED_STORE_DIR);
        assertThat(testPlanInfoAtch.getStoreName()).isEqualTo(UPDATED_STORE_NAME);
        assertThat(testPlanInfoAtch.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfoAtch.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testPlanInfoAtch.isDeleteFlag()).isEqualTo(UPDATED_DELETE_FLAG);
        assertThat(testPlanInfoAtch.getStoreType()).isEqualTo(UPDATED_STORE_TYPE);
        assertThat(testPlanInfoAtch.getVer()).isEqualTo(UPDATED_VER);
        assertThat(testPlanInfoAtch.getEncryptedFlag()).isEqualTo(UPDATED_ENCRYPTED_FLAG);
        assertThat(testPlanInfoAtch.getEncryptedType()).isEqualTo(UPDATED_ENCRYPTED_TYPE);
        assertThat(testPlanInfoAtch.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfoAtch.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfoAtch.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfoAtch.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfoAtch.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoAtch.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfoAtch.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfoAtch.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfoAtch.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoAtch.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoAtch.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfoAtch.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfoAtch.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfoAtch.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfoAtch.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPlanInfoAtch.getPublishedTime()).isEqualTo(UPDATED_PUBLISHED_TIME);

        // Validate the PlanInfoAtch in Elasticsearch
        verify(mockPlanInfoAtchSearchRepository, times(1)).save(testPlanInfoAtch);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfoAtch() throws Exception {
        int databaseSizeBeforeUpdate = planInfoAtchRepository.findAll().size();

        // Create the PlanInfoAtch
        PlanInfoAtchDTO planInfoAtchDTO = planInfoAtchMapper.toDto(planInfoAtch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoAtchMockMvc.perform(put("/api/plan-info-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoAtchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoAtch in the database
        List<PlanInfoAtch> planInfoAtchList = planInfoAtchRepository.findAll();
        assertThat(planInfoAtchList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfoAtch in Elasticsearch
        verify(mockPlanInfoAtchSearchRepository, times(0)).save(planInfoAtch);
    }

    @Test
    @Transactional
    public void deletePlanInfoAtch() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);

        int databaseSizeBeforeDelete = planInfoAtchRepository.findAll().size();

        // Get the planInfoAtch
        restPlanInfoAtchMockMvc.perform(delete("/api/plan-info-atches/{id}", planInfoAtch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfoAtch> planInfoAtchList = planInfoAtchRepository.findAll();
        assertThat(planInfoAtchList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfoAtch in Elasticsearch
        verify(mockPlanInfoAtchSearchRepository, times(1)).deleteById(planInfoAtch.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfoAtch() throws Exception {
        // Initialize the database
        planInfoAtchRepository.saveAndFlush(planInfoAtch);
        when(mockPlanInfoAtchSearchRepository.search(queryStringQuery("id:" + planInfoAtch.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfoAtch), PageRequest.of(0, 1), 1));
        // Search the planInfoAtch
        restPlanInfoAtchMockMvc.perform(get("/api/_search/plan-info-atches?query=id:" + planInfoAtch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoAtch.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].publishedTime").value(hasItem(DEFAULT_PUBLISHED_TIME.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoAtch.class);
        PlanInfoAtch planInfoAtch1 = new PlanInfoAtch();
        planInfoAtch1.setId(1L);
        PlanInfoAtch planInfoAtch2 = new PlanInfoAtch();
        planInfoAtch2.setId(planInfoAtch1.getId());
        assertThat(planInfoAtch1).isEqualTo(planInfoAtch2);
        planInfoAtch2.setId(2L);
        assertThat(planInfoAtch1).isNotEqualTo(planInfoAtch2);
        planInfoAtch1.setId(null);
        assertThat(planInfoAtch1).isNotEqualTo(planInfoAtch2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoAtchDTO.class);
        PlanInfoAtchDTO planInfoAtchDTO1 = new PlanInfoAtchDTO();
        planInfoAtchDTO1.setId(1L);
        PlanInfoAtchDTO planInfoAtchDTO2 = new PlanInfoAtchDTO();
        assertThat(planInfoAtchDTO1).isNotEqualTo(planInfoAtchDTO2);
        planInfoAtchDTO2.setId(planInfoAtchDTO1.getId());
        assertThat(planInfoAtchDTO1).isEqualTo(planInfoAtchDTO2);
        planInfoAtchDTO2.setId(2L);
        assertThat(planInfoAtchDTO1).isNotEqualTo(planInfoAtchDTO2);
        planInfoAtchDTO1.setId(null);
        assertThat(planInfoAtchDTO1).isNotEqualTo(planInfoAtchDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoAtchMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoAtchMapper.fromId(null)).isNull();
    }
}
