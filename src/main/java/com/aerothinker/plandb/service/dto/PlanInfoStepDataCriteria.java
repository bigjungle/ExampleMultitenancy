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
 * Criteria class for the PlanInfoStepData entity. This class is used in PlanInfoStepDataResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /plan-info-step-data?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PlanInfoStepDataCriteria implements Serializable {
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

    private StringFilter attachmentPath;

    private StringFilter attachmentName;

    private StringFilter imageBlobName;

    private BooleanFilter usingFlag;

    private ValidTypeFilter validType;

    private InstantFilter validBegin;

    private InstantFilter validEnd;

    private InstantFilter insertTime;

    private InstantFilter updateTime;

    private InstantFilter verifyTime;

    private BooleanFilter verifyNeed;

    private StringFilter verifyOpinion;

    private IntegerFilter viewCount;

    private ViewPermissionFilter viewPermission;

    private StringFilter viewPermPersion;

    private StringFilter serialNumber;

    private StringFilter versionNumber;

    private IntegerFilter stepOrder;

    private LongFilter createdById;

    private LongFilter createdDepById;

    private LongFilter modifiedById;

    private LongFilter modifiedDepById;

    private LongFilter verifiedById;

    private LongFilter verifiedDepById;

    private LongFilter planInfoStepId;

    private LongFilter planInfoDataId;

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

    public IntegerFilter getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(IntegerFilter stepOrder) {
        this.stepOrder = stepOrder;
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

    public LongFilter getPlanInfoStepId() {
        return planInfoStepId;
    }

    public void setPlanInfoStepId(LongFilter planInfoStepId) {
        this.planInfoStepId = planInfoStepId;
    }

    public LongFilter getPlanInfoDataId() {
        return planInfoDataId;
    }

    public void setPlanInfoDataId(LongFilter planInfoDataId) {
        this.planInfoDataId = planInfoDataId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PlanInfoStepDataCriteria that = (PlanInfoStepDataCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(sortString, that.sortString) &&
            Objects.equals(descString, that.descString) &&
            Objects.equals(jsonString, that.jsonString) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(attachmentPath, that.attachmentPath) &&
            Objects.equals(attachmentName, that.attachmentName) &&
            Objects.equals(imageBlobName, that.imageBlobName) &&
            Objects.equals(usingFlag, that.usingFlag) &&
            Objects.equals(validType, that.validType) &&
            Objects.equals(validBegin, that.validBegin) &&
            Objects.equals(validEnd, that.validEnd) &&
            Objects.equals(insertTime, that.insertTime) &&
            Objects.equals(updateTime, that.updateTime) &&
            Objects.equals(verifyTime, that.verifyTime) &&
            Objects.equals(verifyNeed, that.verifyNeed) &&
            Objects.equals(verifyOpinion, that.verifyOpinion) &&
            Objects.equals(viewCount, that.viewCount) &&
            Objects.equals(viewPermission, that.viewPermission) &&
            Objects.equals(viewPermPersion, that.viewPermPersion) &&
            Objects.equals(serialNumber, that.serialNumber) &&
            Objects.equals(versionNumber, that.versionNumber) &&
            Objects.equals(stepOrder, that.stepOrder) &&
            Objects.equals(createdById, that.createdById) &&
            Objects.equals(createdDepById, that.createdDepById) &&
            Objects.equals(modifiedById, that.modifiedById) &&
            Objects.equals(modifiedDepById, that.modifiedDepById) &&
            Objects.equals(verifiedById, that.verifiedById) &&
            Objects.equals(verifiedDepById, that.verifiedDepById) &&
            Objects.equals(planInfoStepId, that.planInfoStepId) &&
            Objects.equals(planInfoDataId, that.planInfoDataId);
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
        attachmentPath,
        attachmentName,
        imageBlobName,
        usingFlag,
        validType,
        validBegin,
        validEnd,
        insertTime,
        updateTime,
        verifyTime,
        verifyNeed,
        verifyOpinion,
        viewCount,
        viewPermission,
        viewPermPersion,
        serialNumber,
        versionNumber,
        stepOrder,
        createdById,
        createdDepById,
        modifiedById,
        modifiedDepById,
        verifiedById,
        verifiedDepById,
        planInfoStepId,
        planInfoDataId
        );
    }

    @Override
    public String toString() {
        return "PlanInfoStepDataCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (sortString != null ? "sortString=" + sortString + ", " : "") +
                (descString != null ? "descString=" + descString + ", " : "") +
                (jsonString != null ? "jsonString=" + jsonString + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
                (attachmentPath != null ? "attachmentPath=" + attachmentPath + ", " : "") +
                (attachmentName != null ? "attachmentName=" + attachmentName + ", " : "") +
                (imageBlobName != null ? "imageBlobName=" + imageBlobName + ", " : "") +
                (usingFlag != null ? "usingFlag=" + usingFlag + ", " : "") +
                (validType != null ? "validType=" + validType + ", " : "") +
                (validBegin != null ? "validBegin=" + validBegin + ", " : "") +
                (validEnd != null ? "validEnd=" + validEnd + ", " : "") +
                (insertTime != null ? "insertTime=" + insertTime + ", " : "") +
                (updateTime != null ? "updateTime=" + updateTime + ", " : "") +
                (verifyTime != null ? "verifyTime=" + verifyTime + ", " : "") +
                (verifyNeed != null ? "verifyNeed=" + verifyNeed + ", " : "") +
                (verifyOpinion != null ? "verifyOpinion=" + verifyOpinion + ", " : "") +
                (viewCount != null ? "viewCount=" + viewCount + ", " : "") +
                (viewPermission != null ? "viewPermission=" + viewPermission + ", " : "") +
                (viewPermPersion != null ? "viewPermPersion=" + viewPermPersion + ", " : "") +
                (serialNumber != null ? "serialNumber=" + serialNumber + ", " : "") +
                (versionNumber != null ? "versionNumber=" + versionNumber + ", " : "") +
                (stepOrder != null ? "stepOrder=" + stepOrder + ", " : "") +
                (createdById != null ? "createdById=" + createdById + ", " : "") +
                (createdDepById != null ? "createdDepById=" + createdDepById + ", " : "") +
                (modifiedById != null ? "modifiedById=" + modifiedById + ", " : "") +
                (modifiedDepById != null ? "modifiedDepById=" + modifiedDepById + ", " : "") +
                (verifiedById != null ? "verifiedById=" + verifiedById + ", " : "") +
                (verifiedDepById != null ? "verifiedDepById=" + verifiedDepById + ", " : "") +
                (planInfoStepId != null ? "planInfoStepId=" + planInfoStepId + ", " : "") +
                (planInfoDataId != null ? "planInfoDataId=" + planInfoDataId + ", " : "") +
            "}";
    }

}
