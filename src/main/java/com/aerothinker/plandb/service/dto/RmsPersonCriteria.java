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
 * Criteria class for the RmsPerson entity. This class is used in RmsPersonResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /rms-people?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RmsPersonCriteria implements Serializable {
    /**
     * Class for filtering ValidType
     */
    public static class ValidTypeFilter extends Filter<ValidType> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter personName;

    private StringFilter firstName;

    private StringFilter jobId;

    private StringFilter lastName;

    private StringFilter otherName;

    private BooleanFilter sex;

    private InstantFilter birthday;

    private StringFilter pic;

    private StringFilter icon;

    private StringFilter mobile;

    private StringFilter depAddress;

    private StringFilter depCode;

    private StringFilter dutyId;

    private BooleanFilter dimissionFlag;

    private BooleanFilter headerFlag;

    private BooleanFilter satrapFlag;

    private BooleanFilter leaderFlag;

    private BooleanFilter postFlag1;

    private BooleanFilter postFlag2;

    private BooleanFilter postFlag3;

    private StringFilter officeTel;

    private StringFilter fax;

    private StringFilter mail1;

    private StringFilter mail2;

    private StringFilter familyTel;

    private StringFilter familyAdd;

    private StringFilter familyCode;

    private StringFilter personDesc;

    private StringFilter idCode;

    private StringFilter pop3;

    private StringFilter smtp;

    private StringFilter mailUsername;

    private StringFilter mailPassword;

    private BooleanFilter mailKeepFlag;

    private StringFilter personSort;

    private IntegerFilter levelNum;

    private StringFilter personStateId;

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

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPersonName() {
        return personName;
    }

    public void setPersonName(StringFilter personName) {
        this.personName = personName;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getJobId() {
        return jobId;
    }

    public void setJobId(StringFilter jobId) {
        this.jobId = jobId;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public StringFilter getOtherName() {
        return otherName;
    }

    public void setOtherName(StringFilter otherName) {
        this.otherName = otherName;
    }

    public BooleanFilter getSex() {
        return sex;
    }

    public void setSex(BooleanFilter sex) {
        this.sex = sex;
    }

    public InstantFilter getBirthday() {
        return birthday;
    }

    public void setBirthday(InstantFilter birthday) {
        this.birthday = birthday;
    }

    public StringFilter getPic() {
        return pic;
    }

    public void setPic(StringFilter pic) {
        this.pic = pic;
    }

    public StringFilter getIcon() {
        return icon;
    }

    public void setIcon(StringFilter icon) {
        this.icon = icon;
    }

    public StringFilter getMobile() {
        return mobile;
    }

    public void setMobile(StringFilter mobile) {
        this.mobile = mobile;
    }

    public StringFilter getDepAddress() {
        return depAddress;
    }

    public void setDepAddress(StringFilter depAddress) {
        this.depAddress = depAddress;
    }

    public StringFilter getDepCode() {
        return depCode;
    }

    public void setDepCode(StringFilter depCode) {
        this.depCode = depCode;
    }

    public StringFilter getDutyId() {
        return dutyId;
    }

    public void setDutyId(StringFilter dutyId) {
        this.dutyId = dutyId;
    }

    public BooleanFilter getDimissionFlag() {
        return dimissionFlag;
    }

    public void setDimissionFlag(BooleanFilter dimissionFlag) {
        this.dimissionFlag = dimissionFlag;
    }

    public BooleanFilter getHeaderFlag() {
        return headerFlag;
    }

    public void setHeaderFlag(BooleanFilter headerFlag) {
        this.headerFlag = headerFlag;
    }

    public BooleanFilter getSatrapFlag() {
        return satrapFlag;
    }

    public void setSatrapFlag(BooleanFilter satrapFlag) {
        this.satrapFlag = satrapFlag;
    }

    public BooleanFilter getLeaderFlag() {
        return leaderFlag;
    }

    public void setLeaderFlag(BooleanFilter leaderFlag) {
        this.leaderFlag = leaderFlag;
    }

    public BooleanFilter getPostFlag1() {
        return postFlag1;
    }

    public void setPostFlag1(BooleanFilter postFlag1) {
        this.postFlag1 = postFlag1;
    }

    public BooleanFilter getPostFlag2() {
        return postFlag2;
    }

    public void setPostFlag2(BooleanFilter postFlag2) {
        this.postFlag2 = postFlag2;
    }

    public BooleanFilter getPostFlag3() {
        return postFlag3;
    }

    public void setPostFlag3(BooleanFilter postFlag3) {
        this.postFlag3 = postFlag3;
    }

    public StringFilter getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(StringFilter officeTel) {
        this.officeTel = officeTel;
    }

    public StringFilter getFax() {
        return fax;
    }

    public void setFax(StringFilter fax) {
        this.fax = fax;
    }

    public StringFilter getMail1() {
        return mail1;
    }

    public void setMail1(StringFilter mail1) {
        this.mail1 = mail1;
    }

    public StringFilter getMail2() {
        return mail2;
    }

    public void setMail2(StringFilter mail2) {
        this.mail2 = mail2;
    }

    public StringFilter getFamilyTel() {
        return familyTel;
    }

    public void setFamilyTel(StringFilter familyTel) {
        this.familyTel = familyTel;
    }

    public StringFilter getFamilyAdd() {
        return familyAdd;
    }

    public void setFamilyAdd(StringFilter familyAdd) {
        this.familyAdd = familyAdd;
    }

    public StringFilter getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(StringFilter familyCode) {
        this.familyCode = familyCode;
    }

    public StringFilter getPersonDesc() {
        return personDesc;
    }

    public void setPersonDesc(StringFilter personDesc) {
        this.personDesc = personDesc;
    }

    public StringFilter getIdCode() {
        return idCode;
    }

    public void setIdCode(StringFilter idCode) {
        this.idCode = idCode;
    }

    public StringFilter getPop3() {
        return pop3;
    }

    public void setPop3(StringFilter pop3) {
        this.pop3 = pop3;
    }

    public StringFilter getSmtp() {
        return smtp;
    }

    public void setSmtp(StringFilter smtp) {
        this.smtp = smtp;
    }

    public StringFilter getMailUsername() {
        return mailUsername;
    }

    public void setMailUsername(StringFilter mailUsername) {
        this.mailUsername = mailUsername;
    }

    public StringFilter getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(StringFilter mailPassword) {
        this.mailPassword = mailPassword;
    }

    public BooleanFilter getMailKeepFlag() {
        return mailKeepFlag;
    }

    public void setMailKeepFlag(BooleanFilter mailKeepFlag) {
        this.mailKeepFlag = mailKeepFlag;
    }

    public StringFilter getPersonSort() {
        return personSort;
    }

    public void setPersonSort(StringFilter personSort) {
        this.personSort = personSort;
    }

    public IntegerFilter getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(IntegerFilter levelNum) {
        this.levelNum = levelNum;
    }

    public StringFilter getPersonStateId() {
        return personStateId;
    }

    public void setPersonStateId(StringFilter personStateId) {
        this.personStateId = personStateId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RmsPersonCriteria that = (RmsPersonCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(personName, that.personName) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(jobId, that.jobId) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(otherName, that.otherName) &&
            Objects.equals(sex, that.sex) &&
            Objects.equals(birthday, that.birthday) &&
            Objects.equals(pic, that.pic) &&
            Objects.equals(icon, that.icon) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(depAddress, that.depAddress) &&
            Objects.equals(depCode, that.depCode) &&
            Objects.equals(dutyId, that.dutyId) &&
            Objects.equals(dimissionFlag, that.dimissionFlag) &&
            Objects.equals(headerFlag, that.headerFlag) &&
            Objects.equals(satrapFlag, that.satrapFlag) &&
            Objects.equals(leaderFlag, that.leaderFlag) &&
            Objects.equals(postFlag1, that.postFlag1) &&
            Objects.equals(postFlag2, that.postFlag2) &&
            Objects.equals(postFlag3, that.postFlag3) &&
            Objects.equals(officeTel, that.officeTel) &&
            Objects.equals(fax, that.fax) &&
            Objects.equals(mail1, that.mail1) &&
            Objects.equals(mail2, that.mail2) &&
            Objects.equals(familyTel, that.familyTel) &&
            Objects.equals(familyAdd, that.familyAdd) &&
            Objects.equals(familyCode, that.familyCode) &&
            Objects.equals(personDesc, that.personDesc) &&
            Objects.equals(idCode, that.idCode) &&
            Objects.equals(pop3, that.pop3) &&
            Objects.equals(smtp, that.smtp) &&
            Objects.equals(mailUsername, that.mailUsername) &&
            Objects.equals(mailPassword, that.mailPassword) &&
            Objects.equals(mailKeepFlag, that.mailKeepFlag) &&
            Objects.equals(personSort, that.personSort) &&
            Objects.equals(levelNum, that.levelNum) &&
            Objects.equals(personStateId, that.personStateId) &&
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
            Objects.equals(verifiedById, that.verifiedById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        personName,
        firstName,
        jobId,
        lastName,
        otherName,
        sex,
        birthday,
        pic,
        icon,
        mobile,
        depAddress,
        depCode,
        dutyId,
        dimissionFlag,
        headerFlag,
        satrapFlag,
        leaderFlag,
        postFlag1,
        postFlag2,
        postFlag3,
        officeTel,
        fax,
        mail1,
        mail2,
        familyTel,
        familyAdd,
        familyCode,
        personDesc,
        idCode,
        pop3,
        smtp,
        mailUsername,
        mailPassword,
        mailKeepFlag,
        personSort,
        levelNum,
        personStateId,
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
        verifiedById
        );
    }

    @Override
    public String toString() {
        return "RmsPersonCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (personName != null ? "personName=" + personName + ", " : "") +
                (firstName != null ? "firstName=" + firstName + ", " : "") +
                (jobId != null ? "jobId=" + jobId + ", " : "") +
                (lastName != null ? "lastName=" + lastName + ", " : "") +
                (otherName != null ? "otherName=" + otherName + ", " : "") +
                (sex != null ? "sex=" + sex + ", " : "") +
                (birthday != null ? "birthday=" + birthday + ", " : "") +
                (pic != null ? "pic=" + pic + ", " : "") +
                (icon != null ? "icon=" + icon + ", " : "") +
                (mobile != null ? "mobile=" + mobile + ", " : "") +
                (depAddress != null ? "depAddress=" + depAddress + ", " : "") +
                (depCode != null ? "depCode=" + depCode + ", " : "") +
                (dutyId != null ? "dutyId=" + dutyId + ", " : "") +
                (dimissionFlag != null ? "dimissionFlag=" + dimissionFlag + ", " : "") +
                (headerFlag != null ? "headerFlag=" + headerFlag + ", " : "") +
                (satrapFlag != null ? "satrapFlag=" + satrapFlag + ", " : "") +
                (leaderFlag != null ? "leaderFlag=" + leaderFlag + ", " : "") +
                (postFlag1 != null ? "postFlag1=" + postFlag1 + ", " : "") +
                (postFlag2 != null ? "postFlag2=" + postFlag2 + ", " : "") +
                (postFlag3 != null ? "postFlag3=" + postFlag3 + ", " : "") +
                (officeTel != null ? "officeTel=" + officeTel + ", " : "") +
                (fax != null ? "fax=" + fax + ", " : "") +
                (mail1 != null ? "mail1=" + mail1 + ", " : "") +
                (mail2 != null ? "mail2=" + mail2 + ", " : "") +
                (familyTel != null ? "familyTel=" + familyTel + ", " : "") +
                (familyAdd != null ? "familyAdd=" + familyAdd + ", " : "") +
                (familyCode != null ? "familyCode=" + familyCode + ", " : "") +
                (personDesc != null ? "personDesc=" + personDesc + ", " : "") +
                (idCode != null ? "idCode=" + idCode + ", " : "") +
                (pop3 != null ? "pop3=" + pop3 + ", " : "") +
                (smtp != null ? "smtp=" + smtp + ", " : "") +
                (mailUsername != null ? "mailUsername=" + mailUsername + ", " : "") +
                (mailPassword != null ? "mailPassword=" + mailPassword + ", " : "") +
                (mailKeepFlag != null ? "mailKeepFlag=" + mailKeepFlag + ", " : "") +
                (personSort != null ? "personSort=" + personSort + ", " : "") +
                (levelNum != null ? "levelNum=" + levelNum + ", " : "") +
                (personStateId != null ? "personStateId=" + personStateId + ", " : "") +
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
            "}";
    }

}
