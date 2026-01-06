package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dto.CounsellorDTO;
import com.example.dto.DashboardDTO;
import com.example.dto.EnquiryDTO;

@Service
public interface CousellorService {
	public CounsellorDTO login(CounsellorDTO cd);
	public boolean checkEmail(String mail);
	public boolean register(CounsellorDTO cd);
	public DashboardDTO dashboard(Integer CounsellorId);
	public boolean addEnq(EnquiryDTO ed,Integer cid);
	public List<EnquiryDTO> view(Integer CounsellorId);
}