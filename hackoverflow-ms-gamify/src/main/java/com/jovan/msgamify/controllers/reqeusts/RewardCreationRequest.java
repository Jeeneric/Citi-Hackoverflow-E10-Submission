package com.jovan.msgamify.controllers.reqeusts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardCreationRequest {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Min(1)
    private Integer cost;

    private URL imageUrl;
}
