package com.example.task_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class CurrencyDTO {
    private String from;
    private String to;
    private Double money;
}
