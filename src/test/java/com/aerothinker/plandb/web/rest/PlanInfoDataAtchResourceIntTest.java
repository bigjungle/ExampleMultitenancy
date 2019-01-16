package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfoDataAtch;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.PlanInfoAtch;
import com.aerothinker.plandb.domain.PlanInfoData;
import com.aerothinker.plandb.repository.PlanInfoDataAtchRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataAtchSearchRepository;
import com.aerothinker.plandb.service.PlanInfoDataAtchService;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataAtchMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchCriteria;
import com.aerothinker.plandb.service.PlanInfoDataAtchQueryService;

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
 * Test class for the PlanInfoDataAtchResource REST controller.
 *
 * @see PlanInfoDataAtchResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoDataAtchResourceIntTest {

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
    private PlanInfoDataAtchRepository planInfoDataAtchRepository;

    @Autowired
    private PlanInfoDataAtchMapper planInfoDataAtchMapper;

    @Autowired
    private PlanInfoDataAtchService planInfoDataAtchService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoDataAtchSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoDataAtchSearchRepository mockPlanInfoDataAtchSearchRepository;

    @Autowired
    private PlanInfoDataAtchQueryService planInfoDataAtchQueryService;

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

    private MockMvc restPlanInfoDataAtchMockMvc;

    private PlanInfoDataAtch planInfoDataAtch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoDataAtchResource planInfoDataAtchResource = new PlanInfoDataAtchResource(planInfoDataAtchService, planInfoDataAtchQueryService);
        this.restPlanInfoDataAtchMockMvc = MockMvcBuilders.standaloneSetup(planInfoDataAtchResource)
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
    public static PlanInfoDataAtch createEntity(EntityManager em) {
        PlanInfoDataAtch planInfoDataAtch = new PlanInfoDataAtch()
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
        return planInfoDataAtch;
    }

    @Before
    public void initTest() {
        planInfoDataAtch = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfoDataAtch() throws Exception {
        int databaseSizeBeforeCreate = planInfoDataAtchRepository.findAll().size();

        // Create the PlanInfoDataAtch
        PlanInfoDataAtchDTO planInfoDataAtchDTO = planInfoDataAtchMapper.toDto(planInfoDataAtch);
        restPlanInfoDataAtchMockMvc.perform(post("/api/plan-info-data-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataAtchDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfoDataAtch in the database
        List<PlanInfoDataAtch> planInfoDataAtchList = planInfoDataAtchRepository.findAll();
        assertThat(planInfoDataAtchList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfoDataAtch testPlanInfoDataAtch = planInfoDataAtchList.get(planInfoDataAtchList.size() - 1);
        assertThat(testPlanInfoDataAtch.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfoDataAtch.getStoreDir()).isEqualTo(DEFAULT_STORE_DIR);
        assertThat(testPlanInfoDataAtch.getStoreName()).isEqualTo(DEFAULT_STORE_NAME);
        assertThat(testPlanInfoDataAtch.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfoDataAtch.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testPlanInfoDataAtch.isDeleteFlag()).isEqualTo(DEFAULT_DELETE_FLAG);
        assertThat(testPlanInfoDataAtch.getStoreType()).isEqualTo(DEFAULT_STORE_TYPE);
        assertThat(testPlanInfoDataAtch.getVer()).isEqualTo(DEFAULT_VER);
        assertThat(testPlanInfoDataAtch.getEncryptedFlag()).isEqualTo(DEFAULT_ENCRYPTED_FLAG);
        assertThat(testPlanInfoDataAtch.getEncryptedType()).isEqualTo(DEFAULT_ENCRYPTED_TYPE);
        assertThat(testPlanInfoDataAtch.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfoDataAtch.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfoDataAtch.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfoDataAtch.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfoDataAtch.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataAtch.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfoDataAtch.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfoDataAtch.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfoDataAtch.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataAtch.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoDataAtch.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfoDataAtch.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfoDataAtch.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfoDataAtch.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfoDataAtch.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);

        // Validate the PlanInfoDataAtch in Elasticsearch
        verify(mockPlanInfoDataAtchSearchRepository, times(1)).save(testPlanInfoDataAtch);
    }

    @Test
    @Transactional
    public void createPlanInfoDataAtchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoDataAtchRepository.findAll().size();

        // Create the PlanInfoDataAtch with an existing ID
        planInfoDataAtch.setId(1L);
        PlanInfoDataAtchDTO planInfoDataAtchDTO = planInfoDataAtchMapper.toDto(planInfoDataAtch);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoDataAtchMockMvc.perform(post("/api/plan-info-data-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataAtchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoDataAtch in the database
        List<PlanInfoDataAtch> planInfoDataAtchList = planInfoDataAtchRepository.findAll();
        assertThat(planInfoDataAtchList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfoDataAtch in Elasticsearch
        verify(mockPlanInfoDataAtchSearchRepository, times(0)).save(planInfoDataAtch);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoDataAtchRepository.findAll().size();
        // set the field null
        planInfoDataAtch.setName(null);

        // Create the PlanInfoDataAtch, which fails.
        PlanInfoDataAtchDTO planInfoDataAtchDTO = planInfoDataAtchMapper.toDto(planInfoDataAtch);

        restPlanInfoDataAtchMockMvc.perform(post("/api/plan-info-data-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataAtchDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfoDataAtch> planInfoDataAtchList = planInfoDataAtchRepository.findAll();
        assertThat(planInfoDataAtchList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtches() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList
        restPlanInfoDataAtchMockMvc.perform(get("/api/plan-info-data-atches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoDataAtch.getId().intValue())))
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
    public void getPlanInfoDataAtch() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get the planInfoDataAtch
        restPlanInfoDataAtchMockMvc.perform(get("/api/plan-info-data-atches/{id}", planInfoDataAtch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfoDataAtch.getId().intValue()))
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
    public void getAllPlanInfoDataAtchesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where name equals to DEFAULT_NAME
        defaultPlanInfoDataAtchShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoDataAtchList where name equals to UPDATED_NAME
        defaultPlanInfoDataAtchShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoDataAtchShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoDataAtchList where name equals to UPDATED_NAME
        defaultPlanInfoDataAtchShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where name is not null
        defaultPlanInfoDataAtchShouldBeFound("name.specified=true");

        // Get all the planInfoDataAtchList where name is null
        defaultPlanInfoDataAtchShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByStoreDirIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where storeDir equals to DEFAULT_STORE_DIR
        defaultPlanInfoDataAtchShouldBeFound("storeDir.equals=" + DEFAULT_STORE_DIR);

        // Get all the planInfoDataAtchList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoDataAtchShouldNotBeFound("storeDir.equals=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByStoreDirIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where storeDir in DEFAULT_STORE_DIR or UPDATED_STORE_DIR
        defaultPlanInfoDataAtchShouldBeFound("storeDir.in=" + DEFAULT_STORE_DIR + "," + UPDATED_STORE_DIR);

        // Get all the planInfoDataAtchList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoDataAtchShouldNotBeFound("storeDir.in=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByStoreDirIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where storeDir is not null
        defaultPlanInfoDataAtchShouldBeFound("storeDir.specified=true");

        // Get all the planInfoDataAtchList where storeDir is null
        defaultPlanInfoDataAtchShouldNotBeFound("storeDir.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByStoreNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where storeName equals to DEFAULT_STORE_NAME
        defaultPlanInfoDataAtchShouldBeFound("storeName.equals=" + DEFAULT_STORE_NAME);

        // Get all the planInfoDataAtchList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoDataAtchShouldNotBeFound("storeName.equals=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByStoreNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where storeName in DEFAULT_STORE_NAME or UPDATED_STORE_NAME
        defaultPlanInfoDataAtchShouldBeFound("storeName.in=" + DEFAULT_STORE_NAME + "," + UPDATED_STORE_NAME);

        // Get all the planInfoDataAtchList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoDataAtchShouldNotBeFound("storeName.in=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByStoreNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where storeName is not null
        defaultPlanInfoDataAtchShouldBeFound("storeName.specified=true");

        // Get all the planInfoDataAtchList where storeName is null
        defaultPlanInfoDataAtchShouldNotBeFound("storeName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoDataAtchShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoDataAtchList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoDataAtchShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoDataAtchShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoDataAtchList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoDataAtchShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where sortString is not null
        defaultPlanInfoDataAtchShouldBeFound("sortString.specified=true");

        // Get all the planInfoDataAtchList where sortString is null
        defaultPlanInfoDataAtchShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByFileTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where fileType equals to DEFAULT_FILE_TYPE
        defaultPlanInfoDataAtchShouldBeFound("fileType.equals=" + DEFAULT_FILE_TYPE);

        // Get all the planInfoDataAtchList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoDataAtchShouldNotBeFound("fileType.equals=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByFileTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where fileType in DEFAULT_FILE_TYPE or UPDATED_FILE_TYPE
        defaultPlanInfoDataAtchShouldBeFound("fileType.in=" + DEFAULT_FILE_TYPE + "," + UPDATED_FILE_TYPE);

        // Get all the planInfoDataAtchList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoDataAtchShouldNotBeFound("fileType.in=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByFileTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where fileType is not null
        defaultPlanInfoDataAtchShouldBeFound("fileType.specified=true");

        // Get all the planInfoDataAtchList where fileType is null
        defaultPlanInfoDataAtchShouldNotBeFound("fileType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByDeleteFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where deleteFlag equals to DEFAULT_DELETE_FLAG
        defaultPlanInfoDataAtchShouldBeFound("deleteFlag.equals=" + DEFAULT_DELETE_FLAG);

        // Get all the planInfoDataAtchList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoDataAtchShouldNotBeFound("deleteFlag.equals=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByDeleteFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where deleteFlag in DEFAULT_DELETE_FLAG or UPDATED_DELETE_FLAG
        defaultPlanInfoDataAtchShouldBeFound("deleteFlag.in=" + DEFAULT_DELETE_FLAG + "," + UPDATED_DELETE_FLAG);

        // Get all the planInfoDataAtchList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoDataAtchShouldNotBeFound("deleteFlag.in=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByDeleteFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where deleteFlag is not null
        defaultPlanInfoDataAtchShouldBeFound("deleteFlag.specified=true");

        // Get all the planInfoDataAtchList where deleteFlag is null
        defaultPlanInfoDataAtchShouldNotBeFound("deleteFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByStoreTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where storeType equals to DEFAULT_STORE_TYPE
        defaultPlanInfoDataAtchShouldBeFound("storeType.equals=" + DEFAULT_STORE_TYPE);

        // Get all the planInfoDataAtchList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoDataAtchShouldNotBeFound("storeType.equals=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByStoreTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where storeType in DEFAULT_STORE_TYPE or UPDATED_STORE_TYPE
        defaultPlanInfoDataAtchShouldBeFound("storeType.in=" + DEFAULT_STORE_TYPE + "," + UPDATED_STORE_TYPE);

        // Get all the planInfoDataAtchList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoDataAtchShouldNotBeFound("storeType.in=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByStoreTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where storeType is not null
        defaultPlanInfoDataAtchShouldBeFound("storeType.specified=true");

        // Get all the planInfoDataAtchList where storeType is null
        defaultPlanInfoDataAtchShouldNotBeFound("storeType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByVerIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where ver equals to DEFAULT_VER
        defaultPlanInfoDataAtchShouldBeFound("ver.equals=" + DEFAULT_VER);

        // Get all the planInfoDataAtchList where ver equals to UPDATED_VER
        defaultPlanInfoDataAtchShouldNotBeFound("ver.equals=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByVerIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where ver in DEFAULT_VER or UPDATED_VER
        defaultPlanInfoDataAtchShouldBeFound("ver.in=" + DEFAULT_VER + "," + UPDATED_VER);

        // Get all the planInfoDataAtchList where ver equals to UPDATED_VER
        defaultPlanInfoDataAtchShouldNotBeFound("ver.in=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByVerIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where ver is not null
        defaultPlanInfoDataAtchShouldBeFound("ver.specified=true");

        // Get all the planInfoDataAtchList where ver is null
        defaultPlanInfoDataAtchShouldNotBeFound("ver.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByEncryptedFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where encryptedFlag equals to DEFAULT_ENCRYPTED_FLAG
        defaultPlanInfoDataAtchShouldBeFound("encryptedFlag.equals=" + DEFAULT_ENCRYPTED_FLAG);

        // Get all the planInfoDataAtchList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoDataAtchShouldNotBeFound("encryptedFlag.equals=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByEncryptedFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where encryptedFlag in DEFAULT_ENCRYPTED_FLAG or UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoDataAtchShouldBeFound("encryptedFlag.in=" + DEFAULT_ENCRYPTED_FLAG + "," + UPDATED_ENCRYPTED_FLAG);

        // Get all the planInfoDataAtchList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoDataAtchShouldNotBeFound("encryptedFlag.in=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByEncryptedFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where encryptedFlag is not null
        defaultPlanInfoDataAtchShouldBeFound("encryptedFlag.specified=true");

        // Get all the planInfoDataAtchList where encryptedFlag is null
        defaultPlanInfoDataAtchShouldNotBeFound("encryptedFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByEncryptedTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where encryptedType equals to DEFAULT_ENCRYPTED_TYPE
        defaultPlanInfoDataAtchShouldBeFound("encryptedType.equals=" + DEFAULT_ENCRYPTED_TYPE);

        // Get all the planInfoDataAtchList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoDataAtchShouldNotBeFound("encryptedType.equals=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByEncryptedTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where encryptedType in DEFAULT_ENCRYPTED_TYPE or UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoDataAtchShouldBeFound("encryptedType.in=" + DEFAULT_ENCRYPTED_TYPE + "," + UPDATED_ENCRYPTED_TYPE);

        // Get all the planInfoDataAtchList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoDataAtchShouldNotBeFound("encryptedType.in=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByEncryptedTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where encryptedType is not null
        defaultPlanInfoDataAtchShouldBeFound("encryptedType.specified=true");

        // Get all the planInfoDataAtchList where encryptedType is null
        defaultPlanInfoDataAtchShouldNotBeFound("encryptedType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoDataAtchShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoDataAtchList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoDataAtchShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoDataAtchShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoDataAtchList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoDataAtchShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where jsonString is not null
        defaultPlanInfoDataAtchShouldBeFound("jsonString.specified=true");

        // Get all the planInfoDataAtchList where jsonString is null
        defaultPlanInfoDataAtchShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoDataAtchShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoDataAtchList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoDataAtchShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoDataAtchShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoDataAtchList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoDataAtchShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where remarks is not null
        defaultPlanInfoDataAtchShouldBeFound("remarks.specified=true");

        // Get all the planInfoDataAtchList where remarks is null
        defaultPlanInfoDataAtchShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoDataAtchShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoDataAtchList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataAtchShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataAtchShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoDataAtchList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataAtchShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where attachmentPath is not null
        defaultPlanInfoDataAtchShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoDataAtchList where attachmentPath is null
        defaultPlanInfoDataAtchShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoDataAtchShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoDataAtchList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataAtchShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataAtchShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoDataAtchList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataAtchShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where attachmentName is not null
        defaultPlanInfoDataAtchShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoDataAtchList where attachmentName is null
        defaultPlanInfoDataAtchShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoDataAtchShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoDataAtchList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataAtchShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataAtchShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoDataAtchList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataAtchShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where imageBlobName is not null
        defaultPlanInfoDataAtchShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoDataAtchList where imageBlobName is null
        defaultPlanInfoDataAtchShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoDataAtchShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoDataAtchList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoDataAtchShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoDataAtchShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoDataAtchList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoDataAtchShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where validType is not null
        defaultPlanInfoDataAtchShouldBeFound("validType.specified=true");

        // Get all the planInfoDataAtchList where validType is null
        defaultPlanInfoDataAtchShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoDataAtchShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoDataAtchList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoDataAtchShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoDataAtchShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoDataAtchList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoDataAtchShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where validBegin is not null
        defaultPlanInfoDataAtchShouldBeFound("validBegin.specified=true");

        // Get all the planInfoDataAtchList where validBegin is null
        defaultPlanInfoDataAtchShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoDataAtchShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoDataAtchList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoDataAtchShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoDataAtchShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoDataAtchList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoDataAtchShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where validEnd is not null
        defaultPlanInfoDataAtchShouldBeFound("validEnd.specified=true");

        // Get all the planInfoDataAtchList where validEnd is null
        defaultPlanInfoDataAtchShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoDataAtchShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoDataAtchList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoDataAtchShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoDataAtchShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoDataAtchList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoDataAtchShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where insertTime is not null
        defaultPlanInfoDataAtchShouldBeFound("insertTime.specified=true");

        // Get all the planInfoDataAtchList where insertTime is null
        defaultPlanInfoDataAtchShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoDataAtchShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoDataAtchList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoDataAtchShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoDataAtchShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoDataAtchList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoDataAtchShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        // Get all the planInfoDataAtchList where updateTime is not null
        defaultPlanInfoDataAtchShouldBeFound("updateTime.specified=true");

        // Get all the planInfoDataAtchList where updateTime is null
        defaultPlanInfoDataAtchShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfoDataAtch.setCreatedBy(createdBy);
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);
        Long createdById = createdBy.getId();

        // Get all the planInfoDataAtchList where createdBy equals to createdById
        defaultPlanInfoDataAtchShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoDataAtchList where createdBy equals to createdById + 1
        defaultPlanInfoDataAtchShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfoDataAtch.setModifiedBy(modifiedBy);
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoDataAtchList where modifiedBy equals to modifiedById
        defaultPlanInfoDataAtchShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoDataAtchList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoDataAtchShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByPlanInfoAtchIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoAtch planInfoAtch = PlanInfoAtchResourceIntTest.createEntity(em);
        em.persist(planInfoAtch);
        em.flush();
        planInfoDataAtch.setPlanInfoAtch(planInfoAtch);
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);
        Long planInfoAtchId = planInfoAtch.getId();

        // Get all the planInfoDataAtchList where planInfoAtch equals to planInfoAtchId
        defaultPlanInfoDataAtchShouldBeFound("planInfoAtchId.equals=" + planInfoAtchId);

        // Get all the planInfoDataAtchList where planInfoAtch equals to planInfoAtchId + 1
        defaultPlanInfoDataAtchShouldNotBeFound("planInfoAtchId.equals=" + (planInfoAtchId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataAtchesByPlanInfoDataIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoData planInfoData = PlanInfoDataResourceIntTest.createEntity(em);
        em.persist(planInfoData);
        em.flush();
        planInfoDataAtch.setPlanInfoData(planInfoData);
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);
        Long planInfoDataId = planInfoData.getId();

        // Get all the planInfoDataAtchList where planInfoData equals to planInfoDataId
        defaultPlanInfoDataAtchShouldBeFound("planInfoDataId.equals=" + planInfoDataId);

        // Get all the planInfoDataAtchList where planInfoData equals to planInfoDataId + 1
        defaultPlanInfoDataAtchShouldNotBeFound("planInfoDataId.equals=" + (planInfoDataId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoDataAtchShouldBeFound(String filter) throws Exception {
        restPlanInfoDataAtchMockMvc.perform(get("/api/plan-info-data-atches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoDataAtch.getId().intValue())))
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
        restPlanInfoDataAtchMockMvc.perform(get("/api/plan-info-data-atches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoDataAtchShouldNotBeFound(String filter) throws Exception {
        restPlanInfoDataAtchMockMvc.perform(get("/api/plan-info-data-atches?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoDataAtchMockMvc.perform(get("/api/plan-info-data-atches/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfoDataAtch() throws Exception {
        // Get the planInfoDataAtch
        restPlanInfoDataAtchMockMvc.perform(get("/api/plan-info-data-atches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfoDataAtch() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        int databaseSizeBeforeUpdate = planInfoDataAtchRepository.findAll().size();

        // Update the planInfoDataAtch
        PlanInfoDataAtch updatedPlanInfoDataAtch = planInfoDataAtchRepository.findById(planInfoDataAtch.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfoDataAtch are not directly saved in db
        em.detach(updatedPlanInfoDataAtch);
        updatedPlanInfoDataAtch
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
        PlanInfoDataAtchDTO planInfoDataAtchDTO = planInfoDataAtchMapper.toDto(updatedPlanInfoDataAtch);

        restPlanInfoDataAtchMockMvc.perform(put("/api/plan-info-data-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataAtchDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfoDataAtch in the database
        List<PlanInfoDataAtch> planInfoDataAtchList = planInfoDataAtchRepository.findAll();
        assertThat(planInfoDataAtchList).hasSize(databaseSizeBeforeUpdate);
        PlanInfoDataAtch testPlanInfoDataAtch = planInfoDataAtchList.get(planInfoDataAtchList.size() - 1);
        assertThat(testPlanInfoDataAtch.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfoDataAtch.getStoreDir()).isEqualTo(UPDATED_STORE_DIR);
        assertThat(testPlanInfoDataAtch.getStoreName()).isEqualTo(UPDATED_STORE_NAME);
        assertThat(testPlanInfoDataAtch.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfoDataAtch.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testPlanInfoDataAtch.isDeleteFlag()).isEqualTo(UPDATED_DELETE_FLAG);
        assertThat(testPlanInfoDataAtch.getStoreType()).isEqualTo(UPDATED_STORE_TYPE);
        assertThat(testPlanInfoDataAtch.getVer()).isEqualTo(UPDATED_VER);
        assertThat(testPlanInfoDataAtch.getEncryptedFlag()).isEqualTo(UPDATED_ENCRYPTED_FLAG);
        assertThat(testPlanInfoDataAtch.getEncryptedType()).isEqualTo(UPDATED_ENCRYPTED_TYPE);
        assertThat(testPlanInfoDataAtch.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfoDataAtch.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfoDataAtch.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfoDataAtch.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfoDataAtch.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataAtch.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfoDataAtch.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfoDataAtch.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfoDataAtch.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataAtch.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoDataAtch.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfoDataAtch.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfoDataAtch.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfoDataAtch.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfoDataAtch.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);

        // Validate the PlanInfoDataAtch in Elasticsearch
        verify(mockPlanInfoDataAtchSearchRepository, times(1)).save(testPlanInfoDataAtch);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfoDataAtch() throws Exception {
        int databaseSizeBeforeUpdate = planInfoDataAtchRepository.findAll().size();

        // Create the PlanInfoDataAtch
        PlanInfoDataAtchDTO planInfoDataAtchDTO = planInfoDataAtchMapper.toDto(planInfoDataAtch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoDataAtchMockMvc.perform(put("/api/plan-info-data-atches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataAtchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoDataAtch in the database
        List<PlanInfoDataAtch> planInfoDataAtchList = planInfoDataAtchRepository.findAll();
        assertThat(planInfoDataAtchList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfoDataAtch in Elasticsearch
        verify(mockPlanInfoDataAtchSearchRepository, times(0)).save(planInfoDataAtch);
    }

    @Test
    @Transactional
    public void deletePlanInfoDataAtch() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);

        int databaseSizeBeforeDelete = planInfoDataAtchRepository.findAll().size();

        // Get the planInfoDataAtch
        restPlanInfoDataAtchMockMvc.perform(delete("/api/plan-info-data-atches/{id}", planInfoDataAtch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfoDataAtch> planInfoDataAtchList = planInfoDataAtchRepository.findAll();
        assertThat(planInfoDataAtchList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfoDataAtch in Elasticsearch
        verify(mockPlanInfoDataAtchSearchRepository, times(1)).deleteById(planInfoDataAtch.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfoDataAtch() throws Exception {
        // Initialize the database
        planInfoDataAtchRepository.saveAndFlush(planInfoDataAtch);
        when(mockPlanInfoDataAtchSearchRepository.search(queryStringQuery("id:" + planInfoDataAtch.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfoDataAtch), PageRequest.of(0, 1), 1));
        // Search the planInfoDataAtch
        restPlanInfoDataAtchMockMvc.perform(get("/api/_search/plan-info-data-atches?query=id:" + planInfoDataAtch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoDataAtch.getId().intValue())))
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
        TestUtil.equalsVerifier(PlanInfoDataAtch.class);
        PlanInfoDataAtch planInfoDataAtch1 = new PlanInfoDataAtch();
        planInfoDataAtch1.setId(1L);
        PlanInfoDataAtch planInfoDataAtch2 = new PlanInfoDataAtch();
        planInfoDataAtch2.setId(planInfoDataAtch1.getId());
        assertThat(planInfoDataAtch1).isEqualTo(planInfoDataAtch2);
        planInfoDataAtch2.setId(2L);
        assertThat(planInfoDataAtch1).isNotEqualTo(planInfoDataAtch2);
        planInfoDataAtch1.setId(null);
        assertThat(planInfoDataAtch1).isNotEqualTo(planInfoDataAtch2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoDataAtchDTO.class);
        PlanInfoDataAtchDTO planInfoDataAtchDTO1 = new PlanInfoDataAtchDTO();
        planInfoDataAtchDTO1.setId(1L);
        PlanInfoDataAtchDTO planInfoDataAtchDTO2 = new PlanInfoDataAtchDTO();
        assertThat(planInfoDataAtchDTO1).isNotEqualTo(planInfoDataAtchDTO2);
        planInfoDataAtchDTO2.setId(planInfoDataAtchDTO1.getId());
        assertThat(planInfoDataAtchDTO1).isEqualTo(planInfoDataAtchDTO2);
        planInfoDataAtchDTO2.setId(2L);
        assertThat(planInfoDataAtchDTO1).isNotEqualTo(planInfoDataAtchDTO2);
        planInfoDataAtchDTO1.setId(null);
        assertThat(planInfoDataAtchDTO1).isNotEqualTo(planInfoDataAtchDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoDataAtchMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoDataAtchMapper.fromId(null)).isNull();
    }
}
