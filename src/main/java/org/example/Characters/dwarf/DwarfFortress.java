package org.example.Characters.dwarf;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Scanner;

@Setter
@Getter
@AllArgsConstructor
public class DwarfFortress {
    private String fortressName;
    private int fortressHealth;
    private GatesOfTheFortress left;
    private GatesOfTheFortress right;
    private GatesOfTheFortress upper;
    private GatesOfTheFortress lower;
    private ArrayList<FortressDwarfGroup> fortressDwarfGroups;
    private int provision;
    private int fortressBalance;
    private int experience;


    public DwarfFortress() {
        fortressDwarfGroups = new ArrayList<>();
        this.fortressHealth = 1000;
        this.left = new GatesOfTheFortress();
        this.right = new GatesOfTheFortress();
        this.upper = new GatesOfTheFortress();
        this.lower = new GatesOfTheFortress();
        this.fortressBalance = 200;
        this.fortressDwarfGroups.add(new FortressDwarfGroup());
        this.fortressDwarfGroups.add(new FortressDwarfGroup());
        this.experience = 1;
        this.provision = 4;
    }

    public void stateFortress() {
        System.out.println("Здоровье крепости: " + getFortressHealth());
        if(getFortressDwarfGroups().isEmpty()) {
            System.out.println("у вас есть: " + getFortressDwarfGroups().size() + " групп гномов");
        } else {
            System.out.println("У вас нету гномов");
        }
        System.out.println("Провизия(1-1день): " + getProvision());
        System.out.println("Баланс крепости: " + getFortressBalance() + " монет");

    }
}
