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
 * Plan Step Data预案步骤数据表
 * @author JungleYang
 */
@ApiModel(description = "Plan Step Data预案步骤数据表 @author JungleYang")
@Entity
@Table(name = "plan_info_step_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "planinfostepdata")
public class PlanInfoStepData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 预案库类型参数名称
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "预案库类型参数名称", required = true)
    @Column(name = "name", length = 64, nullable = false)
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
     * 步骤顺序
     */
    @ApiModelProperty(value = "步骤顺序")
    @Column(name = "step_order")
    private Integer stepOrder;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsUser createdBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsDep createdDepBy;

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
    private PlanInfoStep planInfoStep;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PlanInfoData planInfoData;

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

    public PlanInfoStepData name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortString() {
        return sortString;
    }

    public PlanInfoStepData sortString(String sortString) {
        this.sortString = sortString;
        return this;
    }

    public void setSortString(String sortString) {
        this.sortString = sortString;
    }

    public String getDescString() {
        return descString;
    }

    public PlanInfoStepData descString(String descString) {
        this.descString = descString;
        return this;
    }

    public void setDescString(String descString) {
        this.descString = descString;
    }

    public String getJsonString() {
        return jsonString;
    }

    public PlanInfoStepData jsonString(String jsonString) {
        this.jsonString = jsonString;
        return this;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getRemarks() {
        return remarks;
    }

    public PlanInfoStepData remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public PlanInfoStepData attachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
        return this;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public byte[] getAttachmentBlob() {
        return attachmentBlob;
    }

    public PlanInfoStepData attachmentBlob(byte[] attachmentBlob) {
        this.attachmentBlob = attachmentBlob;
        return this;
    }

    public void setAttachmentBlob(byte[] attachmentBlob) {
        this.attachmentBlob = attachmentBlob;
    }

    public String getAttachmentBlobContentType() {
        return attachmentBlobContentType;
    }

    public PlanInfoStepData attachmentBlobContentType(String attachmentBlobContentType) {
        this.attachmentBlobContentType = attachmentBlobContentType;
        return this;
    }

    public void setAttachmentBlobContentType(String attachmentBlobContentType) {
        this.attachmentBlobContentType = attachmentBlobContentType;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public PlanInfoStepData attachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
        return this;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getTextBlob() {
        return textBlob;
    }

    public PlanInfoStepData textBlob(String textBlob) {
        this.textBlob = textBlob;
        return this;
    }

    public void setTextBlob(String textBlob) {
        this.textBlob = textBlob;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public PlanInfoStepData imageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
        return this;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }

    public String getImageBlobContentType() {
        return imageBlobContentType;
    }

    public PlanInfoStepData imageBlobContentType(String imageBlobContentType) {
        this.imageBlobContentType = imageBlobContentType;
        return this;
    }

    public void setImageBlobContentType(String imageBlobContentType) {
        this.imageBlobContentType = imageBlobContentType;
    }

    public String getImageBlobName() {
        return imageBlobName;
    }

    public PlanInfoStepData imageBlobName(String imageBlobName) {
        this.imageBlobName = imageBlobName;
        return this;
    }

    public void setImageBlobName(String imageBlobName) {
        this.imageBlobName = imageBlobName;
    }

    public Boolean isUsingFlag() {
        return usingFlag;
    }

    public PlanInfoStepData usingFlag(Boolean usingFlag) {
        this.usingFlag = usingFlag;
        return this;
    }

    public void setUsingFlag(Boolean usingFlag) {
        this.usingFlag = usingFlag;
    }

    public ValidType getValidType() {
        return validType;
    }

    public PlanInfoStepData validType(ValidType validType) {
        this.validType = validType;
        return this;
    }

    public void setValidType(ValidType validType) {
        this.validType = validType;
    }

    public Instant getValidBegin() {
        return validBegin;
    }

    public PlanInfoStepData validBegin(Instant validBegin) {
        this.validBegin = validBegin;
        return this;
    }

    public void setValidBegin(Instant validBegin) {
        this.validBegin = validBegin;
    }

    public Instant getValidEnd() {
        return validEnd;
    }

    public PlanInfoStepData validEnd(Instant validEnd) {
        this.validEnd = validEnd;
        return this;
    }

    public void setValidEnd(Instant validEnd) {
        this.validEnd = validEnd;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public PlanInfoStepData insertTime(Instant insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public PlanInfoStepData updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Instant getVerifyTime() {
        return verifyTime;
    }

    public PlanInfoStepData verifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
        return this;
    }

    public void setVerifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Boolean isVerifyNeed() {
        return verifyNeed;
    }

    public PlanInfoStepData verifyNeed(Boolean verifyNeed) {
        this.verifyNeed = verifyNeed;
        return this;
    }

    public void setVerifyNeed(Boolean verifyNeed) {
        this.verifyNeed = verifyNeed;
    }

    public String getVerifyOpinion() {
        return verifyOpinion;
    }

    public PlanInfoStepData verifyOpinion(String verifyOpinion) {
        this.verifyOpinion = verifyOpinion;
        return this;
    }

    public void setVerifyOpinion(String verifyOpinion) {
        this.verifyOpinion = verifyOpinion;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public PlanInfoStepData viewCount(Integer viewCount) {
        this.viewCount = viewCount;
        return this;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public ViewPermission getViewPermission() {
        return viewPermission;
    }

    public PlanInfoStepData viewPermission(ViewPermission viewPermission) {
        this.viewPermission = viewPermission;
        return this;
    }

    public void setViewPermission(ViewPermission viewPermission) {
        this.viewPermission = viewPermission;
    }

    public String getViewPermPersion() {
        return viewPermPersion;
    }

    public PlanInfoStepData viewPermPersion(String viewPermPersion) {
        this.viewPermPersion = viewPermPersion;
        return this;
    }

    public void setViewPermPersion(String viewPermPersion) {
        this.viewPermPersion = viewPermPersion;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public PlanInfoStepData serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public PlanInfoStepData versionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
        return this;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Integer getStepOrder() {
        return stepOrder;
    }

    public PlanInfoStepData stepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
        return this;
    }

    public void setStepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
    }

    public RmsUser getCreatedBy() {
        return createdBy;
    }

    public PlanInfoStepData createdBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
        return this;
    }

    public void setCreatedBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
    }

    public RmsDep getCreatedDepBy() {
        return createdDepBy;
    }

    public PlanInfoStepData createdDepBy(RmsDep rmsDep) {
        this.createdDepBy = rmsDep;
        return this;
    }

    public void setCreatedDepBy(RmsDep rmsDep) {
        this.createdDepBy = rmsDep;
    }

    public RmsUser getModifiedBy() {
        return modifiedBy;
    }

    public PlanInfoStepData modifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
        return this;
    }

    public void setModifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
    }

    public RmsDep getModifiedDepBy() {
        return modifiedDepBy;
    }

    public PlanInfoStepData modifiedDepBy(RmsDep rmsDep) {
        this.modifiedDepBy = rmsDep;
        return this;
    }

    public void setModifiedDepBy(RmsDep rmsDep) {
        this.modifiedDepBy = rmsDep;
    }

    public RmsUser getVerifiedBy() {
        return verifiedBy;
    }

    public PlanInfoStepData verifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
        return this;
    }

    public void setVerifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
    }

    public RmsDep getVerifiedDepBy() {
        return verifiedDepBy;
    }

    public PlanInfoStepData verifiedDepBy(RmsDep rmsDep) {
        this.verifiedDepBy = rmsDep;
        return this;
    }

    public void setVerifiedDepBy(RmsDep rmsDep) {
        this.verifiedDepBy = rmsDep;
    }

    public PlanInfoStep getPlanInfoStep() {
        return planInfoStep;
    }

    public PlanInfoStepData planInfoStep(PlanInfoStep planInfoStep) {
        this.planInfoStep = planInfoStep;
        return this;
    }

    public void setPlanInfoStep(PlanInfoStep planInfoStep) {
        this.planInfoStep = planInfoStep;
    }

    public PlanInfoData getPlanInfoData() {
        return planInfoData;
    }

    public PlanInfoStepData planInfoData(PlanInfoData planInfoData) {
        this.planInfoData = planInfoData;
        return this;
    }

    public void setPlanInfoData(PlanInfoData planInfoData) {
        this.planInfoData = planInfoData;
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
        PlanInfoStepData planInfoStepData = (PlanInfoStepData) o;
        if (planInfoStepData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planInfoStepData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanInfoStepData{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sortString='" + getSortString() + "'" +
            ", descString='" + getDescString() + "'" +
            ", jsonString='" + getJsonString() + "'" +
            ", remarks='" + getRemarks() + "'" +
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
            ", stepOrder=" + getStepOrder() +
            "}";
    }
}
