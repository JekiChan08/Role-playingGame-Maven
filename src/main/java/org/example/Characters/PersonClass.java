package org.example.Characters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//класс персонажа
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PersonClass {
    WARRIOR("Воин"),
    MAG("Маг"),
    TANK("Танк");

    private String title;

}
