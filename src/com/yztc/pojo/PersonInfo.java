package com.yztc.pojo;

import java.util.Date;

public class PersonInfo {
	
	private int id;
	private String station;
	private Date joinDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id="+id+",station="+station+",join_date="+joinDate;
	}
}
