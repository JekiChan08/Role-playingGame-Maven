package org.example.Characters;
//Расса-гном, эльф итд


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Rassa {
    //гном
    DWARF("Гном"),
    //эльф
    ELF("Эльф"),
    //человек
    PERSON("Человек"),
    //орк
    ORC("Орк"),
    MAGICAL_BEST("Маг-Зверь");

    private String title;

}
