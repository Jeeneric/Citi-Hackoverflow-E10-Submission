package com.jovan.msaccounts.services;

import com.jovan.msaccounts.constants.AccountType;
import com.jovan.msaccounts.controllers.requests.DestinationCreationRequest;
import com.jovan.msaccounts.entities.Account;
import com.jovan.msaccounts.entities.Destination;
import com.jovan.msaccounts.repositories.DestinationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private AccountService accountService;

    public List<Destination> inquireDestinations(List<UUID> accountId){
        return destinationRepository.findByAccountIdIn(accountId);
    }

    public Destination createDestination(UUID userId, DestinationCreationRequest request){
        log.info("creating destination [{}] {}", request.getCategory(), request.getName());
        Account account = Account.builder().accountId(UUID.randomUUID()).userId(userId).accountType(AccountType.DESTINATION).balance(BigDecimal.ZERO).build();
        accountService.createAccount(account);
        return destinationRepository.save(Destination.builder()
                .category(request.getCategory())
                .accountId(account.getAccountId())
                .name(request.getName())
                .description(request.getDescription())
                .goal(request.getGoal())
                .build()
        );
    }
}
