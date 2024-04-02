package com.realestate.service;


import com.realestate.dto.PropertyDto;
import com.realestate.exception.PropertyNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyService  {
    public String addProperty(PropertyDto propertyDto);


    String updateProperty(PropertyDto propertyDto, Integer propertyId);

    List<PropertyDto> getAllProperties();

    PropertyDto getPropertyById(Integer propertyId) throws PropertyNotFoundException;

    void deletePropertyById(Integer propertyId) throws PropertyNotFoundException;
}
