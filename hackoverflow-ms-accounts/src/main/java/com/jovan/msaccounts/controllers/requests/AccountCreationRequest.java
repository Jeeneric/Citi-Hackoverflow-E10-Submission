package com.jovan.msaccounts.controllers.requests;

import com.jovan.msaccounts.constants.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreationRequest {
    @NotNull
    private AccountType accountType;
}
