package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfoDataHis;
import com.aerothinker.plandb.domain.VerifyRec;
import com.aerothinker.plandb.domain.ParaType;
import com.aerothinker.plandb.domain.ParaClass;
import com.aerothinker.plandb.domain.ParaCat;
import com.aerothinker.plandb.domain.ParaState;
import com.aerothinker.plandb.domain.ParaSource;
import com.aerothinker.plandb.domain.ParaAttr;
import com.aerothinker.plandb.domain.ParaProp;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.PlanInfoDataHis;
import com.aerothinker.plandb.domain.PlanInfo;
import com.aerothinker.plandb.repository.PlanInfoDataHisRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataHisSearchRepository;
import com.aerothinker.plandb.service.PlanInfoDataHisService;
import com.aerothinker.plandb.service.dto.PlanInfoDataHisDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataHisMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoDataHisCriteria;
import com.aerothinker.plandb.service.PlanInfoDataHisQueryService;

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
 * Test class for the PlanInfoDataHisResource REST controller.
 *
 * @see PlanInfoDataHisResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoDataHisResourceIntTest {

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

    private static final String DEFAULT_REF_EVENT = "AAAAAAAAAA";
    private static final String UPDATED_REF_EVENT = "BBBBBBBBBB";

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

    private static final String DEFAULT_PARA_SOURCE_STRING = "AAAAAAAAAA";
    private static final String UPDATED_PARA_SOURCE_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_FEATURE_KEYWORD = "AAAAAAAAAA";
    private static final String UPDATED_FEATURE_KEYWORD = "BBBBBBBBBB";

    private static final String DEFAULT_SUGGESTION = "AAAAAAAAAA";
    private static final String UPDATED_SUGGESTION = "BBBBBBBBBB";

    private static final String DEFAULT_RELEASE_SCOPE = "AAAAAAAAAA";
    private static final String UPDATED_RELEASE_SCOPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CURRENT_STEP_ORDER = 1;
    private static final Integer UPDATED_CURRENT_STEP_ORDER = 2;

    @Autowired
    private PlanInfoDataHisRepository planInfoDataHisRepository;

    @Autowired
    private PlanInfoDataHisMapper planInfoDataHisMapper;

    @Autowired
    private PlanInfoDataHisService planInfoDataHisService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoDataHisSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoDataHisSearchRepository mockPlanInfoDataHisSearchRepository;

    @Autowired
    private PlanInfoDataHisQueryService planInfoDataHisQueryService;

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

    private MockMvc restPlanInfoDataHisMockMvc;

    private PlanInfoDataHis planInfoDataHis;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoDataHisResource planInfoDataHisResource = new PlanInfoDataHisResource(planInfoDataHisService, planInfoDataHisQueryService);
        this.restPlanInfoDataHisMockMvc = MockMvcBuilders.standaloneSetup(planInfoDataHisResource)
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
    public static PlanInfoDataHis createEntity(EntityManager em) {
        PlanInfoDataHis planInfoDataHis = new PlanInfoDataHis()
            .name(DEFAULT_NAME)
            .sortString(DEFAULT_SORT_STRING)
            .descString(DEFAULT_DESC_STRING)
            .jsonString(DEFAULT_JSON_STRING)
            .remarks(DEFAULT_REMARKS)
            .refEvent(DEFAULT_REF_EVENT)
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
            .paraSourceString(DEFAULT_PARA_SOURCE_STRING)
            .featureKeyword(DEFAULT_FEATURE_KEYWORD)
            .suggestion(DEFAULT_SUGGESTION)
            .releaseScope(DEFAULT_RELEASE_SCOPE)
            .currentStepOrder(DEFAULT_CURRENT_STEP_ORDER);
        return planInfoDataHis;
    }

    @Before
    public void initTest() {
        planInfoDataHis = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfoDataHis() throws Exception {
        int databaseSizeBeforeCreate = planInfoDataHisRepository.findAll().size();

        // Create the PlanInfoDataHis
        PlanInfoDataHisDTO planInfoDataHisDTO = planInfoDataHisMapper.toDto(planInfoDataHis);
        restPlanInfoDataHisMockMvc.perform(post("/api/plan-info-data-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataHisDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfoDataHis in the database
        List<PlanInfoDataHis> planInfoDataHisList = planInfoDataHisRepository.findAll();
        assertThat(planInfoDataHisList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfoDataHis testPlanInfoDataHis = planInfoDataHisList.get(planInfoDataHisList.size() - 1);
        assertThat(testPlanInfoDataHis.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfoDataHis.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfoDataHis.getDescString()).isEqualTo(DEFAULT_DESC_STRING);
        assertThat(testPlanInfoDataHis.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfoDataHis.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfoDataHis.getRefEvent()).isEqualTo(DEFAULT_REF_EVENT);
        assertThat(testPlanInfoDataHis.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfoDataHis.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfoDataHis.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataHis.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfoDataHis.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfoDataHis.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfoDataHis.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataHis.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoDataHis.isUsingFlag()).isEqualTo(DEFAULT_USING_FLAG);
        assertThat(testPlanInfoDataHis.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfoDataHis.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfoDataHis.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfoDataHis.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfoDataHis.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPlanInfoDataHis.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);
        assertThat(testPlanInfoDataHis.isVerifyNeed()).isEqualTo(DEFAULT_VERIFY_NEED);
        assertThat(testPlanInfoDataHis.getVerifyOpinion()).isEqualTo(DEFAULT_VERIFY_OPINION);
        assertThat(testPlanInfoDataHis.getViewCount()).isEqualTo(DEFAULT_VIEW_COUNT);
        assertThat(testPlanInfoDataHis.getViewPermission()).isEqualTo(DEFAULT_VIEW_PERMISSION);
        assertThat(testPlanInfoDataHis.getViewPermPersion()).isEqualTo(DEFAULT_VIEW_PERM_PERSION);
        assertThat(testPlanInfoDataHis.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testPlanInfoDataHis.getVersionNumber()).isEqualTo(DEFAULT_VERSION_NUMBER);
        assertThat(testPlanInfoDataHis.getParaSourceString()).isEqualTo(DEFAULT_PARA_SOURCE_STRING);
        assertThat(testPlanInfoDataHis.getFeatureKeyword()).isEqualTo(DEFAULT_FEATURE_KEYWORD);
        assertThat(testPlanInfoDataHis.getSuggestion()).isEqualTo(DEFAULT_SUGGESTION);
        assertThat(testPlanInfoDataHis.getReleaseScope()).isEqualTo(DEFAULT_RELEASE_SCOPE);
        assertThat(testPlanInfoDataHis.getCurrentStepOrder()).isEqualTo(DEFAULT_CURRENT_STEP_ORDER);

        // Validate the PlanInfoDataHis in Elasticsearch
        verify(mockPlanInfoDataHisSearchRepository, times(1)).save(testPlanInfoDataHis);
    }

    @Test
    @Transactional
    public void createPlanInfoDataHisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoDataHisRepository.findAll().size();

        // Create the PlanInfoDataHis with an existing ID
        planInfoDataHis.setId(1L);
        PlanInfoDataHisDTO planInfoDataHisDTO = planInfoDataHisMapper.toDto(planInfoDataHis);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoDataHisMockMvc.perform(post("/api/plan-info-data-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataHisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoDataHis in the database
        List<PlanInfoDataHis> planInfoDataHisList = planInfoDataHisRepository.findAll();
        assertThat(planInfoDataHisList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfoDataHis in Elasticsearch
        verify(mockPlanInfoDataHisSearchRepository, times(0)).save(planInfoDataHis);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoDataHisRepository.findAll().size();
        // set the field null
        planInfoDataHis.setName(null);

        // Create the PlanInfoDataHis, which fails.
        PlanInfoDataHisDTO planInfoDataHisDTO = planInfoDataHisMapper.toDto(planInfoDataHis);

        restPlanInfoDataHisMockMvc.perform(post("/api/plan-info-data-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataHisDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfoDataHis> planInfoDataHisList = planInfoDataHisRepository.findAll();
        assertThat(planInfoDataHisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHis() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList
        restPlanInfoDataHisMockMvc.perform(get("/api/plan-info-data-his?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoDataHis.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING.toString())))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING.toString())))
            .andExpect(jsonPath("$.[*].jsonString").value(hasItem(DEFAULT_JSON_STRING.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].refEvent").value(hasItem(DEFAULT_REF_EVENT.toString())))
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
            .andExpect(jsonPath("$.[*].paraSourceString").value(hasItem(DEFAULT_PARA_SOURCE_STRING.toString())))
            .andExpect(jsonPath("$.[*].featureKeyword").value(hasItem(DEFAULT_FEATURE_KEYWORD.toString())))
            .andExpect(jsonPath("$.[*].suggestion").value(hasItem(DEFAULT_SUGGESTION.toString())))
            .andExpect(jsonPath("$.[*].releaseScope").value(hasItem(DEFAULT_RELEASE_SCOPE.toString())))
            .andExpect(jsonPath("$.[*].currentStepOrder").value(hasItem(DEFAULT_CURRENT_STEP_ORDER)));
    }
    
    @Test
    @Transactional
    public void getPlanInfoDataHis() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get the planInfoDataHis
        restPlanInfoDataHisMockMvc.perform(get("/api/plan-info-data-his/{id}", planInfoDataHis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfoDataHis.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.sortString").value(DEFAULT_SORT_STRING.toString()))
            .andExpect(jsonPath("$.descString").value(DEFAULT_DESC_STRING.toString()))
            .andExpect(jsonPath("$.jsonString").value(DEFAULT_JSON_STRING.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.refEvent").value(DEFAULT_REF_EVENT.toString()))
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
            .andExpect(jsonPath("$.paraSourceString").value(DEFAULT_PARA_SOURCE_STRING.toString()))
            .andExpect(jsonPath("$.featureKeyword").value(DEFAULT_FEATURE_KEYWORD.toString()))
            .andExpect(jsonPath("$.suggestion").value(DEFAULT_SUGGESTION.toString()))
            .andExpect(jsonPath("$.releaseScope").value(DEFAULT_RELEASE_SCOPE.toString()))
            .andExpect(jsonPath("$.currentStepOrder").value(DEFAULT_CURRENT_STEP_ORDER));
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where name equals to DEFAULT_NAME
        defaultPlanInfoDataHisShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoDataHisList where name equals to UPDATED_NAME
        defaultPlanInfoDataHisShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoDataHisShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoDataHisList where name equals to UPDATED_NAME
        defaultPlanInfoDataHisShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where name is not null
        defaultPlanInfoDataHisShouldBeFound("name.specified=true");

        // Get all the planInfoDataHisList where name is null
        defaultPlanInfoDataHisShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoDataHisShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoDataHisList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoDataHisShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoDataHisShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoDataHisList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoDataHisShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where sortString is not null
        defaultPlanInfoDataHisShouldBeFound("sortString.specified=true");

        // Get all the planInfoDataHisList where sortString is null
        defaultPlanInfoDataHisShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByDescStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where descString equals to DEFAULT_DESC_STRING
        defaultPlanInfoDataHisShouldBeFound("descString.equals=" + DEFAULT_DESC_STRING);

        // Get all the planInfoDataHisList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoDataHisShouldNotBeFound("descString.equals=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByDescStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where descString in DEFAULT_DESC_STRING or UPDATED_DESC_STRING
        defaultPlanInfoDataHisShouldBeFound("descString.in=" + DEFAULT_DESC_STRING + "," + UPDATED_DESC_STRING);

        // Get all the planInfoDataHisList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoDataHisShouldNotBeFound("descString.in=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByDescStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where descString is not null
        defaultPlanInfoDataHisShouldBeFound("descString.specified=true");

        // Get all the planInfoDataHisList where descString is null
        defaultPlanInfoDataHisShouldNotBeFound("descString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoDataHisShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoDataHisList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoDataHisShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoDataHisShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoDataHisList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoDataHisShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where jsonString is not null
        defaultPlanInfoDataHisShouldBeFound("jsonString.specified=true");

        // Get all the planInfoDataHisList where jsonString is null
        defaultPlanInfoDataHisShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoDataHisShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoDataHisList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoDataHisShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoDataHisShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoDataHisList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoDataHisShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where remarks is not null
        defaultPlanInfoDataHisShouldBeFound("remarks.specified=true");

        // Get all the planInfoDataHisList where remarks is null
        defaultPlanInfoDataHisShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByRefEventIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where refEvent equals to DEFAULT_REF_EVENT
        defaultPlanInfoDataHisShouldBeFound("refEvent.equals=" + DEFAULT_REF_EVENT);

        // Get all the planInfoDataHisList where refEvent equals to UPDATED_REF_EVENT
        defaultPlanInfoDataHisShouldNotBeFound("refEvent.equals=" + UPDATED_REF_EVENT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByRefEventIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where refEvent in DEFAULT_REF_EVENT or UPDATED_REF_EVENT
        defaultPlanInfoDataHisShouldBeFound("refEvent.in=" + DEFAULT_REF_EVENT + "," + UPDATED_REF_EVENT);

        // Get all the planInfoDataHisList where refEvent equals to UPDATED_REF_EVENT
        defaultPlanInfoDataHisShouldNotBeFound("refEvent.in=" + UPDATED_REF_EVENT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByRefEventIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where refEvent is not null
        defaultPlanInfoDataHisShouldBeFound("refEvent.specified=true");

        // Get all the planInfoDataHisList where refEvent is null
        defaultPlanInfoDataHisShouldNotBeFound("refEvent.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoDataHisShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoDataHisList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataHisShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataHisShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoDataHisList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataHisShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where attachmentPath is not null
        defaultPlanInfoDataHisShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoDataHisList where attachmentPath is null
        defaultPlanInfoDataHisShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoDataHisShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoDataHisList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataHisShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataHisShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoDataHisList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataHisShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where attachmentName is not null
        defaultPlanInfoDataHisShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoDataHisList where attachmentName is null
        defaultPlanInfoDataHisShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoDataHisShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoDataHisList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataHisShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataHisShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoDataHisList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataHisShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where imageBlobName is not null
        defaultPlanInfoDataHisShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoDataHisList where imageBlobName is null
        defaultPlanInfoDataHisShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByUsingFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where usingFlag equals to DEFAULT_USING_FLAG
        defaultPlanInfoDataHisShouldBeFound("usingFlag.equals=" + DEFAULT_USING_FLAG);

        // Get all the planInfoDataHisList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoDataHisShouldNotBeFound("usingFlag.equals=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByUsingFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where usingFlag in DEFAULT_USING_FLAG or UPDATED_USING_FLAG
        defaultPlanInfoDataHisShouldBeFound("usingFlag.in=" + DEFAULT_USING_FLAG + "," + UPDATED_USING_FLAG);

        // Get all the planInfoDataHisList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoDataHisShouldNotBeFound("usingFlag.in=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByUsingFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where usingFlag is not null
        defaultPlanInfoDataHisShouldBeFound("usingFlag.specified=true");

        // Get all the planInfoDataHisList where usingFlag is null
        defaultPlanInfoDataHisShouldNotBeFound("usingFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoDataHisShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoDataHisList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoDataHisShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoDataHisShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoDataHisList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoDataHisShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where validType is not null
        defaultPlanInfoDataHisShouldBeFound("validType.specified=true");

        // Get all the planInfoDataHisList where validType is null
        defaultPlanInfoDataHisShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoDataHisShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoDataHisList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoDataHisShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoDataHisShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoDataHisList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoDataHisShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where validBegin is not null
        defaultPlanInfoDataHisShouldBeFound("validBegin.specified=true");

        // Get all the planInfoDataHisList where validBegin is null
        defaultPlanInfoDataHisShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoDataHisShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoDataHisList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoDataHisShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoDataHisShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoDataHisList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoDataHisShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where validEnd is not null
        defaultPlanInfoDataHisShouldBeFound("validEnd.specified=true");

        // Get all the planInfoDataHisList where validEnd is null
        defaultPlanInfoDataHisShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoDataHisShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoDataHisList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoDataHisShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoDataHisShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoDataHisList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoDataHisShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where insertTime is not null
        defaultPlanInfoDataHisShouldBeFound("insertTime.specified=true");

        // Get all the planInfoDataHisList where insertTime is null
        defaultPlanInfoDataHisShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoDataHisShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoDataHisList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoDataHisShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoDataHisShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoDataHisList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoDataHisShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where updateTime is not null
        defaultPlanInfoDataHisShouldBeFound("updateTime.specified=true");

        // Get all the planInfoDataHisList where updateTime is null
        defaultPlanInfoDataHisShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultPlanInfoDataHisShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the planInfoDataHisList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoDataHisShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultPlanInfoDataHisShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the planInfoDataHisList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoDataHisShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where verifyTime is not null
        defaultPlanInfoDataHisShouldBeFound("verifyTime.specified=true");

        // Get all the planInfoDataHisList where verifyTime is null
        defaultPlanInfoDataHisShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifyNeedIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where verifyNeed equals to DEFAULT_VERIFY_NEED
        defaultPlanInfoDataHisShouldBeFound("verifyNeed.equals=" + DEFAULT_VERIFY_NEED);

        // Get all the planInfoDataHisList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoDataHisShouldNotBeFound("verifyNeed.equals=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifyNeedIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where verifyNeed in DEFAULT_VERIFY_NEED or UPDATED_VERIFY_NEED
        defaultPlanInfoDataHisShouldBeFound("verifyNeed.in=" + DEFAULT_VERIFY_NEED + "," + UPDATED_VERIFY_NEED);

        // Get all the planInfoDataHisList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoDataHisShouldNotBeFound("verifyNeed.in=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifyNeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where verifyNeed is not null
        defaultPlanInfoDataHisShouldBeFound("verifyNeed.specified=true");

        // Get all the planInfoDataHisList where verifyNeed is null
        defaultPlanInfoDataHisShouldNotBeFound("verifyNeed.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifyOpinionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where verifyOpinion equals to DEFAULT_VERIFY_OPINION
        defaultPlanInfoDataHisShouldBeFound("verifyOpinion.equals=" + DEFAULT_VERIFY_OPINION);

        // Get all the planInfoDataHisList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoDataHisShouldNotBeFound("verifyOpinion.equals=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifyOpinionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where verifyOpinion in DEFAULT_VERIFY_OPINION or UPDATED_VERIFY_OPINION
        defaultPlanInfoDataHisShouldBeFound("verifyOpinion.in=" + DEFAULT_VERIFY_OPINION + "," + UPDATED_VERIFY_OPINION);

        // Get all the planInfoDataHisList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoDataHisShouldNotBeFound("verifyOpinion.in=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifyOpinionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where verifyOpinion is not null
        defaultPlanInfoDataHisShouldBeFound("verifyOpinion.specified=true");

        // Get all the planInfoDataHisList where verifyOpinion is null
        defaultPlanInfoDataHisShouldNotBeFound("verifyOpinion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByViewCountIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where viewCount equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoDataHisShouldBeFound("viewCount.equals=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoDataHisList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoDataHisShouldNotBeFound("viewCount.equals=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByViewCountIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where viewCount in DEFAULT_VIEW_COUNT or UPDATED_VIEW_COUNT
        defaultPlanInfoDataHisShouldBeFound("viewCount.in=" + DEFAULT_VIEW_COUNT + "," + UPDATED_VIEW_COUNT);

        // Get all the planInfoDataHisList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoDataHisShouldNotBeFound("viewCount.in=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByViewCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where viewCount is not null
        defaultPlanInfoDataHisShouldBeFound("viewCount.specified=true");

        // Get all the planInfoDataHisList where viewCount is null
        defaultPlanInfoDataHisShouldNotBeFound("viewCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByViewCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where viewCount greater than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoDataHisShouldBeFound("viewCount.greaterOrEqualThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoDataHisList where viewCount greater than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoDataHisShouldNotBeFound("viewCount.greaterOrEqualThan=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByViewCountIsLessThanSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where viewCount less than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoDataHisShouldNotBeFound("viewCount.lessThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoDataHisList where viewCount less than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoDataHisShouldBeFound("viewCount.lessThan=" + UPDATED_VIEW_COUNT);
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByViewPermissionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where viewPermission equals to DEFAULT_VIEW_PERMISSION
        defaultPlanInfoDataHisShouldBeFound("viewPermission.equals=" + DEFAULT_VIEW_PERMISSION);

        // Get all the planInfoDataHisList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoDataHisShouldNotBeFound("viewPermission.equals=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByViewPermissionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where viewPermission in DEFAULT_VIEW_PERMISSION or UPDATED_VIEW_PERMISSION
        defaultPlanInfoDataHisShouldBeFound("viewPermission.in=" + DEFAULT_VIEW_PERMISSION + "," + UPDATED_VIEW_PERMISSION);

        // Get all the planInfoDataHisList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoDataHisShouldNotBeFound("viewPermission.in=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByViewPermissionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where viewPermission is not null
        defaultPlanInfoDataHisShouldBeFound("viewPermission.specified=true");

        // Get all the planInfoDataHisList where viewPermission is null
        defaultPlanInfoDataHisShouldNotBeFound("viewPermission.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByViewPermPersionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where viewPermPersion equals to DEFAULT_VIEW_PERM_PERSION
        defaultPlanInfoDataHisShouldBeFound("viewPermPersion.equals=" + DEFAULT_VIEW_PERM_PERSION);

        // Get all the planInfoDataHisList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoDataHisShouldNotBeFound("viewPermPersion.equals=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByViewPermPersionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where viewPermPersion in DEFAULT_VIEW_PERM_PERSION or UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoDataHisShouldBeFound("viewPermPersion.in=" + DEFAULT_VIEW_PERM_PERSION + "," + UPDATED_VIEW_PERM_PERSION);

        // Get all the planInfoDataHisList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoDataHisShouldNotBeFound("viewPermPersion.in=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByViewPermPersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where viewPermPersion is not null
        defaultPlanInfoDataHisShouldBeFound("viewPermPersion.specified=true");

        // Get all the planInfoDataHisList where viewPermPersion is null
        defaultPlanInfoDataHisShouldNotBeFound("viewPermPersion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultPlanInfoDataHisShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the planInfoDataHisList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoDataHisShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultPlanInfoDataHisShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the planInfoDataHisList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoDataHisShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where serialNumber is not null
        defaultPlanInfoDataHisShouldBeFound("serialNumber.specified=true");

        // Get all the planInfoDataHisList where serialNumber is null
        defaultPlanInfoDataHisShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVersionNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where versionNumber equals to DEFAULT_VERSION_NUMBER
        defaultPlanInfoDataHisShouldBeFound("versionNumber.equals=" + DEFAULT_VERSION_NUMBER);

        // Get all the planInfoDataHisList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoDataHisShouldNotBeFound("versionNumber.equals=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVersionNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where versionNumber in DEFAULT_VERSION_NUMBER or UPDATED_VERSION_NUMBER
        defaultPlanInfoDataHisShouldBeFound("versionNumber.in=" + DEFAULT_VERSION_NUMBER + "," + UPDATED_VERSION_NUMBER);

        // Get all the planInfoDataHisList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoDataHisShouldNotBeFound("versionNumber.in=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVersionNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where versionNumber is not null
        defaultPlanInfoDataHisShouldBeFound("versionNumber.specified=true");

        // Get all the planInfoDataHisList where versionNumber is null
        defaultPlanInfoDataHisShouldNotBeFound("versionNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByParaSourceStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where paraSourceString equals to DEFAULT_PARA_SOURCE_STRING
        defaultPlanInfoDataHisShouldBeFound("paraSourceString.equals=" + DEFAULT_PARA_SOURCE_STRING);

        // Get all the planInfoDataHisList where paraSourceString equals to UPDATED_PARA_SOURCE_STRING
        defaultPlanInfoDataHisShouldNotBeFound("paraSourceString.equals=" + UPDATED_PARA_SOURCE_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByParaSourceStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where paraSourceString in DEFAULT_PARA_SOURCE_STRING or UPDATED_PARA_SOURCE_STRING
        defaultPlanInfoDataHisShouldBeFound("paraSourceString.in=" + DEFAULT_PARA_SOURCE_STRING + "," + UPDATED_PARA_SOURCE_STRING);

        // Get all the planInfoDataHisList where paraSourceString equals to UPDATED_PARA_SOURCE_STRING
        defaultPlanInfoDataHisShouldNotBeFound("paraSourceString.in=" + UPDATED_PARA_SOURCE_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByParaSourceStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where paraSourceString is not null
        defaultPlanInfoDataHisShouldBeFound("paraSourceString.specified=true");

        // Get all the planInfoDataHisList where paraSourceString is null
        defaultPlanInfoDataHisShouldNotBeFound("paraSourceString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByFeatureKeywordIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where featureKeyword equals to DEFAULT_FEATURE_KEYWORD
        defaultPlanInfoDataHisShouldBeFound("featureKeyword.equals=" + DEFAULT_FEATURE_KEYWORD);

        // Get all the planInfoDataHisList where featureKeyword equals to UPDATED_FEATURE_KEYWORD
        defaultPlanInfoDataHisShouldNotBeFound("featureKeyword.equals=" + UPDATED_FEATURE_KEYWORD);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByFeatureKeywordIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where featureKeyword in DEFAULT_FEATURE_KEYWORD or UPDATED_FEATURE_KEYWORD
        defaultPlanInfoDataHisShouldBeFound("featureKeyword.in=" + DEFAULT_FEATURE_KEYWORD + "," + UPDATED_FEATURE_KEYWORD);

        // Get all the planInfoDataHisList where featureKeyword equals to UPDATED_FEATURE_KEYWORD
        defaultPlanInfoDataHisShouldNotBeFound("featureKeyword.in=" + UPDATED_FEATURE_KEYWORD);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByFeatureKeywordIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where featureKeyword is not null
        defaultPlanInfoDataHisShouldBeFound("featureKeyword.specified=true");

        // Get all the planInfoDataHisList where featureKeyword is null
        defaultPlanInfoDataHisShouldNotBeFound("featureKeyword.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisBySuggestionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where suggestion equals to DEFAULT_SUGGESTION
        defaultPlanInfoDataHisShouldBeFound("suggestion.equals=" + DEFAULT_SUGGESTION);

        // Get all the planInfoDataHisList where suggestion equals to UPDATED_SUGGESTION
        defaultPlanInfoDataHisShouldNotBeFound("suggestion.equals=" + UPDATED_SUGGESTION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisBySuggestionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where suggestion in DEFAULT_SUGGESTION or UPDATED_SUGGESTION
        defaultPlanInfoDataHisShouldBeFound("suggestion.in=" + DEFAULT_SUGGESTION + "," + UPDATED_SUGGESTION);

        // Get all the planInfoDataHisList where suggestion equals to UPDATED_SUGGESTION
        defaultPlanInfoDataHisShouldNotBeFound("suggestion.in=" + UPDATED_SUGGESTION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisBySuggestionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where suggestion is not null
        defaultPlanInfoDataHisShouldBeFound("suggestion.specified=true");

        // Get all the planInfoDataHisList where suggestion is null
        defaultPlanInfoDataHisShouldNotBeFound("suggestion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByReleaseScopeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where releaseScope equals to DEFAULT_RELEASE_SCOPE
        defaultPlanInfoDataHisShouldBeFound("releaseScope.equals=" + DEFAULT_RELEASE_SCOPE);

        // Get all the planInfoDataHisList where releaseScope equals to UPDATED_RELEASE_SCOPE
        defaultPlanInfoDataHisShouldNotBeFound("releaseScope.equals=" + UPDATED_RELEASE_SCOPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByReleaseScopeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where releaseScope in DEFAULT_RELEASE_SCOPE or UPDATED_RELEASE_SCOPE
        defaultPlanInfoDataHisShouldBeFound("releaseScope.in=" + DEFAULT_RELEASE_SCOPE + "," + UPDATED_RELEASE_SCOPE);

        // Get all the planInfoDataHisList where releaseScope equals to UPDATED_RELEASE_SCOPE
        defaultPlanInfoDataHisShouldNotBeFound("releaseScope.in=" + UPDATED_RELEASE_SCOPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByReleaseScopeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where releaseScope is not null
        defaultPlanInfoDataHisShouldBeFound("releaseScope.specified=true");

        // Get all the planInfoDataHisList where releaseScope is null
        defaultPlanInfoDataHisShouldNotBeFound("releaseScope.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByCurrentStepOrderIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where currentStepOrder equals to DEFAULT_CURRENT_STEP_ORDER
        defaultPlanInfoDataHisShouldBeFound("currentStepOrder.equals=" + DEFAULT_CURRENT_STEP_ORDER);

        // Get all the planInfoDataHisList where currentStepOrder equals to UPDATED_CURRENT_STEP_ORDER
        defaultPlanInfoDataHisShouldNotBeFound("currentStepOrder.equals=" + UPDATED_CURRENT_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByCurrentStepOrderIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where currentStepOrder in DEFAULT_CURRENT_STEP_ORDER or UPDATED_CURRENT_STEP_ORDER
        defaultPlanInfoDataHisShouldBeFound("currentStepOrder.in=" + DEFAULT_CURRENT_STEP_ORDER + "," + UPDATED_CURRENT_STEP_ORDER);

        // Get all the planInfoDataHisList where currentStepOrder equals to UPDATED_CURRENT_STEP_ORDER
        defaultPlanInfoDataHisShouldNotBeFound("currentStepOrder.in=" + UPDATED_CURRENT_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByCurrentStepOrderIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where currentStepOrder is not null
        defaultPlanInfoDataHisShouldBeFound("currentStepOrder.specified=true");

        // Get all the planInfoDataHisList where currentStepOrder is null
        defaultPlanInfoDataHisShouldNotBeFound("currentStepOrder.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByCurrentStepOrderIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where currentStepOrder greater than or equals to DEFAULT_CURRENT_STEP_ORDER
        defaultPlanInfoDataHisShouldBeFound("currentStepOrder.greaterOrEqualThan=" + DEFAULT_CURRENT_STEP_ORDER);

        // Get all the planInfoDataHisList where currentStepOrder greater than or equals to UPDATED_CURRENT_STEP_ORDER
        defaultPlanInfoDataHisShouldNotBeFound("currentStepOrder.greaterOrEqualThan=" + UPDATED_CURRENT_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataHisByCurrentStepOrderIsLessThanSomething() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        // Get all the planInfoDataHisList where currentStepOrder less than or equals to DEFAULT_CURRENT_STEP_ORDER
        defaultPlanInfoDataHisShouldNotBeFound("currentStepOrder.lessThan=" + DEFAULT_CURRENT_STEP_ORDER);

        // Get all the planInfoDataHisList where currentStepOrder less than or equals to UPDATED_CURRENT_STEP_ORDER
        defaultPlanInfoDataHisShouldBeFound("currentStepOrder.lessThan=" + UPDATED_CURRENT_STEP_ORDER);
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifyRecIsEqualToSomething() throws Exception {
        // Initialize the database
        VerifyRec verifyRec = VerifyRecResourceIntTest.createEntity(em);
        em.persist(verifyRec);
        em.flush();
        planInfoDataHis.setVerifyRec(verifyRec);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long verifyRecId = verifyRec.getId();

        // Get all the planInfoDataHisList where verifyRec equals to verifyRecId
        defaultPlanInfoDataHisShouldBeFound("verifyRecId.equals=" + verifyRecId);

        // Get all the planInfoDataHisList where verifyRec equals to verifyRecId + 1
        defaultPlanInfoDataHisShouldNotBeFound("verifyRecId.equals=" + (verifyRecId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByParaTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaType paraType = ParaTypeResourceIntTest.createEntity(em);
        em.persist(paraType);
        em.flush();
        planInfoDataHis.setParaType(paraType);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long paraTypeId = paraType.getId();

        // Get all the planInfoDataHisList where paraType equals to paraTypeId
        defaultPlanInfoDataHisShouldBeFound("paraTypeId.equals=" + paraTypeId);

        // Get all the planInfoDataHisList where paraType equals to paraTypeId + 1
        defaultPlanInfoDataHisShouldNotBeFound("paraTypeId.equals=" + (paraTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByParaClassIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaClass paraClass = ParaClassResourceIntTest.createEntity(em);
        em.persist(paraClass);
        em.flush();
        planInfoDataHis.setParaClass(paraClass);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long paraClassId = paraClass.getId();

        // Get all the planInfoDataHisList where paraClass equals to paraClassId
        defaultPlanInfoDataHisShouldBeFound("paraClassId.equals=" + paraClassId);

        // Get all the planInfoDataHisList where paraClass equals to paraClassId + 1
        defaultPlanInfoDataHisShouldNotBeFound("paraClassId.equals=" + (paraClassId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByParaCatIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaCat paraCat = ParaCatResourceIntTest.createEntity(em);
        em.persist(paraCat);
        em.flush();
        planInfoDataHis.setParaCat(paraCat);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long paraCatId = paraCat.getId();

        // Get all the planInfoDataHisList where paraCat equals to paraCatId
        defaultPlanInfoDataHisShouldBeFound("paraCatId.equals=" + paraCatId);

        // Get all the planInfoDataHisList where paraCat equals to paraCatId + 1
        defaultPlanInfoDataHisShouldNotBeFound("paraCatId.equals=" + (paraCatId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByParaStateIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaState paraState = ParaStateResourceIntTest.createEntity(em);
        em.persist(paraState);
        em.flush();
        planInfoDataHis.setParaState(paraState);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long paraStateId = paraState.getId();

        // Get all the planInfoDataHisList where paraState equals to paraStateId
        defaultPlanInfoDataHisShouldBeFound("paraStateId.equals=" + paraStateId);

        // Get all the planInfoDataHisList where paraState equals to paraStateId + 1
        defaultPlanInfoDataHisShouldNotBeFound("paraStateId.equals=" + (paraStateId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByParaSourceIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaSource paraSource = ParaSourceResourceIntTest.createEntity(em);
        em.persist(paraSource);
        em.flush();
        planInfoDataHis.setParaSource(paraSource);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long paraSourceId = paraSource.getId();

        // Get all the planInfoDataHisList where paraSource equals to paraSourceId
        defaultPlanInfoDataHisShouldBeFound("paraSourceId.equals=" + paraSourceId);

        // Get all the planInfoDataHisList where paraSource equals to paraSourceId + 1
        defaultPlanInfoDataHisShouldNotBeFound("paraSourceId.equals=" + (paraSourceId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByParaAttrIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaAttr paraAttr = ParaAttrResourceIntTest.createEntity(em);
        em.persist(paraAttr);
        em.flush();
        planInfoDataHis.setParaAttr(paraAttr);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long paraAttrId = paraAttr.getId();

        // Get all the planInfoDataHisList where paraAttr equals to paraAttrId
        defaultPlanInfoDataHisShouldBeFound("paraAttrId.equals=" + paraAttrId);

        // Get all the planInfoDataHisList where paraAttr equals to paraAttrId + 1
        defaultPlanInfoDataHisShouldNotBeFound("paraAttrId.equals=" + (paraAttrId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByParaPropIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaProp paraProp = ParaPropResourceIntTest.createEntity(em);
        em.persist(paraProp);
        em.flush();
        planInfoDataHis.setParaProp(paraProp);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long paraPropId = paraProp.getId();

        // Get all the planInfoDataHisList where paraProp equals to paraPropId
        defaultPlanInfoDataHisShouldBeFound("paraPropId.equals=" + paraPropId);

        // Get all the planInfoDataHisList where paraProp equals to paraPropId + 1
        defaultPlanInfoDataHisShouldNotBeFound("paraPropId.equals=" + (paraPropId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfoDataHis.setCreatedBy(createdBy);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long createdById = createdBy.getId();

        // Get all the planInfoDataHisList where createdBy equals to createdById
        defaultPlanInfoDataHisShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoDataHisList where createdBy equals to createdById + 1
        defaultPlanInfoDataHisShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByCreatedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep createdDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(createdDepBy);
        em.flush();
        planInfoDataHis.setCreatedDepBy(createdDepBy);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long createdDepById = createdDepBy.getId();

        // Get all the planInfoDataHisList where createdDepBy equals to createdDepById
        defaultPlanInfoDataHisShouldBeFound("createdDepById.equals=" + createdDepById);

        // Get all the planInfoDataHisList where createdDepBy equals to createdDepById + 1
        defaultPlanInfoDataHisShouldNotBeFound("createdDepById.equals=" + (createdDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByOwnerByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser ownerBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(ownerBy);
        em.flush();
        planInfoDataHis.setOwnerBy(ownerBy);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long ownerById = ownerBy.getId();

        // Get all the planInfoDataHisList where ownerBy equals to ownerById
        defaultPlanInfoDataHisShouldBeFound("ownerById.equals=" + ownerById);

        // Get all the planInfoDataHisList where ownerBy equals to ownerById + 1
        defaultPlanInfoDataHisShouldNotBeFound("ownerById.equals=" + (ownerById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByOwnerDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep ownerDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(ownerDepBy);
        em.flush();
        planInfoDataHis.setOwnerDepBy(ownerDepBy);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long ownerDepById = ownerDepBy.getId();

        // Get all the planInfoDataHisList where ownerDepBy equals to ownerDepById
        defaultPlanInfoDataHisShouldBeFound("ownerDepById.equals=" + ownerDepById);

        // Get all the planInfoDataHisList where ownerDepBy equals to ownerDepById + 1
        defaultPlanInfoDataHisShouldNotBeFound("ownerDepById.equals=" + (ownerDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfoDataHis.setModifiedBy(modifiedBy);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoDataHisList where modifiedBy equals to modifiedById
        defaultPlanInfoDataHisShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoDataHisList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoDataHisShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByModifiedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep modifiedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(modifiedDepBy);
        em.flush();
        planInfoDataHis.setModifiedDepBy(modifiedDepBy);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long modifiedDepById = modifiedDepBy.getId();

        // Get all the planInfoDataHisList where modifiedDepBy equals to modifiedDepById
        defaultPlanInfoDataHisShouldBeFound("modifiedDepById.equals=" + modifiedDepById);

        // Get all the planInfoDataHisList where modifiedDepBy equals to modifiedDepById + 1
        defaultPlanInfoDataHisShouldNotBeFound("modifiedDepById.equals=" + (modifiedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        planInfoDataHis.setVerifiedBy(verifiedBy);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long verifiedById = verifiedBy.getId();

        // Get all the planInfoDataHisList where verifiedBy equals to verifiedById
        defaultPlanInfoDataHisShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the planInfoDataHisList where verifiedBy equals to verifiedById + 1
        defaultPlanInfoDataHisShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByVerifiedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep verifiedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(verifiedDepBy);
        em.flush();
        planInfoDataHis.setVerifiedDepBy(verifiedDepBy);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long verifiedDepById = verifiedDepBy.getId();

        // Get all the planInfoDataHisList where verifiedDepBy equals to verifiedDepById
        defaultPlanInfoDataHisShouldBeFound("verifiedDepById.equals=" + verifiedDepById);

        // Get all the planInfoDataHisList where verifiedDepBy equals to verifiedDepById + 1
        defaultPlanInfoDataHisShouldNotBeFound("verifiedDepById.equals=" + (verifiedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByPublishedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser publishedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(publishedBy);
        em.flush();
        planInfoDataHis.setPublishedBy(publishedBy);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long publishedById = publishedBy.getId();

        // Get all the planInfoDataHisList where publishedBy equals to publishedById
        defaultPlanInfoDataHisShouldBeFound("publishedById.equals=" + publishedById);

        // Get all the planInfoDataHisList where publishedBy equals to publishedById + 1
        defaultPlanInfoDataHisShouldNotBeFound("publishedById.equals=" + (publishedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByPublishedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep publishedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(publishedDepBy);
        em.flush();
        planInfoDataHis.setPublishedDepBy(publishedDepBy);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long publishedDepById = publishedDepBy.getId();

        // Get all the planInfoDataHisList where publishedDepBy equals to publishedDepById
        defaultPlanInfoDataHisShouldBeFound("publishedDepById.equals=" + publishedDepById);

        // Get all the planInfoDataHisList where publishedDepBy equals to publishedDepById + 1
        defaultPlanInfoDataHisShouldNotBeFound("publishedDepById.equals=" + (publishedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoDataHis parent = PlanInfoDataHisResourceIntTest.createEntity(em);
        em.persist(parent);
        em.flush();
        planInfoDataHis.setParent(parent);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long parentId = parent.getId();

        // Get all the planInfoDataHisList where parent equals to parentId
        defaultPlanInfoDataHisShouldBeFound("parentId.equals=" + parentId);

        // Get all the planInfoDataHisList where parent equals to parentId + 1
        defaultPlanInfoDataHisShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataHisByPlanInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfo planInfo = PlanInfoResourceIntTest.createEntity(em);
        em.persist(planInfo);
        em.flush();
        planInfoDataHis.setPlanInfo(planInfo);
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        Long planInfoId = planInfo.getId();

        // Get all the planInfoDataHisList where planInfo equals to planInfoId
        defaultPlanInfoDataHisShouldBeFound("planInfoId.equals=" + planInfoId);

        // Get all the planInfoDataHisList where planInfo equals to planInfoId + 1
        defaultPlanInfoDataHisShouldNotBeFound("planInfoId.equals=" + (planInfoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoDataHisShouldBeFound(String filter) throws Exception {
        restPlanInfoDataHisMockMvc.perform(get("/api/plan-info-data-his?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoDataHis.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING.toString())))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING.toString())))
            .andExpect(jsonPath("$.[*].jsonString").value(hasItem(DEFAULT_JSON_STRING.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].refEvent").value(hasItem(DEFAULT_REF_EVENT.toString())))
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
            .andExpect(jsonPath("$.[*].paraSourceString").value(hasItem(DEFAULT_PARA_SOURCE_STRING.toString())))
            .andExpect(jsonPath("$.[*].featureKeyword").value(hasItem(DEFAULT_FEATURE_KEYWORD.toString())))
            .andExpect(jsonPath("$.[*].suggestion").value(hasItem(DEFAULT_SUGGESTION.toString())))
            .andExpect(jsonPath("$.[*].releaseScope").value(hasItem(DEFAULT_RELEASE_SCOPE.toString())))
            .andExpect(jsonPath("$.[*].currentStepOrder").value(hasItem(DEFAULT_CURRENT_STEP_ORDER)));

        // Check, that the count call also returns 1
        restPlanInfoDataHisMockMvc.perform(get("/api/plan-info-data-his/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoDataHisShouldNotBeFound(String filter) throws Exception {
        restPlanInfoDataHisMockMvc.perform(get("/api/plan-info-data-his?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoDataHisMockMvc.perform(get("/api/plan-info-data-his/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfoDataHis() throws Exception {
        // Get the planInfoDataHis
        restPlanInfoDataHisMockMvc.perform(get("/api/plan-info-data-his/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfoDataHis() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        int databaseSizeBeforeUpdate = planInfoDataHisRepository.findAll().size();

        // Update the planInfoDataHis
        PlanInfoDataHis updatedPlanInfoDataHis = planInfoDataHisRepository.findById(planInfoDataHis.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfoDataHis are not directly saved in db
        em.detach(updatedPlanInfoDataHis);
        updatedPlanInfoDataHis
            .name(UPDATED_NAME)
            .sortString(UPDATED_SORT_STRING)
            .descString(UPDATED_DESC_STRING)
            .jsonString(UPDATED_JSON_STRING)
            .remarks(UPDATED_REMARKS)
            .refEvent(UPDATED_REF_EVENT)
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
            .paraSourceString(UPDATED_PARA_SOURCE_STRING)
            .featureKeyword(UPDATED_FEATURE_KEYWORD)
            .suggestion(UPDATED_SUGGESTION)
            .releaseScope(UPDATED_RELEASE_SCOPE)
            .currentStepOrder(UPDATED_CURRENT_STEP_ORDER);
        PlanInfoDataHisDTO planInfoDataHisDTO = planInfoDataHisMapper.toDto(updatedPlanInfoDataHis);

        restPlanInfoDataHisMockMvc.perform(put("/api/plan-info-data-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataHisDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfoDataHis in the database
        List<PlanInfoDataHis> planInfoDataHisList = planInfoDataHisRepository.findAll();
        assertThat(planInfoDataHisList).hasSize(databaseSizeBeforeUpdate);
        PlanInfoDataHis testPlanInfoDataHis = planInfoDataHisList.get(planInfoDataHisList.size() - 1);
        assertThat(testPlanInfoDataHis.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfoDataHis.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfoDataHis.getDescString()).isEqualTo(UPDATED_DESC_STRING);
        assertThat(testPlanInfoDataHis.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfoDataHis.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfoDataHis.getRefEvent()).isEqualTo(UPDATED_REF_EVENT);
        assertThat(testPlanInfoDataHis.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfoDataHis.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfoDataHis.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataHis.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfoDataHis.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfoDataHis.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfoDataHis.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoDataHis.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoDataHis.isUsingFlag()).isEqualTo(UPDATED_USING_FLAG);
        assertThat(testPlanInfoDataHis.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfoDataHis.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfoDataHis.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfoDataHis.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfoDataHis.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPlanInfoDataHis.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);
        assertThat(testPlanInfoDataHis.isVerifyNeed()).isEqualTo(UPDATED_VERIFY_NEED);
        assertThat(testPlanInfoDataHis.getVerifyOpinion()).isEqualTo(UPDATED_VERIFY_OPINION);
        assertThat(testPlanInfoDataHis.getViewCount()).isEqualTo(UPDATED_VIEW_COUNT);
        assertThat(testPlanInfoDataHis.getViewPermission()).isEqualTo(UPDATED_VIEW_PERMISSION);
        assertThat(testPlanInfoDataHis.getViewPermPersion()).isEqualTo(UPDATED_VIEW_PERM_PERSION);
        assertThat(testPlanInfoDataHis.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testPlanInfoDataHis.getVersionNumber()).isEqualTo(UPDATED_VERSION_NUMBER);
        assertThat(testPlanInfoDataHis.getParaSourceString()).isEqualTo(UPDATED_PARA_SOURCE_STRING);
        assertThat(testPlanInfoDataHis.getFeatureKeyword()).isEqualTo(UPDATED_FEATURE_KEYWORD);
        assertThat(testPlanInfoDataHis.getSuggestion()).isEqualTo(UPDATED_SUGGESTION);
        assertThat(testPlanInfoDataHis.getReleaseScope()).isEqualTo(UPDATED_RELEASE_SCOPE);
        assertThat(testPlanInfoDataHis.getCurrentStepOrder()).isEqualTo(UPDATED_CURRENT_STEP_ORDER);

        // Validate the PlanInfoDataHis in Elasticsearch
        verify(mockPlanInfoDataHisSearchRepository, times(1)).save(testPlanInfoDataHis);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfoDataHis() throws Exception {
        int databaseSizeBeforeUpdate = planInfoDataHisRepository.findAll().size();

        // Create the PlanInfoDataHis
        PlanInfoDataHisDTO planInfoDataHisDTO = planInfoDataHisMapper.toDto(planInfoDataHis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoDataHisMockMvc.perform(put("/api/plan-info-data-his")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataHisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoDataHis in the database
        List<PlanInfoDataHis> planInfoDataHisList = planInfoDataHisRepository.findAll();
        assertThat(planInfoDataHisList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfoDataHis in Elasticsearch
        verify(mockPlanInfoDataHisSearchRepository, times(0)).save(planInfoDataHis);
    }

    @Test
    @Transactional
    public void deletePlanInfoDataHis() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);

        int databaseSizeBeforeDelete = planInfoDataHisRepository.findAll().size();

        // Get the planInfoDataHis
        restPlanInfoDataHisMockMvc.perform(delete("/api/plan-info-data-his/{id}", planInfoDataHis.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfoDataHis> planInfoDataHisList = planInfoDataHisRepository.findAll();
        assertThat(planInfoDataHisList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfoDataHis in Elasticsearch
        verify(mockPlanInfoDataHisSearchRepository, times(1)).deleteById(planInfoDataHis.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfoDataHis() throws Exception {
        // Initialize the database
        planInfoDataHisRepository.saveAndFlush(planInfoDataHis);
        when(mockPlanInfoDataHisSearchRepository.search(queryStringQuery("id:" + planInfoDataHis.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfoDataHis), PageRequest.of(0, 1), 1));
        // Search the planInfoDataHis
        restPlanInfoDataHisMockMvc.perform(get("/api/_search/plan-info-data-his?query=id:" + planInfoDataHis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoDataHis.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING)))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING)))
            .andExpect(jsonPath("$.[*].jsonString").value(hasItem(DEFAULT_JSON_STRING)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].refEvent").value(hasItem(DEFAULT_REF_EVENT)))
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
            .andExpect(jsonPath("$.[*].paraSourceString").value(hasItem(DEFAULT_PARA_SOURCE_STRING)))
            .andExpect(jsonPath("$.[*].featureKeyword").value(hasItem(DEFAULT_FEATURE_KEYWORD)))
            .andExpect(jsonPath("$.[*].suggestion").value(hasItem(DEFAULT_SUGGESTION)))
            .andExpect(jsonPath("$.[*].releaseScope").value(hasItem(DEFAULT_RELEASE_SCOPE)))
            .andExpect(jsonPath("$.[*].currentStepOrder").value(hasItem(DEFAULT_CURRENT_STEP_ORDER)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoDataHis.class);
        PlanInfoDataHis planInfoDataHis1 = new PlanInfoDataHis();
        planInfoDataHis1.setId(1L);
        PlanInfoDataHis planInfoDataHis2 = new PlanInfoDataHis();
        planInfoDataHis2.setId(planInfoDataHis1.getId());
        assertThat(planInfoDataHis1).isEqualTo(planInfoDataHis2);
        planInfoDataHis2.setId(2L);
        assertThat(planInfoDataHis1).isNotEqualTo(planInfoDataHis2);
        planInfoDataHis1.setId(null);
        assertThat(planInfoDataHis1).isNotEqualTo(planInfoDataHis2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoDataHisDTO.class);
        PlanInfoDataHisDTO planInfoDataHisDTO1 = new PlanInfoDataHisDTO();
        planInfoDataHisDTO1.setId(1L);
        PlanInfoDataHisDTO planInfoDataHisDTO2 = new PlanInfoDataHisDTO();
        assertThat(planInfoDataHisDTO1).isNotEqualTo(planInfoDataHisDTO2);
        planInfoDataHisDTO2.setId(planInfoDataHisDTO1.getId());
        assertThat(planInfoDataHisDTO1).isEqualTo(planInfoDataHisDTO2);
        planInfoDataHisDTO2.setId(2L);
        assertThat(planInfoDataHisDTO1).isNotEqualTo(planInfoDataHisDTO2);
        planInfoDataHisDTO1.setId(null);
        assertThat(planInfoDataHisDTO1).isNotEqualTo(planInfoDataHisDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoDataHisMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoDataHisMapper.fromId(null)).isNull();
    }
}
