package com.realestate.service;


import com.realestate.dto.InquiryDto;
import java.util.List;

public interface InquiryService {
    String createInquiry(InquiryDto inquiryDto);
    InquiryDto getInquiryById(Integer inquiryId);
    List<InquiryDto> getAllInquiries();
    String deleteInquiry(Integer inquiryId);
    String updateInquiry(Integer inquiryId, InquiryDto inquiryDto);
    String deleteAllInquiries();
}

