package org.example.Characters;

//враги
public enum Enemies {
    GOBLIN(1, "Гоблин", 70, 10, 100),
    SKELETON(1, "Скелет", 70, 50, 100),
    ZOMBIE(1, "Зомби", 70, 50, 100),
    COBALT(1, "Кобальт", 70, 50, 100),
    VAMPIRE(1, "Вампир", 70, 50, 100),
    BASILISK(1, "Василиск", 70, 50, 100),
    GIANT(1, "Гигант", 70, 50, 100),
    SPIDER(1, "Паук", 70, 50, 100),
    CYCLOPS(1, "Циклоп", 70, 50, 100);


    private String name;
    //жизнь
    private double health;
    //защита
    private double protection;
    //урон
    private double damage;
    //лут-деньги
    private double many;

    Enemies(double protection, String name, double health, double damage, double many) {
        this.protection = protection;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.many = many;
    }


    public double getMany() {
        return many;
    }

    public void setMany(double many) {
        this.many = many;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getProtection() {
        return protection;
    }

    public void setProtection(double protection) {
        this.protection = protection;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
