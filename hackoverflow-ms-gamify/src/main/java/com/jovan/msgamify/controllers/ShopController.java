package com.jovan.msgamify.controllers;

import com.jovan.msgamify.controllers.reqeusts.RewardPurchaseRequest;
import com.jovan.msgamify.services.RewardWalletService;
import com.jovan.msgamify.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/shop")
@CrossOrigin
public class ShopController {

    @Autowired
    private RewardWalletService rewardWalletService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping("/purchase")
    public void purchaseReward(@RequestBody RewardPurchaseRequest request){
        rewardWalletService.purchaseReward(JWTUtil.getUserId(httpServletRequest), request);
    }
}
