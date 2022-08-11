package com.jovan.msgamify.utils;

import com.jovan.msgamify.exceptions.InvalidJWTException;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.UUID;

@Slf4j
public class JWTUtil {

    public static UUID getUserId(HttpServletRequest request){
        return getUserId(request.getHeader("jwt").split(" ")[1]);
    }

    public static UUID getUserId(String jwt){
        try{
            return UUID.fromString(extractAllClaims(parseJWTFromString(jwt)).getSubject());
        } catch (IllegalArgumentException illegalArgumentException){
            log.error("Invalid accountId value in jwt");
            throw new InvalidJWTException("invalid accountId value");
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
