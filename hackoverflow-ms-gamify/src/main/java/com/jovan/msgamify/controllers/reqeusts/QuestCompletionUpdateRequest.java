package com.jovan.msgamify.controllers.reqeusts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestCompletionUpdateRequest {

    @NotNull
    private UUID questId;

    @NotNull
    private Integer progress;

}
