package com.daodao.entity;

import java.util.Date;

public class Contact {
	private Integer id;
	private String name;
	private Date birth;
	private Integer age;
	private String phone;
	private String zipCode;
	public Contact(){}
	public Contact(Integer id, String name, Date birth, Integer age, String phone, String zipCode) {
		super();
		this.id = id;
		this.name = name;
		this.birth = birth;
		this.age = age;
		this.phone = phone;
		this.zipCode = zipCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", birth=" + birth + ", age=" + age + ", phone=" + phone
				+ ", zipCode=" + zipCode + "]";
	}
}
