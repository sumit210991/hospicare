package com.example.todo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class ToDoList {

	   @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String title;

	    private String content;

	    private Date updated;
	    
	    private String status;
	    
	    public ToDoList() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	    public ToDoList(String title, String content, Date updated, String status) {
	        super();
	        this.title = title;
	        this.content = content;
	        this.updated = updated;
	        this.status = status;
	    }
	    
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

	    public Date getUpdated() {
	        return updated;
	    }

	    public void setUpdated(Date updated) {
	        this.updated = updated;
	    }

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	    
}
