package com.aerothinker.plandb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.aerothinker.plandb.domain.enumeration.ValidType;

import com.aerothinker.plandb.domain.enumeration.ViewPermission;

/**
 * PlanInfo 预案库模板表
 * @author JungleYang
 */
@ApiModel(description = "PlanInfo 预案库模板表 @author JungleYang")
@Entity
@Table(name = "plan_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "planinfo")
@FilterDef(name = "COMPANY_FILTER", parameters = {@ParamDef(name = "companyId", type = "long")})
@Filter(name = "COMPANY_FILTER", condition = "company_id = :companyId")

public class PlanInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 预案
     */
    @NotNull
    @Size(max = 256)
    @ApiModelProperty(value = "预案", required = true)
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
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    @Column(name = "published_time")
    private Instant publishedTime;

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
    private PlanInfo parent;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Company company;

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

    public PlanInfo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortString() {
        return sortString;
    }

    public PlanInfo sortString(String sortString) {
        this.sortString = sortString;
        return this;
    }

    public void setSortString(String sortString) {
        this.sortString = sortString;
    }

    public String getDescString() {
        return descString;
    }

    public PlanInfo descString(String descString) {
        this.descString = descString;
        return this;
    }

    public void setDescString(String descString) {
        this.descString = descString;
    }

    public String getJsonString() {
        return jsonString;
    }

    public PlanInfo jsonString(String jsonString) {
        this.jsonString = jsonString;
        return this;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getRemarks() {
        return remarks;
    }

    public PlanInfo remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRefEvent() {
        return refEvent;
    }

    public PlanInfo refEvent(String refEvent) {
        this.refEvent = refEvent;
        return this;
    }

    public void setRefEvent(String refEvent) {
        this.refEvent = refEvent;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public PlanInfo attachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
        return this;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public byte[] getAttachmentBlob() {
        return attachmentBlob;
    }

    public PlanInfo attachmentBlob(byte[] attachmentBlob) {
        this.attachmentBlob = attachmentBlob;
        return this;
    }

    public void setAttachmentBlob(byte[] attachmentBlob) {
        this.attachmentBlob = attachmentBlob;
    }

    public String getAttachmentBlobContentType() {
        return attachmentBlobContentType;
    }

    public PlanInfo attachmentBlobContentType(String attachmentBlobContentType) {
        this.attachmentBlobContentType = attachmentBlobContentType;
        return this;
    }

    public void setAttachmentBlobContentType(String attachmentBlobContentType) {
        this.attachmentBlobContentType = attachmentBlobContentType;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public PlanInfo attachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
        return this;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getTextBlob() {
        return textBlob;
    }

    public PlanInfo textBlob(String textBlob) {
        this.textBlob = textBlob;
        return this;
    }

    public void setTextBlob(String textBlob) {
        this.textBlob = textBlob;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public PlanInfo imageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
        return this;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }

    public String getImageBlobContentType() {
        return imageBlobContentType;
    }

    public PlanInfo imageBlobContentType(String imageBlobContentType) {
        this.imageBlobContentType = imageBlobContentType;
        return this;
    }

    public void setImageBlobContentType(String imageBlobContentType) {
        this.imageBlobContentType = imageBlobContentType;
    }

    public String getImageBlobName() {
        return imageBlobName;
    }

    public PlanInfo imageBlobName(String imageBlobName) {
        this.imageBlobName = imageBlobName;
        return this;
    }

    public void setImageBlobName(String imageBlobName) {
        this.imageBlobName = imageBlobName;
    }

    public Boolean isUsingFlag() {
        return usingFlag;
    }

    public PlanInfo usingFlag(Boolean usingFlag) {
        this.usingFlag = usingFlag;
        return this;
    }

    public void setUsingFlag(Boolean usingFlag) {
        this.usingFlag = usingFlag;
    }

    public ValidType getValidType() {
        return validType;
    }

    public PlanInfo validType(ValidType validType) {
        this.validType = validType;
        return this;
    }

    public void setValidType(ValidType validType) {
        this.validType = validType;
    }

    public Instant getValidBegin() {
        return validBegin;
    }

    public PlanInfo validBegin(Instant validBegin) {
        this.validBegin = validBegin;
        return this;
    }

    public void setValidBegin(Instant validBegin) {
        this.validBegin = validBegin;
    }

    public Instant getValidEnd() {
        return validEnd;
    }

    public PlanInfo validEnd(Instant validEnd) {
        this.validEnd = validEnd;
        return this;
    }

    public void setValidEnd(Instant validEnd) {
        this.validEnd = validEnd;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public PlanInfo insertTime(Instant insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public PlanInfo updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Instant getPublishedTime() {
        return publishedTime;
    }

    public PlanInfo publishedTime(Instant publishedTime) {
        this.publishedTime = publishedTime;
        return this;
    }

    public void setPublishedTime(Instant publishedTime) {
        this.publishedTime = publishedTime;
    }

    public Instant getVerifyTime() {
        return verifyTime;
    }

    public PlanInfo verifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
        return this;
    }

    public void setVerifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Boolean isVerifyNeed() {
        return verifyNeed;
    }

    public PlanInfo verifyNeed(Boolean verifyNeed) {
        this.verifyNeed = verifyNeed;
        return this;
    }

    public void setVerifyNeed(Boolean verifyNeed) {
        this.verifyNeed = verifyNeed;
    }

    public String getVerifyOpinion() {
        return verifyOpinion;
    }

    public PlanInfo verifyOpinion(String verifyOpinion) {
        this.verifyOpinion = verifyOpinion;
        return this;
    }

    public void setVerifyOpinion(String verifyOpinion) {
        this.verifyOpinion = verifyOpinion;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public PlanInfo viewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public ViewPermission getViewPermission() {
        return viewPermission;
    }

    public PlanInfo viewPermission(ViewPermission viewPermission) {
        this.viewPermission = viewPermission;
        return this;
    }

    public void setViewPermission(ViewPermission viewPermission) {
        this.viewPermission = viewPermission;
    }

    public String getViewPermPersion() {
        return viewPermPersion;
    }

    public PlanInfo viewPermPersion(String viewPermPersion) {
        this.viewPermPersion = viewPermPersion;
        return this;
    }

    public void setViewPermPersion(String viewPermPersion) {
        this.viewPermPersion = viewPermPersion;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public PlanInfo serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public PlanInfo versionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
        return this;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getParaSourceString() {
        return paraSourceString;
    }

    public PlanInfo paraSourceString(String paraSourceString) {
        this.paraSourceString = paraSourceString;
        return this;
    }

    public void setParaSourceString(String paraSourceString) {
        this.paraSourceString = paraSourceString;
    }

    public String getFeatureKeyword() {
        return featureKeyword;
    }

    public PlanInfo featureKeyword(String featureKeyword) {
        this.featureKeyword = featureKeyword;
        return this;
    }

    public void setFeatureKeyword(String featureKeyword) {
        this.featureKeyword = featureKeyword;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public PlanInfo suggestion(String suggestion) {
        this.suggestion = suggestion;
        return this;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getReleaseScope() {
        return releaseScope;
    }

    public PlanInfo releaseScope(String releaseScope) {
        this.releaseScope = releaseScope;
        return this;
    }

    public void setReleaseScope(String releaseScope) {
        this.releaseScope = releaseScope;
    }

    public VerifyRec getVerifyRec() {
        return verifyRec;
    }

    public PlanInfo verifyRec(VerifyRec verifyRec) {
        this.verifyRec = verifyRec;
        return this;
    }

    public void setVerifyRec(VerifyRec verifyRec) {
        this.verifyRec = verifyRec;
    }

    public ParaType getParaType() {
        return paraType;
    }

    public PlanInfo paraType(ParaType paraType) {
        this.paraType = paraType;
        return this;
    }

    public void setParaType(ParaType paraType) {
        this.paraType = paraType;
    }

    public ParaClass getParaClass() {
        return paraClass;
    }

    public PlanInfo paraClass(ParaClass paraClass) {
        this.paraClass = paraClass;
        return this;
    }

    public void setParaClass(ParaClass paraClass) {
        this.paraClass = paraClass;
    }

    public ParaCat getParaCat() {
        return paraCat;
    }

    public PlanInfo paraCat(ParaCat paraCat) {
        this.paraCat = paraCat;
        return this;
    }

    public void setParaCat(ParaCat paraCat) {
        this.paraCat = paraCat;
    }

    public ParaState getParaState() {
        return paraState;
    }

    public PlanInfo paraState(ParaState paraState) {
        this.paraState = paraState;
        return this;
    }

    public void setParaState(ParaState paraState) {
        this.paraState = paraState;
    }

    public ParaSource getParaSource() {
        return paraSource;
    }

    public PlanInfo paraSource(ParaSource paraSource) {
        this.paraSource = paraSource;
        return this;
    }

    public void setParaSource(ParaSource paraSource) {
        this.paraSource = paraSource;
    }

    public ParaAttr getParaAttr() {
        return paraAttr;
    }

    public PlanInfo paraAttr(ParaAttr paraAttr) {
        this.paraAttr = paraAttr;
        return this;
    }

    public void setParaAttr(ParaAttr paraAttr) {
        this.paraAttr = paraAttr;
    }

    public ParaProp getParaProp() {
        return paraProp;
    }

    public PlanInfo paraProp(ParaProp paraProp) {
        this.paraProp = paraProp;
        return this;
    }

    public void setParaProp(ParaProp paraProp) {
        this.paraProp = paraProp;
    }

    public RmsUser getCreatedBy() {
        return createdBy;
    }

    public PlanInfo createdBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
        return this;
    }

    public void setCreatedBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
    }

    public RmsDep getCreatedDepBy() {
        return createdDepBy;
    }

    public PlanInfo createdDepBy(RmsDep rmsDep) {
        this.createdDepBy = rmsDep;
        return this;
    }

    public void setCreatedDepBy(RmsDep rmsDep) {
        this.createdDepBy = rmsDep;
    }

    public RmsUser getOwnerBy() {
        return ownerBy;
    }

    public PlanInfo ownerBy(RmsUser rmsUser) {
        this.ownerBy = rmsUser;
        return this;
    }

    public void setOwnerBy(RmsUser rmsUser) {
        this.ownerBy = rmsUser;
    }

    public RmsDep getOwnerDepBy() {
        return ownerDepBy;
    }

    public PlanInfo ownerDepBy(RmsDep rmsDep) {
        this.ownerDepBy = rmsDep;
        return this;
    }

    public void setOwnerDepBy(RmsDep rmsDep) {
        this.ownerDepBy = rmsDep;
    }

    public RmsUser getModifiedBy() {
        return modifiedBy;
    }

    public PlanInfo modifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
        return this;
    }

    public void setModifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
    }

    public RmsDep getModifiedDepBy() {
        return modifiedDepBy;
    }

    public PlanInfo modifiedDepBy(RmsDep rmsDep) {
        this.modifiedDepBy = rmsDep;
        return this;
    }

    public void setModifiedDepBy(RmsDep rmsDep) {
        this.modifiedDepBy = rmsDep;
    }

    public RmsUser getVerifiedBy() {
        return verifiedBy;
    }

    public PlanInfo verifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
        return this;
    }

    public void setVerifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
    }

    public RmsDep getVerifiedDepBy() {
        return verifiedDepBy;
    }

    public PlanInfo verifiedDepBy(RmsDep rmsDep) {
        this.verifiedDepBy = rmsDep;
        return this;
    }

    public void setVerifiedDepBy(RmsDep rmsDep) {
        this.verifiedDepBy = rmsDep;
    }

    public RmsUser getPublishedBy() {
        return publishedBy;
    }

    public PlanInfo publishedBy(RmsUser rmsUser) {
        this.publishedBy = rmsUser;
        return this;
    }

    public void setPublishedBy(RmsUser rmsUser) {
        this.publishedBy = rmsUser;
    }

    public RmsDep getPublishedDepBy() {
        return publishedDepBy;
    }

    public PlanInfo publishedDepBy(RmsDep rmsDep) {
        this.publishedDepBy = rmsDep;
        return this;
    }

    public void setPublishedDepBy(RmsDep rmsDep) {
        this.publishedDepBy = rmsDep;
    }

    public PlanInfo getParent() {
        return parent;
    }

    public PlanInfo parent(PlanInfo planInfo) {
        this.parent = planInfo;
        return this;
    }

    public void setParent(PlanInfo planInfo) {
        this.parent = planInfo;
    }

    public Company getCompany() {
        return company;
    }

    public PlanInfo company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        PlanInfo planInfo = (PlanInfo) o;
        if (planInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanInfo{" +
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
            ", publishedTime='" + getPublishedTime() + "'" +
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
            "}";
    }
}
