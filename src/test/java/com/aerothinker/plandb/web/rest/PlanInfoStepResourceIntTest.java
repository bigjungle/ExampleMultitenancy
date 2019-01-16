package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfoStep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.PlanInfo;
import com.aerothinker.plandb.repository.PlanInfoStepRepository;
import com.aerothinker.plandb.repository.search.PlanInfoStepSearchRepository;
import com.aerothinker.plandb.service.PlanInfoStepService;
import com.aerothinker.plandb.service.dto.PlanInfoStepDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoStepMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoStepCriteria;
import com.aerothinker.plandb.service.PlanInfoStepQueryService;

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
 * Test class for the PlanInfoStepResource REST controller.
 *
 * @see PlanInfoStepResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoStepResourceIntTest {

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

    private static final Integer DEFAULT_STEP_ORDER = 1;
    private static final Integer UPDATED_STEP_ORDER = 2;

    private static final Instant DEFAULT_PUBLISHED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLISHED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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

    @Autowired
    private PlanInfoStepRepository planInfoStepRepository;

    @Autowired
    private PlanInfoStepMapper planInfoStepMapper;

    @Autowired
    private PlanInfoStepService planInfoStepService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoStepSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoStepSearchRepository mockPlanInfoStepSearchRepository;

    @Autowired
    private PlanInfoStepQueryService planInfoStepQueryService;

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

    private MockMvc restPlanInfoStepMockMvc;

    private PlanInfoStep planInfoStep;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoStepResource planInfoStepResource = new PlanInfoStepResource(planInfoStepService, planInfoStepQueryService);
        this.restPlanInfoStepMockMvc = MockMvcBuilders.standaloneSetup(planInfoStepResource)
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
    public static PlanInfoStep createEntity(EntityManager em) {
        PlanInfoStep planInfoStep = new PlanInfoStep()
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
            .stepOrder(DEFAULT_STEP_ORDER)
            .publishedTime(DEFAULT_PUBLISHED_TIME)
            .verifyTime(DEFAULT_VERIFY_TIME)
            .verifyNeed(DEFAULT_VERIFY_NEED)
            .verifyOpinion(DEFAULT_VERIFY_OPINION)
            .viewCount(DEFAULT_VIEW_COUNT)
            .viewPermission(DEFAULT_VIEW_PERMISSION)
            .viewPermPersion(DEFAULT_VIEW_PERM_PERSION)
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .versionNumber(DEFAULT_VERSION_NUMBER);
        return planInfoStep;
    }

    @Before
    public void initTest() {
        planInfoStep = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfoStep() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepRepository.findAll().size();

        // Create the PlanInfoStep
        PlanInfoStepDTO planInfoStepDTO = planInfoStepMapper.toDto(planInfoStep);
        restPlanInfoStepMockMvc.perform(post("/api/plan-info-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfoStep in the database
        List<PlanInfoStep> planInfoStepList = planInfoStepRepository.findAll();
        assertThat(planInfoStepList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfoStep testPlanInfoStep = planInfoStepList.get(planInfoStepList.size() - 1);
        assertThat(testPlanInfoStep.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfoStep.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfoStep.getDescString()).isEqualTo(DEFAULT_DESC_STRING);
        assertThat(testPlanInfoStep.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfoStep.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfoStep.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfoStep.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStep.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStep.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfoStep.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfoStep.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfoStep.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStep.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStep.isUsingFlag()).isEqualTo(DEFAULT_USING_FLAG);
        assertThat(testPlanInfoStep.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfoStep.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfoStep.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfoStep.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfoStep.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPlanInfoStep.getStepOrder()).isEqualTo(DEFAULT_STEP_ORDER);
        assertThat(testPlanInfoStep.getPublishedTime()).isEqualTo(DEFAULT_PUBLISHED_TIME);
        assertThat(testPlanInfoStep.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);
        assertThat(testPlanInfoStep.isVerifyNeed()).isEqualTo(DEFAULT_VERIFY_NEED);
        assertThat(testPlanInfoStep.getVerifyOpinion()).isEqualTo(DEFAULT_VERIFY_OPINION);
        assertThat(testPlanInfoStep.getViewCount()).isEqualTo(DEFAULT_VIEW_COUNT);
        assertThat(testPlanInfoStep.getViewPermission()).isEqualTo(DEFAULT_VIEW_PERMISSION);
        assertThat(testPlanInfoStep.getViewPermPersion()).isEqualTo(DEFAULT_VIEW_PERM_PERSION);
        assertThat(testPlanInfoStep.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testPlanInfoStep.getVersionNumber()).isEqualTo(DEFAULT_VERSION_NUMBER);

        // Validate the PlanInfoStep in Elasticsearch
        verify(mockPlanInfoStepSearchRepository, times(1)).save(testPlanInfoStep);
    }

    @Test
    @Transactional
    public void createPlanInfoStepWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoStepRepository.findAll().size();

        // Create the PlanInfoStep with an existing ID
        planInfoStep.setId(1L);
        PlanInfoStepDTO planInfoStepDTO = planInfoStepMapper.toDto(planInfoStep);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoStepMockMvc.perform(post("/api/plan-info-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStep in the database
        List<PlanInfoStep> planInfoStepList = planInfoStepRepository.findAll();
        assertThat(planInfoStepList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfoStep in Elasticsearch
        verify(mockPlanInfoStepSearchRepository, times(0)).save(planInfoStep);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoStepRepository.findAll().size();
        // set the field null
        planInfoStep.setName(null);

        // Create the PlanInfoStep, which fails.
        PlanInfoStepDTO planInfoStepDTO = planInfoStepMapper.toDto(planInfoStep);

        restPlanInfoStepMockMvc.perform(post("/api/plan-info-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfoStep> planInfoStepList = planInfoStepRepository.findAll();
        assertThat(planInfoStepList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfoSteps() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList
        restPlanInfoStepMockMvc.perform(get("/api/plan-info-steps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStep.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].stepOrder").value(hasItem(DEFAULT_STEP_ORDER)))
            .andExpect(jsonPath("$.[*].publishedTime").value(hasItem(DEFAULT_PUBLISHED_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyNeed").value(hasItem(DEFAULT_VERIFY_NEED.booleanValue())))
            .andExpect(jsonPath("$.[*].verifyOpinion").value(hasItem(DEFAULT_VERIFY_OPINION.toString())))
            .andExpect(jsonPath("$.[*].viewCount").value(hasItem(DEFAULT_VIEW_COUNT)))
            .andExpect(jsonPath("$.[*].viewPermission").value(hasItem(DEFAULT_VIEW_PERMISSION.toString())))
            .andExpect(jsonPath("$.[*].viewPermPersion").value(hasItem(DEFAULT_VIEW_PERM_PERSION.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].versionNumber").value(hasItem(DEFAULT_VERSION_NUMBER.toString())));
    }
    
    @Test
    @Transactional
    public void getPlanInfoStep() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get the planInfoStep
        restPlanInfoStepMockMvc.perform(get("/api/plan-info-steps/{id}", planInfoStep.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfoStep.getId().intValue()))
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
            .andExpect(jsonPath("$.stepOrder").value(DEFAULT_STEP_ORDER))
            .andExpect(jsonPath("$.publishedTime").value(DEFAULT_PUBLISHED_TIME.toString()))
            .andExpect(jsonPath("$.verifyTime").value(DEFAULT_VERIFY_TIME.toString()))
            .andExpect(jsonPath("$.verifyNeed").value(DEFAULT_VERIFY_NEED.booleanValue()))
            .andExpect(jsonPath("$.verifyOpinion").value(DEFAULT_VERIFY_OPINION.toString()))
            .andExpect(jsonPath("$.viewCount").value(DEFAULT_VIEW_COUNT))
            .andExpect(jsonPath("$.viewPermission").value(DEFAULT_VIEW_PERMISSION.toString()))
            .andExpect(jsonPath("$.viewPermPersion").value(DEFAULT_VIEW_PERM_PERSION.toString()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.versionNumber").value(DEFAULT_VERSION_NUMBER.toString()));
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where name equals to DEFAULT_NAME
        defaultPlanInfoStepShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoStepList where name equals to UPDATED_NAME
        defaultPlanInfoStepShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoStepShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoStepList where name equals to UPDATED_NAME
        defaultPlanInfoStepShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where name is not null
        defaultPlanInfoStepShouldBeFound("name.specified=true");

        // Get all the planInfoStepList where name is null
        defaultPlanInfoStepShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoStepShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoStepList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoStepShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoStepList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoStepShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where sortString is not null
        defaultPlanInfoStepShouldBeFound("sortString.specified=true");

        // Get all the planInfoStepList where sortString is null
        defaultPlanInfoStepShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByDescStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where descString equals to DEFAULT_DESC_STRING
        defaultPlanInfoStepShouldBeFound("descString.equals=" + DEFAULT_DESC_STRING);

        // Get all the planInfoStepList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoStepShouldNotBeFound("descString.equals=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByDescStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where descString in DEFAULT_DESC_STRING or UPDATED_DESC_STRING
        defaultPlanInfoStepShouldBeFound("descString.in=" + DEFAULT_DESC_STRING + "," + UPDATED_DESC_STRING);

        // Get all the planInfoStepList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoStepShouldNotBeFound("descString.in=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByDescStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where descString is not null
        defaultPlanInfoStepShouldBeFound("descString.specified=true");

        // Get all the planInfoStepList where descString is null
        defaultPlanInfoStepShouldNotBeFound("descString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoStepShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoStepList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoStepShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoStepList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoStepShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where jsonString is not null
        defaultPlanInfoStepShouldBeFound("jsonString.specified=true");

        // Get all the planInfoStepList where jsonString is null
        defaultPlanInfoStepShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoStepShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoStepList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoStepShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoStepList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoStepShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where remarks is not null
        defaultPlanInfoStepShouldBeFound("remarks.specified=true");

        // Get all the planInfoStepList where remarks is null
        defaultPlanInfoStepShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoStepShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoStepList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoStepList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoStepShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where attachmentPath is not null
        defaultPlanInfoStepShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoStepList where attachmentPath is null
        defaultPlanInfoStepShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoStepShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoStepList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoStepList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoStepShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where attachmentName is not null
        defaultPlanInfoStepShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoStepList where attachmentName is null
        defaultPlanInfoStepShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoStepShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoStepList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoStepList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoStepShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where imageBlobName is not null
        defaultPlanInfoStepShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoStepList where imageBlobName is null
        defaultPlanInfoStepShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByUsingFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where usingFlag equals to DEFAULT_USING_FLAG
        defaultPlanInfoStepShouldBeFound("usingFlag.equals=" + DEFAULT_USING_FLAG);

        // Get all the planInfoStepList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoStepShouldNotBeFound("usingFlag.equals=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByUsingFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where usingFlag in DEFAULT_USING_FLAG or UPDATED_USING_FLAG
        defaultPlanInfoStepShouldBeFound("usingFlag.in=" + DEFAULT_USING_FLAG + "," + UPDATED_USING_FLAG);

        // Get all the planInfoStepList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoStepShouldNotBeFound("usingFlag.in=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByUsingFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where usingFlag is not null
        defaultPlanInfoStepShouldBeFound("usingFlag.specified=true");

        // Get all the planInfoStepList where usingFlag is null
        defaultPlanInfoStepShouldNotBeFound("usingFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoStepShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoStepList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoStepShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoStepList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoStepShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where validType is not null
        defaultPlanInfoStepShouldBeFound("validType.specified=true");

        // Get all the planInfoStepList where validType is null
        defaultPlanInfoStepShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoStepShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoStepList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoStepShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoStepList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoStepShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where validBegin is not null
        defaultPlanInfoStepShouldBeFound("validBegin.specified=true");

        // Get all the planInfoStepList where validBegin is null
        defaultPlanInfoStepShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoStepShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoStepList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoStepShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoStepList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoStepShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where validEnd is not null
        defaultPlanInfoStepShouldBeFound("validEnd.specified=true");

        // Get all the planInfoStepList where validEnd is null
        defaultPlanInfoStepShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoStepShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoStepList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoStepShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoStepList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoStepShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where insertTime is not null
        defaultPlanInfoStepShouldBeFound("insertTime.specified=true");

        // Get all the planInfoStepList where insertTime is null
        defaultPlanInfoStepShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoStepShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoStepList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoStepShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoStepList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoStepShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where updateTime is not null
        defaultPlanInfoStepShouldBeFound("updateTime.specified=true");

        // Get all the planInfoStepList where updateTime is null
        defaultPlanInfoStepShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByStepOrderIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where stepOrder equals to DEFAULT_STEP_ORDER
        defaultPlanInfoStepShouldBeFound("stepOrder.equals=" + DEFAULT_STEP_ORDER);

        // Get all the planInfoStepList where stepOrder equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepShouldNotBeFound("stepOrder.equals=" + UPDATED_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByStepOrderIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where stepOrder in DEFAULT_STEP_ORDER or UPDATED_STEP_ORDER
        defaultPlanInfoStepShouldBeFound("stepOrder.in=" + DEFAULT_STEP_ORDER + "," + UPDATED_STEP_ORDER);

        // Get all the planInfoStepList where stepOrder equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepShouldNotBeFound("stepOrder.in=" + UPDATED_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByStepOrderIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where stepOrder is not null
        defaultPlanInfoStepShouldBeFound("stepOrder.specified=true");

        // Get all the planInfoStepList where stepOrder is null
        defaultPlanInfoStepShouldNotBeFound("stepOrder.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByStepOrderIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where stepOrder greater than or equals to DEFAULT_STEP_ORDER
        defaultPlanInfoStepShouldBeFound("stepOrder.greaterOrEqualThan=" + DEFAULT_STEP_ORDER);

        // Get all the planInfoStepList where stepOrder greater than or equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepShouldNotBeFound("stepOrder.greaterOrEqualThan=" + UPDATED_STEP_ORDER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByStepOrderIsLessThanSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where stepOrder less than or equals to DEFAULT_STEP_ORDER
        defaultPlanInfoStepShouldNotBeFound("stepOrder.lessThan=" + DEFAULT_STEP_ORDER);

        // Get all the planInfoStepList where stepOrder less than or equals to UPDATED_STEP_ORDER
        defaultPlanInfoStepShouldBeFound("stepOrder.lessThan=" + UPDATED_STEP_ORDER);
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepsByPublishedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where publishedTime equals to DEFAULT_PUBLISHED_TIME
        defaultPlanInfoStepShouldBeFound("publishedTime.equals=" + DEFAULT_PUBLISHED_TIME);

        // Get all the planInfoStepList where publishedTime equals to UPDATED_PUBLISHED_TIME
        defaultPlanInfoStepShouldNotBeFound("publishedTime.equals=" + UPDATED_PUBLISHED_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByPublishedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where publishedTime in DEFAULT_PUBLISHED_TIME or UPDATED_PUBLISHED_TIME
        defaultPlanInfoStepShouldBeFound("publishedTime.in=" + DEFAULT_PUBLISHED_TIME + "," + UPDATED_PUBLISHED_TIME);

        // Get all the planInfoStepList where publishedTime equals to UPDATED_PUBLISHED_TIME
        defaultPlanInfoStepShouldNotBeFound("publishedTime.in=" + UPDATED_PUBLISHED_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByPublishedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where publishedTime is not null
        defaultPlanInfoStepShouldBeFound("publishedTime.specified=true");

        // Get all the planInfoStepList where publishedTime is null
        defaultPlanInfoStepShouldNotBeFound("publishedTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultPlanInfoStepShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the planInfoStepList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoStepShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultPlanInfoStepShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the planInfoStepList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoStepShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where verifyTime is not null
        defaultPlanInfoStepShouldBeFound("verifyTime.specified=true");

        // Get all the planInfoStepList where verifyTime is null
        defaultPlanInfoStepShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVerifyNeedIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where verifyNeed equals to DEFAULT_VERIFY_NEED
        defaultPlanInfoStepShouldBeFound("verifyNeed.equals=" + DEFAULT_VERIFY_NEED);

        // Get all the planInfoStepList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoStepShouldNotBeFound("verifyNeed.equals=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVerifyNeedIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where verifyNeed in DEFAULT_VERIFY_NEED or UPDATED_VERIFY_NEED
        defaultPlanInfoStepShouldBeFound("verifyNeed.in=" + DEFAULT_VERIFY_NEED + "," + UPDATED_VERIFY_NEED);

        // Get all the planInfoStepList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoStepShouldNotBeFound("verifyNeed.in=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVerifyNeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where verifyNeed is not null
        defaultPlanInfoStepShouldBeFound("verifyNeed.specified=true");

        // Get all the planInfoStepList where verifyNeed is null
        defaultPlanInfoStepShouldNotBeFound("verifyNeed.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVerifyOpinionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where verifyOpinion equals to DEFAULT_VERIFY_OPINION
        defaultPlanInfoStepShouldBeFound("verifyOpinion.equals=" + DEFAULT_VERIFY_OPINION);

        // Get all the planInfoStepList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoStepShouldNotBeFound("verifyOpinion.equals=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVerifyOpinionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where verifyOpinion in DEFAULT_VERIFY_OPINION or UPDATED_VERIFY_OPINION
        defaultPlanInfoStepShouldBeFound("verifyOpinion.in=" + DEFAULT_VERIFY_OPINION + "," + UPDATED_VERIFY_OPINION);

        // Get all the planInfoStepList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoStepShouldNotBeFound("verifyOpinion.in=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVerifyOpinionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where verifyOpinion is not null
        defaultPlanInfoStepShouldBeFound("verifyOpinion.specified=true");

        // Get all the planInfoStepList where verifyOpinion is null
        defaultPlanInfoStepShouldNotBeFound("verifyOpinion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByViewCountIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where viewCount equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoStepShouldBeFound("viewCount.equals=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoStepList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepShouldNotBeFound("viewCount.equals=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByViewCountIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where viewCount in DEFAULT_VIEW_COUNT or UPDATED_VIEW_COUNT
        defaultPlanInfoStepShouldBeFound("viewCount.in=" + DEFAULT_VIEW_COUNT + "," + UPDATED_VIEW_COUNT);

        // Get all the planInfoStepList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepShouldNotBeFound("viewCount.in=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByViewCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where viewCount is not null
        defaultPlanInfoStepShouldBeFound("viewCount.specified=true");

        // Get all the planInfoStepList where viewCount is null
        defaultPlanInfoStepShouldNotBeFound("viewCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByViewCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where viewCount greater than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoStepShouldBeFound("viewCount.greaterOrEqualThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoStepList where viewCount greater than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepShouldNotBeFound("viewCount.greaterOrEqualThan=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByViewCountIsLessThanSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where viewCount less than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoStepShouldNotBeFound("viewCount.lessThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoStepList where viewCount less than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoStepShouldBeFound("viewCount.lessThan=" + UPDATED_VIEW_COUNT);
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepsByViewPermissionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where viewPermission equals to DEFAULT_VIEW_PERMISSION
        defaultPlanInfoStepShouldBeFound("viewPermission.equals=" + DEFAULT_VIEW_PERMISSION);

        // Get all the planInfoStepList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoStepShouldNotBeFound("viewPermission.equals=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByViewPermissionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where viewPermission in DEFAULT_VIEW_PERMISSION or UPDATED_VIEW_PERMISSION
        defaultPlanInfoStepShouldBeFound("viewPermission.in=" + DEFAULT_VIEW_PERMISSION + "," + UPDATED_VIEW_PERMISSION);

        // Get all the planInfoStepList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoStepShouldNotBeFound("viewPermission.in=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByViewPermissionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where viewPermission is not null
        defaultPlanInfoStepShouldBeFound("viewPermission.specified=true");

        // Get all the planInfoStepList where viewPermission is null
        defaultPlanInfoStepShouldNotBeFound("viewPermission.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByViewPermPersionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where viewPermPersion equals to DEFAULT_VIEW_PERM_PERSION
        defaultPlanInfoStepShouldBeFound("viewPermPersion.equals=" + DEFAULT_VIEW_PERM_PERSION);

        // Get all the planInfoStepList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoStepShouldNotBeFound("viewPermPersion.equals=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByViewPermPersionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where viewPermPersion in DEFAULT_VIEW_PERM_PERSION or UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoStepShouldBeFound("viewPermPersion.in=" + DEFAULT_VIEW_PERM_PERSION + "," + UPDATED_VIEW_PERM_PERSION);

        // Get all the planInfoStepList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoStepShouldNotBeFound("viewPermPersion.in=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByViewPermPersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where viewPermPersion is not null
        defaultPlanInfoStepShouldBeFound("viewPermPersion.specified=true");

        // Get all the planInfoStepList where viewPermPersion is null
        defaultPlanInfoStepShouldNotBeFound("viewPermPersion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultPlanInfoStepShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the planInfoStepList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoStepShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultPlanInfoStepShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the planInfoStepList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoStepShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where serialNumber is not null
        defaultPlanInfoStepShouldBeFound("serialNumber.specified=true");

        // Get all the planInfoStepList where serialNumber is null
        defaultPlanInfoStepShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVersionNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where versionNumber equals to DEFAULT_VERSION_NUMBER
        defaultPlanInfoStepShouldBeFound("versionNumber.equals=" + DEFAULT_VERSION_NUMBER);

        // Get all the planInfoStepList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoStepShouldNotBeFound("versionNumber.equals=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVersionNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where versionNumber in DEFAULT_VERSION_NUMBER or UPDATED_VERSION_NUMBER
        defaultPlanInfoStepShouldBeFound("versionNumber.in=" + DEFAULT_VERSION_NUMBER + "," + UPDATED_VERSION_NUMBER);

        // Get all the planInfoStepList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoStepShouldNotBeFound("versionNumber.in=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByVersionNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        // Get all the planInfoStepList where versionNumber is not null
        defaultPlanInfoStepShouldBeFound("versionNumber.specified=true");

        // Get all the planInfoStepList where versionNumber is null
        defaultPlanInfoStepShouldNotBeFound("versionNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfoStepsByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfoStep.setCreatedBy(createdBy);
        planInfoStepRepository.saveAndFlush(planInfoStep);
        Long createdById = createdBy.getId();

        // Get all the planInfoStepList where createdBy equals to createdById
        defaultPlanInfoStepShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoStepList where createdBy equals to createdById + 1
        defaultPlanInfoStepShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepsByCreatedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep createdDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(createdDepBy);
        em.flush();
        planInfoStep.setCreatedDepBy(createdDepBy);
        planInfoStepRepository.saveAndFlush(planInfoStep);
        Long createdDepById = createdDepBy.getId();

        // Get all the planInfoStepList where createdDepBy equals to createdDepById
        defaultPlanInfoStepShouldBeFound("createdDepById.equals=" + createdDepById);

        // Get all the planInfoStepList where createdDepBy equals to createdDepById + 1
        defaultPlanInfoStepShouldNotBeFound("createdDepById.equals=" + (createdDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepsByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfoStep.setModifiedBy(modifiedBy);
        planInfoStepRepository.saveAndFlush(planInfoStep);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoStepList where modifiedBy equals to modifiedById
        defaultPlanInfoStepShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoStepList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoStepShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepsByModifiedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep modifiedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(modifiedDepBy);
        em.flush();
        planInfoStep.setModifiedDepBy(modifiedDepBy);
        planInfoStepRepository.saveAndFlush(planInfoStep);
        Long modifiedDepById = modifiedDepBy.getId();

        // Get all the planInfoStepList where modifiedDepBy equals to modifiedDepById
        defaultPlanInfoStepShouldBeFound("modifiedDepById.equals=" + modifiedDepById);

        // Get all the planInfoStepList where modifiedDepBy equals to modifiedDepById + 1
        defaultPlanInfoStepShouldNotBeFound("modifiedDepById.equals=" + (modifiedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepsByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        planInfoStep.setVerifiedBy(verifiedBy);
        planInfoStepRepository.saveAndFlush(planInfoStep);
        Long verifiedById = verifiedBy.getId();

        // Get all the planInfoStepList where verifiedBy equals to verifiedById
        defaultPlanInfoStepShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the planInfoStepList where verifiedBy equals to verifiedById + 1
        defaultPlanInfoStepShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfoStepsByPlanInfoIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfo planInfo = PlanInfoResourceIntTest.createEntity(em);
        em.persist(planInfo);
        em.flush();
        planInfoStep.setPlanInfo(planInfo);
        planInfoStepRepository.saveAndFlush(planInfoStep);
        Long planInfoId = planInfo.getId();

        // Get all the planInfoStepList where planInfo equals to planInfoId
        defaultPlanInfoStepShouldBeFound("planInfoId.equals=" + planInfoId);

        // Get all the planInfoStepList where planInfo equals to planInfoId + 1
        defaultPlanInfoStepShouldNotBeFound("planInfoId.equals=" + (planInfoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoStepShouldBeFound(String filter) throws Exception {
        restPlanInfoStepMockMvc.perform(get("/api/plan-info-steps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStep.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].stepOrder").value(hasItem(DEFAULT_STEP_ORDER)))
            .andExpect(jsonPath("$.[*].publishedTime").value(hasItem(DEFAULT_PUBLISHED_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyNeed").value(hasItem(DEFAULT_VERIFY_NEED.booleanValue())))
            .andExpect(jsonPath("$.[*].verifyOpinion").value(hasItem(DEFAULT_VERIFY_OPINION.toString())))
            .andExpect(jsonPath("$.[*].viewCount").value(hasItem(DEFAULT_VIEW_COUNT)))
            .andExpect(jsonPath("$.[*].viewPermission").value(hasItem(DEFAULT_VIEW_PERMISSION.toString())))
            .andExpect(jsonPath("$.[*].viewPermPersion").value(hasItem(DEFAULT_VIEW_PERM_PERSION.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].versionNumber").value(hasItem(DEFAULT_VERSION_NUMBER.toString())));

        // Check, that the count call also returns 1
        restPlanInfoStepMockMvc.perform(get("/api/plan-info-steps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoStepShouldNotBeFound(String filter) throws Exception {
        restPlanInfoStepMockMvc.perform(get("/api/plan-info-steps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoStepMockMvc.perform(get("/api/plan-info-steps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfoStep() throws Exception {
        // Get the planInfoStep
        restPlanInfoStepMockMvc.perform(get("/api/plan-info-steps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfoStep() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        int databaseSizeBeforeUpdate = planInfoStepRepository.findAll().size();

        // Update the planInfoStep
        PlanInfoStep updatedPlanInfoStep = planInfoStepRepository.findById(planInfoStep.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfoStep are not directly saved in db
        em.detach(updatedPlanInfoStep);
        updatedPlanInfoStep
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
            .stepOrder(UPDATED_STEP_ORDER)
            .publishedTime(UPDATED_PUBLISHED_TIME)
            .verifyTime(UPDATED_VERIFY_TIME)
            .verifyNeed(UPDATED_VERIFY_NEED)
            .verifyOpinion(UPDATED_VERIFY_OPINION)
            .viewCount(UPDATED_VIEW_COUNT)
            .viewPermission(UPDATED_VIEW_PERMISSION)
            .viewPermPersion(UPDATED_VIEW_PERM_PERSION)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .versionNumber(UPDATED_VERSION_NUMBER);
        PlanInfoStepDTO planInfoStepDTO = planInfoStepMapper.toDto(updatedPlanInfoStep);

        restPlanInfoStepMockMvc.perform(put("/api/plan-info-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfoStep in the database
        List<PlanInfoStep> planInfoStepList = planInfoStepRepository.findAll();
        assertThat(planInfoStepList).hasSize(databaseSizeBeforeUpdate);
        PlanInfoStep testPlanInfoStep = planInfoStepList.get(planInfoStepList.size() - 1);
        assertThat(testPlanInfoStep.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfoStep.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfoStep.getDescString()).isEqualTo(UPDATED_DESC_STRING);
        assertThat(testPlanInfoStep.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfoStep.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfoStep.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfoStep.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfoStep.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStep.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfoStep.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfoStep.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfoStep.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfoStep.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfoStep.isUsingFlag()).isEqualTo(UPDATED_USING_FLAG);
        assertThat(testPlanInfoStep.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfoStep.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfoStep.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfoStep.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfoStep.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPlanInfoStep.getStepOrder()).isEqualTo(UPDATED_STEP_ORDER);
        assertThat(testPlanInfoStep.getPublishedTime()).isEqualTo(UPDATED_PUBLISHED_TIME);
        assertThat(testPlanInfoStep.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);
        assertThat(testPlanInfoStep.isVerifyNeed()).isEqualTo(UPDATED_VERIFY_NEED);
        assertThat(testPlanInfoStep.getVerifyOpinion()).isEqualTo(UPDATED_VERIFY_OPINION);
        assertThat(testPlanInfoStep.getViewCount()).isEqualTo(UPDATED_VIEW_COUNT);
        assertThat(testPlanInfoStep.getViewPermission()).isEqualTo(UPDATED_VIEW_PERMISSION);
        assertThat(testPlanInfoStep.getViewPermPersion()).isEqualTo(UPDATED_VIEW_PERM_PERSION);
        assertThat(testPlanInfoStep.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testPlanInfoStep.getVersionNumber()).isEqualTo(UPDATED_VERSION_NUMBER);

        // Validate the PlanInfoStep in Elasticsearch
        verify(mockPlanInfoStepSearchRepository, times(1)).save(testPlanInfoStep);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfoStep() throws Exception {
        int databaseSizeBeforeUpdate = planInfoStepRepository.findAll().size();

        // Create the PlanInfoStep
        PlanInfoStepDTO planInfoStepDTO = planInfoStepMapper.toDto(planInfoStep);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoStepMockMvc.perform(put("/api/plan-info-steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoStepDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfoStep in the database
        List<PlanInfoStep> planInfoStepList = planInfoStepRepository.findAll();
        assertThat(planInfoStepList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfoStep in Elasticsearch
        verify(mockPlanInfoStepSearchRepository, times(0)).save(planInfoStep);
    }

    @Test
    @Transactional
    public void deletePlanInfoStep() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);

        int databaseSizeBeforeDelete = planInfoStepRepository.findAll().size();

        // Get the planInfoStep
        restPlanInfoStepMockMvc.perform(delete("/api/plan-info-steps/{id}", planInfoStep.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfoStep> planInfoStepList = planInfoStepRepository.findAll();
        assertThat(planInfoStepList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfoStep in Elasticsearch
        verify(mockPlanInfoStepSearchRepository, times(1)).deleteById(planInfoStep.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfoStep() throws Exception {
        // Initialize the database
        planInfoStepRepository.saveAndFlush(planInfoStep);
        when(mockPlanInfoStepSearchRepository.search(queryStringQuery("id:" + planInfoStep.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfoStep), PageRequest.of(0, 1), 1));
        // Search the planInfoStep
        restPlanInfoStepMockMvc.perform(get("/api/_search/plan-info-steps?query=id:" + planInfoStep.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfoStep.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].stepOrder").value(hasItem(DEFAULT_STEP_ORDER)))
            .andExpect(jsonPath("$.[*].publishedTime").value(hasItem(DEFAULT_PUBLISHED_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyNeed").value(hasItem(DEFAULT_VERIFY_NEED.booleanValue())))
            .andExpect(jsonPath("$.[*].verifyOpinion").value(hasItem(DEFAULT_VERIFY_OPINION)))
            .andExpect(jsonPath("$.[*].viewCount").value(hasItem(DEFAULT_VIEW_COUNT)))
            .andExpect(jsonPath("$.[*].viewPermission").value(hasItem(DEFAULT_VIEW_PERMISSION.toString())))
            .andExpect(jsonPath("$.[*].viewPermPersion").value(hasItem(DEFAULT_VIEW_PERM_PERSION)))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)))
            .andExpect(jsonPath("$.[*].versionNumber").value(hasItem(DEFAULT_VERSION_NUMBER)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoStep.class);
        PlanInfoStep planInfoStep1 = new PlanInfoStep();
        planInfoStep1.setId(1L);
        PlanInfoStep planInfoStep2 = new PlanInfoStep();
        planInfoStep2.setId(planInfoStep1.getId());
        assertThat(planInfoStep1).isEqualTo(planInfoStep2);
        planInfoStep2.setId(2L);
        assertThat(planInfoStep1).isNotEqualTo(planInfoStep2);
        planInfoStep1.setId(null);
        assertThat(planInfoStep1).isNotEqualTo(planInfoStep2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoStepDTO.class);
        PlanInfoStepDTO planInfoStepDTO1 = new PlanInfoStepDTO();
        planInfoStepDTO1.setId(1L);
        PlanInfoStepDTO planInfoStepDTO2 = new PlanInfoStepDTO();
        assertThat(planInfoStepDTO1).isNotEqualTo(planInfoStepDTO2);
        planInfoStepDTO2.setId(planInfoStepDTO1.getId());
        assertThat(planInfoStepDTO1).isEqualTo(planInfoStepDTO2);
        planInfoStepDTO2.setId(2L);
        assertThat(planInfoStepDTO1).isNotEqualTo(planInfoStepDTO2);
        planInfoStepDTO1.setId(null);
        assertThat(planInfoStepDTO1).isNotEqualTo(planInfoStepDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoStepMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoStepMapper.fromId(null)).isNull();
    }
}
