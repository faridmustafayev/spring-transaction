package com.example.practice.model.criteria;

import com.example.practice.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceCriteria {
    private Status status;
    private BigDecimal amountFrom;
    private BigDecimal amountTo;
}
