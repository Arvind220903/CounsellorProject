package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CounsellorDTO;
import com.example.dto.DashboardDTO;
import com.example.dto.EnquiryDTO;
import com.example.entity.CounsellorEntity;
import com.example.entity.EnquiryEntity;
import com.example.repo.CounsellerRepo;
import com.example.repo.EnquiryRepo;
@Service
public class CousellorServiceImpl implements CousellorService {
	@Autowired
	private CounsellerRepo cr;
	@Autowired
	private EnquiryRepo er;
	@Override
	public CounsellorDTO login(CounsellorDTO cd) {
		CounsellorEntity ce=cr.findByEmailAndPass(cd.getEmail(), cd.getPass());
		 if(ce == null) {
		        return null;
		    }
		CounsellorDTO dto=new CounsellorDTO();
		BeanUtils.copyProperties(ce,dto);
		
		return dto;
	}

	@Override
	public boolean checkEmail(String mail) {
		CounsellorEntity ce=cr.findByEmail(mail);
		
		if(ce==null)return true;
		return false;
	}

	@Override
	public boolean register(CounsellorDTO cd) {

	    CounsellorEntity ce = new CounsellorEntity();
	    BeanUtils.copyProperties(cd, ce);

	    ce.setCounsellorId(null); // ⭐ CRITICAL LINE

	    CounsellorEntity saved = cr.save(ce);
	    return saved.getCounsellorId() != null;
	}


	@Override
	public DashboardDTO dashboard(Integer CounsellorId) {
		List<EnquiryEntity> l1=er.findByCounsellorCounsellorId(CounsellorId);
		DashboardDTO dto=new DashboardDTO();
		int open=0;
		int lost=0;
		int enrolled=0;
		int counsellorId=CounsellorId;
		for(EnquiryEntity et:l1) {
			if(et.getEnqStatus().equals("OPEN"))open++;
			else if(et.getEnqStatus().equals("LOST"))lost++;
			else {
				enrolled++;
			}
			
			
		}
		dto.setTotal(l1.size());
		dto.setEnroll(enrolled);
		dto.setLost(lost);
		dto.setOpen(open);
		dto.setCounsellorId(CounsellorId);
		return dto;
	}

	@Override
	public boolean addEnq(EnquiryDTO ed,Integer cid) {
		Optional<CounsellorEntity> ce=cr.findById(cid);
		EnquiryEntity et=new EnquiryEntity();
		BeanUtils.copyProperties(ed, et);
		
		CounsellorEntity cet=ce.get();
		et.setCounsellor(cet);
		EnquiryEntity save=er.save(et);
		
		return save.getEnquiry_Id()!=null;
	}

	@Override
	public List<EnquiryDTO> view(Integer CounsellorId) {
		List<EnquiryEntity> l1=er.findByCounsellorCounsellorId(CounsellorId);
		List<EnquiryDTO> l2=new ArrayList<>();
		for(EnquiryEntity entity:l1) {
			EnquiryDTO dto=new EnquiryDTO();
			BeanUtils.copyProperties(entity, dto);
			l2.add(dto);
		}
		return l2;
	}
}
