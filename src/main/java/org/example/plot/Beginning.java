package org.example.plot;


import org.example.Characters.*;
import org.example.Characters.dwarf.DwarfFortress;
import org.example.item.*;

import java.util.Scanner;

public class Beginning {
    //выбор имени рассы и класса
    public static void ChoosingRace(MainHero mainHero, DwarfFortress dwarfFortress) {
        boolean whileBeginning = true;
        while (whileBeginning ) {
            boolean endWhile = true;
            Scanner sc = new Scanner(System.in);

            while (endWhile) {

                System.out.println("""
                        Здравствуй игрок выбери рассу написав число:
                        1) Человек
                        2) Гном
                        3) Эльф
                        4) Орк
                        по выбранному персоважу будет по разному развиваться сюжет""");

                int rassNum = sc.nextInt();

                //Выбор и инициализация рассы
                switch (rassNum) {
                    case 1:
                        mainHero.setRassa(Rassa.PERSON);
                        System.out.println("Теперь ваша расса: " + mainHero.getRassa().getTitle());
                        endWhile = false;
                        System.out.println("Привет игрок как тебя зовут?");
                        String name = sc.next();
                        mainHero.setName(name);
                        break;
                    case 2:
                        System.out.println("Теперь вы играете за рассу Гнома!");
                        endWhile = false;
                        System.out.println("Привет игрок! Как назовёш свою крепость?");
                        String nameFort = sc.next();
                        dwarfFortress.setFortressName(nameFort);
                        mainHero.setRassa(Rassa.DWARF);
                        whileBeginning = false;
                        break;
                    case 3:
                        mainHero.setRassa(Rassa.ELF);
                        System.out.println("Теперь ваша расса: " + mainHero.getRassa().getTitle());
                        endWhile = false;
                        break;
                    case 4:
                        mainHero.setRassa(Rassa.ORC);
                        System.out.println("Теперь ваша расса: " + mainHero.getRassa().getTitle());
                        endWhile = false;
                        break;
                    default: {
                        System.out.println("Такой рассы нету на выбор, выберите снова");
                        break;
                    }
                }
            }
            // сэжет не за дварфа
            if (mainHero.getRassa().equals(Rassa.PERSON)) {
                endWhile = true;
                while (endWhile) {
                    System.out.println(mainHero.getName() + " Выберете класс для вашего персонажа: \n" +
                            "1) воин\n" +
                            "2) маг\n");
                    int classPerson = sc.nextInt();
                    switch (classPerson) {
                        case 1: {
                            mainHero.setPersonClass(PersonClass.WARRIOR);
                            System.out.println("Теперь вы: " + mainHero.getPersonClass().getTitle());
                            endWhile = false;
                            break;
                        }
                        case 2: {
                            mainHero.setPersonClass(PersonClass.MAG);
                            System.out.println("Теперь вы: " + mainHero.getPersonClass().getTitle());
                            endWhile = false;
                            break;
                        }
                        default: {
                            System.out.println("Такого класса нет, выберите то что предложено");
                            break;
                        }
                    }
                }
                if (mainHero.getRassa().equals(Rassa.PERSON)) {
                    endWhile = true;
                    while (endWhile) {
                        if (mainHero.getPersonClass() == PersonClass.WARRIOR) {
                            System.out.println("Вам выдаеться базовай меч!");
                            mainHero.setWeapon(Weapon.SWORD);
                            endWhile = false;
                            whileBeginning = false;

                        } else if (mainHero.getPersonClass() == PersonClass.MAG) {
                            System.out.println("Вам выдаеться базовай гримуар!");
                            mainHero.setWeapon(Weapon.GREAMUAR);
                            endWhile = false;
                            whileBeginning = false;
                        }
                    }
                }
            }
        }
    }
}
