package org.example.plot.endings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.Characters.Boss;
import org.example.Characters.Enemies;
import org.example.Characters.MainHero;
import org.example.Characters.PersonClass;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

@Setter
@AllArgsConstructor
@Getter
public class Mirkwood {
    private int level;
    private ArrayList<Enemies> enemies;
    private ArrayList<Boss> bosses;
    private Scanner sc = new Scanner(System.in);
    private Boss boss;
    private Thread attackEnemies;
    private Thread attackBoss;
    private String redText = "\u001B[31m";
    private String resetText = "\u001B[0m";

    public Mirkwood() {
        this.level = 1;
        enemies = new ArrayList<>();
        enemies.add(Enemies.SPYSPIRIT);
        enemies.add(Enemies.SPIDER);
        enemies.add(Enemies.DRAUGLIN);
        bosses = new ArrayList<>();
        bosses.add(Boss.NAZGUL);
        Random rn = new Random();
        boss = bosses.get(rn.nextInt(bosses.size()));
    }

    public int choiceMirkwood() {
        System.out.println("""
                Выберите действие:
                1) Пойти сражаться
                2) Проверить свои статы
                3) Улучшить оружие (75 монет)
                4) Улучшить защиту (100 монет)
                5) Использовать зелье (50 монет, восстанавливает 50 HP)
                любое другое) Выйти из игры""");
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод! Введите число.");
            sc.nextLine();
            return 0;
        }
    }

    public void start(MainHero mainHero) {

        System.out.println("Вы вошли в Лихолесье");
        boolean inMirkwood = true;

        while (inMirkwood) {
            if (level <= enemies.size()) {
                System.out.println("Текущий уровень Лихолесье: " + level);
                switch (choiceMirkwood()) {
                    case 1 -> battle(mainHero); // Начать битву
                    case 2 -> mainHero.getStats(); // Посмотреть статы героя
                    case 3 -> upgradeWeapon(mainHero); // Улучшить оружие
                    case 4 -> upgradeArmor(mainHero); // Улучшить броню
                    case 5 -> usePotion(mainHero); // Использовать зелье
                    default -> {
                        System.out.println("Вы вышли из игры.");
                        inMirkwood = false;
                    }
                }
            }
            if (level == (enemies.size() + 1)) {
                switch (choiceMirkwood()) {
                    case 1 -> battleOfBoss(mainHero); //Битва с боссом
                    case 2 -> mainHero.getStats(); // Посмотреть статы героя
                    case 3 -> upgradeWeapon(mainHero); // Улучшить оружие
                    case 4 -> upgradeArmor(mainHero); // Улучшить броню
                    case 5 -> usePotion(mainHero); // Использовать зелье
                    default -> {
                        System.out.println("Вы вышли из игры.");
                        inMirkwood = false;
                    }
                }



                if (mainHero.isEndGame()) {
                    inMirkwood = false;
                }
            }
            if (mainHero.getHealth() <= 0) {
                System.out.println(redText + "Вы погибли! Игра окончена." + resetText);
                inMirkwood = false;
            }
        }
    }

    public void battle(MainHero mainHero) {
        Enemies enemy = enemies.get(level - 1);

        double enemyHealth = 45 + (level * 10);
        double enemyDamage = 6.2 + (level * 3);
        boolean inBattle = true;

        System.out.println("Вы встретили врага: " + enemy.getName());
        System.out.println("Урон врага: " + enemyDamage + ", Здоровье врага: " + enemyHealth);
        System.out.println("Ваше здоровье: " + mainHero.getHealth());
        System.out.println("Если вы не ударите врага в течении 15-секунд, враг вас заметит и ударит, а также будет вас ударять каждые 10 секунд");

        attackEnemies = new Thread(() -> {
            boolean attackIn15sec = true;
            while (attackIn15sec) {
                try {
                    Thread.sleep(15 * 1000);
                    enemyAttack(mainHero, enemyDamage);
                } catch (InterruptedException e) {
                    attackIn15sec = false;
                    Thread.currentThread().interrupt();
                }
            }

        });
        attackEnemies.start();

        while (inBattle) {
            System.out.println("\nВаши действия:\n" +
                    "1) Атаковать (ваш урон: " + mainHero.getDamage() + ")\n" +
                    "2) Использовать зелье (восстановить 50 HP)\n" +
                    "3) Сбежать из битвы");

            try {
                int choice = sc.nextInt();
                attackEnemies.interrupt();
                switch (choice) {
                    case 1 -> {
                        double damage = mainHero.getDamage();
                        if (mainHero.getPersonClass() == PersonClass.MAG) {
                            enemyHealth -= damage;
                        } else {
                            enemyHealth -= damage - (damage * 20 / 100);
                        }
                        if (enemyHealth <= 0) {
                            System.out.println("Вы победили " + enemy.getName() + "!");
                            levelUp(mainHero, enemy);
                            inBattle = false;
                        } else {
                            enemyAttack(mainHero, enemyDamage);
                            if (mainHero.getHealth() <= 0) {
                                System.out.println("Вы погибли в бою.");
                                inBattle = false;
                            }
                        }
                    }
                    case 2 -> usePotion(mainHero);
                    case 3 -> {
                        System.out.println("Вы сбежали из битвы.");
                        inBattle = false;
                    }
                    default -> System.out.println("Некорректный выбор.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод! Введите число.");
                sc.nextLine();
            }
        }
    }

    private void enemyAttack(MainHero mainHero, double enemyDamage) {
        double damage = enemyDamage * (1 - mainHero.getArmor().getDefense() / 100.0);
        mainHero.setHealth(mainHero.getHealth() - damage);
        System.out.println("Враг атакует и наносит вам " + damage + " урона.");
        System.out.println("Теперь у вас " + "\u001B[32m" + mainHero.getHealth() + resetText + " здоровья");
    }

    private void levelUp(MainHero mainHero, Enemies enemy) {
        level++;
        mainHero.setMoney(mainHero.getMoney() + enemy.getMany());
        System.out.println("Вы получили " + enemy.getMany() + " монет. Всего денег: " + mainHero.getMoney());
    }

    public void upgradeWeapon(MainHero mainHero) {
        if (mainHero.getMoney() >= 75) {
            mainHero.setMoney(mainHero.getMoney() - 75);
            mainHero.setWeapon(mainHero.getWeapon().upgradeWeapon());
            System.out.println("Вы улучшили оружие! Новый урон: " + mainHero.getDamage());
        } else {
            System.out.println("Недостаточно монет для улучшения оружия.");
        }
    }

    public void upgradeArmor(MainHero mainHero) {
        if (mainHero.getMoney() >= 100) {
            mainHero.setMoney(mainHero.getMoney() - 100);
            mainHero.upgradeArmor(10);
            System.out.println("Вы улучшили броню! Новая защита: " + mainHero.getArmor().getDefense() + "%");
        } else {
            System.out.println("Недостаточно монет для улучшения брони.");
        }
    }

    public void usePotion(MainHero mainHero) {
        if (mainHero.getMoney() >= 50) {
            mainHero.setMoney(mainHero.getMoney() - 50);
            mainHero.setHealth(mainHero.getHealth() + 50);
            System.out.println("Вы использовали зелье и восстановили 50 HP. Ваше здоровье: " + mainHero.getHealth());
        } else {
            System.out.println("Недостаточно монет для зелья.");
        }
    }

    public void battleOfBoss(MainHero mainHero) {
        double bossHealth = 100;
        double bossDamage = 40;
        boolean inBattle = true;


        System.out.println("Вы встретили Босса в сердце леса: " + boss.getName());
        System.out.println("Урон врага: " + bossDamage + ", Здоровье врага: " + bossHealth);
        System.out.println("Ваше здоровье: " + mainHero.getHealth());
        System.out.println("если вы не ударите врага в течении 10-секунд, босс вас заметит и ударит");

        attackBoss = new Thread(() -> {
            boolean attackIn15sec = true;
            while (attackIn15sec) {
                try {
                    Thread.sleep(15 * 1000);
                    bossAttack(mainHero, bossDamage);
                } catch (InterruptedException e) {
                    attackIn15sec = false;
                    Thread.currentThread().interrupt();
                }
            }

        });
        attackBoss.start();

        while (inBattle) {
            System.out.println("\nВаши действия:\n" +
                    "1) Атаковать (ваш урон: " + mainHero.getDamage() + ")\n" +
                    "2) Использовать зелье (восстановить 50 HP)\n" +
                    "3) Сбежать из битвы");

            try {
                int choice = sc.nextInt();
                attackBoss.interrupt();

                switch (choice) {
                    case 1 -> {
                        bossHealth -= mainHero.getDamage();
                        if (bossHealth <= 0) {
                            System.out.println("\u001B[32mВы победили " + boss.getName() + "!" + resetText);
                            mainHero.setEndGame(true);
                            inBattle = false;
                        } else {
                            bossAttack(mainHero, bossDamage);
                            if (mainHero.getHealth() <= 0) {
                                System.out.println("Вы погибли в бою.");
                                inBattle = false;
                            }
                        }
                    }
                    case 2 -> usePotion(mainHero);
                    case 3 -> {
                        System.out.println("Вы сбежали из битвы.");
                        inBattle = false;
                    }
                    default -> System.out.println("Некорректный выбор.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод! Введите число.");
                sc.nextLine();
            }
        }
    }

    private void bossAttack(MainHero mainHero, double bossDamage) {
        double damage = bossDamage * (1 - mainHero.getArmor().getDefense() / 100.0);
        mainHero.setHealth(mainHero.getHealth() - damage);
        System.out.println("Враг атакует и наносит вам " + redText + damage + resetText + " урона.");

    }
}
