package com.jovan.msaccounts.controllers;

import com.jovan.msaccounts.controllers.requests.AccountCreationRequest;
import com.jovan.msaccounts.entities.Account;
import com.jovan.msaccounts.services.AccountService;
import com.jovan.msaccounts.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping("/inquire")
    public List<Account> inquireAccounts(){
        return accountService.inquireAccounts(JWTUtil.getUserId(httpServletRequest));
    }

    @PostMapping
    public Account createAccount(@RequestBody @Valid AccountCreationRequest request){
        return accountService.createAccount(JWTUtil.getUserId(httpServletRequest), request);
    }

    @GetMapping
    public Account retrieveAccount(@RequestParam("account-id") UUID accountId){
        return accountService.retrieveAccount(accountId);
    }

    @DeleteMapping
    public void deleteAccount(@RequestParam("account-id") UUID accountId){
        accountService.deleteAccount(accountId);
    }
}
