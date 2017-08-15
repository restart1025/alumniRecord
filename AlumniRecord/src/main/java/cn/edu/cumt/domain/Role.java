package cn.edu.cumt.domain;

import java.io.Serializable;
import java.util.HashSet;
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
@Table(name="role")
public class Role implements Serializable{	
	private static final long serialVersionUID = 1L;
	private int id;
	private String roleSn;//角色编号
	private String roleName;//角色名称
	private RoleType roleType;//角色类型
	private Boolean deleted;//是否删除
	private Set<Person> persons=new HashSet<Person>(0);//属于该角色的人员集合
	private Set<Resource> roleResources=new HashSet<Resource>(0);
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="role_sn",length=45,unique=true,nullable=false)
	public String getRoleSn() {
		return roleSn;
	}
	public void setRoleSn(String roleSn) {
		this.roleSn = roleSn;
	}
	@Column(name="role_name",length=45,nullable=false)
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Enumerated(EnumType.ORDINAL)
	@Column(name="role_type")
	public RoleType getRoleType() {
		return roleType;
	}
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	@Column(name="deleted",nullable=false)
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	@ManyToMany(targetEntity=Person.class)
	@JoinTable(name="person_role",joinColumns=@JoinColumn(name="role_sn",referencedColumnName="role_sn"),
				inverseJoinColumns=@JoinColumn(name="person_id",referencedColumnName="person_id"))
	@JSON(serialize=false)
	public Set<Person> getPersons() {
		return persons;
	}
	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
	@ManyToMany(targetEntity=Resource.class, cascade=CascadeType.ALL)
	@JoinTable(name="role_resource", joinColumns=@JoinColumn(name="resource_sn",referencedColumnName="resource_sn"),
				inverseJoinColumns=@JoinColumn(name="role_sn",referencedColumnName="role_sn"))
	@JSON(serialize=false)
	public Set<Resource> getRoleResources() {
		return roleResources;
	}
	public void setRoleResources(Set<Resource> roleResources) {
		this.roleResources = roleResources;
	}	
}
