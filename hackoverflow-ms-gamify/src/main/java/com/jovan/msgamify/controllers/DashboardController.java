package com.jovan.msgamify.controllers;

import com.jovan.msgamify.controllers.responses.DashboardResponse;
import com.jovan.msgamify.entities.GameProfile;
import com.jovan.msgamify.entities.Quest;
import com.jovan.msgamify.entities.QuestCompletion;
import com.jovan.msgamify.exceptions.EntityNotFoundException;
import com.jovan.msgamify.services.GameProfileService;
import com.jovan.msgamify.services.QuestCompletionService;
import com.jovan.msgamify.services.QuestService;
import com.jovan.msgamify.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game-dashboard")
@CrossOrigin
@Slf4j
public class DashboardController {

    @Autowired
    private QuestService questService;

    @Autowired
    private QuestCompletionService questCompletionService;

    @Autowired
    private GameProfileService gameProfileService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public DashboardResponse getDashboard(){
        GameProfile gameProfile;
        try{
            gameProfile = gameProfileService.retrieveGameProfile(JWTUtil.getUserId(httpServletRequest));
        } catch (EntityNotFoundException e){
            gameProfile = gameProfileService.createGameProfile(JWTUtil.getUserId(httpServletRequest));
        }
        List<Quest> quests = questService.inquireAllQuests();
        Map<UUID, QuestCompletion> questCompletionMap =
                questCompletionService.inquireQuestCompletion(JWTUtil.getUserId(httpServletRequest)).stream().collect(Collectors.toMap(QuestCompletion::getQuestId, questCompletion -> questCompletion));

        List<DashboardResponse.QuestEntry> questEntries = new ArrayList<>();
        for(Quest quest: quests){
            if(questCompletionMap.containsKey(quest.getQuestId())){
                questEntries.add(DashboardResponse.QuestEntry.builder()
                        .quest(quest)
                        .questCompletion(questCompletionMap.get(quest.getQuestId()))
                        .build());
            }
            else questEntries.add(DashboardResponse.QuestEntry.builder().quest(quest).build());
        }

        return DashboardResponse.builder().profile(gameProfile).quests(questEntries).build();
    }

}
