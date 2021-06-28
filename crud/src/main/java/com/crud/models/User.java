package com.crud.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.crud.utils.Utils;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO: Apply Validation here
 * 
 * @author Anurag Asthana
 *
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User extends BaseEntity implements Cloneable {

	@Column(name = "firstName")
	@NotNull
	@NotEmpty
	@Length(min = 1)
	String firstName;
	
	@Column(name = "sur_name")
	String surName;
	
	@Column(name = "username")
	@NotNull
	@NotEmpty
	@Length(min = 5, max = 50)
	String userName;
	
	@Column(name = "password")
	@NotNull
	@NotEmpty
	@Length(min = 5, max = 50)
	String password;
	
	@Column(name = "email")
	@Email
	@Length(min = 5, max = 50)
	String email;
	
	/**
	 * For india only, It should by dynamic change based on Country for internationalization
	 */
	@Column(name = "mobile")
	@NotNull
	@Length(min = 10, max = 10)
	String mobile;
	
	@Column(name = "dob")
	@Temporal(TemporalType.TIMESTAMP)
	Date dob;
	
	@Column(name = "access_time")
	@Temporal(TemporalType.TIMESTAMP)
	Date accessTime;
	
	@Column(name = "pincode")
	@Length(min = 6, max = 6)
	String pincode;
	
	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	Date updateTime;
	
	@Column(name = "deleted")
	boolean deleted;
	
	@PrePersist
	public void onUpdate() {
		this.setUpdateTime(Utils.now());
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
