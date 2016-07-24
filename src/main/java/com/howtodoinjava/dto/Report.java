package com.howtodoinjava.dto;

public class Report {
	private int id;
	private String name;
	private boolean active;
	private String date;
	private String color;

	public Report(){
		System.out.println("Inside Constructor with 0 arguments");
	}
	
public Report(int id,String name,boolean active,String date,String color){
		this.active=active;
		this.id=id;
		this.name=name;
		this.color=color;
		this.date=date;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	//----------------------------------------
	private Report(Builder builder) {
		this.active=builder.active;
		this.id=builder.id;
		this.name=builder.name;
		this.color=builder.color;
		this.date=builder.date;
	}

	public static class Builder {
		private int id;
		private String name;
		private boolean active;
		private String date;
		private String color;
		public Builder id(int id) {
			this.id = id;
			return this;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder active(boolean active) {
			this.active = active;
			return this;
		}

		public Builder date(String date) {
			this.date = date;
			return this;
		}

		public Builder color(String color) {
			this.color = color;
			return this;
		}


		public Report build() {
			if (name == null) {
                throw new IllegalArgumentException("Name cannot be null");
            }
			return new Report(this);
		}
	}
	//----------------------------------------
}
