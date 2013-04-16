package com.easyteam.killline.common.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.condition.IfFalse;


/**
 * 
 * 
 * @author Anderson Soares < andersonsoares@killline.com >
 *
 */
@SuppressWarnings("serial")
public abstract class GenericEntity implements Serializable {
	
	@Id
	protected Long id;
	
	/** 
	 * Index this attribute only when it values = False 
	 * */
	@Index(IfFalse.class)
	private boolean deleted;
	
	/* Getters && Setters... */
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
