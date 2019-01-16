package com.aerothinker.plandb.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.aerothinker.plandb.domain.enumeration.ValidType;
import com.aerothinker.plandb.domain.enumeration.ViewPermission;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the PlanInfo entity. This class is used in PlanInfoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /plan-infos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PlanInfoCriteria implements Serializable {
    /**
     * Class for filtering ValidType
     */
    public static class ValidTypeFilter extends Filter<ValidType> {
    }
    /**
     * Class for filtering ViewPermission
     */
    public static class ViewPermissionFilter extends Filter<ViewPermission> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter sortString;

    private StringFilter descString;

    private StringFilter jsonString;

    private StringFilter remarks;

    private StringFilter refEvent;

    private StringFilter attachmentPath;

    private StringFilter attachmentName;

    private StringFilter imageBlobName;

    private BooleanFilter usingFlag;

    private ValidTypeFilter validType;

    private InstantFilter validBegin;

    private InstantFilter validEnd;

    private InstantFilter insertTime;

    private InstantFilter updateTime;

    private InstantFilter publishedTime;

    private InstantFilter verifyTime;

    private BooleanFilter verifyNeed;

    private StringFilter verifyOpinion;

    private IntegerFilter viewCount;

    private ViewPermissionFilter viewPermission;

    private StringFilter viewPermPersion;

    private StringFilter serialNumber;

    private StringFilter versionNumber;

    private StringFilter paraSourceString;

    private StringFilter featureKeyword;

    private StringFilter suggestion;

    private StringFilter releaseScope;

    private LongFilter verifyRecId;

    private LongFilter paraTypeId;

    private LongFilter paraClassId;

    private LongFilter paraCatId;

    private LongFilter paraStateId;

    private LongFilter paraSourceId;

    private LongFilter paraAttrId;

    private LongFilter paraPropId;

    private LongFilter createdById;

    private LongFilter createdDepById;

    private LongFilter ownerById;

    private LongFilter ownerDepById;

    private LongFilter modifiedById;

    private LongFilter modifiedDepById;

    private LongFilter verifiedById;

    private LongFilter verifiedDepById;

    private LongFilter publishedById;

    private LongFilter publishedDepById;

    private LongFilter parentId;

    private LongFilter companyId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getSortString() {
        return sortString;
    }

    public void setSortString(StringFilter sortString) {
        this.sortString = sortString;
    }

    public StringFilter getDescString() {
        return descString;
    }

    public void setDescString(StringFilter descString) {
        this.descString = descString;
    }

    public StringFilter getJsonString() {
        return jsonString;
    }

    public void setJsonString(StringFilter jsonString) {
        this.jsonString = jsonString;
    }

    public StringFilter getRemarks() {
        return remarks;
    }

    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }

    public StringFilter getRefEvent() {
        return refEvent;
    }

    public void setRefEvent(StringFilter refEvent) {
        this.refEvent = refEvent;
    }

    public StringFilter getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(StringFilter attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public StringFilter getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(StringFilter attachmentName) {
        this.attachmentName = attachmentName;
    }

    public StringFilter getImageBlobName() {
        return imageBlobName;
    }

    public void setImageBlobName(StringFilter imageBlobName) {
        this.imageBlobName = imageBlobName;
    }

    public BooleanFilter getUsingFlag() {
        return usingFlag;
    }

    public void setUsingFlag(BooleanFilter usingFlag) {
        this.usingFlag = usingFlag;
    }

    public ValidTypeFilter getValidType() {
        return validType;
    }

    public void setValidType(ValidTypeFilter validType) {
        this.validType = validType;
    }

    public InstantFilter getValidBegin() {
        return validBegin;
    }

    public void setValidBegin(InstantFilter validBegin) {
        this.validBegin = validBegin;
    }

    public InstantFilter getValidEnd() {
        return validEnd;
    }

    public void setValidEnd(InstantFilter validEnd) {
        this.validEnd = validEnd;
    }

    public InstantFilter getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(InstantFilter insertTime) {
        this.insertTime = insertTime;
    }

    public InstantFilter getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(InstantFilter updateTime) {
        this.updateTime = updateTime;
    }

    public InstantFilter getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(InstantFilter publishedTime) {
        this.publishedTime = publishedTime;
    }

    public InstantFilter getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(InstantFilter verifyTime) {
        this.verifyTime = verifyTime;
    }

    public BooleanFilter getVerifyNeed() {
        return verifyNeed;
    }

    public void setVerifyNeed(BooleanFilter verifyNeed) {
        this.verifyNeed = verifyNeed;
    }

    public StringFilter getVerifyOpinion() {
        return verifyOpinion;
    }

    public void setVerifyOpinion(StringFilter verifyOpinion) {
        this.verifyOpinion = verifyOpinion;
    }

    public IntegerFilter getViewCount() {
        return viewCount;
    }

    public void setViewCount(IntegerFilter viewCount) {
        this.viewCount = viewCount;
    }

    public ViewPermissionFilter getViewPermission() {
        return viewPermission;
    }

    public void setViewPermission(ViewPermissionFilter viewPermission) {
        this.viewPermission = viewPermission;
    }

    public StringFilter getViewPermPersion() {
        return viewPermPersion;
    }

    public void setViewPermPersion(StringFilter viewPermPersion) {
        this.viewPermPersion = viewPermPersion;
    }

    public StringFilter getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(StringFilter serialNumber) {
        this.serialNumber = serialNumber;
    }

    public StringFilter getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(StringFilter versionNumber) {
        this.versionNumber = versionNumber;
    }

    public StringFilter getParaSourceString() {
        return paraSourceString;
    }

    public void setParaSourceString(StringFilter paraSourceString) {
        this.paraSourceString = paraSourceString;
    }

    public StringFilter getFeatureKeyword() {
        return featureKeyword;
    }

    public void setFeatureKeyword(StringFilter featureKeyword) {
        this.featureKeyword = featureKeyword;
    }

    public StringFilter getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(StringFilter suggestion) {
        this.suggestion = suggestion;
    }

    public StringFilter getReleaseScope() {
        return releaseScope;
    }

    public void setReleaseScope(StringFilter releaseScope) {
        this.releaseScope = releaseScope;
    }

    public LongFilter getVerifyRecId() {
        return verifyRecId;
    }

    public void setVerifyRecId(LongFilter verifyRecId) {
        this.verifyRecId = verifyRecId;
    }

    public LongFilter getParaTypeId() {
        return paraTypeId;
    }

    public void setParaTypeId(LongFilter paraTypeId) {
        this.paraTypeId = paraTypeId;
    }

    public LongFilter getParaClassId() {
        return paraClassId;
    }

    public void setParaClassId(LongFilter paraClassId) {
        this.paraClassId = paraClassId;
    }

    public LongFilter getParaCatId() {
        return paraCatId;
    }

    public void setParaCatId(LongFilter paraCatId) {
        this.paraCatId = paraCatId;
    }

    public LongFilter getParaStateId() {
        return paraStateId;
    }

    public void setParaStateId(LongFilter paraStateId) {
        this.paraStateId = paraStateId;
    }

    public LongFilter getParaSourceId() {
        return paraSourceId;
    }

    public void setParaSourceId(LongFilter paraSourceId) {
        this.paraSourceId = paraSourceId;
    }

    public LongFilter getParaAttrId() {
        return paraAttrId;
    }

    public void setParaAttrId(LongFilter paraAttrId) {
        this.paraAttrId = paraAttrId;
    }

    public LongFilter getParaPropId() {
        return paraPropId;
    }

    public void setParaPropId(LongFilter paraPropId) {
        this.paraPropId = paraPropId;
    }

    public LongFilter getCreatedById() {
        return createdById;
    }

    public void setCreatedById(LongFilter createdById) {
        this.createdById = createdById;
    }

    public LongFilter getCreatedDepById() {
        return createdDepById;
    }

    public void setCreatedDepById(LongFilter createdDepById) {
        this.createdDepById = createdDepById;
    }

    public LongFilter getOwnerById() {
        return ownerById;
    }

    public void setOwnerById(LongFilter ownerById) {
        this.ownerById = ownerById;
    }

    public LongFilter getOwnerDepById() {
        return ownerDepById;
    }

    public void setOwnerDepById(LongFilter ownerDepById) {
        this.ownerDepById = ownerDepById;
    }

    public LongFilter getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(LongFilter modifiedById) {
        this.modifiedById = modifiedById;
    }

    public LongFilter getModifiedDepById() {
        return modifiedDepById;
    }

    public void setModifiedDepById(LongFilter modifiedDepById) {
        this.modifiedDepById = modifiedDepById;
    }

    public LongFilter getVerifiedById() {
        return verifiedById;
    }

    public void setVerifiedById(LongFilter verifiedById) {
        this.verifiedById = verifiedById;
    }

    public LongFilter getVerifiedDepById() {
        return verifiedDepById;
    }

    public void setVerifiedDepById(LongFilter verifiedDepById) {
        this.verifiedDepById = verifiedDepById;
    }

    public LongFilter getPublishedById() {
        return publishedById;
    }

    public void setPublishedById(LongFilter publishedById) {
        this.publishedById = publishedById;
    }

    public LongFilter getPublishedDepById() {
        return publishedDepById;
    }

    public void setPublishedDepById(LongFilter publishedDepById) {
        this.publishedDepById = publishedDepById;
    }

    public LongFilter getParentId() {
        return parentId;
    }

    public void setParentId(LongFilter parentId) {
        this.parentId = parentId;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
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
        final PlanInfoCriteria that = (PlanInfoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(sortString, that.sortString) &&
            Objects.equals(descString, that.descString) &&
            Objects.equals(jsonString, that.jsonString) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(refEvent, that.refEvent) &&
            Objects.equals(attachmentPath, that.attachmentPath) &&
            Objects.equals(attachmentName, that.attachmentName) &&
            Objects.equals(imageBlobName, that.imageBlobName) &&
            Objects.equals(usingFlag, that.usingFlag) &&
            Objects.equals(validType, that.validType) &&
            Objects.equals(validBegin, that.validBegin) &&
            Objects.equals(validEnd, that.validEnd) &&
            Objects.equals(insertTime, that.insertTime) &&
            Objects.equals(updateTime, that.updateTime) &&
            Objects.equals(publishedTime, that.publishedTime) &&
            Objects.equals(verifyTime, that.verifyTime) &&
            Objects.equals(verifyNeed, that.verifyNeed) &&
            Objects.equals(verifyOpinion, that.verifyOpinion) &&
            Objects.equals(viewCount, that.viewCount) &&
            Objects.equals(viewPermission, that.viewPermission) &&
            Objects.equals(viewPermPersion, that.viewPermPersion) &&
            Objects.equals(serialNumber, that.serialNumber) &&
            Objects.equals(versionNumber, that.versionNumber) &&
            Objects.equals(paraSourceString, that.paraSourceString) &&
            Objects.equals(featureKeyword, that.featureKeyword) &&
            Objects.equals(suggestion, that.suggestion) &&
            Objects.equals(releaseScope, that.releaseScope) &&
            Objects.equals(verifyRecId, that.verifyRecId) &&
            Objects.equals(paraTypeId, that.paraTypeId) &&
            Objects.equals(paraClassId, that.paraClassId) &&
            Objects.equals(paraCatId, that.paraCatId) &&
            Objects.equals(paraStateId, that.paraStateId) &&
            Objects.equals(paraSourceId, that.paraSourceId) &&
            Objects.equals(paraAttrId, that.paraAttrId) &&
            Objects.equals(paraPropId, that.paraPropId) &&
            Objects.equals(createdById, that.createdById) &&
            Objects.equals(createdDepById, that.createdDepById) &&
            Objects.equals(ownerById, that.ownerById) &&
            Objects.equals(ownerDepById, that.ownerDepById) &&
            Objects.equals(modifiedById, that.modifiedById) &&
            Objects.equals(modifiedDepById, that.modifiedDepById) &&
            Objects.equals(verifiedById, that.verifiedById) &&
            Objects.equals(verifiedDepById, that.verifiedDepById) &&
            Objects.equals(publishedById, that.publishedById) &&
            Objects.equals(publishedDepById, that.publishedDepById) &&
            Objects.equals(parentId, that.parentId) &&
            Objects.equals(companyId, that.companyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        sortString,
        descString,
        jsonString,
        remarks,
        refEvent,
        attachmentPath,
        attachmentName,
        imageBlobName,
        usingFlag,
        validType,
        validBegin,
        validEnd,
        insertTime,
        updateTime,
        publishedTime,
        verifyTime,
        verifyNeed,
        verifyOpinion,
        viewCount,
        viewPermission,
        viewPermPersion,
        serialNumber,
        versionNumber,
        paraSourceString,
        featureKeyword,
        suggestion,
        releaseScope,
        verifyRecId,
        paraTypeId,
        paraClassId,
        paraCatId,
        paraStateId,
        paraSourceId,
        paraAttrId,
        paraPropId,
        createdById,
        createdDepById,
        ownerById,
        ownerDepById,
        modifiedById,
        modifiedDepById,
        verifiedById,
        verifiedDepById,
        publishedById,
        publishedDepById,
        parentId,
        companyId
        );
    }

    @Override
    public String toString() {
        return "PlanInfoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (sortString != null ? "sortString=" + sortString + ", " : "") +
                (descString != null ? "descString=" + descString + ", " : "") +
                (jsonString != null ? "jsonString=" + jsonString + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
                (refEvent != null ? "refEvent=" + refEvent + ", " : "") +
                (attachmentPath != null ? "attachmentPath=" + attachmentPath + ", " : "") +
                (attachmentName != null ? "attachmentName=" + attachmentName + ", " : "") +
                (imageBlobName != null ? "imageBlobName=" + imageBlobName + ", " : "") +
                (usingFlag != null ? "usingFlag=" + usingFlag + ", " : "") +
                (validType != null ? "validType=" + validType + ", " : "") +
                (validBegin != null ? "validBegin=" + validBegin + ", " : "") +
                (validEnd != null ? "validEnd=" + validEnd + ", " : "") +
                (insertTime != null ? "insertTime=" + insertTime + ", " : "") +
                (updateTime != null ? "updateTime=" + updateTime + ", " : "") +
                (publishedTime != null ? "publishedTime=" + publishedTime + ", " : "") +
                (verifyTime != null ? "verifyTime=" + verifyTime + ", " : "") +
                (verifyNeed != null ? "verifyNeed=" + verifyNeed + ", " : "") +
                (verifyOpinion != null ? "verifyOpinion=" + verifyOpinion + ", " : "") +
                (viewCount != null ? "viewCount=" + viewCount + ", " : "") +
                (viewPermission != null ? "viewPermission=" + viewPermission + ", " : "") +
                (viewPermPersion != null ? "viewPermPersion=" + viewPermPersion + ", " : "") +
                (serialNumber != null ? "serialNumber=" + serialNumber + ", " : "") +
                (versionNumber != null ? "versionNumber=" + versionNumber + ", " : "") +
                (paraSourceString != null ? "paraSourceString=" + paraSourceString + ", " : "") +
                (featureKeyword != null ? "featureKeyword=" + featureKeyword + ", " : "") +
                (suggestion != null ? "suggestion=" + suggestion + ", " : "") +
                (releaseScope != null ? "releaseScope=" + releaseScope + ", " : "") +
                (verifyRecId != null ? "verifyRecId=" + verifyRecId + ", " : "") +
                (paraTypeId != null ? "paraTypeId=" + paraTypeId + ", " : "") +
                (paraClassId != null ? "paraClassId=" + paraClassId + ", " : "") +
                (paraCatId != null ? "paraCatId=" + paraCatId + ", " : "") +
                (paraStateId != null ? "paraStateId=" + paraStateId + ", " : "") +
                (paraSourceId != null ? "paraSourceId=" + paraSourceId + ", " : "") +
                (paraAttrId != null ? "paraAttrId=" + paraAttrId + ", " : "") +
                (paraPropId != null ? "paraPropId=" + paraPropId + ", " : "") +
                (createdById != null ? "createdById=" + createdById + ", " : "") +
                (createdDepById != null ? "createdDepById=" + createdDepById + ", " : "") +
                (ownerById != null ? "ownerById=" + ownerById + ", " : "") +
                (ownerDepById != null ? "ownerDepById=" + ownerDepById + ", " : "") +
                (modifiedById != null ? "modifiedById=" + modifiedById + ", " : "") +
                (modifiedDepById != null ? "modifiedDepById=" + modifiedDepById + ", " : "") +
                (verifiedById != null ? "verifiedById=" + verifiedById + ", " : "") +
                (verifiedDepById != null ? "verifiedDepById=" + verifiedDepById + ", " : "") +
                (publishedById != null ? "publishedById=" + publishedById + ", " : "") +
                (publishedDepById != null ? "publishedDepById=" + publishedDepById + ", " : "") +
                (parentId != null ? "parentId=" + parentId + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
            "}";
    }

}
