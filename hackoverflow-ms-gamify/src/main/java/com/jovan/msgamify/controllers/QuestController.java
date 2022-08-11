package com.jovan.msgamify.controllers;

import com.jovan.msgamify.controllers.reqeusts.QuestCreationRequest;
import com.jovan.msgamify.entities.Quest;
import com.jovan.msgamify.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quest")
@CrossOrigin
public class QuestController {

    @Autowired
    private QuestService questService;

    @PostMapping
    public Quest createQuest(QuestCreationRequest request){
        return questService.createQuest(request);
    }


}
