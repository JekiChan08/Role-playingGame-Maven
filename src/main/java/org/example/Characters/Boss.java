package org.example.Characters;

//Боссы
public enum Boss {
    DRACULA(500, "Дракула", 9000, 200),
    DRAGON(500, "Дракон", 10000, 200),
    GIANT_WORM(500, "Гиганский Червь", 10000, 200),
    GRIFFIN(500, "Грифон", 10000, 200);

    private String name;
    //жизнь
    private double health;
    //защита
    private double protection;
    //урон
    private double damage;

    Boss(double protection, String name, double health, double damage) {
        this.protection = protection;
        this.name = name;
        this.health = health;
        this.damage = damage;
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
