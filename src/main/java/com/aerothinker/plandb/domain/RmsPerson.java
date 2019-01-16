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
 * RmsPerson 人员信息
 * @author JungleYang.
 */
@ApiModel(description = "RmsPerson 人员信息 @author JungleYang.")
@Entity
@Table(name = "rms_person")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "rmsperson")
public class RmsPerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * 人员名称
     */
    @Size(max = 256)
    @ApiModelProperty(value = "人员名称")
    @Column(name = "person_name", length = 256)
    private String personName;

    /**
     * 姓名称
     */
    @Size(max = 256)
    @ApiModelProperty(value = "姓名称")
    @Column(name = "first_name", length = 256)
    private String firstName;

    /**
     * 工作代码
     */
    @Size(max = 256)
    @ApiModelProperty(value = "工作代码")
    @Column(name = "job_id", length = 256)
    private String jobId;

    /**
     * 最后名称
     */
    @Size(max = 256)
    @ApiModelProperty(value = "最后名称")
    @Column(name = "last_name", length = 256)
    private String lastName;

    /**
     * 其他名称
     */
    @Size(max = 256)
    @ApiModelProperty(value = "其他名称")
    @Column(name = "other_name", length = 256)
    private String otherName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @Column(name = "sex")
    private Boolean sex;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @Column(name = "birthday")
    private Instant birthday;

    /**
     * 图片
     */
    @Size(max = 256)
    @ApiModelProperty(value = "图片")
    @Column(name = "pic", length = 256)
    private String pic;

    /**
     * 图标
     */
    @Size(max = 256)
    @ApiModelProperty(value = "图标")
    @Column(name = "icon", length = 256)
    private String icon;

    /**
     * 移动电话
     */
    @Size(max = 256)
    @ApiModelProperty(value = "移动电话")
    @Column(name = "mobile", length = 256)
    private String mobile;

    /**
     * 单位地址
     */
    @Size(max = 256)
    @ApiModelProperty(value = "单位地址")
    @Column(name = "dep_address", length = 256)
    private String depAddress;

    /**
     * 单位代码
     */
    @Size(max = 256)
    @ApiModelProperty(value = "单位代码")
    @Column(name = "dep_code", length = 256)
    private String depCode;

    /**
     * 职务代码
     */
    @Size(max = 256)
    @ApiModelProperty(value = "职务代码")
    @Column(name = "duty_id", length = 256)
    private String dutyId;

    /**
     * 离职标志
     */
    @ApiModelProperty(value = "离职标志")
    @Column(name = "dimission_flag")
    private Boolean dimissionFlag;

    /**
     * 正职领导标志
     */
    @ApiModelProperty(value = "正职领导标志")
    @Column(name = "header_flag")
    private Boolean headerFlag;

    /**
     * 业务负责人标志
     */
    @ApiModelProperty(value = "业务负责人标志")
    @Column(name = "satrap_flag")
    private Boolean satrapFlag;

    /**
     * 领导标志
     */
    @ApiModelProperty(value = "领导标志")
    @Column(name = "leader_flag")
    private Boolean leaderFlag;

    /**
     * 岗位标志1
     */
    @ApiModelProperty(value = "岗位标志1")
    @Column(name = "post_flag_1")
    private Boolean postFlag1;

    /**
     * 岗位标志2
     */
    @ApiModelProperty(value = "岗位标志2")
    @Column(name = "post_flag_2")
    private Boolean postFlag2;

    /**
     * 岗位标志3
     */
    @ApiModelProperty(value = "岗位标志3")
    @Column(name = "post_flag_3")
    private Boolean postFlag3;

    /**
     * 办公电话
     */
    @Size(max = 256)
    @ApiModelProperty(value = "办公电话")
    @Column(name = "office_tel", length = 256)
    private String officeTel;

    /**
     * 传真
     */
    @Size(max = 256)
    @ApiModelProperty(value = "传真")
    @Column(name = "fax", length = 256)
    private String fax;

    /**
     * 邮箱1
     */
    @Size(max = 256)
    @ApiModelProperty(value = "邮箱1")
    @Column(name = "mail_1", length = 256)
    private String mail1;

    /**
     * 邮箱2
     */
    @Size(max = 256)
    @ApiModelProperty(value = "邮箱2")
    @Column(name = "mail_2", length = 256)
    private String mail2;

    /**
     * 家庭电话
     */
    @Size(max = 256)
    @ApiModelProperty(value = "家庭电话")
    @Column(name = "family_tel", length = 256)
    private String familyTel;

    /**
     * 家庭地址
     */
    @Size(max = 256)
    @ApiModelProperty(value = "家庭地址")
    @Column(name = "family_add", length = 256)
    private String familyAdd;

    /**
     * 家庭代码
     */
    @Size(max = 20)
    @ApiModelProperty(value = "家庭代码")
    @Column(name = "family_code", length = 20)
    private String familyCode;

    /**
     * 人员说明
     */
    @Size(max = 256)
    @ApiModelProperty(value = "人员说明")
    @Column(name = "person_desc", length = 256)
    private String personDesc;

    /**
     * 代码代码
     */
    @Size(max = 256)
    @ApiModelProperty(value = "代码代码")
    @Column(name = "id_code", length = 256)
    private String idCode;

    /**
     * POP3
     */
    @Size(max = 256)
    @ApiModelProperty(value = "POP3")
    @Column(name = "pop_3", length = 256)
    private String pop3;

    /**
     * SMTP
     */
    @Size(max = 256)
    @ApiModelProperty(value = "SMTP")
    @Column(name = "smtp", length = 256)
    private String smtp;

    /**
     * 邮箱用户名
     */
    @Size(max = 256)
    @ApiModelProperty(value = "邮箱用户名")
    @Column(name = "mail_username", length = 256)
    private String mailUsername;

    /**
     * 邮箱密码
     */
    @Size(max = 256)
    @ApiModelProperty(value = "邮箱密码")
    @Column(name = "mail_password", length = 256)
    private String mailPassword;

    /**
     * 邮箱保留标志
     */
    @ApiModelProperty(value = "邮箱保留标志")
    @Column(name = "mail_keep_flag")
    private Boolean mailKeepFlag;

    /**
     * 人员序号
     */
    @Size(max = 10)
    @ApiModelProperty(value = "人员序号")
    @Column(name = "person_sort", length = 10)
    private String personSort;

    /**
     * 等级号
     */
    @ApiModelProperty(value = "等级号")
    @Column(name = "level_num")
    private Integer levelNum;

    /**
     * 人员状态号码
     */
    @Size(max = 32)
    @ApiModelProperty(value = "人员状态号码")
    @Column(name = "person_state_id", length = 32)
    private String personStateId;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public RmsPerson personName(String personName) {
        this.personName = personName;
        return this;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getFirstName() {
        return firstName;
    }

    public RmsPerson firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getJobId() {
        return jobId;
    }

    public RmsPerson jobId(String jobId) {
        this.jobId = jobId;
        return this;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getLastName() {
        return lastName;
    }

    public RmsPerson lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName() {
        return otherName;
    }

    public RmsPerson otherName(String otherName) {
        this.otherName = otherName;
        return this;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public Boolean isSex() {
        return sex;
    }

    public RmsPerson sex(Boolean sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public RmsPerson birthday(Instant birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getPic() {
        return pic;
    }

    public RmsPerson pic(String pic) {
        this.pic = pic;
        return this;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIcon() {
        return icon;
    }

    public RmsPerson icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMobile() {
        return mobile;
    }

    public RmsPerson mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepAddress() {
        return depAddress;
    }

    public RmsPerson depAddress(String depAddress) {
        this.depAddress = depAddress;
        return this;
    }

    public void setDepAddress(String depAddress) {
        this.depAddress = depAddress;
    }

    public String getDepCode() {
        return depCode;
    }

    public RmsPerson depCode(String depCode) {
        this.depCode = depCode;
        return this;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDutyId() {
        return dutyId;
    }

    public RmsPerson dutyId(String dutyId) {
        this.dutyId = dutyId;
        return this;
    }

    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }

    public Boolean isDimissionFlag() {
        return dimissionFlag;
    }

    public RmsPerson dimissionFlag(Boolean dimissionFlag) {
        this.dimissionFlag = dimissionFlag;
        return this;
    }

    public void setDimissionFlag(Boolean dimissionFlag) {
        this.dimissionFlag = dimissionFlag;
    }

    public Boolean isHeaderFlag() {
        return headerFlag;
    }

    public RmsPerson headerFlag(Boolean headerFlag) {
        this.headerFlag = headerFlag;
        return this;
    }

    public void setHeaderFlag(Boolean headerFlag) {
        this.headerFlag = headerFlag;
    }

    public Boolean isSatrapFlag() {
        return satrapFlag;
    }

    public RmsPerson satrapFlag(Boolean satrapFlag) {
        this.satrapFlag = satrapFlag;
        return this;
    }

    public void setSatrapFlag(Boolean satrapFlag) {
        this.satrapFlag = satrapFlag;
    }

    public Boolean isLeaderFlag() {
        return leaderFlag;
    }

    public RmsPerson leaderFlag(Boolean leaderFlag) {
        this.leaderFlag = leaderFlag;
        return this;
    }

    public void setLeaderFlag(Boolean leaderFlag) {
        this.leaderFlag = leaderFlag;
    }

    public Boolean isPostFlag1() {
        return postFlag1;
    }

    public RmsPerson postFlag1(Boolean postFlag1) {
        this.postFlag1 = postFlag1;
        return this;
    }

    public void setPostFlag1(Boolean postFlag1) {
        this.postFlag1 = postFlag1;
    }

    public Boolean isPostFlag2() {
        return postFlag2;
    }

    public RmsPerson postFlag2(Boolean postFlag2) {
        this.postFlag2 = postFlag2;
        return this;
    }

    public void setPostFlag2(Boolean postFlag2) {
        this.postFlag2 = postFlag2;
    }

    public Boolean isPostFlag3() {
        return postFlag3;
    }

    public RmsPerson postFlag3(Boolean postFlag3) {
        this.postFlag3 = postFlag3;
        return this;
    }

    public void setPostFlag3(Boolean postFlag3) {
        this.postFlag3 = postFlag3;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public RmsPerson officeTel(String officeTel) {
        this.officeTel = officeTel;
        return this;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getFax() {
        return fax;
    }

    public RmsPerson fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail1() {
        return mail1;
    }

    public RmsPerson mail1(String mail1) {
        this.mail1 = mail1;
        return this;
    }

    public void setMail1(String mail1) {
        this.mail1 = mail1;
    }

    public String getMail2() {
        return mail2;
    }

    public RmsPerson mail2(String mail2) {
        this.mail2 = mail2;
        return this;
    }

    public void setMail2(String mail2) {
        this.mail2 = mail2;
    }

    public String getFamilyTel() {
        return familyTel;
    }

    public RmsPerson familyTel(String familyTel) {
        this.familyTel = familyTel;
        return this;
    }

    public void setFamilyTel(String familyTel) {
        this.familyTel = familyTel;
    }

    public String getFamilyAdd() {
        return familyAdd;
    }

    public RmsPerson familyAdd(String familyAdd) {
        this.familyAdd = familyAdd;
        return this;
    }

    public void setFamilyAdd(String familyAdd) {
        this.familyAdd = familyAdd;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public RmsPerson familyCode(String familyCode) {
        this.familyCode = familyCode;
        return this;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }

    public String getPersonDesc() {
        return personDesc;
    }

    public RmsPerson personDesc(String personDesc) {
        this.personDesc = personDesc;
        return this;
    }

    public void setPersonDesc(String personDesc) {
        this.personDesc = personDesc;
    }

    public String getIdCode() {
        return idCode;
    }

    public RmsPerson idCode(String idCode) {
        this.idCode = idCode;
        return this;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getPop3() {
        return pop3;
    }

    public RmsPerson pop3(String pop3) {
        this.pop3 = pop3;
        return this;
    }

    public void setPop3(String pop3) {
        this.pop3 = pop3;
    }

    public String getSmtp() {
        return smtp;
    }

    public RmsPerson smtp(String smtp) {
        this.smtp = smtp;
        return this;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public RmsPerson mailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
        return this;
    }

    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public RmsPerson mailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
        return this;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public Boolean isMailKeepFlag() {
        return mailKeepFlag;
    }

    public RmsPerson mailKeepFlag(Boolean mailKeepFlag) {
        this.mailKeepFlag = mailKeepFlag;
        return this;
    }

    public void setMailKeepFlag(Boolean mailKeepFlag) {
        this.mailKeepFlag = mailKeepFlag;
    }

    public String getPersonSort() {
        return personSort;
    }

    public RmsPerson personSort(String personSort) {
        this.personSort = personSort;
        return this;
    }

    public void setPersonSort(String personSort) {
        this.personSort = personSort;
    }

    public Integer getLevelNum() {
        return levelNum;
    }

    public RmsPerson levelNum(Integer levelNum) {
        this.levelNum = levelNum;
        return this;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public String getPersonStateId() {
        return personStateId;
    }

    public RmsPerson personStateId(String personStateId) {
        this.personStateId = personStateId;
        return this;
    }

    public void setPersonStateId(String personStateId) {
        this.personStateId = personStateId;
    }

    public String getBy01() {
        return by01;
    }

    public RmsPerson by01(String by01) {
        this.by01 = by01;
        return this;
    }

    public void setBy01(String by01) {
        this.by01 = by01;
    }

    public String getBy02() {
        return by02;
    }

    public RmsPerson by02(String by02) {
        this.by02 = by02;
        return this;
    }

    public void setBy02(String by02) {
        this.by02 = by02;
    }

    public String getBy03() {
        return by03;
    }

    public RmsPerson by03(String by03) {
        this.by03 = by03;
        return this;
    }

    public void setBy03(String by03) {
        this.by03 = by03;
    }

    public String getBy04() {
        return by04;
    }

    public RmsPerson by04(String by04) {
        this.by04 = by04;
        return this;
    }

    public void setBy04(String by04) {
        this.by04 = by04;
    }

    public String getBy05() {
        return by05;
    }

    public RmsPerson by05(String by05) {
        this.by05 = by05;
        return this;
    }

    public void setBy05(String by05) {
        this.by05 = by05;
    }

    public String getBy06() {
        return by06;
    }

    public RmsPerson by06(String by06) {
        this.by06 = by06;
        return this;
    }

    public void setBy06(String by06) {
        this.by06 = by06;
    }

    public String getBy07() {
        return by07;
    }

    public RmsPerson by07(String by07) {
        this.by07 = by07;
        return this;
    }

    public void setBy07(String by07) {
        this.by07 = by07;
    }

    public String getBy08() {
        return by08;
    }

    public RmsPerson by08(String by08) {
        this.by08 = by08;
        return this;
    }

    public void setBy08(String by08) {
        this.by08 = by08;
    }

    public String getBy09() {
        return by09;
    }

    public RmsPerson by09(String by09) {
        this.by09 = by09;
        return this;
    }

    public void setBy09(String by09) {
        this.by09 = by09;
    }

    public String getBy10() {
        return by10;
    }

    public RmsPerson by10(String by10) {
        this.by10 = by10;
        return this;
    }

    public void setBy10(String by10) {
        this.by10 = by10;
    }

    public ValidType getValidType() {
        return validType;
    }

    public RmsPerson validType(ValidType validType) {
        this.validType = validType;
        return this;
    }

    public void setValidType(ValidType validType) {
        this.validType = validType;
    }

    public Instant getValidBegin() {
        return validBegin;
    }

    public RmsPerson validBegin(Instant validBegin) {
        this.validBegin = validBegin;
        return this;
    }

    public void setValidBegin(Instant validBegin) {
        this.validBegin = validBegin;
    }

    public Instant getValidEnd() {
        return validEnd;
    }

    public RmsPerson validEnd(Instant validEnd) {
        this.validEnd = validEnd;
        return this;
    }

    public void setValidEnd(Instant validEnd) {
        this.validEnd = validEnd;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public RmsPerson insertTime(Instant insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public RmsPerson updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Instant getVerifyTime() {
        return verifyTime;
    }

    public RmsPerson verifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
        return this;
    }

    public void setVerifyTime(Instant verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public RmsPerson serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public RmsUser getCreatedBy() {
        return createdBy;
    }

    public RmsPerson createdBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
        return this;
    }

    public void setCreatedBy(RmsUser rmsUser) {
        this.createdBy = rmsUser;
    }

    public RmsUser getModifiedBy() {
        return modifiedBy;
    }

    public RmsPerson modifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
        return this;
    }

    public void setModifiedBy(RmsUser rmsUser) {
        this.modifiedBy = rmsUser;
    }

    public RmsUser getVerifiedBy() {
        return verifiedBy;
    }

    public RmsPerson verifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
        return this;
    }

    public void setVerifiedBy(RmsUser rmsUser) {
        this.verifiedBy = rmsUser;
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
        RmsPerson rmsPerson = (RmsPerson) o;
        if (rmsPerson.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rmsPerson.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RmsPerson{" +
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
            "}";
    }
}
