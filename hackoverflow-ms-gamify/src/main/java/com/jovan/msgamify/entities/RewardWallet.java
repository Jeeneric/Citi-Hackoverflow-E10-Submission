package com.jovan.msgamify.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jovan.msgamify.models.RewardWalletId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(RewardWalletId.class)
public class RewardWallet {

    @Id
    @JsonIgnore
    private UUID userId;
    @Id
    private UUID rewardId;
    private Integer quantity;

}
