package com.example.tripease.Controller;

import com.example.tripease.DTO.Request.DriverRequest;
import com.example.tripease.DTO.Response.DriverResponse;
import com.example.tripease.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping("/add")
    public DriverResponse addDriver(@RequestBody DriverRequest driverRequest){
        return driverService.addDriver(driverRequest);
    }

}
