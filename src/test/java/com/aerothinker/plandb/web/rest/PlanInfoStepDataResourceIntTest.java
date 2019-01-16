package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfoStepData;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.PlanInfoStep;
import com.aerothinker.plandb.domain.PlanInfoData;
import com.aerothinker.plandb.repository.PlanInfoStepDataRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepDataSearchRepository;
import com.aerothinker.plandb.service.PlanInfoStepDataService;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepDataMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoStepDataCriteria;
import com.aerothinker.plandb.service.PlanInfoStepDataQueryService;

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
 * Test class for the PlanInfoStepDataResource REST controller.
 *
 * @see PlanInfoStepDataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoStepDataResourceIntTest {

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
    private PlanInfoStepDataRepository planInfoStepDataRepository;

    @Autowired
    private PlanInfoStepDataMapper planInfoStepDataMapper;

    @Autowired
    private PlanInfoStepDataService planInfoStepDataService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoStepDataSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoStepDataSearchRepository mockPlanInfoStepDataSearchRepository;

    @Autowired
    private PlanInfoStepDataQueryService planInfoStepDataQueryService;

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

    private MockMvc restPlanInfoStepDataMockMvc;

    private PlanInfoStepData planInfoStepData;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoStepDataResource planInfoStepDataResource = new PlanInfoStepDataResource(planInfoStepDataService, planInfoStepDataQueryService);
        this.restPlanInfoStepDataMockMvc = MockMvcBuilders.standaloneSetup(planInfoStepDataResource)
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
    public static PlanInfoStepData createEntity(EntityManager em) {
        PlanInfoStepData planInfoStepData = new PlanInfoStepData()
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
        return planInfoStepData;
    }

    @Before
    public void initTest() {
        planInfoStepData = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfoStepData() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepDataRepository.findAll().size();

        // Create the PlanInfoStepData
        PlanInfoStepDataDTO planInfoStepDataDTO = planInfoStepDataMapper.toDto(planInfoStepData);
        restPlanInfoStepDataMockMvc.perform(post("/api/plan-info-step-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfoStepData in the database
        List<PlanInfoStepData> planInfoStepDataList = planInfoStepDataRepository.findAll();
        assertThat(planInfoStepDataList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfoStepData testPlanInfoStepData = planInfoStepDataList.get(planInfoStepDataList.size() - 1);
        assertThat(testPlanInfoStepData.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfoStepData.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfoStepData.getDescString()).isEqualTo(DEFAULT_DESC_STRING);
        assertThat(testPlanInfoStepData.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfoStepData.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfoStepData.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfoStepData.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStepData.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepData.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfoStepData.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfoStepData.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfoStepData.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepData.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStepData.isUsingFlag()).isEqualTo(DEFAULT_USING_FLAG);
        assertThat(testPlanInfoStepData.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfoStepData.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfoStepData.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfoStepData.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfoStepData.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPlanInfoStepData.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);
        assertThat(testPlanInfoStepData.isVerifyNeed()).isEqualTo(DEFAULT_VERIFY_NEED);
        assertThat(testPlanInfoStepData.getVerifyOpinion()).isEqualTo(DEFAULT_VERIFY_OPINION);
        assertThat(testPlanInfoStepData.getViewCount()).isEqualTo(DEFAULT_VIEW_COUNT);
        assertThat(testPlanInfoStepData.getViewPermission()).isEqualTo(DEFAULT_VIEW_PERMISSION);
        assertThat(testPlanInfoStepData.getViewPermPersion()).isEqualTo(DEFAULT_VIEW_PERM_PERSION);
        assertThat(testPlanInfoStepData.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testPlanInfoStepData.getVersionNumber()).isEqualTo(DEFAULT_VERSION_NUMBER);
        assertThat(testPlanInfoStepData.getStepOrder()).isEqualTo(DEFAULT_STEP_ORDER);

        // Validate the PlanInfoStepData in Elasticsearch
        verify(mockPlanInfoStepDataSearchRepository, times(1)).save(testPlanInfoStepData);
    }

    @Test
    @Transactional
    public void createPlanInfoStepDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepDataRepository.findAll().size();

        // Create the PlanInfoStepData with an existing ID
        planInfoStepData.setId(1L);
        PlanInfoStepDataDTO planInfoStepDataDTO = planInfoStepDataMapper.toDto(planInfoStepData);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoStepDataMockMvc.perform(post("/api/plan-info-step-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStepData in the database
        List<PlanInfoStepData> planInfoStepDataList = planInfoStepDataRepository.findAll();
        assertThat(planInfoStepDataList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfoStepData in Elasticsearch
        verify(mockPlanInfoStepDataSearchRepository, times(0)).save(planInfoStepData);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoStepDataRepository.findAll().size();
        // set the field null
        planInfoStepData.setName(null);

        // Create the PlanInfoStepData, which fails.
        PlanInfoStepDataDTO planInfoStepDataDTO = planInfoStepDataMapper.toDto(planInfoStepData);

        restPlanInfoStepDataMockMvc.perform(post("/api/plan-info-step-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfoStepData> planInfoStepDataList = planInfoStepDataRepository.findAll();
        assertThat(planInfoStepDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepData() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList
        restPlanInfoStepDataMockMvc.perform(get("/api/plan-info-step-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepData.getId().intValue())))
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
    public void getPlanInfoStepData() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get the planInfoStepData
        restPlanInfoStepDataMockMvc.perform(get("/api/plan-info-step-data/{id}", planInfoStepData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfoStepData.getId().intValue()))
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
    public void getAllPlanInfoStepDataByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where name equals to DEFAULT_NAME
        defaultPlanInfoStepDataShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoStepDataList where name equals to UPDATED_NAME
        defaultPlanInfoStepDataShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoStepDataShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoStepDataList where name equals to UPDATED_NAME
        defaultPlanInfoStepDataShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where name is not null
        defaultPlanInfoStepDataShouldBeFound("name.specified=true");

        // Get all the planInfoStepDataList where name is null
        defaultPlanInfoStepDataShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoStepDataShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoStepDataList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepDataShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoStepDataShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoStepDataList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepDataShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where sortString is not null
        defaultPlanInfoStepDataShouldBeFound("sortString.specified=true");

        // Get all the planInfoStepDataList where sortString is null
        defaultPlanInfoStepDataShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByDescStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where descString equals to DEFAULT_DESC_STRING
        defaultPlanInfoStepDataShouldBeFound("descString.equals=" + DEFAULT_DESC_STRING);

        // Get all the planInfoStepDataList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoStepDataShouldNotBeFound("descString.equals=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByDescStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where descString in DEFAULT_DESC_STRING or UPDATED_DESC_STRING
        defaultPlanInfoStepDataShouldBeFound("descString.in=" + DEFAULT_DESC_STRING + "," + UPDATED_DESC_STRING);

        // Get all the planInfoStepDataList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoStepDataShouldNotBeFound("descString.in=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByDescStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where descString is not null
        defaultPlanInfoStepDataShouldBeFound("descString.specified=true");

        // Get all the planInfoStepDataList where descString is null
        defaultPlanInfoStepDataShouldNotBeFound("descString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoStepDataShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoStepDataList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepDataShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoStepDataShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoStepDataList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepDataShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where jsonString is not null
        defaultPlanInfoStepDataShouldBeFound("jsonString.specified=true");

        // Get all the planInfoStepDataList where jsonString is null
        defaultPlanInfoStepDataShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoStepDataShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoStepDataList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepDataShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoStepDataShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoStepDataList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepDataShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where remarks is not null
        defaultPlanInfoStepDataShouldBeFound("remarks.specified=true");

        // Get all the planInfoStepDataList where remarks is null
        defaultPlanInfoStepDataShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoStepDataShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoStepDataList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoStepDataList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepDataShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where attachmentPath is not null
        defaultPlanInfoStepDataShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoStepDataList where attachmentPath is null
        defaultPlanInfoStepDataShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoStepDataShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoStepDataList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoStepDataList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepDataShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where attachmentName is not null
        defaultPlanInfoStepDataShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoStepDataList where attachmentName is null
        defaultPlanInfoStepDataShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoStepDataList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoStepDataList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepDataShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where imageBlobName is not null
        defaultPlanInfoStepDataShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoStepDataList where imageBlobName is null
        defaultPlanInfoStepDataShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByUsingFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where usingFlag equals to DEFAULT_USING_FLAG
        defaultPlanInfoStepDataShouldBeFound("usingFlag.equals=" + DEFAULT_USING_FLAG);

        // Get all the planInfoStepDataList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoStepDataShouldNotBeFound("usingFlag.equals=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByUsingFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where usingFlag in DEFAULT_USING_FLAG or UPDATED_USING_FLAG
        defaultPlanInfoStepDataShouldBeFound("usingFlag.in=" + DEFAULT_USING_FLAG + "," + UPDATED_USING_FLAG);

        // Get all the planInfoStepDataList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoStepDataShouldNotBeFound("usingFlag.in=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByUsingFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where usingFlag is not null
        defaultPlanInfoStepDataShouldBeFound("usingFlag.specified=true");

        // Get all the planInfoStepDataList where usingFlag is null
        defaultPlanInfoStepDataShouldNotBeFound("usingFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoStepDataShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoStepDataList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepDataShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoStepDataShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoStepDataList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepDataShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where validType is not null
        defaultPlanInfoStepDataShouldBeFound("validType.specified=true");

        // Get all the planInfoStepDataList where validType is null
        defaultPlanInfoStepDataShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoStepDataShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoStepDataList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoStepDataList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepDataShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where validBegin is not null
        defaultPlanInfoStepDataShouldBeFound("validBegin.specified=true");

        // Get all the planInfoStepDataList where validBegin is null
        defaultPlanInfoStepDataShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoStepDataShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoStepDataList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepDataShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoStepDataShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoStepDataList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepDataShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where validEnd is not null
        defaultPlanInfoStepDataShouldBeFound("validEnd.specified=true");

        // Get all the planInfoStepDataList where validEnd is null
        defaultPlanInfoStepDataShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoStepDataShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoStepDataList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepDataShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoStepDataShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoStepDataList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepDataShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where insertTime is not null
        defaultPlanInfoStepDataShouldBeFound("insertTime.specified=true");

        // Get all the planInfoStepDataList where insertTime is null
        defaultPlanInfoStepDataShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoStepDataShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoStepDataList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoStepDataList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepDataShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where updateTime is not null
        defaultPlanInfoStepDataShouldBeFound("updateTime.specified=true");

        // Get all the planInfoStepDataList where updateTime is null
        defaultPlanInfoStepDataShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultPlanInfoStepDataShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the planInfoStepDataList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoStepDataShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultPlanInfoStepDataShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the planInfoStepDataList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoStepDataShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where verifyTime is not null
        defaultPlanInfoStepDataShouldBeFound("verifyTime.specified=true");

        // Get all the planInfoStepDataList where verifyTime is null
        defaultPlanInfoStepDataShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVerifyNeedIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where verifyNeed equals to DEFAULT_VERIFY_NEED
        defaultPlanInfoStepDataShouldBeFound("verifyNeed.equals=" + DEFAULT_VERIFY_NEED);

        // Get all the planInfoStepDataList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoStepDataShouldNotBeFound("verifyNeed.equals=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVerifyNeedIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where verifyNeed in DEFAULT_VERIFY_NEED or UPDATED_VERIFY_NEED
        defaultPlanInfoStepDataShouldBeFound("verifyNeed.in=" + DEFAULT_VERIFY_NEED + "," + UPDATED_VERIFY_NEED);

        // Get all the planInfoStepDataList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoStepDataShouldNotBeFound("verifyNeed.in=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVerifyNeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where verifyNeed is not null
        defaultPlanInfoStepDataShouldBeFound("verifyNeed.specified=true");

        // Get all the planInfoStepDataList where verifyNeed is null
        defaultPlanInfoStepDataShouldNotBeFound("verifyNeed.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVerifyOpinionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where verifyOpinion equals to DEFAULT_VERIFY_OPINION
        defaultPlanInfoStepDataShouldBeFound("verifyOpinion.equals=" + DEFAULT_VERIFY_OPINION);

        // Get all the planInfoStepDataList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoStepDataShouldNotBeFound("verifyOpinion.equals=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVerifyOpinionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where verifyOpinion in DEFAULT_VERIFY_OPINION or UPDATED_VERIFY_OPINION
        defaultPlanInfoStepDataShouldBeFound("verifyOpinion.in=" + DEFAULT_VERIFY_OPINION + "," + UPDATED_VERIFY_OPINION);

        // Get all the planInfoStepDataList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoStepDataShouldNotBeFound("verifyOpinion.in=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVerifyOpinionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where verifyOpinion is not null
        defaultPlanInfoStepDataShouldBeFound("verifyOpinion.specified=true");

        // Get all the planInfoStepDataList where verifyOpinion is null
        defaultPlanInfoStepDataShouldNotBeFound("verifyOpinion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByViewCountIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where viewCount equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoStepDataShouldBeFound("viewCount.equals=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoStepDataList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepDataShouldNotBeFound("viewCount.equals=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByViewCountIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where viewCount in DEFAULT_VIEW_COUNT or UPDATED_VIEW_COUNT
        defaultPlanInfoStepDataShouldBeFound("viewCount.in=" + DEFAULT_VIEW_COUNT + "," + UPDATED_VIEW_COUNT);

        // Get all the planInfoStepDataList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepDataShouldNotBeFound("viewCount.in=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByViewCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where viewCount is not null
        defaultPlanInfoStepDataShouldBeFound("viewCount.specified=true");

        // Get all the planInfoStepDataList where viewCount is null
        defaultPlanInfoStepDataShouldNotBeFound("viewCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByViewCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where viewCount greater than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoStepDataShouldBeFound("viewCount.greaterOrEqualThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoStepDataList where viewCount greater than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepDataShouldNotBeFound("viewCount.greaterOrEqualThan=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByViewCountIsLessThanSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where viewCount less than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoStepDataShouldNotBeFound("viewCount.lessThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoStepDataList where viewCount less than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepDataShouldBeFound("viewCount.lessThan=" + UPDATED_VIEW_COUNT);
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataByViewPermissionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where viewPermission equals to DEFAULT_VIEW_PERMISSION
        defaultPlanInfoStepDataShouldBeFound("viewPermission.equals=" + DEFAULT_VIEW_PERMISSION);

        // Get all the planInfoStepDataList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoStepDataShouldNotBeFound("viewPermission.equals=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByViewPermissionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where viewPermission in DEFAULT_VIEW_PERMISSION or UPDATED_VIEW_PERMISSION
        defaultPlanInfoStepDataShouldBeFound("viewPermission.in=" + DEFAULT_VIEW_PERMISSION + "," + UPDATED_VIEW_PERMISSION);

        // Get all the planInfoStepDataList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoStepDataShouldNotBeFound("viewPermission.in=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByViewPermissionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where viewPermission is not null
        defaultPlanInfoStepDataShouldBeFound("viewPermission.specified=true");

        // Get all the planInfoStepDataList where viewPermission is null
        defaultPlanInfoStepDataShouldNotBeFound("viewPermission.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByViewPermPersionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where viewPermPersion equals to DEFAULT_VIEW_PERM_PERSION
        defaultPlanInfoStepDataShouldBeFound("viewPermPersion.equals=" + DEFAULT_VIEW_PERM_PERSION);

        // Get all the planInfoStepDataList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoStepDataShouldNotBeFound("viewPermPersion.equals=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByViewPermPersionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where viewPermPersion in DEFAULT_VIEW_PERM_PERSION or UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoStepDataShouldBeFound("viewPermPersion.in=" + DEFAULT_VIEW_PERM_PERSION + "," + UPDATED_VIEW_PERM_PERSION);

        // Get all the planInfoStepDataList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoStepDataShouldNotBeFound("viewPermPersion.in=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByViewPermPersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where viewPermPersion is not null
        defaultPlanInfoStepDataShouldBeFound("viewPermPersion.specified=true");

        // Get all the planInfoStepDataList where viewPermPersion is null
        defaultPlanInfoStepDataShouldNotBeFound("viewPermPersion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultPlanInfoStepDataShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the planInfoStepDataList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoStepDataShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultPlanInfoStepDataShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the planInfoStepDataList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoStepDataShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where serialNumber is not null
        defaultPlanInfoStepDataShouldBeFound("serialNumber.specified=true");

        // Get all the planInfoStepDataList where serialNumber is null
        defaultPlanInfoStepDataShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVersionNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where versionNumber equals to DEFAULT_VERSION_NUMBER
        defaultPlanInfoStepDataShouldBeFound("versionNumber.equals=" + DEFAULT_VERSION_NUMBER);

        // Get all the planInfoStepDataList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoStepDataShouldNotBeFound("versionNumber.equals=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVersionNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where versionNumber in DEFAULT_VERSION_NUMBER or UPDATED_VERSION_NUMBER
        defaultPlanInfoStepDataShouldBeFound("versionNumber.in=" + DEFAULT_VERSION_NUMBER + "," + UPDATED_VERSION_NUMBER);

        // Get all the planInfoStepDataList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoStepDataShouldNotBeFound("versionNumber.in=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVersionNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where versionNumber is not null
        defaultPlanInfoStepDataShouldBeFound("versionNumber.specified=true");

        // Get all the planInfoStepDataList where versionNumber is null
        defaultPlanInfoStepDataShouldNotBeFound("versionNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByStepOrderIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where stepOrder equals to DEFAULT_STEP_ORDER
        defaultPlanInfoStepDataShouldBeFound("stepOrder.equals=" + DEFAULT_STEP_ORDER);

        // Get all the planInfoStepDataList where stepOrder equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepDataShouldNotBeFound("stepOrder.equals=" + UPDATED_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByStepOrderIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where stepOrder in DEFAULT_STEP_ORDER or UPDATED_STEP_ORDER
        defaultPlanInfoStepDataShouldBeFound("stepOrder.in=" + DEFAULT_STEP_ORDER + "," + UPDATED_STEP_ORDER);

        // Get all the planInfoStepDataList where stepOrder equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepDataShouldNotBeFound("stepOrder.in=" + UPDATED_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByStepOrderIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where stepOrder is not null
        defaultPlanInfoStepDataShouldBeFound("stepOrder.specified=true");

        // Get all the planInfoStepDataList where stepOrder is null
        defaultPlanInfoStepDataShouldNotBeFound("stepOrder.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByStepOrderIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where stepOrder greater than or equals to DEFAULT_STEP_ORDER
        defaultPlanInfoStepDataShouldBeFound("stepOrder.greaterOrEqualThan=" + DEFAULT_STEP_ORDER);

        // Get all the planInfoStepDataList where stepOrder greater than or equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepDataShouldNotBeFound("stepOrder.greaterOrEqualThan=" + UPDATED_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepDataByStepOrderIsLessThanSomething() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        // Get all the planInfoStepDataList where stepOrder less than or equals to DEFAULT_STEP_ORDER
        defaultPlanInfoStepDataShouldNotBeFound("stepOrder.lessThan=" + DEFAULT_STEP_ORDER);

        // Get all the planInfoStepDataList where stepOrder less than or equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepDataShouldBeFound("stepOrder.lessThan=" + UPDATED_STEP_ORDER);
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfoStepData.setCreatedBy(createdBy);
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);
        Long createdById = createdBy.getId();

        // Get all the planInfoStepDataList where createdBy equals to createdById
        defaultPlanInfoStepDataShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoStepDataList where createdBy equals to createdById + 1
        defaultPlanInfoStepDataShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataByCreatedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep createdDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(createdDepBy);
        em.flush();
        planInfoStepData.setCreatedDepBy(createdDepBy);
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);
        Long createdDepById = createdDepBy.getId();

        // Get all the planInfoStepDataList where createdDepBy equals to createdDepById
        defaultPlanInfoStepDataShouldBeFound("createdDepById.equals=" + createdDepById);

        // Get all the planInfoStepDataList where createdDepBy equals to createdDepById + 1
        defaultPlanInfoStepDataShouldNotBeFound("createdDepById.equals=" + (createdDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfoStepData.setModifiedBy(modifiedBy);
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoStepDataList where modifiedBy equals to modifiedById
        defaultPlanInfoStepDataShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoStepDataList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoStepDataShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataByModifiedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep modifiedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(modifiedDepBy);
        em.flush();
        planInfoStepData.setModifiedDepBy(modifiedDepBy);
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);
        Long modifiedDepById = modifiedDepBy.getId();

        // Get all the planInfoStepDataList where modifiedDepBy equals to modifiedDepById
        defaultPlanInfoStepDataShouldBeFound("modifiedDepById.equals=" + modifiedDepById);

        // Get all the planInfoStepDataList where modifiedDepBy equals to modifiedDepById + 1
        defaultPlanInfoStepDataShouldNotBeFound("modifiedDepById.equals=" + (modifiedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        planInfoStepData.setVerifiedBy(verifiedBy);
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);
        Long verifiedById = verifiedBy.getId();

        // Get all the planInfoStepDataList where verifiedBy equals to verifiedById
        defaultPlanInfoStepDataShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the planInfoStepDataList where verifiedBy equals to verifiedById + 1
        defaultPlanInfoStepDataShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataByVerifiedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep verifiedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(verifiedDepBy);
        em.flush();
        planInfoStepData.setVerifiedDepBy(verifiedDepBy);
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);
        Long verifiedDepById = verifiedDepBy.getId();

        // Get all the planInfoStepDataList where verifiedDepBy equals to verifiedDepById
        defaultPlanInfoStepDataShouldBeFound("verifiedDepById.equals=" + verifiedDepById);

        // Get all the planInfoStepDataList where verifiedDepBy equals to verifiedDepById + 1
        defaultPlanInfoStepDataShouldNotBeFound("verifiedDepById.equals=" + (verifiedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataByPlanInfoStepIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoStep planInfoStep = PlanInfoStepResourceIntTest.createEntity(em);
        em.persist(planInfoStep);
        em.flush();
        planInfoStepData.setPlanInfoStep(planInfoStep);
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);
        Long planInfoStepId = planInfoStep.getId();

        // Get all the planInfoStepDataList where planInfoStep equals to planInfoStepId
        defaultPlanInfoStepDataShouldBeFound("planInfoStepId.equals=" + planInfoStepId);

        // Get all the planInfoStepDataList where planInfoStep equals to planInfoStepId + 1
        defaultPlanInfoStepDataShouldNotBeFound("planInfoStepId.equals=" + (planInfoStepId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepDataByPlanInfoDataIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfoData planInfoData = PlanInfoDataResourceIntTest.createEntity(em);
        em.persist(planInfoData);
        em.flush();
        planInfoStepData.setPlanInfoData(planInfoData);
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);
        Long planInfoDataId = planInfoData.getId();

        // Get all the planInfoStepDataList where planInfoData equals to planInfoDataId
        defaultPlanInfoStepDataShouldBeFound("planInfoDataId.equals=" + planInfoDataId);

        // Get all the planInfoStepDataList where planInfoData equals to planInfoDataId + 1
        defaultPlanInfoStepDataShouldNotBeFound("planInfoDataId.equals=" + (planInfoDataId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoStepDataShouldBeFound(String filter) throws Exception {
        restPlanInfoStepDataMockMvc.perform(get("/api/plan-info-step-data?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepData.getId().intValue())))
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
        restPlanInfoStepDataMockMvc.perform(get("/api/plan-info-step-data/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoStepDataShouldNotBeFound(String filter) throws Exception {
        restPlanInfoStepDataMockMvc.perform(get("/api/plan-info-step-data?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoStepDataMockMvc.perform(get("/api/plan-info-step-data/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfoStepData() throws Exception {
        // Get the planInfoStepData
        restPlanInfoStepDataMockMvc.perform(get("/api/plan-info-step-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfoStepData() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        int databaseSizeBeforeUpdate = planInfoStepDataRepository.findAll().size();

        // Update the planInfoStepData
        PlanInfoStepData updatedPlanInfoStepData = planInfoStepDataRepository.findById(planInfoStepData.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfoStepData are not directly saved in db
        em.detach(updatedPlanInfoStepData);
        updatedPlanInfoStepData
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
        PlanInfoStepDataDTO planInfoStepDataDTO = planInfoStepDataMapper.toDto(updatedPlanInfoStepData);

        restPlanInfoStepDataMockMvc.perform(put("/api/plan-info-step-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfoStepData in the database
        List<PlanInfoStepData> planInfoStepDataList = planInfoStepDataRepository.findAll();
        assertThat(planInfoStepDataList).hasSize(databaseSizeBeforeUpdate);
        PlanInfoStepData testPlanInfoStepData = planInfoStepDataList.get(planInfoStepDataList.size() - 1);
        assertThat(testPlanInfoStepData.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfoStepData.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfoStepData.getDescString()).isEqualTo(UPDATED_DESC_STRING);
        assertThat(testPlanInfoStepData.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfoStepData.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfoStepData.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfoStepData.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStepData.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepData.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfoStepData.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfoStepData.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfoStepData.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStepData.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStepData.isUsingFlag()).isEqualTo(UPDATED_USING_FLAG);
        assertThat(testPlanInfoStepData.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfoStepData.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfoStepData.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfoStepData.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfoStepData.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPlanInfoStepData.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);
        assertThat(testPlanInfoStepData.isVerifyNeed()).isEqualTo(UPDATED_VERIFY_NEED);
        assertThat(testPlanInfoStepData.getVerifyOpinion()).isEqualTo(UPDATED_VERIFY_OPINION);
        assertThat(testPlanInfoStepData.getViewCount()).isEqualTo(UPDATED_VIEW_COUNT);
        assertThat(testPlanInfoStepData.getViewPermission()).isEqualTo(UPDATED_VIEW_PERMISSION);
        assertThat(testPlanInfoStepData.getViewPermPersion()).isEqualTo(UPDATED_VIEW_PERM_PERSION);
        assertThat(testPlanInfoStepData.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testPlanInfoStepData.getVersionNumber()).isEqualTo(UPDATED_VERSION_NUMBER);
        assertThat(testPlanInfoStepData.getStepOrder()).isEqualTo(UPDATED_STEP_ORDER);

        // Validate the PlanInfoStepData in Elasticsearch
        verify(mockPlanInfoStepDataSearchRepository, times(1)).save(testPlanInfoStepData);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfoStepData() throws Exception {
        int databaseSizeBeforeUpdate = planInfoStepDataRepository.findAll().size();

        // Create the PlanInfoStepData
        PlanInfoStepDataDTO planInfoStepDataDTO = planInfoStepDataMapper.toDto(planInfoStepData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoStepDataMockMvc.perform(put("/api/plan-info-step-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStepData in the database
        List<PlanInfoStepData> planInfoStepDataList = planInfoStepDataRepository.findAll();
        assertThat(planInfoStepDataList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfoStepData in Elasticsearch
        verify(mockPlanInfoStepDataSearchRepository, times(0)).save(planInfoStepData);
    }

    @Test
    @Transactional
    public void deletePlanInfoStepData() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);

        int databaseSizeBeforeDelete = planInfoStepDataRepository.findAll().size();

        // Get the planInfoStepData
        restPlanInfoStepDataMockMvc.perform(delete("/api/plan-info-step-data/{id}", planInfoStepData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfoStepData> planInfoStepDataList = planInfoStepDataRepository.findAll();
        assertThat(planInfoStepDataList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfoStepData in Elasticsearch
        verify(mockPlanInfoStepDataSearchRepository, times(1)).deleteById(planInfoStepData.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfoStepData() throws Exception {
        // Initialize the database
        planInfoStepDataRepository.saveAndFlush(planInfoStepData);
        when(mockPlanInfoStepDataSearchRepository.search(queryStringQuery("id:" + planInfoStepData.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfoStepData), PageRequest.of(0, 1), 1));
        // Search the planInfoStepData
        restPlanInfoStepDataMockMvc.perform(get("/api/_search/plan-info-step-data?query=id:" + planInfoStepData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStepData.getId().intValue())))
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
        TestUtil.equalsVerifier(PlanInfoStepData.class);
        PlanInfoStepData planInfoStepData1 = new PlanInfoStepData();
        planInfoStepData1.setId(1L);
        PlanInfoStepData planInfoStepData2 = new PlanInfoStepData();
        planInfoStepData2.setId(planInfoStepData1.getId());
        assertThat(planInfoStepData1).isEqualTo(planInfoStepData2);
        planInfoStepData2.setId(2L);
        assertThat(planInfoStepData1).isNotEqualTo(planInfoStepData2);
        planInfoStepData1.setId(null);
        assertThat(planInfoStepData1).isNotEqualTo(planInfoStepData2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoStepDataDTO.class);
        PlanInfoStepDataDTO planInfoStepDataDTO1 = new PlanInfoStepDataDTO();
        planInfoStepDataDTO1.setId(1L);
        PlanInfoStepDataDTO planInfoStepDataDTO2 = new PlanInfoStepDataDTO();
        assertThat(planInfoStepDataDTO1).isNotEqualTo(planInfoStepDataDTO2);
        planInfoStepDataDTO2.setId(planInfoStepDataDTO1.getId());
        assertThat(planInfoStepDataDTO1).isEqualTo(planInfoStepDataDTO2);
        planInfoStepDataDTO2.setId(2L);
        assertThat(planInfoStepDataDTO1).isNotEqualTo(planInfoStepDataDTO2);
        planInfoStepDataDTO1.setId(null);
        assertThat(planInfoStepDataDTO1).isNotEqualTo(planInfoStepDataDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoStepDataMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoStepDataMapper.fromId(null)).isNull();
    }
}
