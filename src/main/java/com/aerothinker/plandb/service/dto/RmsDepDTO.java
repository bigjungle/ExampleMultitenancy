package com.aerothinker.plandb.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.aerothinker.plandb.domain.enumeration.ValidType;

/**
 * A DTO for the RmsDep entity.
 */
public class RmsDepDTO implements Serializable {

    private Long id;

    @Size(max = 256)
    private String depName;

    @Size(max = 256)
    private String depTypeId;

    @Size(max = 256)
    private String parentDepId;

    private Boolean repealFlag;

    @Size(max = 32)
    private String levelId;

    @Size(max = 10)
    private String depSort;

    @Size(max = 2560)
    private String parentDepStr;

    @Size(max = 3999)
    private String childDepStr;

    @Size(max = 256)
    private String depDesc;

    @Size(max = 256)
    private String tel;

    @Size(max = 256)
    private String fax;

    @Size(max = 256)
    private String address;

    @Size(max = 32)
    private String code;

    @Size(max = 256)
    private String internet;

    @Size(max = 256)
    private String mail;

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

    private Long parentId;

    private String parentDepName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepTypeId() {
        return depTypeId;
    }

    public void setDepTypeId(String depTypeId) {
        this.depTypeId = depTypeId;
    }

    public String getParentDepId() {
        return parentDepId;
    }

    public void setParentDepId(String parentDepId) {
        this.parentDepId = parentDepId;
    }

    public Boolean isRepealFlag() {
        return repealFlag;
    }

    public void setRepealFlag(Boolean repealFlag) {
        this.repealFlag = repealFlag;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getDepSort() {
        return depSort;
    }

    public void setDepSort(String depSort) {
        this.depSort = depSort;
    }

    public String getParentDepStr() {
        return parentDepStr;
    }

    public void setParentDepStr(String parentDepStr) {
        this.parentDepStr = parentDepStr;
    }

    public String getChildDepStr() {
        return childDepStr;
    }

    public void setChildDepStr(String childDepStr) {
        this.childDepStr = childDepStr;
    }

    public String getDepDesc() {
        return depDesc;
    }

    public void setDepDesc(String depDesc) {
        this.depDesc = depDesc;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long rmsDepId) {
        this.parentId = rmsDepId;
    }

    public String getParentDepName() {
        return parentDepName;
    }

    public void setParentDepName(String rmsDepDepName) {
        this.parentDepName = rmsDepDepName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RmsDepDTO rmsDepDTO = (RmsDepDTO) o;
        if (rmsDepDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rmsDepDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RmsDepDTO{" +
            "id=" + getId() +
            ", depName='" + getDepName() + "'" +
            ", depTypeId='" + getDepTypeId() + "'" +
            ", parentDepId='" + getParentDepId() + "'" +
            ", repealFlag='" + isRepealFlag() + "'" +
            ", levelId='" + getLevelId() + "'" +
            ", depSort='" + getDepSort() + "'" +
            ", parentDepStr='" + getParentDepStr() + "'" +
            ", childDepStr='" + getChildDepStr() + "'" +
            ", depDesc='" + getDepDesc() + "'" +
            ", tel='" + getTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", address='" + getAddress() + "'" +
            ", code='" + getCode() + "'" +
            ", internet='" + getInternet() + "'" +
            ", mail='" + getMail() + "'" +
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
            ", parent=" + getParentId() +
            ", parent='" + getParentDepName() + "'" +
            "}";
    }
}
