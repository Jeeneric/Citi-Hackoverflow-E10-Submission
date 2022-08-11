package com.jovan.msgamify.controllers;

import com.jovan.msgamify.entities.GameProfile;
import com.jovan.msgamify.services.GameProfileService;
import com.jovan.msgamify.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/game-profile")
@CrossOrigin
public class GameProfileController {

    @Autowired
    private GameProfileService gameProfileService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public GameProfile retrieveGameProfile(){
        return gameProfileService.retrieveGameProfile(JWTUtil.getUserId(httpServletRequest));
    }

    @PostMapping
    public GameProfile createGameProfile(){
        return gameProfileService.createGameProfile(JWTUtil.getUserId(httpServletRequest));
    }
}
