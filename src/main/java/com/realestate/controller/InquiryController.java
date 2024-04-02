package com.realestate.controller;

import com.realestate.dto.InquiryDto;
import com.realestate.dto.ResponseDto;

import com.realestate.dto.ResponseInquiryDto;
import com.realestate.exception.InquiryNotFoundException;
import com.realestate.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/inquiries")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @PostMapping("/add")
    public ResponseEntity<String> createInquiry(@RequestBody InquiryDto inquiryDto) {
        try {
            this.inquiryService.createInquiry(inquiryDto);
            return ResponseEntity.ok("Inquiry created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create inquiry: " + e.getMessage());
        }
    }


    @GetMapping("/byId")
    public ResponseEntity<ResponseInquiryDto> getInquiryById(@RequestParam Integer inquiryId) {
        try {
            InquiryDto inquiryDto = inquiryService.getInquiryById(inquiryId);
            ResponseInquiryDto responseInquiryDto = new ResponseInquiryDto("success");
            responseInquiryDto.setObject(inquiryDto);
            return ResponseEntity.status(HttpStatus.OK).body(responseInquiryDto);
        } catch (InquiryNotFoundException ex) {
            ResponseInquiryDto responseInquiryDto = new ResponseInquiryDto("unsuccessful");
            responseInquiryDto.setException("Inquiry Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseInquiryDto);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<InquiryDto>> getAllInquiries() {
        List<InquiryDto> inquiries = inquiryService.getAllInquiries();
        return ResponseEntity.status(HttpStatus.OK).body(inquiries);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteInquiry(@RequestParam Integer inquiryId) {
        try {
            String message = inquiryService.deleteInquiry(inquiryId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", message));
        } catch (InquiryNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccessful", "Inquiry Not Found"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateInquiry(@RequestBody InquiryDto inquiryDto, @RequestParam Integer inquiryId) {
        try {
            String message = inquiryService.updateInquiry(inquiryId, inquiryDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", message));
        } catch (InquiryNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccessful", "Inquiry Not Found"));
        }
    }


    @DeleteMapping("/deleteAll")
    public ResponseEntity<ResponseDto> deleteAllInquiries() {
        try {
            String message = inquiryService.deleteAllInquiries();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", message));
        } catch (InquiryNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccessful", "Inquiries Not Found"));
        }
    }
}

