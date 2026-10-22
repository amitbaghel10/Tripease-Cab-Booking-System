package com.example.tripease.Service;

import com.example.tripease.DTO.Request.DriverRequest;
import com.example.tripease.DTO.Response.DriverResponse;
import com.example.tripease.Model.Driver;
import com.example.tripease.Repository.DriverRepository;
import com.example.tripease.Transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public DriverResponse addDriver(DriverRequest driverRequest) {

        Driver driver = DriverTransformer.driverRequestToDriver(driverRequest);
        Driver savedDriver = driverRepository.save(driver);
        return DriverTransformer.driverToDriverResponse(savedDriver);

    }
}
