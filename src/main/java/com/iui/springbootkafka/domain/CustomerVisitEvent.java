package com.iui.springbootkafka.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CustomerVisitEvent {

    private String customerId;

    private LocalDateTime dateTime;
}
