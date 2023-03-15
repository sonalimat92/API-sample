package com.demo.bluejay.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity

public class Record {

    public Record() {
	super();
	this.id = id; 
	this.name = name;
	this.email = email;
}
	
@Id
    @Column(name="Id")
      private Long id;


    @Column(name="Name")
	    private String name;


    @Column(name="Email")
	    private String email;


		public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }
	}



