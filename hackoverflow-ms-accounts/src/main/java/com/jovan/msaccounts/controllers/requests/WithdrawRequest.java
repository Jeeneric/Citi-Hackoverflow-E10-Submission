package com.jovan.msaccounts.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawRequest {
    @NotNull
    private UUID accountId;
    @NotNull
    private BigDecimal amount;
}
