package com.jovan.msgamify.services;

import com.jovan.msgamify.controllers.reqeusts.RewardPurchaseRequest;
import com.jovan.msgamify.controllers.reqeusts.RewardSpendRequest;
import com.jovan.msgamify.entities.GameProfile;
import com.jovan.msgamify.entities.Reward;
import com.jovan.msgamify.entities.RewardWallet;
import com.jovan.msgamify.exceptions.BadRequestException;
import com.jovan.msgamify.exceptions.EntityNotFoundException;
import com.jovan.msgamify.models.RewardWalletId;
import com.jovan.msgamify.repositories.RewardRepository;
import com.jovan.msgamify.repositories.RewardWalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RewardWalletService {

    @Autowired
    private RewardWalletRepository rewardWalletRepository;

    @Autowired
    private RewardService rewardService;

    @Autowired
    private GameProfileService gameProfileService;

    public List<RewardWallet> inquireWallet(UUID userId){
        return rewardWalletRepository.findAllByUserId(userId);
    }

    public void purchaseReward(UUID userId, RewardPurchaseRequest request){
        log.info("processing reward purchase {} for {}", request, userId);
        Map<UUID, Integer> requestedRewards = rewardService
                        .retrieveRewards(request.getPurchases().stream().map(RewardPurchaseRequest.Purchase::getRewardId).collect(Collectors.toList()))
                        .stream().collect(Collectors.toMap(Reward::getRewardId, Reward::getCost));
        Integer totalCost = calculateTotalCost(request, requestedRewards);

        GameProfile gameProfile = gameProfileService.retrieveGameProfile(userId);
        validatePurchase(gameProfile, totalCost);

        gameProfile.setCredits(gameProfile.getCredits() - totalCost);
        gameProfileService.updateGameProfile(gameProfile);

        addRewardToWallet(request, userId);
    }

    public RewardWallet spendReward(UUID userId, RewardSpendRequest request){
        log.info("processing reward spend for {}", userId);
        RewardWallet rewardWallet = rewardWalletRepository.findById(RewardWalletId.builder().userId(userId).rewardId(request.getRewardId()).build())
                .orElseThrow(() -> {
                    log.error("failed to spend reward - no matching wallet entry found");
                    return new EntityNotFoundException("failed to find reward wallet entry");
                });
        rewardWallet.setQuantity(rewardWallet.getQuantity() - request.getQuantity());
        return rewardWalletRepository.save(rewardWallet);
    }

    private void addRewardToWallet(RewardPurchaseRequest request, UUID userId) {
        for (RewardPurchaseRequest.Purchase purchase: request.getPurchases()){
            log.info("adding X{} of reward {} into wallet", purchase.getQuantity(), purchase.getRewardId());
            Optional<RewardWallet> optional = rewardWalletRepository.findById(RewardWalletId.builder().userId(userId).rewardId(purchase.getRewardId()).build());
            if(optional.isPresent()){
                RewardWallet rewardWallet = optional.get();
                rewardWallet.setQuantity(rewardWallet.getQuantity() + purchase.getQuantity());
                rewardWalletRepository.save(rewardWallet);
            } else{
                rewardWalletRepository.save(
                        RewardWallet.builder()
                                .rewardId(purchase.getRewardId())
                                .userId(userId).quantity(purchase
                                .getQuantity())
                                .build()
                );
            }
        }
    }

    private Integer calculateTotalCost(RewardPurchaseRequest request, Map<UUID, Integer> requestedRewards) {
        Integer total = 0;
        for(RewardPurchaseRequest.Purchase purchase: request.getPurchases()){
            total += requestedRewards.get(purchase.getRewardId()) * purchase.getQuantity();
        }
        return total;
    }

    private void validatePurchase(GameProfile gameProfile, Integer totalCost) {
        if(gameProfile.getCredits() < totalCost){
            log.error("user has insufficient credits to process purchase");
            throw new BadRequestException("insufficient credits to process purchase");
        }
    }


}
