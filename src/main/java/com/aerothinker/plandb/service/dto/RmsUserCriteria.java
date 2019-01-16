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
 * Criteria class for the RmsUser entity. This class is used in RmsUserResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /rms-users?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RmsUserCriteria implements Serializable {
    /**
     * Class for filtering ValidType
     */
    public static class ValidTypeFilter extends Filter<ValidType> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter userName;

    private StringFilter personId;

    private StringFilter userPassword;

    private StringFilter processPassword;

    private StringFilter userSort;

    private StringFilter userDesc;

    private IntegerFilter userPasswordValiInstantTimes;

    private BooleanFilter userPasswordLockFlag;

    private IntegerFilter procPasswordValiInstantTimes;

    private StringFilter procPasswordLockFlag;

    private StringFilter userProp;

    private StringFilter by01;

    private StringFilter by02;

    private StringFilter by03;

    private StringFilter by04;

    private StringFilter by05;

    private ValidTypeFilter validType;

    private InstantFilter validBegin;

    private InstantFilter validEnd;

    private InstantFilter insertTime;

    private InstantFilter updateTime;

    private InstantFilter verifyTime;

    private StringFilter serialNumber;

    private LongFilter createdById;

    private LongFilter modifiedById;

    private LongFilter verifiedById;

    private LongFilter rmsRoleId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getUserName() {
        return userName;
    }

    public void setUserName(StringFilter userName) {
        this.userName = userName;
    }

    public StringFilter getPersonId() {
        return personId;
    }

    public void setPersonId(StringFilter personId) {
        this.personId = personId;
    }

    public StringFilter getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(StringFilter userPassword) {
        this.userPassword = userPassword;
    }

    public StringFilter getProcessPassword() {
        return processPassword;
    }

    public void setProcessPassword(StringFilter processPassword) {
        this.processPassword = processPassword;
    }

    public StringFilter getUserSort() {
        return userSort;
    }

    public void setUserSort(StringFilter userSort) {
        this.userSort = userSort;
    }

    public StringFilter getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(StringFilter userDesc) {
        this.userDesc = userDesc;
    }

    public IntegerFilter getUserPasswordValiInstantTimes() {
        return userPasswordValiInstantTimes;
    }

    public void setUserPasswordValiInstantTimes(IntegerFilter userPasswordValiInstantTimes) {
        this.userPasswordValiInstantTimes = userPasswordValiInstantTimes;
    }

    public BooleanFilter getUserPasswordLockFlag() {
        return userPasswordLockFlag;
    }

    public void setUserPasswordLockFlag(BooleanFilter userPasswordLockFlag) {
        this.userPasswordLockFlag = userPasswordLockFlag;
    }

    public IntegerFilter getProcPasswordValiInstantTimes() {
        return procPasswordValiInstantTimes;
    }

    public void setProcPasswordValiInstantTimes(IntegerFilter procPasswordValiInstantTimes) {
        this.procPasswordValiInstantTimes = procPasswordValiInstantTimes;
    }

    public StringFilter getProcPasswordLockFlag() {
        return procPasswordLockFlag;
    }

    public void setProcPasswordLockFlag(StringFilter procPasswordLockFlag) {
        this.procPasswordLockFlag = procPasswordLockFlag;
    }

    public StringFilter getUserProp() {
        return userProp;
    }

    public void setUserProp(StringFilter userProp) {
        this.userProp = userProp;
    }

    public StringFilter getBy01() {
        return by01;
    }

    public void setBy01(StringFilter by01) {
        this.by01 = by01;
    }

    public StringFilter getBy02() {
        return by02;
    }

    public void setBy02(StringFilter by02) {
        this.by02 = by02;
    }

    public StringFilter getBy03() {
        return by03;
    }

    public void setBy03(StringFilter by03) {
        this.by03 = by03;
    }

    public StringFilter getBy04() {
        return by04;
    }

    public void setBy04(StringFilter by04) {
        this.by04 = by04;
    }

    public StringFilter getBy05() {
        return by05;
    }

    public void setBy05(StringFilter by05) {
        this.by05 = by05;
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

    public StringFilter getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(StringFilter serialNumber) {
        this.serialNumber = serialNumber;
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

    public LongFilter getVerifiedById() {
        return verifiedById;
    }

    public void setVerifiedById(LongFilter verifiedById) {
        this.verifiedById = verifiedById;
    }

    public LongFilter getRmsRoleId() {
        return rmsRoleId;
    }

    public void setRmsRoleId(LongFilter rmsRoleId) {
        this.rmsRoleId = rmsRoleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RmsUserCriteria that = (RmsUserCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(userPassword, that.userPassword) &&
            Objects.equals(processPassword, that.processPassword) &&
            Objects.equals(userSort, that.userSort) &&
            Objects.equals(userDesc, that.userDesc) &&
            Objects.equals(userPasswordValiInstantTimes, that.userPasswordValiInstantTimes) &&
            Objects.equals(userPasswordLockFlag, that.userPasswordLockFlag) &&
            Objects.equals(procPasswordValiInstantTimes, that.procPasswordValiInstantTimes) &&
            Objects.equals(procPasswordLockFlag, that.procPasswordLockFlag) &&
            Objects.equals(userProp, that.userProp) &&
            Objects.equals(by01, that.by01) &&
            Objects.equals(by02, that.by02) &&
            Objects.equals(by03, that.by03) &&
            Objects.equals(by04, that.by04) &&
            Objects.equals(by05, that.by05) &&
            Objects.equals(validType, that.validType) &&
            Objects.equals(validBegin, that.validBegin) &&
            Objects.equals(validEnd, that.validEnd) &&
            Objects.equals(insertTime, that.insertTime) &&
            Objects.equals(updateTime, that.updateTime) &&
            Objects.equals(verifyTime, that.verifyTime) &&
            Objects.equals(serialNumber, that.serialNumber) &&
            Objects.equals(createdById, that.createdById) &&
            Objects.equals(modifiedById, that.modifiedById) &&
            Objects.equals(verifiedById, that.verifiedById) &&
            Objects.equals(rmsRoleId, that.rmsRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        userName,
        personId,
        userPassword,
        processPassword,
        userSort,
        userDesc,
        userPasswordValiInstantTimes,
        userPasswordLockFlag,
        procPasswordValiInstantTimes,
        procPasswordLockFlag,
        userProp,
        by01,
        by02,
        by03,
        by04,
        by05,
        validType,
        validBegin,
        validEnd,
        insertTime,
        updateTime,
        verifyTime,
        serialNumber,
        createdById,
        modifiedById,
        verifiedById,
        rmsRoleId
        );
    }

    @Override
    public String toString() {
        return "RmsUserCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (userName != null ? "userName=" + userName + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (userPassword != null ? "userPassword=" + userPassword + ", " : "") +
                (processPassword != null ? "processPassword=" + processPassword + ", " : "") +
                (userSort != null ? "userSort=" + userSort + ", " : "") +
                (userDesc != null ? "userDesc=" + userDesc + ", " : "") +
                (userPasswordValiInstantTimes != null ? "userPasswordValiInstantTimes=" + userPasswordValiInstantTimes + ", " : "") +
                (userPasswordLockFlag != null ? "userPasswordLockFlag=" + userPasswordLockFlag + ", " : "") +
                (procPasswordValiInstantTimes != null ? "procPasswordValiInstantTimes=" + procPasswordValiInstantTimes + ", " : "") +
                (procPasswordLockFlag != null ? "procPasswordLockFlag=" + procPasswordLockFlag + ", " : "") +
                (userProp != null ? "userProp=" + userProp + ", " : "") +
                (by01 != null ? "by01=" + by01 + ", " : "") +
                (by02 != null ? "by02=" + by02 + ", " : "") +
                (by03 != null ? "by03=" + by03 + ", " : "") +
                (by04 != null ? "by04=" + by04 + ", " : "") +
                (by05 != null ? "by05=" + by05 + ", " : "") +
                (validType != null ? "validType=" + validType + ", " : "") +
                (validBegin != null ? "validBegin=" + validBegin + ", " : "") +
                (validEnd != null ? "validEnd=" + validEnd + ", " : "") +
                (insertTime != null ? "insertTime=" + insertTime + ", " : "") +
                (updateTime != null ? "updateTime=" + updateTime + ", " : "") +
                (verifyTime != null ? "verifyTime=" + verifyTime + ", " : "") +
                (serialNumber != null ? "serialNumber=" + serialNumber + ", " : "") +
                (createdById != null ? "createdById=" + createdById + ", " : "") +
                (modifiedById != null ? "modifiedById=" + modifiedById + ", " : "") +
                (verifiedById != null ? "verifiedById=" + verifiedById + ", " : "") +
                (rmsRoleId != null ? "rmsRoleId=" + rmsRoleId + ", " : "") +
            "}";
    }

}
