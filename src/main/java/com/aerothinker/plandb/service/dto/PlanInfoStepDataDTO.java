package com.aerothinker.plandb.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.aerothinker.plandb.domain.enumeration.ValidType;
import com.aerothinker.plandb.domain.enumeration.ViewPermission;

/**
 * A DTO for the PlanInfoStepData entity.
 */
public class PlanInfoStepDataDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 64)
    private String name;

    @Size(max = 10)
    private String sortString;

    @Size(max = 4000)
    private String descString;

    @Size(max = 4000)
    private String jsonString;

    @Size(max = 4000)
    private String remarks;

    @Size(max = 4000)
    private String attachmentPath;

    @Lob
    private byte[] attachmentBlob;
    private String attachmentBlobContentType;

    @Size(max = 4000)
    private String attachmentName;

    @Lob
    private String textBlob;

    @Lob
    private byte[] imageBlob;
    private String imageBlobContentType;

    @Size(max = 4000)
    private String imageBlobName;

    private Boolean usingFlag;

    private ValidType validType;

    private Instant validBegin;

    private Instant validEnd;

    private Instant insertTime;

    private Instant updateTime;

    private Instant verifyTime;

    private Boolean verifyNeed;

    @Size(max = 4000)
    private String verifyOpinion;

    private Integer viewCount;

    private ViewPermission viewPermission;

    @Size(max = 4000)
    private String viewPermPersion;

    @Size(max = 256)
    private String serialNumber;

    @Size(max = 256)
    private String versionNumber;

    private Integer stepOrder;

    private Long createdById;

    private String createdByUserName;

    private Long createdDepById;

    private String createdDepByDepName;

    private Long modifiedById;

    private String modifiedByUserName;

    private Long modifiedDepById;

    private String modifiedDepByDepName;

    private Long verifiedById;

    private String verifiedByUserName;

    private Long verifiedDepById;

    private String verifiedDepByDepName;

    private Long planInfoStepId;

    private String planInfoStepName;

    private Long planInfoDataId;

    private String planInfoDataName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortString() {
        return sortString;
    }

    public void setSortString(String sortString) {
        this.sortString = sortString;
    }

    public String getDescString() {
        return descString;
    }

    public void setDescString(String descString) {
        this.descString = descString;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public byte[] getAttachmentBlob() {
        return attachmentBlob;
    }

    public void setAttachmentBlob(byte[] attachmentBlob) {
        this.attachmentBlob = attachmentBlob;
    }

    public String getAttachmentBlobContentType() {
        return attachmentBlobContentType;
    }

    public void setAttachmentBlobContentType(String attachmentBlobContentType) {
        this.attachmentBlobContentType = attachmentBlobContentType;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getTextBlob() {
        return textBlob;
    }

    public void setTextBlob(String textBlob) {
        this.textBlob = textBlob;
    }

    public byte[] getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(byte[] imageBlob) {
        this.imageBlob = imageBlob;
    }

    public String getImageBlobContentType() {
        return imageBlobContentType;
    }

    public void setImageBlobContentType(String imageBlobContentType) {
        this.imageBlobContentType = imageBlobContentType;
    }

    public String getImageBlobName() {
        return imageBlobName;
    }

    public void setImageBlobName(String imageBlobName) {
        this.imageBlobName = imageBlobName;
    }

    public Boolean isUsingFlag() {
        return usingFlag;
    }

    public void setUsingFlag(Boolean usingFlag) {
        this.usingFlag = usingFlag;
    }

    public ValidType getValidType() {
        return validType;
    }

    public void setValidType(ValidType validType) {
        this.validType = validType;
    }

    public Instant getValidBegin() {
        return validBegin;
    }

    public void setValidBegin(Instant validBegin) {
        this.validBegin = validBegin;
    }

    public Instant getValidEnd() {
        return validEnd;
    }

    public void setValidEnd(Instant validEnd) {
        this.validEnd = validEnd;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Instant getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Boolean isVerifyNeed() {
        return verifyNeed;
    }

    public void setVerifyNeed(Boolean verifyNeed) {
        this.verifyNeed = verifyNeed;
    }

    public String getVerifyOpinion() {
        return verifyOpinion;
    }

    public void setVerifyOpinion(String verifyOpinion) {
        this.verifyOpinion = verifyOpinion;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public ViewPermission getViewPermission() {
        return viewPermission;
    }

    public void setViewPermission(ViewPermission viewPermission) {
        this.viewPermission = viewPermission;
    }

    public String getViewPermPersion() {
        return viewPermPersion;
    }

    public void setViewPermPersion(String viewPermPersion) {
        this.viewPermPersion = viewPermPersion;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Integer getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long rmsUserId) {
        this.createdById = rmsUserId;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String rmsUserUserName) {
        this.createdByUserName = rmsUserUserName;
    }

    public Long getCreatedDepById() {
        return createdDepById;
    }

    public void setCreatedDepById(Long rmsDepId) {
        this.createdDepById = rmsDepId;
    }

    public String getCreatedDepByDepName() {
        return createdDepByDepName;
    }

    public void setCreatedDepByDepName(String rmsDepDepName) {
        this.createdDepByDepName = rmsDepDepName;
    }

    public Long getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(Long rmsUserId) {
        this.modifiedById = rmsUserId;
    }

    public String getModifiedByUserName() {
        return modifiedByUserName;
    }

    public void setModifiedByUserName(String rmsUserUserName) {
        this.modifiedByUserName = rmsUserUserName;
    }

    public Long getModifiedDepById() {
        return modifiedDepById;
    }

    public void setModifiedDepById(Long rmsDepId) {
        this.modifiedDepById = rmsDepId;
    }

    public String getModifiedDepByDepName() {
        return modifiedDepByDepName;
    }

    public void setModifiedDepByDepName(String rmsDepDepName) {
        this.modifiedDepByDepName = rmsDepDepName;
    }

    public Long getVerifiedById() {
        return verifiedById;
    }

    public void setVerifiedById(Long rmsUserId) {
        this.verifiedById = rmsUserId;
    }

    public String getVerifiedByUserName() {
        return verifiedByUserName;
    }

    public void setVerifiedByUserName(String rmsUserUserName) {
        this.verifiedByUserName = rmsUserUserName;
    }

    public Long getVerifiedDepById() {
        return verifiedDepById;
    }

    public void setVerifiedDepById(Long rmsDepId) {
        this.verifiedDepById = rmsDepId;
    }

    public String getVerifiedDepByDepName() {
        return verifiedDepByDepName;
    }

    public void setVerifiedDepByDepName(String rmsDepDepName) {
        this.verifiedDepByDepName = rmsDepDepName;
    }

    public Long getPlanInfoStepId() {
        return planInfoStepId;
    }

    public void setPlanInfoStepId(Long planInfoStepId) {
        this.planInfoStepId = planInfoStepId;
    }

    public String getPlanInfoStepName() {
        return planInfoStepName;
    }

    public void setPlanInfoStepName(String planInfoStepName) {
        this.planInfoStepName = planInfoStepName;
    }

    public Long getPlanInfoDataId() {
        return planInfoDataId;
    }

    public void setPlanInfoDataId(Long planInfoDataId) {
        this.planInfoDataId = planInfoDataId;
    }

    public String getPlanInfoDataName() {
        return planInfoDataName;
    }

    public void setPlanInfoDataName(String planInfoDataName) {
        this.planInfoDataName = planInfoDataName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlanInfoStepDataDTO planInfoStepDataDTO = (PlanInfoStepDataDTO) o;
        if (planInfoStepDataDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planInfoStepDataDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanInfoStepDataDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sortString='" + getSortString() + "'" +
            ", descString='" + getDescString() + "'" +
            ", jsonString='" + getJsonString() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", attachmentPath='" + getAttachmentPath() + "'" +
            ", attachmentBlob='" + getAttachmentBlob() + "'" +
            ", attachmentName='" + getAttachmentName() + "'" +
            ", textBlob='" + getTextBlob() + "'" +
            ", imageBlob='" + getImageBlob() + "'" +
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
            ", createdBy=" + getCreatedById() +
            ", createdBy='" + getCreatedByUserName() + "'" +
            ", createdDepBy=" + getCreatedDepById() +
            ", createdDepBy='" + getCreatedDepByDepName() + "'" +
            ", modifiedBy=" + getModifiedById() +
            ", modifiedBy='" + getModifiedByUserName() + "'" +
            ", modifiedDepBy=" + getModifiedDepById() +
            ", modifiedDepBy='" + getModifiedDepByDepName() + "'" +
            ", verifiedBy=" + getVerifiedById() +
            ", verifiedBy='" + getVerifiedByUserName() + "'" +
            ", verifiedDepBy=" + getVerifiedDepById() +
            ", verifiedDepBy='" + getVerifiedDepByDepName() + "'" +
            ", planInfoStep=" + getPlanInfoStepId() +
            ", planInfoStep='" + getPlanInfoStepName() + "'" +
            ", planInfoData=" + getPlanInfoDataId() +
            ", planInfoData='" + getPlanInfoDataName() + "'" +
            "}";
    }
}
