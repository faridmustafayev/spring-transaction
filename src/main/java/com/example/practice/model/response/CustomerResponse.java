package com.example.practice.model.response;

import com.example.practice.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private Status status;
    private BigDecimal discount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
