package cn.edu.cumt.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
	private Set<Role> roles=new HashSet<Role>(0);
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResourceSn() {
		return resourceSn;
	}
	public void setResourceSn(String resourceSn) {
		this.resourceSn = resourceSn;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Resource getParent() {
		return parent;
	}
	public void setParent(Resource parent) {
		this.parent = parent;
	}
	public Boolean getHasMenuChildren() {
		return hasMenuChildren;
	}
	public void setHasMenuChildren(Boolean hasMenuChildren) {
		this.hasMenuChildren = hasMenuChildren;
	}
	public Set<Resource> getChildren() {
		return children;
	}
	public void setChildren(Set<Resource> children) {
		this.children = children;
	}
	public int getShowSequence() {
		return showSequence;
	}
	public void setShowSequence(int showSequence) {
		this.showSequence = showSequence;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
