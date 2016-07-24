package com.howtodoinjava.dto;

import java.util.Date;

public class Person {
	private String name;
	private String email;
	private int age;
	private Date birthday;
	private String phone;
	private String address;
	private float weight;
	private float height;

	private Person(Builder builder) {
		this.name = builder.name;
		this.email = builder.email;
		this.age = builder.age;
		this.birthday = builder.birthday;
		this.phone = builder.phone;
		this.address = builder.address;
		this.weight = builder.weight;
		this.name = builder.name;
		this.height = builder.height;
	}

	public static class Builder {
		private String name;
		private int age;

		private Date birthday;
		private String email;
		private String phone;
		private String address;
		private float weight;
		private float height;

		public Builder(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public Builder birthday(Date birthday) {
			this.birthday = birthday;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Builder weight(float weight) {
			this.weight = weight;
			return this;
		}

		public Builder height(float height) {
			this.height = height;
			return this;
		}

		public Person build() {
			if (name == null) {
                throw new IllegalArgumentException("Name cannot be null");
            }
			return new Person(this);
		}
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public float getWeight() {
		return weight;
	}

	public float getHeight() {
		return height;
	}
	public String toString(){
		return this.address+"/"+this.age+"/"+this.email+"/"+this.height+"/"+this.name+"/"+this.phone+"/"+this.weight+"/"+this.birthday;
	}
	public static void main(String[] args) {
		Person me = new Person.Builder("Nam", 31)
                .email("hainatu@gmail.com")
                .address("Hanoi")
                .build();
		System.out.println(me);
	}
}
