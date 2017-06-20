package cn.edu.cumt.domain;

import java.util.Set;

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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getCellphoneNumber() {
		return cellphoneNumber;
	}
	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}
	public java.sql.Date getBirthday() {
		return birthday;
	}
	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Academy getAcademy() {
		return academy;
	}
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Boolean getHasModifiedPwd() {
		return hasModifiedPwd;
	}
	public void setHasModifiedPwd(Boolean hasModifiedPwd) {
		this.hasModifiedPwd = hasModifiedPwd;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}	
}
