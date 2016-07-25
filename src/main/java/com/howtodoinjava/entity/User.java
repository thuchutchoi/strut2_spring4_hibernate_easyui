package com.howtodoinjava.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;



@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 6295524232169619097L;

	private Long id;

	private String name;

	private String password;

	private String gender;

	private String country;

	private String aboutYou;

	private Boolean mailingList;

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
	@Length(max=50)
	@Column(name = "USER_NAME", nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=6, max=10)
	@Column(name = "USER_PASSWORD", nullable = false, length = 10)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotEmpty(message="Please select a gender")
	@Column(name = "USER_GENDER")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@NotEmpty(message="Please select a country")
	@Column(name = "USER_COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@NotEmpty
	@Length(max=100)
	@Column(name = "USER_ABOUT_YOU", length = 100)
	public String getAboutYou() {
		return aboutYou;
	}

	public void setAboutYou(String aboutYou) {
		this.aboutYou = aboutYou;
	}

	@Column(name = "USER_MAILING_LIST")
	public Boolean getMailingList() {
		return mailingList;
	}

	public void setMailingList(Boolean mailingList) {
		this.mailingList = mailingList;
	}

}
