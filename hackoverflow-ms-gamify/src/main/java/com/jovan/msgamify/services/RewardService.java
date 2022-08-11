package com.jovan.msgamify.services;

import com.jovan.msgamify.controllers.reqeusts.RewardCreationRequest;
import com.jovan.msgamify.entities.Reward;
import com.jovan.msgamify.exceptions.EntityNotFoundException;
import com.jovan.msgamify.repositories.QuestCompletionRepository;
import com.jovan.msgamify.repositories.RewardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    public List<Reward> inquireRewards(){
        log.info("inquiring all rewards");
        List<Reward> rewards = new ArrayList<>();
        rewardRepository.findAll().forEach(reward -> rewards.add(reward));
        return rewards;
    }

    public Reward createReward(RewardCreationRequest request){
        log.info("creating reward {}", request.getName());
        return rewardRepository.save(Reward.builder()
                .rewardId(UUID.randomUUID())
                .name(request.getName())
                .description(request.getDescription()).cost(request.getCost())
                .imageUrl(request.getImageUrl())
                .build());
    }

    public Reward retrieveReward(UUID rewardId){
        log.info("retrieving reward {}", rewardId);
        return rewardRepository.findById(rewardId).orElseThrow(() -> {
            log.error("failed to find reward {}", rewardId);
            return new EntityNotFoundException("failed to find reward " + rewardId);
        });
    }

    public List<Reward> retrieveRewards(List<UUID> rewardIds){
        log.info("retrieving {} rewards", rewardIds.size());
        return rewardRepository.findAllByRewardIdIn(rewardIds);
    }
}
