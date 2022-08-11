package com.jovan.msaccounts.controllers;

import com.jovan.msaccounts.controllers.requests.DepositRequest;
import com.jovan.msaccounts.controllers.requests.FundTransferRequest;
import com.jovan.msaccounts.controllers.requests.WithdrawRequest;
import com.jovan.msaccounts.entities.Account;
import com.jovan.msaccounts.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    public Account deposit(@RequestBody @Valid DepositRequest request){
        return accountService.deposit(request);
    }

    @PostMapping("/withdraw")
    public Account withdraw(@RequestBody @Valid  WithdrawRequest request){
        return accountService.withdraw(request);
    }

    @PostMapping("/transfer")
    public void withdraw(@RequestBody @Valid FundTransferRequest request){
        accountService.transferFunds(request);
    }

}
