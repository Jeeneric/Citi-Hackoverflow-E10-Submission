package com.jovan.msaccounts.controllers.requests;

import com.jovan.msaccounts.constants.DestinationCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinationCreationRequest {
    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @Size(max = 100)
    private String description;

    @NotNull
    @Min(1)
    private BigDecimal goal;

    @NotNull
    private DestinationCategory category;
}
