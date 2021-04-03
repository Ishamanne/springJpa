package com.boot.dao;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "user")
public class User implements Serializable{
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name = "id")
	  private long id;
	  
	  @Column(name = "username")
	  private String username;
		
	  @Column(name = "address")
	  private String address;
	  
	  @Column(name = "email")
	  private String email;
	  
	  
	 
	  public User() { }

	  public User(long id) { 
	    this.id = id;
	  }
	  
	  public User(String email, String name) {
	    this.email = email;
	    this.username = name;
	  }
	  
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	  
}
