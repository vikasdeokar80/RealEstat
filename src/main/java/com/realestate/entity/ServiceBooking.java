package com.realestate.entity;

import com.realestate.dto.ServiceBookingDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceBookingId;
    private  String status;
    private  String col;
    private String serviceBookingCol;
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Service service;
//    @ManyToOne(fetch = FetchType.EAGER)
//    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;



    public ServiceBooking(ServiceBookingDto serviceBookingDto){
        this.serviceBookingId = serviceBookingDto.getServiceBookingId();
        this.status = serviceBookingDto.getStatus();
        this.col = serviceBookingDto.getCol();
        this.serviceBookingCol = serviceBookingDto.getServiceBookingCol();

        if (serviceBookingDto.getServiceId() != null) {
            this.service = new Service();
            this.service.setServiceId(serviceBookingDto.getServiceId());
        }
        if (serviceBookingDto.getUserId() != null) {
            this.user = new User();
            this.user.setUserId(serviceBookingDto.getUserId());
        }
    }
}
