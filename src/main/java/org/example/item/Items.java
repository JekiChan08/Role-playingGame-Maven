package org.example.item;

public enum Items {
    SHIELD("(Щит) защитный предмет который позволяет онулировать некоторый урон", 5,100),
    POLITION_LIFE("(Зелье жизни) дает некоторое количество здоровья", 50, 50);

    private String description;
    private double value;
    private double price;

    Items(String description, double value, double price) {
        this.description = description;
        this.value = value;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
