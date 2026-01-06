package com.example.dto;


public class CounsellorDTO {
		private int counsellorId;
		private String name;
		private String email;
		private String pass;
		private String phn;
		public String getName() {
			return name;
		}
		
		public int getCounsellorId() {
			return counsellorId;
		}

		public void setCounsellorId(int id) {
			this.counsellorId = id;
		}

		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		public String getPhn() {
			return phn;
		}
		public void setPhn(String phn) {
			this.phn = phn;
		}
		
}
