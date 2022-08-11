package com.jovan.msgamify.services;

import com.jovan.msgamify.entities.GameProfile;
import com.jovan.msgamify.exceptions.BadRequestException;
import com.jovan.msgamify.exceptions.EntityNotFoundException;
import com.jovan.msgamify.models.QuestCompletionRewards;
import com.jovan.msgamify.repositories.GameProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class GameProfileService {

    @Autowired
    private GameProfileRepository gameProfileRepository;

    public GameProfile createGameProfile(UUID userId){
        log.info("creating game profile for user {}", userId);
        if(gameProfileRepository.findById(userId).isPresent()){
            throw new BadRequestException("game profile already exists");
        }
        return gameProfileRepository.save(GameProfile.builder().userId(userId).credits(0).experience(0).build());
    }

    public GameProfile retrieveGameProfile(UUID userId){
        log.info("retrieving game profile for user {}", userId);
        return gameProfileRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("failed to retrieve game profile for current user"));
    }

    GameProfile updateGameProfile(UUID userId, QuestCompletionRewards rewards){
        log.info("updating game profile for user {}", userId);
        GameProfile profile = retrieveGameProfile(userId);
        profile.setCredits(profile.getCredits() + rewards.getGainedCredits());
        profile.setExperience(profile.getExperience() + rewards.getGainedExperience());
        return gameProfileRepository.save(profile);
    }

    GameProfile updateGameProfile(GameProfile gameProfile){
        log.info("updating game profile for user {}", gameProfile.getUserId());
        return gameProfileRepository.save(gameProfile);
    }
}
