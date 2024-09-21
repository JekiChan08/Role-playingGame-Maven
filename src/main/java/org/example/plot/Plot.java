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
                endWhile = false;
            }
        }
    }
}
