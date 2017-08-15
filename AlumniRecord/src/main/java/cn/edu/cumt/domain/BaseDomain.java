package cn.edu.cumt.domain;

import java.io.Serializable;

public class BaseDomain implements Serializable {

	private Integer id;
	
	private Boolean deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
}
