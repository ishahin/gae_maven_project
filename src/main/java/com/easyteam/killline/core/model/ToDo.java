package com.easyteam.killline.core.model;


import com.easyteam.killline.common.model.GenericEntity;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class ToDo extends GenericEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 976967753642746414L;
	/**
	 * Todo owner
	 */
	
	private String owner;
	/**
	 * Todo summary
	 */
	private String summary;
	/**
	 * Todo summary
	 */
	private String description;
	/**
	 * Todo url
	 */
	private String url;
	/**
	 * Todo is completed
	 */
	boolean completed;

	/**
	 * Default constructor 
	 */
	public ToDo() {

	}

	/**
	 * Constructor
	 * 
	 * @param creator
	 * @param summary
	 * @param description
	 * @param url
	 */
	public ToDo(String creator, String summary, String description, String url) {
		this.owner = creator;
		this.summary = summary;
		this.description = description;
		this.url = url;
		this.completed = false;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
