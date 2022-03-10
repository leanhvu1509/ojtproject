package com.lavu.ojtsource.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDTO extends AbstractDTO<String>{

	private long id;
	@NotEmpty
	private String name;
	
	private short status;

	private Boolean isEdit = false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	
	
}
