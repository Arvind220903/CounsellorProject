package com.example.dto;


public class EnquiryDTO {
	private String name;
	private String phn;
	private String pwd;
	private String email;
	private String classmode;
	private String course;
	private String enqStatus;
	private Integer counsellorId;
	private Integer enquiry_Id;
	
	public Integer getEnquiry_Id() {
		return enquiry_Id;
	}

	public void setEnquiry_Id(Integer enquiry_Id) {
		this.enquiry_Id = enquiry_Id;
	}

	public Integer getCounsellorId() {
		return counsellorId;
	}

	public void setCounsellorId(Integer counsellorId) {
		this.counsellorId = counsellorId;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClassmode() {
		return classmode;
	}

	public void setClassmode(String classmode) {
		this.classmode = classmode;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getEnqStatus() {
		return enqStatus;
	}

	public void setEnqStatus(String enqStatus) {
		this.enqStatus = enqStatus;
	}

	@Override
	public String toString() {
		return "EnquiryDTO [name=" + name + ", phn=" + phn + ", pwd=" + pwd + ", email=" + email + ", classmode="
				+ classmode + ", course=" + course + ", enqStatus=" + enqStatus + "]";
	}
	
}
