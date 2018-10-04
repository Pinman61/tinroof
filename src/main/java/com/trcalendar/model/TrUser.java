package com.trcalendar.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trUser")
public class TrUser {
    /* Data Fields */
    private int trUserId;
    private TrCalendar trCalendar;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
 
    public TrUser() { }
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "trUserId", unique = true, nullable = false)
	public int getTrUserId() {
		return trUserId;
	}
	
    @Column(name = "firstName")
    public String getFirstName() {
        return this.firstName;
    }
     public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    @Column(name = "lastName")
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getName() {
        return this.firstName + " " + this.lastName;
    }
    
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return this.username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 30)
    public String getPassword() {
        return this.password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "trUser", cascade = CascadeType.ALL)
	public TrCalendar getTrCalendar() {
		return this.trCalendar;
	}   
    
}