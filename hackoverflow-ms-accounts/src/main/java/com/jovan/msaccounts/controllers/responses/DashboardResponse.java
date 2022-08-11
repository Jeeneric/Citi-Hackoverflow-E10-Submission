package com.jovan.msaccounts.controllers.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jovan.msaccounts.entities.Account;
import com.jovan.msaccounts.entities.Destination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardResponse {
    List<Entry> dashboard;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Entry{
        Account account;
        Destination destination;
    }
}
