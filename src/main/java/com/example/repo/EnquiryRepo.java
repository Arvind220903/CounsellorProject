package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.EnquiryEntity;

import jakarta.transaction.Transactional;

@Repository
public interface EnquiryRepo extends JpaRepository<EnquiryEntity, Integer>{
	public List<EnquiryEntity> findByCounsellorCounsellorId(Integer id);
	  @Modifying
	    @Transactional
	    @Query("""
	        UPDATE EnquiryEntity e
	        SET e.enqStatus = :status
	        WHERE e.enquiry_Id = :id
	    """)
	  public int updateStatusById(Integer id, String status);

}
