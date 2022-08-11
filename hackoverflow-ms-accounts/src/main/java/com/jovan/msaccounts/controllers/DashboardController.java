package com.jovan.msaccounts.controllers;

import com.jovan.msaccounts.controllers.responses.DashboardResponse;
import com.jovan.msaccounts.entities.Account;
import com.jovan.msaccounts.entities.Destination;
import com.jovan.msaccounts.services.AccountService;
import com.jovan.msaccounts.services.DestinationService;
import com.jovan.msaccounts.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
public class DashboardController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public DashboardResponse retrieveDashboard(){
        List<Account> accounts = accountService.inquireAccounts(JWTUtil.getUserId(httpServletRequest));
        return generateDashboardResponse(accounts);
    }

    private DashboardResponse generateDashboardResponse(List<Account> accounts) {
        Map<UUID, Destination> destinations = destinationService
                        .inquireDestinations(accounts.stream().map(Account::getAccountId).collect(Collectors.toList()))
                        .stream().collect(Collectors.toMap(Destination::getAccountId, destination -> destination));

        List<DashboardResponse.Entry> entries = new ArrayList<>();
        for(Account account: accounts){
            entries.add(DashboardResponse.Entry.builder().account(account).destination(destinations.getOrDefault(account.getAccountId(), null)).build());
        }
        return DashboardResponse.builder().dashboard(entries).build();
    }
}
