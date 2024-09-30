package org.example.Characters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


//Боссы
@NoArgsConstructor
@AllArgsConstructor
@Getter

public enum Boss {
    DRACULA("Дракула", 500, 90),
    DRAGON("Дракон", 500, 90),
    GIANT_WORM("Гиганский Червь", 500, 90),
    GRIFFIN("Грифон", 500, 90),
    ELF("Эльф", 500, 90),
    NAZGUL("Назгул", 500, 90);

    private String name;
    //жизнь
    private double health;
    //урон
    private double damage;

}
