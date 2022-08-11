package com.jovan.msaccounts.controllers;

import com.jovan.msaccounts.constants.DestinationCategory;
import com.jovan.msaccounts.controllers.requests.DestinationCreationRequest;
import com.jovan.msaccounts.entities.Destination;
import com.jovan.msaccounts.services.DestinationService;
import com.jovan.msaccounts.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/destination")
@CrossOrigin
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/categories")
    public List<DestinationCategory> retrieveDestinationCategories(){
        return Arrays.asList(DestinationCategory.values());
    }

    @PostMapping
    public Destination createDestination(@RequestBody @Valid DestinationCreationRequest request){
        return destinationService.createDestination(JWTUtil.getUserId(httpServletRequest), request);
    }

}
