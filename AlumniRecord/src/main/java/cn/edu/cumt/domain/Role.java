package cn.edu.cumt.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable{	
	private static final long serialVersionUID = 1L;
	private int id;
	private String roleSn;//角色编号
	private String roleName;//角色名称 
	private RoleType roleType;//角色类型
	private Boolean deleted;//是否删除
	private Set<Person> persons=new HashSet<Person>(0);//属于该角色的人员集合
	private Set<Resource> resources=new HashSet<Resource>(0);
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleSn() {
		return roleSn;
	}
	public void setRoleSn(String roleSn) {
		this.roleSn = roleSn;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public RoleType getRoleType() {
		return roleType;
	}
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Set<Person> getPersons() {
		return persons;
	}
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
}
