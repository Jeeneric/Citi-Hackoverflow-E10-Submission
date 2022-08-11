package com.jovan.msaccounts.entities;

import com.jovan.msaccounts.constants.DestinationCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Destination {

    @Id
    private UUID accountId;
    private String name;
    private String description;
    private BigDecimal goal;
    @Enumerated(EnumType.STRING)
    private DestinationCategory category;

}
