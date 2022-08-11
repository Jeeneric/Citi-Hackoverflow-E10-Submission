package com.jovan.msgamify.services;

import com.jovan.msgamify.controllers.reqeusts.QuestCreationRequest;
import com.jovan.msgamify.entities.Quest;
import com.jovan.msgamify.exceptions.EntityNotFoundException;
import com.jovan.msgamify.repositories.GameProfileRepository;
import com.jovan.msgamify.repositories.QuestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    public List<Quest> inquireAllQuests(){
        log.info("inquiring all quests");
        List<Quest> quests = new ArrayList<>();
        for(Quest quest: questRepository.findAll()){
            quests.add(quest);
        }
        return quests;
    }

    public Quest createQuest(QuestCreationRequest request){
        log.info("creating quest {}", request.getName());
        return questRepository.save(
                Quest.builder()
                        .questId(UUID.randomUUID())
                        .name(request.getName())
                        .description(request.getDescription())
                        .creditReward(request.getCreditReward())
                        .experienceReward(request.getExperienceReward())
                        .goal(request.getGoal())
                        .build()
        );
    }

    public Quest retrieveQuest(UUID questId){
        log.info("retrieving quest {}", questId);
        return questRepository.findById(questId).orElseThrow(() -> new EntityNotFoundException("failed to retrieve quest" + questId));
    }
}
