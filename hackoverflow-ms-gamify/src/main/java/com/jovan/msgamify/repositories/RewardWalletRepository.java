package com.jovan.msgamify.repositories;

import com.jovan.msgamify.entities.Reward;
import com.jovan.msgamify.entities.RewardWallet;
import com.jovan.msgamify.models.RewardWalletId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RewardWalletRepository extends CrudRepository<RewardWallet, RewardWalletId> {

    List<RewardWallet> findAllByUserId(UUID userId);

}
