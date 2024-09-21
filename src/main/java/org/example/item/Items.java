package org.example.item;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Items {
    SHIELD("(Щит) защитный предмет который позволяет онулировать некоторый урон", 5,100),
    POLITION_LIFE("(Зелье жизни) дает некоторое количество здоровья", 50, 50);

    private String description;
    private double value;
    private double price;

}
