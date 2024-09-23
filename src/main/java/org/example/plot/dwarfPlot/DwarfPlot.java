package org.example.plot.dwarfPlot;

import lombok.Getter;
import lombok.Setter;
import org.example.Characters.dwarf.DwarfFortress;

import java.util.InputMismatchException;
import java.util.Scanner;

@Getter
@Setter
public class DwarfPlot {
    private int days = 1;

    public void start(DwarfFortress dwarfFortress) {
        System.out.println("Добро пожаловать! Король крепости - " + dwarfFortress.getFortressName());
        System.out.println("""
                В вашем крепости начинает не хвататет еды, из-за этого большинсто гномов ушли на охоту,
                но остальные остались что-бы защищать крепость от монстров, как король своей крепости вы должны
                продержаться месяц отбиваясь от монстов.
                Удачи!""");

        boolean endWhile = true;
        while (endWhile) {
            System.out.println("День " + days);
            System.out.println("Баланс: " + dwarfFortress.getFortressBalance());
            System.out.println("""
                    Что вы хотите сделать перед отакой врагов?
                    1) проверить состояние крепости
                    2) проверить состояния гномов
                    3) улучшить группу гномов(Стоит 1-опыта)
                    4) купить группу гномов у соседа(50 монет)
                    5) купить провизии(на день-20 монет)
                    6) Улучшить группе гномов брони(за 20 монет, рандомно 4-7%)
                    7) лучшить ворота(100 монет, +20% защиты)
                    8) начать защиту крепости
                    9) выйти""");
            try {
                int choice = new Scanner(System.in).nextInt();

                switch (choice) {
                    case 1 -> dwarfFortress.stateFortress();
                    case 2 -> stateDwarfs(dwarfFortress);
                    case 3 -> updateLevelDwarf(dwarfFortress);
                    default -> System.out.println("Такого выбора нету");
                }
                if (dwarfFortress.getFortressHealth() <= 0) {
                    System.out.println("Ваше крепость пала");
                    endWhile = false;
                } else if (dwarfFortress.getProvision() <= 0) {
                    System.out.println("В вашей крепости все умерли из-за голода");
                    endWhile = false;
                }
            }catch (InputMismatchException e ) {
                System.out.println("Нельзя вводить не целочисленное число");
            }
        }
    }

    public void stateDwarfs(DwarfFortress dwarfFortress) {
        System.out.println("Общее количество групп: " + dwarfFortress.getFortressDwarfGroups().size());
        for (int i = 0; i < dwarfFortress.getFortressDwarfGroups().size(); i++) {
            System.out.println("Группа " + (i+1) + "\nЗдоровье группы: " + dwarfFortress.getFortressDwarfGroups().get(i).getDwarfsHealth());
            System.out.println("Уровень группы: " + dwarfFortress.getFortressDwarfGroups().get(i).getLevel());
            System.out.println("Урон группы: " + dwarfFortress.getFortressDwarfGroups().get(i).getDwarfsDamage());
            System.out.println("Защита группы(в процентах): " + dwarfFortress.getFortressDwarfGroups().get(i).getProtection());
            System.out.println();
        }
    }
    public void updateLevelDwarf(DwarfFortress dwarfFortress) {
        boolean endWhile = true;
        while (endWhile) {
            System.out.println("У вас " + dwarfFortress.getExperience() + " очков опыта");
            if (dwarfFortress.getExperience() > 0) {
                for (int i = 0; i < dwarfFortress.getFortressDwarfGroups().size(); i++) {
                    System.out.println("Группа " + (i + 1) + ", Уровень: " + dwarfFortress.getFortressDwarfGroups().get(i).getLevel());
                }
                System.out.println(dwarfFortress.getFortressDwarfGroups().size()+1 + ") выйти");
                System.out.println("Какую группу улучшить?");
                try {
                    int choice = new Scanner(System.in).nextInt();
                    if (choice <= dwarfFortress.getFortressDwarfGroups().size() && choice >= 0) {
                        System.out.println("Level up");
                        dwarfFortress.getFortressDwarfGroups().get(choice - 1).levelUp();
                        endWhile = false;
                    } else if (choice == dwarfFortress.getFortressDwarfGroups().size() + 1) {
                        endWhile = false;
                    } else {
                        System.out.println("Такой группы гномов нету");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Нельзя вводить не целочисленное число");
                }
            }
            else {
                System.out.println("У вас не хватает очков опыта");
                endWhile = false;
            }
        }
    }
}
