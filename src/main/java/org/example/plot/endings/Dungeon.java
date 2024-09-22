package org.example.plot.endings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import org.example.Characters.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

@Setter
@AllArgsConstructor
@Getter
public class Dungeon {
    private int level;
    private ArrayList<Enemies> enemies;
    private ArrayList<Boss> bosses;
    private Scanner sc = new Scanner(System.in);
    private Boss boss;
    private Thread attackEnemies;
    private Thread attackBoss;

    public Dungeon() {
        this.level = 1;
        enemies = new ArrayList<>();
        enemies.add(Enemies.SKELETON);
        enemies.add(Enemies.SPIDER);
        enemies.add(Enemies.GOBLIN);
        enemies.add(Enemies.GIANT);
        enemies.add(Enemies.VAMPIRE);
        enemies.add(Enemies.ZOMBIE);
        enemies.add(Enemies.BASILISK);
        enemies.add(Enemies.COBALT);
        enemies.add(Enemies.CYCLOPS);
        bosses = new ArrayList<>();
        bosses.add(Boss.DRACULA);
        bosses.add(Boss.DRAGON);
        bosses.add(Boss.GRIFFIN);
        bosses.add(Boss.GIANT_WORM);
        Random rn = new Random();
        boss = bosses.get(rn.nextInt(bosses.size()));
    }

    public int choiceDungeon() {
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

        System.out.println("Вы вошли в подземелье");
        boolean inDungeon = true;

        while (inDungeon) {
            if (level < 10) {
                System.out.println("Текущий уровень подземелья: " + level);
                switch (choiceDungeon()) {
                    case 1 -> battle(mainHero); // Начать битву
                    case 2 -> mainHero.getStats(); // Посмотреть статы героя
                    case 3 -> upgradeWeapon(mainHero); // Улучшить оружие
                    case 4 -> upgradeArmor(mainHero); // Улучшить броню
                    case 5 -> usePotion(mainHero); // Использовать зелье
                    default -> {
                        System.out.println("Вы вышли из игры.");
                        inDungeon = false;
                    }
                }
            }
            if (level >= 10) {
                switch (choiceDungeon()) {
                    case 1 -> battleOfBoss(mainHero); //Битва с боссом
                    case 2 -> mainHero.getStats(); // Посмотреть статы героя
                    case 3 -> upgradeWeapon(mainHero); // Улучшить оружие
                    case 4 -> upgradeArmor(mainHero); // Улучшить броню
                    case 5 -> usePotion(mainHero); // Использовать зелье
                    default -> {
                        System.out.println("Вы вышли из игры.");
                        inDungeon = false;
                    }
                }



                if (mainHero.isEndGame()) {
                    inDungeon = false;
                }
            }
            if (mainHero.getHealth() <= 0) {
                System.out.println("Вы погибли! Игра окончена.");
                inDungeon = false;
            }
        }
    }

    public void battle(MainHero mainHero) {
        Random rn = new Random();
        Enemies enemy = enemies.get(rn.nextInt(enemies.size()));

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
                        enemyHealth -= mainHero.getDamage();
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
        System.out.println("Теперь у вас " + mainHero.getHealth() + " здоровья");
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
        double bossHealth = 500;
        double bossDamage = 80;
        boolean inBattle = true;


        System.out.println("Вы встретили Босса подземелья: " + boss.getName());
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
                attackEnemies.interrupt();

                switch (choice) {
                    case 1 -> {
                        bossHealth -= mainHero.getDamage();
                        if (bossHealth <= 0) {
                            System.out.println("Вы победили " + boss.getName() + "!");
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
        System.out.println("Враг атакует и наносит вам " + damage + " урона.");

    }
}
