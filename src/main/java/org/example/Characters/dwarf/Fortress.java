package org.example.Characters.dwarf;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Fortress {
    private String name;
    private int health;
    private ArrayList<Gate> gates;
    private ArrayList<DwarfGroup> dwarfGroups;
    private int provision;
    private int balance;
    private int experience;
    private int days;

    public Fortress() {
        this.name = name;
        this.health = 1000;
        this.gates = new ArrayList<>();
        this.dwarfGroups = new ArrayList<>();
        this.provision = 4;
        this.balance = 2000;
        this.experience = 1;

        gates.add(new Gate("Левый", 500));
        gates.add(new Gate("Правый", 500));
        gates.add(new Gate("Верхний", 500));
        gates.add(new Gate("Нижний", 500));

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