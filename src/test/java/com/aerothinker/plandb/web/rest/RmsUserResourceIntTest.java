package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsRole;
import com.aerothinker.plandb.repository.RmsUserRepository;
import com.aerothinker.plandb.repository.search.RmsUserSearchRepository;
import com.aerothinker.plandb.service.RmsUserService;
import com.aerothinker.plandb.service.dto.RmsUserDTO;
import com.aerothinker.plandb.service.mapper.RmsUserMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.RmsUserCriteria;
import com.aerothinker.plandb.service.RmsUserQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import java.util.ArrayList;
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
 * Test class for the RmsUserResource REST controller.
 *
 * @see RmsUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class RmsUserResourceIntTest {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PERSON_ID = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_USER_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_PROCESS_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PROCESS_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_USER_SORT = "AAAAAAAAAA";
    private static final String UPDATED_USER_SORT = "BBBBBBBBBB";

    private static final String DEFAULT_USER_DESC = "AAAAAAAAAA";
    private static final String UPDATED_USER_DESC = "BBBBBBBBBB";

    private static final Integer DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES = 1;
    private static final Integer UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES = 2;

    private static final Boolean DEFAULT_USER_PASSWORD_LOCK_FLAG = false;
    private static final Boolean UPDATED_USER_PASSWORD_LOCK_FLAG = true;

    private static final Integer DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES = 1;
    private static final Integer UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES = 2;

    private static final String DEFAULT_PROC_PASSWORD_LOCK_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_PROC_PASSWORD_LOCK_FLAG = "BBBBBBBBBB";

    private static final String DEFAULT_USER_PROP = "AAAAAAAAAA";
    private static final String UPDATED_USER_PROP = "BBBBBBBBBB";

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
    private RmsUserRepository rmsUserRepository;

    @Mock
    private RmsUserRepository rmsUserRepositoryMock;

    @Autowired
    private RmsUserMapper rmsUserMapper;

    @Mock
    private RmsUserService rmsUserServiceMock;

    @Autowired
    private RmsUserService rmsUserService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.RmsUserSearchRepositoryMockConfiguration
     */
    @Autowired
    private RmsUserSearchRepository mockRmsUserSearchRepository;

    @Autowired
    private RmsUserQueryService rmsUserQueryService;

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

    private MockMvc restRmsUserMockMvc;

    private RmsUser rmsUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RmsUserResource rmsUserResource = new RmsUserResource(rmsUserService, rmsUserQueryService);
        this.restRmsUserMockMvc = MockMvcBuilders.standaloneSetup(rmsUserResource)
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
    public static RmsUser createEntity(EntityManager em) {
        RmsUser rmsUser = new RmsUser()
            .userName(DEFAULT_USER_NAME)
            .personId(DEFAULT_PERSON_ID)
            .userPassword(DEFAULT_USER_PASSWORD)
            .processPassword(DEFAULT_PROCESS_PASSWORD)
            .userSort(DEFAULT_USER_SORT)
            .userDesc(DEFAULT_USER_DESC)
            .userPasswordValiInstantTimes(DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES)
            .userPasswordLockFlag(DEFAULT_USER_PASSWORD_LOCK_FLAG)
            .procPasswordValiInstantTimes(DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES)
            .procPasswordLockFlag(DEFAULT_PROC_PASSWORD_LOCK_FLAG)
            .userProp(DEFAULT_USER_PROP)
            .by01(DEFAULT_BY_01)
            .by02(DEFAULT_BY_02)
            .by03(DEFAULT_BY_03)
            .by04(DEFAULT_BY_04)
            .by05(DEFAULT_BY_05)
            .validType(DEFAULT_VALID_TYPE)
            .validBegin(DEFAULT_VALID_BEGIN)
            .validEnd(DEFAULT_VALID_END)
            .insertTime(DEFAULT_INSERT_TIME)
            .updateTime(DEFAULT_UPDATE_TIME)
            .verifyTime(DEFAULT_VERIFY_TIME)
            .serialNumber(DEFAULT_SERIAL_NUMBER);
        return rmsUser;
    }

    @Before
    public void initTest() {
        rmsUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createRmsUser() throws Exception {
        int databaseSizeBeforeCreate = rmsUserRepository.findAll().size();

        // Create the RmsUser
        RmsUserDTO rmsUserDTO = rmsUserMapper.toDto(rmsUser);
        restRmsUserMockMvc.perform(post("/api/rms-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsUserDTO)))
            .andExpect(status().isCreated());

        // Validate the RmsUser in the database
        List<RmsUser> rmsUserList = rmsUserRepository.findAll();
        assertThat(rmsUserList).hasSize(databaseSizeBeforeCreate + 1);
        RmsUser testRmsUser = rmsUserList.get(rmsUserList.size() - 1);
        assertThat(testRmsUser.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testRmsUser.getPersonId()).isEqualTo(DEFAULT_PERSON_ID);
        assertThat(testRmsUser.getUserPassword()).isEqualTo(DEFAULT_USER_PASSWORD);
        assertThat(testRmsUser.getProcessPassword()).isEqualTo(DEFAULT_PROCESS_PASSWORD);
        assertThat(testRmsUser.getUserSort()).isEqualTo(DEFAULT_USER_SORT);
        assertThat(testRmsUser.getUserDesc()).isEqualTo(DEFAULT_USER_DESC);
        assertThat(testRmsUser.getUserPasswordValiInstantTimes()).isEqualTo(DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES);
        assertThat(testRmsUser.isUserPasswordLockFlag()).isEqualTo(DEFAULT_USER_PASSWORD_LOCK_FLAG);
        assertThat(testRmsUser.getProcPasswordValiInstantTimes()).isEqualTo(DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES);
        assertThat(testRmsUser.getProcPasswordLockFlag()).isEqualTo(DEFAULT_PROC_PASSWORD_LOCK_FLAG);
        assertThat(testRmsUser.getUserProp()).isEqualTo(DEFAULT_USER_PROP);
        assertThat(testRmsUser.getBy01()).isEqualTo(DEFAULT_BY_01);
        assertThat(testRmsUser.getBy02()).isEqualTo(DEFAULT_BY_02);
        assertThat(testRmsUser.getBy03()).isEqualTo(DEFAULT_BY_03);
        assertThat(testRmsUser.getBy04()).isEqualTo(DEFAULT_BY_04);
        assertThat(testRmsUser.getBy05()).isEqualTo(DEFAULT_BY_05);
        assertThat(testRmsUser.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testRmsUser.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testRmsUser.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testRmsUser.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testRmsUser.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testRmsUser.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);
        assertThat(testRmsUser.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);

        // Validate the RmsUser in Elasticsearch
        verify(mockRmsUserSearchRepository, times(1)).save(testRmsUser);
    }

    @Test
    @Transactional
    public void createRmsUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rmsUserRepository.findAll().size();

        // Create the RmsUser with an existing ID
        rmsUser.setId(1L);
        RmsUserDTO rmsUserDTO = rmsUserMapper.toDto(rmsUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRmsUserMockMvc.perform(post("/api/rms-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RmsUser in the database
        List<RmsUser> rmsUserList = rmsUserRepository.findAll();
        assertThat(rmsUserList).hasSize(databaseSizeBeforeCreate);

        // Validate the RmsUser in Elasticsearch
        verify(mockRmsUserSearchRepository, times(0)).save(rmsUser);
    }

    @Test
    @Transactional
    public void getAllRmsUsers() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList
        restRmsUserMockMvc.perform(get("/api/rms-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].personId").value(hasItem(DEFAULT_PERSON_ID.toString())))
            .andExpect(jsonPath("$.[*].userPassword").value(hasItem(DEFAULT_USER_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].processPassword").value(hasItem(DEFAULT_PROCESS_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].userSort").value(hasItem(DEFAULT_USER_SORT.toString())))
            .andExpect(jsonPath("$.[*].userDesc").value(hasItem(DEFAULT_USER_DESC.toString())))
            .andExpect(jsonPath("$.[*].userPasswordValiInstantTimes").value(hasItem(DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES)))
            .andExpect(jsonPath("$.[*].userPasswordLockFlag").value(hasItem(DEFAULT_USER_PASSWORD_LOCK_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].procPasswordValiInstantTimes").value(hasItem(DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES)))
            .andExpect(jsonPath("$.[*].procPasswordLockFlag").value(hasItem(DEFAULT_PROC_PASSWORD_LOCK_FLAG.toString())))
            .andExpect(jsonPath("$.[*].userProp").value(hasItem(DEFAULT_USER_PROP.toString())))
            .andExpect(jsonPath("$.[*].by01").value(hasItem(DEFAULT_BY_01.toString())))
            .andExpect(jsonPath("$.[*].by02").value(hasItem(DEFAULT_BY_02.toString())))
            .andExpect(jsonPath("$.[*].by03").value(hasItem(DEFAULT_BY_03.toString())))
            .andExpect(jsonPath("$.[*].by04").value(hasItem(DEFAULT_BY_04.toString())))
            .andExpect(jsonPath("$.[*].by05").value(hasItem(DEFAULT_BY_05.toString())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllRmsUsersWithEagerRelationshipsIsEnabled() throws Exception {
        RmsUserResource rmsUserResource = new RmsUserResource(rmsUserServiceMock, rmsUserQueryService);
        when(rmsUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restRmsUserMockMvc = MockMvcBuilders.standaloneSetup(rmsUserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restRmsUserMockMvc.perform(get("/api/rms-users?eagerload=true"))
        .andExpect(status().isOk());

        verify(rmsUserServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllRmsUsersWithEagerRelationshipsIsNotEnabled() throws Exception {
        RmsUserResource rmsUserResource = new RmsUserResource(rmsUserServiceMock, rmsUserQueryService);
            when(rmsUserServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restRmsUserMockMvc = MockMvcBuilders.standaloneSetup(rmsUserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restRmsUserMockMvc.perform(get("/api/rms-users?eagerload=true"))
        .andExpect(status().isOk());

            verify(rmsUserServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getRmsUser() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get the rmsUser
        restRmsUserMockMvc.perform(get("/api/rms-users/{id}", rmsUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rmsUser.getId().intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.personId").value(DEFAULT_PERSON_ID.toString()))
            .andExpect(jsonPath("$.userPassword").value(DEFAULT_USER_PASSWORD.toString()))
            .andExpect(jsonPath("$.processPassword").value(DEFAULT_PROCESS_PASSWORD.toString()))
            .andExpect(jsonPath("$.userSort").value(DEFAULT_USER_SORT.toString()))
            .andExpect(jsonPath("$.userDesc").value(DEFAULT_USER_DESC.toString()))
            .andExpect(jsonPath("$.userPasswordValiInstantTimes").value(DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES))
            .andExpect(jsonPath("$.userPasswordLockFlag").value(DEFAULT_USER_PASSWORD_LOCK_FLAG.booleanValue()))
            .andExpect(jsonPath("$.procPasswordValiInstantTimes").value(DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES))
            .andExpect(jsonPath("$.procPasswordLockFlag").value(DEFAULT_PROC_PASSWORD_LOCK_FLAG.toString()))
            .andExpect(jsonPath("$.userProp").value(DEFAULT_USER_PROP.toString()))
            .andExpect(jsonPath("$.by01").value(DEFAULT_BY_01.toString()))
            .andExpect(jsonPath("$.by02").value(DEFAULT_BY_02.toString()))
            .andExpect(jsonPath("$.by03").value(DEFAULT_BY_03.toString()))
            .andExpect(jsonPath("$.by04").value(DEFAULT_BY_04.toString()))
            .andExpect(jsonPath("$.by05").value(DEFAULT_BY_05.toString()))
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
    public void getAllRmsUsersByUserNameIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userName equals to DEFAULT_USER_NAME
        defaultRmsUserShouldBeFound("userName.equals=" + DEFAULT_USER_NAME);

        // Get all the rmsUserList where userName equals to UPDATED_USER_NAME
        defaultRmsUserShouldNotBeFound("userName.equals=" + UPDATED_USER_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserNameIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userName in DEFAULT_USER_NAME or UPDATED_USER_NAME
        defaultRmsUserShouldBeFound("userName.in=" + DEFAULT_USER_NAME + "," + UPDATED_USER_NAME);

        // Get all the rmsUserList where userName equals to UPDATED_USER_NAME
        defaultRmsUserShouldNotBeFound("userName.in=" + UPDATED_USER_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userName is not null
        defaultRmsUserShouldBeFound("userName.specified=true");

        // Get all the rmsUserList where userName is null
        defaultRmsUserShouldNotBeFound("userName.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByPersonIdIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where personId equals to DEFAULT_PERSON_ID
        defaultRmsUserShouldBeFound("personId.equals=" + DEFAULT_PERSON_ID);

        // Get all the rmsUserList where personId equals to UPDATED_PERSON_ID
        defaultRmsUserShouldNotBeFound("personId.equals=" + UPDATED_PERSON_ID);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByPersonIdIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where personId in DEFAULT_PERSON_ID or UPDATED_PERSON_ID
        defaultRmsUserShouldBeFound("personId.in=" + DEFAULT_PERSON_ID + "," + UPDATED_PERSON_ID);

        // Get all the rmsUserList where personId equals to UPDATED_PERSON_ID
        defaultRmsUserShouldNotBeFound("personId.in=" + UPDATED_PERSON_ID);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByPersonIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where personId is not null
        defaultRmsUserShouldBeFound("personId.specified=true");

        // Get all the rmsUserList where personId is null
        defaultRmsUserShouldNotBeFound("personId.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPasswordIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userPassword equals to DEFAULT_USER_PASSWORD
        defaultRmsUserShouldBeFound("userPassword.equals=" + DEFAULT_USER_PASSWORD);

        // Get all the rmsUserList where userPassword equals to UPDATED_USER_PASSWORD
        defaultRmsUserShouldNotBeFound("userPassword.equals=" + UPDATED_USER_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPasswordIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userPassword in DEFAULT_USER_PASSWORD or UPDATED_USER_PASSWORD
        defaultRmsUserShouldBeFound("userPassword.in=" + DEFAULT_USER_PASSWORD + "," + UPDATED_USER_PASSWORD);

        // Get all the rmsUserList where userPassword equals to UPDATED_USER_PASSWORD
        defaultRmsUserShouldNotBeFound("userPassword.in=" + UPDATED_USER_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPasswordIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userPassword is not null
        defaultRmsUserShouldBeFound("userPassword.specified=true");

        // Get all the rmsUserList where userPassword is null
        defaultRmsUserShouldNotBeFound("userPassword.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByProcessPasswordIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where processPassword equals to DEFAULT_PROCESS_PASSWORD
        defaultRmsUserShouldBeFound("processPassword.equals=" + DEFAULT_PROCESS_PASSWORD);

        // Get all the rmsUserList where processPassword equals to UPDATED_PROCESS_PASSWORD
        defaultRmsUserShouldNotBeFound("processPassword.equals=" + UPDATED_PROCESS_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByProcessPasswordIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where processPassword in DEFAULT_PROCESS_PASSWORD or UPDATED_PROCESS_PASSWORD
        defaultRmsUserShouldBeFound("processPassword.in=" + DEFAULT_PROCESS_PASSWORD + "," + UPDATED_PROCESS_PASSWORD);

        // Get all the rmsUserList where processPassword equals to UPDATED_PROCESS_PASSWORD
        defaultRmsUserShouldNotBeFound("processPassword.in=" + UPDATED_PROCESS_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByProcessPasswordIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where processPassword is not null
        defaultRmsUserShouldBeFound("processPassword.specified=true");

        // Get all the rmsUserList where processPassword is null
        defaultRmsUserShouldNotBeFound("processPassword.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserSortIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userSort equals to DEFAULT_USER_SORT
        defaultRmsUserShouldBeFound("userSort.equals=" + DEFAULT_USER_SORT);

        // Get all the rmsUserList where userSort equals to UPDATED_USER_SORT
        defaultRmsUserShouldNotBeFound("userSort.equals=" + UPDATED_USER_SORT);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserSortIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userSort in DEFAULT_USER_SORT or UPDATED_USER_SORT
        defaultRmsUserShouldBeFound("userSort.in=" + DEFAULT_USER_SORT + "," + UPDATED_USER_SORT);

        // Get all the rmsUserList where userSort equals to UPDATED_USER_SORT
        defaultRmsUserShouldNotBeFound("userSort.in=" + UPDATED_USER_SORT);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserSortIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userSort is not null
        defaultRmsUserShouldBeFound("userSort.specified=true");

        // Get all the rmsUserList where userSort is null
        defaultRmsUserShouldNotBeFound("userSort.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserDescIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userDesc equals to DEFAULT_USER_DESC
        defaultRmsUserShouldBeFound("userDesc.equals=" + DEFAULT_USER_DESC);

        // Get all the rmsUserList where userDesc equals to UPDATED_USER_DESC
        defaultRmsUserShouldNotBeFound("userDesc.equals=" + UPDATED_USER_DESC);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserDescIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userDesc in DEFAULT_USER_DESC or UPDATED_USER_DESC
        defaultRmsUserShouldBeFound("userDesc.in=" + DEFAULT_USER_DESC + "," + UPDATED_USER_DESC);

        // Get all the rmsUserList where userDesc equals to UPDATED_USER_DESC
        defaultRmsUserShouldNotBeFound("userDesc.in=" + UPDATED_USER_DESC);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserDescIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userDesc is not null
        defaultRmsUserShouldBeFound("userDesc.specified=true");

        // Get all the rmsUserList where userDesc is null
        defaultRmsUserShouldNotBeFound("userDesc.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPasswordValiInstantTimesIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userPasswordValiInstantTimes equals to DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldBeFound("userPasswordValiInstantTimes.equals=" + DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES);

        // Get all the rmsUserList where userPasswordValiInstantTimes equals to UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldNotBeFound("userPasswordValiInstantTimes.equals=" + UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPasswordValiInstantTimesIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userPasswordValiInstantTimes in DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES or UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldBeFound("userPasswordValiInstantTimes.in=" + DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES + "," + UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES);

        // Get all the rmsUserList where userPasswordValiInstantTimes equals to UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldNotBeFound("userPasswordValiInstantTimes.in=" + UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPasswordValiInstantTimesIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userPasswordValiInstantTimes is not null
        defaultRmsUserShouldBeFound("userPasswordValiInstantTimes.specified=true");

        // Get all the rmsUserList where userPasswordValiInstantTimes is null
        defaultRmsUserShouldNotBeFound("userPasswordValiInstantTimes.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPasswordValiInstantTimesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userPasswordValiInstantTimes greater than or equals to DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldBeFound("userPasswordValiInstantTimes.greaterOrEqualThan=" + DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES);

        // Get all the rmsUserList where userPasswordValiInstantTimes greater than or equals to UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldNotBeFound("userPasswordValiInstantTimes.greaterOrEqualThan=" + UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPasswordValiInstantTimesIsLessThanSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userPasswordValiInstantTimes less than or equals to DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldNotBeFound("userPasswordValiInstantTimes.lessThan=" + DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES);

        // Get all the rmsUserList where userPasswordValiInstantTimes less than or equals to UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldBeFound("userPasswordValiInstantTimes.lessThan=" + UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES);
    }


    @Test
    @Transactional
    public void getAllRmsUsersByUserPasswordLockFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userPasswordLockFlag equals to DEFAULT_USER_PASSWORD_LOCK_FLAG
        defaultRmsUserShouldBeFound("userPasswordLockFlag.equals=" + DEFAULT_USER_PASSWORD_LOCK_FLAG);

        // Get all the rmsUserList where userPasswordLockFlag equals to UPDATED_USER_PASSWORD_LOCK_FLAG
        defaultRmsUserShouldNotBeFound("userPasswordLockFlag.equals=" + UPDATED_USER_PASSWORD_LOCK_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPasswordLockFlagIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userPasswordLockFlag in DEFAULT_USER_PASSWORD_LOCK_FLAG or UPDATED_USER_PASSWORD_LOCK_FLAG
        defaultRmsUserShouldBeFound("userPasswordLockFlag.in=" + DEFAULT_USER_PASSWORD_LOCK_FLAG + "," + UPDATED_USER_PASSWORD_LOCK_FLAG);

        // Get all the rmsUserList where userPasswordLockFlag equals to UPDATED_USER_PASSWORD_LOCK_FLAG
        defaultRmsUserShouldNotBeFound("userPasswordLockFlag.in=" + UPDATED_USER_PASSWORD_LOCK_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPasswordLockFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userPasswordLockFlag is not null
        defaultRmsUserShouldBeFound("userPasswordLockFlag.specified=true");

        // Get all the rmsUserList where userPasswordLockFlag is null
        defaultRmsUserShouldNotBeFound("userPasswordLockFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByProcPasswordValiInstantTimesIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where procPasswordValiInstantTimes equals to DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldBeFound("procPasswordValiInstantTimes.equals=" + DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES);

        // Get all the rmsUserList where procPasswordValiInstantTimes equals to UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldNotBeFound("procPasswordValiInstantTimes.equals=" + UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByProcPasswordValiInstantTimesIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where procPasswordValiInstantTimes in DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES or UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldBeFound("procPasswordValiInstantTimes.in=" + DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES + "," + UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES);

        // Get all the rmsUserList where procPasswordValiInstantTimes equals to UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldNotBeFound("procPasswordValiInstantTimes.in=" + UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByProcPasswordValiInstantTimesIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where procPasswordValiInstantTimes is not null
        defaultRmsUserShouldBeFound("procPasswordValiInstantTimes.specified=true");

        // Get all the rmsUserList where procPasswordValiInstantTimes is null
        defaultRmsUserShouldNotBeFound("procPasswordValiInstantTimes.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByProcPasswordValiInstantTimesIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where procPasswordValiInstantTimes greater than or equals to DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldBeFound("procPasswordValiInstantTimes.greaterOrEqualThan=" + DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES);

        // Get all the rmsUserList where procPasswordValiInstantTimes greater than or equals to UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldNotBeFound("procPasswordValiInstantTimes.greaterOrEqualThan=" + UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByProcPasswordValiInstantTimesIsLessThanSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where procPasswordValiInstantTimes less than or equals to DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldNotBeFound("procPasswordValiInstantTimes.lessThan=" + DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES);

        // Get all the rmsUserList where procPasswordValiInstantTimes less than or equals to UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES
        defaultRmsUserShouldBeFound("procPasswordValiInstantTimes.lessThan=" + UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES);
    }


    @Test
    @Transactional
    public void getAllRmsUsersByProcPasswordLockFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where procPasswordLockFlag equals to DEFAULT_PROC_PASSWORD_LOCK_FLAG
        defaultRmsUserShouldBeFound("procPasswordLockFlag.equals=" + DEFAULT_PROC_PASSWORD_LOCK_FLAG);

        // Get all the rmsUserList where procPasswordLockFlag equals to UPDATED_PROC_PASSWORD_LOCK_FLAG
        defaultRmsUserShouldNotBeFound("procPasswordLockFlag.equals=" + UPDATED_PROC_PASSWORD_LOCK_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByProcPasswordLockFlagIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where procPasswordLockFlag in DEFAULT_PROC_PASSWORD_LOCK_FLAG or UPDATED_PROC_PASSWORD_LOCK_FLAG
        defaultRmsUserShouldBeFound("procPasswordLockFlag.in=" + DEFAULT_PROC_PASSWORD_LOCK_FLAG + "," + UPDATED_PROC_PASSWORD_LOCK_FLAG);

        // Get all the rmsUserList where procPasswordLockFlag equals to UPDATED_PROC_PASSWORD_LOCK_FLAG
        defaultRmsUserShouldNotBeFound("procPasswordLockFlag.in=" + UPDATED_PROC_PASSWORD_LOCK_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByProcPasswordLockFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where procPasswordLockFlag is not null
        defaultRmsUserShouldBeFound("procPasswordLockFlag.specified=true");

        // Get all the rmsUserList where procPasswordLockFlag is null
        defaultRmsUserShouldNotBeFound("procPasswordLockFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPropIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userProp equals to DEFAULT_USER_PROP
        defaultRmsUserShouldBeFound("userProp.equals=" + DEFAULT_USER_PROP);

        // Get all the rmsUserList where userProp equals to UPDATED_USER_PROP
        defaultRmsUserShouldNotBeFound("userProp.equals=" + UPDATED_USER_PROP);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPropIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userProp in DEFAULT_USER_PROP or UPDATED_USER_PROP
        defaultRmsUserShouldBeFound("userProp.in=" + DEFAULT_USER_PROP + "," + UPDATED_USER_PROP);

        // Get all the rmsUserList where userProp equals to UPDATED_USER_PROP
        defaultRmsUserShouldNotBeFound("userProp.in=" + UPDATED_USER_PROP);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUserPropIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where userProp is not null
        defaultRmsUserShouldBeFound("userProp.specified=true");

        // Get all the rmsUserList where userProp is null
        defaultRmsUserShouldNotBeFound("userProp.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy01IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by01 equals to DEFAULT_BY_01
        defaultRmsUserShouldBeFound("by01.equals=" + DEFAULT_BY_01);

        // Get all the rmsUserList where by01 equals to UPDATED_BY_01
        defaultRmsUserShouldNotBeFound("by01.equals=" + UPDATED_BY_01);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy01IsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by01 in DEFAULT_BY_01 or UPDATED_BY_01
        defaultRmsUserShouldBeFound("by01.in=" + DEFAULT_BY_01 + "," + UPDATED_BY_01);

        // Get all the rmsUserList where by01 equals to UPDATED_BY_01
        defaultRmsUserShouldNotBeFound("by01.in=" + UPDATED_BY_01);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy01IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by01 is not null
        defaultRmsUserShouldBeFound("by01.specified=true");

        // Get all the rmsUserList where by01 is null
        defaultRmsUserShouldNotBeFound("by01.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy02IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by02 equals to DEFAULT_BY_02
        defaultRmsUserShouldBeFound("by02.equals=" + DEFAULT_BY_02);

        // Get all the rmsUserList where by02 equals to UPDATED_BY_02
        defaultRmsUserShouldNotBeFound("by02.equals=" + UPDATED_BY_02);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy02IsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by02 in DEFAULT_BY_02 or UPDATED_BY_02
        defaultRmsUserShouldBeFound("by02.in=" + DEFAULT_BY_02 + "," + UPDATED_BY_02);

        // Get all the rmsUserList where by02 equals to UPDATED_BY_02
        defaultRmsUserShouldNotBeFound("by02.in=" + UPDATED_BY_02);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy02IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by02 is not null
        defaultRmsUserShouldBeFound("by02.specified=true");

        // Get all the rmsUserList where by02 is null
        defaultRmsUserShouldNotBeFound("by02.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy03IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by03 equals to DEFAULT_BY_03
        defaultRmsUserShouldBeFound("by03.equals=" + DEFAULT_BY_03);

        // Get all the rmsUserList where by03 equals to UPDATED_BY_03
        defaultRmsUserShouldNotBeFound("by03.equals=" + UPDATED_BY_03);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy03IsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by03 in DEFAULT_BY_03 or UPDATED_BY_03
        defaultRmsUserShouldBeFound("by03.in=" + DEFAULT_BY_03 + "," + UPDATED_BY_03);

        // Get all the rmsUserList where by03 equals to UPDATED_BY_03
        defaultRmsUserShouldNotBeFound("by03.in=" + UPDATED_BY_03);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy03IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by03 is not null
        defaultRmsUserShouldBeFound("by03.specified=true");

        // Get all the rmsUserList where by03 is null
        defaultRmsUserShouldNotBeFound("by03.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy04IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by04 equals to DEFAULT_BY_04
        defaultRmsUserShouldBeFound("by04.equals=" + DEFAULT_BY_04);

        // Get all the rmsUserList where by04 equals to UPDATED_BY_04
        defaultRmsUserShouldNotBeFound("by04.equals=" + UPDATED_BY_04);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy04IsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by04 in DEFAULT_BY_04 or UPDATED_BY_04
        defaultRmsUserShouldBeFound("by04.in=" + DEFAULT_BY_04 + "," + UPDATED_BY_04);

        // Get all the rmsUserList where by04 equals to UPDATED_BY_04
        defaultRmsUserShouldNotBeFound("by04.in=" + UPDATED_BY_04);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy04IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by04 is not null
        defaultRmsUserShouldBeFound("by04.specified=true");

        // Get all the rmsUserList where by04 is null
        defaultRmsUserShouldNotBeFound("by04.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy05IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by05 equals to DEFAULT_BY_05
        defaultRmsUserShouldBeFound("by05.equals=" + DEFAULT_BY_05);

        // Get all the rmsUserList where by05 equals to UPDATED_BY_05
        defaultRmsUserShouldNotBeFound("by05.equals=" + UPDATED_BY_05);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy05IsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by05 in DEFAULT_BY_05 or UPDATED_BY_05
        defaultRmsUserShouldBeFound("by05.in=" + DEFAULT_BY_05 + "," + UPDATED_BY_05);

        // Get all the rmsUserList where by05 equals to UPDATED_BY_05
        defaultRmsUserShouldNotBeFound("by05.in=" + UPDATED_BY_05);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByBy05IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where by05 is not null
        defaultRmsUserShouldBeFound("by05.specified=true");

        // Get all the rmsUserList where by05 is null
        defaultRmsUserShouldNotBeFound("by05.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where validType equals to DEFAULT_VALID_TYPE
        defaultRmsUserShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the rmsUserList where validType equals to UPDATED_VALID_TYPE
        defaultRmsUserShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultRmsUserShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the rmsUserList where validType equals to UPDATED_VALID_TYPE
        defaultRmsUserShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where validType is not null
        defaultRmsUserShouldBeFound("validType.specified=true");

        // Get all the rmsUserList where validType is null
        defaultRmsUserShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultRmsUserShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the rmsUserList where validBegin equals to UPDATED_VALID_BEGIN
        defaultRmsUserShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultRmsUserShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the rmsUserList where validBegin equals to UPDATED_VALID_BEGIN
        defaultRmsUserShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where validBegin is not null
        defaultRmsUserShouldBeFound("validBegin.specified=true");

        // Get all the rmsUserList where validBegin is null
        defaultRmsUserShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where validEnd equals to DEFAULT_VALID_END
        defaultRmsUserShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the rmsUserList where validEnd equals to UPDATED_VALID_END
        defaultRmsUserShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultRmsUserShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the rmsUserList where validEnd equals to UPDATED_VALID_END
        defaultRmsUserShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where validEnd is not null
        defaultRmsUserShouldBeFound("validEnd.specified=true");

        // Get all the rmsUserList where validEnd is null
        defaultRmsUserShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where insertTime equals to DEFAULT_INSERT_TIME
        defaultRmsUserShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the rmsUserList where insertTime equals to UPDATED_INSERT_TIME
        defaultRmsUserShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultRmsUserShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the rmsUserList where insertTime equals to UPDATED_INSERT_TIME
        defaultRmsUserShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where insertTime is not null
        defaultRmsUserShouldBeFound("insertTime.specified=true");

        // Get all the rmsUserList where insertTime is null
        defaultRmsUserShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultRmsUserShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the rmsUserList where updateTime equals to UPDATED_UPDATE_TIME
        defaultRmsUserShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultRmsUserShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the rmsUserList where updateTime equals to UPDATED_UPDATE_TIME
        defaultRmsUserShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where updateTime is not null
        defaultRmsUserShouldBeFound("updateTime.specified=true");

        // Get all the rmsUserList where updateTime is null
        defaultRmsUserShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultRmsUserShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the rmsUserList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultRmsUserShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultRmsUserShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the rmsUserList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultRmsUserShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsUsersByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where verifyTime is not null
        defaultRmsUserShouldBeFound("verifyTime.specified=true");

        // Get all the rmsUserList where verifyTime is null
        defaultRmsUserShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultRmsUserShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the rmsUserList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultRmsUserShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllRmsUsersBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultRmsUserShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the rmsUserList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultRmsUserShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllRmsUsersBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        // Get all the rmsUserList where serialNumber is not null
        defaultRmsUserShouldBeFound("serialNumber.specified=true");

        // Get all the rmsUserList where serialNumber is null
        defaultRmsUserShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsUsersByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        rmsUser.setCreatedBy(createdBy);
        rmsUserRepository.saveAndFlush(rmsUser);
        Long createdById = createdBy.getId();

        // Get all the rmsUserList where createdBy equals to createdById
        defaultRmsUserShouldBeFound("createdById.equals=" + createdById);

        // Get all the rmsUserList where createdBy equals to createdById + 1
        defaultRmsUserShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsUsersByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        rmsUser.setModifiedBy(modifiedBy);
        rmsUserRepository.saveAndFlush(rmsUser);
        Long modifiedById = modifiedBy.getId();

        // Get all the rmsUserList where modifiedBy equals to modifiedById
        defaultRmsUserShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the rmsUserList where modifiedBy equals to modifiedById + 1
        defaultRmsUserShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsUsersByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        rmsUser.setVerifiedBy(verifiedBy);
        rmsUserRepository.saveAndFlush(rmsUser);
        Long verifiedById = verifiedBy.getId();

        // Get all the rmsUserList where verifiedBy equals to verifiedById
        defaultRmsUserShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the rmsUserList where verifiedBy equals to verifiedById + 1
        defaultRmsUserShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsUsersByRmsRoleIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsRole rmsRole = RmsRoleResourceIntTest.createEntity(em);
        em.persist(rmsRole);
        em.flush();
        rmsUser.addRmsRole(rmsRole);
        rmsUserRepository.saveAndFlush(rmsUser);
        Long rmsRoleId = rmsRole.getId();

        // Get all the rmsUserList where rmsRole equals to rmsRoleId
        defaultRmsUserShouldBeFound("rmsRoleId.equals=" + rmsRoleId);

        // Get all the rmsUserList where rmsRole equals to rmsRoleId + 1
        defaultRmsUserShouldNotBeFound("rmsRoleId.equals=" + (rmsRoleId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultRmsUserShouldBeFound(String filter) throws Exception {
        restRmsUserMockMvc.perform(get("/api/rms-users?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].personId").value(hasItem(DEFAULT_PERSON_ID.toString())))
            .andExpect(jsonPath("$.[*].userPassword").value(hasItem(DEFAULT_USER_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].processPassword").value(hasItem(DEFAULT_PROCESS_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].userSort").value(hasItem(DEFAULT_USER_SORT.toString())))
            .andExpect(jsonPath("$.[*].userDesc").value(hasItem(DEFAULT_USER_DESC.toString())))
            .andExpect(jsonPath("$.[*].userPasswordValiInstantTimes").value(hasItem(DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES)))
            .andExpect(jsonPath("$.[*].userPasswordLockFlag").value(hasItem(DEFAULT_USER_PASSWORD_LOCK_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].procPasswordValiInstantTimes").value(hasItem(DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES)))
            .andExpect(jsonPath("$.[*].procPasswordLockFlag").value(hasItem(DEFAULT_PROC_PASSWORD_LOCK_FLAG.toString())))
            .andExpect(jsonPath("$.[*].userProp").value(hasItem(DEFAULT_USER_PROP.toString())))
            .andExpect(jsonPath("$.[*].by01").value(hasItem(DEFAULT_BY_01.toString())))
            .andExpect(jsonPath("$.[*].by02").value(hasItem(DEFAULT_BY_02.toString())))
            .andExpect(jsonPath("$.[*].by03").value(hasItem(DEFAULT_BY_03.toString())))
            .andExpect(jsonPath("$.[*].by04").value(hasItem(DEFAULT_BY_04.toString())))
            .andExpect(jsonPath("$.[*].by05").value(hasItem(DEFAULT_BY_05.toString())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())));

        // Check, that the count call also returns 1
        restRmsUserMockMvc.perform(get("/api/rms-users/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultRmsUserShouldNotBeFound(String filter) throws Exception {
        restRmsUserMockMvc.perform(get("/api/rms-users?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRmsUserMockMvc.perform(get("/api/rms-users/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingRmsUser() throws Exception {
        // Get the rmsUser
        restRmsUserMockMvc.perform(get("/api/rms-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRmsUser() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        int databaseSizeBeforeUpdate = rmsUserRepository.findAll().size();

        // Update the rmsUser
        RmsUser updatedRmsUser = rmsUserRepository.findById(rmsUser.getId()).get();
        // Disconnect from session so that the updates on updatedRmsUser are not directly saved in db
        em.detach(updatedRmsUser);
        updatedRmsUser
            .userName(UPDATED_USER_NAME)
            .personId(UPDATED_PERSON_ID)
            .userPassword(UPDATED_USER_PASSWORD)
            .processPassword(UPDATED_PROCESS_PASSWORD)
            .userSort(UPDATED_USER_SORT)
            .userDesc(UPDATED_USER_DESC)
            .userPasswordValiInstantTimes(UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES)
            .userPasswordLockFlag(UPDATED_USER_PASSWORD_LOCK_FLAG)
            .procPasswordValiInstantTimes(UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES)
            .procPasswordLockFlag(UPDATED_PROC_PASSWORD_LOCK_FLAG)
            .userProp(UPDATED_USER_PROP)
            .by01(UPDATED_BY_01)
            .by02(UPDATED_BY_02)
            .by03(UPDATED_BY_03)
            .by04(UPDATED_BY_04)
            .by05(UPDATED_BY_05)
            .validType(UPDATED_VALID_TYPE)
            .validBegin(UPDATED_VALID_BEGIN)
            .validEnd(UPDATED_VALID_END)
            .insertTime(UPDATED_INSERT_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .verifyTime(UPDATED_VERIFY_TIME)
            .serialNumber(UPDATED_SERIAL_NUMBER);
        RmsUserDTO rmsUserDTO = rmsUserMapper.toDto(updatedRmsUser);

        restRmsUserMockMvc.perform(put("/api/rms-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsUserDTO)))
            .andExpect(status().isOk());

        // Validate the RmsUser in the database
        List<RmsUser> rmsUserList = rmsUserRepository.findAll();
        assertThat(rmsUserList).hasSize(databaseSizeBeforeUpdate);
        RmsUser testRmsUser = rmsUserList.get(rmsUserList.size() - 1);
        assertThat(testRmsUser.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testRmsUser.getPersonId()).isEqualTo(UPDATED_PERSON_ID);
        assertThat(testRmsUser.getUserPassword()).isEqualTo(UPDATED_USER_PASSWORD);
        assertThat(testRmsUser.getProcessPassword()).isEqualTo(UPDATED_PROCESS_PASSWORD);
        assertThat(testRmsUser.getUserSort()).isEqualTo(UPDATED_USER_SORT);
        assertThat(testRmsUser.getUserDesc()).isEqualTo(UPDATED_USER_DESC);
        assertThat(testRmsUser.getUserPasswordValiInstantTimes()).isEqualTo(UPDATED_USER_PASSWORD_VALI_INSTANT_TIMES);
        assertThat(testRmsUser.isUserPasswordLockFlag()).isEqualTo(UPDATED_USER_PASSWORD_LOCK_FLAG);
        assertThat(testRmsUser.getProcPasswordValiInstantTimes()).isEqualTo(UPDATED_PROC_PASSWORD_VALI_INSTANT_TIMES);
        assertThat(testRmsUser.getProcPasswordLockFlag()).isEqualTo(UPDATED_PROC_PASSWORD_LOCK_FLAG);
        assertThat(testRmsUser.getUserProp()).isEqualTo(UPDATED_USER_PROP);
        assertThat(testRmsUser.getBy01()).isEqualTo(UPDATED_BY_01);
        assertThat(testRmsUser.getBy02()).isEqualTo(UPDATED_BY_02);
        assertThat(testRmsUser.getBy03()).isEqualTo(UPDATED_BY_03);
        assertThat(testRmsUser.getBy04()).isEqualTo(UPDATED_BY_04);
        assertThat(testRmsUser.getBy05()).isEqualTo(UPDATED_BY_05);
        assertThat(testRmsUser.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testRmsUser.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testRmsUser.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testRmsUser.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testRmsUser.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testRmsUser.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);
        assertThat(testRmsUser.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);

        // Validate the RmsUser in Elasticsearch
        verify(mockRmsUserSearchRepository, times(1)).save(testRmsUser);
    }

    @Test
    @Transactional
    public void updateNonExistingRmsUser() throws Exception {
        int databaseSizeBeforeUpdate = rmsUserRepository.findAll().size();

        // Create the RmsUser
        RmsUserDTO rmsUserDTO = rmsUserMapper.toDto(rmsUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRmsUserMockMvc.perform(put("/api/rms-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RmsUser in the database
        List<RmsUser> rmsUserList = rmsUserRepository.findAll();
        assertThat(rmsUserList).hasSize(databaseSizeBeforeUpdate);

        // Validate the RmsUser in Elasticsearch
        verify(mockRmsUserSearchRepository, times(0)).save(rmsUser);
    }

    @Test
    @Transactional
    public void deleteRmsUser() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);

        int databaseSizeBeforeDelete = rmsUserRepository.findAll().size();

        // Get the rmsUser
        restRmsUserMockMvc.perform(delete("/api/rms-users/{id}", rmsUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RmsUser> rmsUserList = rmsUserRepository.findAll();
        assertThat(rmsUserList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the RmsUser in Elasticsearch
        verify(mockRmsUserSearchRepository, times(1)).deleteById(rmsUser.getId());
    }

    @Test
    @Transactional
    public void searchRmsUser() throws Exception {
        // Initialize the database
        rmsUserRepository.saveAndFlush(rmsUser);
        when(mockRmsUserSearchRepository.search(queryStringQuery("id:" + rmsUser.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(rmsUser), PageRequest.of(0, 1), 1));
        // Search the rmsUser
        restRmsUserMockMvc.perform(get("/api/_search/rms-users?query=id:" + rmsUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].personId").value(hasItem(DEFAULT_PERSON_ID)))
            .andExpect(jsonPath("$.[*].userPassword").value(hasItem(DEFAULT_USER_PASSWORD)))
            .andExpect(jsonPath("$.[*].processPassword").value(hasItem(DEFAULT_PROCESS_PASSWORD)))
            .andExpect(jsonPath("$.[*].userSort").value(hasItem(DEFAULT_USER_SORT)))
            .andExpect(jsonPath("$.[*].userDesc").value(hasItem(DEFAULT_USER_DESC)))
            .andExpect(jsonPath("$.[*].userPasswordValiInstantTimes").value(hasItem(DEFAULT_USER_PASSWORD_VALI_INSTANT_TIMES)))
            .andExpect(jsonPath("$.[*].userPasswordLockFlag").value(hasItem(DEFAULT_USER_PASSWORD_LOCK_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].procPasswordValiInstantTimes").value(hasItem(DEFAULT_PROC_PASSWORD_VALI_INSTANT_TIMES)))
            .andExpect(jsonPath("$.[*].procPasswordLockFlag").value(hasItem(DEFAULT_PROC_PASSWORD_LOCK_FLAG)))
            .andExpect(jsonPath("$.[*].userProp").value(hasItem(DEFAULT_USER_PROP)))
            .andExpect(jsonPath("$.[*].by01").value(hasItem(DEFAULT_BY_01)))
            .andExpect(jsonPath("$.[*].by02").value(hasItem(DEFAULT_BY_02)))
            .andExpect(jsonPath("$.[*].by03").value(hasItem(DEFAULT_BY_03)))
            .andExpect(jsonPath("$.[*].by04").value(hasItem(DEFAULT_BY_04)))
            .andExpect(jsonPath("$.[*].by05").value(hasItem(DEFAULT_BY_05)))
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
        TestUtil.equalsVerifier(RmsUser.class);
        RmsUser rmsUser1 = new RmsUser();
        rmsUser1.setId(1L);
        RmsUser rmsUser2 = new RmsUser();
        rmsUser2.setId(rmsUser1.getId());
        assertThat(rmsUser1).isEqualTo(rmsUser2);
        rmsUser2.setId(2L);
        assertThat(rmsUser1).isNotEqualTo(rmsUser2);
        rmsUser1.setId(null);
        assertThat(rmsUser1).isNotEqualTo(rmsUser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RmsUserDTO.class);
        RmsUserDTO rmsUserDTO1 = new RmsUserDTO();
        rmsUserDTO1.setId(1L);
        RmsUserDTO rmsUserDTO2 = new RmsUserDTO();
        assertThat(rmsUserDTO1).isNotEqualTo(rmsUserDTO2);
        rmsUserDTO2.setId(rmsUserDTO1.getId());
        assertThat(rmsUserDTO1).isEqualTo(rmsUserDTO2);
        rmsUserDTO2.setId(2L);
        assertThat(rmsUserDTO1).isNotEqualTo(rmsUserDTO2);
        rmsUserDTO1.setId(null);
        assertThat(rmsUserDTO1).isNotEqualTo(rmsUserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rmsUserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rmsUserMapper.fromId(null)).isNull();
    }
}
