package cn.edu.cumt.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

@Entity
@Table(name="resource")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String resourceSn;
	private String resourceName;
	private ResourceType resourceType;
	private String url;
	private Resource parent;
	private Boolean hasMenuChildren;
	private Set<Resource> children;
	private int showSequence;
	private Boolean deleted;
	private Set<Role> roleResources=new HashSet<Role>(0);
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="resource_sn",length=10,unique=true,nullable=false)
	public String getResourceSn() {
		return resourceSn;
	}
	public void setResourceSn(String resourceSn) {
		this.resourceSn = resourceSn;
	}
	@Column(name="resource_name",length=45,nullable=false)
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	@Column(name="resource_type",length=6,nullable=false)
	@Enumerated(EnumType.STRING)
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	@Column(name="url",length=100,unique=true,nullable=true)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@ManyToOne(targetEntity=Resource.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="parent_resource_sn",referencedColumnName="resource_sn",nullable=true)
	public Resource getParent() {
		return parent;
	}
	public void setParent(Resource parent) {
		this.parent = parent;
	}
	@Column(name="has_menu_children",columnDefinition="bit(1) default 0 ")
	public Boolean getHasMenuChildren() {
		return hasMenuChildren;
	}
	public void setHasMenuChildren(Boolean hasMenuChildren) {
		this.hasMenuChildren = hasMenuChildren;
	}
	@OneToMany(targetEntity=Resource.class,mappedBy="parent",cascade=CascadeType.ALL,fetch=FetchType.EAGER)	
	public Set<Resource> getChildren() {
		return children;
	}
	public void setChildren(Set<Resource> children) {
		this.children = children;
	}
	@Column(name="show_sequence",nullable=false)
	public int getShowSequence() {
		return showSequence;
	}
	public void setShowSequence(int showSequence) {
		this.showSequence = showSequence;
	}
	@Column(name="deleted",nullable=false)
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	@ManyToMany(targetEntity=Role.class, cascade=CascadeType.ALL)
	@JoinTable(name="role_resource", joinColumns=@JoinColumn(name="role_sn",referencedColumnName="role_sn"),
				inverseJoinColumns=@JoinColumn(name="resource_sn",referencedColumnName="resource_sn"))
	@JSON(serialize=false)
	public Set<Role> getRoleResources() {
		return roleResources;
	}
	public void setRoleResources(Set<Role> roleResources) {
		this.roleResources = roleResources;
	}
}
