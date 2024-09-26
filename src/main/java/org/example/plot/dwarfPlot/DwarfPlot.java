package org.example.plot.dwarfPlot;

import lombok.Getter;
import lombok.Setter;
import org.example.Characters.dwarf.DwarfGroup;
import org.example.Characters.dwarf.Fortress;
import org.example.Characters.dwarf.Gate;
import org.example.Characters.dwarf.enemies.EnemiesGroup;
import org.example.Characters.dwarf.enemies.GroupEnemies;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

@Getter
@Setter
public class DwarfPlot {
    private Fortress fortress;
    private Random random = new Random();
    private Scanner sc = new Scanner(System.in);
    private ArrayList<EnemiesGroup> enemies;
    private ArrayList<DwarfGroup> recerv;

    public DwarfPlot(Fortress fortress) {
        this.fortress = fortress;
        enemies = new ArrayList<>();
        recerv = new ArrayList<>();
        enemies.add(new EnemiesGroup(GroupEnemies.VAMPIRE));
        enemies.add(new EnemiesGroup(GroupEnemies.GOBLIN));
        enemies.add(new EnemiesGroup(GroupEnemies.SKELETON));
        enemies.add(new EnemiesGroup(GroupEnemies.COBALT));
        enemies.add(new EnemiesGroup(GroupEnemies.WOLF));
        enemies.add(new EnemiesGroup(GroupEnemies.EARTHWORMS));
        enemies.add(new EnemiesGroup(GroupEnemies.GIANT_MOLES));
        enemies.add(new EnemiesGroup(GroupEnemies.SPIDER));
    }

