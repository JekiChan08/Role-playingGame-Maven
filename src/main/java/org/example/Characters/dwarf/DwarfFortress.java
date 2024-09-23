package org.example.Characters.dwarf;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
public class DwarfFortress {
    private String fortressName;
    private int fortressHealth;
    private SidesOfTheFortress left;
    private SidesOfTheFortress right;
    private SidesOfTheFortress upper;
    private SidesOfTheFortress lower;
    private ArrayList<FortressDwarfGroup> fortressDwarfGroup;
    private int days;

    public DwarfFortress() {
        fortressDwarfGroup = new ArrayList<>();
        this.days = 1;
        this.fortressHealth = 1000;
        this.left = new SidesOfTheFortress();
        this.right = new SidesOfTheFortress();
        this.upper = new SidesOfTheFortress();
        this.lower = new SidesOfTheFortress();
    }


}
