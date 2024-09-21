package org.example.item;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum TypeDamage {
    //Вид урона
    MAGICAL("Магический"),
    PHYSICAL("Физический");

    private String title;
}
