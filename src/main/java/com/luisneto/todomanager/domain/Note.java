package com.luisneto.todomanager.domain;

import java.util.Date;

import javax.persistence.*;

/**
 * @author Luis Neto
 */
@Entity
@Table(name="NOTE")
public class Note {
	private Integer noteId;
	private Integer todoId;
	private String note;
	private Date creationDate;
	
	@Id
	public Integer getNoteId() {
		return noteId;
	}
	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}
	
	public Integer getTodoId() {
		return todoId;
	}
	public void setTodoId(Integer todoId) {
		this.todoId = todoId;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}	

}
