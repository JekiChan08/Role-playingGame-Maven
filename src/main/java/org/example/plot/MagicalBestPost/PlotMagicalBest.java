package org.example.plot.MagicalBestPost;

import org.example.Characters.MagicalBest.MagicalEnemies;
import org.example.Characters.MagicalBest.PC;
import org.example.item.MagicalBest.Abilities;

import java.util.*;

public class PlotMagicalBest {
    private Random random = new Random();
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Abilities> abilities;
    private int battles;

    public PlotMagicalBest() {
        this.battles = 1;
        abilities = new ArrayList<>();
        abilities.add(new Abilities("Огненный Шар", 25.0, 5, false));
        abilities.add(new Abilities("Ледяной Осколок", 15.0, 3, false));
        abilities.add(new Abilities("Удар Молнии", 35.0, 7, false));
        abilities.add(new Abilities("Воздушный Разрез", 10.0, 2, false));
        abilities.add(new Abilities("Землетрясение", 50.0, 10, false));
        abilities.add(new Abilities("Водяной Поток", 18.0, 4, false));
        abilities.add(new Abilities("Темный Шип", 30.0, 6, false));
        abilities.add(new Abilities("Святой Свет", 20.0, 5, false));
        abilities.add(new Abilities("Арканный Импульс", 12.0, 3, false));
        abilities.add(new Abilities("Ядовитый Туман", 8.0, 2, false));
        abilities.add(new Abilities("Метеоритный Дождь", 45.0, 9, false));
        abilities.add(new Abilities("Громовой Удар", 15.0, 4, true));
        abilities.add(new Abilities("Земной Разлом", 20.0, 5, true));
    }

    public void start(PC pc) {
        String[] enemyNames = {"Раин Гослинг", "Морган Фриман", "Киану Вич", "Чак Норрис", "Николас Кейдж", "Арнольд Шварценгер", "Дуэйн Скала Джонсон", "Сильвестр Сталлоне", "Джейсон Стейтем", "Джек Воробей"};

        System.out.println("Здравствуй игрок!");
        System.out.println("""
                Каждые 100 лет у маг-зверей проходит битва чемпионов, где победитель становится
                полубогом, правящим небесным царством. Главный герой тренировался последние 90 лет, и вот настало
                время биться.
                Битва проходит в концепте карточных ролевых игр, где ты выбираешь, каким способностями бить врага.
                Всего в битве вы должны победить 10 раз.
                """);
        Thread manaRegenThread = new Thread(() -> {
            while (battles <= 10 && pc.getHealth() > 0) {
                try {
                    Thread.sleep(3000);
                    synchronized (pc) {
                        if (pc.getMana() < pc.getMaxMana()) {
                            pc.setMana(pc.getMana() + 1);
                            System.out.println("+1 мана восстановлена. Текущая мана: " + pc.getMana());
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        manaRegenThread.start();

        while (battles <= 10) {
            pc.setHealth(pc.getMaxHealth());
            pc.setMana(pc.getMaxMana());
            MagicalEnemies enemy = new MagicalEnemies(100, enemyNames[battles - 1], 10, 10);

            System.out.println("Битва номер: " + battles);
            System.out.println("Ваш противник: " + enemy.getName() + ", Здоровье: " + enemy.getHealth());
            System.out.println("Ваша мана: " + pc.getMana());

            while (enemy.getHealth() > 0 && pc.getHealth() > 0) {
                ArrayList<Abilities> availableAbilities = getRandomAbilities(4);
                System.out.println("\nВыберите способность для атаки:");
                for (int i = 0; i < availableAbilities.size(); i++) {
                    Abilities ability = availableAbilities.get(i);
                    if (!ability.isContinueMotion()) {
                        System.out.println((i + 1) + ". " + ability.getTitle() + " (Урон: " + ability.getDamage() + ", Мана: " + ability.getMana() + " не оглушает)");
                    }else {
                        System.out.println((i + 1) + ". " + ability.getTitle() + " (Урон: " + ability.getDamage() + ", Мана: " + ability.getMana() +  " оглушает)");

                    }
                }


                int choice = getChoice(1, availableAbilities.size());
                Abilities chosenAbility = availableAbilities.get(choice - 1);

                if (pc.getMana() < chosenAbility.getMana()) {
                    System.out.println("Недостаточно маны для использования этой способности!");
                    continue;
                }

                pc.setMana(pc.getMana() - chosenAbility.getMana());
                System.out.println("Вы используете " + chosenAbility.getTitle() + " и наносите " + chosenAbility.getDamage() + " урона.");

                enemy.setHealth(enemy.getHealth() - chosenAbility.getDamage());

                if (chosenAbility.isContinueMotion()) {
                    System.out.println(enemy.getName() + " оглушен и пропускает ход!");
                    System.out.println("Здоровье врага: " + enemy.getHealth());
                } else if (enemy.getHealth() > 0) {
                    System.out.println("Здоровье врага: " + enemy.getHealth());
                    attackPlayer(pc, enemy);
                }

                pc.setMana(Math.min(pc.getMana() + 2, pc.getMaxMana()));
                if (enemy.getHealth() <= 0) {
                    System.out.println("Вы победили " + enemy.getName() + "!");
                } else if (pc.getHealth() <= 0) {
                    System.out.println("Вы были побеждены! Игра окончена.");
                    return;
                }
            }

            battles++;
        }

        System.out.println("Поздравляю! Вы стали полубогом и царем неба!");
    }

    private ArrayList<Abilities> getRandomAbilities(int count) {
        ArrayList<Abilities> randomAbilities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            randomAbilities.add(abilities.get(random.nextInt(abilities.size())));
        }
        return randomAbilities;
    }

    private int getChoice(int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            try {
                System.out.print("Ваш выбор: ");
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Введите корректное число!");
            }
        }
        return choice;
    }

    private void attackPlayer(PC pc, MagicalEnemies enemy) {
        double damage = random.nextInt(15, 30);
        System.out.println(enemy.getName() + " атакует и наносит " + damage + " урона.");
        pc.setHealth(pc.getHealth() - damage);
    }
}
