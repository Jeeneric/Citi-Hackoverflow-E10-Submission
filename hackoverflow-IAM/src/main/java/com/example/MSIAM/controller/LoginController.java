package com.example.MSIAM.controller;

import java.security.SecureRandom;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.MSIAM.entity.AuthenticationRequest;
import com.example.MSIAM.entity.Token;
import com.example.MSIAM.jwt.JwtUtil;
import com.example.MSIAM.exceptions.LoginFailedException;
import com.example.MSIAM.service.TokenService;
import com.example.MSIAM.service.UserService;

@CrossOrigin
@RestController
public class LoginController {
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
            HttpServletResponse response) {

        try {

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword());

            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new LoginFailedException();
        }

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

        String jwt = jwtTokenUtil.generateToken(userDetails);

        Token authToken = new Token();
        authToken.setAuthorization(UUID.randomUUID());
        authToken.setJwt(jwt);

        tokenService.createToken(authToken);

        response.addHeader("Authtoken", authToken.getAuthorization().toString());
        response.addHeader("Access-Control-Expose-Headers", "Authtoken");
    }

}
