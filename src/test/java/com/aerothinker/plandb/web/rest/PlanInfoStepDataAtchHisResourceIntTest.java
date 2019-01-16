package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfoStepDataAtchHis;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.PlanInfoStepAtch;
import com.aerothinker.plandb.domain.PlanInfoStepDataHis;
import com.aerothinker.plandb.repository.PlanInfoStepDataAtchHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataAtchHisSearchRepository;
import com.aerothinker.plandb.service.PlanInfoStepDataAtchHisService;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataAtchHisMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataAtchHisCriteria;
import com.aerothinker.plandb.service.PlanInfoStepDataAtchHisQueryService;

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
 * Test class for the PlanInfoStepDataAtchHisResource REST controller.
 *
 * @see PlanInfoStepDataAtchHisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoStepDataAtchHisResourceIntTest {

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
    private PlanInfoStepDataAtchHisRepository planInfoStepDataAtchHisRepository;

    @Autowired
    private PlanInfoStepDataAtchHisMapper planInfoStepDataAtchHisMapper;

    @Autowired
    private PlanInfoStepDataAtchHisService planInfoStepDataAtchHisService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoStepDataAtchHisSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoStepDataAtchHisSearchRepository mockPlanInfoStepDataAtchHisSearchRepository;

    @Autowired
    private PlanInfoStepDataAtchHisQueryService planInfoStepDataAtchHisQueryService;

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

    private MockMvc restPlanInfoStepDataAtchHisMockMvc;

    private PlanInfoStepDataAtchHis planInfoStepDataAtchHis;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoStepDataAtchHisResource planInfoStepDataAtchHisResource = new PlanInfoStepDataAtchHisResource(planInfoStepDataAtchHisService, planInfoStepDataAtchHisQueryService);
        this.restPlanInfoStepDataAtchHisMockMvc = MockMvcBuilders.standaloneSetup(planInfoStepDataAtchHisResource)
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
    public static PlanInfoStepDataAtchHis createEntity(EntityManager em) {
        PlanInfoStepDataAtchHis planInfoStepDataAtchHis = new PlanInfoStepDataAtchHis()
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
        return planInfoStepDataAtchHis;
    }

    @Before
    public void initTest() {
        planInfoStepDataAtchHis = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfoStepDataAtchHis() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepDataAtchHisRepository.findAll().size();

        // Create the PlanInfoStepDataAtchHis
        PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO = planInfoStepDataAtchHisMapper.toDto(planInfoStepDataAtchHis);
        restPlanInfoStepDataAtchHisMockMvc.perform(post("/api/plan-info-step-data-atch-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataAtchHisDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfoStepDataAtchHis in the database
        List<PlanInfoStepDataAtchHis> planInfoStepDataAtchHisList = planInfoStepDataAtchHisRepository.findAll();
        assertThat(planInfoStepDataAtchHisList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfoStepDataAtchHis testPlanInfoStepDataAtchHis = planInfoStepDataAtchHisList.get(planInfoStepDataAtchHisList.size() - 1);
        assertThat(testPlanInfoStepDataAtchHis.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfoStepDataAtchHis.getStoreDir()).isEqualTo(DEFAULT_STORE_DIR);
        assertThat(testPlanInfoStepDataAtchHis.getStoreName()).isEqualTo(DEFAULT_STORE_NAME);
        assertThat(testPlanInfoStepDataAtchHis.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfoStepDataAtchHis.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.isDeleteFlag()).isEqualTo(DEFAULT_DELETE_FLAG);
        assertThat(testPlanInfoStepDataAtchHis.getStoreType()).isEqualTo(DEFAULT_STORE_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.getVer()).isEqualTo(DEFAULT_VER);
        assertThat(testPlanInfoStepDataAtchHis.getEncryptedFlag()).isEqualTo(DEFAULT_ENCRYPTED_FLAG);
        assertThat(testPlanInfoStepDataAtchHis.getEncryptedType()).isEqualTo(DEFAULT_ENCRYPTED_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfoStepDataAtchHis.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfoStepDataAtchHis.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfoStepDataAtchHis.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStepDataAtchHis.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfoStepDataAtchHis.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfoStepDataAtchHis.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfoStepDataAtchHis.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStepDataAtchHis.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfoStepDataAtchHis.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfoStepDataAtchHis.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfoStepDataAtchHis.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);

        // Validate the PlanInfoStepDataAtchHis in Elasticsearch
        verify(mockPlanInfoStepDataAtchHisSearchRepository, times(1)).save(testPlanInfoStepDataAtchHis);
    }

    @Test
    @Transactional
    public void createPlanInfoStepDataAtchHisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepDataAtchHisRepository.findAll().size();

        // Create the PlanInfoStepDataAtchHis with an existing ID
        planInfoStepDataAtchHis.setId(1L);
        PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO = planInfoStepDataAtchHisMapper.toDto(planInfoStepDataAtchHis);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoStepDataAtchHisMockMvc.perform(post("/api/plan-info-step-data-atch-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataAtchHisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStepDataAtchHis in the database
        List<PlanInfoStepDataAtchHis> planInfoStepDataAtchHisList = planInfoStepDataAtchHisRepository.findAll();
        assertThat(planInfoStepDataAtchHisList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfoStepDataAtchHis in Elasticsearch
        verify(mockPlanInfoStepDataAtchHisSearchRepository, times(0)).save(planInfoStepDataAtchHis);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoStepDataAtchHisRepository.findAll().size();
        // set the field null
        planInfoStepDataAtchHis.setName(null);

        // Create the PlanInfoStepDataAtchHis, which fails.
        PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO = planInfoStepDataAtchHisMapper.toDto(planInfoStepDataAtchHis);

        restPlanInfoStepDataAtchHisMockMvc.perform(post("/api/plan-info-step-data-atch-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataAtchHisDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfoStepDataAtchHis> planInfoStepDataAtchHisList = planInfoStepDataAtchHisRepository.findAll();
        assertThat(planInfoStepDataAtchHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHis() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList
        restPlanInfoStepDataAtchHisMockMvc.perform(get("/api/plan-info-step-data-atch-his?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepDataAtchHis.getId().intValue())))
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
    public void getPlanInfoStepDataAtchHis() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get the planInfoStepDataAtchHis
        restPlanInfoStepDataAtchHisMockMvc.perform(get("/api/plan-info-step-data-atch-his/{id}", planInfoStepDataAtchHis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfoStepDataAtchHis.getId().intValue()))
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
    public void getAllPlanInfoStepDataAtchHisByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where name equals to DEFAULT_NAME
        defaultPlanInfoStepDataAtchHisShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoStepDataAtchHisList where name equals to UPDATED_NAME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoStepDataAtchHisShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoStepDataAtchHisList where name equals to UPDATED_NAME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where name is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("name.specified=true");

        // Get all the planInfoStepDataAtchHisList where name is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByStoreDirIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where storeDir equals to DEFAULT_STORE_DIR
        defaultPlanInfoStepDataAtchHisShouldBeFound("storeDir.equals=" + DEFAULT_STORE_DIR);

        // Get all the planInfoStepDataAtchHisList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("storeDir.equals=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByStoreDirIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where storeDir in DEFAULT_STORE_DIR or UPDATED_STORE_DIR
        defaultPlanInfoStepDataAtchHisShouldBeFound("storeDir.in=" + DEFAULT_STORE_DIR + "," + UPDATED_STORE_DIR);

        // Get all the planInfoStepDataAtchHisList where storeDir equals to UPDATED_STORE_DIR
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("storeDir.in=" + UPDATED_STORE_DIR);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByStoreDirIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where storeDir is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("storeDir.specified=true");

        // Get all the planInfoStepDataAtchHisList where storeDir is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("storeDir.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByStoreNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where storeName equals to DEFAULT_STORE_NAME
        defaultPlanInfoStepDataAtchHisShouldBeFound("storeName.equals=" + DEFAULT_STORE_NAME);

        // Get all the planInfoStepDataAtchHisList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("storeName.equals=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByStoreNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where storeName in DEFAULT_STORE_NAME or UPDATED_STORE_NAME
        defaultPlanInfoStepDataAtchHisShouldBeFound("storeName.in=" + DEFAULT_STORE_NAME + "," + UPDATED_STORE_NAME);

        // Get all the planInfoStepDataAtchHisList where storeName equals to UPDATED_STORE_NAME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("storeName.in=" + UPDATED_STORE_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByStoreNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where storeName is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("storeName.specified=true");

        // Get all the planInfoStepDataAtchHisList where storeName is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("storeName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoStepDataAtchHisShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoStepDataAtchHisList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoStepDataAtchHisShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoStepDataAtchHisList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where sortString is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("sortString.specified=true");

        // Get all the planInfoStepDataAtchHisList where sortString is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByFileTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where fileType equals to DEFAULT_FILE_TYPE
        defaultPlanInfoStepDataAtchHisShouldBeFound("fileType.equals=" + DEFAULT_FILE_TYPE);

        // Get all the planInfoStepDataAtchHisList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("fileType.equals=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByFileTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where fileType in DEFAULT_FILE_TYPE or UPDATED_FILE_TYPE
        defaultPlanInfoStepDataAtchHisShouldBeFound("fileType.in=" + DEFAULT_FILE_TYPE + "," + UPDATED_FILE_TYPE);

        // Get all the planInfoStepDataAtchHisList where fileType equals to UPDATED_FILE_TYPE
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("fileType.in=" + UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByFileTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where fileType is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("fileType.specified=true");

        // Get all the planInfoStepDataAtchHisList where fileType is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("fileType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByDeleteFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where deleteFlag equals to DEFAULT_DELETE_FLAG
        defaultPlanInfoStepDataAtchHisShouldBeFound("deleteFlag.equals=" + DEFAULT_DELETE_FLAG);

        // Get all the planInfoStepDataAtchHisList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("deleteFlag.equals=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByDeleteFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where deleteFlag in DEFAULT_DELETE_FLAG or UPDATED_DELETE_FLAG
        defaultPlanInfoStepDataAtchHisShouldBeFound("deleteFlag.in=" + DEFAULT_DELETE_FLAG + "," + UPDATED_DELETE_FLAG);

        // Get all the planInfoStepDataAtchHisList where deleteFlag equals to UPDATED_DELETE_FLAG
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("deleteFlag.in=" + UPDATED_DELETE_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByDeleteFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where deleteFlag is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("deleteFlag.specified=true");

        // Get all the planInfoStepDataAtchHisList where deleteFlag is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("deleteFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByStoreTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where storeType equals to DEFAULT_STORE_TYPE
        defaultPlanInfoStepDataAtchHisShouldBeFound("storeType.equals=" + DEFAULT_STORE_TYPE);

        // Get all the planInfoStepDataAtchHisList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("storeType.equals=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByStoreTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where storeType in DEFAULT_STORE_TYPE or UPDATED_STORE_TYPE
        defaultPlanInfoStepDataAtchHisShouldBeFound("storeType.in=" + DEFAULT_STORE_TYPE + "," + UPDATED_STORE_TYPE);

        // Get all the planInfoStepDataAtchHisList where storeType equals to UPDATED_STORE_TYPE
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("storeType.in=" + UPDATED_STORE_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByStoreTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where storeType is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("storeType.specified=true");

        // Get all the planInfoStepDataAtchHisList where storeType is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("storeType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByVerIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where ver equals to DEFAULT_VER
        defaultPlanInfoStepDataAtchHisShouldBeFound("ver.equals=" + DEFAULT_VER);

        // Get all the planInfoStepDataAtchHisList where ver equals to UPDATED_VER
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("ver.equals=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByVerIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where ver in DEFAULT_VER or UPDATED_VER
        defaultPlanInfoStepDataAtchHisShouldBeFound("ver.in=" + DEFAULT_VER + "," + UPDATED_VER);

        // Get all the planInfoStepDataAtchHisList where ver equals to UPDATED_VER
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("ver.in=" + UPDATED_VER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByVerIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where ver is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("ver.specified=true");

        // Get all the planInfoStepDataAtchHisList where ver is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("ver.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByEncryptedFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where encryptedFlag equals to DEFAULT_ENCRYPTED_FLAG
        defaultPlanInfoStepDataAtchHisShouldBeFound("encryptedFlag.equals=" + DEFAULT_ENCRYPTED_FLAG);

        // Get all the planInfoStepDataAtchHisList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("encryptedFlag.equals=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByEncryptedFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where encryptedFlag in DEFAULT_ENCRYPTED_FLAG or UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoStepDataAtchHisShouldBeFound("encryptedFlag.in=" + DEFAULT_ENCRYPTED_FLAG + "," + UPDATED_ENCRYPTED_FLAG);

        // Get all the planInfoStepDataAtchHisList where encryptedFlag equals to UPDATED_ENCRYPTED_FLAG
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("encryptedFlag.in=" + UPDATED_ENCRYPTED_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByEncryptedFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where encryptedFlag is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("encryptedFlag.specified=true");

        // Get all the planInfoStepDataAtchHisList where encryptedFlag is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("encryptedFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByEncryptedTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where encryptedType equals to DEFAULT_ENCRYPTED_TYPE
        defaultPlanInfoStepDataAtchHisShouldBeFound("encryptedType.equals=" + DEFAULT_ENCRYPTED_TYPE);

        // Get all the planInfoStepDataAtchHisList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("encryptedType.equals=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByEncryptedTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where encryptedType in DEFAULT_ENCRYPTED_TYPE or UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoStepDataAtchHisShouldBeFound("encryptedType.in=" + DEFAULT_ENCRYPTED_TYPE + "," + UPDATED_ENCRYPTED_TYPE);

        // Get all the planInfoStepDataAtchHisList where encryptedType equals to UPDATED_ENCRYPTED_TYPE
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("encryptedType.in=" + UPDATED_ENCRYPTED_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByEncryptedTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where encryptedType is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("encryptedType.specified=true");

        // Get all the planInfoStepDataAtchHisList where encryptedType is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("encryptedType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoStepDataAtchHisShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoStepDataAtchHisList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoStepDataAtchHisShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoStepDataAtchHisList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where jsonString is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("jsonString.specified=true");

        // Get all the planInfoStepDataAtchHisList where jsonString is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoStepDataAtchHisShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoStepDataAtchHisList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoStepDataAtchHisShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoStepDataAtchHisList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where remarks is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("remarks.specified=true");

        // Get all the planInfoStepDataAtchHisList where remarks is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoStepDataAtchHisShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoStepDataAtchHisList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataAtchHisShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoStepDataAtchHisList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where attachmentPath is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoStepDataAtchHisList where attachmentPath is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoStepDataAtchHisShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoStepDataAtchHisList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataAtchHisShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoStepDataAtchHisList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where attachmentName is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoStepDataAtchHisList where attachmentName is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataAtchHisShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoStepDataAtchHisList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataAtchHisShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoStepDataAtchHisList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where imageBlobName is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoStepDataAtchHisList where imageBlobName is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoStepDataAtchHisShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoStepDataAtchHisList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoStepDataAtchHisShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoStepDataAtchHisList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where validType is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("validType.specified=true");

        // Get all the planInfoStepDataAtchHisList where validType is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoStepDataAtchHisShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoStepDataAtchHisList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataAtchHisShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoStepDataAtchHisList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where validBegin is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("validBegin.specified=true");

        // Get all the planInfoStepDataAtchHisList where validBegin is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoStepDataAtchHisShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoStepDataAtchHisList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoStepDataAtchHisShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoStepDataAtchHisList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where validEnd is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("validEnd.specified=true");

        // Get all the planInfoStepDataAtchHisList where validEnd is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoStepDataAtchHisShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoStepDataAtchHisList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoStepDataAtchHisShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoStepDataAtchHisList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where insertTime is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("insertTime.specified=true");

        // Get all the planInfoStepDataAtchHisList where insertTime is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoStepDataAtchHisShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoStepDataAtchHisList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataAtchHisShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoStepDataAtchHisList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        // Get all the planInfoStepDataAtchHisList where updateTime is not null
        defaultPlanInfoStepDataAtchHisShouldBeFound("updateTime.specified=true");

        // Get all the planInfoStepDataAtchHisList where updateTime is null
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfoStepDataAtchHis.setCreatedBy(createdBy);
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);
        Long createdById = createdBy.getId();

        // Get all the planInfoStepDataAtchHisList where createdBy equals to createdById
        defaultPlanInfoStepDataAtchHisShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoStepDataAtchHisList where createdBy equals to createdById + 1
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfoStepDataAtchHis.setModifiedBy(modifiedBy);
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoStepDataAtchHisList where modifiedBy equals to modifiedById
        defaultPlanInfoStepDataAtchHisShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoStepDataAtchHisList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByPlanInfoStepAtchIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoStepAtch planInfoStepAtch = PlanInfoStepAtchResourceIntTest.createEntity(em);
        em.persist(planInfoStepAtch);
        em.flush();
        planInfoStepDataAtchHis.setPlanInfoStepAtch(planInfoStepAtch);
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);
        Long planInfoStepAtchId = planInfoStepAtch.getId();

        // Get all the planInfoStepDataAtchHisList where planInfoStepAtch equals to planInfoStepAtchId
        defaultPlanInfoStepDataAtchHisShouldBeFound("planInfoStepAtchId.equals=" + planInfoStepAtchId);

        // Get all the planInfoStepDataAtchHisList where planInfoStepAtch equals to planInfoStepAtchId + 1
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("planInfoStepAtchId.equals=" + (planInfoStepAtchId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataAtchHisByPlanInfoStepDataHisIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoStepDataHis planInfoStepDataHis = PlanInfoStepDataHisResourceIntTest.createEntity(em);
        em.persist(planInfoStepDataHis);
        em.flush();
        planInfoStepDataAtchHis.setPlanInfoStepDataHis(planInfoStepDataHis);
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);
        Long planInfoStepDataHisId = planInfoStepDataHis.getId();

        // Get all the planInfoStepDataAtchHisList where planInfoStepDataHis equals to planInfoStepDataHisId
        defaultPlanInfoStepDataAtchHisShouldBeFound("planInfoStepDataHisId.equals=" + planInfoStepDataHisId);

        // Get all the planInfoStepDataAtchHisList where planInfoStepDataHis equals to planInfoStepDataHisId + 1
        defaultPlanInfoStepDataAtchHisShouldNotBeFound("planInfoStepDataHisId.equals=" + (planInfoStepDataHisId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoStepDataAtchHisShouldBeFound(String filter) throws Exception {
        restPlanInfoStepDataAtchHisMockMvc.perform(get("/api/plan-info-step-data-atch-his?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepDataAtchHis.getId().intValue())))
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
        restPlanInfoStepDataAtchHisMockMvc.perform(get("/api/plan-info-step-data-atch-his/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoStepDataAtchHisShouldNotBeFound(String filter) throws Exception {
        restPlanInfoStepDataAtchHisMockMvc.perform(get("/api/plan-info-step-data-atch-his?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoStepDataAtchHisMockMvc.perform(get("/api/plan-info-step-data-atch-his/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfoStepDataAtchHis() throws Exception {
        // Get the planInfoStepDataAtchHis
        restPlanInfoStepDataAtchHisMockMvc.perform(get("/api/plan-info-step-data-atch-his/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfoStepDataAtchHis() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        int databaseSizeBeforeUpdate = planInfoStepDataAtchHisRepository.findAll().size();

        // Update the planInfoStepDataAtchHis
        PlanInfoStepDataAtchHis updatedPlanInfoStepDataAtchHis = planInfoStepDataAtchHisRepository.findById(planInfoStepDataAtchHis.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfoStepDataAtchHis are not directly saved in db
        em.detach(updatedPlanInfoStepDataAtchHis);
        updatedPlanInfoStepDataAtchHis
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
        PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO = planInfoStepDataAtchHisMapper.toDto(updatedPlanInfoStepDataAtchHis);

        restPlanInfoStepDataAtchHisMockMvc.perform(put("/api/plan-info-step-data-atch-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataAtchHisDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfoStepDataAtchHis in the database
        List<PlanInfoStepDataAtchHis> planInfoStepDataAtchHisList = planInfoStepDataAtchHisRepository.findAll();
        assertThat(planInfoStepDataAtchHisList).hasSize(databaseSizeBeforeUpdate);
        PlanInfoStepDataAtchHis testPlanInfoStepDataAtchHis = planInfoStepDataAtchHisList.get(planInfoStepDataAtchHisList.size() - 1);
        assertThat(testPlanInfoStepDataAtchHis.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfoStepDataAtchHis.getStoreDir()).isEqualTo(UPDATED_STORE_DIR);
        assertThat(testPlanInfoStepDataAtchHis.getStoreName()).isEqualTo(UPDATED_STORE_NAME);
        assertThat(testPlanInfoStepDataAtchHis.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfoStepDataAtchHis.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.isDeleteFlag()).isEqualTo(UPDATED_DELETE_FLAG);
        assertThat(testPlanInfoStepDataAtchHis.getStoreType()).isEqualTo(UPDATED_STORE_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.getVer()).isEqualTo(UPDATED_VER);
        assertThat(testPlanInfoStepDataAtchHis.getEncryptedFlag()).isEqualTo(UPDATED_ENCRYPTED_FLAG);
        assertThat(testPlanInfoStepDataAtchHis.getEncryptedType()).isEqualTo(UPDATED_ENCRYPTED_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfoStepDataAtchHis.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfoStepDataAtchHis.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfoStepDataAtchHis.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStepDataAtchHis.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfoStepDataAtchHis.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfoStepDataAtchHis.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfoStepDataAtchHis.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStepDataAtchHis.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfoStepDataAtchHis.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfoStepDataAtchHis.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfoStepDataAtchHis.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfoStepDataAtchHis.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);

        // Validate the PlanInfoStepDataAtchHis in Elasticsearch
        verify(mockPlanInfoStepDataAtchHisSearchRepository, times(1)).save(testPlanInfoStepDataAtchHis);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfoStepDataAtchHis() throws Exception {
        int databaseSizeBeforeUpdate = planInfoStepDataAtchHisRepository.findAll().size();

        // Create the PlanInfoStepDataAtchHis
        PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO = planInfoStepDataAtchHisMapper.toDto(planInfoStepDataAtchHis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoStepDataAtchHisMockMvc.perform(put("/api/plan-info-step-data-atch-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataAtchHisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStepDataAtchHis in the database
        List<PlanInfoStepDataAtchHis> planInfoStepDataAtchHisList = planInfoStepDataAtchHisRepository.findAll();
        assertThat(planInfoStepDataAtchHisList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfoStepDataAtchHis in Elasticsearch
        verify(mockPlanInfoStepDataAtchHisSearchRepository, times(0)).save(planInfoStepDataAtchHis);
    }

    @Test
    @Transactional
    public void deletePlanInfoStepDataAtchHis() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);

        int databaseSizeBeforeDelete = planInfoStepDataAtchHisRepository.findAll().size();

        // Get the planInfoStepDataAtchHis
        restPlanInfoStepDataAtchHisMockMvc.perform(delete("/api/plan-info-step-data-atch-his/{id}", planInfoStepDataAtchHis.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfoStepDataAtchHis> planInfoStepDataAtchHisList = planInfoStepDataAtchHisRepository.findAll();
        assertThat(planInfoStepDataAtchHisList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfoStepDataAtchHis in Elasticsearch
        verify(mockPlanInfoStepDataAtchHisSearchRepository, times(1)).deleteById(planInfoStepDataAtchHis.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfoStepDataAtchHis() throws Exception {
        // Initialize the database
        planInfoStepDataAtchHisRepository.saveAndFlush(planInfoStepDataAtchHis);
        when(mockPlanInfoStepDataAtchHisSearchRepository.search(queryStringQuery("id:" + planInfoStepDataAtchHis.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfoStepDataAtchHis), PageRequest.of(0, 1), 1));
        // Search the planInfoStepDataAtchHis
        restPlanInfoStepDataAtchHisMockMvc.perform(get("/api/_search/plan-info-step-data-atch-his?query=id:" + planInfoStepDataAtchHis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepDataAtchHis.getId().intValue())))
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
        TestUtil.equalsVerifier(PlanInfoStepDataAtchHis.class);
        PlanInfoStepDataAtchHis planInfoStepDataAtchHis1 = new PlanInfoStepDataAtchHis();
        planInfoStepDataAtchHis1.setId(1L);
        PlanInfoStepDataAtchHis planInfoStepDataAtchHis2 = new PlanInfoStepDataAtchHis();
        planInfoStepDataAtchHis2.setId(planInfoStepDataAtchHis1.getId());
        assertThat(planInfoStepDataAtchHis1).isEqualTo(planInfoStepDataAtchHis2);
        planInfoStepDataAtchHis2.setId(2L);
        assertThat(planInfoStepDataAtchHis1).isNotEqualTo(planInfoStepDataAtchHis2);
        planInfoStepDataAtchHis1.setId(null);
        assertThat(planInfoStepDataAtchHis1).isNotEqualTo(planInfoStepDataAtchHis2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoStepDataAtchHisDTO.class);
        PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO1 = new PlanInfoStepDataAtchHisDTO();
        planInfoStepDataAtchHisDTO1.setId(1L);
        PlanInfoStepDataAtchHisDTO planInfoStepDataAtchHisDTO2 = new PlanInfoStepDataAtchHisDTO();
        assertThat(planInfoStepDataAtchHisDTO1).isNotEqualTo(planInfoStepDataAtchHisDTO2);
        planInfoStepDataAtchHisDTO2.setId(planInfoStepDataAtchHisDTO1.getId());
        assertThat(planInfoStepDataAtchHisDTO1).isEqualTo(planInfoStepDataAtchHisDTO2);
        planInfoStepDataAtchHisDTO2.setId(2L);
        assertThat(planInfoStepDataAtchHisDTO1).isNotEqualTo(planInfoStepDataAtchHisDTO2);
        planInfoStepDataAtchHisDTO1.setId(null);
        assertThat(planInfoStepDataAtchHisDTO1).isNotEqualTo(planInfoStepDataAtchHisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoStepDataAtchHisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoStepDataAtchHisMapper.fromId(null)).isNull();
    }
}
