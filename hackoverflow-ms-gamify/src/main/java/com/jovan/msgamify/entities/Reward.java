package com.jovan.msgamify.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URL;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reward {

    @Id
    private UUID rewardId;
    private String name;
    private String description;
    private Integer cost;
    private URL imageUrl;

}
