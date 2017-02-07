package com.yztc.pojo;

import java.util.*;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Person {
	private PersonInfo personInfo;  //一对一关系
	
	private List<Book> books ; //一对多关系
	
	private int id;
	private String name;
	private int age;
	private String remark;
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	public String toString(){
		return "id="+id+",name="+name+",age="+age+",remark="+remark;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}

}




