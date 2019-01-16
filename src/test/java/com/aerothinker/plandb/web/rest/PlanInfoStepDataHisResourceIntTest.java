package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfoStepDataHis;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.PlanInfoStep;
import com.aerothinker.plandb.domain.PlanInfoDataHis;
import com.aerothinker.plandb.repository.PlanInfoStepDataHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataHisSearchRepository;
import com.aerothinker.plandb.service.PlanInfoStepDataHisService;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataHisMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataHisCriteria;
import com.aerothinker.plandb.service.PlanInfoStepDataHisQueryService;

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
import com.aerothinker.plandb.domain.enumeration.ViewPermission;
/**
 * Test class for the PlanInfoStepDataHisResource REST controller.
 *
 * @see PlanInfoStepDataHisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoStepDataHisResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SORT_STRING = "AAAAAAAAAA";
    private static final String UPDATED_SORT_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_DESC_STRING = "AAAAAAAAAA";
    private static final String UPDATED_DESC_STRING = "BBBBBBBBBB";

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

    private static final Boolean DEFAULT_USING_FLAG = false;
    private static final Boolean UPDATED_USING_FLAG = true;

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

    private static final Boolean DEFAULT_VERIFY_NEED = false;
    private static final Boolean UPDATED_VERIFY_NEED = true;

    private static final String DEFAULT_VERIFY_OPINION = "AAAAAAAAAA";
    private static final String UPDATED_VERIFY_OPINION = "BBBBBBBBBB";

    private static final Integer DEFAULT_VIEW_COUNT = 1;
    private static final Integer UPDATED_VIEW_COUNT = 2;

    private static final ViewPermission DEFAULT_VIEW_PERMISSION = ViewPermission.PRIVATEVIEW;
    private static final ViewPermission UPDATED_VIEW_PERMISSION = ViewPermission.DEFAULTVIEW;

    private static final String DEFAULT_VIEW_PERM_PERSION = "AAAAAAAAAA";
    private static final String UPDATED_VIEW_PERM_PERSION = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VERSION_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_STEP_ORDER = 1;
    private static final Integer UPDATED_STEP_ORDER = 2;

    @Autowired
    private PlanInfoStepDataHisRepository planInfoStepDataHisRepository;

    @Autowired
    private PlanInfoStepDataHisMapper planInfoStepDataHisMapper;

    @Autowired
    private PlanInfoStepDataHisService planInfoStepDataHisService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoStepDataHisSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoStepDataHisSearchRepository mockPlanInfoStepDataHisSearchRepository;

    @Autowired
    private PlanInfoStepDataHisQueryService planInfoStepDataHisQueryService;

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

    private MockMvc restPlanInfoStepDataHisMockMvc;

    private PlanInfoStepDataHis planInfoStepDataHis;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoStepDataHisResource planInfoStepDataHisResource = new PlanInfoStepDataHisResource(planInfoStepDataHisService, planInfoStepDataHisQueryService);
        this.restPlanInfoStepDataHisMockMvc = MockMvcBuilders.standaloneSetup(planInfoStepDataHisResource)
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
    public static PlanInfoStepDataHis createEntity(EntityManager em) {
        PlanInfoStepDataHis planInfoStepDataHis = new PlanInfoStepDataHis()
            .name(DEFAULT_NAME)
            .sortString(DEFAULT_SORT_STRING)
            .descString(DEFAULT_DESC_STRING)
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
            .usingFlag(DEFAULT_USING_FLAG)
            .validType(DEFAULT_VALID_TYPE)
            .validBegin(DEFAULT_VALID_BEGIN)
            .validEnd(DEFAULT_VALID_END)
            .insertTime(DEFAULT_INSERT_TIME)
            .updateTime(DEFAULT_UPDATE_TIME)
            .verifyTime(DEFAULT_VERIFY_TIME)
            .verifyNeed(DEFAULT_VERIFY_NEED)
            .verifyOpinion(DEFAULT_VERIFY_OPINION)
            .viewCount(DEFAULT_VIEW_COUNT)
            .viewPermission(DEFAULT_VIEW_PERMISSION)
            .viewPermPersion(DEFAULT_VIEW_PERM_PERSION)
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .versionNumber(DEFAULT_VERSION_NUMBER)
            .stepOrder(DEFAULT_STEP_ORDER);
        return planInfoStepDataHis;
    }

    @Before
    public void initTest() {
        planInfoStepDataHis = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfoStepDataHis() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepDataHisRepository.findAll().size();

        // Create the PlanInfoStepDataHis
        PlanInfoStepDataHisDTO planInfoStepDataHisDTO = planInfoStepDataHisMapper.toDto(planInfoStepDataHis);
        restPlanInfoStepDataHisMockMvc.perform(post("/api/plan-info-step-data-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataHisDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfoStepDataHis in the database
        List<PlanInfoStepDataHis> planInfoStepDataHisList = planInfoStepDataHisRepository.findAll();
        assertThat(planInfoStepDataHisList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfoStepDataHis testPlanInfoStepDataHis = planInfoStepDataHisList.get(planInfoStepDataHisList.size() - 1);
        assertThat(testPlanInfoStepDataHis.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfoStepDataHis.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfoStepDataHis.getDescString()).isEqualTo(DEFAULT_DESC_STRING);
        assertThat(testPlanInfoStepDataHis.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfoStepDataHis.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfoStepDataHis.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfoStepDataHis.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStepDataHis.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataHis.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfoStepDataHis.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfoStepDataHis.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfoStepDataHis.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataHis.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStepDataHis.isUsingFlag()).isEqualTo(DEFAULT_USING_FLAG);
        assertThat(testPlanInfoStepDataHis.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfoStepDataHis.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfoStepDataHis.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfoStepDataHis.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfoStepDataHis.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPlanInfoStepDataHis.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);
        assertThat(testPlanInfoStepDataHis.isVerifyNeed()).isEqualTo(DEFAULT_VERIFY_NEED);
        assertThat(testPlanInfoStepDataHis.getVerifyOpinion()).isEqualTo(DEFAULT_VERIFY_OPINION);
        assertThat(testPlanInfoStepDataHis.getViewCount()).isEqualTo(DEFAULT_VIEW_COUNT);
        assertThat(testPlanInfoStepDataHis.getViewPermission()).isEqualTo(DEFAULT_VIEW_PERMISSION);
        assertThat(testPlanInfoStepDataHis.getViewPermPersion()).isEqualTo(DEFAULT_VIEW_PERM_PERSION);
        assertThat(testPlanInfoStepDataHis.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testPlanInfoStepDataHis.getVersionNumber()).isEqualTo(DEFAULT_VERSION_NUMBER);
        assertThat(testPlanInfoStepDataHis.getStepOrder()).isEqualTo(DEFAULT_STEP_ORDER);

        // Validate the PlanInfoStepDataHis in Elasticsearch
        verify(mockPlanInfoStepDataHisSearchRepository, times(1)).save(testPlanInfoStepDataHis);
    }

    @Test
    @Transactional
    public void createPlanInfoStepDataHisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepDataHisRepository.findAll().size();

        // Create the PlanInfoStepDataHis with an existing ID
        planInfoStepDataHis.setId(1L);
        PlanInfoStepDataHisDTO planInfoStepDataHisDTO = planInfoStepDataHisMapper.toDto(planInfoStepDataHis);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoStepDataHisMockMvc.perform(post("/api/plan-info-step-data-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataHisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStepDataHis in the database
        List<PlanInfoStepDataHis> planInfoStepDataHisList = planInfoStepDataHisRepository.findAll();
        assertThat(planInfoStepDataHisList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfoStepDataHis in Elasticsearch
        verify(mockPlanInfoStepDataHisSearchRepository, times(0)).save(planInfoStepDataHis);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoStepDataHisRepository.findAll().size();
        // set the field null
        planInfoStepDataHis.setName(null);

        // Create the PlanInfoStepDataHis, which fails.
        PlanInfoStepDataHisDTO planInfoStepDataHisDTO = planInfoStepDataHisMapper.toDto(planInfoStepDataHis);

        restPlanInfoStepDataHisMockMvc.perform(post("/api/plan-info-step-data-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataHisDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfoStepDataHis> planInfoStepDataHisList = planInfoStepDataHisRepository.findAll();
        assertThat(planInfoStepDataHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHis() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList
        restPlanInfoStepDataHisMockMvc.perform(get("/api/plan-info-step-data-his?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepDataHis.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING.toString())))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING.toString())))
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
            .andExpect(jsonPath("$.[*].usingFlag").value(hasItem(DEFAULT_USING_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyNeed").value(hasItem(DEFAULT_VERIFY_NEED.booleanValue())))
            .andExpect(jsonPath("$.[*].verifyOpinion").value(hasItem(DEFAULT_VERIFY_OPINION.toString())))
            .andExpect(jsonPath("$.[*].viewCount").value(hasItem(DEFAULT_VIEW_COUNT)))
            .andExpect(jsonPath("$.[*].viewPermission").value(hasItem(DEFAULT_VIEW_PERMISSION.toString())))
            .andExpect(jsonPath("$.[*].viewPermPersion").value(hasItem(DEFAULT_VIEW_PERM_PERSION.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].versionNumber").value(hasItem(DEFAULT_VERSION_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].stepOrder").value(hasItem(DEFAULT_STEP_ORDER)));
    }
    
    @Test
    @Transactional
    public void getPlanInfoStepDataHis() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get the planInfoStepDataHis
        restPlanInfoStepDataHisMockMvc.perform(get("/api/plan-info-step-data-his/{id}", planInfoStepDataHis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfoStepDataHis.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sortString").value(DEFAULT_SORT_STRING.toString()))
            .andExpect(jsonPath("$.descString").value(DEFAULT_DESC_STRING.toString()))
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
            .andExpect(jsonPath("$.usingFlag").value(DEFAULT_USING_FLAG.booleanValue()))
            .andExpect(jsonPath("$.validType").value(DEFAULT_VALID_TYPE.toString()))
            .andExpect(jsonPath("$.validBegin").value(DEFAULT_VALID_BEGIN.toString()))
            .andExpect(jsonPath("$.validEnd").value(DEFAULT_VALID_END.toString()))
            .andExpect(jsonPath("$.insertTime").value(DEFAULT_INSERT_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.verifyTime").value(DEFAULT_VERIFY_TIME.toString()))
            .andExpect(jsonPath("$.verifyNeed").value(DEFAULT_VERIFY_NEED.booleanValue()))
            .andExpect(jsonPath("$.verifyOpinion").value(DEFAULT_VERIFY_OPINION.toString()))
            .andExpect(jsonPath("$.viewCount").value(DEFAULT_VIEW_COUNT))
            .andExpect(jsonPath("$.viewPermission").value(DEFAULT_VIEW_PERMISSION.toString()))
            .andExpect(jsonPath("$.viewPermPersion").value(DEFAULT_VIEW_PERM_PERSION.toString()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.versionNumber").value(DEFAULT_VERSION_NUMBER.toString()))
            .andExpect(jsonPath("$.stepOrder").value(DEFAULT_STEP_ORDER));
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where name equals to DEFAULT_NAME
        defaultPlanInfoStepDataHisShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoStepDataHisList where name equals to UPDATED_NAME
        defaultPlanInfoStepDataHisShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoStepDataHisShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoStepDataHisList where name equals to UPDATED_NAME
        defaultPlanInfoStepDataHisShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where name is not null
        defaultPlanInfoStepDataHisShouldBeFound("name.specified=true");

        // Get all the planInfoStepDataHisList where name is null
        defaultPlanInfoStepDataHisShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoStepDataHisShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoStepDataHisList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepDataHisShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoStepDataHisShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoStepDataHisList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepDataHisShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where sortString is not null
        defaultPlanInfoStepDataHisShouldBeFound("sortString.specified=true");

        // Get all the planInfoStepDataHisList where sortString is null
        defaultPlanInfoStepDataHisShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByDescStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where descString equals to DEFAULT_DESC_STRING
        defaultPlanInfoStepDataHisShouldBeFound("descString.equals=" + DEFAULT_DESC_STRING);

        // Get all the planInfoStepDataHisList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoStepDataHisShouldNotBeFound("descString.equals=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByDescStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where descString in DEFAULT_DESC_STRING or UPDATED_DESC_STRING
        defaultPlanInfoStepDataHisShouldBeFound("descString.in=" + DEFAULT_DESC_STRING + "," + UPDATED_DESC_STRING);

        // Get all the planInfoStepDataHisList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoStepDataHisShouldNotBeFound("descString.in=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByDescStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where descString is not null
        defaultPlanInfoStepDataHisShouldBeFound("descString.specified=true");

        // Get all the planInfoStepDataHisList where descString is null
        defaultPlanInfoStepDataHisShouldNotBeFound("descString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoStepDataHisShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoStepDataHisList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepDataHisShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoStepDataHisShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoStepDataHisList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepDataHisShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where jsonString is not null
        defaultPlanInfoStepDataHisShouldBeFound("jsonString.specified=true");

        // Get all the planInfoStepDataHisList where jsonString is null
        defaultPlanInfoStepDataHisShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoStepDataHisShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoStepDataHisList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepDataHisShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoStepDataHisShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoStepDataHisList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepDataHisShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where remarks is not null
        defaultPlanInfoStepDataHisShouldBeFound("remarks.specified=true");

        // Get all the planInfoStepDataHisList where remarks is null
        defaultPlanInfoStepDataHisShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoStepDataHisShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoStepDataHisList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataHisShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataHisShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoStepDataHisList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataHisShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where attachmentPath is not null
        defaultPlanInfoStepDataHisShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoStepDataHisList where attachmentPath is null
        defaultPlanInfoStepDataHisShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoStepDataHisShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoStepDataHisList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataHisShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataHisShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoStepDataHisList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataHisShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where attachmentName is not null
        defaultPlanInfoStepDataHisShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoStepDataHisList where attachmentName is null
        defaultPlanInfoStepDataHisShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataHisShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoStepDataHisList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataHisShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataHisShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoStepDataHisList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataHisShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where imageBlobName is not null
        defaultPlanInfoStepDataHisShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoStepDataHisList where imageBlobName is null
        defaultPlanInfoStepDataHisShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByUsingFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where usingFlag equals to DEFAULT_USING_FLAG
        defaultPlanInfoStepDataHisShouldBeFound("usingFlag.equals=" + DEFAULT_USING_FLAG);

        // Get all the planInfoStepDataHisList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoStepDataHisShouldNotBeFound("usingFlag.equals=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByUsingFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where usingFlag in DEFAULT_USING_FLAG or UPDATED_USING_FLAG
        defaultPlanInfoStepDataHisShouldBeFound("usingFlag.in=" + DEFAULT_USING_FLAG + "," + UPDATED_USING_FLAG);

        // Get all the planInfoStepDataHisList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoStepDataHisShouldNotBeFound("usingFlag.in=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByUsingFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where usingFlag is not null
        defaultPlanInfoStepDataHisShouldBeFound("usingFlag.specified=true");

        // Get all the planInfoStepDataHisList where usingFlag is null
        defaultPlanInfoStepDataHisShouldNotBeFound("usingFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoStepDataHisShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoStepDataHisList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepDataHisShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoStepDataHisShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoStepDataHisList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepDataHisShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where validType is not null
        defaultPlanInfoStepDataHisShouldBeFound("validType.specified=true");

        // Get all the planInfoStepDataHisList where validType is null
        defaultPlanInfoStepDataHisShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoStepDataHisShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoStepDataHisList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataHisShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataHisShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoStepDataHisList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataHisShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where validBegin is not null
        defaultPlanInfoStepDataHisShouldBeFound("validBegin.specified=true");

        // Get all the planInfoStepDataHisList where validBegin is null
        defaultPlanInfoStepDataHisShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoStepDataHisShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoStepDataHisList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepDataHisShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoStepDataHisShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoStepDataHisList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepDataHisShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where validEnd is not null
        defaultPlanInfoStepDataHisShouldBeFound("validEnd.specified=true");

        // Get all the planInfoStepDataHisList where validEnd is null
        defaultPlanInfoStepDataHisShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoStepDataHisShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoStepDataHisList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepDataHisShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoStepDataHisShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoStepDataHisList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepDataHisShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where insertTime is not null
        defaultPlanInfoStepDataHisShouldBeFound("insertTime.specified=true");

        // Get all the planInfoStepDataHisList where insertTime is null
        defaultPlanInfoStepDataHisShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoStepDataHisShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoStepDataHisList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataHisShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataHisShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoStepDataHisList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataHisShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where updateTime is not null
        defaultPlanInfoStepDataHisShouldBeFound("updateTime.specified=true");

        // Get all the planInfoStepDataHisList where updateTime is null
        defaultPlanInfoStepDataHisShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultPlanInfoStepDataHisShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the planInfoStepDataHisList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoStepDataHisShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultPlanInfoStepDataHisShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the planInfoStepDataHisList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoStepDataHisShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where verifyTime is not null
        defaultPlanInfoStepDataHisShouldBeFound("verifyTime.specified=true");

        // Get all the planInfoStepDataHisList where verifyTime is null
        defaultPlanInfoStepDataHisShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVerifyNeedIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where verifyNeed equals to DEFAULT_VERIFY_NEED
        defaultPlanInfoStepDataHisShouldBeFound("verifyNeed.equals=" + DEFAULT_VERIFY_NEED);

        // Get all the planInfoStepDataHisList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoStepDataHisShouldNotBeFound("verifyNeed.equals=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVerifyNeedIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where verifyNeed in DEFAULT_VERIFY_NEED or UPDATED_VERIFY_NEED
        defaultPlanInfoStepDataHisShouldBeFound("verifyNeed.in=" + DEFAULT_VERIFY_NEED + "," + UPDATED_VERIFY_NEED);

        // Get all the planInfoStepDataHisList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoStepDataHisShouldNotBeFound("verifyNeed.in=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVerifyNeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where verifyNeed is not null
        defaultPlanInfoStepDataHisShouldBeFound("verifyNeed.specified=true");

        // Get all the planInfoStepDataHisList where verifyNeed is null
        defaultPlanInfoStepDataHisShouldNotBeFound("verifyNeed.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVerifyOpinionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where verifyOpinion equals to DEFAULT_VERIFY_OPINION
        defaultPlanInfoStepDataHisShouldBeFound("verifyOpinion.equals=" + DEFAULT_VERIFY_OPINION);

        // Get all the planInfoStepDataHisList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoStepDataHisShouldNotBeFound("verifyOpinion.equals=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVerifyOpinionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where verifyOpinion in DEFAULT_VERIFY_OPINION or UPDATED_VERIFY_OPINION
        defaultPlanInfoStepDataHisShouldBeFound("verifyOpinion.in=" + DEFAULT_VERIFY_OPINION + "," + UPDATED_VERIFY_OPINION);

        // Get all the planInfoStepDataHisList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoStepDataHisShouldNotBeFound("verifyOpinion.in=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVerifyOpinionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where verifyOpinion is not null
        defaultPlanInfoStepDataHisShouldBeFound("verifyOpinion.specified=true");

        // Get all the planInfoStepDataHisList where verifyOpinion is null
        defaultPlanInfoStepDataHisShouldNotBeFound("verifyOpinion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByViewCountIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where viewCount equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoStepDataHisShouldBeFound("viewCount.equals=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoStepDataHisList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepDataHisShouldNotBeFound("viewCount.equals=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByViewCountIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where viewCount in DEFAULT_VIEW_COUNT or UPDATED_VIEW_COUNT
        defaultPlanInfoStepDataHisShouldBeFound("viewCount.in=" + DEFAULT_VIEW_COUNT + "," + UPDATED_VIEW_COUNT);

        // Get all the planInfoStepDataHisList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepDataHisShouldNotBeFound("viewCount.in=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByViewCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where viewCount is not null
        defaultPlanInfoStepDataHisShouldBeFound("viewCount.specified=true");

        // Get all the planInfoStepDataHisList where viewCount is null
        defaultPlanInfoStepDataHisShouldNotBeFound("viewCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByViewCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where viewCount greater than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoStepDataHisShouldBeFound("viewCount.greaterOrEqualThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoStepDataHisList where viewCount greater than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepDataHisShouldNotBeFound("viewCount.greaterOrEqualThan=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByViewCountIsLessThanSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where viewCount less than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoStepDataHisShouldNotBeFound("viewCount.lessThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoStepDataHisList where viewCount less than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepDataHisShouldBeFound("viewCount.lessThan=" + UPDATED_VIEW_COUNT);
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByViewPermissionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where viewPermission equals to DEFAULT_VIEW_PERMISSION
        defaultPlanInfoStepDataHisShouldBeFound("viewPermission.equals=" + DEFAULT_VIEW_PERMISSION);

        // Get all the planInfoStepDataHisList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoStepDataHisShouldNotBeFound("viewPermission.equals=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByViewPermissionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where viewPermission in DEFAULT_VIEW_PERMISSION or UPDATED_VIEW_PERMISSION
        defaultPlanInfoStepDataHisShouldBeFound("viewPermission.in=" + DEFAULT_VIEW_PERMISSION + "," + UPDATED_VIEW_PERMISSION);

        // Get all the planInfoStepDataHisList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoStepDataHisShouldNotBeFound("viewPermission.in=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByViewPermissionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where viewPermission is not null
        defaultPlanInfoStepDataHisShouldBeFound("viewPermission.specified=true");

        // Get all the planInfoStepDataHisList where viewPermission is null
        defaultPlanInfoStepDataHisShouldNotBeFound("viewPermission.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByViewPermPersionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where viewPermPersion equals to DEFAULT_VIEW_PERM_PERSION
        defaultPlanInfoStepDataHisShouldBeFound("viewPermPersion.equals=" + DEFAULT_VIEW_PERM_PERSION);

        // Get all the planInfoStepDataHisList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoStepDataHisShouldNotBeFound("viewPermPersion.equals=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByViewPermPersionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where viewPermPersion in DEFAULT_VIEW_PERM_PERSION or UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoStepDataHisShouldBeFound("viewPermPersion.in=" + DEFAULT_VIEW_PERM_PERSION + "," + UPDATED_VIEW_PERM_PERSION);

        // Get all the planInfoStepDataHisList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoStepDataHisShouldNotBeFound("viewPermPersion.in=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByViewPermPersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where viewPermPersion is not null
        defaultPlanInfoStepDataHisShouldBeFound("viewPermPersion.specified=true");

        // Get all the planInfoStepDataHisList where viewPermPersion is null
        defaultPlanInfoStepDataHisShouldNotBeFound("viewPermPersion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultPlanInfoStepDataHisShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the planInfoStepDataHisList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoStepDataHisShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultPlanInfoStepDataHisShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the planInfoStepDataHisList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoStepDataHisShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where serialNumber is not null
        defaultPlanInfoStepDataHisShouldBeFound("serialNumber.specified=true");

        // Get all the planInfoStepDataHisList where serialNumber is null
        defaultPlanInfoStepDataHisShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVersionNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where versionNumber equals to DEFAULT_VERSION_NUMBER
        defaultPlanInfoStepDataHisShouldBeFound("versionNumber.equals=" + DEFAULT_VERSION_NUMBER);

        // Get all the planInfoStepDataHisList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoStepDataHisShouldNotBeFound("versionNumber.equals=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVersionNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where versionNumber in DEFAULT_VERSION_NUMBER or UPDATED_VERSION_NUMBER
        defaultPlanInfoStepDataHisShouldBeFound("versionNumber.in=" + DEFAULT_VERSION_NUMBER + "," + UPDATED_VERSION_NUMBER);

        // Get all the planInfoStepDataHisList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoStepDataHisShouldNotBeFound("versionNumber.in=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVersionNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where versionNumber is not null
        defaultPlanInfoStepDataHisShouldBeFound("versionNumber.specified=true");

        // Get all the planInfoStepDataHisList where versionNumber is null
        defaultPlanInfoStepDataHisShouldNotBeFound("versionNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByStepOrderIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where stepOrder equals to DEFAULT_STEP_ORDER
        defaultPlanInfoStepDataHisShouldBeFound("stepOrder.equals=" + DEFAULT_STEP_ORDER);

        // Get all the planInfoStepDataHisList where stepOrder equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepDataHisShouldNotBeFound("stepOrder.equals=" + UPDATED_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByStepOrderIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where stepOrder in DEFAULT_STEP_ORDER or UPDATED_STEP_ORDER
        defaultPlanInfoStepDataHisShouldBeFound("stepOrder.in=" + DEFAULT_STEP_ORDER + "," + UPDATED_STEP_ORDER);

        // Get all the planInfoStepDataHisList where stepOrder equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepDataHisShouldNotBeFound("stepOrder.in=" + UPDATED_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByStepOrderIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where stepOrder is not null
        defaultPlanInfoStepDataHisShouldBeFound("stepOrder.specified=true");

        // Get all the planInfoStepDataHisList where stepOrder is null
        defaultPlanInfoStepDataHisShouldNotBeFound("stepOrder.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByStepOrderIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where stepOrder greater than or equals to DEFAULT_STEP_ORDER
        defaultPlanInfoStepDataHisShouldBeFound("stepOrder.greaterOrEqualThan=" + DEFAULT_STEP_ORDER);

        // Get all the planInfoStepDataHisList where stepOrder greater than or equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepDataHisShouldNotBeFound("stepOrder.greaterOrEqualThan=" + UPDATED_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByStepOrderIsLessThanSomething() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        // Get all the planInfoStepDataHisList where stepOrder less than or equals to DEFAULT_STEP_ORDER
        defaultPlanInfoStepDataHisShouldNotBeFound("stepOrder.lessThan=" + DEFAULT_STEP_ORDER);

        // Get all the planInfoStepDataHisList where stepOrder less than or equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepDataHisShouldBeFound("stepOrder.lessThan=" + UPDATED_STEP_ORDER);
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfoStepDataHis.setCreatedBy(createdBy);
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);
        Long createdById = createdBy.getId();

        // Get all the planInfoStepDataHisList where createdBy equals to createdById
        defaultPlanInfoStepDataHisShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoStepDataHisList where createdBy equals to createdById + 1
        defaultPlanInfoStepDataHisShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByCreatedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep createdDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(createdDepBy);
        em.flush();
        planInfoStepDataHis.setCreatedDepBy(createdDepBy);
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);
        Long createdDepById = createdDepBy.getId();

        // Get all the planInfoStepDataHisList where createdDepBy equals to createdDepById
        defaultPlanInfoStepDataHisShouldBeFound("createdDepById.equals=" + createdDepById);

        // Get all the planInfoStepDataHisList where createdDepBy equals to createdDepById + 1
        defaultPlanInfoStepDataHisShouldNotBeFound("createdDepById.equals=" + (createdDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfoStepDataHis.setModifiedBy(modifiedBy);
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoStepDataHisList where modifiedBy equals to modifiedById
        defaultPlanInfoStepDataHisShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoStepDataHisList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoStepDataHisShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByModifiedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep modifiedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(modifiedDepBy);
        em.flush();
        planInfoStepDataHis.setModifiedDepBy(modifiedDepBy);
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);
        Long modifiedDepById = modifiedDepBy.getId();

        // Get all the planInfoStepDataHisList where modifiedDepBy equals to modifiedDepById
        defaultPlanInfoStepDataHisShouldBeFound("modifiedDepById.equals=" + modifiedDepById);

        // Get all the planInfoStepDataHisList where modifiedDepBy equals to modifiedDepById + 1
        defaultPlanInfoStepDataHisShouldNotBeFound("modifiedDepById.equals=" + (modifiedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        planInfoStepDataHis.setVerifiedBy(verifiedBy);
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);
        Long verifiedById = verifiedBy.getId();

        // Get all the planInfoStepDataHisList where verifiedBy equals to verifiedById
        defaultPlanInfoStepDataHisShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the planInfoStepDataHisList where verifiedBy equals to verifiedById + 1
        defaultPlanInfoStepDataHisShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByVerifiedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep verifiedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(verifiedDepBy);
        em.flush();
        planInfoStepDataHis.setVerifiedDepBy(verifiedDepBy);
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);
        Long verifiedDepById = verifiedDepBy.getId();

        // Get all the planInfoStepDataHisList where verifiedDepBy equals to verifiedDepById
        defaultPlanInfoStepDataHisShouldBeFound("verifiedDepById.equals=" + verifiedDepById);

        // Get all the planInfoStepDataHisList where verifiedDepBy equals to verifiedDepById + 1
        defaultPlanInfoStepDataHisShouldNotBeFound("verifiedDepById.equals=" + (verifiedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByPlanInfoStepIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoStep planInfoStep = PlanInfoStepResourceIntTest.createEntity(em);
        em.persist(planInfoStep);
        em.flush();
        planInfoStepDataHis.setPlanInfoStep(planInfoStep);
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);
        Long planInfoStepId = planInfoStep.getId();

        // Get all the planInfoStepDataHisList where planInfoStep equals to planInfoStepId
        defaultPlanInfoStepDataHisShouldBeFound("planInfoStepId.equals=" + planInfoStepId);

        // Get all the planInfoStepDataHisList where planInfoStep equals to planInfoStepId + 1
        defaultPlanInfoStepDataHisShouldNotBeFound("planInfoStepId.equals=" + (planInfoStepId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataHisByPlanInfoDataHisIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoDataHis planInfoDataHis = PlanInfoDataHisResourceIntTest.createEntity(em);
        em.persist(planInfoDataHis);
        em.flush();
        planInfoStepDataHis.setPlanInfoDataHis(planInfoDataHis);
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);
        Long planInfoDataHisId = planInfoDataHis.getId();

        // Get all the planInfoStepDataHisList where planInfoDataHis equals to planInfoDataHisId
        defaultPlanInfoStepDataHisShouldBeFound("planInfoDataHisId.equals=" + planInfoDataHisId);

        // Get all the planInfoStepDataHisList where planInfoDataHis equals to planInfoDataHisId + 1
        defaultPlanInfoStepDataHisShouldNotBeFound("planInfoDataHisId.equals=" + (planInfoDataHisId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoStepDataHisShouldBeFound(String filter) throws Exception {
        restPlanInfoStepDataHisMockMvc.perform(get("/api/plan-info-step-data-his?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepDataHis.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING.toString())))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING.toString())))
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
            .andExpect(jsonPath("$.[*].usingFlag").value(hasItem(DEFAULT_USING_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyNeed").value(hasItem(DEFAULT_VERIFY_NEED.booleanValue())))
            .andExpect(jsonPath("$.[*].verifyOpinion").value(hasItem(DEFAULT_VERIFY_OPINION.toString())))
            .andExpect(jsonPath("$.[*].viewCount").value(hasItem(DEFAULT_VIEW_COUNT)))
            .andExpect(jsonPath("$.[*].viewPermission").value(hasItem(DEFAULT_VIEW_PERMISSION.toString())))
            .andExpect(jsonPath("$.[*].viewPermPersion").value(hasItem(DEFAULT_VIEW_PERM_PERSION.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].versionNumber").value(hasItem(DEFAULT_VERSION_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].stepOrder").value(hasItem(DEFAULT_STEP_ORDER)));

        // Check, that the count call also returns 1
        restPlanInfoStepDataHisMockMvc.perform(get("/api/plan-info-step-data-his/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoStepDataHisShouldNotBeFound(String filter) throws Exception {
        restPlanInfoStepDataHisMockMvc.perform(get("/api/plan-info-step-data-his?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoStepDataHisMockMvc.perform(get("/api/plan-info-step-data-his/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfoStepDataHis() throws Exception {
        // Get the planInfoStepDataHis
        restPlanInfoStepDataHisMockMvc.perform(get("/api/plan-info-step-data-his/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfoStepDataHis() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        int databaseSizeBeforeUpdate = planInfoStepDataHisRepository.findAll().size();

        // Update the planInfoStepDataHis
        PlanInfoStepDataHis updatedPlanInfoStepDataHis = planInfoStepDataHisRepository.findById(planInfoStepDataHis.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfoStepDataHis are not directly saved in db
        em.detach(updatedPlanInfoStepDataHis);
        updatedPlanInfoStepDataHis
            .name(UPDATED_NAME)
            .sortString(UPDATED_SORT_STRING)
            .descString(UPDATED_DESC_STRING)
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
            .usingFlag(UPDATED_USING_FLAG)
            .validType(UPDATED_VALID_TYPE)
            .validBegin(UPDATED_VALID_BEGIN)
            .validEnd(UPDATED_VALID_END)
            .insertTime(UPDATED_INSERT_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .verifyTime(UPDATED_VERIFY_TIME)
            .verifyNeed(UPDATED_VERIFY_NEED)
            .verifyOpinion(UPDATED_VERIFY_OPINION)
            .viewCount(UPDATED_VIEW_COUNT)
            .viewPermission(UPDATED_VIEW_PERMISSION)
            .viewPermPersion(UPDATED_VIEW_PERM_PERSION)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .versionNumber(UPDATED_VERSION_NUMBER)
            .stepOrder(UPDATED_STEP_ORDER);
        PlanInfoStepDataHisDTO planInfoStepDataHisDTO = planInfoStepDataHisMapper.toDto(updatedPlanInfoStepDataHis);

        restPlanInfoStepDataHisMockMvc.perform(put("/api/plan-info-step-data-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataHisDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfoStepDataHis in the database
        List<PlanInfoStepDataHis> planInfoStepDataHisList = planInfoStepDataHisRepository.findAll();
        assertThat(planInfoStepDataHisList).hasSize(databaseSizeBeforeUpdate);
        PlanInfoStepDataHis testPlanInfoStepDataHis = planInfoStepDataHisList.get(planInfoStepDataHisList.size() - 1);
        assertThat(testPlanInfoStepDataHis.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfoStepDataHis.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfoStepDataHis.getDescString()).isEqualTo(UPDATED_DESC_STRING);
        assertThat(testPlanInfoStepDataHis.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfoStepDataHis.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfoStepDataHis.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfoStepDataHis.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStepDataHis.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataHis.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfoStepDataHis.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfoStepDataHis.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfoStepDataHis.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepDataHis.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStepDataHis.isUsingFlag()).isEqualTo(UPDATED_USING_FLAG);
        assertThat(testPlanInfoStepDataHis.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfoStepDataHis.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfoStepDataHis.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfoStepDataHis.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfoStepDataHis.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPlanInfoStepDataHis.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);
        assertThat(testPlanInfoStepDataHis.isVerifyNeed()).isEqualTo(UPDATED_VERIFY_NEED);
        assertThat(testPlanInfoStepDataHis.getVerifyOpinion()).isEqualTo(UPDATED_VERIFY_OPINION);
        assertThat(testPlanInfoStepDataHis.getViewCount()).isEqualTo(UPDATED_VIEW_COUNT);
        assertThat(testPlanInfoStepDataHis.getViewPermission()).isEqualTo(UPDATED_VIEW_PERMISSION);
        assertThat(testPlanInfoStepDataHis.getViewPermPersion()).isEqualTo(UPDATED_VIEW_PERM_PERSION);
        assertThat(testPlanInfoStepDataHis.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testPlanInfoStepDataHis.getVersionNumber()).isEqualTo(UPDATED_VERSION_NUMBER);
        assertThat(testPlanInfoStepDataHis.getStepOrder()).isEqualTo(UPDATED_STEP_ORDER);

        // Validate the PlanInfoStepDataHis in Elasticsearch
        verify(mockPlanInfoStepDataHisSearchRepository, times(1)).save(testPlanInfoStepDataHis);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfoStepDataHis() throws Exception {
        int databaseSizeBeforeUpdate = planInfoStepDataHisRepository.findAll().size();

        // Create the PlanInfoStepDataHis
        PlanInfoStepDataHisDTO planInfoStepDataHisDTO = planInfoStepDataHisMapper.toDto(planInfoStepDataHis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoStepDataHisMockMvc.perform(put("/api/plan-info-step-data-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataHisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStepDataHis in the database
        List<PlanInfoStepDataHis> planInfoStepDataHisList = planInfoStepDataHisRepository.findAll();
        assertThat(planInfoStepDataHisList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfoStepDataHis in Elasticsearch
        verify(mockPlanInfoStepDataHisSearchRepository, times(0)).save(planInfoStepDataHis);
    }

    @Test
    @Transactional
    public void deletePlanInfoStepDataHis() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);

        int databaseSizeBeforeDelete = planInfoStepDataHisRepository.findAll().size();

        // Get the planInfoStepDataHis
        restPlanInfoStepDataHisMockMvc.perform(delete("/api/plan-info-step-data-his/{id}", planInfoStepDataHis.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfoStepDataHis> planInfoStepDataHisList = planInfoStepDataHisRepository.findAll();
        assertThat(planInfoStepDataHisList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfoStepDataHis in Elasticsearch
        verify(mockPlanInfoStepDataHisSearchRepository, times(1)).deleteById(planInfoStepDataHis.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfoStepDataHis() throws Exception {
        // Initialize the database
        planInfoStepDataHisRepository.saveAndFlush(planInfoStepDataHis);
        when(mockPlanInfoStepDataHisSearchRepository.search(queryStringQuery("id:" + planInfoStepDataHis.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfoStepDataHis), PageRequest.of(0, 1), 1));
        // Search the planInfoStepDataHis
        restPlanInfoStepDataHisMockMvc.perform(get("/api/_search/plan-info-step-data-his?query=id:" + planInfoStepDataHis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepDataHis.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING)))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING)))
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
            .andExpect(jsonPath("$.[*].usingFlag").value(hasItem(DEFAULT_USING_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyNeed").value(hasItem(DEFAULT_VERIFY_NEED.booleanValue())))
            .andExpect(jsonPath("$.[*].verifyOpinion").value(hasItem(DEFAULT_VERIFY_OPINION)))
            .andExpect(jsonPath("$.[*].viewCount").value(hasItem(DEFAULT_VIEW_COUNT)))
            .andExpect(jsonPath("$.[*].viewPermission").value(hasItem(DEFAULT_VIEW_PERMISSION.toString())))
            .andExpect(jsonPath("$.[*].viewPermPersion").value(hasItem(DEFAULT_VIEW_PERM_PERSION)))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)))
            .andExpect(jsonPath("$.[*].versionNumber").value(hasItem(DEFAULT_VERSION_NUMBER)))
            .andExpect(jsonPath("$.[*].stepOrder").value(hasItem(DEFAULT_STEP_ORDER)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoStepDataHis.class);
        PlanInfoStepDataHis planInfoStepDataHis1 = new PlanInfoStepDataHis();
        planInfoStepDataHis1.setId(1L);
        PlanInfoStepDataHis planInfoStepDataHis2 = new PlanInfoStepDataHis();
        planInfoStepDataHis2.setId(planInfoStepDataHis1.getId());
        assertThat(planInfoStepDataHis1).isEqualTo(planInfoStepDataHis2);
        planInfoStepDataHis2.setId(2L);
        assertThat(planInfoStepDataHis1).isNotEqualTo(planInfoStepDataHis2);
        planInfoStepDataHis1.setId(null);
        assertThat(planInfoStepDataHis1).isNotEqualTo(planInfoStepDataHis2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoStepDataHisDTO.class);
        PlanInfoStepDataHisDTO planInfoStepDataHisDTO1 = new PlanInfoStepDataHisDTO();
        planInfoStepDataHisDTO1.setId(1L);
        PlanInfoStepDataHisDTO planInfoStepDataHisDTO2 = new PlanInfoStepDataHisDTO();
        assertThat(planInfoStepDataHisDTO1).isNotEqualTo(planInfoStepDataHisDTO2);
        planInfoStepDataHisDTO2.setId(planInfoStepDataHisDTO1.getId());
        assertThat(planInfoStepDataHisDTO1).isEqualTo(planInfoStepDataHisDTO2);
        planInfoStepDataHisDTO2.setId(2L);
        assertThat(planInfoStepDataHisDTO1).isNotEqualTo(planInfoStepDataHisDTO2);
        planInfoStepDataHisDTO1.setId(null);
        assertThat(planInfoStepDataHisDTO1).isNotEqualTo(planInfoStepDataHisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoStepDataHisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoStepDataHisMapper.fromId(null)).isNull();
    }
}
