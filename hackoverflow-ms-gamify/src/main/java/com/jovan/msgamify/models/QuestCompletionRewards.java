package com.jovan.msgamify.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestCompletionRewards {
    private Integer gainedCredits;
    private Integer gainedExperience;
}
