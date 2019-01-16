package com.aerothinker.plandb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.aerothinker.plandb.domain.enumeration.ValidType;

/**
 * RmsDep 单位信息
 * @author JungleYang.
 */
@ApiModel(description = "RmsDep 单位信息 @author JungleYang.")
@Entity
@Table(name = "rms_dep")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "rmsdep")
public class RmsDep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 单位名称
     */
    @Size(max = 256)
    @ApiModelProperty(value = "单位名称")
    @Column(name = "dep_name", length = 256)
    private String depName;

    /**
     * 单位类型代码
     */
    @Size(max = 256)
    @ApiModelProperty(value = "单位类型代码")
    @Column(name = "dep_type_id", length = 256)
    private String depTypeId;

    /**
     * 上级单位代码
     */
    @Size(max = 256)
    @ApiModelProperty(value = "上级单位代码")
    @Column(name = "parent_dep_id", length = 256)
    private String parentDepId;

    /**
     * 撤销标志
     */
    @ApiModelProperty(value = "撤销标志")
    @Column(name = "repeal_flag")
    private Boolean repealFlag;

    /**
     * 等级代码
     */
    @Size(max = 32)
    @ApiModelProperty(value = "等级代码")
    @Column(name = "level_id", length = 32)
    private String levelId;

    /**
     * 单位序号
     */
    @Size(max = 10)
    @ApiModelProperty(value = "单位序号")
    @Column(name = "dep_sort", length = 10)
    private String depSort;

    /**
     * 上级单位字符串
     */
    @Size(max = 2560)
    @ApiModelProperty(value = "上级单位字符串")
    @Column(name = "parent_dep_str", length = 2560)
    private String parentDepStr;

    /**
     * 子单位字符串
     */
    @Size(max = 3999)
    @ApiModelProperty(value = "子单位字符串")
    @Column(name = "child_dep_str", length = 3999)
    private String childDepStr;

    /**
     * 单位说明
     */
    @Size(max = 256)
    @ApiModelProperty(value = "单位说明")
    @Column(name = "dep_desc", length = 256)
    private String depDesc;

    /**
     * 电话
     */
    @Size(max = 256)
    @ApiModelProperty(value = "电话")
    @Column(name = "tel", length = 256)
    private String tel;

    /**
     * 传真
     */
    @Size(max = 256)
    @ApiModelProperty(value = "传真")
    @Column(name = "fax", length = 256)
    private String fax;

    /**
     * 地址
     */
    @Size(max = 256)
    @ApiModelProperty(value = "地址")
    @Column(name = "address", length = 256)
    private String address;

    /**
     * 代码
     */
    @Size(max = 32)
    @ApiModelProperty(value = "代码")
    @Column(name = "code", length = 32)
    private String code;

    /**
     * 网站
     */
    @Size(max = 256)
    @ApiModelProperty(value = "网站")
    @Column(name = "internet", length = 256)
    private String internet;

    /**
     * 邮箱
     */
    @Size(max = 256)
    @ApiModelProperty(value = "邮箱")
    @Column(name = "mail", length = 256)
    private String mail;

    /**
     * 备用01
     */
    @Size(max = 256)
    @ApiModelProperty(value = "备用01")
    @Column(name = "by_01", length = 256)
    private String by01;

    /**
     * 备用02
     */
    @Size(max = 256)
    @ApiModelProperty(value = "备用02")
    @Column(name = "by_02", length = 256)
    private String by02;

    /**
     * 备用03
     */
    @Size(max = 256)
    @ApiModelProperty(value = "备用03")
    @Column(name = "by_03", length = 256)
    private String by03;

    /**
     * 备用04
     */
    @Size(max = 256)
    @ApiModelProperty(value = "备用04")
    @Column(name = "by_04", length = 256)
    private String by04;

    /**
     * 备用05
     */
    @Size(max = 256)
    @ApiModelProperty(value = "备用05")
    @Column(name = "by_05", length = 256)
    private String by05;

    /**
     * 备用06
     */
    @Size(max = 256)
    @ApiModelProperty(value = "备用06")
    @Column(name = "by_06", length = 256)
    private String by06;

    /**
     * 备用07
     */
    @Size(max = 256)
    @ApiModelProperty(value = "备用07")
    @Column(name = "by_07", length = 256)
    private String by07;

    /**
     * 备用08
     */
    @Size(max = 256)
    @ApiModelProperty(value = "备用08")
    @Column(name = "by_08", length = 256)
    private String by08;

    /**
     * 备用09
     */
    @Size(max = 256)
    @ApiModelProperty(value = "备用09")
    @Column(name = "by_09", length = 256)
    private String by09;

    /**
     * 备用10
     */
    @Size(max = 256)
    @ApiModelProperty(value = "备用10")
    @Column(name = "by_10", length = 256)
    private String by10;

    /**
     * 有效类型
     */
    @ApiModelProperty(value = "有效类型")
    @Enumerated(EnumType.STRING)
    @Column(name = "valid_type")
    private ValidType validType;

    /**
     * 生效开始时间
     */
    @ApiModelProperty(value = "生效开始时间")
    @Column(name = "valid_begin")
    private Instant validBegin;

    /**
     * 有效截止时间
     */
    @ApiModelProperty(value = "有效截止时间")
    @Column(name = "valid_end")
    private Instant validEnd;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "insert_time")
    private Instant insertTime;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    @Column(name = "update_time")
    private Instant updateTime;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    @Column(name = "verify_time")
    private Instant verifyTime;

    /**
     * 编号
     */
    @Size(max = 256)
    @ApiModelProperty(value = "编号")
    @Column(name = "serial_number", length = 256)
    private String serialNumber;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsUser createdBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsUser modifiedBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsUser verifiedBy;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RmsDep parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public RmsDep depName(String depName) {
        this.depName = depName;
        return this;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepTypeId() {
        return depTypeId;
    }

    public RmsDep depTypeId(String depTypeId) {
        this.depTypeId = depTypeId;
        return this;
    }

    public void setDepTypeId(String depTypeId) {
        this.depTypeId = depTypeId;
    }

    public String getParentDepId() {
        return parentDepId;
    }

    public RmsDep parentDepId(String parentDepId) {
        this.parentDepId = parentDepId;
        return this;
    }

    public void setParentDepId(String parentDepId) {
        this.parentDepId = parentDepId;
    }

    public Boolean isRepealFlag() {
        return repealFlag;
    }

    public RmsDep repealFlag(Boolean repealFlag) {
        this.repealFlag = repealFlag;
        return this;
    }

    public void setRepealFlag(Boolean repealFlag) {
        this.repealFlag = repealFlag;
    }

    public String getLevelId() {
        return levelId;
    }

    public RmsDep levelId(String levelId) {
        this.levelId = levelId;
        return this;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getDepSort() {
        return depSort;
    }

    public RmsDep depSort(String depSort) {
        this.depSort = depSort;
        return this;
    }

    public void setDepSort(String depSort) {
        this.depSort = depSort;
    }

    public String getParentDepStr() {
        return parentDepStr;
    }

    public RmsDep parentDepStr(String parentDepStr) {
        this.parentDepStr = parentDepStr;
        return this;
    }

    public void setParentDepStr(String parentDepStr) {
        this.parentDepStr = parentDepStr;
    }

    public String getChildDepStr() {
        return childDepStr;
    }

    public RmsDep childDepStr(String childDepStr) {
        this.childDepStr = childDepStr;
        return this;
    }

    public void setChildDepStr(String childDepStr) {
        this.childDepStr = childDepStr;
    }

    public String getDepDesc() {
        return depDesc;
    }

    public RmsDep depDesc(String depDesc) {
        this.depDesc = depDesc;
        return this;
    }

    public void setDepDesc(String depDesc) {
        this.depDesc = depDesc;
    }

    public String getTel() {
        return tel;
    }

    public RmsDep tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public RmsDep fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public RmsDep address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public RmsDep code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInternet() {
        return internet;
    }

    public RmsDep internet(String internet) {
        this.internet = internet;
        return this;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getMail() {
        return mail;
    }

    public RmsDep mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBy01() {
        return by01;
    }

    public RmsDep by01(String by01) {
        this.by01 = by01;
        return this;
    }

    public void setBy01(String by01) {
        this.by01 = by01;
    }

    public String getBy02() {
        return by02;
    }

    public RmsDep by02(String by02) {
        this.by02 = by02;
        return this;
    }

    public void setBy02(String by02) {
        this.by02 = by02;
    }

    public String getBy03() {
        return by03;
    }

    public RmsDep by03(String by03) {
        this.by03 = by03;
        return this;
    }

    public void setBy03(String by03) {
        this.by03 = by03;
    }

    public String getBy04() {
        return by04;
    }

    public RmsDep by04(String by04) {
        this.by04 = by04;
        return this;
    }

    public void setBy04(String by04) {
        this.by04 = by04;
    }

    public String getBy05() {
        return by05;
    }

    public RmsDep by05(String by05) {
        this.by05 = by05;
        return this;
    }

    public void setBy05(String by05) {
        this.by05 = by05;
    }

    public String getBy06() {
        return by06;
    }

    public RmsDep by06(String by06) {
        this.by06 = by06;
        return this;
    }

    public void setBy06(String by06) {
        this.by06 = by06;
    }

    public String getBy07() {
        return by07;
    }

    public RmsDep by07(String by07) {
        this.by07 = by07;
        return this;
    }

    public void setBy07(String by07) {
        this.by07 = by07;
    }

    public String getBy08() {
        return by08;
    }

    public RmsDep by08(String by08) {
        this.by08 = by08;
        return this;
    }

    public void setBy08(String by08) {
        this.by08 = by08;
    }

    public String getBy09() {
        return by09;
    }

    public RmsDep by09(String by09) {
        this.by09 = by09;
        return this;
    }

    public void setBy09(String by09) {
        this.by09 = by09;
    }

    public String getBy10() {
        return by10;
    }

    public RmsDep by10(String by10) {
        this.by10 = by10;
        return this;
    }

    public void setBy10(String by10) {
        this.by10 = by10;
    }

    public ValidType getValidType() {
        return validType;
    }

    public RmsDep validType(ValidType validType) {
        this.validType = validType;
        return this;
    }

    public void setValidType(ValidType validType) {
        this.validType = validType;
    }

    public Instant getValidBegin() {
        return validBegin;
    }

    public RmsDep validBegin(Instant validBegin) {
        this.validBegin = validBegin;
        return this;
    }

    public void setValidBegin(Instant validBegin) {
        this.validBegin = validBegin;
    }

    public Instant getValidEnd() {
        return validEnd;
    }

    public RmsDep validEnd(Instant validEnd) {
        this.validEnd = validEnd;
        return this;
    }

    public void setValidEnd(Instant validEnd) {
        this.validEnd = validEnd;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public RmsDep insertTime(Instant insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public RmsDep updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Instant getVerifyTime() {
        return verifyTime;
    }

    public RmsDep verifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
        return this;
    }

    public void setVerifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public RmsDep serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public RmsUser getCreatedBy() {
        return createdBy;
    }

    public RmsDep createdBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
        return this;
    }

    public void setCreatedBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
    }

    public RmsUser getModifiedBy() {
        return modifiedBy;
    }

    public RmsDep modifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
        return this;
    }

    public void setModifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
    }

    public RmsUser getVerifiedBy() {
        return verifiedBy;
    }

    public RmsDep verifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
        return this;
    }

    public void setVerifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
    }

    public RmsDep getParent() {
        return parent;
    }

    public RmsDep parent(RmsDep rmsDep) {
        this.parent = rmsDep;
        return this;
    }

    public void setParent(RmsDep rmsDep) {
        this.parent = rmsDep;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RmsDep rmsDep = (RmsDep) o;
        if (rmsDep.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rmsDep.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RmsDep{" +
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
            "}";
    }
}
