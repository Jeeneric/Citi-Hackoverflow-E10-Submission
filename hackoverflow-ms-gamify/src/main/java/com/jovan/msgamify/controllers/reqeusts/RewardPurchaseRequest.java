package com.jovan.msgamify.controllers.reqeusts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardPurchaseRequest {

    @NotNull
    List<Purchase> purchases;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Purchase{
        @NotNull
        private UUID rewardId;

        @NotNull
        private Integer quantity;
    }


}
