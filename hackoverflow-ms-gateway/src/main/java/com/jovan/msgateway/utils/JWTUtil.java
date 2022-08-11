package com.jovan.msgateway.utils;

import com.jovan.msgateway.exceptions.InvalidJWTException;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.text.ParseException;
import java.util.Date;

@Slf4j
public class JWTUtil {

    public static void verifyJWT(String token){
        JWT jwt = parseJWTFromString(token);
        JWTClaimsSet claimsSet = extractAllClaims(jwt);

        if(Strings.isEmpty(claimsSet.getSubject())){
            log.info("received jwt has no subject value");
            throw new InvalidJWTException("jwt has no subject value");
        }

        if(claimsSet.getExpirationTime() == null || claimsSet.getExpirationTime().before(new Date())){
            log.info("received jwt is expired");
            throw new InvalidJWTException("jwt has expired");
        }
    }

    private static JWTClaimsSet extractAllClaims(JWT jwt) {
        try{
            return jwt.getJWTClaimsSet();
        } catch (ParseException parseException){
            log.error("Failed to parse jwt");
            throw new InvalidJWTException("failed to parse jwt");
        }
    }

    private static JWT parseJWTFromString(String token){
        try{
            return JWTParser.parse(token);
        } catch (ParseException parseException){
            log.error("Failed to parse jwt");
            throw new InvalidJWTException("failed to parse jwt");
        }
    }
}
