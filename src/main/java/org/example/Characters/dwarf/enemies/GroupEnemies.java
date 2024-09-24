package org.example.Characters.dwarf.enemies;


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
    private final double damage;
    private final double health;
    private final int coin;
    private final int level;


    GroupEnemies(String name) {
        this.damage = ((20 * getLevel()) * 0.12);
        this.health = (20 * getLevel()) * 0.12;
        this.name = name;
        this.level = 1;
        this.coin = 100;
    }
}
