package cn.edu.cumt.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

@Entity
@Table(name="person")
public class Person implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;	
	private String personId;//人员编号
	private String personName;//人员姓名
	private String password;//密码
	private String idNumber;//身份证号
	private String cellphoneNumber;//移动电话
	private java.sql.Date birthday;//出生日期
	private Education education;//学历
	private Gender gender;//性别
	private Academy academy;//所属学院
	private Boolean hasModifiedPwd;//已修改密码
	private String sessionId;//最后一次在线SessionId
	private String ipAddress;//ip地址	
	private Boolean deleted;//是否删除
	private Set<Role> roles;//拥有的角色集合
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="person_id",length=45,unique=true,nullable=false)
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	@Column(name="person_name",length=45,nullable=false)
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	@Column(name="password",length=32,nullable=true)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="id_number",length=18,nullable=true)
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	@Column(name="cellphone_number",length=11,nullable=true)
	public String getCellphoneNumber() {
		return cellphoneNumber;
	}
	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}
	@Column(name="birthday",nullable=true)
	public java.sql.Date getBirthday() {
		return birthday;
	}
	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}
	@Enumerated(EnumType.ORDINAL)
	@Column(name="education",nullable=true)
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	@Enumerated(EnumType.ORDINAL)
	@Column(name="gender",nullable=true)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@Enumerated(EnumType.ORDINAL)
	@Column(name="academy",nullable=true)
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	@ManyToMany(targetEntity=Role.class,cascade=CascadeType.ALL)
	@JoinTable(name="person_role",joinColumns=@JoinColumn(name="person_id",referencedColumnName="person_id"),inverseJoinColumns=@JoinColumn(name="role_sn",referencedColumnName="role_sn"))
	@JSON(serialize=false)
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Column(name="hasModifiedPwd",columnDefinition = "boolean default false")
	public Boolean getHasModifiedPwd() {
		return hasModifiedPwd;
	}
	public void setHasModifiedPwd(Boolean hasModifiedPwd) {
		this.hasModifiedPwd = hasModifiedPwd;
	}
	@Column(name="ip_address")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	@Column(name="session_id")
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Column(name="deleted")
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}	
}
