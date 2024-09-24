package org.example.Characters.dwarf.enemies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy {
    private String name;
    private double health;
    private double damage;

    public Enemy(String name, double health, double damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }
}