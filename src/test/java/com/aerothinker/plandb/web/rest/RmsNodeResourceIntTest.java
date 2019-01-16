package com.aerothinker.plandb.web.rest;

import com.aerothinker.plandb.PlandbApp;

import com.aerothinker.plandb.domain.RmsNode;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsUser;
import com.aerothinker.plandb.domain.RmsNode;
import com.aerothinker.plandb.domain.RmsRole;
import com.aerothinker.plandb.repository.RmsNodeRepository;
import com.aerothinker.plandb.repository.search.RmsNodeSearchRepository;
import com.aerothinker.plandb.service.RmsNodeService;
import com.aerothinker.plandb.service.dto.RmsNodeDTO;
import com.aerothinker.plandb.service.mapper.RmsNodeMapper;
import com.aerothinker.plandb.web.rest.errors.ExceptionTranslator;
import com.aerothinker.plandb.service.dto.RmsNodeCriteria;
import com.aerothinker.plandb.service.RmsNodeQueryService;

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
 * Test class for the RmsNodeResource REST controller.
 *
 * @see RmsNodeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlandbApp.class)
public class RmsNodeResourceIntTest {

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
    private RmsNodeRepository rmsNodeRepository;

    @Autowired
    private RmsNodeMapper rmsNodeMapper;

    @Autowired
    private RmsNodeService rmsNodeService;

    /**
     * This repository is mocked in the com.aerothinker.plandb.repository.search test package.
     *
     * @see com.aerothinker.plandb.repository.search.RmsNodeSearchRepositoryMockConfiguration
     */
    @Autowired
    private RmsNodeSearchRepository mockRmsNodeSearchRepository;

    @Autowired
    private RmsNodeQueryService rmsNodeQueryService;

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

    private MockMvc restRmsNodeMockMvc;

    private RmsNode rmsNode;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RmsNodeResource rmsNodeResource = new RmsNodeResource(rmsNodeService, rmsNodeQueryService);
        this.restRmsNodeMockMvc = MockMvcBuilders.standaloneSetup(rmsNodeResource)
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
    public static RmsNode createEntity(EntityManager em) {
        RmsNode rmsNode = new RmsNode()
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
        return rmsNode;
    }

    @Before
    public void initTest() {
        rmsNode = createEntity(em);
    }

