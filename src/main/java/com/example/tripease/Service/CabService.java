package com.example.tripease.Service;

import com.example.tripease.DTO.Request.CabRequest;
import com.example.tripease.DTO.Response.CabResponse;
import com.example.tripease.Exception.DriverNotFoundException;
import com.example.tripease.Model.Cab;
import com.example.tripease.Model.Driver;
import com.example.tripease.Repository.DriverRepository;
import com.example.tripease.Transformer.CabTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CabService {
    @Autowired
    DriverRepository driverRepository;

    public CabResponse registerCab(CabRequest cabRequest, int driverId) {


        Optional<Driver> optionalDriver = driverRepository.findById(driverId);

        if(optionalDriver.isEmpty()){
            throw new DriverNotFoundException("Invalid Driver Id! ");
        }

        Driver driver = optionalDriver.get();

        Cab cab = CabTransformer.cabRequestToCab(cabRequest);

        driver.setCab(cab);

        Driver savedDriver = driverRepository.save(driver); //save both driver and cab

        return CabTransformer.cabToCabResponse(savedDriver.getCab(), savedDriver);
    }
}
