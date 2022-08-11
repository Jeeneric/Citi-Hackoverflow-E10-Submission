package com.jovan.msgamify.services;

import com.jovan.msgamify.controllers.reqeusts.QuestCompletionCreationRequest;
import com.jovan.msgamify.controllers.reqeusts.QuestCompletionUpdateRequest;
import com.jovan.msgamify.entities.Quest;
import com.jovan.msgamify.entities.QuestCompletion;
import com.jovan.msgamify.exceptions.EntityNotFoundException;
import com.jovan.msgamify.models.QuestCompletionId;
import com.jovan.msgamify.models.QuestCompletionRewards;
import com.jovan.msgamify.repositories.QuestCompletionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class QuestCompletionService {

    @Autowired
    private QuestCompletionRepository questCompletionRepository;

    @Autowired
    private QuestService questService;

    @Autowired
    private GameProfileService gameProfileService;

    public List<QuestCompletion> inquireQuestCompletion(UUID userId){
        log.info("inquiring quest completion for user {}", userId);
        List<QuestCompletion> questCompletions = new ArrayList<>();
        for(QuestCompletion questCompletion: questCompletionRepository.findAll()){
            questCompletions.add(questCompletion);
        }
        return questCompletions;
    }

    public QuestCompletion createQuestCompletion(UUID userId, QuestCompletionCreationRequest request){
        log.info("creating quest completion entry");
        Quest quest = questService.retrieveQuest(request.getQuestId());
        return questCompletionRepository.save(
                QuestCompletion.builder()
                        .userId(userId)
                        .questId(quest.getQuestId())
                        .isCompleted(false)
                        .progress(0)
                        .build()
        );
    }

    public QuestCompletion retrieveQuestCompletion(UUID userId, UUID questId){
        log.info("retrieving quest completion {} for user {}", questId, userId);
        return questCompletionRepository.findById(QuestCompletionId.builder().userId(userId).questId(questId).build())
                .orElseThrow(() -> {
                    log.error("no quest completion record {} exists for the current user", questId);
                    return new EntityNotFoundException("no quest completion record" + userId + "exists for the current user");
                });
    }

    public QuestCompletion updateQuestCompletion(UUID userId, QuestCompletionUpdateRequest request){
        log.info("updating quest completion", request.getQuestId());
        Quest quest = questService.retrieveQuest(request.getQuestId());
        QuestCompletion questCompletion = retrieveQuestCompletion(userId, request.getQuestId());
        questCompletion.setProgress(request.getProgress());
        questCompletion.setIsCompleted(request.getProgress() == quest.getGoal());

        if(questCompletion.getIsCompleted()){
            log.info("user {} has completed quest {} - crediting rewards");
            gameProfileService.updateGameProfile(
                    userId,
                    QuestCompletionRewards.builder()
                            .gainedCredits(quest.getCreditReward())
                            .gainedExperience(quest.getExperienceReward())
                            .build());
        }
        return questCompletionRepository.save(questCompletion);
    }

}
