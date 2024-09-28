package org.example.item.MagicalBest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Abilities{
    private String title;
    private double damage;
    private int mana;
    private boolean continueMotion = false;
}
