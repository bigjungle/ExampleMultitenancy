package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.PlanInfo;
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
import com.aerothinker.plandb.domain.PlanInfo;
import com.aerothinker.plandb.domain.Company;
import com.aerothinker.plandb.repository.PlanInfoRepository;
import com.aerothinker.plandb.repository.search.PlanInfoSearchRepository;
import com.aerothinker.plandb.service.PlanInfoService;
import com.aerothinker.plandb.service.dto.PlanInfoDTO;
import com.aerothinker.plandb.service.mapper.PlanInfoMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.PlanInfoCriteria;
import com.aerothinker.plandb.service.PlanInfoQueryService;

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
 * Test class for the PlanInfoResource REST controller.
 *
 * @see PlanInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class PlanInfoResourceIntTest {

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

    private static final String DEFAULT_PARA_SOURCE_STRING = "AAAAAAAAAA";
    private static final String UPDATED_PARA_SOURCE_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_FEATURE_KEYWORD = "AAAAAAAAAA";
    private static final String UPDATED_FEATURE_KEYWORD = "BBBBBBBBBB";

    private static final String DEFAULT_SUGGESTION = "AAAAAAAAAA";
    private static final String UPDATED_SUGGESTION = "BBBBBBBBBB";

    private static final String DEFAULT_RELEASE_SCOPE = "AAAAAAAAAA";
    private static final String UPDATED_RELEASE_SCOPE = "BBBBBBBBBB";

    @Autowired
    private PlanInfoRepository planInfoRepository;

    @Autowired
    private PlanInfoMapper planInfoMapper;

    @Autowired
    private PlanInfoService planInfoService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.PlanInfoSearchRepositoryMockConfiguration
     */
    @Autowired
    private PlanInfoSearchRepository mockPlanInfoSearchRepository;

    @Autowired
    private PlanInfoQueryService planInfoQueryService;

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

    private MockMvc restPlanInfoMockMvc;

    private PlanInfo planInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanInfoResource planInfoResource = new PlanInfoResource(planInfoService, planInfoQueryService);
        this.restPlanInfoMockMvc = MockMvcBuilders.standaloneSetup(planInfoResource)
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
    public static PlanInfo createEntity(EntityManager em) {
        PlanInfo planInfo = new PlanInfo()
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
            .publishedTime(DEFAULT_PUBLISHED_TIME)
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
            .releaseScope(DEFAULT_RELEASE_SCOPE);
        return planInfo;
    }

    @Before
    public void initTest() {
        planInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlanInfo() throws Exception {
        int databaseSizeBeforeCreate = planInfoRepository.findAll().size();

        // Create the PlanInfo
        PlanInfoDTO planInfoDTO = planInfoMapper.toDto(planInfo);
        restPlanInfoMockMvc.perform(post("/api/plan-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the PlanInfo in the database
        List<PlanInfo> planInfoList = planInfoRepository.findAll();
        assertThat(planInfoList).hasSize(databaseSizeBeforeCreate + 1);
        PlanInfo testPlanInfo = planInfoList.get(planInfoList.size() - 1);
        assertThat(testPlanInfo.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPlanInfo.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testPlanInfo.getDescString()).isEqualTo(DEFAULT_DESC_STRING);
        assertThat(testPlanInfo.getJsonString()).isEqualTo(DEFAULT_JSON_STRING);
        assertThat(testPlanInfo.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testPlanInfo.getRefEvent()).isEqualTo(DEFAULT_REF_EVENT);
        assertThat(testPlanInfo.getAttachmentPath()).isEqualTo(DEFAULT_ATTACHMENT_PATH);
        assertThat(testPlanInfo.getAttachmentBlob()).isEqualTo(DEFAULT_ATTACHMENT_BLOB);
        assertThat(testPlanInfo.getAttachmentBlobContentType()).isEqualTo(DEFAULT_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfo.getAttachmentName()).isEqualTo(DEFAULT_ATTACHMENT_NAME);
        assertThat(testPlanInfo.getTextBlob()).isEqualTo(DEFAULT_TEXT_BLOB);
        assertThat(testPlanInfo.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testPlanInfo.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfo.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testPlanInfo.isUsingFlag()).isEqualTo(DEFAULT_USING_FLAG);
        assertThat(testPlanInfo.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testPlanInfo.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testPlanInfo.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testPlanInfo.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testPlanInfo.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testPlanInfo.getPublishedTime()).isEqualTo(DEFAULT_PUBLISHED_TIME);
        assertThat(testPlanInfo.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);
        assertThat(testPlanInfo.isVerifyNeed()).isEqualTo(DEFAULT_VERIFY_NEED);
        assertThat(testPlanInfo.getVerifyOpinion()).isEqualTo(DEFAULT_VERIFY_OPINION);
        assertThat(testPlanInfo.getViewCount()).isEqualTo(DEFAULT_VIEW_COUNT);
        assertThat(testPlanInfo.getViewPermission()).isEqualTo(DEFAULT_VIEW_PERMISSION);
        assertThat(testPlanInfo.getViewPermPersion()).isEqualTo(DEFAULT_VIEW_PERM_PERSION);
        assertThat(testPlanInfo.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testPlanInfo.getVersionNumber()).isEqualTo(DEFAULT_VERSION_NUMBER);
        assertThat(testPlanInfo.getParaSourceString()).isEqualTo(DEFAULT_PARA_SOURCE_STRING);
        assertThat(testPlanInfo.getFeatureKeyword()).isEqualTo(DEFAULT_FEATURE_KEYWORD);
        assertThat(testPlanInfo.getSuggestion()).isEqualTo(DEFAULT_SUGGESTION);
        assertThat(testPlanInfo.getReleaseScope()).isEqualTo(DEFAULT_RELEASE_SCOPE);

        // Validate the PlanInfo in Elasticsearch
        verify(mockPlanInfoSearchRepository, times(1)).save(testPlanInfo);
    }

    @Test
    @Transactional
    public void createPlanInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planInfoRepository.findAll().size();

        // Create the PlanInfo with an existing ID
        planInfo.setId(1L);
        PlanInfoDTO planInfoDTO = planInfoMapper.toDto(planInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanInfoMockMvc.perform(post("/api/plan-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfo in the database
        List<PlanInfo> planInfoList = planInfoRepository.findAll();
        assertThat(planInfoList).hasSize(databaseSizeBeforeCreate);

        // Validate the PlanInfo in Elasticsearch
        verify(mockPlanInfoSearchRepository, times(0)).save(planInfo);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = planInfoRepository.findAll().size();
        // set the field null
        planInfo.setName(null);

        // Create the PlanInfo, which fails.
        PlanInfoDTO planInfoDTO = planInfoMapper.toDto(planInfo);

        restPlanInfoMockMvc.perform(post("/api/plan-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDTO)))
            .andExpect(status().isBadRequest());

        List<PlanInfo> planInfoList = planInfoRepository.findAll();
        assertThat(planInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlanInfos() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList
        restPlanInfoMockMvc.perform(get("/api/plan-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfo.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].publishedTime").value(hasItem(DEFAULT_PUBLISHED_TIME.toString())))
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
            .andExpect(jsonPath("$.[*].releaseScope").value(hasItem(DEFAULT_RELEASE_SCOPE.toString())));
    }
    
    @Test
    @Transactional
    public void getPlanInfo() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get the planInfo
        restPlanInfoMockMvc.perform(get("/api/plan-infos/{id}", planInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planInfo.getId().intValue()))
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
            .andExpect(jsonPath("$.publishedTime").value(DEFAULT_PUBLISHED_TIME.toString()))
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
            .andExpect(jsonPath("$.releaseScope").value(DEFAULT_RELEASE_SCOPE.toString()));
    }

    @Test
    @Transactional
    public void getAllPlanInfosByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where name equals to DEFAULT_NAME
        defaultPlanInfoShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the planInfoList where name equals to UPDATED_NAME
        defaultPlanInfoShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPlanInfoShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the planInfoList where name equals to UPDATED_NAME
        defaultPlanInfoShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where name is not null
        defaultPlanInfoShouldBeFound("name.specified=true");

        // Get all the planInfoList where name is null
        defaultPlanInfoShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where sortString equals to DEFAULT_SORT_STRING
        defaultPlanInfoShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the planInfoList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfosBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultPlanInfoShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the planInfoList where sortString equals to UPDATED_SORT_STRING
        defaultPlanInfoShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfosBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where sortString is not null
        defaultPlanInfoShouldBeFound("sortString.specified=true");

        // Get all the planInfoList where sortString is null
        defaultPlanInfoShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByDescStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where descString equals to DEFAULT_DESC_STRING
        defaultPlanInfoShouldBeFound("descString.equals=" + DEFAULT_DESC_STRING);

        // Get all the planInfoList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoShouldNotBeFound("descString.equals=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByDescStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where descString in DEFAULT_DESC_STRING or UPDATED_DESC_STRING
        defaultPlanInfoShouldBeFound("descString.in=" + DEFAULT_DESC_STRING + "," + UPDATED_DESC_STRING);

        // Get all the planInfoList where descString equals to UPDATED_DESC_STRING
        defaultPlanInfoShouldNotBeFound("descString.in=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByDescStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where descString is not null
        defaultPlanInfoShouldBeFound("descString.specified=true");

        // Get all the planInfoList where descString is null
        defaultPlanInfoShouldNotBeFound("descString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByJsonStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where jsonString equals to DEFAULT_JSON_STRING
        defaultPlanInfoShouldBeFound("jsonString.equals=" + DEFAULT_JSON_STRING);

        // Get all the planInfoList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoShouldNotBeFound("jsonString.equals=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByJsonStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where jsonString in DEFAULT_JSON_STRING or UPDATED_JSON_STRING
        defaultPlanInfoShouldBeFound("jsonString.in=" + DEFAULT_JSON_STRING + "," + UPDATED_JSON_STRING);

        // Get all the planInfoList where jsonString equals to UPDATED_JSON_STRING
        defaultPlanInfoShouldNotBeFound("jsonString.in=" + UPDATED_JSON_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByJsonStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where jsonString is not null
        defaultPlanInfoShouldBeFound("jsonString.specified=true");

        // Get all the planInfoList where jsonString is null
        defaultPlanInfoShouldNotBeFound("jsonString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where remarks equals to DEFAULT_REMARKS
        defaultPlanInfoShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the planInfoList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultPlanInfoShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the planInfoList where remarks equals to UPDATED_REMARKS
        defaultPlanInfoShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where remarks is not null
        defaultPlanInfoShouldBeFound("remarks.specified=true");

        // Get all the planInfoList where remarks is null
        defaultPlanInfoShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByRefEventIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where refEvent equals to DEFAULT_REF_EVENT
        defaultPlanInfoShouldBeFound("refEvent.equals=" + DEFAULT_REF_EVENT);

        // Get all the planInfoList where refEvent equals to UPDATED_REF_EVENT
        defaultPlanInfoShouldNotBeFound("refEvent.equals=" + UPDATED_REF_EVENT);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByRefEventIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where refEvent in DEFAULT_REF_EVENT or UPDATED_REF_EVENT
        defaultPlanInfoShouldBeFound("refEvent.in=" + DEFAULT_REF_EVENT + "," + UPDATED_REF_EVENT);

        // Get all the planInfoList where refEvent equals to UPDATED_REF_EVENT
        defaultPlanInfoShouldNotBeFound("refEvent.in=" + UPDATED_REF_EVENT);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByRefEventIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where refEvent is not null
        defaultPlanInfoShouldBeFound("refEvent.specified=true");

        // Get all the planInfoList where refEvent is null
        defaultPlanInfoShouldNotBeFound("refEvent.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByAttachmentPathIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where attachmentPath equals to DEFAULT_ATTACHMENT_PATH
        defaultPlanInfoShouldBeFound("attachmentPath.equals=" + DEFAULT_ATTACHMENT_PATH);

        // Get all the planInfoList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoShouldNotBeFound("attachmentPath.equals=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByAttachmentPathIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where attachmentPath in DEFAULT_ATTACHMENT_PATH or UPDATED_ATTACHMENT_PATH
        defaultPlanInfoShouldBeFound("attachmentPath.in=" + DEFAULT_ATTACHMENT_PATH + "," + UPDATED_ATTACHMENT_PATH);

        // Get all the planInfoList where attachmentPath equals to UPDATED_ATTACHMENT_PATH
        defaultPlanInfoShouldNotBeFound("attachmentPath.in=" + UPDATED_ATTACHMENT_PATH);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByAttachmentPathIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where attachmentPath is not null
        defaultPlanInfoShouldBeFound("attachmentPath.specified=true");

        // Get all the planInfoList where attachmentPath is null
        defaultPlanInfoShouldNotBeFound("attachmentPath.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByAttachmentNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where attachmentName equals to DEFAULT_ATTACHMENT_NAME
        defaultPlanInfoShouldBeFound("attachmentName.equals=" + DEFAULT_ATTACHMENT_NAME);

        // Get all the planInfoList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoShouldNotBeFound("attachmentName.equals=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByAttachmentNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where attachmentName in DEFAULT_ATTACHMENT_NAME or UPDATED_ATTACHMENT_NAME
        defaultPlanInfoShouldBeFound("attachmentName.in=" + DEFAULT_ATTACHMENT_NAME + "," + UPDATED_ATTACHMENT_NAME);

        // Get all the planInfoList where attachmentName equals to UPDATED_ATTACHMENT_NAME
        defaultPlanInfoShouldNotBeFound("attachmentName.in=" + UPDATED_ATTACHMENT_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByAttachmentNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where attachmentName is not null
        defaultPlanInfoShouldBeFound("attachmentName.specified=true");

        // Get all the planInfoList where attachmentName is null
        defaultPlanInfoShouldNotBeFound("attachmentName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultPlanInfoShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the planInfoList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the planInfoList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultPlanInfoShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where imageBlobName is not null
        defaultPlanInfoShouldBeFound("imageBlobName.specified=true");

        // Get all the planInfoList where imageBlobName is null
        defaultPlanInfoShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByUsingFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where usingFlag equals to DEFAULT_USING_FLAG
        defaultPlanInfoShouldBeFound("usingFlag.equals=" + DEFAULT_USING_FLAG);

        // Get all the planInfoList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoShouldNotBeFound("usingFlag.equals=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByUsingFlagIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where usingFlag in DEFAULT_USING_FLAG or UPDATED_USING_FLAG
        defaultPlanInfoShouldBeFound("usingFlag.in=" + DEFAULT_USING_FLAG + "," + UPDATED_USING_FLAG);

        // Get all the planInfoList where usingFlag equals to UPDATED_USING_FLAG
        defaultPlanInfoShouldNotBeFound("usingFlag.in=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByUsingFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where usingFlag is not null
        defaultPlanInfoShouldBeFound("usingFlag.specified=true");

        // Get all the planInfoList where usingFlag is null
        defaultPlanInfoShouldNotBeFound("usingFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where validType equals to DEFAULT_VALID_TYPE
        defaultPlanInfoShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the planInfoList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultPlanInfoShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the planInfoList where validType equals to UPDATED_VALID_TYPE
        defaultPlanInfoShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where validType is not null
        defaultPlanInfoShouldBeFound("validType.specified=true");

        // Get all the planInfoList where validType is null
        defaultPlanInfoShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultPlanInfoShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the planInfoList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultPlanInfoShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the planInfoList where validBegin equals to UPDATED_VALID_BEGIN
        defaultPlanInfoShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where validBegin is not null
        defaultPlanInfoShouldBeFound("validBegin.specified=true");

        // Get all the planInfoList where validBegin is null
        defaultPlanInfoShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where validEnd equals to DEFAULT_VALID_END
        defaultPlanInfoShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the planInfoList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultPlanInfoShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the planInfoList where validEnd equals to UPDATED_VALID_END
        defaultPlanInfoShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where validEnd is not null
        defaultPlanInfoShouldBeFound("validEnd.specified=true");

        // Get all the planInfoList where validEnd is null
        defaultPlanInfoShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where insertTime equals to DEFAULT_INSERT_TIME
        defaultPlanInfoShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the planInfoList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultPlanInfoShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the planInfoList where insertTime equals to UPDATED_INSERT_TIME
        defaultPlanInfoShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where insertTime is not null
        defaultPlanInfoShouldBeFound("insertTime.specified=true");

        // Get all the planInfoList where insertTime is null
        defaultPlanInfoShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultPlanInfoShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the planInfoList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultPlanInfoShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the planInfoList where updateTime equals to UPDATED_UPDATE_TIME
        defaultPlanInfoShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where updateTime is not null
        defaultPlanInfoShouldBeFound("updateTime.specified=true");

        // Get all the planInfoList where updateTime is null
        defaultPlanInfoShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByPublishedTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where publishedTime equals to DEFAULT_PUBLISHED_TIME
        defaultPlanInfoShouldBeFound("publishedTime.equals=" + DEFAULT_PUBLISHED_TIME);

        // Get all the planInfoList where publishedTime equals to UPDATED_PUBLISHED_TIME
        defaultPlanInfoShouldNotBeFound("publishedTime.equals=" + UPDATED_PUBLISHED_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByPublishedTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where publishedTime in DEFAULT_PUBLISHED_TIME or UPDATED_PUBLISHED_TIME
        defaultPlanInfoShouldBeFound("publishedTime.in=" + DEFAULT_PUBLISHED_TIME + "," + UPDATED_PUBLISHED_TIME);

        // Get all the planInfoList where publishedTime equals to UPDATED_PUBLISHED_TIME
        defaultPlanInfoShouldNotBeFound("publishedTime.in=" + UPDATED_PUBLISHED_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByPublishedTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where publishedTime is not null
        defaultPlanInfoShouldBeFound("publishedTime.specified=true");

        // Get all the planInfoList where publishedTime is null
        defaultPlanInfoShouldNotBeFound("publishedTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultPlanInfoShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the planInfoList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultPlanInfoShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the planInfoList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultPlanInfoShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where verifyTime is not null
        defaultPlanInfoShouldBeFound("verifyTime.specified=true");

        // Get all the planInfoList where verifyTime is null
        defaultPlanInfoShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVerifyNeedIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where verifyNeed equals to DEFAULT_VERIFY_NEED
        defaultPlanInfoShouldBeFound("verifyNeed.equals=" + DEFAULT_VERIFY_NEED);

        // Get all the planInfoList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoShouldNotBeFound("verifyNeed.equals=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVerifyNeedIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where verifyNeed in DEFAULT_VERIFY_NEED or UPDATED_VERIFY_NEED
        defaultPlanInfoShouldBeFound("verifyNeed.in=" + DEFAULT_VERIFY_NEED + "," + UPDATED_VERIFY_NEED);

        // Get all the planInfoList where verifyNeed equals to UPDATED_VERIFY_NEED
        defaultPlanInfoShouldNotBeFound("verifyNeed.in=" + UPDATED_VERIFY_NEED);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVerifyNeedIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where verifyNeed is not null
        defaultPlanInfoShouldBeFound("verifyNeed.specified=true");

        // Get all the planInfoList where verifyNeed is null
        defaultPlanInfoShouldNotBeFound("verifyNeed.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVerifyOpinionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where verifyOpinion equals to DEFAULT_VERIFY_OPINION
        defaultPlanInfoShouldBeFound("verifyOpinion.equals=" + DEFAULT_VERIFY_OPINION);

        // Get all the planInfoList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoShouldNotBeFound("verifyOpinion.equals=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVerifyOpinionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where verifyOpinion in DEFAULT_VERIFY_OPINION or UPDATED_VERIFY_OPINION
        defaultPlanInfoShouldBeFound("verifyOpinion.in=" + DEFAULT_VERIFY_OPINION + "," + UPDATED_VERIFY_OPINION);

        // Get all the planInfoList where verifyOpinion equals to UPDATED_VERIFY_OPINION
        defaultPlanInfoShouldNotBeFound("verifyOpinion.in=" + UPDATED_VERIFY_OPINION);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVerifyOpinionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where verifyOpinion is not null
        defaultPlanInfoShouldBeFound("verifyOpinion.specified=true");

        // Get all the planInfoList where verifyOpinion is null
        defaultPlanInfoShouldNotBeFound("verifyOpinion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByViewCountIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where viewCount equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoShouldBeFound("viewCount.equals=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoShouldNotBeFound("viewCount.equals=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByViewCountIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where viewCount in DEFAULT_VIEW_COUNT or UPDATED_VIEW_COUNT
        defaultPlanInfoShouldBeFound("viewCount.in=" + DEFAULT_VIEW_COUNT + "," + UPDATED_VIEW_COUNT);

        // Get all the planInfoList where viewCount equals to UPDATED_VIEW_COUNT
        defaultPlanInfoShouldNotBeFound("viewCount.in=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByViewCountIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where viewCount is not null
        defaultPlanInfoShouldBeFound("viewCount.specified=true");

        // Get all the planInfoList where viewCount is null
        defaultPlanInfoShouldNotBeFound("viewCount.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByViewCountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where viewCount greater than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoShouldBeFound("viewCount.greaterOrEqualThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoList where viewCount greater than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoShouldNotBeFound("viewCount.greaterOrEqualThan=" + UPDATED_VIEW_COUNT);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByViewCountIsLessThanSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where viewCount less than or equals to DEFAULT_VIEW_COUNT
        defaultPlanInfoShouldNotBeFound("viewCount.lessThan=" + DEFAULT_VIEW_COUNT);

        // Get all the planInfoList where viewCount less than or equals to UPDATED_VIEW_COUNT
        defaultPlanInfoShouldBeFound("viewCount.lessThan=" + UPDATED_VIEW_COUNT);
    }


    @Test
    @Transactional
    public void getAllPlanInfosByViewPermissionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where viewPermission equals to DEFAULT_VIEW_PERMISSION
        defaultPlanInfoShouldBeFound("viewPermission.equals=" + DEFAULT_VIEW_PERMISSION);

        // Get all the planInfoList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoShouldNotBeFound("viewPermission.equals=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByViewPermissionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where viewPermission in DEFAULT_VIEW_PERMISSION or UPDATED_VIEW_PERMISSION
        defaultPlanInfoShouldBeFound("viewPermission.in=" + DEFAULT_VIEW_PERMISSION + "," + UPDATED_VIEW_PERMISSION);

        // Get all the planInfoList where viewPermission equals to UPDATED_VIEW_PERMISSION
        defaultPlanInfoShouldNotBeFound("viewPermission.in=" + UPDATED_VIEW_PERMISSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByViewPermissionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where viewPermission is not null
        defaultPlanInfoShouldBeFound("viewPermission.specified=true");

        // Get all the planInfoList where viewPermission is null
        defaultPlanInfoShouldNotBeFound("viewPermission.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByViewPermPersionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where viewPermPersion equals to DEFAULT_VIEW_PERM_PERSION
        defaultPlanInfoShouldBeFound("viewPermPersion.equals=" + DEFAULT_VIEW_PERM_PERSION);

        // Get all the planInfoList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoShouldNotBeFound("viewPermPersion.equals=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByViewPermPersionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where viewPermPersion in DEFAULT_VIEW_PERM_PERSION or UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoShouldBeFound("viewPermPersion.in=" + DEFAULT_VIEW_PERM_PERSION + "," + UPDATED_VIEW_PERM_PERSION);

        // Get all the planInfoList where viewPermPersion equals to UPDATED_VIEW_PERM_PERSION
        defaultPlanInfoShouldNotBeFound("viewPermPersion.in=" + UPDATED_VIEW_PERM_PERSION);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByViewPermPersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where viewPermPersion is not null
        defaultPlanInfoShouldBeFound("viewPermPersion.specified=true");

        // Get all the planInfoList where viewPermPersion is null
        defaultPlanInfoShouldNotBeFound("viewPermPersion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultPlanInfoShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the planInfoList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfosBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultPlanInfoShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the planInfoList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultPlanInfoShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfosBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where serialNumber is not null
        defaultPlanInfoShouldBeFound("serialNumber.specified=true");

        // Get all the planInfoList where serialNumber is null
        defaultPlanInfoShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVersionNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where versionNumber equals to DEFAULT_VERSION_NUMBER
        defaultPlanInfoShouldBeFound("versionNumber.equals=" + DEFAULT_VERSION_NUMBER);

        // Get all the planInfoList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoShouldNotBeFound("versionNumber.equals=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVersionNumberIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where versionNumber in DEFAULT_VERSION_NUMBER or UPDATED_VERSION_NUMBER
        defaultPlanInfoShouldBeFound("versionNumber.in=" + DEFAULT_VERSION_NUMBER + "," + UPDATED_VERSION_NUMBER);

        // Get all the planInfoList where versionNumber equals to UPDATED_VERSION_NUMBER
        defaultPlanInfoShouldNotBeFound("versionNumber.in=" + UPDATED_VERSION_NUMBER);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVersionNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where versionNumber is not null
        defaultPlanInfoShouldBeFound("versionNumber.specified=true");

        // Get all the planInfoList where versionNumber is null
        defaultPlanInfoShouldNotBeFound("versionNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByParaSourceStringIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where paraSourceString equals to DEFAULT_PARA_SOURCE_STRING
        defaultPlanInfoShouldBeFound("paraSourceString.equals=" + DEFAULT_PARA_SOURCE_STRING);

        // Get all the planInfoList where paraSourceString equals to UPDATED_PARA_SOURCE_STRING
        defaultPlanInfoShouldNotBeFound("paraSourceString.equals=" + UPDATED_PARA_SOURCE_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByParaSourceStringIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where paraSourceString in DEFAULT_PARA_SOURCE_STRING or UPDATED_PARA_SOURCE_STRING
        defaultPlanInfoShouldBeFound("paraSourceString.in=" + DEFAULT_PARA_SOURCE_STRING + "," + UPDATED_PARA_SOURCE_STRING);

        // Get all the planInfoList where paraSourceString equals to UPDATED_PARA_SOURCE_STRING
        defaultPlanInfoShouldNotBeFound("paraSourceString.in=" + UPDATED_PARA_SOURCE_STRING);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByParaSourceStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where paraSourceString is not null
        defaultPlanInfoShouldBeFound("paraSourceString.specified=true");

        // Get all the planInfoList where paraSourceString is null
        defaultPlanInfoShouldNotBeFound("paraSourceString.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByFeatureKeywordIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where featureKeyword equals to DEFAULT_FEATURE_KEYWORD
        defaultPlanInfoShouldBeFound("featureKeyword.equals=" + DEFAULT_FEATURE_KEYWORD);

        // Get all the planInfoList where featureKeyword equals to UPDATED_FEATURE_KEYWORD
        defaultPlanInfoShouldNotBeFound("featureKeyword.equals=" + UPDATED_FEATURE_KEYWORD);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByFeatureKeywordIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where featureKeyword in DEFAULT_FEATURE_KEYWORD or UPDATED_FEATURE_KEYWORD
        defaultPlanInfoShouldBeFound("featureKeyword.in=" + DEFAULT_FEATURE_KEYWORD + "," + UPDATED_FEATURE_KEYWORD);

        // Get all the planInfoList where featureKeyword equals to UPDATED_FEATURE_KEYWORD
        defaultPlanInfoShouldNotBeFound("featureKeyword.in=" + UPDATED_FEATURE_KEYWORD);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByFeatureKeywordIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where featureKeyword is not null
        defaultPlanInfoShouldBeFound("featureKeyword.specified=true");

        // Get all the planInfoList where featureKeyword is null
        defaultPlanInfoShouldNotBeFound("featureKeyword.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosBySuggestionIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where suggestion equals to DEFAULT_SUGGESTION
        defaultPlanInfoShouldBeFound("suggestion.equals=" + DEFAULT_SUGGESTION);

        // Get all the planInfoList where suggestion equals to UPDATED_SUGGESTION
        defaultPlanInfoShouldNotBeFound("suggestion.equals=" + UPDATED_SUGGESTION);
    }

    @Test
    @Transactional
    public void getAllPlanInfosBySuggestionIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where suggestion in DEFAULT_SUGGESTION or UPDATED_SUGGESTION
        defaultPlanInfoShouldBeFound("suggestion.in=" + DEFAULT_SUGGESTION + "," + UPDATED_SUGGESTION);

        // Get all the planInfoList where suggestion equals to UPDATED_SUGGESTION
        defaultPlanInfoShouldNotBeFound("suggestion.in=" + UPDATED_SUGGESTION);
    }

    @Test
    @Transactional
    public void getAllPlanInfosBySuggestionIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where suggestion is not null
        defaultPlanInfoShouldBeFound("suggestion.specified=true");

        // Get all the planInfoList where suggestion is null
        defaultPlanInfoShouldNotBeFound("suggestion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByReleaseScopeIsEqualToSomething() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where releaseScope equals to DEFAULT_RELEASE_SCOPE
        defaultPlanInfoShouldBeFound("releaseScope.equals=" + DEFAULT_RELEASE_SCOPE);

        // Get all the planInfoList where releaseScope equals to UPDATED_RELEASE_SCOPE
        defaultPlanInfoShouldNotBeFound("releaseScope.equals=" + UPDATED_RELEASE_SCOPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByReleaseScopeIsInShouldWork() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where releaseScope in DEFAULT_RELEASE_SCOPE or UPDATED_RELEASE_SCOPE
        defaultPlanInfoShouldBeFound("releaseScope.in=" + DEFAULT_RELEASE_SCOPE + "," + UPDATED_RELEASE_SCOPE);

        // Get all the planInfoList where releaseScope equals to UPDATED_RELEASE_SCOPE
        defaultPlanInfoShouldNotBeFound("releaseScope.in=" + UPDATED_RELEASE_SCOPE);
    }

    @Test
    @Transactional
    public void getAllPlanInfosByReleaseScopeIsNullOrNotNull() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        // Get all the planInfoList where releaseScope is not null
        defaultPlanInfoShouldBeFound("releaseScope.specified=true");

        // Get all the planInfoList where releaseScope is null
        defaultPlanInfoShouldNotBeFound("releaseScope.specified=false");
    }

    @Test
    @Transactional
    public void getAllPlanInfosByVerifyRecIsEqualToSomething() throws Exception {
        // Initialize the database
        VerifyRec verifyRec = VerifyRecResourceIntTest.createEntity(em);
        em.persist(verifyRec);
        em.flush();
        planInfo.setVerifyRec(verifyRec);
        planInfoRepository.saveAndFlush(planInfo);
        Long verifyRecId = verifyRec.getId();

        // Get all the planInfoList where verifyRec equals to verifyRecId
        defaultPlanInfoShouldBeFound("verifyRecId.equals=" + verifyRecId);

        // Get all the planInfoList where verifyRec equals to verifyRecId + 1
        defaultPlanInfoShouldNotBeFound("verifyRecId.equals=" + (verifyRecId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByParaTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaType paraType = ParaTypeResourceIntTest.createEntity(em);
        em.persist(paraType);
        em.flush();
        planInfo.setParaType(paraType);
        planInfoRepository.saveAndFlush(planInfo);
        Long paraTypeId = paraType.getId();

        // Get all the planInfoList where paraType equals to paraTypeId
        defaultPlanInfoShouldBeFound("paraTypeId.equals=" + paraTypeId);

        // Get all the planInfoList where paraType equals to paraTypeId + 1
        defaultPlanInfoShouldNotBeFound("paraTypeId.equals=" + (paraTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByParaClassIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaClass paraClass = ParaClassResourceIntTest.createEntity(em);
        em.persist(paraClass);
        em.flush();
        planInfo.setParaClass(paraClass);
        planInfoRepository.saveAndFlush(planInfo);
        Long paraClassId = paraClass.getId();

        // Get all the planInfoList where paraClass equals to paraClassId
        defaultPlanInfoShouldBeFound("paraClassId.equals=" + paraClassId);

        // Get all the planInfoList where paraClass equals to paraClassId + 1
        defaultPlanInfoShouldNotBeFound("paraClassId.equals=" + (paraClassId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByParaCatIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaCat paraCat = ParaCatResourceIntTest.createEntity(em);
        em.persist(paraCat);
        em.flush();
        planInfo.setParaCat(paraCat);
        planInfoRepository.saveAndFlush(planInfo);
        Long paraCatId = paraCat.getId();

        // Get all the planInfoList where paraCat equals to paraCatId
        defaultPlanInfoShouldBeFound("paraCatId.equals=" + paraCatId);

        // Get all the planInfoList where paraCat equals to paraCatId + 1
        defaultPlanInfoShouldNotBeFound("paraCatId.equals=" + (paraCatId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByParaStateIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaState paraState = ParaStateResourceIntTest.createEntity(em);
        em.persist(paraState);
        em.flush();
        planInfo.setParaState(paraState);
        planInfoRepository.saveAndFlush(planInfo);
        Long paraStateId = paraState.getId();

        // Get all the planInfoList where paraState equals to paraStateId
        defaultPlanInfoShouldBeFound("paraStateId.equals=" + paraStateId);

        // Get all the planInfoList where paraState equals to paraStateId + 1
        defaultPlanInfoShouldNotBeFound("paraStateId.equals=" + (paraStateId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByParaSourceIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaSource paraSource = ParaSourceResourceIntTest.createEntity(em);
        em.persist(paraSource);
        em.flush();
        planInfo.setParaSource(paraSource);
        planInfoRepository.saveAndFlush(planInfo);
        Long paraSourceId = paraSource.getId();

        // Get all the planInfoList where paraSource equals to paraSourceId
        defaultPlanInfoShouldBeFound("paraSourceId.equals=" + paraSourceId);

        // Get all the planInfoList where paraSource equals to paraSourceId + 1
        defaultPlanInfoShouldNotBeFound("paraSourceId.equals=" + (paraSourceId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByParaAttrIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaAttr paraAttr = ParaAttrResourceIntTest.createEntity(em);
        em.persist(paraAttr);
        em.flush();
        planInfo.setParaAttr(paraAttr);
        planInfoRepository.saveAndFlush(planInfo);
        Long paraAttrId = paraAttr.getId();

        // Get all the planInfoList where paraAttr equals to paraAttrId
        defaultPlanInfoShouldBeFound("paraAttrId.equals=" + paraAttrId);

        // Get all the planInfoList where paraAttr equals to paraAttrId + 1
        defaultPlanInfoShouldNotBeFound("paraAttrId.equals=" + (paraAttrId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByParaPropIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaProp paraProp = ParaPropResourceIntTest.createEntity(em);
        em.persist(paraProp);
        em.flush();
        planInfo.setParaProp(paraProp);
        planInfoRepository.saveAndFlush(planInfo);
        Long paraPropId = paraProp.getId();

        // Get all the planInfoList where paraProp equals to paraPropId
        defaultPlanInfoShouldBeFound("paraPropId.equals=" + paraPropId);

        // Get all the planInfoList where paraProp equals to paraPropId + 1
        defaultPlanInfoShouldNotBeFound("paraPropId.equals=" + (paraPropId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        planInfo.setCreatedBy(createdBy);
        planInfoRepository.saveAndFlush(planInfo);
        Long createdById = createdBy.getId();

        // Get all the planInfoList where createdBy equals to createdById
        defaultPlanInfoShouldBeFound("createdById.equals=" + createdById);

        // Get all the planInfoList where createdBy equals to createdById + 1
        defaultPlanInfoShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByCreatedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep createdDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(createdDepBy);
        em.flush();
        planInfo.setCreatedDepBy(createdDepBy);
        planInfoRepository.saveAndFlush(planInfo);
        Long createdDepById = createdDepBy.getId();

        // Get all the planInfoList where createdDepBy equals to createdDepById
        defaultPlanInfoShouldBeFound("createdDepById.equals=" + createdDepById);

        // Get all the planInfoList where createdDepBy equals to createdDepById + 1
        defaultPlanInfoShouldNotBeFound("createdDepById.equals=" + (createdDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByOwnerByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser ownerBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(ownerBy);
        em.flush();
        planInfo.setOwnerBy(ownerBy);
        planInfoRepository.saveAndFlush(planInfo);
        Long ownerById = ownerBy.getId();

        // Get all the planInfoList where ownerBy equals to ownerById
        defaultPlanInfoShouldBeFound("ownerById.equals=" + ownerById);

        // Get all the planInfoList where ownerBy equals to ownerById + 1
        defaultPlanInfoShouldNotBeFound("ownerById.equals=" + (ownerById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByOwnerDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep ownerDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(ownerDepBy);
        em.flush();
        planInfo.setOwnerDepBy(ownerDepBy);
        planInfoRepository.saveAndFlush(planInfo);
        Long ownerDepById = ownerDepBy.getId();

        // Get all the planInfoList where ownerDepBy equals to ownerDepById
        defaultPlanInfoShouldBeFound("ownerDepById.equals=" + ownerDepById);

        // Get all the planInfoList where ownerDepBy equals to ownerDepById + 1
        defaultPlanInfoShouldNotBeFound("ownerDepById.equals=" + (ownerDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        planInfo.setModifiedBy(modifiedBy);
        planInfoRepository.saveAndFlush(planInfo);
        Long modifiedById = modifiedBy.getId();

        // Get all the planInfoList where modifiedBy equals to modifiedById
        defaultPlanInfoShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the planInfoList where modifiedBy equals to modifiedById + 1
        defaultPlanInfoShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByModifiedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep modifiedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(modifiedDepBy);
        em.flush();
        planInfo.setModifiedDepBy(modifiedDepBy);
        planInfoRepository.saveAndFlush(planInfo);
        Long modifiedDepById = modifiedDepBy.getId();

        // Get all the planInfoList where modifiedDepBy equals to modifiedDepById
        defaultPlanInfoShouldBeFound("modifiedDepById.equals=" + modifiedDepById);

        // Get all the planInfoList where modifiedDepBy equals to modifiedDepById + 1
        defaultPlanInfoShouldNotBeFound("modifiedDepById.equals=" + (modifiedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        planInfo.setVerifiedBy(verifiedBy);
        planInfoRepository.saveAndFlush(planInfo);
        Long verifiedById = verifiedBy.getId();

        // Get all the planInfoList where verifiedBy equals to verifiedById
        defaultPlanInfoShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the planInfoList where verifiedBy equals to verifiedById + 1
        defaultPlanInfoShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByVerifiedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep verifiedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(verifiedDepBy);
        em.flush();
        planInfo.setVerifiedDepBy(verifiedDepBy);
        planInfoRepository.saveAndFlush(planInfo);
        Long verifiedDepById = verifiedDepBy.getId();

        // Get all the planInfoList where verifiedDepBy equals to verifiedDepById
        defaultPlanInfoShouldBeFound("verifiedDepById.equals=" + verifiedDepById);

        // Get all the planInfoList where verifiedDepBy equals to verifiedDepById + 1
        defaultPlanInfoShouldNotBeFound("verifiedDepById.equals=" + (verifiedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByPublishedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser publishedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(publishedBy);
        em.flush();
        planInfo.setPublishedBy(publishedBy);
        planInfoRepository.saveAndFlush(planInfo);
        Long publishedById = publishedBy.getId();

        // Get all the planInfoList where publishedBy equals to publishedById
        defaultPlanInfoShouldBeFound("publishedById.equals=" + publishedById);

        // Get all the planInfoList where publishedBy equals to publishedById + 1
        defaultPlanInfoShouldNotBeFound("publishedById.equals=" + (publishedById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByPublishedDepByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep publishedDepBy = RmsDepResourceIntTest.createEntity(em);
        em.persist(publishedDepBy);
        em.flush();
        planInfo.setPublishedDepBy(publishedDepBy);
        planInfoRepository.saveAndFlush(planInfo);
        Long publishedDepById = publishedDepBy.getId();

        // Get all the planInfoList where publishedDepBy equals to publishedDepById
        defaultPlanInfoShouldBeFound("publishedDepById.equals=" + publishedDepById);

        // Get all the planInfoList where publishedDepBy equals to publishedDepById + 1
        defaultPlanInfoShouldNotBeFound("publishedDepById.equals=" + (publishedDepById + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        PlanInfo parent = PlanInfoResourceIntTest.createEntity(em);
        em.persist(parent);
        em.flush();
        planInfo.setParent(parent);
        planInfoRepository.saveAndFlush(planInfo);
        Long parentId = parent.getId();

        // Get all the planInfoList where parent equals to parentId
        defaultPlanInfoShouldBeFound("parentId.equals=" + parentId);

        // Get all the planInfoList where parent equals to parentId + 1
        defaultPlanInfoShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }


    @Test
    @Transactional
    public void getAllPlanInfosByCompanyIsEqualToSomething() throws Exception {
        // Initialize the database
        Company company = CompanyResourceIntTest.createEntity(em);
        em.persist(company);
        em.flush();
        planInfo.setCompany(company);
        planInfoRepository.saveAndFlush(planInfo);
        Long companyId = company.getId();

        // Get all the planInfoList where company equals to companyId
        defaultPlanInfoShouldBeFound("companyId.equals=" + companyId);

        // Get all the planInfoList where company equals to companyId + 1
        defaultPlanInfoShouldNotBeFound("companyId.equals=" + (companyId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultPlanInfoShouldBeFound(String filter) throws Exception {
        restPlanInfoMockMvc.perform(get("/api/plan-infos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfo.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].publishedTime").value(hasItem(DEFAULT_PUBLISHED_TIME.toString())))
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
            .andExpect(jsonPath("$.[*].releaseScope").value(hasItem(DEFAULT_RELEASE_SCOPE.toString())));

        // Check, that the count call also returns 1
        restPlanInfoMockMvc.perform(get("/api/plan-infos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultPlanInfoShouldNotBeFound(String filter) throws Exception {
        restPlanInfoMockMvc.perform(get("/api/plan-infos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPlanInfoMockMvc.perform(get("/api/plan-infos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPlanInfo() throws Exception {
        // Get the planInfo
        restPlanInfoMockMvc.perform(get("/api/plan-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlanInfo() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        int databaseSizeBeforeUpdate = planInfoRepository.findAll().size();

        // Update the planInfo
        PlanInfo updatedPlanInfo = planInfoRepository.findById(planInfo.getId()).get();
        // Disconnect from session so that the updates on updatedPlanInfo are not directly saved in db
        em.detach(updatedPlanInfo);
        updatedPlanInfo
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
            .publishedTime(UPDATED_PUBLISHED_TIME)
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
            .releaseScope(UPDATED_RELEASE_SCOPE);
        PlanInfoDTO planInfoDTO = planInfoMapper.toDto(updatedPlanInfo);

        restPlanInfoMockMvc.perform(put("/api/plan-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDTO)))
            .andExpect(status().isOk());

        // Validate the PlanInfo in the database
        List<PlanInfo> planInfoList = planInfoRepository.findAll();
        assertThat(planInfoList).hasSize(databaseSizeBeforeUpdate);
        PlanInfo testPlanInfo = planInfoList.get(planInfoList.size() - 1);
        assertThat(testPlanInfo.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPlanInfo.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testPlanInfo.getDescString()).isEqualTo(UPDATED_DESC_STRING);
        assertThat(testPlanInfo.getJsonString()).isEqualTo(UPDATED_JSON_STRING);
        assertThat(testPlanInfo.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testPlanInfo.getRefEvent()).isEqualTo(UPDATED_REF_EVENT);
        assertThat(testPlanInfo.getAttachmentPath()).isEqualTo(UPDATED_ATTACHMENT_PATH);
        assertThat(testPlanInfo.getAttachmentBlob()).isEqualTo(UPDATED_ATTACHMENT_BLOB);
        assertThat(testPlanInfo.getAttachmentBlobContentType()).isEqualTo(UPDATED_ATTACHMENT_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfo.getAttachmentName()).isEqualTo(UPDATED_ATTACHMENT_NAME);
        assertThat(testPlanInfo.getTextBlob()).isEqualTo(UPDATED_TEXT_BLOB);
        assertThat(testPlanInfo.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testPlanInfo.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testPlanInfo.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testPlanInfo.isUsingFlag()).isEqualTo(UPDATED_USING_FLAG);
        assertThat(testPlanInfo.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testPlanInfo.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testPlanInfo.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testPlanInfo.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testPlanInfo.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testPlanInfo.getPublishedTime()).isEqualTo(UPDATED_PUBLISHED_TIME);
        assertThat(testPlanInfo.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);
        assertThat(testPlanInfo.isVerifyNeed()).isEqualTo(UPDATED_VERIFY_NEED);
        assertThat(testPlanInfo.getVerifyOpinion()).isEqualTo(UPDATED_VERIFY_OPINION);
        assertThat(testPlanInfo.getViewCount()).isEqualTo(UPDATED_VIEW_COUNT);
        assertThat(testPlanInfo.getViewPermission()).isEqualTo(UPDATED_VIEW_PERMISSION);
        assertThat(testPlanInfo.getViewPermPersion()).isEqualTo(UPDATED_VIEW_PERM_PERSION);
        assertThat(testPlanInfo.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testPlanInfo.getVersionNumber()).isEqualTo(UPDATED_VERSION_NUMBER);
        assertThat(testPlanInfo.getParaSourceString()).isEqualTo(UPDATED_PARA_SOURCE_STRING);
        assertThat(testPlanInfo.getFeatureKeyword()).isEqualTo(UPDATED_FEATURE_KEYWORD);
        assertThat(testPlanInfo.getSuggestion()).isEqualTo(UPDATED_SUGGESTION);
        assertThat(testPlanInfo.getReleaseScope()).isEqualTo(UPDATED_RELEASE_SCOPE);

        // Validate the PlanInfo in Elasticsearch
        verify(mockPlanInfoSearchRepository, times(1)).save(testPlanInfo);
    }

    @Test
    @Transactional
    public void updateNonExistingPlanInfo() throws Exception {
        int databaseSizeBeforeUpdate = planInfoRepository.findAll().size();

        // Create the PlanInfo
        PlanInfoDTO planInfoDTO = planInfoMapper.toDto(planInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanInfoMockMvc.perform(put("/api/plan-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlanInfo in the database
        List<PlanInfo> planInfoList = planInfoRepository.findAll();
        assertThat(planInfoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PlanInfo in Elasticsearch
        verify(mockPlanInfoSearchRepository, times(0)).save(planInfo);
    }

    @Test
    @Transactional
    public void deletePlanInfo() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);

        int databaseSizeBeforeDelete = planInfoRepository.findAll().size();

        // Get the planInfo
        restPlanInfoMockMvc.perform(delete("/api/plan-infos/{id}", planInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PlanInfo> planInfoList = planInfoRepository.findAll();
        assertThat(planInfoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PlanInfo in Elasticsearch
        verify(mockPlanInfoSearchRepository, times(1)).deleteById(planInfo.getId());
    }

    @Test
    @Transactional
    public void searchPlanInfo() throws Exception {
        // Initialize the database
        planInfoRepository.saveAndFlush(planInfo);
        when(mockPlanInfoSearchRepository.search(queryStringQuery("id:" + planInfo.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(planInfo), PageRequest.of(0, 1), 1));
        // Search the planInfo
        restPlanInfoMockMvc.perform(get("/api/_search/plan-infos?query=id:" + planInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planInfo.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].publishedTime").value(hasItem(DEFAULT_PUBLISHED_TIME.toString())))
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
            .andExpect(jsonPath("$.[*].releaseScope").value(hasItem(DEFAULT_RELEASE_SCOPE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfo.class);
        PlanInfo planInfo1 = new PlanInfo();
        planInfo1.setId(1L);
        PlanInfo planInfo2 = new PlanInfo();
        planInfo2.setId(planInfo1.getId());
        assertThat(planInfo1).isEqualTo(planInfo2);
        planInfo2.setId(2L);
        assertThat(planInfo1).isNotEqualTo(planInfo2);
        planInfo1.setId(null);
        assertThat(planInfo1).isNotEqualTo(planInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanInfoDTO.class);
        PlanInfoDTO planInfoDTO1 = new PlanInfoDTO();
        planInfoDTO1.setId(1L);
        PlanInfoDTO planInfoDTO2 = new PlanInfoDTO();
        assertThat(planInfoDTO1).isNotEqualTo(planInfoDTO2);
        planInfoDTO2.setId(planInfoDTO1.getId());
        assertThat(planInfoDTO1).isEqualTo(planInfoDTO2);
        planInfoDTO2.setId(2L);
        assertThat(planInfoDTO1).isNotEqualTo(planInfoDTO2);
        planInfoDTO1.setId(null);
        assertThat(planInfoDTO1).isNotEqualTo(planInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planInfoMapper.fromId(null)).isNull();
    }
}
