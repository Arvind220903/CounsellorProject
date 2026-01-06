package com.example.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CounsellorDTO;
import com.example.dto.DashboardDTO;
import com.example.dto.EnquiryDTO;
import com.example.dto.StatusUpdateDTO;
import com.example.entity.EnquiryEntity;
import com.example.repo.EnquiryRepo;
import com.example.service.CousellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class CounsellorController {

    @Autowired
    private CousellorService service;
    @Autowired
    private EnquiryRepo er;
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @RequestBody CounsellorDTO counsellor) {

        boolean isUnique = service.checkEmail(counsellor.getEmail());

        if (!isUnique) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "Duplicate Email Found"));
        }

        boolean registered = service.register(counsellor);

        if (registered) {
            return ResponseEntity
                    .ok(Map.of("message", "success"));
        } else {
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of("message", "Failed"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<DashboardDTO> login(
            @RequestBody CounsellorDTO dto,
            HttpServletRequest request) {

        CounsellorDTO user = service.login(dto);

        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        

        HttpSession session = request.getSession(true);
        session.setAttribute("COUNSELLOR_ID", user.getCounsellorId());
        DashboardDTO ddto=new DashboardDTO();
        ddto=service.dashboard(user.getCounsellorId());

        return ResponseEntity.ok(ddto);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDTO> dashboard(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("COUNSELLOR_ID") == null) {
            return ResponseEntity.status(401).build();
        }

        Integer cid = (Integer) session.getAttribute("COUNSELLOR_ID");
        return ResponseEntity.ok(service.dashboard(cid));
    }

   
    @PostMapping("/enquiry")
    public ResponseEntity<Map<String, String>> addEnquiry(
            @RequestBody EnquiryDTO dto,
            HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("COUNSELLOR_ID") == null) {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("message", "Session expired. Please login again."));
        }

        Integer cid = (Integer) session.getAttribute("COUNSELLOR_ID");

        boolean status = service.addEnq(dto, cid);
        System.out.println();

        return status
                ? ResponseEntity.ok(Map.of("message", "Enquiry Added"))
                : ResponseEntity.internalServerError()
                    .body(Map.of("message", "Fail"));
    }



    @GetMapping("/enquiries")
    public ResponseEntity<List<EnquiryDTO>> view(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("COUNSELLOR_ID") == null) {
            return ResponseEntity.status(401).build();
        }

        Integer cid = (Integer) session.getAttribute("COUNSELLOR_ID");
       
        return ResponseEntity.ok(service.view(cid));
    }
    @PostMapping("/statusupdate")
    public ResponseEntity<List<EnquiryDTO>> update(
            @RequestBody StatusUpdateDTO sd) {

       
        er.updateStatusById(sd.getEnquiryId(), sd.getStatus());

        System.out.println(sd);
        Optional<EnquiryEntity> opt = er.findById(sd.getEnquiryId());

        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        
        Integer counsellorId =
                opt.get().getCounsellor().getCounsellorId();

       
        return ResponseEntity.ok(service.view(counsellorId));
    }


}
