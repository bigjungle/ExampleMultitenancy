package com.aerothinker.plandb.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.aerothinker.plandb.domain.enumeration.ValidType;

/**
 * A DTO for the RmsPerson entity.
 */
public class RmsPersonDTO implements Serializable {

    private Long id;

    @Size(max = 256)
    private String personName;

    @Size(max = 256)
    private String firstName;

    @Size(max = 256)
    private String jobId;

    @Size(max = 256)
    private String lastName;

    @Size(max = 256)
    private String otherName;

    private Boolean sex;

    private Instant birthday;

    @Size(max = 256)
    private String pic;

    @Size(max = 256)
    private String icon;

    @Size(max = 256)
    private String mobile;

    @Size(max = 256)
    private String depAddress;

    @Size(max = 256)
    private String depCode;

    @Size(max = 256)
    private String dutyId;

    private Boolean dimissionFlag;

    private Boolean headerFlag;

    private Boolean satrapFlag;

    private Boolean leaderFlag;

    private Boolean postFlag1;

    private Boolean postFlag2;

    private Boolean postFlag3;

    @Size(max = 256)
    private String officeTel;

    @Size(max = 256)
    private String fax;

    @Size(max = 256)
    private String mail1;

    @Size(max = 256)
    private String mail2;

    @Size(max = 256)
    private String familyTel;

    @Size(max = 256)
    private String familyAdd;

    @Size(max = 20)
    private String familyCode;

    @Size(max = 256)
    private String personDesc;

    @Size(max = 256)
    private String idCode;

    @Size(max = 256)
    private String pop3;

    @Size(max = 256)
    private String smtp;

    @Size(max = 256)
    private String mailUsername;

    @Size(max = 256)
    private String mailPassword;

    private Boolean mailKeepFlag;

    @Size(max = 10)
    private String personSort;

    private Integer levelNum;

    @Size(max = 32)
    private String personStateId;

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

    @Size(max = 256)
    private String by06;

    @Size(max = 256)
    private String by07;

    @Size(max = 256)
    private String by08;

    @Size(max = 256)
    private String by09;

    @Size(max = 256)
    private String by10;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public Boolean isSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepAddress() {
        return depAddress;
    }

