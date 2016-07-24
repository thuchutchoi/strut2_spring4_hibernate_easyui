package com.howtodoinjava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.howtodoinjava.dto.Report;
import com.howtodoinjava.dto.Report.Builder;

@Entity
@Table(name="EMPLOYEE")
public class EmployeeEntity {
     
    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;
     
    @Column(name="FIRSTNAME")
    private String firstname;
 
    @Column(name="LASTNAME")
    private String lastname;
 
    @Column(name="EMAIL")
    private String email;
     
    @Column(name="TELEPHONE")
    private String telephone;
     
     
    public String getEmail() {
        return email;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public EmployeeEntity() {
	}
    private EmployeeEntity(Builder builder) {
    	this.id=builder.id;
	    this.firstname=builder.firstname;
	    this.lastname=builder.lastname;
	    this.email=builder.email;
	    this.telephone=builder.telephone;
	}

	public static class Builder {
	    private Integer id;
	    private String firstname;
	    private String lastname;
	    private String email;
	    private String telephone;
	    
		public Builder id(Integer id) {
			this.id = id;
			return this;
		}
		public Builder firstname(String firstname) {
			this.firstname = firstname;
			return this;
		}

		public Builder lastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder telephone(String telephone) {
			this.telephone = telephone;
			return this;
		}


		public EmployeeEntity build() {
			if (firstname == null) {
                throw new IllegalArgumentException("Name cannot be null");
            }
			return new EmployeeEntity(this);
		}
	}

}