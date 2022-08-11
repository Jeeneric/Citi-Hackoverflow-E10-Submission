package com.jovan.msgamify.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jovan.msgamify.models.QuestCompletionId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(QuestCompletionId.class)
public class QuestCompletion {

    @Id
    @JsonIgnore
    private UUID userId;
    @Id
    private UUID questId;
    private Boolean isCompleted;
    private Integer progress;

}
