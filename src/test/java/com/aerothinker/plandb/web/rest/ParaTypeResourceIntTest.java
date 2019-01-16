package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.ParaType;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.ParaType;
import com.aerothinker.plandb.repository.ParaTypeRepository;
import com.aerothinker.plandb.repository.search.ParaTypeSearchRepository;
import com.aerothinker.plandb.service.ParaTypeService;
import com.aerothinker.plandb.service.dto.ParaTypeDTO;
import com.aerothinker.plandb.service.mapper.ParaTypeMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.ParaTypeCriteria;
import com.aerothinker.plandb.service.ParaTypeQueryService;

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
 * Test class for the ParaTypeResource REST controller.
 *
 * @see ParaTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class ParaTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SORT_STRING = "AAAAAAAAAA";
    private static final String UPDATED_SORT_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_DESC_STRING = "AAAAAAAAAA";
    private static final String UPDATED_DESC_STRING = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE_BLOB = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE_BLOB = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_BLOB_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_BLOB_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_IMAGE_BLOB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_BLOB_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_USING_FLAG = false;
    private static final Boolean UPDATED_USING_FLAG = true;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

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

    @Autowired
    private ParaTypeRepository paraTypeRepository;

    @Autowired
    private ParaTypeMapper paraTypeMapper;

    @Autowired
    private ParaTypeService paraTypeService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.ParaTypeSearchRepositoryMockConfiguration
     */
    @Autowired
    private ParaTypeSearchRepository mockParaTypeSearchRepository;

    @Autowired
    private ParaTypeQueryService paraTypeQueryService;

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

    private MockMvc restParaTypeMockMvc;

    private ParaType paraType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParaTypeResource paraTypeResource = new ParaTypeResource(paraTypeService, paraTypeQueryService);
        this.restParaTypeMockMvc = MockMvcBuilders.standaloneSetup(paraTypeResource)
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
    public static ParaType createEntity(EntityManager em) {
        ParaType paraType = new ParaType()
            .name(DEFAULT_NAME)
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .sortString(DEFAULT_SORT_STRING)
            .descString(DEFAULT_DESC_STRING)
            .imageBlob(DEFAULT_IMAGE_BLOB)
            .imageBlobContentType(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)
            .imageBlobName(DEFAULT_IMAGE_BLOB_NAME)
            .usingFlag(DEFAULT_USING_FLAG)
            .remarks(DEFAULT_REMARKS)
            .validType(DEFAULT_VALID_TYPE)
            .validBegin(DEFAULT_VALID_BEGIN)
            .validEnd(DEFAULT_VALID_END)
            .insertTime(DEFAULT_INSERT_TIME)
            .updateTime(DEFAULT_UPDATE_TIME)
            .verifyTime(DEFAULT_VERIFY_TIME);
        return paraType;
    }

    @Before
    public void initTest() {
        paraType = createEntity(em);
    }

    @Test
    @Transactional
    public void createParaType() throws Exception {
        int databaseSizeBeforeCreate = paraTypeRepository.findAll().size();

        // Create the ParaType
        ParaTypeDTO paraTypeDTO = paraTypeMapper.toDto(paraType);
        restParaTypeMockMvc.perform(post("/api/para-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paraTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ParaType in the database
        List<ParaType> paraTypeList = paraTypeRepository.findAll();
        assertThat(paraTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ParaType testParaType = paraTypeList.get(paraTypeList.size() - 1);
        assertThat(testParaType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testParaType.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testParaType.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testParaType.getDescString()).isEqualTo(DEFAULT_DESC_STRING);
        assertThat(testParaType.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testParaType.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testParaType.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testParaType.isUsingFlag()).isEqualTo(DEFAULT_USING_FLAG);
        assertThat(testParaType.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testParaType.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testParaType.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testParaType.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testParaType.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testParaType.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testParaType.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);

        // Validate the ParaType in Elasticsearch
        verify(mockParaTypeSearchRepository, times(1)).save(testParaType);
    }

    @Test
    @Transactional
    public void createParaTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = paraTypeRepository.findAll().size();

        // Create the ParaType with an existing ID
        paraType.setId(1L);
        ParaTypeDTO paraTypeDTO = paraTypeMapper.toDto(paraType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParaTypeMockMvc.perform(post("/api/para-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paraTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ParaType in the database
        List<ParaType> paraTypeList = paraTypeRepository.findAll();
        assertThat(paraTypeList).hasSize(databaseSizeBeforeCreate);

        // Validate the ParaType in Elasticsearch
        verify(mockParaTypeSearchRepository, times(0)).save(paraType);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = paraTypeRepository.findAll().size();
        // set the field null
        paraType.setName(null);

        // Create the ParaType, which fails.
        ParaTypeDTO paraTypeDTO = paraTypeMapper.toDto(paraType);

        restParaTypeMockMvc.perform(post("/api/para-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paraTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ParaType> paraTypeList = paraTypeRepository.findAll();
        assertThat(paraTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParaTypes() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList
        restParaTypeMockMvc.perform(get("/api/para-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paraType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING.toString())))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING.toString())))
            .andExpect(jsonPath("$.[*].imageBlobContentType").value(hasItem(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB))))
            .andExpect(jsonPath("$.[*].imageBlobName").value(hasItem(DEFAULT_IMAGE_BLOB_NAME.toString())))
            .andExpect(jsonPath("$.[*].usingFlag").value(hasItem(DEFAULT_USING_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getParaType() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get the paraType
        restParaTypeMockMvc.perform(get("/api/para-types/{id}", paraType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(paraType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.sortString").value(DEFAULT_SORT_STRING.toString()))
            .andExpect(jsonPath("$.descString").value(DEFAULT_DESC_STRING.toString()))
            .andExpect(jsonPath("$.imageBlobContentType").value(DEFAULT_IMAGE_BLOB_CONTENT_TYPE))
            .andExpect(jsonPath("$.imageBlob").value(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB)))
            .andExpect(jsonPath("$.imageBlobName").value(DEFAULT_IMAGE_BLOB_NAME.toString()))
            .andExpect(jsonPath("$.usingFlag").value(DEFAULT_USING_FLAG.booleanValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.validType").value(DEFAULT_VALID_TYPE.toString()))
            .andExpect(jsonPath("$.validBegin").value(DEFAULT_VALID_BEGIN.toString()))
            .andExpect(jsonPath("$.validEnd").value(DEFAULT_VALID_END.toString()))
            .andExpect(jsonPath("$.insertTime").value(DEFAULT_INSERT_TIME.toString()))
            .andExpect(jsonPath("$.updateTime").value(DEFAULT_UPDATE_TIME.toString()))
            .andExpect(jsonPath("$.verifyTime").value(DEFAULT_VERIFY_TIME.toString()));
    }

    @Test
    @Transactional
    public void getAllParaTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where name equals to DEFAULT_NAME
        defaultParaTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the paraTypeList where name equals to UPDATED_NAME
        defaultParaTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllParaTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultParaTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the paraTypeList where name equals to UPDATED_NAME
        defaultParaTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllParaTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where name is not null
        defaultParaTypeShouldBeFound("name.specified=true");

        // Get all the paraTypeList where name is null
        defaultParaTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultParaTypeShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the paraTypeList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultParaTypeShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllParaTypesBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultParaTypeShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the paraTypeList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultParaTypeShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllParaTypesBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where serialNumber is not null
        defaultParaTypeShouldBeFound("serialNumber.specified=true");

        // Get all the paraTypeList where serialNumber is null
        defaultParaTypeShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where sortString equals to DEFAULT_SORT_STRING
        defaultParaTypeShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the paraTypeList where sortString equals to UPDATED_SORT_STRING
        defaultParaTypeShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllParaTypesBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultParaTypeShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the paraTypeList where sortString equals to UPDATED_SORT_STRING
        defaultParaTypeShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllParaTypesBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where sortString is not null
        defaultParaTypeShouldBeFound("sortString.specified=true");

        // Get all the paraTypeList where sortString is null
        defaultParaTypeShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesByDescStringIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where descString equals to DEFAULT_DESC_STRING
        defaultParaTypeShouldBeFound("descString.equals=" + DEFAULT_DESC_STRING);

        // Get all the paraTypeList where descString equals to UPDATED_DESC_STRING
        defaultParaTypeShouldNotBeFound("descString.equals=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllParaTypesByDescStringIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where descString in DEFAULT_DESC_STRING or UPDATED_DESC_STRING
        defaultParaTypeShouldBeFound("descString.in=" + DEFAULT_DESC_STRING + "," + UPDATED_DESC_STRING);

        // Get all the paraTypeList where descString equals to UPDATED_DESC_STRING
        defaultParaTypeShouldNotBeFound("descString.in=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllParaTypesByDescStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where descString is not null
        defaultParaTypeShouldBeFound("descString.specified=true");

        // Get all the paraTypeList where descString is null
        defaultParaTypeShouldNotBeFound("descString.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultParaTypeShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the paraTypeList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultParaTypeShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllParaTypesByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultParaTypeShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the paraTypeList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultParaTypeShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllParaTypesByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where imageBlobName is not null
        defaultParaTypeShouldBeFound("imageBlobName.specified=true");

        // Get all the paraTypeList where imageBlobName is null
        defaultParaTypeShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesByUsingFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where usingFlag equals to DEFAULT_USING_FLAG
        defaultParaTypeShouldBeFound("usingFlag.equals=" + DEFAULT_USING_FLAG);

        // Get all the paraTypeList where usingFlag equals to UPDATED_USING_FLAG
        defaultParaTypeShouldNotBeFound("usingFlag.equals=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllParaTypesByUsingFlagIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where usingFlag in DEFAULT_USING_FLAG or UPDATED_USING_FLAG
        defaultParaTypeShouldBeFound("usingFlag.in=" + DEFAULT_USING_FLAG + "," + UPDATED_USING_FLAG);

        // Get all the paraTypeList where usingFlag equals to UPDATED_USING_FLAG
        defaultParaTypeShouldNotBeFound("usingFlag.in=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllParaTypesByUsingFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where usingFlag is not null
        defaultParaTypeShouldBeFound("usingFlag.specified=true");

        // Get all the paraTypeList where usingFlag is null
        defaultParaTypeShouldNotBeFound("usingFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where remarks equals to DEFAULT_REMARKS
        defaultParaTypeShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the paraTypeList where remarks equals to UPDATED_REMARKS
        defaultParaTypeShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllParaTypesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultParaTypeShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the paraTypeList where remarks equals to UPDATED_REMARKS
        defaultParaTypeShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllParaTypesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where remarks is not null
        defaultParaTypeShouldBeFound("remarks.specified=true");

        // Get all the paraTypeList where remarks is null
        defaultParaTypeShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where validType equals to DEFAULT_VALID_TYPE
        defaultParaTypeShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the paraTypeList where validType equals to UPDATED_VALID_TYPE
        defaultParaTypeShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllParaTypesByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultParaTypeShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the paraTypeList where validType equals to UPDATED_VALID_TYPE
        defaultParaTypeShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllParaTypesByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where validType is not null
        defaultParaTypeShouldBeFound("validType.specified=true");

        // Get all the paraTypeList where validType is null
        defaultParaTypeShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultParaTypeShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the paraTypeList where validBegin equals to UPDATED_VALID_BEGIN
        defaultParaTypeShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllParaTypesByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultParaTypeShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the paraTypeList where validBegin equals to UPDATED_VALID_BEGIN
        defaultParaTypeShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllParaTypesByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where validBegin is not null
        defaultParaTypeShouldBeFound("validBegin.specified=true");

        // Get all the paraTypeList where validBegin is null
        defaultParaTypeShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where validEnd equals to DEFAULT_VALID_END
        defaultParaTypeShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the paraTypeList where validEnd equals to UPDATED_VALID_END
        defaultParaTypeShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllParaTypesByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultParaTypeShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the paraTypeList where validEnd equals to UPDATED_VALID_END
        defaultParaTypeShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllParaTypesByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where validEnd is not null
        defaultParaTypeShouldBeFound("validEnd.specified=true");

        // Get all the paraTypeList where validEnd is null
        defaultParaTypeShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where insertTime equals to DEFAULT_INSERT_TIME
        defaultParaTypeShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the paraTypeList where insertTime equals to UPDATED_INSERT_TIME
        defaultParaTypeShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllParaTypesByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultParaTypeShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the paraTypeList where insertTime equals to UPDATED_INSERT_TIME
        defaultParaTypeShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllParaTypesByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where insertTime is not null
        defaultParaTypeShouldBeFound("insertTime.specified=true");

        // Get all the paraTypeList where insertTime is null
        defaultParaTypeShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultParaTypeShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the paraTypeList where updateTime equals to UPDATED_UPDATE_TIME
        defaultParaTypeShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllParaTypesByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultParaTypeShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the paraTypeList where updateTime equals to UPDATED_UPDATE_TIME
        defaultParaTypeShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllParaTypesByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where updateTime is not null
        defaultParaTypeShouldBeFound("updateTime.specified=true");

        // Get all the paraTypeList where updateTime is null
        defaultParaTypeShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultParaTypeShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the paraTypeList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultParaTypeShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllParaTypesByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultParaTypeShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the paraTypeList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultParaTypeShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllParaTypesByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        // Get all the paraTypeList where verifyTime is not null
        defaultParaTypeShouldBeFound("verifyTime.specified=true");

        // Get all the paraTypeList where verifyTime is null
        defaultParaTypeShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllParaTypesByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        paraType.setCreatedBy(createdBy);
        paraTypeRepository.saveAndFlush(paraType);
        Long createdById = createdBy.getId();

        // Get all the paraTypeList where createdBy equals to createdById
        defaultParaTypeShouldBeFound("createdById.equals=" + createdById);

        // Get all the paraTypeList where createdBy equals to createdById + 1
        defaultParaTypeShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllParaTypesByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        paraType.setModifiedBy(modifiedBy);
        paraTypeRepository.saveAndFlush(paraType);
        Long modifiedById = modifiedBy.getId();

        // Get all the paraTypeList where modifiedBy equals to modifiedById
        defaultParaTypeShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the paraTypeList where modifiedBy equals to modifiedById + 1
        defaultParaTypeShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllParaTypesByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        paraType.setVerifiedBy(verifiedBy);
        paraTypeRepository.saveAndFlush(paraType);
        Long verifiedById = verifiedBy.getId();

        // Get all the paraTypeList where verifiedBy equals to verifiedById
        defaultParaTypeShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the paraTypeList where verifiedBy equals to verifiedById + 1
        defaultParaTypeShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllParaTypesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        ParaType parent = ParaTypeResourceIntTest.createEntity(em);
        em.persist(parent);
        em.flush();
        paraType.setParent(parent);
        paraTypeRepository.saveAndFlush(paraType);
        Long parentId = parent.getId();

        // Get all the paraTypeList where parent equals to parentId
        defaultParaTypeShouldBeFound("parentId.equals=" + parentId);

        // Get all the paraTypeList where parent equals to parentId + 1
        defaultParaTypeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultParaTypeShouldBeFound(String filter) throws Exception {
        restParaTypeMockMvc.perform(get("/api/para-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paraType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING.toString())))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING.toString())))
            .andExpect(jsonPath("$.[*].imageBlobContentType").value(hasItem(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB))))
            .andExpect(jsonPath("$.[*].imageBlobName").value(hasItem(DEFAULT_IMAGE_BLOB_NAME.toString())))
            .andExpect(jsonPath("$.[*].usingFlag").value(hasItem(DEFAULT_USING_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())));

        // Check, that the count call also returns 1
        restParaTypeMockMvc.perform(get("/api/para-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultParaTypeShouldNotBeFound(String filter) throws Exception {
        restParaTypeMockMvc.perform(get("/api/para-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restParaTypeMockMvc.perform(get("/api/para-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingParaType() throws Exception {
        // Get the paraType
        restParaTypeMockMvc.perform(get("/api/para-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParaType() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        int databaseSizeBeforeUpdate = paraTypeRepository.findAll().size();

        // Update the paraType
        ParaType updatedParaType = paraTypeRepository.findById(paraType.getId()).get();
        // Disconnect from session so that the updates on updatedParaType are not directly saved in db
        em.detach(updatedParaType);
        updatedParaType
            .name(UPDATED_NAME)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .sortString(UPDATED_SORT_STRING)
            .descString(UPDATED_DESC_STRING)
            .imageBlob(UPDATED_IMAGE_BLOB)
            .imageBlobContentType(UPDATED_IMAGE_BLOB_CONTENT_TYPE)
            .imageBlobName(UPDATED_IMAGE_BLOB_NAME)
            .usingFlag(UPDATED_USING_FLAG)
            .remarks(UPDATED_REMARKS)
            .validType(UPDATED_VALID_TYPE)
            .validBegin(UPDATED_VALID_BEGIN)
            .validEnd(UPDATED_VALID_END)
            .insertTime(UPDATED_INSERT_TIME)
            .updateTime(UPDATED_UPDATE_TIME)
            .verifyTime(UPDATED_VERIFY_TIME);
        ParaTypeDTO paraTypeDTO = paraTypeMapper.toDto(updatedParaType);

        restParaTypeMockMvc.perform(put("/api/para-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paraTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ParaType in the database
        List<ParaType> paraTypeList = paraTypeRepository.findAll();
        assertThat(paraTypeList).hasSize(databaseSizeBeforeUpdate);
        ParaType testParaType = paraTypeList.get(paraTypeList.size() - 1);
        assertThat(testParaType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testParaType.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testParaType.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testParaType.getDescString()).isEqualTo(UPDATED_DESC_STRING);
        assertThat(testParaType.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testParaType.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testParaType.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testParaType.isUsingFlag()).isEqualTo(UPDATED_USING_FLAG);
        assertThat(testParaType.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testParaType.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testParaType.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testParaType.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testParaType.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testParaType.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testParaType.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);

        // Validate the ParaType in Elasticsearch
        verify(mockParaTypeSearchRepository, times(1)).save(testParaType);
    }

    @Test
    @Transactional
    public void updateNonExistingParaType() throws Exception {
        int databaseSizeBeforeUpdate = paraTypeRepository.findAll().size();

        // Create the ParaType
        ParaTypeDTO paraTypeDTO = paraTypeMapper.toDto(paraType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParaTypeMockMvc.perform(put("/api/para-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(paraTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ParaType in the database
        List<ParaType> paraTypeList = paraTypeRepository.findAll();
        assertThat(paraTypeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ParaType in Elasticsearch
        verify(mockParaTypeSearchRepository, times(0)).save(paraType);
    }

    @Test
    @Transactional
    public void deleteParaType() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);

        int databaseSizeBeforeDelete = paraTypeRepository.findAll().size();

        // Get the paraType
        restParaTypeMockMvc.perform(delete("/api/para-types/{id}", paraType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ParaType> paraTypeList = paraTypeRepository.findAll();
        assertThat(paraTypeList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ParaType in Elasticsearch
        verify(mockParaTypeSearchRepository, times(1)).deleteById(paraType.getId());
    }

    @Test
    @Transactional
    public void searchParaType() throws Exception {
        // Initialize the database
        paraTypeRepository.saveAndFlush(paraType);
        when(mockParaTypeSearchRepository.search(queryStringQuery("id:" + paraType.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(paraType), PageRequest.of(0, 1), 1));
        // Search the paraType
        restParaTypeMockMvc.perform(get("/api/_search/para-types?query=id:" + paraType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paraType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)))
            .andExpect(jsonPath("$.[*].sortString").value(hasItem(DEFAULT_SORT_STRING)))
            .andExpect(jsonPath("$.[*].descString").value(hasItem(DEFAULT_DESC_STRING)))
            .andExpect(jsonPath("$.[*].imageBlobContentType").value(hasItem(DEFAULT_IMAGE_BLOB_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageBlob").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_BLOB))))
            .andExpect(jsonPath("$.[*].imageBlobName").value(hasItem(DEFAULT_IMAGE_BLOB_NAME)))
            .andExpect(jsonPath("$.[*].usingFlag").value(hasItem(DEFAULT_USING_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].validBegin").value(hasItem(DEFAULT_VALID_BEGIN.toString())))
            .andExpect(jsonPath("$.[*].validEnd").value(hasItem(DEFAULT_VALID_END.toString())))
            .andExpect(jsonPath("$.[*].insertTime").value(hasItem(DEFAULT_INSERT_TIME.toString())))
            .andExpect(jsonPath("$.[*].updateTime").value(hasItem(DEFAULT_UPDATE_TIME.toString())))
            .andExpect(jsonPath("$.[*].verifyTime").value(hasItem(DEFAULT_VERIFY_TIME.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParaType.class);
        ParaType paraType1 = new ParaType();
        paraType1.setId(1L);
        ParaType paraType2 = new ParaType();
        paraType2.setId(paraType1.getId());
        assertThat(paraType1).isEqualTo(paraType2);
        paraType2.setId(2L);
        assertThat(paraType1).isNotEqualTo(paraType2);
        paraType1.setId(null);
        assertThat(paraType1).isNotEqualTo(paraType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParaTypeDTO.class);
        ParaTypeDTO paraTypeDTO1 = new ParaTypeDTO();
        paraTypeDTO1.setId(1L);
        ParaTypeDTO paraTypeDTO2 = new ParaTypeDTO();
        assertThat(paraTypeDTO1).isNotEqualTo(paraTypeDTO2);
        paraTypeDTO2.setId(paraTypeDTO1.getId());
        assertThat(paraTypeDTO1).isEqualTo(paraTypeDTO2);
        paraTypeDTO2.setId(2L);
        assertThat(paraTypeDTO1).isNotEqualTo(paraTypeDTO2);
        paraTypeDTO1.setId(null);
        assertThat(paraTypeDTO1).isNotEqualTo(paraTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(paraTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(paraTypeMapper.fromId(null)).isNull();
    }
}
