package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsDep;
import com.aerothinker.plandb.repository.RmsDepRepository;
import com.aerothinker.plandb.repository.search.RmsDepSearchRepository;
import com.aerothinker.plandb.service.RmsDepService;
import com.aerothinker.plandb.service.dto.RmsDepDTO;
import com.aerothinker.plandb.service.mapper.RmsDepMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.RmsDepCriteria;
import com.aerothinker.plandb.service.RmsDepQueryService;

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
 * Test class for the RmsDepResource REST controller.
 *
 * @see RmsDepResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class RmsDepResourceIntTest {

    private static final String DEFAULT_DEP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEP_TYPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEP_TYPE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_DEP_ID = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_DEP_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_REPEAL_FLAG = false;
    private static final Boolean UPDATED_REPEAL_FLAG = true;

    private static final String DEFAULT_LEVEL_ID = "AAAAAAAAAA";
    private static final String UPDATED_LEVEL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DEP_SORT = "AAAAAAAAAA";
    private static final String UPDATED_DEP_SORT = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_DEP_STR = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_DEP_STR = "BBBBBBBBBB";

    private static final String DEFAULT_CHILD_DEP_STR = "AAAAAAAAAA";
    private static final String UPDATED_CHILD_DEP_STR = "BBBBBBBBBB";

    private static final String DEFAULT_DEP_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DEP_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_INTERNET = "AAAAAAAAAA";
    private static final String UPDATED_INTERNET = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_BY_01 = "AAAAAAAAAA";
    private static final String UPDATED_BY_01 = "BBBBBBBBBB";

    private static final String DEFAULT_BY_02 = "AAAAAAAAAA";
    private static final String UPDATED_BY_02 = "BBBBBBBBBB";

    private static final String DEFAULT_BY_03 = "AAAAAAAAAA";
    private static final String UPDATED_BY_03 = "BBBBBBBBBB";

    private static final String DEFAULT_BY_04 = "AAAAAAAAAA";
    private static final String UPDATED_BY_04 = "BBBBBBBBBB";

    private static final String DEFAULT_BY_05 = "AAAAAAAAAA";
    private static final String UPDATED_BY_05 = "BBBBBBBBBB";

    private static final String DEFAULT_BY_06 = "AAAAAAAAAA";
    private static final String UPDATED_BY_06 = "BBBBBBBBBB";

    private static final String DEFAULT_BY_07 = "AAAAAAAAAA";
    private static final String UPDATED_BY_07 = "BBBBBBBBBB";

    private static final String DEFAULT_BY_08 = "AAAAAAAAAA";
    private static final String UPDATED_BY_08 = "BBBBBBBBBB";

    private static final String DEFAULT_BY_09 = "AAAAAAAAAA";
    private static final String UPDATED_BY_09 = "BBBBBBBBBB";

    private static final String DEFAULT_BY_10 = "AAAAAAAAAA";
    private static final String UPDATED_BY_10 = "BBBBBBBBBB";

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

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    @Autowired
    private RmsDepRepository rmsDepRepository;

    @Autowired
    private RmsDepMapper rmsDepMapper;

    @Autowired
    private RmsDepService rmsDepService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.RmsDepSearchRepositoryMockConfiguration
     */
    @Autowired
    private RmsDepSearchRepository mockRmsDepSearchRepository;

    @Autowired
    private RmsDepQueryService rmsDepQueryService;

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

    private MockMvc restRmsDepMockMvc;

    private RmsDep rmsDep;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RmsDepResource rmsDepResource = new RmsDepResource(rmsDepService, rmsDepQueryService);
        this.restRmsDepMockMvc = MockMvcBuilders.standaloneSetup(rmsDepResource)
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
    public static RmsDep createEntity(EntityManager em) {
        RmsDep rmsDep = new RmsDep()
            .depName(DEFAULT_DEP_NAME)
            .depTypeId(DEFAULT_DEP_TYPE_ID)
            .parentDepId(DEFAULT_PARENT_DEP_ID)
            .repealFlag(DEFAULT_REPEAL_FLAG)
            .levelId(DEFAULT_LEVEL_ID)
            .depSort(DEFAULT_DEP_SORT)
            .parentDepStr(DEFAULT_PARENT_DEP_STR)
            .childDepStr(DEFAULT_CHILD_DEP_STR)
            .depDesc(DEFAULT_DEP_DESC)
            .tel(DEFAULT_TEL)
            .fax(DEFAULT_FAX)
            .address(DEFAULT_ADDRESS)
            .code(DEFAULT_CODE)
            .internet(DEFAULT_INTERNET)
            .mail(DEFAULT_MAIL)
            .by01(DEFAULT_BY_01)
            .by02(DEFAULT_BY_02)
            .by03(DEFAULT_BY_03)
            .by04(DEFAULT_BY_04)
            .by05(DEFAULT_BY_05)
            .by06(DEFAULT_BY_06)
            .by07(DEFAULT_BY_07)
            .by08(DEFAULT_BY_08)
            .by09(DEFAULT_BY_09)
            .by10(DEFAULT_BY_10)
            .validType(DEFAULT_VALID_TYPE)
            .validBegin(DEFAULT_VALID_BEGIN)
            .validEnd(DEFAULT_VALID_END)
            .insertTime(DEFAULT_INSERT_TIME)
            .updateTime(DEFAULT_UPDATE_TIME)
            .verifyTime(DEFAULT_VERIFY_TIME)
            .serialNumber(DEFAULT_SERIAL_NUMBER);
        return rmsDep;
    }

    @Before
    public void initTest() {
        rmsDep = createEntity(em);
    }

    @Test
    @Transactional
    public void createRmsDep() throws Exception {
        int databaseSizeBeforeCreate = rmsDepRepository.findAll().size();

        // Create the RmsDep
        RmsDepDTO rmsDepDTO = rmsDepMapper.toDto(rmsDep);
        restRmsDepMockMvc.perform(post("/api/rms-deps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsDepDTO)))
            .andExpect(status().isCreated());

        // Validate the RmsDep in the database
        List<RmsDep> rmsDepList = rmsDepRepository.findAll();
        assertThat(rmsDepList).hasSize(databaseSizeBeforeCreate + 1);
        RmsDep testRmsDep = rmsDepList.get(rmsDepList.size() - 1);
        assertThat(testRmsDep.getDepName()).isEqualTo(DEFAULT_DEP_NAME);
        assertThat(testRmsDep.getDepTypeId()).isEqualTo(DEFAULT_DEP_TYPE_ID);
        assertThat(testRmsDep.getParentDepId()).isEqualTo(DEFAULT_PARENT_DEP_ID);
        assertThat(testRmsDep.isRepealFlag()).isEqualTo(DEFAULT_REPEAL_FLAG);
        assertThat(testRmsDep.getLevelId()).isEqualTo(DEFAULT_LEVEL_ID);
        assertThat(testRmsDep.getDepSort()).isEqualTo(DEFAULT_DEP_SORT);
        assertThat(testRmsDep.getParentDepStr()).isEqualTo(DEFAULT_PARENT_DEP_STR);
        assertThat(testRmsDep.getChildDepStr()).isEqualTo(DEFAULT_CHILD_DEP_STR);
        assertThat(testRmsDep.getDepDesc()).isEqualTo(DEFAULT_DEP_DESC);
        assertThat(testRmsDep.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testRmsDep.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testRmsDep.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testRmsDep.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testRmsDep.getInternet()).isEqualTo(DEFAULT_INTERNET);
        assertThat(testRmsDep.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testRmsDep.getBy01()).isEqualTo(DEFAULT_BY_01);
        assertThat(testRmsDep.getBy02()).isEqualTo(DEFAULT_BY_02);
        assertThat(testRmsDep.getBy03()).isEqualTo(DEFAULT_BY_03);
        assertThat(testRmsDep.getBy04()).isEqualTo(DEFAULT_BY_04);
        assertThat(testRmsDep.getBy05()).isEqualTo(DEFAULT_BY_05);
        assertThat(testRmsDep.getBy06()).isEqualTo(DEFAULT_BY_06);
        assertThat(testRmsDep.getBy07()).isEqualTo(DEFAULT_BY_07);
        assertThat(testRmsDep.getBy08()).isEqualTo(DEFAULT_BY_08);
        assertThat(testRmsDep.getBy09()).isEqualTo(DEFAULT_BY_09);
        assertThat(testRmsDep.getBy10()).isEqualTo(DEFAULT_BY_10);
        assertThat(testRmsDep.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testRmsDep.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testRmsDep.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testRmsDep.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testRmsDep.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testRmsDep.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);
        assertThat(testRmsDep.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);

        // Validate the RmsDep in Elasticsearch
        verify(mockRmsDepSearchRepository, times(1)).save(testRmsDep);
    }

    @Test
    @Transactional
    public void createRmsDepWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rmsDepRepository.findAll().size();

        // Create the RmsDep with an existing ID
        rmsDep.setId(1L);
        RmsDepDTO rmsDepDTO = rmsDepMapper.toDto(rmsDep);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRmsDepMockMvc.perform(post("/api/rms-deps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsDepDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RmsDep in the database
        List<RmsDep> rmsDepList = rmsDepRepository.findAll();
        assertThat(rmsDepList).hasSize(databaseSizeBeforeCreate);

        // Validate the RmsDep in Elasticsearch
        verify(mockRmsDepSearchRepository, times(0)).save(rmsDep);
    }

    @Test
    @Transactional
    public void getAllRmsDeps() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList
        restRmsDepMockMvc.perform(get("/api/rms-deps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsDep.getId().intValue())))
            .andExpect(jsonPath("$.[*].depName").value(hasItem(DEFAULT_DEP_NAME.toString())))
            .andExpect(jsonPath("$.[*].depTypeId").value(hasItem(DEFAULT_DEP_TYPE_ID.toString())))
            .andExpect(jsonPath("$.[*].parentDepId").value(hasItem(DEFAULT_PARENT_DEP_ID.toString())))
            .andExpect(jsonPath("$.[*].repealFlag").value(hasItem(DEFAULT_REPEAL_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].levelId").value(hasItem(DEFAULT_LEVEL_ID.toString())))
            .andExpect(jsonPath("$.[*].depSort").value(hasItem(DEFAULT_DEP_SORT.toString())))
            .andExpect(jsonPath("$.[*].parentDepStr").value(hasItem(DEFAULT_PARENT_DEP_STR.toString())))
            .andExpect(jsonPath("$.[*].childDepStr").value(hasItem(DEFAULT_CHILD_DEP_STR.toString())))
            .andExpect(jsonPath("$.[*].depDesc").value(hasItem(DEFAULT_DEP_DESC.toString())))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL.toString())))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].internet").value(hasItem(DEFAULT_INTERNET.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].by01").value(hasItem(DEFAULT_BY_01.toString())))
            .andExpect(jsonPath("$.[*].by02").value(hasItem(DEFAULT_BY_02.toString())))
            .andExpect(jsonPath("$.[*].by03").value(hasItem(DEFAULT_BY_03.toString())))
            .andExpect(jsonPath("$.[*].by04").value(hasItem(DEFAULT_BY_04.toString())))
            .andExpect(jsonPath("$.[*].by05").value(hasItem(DEFAULT_BY_05.toString())))
            .andExpect(jsonPath("$.[*].by06").value(hasItem(DEFAULT_BY_06.toString())))
            .andExpect(jsonPath("$.[*].by07").value(hasItem(DEFAULT_BY_07.toString())))
            .andExpect(jsonPath("$.[*].by08").value(hasItem(DEFAULT_BY_08.toString())))
            .andExpect(jsonPath("$.[*].by09").value(hasItem(DEFAULT_BY_09.toString())))
            .andExpect(jsonPath("$.[*].by10").value(hasItem(DEFAULT_BY_10.toString())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())));
    }
    
    @Test
    @Transactional
    public void getRmsDep() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get the rmsDep
        restRmsDepMockMvc.perform(get("/api/rms-deps/{id}", rmsDep.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rmsDep.getId().intValue()))
            .andExpect(jsonPath("$.depName").value(DEFAULT_DEP_NAME.toString()))
            .andExpect(jsonPath("$.depTypeId").value(DEFAULT_DEP_TYPE_ID.toString()))
            .andExpect(jsonPath("$.parentDepId").value(DEFAULT_PARENT_DEP_ID.toString()))
            .andExpect(jsonPath("$.repealFlag").value(DEFAULT_REPEAL_FLAG.booleanValue()))
            .andExpect(jsonPath("$.levelId").value(DEFAULT_LEVEL_ID.toString()))
            .andExpect(jsonPath("$.depSort").value(DEFAULT_DEP_SORT.toString()))
            .andExpect(jsonPath("$.parentDepStr").value(DEFAULT_PARENT_DEP_STR.toString()))
            .andExpect(jsonPath("$.childDepStr").value(DEFAULT_CHILD_DEP_STR.toString()))
            .andExpect(jsonPath("$.depDesc").value(DEFAULT_DEP_DESC.toString()))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL.toString()))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.internet").value(DEFAULT_INTERNET.toString()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL.toString()))
            .andExpect(jsonPath("$.by01").value(DEFAULT_BY_01.toString()))
            .andExpect(jsonPath("$.by02").value(DEFAULT_BY_02.toString()))
            .andExpect(jsonPath("$.by03").value(DEFAULT_BY_03.toString()))
            .andExpect(jsonPath("$.by04").value(DEFAULT_BY_04.toString()))
            .andExpect(jsonPath("$.by05").value(DEFAULT_BY_05.toString()))
            .andExpect(jsonPath("$.by06").value(DEFAULT_BY_06.toString()))
            .andExpect(jsonPath("$.by07").value(DEFAULT_BY_07.toString()))
            .andExpect(jsonPath("$.by08").value(DEFAULT_BY_08.toString()))
            .andExpect(jsonPath("$.by09").value(DEFAULT_BY_09.toString()))
            .andExpect(jsonPath("$.by10").value(DEFAULT_BY_10.toString()))
            .andExpect(jsonPath("$.validType").value(DEFAULT_VALID_TYPE.toString()))
            .andExpect(jsonPath("$.validBegin").value(DEFAULT_VALID_BEGIN.toString()))
            .andExpect(jsonPath("$.validEnd").value(DEFAULT_VALID_END.toString()))
            .andExpect(jsonPath("$.insertTime").value(DEFAULT_INSERT_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.verifyTime").value(DEFAULT_VERIFY_TIME.toString()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()));
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepNameIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depName equals to DEFAULT_DEP_NAME
        defaultRmsDepShouldBeFound("depName.equals=" + DEFAULT_DEP_NAME);

        // Get all the rmsDepList where depName equals to UPDATED_DEP_NAME
        defaultRmsDepShouldNotBeFound("depName.equals=" + UPDATED_DEP_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepNameIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depName in DEFAULT_DEP_NAME or UPDATED_DEP_NAME
        defaultRmsDepShouldBeFound("depName.in=" + DEFAULT_DEP_NAME + "," + UPDATED_DEP_NAME);

        // Get all the rmsDepList where depName equals to UPDATED_DEP_NAME
        defaultRmsDepShouldNotBeFound("depName.in=" + UPDATED_DEP_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depName is not null
        defaultRmsDepShouldBeFound("depName.specified=true");

        // Get all the rmsDepList where depName is null
        defaultRmsDepShouldNotBeFound("depName.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepTypeIdIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depTypeId equals to DEFAULT_DEP_TYPE_ID
        defaultRmsDepShouldBeFound("depTypeId.equals=" + DEFAULT_DEP_TYPE_ID);

        // Get all the rmsDepList where depTypeId equals to UPDATED_DEP_TYPE_ID
        defaultRmsDepShouldNotBeFound("depTypeId.equals=" + UPDATED_DEP_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepTypeIdIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depTypeId in DEFAULT_DEP_TYPE_ID or UPDATED_DEP_TYPE_ID
        defaultRmsDepShouldBeFound("depTypeId.in=" + DEFAULT_DEP_TYPE_ID + "," + UPDATED_DEP_TYPE_ID);

        // Get all the rmsDepList where depTypeId equals to UPDATED_DEP_TYPE_ID
        defaultRmsDepShouldNotBeFound("depTypeId.in=" + UPDATED_DEP_TYPE_ID);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepTypeIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depTypeId is not null
        defaultRmsDepShouldBeFound("depTypeId.specified=true");

        // Get all the rmsDepList where depTypeId is null
        defaultRmsDepShouldNotBeFound("depTypeId.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByParentDepIdIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where parentDepId equals to DEFAULT_PARENT_DEP_ID
        defaultRmsDepShouldBeFound("parentDepId.equals=" + DEFAULT_PARENT_DEP_ID);

        // Get all the rmsDepList where parentDepId equals to UPDATED_PARENT_DEP_ID
        defaultRmsDepShouldNotBeFound("parentDepId.equals=" + UPDATED_PARENT_DEP_ID);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByParentDepIdIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where parentDepId in DEFAULT_PARENT_DEP_ID or UPDATED_PARENT_DEP_ID
        defaultRmsDepShouldBeFound("parentDepId.in=" + DEFAULT_PARENT_DEP_ID + "," + UPDATED_PARENT_DEP_ID);

        // Get all the rmsDepList where parentDepId equals to UPDATED_PARENT_DEP_ID
        defaultRmsDepShouldNotBeFound("parentDepId.in=" + UPDATED_PARENT_DEP_ID);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByParentDepIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where parentDepId is not null
        defaultRmsDepShouldBeFound("parentDepId.specified=true");

        // Get all the rmsDepList where parentDepId is null
        defaultRmsDepShouldNotBeFound("parentDepId.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByRepealFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where repealFlag equals to DEFAULT_REPEAL_FLAG
        defaultRmsDepShouldBeFound("repealFlag.equals=" + DEFAULT_REPEAL_FLAG);

        // Get all the rmsDepList where repealFlag equals to UPDATED_REPEAL_FLAG
        defaultRmsDepShouldNotBeFound("repealFlag.equals=" + UPDATED_REPEAL_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByRepealFlagIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where repealFlag in DEFAULT_REPEAL_FLAG or UPDATED_REPEAL_FLAG
        defaultRmsDepShouldBeFound("repealFlag.in=" + DEFAULT_REPEAL_FLAG + "," + UPDATED_REPEAL_FLAG);

        // Get all the rmsDepList where repealFlag equals to UPDATED_REPEAL_FLAG
        defaultRmsDepShouldNotBeFound("repealFlag.in=" + UPDATED_REPEAL_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByRepealFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where repealFlag is not null
        defaultRmsDepShouldBeFound("repealFlag.specified=true");

        // Get all the rmsDepList where repealFlag is null
        defaultRmsDepShouldNotBeFound("repealFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByLevelIdIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where levelId equals to DEFAULT_LEVEL_ID
        defaultRmsDepShouldBeFound("levelId.equals=" + DEFAULT_LEVEL_ID);

        // Get all the rmsDepList where levelId equals to UPDATED_LEVEL_ID
        defaultRmsDepShouldNotBeFound("levelId.equals=" + UPDATED_LEVEL_ID);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByLevelIdIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where levelId in DEFAULT_LEVEL_ID or UPDATED_LEVEL_ID
        defaultRmsDepShouldBeFound("levelId.in=" + DEFAULT_LEVEL_ID + "," + UPDATED_LEVEL_ID);

        // Get all the rmsDepList where levelId equals to UPDATED_LEVEL_ID
        defaultRmsDepShouldNotBeFound("levelId.in=" + UPDATED_LEVEL_ID);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByLevelIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where levelId is not null
        defaultRmsDepShouldBeFound("levelId.specified=true");

        // Get all the rmsDepList where levelId is null
        defaultRmsDepShouldNotBeFound("levelId.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepSortIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depSort equals to DEFAULT_DEP_SORT
        defaultRmsDepShouldBeFound("depSort.equals=" + DEFAULT_DEP_SORT);

        // Get all the rmsDepList where depSort equals to UPDATED_DEP_SORT
        defaultRmsDepShouldNotBeFound("depSort.equals=" + UPDATED_DEP_SORT);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepSortIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depSort in DEFAULT_DEP_SORT or UPDATED_DEP_SORT
        defaultRmsDepShouldBeFound("depSort.in=" + DEFAULT_DEP_SORT + "," + UPDATED_DEP_SORT);

        // Get all the rmsDepList where depSort equals to UPDATED_DEP_SORT
        defaultRmsDepShouldNotBeFound("depSort.in=" + UPDATED_DEP_SORT);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepSortIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depSort is not null
        defaultRmsDepShouldBeFound("depSort.specified=true");

        // Get all the rmsDepList where depSort is null
        defaultRmsDepShouldNotBeFound("depSort.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByParentDepStrIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where parentDepStr equals to DEFAULT_PARENT_DEP_STR
        defaultRmsDepShouldBeFound("parentDepStr.equals=" + DEFAULT_PARENT_DEP_STR);

        // Get all the rmsDepList where parentDepStr equals to UPDATED_PARENT_DEP_STR
        defaultRmsDepShouldNotBeFound("parentDepStr.equals=" + UPDATED_PARENT_DEP_STR);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByParentDepStrIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where parentDepStr in DEFAULT_PARENT_DEP_STR or UPDATED_PARENT_DEP_STR
        defaultRmsDepShouldBeFound("parentDepStr.in=" + DEFAULT_PARENT_DEP_STR + "," + UPDATED_PARENT_DEP_STR);

        // Get all the rmsDepList where parentDepStr equals to UPDATED_PARENT_DEP_STR
        defaultRmsDepShouldNotBeFound("parentDepStr.in=" + UPDATED_PARENT_DEP_STR);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByParentDepStrIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where parentDepStr is not null
        defaultRmsDepShouldBeFound("parentDepStr.specified=true");

        // Get all the rmsDepList where parentDepStr is null
        defaultRmsDepShouldNotBeFound("parentDepStr.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByChildDepStrIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where childDepStr equals to DEFAULT_CHILD_DEP_STR
        defaultRmsDepShouldBeFound("childDepStr.equals=" + DEFAULT_CHILD_DEP_STR);

        // Get all the rmsDepList where childDepStr equals to UPDATED_CHILD_DEP_STR
        defaultRmsDepShouldNotBeFound("childDepStr.equals=" + UPDATED_CHILD_DEP_STR);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByChildDepStrIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where childDepStr in DEFAULT_CHILD_DEP_STR or UPDATED_CHILD_DEP_STR
        defaultRmsDepShouldBeFound("childDepStr.in=" + DEFAULT_CHILD_DEP_STR + "," + UPDATED_CHILD_DEP_STR);

        // Get all the rmsDepList where childDepStr equals to UPDATED_CHILD_DEP_STR
        defaultRmsDepShouldNotBeFound("childDepStr.in=" + UPDATED_CHILD_DEP_STR);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByChildDepStrIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where childDepStr is not null
        defaultRmsDepShouldBeFound("childDepStr.specified=true");

        // Get all the rmsDepList where childDepStr is null
        defaultRmsDepShouldNotBeFound("childDepStr.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepDescIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depDesc equals to DEFAULT_DEP_DESC
        defaultRmsDepShouldBeFound("depDesc.equals=" + DEFAULT_DEP_DESC);

        // Get all the rmsDepList where depDesc equals to UPDATED_DEP_DESC
        defaultRmsDepShouldNotBeFound("depDesc.equals=" + UPDATED_DEP_DESC);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepDescIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depDesc in DEFAULT_DEP_DESC or UPDATED_DEP_DESC
        defaultRmsDepShouldBeFound("depDesc.in=" + DEFAULT_DEP_DESC + "," + UPDATED_DEP_DESC);

        // Get all the rmsDepList where depDesc equals to UPDATED_DEP_DESC
        defaultRmsDepShouldNotBeFound("depDesc.in=" + UPDATED_DEP_DESC);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByDepDescIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where depDesc is not null
        defaultRmsDepShouldBeFound("depDesc.specified=true");

        // Get all the rmsDepList where depDesc is null
        defaultRmsDepShouldNotBeFound("depDesc.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByTelIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where tel equals to DEFAULT_TEL
        defaultRmsDepShouldBeFound("tel.equals=" + DEFAULT_TEL);

        // Get all the rmsDepList where tel equals to UPDATED_TEL
        defaultRmsDepShouldNotBeFound("tel.equals=" + UPDATED_TEL);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByTelIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where tel in DEFAULT_TEL or UPDATED_TEL
        defaultRmsDepShouldBeFound("tel.in=" + DEFAULT_TEL + "," + UPDATED_TEL);

        // Get all the rmsDepList where tel equals to UPDATED_TEL
        defaultRmsDepShouldNotBeFound("tel.in=" + UPDATED_TEL);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByTelIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where tel is not null
        defaultRmsDepShouldBeFound("tel.specified=true");

        // Get all the rmsDepList where tel is null
        defaultRmsDepShouldNotBeFound("tel.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByFaxIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where fax equals to DEFAULT_FAX
        defaultRmsDepShouldBeFound("fax.equals=" + DEFAULT_FAX);

        // Get all the rmsDepList where fax equals to UPDATED_FAX
        defaultRmsDepShouldNotBeFound("fax.equals=" + UPDATED_FAX);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByFaxIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where fax in DEFAULT_FAX or UPDATED_FAX
        defaultRmsDepShouldBeFound("fax.in=" + DEFAULT_FAX + "," + UPDATED_FAX);

        // Get all the rmsDepList where fax equals to UPDATED_FAX
        defaultRmsDepShouldNotBeFound("fax.in=" + UPDATED_FAX);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByFaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where fax is not null
        defaultRmsDepShouldBeFound("fax.specified=true");

        // Get all the rmsDepList where fax is null
        defaultRmsDepShouldNotBeFound("fax.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where address equals to DEFAULT_ADDRESS
        defaultRmsDepShouldBeFound("address.equals=" + DEFAULT_ADDRESS);

        // Get all the rmsDepList where address equals to UPDATED_ADDRESS
        defaultRmsDepShouldNotBeFound("address.equals=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByAddressIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where address in DEFAULT_ADDRESS or UPDATED_ADDRESS
        defaultRmsDepShouldBeFound("address.in=" + DEFAULT_ADDRESS + "," + UPDATED_ADDRESS);

        // Get all the rmsDepList where address equals to UPDATED_ADDRESS
        defaultRmsDepShouldNotBeFound("address.in=" + UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where address is not null
        defaultRmsDepShouldBeFound("address.specified=true");

        // Get all the rmsDepList where address is null
        defaultRmsDepShouldNotBeFound("address.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where code equals to DEFAULT_CODE
        defaultRmsDepShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the rmsDepList where code equals to UPDATED_CODE
        defaultRmsDepShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where code in DEFAULT_CODE or UPDATED_CODE
        defaultRmsDepShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the rmsDepList where code equals to UPDATED_CODE
        defaultRmsDepShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where code is not null
        defaultRmsDepShouldBeFound("code.specified=true");

        // Get all the rmsDepList where code is null
        defaultRmsDepShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByInternetIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where internet equals to DEFAULT_INTERNET
        defaultRmsDepShouldBeFound("internet.equals=" + DEFAULT_INTERNET);

        // Get all the rmsDepList where internet equals to UPDATED_INTERNET
        defaultRmsDepShouldNotBeFound("internet.equals=" + UPDATED_INTERNET);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByInternetIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where internet in DEFAULT_INTERNET or UPDATED_INTERNET
        defaultRmsDepShouldBeFound("internet.in=" + DEFAULT_INTERNET + "," + UPDATED_INTERNET);

        // Get all the rmsDepList where internet equals to UPDATED_INTERNET
        defaultRmsDepShouldNotBeFound("internet.in=" + UPDATED_INTERNET);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByInternetIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where internet is not null
        defaultRmsDepShouldBeFound("internet.specified=true");

        // Get all the rmsDepList where internet is null
        defaultRmsDepShouldNotBeFound("internet.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByMailIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where mail equals to DEFAULT_MAIL
        defaultRmsDepShouldBeFound("mail.equals=" + DEFAULT_MAIL);

        // Get all the rmsDepList where mail equals to UPDATED_MAIL
        defaultRmsDepShouldNotBeFound("mail.equals=" + UPDATED_MAIL);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByMailIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where mail in DEFAULT_MAIL or UPDATED_MAIL
        defaultRmsDepShouldBeFound("mail.in=" + DEFAULT_MAIL + "," + UPDATED_MAIL);

        // Get all the rmsDepList where mail equals to UPDATED_MAIL
        defaultRmsDepShouldNotBeFound("mail.in=" + UPDATED_MAIL);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByMailIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where mail is not null
        defaultRmsDepShouldBeFound("mail.specified=true");

        // Get all the rmsDepList where mail is null
        defaultRmsDepShouldNotBeFound("mail.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy01IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by01 equals to DEFAULT_BY_01
        defaultRmsDepShouldBeFound("by01.equals=" + DEFAULT_BY_01);

        // Get all the rmsDepList where by01 equals to UPDATED_BY_01
        defaultRmsDepShouldNotBeFound("by01.equals=" + UPDATED_BY_01);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy01IsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by01 in DEFAULT_BY_01 or UPDATED_BY_01
        defaultRmsDepShouldBeFound("by01.in=" + DEFAULT_BY_01 + "," + UPDATED_BY_01);

        // Get all the rmsDepList where by01 equals to UPDATED_BY_01
        defaultRmsDepShouldNotBeFound("by01.in=" + UPDATED_BY_01);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy01IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by01 is not null
        defaultRmsDepShouldBeFound("by01.specified=true");

        // Get all the rmsDepList where by01 is null
        defaultRmsDepShouldNotBeFound("by01.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy02IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by02 equals to DEFAULT_BY_02
        defaultRmsDepShouldBeFound("by02.equals=" + DEFAULT_BY_02);

        // Get all the rmsDepList where by02 equals to UPDATED_BY_02
        defaultRmsDepShouldNotBeFound("by02.equals=" + UPDATED_BY_02);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy02IsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by02 in DEFAULT_BY_02 or UPDATED_BY_02
        defaultRmsDepShouldBeFound("by02.in=" + DEFAULT_BY_02 + "," + UPDATED_BY_02);

        // Get all the rmsDepList where by02 equals to UPDATED_BY_02
        defaultRmsDepShouldNotBeFound("by02.in=" + UPDATED_BY_02);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy02IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by02 is not null
        defaultRmsDepShouldBeFound("by02.specified=true");

        // Get all the rmsDepList where by02 is null
        defaultRmsDepShouldNotBeFound("by02.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy03IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by03 equals to DEFAULT_BY_03
        defaultRmsDepShouldBeFound("by03.equals=" + DEFAULT_BY_03);

        // Get all the rmsDepList where by03 equals to UPDATED_BY_03
        defaultRmsDepShouldNotBeFound("by03.equals=" + UPDATED_BY_03);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy03IsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by03 in DEFAULT_BY_03 or UPDATED_BY_03
        defaultRmsDepShouldBeFound("by03.in=" + DEFAULT_BY_03 + "," + UPDATED_BY_03);

        // Get all the rmsDepList where by03 equals to UPDATED_BY_03
        defaultRmsDepShouldNotBeFound("by03.in=" + UPDATED_BY_03);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy03IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by03 is not null
        defaultRmsDepShouldBeFound("by03.specified=true");

        // Get all the rmsDepList where by03 is null
        defaultRmsDepShouldNotBeFound("by03.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy04IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by04 equals to DEFAULT_BY_04
        defaultRmsDepShouldBeFound("by04.equals=" + DEFAULT_BY_04);

        // Get all the rmsDepList where by04 equals to UPDATED_BY_04
        defaultRmsDepShouldNotBeFound("by04.equals=" + UPDATED_BY_04);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy04IsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by04 in DEFAULT_BY_04 or UPDATED_BY_04
        defaultRmsDepShouldBeFound("by04.in=" + DEFAULT_BY_04 + "," + UPDATED_BY_04);

        // Get all the rmsDepList where by04 equals to UPDATED_BY_04
        defaultRmsDepShouldNotBeFound("by04.in=" + UPDATED_BY_04);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy04IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by04 is not null
        defaultRmsDepShouldBeFound("by04.specified=true");

        // Get all the rmsDepList where by04 is null
        defaultRmsDepShouldNotBeFound("by04.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy05IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by05 equals to DEFAULT_BY_05
        defaultRmsDepShouldBeFound("by05.equals=" + DEFAULT_BY_05);

        // Get all the rmsDepList where by05 equals to UPDATED_BY_05
        defaultRmsDepShouldNotBeFound("by05.equals=" + UPDATED_BY_05);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy05IsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by05 in DEFAULT_BY_05 or UPDATED_BY_05
        defaultRmsDepShouldBeFound("by05.in=" + DEFAULT_BY_05 + "," + UPDATED_BY_05);

        // Get all the rmsDepList where by05 equals to UPDATED_BY_05
        defaultRmsDepShouldNotBeFound("by05.in=" + UPDATED_BY_05);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy05IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by05 is not null
        defaultRmsDepShouldBeFound("by05.specified=true");

        // Get all the rmsDepList where by05 is null
        defaultRmsDepShouldNotBeFound("by05.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy06IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by06 equals to DEFAULT_BY_06
        defaultRmsDepShouldBeFound("by06.equals=" + DEFAULT_BY_06);

        // Get all the rmsDepList where by06 equals to UPDATED_BY_06
        defaultRmsDepShouldNotBeFound("by06.equals=" + UPDATED_BY_06);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy06IsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by06 in DEFAULT_BY_06 or UPDATED_BY_06
        defaultRmsDepShouldBeFound("by06.in=" + DEFAULT_BY_06 + "," + UPDATED_BY_06);

        // Get all the rmsDepList where by06 equals to UPDATED_BY_06
        defaultRmsDepShouldNotBeFound("by06.in=" + UPDATED_BY_06);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy06IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by06 is not null
        defaultRmsDepShouldBeFound("by06.specified=true");

        // Get all the rmsDepList where by06 is null
        defaultRmsDepShouldNotBeFound("by06.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy07IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by07 equals to DEFAULT_BY_07
        defaultRmsDepShouldBeFound("by07.equals=" + DEFAULT_BY_07);

        // Get all the rmsDepList where by07 equals to UPDATED_BY_07
        defaultRmsDepShouldNotBeFound("by07.equals=" + UPDATED_BY_07);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy07IsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by07 in DEFAULT_BY_07 or UPDATED_BY_07
        defaultRmsDepShouldBeFound("by07.in=" + DEFAULT_BY_07 + "," + UPDATED_BY_07);

        // Get all the rmsDepList where by07 equals to UPDATED_BY_07
        defaultRmsDepShouldNotBeFound("by07.in=" + UPDATED_BY_07);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy07IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by07 is not null
        defaultRmsDepShouldBeFound("by07.specified=true");

        // Get all the rmsDepList where by07 is null
        defaultRmsDepShouldNotBeFound("by07.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy08IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by08 equals to DEFAULT_BY_08
        defaultRmsDepShouldBeFound("by08.equals=" + DEFAULT_BY_08);

        // Get all the rmsDepList where by08 equals to UPDATED_BY_08
        defaultRmsDepShouldNotBeFound("by08.equals=" + UPDATED_BY_08);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy08IsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by08 in DEFAULT_BY_08 or UPDATED_BY_08
        defaultRmsDepShouldBeFound("by08.in=" + DEFAULT_BY_08 + "," + UPDATED_BY_08);

        // Get all the rmsDepList where by08 equals to UPDATED_BY_08
        defaultRmsDepShouldNotBeFound("by08.in=" + UPDATED_BY_08);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy08IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by08 is not null
        defaultRmsDepShouldBeFound("by08.specified=true");

        // Get all the rmsDepList where by08 is null
        defaultRmsDepShouldNotBeFound("by08.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy09IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by09 equals to DEFAULT_BY_09
        defaultRmsDepShouldBeFound("by09.equals=" + DEFAULT_BY_09);

        // Get all the rmsDepList where by09 equals to UPDATED_BY_09
        defaultRmsDepShouldNotBeFound("by09.equals=" + UPDATED_BY_09);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy09IsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by09 in DEFAULT_BY_09 or UPDATED_BY_09
        defaultRmsDepShouldBeFound("by09.in=" + DEFAULT_BY_09 + "," + UPDATED_BY_09);

        // Get all the rmsDepList where by09 equals to UPDATED_BY_09
        defaultRmsDepShouldNotBeFound("by09.in=" + UPDATED_BY_09);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy09IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by09 is not null
        defaultRmsDepShouldBeFound("by09.specified=true");

        // Get all the rmsDepList where by09 is null
        defaultRmsDepShouldNotBeFound("by09.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy10IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by10 equals to DEFAULT_BY_10
        defaultRmsDepShouldBeFound("by10.equals=" + DEFAULT_BY_10);

        // Get all the rmsDepList where by10 equals to UPDATED_BY_10
        defaultRmsDepShouldNotBeFound("by10.equals=" + UPDATED_BY_10);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy10IsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by10 in DEFAULT_BY_10 or UPDATED_BY_10
        defaultRmsDepShouldBeFound("by10.in=" + DEFAULT_BY_10 + "," + UPDATED_BY_10);

        // Get all the rmsDepList where by10 equals to UPDATED_BY_10
        defaultRmsDepShouldNotBeFound("by10.in=" + UPDATED_BY_10);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByBy10IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where by10 is not null
        defaultRmsDepShouldBeFound("by10.specified=true");

        // Get all the rmsDepList where by10 is null
        defaultRmsDepShouldNotBeFound("by10.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where validType equals to DEFAULT_VALID_TYPE
        defaultRmsDepShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the rmsDepList where validType equals to UPDATED_VALID_TYPE
        defaultRmsDepShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultRmsDepShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the rmsDepList where validType equals to UPDATED_VALID_TYPE
        defaultRmsDepShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where validType is not null
        defaultRmsDepShouldBeFound("validType.specified=true");

        // Get all the rmsDepList where validType is null
        defaultRmsDepShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultRmsDepShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the rmsDepList where validBegin equals to UPDATED_VALID_BEGIN
        defaultRmsDepShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultRmsDepShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the rmsDepList where validBegin equals to UPDATED_VALID_BEGIN
        defaultRmsDepShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where validBegin is not null
        defaultRmsDepShouldBeFound("validBegin.specified=true");

        // Get all the rmsDepList where validBegin is null
        defaultRmsDepShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where validEnd equals to DEFAULT_VALID_END
        defaultRmsDepShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the rmsDepList where validEnd equals to UPDATED_VALID_END
        defaultRmsDepShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultRmsDepShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the rmsDepList where validEnd equals to UPDATED_VALID_END
        defaultRmsDepShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where validEnd is not null
        defaultRmsDepShouldBeFound("validEnd.specified=true");

        // Get all the rmsDepList where validEnd is null
        defaultRmsDepShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where insertTime equals to DEFAULT_INSERT_TIME
        defaultRmsDepShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the rmsDepList where insertTime equals to UPDATED_INSERT_TIME
        defaultRmsDepShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultRmsDepShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the rmsDepList where insertTime equals to UPDATED_INSERT_TIME
        defaultRmsDepShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where insertTime is not null
        defaultRmsDepShouldBeFound("insertTime.specified=true");

        // Get all the rmsDepList where insertTime is null
        defaultRmsDepShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultRmsDepShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the rmsDepList where updateTime equals to UPDATED_UPDATE_TIME
        defaultRmsDepShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultRmsDepShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the rmsDepList where updateTime equals to UPDATED_UPDATE_TIME
        defaultRmsDepShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where updateTime is not null
        defaultRmsDepShouldBeFound("updateTime.specified=true");

        // Get all the rmsDepList where updateTime is null
        defaultRmsDepShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultRmsDepShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the rmsDepList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultRmsDepShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultRmsDepShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the rmsDepList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultRmsDepShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsDepsByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where verifyTime is not null
        defaultRmsDepShouldBeFound("verifyTime.specified=true");

        // Get all the rmsDepList where verifyTime is null
        defaultRmsDepShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultRmsDepShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the rmsDepList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultRmsDepShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllRmsDepsBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultRmsDepShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the rmsDepList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultRmsDepShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllRmsDepsBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        // Get all the rmsDepList where serialNumber is not null
        defaultRmsDepShouldBeFound("serialNumber.specified=true");

        // Get all the rmsDepList where serialNumber is null
        defaultRmsDepShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsDepsByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        rmsDep.setCreatedBy(createdBy);
        rmsDepRepository.saveAndFlush(rmsDep);
        Long createdById = createdBy.getId();

        // Get all the rmsDepList where createdBy equals to createdById
        defaultRmsDepShouldBeFound("createdById.equals=" + createdById);

        // Get all the rmsDepList where createdBy equals to createdById + 1
        defaultRmsDepShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsDepsByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        rmsDep.setModifiedBy(modifiedBy);
        rmsDepRepository.saveAndFlush(rmsDep);
        Long modifiedById = modifiedBy.getId();

        // Get all the rmsDepList where modifiedBy equals to modifiedById
        defaultRmsDepShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the rmsDepList where modifiedBy equals to modifiedById + 1
        defaultRmsDepShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsDepsByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        rmsDep.setVerifiedBy(verifiedBy);
        rmsDepRepository.saveAndFlush(rmsDep);
        Long verifiedById = verifiedBy.getId();

        // Get all the rmsDepList where verifiedBy equals to verifiedById
        defaultRmsDepShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the rmsDepList where verifiedBy equals to verifiedById + 1
        defaultRmsDepShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsDepsByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsDep parent = RmsDepResourceIntTest.createEntity(em);
        em.persist(parent);
        em.flush();
        rmsDep.setParent(parent);
        rmsDepRepository.saveAndFlush(rmsDep);
        Long parentId = parent.getId();

        // Get all the rmsDepList where parent equals to parentId
        defaultRmsDepShouldBeFound("parentId.equals=" + parentId);

        // Get all the rmsDepList where parent equals to parentId + 1
        defaultRmsDepShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultRmsDepShouldBeFound(String filter) throws Exception {
        restRmsDepMockMvc.perform(get("/api/rms-deps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsDep.getId().intValue())))
            .andExpect(jsonPath("$.[*].depName").value(hasItem(DEFAULT_DEP_NAME.toString())))
            .andExpect(jsonPath("$.[*].depTypeId").value(hasItem(DEFAULT_DEP_TYPE_ID.toString())))
            .andExpect(jsonPath("$.[*].parentDepId").value(hasItem(DEFAULT_PARENT_DEP_ID.toString())))
            .andExpect(jsonPath("$.[*].repealFlag").value(hasItem(DEFAULT_REPEAL_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].levelId").value(hasItem(DEFAULT_LEVEL_ID.toString())))
            .andExpect(jsonPath("$.[*].depSort").value(hasItem(DEFAULT_DEP_SORT.toString())))
            .andExpect(jsonPath("$.[*].parentDepStr").value(hasItem(DEFAULT_PARENT_DEP_STR.toString())))
            .andExpect(jsonPath("$.[*].childDepStr").value(hasItem(DEFAULT_CHILD_DEP_STR.toString())))
            .andExpect(jsonPath("$.[*].depDesc").value(hasItem(DEFAULT_DEP_DESC.toString())))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL.toString())))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].internet").value(hasItem(DEFAULT_INTERNET.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].by01").value(hasItem(DEFAULT_BY_01.toString())))
            .andExpect(jsonPath("$.[*].by02").value(hasItem(DEFAULT_BY_02.toString())))
            .andExpect(jsonPath("$.[*].by03").value(hasItem(DEFAULT_BY_03.toString())))
            .andExpect(jsonPath("$.[*].by04").value(hasItem(DEFAULT_BY_04.toString())))
            .andExpect(jsonPath("$.[*].by05").value(hasItem(DEFAULT_BY_05.toString())))
            .andExpect(jsonPath("$.[*].by06").value(hasItem(DEFAULT_BY_06.toString())))
            .andExpect(jsonPath("$.[*].by07").value(hasItem(DEFAULT_BY_07.toString())))
            .andExpect(jsonPath("$.[*].by08").value(hasItem(DEFAULT_BY_08.toString())))
            .andExpect(jsonPath("$.[*].by09").value(hasItem(DEFAULT_BY_09.toString())))
            .andExpect(jsonPath("$.[*].by10").value(hasItem(DEFAULT_BY_10.toString())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())));

        // Check, that the count call also returns 1
        restRmsDepMockMvc.perform(get("/api/rms-deps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultRmsDepShouldNotBeFound(String filter) throws Exception {
        restRmsDepMockMvc.perform(get("/api/rms-deps?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRmsDepMockMvc.perform(get("/api/rms-deps/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingRmsDep() throws Exception {
        // Get the rmsDep
        restRmsDepMockMvc.perform(get("/api/rms-deps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRmsDep() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        int databaseSizeBeforeUpdate = rmsDepRepository.findAll().size();

        // Update the rmsDep
        RmsDep updatedRmsDep = rmsDepRepository.findById(rmsDep.getId()).get();
        // Disconnect from session so that the updates on updatedRmsDep are not directly saved in db
        em.detach(updatedRmsDep);
        updatedRmsDep
            .depName(UPDATED_DEP_NAME)
            .depTypeId(UPDATED_DEP_TYPE_ID)
            .parentDepId(UPDATED_PARENT_DEP_ID)
            .repealFlag(UPDATED_REPEAL_FLAG)
            .levelId(UPDATED_LEVEL_ID)
            .depSort(UPDATED_DEP_SORT)
            .parentDepStr(UPDATED_PARENT_DEP_STR)
            .childDepStr(UPDATED_CHILD_DEP_STR)
            .depDesc(UPDATED_DEP_DESC)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .address(UPDATED_ADDRESS)
            .code(UPDATED_CODE)
            .internet(UPDATED_INTERNET)
            .mail(UPDATED_MAIL)
            .by01(UPDATED_BY_01)
            .by02(UPDATED_BY_02)
            .by03(UPDATED_BY_03)
            .by04(UPDATED_BY_04)
            .by05(UPDATED_BY_05)
            .by06(UPDATED_BY_06)
            .by07(UPDATED_BY_07)
            .by08(UPDATED_BY_08)
            .by09(UPDATED_BY_09)
            .by10(UPDATED_BY_10)
            .validType(UPDATED_VALID_TYPE)
            .validBegin(UPDATED_VALID_BEGIN)
            .validEnd(UPDATED_VALID_END)
            .insertTime(UPDATED_INSERT_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .verifyTime(UPDATED_VERIFY_TIME)
            .serialNumber(UPDATED_SERIAL_NUMBER);
        RmsDepDTO rmsDepDTO = rmsDepMapper.toDto(updatedRmsDep);

        restRmsDepMockMvc.perform(put("/api/rms-deps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsDepDTO)))
            .andExpect(status().isOk());

        // Validate the RmsDep in the database
        List<RmsDep> rmsDepList = rmsDepRepository.findAll();
        assertThat(rmsDepList).hasSize(databaseSizeBeforeUpdate);
        RmsDep testRmsDep = rmsDepList.get(rmsDepList.size() - 1);
        assertThat(testRmsDep.getDepName()).isEqualTo(UPDATED_DEP_NAME);
        assertThat(testRmsDep.getDepTypeId()).isEqualTo(UPDATED_DEP_TYPE_ID);
        assertThat(testRmsDep.getParentDepId()).isEqualTo(UPDATED_PARENT_DEP_ID);
        assertThat(testRmsDep.isRepealFlag()).isEqualTo(UPDATED_REPEAL_FLAG);
        assertThat(testRmsDep.getLevelId()).isEqualTo(UPDATED_LEVEL_ID);
        assertThat(testRmsDep.getDepSort()).isEqualTo(UPDATED_DEP_SORT);
        assertThat(testRmsDep.getParentDepStr()).isEqualTo(UPDATED_PARENT_DEP_STR);
        assertThat(testRmsDep.getChildDepStr()).isEqualTo(UPDATED_CHILD_DEP_STR);
        assertThat(testRmsDep.getDepDesc()).isEqualTo(UPDATED_DEP_DESC);
        assertThat(testRmsDep.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testRmsDep.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testRmsDep.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testRmsDep.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testRmsDep.getInternet()).isEqualTo(UPDATED_INTERNET);
        assertThat(testRmsDep.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testRmsDep.getBy01()).isEqualTo(UPDATED_BY_01);
        assertThat(testRmsDep.getBy02()).isEqualTo(UPDATED_BY_02);
        assertThat(testRmsDep.getBy03()).isEqualTo(UPDATED_BY_03);
        assertThat(testRmsDep.getBy04()).isEqualTo(UPDATED_BY_04);
        assertThat(testRmsDep.getBy05()).isEqualTo(UPDATED_BY_05);
        assertThat(testRmsDep.getBy06()).isEqualTo(UPDATED_BY_06);
        assertThat(testRmsDep.getBy07()).isEqualTo(UPDATED_BY_07);
        assertThat(testRmsDep.getBy08()).isEqualTo(UPDATED_BY_08);
        assertThat(testRmsDep.getBy09()).isEqualTo(UPDATED_BY_09);
        assertThat(testRmsDep.getBy10()).isEqualTo(UPDATED_BY_10);
        assertThat(testRmsDep.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testRmsDep.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testRmsDep.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testRmsDep.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testRmsDep.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testRmsDep.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);
        assertThat(testRmsDep.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);

        // Validate the RmsDep in Elasticsearch
        verify(mockRmsDepSearchRepository, times(1)).save(testRmsDep);
    }

    @Test
    @Transactional
    public void updateNonExistingRmsDep() throws Exception {
        int databaseSizeBeforeUpdate = rmsDepRepository.findAll().size();

        // Create the RmsDep
        RmsDepDTO rmsDepDTO = rmsDepMapper.toDto(rmsDep);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRmsDepMockMvc.perform(put("/api/rms-deps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsDepDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RmsDep in the database
        List<RmsDep> rmsDepList = rmsDepRepository.findAll();
        assertThat(rmsDepList).hasSize(databaseSizeBeforeUpdate);

        // Validate the RmsDep in Elasticsearch
        verify(mockRmsDepSearchRepository, times(0)).save(rmsDep);
    }

    @Test
    @Transactional
    public void deleteRmsDep() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);

        int databaseSizeBeforeDelete = rmsDepRepository.findAll().size();

        // Get the rmsDep
        restRmsDepMockMvc.perform(delete("/api/rms-deps/{id}", rmsDep.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RmsDep> rmsDepList = rmsDepRepository.findAll();
        assertThat(rmsDepList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the RmsDep in Elasticsearch
        verify(mockRmsDepSearchRepository, times(1)).deleteById(rmsDep.getId());
    }

    @Test
    @Transactional
    public void searchRmsDep() throws Exception {
        // Initialize the database
        rmsDepRepository.saveAndFlush(rmsDep);
        when(mockRmsDepSearchRepository.search(queryStringQuery("id:" + rmsDep.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(rmsDep), PageRequest.of(0, 1), 1));
        // Search the rmsDep
        restRmsDepMockMvc.perform(get("/api/_search/rms-deps?query=id:" + rmsDep.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsDep.getId().intValue())))
            .andExpect(jsonPath("$.[*].depName").value(hasItem(DEFAULT_DEP_NAME)))
            .andExpect(jsonPath("$.[*].depTypeId").value(hasItem(DEFAULT_DEP_TYPE_ID)))
            .andExpect(jsonPath("$.[*].parentDepId").value(hasItem(DEFAULT_PARENT_DEP_ID)))
            .andExpect(jsonPath("$.[*].repealFlag").value(hasItem(DEFAULT_REPEAL_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].levelId").value(hasItem(DEFAULT_LEVEL_ID)))
            .andExpect(jsonPath("$.[*].depSort").value(hasItem(DEFAULT_DEP_SORT)))
            .andExpect(jsonPath("$.[*].parentDepStr").value(hasItem(DEFAULT_PARENT_DEP_STR)))
            .andExpect(jsonPath("$.[*].childDepStr").value(hasItem(DEFAULT_CHILD_DEP_STR)))
            .andExpect(jsonPath("$.[*].depDesc").value(hasItem(DEFAULT_DEP_DESC)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].internet").value(hasItem(DEFAULT_INTERNET)))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)))
            .andExpect(jsonPath("$.[*].by01").value(hasItem(DEFAULT_BY_01)))
            .andExpect(jsonPath("$.[*].by02").value(hasItem(DEFAULT_BY_02)))
            .andExpect(jsonPath("$.[*].by03").value(hasItem(DEFAULT_BY_03)))
            .andExpect(jsonPath("$.[*].by04").value(hasItem(DEFAULT_BY_04)))
            .andExpect(jsonPath("$.[*].by05").value(hasItem(DEFAULT_BY_05)))
            .andExpect(jsonPath("$.[*].by06").value(hasItem(DEFAULT_BY_06)))
            .andExpect(jsonPath("$.[*].by07").value(hasItem(DEFAULT_BY_07)))
            .andExpect(jsonPath("$.[*].by08").value(hasItem(DEFAULT_BY_08)))
            .andExpect(jsonPath("$.[*].by09").value(hasItem(DEFAULT_BY_09)))
            .andExpect(jsonPath("$.[*].by10").value(hasItem(DEFAULT_BY_10)))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RmsDep.class);
        RmsDep rmsDep1 = new RmsDep();
        rmsDep1.setId(1L);
        RmsDep rmsDep2 = new RmsDep();
        rmsDep2.setId(rmsDep1.getId());
        assertThat(rmsDep1).isEqualTo(rmsDep2);
        rmsDep2.setId(2L);
        assertThat(rmsDep1).isNotEqualTo(rmsDep2);
        rmsDep1.setId(null);
        assertThat(rmsDep1).isNotEqualTo(rmsDep2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RmsDepDTO.class);
        RmsDepDTO rmsDepDTO1 = new RmsDepDTO();
        rmsDepDTO1.setId(1L);
        RmsDepDTO rmsDepDTO2 = new RmsDepDTO();
        assertThat(rmsDepDTO1).isNotEqualTo(rmsDepDTO2);
        rmsDepDTO2.setId(rmsDepDTO1.getId());
        assertThat(rmsDepDTO1).isEqualTo(rmsDepDTO2);
        rmsDepDTO2.setId(2L);
        assertThat(rmsDepDTO1).isNotEqualTo(rmsDepDTO2);
        rmsDepDTO1.setId(null);
        assertThat(rmsDepDTO1).isNotEqualTo(rmsDepDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rmsDepMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rmsDepMapper.fromId(null)).isNull();
    }
}
