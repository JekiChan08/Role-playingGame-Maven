package org.example.Characters.dwarf;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gate {
    private String name;
    private double health;
    private int protection;

    public Gate(String name, int health) {
        this.name = name;
        this.health = health;
        this.protection = 0;
    }
}