package org.example.Characters.dwarf;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//Стороны крепости
public class GatesOfTheFortress {
    private int steps;
    private int maxSteps;
    private int minSteps;
    private double protection;
    private boolean placeOfProtection; //место защиты на которую встаёт группа дварфов

    public GatesOfTheFortress() {
        maxSteps = 3;
        minSteps = 0;
        protection = 200;
    }
}
