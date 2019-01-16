package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfoDataAtchHis;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.PlanInfoAtch;
import com.aerothinker.plandb.domain.PlanInfoDataHis;
import com.aerothinker.plandb.repository.PlanInfoDataAtchHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataAtchHisSearchRepository;
import com.aerothinker.plandb.service.PlanInfoDataAtchHisService;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataAtchHisMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoDataAtchHisCriteria;
import com.aerothinker.plandb.service.PlanInfoDataAtchHisQueryService;

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
 * Test class for the PlanInfoDataAtchHisResource REST controller.
 *
 * @see PlanInfoDataAtchHisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoDataAtchHisResourceIntTest {

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
    private PlanInfoDataAtchHisRepository planInfoDataAtchHisRepository;

    @Autowired
    private PlanInfoDataAtchHisMapper planInfoDataAtchHisMapper;

    @Autowired
    private PlanInfoDataAtchHisService planInfoDataAtchHisService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoDataAtchHisSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoDataAtchHisSearchRepository mockPlanInfoDataAtchHisSearchRepository;

    @Autowired
    private PlanInfoDataAtchHisQueryService planInfoDataAtchHisQueryService;

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

    private MockMvc restPlanInfoDataAtchHisMockMvc;

    private PlanInfoDataAtchHis planInfoDataAtchHis;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoDataAtchHisResource planInfoDataAtchHisResource = new PlanInfoDataAtchHisResource(planInfoDataAtchHisService, planInfoDataAtchHisQueryService);
        this.restPlanInfoDataAtchHisMockMvc = MockMvcBuilders.standaloneSetup(planInfoDataAtchHisResource)
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
    public static PlanInfoDataAtchHis createEntity(EntityManager em) {
        PlanInfoDataAtchHis planInfoDataAtchHis = new PlanInfoDataAtchHis()
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
        return planInfoDataAtchHis;
    }

    @Before
    public void initTest() {
        planInfoDataAtchHis = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfoDataAtchHis() throws Exception {
        int databaseSizeBeforeCreate = planInfoDataAtchHisRepository.findAll().size();

        // Create the PlanInfoDataAtchHis
        PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO = planInfoDataAtchHisMapper.toDto(planInfoDataAtchHis);
        restPlanInfoDataAtchHisMockMvc.perform(post("/api/plan-info-data-atch-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataAtchHisDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfoDataAtchHis in the database
        List<PlanInfoDataAtchHis> planInfoDataAtchHisList = planInfoDataAtchHisRepository.findAll();
        assertThat(planInfoDataAtchHisList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfoDataAtchHis testPlanInfoDataAtchHis = planInfoDataAtchHisList.get(planInfoDataAtchHisList.size() - 1);
        assertThat(testPlanInfoDataAtchHis.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfoDataAtchHis.getStoreDir()).isEqualTo(DEFAULT_STORE_DIR);
        assertThat(testPlanInfoDataAtchHis.getStoreName()).isEqualTo(DEFAULT_STORE_NAME);
        assertThat(testPlanInfoDataAtchHis.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfoDataAtchHis.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testPlanInfoDataAtchHis.isDeleteFlag()).isEqualTo(DEFAULT_DELETE_FLAG);
        assertThat(testPlanInfoDataAtchHis.getStoreType()).isEqualTo(DEFAULT_STORE_TYPE);
        assertThat(testPlanInfoDataAtchHis.getVer()).isEqualTo(DEFAULT_VER);
        assertThat(testPlanInfoDataAtchHis.getEncryptedFlag()).isEqualTo(DEFAULT_ENCRYPTED_FLAG);
        assertThat(testPlanInfoDataAtchHis.getEncryptedType()).isEqualTo(DEFAULT_ENCRYPTED_TYPE);
        assertThat(testPlanInfoDataAtchHis.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfoDataAtchHis.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfoDataAtchHis.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfoDataAtchHis.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfoDataAtchHis.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataAtchHis.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfoDataAtchHis.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfoDataAtchHis.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfoDataAtchHis.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataAtchHis.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoDataAtchHis.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfoDataAtchHis.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfoDataAtchHis.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfoDataAtchHis.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfoDataAtchHis.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);

        // Validate the PlanInfoDataAtchHis in Elasticsearch
        verify(mockPlanInfoDataAtchHisSearchRepository, times(1)).save(testPlanInfoDataAtchHis);
    }

    @Test
    @Transactional
    public void createPlanInfoDataAtchHisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoDataAtchHisRepository.findAll().size();

        // Create the PlanInfoDataAtchHis with an existing ID
        planInfoDataAtchHis.setId(1L);
        PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO = planInfoDataAtchHisMapper.toDto(planInfoDataAtchHis);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoDataAtchHisMockMvc.perform(post("/api/plan-info-data-atch-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataAtchHisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoDataAtchHis in the database
        List<PlanInfoDataAtchHis> planInfoDataAtchHisList = planInfoDataAtchHisRepository.findAll();
        assertThat(planInfoDataAtchHisList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfoDataAtchHis in Elasticsearch
        verify(mockPlanInfoDataAtchHisSearchRepository, times(0)).save(planInfoDataAtchHis);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoDataAtchHisRepository.findAll().size();
        // set the field null
        planInfoDataAtchHis.setName(null);

        // Create the PlanInfoDataAtchHis, which fails.
        PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO = planInfoDataAtchHisMapper.toDto(planInfoDataAtchHis);

        restPlanInfoDataAtchHisMockMvc.perform(post("/api/plan-info-data-atch-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataAtchHisDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfoDataAtchHis> planInfoDataAtchHisList = planInfoDataAtchHisRepository.findAll();
        assertThat(planInfoDataAtchHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHis() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList
        restPlanInfoDataAtchHisMockMvc.perform(get("/api/plan-info-data-atch-his?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoDataAtchHis.getId().intValue())))
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
    public void getPlanInfoDataAtchHis() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get the planInfoDataAtchHis
        restPlanInfoDataAtchHisMockMvc.perform(get("/api/plan-info-data-atch-his/{id}", planInfoDataAtchHis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfoDataAtchHis.getId().intValue()))
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
    public void getAllPlanInfoDataAtchHisByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where name equals to DEFAULT_NAME
        defaultPlanInfoDataAtchHisShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoDataAtchHisList where name equals to UPDATED_NAME
        defaultPlanInfoDataAtchHisShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoDataAtchHisShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoDataAtchHisList where name equals to UPDATED_NAME
        defaultPlanInfoDataAtchHisShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where name is not null
        defaultPlanInfoDataAtchHisShouldBeFound("name.specified=true");

        // Get all the planInfoDataAtchHisList where name is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByStoreDirIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where storeDir equals to DEFAULT_STORE_DIR
        defaultPlanInfoDataAtchHisShouldBeFound("storeDir.equals=" + DEFAULT_STORE_DIR);

        // Get all the planInfoDataAtchHisList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoDataAtchHisShouldNotBeFound("storeDir.equals=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByStoreDirIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where storeDir in DEFAULT_STORE_DIR or UPDATED_STORE_DIR
        defaultPlanInfoDataAtchHisShouldBeFound("storeDir.in=" + DEFAULT_STORE_DIR + "," + UPDATED_STORE_DIR);

        // Get all the planInfoDataAtchHisList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoDataAtchHisShouldNotBeFound("storeDir.in=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByStoreDirIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where storeDir is not null
        defaultPlanInfoDataAtchHisShouldBeFound("storeDir.specified=true");

        // Get all the planInfoDataAtchHisList where storeDir is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("storeDir.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByStoreNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where storeName equals to DEFAULT_STORE_NAME
        defaultPlanInfoDataAtchHisShouldBeFound("storeName.equals=" + DEFAULT_STORE_NAME);

        // Get all the planInfoDataAtchHisList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoDataAtchHisShouldNotBeFound("storeName.equals=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByStoreNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where storeName in DEFAULT_STORE_NAME or UPDATED_STORE_NAME
        defaultPlanInfoDataAtchHisShouldBeFound("storeName.in=" + DEFAULT_STORE_NAME + "," + UPDATED_STORE_NAME);

        // Get all the planInfoDataAtchHisList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoDataAtchHisShouldNotBeFound("storeName.in=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByStoreNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where storeName is not null
        defaultPlanInfoDataAtchHisShouldBeFound("storeName.specified=true");

        // Get all the planInfoDataAtchHisList where storeName is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("storeName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoDataAtchHisShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoDataAtchHisList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoDataAtchHisShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoDataAtchHisShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoDataAtchHisList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoDataAtchHisShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where sortString is not null
        defaultPlanInfoDataAtchHisShouldBeFound("sortString.specified=true");

        // Get all the planInfoDataAtchHisList where sortString is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByFileTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where fileType equals to DEFAULT_FILE_TYPE
        defaultPlanInfoDataAtchHisShouldBeFound("fileType.equals=" + DEFAULT_FILE_TYPE);

        // Get all the planInfoDataAtchHisList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoDataAtchHisShouldNotBeFound("fileType.equals=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByFileTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where fileType in DEFAULT_FILE_TYPE or UPDATED_FILE_TYPE
        defaultPlanInfoDataAtchHisShouldBeFound("fileType.in=" + DEFAULT_FILE_TYPE + "," + UPDATED_FILE_TYPE);

        // Get all the planInfoDataAtchHisList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoDataAtchHisShouldNotBeFound("fileType.in=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByFileTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where fileType is not null
        defaultPlanInfoDataAtchHisShouldBeFound("fileType.specified=true");

        // Get all the planInfoDataAtchHisList where fileType is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("fileType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByDeleteFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where deleteFlag equals to DEFAULT_DELETE_FLAG
        defaultPlanInfoDataAtchHisShouldBeFound("deleteFlag.equals=" + DEFAULT_DELETE_FLAG);

        // Get all the planInfoDataAtchHisList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoDataAtchHisShouldNotBeFound("deleteFlag.equals=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByDeleteFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where deleteFlag in DEFAULT_DELETE_FLAG or UPDATED_DELETE_FLAG
        defaultPlanInfoDataAtchHisShouldBeFound("deleteFlag.in=" + DEFAULT_DELETE_FLAG + "," + UPDATED_DELETE_FLAG);

        // Get all the planInfoDataAtchHisList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoDataAtchHisShouldNotBeFound("deleteFlag.in=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByDeleteFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where deleteFlag is not null
        defaultPlanInfoDataAtchHisShouldBeFound("deleteFlag.specified=true");

        // Get all the planInfoDataAtchHisList where deleteFlag is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("deleteFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByStoreTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where storeType equals to DEFAULT_STORE_TYPE
        defaultPlanInfoDataAtchHisShouldBeFound("storeType.equals=" + DEFAULT_STORE_TYPE);

        // Get all the planInfoDataAtchHisList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoDataAtchHisShouldNotBeFound("storeType.equals=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByStoreTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where storeType in DEFAULT_STORE_TYPE or UPDATED_STORE_TYPE
        defaultPlanInfoDataAtchHisShouldBeFound("storeType.in=" + DEFAULT_STORE_TYPE + "," + UPDATED_STORE_TYPE);

        // Get all the planInfoDataAtchHisList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoDataAtchHisShouldNotBeFound("storeType.in=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByStoreTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where storeType is not null
        defaultPlanInfoDataAtchHisShouldBeFound("storeType.specified=true");

        // Get all the planInfoDataAtchHisList where storeType is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("storeType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByVerIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where ver equals to DEFAULT_VER
        defaultPlanInfoDataAtchHisShouldBeFound("ver.equals=" + DEFAULT_VER);

        // Get all the planInfoDataAtchHisList where ver equals to UPDATED_VER
        defaultPlanInfoDataAtchHisShouldNotBeFound("ver.equals=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByVerIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where ver in DEFAULT_VER or UPDATED_VER
        defaultPlanInfoDataAtchHisShouldBeFound("ver.in=" + DEFAULT_VER + "," + UPDATED_VER);

        // Get all the planInfoDataAtchHisList where ver equals to UPDATED_VER
        defaultPlanInfoDataAtchHisShouldNotBeFound("ver.in=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByVerIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where ver is not null
        defaultPlanInfoDataAtchHisShouldBeFound("ver.specified=true");

        // Get all the planInfoDataAtchHisList where ver is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("ver.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByEncryptedFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where encryptedFlag equals to DEFAULT_ENCRYPTED_FLAG
        defaultPlanInfoDataAtchHisShouldBeFound("encryptedFlag.equals=" + DEFAULT_ENCRYPTED_FLAG);

        // Get all the planInfoDataAtchHisList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoDataAtchHisShouldNotBeFound("encryptedFlag.equals=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByEncryptedFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where encryptedFlag in DEFAULT_ENCRYPTED_FLAG or UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoDataAtchHisShouldBeFound("encryptedFlag.in=" + DEFAULT_ENCRYPTED_FLAG + "," + UPDATED_ENCRYPTED_FLAG);

        // Get all the planInfoDataAtchHisList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoDataAtchHisShouldNotBeFound("encryptedFlag.in=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByEncryptedFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where encryptedFlag is not null
        defaultPlanInfoDataAtchHisShouldBeFound("encryptedFlag.specified=true");

        // Get all the planInfoDataAtchHisList where encryptedFlag is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("encryptedFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByEncryptedTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where encryptedType equals to DEFAULT_ENCRYPTED_TYPE
        defaultPlanInfoDataAtchHisShouldBeFound("encryptedType.equals=" + DEFAULT_ENCRYPTED_TYPE);

        // Get all the planInfoDataAtchHisList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoDataAtchHisShouldNotBeFound("encryptedType.equals=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByEncryptedTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where encryptedType in DEFAULT_ENCRYPTED_TYPE or UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoDataAtchHisShouldBeFound("encryptedType.in=" + DEFAULT_ENCRYPTED_TYPE + "," + UPDATED_ENCRYPTED_TYPE);

        // Get all the planInfoDataAtchHisList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoDataAtchHisShouldNotBeFound("encryptedType.in=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByEncryptedTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where encryptedType is not null
        defaultPlanInfoDataAtchHisShouldBeFound("encryptedType.specified=true");

        // Get all the planInfoDataAtchHisList where encryptedType is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("encryptedType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoDataAtchHisShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoDataAtchHisList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoDataAtchHisShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoDataAtchHisShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoDataAtchHisList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoDataAtchHisShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where jsonString is not null
        defaultPlanInfoDataAtchHisShouldBeFound("jsonString.specified=true");

        // Get all the planInfoDataAtchHisList where jsonString is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoDataAtchHisShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoDataAtchHisList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoDataAtchHisShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoDataAtchHisShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoDataAtchHisList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoDataAtchHisShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where remarks is not null
        defaultPlanInfoDataAtchHisShouldBeFound("remarks.specified=true");

        // Get all the planInfoDataAtchHisList where remarks is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoDataAtchHisShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoDataAtchHisList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataAtchHisShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataAtchHisShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoDataAtchHisList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataAtchHisShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where attachmentPath is not null
        defaultPlanInfoDataAtchHisShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoDataAtchHisList where attachmentPath is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoDataAtchHisShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoDataAtchHisList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataAtchHisShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataAtchHisShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoDataAtchHisList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataAtchHisShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where attachmentName is not null
        defaultPlanInfoDataAtchHisShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoDataAtchHisList where attachmentName is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoDataAtchHisShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoDataAtchHisList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataAtchHisShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataAtchHisShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoDataAtchHisList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataAtchHisShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where imageBlobName is not null
        defaultPlanInfoDataAtchHisShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoDataAtchHisList where imageBlobName is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoDataAtchHisShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoDataAtchHisList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoDataAtchHisShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoDataAtchHisShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoDataAtchHisList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoDataAtchHisShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where validType is not null
        defaultPlanInfoDataAtchHisShouldBeFound("validType.specified=true");

        // Get all the planInfoDataAtchHisList where validType is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoDataAtchHisShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoDataAtchHisList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoDataAtchHisShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoDataAtchHisShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoDataAtchHisList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoDataAtchHisShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where validBegin is not null
        defaultPlanInfoDataAtchHisShouldBeFound("validBegin.specified=true");

        // Get all the planInfoDataAtchHisList where validBegin is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoDataAtchHisShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoDataAtchHisList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoDataAtchHisShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoDataAtchHisShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoDataAtchHisList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoDataAtchHisShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where validEnd is not null
        defaultPlanInfoDataAtchHisShouldBeFound("validEnd.specified=true");

        // Get all the planInfoDataAtchHisList where validEnd is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoDataAtchHisShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoDataAtchHisList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoDataAtchHisShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoDataAtchHisShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoDataAtchHisList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoDataAtchHisShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where insertTime is not null
        defaultPlanInfoDataAtchHisShouldBeFound("insertTime.specified=true");

        // Get all the planInfoDataAtchHisList where insertTime is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoDataAtchHisShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoDataAtchHisList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoDataAtchHisShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoDataAtchHisShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoDataAtchHisList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoDataAtchHisShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        // Get all the planInfoDataAtchHisList where updateTime is not null
        defaultPlanInfoDataAtchHisShouldBeFound("updateTime.specified=true");

        // Get all the planInfoDataAtchHisList where updateTime is null
        defaultPlanInfoDataAtchHisShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfoDataAtchHis.setCreatedBy(createdBy);
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);
        Long createdById = createdBy.getId();

        // Get all the planInfoDataAtchHisList where createdBy equals to createdById
        defaultPlanInfoDataAtchHisShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoDataAtchHisList where createdBy equals to createdById + 1
        defaultPlanInfoDataAtchHisShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfoDataAtchHis.setModifiedBy(modifiedBy);
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoDataAtchHisList where modifiedBy equals to modifiedById
        defaultPlanInfoDataAtchHisShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoDataAtchHisList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoDataAtchHisShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByPlanInfoAtchIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoAtch planInfoAtch = PlanInfoAtchResourceIntTest.createEntity(em);
        em.persist(planInfoAtch);
        em.flush();
        planInfoDataAtchHis.setPlanInfoAtch(planInfoAtch);
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);
        Long planInfoAtchId = planInfoAtch.getId();

        // Get all the planInfoDataAtchHisList where planInfoAtch equals to planInfoAtchId
        defaultPlanInfoDataAtchHisShouldBeFound("planInfoAtchId.equals=" + planInfoAtchId);

        // Get all the planInfoDataAtchHisList where planInfoAtch equals to planInfoAtchId + 1
        defaultPlanInfoDataAtchHisShouldNotBeFound("planInfoAtchId.equals=" + (planInfoAtchId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataAtchHisByPlanInfoDataHisIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoDataHis planInfoDataHis = PlanInfoDataHisResourceIntTest.createEntity(em);
        em.persist(planInfoDataHis);
        em.flush();
        planInfoDataAtchHis.setPlanInfoDataHis(planInfoDataHis);
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);
        Long planInfoDataHisId = planInfoDataHis.getId();

        // Get all the planInfoDataAtchHisList where planInfoDataHis equals to planInfoDataHisId
        defaultPlanInfoDataAtchHisShouldBeFound("planInfoDataHisId.equals=" + planInfoDataHisId);

        // Get all the planInfoDataAtchHisList where planInfoDataHis equals to planInfoDataHisId + 1
        defaultPlanInfoDataAtchHisShouldNotBeFound("planInfoDataHisId.equals=" + (planInfoDataHisId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoDataAtchHisShouldBeFound(String filter) throws Exception {
        restPlanInfoDataAtchHisMockMvc.perform(get("/api/plan-info-data-atch-his?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoDataAtchHis.getId().intValue())))
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
        restPlanInfoDataAtchHisMockMvc.perform(get("/api/plan-info-data-atch-his/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoDataAtchHisShouldNotBeFound(String filter) throws Exception {
        restPlanInfoDataAtchHisMockMvc.perform(get("/api/plan-info-data-atch-his?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoDataAtchHisMockMvc.perform(get("/api/plan-info-data-atch-his/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfoDataAtchHis() throws Exception {
        // Get the planInfoDataAtchHis
        restPlanInfoDataAtchHisMockMvc.perform(get("/api/plan-info-data-atch-his/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfoDataAtchHis() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        int databaseSizeBeforeUpdate = planInfoDataAtchHisRepository.findAll().size();

        // Update the planInfoDataAtchHis
        PlanInfoDataAtchHis updatedPlanInfoDataAtchHis = planInfoDataAtchHisRepository.findById(planInfoDataAtchHis.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfoDataAtchHis are not directly saved in db
        em.detach(updatedPlanInfoDataAtchHis);
        updatedPlanInfoDataAtchHis
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
        PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO = planInfoDataAtchHisMapper.toDto(updatedPlanInfoDataAtchHis);

        restPlanInfoDataAtchHisMockMvc.perform(put("/api/plan-info-data-atch-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataAtchHisDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfoDataAtchHis in the database
        List<PlanInfoDataAtchHis> planInfoDataAtchHisList = planInfoDataAtchHisRepository.findAll();
        assertThat(planInfoDataAtchHisList).hasSize(databaseSizeBeforeUpdate);
        PlanInfoDataAtchHis testPlanInfoDataAtchHis = planInfoDataAtchHisList.get(planInfoDataAtchHisList.size() - 1);
        assertThat(testPlanInfoDataAtchHis.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfoDataAtchHis.getStoreDir()).isEqualTo(UPDATED_STORE_DIR);
        assertThat(testPlanInfoDataAtchHis.getStoreName()).isEqualTo(UPDATED_STORE_NAME);
        assertThat(testPlanInfoDataAtchHis.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfoDataAtchHis.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testPlanInfoDataAtchHis.isDeleteFlag()).isEqualTo(UPDATED_DELETE_FLAG);
        assertThat(testPlanInfoDataAtchHis.getStoreType()).isEqualTo(UPDATED_STORE_TYPE);
        assertThat(testPlanInfoDataAtchHis.getVer()).isEqualTo(UPDATED_VER);
        assertThat(testPlanInfoDataAtchHis.getEncryptedFlag()).isEqualTo(UPDATED_ENCRYPTED_FLAG);
        assertThat(testPlanInfoDataAtchHis.getEncryptedType()).isEqualTo(UPDATED_ENCRYPTED_TYPE);
        assertThat(testPlanInfoDataAtchHis.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfoDataAtchHis.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfoDataAtchHis.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfoDataAtchHis.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfoDataAtchHis.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataAtchHis.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfoDataAtchHis.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfoDataAtchHis.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfoDataAtchHis.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataAtchHis.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoDataAtchHis.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfoDataAtchHis.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfoDataAtchHis.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfoDataAtchHis.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfoDataAtchHis.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);

        // Validate the PlanInfoDataAtchHis in Elasticsearch
        verify(mockPlanInfoDataAtchHisSearchRepository, times(1)).save(testPlanInfoDataAtchHis);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfoDataAtchHis() throws Exception {
        int databaseSizeBeforeUpdate = planInfoDataAtchHisRepository.findAll().size();

        // Create the PlanInfoDataAtchHis
        PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO = planInfoDataAtchHisMapper.toDto(planInfoDataAtchHis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoDataAtchHisMockMvc.perform(put("/api/plan-info-data-atch-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataAtchHisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoDataAtchHis in the database
        List<PlanInfoDataAtchHis> planInfoDataAtchHisList = planInfoDataAtchHisRepository.findAll();
        assertThat(planInfoDataAtchHisList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfoDataAtchHis in Elasticsearch
        verify(mockPlanInfoDataAtchHisSearchRepository, times(0)).save(planInfoDataAtchHis);
    }

    @Test
    @Transactional
    public void deletePlanInfoDataAtchHis() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);

        int databaseSizeBeforeDelete = planInfoDataAtchHisRepository.findAll().size();

        // Get the planInfoDataAtchHis
        restPlanInfoDataAtchHisMockMvc.perform(delete("/api/plan-info-data-atch-his/{id}", planInfoDataAtchHis.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfoDataAtchHis> planInfoDataAtchHisList = planInfoDataAtchHisRepository.findAll();
        assertThat(planInfoDataAtchHisList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfoDataAtchHis in Elasticsearch
        verify(mockPlanInfoDataAtchHisSearchRepository, times(1)).deleteById(planInfoDataAtchHis.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfoDataAtchHis() throws Exception {
        // Initialize the database
        planInfoDataAtchHisRepository.saveAndFlush(planInfoDataAtchHis);
        when(mockPlanInfoDataAtchHisSearchRepository.search(queryStringQuery("id:" + planInfoDataAtchHis.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfoDataAtchHis), PageRequest.of(0, 1), 1));
        // Search the planInfoDataAtchHis
        restPlanInfoDataAtchHisMockMvc.perform(get("/api/_search/plan-info-data-atch-his?query=id:" + planInfoDataAtchHis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoDataAtchHis.getId().intValue())))
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
        TestUtil.equalsVerifier(PlanInfoDataAtchHis.class);
        PlanInfoDataAtchHis planInfoDataAtchHis1 = new PlanInfoDataAtchHis();
        planInfoDataAtchHis1.setId(1L);
        PlanInfoDataAtchHis planInfoDataAtchHis2 = new PlanInfoDataAtchHis();
        planInfoDataAtchHis2.setId(planInfoDataAtchHis1.getId());
        assertThat(planInfoDataAtchHis1).isEqualTo(planInfoDataAtchHis2);
        planInfoDataAtchHis2.setId(2L);
        assertThat(planInfoDataAtchHis1).isNotEqualTo(planInfoDataAtchHis2);
        planInfoDataAtchHis1.setId(null);
        assertThat(planInfoDataAtchHis1).isNotEqualTo(planInfoDataAtchHis2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoDataAtchHisDTO.class);
        PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO1 = new PlanInfoDataAtchHisDTO();
        planInfoDataAtchHisDTO1.setId(1L);
        PlanInfoDataAtchHisDTO planInfoDataAtchHisDTO2 = new PlanInfoDataAtchHisDTO();
        assertThat(planInfoDataAtchHisDTO1).isNotEqualTo(planInfoDataAtchHisDTO2);
        planInfoDataAtchHisDTO2.setId(planInfoDataAtchHisDTO1.getId());
        assertThat(planInfoDataAtchHisDTO1).isEqualTo(planInfoDataAtchHisDTO2);
        planInfoDataAtchHisDTO2.setId(2L);
        assertThat(planInfoDataAtchHisDTO1).isNotEqualTo(planInfoDataAtchHisDTO2);
        planInfoDataAtchHisDTO1.setId(null);
        assertThat(planInfoDataAtchHisDTO1).isNotEqualTo(planInfoDataAtchHisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoDataAtchHisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoDataAtchHisMapper.fromId(null)).isNull();
    }
}
