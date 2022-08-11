package com.jovan.msgamify.controllers;

import com.jovan.msgamify.controllers.reqeusts.QuestCompletionCreationRequest;
import com.jovan.msgamify.controllers.reqeusts.QuestCompletionUpdateRequest;
import com.jovan.msgamify.entities.QuestCompletion;
import com.jovan.msgamify.services.QuestCompletionService;
import com.jovan.msgamify.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/quest-completion")
@Slf4j
@CrossOrigin
public class QuestCompletionController {

    @Autowired
    private QuestCompletionService questCompletionService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping
    public QuestCompletion acceptQuest(@RequestBody QuestCompletionCreationRequest request){
        log.info("{}", request.toString());
        return questCompletionService.createQuestCompletion(JWTUtil.getUserId(httpServletRequest), request);
    }

    @PutMapping
    public QuestCompletion updateCompletion(@RequestBody QuestCompletionUpdateRequest request){
        log.info("{}", request.toString());
        return questCompletionService.updateQuestCompletion(JWTUtil.getUserId(httpServletRequest), request);
    }


}
