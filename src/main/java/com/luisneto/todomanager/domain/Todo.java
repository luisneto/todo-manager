package com.luisneto.todomanager.domain;

import java.util.Date;

import javax.persistence.*;

/**
 * @author Luis Neto
 */
@Entity
@Table(name="TODO")
public class Todo {
	private Integer id;
	private String title;
	private String description;
	private TodoStatus status;
	private Category category;
	private Date creationDate;
	private Date modificationDate;
	
	public Todo(String title, String description) {
		this(title, description, TodoStatus.OUTSTANDING);
	}

	public Todo(String title, String description, TodoStatus status) {
		this.title = title;
		this.description = description;
		this.status = status;
	}
	
	public Todo() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public TodoStatus getStatus() {
		return status;
	}
	
	public void setStatus(TodoStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="categoryId")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public enum TodoStatus {
		OUTSTANDING,
		COMPLETED
	}

}
