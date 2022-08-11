package com.jovan.msaccounts.services;

import com.jovan.msaccounts.controllers.requests.AccountCreationRequest;
import com.jovan.msaccounts.controllers.requests.DepositRequest;
import com.jovan.msaccounts.controllers.requests.FundTransferRequest;
import com.jovan.msaccounts.controllers.requests.WithdrawRequest;
import com.jovan.msaccounts.entities.Account;
import com.jovan.msaccounts.exceptions.AccountNotFoundException;
import com.jovan.msaccounts.repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> inquireAccounts(UUID userId){
        log.info("inquiring all accounts for user {}", userId);
        return accountRepository.findAllByUserId(userId);
    }

    public Account createAccount(UUID userId, AccountCreationRequest request){
        log.info("creating {} account for user {}", request.getAccountType(), userId);
        return accountRepository.save(
                Account.builder()
                        .accountId(UUID.randomUUID())
                        .userId(userId)
                        .accountType(request.getAccountType())
                        .balance(BigDecimal.ZERO)
                        .build());
    }

    public Account createAccount(Account account){
        log.info("creating {} account for user {}", account.getAccountType(), account.getUserId());
        return accountRepository.save(account);
    }

    public Account retrieveAccount(UUID accountId){
        log.info("attempting to retrieve account {}", accountId);
        return accountRepository.findById(accountId)
                .orElseThrow(
                        () -> {
                            log.error("failed to find account with id: {}", accountId);
                            return new AccountNotFoundException("no matching account found for the given accountId");
                        }
                );
    }

    public Account deposit(DepositRequest request){
        log.info("processing deposit of {} into {}", request.getAmount(), request.getAccountId());
        Account account = retrieveAccount(request.getAccountId());
        account.setBalance(account.getBalance().add(request.getAmount()).setScale(2, RoundingMode.HALF_DOWN));
        return accountRepository.save(account);
    }

    public Account withdraw(WithdrawRequest request){
        log.info("processing withdrawal of {} from {}", request.getAmount(), request.getAccountId());
        Account account = retrieveAccount(request.getAccountId());
        account.setBalance(account.getBalance().add(request.getAmount().negate()).setScale(2, RoundingMode.HALF_DOWN));
        return accountRepository.save(account);
    }

    public void transferFunds(FundTransferRequest fundTransferRequest){
        log.info("transferring {} from {} to {}",
                fundTransferRequest.getTransferAmount(),
                fundTransferRequest.getBeneAccountId(),
                fundTransferRequest.getRecvAccountId());
        Account beneAccount = retrieveAccount(fundTransferRequest.getBeneAccountId());
        Account recvAccount = retrieveAccount(fundTransferRequest.getRecvAccountId());
        beneAccount.setBalance(beneAccount.getBalance().add(fundTransferRequest.getTransferAmount().negate()).setScale(2, RoundingMode.HALF_DOWN));
        recvAccount.setBalance(recvAccount.getBalance().add(fundTransferRequest.getTransferAmount()).setScale(2, RoundingMode.HALF_DOWN));
        accountRepository.save(beneAccount);
        accountRepository.save(recvAccount);
    }

    public void deleteAccount(UUID accountId){
        log.info("deleting account {}", accountId);
        accountRepository.delete(retrieveAccount(accountId));
    }

}
