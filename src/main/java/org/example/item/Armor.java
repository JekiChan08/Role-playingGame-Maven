package org.example.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Armor {
    private String name;
    private int defense;// Защита брони в процентах

    public void upgradeDefense(int increase) {
        this.defense += increase;
    }

    @Override
    public String toString() {
        return name + " (Защита: " + defense + "%)";
    }
}

