package org.example.Characters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.item.*;

import java.util.ArrayList;

@Setter
@AllArgsConstructor
@Getter
public class MainHero {
    private String name;
    private Rassa rassa;// Раса героя
    private double health;// Здоровье героя
    private PersonClass personClass;// Класс героя
    private Weapon weapon;// Оружие героя
    private Armor armor;// Броня героя
    private double money;// Деньги
    private ArrayList<Items> items;// Предметы
    private boolean endGame;

    public MainHero() {
        this.health = 100;
        this.money = 0;
        this.armor = new Armor("Начальная броня", 20);  // Начальная броня с 20% защитой
        items = new ArrayList<>();
        this.endGame = false;
    }

    // Метод для вывода всех статов героя
    public void getStats() {
        System.out.println("Ваши статы:");
        System.out.println("Здоровье: \u001B[34m" + health + "\u001B[0m");
        System.out.println("Урон: \u001B[34m" + weapon.getDamage() + "\u001B[0m");
        System.out.println("Класс: \u001B[34m" + personClass.getTitle() + "\u001B[0m");
        System.out.println("Деньги: \u001B[34m" + money + "\u001B[0m");
        System.out.println("Броня: \u001B[34m" + armor + "\u001B[0m");
    }

    public double getDamage() {
        return weapon.getDamage();
    }

    public void upgradeArmor(int increase) {
        this.armor.upgradeDefense(increase);
    }
}
