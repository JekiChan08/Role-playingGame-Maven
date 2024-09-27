package org.example.Characters.dwarf;

import lombok.Getter;
import lombok.Setter;
import org.example.Characters.dwarf.enemies.GroupEnemies;

import java.util.ArrayList;

@Getter
@Setter
public class Fortress {
    private String name;
    private double health;
    private ArrayList<Gate> gates;
    private ArrayList<DwarfGroup> dwarfGroups;
    private int provision;
    private int balance;
    private int experience;
    private int days;

    public Fortress() {
        this.health = 500;
        this.gates = new ArrayList<>();
        this.dwarfGroups = new ArrayList<>();
        this.provision = 2;
        this.balance = 100;
        this.experience = 1;
        this.days = 1;

        Gate left = new Gate("На Левых воротах", 500);
        gates.add(left);
        Gate right = new Gate("На правых воротах", 500);
        gates.add(right);
        Gate upper = new Gate("На верхних воротах", 500);
        gates.add(upper);
        Gate lower = new Gate("На нижних воротах", 500);
        gates.add(lower);

        dwarfGroups.add(new DwarfGroup());
        dwarfGroups.add(new DwarfGroup());
    }

    public void stateFortress() {
        System.out.println("Здоровье крепости: " + health);
        if (!dwarfGroups.isEmpty()) {
            System.out.println("У вас есть: " + dwarfGroups.size() + " групп гномов");
        } else {
            System.out.println("У вас нет гномов");
        }
        System.out.println("Провизия (1-1 день): " + provision);
        System.out.println("Баланс крепости: " + balance + " монет");
    }
}