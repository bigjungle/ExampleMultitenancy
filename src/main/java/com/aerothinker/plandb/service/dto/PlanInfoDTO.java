package com.aerothinker.plandb.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.aerothinker.plandb.domain.enumeration.ValidType;
import com.aerothinker.plandb.domain.enumeration.ViewPermission;

/**
 * A DTO for the PlanInfo entity.
 */
public class PlanInfoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 256)
    private String name;

    @Size(max = 10)
    private String sortString;

    @Size(max = 4000)
    private String descString;

    @Size(max = 4000)
    private String jsonString;

    @Size(max = 4000)
    private String remarks;

    @Size(max = 512)
    private String refEvent;

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

    private Instant publishedTime;

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

    @Size(max = 4000)
    private String paraSourceString;

    @Size(max = 4000)
    private String featureKeyword;

    @Size(max = 4000)
    private String suggestion;

    @Size(max = 4000)
    private String releaseScope;

    private Long verifyRecId;

    private String verifyRecName;

    private Long paraTypeId;

    private String paraTypeName;

    private Long paraClassId;

    private String paraClassName;

    private Long paraCatId;

    private String paraCatName;

    private Long paraStateId;

    private String paraStateName;

    private Long paraSourceId;

    private String paraSourceName;

    private Long paraAttrId;

    private String paraAttrName;

    private Long paraPropId;

    private String paraPropName;

    private Long createdById;

    private String createdByUserName;

    private Long createdDepById;

    private String createdDepByDepName;

    private Long ownerById;

    private String ownerByUserName;

    private Long ownerDepById;

    private String ownerDepByDepName;

    private Long modifiedById;

    private String modifiedByUserName;

    private Long modifiedDepById;

    private String modifiedDepByDepName;

    private Long verifiedById;

    private String verifiedByUserName;

    private Long verifiedDepById;

    private String verifiedDepByDepName;

    private Long publishedById;

    private String publishedByUserName;

    private Long publishedDepById;

    private String publishedDepByDepName;

    private Long parentId;

    private String parentName;

    private Long companyId;

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

    public String getRefEvent() {
        return refEvent;
    }

    public void setRefEvent(String refEvent) {
        this.refEvent = refEvent;
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

    public Instant getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Instant publishedTime) {
        this.publishedTime = publishedTime;
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

    public String getParaSourceString() {
        return paraSourceString;
    }

    public void setParaSourceString(String paraSourceString) {
        this.paraSourceString = paraSourceString;
    }

    public String getFeatureKeyword() {
        return featureKeyword;
    }

    public void setFeatureKeyword(String featureKeyword) {
        this.featureKeyword = featureKeyword;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getReleaseScope() {
        return releaseScope;
    }

    public void setReleaseScope(String releaseScope) {
        this.releaseScope = releaseScope;
    }

    public Long getVerifyRecId() {
        return verifyRecId;
    }

    public void setVerifyRecId(Long verifyRecId) {
        this.verifyRecId = verifyRecId;
    }

    public String getVerifyRecName() {
        return verifyRecName;
    }

    public void setVerifyRecName(String verifyRecName) {
        this.verifyRecName = verifyRecName;
    }

    public Long getParaTypeId() {
        return paraTypeId;
    }

    public void setParaTypeId(Long paraTypeId) {
        this.paraTypeId = paraTypeId;
    }

    public String getParaTypeName() {
        return paraTypeName;
    }

    public void setParaTypeName(String paraTypeName) {
        this.paraTypeName = paraTypeName;
    }

    public Long getParaClassId() {
        return paraClassId;
    }

    public void setParaClassId(Long paraClassId) {
        this.paraClassId = paraClassId;
    }

    public String getParaClassName() {
        return paraClassName;
    }

    public void setParaClassName(String paraClassName) {
        this.paraClassName = paraClassName;
    }

    public Long getParaCatId() {
        return paraCatId;
    }

    public void setParaCatId(Long paraCatId) {
        this.paraCatId = paraCatId;
    }

    public String getParaCatName() {
        return paraCatName;
    }

    public void setParaCatName(String paraCatName) {
        this.paraCatName = paraCatName;
    }

    public Long getParaStateId() {
        return paraStateId;
    }

    public void setParaStateId(Long paraStateId) {
        this.paraStateId = paraStateId;
    }

    public String getParaStateName() {
        return paraStateName;
    }

    public void setParaStateName(String paraStateName) {
        this.paraStateName = paraStateName;
    }

    public Long getParaSourceId() {
        return paraSourceId;
    }

    public void setParaSourceId(Long paraSourceId) {
        this.paraSourceId = paraSourceId;
    }

    public String getParaSourceName() {
        return paraSourceName;
    }

    public void setParaSourceName(String paraSourceName) {
        this.paraSourceName = paraSourceName;
    }

    public Long getParaAttrId() {
        return paraAttrId;
    }

    public void setParaAttrId(Long paraAttrId) {
        this.paraAttrId = paraAttrId;
    }

    public String getParaAttrName() {
        return paraAttrName;
    }

    public void setParaAttrName(String paraAttrName) {
        this.paraAttrName = paraAttrName;
    }

    public Long getParaPropId() {
        return paraPropId;
    }

    public void setParaPropId(Long paraPropId) {
        this.paraPropId = paraPropId;
    }

    public String getParaPropName() {
        return paraPropName;
    }

    public void setParaPropName(String paraPropName) {
        this.paraPropName = paraPropName;
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

    public Long getOwnerById() {
        return ownerById;
    }

    public void setOwnerById(Long rmsUserId) {
        this.ownerById = rmsUserId;
    }

    public String getOwnerByUserName() {
        return ownerByUserName;
    }

    public void setOwnerByUserName(String rmsUserUserName) {
        this.ownerByUserName = rmsUserUserName;
    }

    public Long getOwnerDepById() {
        return ownerDepById;
    }

    public void setOwnerDepById(Long rmsDepId) {
        this.ownerDepById = rmsDepId;
    }

    public String getOwnerDepByDepName() {
        return ownerDepByDepName;
    }

    public void setOwnerDepByDepName(String rmsDepDepName) {
        this.ownerDepByDepName = rmsDepDepName;
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

    public Long getPublishedById() {
        return publishedById;
    }

    public void setPublishedById(Long rmsUserId) {
        this.publishedById = rmsUserId;
    }

    public String getPublishedByUserName() {
        return publishedByUserName;
    }

    public void setPublishedByUserName(String rmsUserUserName) {
        this.publishedByUserName = rmsUserUserName;
    }

    public Long getPublishedDepById() {
        return publishedDepById;
    }

    public void setPublishedDepById(Long rmsDepId) {
        this.publishedDepById = rmsDepId;
    }

    public String getPublishedDepByDepName() {
        return publishedDepByDepName;
    }

    public void setPublishedDepByDepName(String rmsDepDepName) {
        this.publishedDepByDepName = rmsDepDepName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long planInfoId) {
        this.parentId = planInfoId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String planInfoName) {
        this.parentName = planInfoName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlanInfoDTO planInfoDTO = (PlanInfoDTO) o;
        if (planInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanInfoDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sortString='" + getSortString() + "'" +
            ", descString='" + getDescString() + "'" +
            ", jsonString='" + getJsonString() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", refEvent='" + getRefEvent() + "'" +
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
            ", verifyRec=" + getVerifyRecId() +
            ", verifyRec='" + getVerifyRecName() + "'" +
            ", paraType=" + getParaTypeId() +
            ", paraType='" + getParaTypeName() + "'" +
            ", paraClass=" + getParaClassId() +
            ", paraClass='" + getParaClassName() + "'" +
            ", paraCat=" + getParaCatId() +
            ", paraCat='" + getParaCatName() + "'" +
            ", paraState=" + getParaStateId() +
            ", paraState='" + getParaStateName() + "'" +
            ", paraSource=" + getParaSourceId() +
            ", paraSource='" + getParaSourceName() + "'" +
            ", paraAttr=" + getParaAttrId() +
            ", paraAttr='" + getParaAttrName() + "'" +
            ", paraProp=" + getParaPropId() +
            ", paraProp='" + getParaPropName() + "'" +
            ", createdBy=" + getCreatedById() +
            ", createdBy='" + getCreatedByUserName() + "'" +
            ", createdDepBy=" + getCreatedDepById() +
            ", createdDepBy='" + getCreatedDepByDepName() + "'" +
            ", ownerBy=" + getOwnerById() +
            ", ownerBy='" + getOwnerByUserName() + "'" +
            ", ownerDepBy=" + getOwnerDepById() +
            ", ownerDepBy='" + getOwnerDepByDepName() + "'" +
            ", modifiedBy=" + getModifiedById() +
            ", modifiedBy='" + getModifiedByUserName() + "'" +
            ", modifiedDepBy=" + getModifiedDepById() +
            ", modifiedDepBy='" + getModifiedDepByDepName() + "'" +
            ", verifiedBy=" + getVerifiedById() +
            ", verifiedBy='" + getVerifiedByUserName() + "'" +
            ", verifiedDepBy=" + getVerifiedDepById() +
            ", verifiedDepBy='" + getVerifiedDepByDepName() + "'" +
            ", publishedBy=" + getPublishedById() +
            ", publishedBy='" + getPublishedByUserName() + "'" +
            ", publishedDepBy=" + getPublishedDepById() +
            ", publishedDepBy='" + getPublishedDepByDepName() + "'" +
            ", parent=" + getParentId() +
            ", parent='" + getParentName() + "'" +
            ", company=" + getCompanyId() +
            "}";
    }
}
