package org.example.item;

public enum TypeDamage {
    //Вид урона
    MAGICAL("Магический"),
    PHYSICAL("Физический");

    private String title;

    TypeDamage(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
