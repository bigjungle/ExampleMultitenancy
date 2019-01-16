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
 * Criteria class for the RmsDep entity. This class is used in RmsDepResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /rms-deps?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RmsDepCriteria implements Serializable {
    /**
     * Class for filtering ValidType
     */
    public static class ValidTypeFilter extends Filter<ValidType> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter depName;

    private StringFilter depTypeId;

    private StringFilter parentDepId;

    private BooleanFilter repealFlag;

    private StringFilter levelId;

    private StringFilter depSort;

    private StringFilter parentDepStr;

    private StringFilter childDepStr;

    private StringFilter depDesc;

    private StringFilter tel;

    private StringFilter fax;

    private StringFilter address;

    private StringFilter code;

    private StringFilter internet;

    private StringFilter mail;

    private StringFilter by01;

    private StringFilter by02;

    private StringFilter by03;

    private StringFilter by04;

    private StringFilter by05;

    private StringFilter by06;

    private StringFilter by07;

    private StringFilter by08;

    private StringFilter by09;

    private StringFilter by10;

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

    private LongFilter parentId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getDepName() {
        return depName;
    }

    public void setDepName(StringFilter depName) {
        this.depName = depName;
    }

    public StringFilter getDepTypeId() {
        return depTypeId;
    }

    public void setDepTypeId(StringFilter depTypeId) {
        this.depTypeId = depTypeId;
    }

    public StringFilter getParentDepId() {
        return parentDepId;
    }

    public void setParentDepId(StringFilter parentDepId) {
        this.parentDepId = parentDepId;
    }

    public BooleanFilter getRepealFlag() {
        return repealFlag;
    }

    public void setRepealFlag(BooleanFilter repealFlag) {
        this.repealFlag = repealFlag;
    }

    public StringFilter getLevelId() {
        return levelId;
    }

    public void setLevelId(StringFilter levelId) {
        this.levelId = levelId;
    }

    public StringFilter getDepSort() {
        return depSort;
    }

    public void setDepSort(StringFilter depSort) {
        this.depSort = depSort;
    }

    public StringFilter getParentDepStr() {
        return parentDepStr;
    }

    public void setParentDepStr(StringFilter parentDepStr) {
        this.parentDepStr = parentDepStr;
    }

    public StringFilter getChildDepStr() {
        return childDepStr;
    }

    public void setChildDepStr(StringFilter childDepStr) {
        this.childDepStr = childDepStr;
    }

    public StringFilter getDepDesc() {
        return depDesc;
    }

    public void setDepDesc(StringFilter depDesc) {
        this.depDesc = depDesc;
    }

    public StringFilter getTel() {
        return tel;
    }

    public void setTel(StringFilter tel) {
        this.tel = tel;
    }

    public StringFilter getFax() {
        return fax;
    }

    public void setFax(StringFilter fax) {
        this.fax = fax;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getInternet() {
        return internet;
    }

    public void setInternet(StringFilter internet) {
        this.internet = internet;
    }

    public StringFilter getMail() {
        return mail;
    }

    public void setMail(StringFilter mail) {
        this.mail = mail;
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

    public StringFilter getBy06() {
        return by06;
    }

    public void setBy06(StringFilter by06) {
        this.by06 = by06;
    }

    public StringFilter getBy07() {
        return by07;
    }

    public void setBy07(StringFilter by07) {
        this.by07 = by07;
    }

    public StringFilter getBy08() {
        return by08;
    }

    public void setBy08(StringFilter by08) {
        this.by08 = by08;
    }

    public StringFilter getBy09() {
        return by09;
    }

    public void setBy09(StringFilter by09) {
        this.by09 = by09;
    }

    public StringFilter getBy10() {
        return by10;
    }

    public void setBy10(StringFilter by10) {
        this.by10 = by10;
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

    public LongFilter getParentId() {
        return parentId;
    }

    public void setParentId(LongFilter parentId) {
        this.parentId = parentId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RmsDepCriteria that = (RmsDepCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(depName, that.depName) &&
            Objects.equals(depTypeId, that.depTypeId) &&
            Objects.equals(parentDepId, that.parentDepId) &&
            Objects.equals(repealFlag, that.repealFlag) &&
            Objects.equals(levelId, that.levelId) &&
            Objects.equals(depSort, that.depSort) &&
            Objects.equals(parentDepStr, that.parentDepStr) &&
            Objects.equals(childDepStr, that.childDepStr) &&
            Objects.equals(depDesc, that.depDesc) &&
            Objects.equals(tel, that.tel) &&
            Objects.equals(fax, that.fax) &&
            Objects.equals(address, that.address) &&
            Objects.equals(code, that.code) &&
            Objects.equals(internet, that.internet) &&
            Objects.equals(mail, that.mail) &&
            Objects.equals(by01, that.by01) &&
            Objects.equals(by02, that.by02) &&
            Objects.equals(by03, that.by03) &&
            Objects.equals(by04, that.by04) &&
            Objects.equals(by05, that.by05) &&
            Objects.equals(by06, that.by06) &&
            Objects.equals(by07, that.by07) &&
            Objects.equals(by08, that.by08) &&
            Objects.equals(by09, that.by09) &&
            Objects.equals(by10, that.by10) &&
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
            Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        depName,
        depTypeId,
        parentDepId,
        repealFlag,
        levelId,
        depSort,
        parentDepStr,
        childDepStr,
        depDesc,
        tel,
        fax,
        address,
        code,
        internet,
        mail,
        by01,
        by02,
        by03,
        by04,
        by05,
        by06,
        by07,
        by08,
        by09,
        by10,
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
        parentId
        );
    }

    @Override
    public String toString() {
        return "RmsDepCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (depName != null ? "depName=" + depName + ", " : "") +
                (depTypeId != null ? "depTypeId=" + depTypeId + ", " : "") +
                (parentDepId != null ? "parentDepId=" + parentDepId + ", " : "") +
                (repealFlag != null ? "repealFlag=" + repealFlag + ", " : "") +
                (levelId != null ? "levelId=" + levelId + ", " : "") +
                (depSort != null ? "depSort=" + depSort + ", " : "") +
                (parentDepStr != null ? "parentDepStr=" + parentDepStr + ", " : "") +
                (childDepStr != null ? "childDepStr=" + childDepStr + ", " : "") +
                (depDesc != null ? "depDesc=" + depDesc + ", " : "") +
                (tel != null ? "tel=" + tel + ", " : "") +
                (fax != null ? "fax=" + fax + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (internet != null ? "internet=" + internet + ", " : "") +
                (mail != null ? "mail=" + mail + ", " : "") +
                (by01 != null ? "by01=" + by01 + ", " : "") +
                (by02 != null ? "by02=" + by02 + ", " : "") +
                (by03 != null ? "by03=" + by03 + ", " : "") +
                (by04 != null ? "by04=" + by04 + ", " : "") +
                (by05 != null ? "by05=" + by05 + ", " : "") +
                (by06 != null ? "by06=" + by06 + ", " : "") +
                (by07 != null ? "by07=" + by07 + ", " : "") +
                (by08 != null ? "by08=" + by08 + ", " : "") +
                (by09 != null ? "by09=" + by09 + ", " : "") +
                (by10 != null ? "by10=" + by10 + ", " : "") +
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
                (parentId != null ? "parentId=" + parentId + ", " : "") +
            "}";
    }

}
