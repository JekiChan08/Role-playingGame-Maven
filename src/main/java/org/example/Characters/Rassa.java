package org.example.Characters;
//Расса-гном, эльф итд
public enum Rassa {
    //гном
    DWARF("Гном"),
    //эльф
    ELF("Эльф"),
    //человек
    PERSON("Человек"),
    //орк
    ORC("Орк");
    private String title;

    Rassa(String title) {
        this.title = title;
    }

    Rassa() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
