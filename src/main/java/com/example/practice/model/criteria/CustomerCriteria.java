package com.example.practice.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCriteria {
    private String name;
    private Integer ageFrom;
    private Integer ageTo;
}
