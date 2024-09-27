package org.example.Characters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//враги
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Enemies {
    GOBLIN( "Гоблин", 70, 10, 100),
    SKELETON("Скелет", 70, 50, 100),
    ZOMBIE("Зомби", 70, 50, 100),
    COBALT("Кобальт", 70, 50, 100),
    VAMPIRE( "Вампир", 70, 50, 100),
    BASILISK( "Василиск", 70, 50, 100),
    GIANT("Гигант", 70, 50, 100),
    SPIDER("Паук", 70, 50, 100),
    CYCLOPS("Циклоп", 70, 50, 100),
    SPYSPIRIT("Дух-шпион", 70, 20, 100),
    DRAUGLIN("Драуглин", 70, 30, 100);


    private String name;
    //жизнь
    private double health;
    //урон
    private double damage;
    //лут-деньги
    private double many;

}
