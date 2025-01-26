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
@Builder
@NoArgsConstructor
public class InvoiceResponse {
    private Long id;
    private BigDecimal amount;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
