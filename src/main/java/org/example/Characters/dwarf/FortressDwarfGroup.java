package org.example.Characters.dwarf;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
public class FortressDwarfGroup {
    private double dwarfsHealth;
    private double dwarfsDamage;
    private double protection;
    private int level;
    private double maxHealth;

    public FortressDwarfGroup() {
        this.level = 1;
        this.dwarfsDamage = new Random().nextInt(25, 33) * level;
        this.dwarfsHealth = new Random().nextInt(37, 45) * level;
        this.protection = new Random().nextInt(1, 10);
        this.maxHealth = dwarfsHealth;
    }

    public void levelUp() {
        level++;
        setDwarfsHealth(getDwarfsHealth() * level);
        setDwarfsDamage(getDwarfsDamage() * level);
        setMaxHealth(getMaxHealth() * level);
        System.out.println("Теперь здоровье " + getDwarfsHealth());
        System.out.println("Теперь урон " + getDwarfsDamage());
    }
}
