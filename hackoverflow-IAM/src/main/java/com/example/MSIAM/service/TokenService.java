package com.example.MSIAM.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.MSIAM.entity.Token;
import com.example.MSIAM.exceptions.TokenAlreadyExistsException;
import com.example.MSIAM.repository.TokenRepository;

@Service
public class TokenService {

    private TokenRepository tokenRepo;

    public TokenService(TokenRepository tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    /**
     * Updates a Token in token_mapping table
     * 
     * @param token
     * @return updated token
     */
    public Token update(Token token) {
        return tokenRepo.save(token);
    }

    /**
     * Creates a new token in token_mapping table
     * 
     * @param token
     * @return newly created token
     */
    public Token createToken(Token token) {
        Optional<Token> search = tokenRepo.findByAuthorization(token.getAuthorization());

        if (search.isPresent()) {
            throw new TokenAlreadyExistsException();
        }

        return tokenRepo.save(token);
    }

}
