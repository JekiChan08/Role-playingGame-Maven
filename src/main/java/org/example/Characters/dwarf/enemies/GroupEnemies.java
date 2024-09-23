package org.example.Characters.dwarf.enemies;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public enum GroupEnemies {
    WOLF("Волки"),
    EARTHWORMS("Земляные черви"),
    GIANT_MOLES("Гиганские кроты"),
    VAMPIRE("Вампиры"),
    SPIDER("Пауки"),
    COBALT("Кобальды"),
    SKELETON("Скелеты"),
    GOBLIN( "Гоблины");


    private final String name;
    private final int damage;
    private final int health;

    GroupEnemies(String name) {
        this.damage = 20;
        this.health = 20;
        this.name = name;
    }
}
