package com.example.MSIAM.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import com.example.MSIAM.DTO.JwtDTO;
import com.example.MSIAM.entity.Token;
import com.example.MSIAM.exceptions.AuthTokenDoesNotExistException;
import com.example.MSIAM.repository.TokenRepository;

@CrossOrigin
@RestController
public class TokenController {

    private TokenRepository tokenRepo;

    public TokenController(TokenRepository tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
    @RequestMapping(value = "/jwt", method = RequestMethod.POST)
    public JwtDTO exchangeJwt(HttpServletRequest request, HttpServletResponse response) {
        final String authHeader = request.getHeader("AuthToken");

        Optional<Token> search = tokenRepo.findByAuthorization(UUID.fromString(authHeader));
        if (!search.isPresent()) {
            throw new AuthTokenDoesNotExistException();
        }

        String jwt = search.get().getJwt();

        return new JwtDTO(jwt);
    }

}
