package com.realestate.serviceImpl;


import com.realestate.dto.InquiryDto;
import com.realestate.entity.Inquiry;

import com.realestate.entity.Property;
import com.realestate.entity.User;
import com.realestate.exception.InquiryNotFoundException;
import com.realestate.repository.InquiryRepository;
import com.realestate.repository.PropertyRepository;
import com.realestate.repository.UserRepo;
import com.realestate.service.InquiryService;
import jakarta.transaction.Transactional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepo userRepository;


    @Override
    public String createInquiry(InquiryDto inquiryDto) {
        Inquiry inquiry = new Inquiry(inquiryDto);

        Property property = propertyRepository.findById(inquiryDto.getPropertyId())
                .orElseThrow(() -> new InquiryNotFoundException("Property not found with ID: " + inquiryDto.getPropertyId()));

        User user = userRepository.findById(inquiryDto.getUserId())
                .orElseThrow(() -> new InquiryNotFoundException("User not found with ID: " + inquiryDto.getUserId()));

        inquiry.setProperty(property);
        inquiry.setUser(user);

        inquiryRepository.save(inquiry);
        return null;
    }

    @Override
    public InquiryDto getInquiryById(Integer inquiryId) {
        Optional<Inquiry> optionalInquiry = inquiryRepository.findById(inquiryId);
        if (optionalInquiry.isEmpty()) {
            throw new InquiryNotFoundException("Inquiry Not Found", HttpStatus.NOT_FOUND);
        }

        InquiryDto inquiryDto = new InquiryDto(optionalInquiry.get());
        inquiryDto.setInquiryId(inquiryId);
        return inquiryDto;
    }

    @Override
    public List<InquiryDto> getAllInquiries() {
        List<Inquiry> inquiries = inquiryRepository.findAll();
        return inquiries.stream().map(InquiryDto::new).collect(Collectors.toList());
    }

    @Override
    public String deleteInquiry(Integer inquiryId) {
        if (!inquiryRepository.existsById(inquiryId)) {
            throw new InquiryNotFoundException("Inquiry Not Found");
        }
        inquiryRepository.deleteById(inquiryId);
        return "Inquiry Deleted Successfully";
    }

    @Override
    public String updateInquiry(Integer inquiryId, InquiryDto inquiryDto) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(() ->
                new InquiryNotFoundException("Inquiry Not Found", HttpStatus.NOT_FOUND));

        if (inquiryDto.getMessage() != null){
            inquiry.setMessage(inquiryDto.getMessage());
        }
        if (inquiryDto.getStatus() != null){
            inquiry.setStatus(inquiryDto.getStatus());
        }

        inquiryRepository.save(inquiry);
        return "Inquiry Updated";
    }


    @Transactional
    @Override
    public String deleteAllInquiries() {
        inquiryRepository.deleteAll();
        return "All Inquiries Deleted Successfully";
    }
}

