package com.jovan.msgamify.controllers;

import com.jovan.msgamify.controllers.responses.RewardInquiryResponse;
import com.jovan.msgamify.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reward")
@CrossOrigin
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping("/inquire")
    public RewardInquiryResponse inquireRewards(){
        return RewardInquiryResponse.builder().rewards(rewardService.inquireRewards()).build();
    }


}
