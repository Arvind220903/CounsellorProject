package com.example.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CounsellorDTO;
import com.example.dto.DashboardDTO;
import com.example.dto.EnquiryDTO;
import com.example.dto.StatusUpdateDTO;
import com.example.entity.EnquiryEntity;
import com.example.repo.CounsellerRepo;
import com.example.repo.EnquiryRepo;
import com.example.service.CousellorService;

@RestController
@CrossOrigin(
	    origins = "https://counsellorprojectfrontend01.onrender.com",
	    allowCredentials = "true"
	)
public class CounsellorController {

    @Autowired
    private CousellorService service;

    @Autowired
    private EnquiryRepo enquiryRepo;

    @Autowired
    private CounsellerRepo counsellorRepo;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @RequestBody CounsellorDTO dto) {

        if (!service.checkEmail(dto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Duplicate Email"));
        }

        service.register(dto);
        return ResponseEntity.ok(Map.of("message", "success"));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<DashboardDTO> login(
            @RequestBody CounsellorDTO dto) {

        CounsellorDTO user = service.login(dto);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(service.dashboard(user.getCounsellorId()));
    }

    // DASHBOARD
    @GetMapping("/dashboard/{id}")
    public ResponseEntity<DashboardDTO> dashboard(
            @PathVariable Integer id) {

        if (!counsellorRepo.existsById(id)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(service.dashboard(id));
    }

    // ADD ENQUIRY
    @PostMapping("/enquiry")
    public ResponseEntity<?> addEnquiry(
            @RequestBody EnquiryDTO dto) {

        if (dto.getCounsellorId() == null) {
            return ResponseEntity.status(401).build();
        }

        service.addEnq(dto, dto.getCounsellorId());
        return ResponseEntity.ok(Map.of("message", "Added"));
    }

    // VIEW ENQUIRIES
    @GetMapping("/enquiries/{id}")
    public ResponseEntity<List<EnquiryDTO>> view(
            @PathVariable Integer id) {

        if (!counsellorRepo.existsById(id)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(service.view(id));
    }

    // UPDATE STATUS
    @PostMapping("/statusupdate")
    public ResponseEntity<List<EnquiryDTO>> updateStatus(
            @RequestBody StatusUpdateDTO dto) {

        enquiryRepo.updateStatusById(dto.getEnquiryId(), dto.getStatus());

        EnquiryEntity enq = enquiryRepo
                .findById(dto.getEnquiryId())
                .orElseThrow();

        Integer cid = enq.getCounsellor().getCounsellorId();
        return ResponseEntity.ok(service.view(cid));
    }
}
