package com.aerothinker.plandb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.aerothinker.plandb.domain.enumeration.ValidType;

import com.aerothinker.plandb.domain.enumeration.ViewPermission;

/**
 * PlanInfo 预案数据历史表
 * @author JungleYang
 */
@ApiModel(description = "PlanInfo 预案数据历史表 @author JungleYang")
@Entity
@Table(name = "plan_info_data_his")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "planinfodatahis")
public class PlanInfoDataHis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 预案名称
     */
    @NotNull
    @Size(max = 256)
    @ApiModelProperty(value = "预案名称", required = true)
    @Column(name = "name", length = 256, nullable = false)
    private String name;

    /**
     * 序号
     */
    @Size(max = 10)
    @ApiModelProperty(value = "序号")
    @Column(name = "sort_string", length = 10)
    private String sortString;

    /**
     * 描述
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "描述")
    @Column(name = "desc_string", length = 4000)
    private String descString;

    /**
     * json格式数据
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "json格式数据")
    @Column(name = "json_string", length = 4000)
    private String jsonString;

    /**
     * 备注
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "备注")
    @Column(name = "remarks", length = 4000)
    private String remarks;

    /**
     * 相关事件
     */
    @Size(max = 512)
    @ApiModelProperty(value = "相关事件")
    @Column(name = "ref_event", length = 512)
    private String refEvent;

    /**
     * 附件路径
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "附件路径")
    @Column(name = "attachment_path", length = 4000)
    private String attachmentPath;

    /**
     * 附件
     */
    @ApiModelProperty(value = "附件")
    @Lob
    @Column(name = "attachment_blob")
    private byte[] attachmentBlob;

    @Column(name = "attachment_blob_content_type")
    private String attachmentBlobContentType;

    /**
     * 附件名称
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "附件名称")
    @Column(name = "attachment_name", length = 4000)
    private String attachmentName;

    /**
     * 文本附件
     */
    @ApiModelProperty(value = "文本附件")
    @Lob
    @Column(name = "text_blob")
    private String textBlob;

    /**
     * 图片附件
     */
    @ApiModelProperty(value = "图片附件")
    @Lob
    @Column(name = "image_blob")
    private byte[] imageBlob;

    @Column(name = "image_blob_content_type")
    private String imageBlobContentType;

    /**
     * 附件名称
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "附件名称")
    @Column(name = "image_blob_name", length = 4000)
    private String imageBlobName;

    /**
     * 是否使用
     */
    @ApiModelProperty(value = "是否使用")
    @Column(name = "using_flag")
    private Boolean usingFlag;

    /**
     * 有效类型
     */
    @ApiModelProperty(value = "有效类型")
    @Enumerated(EnumType.STRING)
    @Column(name = "valid_type")
    private ValidType validType;

    /**
     * 生效开始时间
     */
    @ApiModelProperty(value = "生效开始时间")
    @Column(name = "valid_begin")
    private Instant validBegin;

    /**
     * 有效截止时间
     */
    @ApiModelProperty(value = "有效截止时间")
    @Column(name = "valid_end")
    private Instant validEnd;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "insert_time")
    private Instant insertTime;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    @Column(name = "update_time")
    private Instant updateTime;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    @Column(name = "verify_time")
    private Instant verifyTime;

    /**
     * 审核需要
     */
    @ApiModelProperty(value = "审核需要")
    @Column(name = "verify_need")
    private Boolean verifyNeed;

    /**
     * 审核意见
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "审核意见")
    @Column(name = "verify_opinion", length = 4000)
    private String verifyOpinion;

    /**
     * 查看次数
     */
    @ApiModelProperty(value = "查看次数")
    @Column(name = "view_count")
    private Integer viewCount;

    /**
     * 查看权限类别
     */
    @ApiModelProperty(value = "查看权限类别")
    @Enumerated(EnumType.STRING)
    @Column(name = "view_permission")
    private ViewPermission viewPermission;

    /**
     * 查看权限人员Json
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "查看权限人员Json")
    @Column(name = "view_perm_persion", length = 4000)
    private String viewPermPersion;

    /**
     * 编号
     */
    @Size(max = 256)
    @ApiModelProperty(value = "编号")
    @Column(name = "serial_number", length = 256)
    private String serialNumber;

    /**
     * 版本号
     */
    @Size(max = 256)
    @ApiModelProperty(value = "版本号")
    @Column(name = "version_number", length = 256)
    private String versionNumber;

    /**
     * 来源相关信息
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "来源相关信息")
    @Column(name = "para_source_string", length = 4000)
    private String paraSourceString;

    /**
     * 特征关键词
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "特征关键词")
    @Column(name = "feature_keyword", length = 4000)
    private String featureKeyword;

    /**
     * 我的建议
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "我的建议")
    @Column(name = "suggestion", length = 4000)
    private String suggestion;

    /**
     * 发布范围
     */
    @Size(max = 4000)
    @ApiModelProperty(value = "发布范围")
    @Column(name = "release_scope", length = 4000)
    private String releaseScope;

    /**
     * 当前步骤
     */
    @ApiModelProperty(value = "当前步骤")
    @Column(name = "current_step_order")
    private Integer currentStepOrder;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VerifyRec verifyRec;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ParaType paraType;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ParaClass paraClass;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ParaCat paraCat;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ParaState paraState;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ParaSource paraSource;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ParaAttr paraAttr;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ParaProp paraProp;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsUser createdBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsDep createdDepBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsUser ownerBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsDep ownerDepBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsUser modifiedBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsDep modifiedDepBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsUser verifiedBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsDep verifiedDepBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsUser publishedBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsDep publishedDepBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PlanInfoDataHis parent;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PlanInfo planInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PlanInfoDataHis name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortString() {
        return sortString;
    }

    public PlanInfoDataHis sortString(String sortString) {
        this.sortString = sortString;
        return this;
    }

    public void setSortString(String sortString) {
        this.sortString = sortString;
    }

    public String getDescString() {
        return descString;
    }

    public PlanInfoDataHis descString(String descString) {
        this.descString = descString;
        return this;
    }

    public void setDescString(String descString) {
        this.descString = descString;
    }

    public String getJsonString() {
        return jsonString;
    }

    public PlanInfoDataHis jsonString(String jsonString) {
        this.jsonString = jsonString;
        return this;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getRemarks() {
        return remarks;
    }

    public PlanInfoDataHis remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRefEvent() {
        return refEvent;
    }

    public PlanInfoDataHis refEvent(String refEvent) {
        this.refEvent = refEvent;
        return this;
    }

    public void setRefEvent(String refEvent) {
        this.refEvent = refEvent;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public PlanInfoDataHis attachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
        return this;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public byte[] getAttachmentBlob() {
        return attachmentBlob;
    }

    public PlanInfoDataHis attachmentBlob(byte[] attachmentBlob) {
        this.attachmentBlob = attachmentBlob;
        return this;
    }

    public void setAttachmentBlob(byte[] attachmentBlob) {
        this.attachmentBlob = attachmentBlob;
    }

    public String getAttachmentBlobContentType() {
        return attachmentBlobContentType;
    }

    public PlanInfoDataHis attachmentBlobContentType(String attachmentBlobContentType) {
        this.attachmentBlobContentType = attachmentBlobContentType;
        return this;
    }

    public void setAttachmentBlobContentType(String attachmentBlobContentType) {
        this.attachmentBlobContentType = attachmentBlobContentType;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public PlanInfoDataHis attachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
        return this;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getTextBlob() {
        return textBlob;
    }

    public PlanInfoDataHis textBlob(String textBlob) {
        this.textBlob = textBlob;
        return this;
    }

    public void setTextBlob(String textBlob) {
        this.textBlob = textBlob;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public PlanInfoDataHis imageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
        return this;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }

    public String getImageBlobContentType() {
        return imageBlobContentType;
    }

    public PlanInfoDataHis imageBlobContentType(String imageBlobContentType) {
        this.imageBlobContentType = imageBlobContentType;
        return this;
    }

    public void setImageBlobContentType(String imageBlobContentType) {
        this.imageBlobContentType = imageBlobContentType;
    }

    public String getImageBlobName() {
        return imageBlobName;
    }

    public PlanInfoDataHis imageBlobName(String imageBlobName) {
        this.imageBlobName = imageBlobName;
        return this;
    }

    public void setImageBlobName(String imageBlobName) {
        this.imageBlobName = imageBlobName;
    }

    public Boolean isUsingFlag() {
        return usingFlag;
    }

    public PlanInfoDataHis usingFlag(Boolean usingFlag) {
        this.usingFlag = usingFlag;
        return this;
    }

    public void setUsingFlag(Boolean usingFlag) {
        this.usingFlag = usingFlag;
    }

    public ValidType getValidType() {
        return validType;
    }

    public PlanInfoDataHis validType(ValidType validType) {
        this.validType = validType;
        return this;
    }

    public void setValidType(ValidType validType) {
        this.validType = validType;
    }

    public Instant getValidBegin() {
        return validBegin;
    }

    public PlanInfoDataHis validBegin(Instant validBegin) {
        this.validBegin = validBegin;
        return this;
    }

    public void setValidBegin(Instant validBegin) {
        this.validBegin = validBegin;
    }

    public Instant getValidEnd() {
        return validEnd;
    }

    public PlanInfoDataHis validEnd(Instant validEnd) {
        this.validEnd = validEnd;
        return this;
    }

    public void setValidEnd(Instant validEnd) {
        this.validEnd = validEnd;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public PlanInfoDataHis insertTime(Instant insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public PlanInfoDataHis updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Instant getVerifyTime() {
        return verifyTime;
    }

    public PlanInfoDataHis verifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
        return this;
    }

    public void setVerifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Boolean isVerifyNeed() {
        return verifyNeed;
    }

    public PlanInfoDataHis verifyNeed(Boolean verifyNeed) {
        this.verifyNeed = verifyNeed;
        return this;
    }

    public void setVerifyNeed(Boolean verifyNeed) {
        this.verifyNeed = verifyNeed;
    }

    public String getVerifyOpinion() {
        return verifyOpinion;
    }

    public PlanInfoDataHis verifyOpinion(String verifyOpinion) {
        this.verifyOpinion = verifyOpinion;
        return this;
    }

    public void setVerifyOpinion(String verifyOpinion) {
        this.verifyOpinion = verifyOpinion;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public PlanInfoDataHis viewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public ViewPermission getViewPermission() {
        return viewPermission;
    }

    public PlanInfoDataHis viewPermission(ViewPermission viewPermission) {
        this.viewPermission = viewPermission;
        return this;
    }

    public void setViewPermission(ViewPermission viewPermission) {
        this.viewPermission = viewPermission;
    }

    public String getViewPermPersion() {
        return viewPermPersion;
    }

    public PlanInfoDataHis viewPermPersion(String viewPermPersion) {
        this.viewPermPersion = viewPermPersion;
        return this;
    }

    public void setViewPermPersion(String viewPermPersion) {
        this.viewPermPersion = viewPermPersion;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public PlanInfoDataHis serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public PlanInfoDataHis versionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
        return this;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getParaSourceString() {
        return paraSourceString;
    }

    public PlanInfoDataHis paraSourceString(String paraSourceString) {
        this.paraSourceString = paraSourceString;
        return this;
    }

    public void setParaSourceString(String paraSourceString) {
        this.paraSourceString = paraSourceString;
    }

    public String getFeatureKeyword() {
        return featureKeyword;
    }

    public PlanInfoDataHis featureKeyword(String featureKeyword) {
        this.featureKeyword = featureKeyword;
        return this;
    }

    public void setFeatureKeyword(String featureKeyword) {
        this.featureKeyword = featureKeyword;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public PlanInfoDataHis suggestion(String suggestion) {
        this.suggestion = suggestion;
        return this;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getReleaseScope() {
        return releaseScope;
    }

    public PlanInfoDataHis releaseScope(String releaseScope) {
        this.releaseScope = releaseScope;
        return this;
    }

    public void setReleaseScope(String releaseScope) {
        this.releaseScope = releaseScope;
    }

    public Integer getCurrentStepOrder() {
        return currentStepOrder;
    }

    public PlanInfoDataHis currentStepOrder(Integer currentStepOrder) {
        this.currentStepOrder = currentStepOrder;
        return this;
    }

    public void setCurrentStepOrder(Integer currentStepOrder) {
        this.currentStepOrder = currentStepOrder;
    }

    public VerifyRec getVerifyRec() {
        return verifyRec;
    }

    public PlanInfoDataHis verifyRec(VerifyRec verifyRec) {
        this.verifyRec = verifyRec;
        return this;
    }

    public void setVerifyRec(VerifyRec verifyRec) {
        this.verifyRec = verifyRec;
    }

    public ParaType getParaType() {
        return paraType;
    }

    public PlanInfoDataHis paraType(ParaType paraType) {
        this.paraType = paraType;
        return this;
    }

    public void setParaType(ParaType paraType) {
        this.paraType = paraType;
    }

    public ParaClass getParaClass() {
        return paraClass;
    }

    public PlanInfoDataHis paraClass(ParaClass paraClass) {
        this.paraClass = paraClass;
        return this;
    }

    public void setParaClass(ParaClass paraClass) {
        this.paraClass = paraClass;
    }

    public ParaCat getParaCat() {
        return paraCat;
    }

    public PlanInfoDataHis paraCat(ParaCat paraCat) {
        this.paraCat = paraCat;
        return this;
    }

    public void setParaCat(ParaCat paraCat) {
        this.paraCat = paraCat;
    }

    public ParaState getParaState() {
        return paraState;
    }

    public PlanInfoDataHis paraState(ParaState paraState) {
        this.paraState = paraState;
        return this;
    }

    public void setParaState(ParaState paraState) {
        this.paraState = paraState;
    }

    public ParaSource getParaSource() {
        return paraSource;
    }

    public PlanInfoDataHis paraSource(ParaSource paraSource) {
        this.paraSource = paraSource;
        return this;
    }

    public void setParaSource(ParaSource paraSource) {
        this.paraSource = paraSource;
    }

    public ParaAttr getParaAttr() {
        return paraAttr;
    }

    public PlanInfoDataHis paraAttr(ParaAttr paraAttr) {
        this.paraAttr = paraAttr;
        return this;
    }

    public void setParaAttr(ParaAttr paraAttr) {
        this.paraAttr = paraAttr;
    }

    public ParaProp getParaProp() {
        return paraProp;
    }

    public PlanInfoDataHis paraProp(ParaProp paraProp) {
        this.paraProp = paraProp;
        return this;
    }

    public void setParaProp(ParaProp paraProp) {
        this.paraProp = paraProp;
    }

    public RmsUser getCreatedBy() {
        return createdBy;
    }

    public PlanInfoDataHis createdBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
        return this;
    }

    public void setCreatedBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
    }

    public RmsDep getCreatedDepBy() {
        return createdDepBy;
    }

    public PlanInfoDataHis createdDepBy(RmsDep rmsDep) {
        this.createdDepBy = rmsDep;
        return this;
    }

    public void setCreatedDepBy(RmsDep rmsDep) {
        this.createdDepBy = rmsDep;
    }

    public RmsUser getOwnerBy() {
        return ownerBy;
    }

    public PlanInfoDataHis ownerBy(RmsUser rmsUser) {
        this.ownerBy = rmsUser;
        return this;
    }

    public void setOwnerBy(RmsUser rmsUser) {
        this.ownerBy = rmsUser;
    }

    public RmsDep getOwnerDepBy() {
        return ownerDepBy;
    }

    public PlanInfoDataHis ownerDepBy(RmsDep rmsDep) {
        this.ownerDepBy = rmsDep;
        return this;
    }

    public void setOwnerDepBy(RmsDep rmsDep) {
        this.ownerDepBy = rmsDep;
    }

    public RmsUser getModifiedBy() {
        return modifiedBy;
    }

    public PlanInfoDataHis modifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
        return this;
    }

    public void setModifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
    }

    public RmsDep getModifiedDepBy() {
        return modifiedDepBy;
    }

    public PlanInfoDataHis modifiedDepBy(RmsDep rmsDep) {
        this.modifiedDepBy = rmsDep;
        return this;
    }

    public void setModifiedDepBy(RmsDep rmsDep) {
        this.modifiedDepBy = rmsDep;
    }

    public RmsUser getVerifiedBy() {
        return verifiedBy;
    }

    public PlanInfoDataHis verifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
        return this;
    }

    public void setVerifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
    }

    public RmsDep getVerifiedDepBy() {
        return verifiedDepBy;
    }

    public PlanInfoDataHis verifiedDepBy(RmsDep rmsDep) {
        this.verifiedDepBy = rmsDep;
        return this;
    }

    public void setVerifiedDepBy(RmsDep rmsDep) {
        this.verifiedDepBy = rmsDep;
    }

    public RmsUser getPublishedBy() {
        return publishedBy;
    }

    public PlanInfoDataHis publishedBy(RmsUser rmsUser) {
        this.publishedBy = rmsUser;
        return this;
    }

    public void setPublishedBy(RmsUser rmsUser) {
        this.publishedBy = rmsUser;
    }

    public RmsDep getPublishedDepBy() {
        return publishedDepBy;
    }

    public PlanInfoDataHis publishedDepBy(RmsDep rmsDep) {
        this.publishedDepBy = rmsDep;
        return this;
    }

    public void setPublishedDepBy(RmsDep rmsDep) {
        this.publishedDepBy = rmsDep;
    }

    public PlanInfoDataHis getParent() {
        return parent;
    }

    public PlanInfoDataHis parent(PlanInfoDataHis planInfoDataHis) {
        this.parent = planInfoDataHis;
        return this;
    }

    public void setParent(PlanInfoDataHis planInfoDataHis) {
        this.parent = planInfoDataHis;
    }

    public PlanInfo getPlanInfo() {
        return planInfo;
    }

    public PlanInfoDataHis planInfo(PlanInfo planInfo) {
        this.planInfo = planInfo;
        return this;
    }

    public void setPlanInfo(PlanInfo planInfo) {
        this.planInfo = planInfo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlanInfoDataHis planInfoDataHis = (PlanInfoDataHis) o;
        if (planInfoDataHis.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planInfoDataHis.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanInfoDataHis{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sortString='" + getSortString() + "'" +
            ", descString='" + getDescString() + "'" +
            ", jsonString='" + getJsonString() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", refEvent='" + getRefEvent() + "'" +
            ", attachmentPath='" + getAttachmentPath() + "'" +
            ", attachmentBlob='" + getAttachmentBlob() + "'" +
            ", attachmentBlobContentType='" + getAttachmentBlobContentType() + "'" +
            ", attachmentName='" + getAttachmentName() + "'" +
            ", textBlob='" + getTextBlob() + "'" +
            ", imageBlob='" + getImageBlob() + "'" +
            ", imageBlobContentType='" + getImageBlobContentType() + "'" +
            ", imageBlobName='" + getImageBlobName() + "'" +
            ", usingFlag='" + isUsingFlag() + "'" +
            ", validType='" + getValidType() + "'" +
            ", validBegin='" + getValidBegin() + "'" +
            ", validEnd='" + getValidEnd() + "'" +
            ", insertTime='" + getInsertTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", verifyTime='" + getVerifyTime() + "'" +
            ", verifyNeed='" + isVerifyNeed() + "'" +
            ", verifyOpinion='" + getVerifyOpinion() + "'" +
            ", viewCount=" + getViewCount() +
            ", viewPermission='" + getViewPermission() + "'" +
            ", viewPermPersion='" + getViewPermPersion() + "'" +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", versionNumber='" + getVersionNumber() + "'" +
            ", paraSourceString='" + getParaSourceString() + "'" +
            ", featureKeyword='" + getFeatureKeyword() + "'" +
            ", suggestion='" + getSuggestion() + "'" +
            ", releaseScope='" + getReleaseScope() + "'" +
            ", currentStepOrder=" + getCurrentStepOrder() +
            "}";
    }
}