    @Test
    @Transactional
    public void createRmsNode() throws Exception {
        int databaseSizeBeforeCreate = rmsNodeRepository.findAll().size();

        // Create the RmsNode
        RmsNodeDTO rmsNodeDTO = rmsNodeMapper.toDto(rmsNode);
        restRmsNodeMockMvc.perform(post("/api/rms-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsNodeDTO)))
            .andExpect(status().isCreated());

        // Validate the RmsNode in the database
        List<RmsNode> rmsNodeList = rmsNodeRepository.findAll();
        assertThat(rmsNodeList).hasSize(databaseSizeBeforeCreate + 1);
        RmsNode testRmsNode = rmsNodeList.get(rmsNodeList.size() - 1);
        assertThat(testRmsNode.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRmsNode.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testRmsNode.getSortString()).isEqualTo(DEFAULT_SORT_STRING);
        assertThat(testRmsNode.getDescString()).isEqualTo(DEFAULT_DESC_STRING);
        assertThat(testRmsNode.getImageBlob()).isEqualTo(DEFAULT_IMAGE_BLOB);
        assertThat(testRmsNode.getImageBlobContentType()).isEqualTo(DEFAULT_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testRmsNode.getImageBlobName()).isEqualTo(DEFAULT_IMAGE_BLOB_NAME);
        assertThat(testRmsNode.isUsingFlag()).isEqualTo(DEFAULT_USING_FLAG);
        assertThat(testRmsNode.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testRmsNode.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testRmsNode.getValidBegin()).isEqualTo(DEFAULT_VALID_BEGIN);
        assertThat(testRmsNode.getValidEnd()).isEqualTo(DEFAULT_VALID_END);
        assertThat(testRmsNode.getInsertTime()).isEqualTo(DEFAULT_INSERT_TIME);
        assertThat(testRmsNode.getUpdateTime()).isEqualTo(DEFAULT_UPDATE_TIME);
        assertThat(testRmsNode.getVerifyTime()).isEqualTo(DEFAULT_VERIFY_TIME);

        // Validate the RmsNode in Elasticsearch
        verify(mockRmsNodeSearchRepository, times(1)).save(testRmsNode);
    }

    @Test
    @Transactional
    public void createRmsNodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rmsNodeRepository.findAll().size();

        // Create the RmsNode with an existing ID
        rmsNode.setId(1L);
        RmsNodeDTO rmsNodeDTO = rmsNodeMapper.toDto(rmsNode);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRmsNodeMockMvc.perform(post("/api/rms-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsNodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RmsNode in the database
        List<RmsNode> rmsNodeList = rmsNodeRepository.findAll();
        assertThat(rmsNodeList).hasSize(databaseSizeBeforeCreate);

        // Validate the RmsNode in Elasticsearch
        verify(mockRmsNodeSearchRepository, times(0)).save(rmsNode);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = rmsNodeRepository.findAll().size();
        // set the field null
        rmsNode.setName(null);

        // Create the RmsNode, which fails.
        RmsNodeDTO rmsNodeDTO = rmsNodeMapper.toDto(rmsNode);

        restRmsNodeMockMvc.perform(post("/api/rms-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsNodeDTO)))
            .andExpect(status().isBadRequest());

        List<RmsNode> rmsNodeList = rmsNodeRepository.findAll();
        assertThat(rmsNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRmsNodes() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList
        restRmsNodeMockMvc.perform(get("/api/rms-nodes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsNode.getId().intValue())))
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
    public void getRmsNode() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get the rmsNode
        restRmsNodeMockMvc.perform(get("/api/rms-nodes/{id}", rmsNode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rmsNode.getId().intValue()))
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
    public void getAllRmsNodesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where name equals to DEFAULT_NAME
        defaultRmsNodeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the rmsNodeList where name equals to UPDATED_NAME
        defaultRmsNodeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultRmsNodeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the rmsNodeList where name equals to UPDATED_NAME
        defaultRmsNodeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where name is not null
        defaultRmsNodeShouldBeFound("name.specified=true");

        // Get all the rmsNodeList where name is null
        defaultRmsNodeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesBySerialNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where serialNumber equals to DEFAULT_SERIAL_NUMBER
        defaultRmsNodeShouldBeFound("serialNumber.equals=" + DEFAULT_SERIAL_NUMBER);

        // Get all the rmsNodeList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultRmsNodeShouldNotBeFound("serialNumber.equals=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllRmsNodesBySerialNumberIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where serialNumber in DEFAULT_SERIAL_NUMBER or UPDATED_SERIAL_NUMBER
        defaultRmsNodeShouldBeFound("serialNumber.in=" + DEFAULT_SERIAL_NUMBER + "," + UPDATED_SERIAL_NUMBER);

        // Get all the rmsNodeList where serialNumber equals to UPDATED_SERIAL_NUMBER
        defaultRmsNodeShouldNotBeFound("serialNumber.in=" + UPDATED_SERIAL_NUMBER);
    }

    @Test
    @Transactional
    public void getAllRmsNodesBySerialNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where serialNumber is not null
        defaultRmsNodeShouldBeFound("serialNumber.specified=true");

        // Get all the rmsNodeList where serialNumber is null
        defaultRmsNodeShouldNotBeFound("serialNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesBySortStringIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where sortString equals to DEFAULT_SORT_STRING
        defaultRmsNodeShouldBeFound("sortString.equals=" + DEFAULT_SORT_STRING);

        // Get all the rmsNodeList where sortString equals to UPDATED_SORT_STRING
        defaultRmsNodeShouldNotBeFound("sortString.equals=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllRmsNodesBySortStringIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where sortString in DEFAULT_SORT_STRING or UPDATED_SORT_STRING
        defaultRmsNodeShouldBeFound("sortString.in=" + DEFAULT_SORT_STRING + "," + UPDATED_SORT_STRING);

        // Get all the rmsNodeList where sortString equals to UPDATED_SORT_STRING
        defaultRmsNodeShouldNotBeFound("sortString.in=" + UPDATED_SORT_STRING);
    }

    @Test
    @Transactional
    public void getAllRmsNodesBySortStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where sortString is not null
        defaultRmsNodeShouldBeFound("sortString.specified=true");

        // Get all the rmsNodeList where sortString is null
        defaultRmsNodeShouldNotBeFound("sortString.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesByDescStringIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where descString equals to DEFAULT_DESC_STRING
        defaultRmsNodeShouldBeFound("descString.equals=" + DEFAULT_DESC_STRING);

        // Get all the rmsNodeList where descString equals to UPDATED_DESC_STRING
        defaultRmsNodeShouldNotBeFound("descString.equals=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByDescStringIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where descString in DEFAULT_DESC_STRING or UPDATED_DESC_STRING
        defaultRmsNodeShouldBeFound("descString.in=" + DEFAULT_DESC_STRING + "," + UPDATED_DESC_STRING);

        // Get all the rmsNodeList where descString equals to UPDATED_DESC_STRING
        defaultRmsNodeShouldNotBeFound("descString.in=" + UPDATED_DESC_STRING);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByDescStringIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where descString is not null
        defaultRmsNodeShouldBeFound("descString.specified=true");

        // Get all the rmsNodeList where descString is null
        defaultRmsNodeShouldNotBeFound("descString.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesByImageBlobNameIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where imageBlobName equals to DEFAULT_IMAGE_BLOB_NAME
        defaultRmsNodeShouldBeFound("imageBlobName.equals=" + DEFAULT_IMAGE_BLOB_NAME);

        // Get all the rmsNodeList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultRmsNodeShouldNotBeFound("imageBlobName.equals=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByImageBlobNameIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where imageBlobName in DEFAULT_IMAGE_BLOB_NAME or UPDATED_IMAGE_BLOB_NAME
        defaultRmsNodeShouldBeFound("imageBlobName.in=" + DEFAULT_IMAGE_BLOB_NAME + "," + UPDATED_IMAGE_BLOB_NAME);

        // Get all the rmsNodeList where imageBlobName equals to UPDATED_IMAGE_BLOB_NAME
        defaultRmsNodeShouldNotBeFound("imageBlobName.in=" + UPDATED_IMAGE_BLOB_NAME);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByImageBlobNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where imageBlobName is not null
        defaultRmsNodeShouldBeFound("imageBlobName.specified=true");

        // Get all the rmsNodeList where imageBlobName is null
        defaultRmsNodeShouldNotBeFound("imageBlobName.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesByUsingFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where usingFlag equals to DEFAULT_USING_FLAG
        defaultRmsNodeShouldBeFound("usingFlag.equals=" + DEFAULT_USING_FLAG);

        // Get all the rmsNodeList where usingFlag equals to UPDATED_USING_FLAG
        defaultRmsNodeShouldNotBeFound("usingFlag.equals=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByUsingFlagIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where usingFlag in DEFAULT_USING_FLAG or UPDATED_USING_FLAG
        defaultRmsNodeShouldBeFound("usingFlag.in=" + DEFAULT_USING_FLAG + "," + UPDATED_USING_FLAG);

        // Get all the rmsNodeList where usingFlag equals to UPDATED_USING_FLAG
        defaultRmsNodeShouldNotBeFound("usingFlag.in=" + UPDATED_USING_FLAG);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByUsingFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where usingFlag is not null
        defaultRmsNodeShouldBeFound("usingFlag.specified=true");

        // Get all the rmsNodeList where usingFlag is null
        defaultRmsNodeShouldNotBeFound("usingFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where remarks equals to DEFAULT_REMARKS
        defaultRmsNodeShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the rmsNodeList where remarks equals to UPDATED_REMARKS
        defaultRmsNodeShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultRmsNodeShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the rmsNodeList where remarks equals to UPDATED_REMARKS
        defaultRmsNodeShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where remarks is not null
        defaultRmsNodeShouldBeFound("remarks.specified=true");

        // Get all the rmsNodeList where remarks is null
        defaultRmsNodeShouldNotBeFound("remarks.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesByValidTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where validType equals to DEFAULT_VALID_TYPE
        defaultRmsNodeShouldBeFound("validType.equals=" + DEFAULT_VALID_TYPE);

        // Get all the rmsNodeList where validType equals to UPDATED_VALID_TYPE
        defaultRmsNodeShouldNotBeFound("validType.equals=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByValidTypeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where validType in DEFAULT_VALID_TYPE or UPDATED_VALID_TYPE
        defaultRmsNodeShouldBeFound("validType.in=" + DEFAULT_VALID_TYPE + "," + UPDATED_VALID_TYPE);

        // Get all the rmsNodeList where validType equals to UPDATED_VALID_TYPE
        defaultRmsNodeShouldNotBeFound("validType.in=" + UPDATED_VALID_TYPE);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByValidTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where validType is not null
        defaultRmsNodeShouldBeFound("validType.specified=true");

        // Get all the rmsNodeList where validType is null
        defaultRmsNodeShouldNotBeFound("validType.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesByValidBeginIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where validBegin equals to DEFAULT_VALID_BEGIN
        defaultRmsNodeShouldBeFound("validBegin.equals=" + DEFAULT_VALID_BEGIN);

        // Get all the rmsNodeList where validBegin equals to UPDATED_VALID_BEGIN
        defaultRmsNodeShouldNotBeFound("validBegin.equals=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByValidBeginIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where validBegin in DEFAULT_VALID_BEGIN or UPDATED_VALID_BEGIN
        defaultRmsNodeShouldBeFound("validBegin.in=" + DEFAULT_VALID_BEGIN + "," + UPDATED_VALID_BEGIN);

        // Get all the rmsNodeList where validBegin equals to UPDATED_VALID_BEGIN
        defaultRmsNodeShouldNotBeFound("validBegin.in=" + UPDATED_VALID_BEGIN);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByValidBeginIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where validBegin is not null
        defaultRmsNodeShouldBeFound("validBegin.specified=true");

        // Get all the rmsNodeList where validBegin is null
        defaultRmsNodeShouldNotBeFound("validBegin.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesByValidEndIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where validEnd equals to DEFAULT_VALID_END
        defaultRmsNodeShouldBeFound("validEnd.equals=" + DEFAULT_VALID_END);

        // Get all the rmsNodeList where validEnd equals to UPDATED_VALID_END
        defaultRmsNodeShouldNotBeFound("validEnd.equals=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByValidEndIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where validEnd in DEFAULT_VALID_END or UPDATED_VALID_END
        defaultRmsNodeShouldBeFound("validEnd.in=" + DEFAULT_VALID_END + "," + UPDATED_VALID_END);

        // Get all the rmsNodeList where validEnd equals to UPDATED_VALID_END
        defaultRmsNodeShouldNotBeFound("validEnd.in=" + UPDATED_VALID_END);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByValidEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where validEnd is not null
        defaultRmsNodeShouldBeFound("validEnd.specified=true");

        // Get all the rmsNodeList where validEnd is null
        defaultRmsNodeShouldNotBeFound("validEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesByInsertTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where insertTime equals to DEFAULT_INSERT_TIME
        defaultRmsNodeShouldBeFound("insertTime.equals=" + DEFAULT_INSERT_TIME);

        // Get all the rmsNodeList where insertTime equals to UPDATED_INSERT_TIME
        defaultRmsNodeShouldNotBeFound("insertTime.equals=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByInsertTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where insertTime in DEFAULT_INSERT_TIME or UPDATED_INSERT_TIME
        defaultRmsNodeShouldBeFound("insertTime.in=" + DEFAULT_INSERT_TIME + "," + UPDATED_INSERT_TIME);

        // Get all the rmsNodeList where insertTime equals to UPDATED_INSERT_TIME
        defaultRmsNodeShouldNotBeFound("insertTime.in=" + UPDATED_INSERT_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByInsertTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where insertTime is not null
        defaultRmsNodeShouldBeFound("insertTime.specified=true");

        // Get all the rmsNodeList where insertTime is null
        defaultRmsNodeShouldNotBeFound("insertTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesByUpdateTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where updateTime equals to DEFAULT_UPDATE_TIME
        defaultRmsNodeShouldBeFound("updateTime.equals=" + DEFAULT_UPDATE_TIME);

        // Get all the rmsNodeList where updateTime equals to UPDATED_UPDATE_TIME
        defaultRmsNodeShouldNotBeFound("updateTime.equals=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByUpdateTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where updateTime in DEFAULT_UPDATE_TIME or UPDATED_UPDATE_TIME
        defaultRmsNodeShouldBeFound("updateTime.in=" + DEFAULT_UPDATE_TIME + "," + UPDATED_UPDATE_TIME);

        // Get all the rmsNodeList where updateTime equals to UPDATED_UPDATE_TIME
        defaultRmsNodeShouldNotBeFound("updateTime.in=" + UPDATED_UPDATE_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByUpdateTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where updateTime is not null
        defaultRmsNodeShouldBeFound("updateTime.specified=true");

        // Get all the rmsNodeList where updateTime is null
        defaultRmsNodeShouldNotBeFound("updateTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesByVerifyTimeIsEqualToSomething() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where verifyTime equals to DEFAULT_VERIFY_TIME
        defaultRmsNodeShouldBeFound("verifyTime.equals=" + DEFAULT_VERIFY_TIME);

        // Get all the rmsNodeList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultRmsNodeShouldNotBeFound("verifyTime.equals=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByVerifyTimeIsInShouldWork() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where verifyTime in DEFAULT_VERIFY_TIME or UPDATED_VERIFY_TIME
        defaultRmsNodeShouldBeFound("verifyTime.in=" + DEFAULT_VERIFY_TIME + "," + UPDATED_VERIFY_TIME);

        // Get all the rmsNodeList where verifyTime equals to UPDATED_VERIFY_TIME
        defaultRmsNodeShouldNotBeFound("verifyTime.in=" + UPDATED_VERIFY_TIME);
    }

    @Test
    @Transactional
    public void getAllRmsNodesByVerifyTimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        // Get all the rmsNodeList where verifyTime is not null
        defaultRmsNodeShouldBeFound("verifyTime.specified=true");

        // Get all the rmsNodeList where verifyTime is null
        defaultRmsNodeShouldNotBeFound("verifyTime.specified=false");
    }

    @Test
    @Transactional
    public void getAllRmsNodesByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser createdBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(createdBy);
        em.flush();
        rmsNode.setCreatedBy(createdBy);
        rmsNodeRepository.saveAndFlush(rmsNode);
        Long createdById = createdBy.getId();

        // Get all the rmsNodeList where createdBy equals to createdById
        defaultRmsNodeShouldBeFound("createdById.equals=" + createdById);

        // Get all the rmsNodeList where createdBy equals to createdById + 1
        defaultRmsNodeShouldNotBeFound("createdById.equals=" + (createdById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsNodesByModifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser modifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(modifiedBy);
        em.flush();
        rmsNode.setModifiedBy(modifiedBy);
        rmsNodeRepository.saveAndFlush(rmsNode);
        Long modifiedById = modifiedBy.getId();

        // Get all the rmsNodeList where modifiedBy equals to modifiedById
        defaultRmsNodeShouldBeFound("modifiedById.equals=" + modifiedById);

        // Get all the rmsNodeList where modifiedBy equals to modifiedById + 1
        defaultRmsNodeShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsNodesByVerifiedByIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsUser verifiedBy = RmsUserResourceIntTest.createEntity(em);
        em.persist(verifiedBy);
        em.flush();
        rmsNode.setVerifiedBy(verifiedBy);
        rmsNodeRepository.saveAndFlush(rmsNode);
        Long verifiedById = verifiedBy.getId();

        // Get all the rmsNodeList where verifiedBy equals to verifiedById
        defaultRmsNodeShouldBeFound("verifiedById.equals=" + verifiedById);

        // Get all the rmsNodeList where verifiedBy equals to verifiedById + 1
        defaultRmsNodeShouldNotBeFound("verifiedById.equals=" + (verifiedById + 1));
    }


    @Test
    @Transactional
    public void getAllRmsNodesByParentIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsNode parent = RmsNodeResourceIntTest.createEntity(em);
        em.persist(parent);
        em.flush();
        rmsNode.setParent(parent);
        rmsNodeRepository.saveAndFlush(rmsNode);
        Long parentId = parent.getId();

        // Get all the rmsNodeList where parent equals to parentId
        defaultRmsNodeShouldBeFound("parentId.equals=" + parentId);

        // Get all the rmsNodeList where parent equals to parentId + 1
        defaultRmsNodeShouldNotBeFound("parentId.equals=" + (parentId + 1));
    }


    @Test
    @Transactional
    public void getAllRmsNodesByRmsRoleIsEqualToSomething() throws Exception {
        // Initialize the database
        RmsRole rmsRole = RmsRoleResourceIntTest.createEntity(em);
        em.persist(rmsRole);
        em.flush();
        rmsNode.addRmsRole(rmsRole);
        rmsNodeRepository.saveAndFlush(rmsNode);
        Long rmsRoleId = rmsRole.getId();

        // Get all the rmsNodeList where rmsRole equals to rmsRoleId
        defaultRmsNodeShouldBeFound("rmsRoleId.equals=" + rmsRoleId);

        // Get all the rmsNodeList where rmsRole equals to rmsRoleId + 1
        defaultRmsNodeShouldNotBeFound("rmsRoleId.equals=" + (rmsRoleId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultRmsNodeShouldBeFound(String filter) throws Exception {
        restRmsNodeMockMvc.perform(get("/api/rms-nodes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsNode.getId().intValue())))
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
        restRmsNodeMockMvc.perform(get("/api/rms-nodes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultRmsNodeShouldNotBeFound(String filter) throws Exception {
        restRmsNodeMockMvc.perform(get("/api/rms-nodes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRmsNodeMockMvc.perform(get("/api/rms-nodes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingRmsNode() throws Exception {
        // Get the rmsNode
        restRmsNodeMockMvc.perform(get("/api/rms-nodes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRmsNode() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        int databaseSizeBeforeUpdate = rmsNodeRepository.findAll().size();

        // Update the rmsNode
        RmsNode updatedRmsNode = rmsNodeRepository.findById(rmsNode.getId()).get();
        // Disconnect from session so that the updates on updatedRmsNode are not directly saved in db
        em.detach(updatedRmsNode);
        updatedRmsNode
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
        RmsNodeDTO rmsNodeDTO = rmsNodeMapper.toDto(updatedRmsNode);

        restRmsNodeMockMvc.perform(put("/api/rms-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsNodeDTO)))
            .andExpect(status().isOk());

        // Validate the RmsNode in the database
        List<RmsNode> rmsNodeList = rmsNodeRepository.findAll();
        assertThat(rmsNodeList).hasSize(databaseSizeBeforeUpdate);
        RmsNode testRmsNode = rmsNodeList.get(rmsNodeList.size() - 1);
        assertThat(testRmsNode.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRmsNode.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testRmsNode.getSortString()).isEqualTo(UPDATED_SORT_STRING);
        assertThat(testRmsNode.getDescString()).isEqualTo(UPDATED_DESC_STRING);
        assertThat(testRmsNode.getImageBlob()).isEqualTo(UPDATED_IMAGE_BLOB);
        assertThat(testRmsNode.getImageBlobContentType()).isEqualTo(UPDATED_IMAGE_BLOB_CONTENT_TYPE);
        assertThat(testRmsNode.getImageBlobName()).isEqualTo(UPDATED_IMAGE_BLOB_NAME);
        assertThat(testRmsNode.isUsingFlag()).isEqualTo(UPDATED_USING_FLAG);
        assertThat(testRmsNode.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testRmsNode.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testRmsNode.getValidBegin()).isEqualTo(UPDATED_VALID_BEGIN);
        assertThat(testRmsNode.getValidEnd()).isEqualTo(UPDATED_VALID_END);
        assertThat(testRmsNode.getInsertTime()).isEqualTo(UPDATED_INSERT_TIME);
        assertThat(testRmsNode.getUpdateTime()).isEqualTo(UPDATED_UPDATE_TIME);
        assertThat(testRmsNode.getVerifyTime()).isEqualTo(UPDATED_VERIFY_TIME);

        // Validate the RmsNode in Elasticsearch
        verify(mockRmsNodeSearchRepository, times(1)).save(testRmsNode);
    }

    @Test
    @Transactional
    public void updateNonExistingRmsNode() throws Exception {
        int databaseSizeBeforeUpdate = rmsNodeRepository.findAll().size();

        // Create the RmsNode
        RmsNodeDTO rmsNodeDTO = rmsNodeMapper.toDto(rmsNode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRmsNodeMockMvc.perform(put("/api/rms-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rmsNodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RmsNode in the database
        List<RmsNode> rmsNodeList = rmsNodeRepository.findAll();
        assertThat(rmsNodeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the RmsNode in Elasticsearch
        verify(mockRmsNodeSearchRepository, times(0)).save(rmsNode);
    }

    @Test
    @Transactional
    public void deleteRmsNode() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);

        int databaseSizeBeforeDelete = rmsNodeRepository.findAll().size();

        // Get the rmsNode
        restRmsNodeMockMvc.perform(delete("/api/rms-nodes/{id}", rmsNode.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RmsNode> rmsNodeList = rmsNodeRepository.findAll();
        assertThat(rmsNodeList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the RmsNode in Elasticsearch
        verify(mockRmsNodeSearchRepository, times(1)).deleteById(rmsNode.getId());
    }

    @Test
    @Transactional
    public void searchRmsNode() throws Exception {
        // Initialize the database
        rmsNodeRepository.saveAndFlush(rmsNode);
        when(mockRmsNodeSearchRepository.search(queryStringQuery("id:" + rmsNode.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(rmsNode), PageRequest.of(0, 1), 1));
        // Search the rmsNode
        restRmsNodeMockMvc.perform(get("/api/_search/rms-nodes?query=id:" + rmsNode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rmsNode.getId().intValue())))
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
        TestUtil.equalsVerifier(RmsNode.class);
        RmsNode rmsNode1 = new RmsNode();
        rmsNode1.setId(1L);
        RmsNode rmsNode2 = new RmsNode();
        rmsNode2.setId(rmsNode1.getId());
        assertThat(rmsNode1).isEqualTo(rmsNode2);
        rmsNode2.setId(2L);
        assertThat(rmsNode1).isNotEqualTo(rmsNode2);
        rmsNode1.setId(null);
        assertThat(rmsNode1).isNotEqualTo(rmsNode2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RmsNodeDTO.class);
        RmsNodeDTO rmsNodeDTO1 = new RmsNodeDTO();
        rmsNodeDTO1.setId(1L);
        RmsNodeDTO rmsNodeDTO2 = new RmsNodeDTO();
        assertThat(rmsNodeDTO1).isNotEqualTo(rmsNodeDTO2);
        rmsNodeDTO2.setId(rmsNodeDTO1.getId());
        assertThat(rmsNodeDTO1).isEqualTo(rmsNodeDTO2);
        rmsNodeDTO2.setId(2L);
        assertThat(rmsNodeDTO1).isNotEqualTo(rmsNodeDTO2);
        rmsNodeDTO1.setId(null);
        assertThat(rmsNodeDTO1).isNotEqualTo(rmsNodeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rmsNodeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rmsNodeMapper.fromId(null)).isNull();
    }
}
