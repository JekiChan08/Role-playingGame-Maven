package org.example.Characters.dwarf;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class DwarfGroup {
    private double health;
    private double damage;
    private double protection;
    private int level;
    private double maxHealth;


    public DwarfGroup() {
        this.level = 1;
        this.damage = new Random().nextInt(20, 30);
        this.health = new Random().nextInt(23, 33);
        this.protection = new Random().nextInt(1, 10);
        this.maxHealth = health;
    }

    public void levelUp() {
        level++;
        setHealth(getHealth() * level);
        setDamage(getDamage() * level);
        System.out.println("Теперь здоровье " + getHealth());
        System.out.println("Теперь урон " + getDamage());
    }
}