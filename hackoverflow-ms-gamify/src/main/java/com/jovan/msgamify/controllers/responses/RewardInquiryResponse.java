package com.jovan.msgamify.controllers.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jovan.msgamify.entities.Reward;
import com.jovan.msgamify.entities.RewardWallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RewardInquiryResponse {

    List<Reward> rewards;

}