    public void start() {
        if (fortress.getDays() >= 7) {
            System.out.println("""
                    Вы продержались эти адские недели и смогли защитить свой народ от злых монстров.
                    Ваше имя останеться в истории как: Отец гномов, спаситель
                    Теперь вы правите своими землями со спокойствием""");
            return;
        }

        System.out.println("Добро пожаловать! Король крепости - " + fortress.getName());
        System.out.println("""
                В вашем крепости начинает не хватать еды, из-за этого большинство гномов ушли на охоту,
                но остальные остались, чтобы защищать крепость от монстров, как король своей крепости вы должны
                продержаться неделью, отбиваясь от монстров.
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
                    5) купить провизии (на день - 100 монет)
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
                    case 9 -> fortressDefense();
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

    public void fortressDefense() {
        int wave = fortress.getDays();
        System.out.println("Волна: " + wave);
        double enemiesDamage = 20 * wave;
        double enemiesHealth = 20 * wave;
        int coin = 100;
        ArrayList<EnemiesGroup> allEnemies = new ArrayList<>();
        for (int i = -1; i < wave; i++) {
            int rnNum = random.nextInt(enemies.size());
            allEnemies.add(enemies.get(rnNum));
        }
        fortress.setProvision(fortress.getProvision() - 1);
        boolean inBattle = true;
        boolean motion = false;
        while (inBattle) {
            int enemiesInAttackCount = 0;
            boolean inAllEnemiesDied = false;
            for (int rnNum = random.nextInt(fortress.getGates().size()); enemiesInAttackCount < allEnemies.size(); ) {
                if (fortress.getGates().get(rnNum).AddEnemiesToThePolygon(allEnemies.get(allEnemies.size() - 1))){
                    allEnemies.remove(allEnemies.size() - 1);
                    enemiesInAttackCount++;
                } else enemiesInAttackCount--;
            }
            for (int i = 0; i < fortress.getGates().size(); i++) {
                fortress.getGates().get(i).printEnemies();
            }
            System.out.println("""
                    Что вы хотите сделать?
                    1) Отправить Гномов на защиту ворот
                    2) Убрать гномов из ворот
                    3) Пропустить ход""");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 : {
                    if (otpravkaDwarfs()) {
                        motion = true;
                        break;
                    }else {
                        break;
                    }
                }
                case 2 : {
                    if (ubratDwarfs()) {
                        motion = true;
                        break;
                    }else {
                        break;
                    }
                }
                case 3 : {
                    motion = true;
                    break;
                }
                default: System.out.println("Такого выбора нет");
            }
            // ход врагов
            if (motion){
                for (int i = 0; i < fortress.getGates().size(); i++) {
                    // удалить гномов если они сдохли
                    if (fortress.getGates().get(i).getDwarf()[0] != null && fortress.getGates().get(i).getDwarf()[0].getHealth() <= 0) {
                        fortress.getGates().get(i).getDwarf()[0] = null;
                    }
                    if(fortress.getGates().get(i).isStepsInEnemies()) {
                        if (!fortress.getGates().get(i).isPlaceOfProtection()) {
                            fortress.getGates().get(i).stepForward(fortress);
                        }else {
                            if (fortress.getGates().get(i).getSteps()[0] != null) {
                                fortress.getGates().get(i).damageInDwarfsIsEnemies(fortress);
                                if (fortress.getGates().get(i).getDwarf()[0].getHealth() <= 0) {

                                    fortress.getGates().get(i).getDwarf()[0] = null;
                                    System.out.println("Группа гномов " + fortress.getGates().get(i).getName() + "были убиты");
                                }
                            }
                            else {
                                fortress.getGates().get(i).stepForward(fortress);
                            }
                        }
                        motion = false;
                    }
                }
            }


            if (fortress.getHealth() <= 0) {
                return;
            } else if (fortress.getProvision() <= 0) {
                return;
            }
            if (isAllDeadEnemies()) {
                if (!recerv.isEmpty()) {
                    for (int i = 0; i < recerv.size(); i++) {
                        fortress.getDwarfGroups().add(recerv.get(i));
                    }
                }
                for (int i = 0; i < fortress.getGates().size(); i++) {
                    fortress.getGates().get(i).getDwarf()[0] = null;
                }
                System.out.println("Все враги были повержены");
                fortress.setDays(fortress.getDays() + 1);
                return;
            }
            motion = false;
        }
    }

    public boolean isAllDeadEnemies() {
        for (int i = 0; i < fortress.getGates().size(); i++) {
            if (fortress.getGates().get(i).isStepsInEnemies()) {
                return false;
            }
        }
        return true;
    }

    public boolean ubratDwarfs() {
        System.out.println("Выберете ворота");
        for (int i = 0; i < fortress.getGates().size(); i++) {
            System.out.println(i + 1 + (") убрать гномов " + fortress.getGates().get(i).getName()));
        }
        System.out.println(fortress.getGates().size() + 1 + ") вернутся назад");
        int choiceInGate = sc.nextInt() - 1;
        if(choiceInGate == fortress.getGates().size()) {
            return false;
        }
        if (choiceInGate >= 0 && choiceInGate <= fortress.getGates().size()) {
            if (fortress.getGates().get(choiceInGate).isPlaceOfProtection()) {
                deleteDwarfs(choiceInGate);
                return true;
            } else {
                System.out.println("В этих воротах нету гномов");
                return false;
            }
        } else {
            System.out.println("Таких ворот нету выберите заново");
            return false;
        }
    }

    public void deleteDwarfs(int choice) {
        for (int i = 0; i < fortress.getGates().get(choice).getDwarf().length; i++) {
            if (fortress.getGates().get(choice).isPlaceOfProtection()) {
                if (fortress.getGates().get(choice).getDwarf()[i] != null) {
                    fortress.getDwarfGroups().add(fortress.getGates().get(choice).getDwarf()[i]);
                    for (int j = 0; j < recerv.size(); j++) {
                        if (recerv.get(i) == fortress.getGates().get(choice).getDwarf()[i]) {
                            recerv.remove(i);
                        }
                    }
                    fortress.getGates().get(choice).getDwarf()[i] = null;
                    System.out.println("Вы убрали гномов " + fortress.getGates().get(choice).getName());
                }
            }
        }
    }


    public boolean otpravkaDwarfs() {
        while (true) {
            if (!fortress.getDwarfGroups().isEmpty()) {
                for (int i = 0; i < fortress.getDwarfGroups().size(); i++) {
                    DwarfGroup group = fortress.getDwarfGroups().get(i);
                    System.out.println("Группа " + (i + 1) + "\nЗдоровье группы: " + group.getHealth());
                    System.out.println("Урон группы: " + group.getDamage());
                    System.out.println("Защита группы (в процентах): " + group.getProtection());
                    System.out.println();
                }
                System.out.println(fortress.getDwarfGroups().size() + 1 + ") вернутся назад");
                int choice = sc.nextInt() - 1;
                if(choice == fortress.getDwarfGroups().size()) {
                    return false;
                }
                if (choice <= fortress.getDwarfGroups().size() && choice >= 0) {
                    System.out.println("Вы выбрали гномов теперь выберете ворота");
                    for (int i = 0; i < fortress.getGates().size(); i++) {
                        System.out.println(i + 1 + (") поставить гномов " + fortress.getGates().get(i).getName()));
                    }
                    System.out.println(fortress.getGates().size() + 1 + ") вернуться назад");

                    int choiceInGate = sc.nextInt() - 1;
                    if(choiceInGate == fortress.getGates().size()) {
                        return false;
                    }
                    if (choiceInGate >= 0 && choiceInGate <= fortress.getGates().size()) {
                        if (fortress.getGates().get(choiceInGate).dwarfOfProtection(fortress.getDwarfGroups().get(choice))) {
                            recerv.add(fortress.getDwarfGroups().get(choice));
                            fortress.getDwarfGroups().remove(choice);
                            return true;
                        }
                    } else {
                        System.out.println("Таких ворот нету выберите заново");
                    }
                }

                else {
                    System.out.println("Такой группы гномов нету выберите заново");
                }
            }else {
                System.out.println("У вас нету гномов");
                return false;
            }
        }
    }

    public void stateDwarfs() {
        if (!fortress.getDwarfGroups().isEmpty()) {
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
        System.out.println("У вас нету гномов");
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
        if (fortress.getBalance() >= 100) {
            fortress.setProvision(fortress.getProvision() + 1);
            fortress.setBalance(fortress.getBalance() - 100);
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