package com.aerothinker.plandb.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.aerothinker.plandb.domain.enumeration.ValidType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the PlanInfoAtch entity. This class is used in PlanInfoAtchResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /plan-info-atches?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PlanInfoAtchCriteria implements Serializable {
    /**
     * Class for filtering ValidType
     */
    public static class ValidTypeFilter extends Filter<ValidType> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter storeDir;

    private StringFilter storeName;

    private StringFilter sortString;

    private StringFilter fileType;

    private BooleanFilter deleteFlag;

    private StringFilter storeType;

    private StringFilter ver;

    private StringFilter encryptedFlag;

    private StringFilter encryptedType;

    private StringFilter jsonString;

    private StringFilter remarks;

    private StringFilter attachmentPath;

    private StringFilter attachmentName;

    private StringFilter imageBlobName;

    private ValidTypeFilter validType;

    private InstantFilter validBegin;

    private InstantFilter validEnd;

    private InstantFilter insertTime;

    private InstantFilter updateTime;

    private InstantFilter publishedTime;

    private LongFilter createdById;

    private LongFilter modifiedById;

    private LongFilter planInfoId;

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

    public StringFilter getStoreDir() {
        return storeDir;
    }

    public void setStoreDir(StringFilter storeDir) {
        this.storeDir = storeDir;
    }

    public StringFilter getStoreName() {
        return storeName;
    }

    public void setStoreName(StringFilter storeName) {
        this.storeName = storeName;
    }

    public StringFilter getSortString() {
        return sortString;
    }

    public void setSortString(StringFilter sortString) {
        this.sortString = sortString;
    }

    public StringFilter getFileType() {
        return fileType;
    }

    public void setFileType(StringFilter fileType) {
        this.fileType = fileType;
    }

    public BooleanFilter getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(BooleanFilter deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public StringFilter getStoreType() {
        return storeType;
    }

    public void setStoreType(StringFilter storeType) {
        this.storeType = storeType;
    }

    public StringFilter getVer() {
        return ver;
    }

    public void setVer(StringFilter ver) {
        this.ver = ver;
    }

    public StringFilter getEncryptedFlag() {
        return encryptedFlag;
    }

    public void setEncryptedFlag(StringFilter encryptedFlag) {
        this.encryptedFlag = encryptedFlag;
    }

    public StringFilter getEncryptedType() {
        return encryptedType;
    }

    public void setEncryptedType(StringFilter encryptedType) {
        this.encryptedType = encryptedType;
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

    public LongFilter getCreatedById() {
        return createdById;
    }

    public void setCreatedById(LongFilter createdById) {
        this.createdById = createdById;
    }

    public LongFilter getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(LongFilter modifiedById) {
        this.modifiedById = modifiedById;
    }

    public LongFilter getPlanInfoId() {
        return planInfoId;
    }

    public void setPlanInfoId(LongFilter planInfoId) {
        this.planInfoId = planInfoId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PlanInfoAtchCriteria that = (PlanInfoAtchCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(storeDir, that.storeDir) &&
            Objects.equals(storeName, that.storeName) &&
            Objects.equals(sortString, that.sortString) &&
            Objects.equals(fileType, that.fileType) &&
            Objects.equals(deleteFlag, that.deleteFlag) &&
            Objects.equals(storeType, that.storeType) &&
            Objects.equals(ver, that.ver) &&
            Objects.equals(encryptedFlag, that.encryptedFlag) &&
            Objects.equals(encryptedType, that.encryptedType) &&
            Objects.equals(jsonString, that.jsonString) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(attachmentPath, that.attachmentPath) &&
            Objects.equals(attachmentName, that.attachmentName) &&
            Objects.equals(imageBlobName, that.imageBlobName) &&
            Objects.equals(validType, that.validType) &&
            Objects.equals(validBegin, that.validBegin) &&
            Objects.equals(validEnd, that.validEnd) &&
            Objects.equals(insertTime, that.insertTime) &&
            Objects.equals(updateTime, that.updateTime) &&
            Objects.equals(publishedTime, that.publishedTime) &&
            Objects.equals(createdById, that.createdById) &&
            Objects.equals(modifiedById, that.modifiedById) &&
            Objects.equals(planInfoId, that.planInfoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        storeDir,
        storeName,
        sortString,
        fileType,
        deleteFlag,
        storeType,
        ver,
        encryptedFlag,
        encryptedType,
        jsonString,
        remarks,
        attachmentPath,
        attachmentName,
        imageBlobName,
        validType,
        validBegin,
        validEnd,
        insertTime,
        updateTime,
        publishedTime,
        createdById,
        modifiedById,
        planInfoId
        );
    }

    @Override
    public String toString() {
        return "PlanInfoAtchCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (storeDir != null ? "storeDir=" + storeDir + ", " : "") +
                (storeName != null ? "storeName=" + storeName + ", " : "") +
                (sortString != null ? "sortString=" + sortString + ", " : "") +
                (fileType != null ? "fileType=" + fileType + ", " : "") +
                (deleteFlag != null ? "deleteFlag=" + deleteFlag + ", " : "") +
                (storeType != null ? "storeType=" + storeType + ", " : "") +
                (ver != null ? "ver=" + ver + ", " : "") +
                (encryptedFlag != null ? "encryptedFlag=" + encryptedFlag + ", " : "") +
                (encryptedType != null ? "encryptedType=" + encryptedType + ", " : "") +
                (jsonString != null ? "jsonString=" + jsonString + ", " : "") +
                (remarks != null ? "remarks=" + remarks + ", " : "") +
                (attachmentPath != null ? "attachmentPath=" + attachmentPath + ", " : "") +
                (attachmentName != null ? "attachmentName=" + attachmentName + ", " : "") +
                (imageBlobName != null ? "imageBlobName=" + imageBlobName + ", " : "") +
                (validType != null ? "validType=" + validType + ", " : "") +
                (validBegin != null ? "validBegin=" + validBegin + ", " : "") +
                (validEnd != null ? "validEnd=" + validEnd + ", " : "") +
                (insertTime != null ? "insertTime=" + insertTime + ", " : "") +
                (updateTime != null ? "updateTime=" + updateTime + ", " : "") +
                (publishedTime != null ? "publishedTime=" + publishedTime + ", " : "") +
                (createdById != null ? "createdById=" + createdById + ", " : "") +
                (modifiedById != null ? "modifiedById=" + modifiedById + ", " : "") +
                (planInfoId != null ? "planInfoId=" + planInfoId + ", " : "") +
            "}";
    }

}
