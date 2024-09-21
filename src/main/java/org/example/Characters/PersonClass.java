package org.example.Characters;

//класс персонажа
public enum PersonClass {
    WARRIOR("Воин"),
    MAG("Маг"),
    TANK("Танк");

    private String title;

    PersonClass(String title) {
        this.title = title;
    }

    PersonClass() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
