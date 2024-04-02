package com.realestate.serviceImpl;


import com.realestate.dto.ServiceBookingDto;
import com.realestate.entity.ServiceBooking;
import com.realestate.entity.Service;
import com.realestate.entity.User;
import com.realestate.exception.ServiceBookingNotFoundException;

import com.realestate.repository.ServiceRepository;
import com.realestate.repository.UserRepo;

import com.realestate.service.ServiceBookingService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@org.springframework.stereotype.Service
public class ServiceBookingServiceImpl implements ServiceBookingService {

    @Autowired
    private ServiceBookingRepository serviceBookingRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepo userRepository;

    @Override
    public String createServiceBooking(ServiceBookingDto serviceBookingDto) {
        ServiceBooking serviceBooking = new ServiceBooking(serviceBookingDto);

        Service service = serviceRepository.findById(serviceBookingDto.getServiceId())
                .orElseThrow(() -> new ServiceBookingNotFoundException("Service not found with ID: " + serviceBookingDto.getServiceId()));

        User user = userRepository.findById(serviceBookingDto.getUserId())
                .orElseThrow(() -> new ServiceBookingNotFoundException("User not found with ID: " + serviceBookingDto.getUserId()));

        serviceBooking.setService(service);
        serviceBooking.setUser(user);

        serviceBookingRepository.save(serviceBooking);
        return "Service booking created successfully.";
    }

    @Override
    public ServiceBookingDto getServiceBookingById(Integer serviceBookingId) {
        Optional<ServiceBooking> optionalServiceBooking = serviceBookingRepository.findById(serviceBookingId);
        if (optionalServiceBooking.isEmpty()) {
            throw new ServiceBookingNotFoundException("Service booking not found with ID: " + serviceBookingId);
        }
        return new ServiceBookingDto(optionalServiceBooking.get());
    }

    @Override
    public List<ServiceBookingDto> getAllServiceBookings() {
        List<ServiceBooking> serviceBookings = serviceBookingRepository.findAll();
        return serviceBookings.stream().map(ServiceBookingDto::new).collect(Collectors.toList());
    }

    @Override
    public String deleteServiceBooking(Integer serviceBookingId) {
        if (!serviceBookingRepository.existsById(serviceBookingId)) {
            throw new ServiceBookingNotFoundException("Service booking not found with ID: " + serviceBookingId);
        }
        serviceBookingRepository.deleteById(serviceBookingId);
        return "Service booking deleted successfully.";
    }

    @Override
    public String updateServiceBooking(Integer serviceBookingId, ServiceBookingDto serviceBookingDto) {
        ServiceBooking serviceBooking = serviceBookingRepository.findById(serviceBookingId)
                .orElseThrow(() -> new ServiceBookingNotFoundException("Service booking not found with ID: " + serviceBookingId));

        serviceBooking.setStatus(serviceBookingDto.getStatus());
        serviceBooking.setCol(serviceBookingDto.getCol());
        serviceBooking.setServiceBookingCol(serviceBookingDto.getServiceBookingCol());

        serviceBookingRepository.save(serviceBooking);
        return "Service booking updated successfully.";
    }
}
