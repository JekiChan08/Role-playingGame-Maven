package org.example.plot.dwarfPlot;

import lombok.Getter;
import lombok.Setter;
import org.example.Characters.dwarf.DwarfGroup;
import java.util.InputMismatchException;
import org.example.Characters.dwarf.Fortress;
import org.example.Characters.dwarf.Gate;

import java.util.Random;
import java.util.Scanner;

@Getter
@Setter
public class DwarfPlot {
    private Fortress fortress;
    private Random random = new Random();

    public DwarfPlot(Fortress fortress) {
        this.fortress = fortress;
    }

    public void start() {
        System.out.println("Добро пожаловать! Король крепости - " + fortress.getName());
        System.out.println("""
                В вашем крепости начинает не хватать еды, из-за этого большинство гномов ушли на охоту,
                но остальные остались, чтобы защищать крепость от монстров, как король своей крепости вы должны
                продержаться месяц, отбиваясь от монстров.
                Удачи!""");

        boolean endWhile = true;
        while (endWhile) {
            System.out.println("День " + fortress.getDays());
            System.out.println("Баланс: " + fortress.getBalance());
            System.out.println("""
                    Что вы хотите сделать перед атакой врагов?
                    1) проверить состояние крепости
                    2) проверить состояния гномов
                    3) улучшить группу гномов (Стоит 1 опыта)
                    4) купить группу гномов у соседа (50 монет)
                    5) купить провизии (на день - 20 монет)
                    6) улучшить группе гномов броню (за 20 монет, рандомно 4-7%, макс 80%)
                    7) улучшить ворота (100 монет, +20% защиты, макс 80%)
                    8) проверить состояние ворот
                    9) начать защиту крепости
                    10) выйти из игры""");
            try {
                int choice = new Scanner(System.in).nextInt();

                switch (choice) {
                    case 1 -> fortress.stateFortress();
                    case 2 -> stateDwarfs();
                    case 3 -> updateLevelDwarf();
                    case 4 -> buyingDwarves();
                    case 5 -> buyingProvision();
                    case 6 -> upgradeOfArmor();
                    case 7 -> upgradeGates();
                    case 8 -> stateGates();
                    case 10 -> {
                        System.out.println("Вы вышли из игры");
                        endWhile = false;
                    }
                    default -> System.out.println("Такого выбора нет");
                }
                if (fortress.getHealth() <= 0) {
                    System.out.println("Ваша крепость пала");
                    endWhile = false;
                } else if (fortress.getProvision() <= 0) {
                    System.out.println("В вашей крепости все умерли из-за голода");
                    endWhile = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Нельзя вводить не целочисленное число");
            }
        }
    }


    public void stateDwarfs() {
        System.out.println("Общее количество групп: " + fortress.getDwarfGroups().size());
        for (int i = 0; i < fortress.getDwarfGroups().size(); i++) {
            DwarfGroup group = fortress.getDwarfGroups().get(i);
            System.out.println("Группа " + (i + 1) + "\nЗдоровье группы: " + group.getHealth());
            System.out.println("Уровень группы: " + group.getLevel());
            System.out.println("Урон группы: " + group.getDamage());
            System.out.println("Защита группы (в процентах): " + group.getProtection());
            System.out.println();
        }
    }

    public void stateGates() {
        for (int i = 0; i < fortress.getGates().size(); i++) {
            Gate gate = fortress.getGates().get(i);
            System.out.println("Ворота " + gate.getName() + "\nЗдоровье: " + gate.getHealth());
            System.out.println("Защита: " + gate.getProtection() + "%");
            System.out.println();
        }
    }

    public void updateLevelDwarf() {
        boolean endWhile = true;
        while (endWhile) {
            System.out.println("У вас " + fortress.getExperience() + " очков опыта");
            if (fortress.getExperience() > 0) {
                for (int i = 0; i < fortress.getDwarfGroups().size(); i++) {
                    System.out.println("Группа " + (i + 1) + ", Уровень: " + fortress.getDwarfGroups().get(i).getLevel());
                }
                System.out.println(fortress.getDwarfGroups().size() + 1 + ") выйти");
                System.out.println("Какую группу улучшить?");
                try {
                    int choice = new Scanner(System.in).nextInt();
                    if (choice <= fortress.getDwarfGroups().size() && choice >= 0) {
                        System.out.println("Level up");
                        fortress.getDwarfGroups().get(choice - 1).levelUp();
                        fortress.setExperience(fortress.getExperience() - 1);
                        endWhile = false;
                    } else if (choice == fortress.getDwarfGroups().size() + 1) {
                        endWhile = false;
                    } else {
                        System.out.println("Такой группы гномов нет");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Нельзя вводить не целочисленное число");
                }
            } else {
                System.out.println("У вас не хватает очков опыта");
                endWhile = false;
            }
        }
    }

    public void buyingDwarves() {
        if (fortress.getBalance() >= 50) {
            fortress.setBalance(fortress.getBalance() - 50);
            fortress.getDwarfGroups().add(new DwarfGroup());
            System.out.println("Поздравляю с покупкой");
        } else {
            System.out.println("У вас не хватает монет");
        }
    }

    public void buyingProvision() {
        if (fortress.getBalance() >= 20) {
            fortress.setProvision(fortress.getProvision() + 1);
            fortress.setBalance(fortress.getBalance() - 20);
            System.out.println("Поздравляю с покупкой");
        } else {
            System.out.println("У вас не хватает монет");
        }
    }

    public void upgradeOfArmor() {
        if (fortress.getBalance() >= 20) {
            boolean endWhile = true;
            while (endWhile) {
                if (fortress.getExperience() > 0) {
                    for (int i = 0; i < fortress.getDwarfGroups().size(); i++) {
                        DwarfGroup group = fortress.getDwarfGroups().get(i);
                        System.out.println("Группа " + (i + 1) + ", урон: " + group.getDamage());
                        System.out.println("Защита: " + group.getProtection() + "%");
                        System.out.println();
                    }
                    System.out.println(fortress.getDwarfGroups().size() + 1 + ") выйти");
                    System.out.println("Какой группе улучшить броню?");
                    try {
                        int choice = new Scanner(System.in).nextInt();
                        if (choice <= fortress.getDwarfGroups().size() && choice >= 0) {
                            if (fortress.getDwarfGroups().get(choice - 1).getProtection() < 80) {
                                int upgrade = new Random().nextInt(4, 7);
                                System.out.println("Успешно улучшено!");
                                fortress.getDwarfGroups().get(choice - 1).setProtection(fortress.getDwarfGroups().get(choice - 1).getProtection() + upgrade);
                                fortress.setBalance(fortress.getBalance() - 20);
                                endWhile = false;
                            } else {
                                System.out.println("У группы гномов максимальное значение защиты");
                            }
                        } else if (choice == fortress.getDwarfGroups().size() + 1) {
                            endWhile = false;
                        } else {
                            System.out.println("Такой группы гномов нет");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Нельзя вводить не целочисленное число");
                    }
                } else {
                    System.out.println("У вас не хватает очков опыта");
                    endWhile = false;
                }
            }
            System.out.println("Поздравляю с покупкой");
        } else {
            System.out.println("У вас не хватает монет");
        }
    }

    public void upgradeGates() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите ворота для улучшения:");
        for (int i = 0; i < fortress.getGates().size(); i++) {
            System.out.println((i + 1) + ") " + fortress.getGates().get(i).getName());
        }

        int choice = scanner.nextInt();
        if (choice < 1 || choice > fortress.getGates().size()) {
            System.out.println("Неверный выбор");
            return;
        }

        Gate selectedGate = fortress.getGates().get(choice - 1);

        if (fortress.getBalance() < 100) {
            System.out.println("Недостаточно монет для улучшения ворот");
            return;
        }

        if (selectedGate.getProtection() >= 80) {
            System.out.println("Ворота уже максимально улучшены");
            return;
        }

        selectedGate.setProtection(selectedGate.getProtection() + 20);
        fortress.setBalance(fortress.getBalance() - 100);
        System.out.println("Ворота успешно улучшены!");
    }
}