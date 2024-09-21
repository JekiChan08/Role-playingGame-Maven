package org.example.item;


public class Armor {
    private String name;
    private int defense;// Защита брони в процентах

    public Armor(String name, int defense) {
        this.name = name;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void upgradeDefense(int increase) {
        this.defense += increase;
    }

    @Override
    public String toString() {
        return name + " (Защита: " + defense + "%)";
    }
}

