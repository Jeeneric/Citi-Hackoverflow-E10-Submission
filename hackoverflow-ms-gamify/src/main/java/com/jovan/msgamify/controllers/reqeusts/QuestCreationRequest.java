package com.jovan.msgamify.controllers.reqeusts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestCreationRequest {

    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 100)
    private String description;

    @NotNull
    private Integer creditReward;

    @NotNull
    private Integer experienceReward;

    @NotNull
    private Integer goal;

}
