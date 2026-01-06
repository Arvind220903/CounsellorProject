package com.example.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity

public class EnquiryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquiry_Id;
	private String name;
	private String phn;
	private String email;
	private String pwd;
	@ManyToOne
	@JoinColumn(name="counsellorId")
	private CounsellorEntity counsellor;
	private String classmode;
	private String enqStatus="OPEN";
	private String course;
	public String getEnqStatus() {
		return enqStatus;
	}
	public void setEnqStatus(String enqStatus) {
		this.enqStatus = enqStatus;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhn() {
		return phn;
	}
	public void setPhn(String phn) {
		this.phn = phn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public CounsellorEntity getCounsellor() {
		return counsellor;
	}
	public void setCounsellor(CounsellorEntity counsellor) {
		this.counsellor = counsellor;
	}
	public String getClassmode() {
		return classmode;
	}
	public void setClassmode(String classMode) {
		this.classmode = classMode;
	}
	
	public Integer getEnquiry_Id() {
		return enquiry_Id;
	}
	public void setEnquiry_Id(Integer enquiry_Id) {
		this.enquiry_Id = enquiry_Id;
	}
	

}
