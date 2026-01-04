package com.springbootacademy.batch7.pos.entity.enums;

// If we create a class from enum without s, it is incorrect. It means that the class must be enums.
public enum MeasuringUnitType {
    // If there are two words, _ must be used between two words to join them.
    KILO_GRAM,LITER,GRAM,MILLI_LITER,NUMBER // It changes upon the business requirements. NUMBER means the measuring type of the countable things.
}
