package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.RmsPerson;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.repository.RmsPersonRepository;
import com.aerothinker.plandb.repository.search.RmsPersonSearchRepository;
import com.aerothinker.plandb.service.RmsPersonService;
import com.aerothinker.plandb.service.dto.RmsPersonDTO;
import com.aerothinker.plandb.service.mapper.RmsPersonMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.RmsPersonCriteria;
import com.aerothinker.plandb.service.RmsPersonQueryService;

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
 * Test class for the RmsPersonResource REST controller.
 *
 * @see RmsPersonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class RmsPersonResourceIntTest {

    private static final String DEFAULT_PERSON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_ID = "AAAAAAAAAA";
    private static final String UPDATED_JOB_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SEX = false;
    private static final Boolean UPDATED_SEX = true;

    private static final Instant DEFAULT_BIRTHDAY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BIRTHDAY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PIC = "AAAAAAAAAA";
    private static final String UPDATED_PIC = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_DEP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_DEP_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_DEP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DEP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DUTY_ID = "AAAAAAAAAA";
    private static final String UPDATED_DUTY_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DIMISSION_FLAG = false;
    private static final Boolean UPDATED_DIMISSION_FLAG = true;

    private static final Boolean DEFAULT_HEADER_FLAG = false;
    private static final Boolean UPDATED_HEADER_FLAG = true;

    private static final Boolean DEFAULT_SATRAP_FLAG = false;
    private static final Boolean UPDATED_SATRAP_FLAG = true;

    private static final Boolean DEFAULT_LEADER_FLAG = false;
    private static final Boolean UPDATED_LEADER_FLAG = true;

    private static final Boolean DEFAULT_POST_FLAG_1 = false;
    private static final Boolean UPDATED_POST_FLAG_1 = true;

    private static final Boolean DEFAULT_POST_FLAG_2 = false;
    private static final Boolean UPDATED_POST_FLAG_2 = true;

    private static final Boolean DEFAULT_POST_FLAG_3 = false;
    private static final Boolean UPDATED_POST_FLAG_3 = true;

    private static final String DEFAULT_OFFICE_TEL = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL_1 = "AAAAAAAAAA";
    private static final String UPDATED_MAIL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL_2 = "AAAAAAAAAA";
    private static final String UPDATED_MAIL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY_TEL = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY_ADD = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_ADD = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FAMILY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PERSON_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_ID_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ID_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_POP_3 = "AAAAAAAAAA";
    private static final String UPDATED_POP_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SMTP = "AAAAAAAAAA";
    private static final String UPDATED_SMTP = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_MAIL_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_MAIL_PASSWORD = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MAIL_KEEP_FLAG = false;
    private static final Boolean UPDATED_MAIL_KEEP_FLAG = true;

    private static final String DEFAULT_PERSON_SORT = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_SORT = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL_NUM = 1;
    private static final Integer UPDATED_LEVEL_NUM = 2;

    private static final String DEFAULT_PERSON_STATE_ID = "AAAAAAAAAA";
    private static final String UPDATED_PERSON_STATE_ID = "BBBBBBBBBB";

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
    private RmsPersonRepository rmsPersonRepository;

    @Autowired
    private RmsPersonMapper rmsPersonMapper;

    @Autowired
    private RmsPersonService rmsPersonService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.RmsPersonSearchRepositoryMockConfiguration
     */
    @Autowired
    private RmsPersonSearchRepository mockRmsPersonSearchRepository;

    @Autowired
    private RmsPersonQueryService rmsPersonQueryService;

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

    private MockMvc restRmsPersonMockMvc;

    private RmsPerson rmsPerson;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RmsPersonResource rmsPersonResource = new RmsPersonResource(rmsPersonService, rmsPersonQueryService);
        this.restRmsPersonMockMvc = MockMvcBuilders.standaloneSetup(rmsPersonResource)
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
    public static RmsPerson createEntity(EntityManager em) {
        RmsPerson rmsPerson = new RmsPerson()
            .personName(DEFAULT_PERSON_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .jobId(DEFAULT_JOB_ID)
            .lastName(DEFAULT_LAST_NAME)
            .otherName(DEFAULT_OTHER_NAME)
            .sex(DEFAULT_SEX)
            .birthday(DEFAULT_BIRTHDAY)
            .pic(DEFAULT_PIC)
            .icon(DEFAULT_ICON)
            .mobile(DEFAULT_MOBILE)
            .depAddress(DEFAULT_DEP_ADDRESS)
            .depCode(DEFAULT_DEP_CODE)
            .dutyId(DEFAULT_DUTY_ID)
            .dimissionFlag(DEFAULT_DIMISSION_FLAG)
            .headerFlag(DEFAULT_HEADER_FLAG)
            .satrapFlag(DEFAULT_SATRAP_FLAG)
            .leaderFlag(DEFAULT_LEADER_FLAG)
            .postFlag1(DEFAULT_POST_FLAG_1)
            .postFlag2(DEFAULT_POST_FLAG_2)
            .postFlag3(DEFAULT_POST_FLAG_3)
            .officeTel(DEFAULT_OFFICE_TEL)
            .fax(DEFAULT_FAX)
            .mail1(DEFAULT_MAIL_1)
            .mail2(DEFAULT_MAIL_2)
            .familyTel(DEFAULT_FAMILY_TEL)
            .familyAdd(DEFAULT_FAMILY_ADD)
            .familyCode(DEFAULT_FAMILY_CODE)
            .personDesc(DEFAULT_PERSON_DESC)
            .idCode(DEFAULT_ID_CODE)
            .pop3(DEFAULT_POP_3)
            .smtp(DEFAULT_SMTP)
            .mailUsername(DEFAULT_MAIL_USERNAME)
            .mailPassword(DEFAULT_MAIL_PASSWORD)
            .mailKeepFlag(DEFAULT_MAIL_KEEP_FLAG)
            .personSort(DEFAULT_PERSON_SORT)
            .levelNum(DEFAULT_LEVEL_NUM)
            .personStateId(DEFAULT_PERSON_STATE_ID)
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
        return rmsPerson;
    }

    @Before
    public void initTest() {
        rmsPerson = createEntity(em);
    }

    @Test
    @Transactional
    public void createRmsPerson() throws Exception {
        int databaseSizeBeforeCreate = rmsPersonRepository.findAll().size();

        // Create the RmsPerson
        RmsPersonDTO rmsPersonDTO = rmsPersonMapper.toDto(rmsPerson);
        restRmsPersonMockMvc.perform(post("/api/rms-people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsPersonDTO)))
            .andExpect(status().isCreated());

        // Validate the RmsPerson in the database
        List<RmsPerson> rmsPersonList = rmsPersonRepository.findAll();
        assertThat(rmsPersonList).hasSize(databaseSizeBeforeCreate + 1);
        RmsPerson testRmsPerson = rmsPersonList.get(rmsPersonList.size() - 1);
        assertThat(testRmsPerson.getPersonName()).isEqualTo(DEFAULT_PERSON_NAME);
        assertThat(testRmsPerson.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testRmsPerson.getJobId()).isEqualTo(DEFAULT_JOB_ID);
        assertThat(testRmsPerson.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testRmsPerson.getOtherName()).isEqualTo(DEFAULT_OTHER_NAME);
        assertThat(testRmsPerson.isSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testRmsPerson.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
        assertThat(testRmsPerson.getPic()).isEqualTo(DEFAULT_PIC);
        assertThat(testRmsPerson.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testRmsPerson.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testRmsPerson.getDepAddress()).isEqualTo(DEFAULT_DEP_ADDRESS);
        assertThat(testRmsPerson.getDepCode()).isEqualTo(DEFAULT_DEP_CODE);
        assertThat(testRmsPerson.getDutyId()).isEqualTo(DEFAULT_DUTY_ID);
        assertThat(testRmsPerson.isDimissionFlag()).isEqualTo(DEFAULT_DIMISSION_FLAG);
        assertThat(testRmsPerson.isHeaderFlag()).isEqualTo(DEFAULT_HEADER_FLAG);
        assertThat(testRmsPerson.isSatrapFlag()).isEqualTo(DEFAULT_SATRAP_FLAG);
        assertThat(testRmsPerson.isLeaderFlag()).isEqualTo(DEFAULT_LEADER_FLAG);
        assertThat(testRmsPerson.isPostFlag1()).isEqualTo(DEFAULT_POST_FLAG_1);
        assertThat(testRmsPerson.isPostFlag2()).isEqualTo(DEFAULT_POST_FLAG_2);
        assertThat(testRmsPerson.isPostFlag3()).isEqualTo(DEFAULT_POST_FLAG_3);
        assertThat(testRmsPerson.getOfficeTel()).isEqualTo(DEFAULT_OFFICE_TEL);
        assertThat(testRmsPerson.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testRmsPerson.getMail1()).isEqualTo(DEFAULT_MAIL_1);
        assertThat(testRmsPerson.getMail2()).isEqualTo(DEFAULT_MAIL_2);
        assertThat(testRmsPerson.getFamilyTel()).isEqualTo(DEFAULT_FAMILY_TEL);
        assertThat(testRmsPerson.getFamilyAdd()).isEqualTo(DEFAULT_FAMILY_ADD);
        assertThat(testRmsPerson.getFamilyCode()).isEqualTo(DEFAULT_FAMILY_CODE);
        assertThat(testRmsPerson.getPersonDesc()).isEqualTo(DEFAULT_PERSON_DESC);
        assertThat(testRmsPerson.getIdCode()).isEqualTo(DEFAULT_ID_CODE);
        assertThat(testRmsPerson.getPop3()).isEqualTo(DEFAULT_POP_3);
        assertThat(testRmsPerson.getSmtp()).isEqualTo(DEFAULT_SMTP);
        assertThat(testRmsPerson.getMailUsername()).isEqualTo(DEFAULT_MAIL_USERNAME);
        assertThat(testRmsPerson.getMailPassword()).isEqualTo(DEFAULT_MAIL_PASSWORD);
        assertThat(testRmsPerson.isMailKeepFlag()).isEqualTo(DEFAULT_MAIL_KEEP_FLAG);
        assertThat(testRmsPerson.getPersonSort()).isEqualTo(DEFAULT_PERSON_SORT);
        assertThat(testRmsPerson.getLevelNum()).isEqualTo(DEFAULT_LEVEL_NUM);
        assertThat(testRmsPerson.getPersonStateId()).isEqualTo(DEFAULT_PERSON_STATE_ID);
        assertThat(testRmsPerson.getBy01()).isEqualTo(DEFAULT_BY_01);
        assertThat(testRmsPerson.getBy02()).isEqualTo(DEFAULT_BY_02);
        assertThat(testRmsPerson.getBy03()).isEqualTo(DEFAULT_BY_03);
        assertThat(testRmsPerson.getBy04()).isEqualTo(DEFAULT_BY_04);
        assertThat(testRmsPerson.getBy05()).isEqualTo(DEFAULT_BY_05);
        assertThat(testRmsPerson.getBy06()).isEqualTo(DEFAULT_BY_06);
        assertThat(testRmsPerson.getBy07()).isEqualTo(DEFAULT_BY_07);
        assertThat(testRmsPerson.getBy08()).isEqualTo(DEFAULT_BY_08);
        assertThat(testRmsPerson.getBy09()).isEqualTo(DEFAULT_BY_09);
        assertThat(testRmsPerson.getBy10()).isEqualTo(DEFAULT_BY_10);
        assertThat(testRmsPerson.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testRmsPerson.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testRmsPerson.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testRmsPerson.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testRmsPerson.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testRmsPerson.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);
        assertThat(testRmsPerson.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);

        // Validate the RmsPerson in Elasticsearch
        verify(mockRmsPersonSearchRepository, times(1)).save(testRmsPerson);
    }

    @Test
    @Transactional
    public void createRmsPersonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rmsPersonRepository.findAll().size();

        // Create the RmsPerson with an existing ID
        rmsPerson.setId(1L);
        RmsPersonDTO rmsPersonDTO = rmsPersonMapper.toDto(rmsPerson);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRmsPersonMockMvc.perform(post("/api/rms-people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsPersonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RmsPerson in the database
        List<RmsPerson> rmsPersonList = rmsPersonRepository.findAll();
        assertThat(rmsPersonList).hasSize(databaseSizeBeforeCreate);

        // Validate the RmsPerson in Elasticsearch
        verify(mockRmsPersonSearchRepository, times(0)).save(rmsPerson);
    }

    @Test
    @Transactional
    public void getAllRmsPeople() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList
        restRmsPersonMockMvc.perform(get("/api/rms-people?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsPerson.getId().intValue())))
            .andExpect(jsonPath("$.[*].personName").value(hasItem(DEFAULT_PERSON_NAME.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].jobId").value(hasItem(DEFAULT_JOB_ID.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].otherName").value(hasItem(DEFAULT_OTHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.booleanValue())))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY.toString())))
            .andExpect(jsonPath("$.[*].pic").value(hasItem(DEFAULT_PIC.toString())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].depAddress").value(hasItem(DEFAULT_DEP_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].depCode").value(hasItem(DEFAULT_DEP_CODE.toString())))
            .andExpect(jsonPath("$.[*].dutyId").value(hasItem(DEFAULT_DUTY_ID.toString())))
            .andExpect(jsonPath("$.[*].dimissionFlag").value(hasItem(DEFAULT_DIMISSION_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].headerFlag").value(hasItem(DEFAULT_HEADER_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].satrapFlag").value(hasItem(DEFAULT_SATRAP_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].leaderFlag").value(hasItem(DEFAULT_LEADER_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].postFlag1").value(hasItem(DEFAULT_POST_FLAG_1.booleanValue())))
            .andExpect(jsonPath("$.[*].postFlag2").value(hasItem(DEFAULT_POST_FLAG_2.booleanValue())))
            .andExpect(jsonPath("$.[*].postFlag3").value(hasItem(DEFAULT_POST_FLAG_3.booleanValue())))
            .andExpect(jsonPath("$.[*].officeTel").value(hasItem(DEFAULT_OFFICE_TEL.toString())))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX.toString())))
            .andExpect(jsonPath("$.[*].mail1").value(hasItem(DEFAULT_MAIL_1.toString())))
            .andExpect(jsonPath("$.[*].mail2").value(hasItem(DEFAULT_MAIL_2.toString())))
            .andExpect(jsonPath("$.[*].familyTel").value(hasItem(DEFAULT_FAMILY_TEL.toString())))
            .andExpect(jsonPath("$.[*].familyAdd").value(hasItem(DEFAULT_FAMILY_ADD.toString())))
            .andExpect(jsonPath("$.[*].familyCode").value(hasItem(DEFAULT_FAMILY_CODE.toString())))
            .andExpect(jsonPath("$.[*].personDesc").value(hasItem(DEFAULT_PERSON_DESC.toString())))
            .andExpect(jsonPath("$.[*].idCode").value(hasItem(DEFAULT_ID_CODE.toString())))
            .andExpect(jsonPath("$.[*].pop3").value(hasItem(DEFAULT_POP_3.toString())))
            .andExpect(jsonPath("$.[*].smtp").value(hasItem(DEFAULT_SMTP.toString())))
            .andExpect(jsonPath("$.[*].mailUsername").value(hasItem(DEFAULT_MAIL_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].mailPassword").value(hasItem(DEFAULT_MAIL_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].mailKeepFlag").value(hasItem(DEFAULT_MAIL_KEEP_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].personSort").value(hasItem(DEFAULT_PERSON_SORT.toString())))
            .andExpect(jsonPath("$.[*].levelNum").value(hasItem(DEFAULT_LEVEL_NUM)))
            .andExpect(jsonPath("$.[*].personStateId").value(hasItem(DEFAULT_PERSON_STATE_ID.toString())))
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
    public void getRmsPerson() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get the rmsPerson
        restRmsPersonMockMvc.perform(get("/api/rms-people/{id}", rmsPerson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rmsPerson.getId().intValue()))
            .andExpect(jsonPath("$.personName").value(DEFAULT_PERSON_NAME.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.jobId").value(DEFAULT_JOB_ID.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.otherName").value(DEFAULT_OTHER_NAME.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.booleanValue()))
            .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY.toString()))
            .andExpect(jsonPath("$.pic").value(DEFAULT_PIC.toString()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.depAddress").value(DEFAULT_DEP_ADDRESS.toString()))
            .andExpect(jsonPath("$.depCode").value(DEFAULT_DEP_CODE.toString()))
            .andExpect(jsonPath("$.dutyId").value(DEFAULT_DUTY_ID.toString()))
            .andExpect(jsonPath("$.dimissionFlag").value(DEFAULT_DIMISSION_FLAG.booleanValue()))
            .andExpect(jsonPath("$.headerFlag").value(DEFAULT_HEADER_FLAG.booleanValue()))
            .andExpect(jsonPath("$.satrapFlag").value(DEFAULT_SATRAP_FLAG.booleanValue()))
            .andExpect(jsonPath("$.leaderFlag").value(DEFAULT_LEADER_FLAG.booleanValue()))
            .andExpect(jsonPath("$.postFlag1").value(DEFAULT_POST_FLAG_1.booleanValue()))
            .andExpect(jsonPath("$.postFlag2").value(DEFAULT_POST_FLAG_2.booleanValue()))
            .andExpect(jsonPath("$.postFlag3").value(DEFAULT_POST_FLAG_3.booleanValue()))
            .andExpect(jsonPath("$.officeTel").value(DEFAULT_OFFICE_TEL.toString()))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX.toString()))
            .andExpect(jsonPath("$.mail1").value(DEFAULT_MAIL_1.toString()))
            .andExpect(jsonPath("$.mail2").value(DEFAULT_MAIL_2.toString()))
            .andExpect(jsonPath("$.familyTel").value(DEFAULT_FAMILY_TEL.toString()))
            .andExpect(jsonPath("$.familyAdd").value(DEFAULT_FAMILY_ADD.toString()))
            .andExpect(jsonPath("$.familyCode").value(DEFAULT_FAMILY_CODE.toString()))
            .andExpect(jsonPath("$.personDesc").value(DEFAULT_PERSON_DESC.toString()))
            .andExpect(jsonPath("$.idCode").value(DEFAULT_ID_CODE.toString()))
            .andExpect(jsonPath("$.pop3").value(DEFAULT_POP_3.toString()))
            .andExpect(jsonPath("$.smtp").value(DEFAULT_SMTP.toString()))
            .andExpect(jsonPath("$.mailUsername").value(DEFAULT_MAIL_USERNAME.toString()))
            .andExpect(jsonPath("$.mailPassword").value(DEFAULT_MAIL_PASSWORD.toString()))
            .andExpect(jsonPath("$.mailKeepFlag").value(DEFAULT_MAIL_KEEP_FLAG.booleanValue()))
            .andExpect(jsonPath("$.personSort").value(DEFAULT_PERSON_SORT.toString()))
            .andExpect(jsonPath("$.levelNum").value(DEFAULT_LEVEL_NUM))
            .andExpect(jsonPath("$.personStateId").value(DEFAULT_PERSON_STATE_ID.toString()))
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
    public void getAllRmsPeopleByPersonNameIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personName equals to DEFAULT_PERSON_NAME
        defaultRmsPersonShouldBeFound("personName.equals=" + DEFAULT_PERSON_NAME);

        // Get all the rmsPersonList where personName equals to UPDATED_PERSON_NAME
        defaultRmsPersonShouldNotBeFound("personName.equals=" + UPDATED_PERSON_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPersonNameIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personName in DEFAULT_PERSON_NAME or UPDATED_PERSON_NAME
        defaultRmsPersonShouldBeFound("personName.in=" + DEFAULT_PERSON_NAME + "," + UPDATED_PERSON_NAME);

        // Get all the rmsPersonList where personName equals to UPDATED_PERSON_NAME
        defaultRmsPersonShouldNotBeFound("personName.in=" + UPDATED_PERSON_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPersonNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personName is not null
        defaultRmsPersonShouldBeFound("personName.specified=true");

        // Get all the rmsPersonList where personName is null
        defaultRmsPersonShouldNotBeFound("personName.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where firstName equals to DEFAULT_FIRST_NAME
        defaultRmsPersonShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the rmsPersonList where firstName equals to UPDATED_FIRST_NAME
        defaultRmsPersonShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultRmsPersonShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the rmsPersonList where firstName equals to UPDATED_FIRST_NAME
        defaultRmsPersonShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where firstName is not null
        defaultRmsPersonShouldBeFound("firstName.specified=true");

        // Get all the rmsPersonList where firstName is null
        defaultRmsPersonShouldNotBeFound("firstName.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByJobIdIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where jobId equals to DEFAULT_JOB_ID
        defaultRmsPersonShouldBeFound("jobId.equals=" + DEFAULT_JOB_ID);

        // Get all the rmsPersonList where jobId equals to UPDATED_JOB_ID
        defaultRmsPersonShouldNotBeFound("jobId.equals=" + UPDATED_JOB_ID);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByJobIdIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where jobId in DEFAULT_JOB_ID or UPDATED_JOB_ID
        defaultRmsPersonShouldBeFound("jobId.in=" + DEFAULT_JOB_ID + "," + UPDATED_JOB_ID);

        // Get all the rmsPersonList where jobId equals to UPDATED_JOB_ID
        defaultRmsPersonShouldNotBeFound("jobId.in=" + UPDATED_JOB_ID);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByJobIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where jobId is not null
        defaultRmsPersonShouldBeFound("jobId.specified=true");

        // Get all the rmsPersonList where jobId is null
        defaultRmsPersonShouldNotBeFound("jobId.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where lastName equals to DEFAULT_LAST_NAME
        defaultRmsPersonShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the rmsPersonList where lastName equals to UPDATED_LAST_NAME
        defaultRmsPersonShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultRmsPersonShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the rmsPersonList where lastName equals to UPDATED_LAST_NAME
        defaultRmsPersonShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where lastName is not null
        defaultRmsPersonShouldBeFound("lastName.specified=true");

        // Get all the rmsPersonList where lastName is null
        defaultRmsPersonShouldNotBeFound("lastName.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByOtherNameIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where otherName equals to DEFAULT_OTHER_NAME
        defaultRmsPersonShouldBeFound("otherName.equals=" + DEFAULT_OTHER_NAME);

        // Get all the rmsPersonList where otherName equals to UPDATED_OTHER_NAME
        defaultRmsPersonShouldNotBeFound("otherName.equals=" + UPDATED_OTHER_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByOtherNameIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where otherName in DEFAULT_OTHER_NAME or UPDATED_OTHER_NAME
        defaultRmsPersonShouldBeFound("otherName.in=" + DEFAULT_OTHER_NAME + "," + UPDATED_OTHER_NAME);

        // Get all the rmsPersonList where otherName equals to UPDATED_OTHER_NAME
        defaultRmsPersonShouldNotBeFound("otherName.in=" + UPDATED_OTHER_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByOtherNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where otherName is not null
        defaultRmsPersonShouldBeFound("otherName.specified=true");

        // Get all the rmsPersonList where otherName is null
        defaultRmsPersonShouldNotBeFound("otherName.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySexIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where sex equals to DEFAULT_SEX
        defaultRmsPersonShouldBeFound("sex.equals=" + DEFAULT_SEX);

        // Get all the rmsPersonList where sex equals to UPDATED_SEX
        defaultRmsPersonShouldNotBeFound("sex.equals=" + UPDATED_SEX);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySexIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where sex in DEFAULT_SEX or UPDATED_SEX
        defaultRmsPersonShouldBeFound("sex.in=" + DEFAULT_SEX + "," + UPDATED_SEX);

        // Get all the rmsPersonList where sex equals to UPDATED_SEX
        defaultRmsPersonShouldNotBeFound("sex.in=" + UPDATED_SEX);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySexIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where sex is not null
        defaultRmsPersonShouldBeFound("sex.specified=true");

        // Get all the rmsPersonList where sex is null
        defaultRmsPersonShouldNotBeFound("sex.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBirthdayIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where birthday equals to DEFAULT_BIRTHDAY
        defaultRmsPersonShouldBeFound("birthday.equals=" + DEFAULT_BIRTHDAY);

        // Get all the rmsPersonList where birthday equals to UPDATED_BIRTHDAY
        defaultRmsPersonShouldNotBeFound("birthday.equals=" + UPDATED_BIRTHDAY);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBirthdayIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where birthday in DEFAULT_BIRTHDAY or UPDATED_BIRTHDAY
        defaultRmsPersonShouldBeFound("birthday.in=" + DEFAULT_BIRTHDAY + "," + UPDATED_BIRTHDAY);

        // Get all the rmsPersonList where birthday equals to UPDATED_BIRTHDAY
        defaultRmsPersonShouldNotBeFound("birthday.in=" + UPDATED_BIRTHDAY);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBirthdayIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where birthday is not null
        defaultRmsPersonShouldBeFound("birthday.specified=true");

        // Get all the rmsPersonList where birthday is null
        defaultRmsPersonShouldNotBeFound("birthday.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPicIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where pic equals to DEFAULT_PIC
        defaultRmsPersonShouldBeFound("pic.equals=" + DEFAULT_PIC);

        // Get all the rmsPersonList where pic equals to UPDATED_PIC
        defaultRmsPersonShouldNotBeFound("pic.equals=" + UPDATED_PIC);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPicIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where pic in DEFAULT_PIC or UPDATED_PIC
        defaultRmsPersonShouldBeFound("pic.in=" + DEFAULT_PIC + "," + UPDATED_PIC);

        // Get all the rmsPersonList where pic equals to UPDATED_PIC
        defaultRmsPersonShouldNotBeFound("pic.in=" + UPDATED_PIC);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPicIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where pic is not null
        defaultRmsPersonShouldBeFound("pic.specified=true");

        // Get all the rmsPersonList where pic is null
        defaultRmsPersonShouldNotBeFound("pic.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByIconIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where icon equals to DEFAULT_ICON
        defaultRmsPersonShouldBeFound("icon.equals=" + DEFAULT_ICON);

        // Get all the rmsPersonList where icon equals to UPDATED_ICON
        defaultRmsPersonShouldNotBeFound("icon.equals=" + UPDATED_ICON);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByIconIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where icon in DEFAULT_ICON or UPDATED_ICON
        defaultRmsPersonShouldBeFound("icon.in=" + DEFAULT_ICON + "," + UPDATED_ICON);

        // Get all the rmsPersonList where icon equals to UPDATED_ICON
        defaultRmsPersonShouldNotBeFound("icon.in=" + UPDATED_ICON);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByIconIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where icon is not null
        defaultRmsPersonShouldBeFound("icon.specified=true");

        // Get all the rmsPersonList where icon is null
        defaultRmsPersonShouldNotBeFound("icon.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMobileIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mobile equals to DEFAULT_MOBILE
        defaultRmsPersonShouldBeFound("mobile.equals=" + DEFAULT_MOBILE);

        // Get all the rmsPersonList where mobile equals to UPDATED_MOBILE
        defaultRmsPersonShouldNotBeFound("mobile.equals=" + UPDATED_MOBILE);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMobileIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mobile in DEFAULT_MOBILE or UPDATED_MOBILE
        defaultRmsPersonShouldBeFound("mobile.in=" + DEFAULT_MOBILE + "," + UPDATED_MOBILE);

        // Get all the rmsPersonList where mobile equals to UPDATED_MOBILE
        defaultRmsPersonShouldNotBeFound("mobile.in=" + UPDATED_MOBILE);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMobileIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mobile is not null
        defaultRmsPersonShouldBeFound("mobile.specified=true");

        // Get all the rmsPersonList where mobile is null
        defaultRmsPersonShouldNotBeFound("mobile.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDepAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where depAddress equals to DEFAULT_DEP_ADDRESS
        defaultRmsPersonShouldBeFound("depAddress.equals=" + DEFAULT_DEP_ADDRESS);

        // Get all the rmsPersonList where depAddress equals to UPDATED_DEP_ADDRESS
        defaultRmsPersonShouldNotBeFound("depAddress.equals=" + UPDATED_DEP_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDepAddressIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where depAddress in DEFAULT_DEP_ADDRESS or UPDATED_DEP_ADDRESS
        defaultRmsPersonShouldBeFound("depAddress.in=" + DEFAULT_DEP_ADDRESS + "," + UPDATED_DEP_ADDRESS);

        // Get all the rmsPersonList where depAddress equals to UPDATED_DEP_ADDRESS
        defaultRmsPersonShouldNotBeFound("depAddress.in=" + UPDATED_DEP_ADDRESS);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDepAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where depAddress is not null
        defaultRmsPersonShouldBeFound("depAddress.specified=true");

        // Get all the rmsPersonList where depAddress is null
        defaultRmsPersonShouldNotBeFound("depAddress.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDepCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where depCode equals to DEFAULT_DEP_CODE
        defaultRmsPersonShouldBeFound("depCode.equals=" + DEFAULT_DEP_CODE);

        // Get all the rmsPersonList where depCode equals to UPDATED_DEP_CODE
        defaultRmsPersonShouldNotBeFound("depCode.equals=" + UPDATED_DEP_CODE);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDepCodeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where depCode in DEFAULT_DEP_CODE or UPDATED_DEP_CODE
        defaultRmsPersonShouldBeFound("depCode.in=" + DEFAULT_DEP_CODE + "," + UPDATED_DEP_CODE);

        // Get all the rmsPersonList where depCode equals to UPDATED_DEP_CODE
        defaultRmsPersonShouldNotBeFound("depCode.in=" + UPDATED_DEP_CODE);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDepCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where depCode is not null
        defaultRmsPersonShouldBeFound("depCode.specified=true");

        // Get all the rmsPersonList where depCode is null
        defaultRmsPersonShouldNotBeFound("depCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDutyIdIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where dutyId equals to DEFAULT_DUTY_ID
        defaultRmsPersonShouldBeFound("dutyId.equals=" + DEFAULT_DUTY_ID);

        // Get all the rmsPersonList where dutyId equals to UPDATED_DUTY_ID
        defaultRmsPersonShouldNotBeFound("dutyId.equals=" + UPDATED_DUTY_ID);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDutyIdIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where dutyId in DEFAULT_DUTY_ID or UPDATED_DUTY_ID
        defaultRmsPersonShouldBeFound("dutyId.in=" + DEFAULT_DUTY_ID + "," + UPDATED_DUTY_ID);

        // Get all the rmsPersonList where dutyId equals to UPDATED_DUTY_ID
        defaultRmsPersonShouldNotBeFound("dutyId.in=" + UPDATED_DUTY_ID);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDutyIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where dutyId is not null
        defaultRmsPersonShouldBeFound("dutyId.specified=true");

        // Get all the rmsPersonList where dutyId is null
        defaultRmsPersonShouldNotBeFound("dutyId.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDimissionFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where dimissionFlag equals to DEFAULT_DIMISSION_FLAG
        defaultRmsPersonShouldBeFound("dimissionFlag.equals=" + DEFAULT_DIMISSION_FLAG);

        // Get all the rmsPersonList where dimissionFlag equals to UPDATED_DIMISSION_FLAG
        defaultRmsPersonShouldNotBeFound("dimissionFlag.equals=" + UPDATED_DIMISSION_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDimissionFlagIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where dimissionFlag in DEFAULT_DIMISSION_FLAG or UPDATED_DIMISSION_FLAG
        defaultRmsPersonShouldBeFound("dimissionFlag.in=" + DEFAULT_DIMISSION_FLAG + "," + UPDATED_DIMISSION_FLAG);

        // Get all the rmsPersonList where dimissionFlag equals to UPDATED_DIMISSION_FLAG
        defaultRmsPersonShouldNotBeFound("dimissionFlag.in=" + UPDATED_DIMISSION_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByDimissionFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where dimissionFlag is not null
        defaultRmsPersonShouldBeFound("dimissionFlag.specified=true");

        // Get all the rmsPersonList where dimissionFlag is null
        defaultRmsPersonShouldNotBeFound("dimissionFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByHeaderFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where headerFlag equals to DEFAULT_HEADER_FLAG
        defaultRmsPersonShouldBeFound("headerFlag.equals=" + DEFAULT_HEADER_FLAG);

        // Get all the rmsPersonList where headerFlag equals to UPDATED_HEADER_FLAG
        defaultRmsPersonShouldNotBeFound("headerFlag.equals=" + UPDATED_HEADER_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByHeaderFlagIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where headerFlag in DEFAULT_HEADER_FLAG or UPDATED_HEADER_FLAG
        defaultRmsPersonShouldBeFound("headerFlag.in=" + DEFAULT_HEADER_FLAG + "," + UPDATED_HEADER_FLAG);

        // Get all the rmsPersonList where headerFlag equals to UPDATED_HEADER_FLAG
        defaultRmsPersonShouldNotBeFound("headerFlag.in=" + UPDATED_HEADER_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByHeaderFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where headerFlag is not null
        defaultRmsPersonShouldBeFound("headerFlag.specified=true");

        // Get all the rmsPersonList where headerFlag is null
        defaultRmsPersonShouldNotBeFound("headerFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySatrapFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where satrapFlag equals to DEFAULT_SATRAP_FLAG
        defaultRmsPersonShouldBeFound("satrapFlag.equals=" + DEFAULT_SATRAP_FLAG);

        // Get all the rmsPersonList where satrapFlag equals to UPDATED_SATRAP_FLAG
        defaultRmsPersonShouldNotBeFound("satrapFlag.equals=" + UPDATED_SATRAP_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySatrapFlagIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where satrapFlag in DEFAULT_SATRAP_FLAG or UPDATED_SATRAP_FLAG
        defaultRmsPersonShouldBeFound("satrapFlag.in=" + DEFAULT_SATRAP_FLAG + "," + UPDATED_SATRAP_FLAG);

        // Get all the rmsPersonList where satrapFlag equals to UPDATED_SATRAP_FLAG
        defaultRmsPersonShouldNotBeFound("satrapFlag.in=" + UPDATED_SATRAP_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySatrapFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where satrapFlag is not null
        defaultRmsPersonShouldBeFound("satrapFlag.specified=true");

        // Get all the rmsPersonList where satrapFlag is null
        defaultRmsPersonShouldNotBeFound("satrapFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByLeaderFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where leaderFlag equals to DEFAULT_LEADER_FLAG
        defaultRmsPersonShouldBeFound("leaderFlag.equals=" + DEFAULT_LEADER_FLAG);

        // Get all the rmsPersonList where leaderFlag equals to UPDATED_LEADER_FLAG
        defaultRmsPersonShouldNotBeFound("leaderFlag.equals=" + UPDATED_LEADER_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByLeaderFlagIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where leaderFlag in DEFAULT_LEADER_FLAG or UPDATED_LEADER_FLAG
        defaultRmsPersonShouldBeFound("leaderFlag.in=" + DEFAULT_LEADER_FLAG + "," + UPDATED_LEADER_FLAG);

        // Get all the rmsPersonList where leaderFlag equals to UPDATED_LEADER_FLAG
        defaultRmsPersonShouldNotBeFound("leaderFlag.in=" + UPDATED_LEADER_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByLeaderFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where leaderFlag is not null
        defaultRmsPersonShouldBeFound("leaderFlag.specified=true");

        // Get all the rmsPersonList where leaderFlag is null
        defaultRmsPersonShouldNotBeFound("leaderFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPostFlag1IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where postFlag1 equals to DEFAULT_POST_FLAG_1
        defaultRmsPersonShouldBeFound("postFlag1.equals=" + DEFAULT_POST_FLAG_1);

        // Get all the rmsPersonList where postFlag1 equals to UPDATED_POST_FLAG_1
        defaultRmsPersonShouldNotBeFound("postFlag1.equals=" + UPDATED_POST_FLAG_1);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPostFlag1IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where postFlag1 in DEFAULT_POST_FLAG_1 or UPDATED_POST_FLAG_1
        defaultRmsPersonShouldBeFound("postFlag1.in=" + DEFAULT_POST_FLAG_1 + "," + UPDATED_POST_FLAG_1);

        // Get all the rmsPersonList where postFlag1 equals to UPDATED_POST_FLAG_1
        defaultRmsPersonShouldNotBeFound("postFlag1.in=" + UPDATED_POST_FLAG_1);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPostFlag1IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where postFlag1 is not null
        defaultRmsPersonShouldBeFound("postFlag1.specified=true");

        // Get all the rmsPersonList where postFlag1 is null
        defaultRmsPersonShouldNotBeFound("postFlag1.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPostFlag2IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where postFlag2 equals to DEFAULT_POST_FLAG_2
        defaultRmsPersonShouldBeFound("postFlag2.equals=" + DEFAULT_POST_FLAG_2);

        // Get all the rmsPersonList where postFlag2 equals to UPDATED_POST_FLAG_2
        defaultRmsPersonShouldNotBeFound("postFlag2.equals=" + UPDATED_POST_FLAG_2);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPostFlag2IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where postFlag2 in DEFAULT_POST_FLAG_2 or UPDATED_POST_FLAG_2
        defaultRmsPersonShouldBeFound("postFlag2.in=" + DEFAULT_POST_FLAG_2 + "," + UPDATED_POST_FLAG_2);

        // Get all the rmsPersonList where postFlag2 equals to UPDATED_POST_FLAG_2
        defaultRmsPersonShouldNotBeFound("postFlag2.in=" + UPDATED_POST_FLAG_2);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPostFlag2IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where postFlag2 is not null
        defaultRmsPersonShouldBeFound("postFlag2.specified=true");

        // Get all the rmsPersonList where postFlag2 is null
        defaultRmsPersonShouldNotBeFound("postFlag2.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPostFlag3IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where postFlag3 equals to DEFAULT_POST_FLAG_3
        defaultRmsPersonShouldBeFound("postFlag3.equals=" + DEFAULT_POST_FLAG_3);

        // Get all the rmsPersonList where postFlag3 equals to UPDATED_POST_FLAG_3
        defaultRmsPersonShouldNotBeFound("postFlag3.equals=" + UPDATED_POST_FLAG_3);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPostFlag3IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where postFlag3 in DEFAULT_POST_FLAG_3 or UPDATED_POST_FLAG_3
        defaultRmsPersonShouldBeFound("postFlag3.in=" + DEFAULT_POST_FLAG_3 + "," + UPDATED_POST_FLAG_3);

        // Get all the rmsPersonList where postFlag3 equals to UPDATED_POST_FLAG_3
        defaultRmsPersonShouldNotBeFound("postFlag3.in=" + UPDATED_POST_FLAG_3);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPostFlag3IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where postFlag3 is not null
        defaultRmsPersonShouldBeFound("postFlag3.specified=true");

        // Get all the rmsPersonList where postFlag3 is null
        defaultRmsPersonShouldNotBeFound("postFlag3.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByOfficeTelIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where officeTel equals to DEFAULT_OFFICE_TEL
        defaultRmsPersonShouldBeFound("officeTel.equals=" + DEFAULT_OFFICE_TEL);

        // Get all the rmsPersonList where officeTel equals to UPDATED_OFFICE_TEL
        defaultRmsPersonShouldNotBeFound("officeTel.equals=" + UPDATED_OFFICE_TEL);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByOfficeTelIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where officeTel in DEFAULT_OFFICE_TEL or UPDATED_OFFICE_TEL
        defaultRmsPersonShouldBeFound("officeTel.in=" + DEFAULT_OFFICE_TEL + "," + UPDATED_OFFICE_TEL);

        // Get all the rmsPersonList where officeTel equals to UPDATED_OFFICE_TEL
        defaultRmsPersonShouldNotBeFound("officeTel.in=" + UPDATED_OFFICE_TEL);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByOfficeTelIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where officeTel is not null
        defaultRmsPersonShouldBeFound("officeTel.specified=true");

        // Get all the rmsPersonList where officeTel is null
        defaultRmsPersonShouldNotBeFound("officeTel.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFaxIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where fax equals to DEFAULT_FAX
        defaultRmsPersonShouldBeFound("fax.equals=" + DEFAULT_FAX);

        // Get all the rmsPersonList where fax equals to UPDATED_FAX
        defaultRmsPersonShouldNotBeFound("fax.equals=" + UPDATED_FAX);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFaxIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where fax in DEFAULT_FAX or UPDATED_FAX
        defaultRmsPersonShouldBeFound("fax.in=" + DEFAULT_FAX + "," + UPDATED_FAX);

        // Get all the rmsPersonList where fax equals to UPDATED_FAX
        defaultRmsPersonShouldNotBeFound("fax.in=" + UPDATED_FAX);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where fax is not null
        defaultRmsPersonShouldBeFound("fax.specified=true");

        // Get all the rmsPersonList where fax is null
        defaultRmsPersonShouldNotBeFound("fax.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMail1IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mail1 equals to DEFAULT_MAIL_1
        defaultRmsPersonShouldBeFound("mail1.equals=" + DEFAULT_MAIL_1);

        // Get all the rmsPersonList where mail1 equals to UPDATED_MAIL_1
        defaultRmsPersonShouldNotBeFound("mail1.equals=" + UPDATED_MAIL_1);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMail1IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mail1 in DEFAULT_MAIL_1 or UPDATED_MAIL_1
        defaultRmsPersonShouldBeFound("mail1.in=" + DEFAULT_MAIL_1 + "," + UPDATED_MAIL_1);

        // Get all the rmsPersonList where mail1 equals to UPDATED_MAIL_1
        defaultRmsPersonShouldNotBeFound("mail1.in=" + UPDATED_MAIL_1);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMail1IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mail1 is not null
        defaultRmsPersonShouldBeFound("mail1.specified=true");

        // Get all the rmsPersonList where mail1 is null
        defaultRmsPersonShouldNotBeFound("mail1.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMail2IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mail2 equals to DEFAULT_MAIL_2
        defaultRmsPersonShouldBeFound("mail2.equals=" + DEFAULT_MAIL_2);

        // Get all the rmsPersonList where mail2 equals to UPDATED_MAIL_2
        defaultRmsPersonShouldNotBeFound("mail2.equals=" + UPDATED_MAIL_2);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMail2IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mail2 in DEFAULT_MAIL_2 or UPDATED_MAIL_2
        defaultRmsPersonShouldBeFound("mail2.in=" + DEFAULT_MAIL_2 + "," + UPDATED_MAIL_2);

        // Get all the rmsPersonList where mail2 equals to UPDATED_MAIL_2
        defaultRmsPersonShouldNotBeFound("mail2.in=" + UPDATED_MAIL_2);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMail2IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mail2 is not null
        defaultRmsPersonShouldBeFound("mail2.specified=true");

        // Get all the rmsPersonList where mail2 is null
        defaultRmsPersonShouldNotBeFound("mail2.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFamilyTelIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where familyTel equals to DEFAULT_FAMILY_TEL
        defaultRmsPersonShouldBeFound("familyTel.equals=" + DEFAULT_FAMILY_TEL);

        // Get all the rmsPersonList where familyTel equals to UPDATED_FAMILY_TEL
        defaultRmsPersonShouldNotBeFound("familyTel.equals=" + UPDATED_FAMILY_TEL);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFamilyTelIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where familyTel in DEFAULT_FAMILY_TEL or UPDATED_FAMILY_TEL
        defaultRmsPersonShouldBeFound("familyTel.in=" + DEFAULT_FAMILY_TEL + "," + UPDATED_FAMILY_TEL);

        // Get all the rmsPersonList where familyTel equals to UPDATED_FAMILY_TEL
        defaultRmsPersonShouldNotBeFound("familyTel.in=" + UPDATED_FAMILY_TEL);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFamilyTelIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where familyTel is not null
        defaultRmsPersonShouldBeFound("familyTel.specified=true");

        // Get all the rmsPersonList where familyTel is null
        defaultRmsPersonShouldNotBeFound("familyTel.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFamilyAddIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where familyAdd equals to DEFAULT_FAMILY_ADD
        defaultRmsPersonShouldBeFound("familyAdd.equals=" + DEFAULT_FAMILY_ADD);

        // Get all the rmsPersonList where familyAdd equals to UPDATED_FAMILY_ADD
        defaultRmsPersonShouldNotBeFound("familyAdd.equals=" + UPDATED_FAMILY_ADD);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFamilyAddIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where familyAdd in DEFAULT_FAMILY_ADD or UPDATED_FAMILY_ADD
        defaultRmsPersonShouldBeFound("familyAdd.in=" + DEFAULT_FAMILY_ADD + "," + UPDATED_FAMILY_ADD);

        // Get all the rmsPersonList where familyAdd equals to UPDATED_FAMILY_ADD
        defaultRmsPersonShouldNotBeFound("familyAdd.in=" + UPDATED_FAMILY_ADD);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFamilyAddIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where familyAdd is not null
        defaultRmsPersonShouldBeFound("familyAdd.specified=true");

        // Get all the rmsPersonList where familyAdd is null
        defaultRmsPersonShouldNotBeFound("familyAdd.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFamilyCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where familyCode equals to DEFAULT_FAMILY_CODE
        defaultRmsPersonShouldBeFound("familyCode.equals=" + DEFAULT_FAMILY_CODE);

        // Get all the rmsPersonList where familyCode equals to UPDATED_FAMILY_CODE
        defaultRmsPersonShouldNotBeFound("familyCode.equals=" + UPDATED_FAMILY_CODE);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFamilyCodeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where familyCode in DEFAULT_FAMILY_CODE or UPDATED_FAMILY_CODE
        defaultRmsPersonShouldBeFound("familyCode.in=" + DEFAULT_FAMILY_CODE + "," + UPDATED_FAMILY_CODE);

        // Get all the rmsPersonList where familyCode equals to UPDATED_FAMILY_CODE
        defaultRmsPersonShouldNotBeFound("familyCode.in=" + UPDATED_FAMILY_CODE);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByFamilyCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where familyCode is not null
        defaultRmsPersonShouldBeFound("familyCode.specified=true");

        // Get all the rmsPersonList where familyCode is null
        defaultRmsPersonShouldNotBeFound("familyCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPersonDescIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personDesc equals to DEFAULT_PERSON_DESC
        defaultRmsPersonShouldBeFound("personDesc.equals=" + DEFAULT_PERSON_DESC);

        // Get all the rmsPersonList where personDesc equals to UPDATED_PERSON_DESC
        defaultRmsPersonShouldNotBeFound("personDesc.equals=" + UPDATED_PERSON_DESC);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPersonDescIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personDesc in DEFAULT_PERSON_DESC or UPDATED_PERSON_DESC
        defaultRmsPersonShouldBeFound("personDesc.in=" + DEFAULT_PERSON_DESC + "," + UPDATED_PERSON_DESC);

        // Get all the rmsPersonList where personDesc equals to UPDATED_PERSON_DESC
        defaultRmsPersonShouldNotBeFound("personDesc.in=" + UPDATED_PERSON_DESC);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPersonDescIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personDesc is not null
        defaultRmsPersonShouldBeFound("personDesc.specified=true");

        // Get all the rmsPersonList where personDesc is null
        defaultRmsPersonShouldNotBeFound("personDesc.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByIdCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where idCode equals to DEFAULT_ID_CODE
        defaultRmsPersonShouldBeFound("idCode.equals=" + DEFAULT_ID_CODE);

        // Get all the rmsPersonList where idCode equals to UPDATED_ID_CODE
        defaultRmsPersonShouldNotBeFound("idCode.equals=" + UPDATED_ID_CODE);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByIdCodeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where idCode in DEFAULT_ID_CODE or UPDATED_ID_CODE
        defaultRmsPersonShouldBeFound("idCode.in=" + DEFAULT_ID_CODE + "," + UPDATED_ID_CODE);

        // Get all the rmsPersonList where idCode equals to UPDATED_ID_CODE
        defaultRmsPersonShouldNotBeFound("idCode.in=" + UPDATED_ID_CODE);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByIdCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where idCode is not null
        defaultRmsPersonShouldBeFound("idCode.specified=true");

        // Get all the rmsPersonList where idCode is null
        defaultRmsPersonShouldNotBeFound("idCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPop3IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where pop3 equals to DEFAULT_POP_3
        defaultRmsPersonShouldBeFound("pop3.equals=" + DEFAULT_POP_3);

        // Get all the rmsPersonList where pop3 equals to UPDATED_POP_3
        defaultRmsPersonShouldNotBeFound("pop3.equals=" + UPDATED_POP_3);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPop3IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where pop3 in DEFAULT_POP_3 or UPDATED_POP_3
        defaultRmsPersonShouldBeFound("pop3.in=" + DEFAULT_POP_3 + "," + UPDATED_POP_3);

        // Get all the rmsPersonList where pop3 equals to UPDATED_POP_3
        defaultRmsPersonShouldNotBeFound("pop3.in=" + UPDATED_POP_3);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPop3IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where pop3 is not null
        defaultRmsPersonShouldBeFound("pop3.specified=true");

        // Get all the rmsPersonList where pop3 is null
        defaultRmsPersonShouldNotBeFound("pop3.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySmtpIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where smtp equals to DEFAULT_SMTP
        defaultRmsPersonShouldBeFound("smtp.equals=" + DEFAULT_SMTP);

        // Get all the rmsPersonList where smtp equals to UPDATED_SMTP
        defaultRmsPersonShouldNotBeFound("smtp.equals=" + UPDATED_SMTP);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySmtpIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where smtp in DEFAULT_SMTP or UPDATED_SMTP
        defaultRmsPersonShouldBeFound("smtp.in=" + DEFAULT_SMTP + "," + UPDATED_SMTP);

        // Get all the rmsPersonList where smtp equals to UPDATED_SMTP
        defaultRmsPersonShouldNotBeFound("smtp.in=" + UPDATED_SMTP);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySmtpIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where smtp is not null
        defaultRmsPersonShouldBeFound("smtp.specified=true");

        // Get all the rmsPersonList where smtp is null
        defaultRmsPersonShouldNotBeFound("smtp.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMailUsernameIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mailUsername equals to DEFAULT_MAIL_USERNAME
        defaultRmsPersonShouldBeFound("mailUsername.equals=" + DEFAULT_MAIL_USERNAME);

        // Get all the rmsPersonList where mailUsername equals to UPDATED_MAIL_USERNAME
        defaultRmsPersonShouldNotBeFound("mailUsername.equals=" + UPDATED_MAIL_USERNAME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMailUsernameIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mailUsername in DEFAULT_MAIL_USERNAME or UPDATED_MAIL_USERNAME
        defaultRmsPersonShouldBeFound("mailUsername.in=" + DEFAULT_MAIL_USERNAME + "," + UPDATED_MAIL_USERNAME);

        // Get all the rmsPersonList where mailUsername equals to UPDATED_MAIL_USERNAME
        defaultRmsPersonShouldNotBeFound("mailUsername.in=" + UPDATED_MAIL_USERNAME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMailUsernameIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mailUsername is not null
        defaultRmsPersonShouldBeFound("mailUsername.specified=true");

        // Get all the rmsPersonList where mailUsername is null
        defaultRmsPersonShouldNotBeFound("mailUsername.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMailPasswordIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mailPassword equals to DEFAULT_MAIL_PASSWORD
        defaultRmsPersonShouldBeFound("mailPassword.equals=" + DEFAULT_MAIL_PASSWORD);

        // Get all the rmsPersonList where mailPassword equals to UPDATED_MAIL_PASSWORD
        defaultRmsPersonShouldNotBeFound("mailPassword.equals=" + UPDATED_MAIL_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMailPasswordIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mailPassword in DEFAULT_MAIL_PASSWORD or UPDATED_MAIL_PASSWORD
        defaultRmsPersonShouldBeFound("mailPassword.in=" + DEFAULT_MAIL_PASSWORD + "," + UPDATED_MAIL_PASSWORD);

        // Get all the rmsPersonList where mailPassword equals to UPDATED_MAIL_PASSWORD
        defaultRmsPersonShouldNotBeFound("mailPassword.in=" + UPDATED_MAIL_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMailPasswordIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mailPassword is not null
        defaultRmsPersonShouldBeFound("mailPassword.specified=true");

        // Get all the rmsPersonList where mailPassword is null
        defaultRmsPersonShouldNotBeFound("mailPassword.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMailKeepFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mailKeepFlag equals to DEFAULT_MAIL_KEEP_FLAG
        defaultRmsPersonShouldBeFound("mailKeepFlag.equals=" + DEFAULT_MAIL_KEEP_FLAG);

        // Get all the rmsPersonList where mailKeepFlag equals to UPDATED_MAIL_KEEP_FLAG
        defaultRmsPersonShouldNotBeFound("mailKeepFlag.equals=" + UPDATED_MAIL_KEEP_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMailKeepFlagIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mailKeepFlag in DEFAULT_MAIL_KEEP_FLAG or UPDATED_MAIL_KEEP_FLAG
        defaultRmsPersonShouldBeFound("mailKeepFlag.in=" + DEFAULT_MAIL_KEEP_FLAG + "," + UPDATED_MAIL_KEEP_FLAG);

        // Get all the rmsPersonList where mailKeepFlag equals to UPDATED_MAIL_KEEP_FLAG
        defaultRmsPersonShouldNotBeFound("mailKeepFlag.in=" + UPDATED_MAIL_KEEP_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByMailKeepFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where mailKeepFlag is not null
        defaultRmsPersonShouldBeFound("mailKeepFlag.specified=true");

        // Get all the rmsPersonList where mailKeepFlag is null
        defaultRmsPersonShouldNotBeFound("mailKeepFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPersonSortIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personSort equals to DEFAULT_PERSON_SORT
        defaultRmsPersonShouldBeFound("personSort.equals=" + DEFAULT_PERSON_SORT);

        // Get all the rmsPersonList where personSort equals to UPDATED_PERSON_SORT
        defaultRmsPersonShouldNotBeFound("personSort.equals=" + UPDATED_PERSON_SORT);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPersonSortIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personSort in DEFAULT_PERSON_SORT or UPDATED_PERSON_SORT
        defaultRmsPersonShouldBeFound("personSort.in=" + DEFAULT_PERSON_SORT + "," + UPDATED_PERSON_SORT);

        // Get all the rmsPersonList where personSort equals to UPDATED_PERSON_SORT
        defaultRmsPersonShouldNotBeFound("personSort.in=" + UPDATED_PERSON_SORT);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPersonSortIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personSort is not null
        defaultRmsPersonShouldBeFound("personSort.specified=true");

        // Get all the rmsPersonList where personSort is null
        defaultRmsPersonShouldNotBeFound("personSort.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByLevelNumIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where levelNum equals to DEFAULT_LEVEL_NUM
        defaultRmsPersonShouldBeFound("levelNum.equals=" + DEFAULT_LEVEL_NUM);

        // Get all the rmsPersonList where levelNum equals to UPDATED_LEVEL_NUM
        defaultRmsPersonShouldNotBeFound("levelNum.equals=" + UPDATED_LEVEL_NUM);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByLevelNumIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where levelNum in DEFAULT_LEVEL_NUM or UPDATED_LEVEL_NUM
        defaultRmsPersonShouldBeFound("levelNum.in=" + DEFAULT_LEVEL_NUM + "," + UPDATED_LEVEL_NUM);

        // Get all the rmsPersonList where levelNum equals to UPDATED_LEVEL_NUM
        defaultRmsPersonShouldNotBeFound("levelNum.in=" + UPDATED_LEVEL_NUM);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByLevelNumIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where levelNum is not null
        defaultRmsPersonShouldBeFound("levelNum.specified=true");

        // Get all the rmsPersonList where levelNum is null
        defaultRmsPersonShouldNotBeFound("levelNum.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByLevelNumIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where levelNum greater than or equals to DEFAULT_LEVEL_NUM
        defaultRmsPersonShouldBeFound("levelNum.greaterOrEqualThan=" + DEFAULT_LEVEL_NUM);

        // Get all the rmsPersonList where levelNum greater than or equals to UPDATED_LEVEL_NUM
        defaultRmsPersonShouldNotBeFound("levelNum.greaterOrEqualThan=" + UPDATED_LEVEL_NUM);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByLevelNumIsLessThanSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where levelNum less than or equals to DEFAULT_LEVEL_NUM
        defaultRmsPersonShouldNotBeFound("levelNum.lessThan=" + DEFAULT_LEVEL_NUM);

        // Get all the rmsPersonList where levelNum less than or equals to UPDATED_LEVEL_NUM
        defaultRmsPersonShouldBeFound("levelNum.lessThan=" + UPDATED_LEVEL_NUM);
    }


    @Test
    @Transactional
    public void getAllRmsPeopleByPersonStateIdIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personStateId equals to DEFAULT_PERSON_STATE_ID
        defaultRmsPersonShouldBeFound("personStateId.equals=" + DEFAULT_PERSON_STATE_ID);

        // Get all the rmsPersonList where personStateId equals to UPDATED_PERSON_STATE_ID
        defaultRmsPersonShouldNotBeFound("personStateId.equals=" + UPDATED_PERSON_STATE_ID);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPersonStateIdIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personStateId in DEFAULT_PERSON_STATE_ID or UPDATED_PERSON_STATE_ID
        defaultRmsPersonShouldBeFound("personStateId.in=" + DEFAULT_PERSON_STATE_ID + "," + UPDATED_PERSON_STATE_ID);

        // Get all the rmsPersonList where personStateId equals to UPDATED_PERSON_STATE_ID
        defaultRmsPersonShouldNotBeFound("personStateId.in=" + UPDATED_PERSON_STATE_ID);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByPersonStateIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where personStateId is not null
        defaultRmsPersonShouldBeFound("personStateId.specified=true");

        // Get all the rmsPersonList where personStateId is null
        defaultRmsPersonShouldNotBeFound("personStateId.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy01IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by01 equals to DEFAULT_BY_01
        defaultRmsPersonShouldBeFound("by01.equals=" + DEFAULT_BY_01);

        // Get all the rmsPersonList where by01 equals to UPDATED_BY_01
        defaultRmsPersonShouldNotBeFound("by01.equals=" + UPDATED_BY_01);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy01IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by01 in DEFAULT_BY_01 or UPDATED_BY_01
        defaultRmsPersonShouldBeFound("by01.in=" + DEFAULT_BY_01 + "," + UPDATED_BY_01);

        // Get all the rmsPersonList where by01 equals to UPDATED_BY_01
        defaultRmsPersonShouldNotBeFound("by01.in=" + UPDATED_BY_01);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy01IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by01 is not null
        defaultRmsPersonShouldBeFound("by01.specified=true");

        // Get all the rmsPersonList where by01 is null
        defaultRmsPersonShouldNotBeFound("by01.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy02IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by02 equals to DEFAULT_BY_02
        defaultRmsPersonShouldBeFound("by02.equals=" + DEFAULT_BY_02);

        // Get all the rmsPersonList where by02 equals to UPDATED_BY_02
        defaultRmsPersonShouldNotBeFound("by02.equals=" + UPDATED_BY_02);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy02IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by02 in DEFAULT_BY_02 or UPDATED_BY_02
        defaultRmsPersonShouldBeFound("by02.in=" + DEFAULT_BY_02 + "," + UPDATED_BY_02);

        // Get all the rmsPersonList where by02 equals to UPDATED_BY_02
        defaultRmsPersonShouldNotBeFound("by02.in=" + UPDATED_BY_02);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy02IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by02 is not null
        defaultRmsPersonShouldBeFound("by02.specified=true");

        // Get all the rmsPersonList where by02 is null
        defaultRmsPersonShouldNotBeFound("by02.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy03IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by03 equals to DEFAULT_BY_03
        defaultRmsPersonShouldBeFound("by03.equals=" + DEFAULT_BY_03);

        // Get all the rmsPersonList where by03 equals to UPDATED_BY_03
        defaultRmsPersonShouldNotBeFound("by03.equals=" + UPDATED_BY_03);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy03IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by03 in DEFAULT_BY_03 or UPDATED_BY_03
        defaultRmsPersonShouldBeFound("by03.in=" + DEFAULT_BY_03 + "," + UPDATED_BY_03);

        // Get all the rmsPersonList where by03 equals to UPDATED_BY_03
        defaultRmsPersonShouldNotBeFound("by03.in=" + UPDATED_BY_03);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy03IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by03 is not null
        defaultRmsPersonShouldBeFound("by03.specified=true");

        // Get all the rmsPersonList where by03 is null
        defaultRmsPersonShouldNotBeFound("by03.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy04IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by04 equals to DEFAULT_BY_04
        defaultRmsPersonShouldBeFound("by04.equals=" + DEFAULT_BY_04);

        // Get all the rmsPersonList where by04 equals to UPDATED_BY_04
        defaultRmsPersonShouldNotBeFound("by04.equals=" + UPDATED_BY_04);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy04IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by04 in DEFAULT_BY_04 or UPDATED_BY_04
        defaultRmsPersonShouldBeFound("by04.in=" + DEFAULT_BY_04 + "," + UPDATED_BY_04);

        // Get all the rmsPersonList where by04 equals to UPDATED_BY_04
        defaultRmsPersonShouldNotBeFound("by04.in=" + UPDATED_BY_04);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy04IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by04 is not null
        defaultRmsPersonShouldBeFound("by04.specified=true");

        // Get all the rmsPersonList where by04 is null
        defaultRmsPersonShouldNotBeFound("by04.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy05IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by05 equals to DEFAULT_BY_05
        defaultRmsPersonShouldBeFound("by05.equals=" + DEFAULT_BY_05);

        // Get all the rmsPersonList where by05 equals to UPDATED_BY_05
        defaultRmsPersonShouldNotBeFound("by05.equals=" + UPDATED_BY_05);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy05IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by05 in DEFAULT_BY_05 or UPDATED_BY_05
        defaultRmsPersonShouldBeFound("by05.in=" + DEFAULT_BY_05 + "," + UPDATED_BY_05);

        // Get all the rmsPersonList where by05 equals to UPDATED_BY_05
        defaultRmsPersonShouldNotBeFound("by05.in=" + UPDATED_BY_05);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy05IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by05 is not null
        defaultRmsPersonShouldBeFound("by05.specified=true");

        // Get all the rmsPersonList where by05 is null
        defaultRmsPersonShouldNotBeFound("by05.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy06IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by06 equals to DEFAULT_BY_06
        defaultRmsPersonShouldBeFound("by06.equals=" + DEFAULT_BY_06);

        // Get all the rmsPersonList where by06 equals to UPDATED_BY_06
        defaultRmsPersonShouldNotBeFound("by06.equals=" + UPDATED_BY_06);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy06IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by06 in DEFAULT_BY_06 or UPDATED_BY_06
        defaultRmsPersonShouldBeFound("by06.in=" + DEFAULT_BY_06 + "," + UPDATED_BY_06);

        // Get all the rmsPersonList where by06 equals to UPDATED_BY_06
        defaultRmsPersonShouldNotBeFound("by06.in=" + UPDATED_BY_06);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy06IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by06 is not null
        defaultRmsPersonShouldBeFound("by06.specified=true");

        // Get all the rmsPersonList where by06 is null
        defaultRmsPersonShouldNotBeFound("by06.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy07IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by07 equals to DEFAULT_BY_07
        defaultRmsPersonShouldBeFound("by07.equals=" + DEFAULT_BY_07);

        // Get all the rmsPersonList where by07 equals to UPDATED_BY_07
        defaultRmsPersonShouldNotBeFound("by07.equals=" + UPDATED_BY_07);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy07IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by07 in DEFAULT_BY_07 or UPDATED_BY_07
        defaultRmsPersonShouldBeFound("by07.in=" + DEFAULT_BY_07 + "," + UPDATED_BY_07);

        // Get all the rmsPersonList where by07 equals to UPDATED_BY_07
        defaultRmsPersonShouldNotBeFound("by07.in=" + UPDATED_BY_07);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy07IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by07 is not null
        defaultRmsPersonShouldBeFound("by07.specified=true");

        // Get all the rmsPersonList where by07 is null
        defaultRmsPersonShouldNotBeFound("by07.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy08IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by08 equals to DEFAULT_BY_08
        defaultRmsPersonShouldBeFound("by08.equals=" + DEFAULT_BY_08);

        // Get all the rmsPersonList where by08 equals to UPDATED_BY_08
        defaultRmsPersonShouldNotBeFound("by08.equals=" + UPDATED_BY_08);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy08IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by08 in DEFAULT_BY_08 or UPDATED_BY_08
        defaultRmsPersonShouldBeFound("by08.in=" + DEFAULT_BY_08 + "," + UPDATED_BY_08);

        // Get all the rmsPersonList where by08 equals to UPDATED_BY_08
        defaultRmsPersonShouldNotBeFound("by08.in=" + UPDATED_BY_08);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy08IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by08 is not null
        defaultRmsPersonShouldBeFound("by08.specified=true");

        // Get all the rmsPersonList where by08 is null
        defaultRmsPersonShouldNotBeFound("by08.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy09IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by09 equals to DEFAULT_BY_09
        defaultRmsPersonShouldBeFound("by09.equals=" + DEFAULT_BY_09);

        // Get all the rmsPersonList where by09 equals to UPDATED_BY_09
        defaultRmsPersonShouldNotBeFound("by09.equals=" + UPDATED_BY_09);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy09IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by09 in DEFAULT_BY_09 or UPDATED_BY_09
        defaultRmsPersonShouldBeFound("by09.in=" + DEFAULT_BY_09 + "," + UPDATED_BY_09);

        // Get all the rmsPersonList where by09 equals to UPDATED_BY_09
        defaultRmsPersonShouldNotBeFound("by09.in=" + UPDATED_BY_09);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy09IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by09 is not null
        defaultRmsPersonShouldBeFound("by09.specified=true");

        // Get all the rmsPersonList where by09 is null
        defaultRmsPersonShouldNotBeFound("by09.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy10IsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by10 equals to DEFAULT_BY_10
        defaultRmsPersonShouldBeFound("by10.equals=" + DEFAULT_BY_10);

        // Get all the rmsPersonList where by10 equals to UPDATED_BY_10
        defaultRmsPersonShouldNotBeFound("by10.equals=" + UPDATED_BY_10);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy10IsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by10 in DEFAULT_BY_10 or UPDATED_BY_10
        defaultRmsPersonShouldBeFound("by10.in=" + DEFAULT_BY_10 + "," + UPDATED_BY_10);

        // Get all the rmsPersonList where by10 equals to UPDATED_BY_10
        defaultRmsPersonShouldNotBeFound("by10.in=" + UPDATED_BY_10);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByBy10IsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where by10 is not null
        defaultRmsPersonShouldBeFound("by10.specified=true");

        // Get all the rmsPersonList where by10 is null
        defaultRmsPersonShouldNotBeFound("by10.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where validType equals to DEFAULT_VALID_TYPE
        defaultRmsPersonShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the rmsPersonList where validType equals to UPDATED_VALID_TYPE
        defaultRmsPersonShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultRmsPersonShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the rmsPersonList where validType equals to UPDATED_VALID_TYPE
        defaultRmsPersonShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where validType is not null
        defaultRmsPersonShouldBeFound("validType.specified=true");

        // Get all the rmsPersonList where validType is null
        defaultRmsPersonShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultRmsPersonShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the rmsPersonList where validBegin equals to UPDATED_VALID_BEGIN
        defaultRmsPersonShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultRmsPersonShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the rmsPersonList where validBegin equals to UPDATED_VALID_BEGIN
        defaultRmsPersonShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where validBegin is not null
        defaultRmsPersonShouldBeFound("validBegin.specified=true");

        // Get all the rmsPersonList where validBegin is null
        defaultRmsPersonShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where validEnd equals to DEFAULT_VALID_END
        defaultRmsPersonShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the rmsPersonList where validEnd equals to UPDATED_VALID_END
        defaultRmsPersonShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultRmsPersonShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the rmsPersonList where validEnd equals to UPDATED_VALID_END
        defaultRmsPersonShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where validEnd is not null
        defaultRmsPersonShouldBeFound("validEnd.specified=true");

        // Get all the rmsPersonList where validEnd is null
        defaultRmsPersonShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where insertTime equals to DEFAULT_INSERT_TIME
        defaultRmsPersonShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the rmsPersonList where insertTime equals to UPDATED_INSERT_TIME
        defaultRmsPersonShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultRmsPersonShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the rmsPersonList where insertTime equals to UPDATED_INSERT_TIME
        defaultRmsPersonShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where insertTime is not null
        defaultRmsPersonShouldBeFound("insertTime.specified=true");

        // Get all the rmsPersonList where insertTime is null
        defaultRmsPersonShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultRmsPersonShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the rmsPersonList where updateTime equals to UPDATED_UPDATE_TIME
        defaultRmsPersonShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultRmsPersonShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the rmsPersonList where updateTime equals to UPDATED_UPDATE_TIME
        defaultRmsPersonShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where updateTime is not null
        defaultRmsPersonShouldBeFound("updateTime.specified=true");

        // Get all the rmsPersonList where updateTime is null
        defaultRmsPersonShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultRmsPersonShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the rmsPersonList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultRmsPersonShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultRmsPersonShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the rmsPersonList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultRmsPersonShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where verifyTime is not null
        defaultRmsPersonShouldBeFound("verifyTime.specified=true");

        // Get all the rmsPersonList where verifyTime is null
        defaultRmsPersonShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultRmsPersonShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the rmsPersonList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultRmsPersonShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultRmsPersonShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the rmsPersonList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultRmsPersonShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllRmsPeopleBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        // Get all the rmsPersonList where serialNumber is not null
        defaultRmsPersonShouldBeFound("serialNumber.specified=true");

        // Get all the rmsPersonList where serialNumber is null
        defaultRmsPersonShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsPeopleByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        rmsPerson.setCreatedBy(createdBy);
        rmsPersonRepository.saveAndFlush(rmsPerson);
        Long createdById = createdBy.getId();

        // Get all the rmsPersonList where createdBy equals to createdById
        defaultRmsPersonShouldBeFound("createdById.equals=" + createdById);

        // Get all the rmsPersonList where createdBy equals to createdById + 1
        defaultRmsPersonShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsPeopleByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        rmsPerson.setModifiedBy(modifiedBy);
        rmsPersonRepository.saveAndFlush(rmsPerson);
        Long modifiedById = modifiedBy.getId();

        // Get all the rmsPersonList where modifiedBy equals to modifiedById
        defaultRmsPersonShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the rmsPersonList where modifiedBy equals to modifiedById + 1
        defaultRmsPersonShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsPeopleByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        rmsPerson.setVerifiedBy(verifiedBy);
        rmsPersonRepository.saveAndFlush(rmsPerson);
        Long verifiedById = verifiedBy.getId();

        // Get all the rmsPersonList where verifiedBy equals to verifiedById
        defaultRmsPersonShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the rmsPersonList where verifiedBy equals to verifiedById + 1
        defaultRmsPersonShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultRmsPersonShouldBeFound(String filter) throws Exception {
        restRmsPersonMockMvc.perform(get("/api/rms-people?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsPerson.getId().intValue())))
            .andExpect(jsonPath("$.[*].personName").value(hasItem(DEFAULT_PERSON_NAME.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].jobId").value(hasItem(DEFAULT_JOB_ID.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].otherName").value(hasItem(DEFAULT_OTHER_NAME.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.booleanValue())))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY.toString())))
            .andExpect(jsonPath("$.[*].pic").value(hasItem(DEFAULT_PIC.toString())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].depAddress").value(hasItem(DEFAULT_DEP_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].depCode").value(hasItem(DEFAULT_DEP_CODE.toString())))
            .andExpect(jsonPath("$.[*].dutyId").value(hasItem(DEFAULT_DUTY_ID.toString())))
            .andExpect(jsonPath("$.[*].dimissionFlag").value(hasItem(DEFAULT_DIMISSION_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].headerFlag").value(hasItem(DEFAULT_HEADER_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].satrapFlag").value(hasItem(DEFAULT_SATRAP_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].leaderFlag").value(hasItem(DEFAULT_LEADER_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].postFlag1").value(hasItem(DEFAULT_POST_FLAG_1.booleanValue())))
            .andExpect(jsonPath("$.[*].postFlag2").value(hasItem(DEFAULT_POST_FLAG_2.booleanValue())))
            .andExpect(jsonPath("$.[*].postFlag3").value(hasItem(DEFAULT_POST_FLAG_3.booleanValue())))
            .andExpect(jsonPath("$.[*].officeTel").value(hasItem(DEFAULT_OFFICE_TEL.toString())))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX.toString())))
            .andExpect(jsonPath("$.[*].mail1").value(hasItem(DEFAULT_MAIL_1.toString())))
            .andExpect(jsonPath("$.[*].mail2").value(hasItem(DEFAULT_MAIL_2.toString())))
            .andExpect(jsonPath("$.[*].familyTel").value(hasItem(DEFAULT_FAMILY_TEL.toString())))
            .andExpect(jsonPath("$.[*].familyAdd").value(hasItem(DEFAULT_FAMILY_ADD.toString())))
            .andExpect(jsonPath("$.[*].familyCode").value(hasItem(DEFAULT_FAMILY_CODE.toString())))
            .andExpect(jsonPath("$.[*].personDesc").value(hasItem(DEFAULT_PERSON_DESC.toString())))
            .andExpect(jsonPath("$.[*].idCode").value(hasItem(DEFAULT_ID_CODE.toString())))
            .andExpect(jsonPath("$.[*].pop3").value(hasItem(DEFAULT_POP_3.toString())))
            .andExpect(jsonPath("$.[*].smtp").value(hasItem(DEFAULT_SMTP.toString())))
            .andExpect(jsonPath("$.[*].mailUsername").value(hasItem(DEFAULT_MAIL_USERNAME.toString())))
            .andExpect(jsonPath("$.[*].mailPassword").value(hasItem(DEFAULT_MAIL_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].mailKeepFlag").value(hasItem(DEFAULT_MAIL_KEEP_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].personSort").value(hasItem(DEFAULT_PERSON_SORT.toString())))
            .andExpect(jsonPath("$.[*].levelNum").value(hasItem(DEFAULT_LEVEL_NUM)))
            .andExpect(jsonPath("$.[*].personStateId").value(hasItem(DEFAULT_PERSON_STATE_ID.toString())))
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
        restRmsPersonMockMvc.perform(get("/api/rms-people/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultRmsPersonShouldNotBeFound(String filter) throws Exception {
        restRmsPersonMockMvc.perform(get("/api/rms-people?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRmsPersonMockMvc.perform(get("/api/rms-people/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingRmsPerson() throws Exception {
        // Get the rmsPerson
        restRmsPersonMockMvc.perform(get("/api/rms-people/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRmsPerson() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        int databaseSizeBeforeUpdate = rmsPersonRepository.findAll().size();

        // Update the rmsPerson
        RmsPerson updatedRmsPerson = rmsPersonRepository.findById(rmsPerson.getId()).get();
        // Disconnect from session so that the updates on updatedRmsPerson are not directly saved in db
        em.detach(updatedRmsPerson);
        updatedRmsPerson
            .personName(UPDATED_PERSON_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .jobId(UPDATED_JOB_ID)
            .lastName(UPDATED_LAST_NAME)
            .otherName(UPDATED_OTHER_NAME)
            .sex(UPDATED_SEX)
            .birthday(UPDATED_BIRTHDAY)
            .pic(UPDATED_PIC)
            .icon(UPDATED_ICON)
            .mobile(UPDATED_MOBILE)
            .depAddress(UPDATED_DEP_ADDRESS)
            .depCode(UPDATED_DEP_CODE)
            .dutyId(UPDATED_DUTY_ID)
            .dimissionFlag(UPDATED_DIMISSION_FLAG)
            .headerFlag(UPDATED_HEADER_FLAG)
            .satrapFlag(UPDATED_SATRAP_FLAG)
            .leaderFlag(UPDATED_LEADER_FLAG)
            .postFlag1(UPDATED_POST_FLAG_1)
            .postFlag2(UPDATED_POST_FLAG_2)
            .postFlag3(UPDATED_POST_FLAG_3)
            .officeTel(UPDATED_OFFICE_TEL)
            .fax(UPDATED_FAX)
            .mail1(UPDATED_MAIL_1)
            .mail2(UPDATED_MAIL_2)
            .familyTel(UPDATED_FAMILY_TEL)
            .familyAdd(UPDATED_FAMILY_ADD)
            .familyCode(UPDATED_FAMILY_CODE)
            .personDesc(UPDATED_PERSON_DESC)
            .idCode(UPDATED_ID_CODE)
            .pop3(UPDATED_POP_3)
            .smtp(UPDATED_SMTP)
            .mailUsername(UPDATED_MAIL_USERNAME)
            .mailPassword(UPDATED_MAIL_PASSWORD)
            .mailKeepFlag(UPDATED_MAIL_KEEP_FLAG)
            .personSort(UPDATED_PERSON_SORT)
            .levelNum(UPDATED_LEVEL_NUM)
            .personStateId(UPDATED_PERSON_STATE_ID)
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
        RmsPersonDTO rmsPersonDTO = rmsPersonMapper.toDto(updatedRmsPerson);

        restRmsPersonMockMvc.perform(put("/api/rms-people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsPersonDTO)))
            .andExpect(status().isOk());

        // Validate the RmsPerson in the database
        List<RmsPerson> rmsPersonList = rmsPersonRepository.findAll();
        assertThat(rmsPersonList).hasSize(databaseSizeBeforeUpdate);
        RmsPerson testRmsPerson = rmsPersonList.get(rmsPersonList.size() - 1);
        assertThat(testRmsPerson.getPersonName()).isEqualTo(UPDATED_PERSON_NAME);
        assertThat(testRmsPerson.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testRmsPerson.getJobId()).isEqualTo(UPDATED_JOB_ID);
        assertThat(testRmsPerson.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testRmsPerson.getOtherName()).isEqualTo(UPDATED_OTHER_NAME);
        assertThat(testRmsPerson.isSex()).isEqualTo(UPDATED_SEX);
        assertThat(testRmsPerson.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
        assertThat(testRmsPerson.getPic()).isEqualTo(UPDATED_PIC);
        assertThat(testRmsPerson.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testRmsPerson.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testRmsPerson.getDepAddress()).isEqualTo(UPDATED_DEP_ADDRESS);
        assertThat(testRmsPerson.getDepCode()).isEqualTo(UPDATED_DEP_CODE);
        assertThat(testRmsPerson.getDutyId()).isEqualTo(UPDATED_DUTY_ID);
        assertThat(testRmsPerson.isDimissionFlag()).isEqualTo(UPDATED_DIMISSION_FLAG);
        assertThat(testRmsPerson.isHeaderFlag()).isEqualTo(UPDATED_HEADER_FLAG);
        assertThat(testRmsPerson.isSatrapFlag()).isEqualTo(UPDATED_SATRAP_FLAG);
        assertThat(testRmsPerson.isLeaderFlag()).isEqualTo(UPDATED_LEADER_FLAG);
        assertThat(testRmsPerson.isPostFlag1()).isEqualTo(UPDATED_POST_FLAG_1);
        assertThat(testRmsPerson.isPostFlag2()).isEqualTo(UPDATED_POST_FLAG_2);
        assertThat(testRmsPerson.isPostFlag3()).isEqualTo(UPDATED_POST_FLAG_3);
        assertThat(testRmsPerson.getOfficeTel()).isEqualTo(UPDATED_OFFICE_TEL);
        assertThat(testRmsPerson.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testRmsPerson.getMail1()).isEqualTo(UPDATED_MAIL_1);
        assertThat(testRmsPerson.getMail2()).isEqualTo(UPDATED_MAIL_2);
        assertThat(testRmsPerson.getFamilyTel()).isEqualTo(UPDATED_FAMILY_TEL);
        assertThat(testRmsPerson.getFamilyAdd()).isEqualTo(UPDATED_FAMILY_ADD);
        assertThat(testRmsPerson.getFamilyCode()).isEqualTo(UPDATED_FAMILY_CODE);
        assertThat(testRmsPerson.getPersonDesc()).isEqualTo(UPDATED_PERSON_DESC);
        assertThat(testRmsPerson.getIdCode()).isEqualTo(UPDATED_ID_CODE);
        assertThat(testRmsPerson.getPop3()).isEqualTo(UPDATED_POP_3);
        assertThat(testRmsPerson.getSmtp()).isEqualTo(UPDATED_SMTP);
        assertThat(testRmsPerson.getMailUsername()).isEqualTo(UPDATED_MAIL_USERNAME);
        assertThat(testRmsPerson.getMailPassword()).isEqualTo(UPDATED_MAIL_PASSWORD);
        assertThat(testRmsPerson.isMailKeepFlag()).isEqualTo(UPDATED_MAIL_KEEP_FLAG);
        assertThat(testRmsPerson.getPersonSort()).isEqualTo(UPDATED_PERSON_SORT);
        assertThat(testRmsPerson.getLevelNum()).isEqualTo(UPDATED_LEVEL_NUM);
        assertThat(testRmsPerson.getPersonStateId()).isEqualTo(UPDATED_PERSON_STATE_ID);
        assertThat(testRmsPerson.getBy01()).isEqualTo(UPDATED_BY_01);
        assertThat(testRmsPerson.getBy02()).isEqualTo(UPDATED_BY_02);
        assertThat(testRmsPerson.getBy03()).isEqualTo(UPDATED_BY_03);
        assertThat(testRmsPerson.getBy04()).isEqualTo(UPDATED_BY_04);
        assertThat(testRmsPerson.getBy05()).isEqualTo(UPDATED_BY_05);
        assertThat(testRmsPerson.getBy06()).isEqualTo(UPDATED_BY_06);
        assertThat(testRmsPerson.getBy07()).isEqualTo(UPDATED_BY_07);
        assertThat(testRmsPerson.getBy08()).isEqualTo(UPDATED_BY_08);
        assertThat(testRmsPerson.getBy09()).isEqualTo(UPDATED_BY_09);
        assertThat(testRmsPerson.getBy10()).isEqualTo(UPDATED_BY_10);
        assertThat(testRmsPerson.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testRmsPerson.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testRmsPerson.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testRmsPerson.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testRmsPerson.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testRmsPerson.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);
        assertThat(testRmsPerson.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);

        // Validate the RmsPerson in Elasticsearch
        verify(mockRmsPersonSearchRepository, times(1)).save(testRmsPerson);
    }

    @Test
    @Transactional
    public void updateNonExistingRmsPerson() throws Exception {
        int databaseSizeBeforeUpdate = rmsPersonRepository.findAll().size();

        // Create the RmsPerson
        RmsPersonDTO rmsPersonDTO = rmsPersonMapper.toDto(rmsPerson);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRmsPersonMockMvc.perform(put("/api/rms-people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsPersonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RmsPerson in the database
        List<RmsPerson> rmsPersonList = rmsPersonRepository.findAll();
        assertThat(rmsPersonList).hasSize(databaseSizeBeforeUpdate);

        // Validate the RmsPerson in Elasticsearch
        verify(mockRmsPersonSearchRepository, times(0)).save(rmsPerson);
    }

    @Test
    @Transactional
    public void deleteRmsPerson() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);

        int databaseSizeBeforeDelete = rmsPersonRepository.findAll().size();

        // Get the rmsPerson
        restRmsPersonMockMvc.perform(delete("/api/rms-people/{id}", rmsPerson.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RmsPerson> rmsPersonList = rmsPersonRepository.findAll();
        assertThat(rmsPersonList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the RmsPerson in Elasticsearch
        verify(mockRmsPersonSearchRepository, times(1)).deleteById(rmsPerson.getId());
    }

    @Test
    @Transactional
    public void searchRmsPerson() throws Exception {
        // Initialize the database
        rmsPersonRepository.saveAndFlush(rmsPerson);
        when(mockRmsPersonSearchRepository.search(queryStringQuery("id:" + rmsPerson.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(rmsPerson), PageRequest.of(0, 1), 1));
        // Search the rmsPerson
        restRmsPersonMockMvc.perform(get("/api/_search/rms-people?query=id:" + rmsPerson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsPerson.getId().intValue())))
            .andExpect(jsonPath("$.[*].personName").value(hasItem(DEFAULT_PERSON_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].jobId").value(hasItem(DEFAULT_JOB_ID)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].otherName").value(hasItem(DEFAULT_OTHER_NAME)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.booleanValue())))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY.toString())))
            .andExpect(jsonPath("$.[*].pic").value(hasItem(DEFAULT_PIC)))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].depAddress").value(hasItem(DEFAULT_DEP_ADDRESS)))
            .andExpect(jsonPath("$.[*].depCode").value(hasItem(DEFAULT_DEP_CODE)))
            .andExpect(jsonPath("$.[*].dutyId").value(hasItem(DEFAULT_DUTY_ID)))
            .andExpect(jsonPath("$.[*].dimissionFlag").value(hasItem(DEFAULT_DIMISSION_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].headerFlag").value(hasItem(DEFAULT_HEADER_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].satrapFlag").value(hasItem(DEFAULT_SATRAP_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].leaderFlag").value(hasItem(DEFAULT_LEADER_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].postFlag1").value(hasItem(DEFAULT_POST_FLAG_1.booleanValue())))
            .andExpect(jsonPath("$.[*].postFlag2").value(hasItem(DEFAULT_POST_FLAG_2.booleanValue())))
            .andExpect(jsonPath("$.[*].postFlag3").value(hasItem(DEFAULT_POST_FLAG_3.booleanValue())))
            .andExpect(jsonPath("$.[*].officeTel").value(hasItem(DEFAULT_OFFICE_TEL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].mail1").value(hasItem(DEFAULT_MAIL_1)))
            .andExpect(jsonPath("$.[*].mail2").value(hasItem(DEFAULT_MAIL_2)))
            .andExpect(jsonPath("$.[*].familyTel").value(hasItem(DEFAULT_FAMILY_TEL)))
            .andExpect(jsonPath("$.[*].familyAdd").value(hasItem(DEFAULT_FAMILY_ADD)))
            .andExpect(jsonPath("$.[*].familyCode").value(hasItem(DEFAULT_FAMILY_CODE)))
            .andExpect(jsonPath("$.[*].personDesc").value(hasItem(DEFAULT_PERSON_DESC)))
            .andExpect(jsonPath("$.[*].idCode").value(hasItem(DEFAULT_ID_CODE)))
            .andExpect(jsonPath("$.[*].pop3").value(hasItem(DEFAULT_POP_3)))
            .andExpect(jsonPath("$.[*].smtp").value(hasItem(DEFAULT_SMTP)))
            .andExpect(jsonPath("$.[*].mailUsername").value(hasItem(DEFAULT_MAIL_USERNAME)))
            .andExpect(jsonPath("$.[*].mailPassword").value(hasItem(DEFAULT_MAIL_PASSWORD)))
            .andExpect(jsonPath("$.[*].mailKeepFlag").value(hasItem(DEFAULT_MAIL_KEEP_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].personSort").value(hasItem(DEFAULT_PERSON_SORT)))
            .andExpect(jsonPath("$.[*].levelNum").value(hasItem(DEFAULT_LEVEL_NUM)))
            .andExpect(jsonPath("$.[*].personStateId").value(hasItem(DEFAULT_PERSON_STATE_ID)))
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
        TestUtil.equalsVerifier(RmsPerson.class);
        RmsPerson rmsPerson1 = new RmsPerson();
        rmsPerson1.setId(1L);
        RmsPerson rmsPerson2 = new RmsPerson();
        rmsPerson2.setId(rmsPerson1.getId());
        assertThat(rmsPerson1).isEqualTo(rmsPerson2);
        rmsPerson2.setId(2L);
        assertThat(rmsPerson1).isNotEqualTo(rmsPerson2);
        rmsPerson1.setId(null);
        assertThat(rmsPerson1).isNotEqualTo(rmsPerson2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RmsPersonDTO.class);
        RmsPersonDTO rmsPersonDTO1 = new RmsPersonDTO();
        rmsPersonDTO1.setId(1L);
        RmsPersonDTO rmsPersonDTO2 = new RmsPersonDTO();
        assertThat(rmsPersonDTO1).isNotEqualTo(rmsPersonDTO2);
        rmsPersonDTO2.setId(rmsPersonDTO1.getId());
        assertThat(rmsPersonDTO1).isEqualTo(rmsPersonDTO2);
        rmsPersonDTO2.setId(2L);
        assertThat(rmsPersonDTO1).isNotEqualTo(rmsPersonDTO2);
        rmsPersonDTO1.setId(null);
        assertThat(rmsPersonDTO1).isNotEqualTo(rmsPersonDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rmsPersonMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rmsPersonMapper.fromId(null)).isNull();
    }
}
