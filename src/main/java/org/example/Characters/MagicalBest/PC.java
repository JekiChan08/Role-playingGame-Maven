package org.example.Characters.MagicalBest;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PC {
    private String name;
    private double health;
    private double maxHealth;
    private int mana;
    private int maxMana;


    public PC() {
        this.mana = 10;
        this.maxMana = 10;
        this.health = 100;
        this.maxHealth = health;
    }
    public void increasedHealth(double maxHealth) {
        this.maxHealth += maxHealth;
    }
}
