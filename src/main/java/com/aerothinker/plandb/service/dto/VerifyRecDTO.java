package com.aerothinker.plandb.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the VerifyRec entity.
 */
public class VerifyRecDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 256)
    private String name;

    @Size(max = 256)
    private String descString;

    private Boolean verifyResult;

    private Integer verifyScore;

    @Size(max = 1000)
    private String remarks;

    private Instant insertTime;

    private Instant updateTime;

    private Long createdById;

    private String createdByUserName;

    private Long modifiedById;

    private String modifiedByUserName;

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

    public String getDescString() {
        return descString;
    }

    public void setDescString(String descString) {
        this.descString = descString;
    }

    public Boolean isVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(Boolean verifyResult) {
        this.verifyResult = verifyResult;
    }

    public Integer getVerifyScore() {
        return verifyScore;
    }

    public void setVerifyScore(Integer verifyScore) {
        this.verifyScore = verifyScore;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VerifyRecDTO verifyRecDTO = (VerifyRecDTO) o;
        if (verifyRecDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), verifyRecDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VerifyRecDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", descString='" + getDescString() + "'" +
            ", verifyResult='" + isVerifyResult() + "'" +
            ", verifyScore=" + getVerifyScore() +
            ", remarks='" + getRemarks() + "'" +
            ", insertTime='" + getInsertTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", createdBy=" + getCreatedById() +
            ", createdBy='" + getCreatedByUserName() + "'" +
            ", modifiedBy=" + getModifiedById() +
            ", modifiedBy='" + getModifiedByUserName() + "'" +
            "}";
    }
}
