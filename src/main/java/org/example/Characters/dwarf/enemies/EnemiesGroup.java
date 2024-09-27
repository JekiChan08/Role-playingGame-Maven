package org.example.Characters.dwarf.enemies;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnemiesGroup {
    private GroupEnemies groupEnemies;
    private String name;
    private double health;
    private double damage;
    private int coin;
    private int point;
    private int level;


    public EnemiesGroup(GroupEnemies groupEnemies) {
        this.groupEnemies = groupEnemies;
        this.name = groupEnemies.getName();
        this.health = groupEnemies.getHealth();
        this.damage = groupEnemies.getDamage();
        this.coin = 100;
        this.point = 1;
        this.level = 1;
    }

    public void levelUp() {
        level++;
        setHealth(getHealth() * level);
        setDamage(getDamage() * level);
    }
}
