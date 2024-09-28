package org.example.Characters.MagicalBest;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@AllArgsConstructor
public class MagicalEnemies {
    private double health;
    private String name;
    private int mana;
    private int maxMana;

    public MagicalEnemies() {
        this.mana = 10;
        this.maxMana = 10;
        this.health = 100;
    }
}
