package ru.calculator.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModelExpression {
    private int id;
    private String text;
    private double result;


}
