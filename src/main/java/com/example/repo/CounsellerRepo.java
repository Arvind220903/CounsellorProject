package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CounsellorEntity;

@Repository
public interface CounsellerRepo extends JpaRepository<CounsellorEntity, Integer>{
	public CounsellorEntity findByEmailAndPass(String email,String pass);

	public CounsellorEntity findByEmail(String mail);

	;

}
