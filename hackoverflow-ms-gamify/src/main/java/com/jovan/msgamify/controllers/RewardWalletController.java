package com.jovan.msgamify.controllers;

import com.jovan.msgamify.controllers.responses.RewardWalletResponse;
import com.jovan.msgamify.services.RewardWalletService;
import com.jovan.msgamify.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/reward-wallet")
@CrossOrigin
public class RewardWalletController {

    @Autowired
    private RewardWalletService rewardWalletService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public RewardWalletResponse getRewardWallet(){
        return RewardWalletResponse
                .builder()
                .rewardWallet(rewardWalletService.inquireWallet(JWTUtil.getUserId(httpServletRequest)))
                .build();
    }
}
