package com.aerothinker.plandb.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.aerothinker.plandb.domain.enumeration.ValidType;

/**
 * A DTO for the RmsUser entity.
 */
public class RmsUserDTO implements Serializable {

    private Long id;

    @Size(max = 256)
    private String userName;

    @Size(max = 256)
    private String personId;

    @Size(max = 640)
    private String userPassword;

    @Size(max = 640)
    private String processPassword;

    @Size(max = 10)
    private String userSort;

    @Size(max = 256)
    private String userDesc;

    private Integer userPasswordValiInstantTimes;

    private Boolean userPasswordLockFlag;

    private Integer procPasswordValiInstantTimes;

    @Size(max = 256)
    private String procPasswordLockFlag;

    @Size(max = 256)
    private String userProp;

    @Size(max = 256)
    private String by01;

    @Size(max = 256)
    private String by02;

    @Size(max = 256)
    private String by03;

    @Size(max = 256)
    private String by04;

    @Size(max = 256)
    private String by05;

    private ValidType validType;

    private Instant validBegin;

    private Instant validEnd;

    private Instant insertTime;

    private Instant updateTime;

    private Instant verifyTime;

    @Size(max = 256)
    private String serialNumber;

    private Long createdById;

    private String createdByUserName;

    private Long modifiedById;

    private String modifiedByUserName;

    private Long verifiedById;

    private String verifiedByUserName;

    private Set<RmsRoleDTO> rmsRoles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getProcessPassword() {
        return processPassword;
    }

    public void setProcessPassword(String processPassword) {
        this.processPassword = processPassword;
    }

    public String getUserSort() {
        return userSort;
    }

    public void setUserSort(String userSort) {
        this.userSort = userSort;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Integer getUserPasswordValiInstantTimes() {
        return userPasswordValiInstantTimes;
    }

    public void setUserPasswordValiInstantTimes(Integer userPasswordValiInstantTimes) {
        this.userPasswordValiInstantTimes = userPasswordValiInstantTimes;
    }

    public Boolean isUserPasswordLockFlag() {
        return userPasswordLockFlag;
    }

    public void setUserPasswordLockFlag(Boolean userPasswordLockFlag) {
        this.userPasswordLockFlag = userPasswordLockFlag;
    }

    public Integer getProcPasswordValiInstantTimes() {
        return procPasswordValiInstantTimes;
    }

    public void setProcPasswordValiInstantTimes(Integer procPasswordValiInstantTimes) {
        this.procPasswordValiInstantTimes = procPasswordValiInstantTimes;
    }

    public String getProcPasswordLockFlag() {
        return procPasswordLockFlag;
    }

    public void setProcPasswordLockFlag(String procPasswordLockFlag) {
        this.procPasswordLockFlag = procPasswordLockFlag;
    }

    public String getUserProp() {
        return userProp;
    }

    public void setUserProp(String userProp) {
        this.userProp = userProp;
    }

    public String getBy01() {
        return by01;
    }

    public void setBy01(String by01) {
        this.by01 = by01;
    }

    public String getBy02() {
        return by02;
    }

    public void setBy02(String by02) {
        this.by02 = by02;
    }

    public String getBy03() {
        return by03;
    }

    public void setBy03(String by03) {
        this.by03 = by03;
    }

    public String getBy04() {
        return by04;
    }

    public void setBy04(String by04) {
        this.by04 = by04;
    }

    public String getBy05() {
        return by05;
    }

    public void setBy05(String by05) {
        this.by05 = by05;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public Set<RmsRoleDTO> getRmsRoles() {
        return rmsRoles;
    }

    public void setRmsRoles(Set<RmsRoleDTO> rmsRoles) {
        this.rmsRoles = rmsRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RmsUserDTO rmsUserDTO = (RmsUserDTO) o;
        if (rmsUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rmsUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RmsUserDTO{" +
            "id=" + getId() +
            ", userName='" + getUserName() + "'" +
            ", personId='" + getPersonId() + "'" +
            ", userPassword='" + getUserPassword() + "'" +
            ", processPassword='" + getProcessPassword() + "'" +
            ", userSort='" + getUserSort() + "'" +
            ", userDesc='" + getUserDesc() + "'" +
            ", userPasswordValiInstantTimes=" + getUserPasswordValiInstantTimes() +
            ", userPasswordLockFlag='" + isUserPasswordLockFlag() + "'" +
            ", procPasswordValiInstantTimes=" + getProcPasswordValiInstantTimes() +
            ", procPasswordLockFlag='" + getProcPasswordLockFlag() + "'" +
            ", userProp='" + getUserProp() + "'" +
            ", by01='" + getBy01() + "'" +
            ", by02='" + getBy02() + "'" +
            ", by03='" + getBy03() + "'" +
            ", by04='" + getBy04() + "'" +
            ", by05='" + getBy05() + "'" +
            ", validType='" + getValidType() + "'" +
            ", validBegin='" + getValidBegin() + "'" +
            ", validEnd='" + getValidEnd() + "'" +
            ", insertTime='" + getInsertTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", verifyTime='" + getVerifyTime() + "'" +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", createdBy=" + getCreatedById() +
            ", createdBy='" + getCreatedByUserName() + "'" +
            ", modifiedBy=" + getModifiedById() +
            ", modifiedBy='" + getModifiedByUserName() + "'" +
            ", verifiedBy=" + getVerifiedById() +
            ", verifiedBy='" + getVerifiedByUserName() + "'" +
            "}";
    }
}
