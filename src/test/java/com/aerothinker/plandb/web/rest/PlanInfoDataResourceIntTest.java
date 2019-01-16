package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfoData;
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
import com.aerothinker.plandb.domain.PlanInfoData;
import com.aerothinker.plandb.domain.PlanInfo;
import com.aerothinker.plandb.repository.PlanInfoDataRepository;
import com.aerothinker.plandb.repository.search.PlanInfoDataSearchRepository;
import com.aerothinker.plandb.service.PlanInfoDataService;
import com.aerothinker.plandb.service.dto.PlanInfoDataDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoDataMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoDataCriteria;
import com.aerothinker.plandb.service.PlanInfoDataQueryService;

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
 * Test class for the PlanInfoDataResource REST controller.
 *
 * @see PlanInfoDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoDataResourceIntTest {

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
    private PlanInfoDataRepository planInfoDataRepository;

    @Autowired
    private PlanInfoDataMapper planInfoDataMapper;

    @Autowired
    private PlanInfoDataService planInfoDataService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoDataSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoDataSearchRepository mockPlanInfoDataSearchRepository;

    @Autowired
    private PlanInfoDataQueryService planInfoDataQueryService;

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

    private MockMvc restPlanInfoDataMockMvc;

    private PlanInfoData planInfoData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoDataResource planInfoDataResource = new PlanInfoDataResource(planInfoDataService, planInfoDataQueryService);
        this.restPlanInfoDataMockMvc = MockMvcBuilders.standaloneSetup(planInfoDataResource)
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
    public static PlanInfoData createEntity(EntityManager em) {
        PlanInfoData planInfoData = new PlanInfoData()
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
        return planInfoData;
    }

    @Before
    public void initTest() {
        planInfoData = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfoData() throws Exception {
        int databaseSizeBeforeCreate = planInfoDataRepository.findAll().size();

        // Create the PlanInfoData
        PlanInfoDataDTO planInfoDataDTO = planInfoDataMapper.toDto(planInfoData);
        restPlanInfoDataMockMvc.perform(post("/api/plan-info-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfoData in the database
        List<PlanInfoData> planInfoDataList = planInfoDataRepository.findAll();
        assertThat(planInfoDataList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfoData testPlanInfoData = planInfoDataList.get(planInfoDataList.size() - 1);
        assertThat(testPlanInfoData.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfoData.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfoData.getDescString()).isEqualTo(DEFAULT_DESC_STRING);
        assertThat(testPlanInfoData.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfoData.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfoData.getRefEvent()).isEqualTo(DEFAULT_REF_EVENT);
        assertThat(testPlanInfoData.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfoData.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfoData.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoData.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfoData.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfoData.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfoData.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoData.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoData.isUsingFlag()).isEqualTo(DEFAULT_USING_FLAG);
        assertThat(testPlanInfoData.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfoData.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfoData.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfoData.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfoData.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPlanInfoData.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);
        assertThat(testPlanInfoData.isVerifyNeed()).isEqualTo(DEFAULT_VERIFY_NEED);
        assertThat(testPlanInfoData.getVerifyOpinion()).isEqualTo(DEFAULT_VERIFY_OPINION);
        assertThat(testPlanInfoData.getViewCount()).isEqualTo(DEFAULT_VIEW_COUNT);
        assertThat(testPlanInfoData.getViewPermission()).isEqualTo(DEFAULT_VIEW_PERMISSION);
        assertThat(testPlanInfoData.getViewPermPersion()).isEqualTo(DEFAULT_VIEW_PERM_PERSION);
        assertThat(testPlanInfoData.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testPlanInfoData.getVersionNumber()).isEqualTo(DEFAULT_VERSION_NUMBER);
        assertThat(testPlanInfoData.getParaSourceString()).isEqualTo(DEFAULT_PARA_SOURCE_STRING);
        assertThat(testPlanInfoData.getFeatureKeyword()).isEqualTo(DEFAULT_FEATURE_KEYWORD);
        assertThat(testPlanInfoData.getSuggestion()).isEqualTo(DEFAULT_SUGGESTION);
        assertThat(testPlanInfoData.getReleaseScope()).isEqualTo(DEFAULT_RELEASE_SCOPE);
        assertThat(testPlanInfoData.getCurrentStepOrder()).isEqualTo(DEFAULT_CURRENT_STEP_ORDER);

        // Validate the PlanInfoData in Elasticsearch
        verify(mockPlanInfoDataSearchRepository, times(1)).save(testPlanInfoData);
    }

    @Test
    @Transactional
    public void createPlanInfoDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoDataRepository.findAll().size();

        // Create the PlanInfoData with an existing ID
        planInfoData.setId(1L);
        PlanInfoDataDTO planInfoDataDTO = planInfoDataMapper.toDto(planInfoData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoDataMockMvc.perform(post("/api/plan-info-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoData in the database
        List<PlanInfoData> planInfoDataList = planInfoDataRepository.findAll();
        assertThat(planInfoDataList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfoData in Elasticsearch
        verify(mockPlanInfoDataSearchRepository, times(0)).save(planInfoData);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoDataRepository.findAll().size();
        // set the field null
        planInfoData.setName(null);

        // Create the PlanInfoData, which fails.
        PlanInfoDataDTO planInfoDataDTO = planInfoDataMapper.toDto(planInfoData);

        restPlanInfoDataMockMvc.perform(post("/api/plan-info-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfoData> planInfoDataList = planInfoDataRepository.findAll();
        assertThat(planInfoDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfoData() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList
        restPlanInfoDataMockMvc.perform(get("/api/plan-info-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoData.getId().intValue())))
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
    public void getPlanInfoData() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get the planInfoData
        restPlanInfoDataMockMvc.perform(get("/api/plan-info-data/{id}", planInfoData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfoData.getId().intValue()))
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
    public void getAllPlanInfoDataByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where name equals to DEFAULT_NAME
        defaultPlanInfoDataShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoDataList where name equals to UPDATED_NAME
        defaultPlanInfoDataShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoDataShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoDataList where name equals to UPDATED_NAME
        defaultPlanInfoDataShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where name is not null
        defaultPlanInfoDataShouldBeFound("name.specified=true");

        // Get all the planInfoDataList where name is null
        defaultPlanInfoDataShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoDataShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoDataList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoDataShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoDataShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoDataList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoDataShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where sortString is not null
        defaultPlanInfoDataShouldBeFound("sortString.specified=true");

        // Get all the planInfoDataList where sortString is null
        defaultPlanInfoDataShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByDescStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where descString equals to DEFAULT_DESC_STRING
        defaultPlanInfoDataShouldBeFound("descString.equals=" + DEFAULT_DESC_STRING);

        // Get all the planInfoDataList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoDataShouldNotBeFound("descString.equals=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByDescStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where descString in DEFAULT_DESC_STRING or UPDATED_DESC_STRING
        defaultPlanInfoDataShouldBeFound("descString.in=" + DEFAULT_DESC_STRING + "," + UPDATED_DESC_STRING);

        // Get all the planInfoDataList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoDataShouldNotBeFound("descString.in=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByDescStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where descString is not null
        defaultPlanInfoDataShouldBeFound("descString.specified=true");

        // Get all the planInfoDataList where descString is null
        defaultPlanInfoDataShouldNotBeFound("descString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoDataShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoDataList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoDataShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoDataShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoDataList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoDataShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where jsonString is not null
        defaultPlanInfoDataShouldBeFound("jsonString.specified=true");

        // Get all the planInfoDataList where jsonString is null
        defaultPlanInfoDataShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoDataShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoDataList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoDataShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoDataShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoDataList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoDataShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where remarks is not null
        defaultPlanInfoDataShouldBeFound("remarks.specified=true");

        // Get all the planInfoDataList where remarks is null
        defaultPlanInfoDataShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByRefEventIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where refEvent equals to DEFAULT_REF_EVENT
        defaultPlanInfoDataShouldBeFound("refEvent.equals=" + DEFAULT_REF_EVENT);

        // Get all the planInfoDataList where refEvent equals to UPDATED_REF_EVENT
        defaultPlanInfoDataShouldNotBeFound("refEvent.equals=" + UPDATED_REF_EVENT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByRefEventIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where refEvent in DEFAULT_REF_EVENT or UPDATED_REF_EVENT
        defaultPlanInfoDataShouldBeFound("refEvent.in=" + DEFAULT_REF_EVENT + "," + UPDATED_REF_EVENT);

        // Get all the planInfoDataList where refEvent equals to UPDATED_REF_EVENT
        defaultPlanInfoDataShouldNotBeFound("refEvent.in=" + UPDATED_REF_EVENT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByRefEventIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where refEvent is not null
        defaultPlanInfoDataShouldBeFound("refEvent.specified=true");

        // Get all the planInfoDataList where refEvent is null
        defaultPlanInfoDataShouldNotBeFound("refEvent.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoDataShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoDataList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoDataList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoDataShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where attachmentPath is not null
        defaultPlanInfoDataShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoDataList where attachmentPath is null
        defaultPlanInfoDataShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoDataShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoDataList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoDataList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoDataShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where attachmentName is not null
        defaultPlanInfoDataShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoDataList where attachmentName is null
        defaultPlanInfoDataShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoDataShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoDataList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoDataList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoDataShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where imageBlobName is not null
        defaultPlanInfoDataShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoDataList where imageBlobName is null
        defaultPlanInfoDataShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByUsingFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where usingFlag equals to DEFAULT_USING_FLAG
        defaultPlanInfoDataShouldBeFound("usingFlag.equals=" + DEFAULT_USING_FLAG);

        // Get all the planInfoDataList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoDataShouldNotBeFound("usingFlag.equals=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByUsingFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where usingFlag in DEFAULT_USING_FLAG or UPDATED_USING_FLAG
        defaultPlanInfoDataShouldBeFound("usingFlag.in=" + DEFAULT_USING_FLAG + "," + UPDATED_USING_FLAG);

        // Get all the planInfoDataList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoDataShouldNotBeFound("usingFlag.in=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByUsingFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where usingFlag is not null
        defaultPlanInfoDataShouldBeFound("usingFlag.specified=true");

        // Get all the planInfoDataList where usingFlag is null
        defaultPlanInfoDataShouldNotBeFound("usingFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoDataShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoDataList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoDataShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoDataShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoDataList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoDataShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where validType is not null
        defaultPlanInfoDataShouldBeFound("validType.specified=true");

        // Get all the planInfoDataList where validType is null
        defaultPlanInfoDataShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoDataShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoDataList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoDataShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoDataShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoDataList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoDataShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where validBegin is not null
        defaultPlanInfoDataShouldBeFound("validBegin.specified=true");

        // Get all the planInfoDataList where validBegin is null
        defaultPlanInfoDataShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoDataShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoDataList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoDataShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoDataShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoDataList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoDataShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where validEnd is not null
        defaultPlanInfoDataShouldBeFound("validEnd.specified=true");

        // Get all the planInfoDataList where validEnd is null
        defaultPlanInfoDataShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoDataShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoDataList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoDataShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoDataShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoDataList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoDataShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where insertTime is not null
        defaultPlanInfoDataShouldBeFound("insertTime.specified=true");

        // Get all the planInfoDataList where insertTime is null
        defaultPlanInfoDataShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoDataShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoDataList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoDataShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoDataShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoDataList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoDataShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where updateTime is not null
        defaultPlanInfoDataShouldBeFound("updateTime.specified=true");

        // Get all the planInfoDataList where updateTime is null
        defaultPlanInfoDataShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultPlanInfoDataShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the planInfoDataList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoDataShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultPlanInfoDataShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the planInfoDataList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoDataShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where verifyTime is not null
        defaultPlanInfoDataShouldBeFound("verifyTime.specified=true");

        // Get all the planInfoDataList where verifyTime is null
        defaultPlanInfoDataShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifyNeedIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where verifyNeed equals to DEFAULT_VERIFY_NEED
        defaultPlanInfoDataShouldBeFound("verifyNeed.equals=" + DEFAULT_VERIFY_NEED);

        // Get all the planInfoDataList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoDataShouldNotBeFound("verifyNeed.equals=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifyNeedIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where verifyNeed in DEFAULT_VERIFY_NEED or UPDATED_VERIFY_NEED
        defaultPlanInfoDataShouldBeFound("verifyNeed.in=" + DEFAULT_VERIFY_NEED + "," + UPDATED_VERIFY_NEED);

        // Get all the planInfoDataList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoDataShouldNotBeFound("verifyNeed.in=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifyNeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where verifyNeed is not null
        defaultPlanInfoDataShouldBeFound("verifyNeed.specified=true");

        // Get all the planInfoDataList where verifyNeed is null
        defaultPlanInfoDataShouldNotBeFound("verifyNeed.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifyOpinionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where verifyOpinion equals to DEFAULT_VERIFY_OPINION
        defaultPlanInfoDataShouldBeFound("verifyOpinion.equals=" + DEFAULT_VERIFY_OPINION);

        // Get all the planInfoDataList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoDataShouldNotBeFound("verifyOpinion.equals=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifyOpinionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where verifyOpinion in DEFAULT_VERIFY_OPINION or UPDATED_VERIFY_OPINION
        defaultPlanInfoDataShouldBeFound("verifyOpinion.in=" + DEFAULT_VERIFY_OPINION + "," + UPDATED_VERIFY_OPINION);

        // Get all the planInfoDataList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoDataShouldNotBeFound("verifyOpinion.in=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifyOpinionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where verifyOpinion is not null
        defaultPlanInfoDataShouldBeFound("verifyOpinion.specified=true");

        // Get all the planInfoDataList where verifyOpinion is null
        defaultPlanInfoDataShouldNotBeFound("verifyOpinion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByViewCountIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where viewCount equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoDataShouldBeFound("viewCount.equals=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoDataList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoDataShouldNotBeFound("viewCount.equals=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByViewCountIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where viewCount in DEFAULT_VIEW_COUNT or UPDATED_VIEW_COUNT
        defaultPlanInfoDataShouldBeFound("viewCount.in=" + DEFAULT_VIEW_COUNT + "," + UPDATED_VIEW_COUNT);

        // Get all the planInfoDataList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoDataShouldNotBeFound("viewCount.in=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByViewCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where viewCount is not null
        defaultPlanInfoDataShouldBeFound("viewCount.specified=true");

        // Get all the planInfoDataList where viewCount is null
        defaultPlanInfoDataShouldNotBeFound("viewCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByViewCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where viewCount greater than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoDataShouldBeFound("viewCount.greaterOrEqualThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoDataList where viewCount greater than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoDataShouldNotBeFound("viewCount.greaterOrEqualThan=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByViewCountIsLessThanSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where viewCount less than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoDataShouldNotBeFound("viewCount.lessThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoDataList where viewCount less than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoDataShouldBeFound("viewCount.lessThan=" + UPDATED_VIEW_COUNT);
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByViewPermissionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where viewPermission equals to DEFAULT_VIEW_PERMISSION
        defaultPlanInfoDataShouldBeFound("viewPermission.equals=" + DEFAULT_VIEW_PERMISSION);

        // Get all the planInfoDataList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoDataShouldNotBeFound("viewPermission.equals=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByViewPermissionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where viewPermission in DEFAULT_VIEW_PERMISSION or UPDATED_VIEW_PERMISSION
        defaultPlanInfoDataShouldBeFound("viewPermission.in=" + DEFAULT_VIEW_PERMISSION + "," + UPDATED_VIEW_PERMISSION);

        // Get all the planInfoDataList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoDataShouldNotBeFound("viewPermission.in=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByViewPermissionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where viewPermission is not null
        defaultPlanInfoDataShouldBeFound("viewPermission.specified=true");

        // Get all the planInfoDataList where viewPermission is null
        defaultPlanInfoDataShouldNotBeFound("viewPermission.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByViewPermPersionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where viewPermPersion equals to DEFAULT_VIEW_PERM_PERSION
        defaultPlanInfoDataShouldBeFound("viewPermPersion.equals=" + DEFAULT_VIEW_PERM_PERSION);

        // Get all the planInfoDataList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoDataShouldNotBeFound("viewPermPersion.equals=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByViewPermPersionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where viewPermPersion in DEFAULT_VIEW_PERM_PERSION or UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoDataShouldBeFound("viewPermPersion.in=" + DEFAULT_VIEW_PERM_PERSION + "," + UPDATED_VIEW_PERM_PERSION);

        // Get all the planInfoDataList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoDataShouldNotBeFound("viewPermPersion.in=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByViewPermPersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where viewPermPersion is not null
        defaultPlanInfoDataShouldBeFound("viewPermPersion.specified=true");

        // Get all the planInfoDataList where viewPermPersion is null
        defaultPlanInfoDataShouldNotBeFound("viewPermPersion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultPlanInfoDataShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the planInfoDataList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoDataShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultPlanInfoDataShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the planInfoDataList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoDataShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where serialNumber is not null
        defaultPlanInfoDataShouldBeFound("serialNumber.specified=true");

        // Get all the planInfoDataList where serialNumber is null
        defaultPlanInfoDataShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVersionNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where versionNumber equals to DEFAULT_VERSION_NUMBER
        defaultPlanInfoDataShouldBeFound("versionNumber.equals=" + DEFAULT_VERSION_NUMBER);

        // Get all the planInfoDataList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoDataShouldNotBeFound("versionNumber.equals=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVersionNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where versionNumber in DEFAULT_VERSION_NUMBER or UPDATED_VERSION_NUMBER
        defaultPlanInfoDataShouldBeFound("versionNumber.in=" + DEFAULT_VERSION_NUMBER + "," + UPDATED_VERSION_NUMBER);

        // Get all the planInfoDataList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoDataShouldNotBeFound("versionNumber.in=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByVersionNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where versionNumber is not null
        defaultPlanInfoDataShouldBeFound("versionNumber.specified=true");

        // Get all the planInfoDataList where versionNumber is null
        defaultPlanInfoDataShouldNotBeFound("versionNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByParaSourceStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where paraSourceString equals to DEFAULT_PARA_SOURCE_STRING
        defaultPlanInfoDataShouldBeFound("paraSourceString.equals=" + DEFAULT_PARA_SOURCE_STRING);

        // Get all the planInfoDataList where paraSourceString equals to UPDATED_PARA_SOURCE_STRING
        defaultPlanInfoDataShouldNotBeFound("paraSourceString.equals=" + UPDATED_PARA_SOURCE_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByParaSourceStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where paraSourceString in DEFAULT_PARA_SOURCE_STRING or UPDATED_PARA_SOURCE_STRING
        defaultPlanInfoDataShouldBeFound("paraSourceString.in=" + DEFAULT_PARA_SOURCE_STRING + "," + UPDATED_PARA_SOURCE_STRING);

        // Get all the planInfoDataList where paraSourceString equals to UPDATED_PARA_SOURCE_STRING
        defaultPlanInfoDataShouldNotBeFound("paraSourceString.in=" + UPDATED_PARA_SOURCE_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByParaSourceStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where paraSourceString is not null
        defaultPlanInfoDataShouldBeFound("paraSourceString.specified=true");

        // Get all the planInfoDataList where paraSourceString is null
        defaultPlanInfoDataShouldNotBeFound("paraSourceString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByFeatureKeywordIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where featureKeyword equals to DEFAULT_FEATURE_KEYWORD
        defaultPlanInfoDataShouldBeFound("featureKeyword.equals=" + DEFAULT_FEATURE_KEYWORD);

        // Get all the planInfoDataList where featureKeyword equals to UPDATED_FEATURE_KEYWORD
        defaultPlanInfoDataShouldNotBeFound("featureKeyword.equals=" + UPDATED_FEATURE_KEYWORD);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByFeatureKeywordIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where featureKeyword in DEFAULT_FEATURE_KEYWORD or UPDATED_FEATURE_KEYWORD
        defaultPlanInfoDataShouldBeFound("featureKeyword.in=" + DEFAULT_FEATURE_KEYWORD + "," + UPDATED_FEATURE_KEYWORD);

        // Get all the planInfoDataList where featureKeyword equals to UPDATED_FEATURE_KEYWORD
        defaultPlanInfoDataShouldNotBeFound("featureKeyword.in=" + UPDATED_FEATURE_KEYWORD);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByFeatureKeywordIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where featureKeyword is not null
        defaultPlanInfoDataShouldBeFound("featureKeyword.specified=true");

        // Get all the planInfoDataList where featureKeyword is null
        defaultPlanInfoDataShouldNotBeFound("featureKeyword.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataBySuggestionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where suggestion equals to DEFAULT_SUGGESTION
        defaultPlanInfoDataShouldBeFound("suggestion.equals=" + DEFAULT_SUGGESTION);

        // Get all the planInfoDataList where suggestion equals to UPDATED_SUGGESTION
        defaultPlanInfoDataShouldNotBeFound("suggestion.equals=" + UPDATED_SUGGESTION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataBySuggestionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where suggestion in DEFAULT_SUGGESTION or UPDATED_SUGGESTION
        defaultPlanInfoDataShouldBeFound("suggestion.in=" + DEFAULT_SUGGESTION + "," + UPDATED_SUGGESTION);

        // Get all the planInfoDataList where suggestion equals to UPDATED_SUGGESTION
        defaultPlanInfoDataShouldNotBeFound("suggestion.in=" + UPDATED_SUGGESTION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataBySuggestionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where suggestion is not null
        defaultPlanInfoDataShouldBeFound("suggestion.specified=true");

        // Get all the planInfoDataList where suggestion is null
        defaultPlanInfoDataShouldNotBeFound("suggestion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByReleaseScopeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where releaseScope equals to DEFAULT_RELEASE_SCOPE
        defaultPlanInfoDataShouldBeFound("releaseScope.equals=" + DEFAULT_RELEASE_SCOPE);

        // Get all the planInfoDataList where releaseScope equals to UPDATED_RELEASE_SCOPE
        defaultPlanInfoDataShouldNotBeFound("releaseScope.equals=" + UPDATED_RELEASE_SCOPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByReleaseScopeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where releaseScope in DEFAULT_RELEASE_SCOPE or UPDATED_RELEASE_SCOPE
        defaultPlanInfoDataShouldBeFound("releaseScope.in=" + DEFAULT_RELEASE_SCOPE + "," + UPDATED_RELEASE_SCOPE);

        // Get all the planInfoDataList where releaseScope equals to UPDATED_RELEASE_SCOPE
        defaultPlanInfoDataShouldNotBeFound("releaseScope.in=" + UPDATED_RELEASE_SCOPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByReleaseScopeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where releaseScope is not null
        defaultPlanInfoDataShouldBeFound("releaseScope.specified=true");

        // Get all the planInfoDataList where releaseScope is null
        defaultPlanInfoDataShouldNotBeFound("releaseScope.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByCurrentStepOrderIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where currentStepOrder equals to DEFAULT_CURRENT_STEP_ORDER
        defaultPlanInfoDataShouldBeFound("currentStepOrder.equals=" + DEFAULT_CURRENT_STEP_ORDER);

        // Get all the planInfoDataList where currentStepOrder equals to UPDATED_CURRENT_STEP_ORDER
        defaultPlanInfoDataShouldNotBeFound("currentStepOrder.equals=" + UPDATED_CURRENT_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByCurrentStepOrderIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where currentStepOrder in DEFAULT_CURRENT_STEP_ORDER or UPDATED_CURRENT_STEP_ORDER
        defaultPlanInfoDataShouldBeFound("currentStepOrder.in=" + DEFAULT_CURRENT_STEP_ORDER + "," + UPDATED_CURRENT_STEP_ORDER);

        // Get all the planInfoDataList where currentStepOrder equals to UPDATED_CURRENT_STEP_ORDER
        defaultPlanInfoDataShouldNotBeFound("currentStepOrder.in=" + UPDATED_CURRENT_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByCurrentStepOrderIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where currentStepOrder is not null
        defaultPlanInfoDataShouldBeFound("currentStepOrder.specified=true");

        // Get all the planInfoDataList where currentStepOrder is null
        defaultPlanInfoDataShouldNotBeFound("currentStepOrder.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByCurrentStepOrderIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where currentStepOrder greater than or equals to DEFAULT_CURRENT_STEP_ORDER
        defaultPlanInfoDataShouldBeFound("currentStepOrder.greaterOrEqualThan=" + DEFAULT_CURRENT_STEP_ORDER);

        // Get all the planInfoDataList where currentStepOrder greater than or equals to UPDATED_CURRENT_STEP_ORDER
        defaultPlanInfoDataShouldNotBeFound("currentStepOrder.greaterOrEqualThan=" + UPDATED_CURRENT_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoDataByCurrentStepOrderIsLessThanSomething() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        // Get all the planInfoDataList where currentStepOrder less than or equals to DEFAULT_CURRENT_STEP_ORDER
        defaultPlanInfoDataShouldNotBeFound("currentStepOrder.lessThan=" + DEFAULT_CURRENT_STEP_ORDER);

        // Get all the planInfoDataList where currentStepOrder less than or equals to UPDATED_CURRENT_STEP_ORDER
        defaultPlanInfoDataShouldBeFound("currentStepOrder.lessThan=" + UPDATED_CURRENT_STEP_ORDER);
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifyRecIsEqualToSomething() throws Exception {
        // Initialize the database
        VerifyRec verifyRec = VerifyRecResourceIntTest.createEntity(em);
        em.persist(verifyRec);
        em.flush();
        planInfoData.setVerifyRec(verifyRec);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long verifyRecId = verifyRec.getId();

        // Get all the planInfoDataList where verifyRec equals to verifyRecId
        defaultPlanInfoDataShouldBeFound("verifyRecId.equals=" + verifyRecId);

        // Get all the planInfoDataList where verifyRec equals to verifyRecId + 1
        defaultPlanInfoDataShouldNotBeFound("verifyRecId.equals=" + (verifyRecId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByParaTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaType paraType = ParaTypeResourceIntTest.createEntity(em);
        em.persist(paraType);
        em.flush();
        planInfoData.setParaType(paraType);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long paraTypeId = paraType.getId();

        // Get all the planInfoDataList where paraType equals to paraTypeId
        defaultPlanInfoDataShouldBeFound("paraTypeId.equals=" + paraTypeId);

        // Get all the planInfoDataList where paraType equals to paraTypeId + 1
        defaultPlanInfoDataShouldNotBeFound("paraTypeId.equals=" + (paraTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByParaClassIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaClass paraClass = ParaClassResourceIntTest.createEntity(em);
        em.persist(paraClass);
        em.flush();
        planInfoData.setParaClass(paraClass);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long paraClassId = paraClass.getId();

        // Get all the planInfoDataList where paraClass equals to paraClassId
        defaultPlanInfoDataShouldBeFound("paraClassId.equals=" + paraClassId);

        // Get all the planInfoDataList where paraClass equals to paraClassId + 1
        defaultPlanInfoDataShouldNotBeFound("paraClassId.equals=" + (paraClassId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByParaCatIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaCat paraCat = ParaCatResourceIntTest.createEntity(em);
        em.persist(paraCat);
        em.flush();
        planInfoData.setParaCat(paraCat);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long paraCatId = paraCat.getId();

        // Get all the planInfoDataList where paraCat equals to paraCatId
        defaultPlanInfoDataShouldBeFound("paraCatId.equals=" + paraCatId);

        // Get all the planInfoDataList where paraCat equals to paraCatId + 1
        defaultPlanInfoDataShouldNotBeFound("paraCatId.equals=" + (paraCatId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByParaStateIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaState paraState = ParaStateResourceIntTest.createEntity(em);
        em.persist(paraState);
        em.flush();
        planInfoData.setParaState(paraState);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long paraStateId = paraState.getId();

        // Get all the planInfoDataList where paraState equals to paraStateId
        defaultPlanInfoDataShouldBeFound("paraStateId.equals=" + paraStateId);

        // Get all the planInfoDataList where paraState equals to paraStateId + 1
        defaultPlanInfoDataShouldNotBeFound("paraStateId.equals=" + (paraStateId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByParaSourceIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaSource paraSource = ParaSourceResourceIntTest.createEntity(em);
        em.persist(paraSource);
        em.flush();
        planInfoData.setParaSource(paraSource);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long paraSourceId = paraSource.getId();

        // Get all the planInfoDataList where paraSource equals to paraSourceId
        defaultPlanInfoDataShouldBeFound("paraSourceId.equals=" + paraSourceId);

        // Get all the planInfoDataList where paraSource equals to paraSourceId + 1
        defaultPlanInfoDataShouldNotBeFound("paraSourceId.equals=" + (paraSourceId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByParaAttrIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaAttr paraAttr = ParaAttrResourceIntTest.createEntity(em);
        em.persist(paraAttr);
        em.flush();
        planInfoData.setParaAttr(paraAttr);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long paraAttrId = paraAttr.getId();

        // Get all the planInfoDataList where paraAttr equals to paraAttrId
        defaultPlanInfoDataShouldBeFound("paraAttrId.equals=" + paraAttrId);

        // Get all the planInfoDataList where paraAttr equals to paraAttrId + 1
        defaultPlanInfoDataShouldNotBeFound("paraAttrId.equals=" + (paraAttrId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByParaPropIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaProp paraProp = ParaPropResourceIntTest.createEntity(em);
        em.persist(paraProp);
        em.flush();
        planInfoData.setParaProp(paraProp);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long paraPropId = paraProp.getId();

        // Get all the planInfoDataList where paraProp equals to paraPropId
        defaultPlanInfoDataShouldBeFound("paraPropId.equals=" + paraPropId);

        // Get all the planInfoDataList where paraProp equals to paraPropId + 1
        defaultPlanInfoDataShouldNotBeFound("paraPropId.equals=" + (paraPropId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfoData.setCreatedBy(createdBy);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long createdById = createdBy.getId();

        // Get all the planInfoDataList where createdBy equals to createdById
        defaultPlanInfoDataShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoDataList where createdBy equals to createdById + 1
        defaultPlanInfoDataShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByCreatedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep createdDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(createdDepBy);
        em.flush();
        planInfoData.setCreatedDepBy(createdDepBy);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long createdDepById = createdDepBy.getId();

        // Get all the planInfoDataList where createdDepBy equals to createdDepById
        defaultPlanInfoDataShouldBeFound("createdDepById.equals=" + createdDepById);

        // Get all the planInfoDataList where createdDepBy equals to createdDepById + 1
        defaultPlanInfoDataShouldNotBeFound("createdDepById.equals=" + (createdDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByOwnerByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser ownerBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(ownerBy);
        em.flush();
        planInfoData.setOwnerBy(ownerBy);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long ownerById = ownerBy.getId();

        // Get all the planInfoDataList where ownerBy equals to ownerById
        defaultPlanInfoDataShouldBeFound("ownerById.equals=" + ownerById);

        // Get all the planInfoDataList where ownerBy equals to ownerById + 1
        defaultPlanInfoDataShouldNotBeFound("ownerById.equals=" + (ownerById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByOwnerDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep ownerDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(ownerDepBy);
        em.flush();
        planInfoData.setOwnerDepBy(ownerDepBy);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long ownerDepById = ownerDepBy.getId();

        // Get all the planInfoDataList where ownerDepBy equals to ownerDepById
        defaultPlanInfoDataShouldBeFound("ownerDepById.equals=" + ownerDepById);

        // Get all the planInfoDataList where ownerDepBy equals to ownerDepById + 1
        defaultPlanInfoDataShouldNotBeFound("ownerDepById.equals=" + (ownerDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfoData.setModifiedBy(modifiedBy);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoDataList where modifiedBy equals to modifiedById
        defaultPlanInfoDataShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoDataList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoDataShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByModifiedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep modifiedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(modifiedDepBy);
        em.flush();
        planInfoData.setModifiedDepBy(modifiedDepBy);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long modifiedDepById = modifiedDepBy.getId();

        // Get all the planInfoDataList where modifiedDepBy equals to modifiedDepById
        defaultPlanInfoDataShouldBeFound("modifiedDepById.equals=" + modifiedDepById);

        // Get all the planInfoDataList where modifiedDepBy equals to modifiedDepById + 1
        defaultPlanInfoDataShouldNotBeFound("modifiedDepById.equals=" + (modifiedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        planInfoData.setVerifiedBy(verifiedBy);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long verifiedById = verifiedBy.getId();

        // Get all the planInfoDataList where verifiedBy equals to verifiedById
        defaultPlanInfoDataShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the planInfoDataList where verifiedBy equals to verifiedById + 1
        defaultPlanInfoDataShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByVerifiedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep verifiedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(verifiedDepBy);
        em.flush();
        planInfoData.setVerifiedDepBy(verifiedDepBy);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long verifiedDepById = verifiedDepBy.getId();

        // Get all the planInfoDataList where verifiedDepBy equals to verifiedDepById
        defaultPlanInfoDataShouldBeFound("verifiedDepById.equals=" + verifiedDepById);

        // Get all the planInfoDataList where verifiedDepBy equals to verifiedDepById + 1
        defaultPlanInfoDataShouldNotBeFound("verifiedDepById.equals=" + (verifiedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByPublishedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser publishedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(publishedBy);
        em.flush();
        planInfoData.setPublishedBy(publishedBy);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long publishedById = publishedBy.getId();

        // Get all the planInfoDataList where publishedBy equals to publishedById
        defaultPlanInfoDataShouldBeFound("publishedById.equals=" + publishedById);

        // Get all the planInfoDataList where publishedBy equals to publishedById + 1
        defaultPlanInfoDataShouldNotBeFound("publishedById.equals=" + (publishedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByPublishedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep publishedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(publishedDepBy);
        em.flush();
        planInfoData.setPublishedDepBy(publishedDepBy);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long publishedDepById = publishedDepBy.getId();

        // Get all the planInfoDataList where publishedDepBy equals to publishedDepById
        defaultPlanInfoDataShouldBeFound("publishedDepById.equals=" + publishedDepById);

        // Get all the planInfoDataList where publishedDepBy equals to publishedDepById + 1
        defaultPlanInfoDataShouldNotBeFound("publishedDepById.equals=" + (publishedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoData parent = PlanInfoDataResourceIntTest.createEntity(em);
        em.persist(parent);
        em.flush();
        planInfoData.setParent(parent);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long parentId = parent.getId();

        // Get all the planInfoDataList where parent equals to parentId
        defaultPlanInfoDataShouldBeFound("parentId.equals=" + parentId);

        // Get all the planInfoDataList where parent equals to parentId + 1
        defaultPlanInfoDataShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoDataByPlanInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfo planInfo = PlanInfoResourceIntTest.createEntity(em);
        em.persist(planInfo);
        em.flush();
        planInfoData.setPlanInfo(planInfo);
        planInfoDataRepository.saveAndFlush(planInfoData);
        Long planInfoId = planInfo.getId();

        // Get all the planInfoDataList where planInfo equals to planInfoId
        defaultPlanInfoDataShouldBeFound("planInfoId.equals=" + planInfoId);

        // Get all the planInfoDataList where planInfo equals to planInfoId + 1
        defaultPlanInfoDataShouldNotBeFound("planInfoId.equals=" + (planInfoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoDataShouldBeFound(String filter) throws Exception {
        restPlanInfoDataMockMvc.perform(get("/api/plan-info-data?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoData.getId().intValue())))
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
        restPlanInfoDataMockMvc.perform(get("/api/plan-info-data/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoDataShouldNotBeFound(String filter) throws Exception {
        restPlanInfoDataMockMvc.perform(get("/api/plan-info-data?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoDataMockMvc.perform(get("/api/plan-info-data/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfoData() throws Exception {
        // Get the planInfoData
        restPlanInfoDataMockMvc.perform(get("/api/plan-info-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfoData() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        int databaseSizeBeforeUpdate = planInfoDataRepository.findAll().size();

        // Update the planInfoData
        PlanInfoData updatedPlanInfoData = planInfoDataRepository.findById(planInfoData.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfoData are not directly saved in db
        em.detach(updatedPlanInfoData);
        updatedPlanInfoData
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
        PlanInfoDataDTO planInfoDataDTO = planInfoDataMapper.toDto(updatedPlanInfoData);

        restPlanInfoDataMockMvc.perform(put("/api/plan-info-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfoData in the database
        List<PlanInfoData> planInfoDataList = planInfoDataRepository.findAll();
        assertThat(planInfoDataList).hasSize(databaseSizeBeforeUpdate);
        PlanInfoData testPlanInfoData = planInfoDataList.get(planInfoDataList.size() - 1);
        assertThat(testPlanInfoData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfoData.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfoData.getDescString()).isEqualTo(UPDATED_DESC_STRING);
        assertThat(testPlanInfoData.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfoData.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfoData.getRefEvent()).isEqualTo(UPDATED_REF_EVENT);
        assertThat(testPlanInfoData.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfoData.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfoData.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoData.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfoData.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfoData.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfoData.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoData.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoData.isUsingFlag()).isEqualTo(UPDATED_USING_FLAG);
        assertThat(testPlanInfoData.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfoData.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfoData.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfoData.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfoData.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPlanInfoData.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);
        assertThat(testPlanInfoData.isVerifyNeed()).isEqualTo(UPDATED_VERIFY_NEED);
        assertThat(testPlanInfoData.getVerifyOpinion()).isEqualTo(UPDATED_VERIFY_OPINION);
        assertThat(testPlanInfoData.getViewCount()).isEqualTo(UPDATED_VIEW_COUNT);
        assertThat(testPlanInfoData.getViewPermission()).isEqualTo(UPDATED_VIEW_PERMISSION);
        assertThat(testPlanInfoData.getViewPermPersion()).isEqualTo(UPDATED_VIEW_PERM_PERSION);
        assertThat(testPlanInfoData.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testPlanInfoData.getVersionNumber()).isEqualTo(UPDATED_VERSION_NUMBER);
        assertThat(testPlanInfoData.getParaSourceString()).isEqualTo(UPDATED_PARA_SOURCE_STRING);
        assertThat(testPlanInfoData.getFeatureKeyword()).isEqualTo(UPDATED_FEATURE_KEYWORD);
        assertThat(testPlanInfoData.getSuggestion()).isEqualTo(UPDATED_SUGGESTION);
        assertThat(testPlanInfoData.getReleaseScope()).isEqualTo(UPDATED_RELEASE_SCOPE);
        assertThat(testPlanInfoData.getCurrentStepOrder()).isEqualTo(UPDATED_CURRENT_STEP_ORDER);

        // Validate the PlanInfoData in Elasticsearch
        verify(mockPlanInfoDataSearchRepository, times(1)).save(testPlanInfoData);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfoData() throws Exception {
        int databaseSizeBeforeUpdate = planInfoDataRepository.findAll().size();

        // Create the PlanInfoData
        PlanInfoDataDTO planInfoDataDTO = planInfoDataMapper.toDto(planInfoData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoDataMockMvc.perform(put("/api/plan-info-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoData in the database
        List<PlanInfoData> planInfoDataList = planInfoDataRepository.findAll();
        assertThat(planInfoDataList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfoData in Elasticsearch
        verify(mockPlanInfoDataSearchRepository, times(0)).save(planInfoData);
    }

    @Test
    @Transactional
    public void deletePlanInfoData() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);

        int databaseSizeBeforeDelete = planInfoDataRepository.findAll().size();

        // Get the planInfoData
        restPlanInfoDataMockMvc.perform(delete("/api/plan-info-data/{id}", planInfoData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfoData> planInfoDataList = planInfoDataRepository.findAll();
        assertThat(planInfoDataList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfoData in Elasticsearch
        verify(mockPlanInfoDataSearchRepository, times(1)).deleteById(planInfoData.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfoData() throws Exception {
        // Initialize the database
        planInfoDataRepository.saveAndFlush(planInfoData);
        when(mockPlanInfoDataSearchRepository.search(queryStringQuery("id:" + planInfoData.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfoData), PageRequest.of(0, 1), 1));
        // Search the planInfoData
        restPlanInfoDataMockMvc.perform(get("/api/_search/plan-info-data?query=id:" + planInfoData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoData.getId().intValue())))
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
        TestUtil.equalsVerifier(PlanInfoData.class);
        PlanInfoData planInfoData1 = new PlanInfoData();
        planInfoData1.setId(1L);
        PlanInfoData planInfoData2 = new PlanInfoData();
        planInfoData2.setId(planInfoData1.getId());
        assertThat(planInfoData1).isEqualTo(planInfoData2);
        planInfoData2.setId(2L);
        assertThat(planInfoData1).isNotEqualTo(planInfoData2);
        planInfoData1.setId(null);
        assertThat(planInfoData1).isNotEqualTo(planInfoData2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoDataDTO.class);
        PlanInfoDataDTO planInfoDataDTO1 = new PlanInfoDataDTO();
        planInfoDataDTO1.setId(1L);
        PlanInfoDataDTO planInfoDataDTO2 = new PlanInfoDataDTO();
        assertThat(planInfoDataDTO1).isNotEqualTo(planInfoDataDTO2);
        planInfoDataDTO2.setId(planInfoDataDTO1.getId());
        assertThat(planInfoDataDTO1).isEqualTo(planInfoDataDTO2);
        planInfoDataDTO2.setId(2L);
        assertThat(planInfoDataDTO1).isNotEqualTo(planInfoDataDTO2);
        planInfoDataDTO1.setId(null);
        assertThat(planInfoDataDTO1).isNotEqualTo(planInfoDataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoDataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoDataMapper.fromId(null)).isNull();
    }
}
