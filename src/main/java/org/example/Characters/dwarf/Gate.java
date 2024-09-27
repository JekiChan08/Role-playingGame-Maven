package org.example.Characters.dwarf;

import lombok.Getter;
import lombok.Setter;
import org.example.Characters.dwarf.enemies.EnemiesGroup;
import org.example.Characters.dwarf.enemies.GroupEnemies;

@Getter
@Setter
public class Gate {
    private String name;
    private double health;
    private double protection;
    private EnemiesGroup[] steps;
    private DwarfGroup[] dwarf;
    private double allDamage;

    public Gate(String name, int health) {
        this.name = name;
        this.health = health;
        this.protection = 0;
        this.steps = new EnemiesGroup[]{null, null, null, null};
        this.dwarf = new DwarfGroup[]{null};
    }

    public void damageInDwarfsIsEnemies(Fortress fortress) {
        for (int i = 0; i < dwarf.length; i++) {
            if (dwarf[0] != null) {
                if (dwarf[0].getHealth() <= 0) {
                    fortress.setHealth(fortress.getHealth() - steps[i].getDamage());
                    System.out.println("Враг наносит по крепости " + steps[i].getDamage() + " урон");
                }
                else {
                    steps[0].setHealth(steps[0].getHealth() - allDamage);
                    System.out.println("Гномы наносять врагу " + name + " " + allDamage + " Урона");
                    if (steps[0].getHealth() > 0) {
                        System.out.println("Теперь у врагов " + steps[0].getHealth() + "здоровье");
                        System.out.println();
                        System.out.println("Враги " + name + " наносят урон гномам в размере " + steps[0].getDamage());
                        dwarf[i].setHealth(dwarf[i].getHealth() - steps[0].getDamage());
                    }else if (steps[0].getHealth() <= 0) {
                        fortress.setBalance(fortress.getBalance() + steps[0].getCoin());
                        fortress.setExperience(fortress.getExperience() + 1);
                        System.out.println("Гномы " + name + " Убили врагов");
                        steps[0] = null;
                    }
                }

            }
        }
    }

    public boolean AddEnemiesToThePolygon(EnemiesGroup groupEnemies) {
        if (steps[3] == null) {
            steps[3] = groupEnemies;
            return true;
        } return false;
    }

    public boolean isPlaceOfProtection() {
        boolean da = false;
        for (int i = 0; i < dwarf.length; i++) {
            if (dwarf[i] != null) {
                da = true;
            }
        }
        return da;
    }



    public boolean dwarfOfProtection(DwarfGroup dwarfGroup) {
        for (int i = 0; i < dwarf.length; i++) {
            if (dwarf[i] == null) {
                dwarf[i] = dwarfGroup;
                allDamage += dwarfGroup.getDamage();
                System.out.println("Гном успешно отправлен на ворота");
                return true;
            }
        }
        System.out.println("Нету свободных мест");
        return false;
    }



    public boolean isStepsInEnemies() {
        boolean da = false;
        for (int i = 0; i < steps.length; i++) {
            if (steps[i] != null) {
                da = true;
            }
        }
        return da;
    }

    public void stepForward(Fortress fortress) {
        for (int i = 0; i < steps.length; i++) {
            if (steps[i] != null && i != 0) {
                if (steps[i-1] == null) {
                    steps[i-1] = steps[i];
                    steps[i] = null;
                }

            } else if (steps[i] != null && i == 0) {
                if (health > 0) {
                    if (protection > 1) {
                        double damage = steps[i].getDamage() - (steps[i].getDamage() * (protection / 100));
                        setHealth(getHealth() - damage);
                        System.out.println(name + " враг наносит урон варотам в размере: " + steps[i].getDamage());
                    } else {
                        setHealth(getHealth() - steps[i].getDamage());
                        System.out.println(name + " враг наносит урон варотам в размере: " + steps[i].getDamage());
                    }
                }else {
                    fortress.setHealth(fortress.getHealth() - steps[i].getDamage());
                    System.out.println("Враг наносит по крепости " + steps[i].getDamage() + " урон");
                }
            }
        }
    }


    public void printEnemies() {
        for (int i = 0; i < steps.length; i++) {
            if (steps[i] != null) {
                System.out.print(name + " ");
                System.out.print("на растоянии " + i + "км идут " + steps[i].getName() + "\n");
            }
        }
    }
}