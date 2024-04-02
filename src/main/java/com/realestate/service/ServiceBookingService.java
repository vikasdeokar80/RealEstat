package com.realestate.service;

import com.realestate.dto.ServiceBookingDto;

import java.util.List;

public interface ServiceBookingService {
    String createServiceBooking(ServiceBookingDto serviceBookingDto);
    ServiceBookingDto getServiceBookingById(Integer serviceBookingId);
    List<ServiceBookingDto> getAllServiceBookings();
    String deleteServiceBooking(Integer serviceBookingId);
    String updateServiceBooking(Integer serviceBookingId, ServiceBookingDto serviceBookingDto);
}