    public void setDepAddress(String depAddress) {
        this.depAddress = depAddress;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDutyId() {
        return dutyId;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }

    public Boolean isDimissionFlag() {
        return dimissionFlag;
    }

    public void setDimissionFlag(Boolean dimissionFlag) {
        this.dimissionFlag = dimissionFlag;
    }

    public Boolean isHeaderFlag() {
        return headerFlag;
    }

    public void setHeaderFlag(Boolean headerFlag) {
        this.headerFlag = headerFlag;
    }

    public Boolean isSatrapFlag() {
        return satrapFlag;
    }

    public void setSatrapFlag(Boolean satrapFlag) {
        this.satrapFlag = satrapFlag;
    }

    public Boolean isLeaderFlag() {
        return leaderFlag;
    }

    public void setLeaderFlag(Boolean leaderFlag) {
        this.leaderFlag = leaderFlag;
    }

    public Boolean isPostFlag1() {
        return postFlag1;
    }

    public void setPostFlag1(Boolean postFlag1) {
        this.postFlag1 = postFlag1;
    }

    public Boolean isPostFlag2() {
        return postFlag2;
    }

    public void setPostFlag2(Boolean postFlag2) {
        this.postFlag2 = postFlag2;
    }

    public Boolean isPostFlag3() {
        return postFlag3;
    }

    public void setPostFlag3(Boolean postFlag3) {
        this.postFlag3 = postFlag3;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail1() {
        return mail1;
    }

    public void setMail1(String mail1) {
        this.mail1 = mail1;
    }

    public String getMail2() {
        return mail2;
    }

    public void setMail2(String mail2) {
        this.mail2 = mail2;
    }

    public String getFamilyTel() {
        return familyTel;
    }

    public void setFamilyTel(String familyTel) {
        this.familyTel = familyTel;
    }

    public String getFamilyAdd() {
        return familyAdd;
    }

    public void setFamilyAdd(String familyAdd) {
        this.familyAdd = familyAdd;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }

    public String getPersonDesc() {
        return personDesc;
    }

    public void setPersonDesc(String personDesc) {
        this.personDesc = personDesc;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getPop3() {
        return pop3;
    }

    public void setPop3(String pop3) {
        this.pop3 = pop3;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public Boolean isMailKeepFlag() {
        return mailKeepFlag;
    }

    public void setMailKeepFlag(Boolean mailKeepFlag) {
        this.mailKeepFlag = mailKeepFlag;
    }

    public String getPersonSort() {
        return personSort;
    }

    public void setPersonSort(String personSort) {
        this.personSort = personSort;
    }

    public Integer getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public String getPersonStateId() {
        return personStateId;
    }

    public void setPersonStateId(String personStateId) {
        this.personStateId = personStateId;
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

    public String getBy06() {
        return by06;
    }

    public void setBy06(String by06) {
        this.by06 = by06;
    }

    public String getBy07() {
        return by07;
    }

    public void setBy07(String by07) {
        this.by07 = by07;
    }

    public String getBy08() {
        return by08;
    }

    public void setBy08(String by08) {
        this.by08 = by08;
    }

    public String getBy09() {
        return by09;
    }

    public void setBy09(String by09) {
        this.by09 = by09;
    }

    public String getBy10() {
        return by10;
    }

    public void setBy10(String by10) {
        this.by10 = by10;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RmsPersonDTO rmsPersonDTO = (RmsPersonDTO) o;
        if (rmsPersonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rmsPersonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RmsPersonDTO{" +
            "id=" + getId() +
            ", personName='" + getPersonName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", jobId='" + getJobId() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", otherName='" + getOtherName() + "'" +
            ", sex='" + isSex() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", pic='" + getPic() + "'" +
            ", icon='" + getIcon() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", depAddress='" + getDepAddress() + "'" +
            ", depCode='" + getDepCode() + "'" +
            ", dutyId='" + getDutyId() + "'" +
            ", dimissionFlag='" + isDimissionFlag() + "'" +
            ", headerFlag='" + isHeaderFlag() + "'" +
            ", satrapFlag='" + isSatrapFlag() + "'" +
            ", leaderFlag='" + isLeaderFlag() + "'" +
            ", postFlag1='" + isPostFlag1() + "'" +
            ", postFlag2='" + isPostFlag2() + "'" +
            ", postFlag3='" + isPostFlag3() + "'" +
            ", officeTel='" + getOfficeTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", mail1='" + getMail1() + "'" +
            ", mail2='" + getMail2() + "'" +
            ", familyTel='" + getFamilyTel() + "'" +
            ", familyAdd='" + getFamilyAdd() + "'" +
            ", familyCode='" + getFamilyCode() + "'" +
            ", personDesc='" + getPersonDesc() + "'" +
            ", idCode='" + getIdCode() + "'" +
            ", pop3='" + getPop3() + "'" +
            ", smtp='" + getSmtp() + "'" +
            ", mailUsername='" + getMailUsername() + "'" +
            ", mailPassword='" + getMailPassword() + "'" +
            ", mailKeepFlag='" + isMailKeepFlag() + "'" +
            ", personSort='" + getPersonSort() + "'" +
            ", levelNum=" + getLevelNum() +
            ", personStateId='" + getPersonStateId() + "'" +
            ", by01='" + getBy01() + "'" +
            ", by02='" + getBy02() + "'" +
            ", by03='" + getBy03() + "'" +
            ", by04='" + getBy04() + "'" +
            ", by05='" + getBy05() + "'" +
            ", by06='" + getBy06() + "'" +
            ", by07='" + getBy07() + "'" +
            ", by08='" + getBy08() + "'" +
            ", by09='" + getBy09() + "'" +
            ", by10='" + getBy10() + "'" +
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
