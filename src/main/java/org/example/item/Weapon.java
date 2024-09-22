package org.example.item;


import lombok.Getter;


@Getter
public enum Weapon {
    SWORD(10, 150, TypeDamage.PHYSICAL),
    GREAMUAR(10, 150, TypeDamage.MAGICAL);

    private double damage;
    private double price;
    private TypeDamage typeDamage;
    private static final double UPGRADE_MULTIPLIER = 1.5; // Коэффициент улучшения урона

    Weapon(double damage, double price, TypeDamage typeDamage) {
        this.damage = damage;
        this.price = price;
        this.typeDamage = typeDamage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public TypeDamage getTypeDamage() {
        return typeDamage;
    }

    public void setTypeDamage(TypeDamage typeDamage) {
        this.typeDamage = typeDamage;
    }

    public Weapon upgradeWeapon() {
        this.damage *= UPGRADE_MULTIPLIER;
        System.out.println("Ваше оружие было улучшено. Новый урон: " + this.damage);
        return this;
    }

    @Override
    public String toString() {
        return name() + " (Урон: " + damage + ", Тип урона: " + typeDamage + ")";
    }
}
