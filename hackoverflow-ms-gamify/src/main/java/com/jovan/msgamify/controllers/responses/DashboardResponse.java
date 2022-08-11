package com.jovan.msgamify.controllers.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jovan.msgamify.entities.GameProfile;
import com.jovan.msgamify.entities.Quest;
import com.jovan.msgamify.entities.QuestCompletion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DashboardResponse {

    private GameProfile profile;
    private List<QuestEntry> quests;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class QuestEntry{
        private Quest quest;
        private QuestCompletion questCompletion;
    }
}
