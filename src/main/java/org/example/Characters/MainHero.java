package org.example.Characters;


import org.example.item.Armor;
import org.example.item.Items;
import org.example.item.Weapon;

import java.util.ArrayList;

public class MainHero {
    private String name;
    private Rassa rassa;// Раса героя
    private double health;// Здоровье героя
    private PersonClass personClass;// Класс героя
    private Weapon weapon;// Оружие героя
    private Armor armor;// Броня героя
    private double money;// Деньги
    private ArrayList<Items> items;// Предметы

    public MainHero() {
        this.health = 100;
        this.money = 0;
        this.armor = new Armor("Начальная броня", 20);  // Начальная броня с 20% защитой
        items = new ArrayList<>();
    }

    // Метод для вывода всех статов героя
    public void getStats() {
        System.out.println("Ваши статы:");
        System.out.println("Здоровье: " + health);
        System.out.println("Урон: " + weapon.getDamage());
        System.out.println("Класс: " + personClass.getTitle());
        System.out.println("Деньги: " + money);
        System.out.println("Броня: " + armor);
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public double getDamage() {
        return weapon.getDamage();
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rassa getRassa() {
        return rassa;
    }

    public void setRassa(Rassa rassa) {
        this.rassa = rassa;
    }

    public PersonClass getPersonClass() {
        return personClass;
    }

    public void setPersonClass(PersonClass personClass) {
        this.personClass = personClass;
    }

    public void upgradeArmor(int increase) {
        this.armor.upgradeDefense(increase);
    }
}
