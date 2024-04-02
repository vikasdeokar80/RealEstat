package com.realestate.serviceImpl;


import com.realestate.entity.ServiceBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Integer> {
}
