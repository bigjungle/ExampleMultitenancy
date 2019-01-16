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
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.aerothinker.plandb.domain.enumeration.ValidType;

/**
 * RmsUser 用户信息
 * @author JungleYang.
 */
@ApiModel(description = "RmsUser 用户信息 @author JungleYang.")
@Entity
@Table(name = "rms_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "rmsuser")
public class RmsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 姓名
     */
    @Size(max = 256)
    @ApiModelProperty(value = "姓名")
    @Column(name = "user_name", length = 256)
    private String userName;

    /**
     * 人员代码
     */
    @Size(max = 256)
    @ApiModelProperty(value = "人员代码")
    @Column(name = "person_id", length = 256)
    private String personId;

    /**
     * 用户密码
     */
    @Size(max = 640)
    @ApiModelProperty(value = "用户密码")
    @Column(name = "user_password", length = 640)
    private String userPassword;

    /**
     * 办理密码
     */
    @Size(max = 640)
    @ApiModelProperty(value = "办理密码")
    @Column(name = "process_password", length = 640)
    private String processPassword;

    /**
     * 用户序号
     */
    @Size(max = 10)
    @ApiModelProperty(value = "用户序号")
    @Column(name = "user_sort", length = 10)
    private String userSort;

    /**
     * 用户说明
     */
    @Size(max = 256)
    @ApiModelProperty(value = "用户说明")
    @Column(name = "user_desc", length = 256)
    private String userDesc;

    /**
     * 用户密码校验时长
     */
    @ApiModelProperty(value = "用户密码校验时长")
    @Column(name = "user_password_vali_instant_times")
    private Integer userPasswordValiInstantTimes;

    /**
     * 用户密码锁定标志
     */
    @ApiModelProperty(value = "用户密码锁定标志")
    @Column(name = "user_password_lock_flag")
    private Boolean userPasswordLockFlag;

    /**
     * 处理密码校验时长
     */
    @ApiModelProperty(value = "处理密码校验时长")
    @Column(name = "proc_password_vali_instant_times")
    private Integer procPasswordValiInstantTimes;

    /**
     * 用户密码锁定标志
     */
    @Size(max = 256)
    @ApiModelProperty(value = "用户密码锁定标志")
    @Column(name = "proc_password_lock_flag", length = 256)
    private String procPasswordLockFlag;

    /**
     * 是否使用
     */
    @Size(max = 256)
    @ApiModelProperty(value = "是否使用")
    @Column(name = "user_prop", length = 256)
    private String userProp;

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

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "rms_user_rms_role",
               joinColumns = @JoinColumn(name = "rms_users_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "rms_roles_id", referencedColumnName = "id"))
    private Set<RmsRole> rmsRoles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public RmsUser userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonId() {
        return personId;
    }

    public RmsUser personId(String personId) {
        this.personId = personId;
        return this;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public RmsUser userPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getProcessPassword() {
        return processPassword;
    }

    public RmsUser processPassword(String processPassword) {
        this.processPassword = processPassword;
        return this;
    }

    public void setProcessPassword(String processPassword) {
        this.processPassword = processPassword;
    }

    public String getUserSort() {
        return userSort;
    }

    public RmsUser userSort(String userSort) {
        this.userSort = userSort;
        return this;
    }

    public void setUserSort(String userSort) {
        this.userSort = userSort;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public RmsUser userDesc(String userDesc) {
        this.userDesc = userDesc;
        return this;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Integer getUserPasswordValiInstantTimes() {
        return userPasswordValiInstantTimes;
    }

    public RmsUser userPasswordValiInstantTimes(Integer userPasswordValiInstantTimes) {
        this.userPasswordValiInstantTimes = userPasswordValiInstantTimes;
        return this;
    }

    public void setUserPasswordValiInstantTimes(Integer userPasswordValiInstantTimes) {
        this.userPasswordValiInstantTimes = userPasswordValiInstantTimes;
    }

    public Boolean isUserPasswordLockFlag() {
        return userPasswordLockFlag;
    }

    public RmsUser userPasswordLockFlag(Boolean userPasswordLockFlag) {
        this.userPasswordLockFlag = userPasswordLockFlag;
        return this;
    }

    public void setUserPasswordLockFlag(Boolean userPasswordLockFlag) {
        this.userPasswordLockFlag = userPasswordLockFlag;
    }

    public Integer getProcPasswordValiInstantTimes() {
        return procPasswordValiInstantTimes;
    }

    public RmsUser procPasswordValiInstantTimes(Integer procPasswordValiInstantTimes) {
        this.procPasswordValiInstantTimes = procPasswordValiInstantTimes;
        return this;
    }

    public void setProcPasswordValiInstantTimes(Integer procPasswordValiInstantTimes) {
        this.procPasswordValiInstantTimes = procPasswordValiInstantTimes;
    }

    public String getProcPasswordLockFlag() {
        return procPasswordLockFlag;
    }

    public RmsUser procPasswordLockFlag(String procPasswordLockFlag) {
        this.procPasswordLockFlag = procPasswordLockFlag;
        return this;
    }

    public void setProcPasswordLockFlag(String procPasswordLockFlag) {
        this.procPasswordLockFlag = procPasswordLockFlag;
    }

    public String getUserProp() {
        return userProp;
    }

    public RmsUser userProp(String userProp) {
        this.userProp = userProp;
        return this;
    }

    public void setUserProp(String userProp) {
        this.userProp = userProp;
    }

    public String getBy01() {
        return by01;
    }

    public RmsUser by01(String by01) {
        this.by01 = by01;
        return this;
    }

    public void setBy01(String by01) {
        this.by01 = by01;
    }

    public String getBy02() {
        return by02;
    }

    public RmsUser by02(String by02) {
        this.by02 = by02;
        return this;
    }

    public void setBy02(String by02) {
        this.by02 = by02;
    }

    public String getBy03() {
        return by03;
    }

    public RmsUser by03(String by03) {
        this.by03 = by03;
        return this;
    }

    public void setBy03(String by03) {
        this.by03 = by03;
    }

    public String getBy04() {
        return by04;
    }

    public RmsUser by04(String by04) {
        this.by04 = by04;
        return this;
    }

    public void setBy04(String by04) {
        this.by04 = by04;
    }

    public String getBy05() {
        return by05;
    }

    public RmsUser by05(String by05) {
        this.by05 = by05;
        return this;
    }

    public void setBy05(String by05) {
        this.by05 = by05;
    }

    public ValidType getValidType() {
        return validType;
    }

    public RmsUser validType(ValidType validType) {
        this.validType = validType;
        return this;
    }

    public void setValidType(ValidType validType) {
        this.validType = validType;
    }

    public Instant getValidBegin() {
        return validBegin;
    }

    public RmsUser validBegin(Instant validBegin) {
        this.validBegin = validBegin;
        return this;
    }

    public void setValidBegin(Instant validBegin) {
        this.validBegin = validBegin;
    }

    public Instant getValidEnd() {
        return validEnd;
    }

    public RmsUser validEnd(Instant validEnd) {
        this.validEnd = validEnd;
        return this;
    }

    public void setValidEnd(Instant validEnd) {
        this.validEnd = validEnd;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public RmsUser insertTime(Instant insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public RmsUser updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Instant getVerifyTime() {
        return verifyTime;
    }

    public RmsUser verifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
        return this;
    }

    public void setVerifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public RmsUser serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public RmsUser getCreatedBy() {
        return createdBy;
    }

    public RmsUser createdBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
        return this;
    }

    public void setCreatedBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
    }

    public RmsUser getModifiedBy() {
        return modifiedBy;
    }

    public RmsUser modifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
        return this;
    }

    public void setModifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
    }

    public RmsUser getVerifiedBy() {
        return verifiedBy;
    }

    public RmsUser verifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
        return this;
    }

    public void setVerifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
    }

    public Set<RmsRole> getRmsRoles() {
        return rmsRoles;
    }

    public RmsUser rmsRoles(Set<RmsRole> rmsRoles) {
        this.rmsRoles = rmsRoles;
        return this;
    }

    public RmsUser addRmsRole(RmsRole rmsRole) {
        this.rmsRoles.add(rmsRole);
        rmsRole.getRmsUsers().add(this);
        return this;
    }

    public RmsUser removeRmsRole(RmsRole rmsRole) {
        this.rmsRoles.remove(rmsRole);
        rmsRole.getRmsUsers().remove(this);
        return this;
    }

    public void setRmsRoles(Set<RmsRole> rmsRoles) {
        this.rmsRoles = rmsRoles;
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
        RmsUser rmsUser = (RmsUser) o;
        if (rmsUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rmsUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RmsUser{" +
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
            "}";
    }
}
