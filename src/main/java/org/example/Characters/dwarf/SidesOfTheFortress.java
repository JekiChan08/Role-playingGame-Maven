package org.example.Characters.dwarf;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//Стороны крепости
public class SidesOfTheFortress {
    private int steps;
    private int maxSteps;
    private  int minSteps;

    public SidesOfTheFortress() {
        maxSteps = 3;
        minSteps = 0;
    }
}
