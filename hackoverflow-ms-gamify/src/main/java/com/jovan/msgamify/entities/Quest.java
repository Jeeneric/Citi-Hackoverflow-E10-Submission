package com.jovan.msgamify.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quest {

    @Id
    private UUID questId;
    private String name;
    private String description;
    private Integer creditReward;
    private Integer experienceReward;
    private Integer goal;

}
