package org.example.plot;

import org.example.Characters.*;
import org.example.plot.endings.*;

import java.util.InputMismatchException;

//Сюжет
public class Plot {
    public static void startingPlot(MainHero mainHero) {
        //начало сюжета
        boolean endWhile = true;
        while (endWhile) {
            try {
            Beginning.ChoosingRace(mainHero);
            }catch (InputMismatchException e) {
                System.out.println("Нельзя писать что то другое кроме целочисленных цифер");
                Beginning.ChoosingRace(mainHero);

            }
            if (mainHero.getRassa() == Rassa.PERSON) {
                EndPerson.start(mainHero);
                if(mainHero.getHealth() > 0 && mainHero.isEndGame()) {
                    System.out.println("""
                            Поздравляю вас с победой над главным злодеем этого мира
                            весь мир благодарна вам за то что вы смогли защитить
                            их земли и семьи.
                            Ваша победа навсегда останется в истории как спаситель земли.""");

                }
                endWhile = false;
            } else if (mainHero.getRassa() == Rassa.ELF) {
                EndElf.start(mainHero);
                if(mainHero.getHealth() > 0 && mainHero.isEndGame()) {
                    System.out.println("""
                            Поздравляю"""
                              + mainHero.getName() +
                            """
                            с победой над главным злодеем этого мира
                            весь мир благодарна вам за то что вы смогли защитить
                            их земли и семьи.
                            Ваша победа навсегда останется в истории как спаситель земли.""");

                }
                endWhile = false;
            }
        }
    }
}
