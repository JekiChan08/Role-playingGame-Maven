package org.example.plot;

import org.example.Characters.*;
import org.example.Characters.MagicalBest.PC;
import org.example.Characters.dwarf.Fortress;
import org.example.plot.MagicalBestPost.PlotMagicalBest;
import org.example.plot.dwarfPlot.DwarfPlot;
import org.example.plot.endings.*;

import java.util.InputMismatchException;

//Сюжет
public class Plot {
    public static void startingPlot() {
        MainHero mainHero = new MainHero();
        Fortress dwarfFortress = new Fortress();
        PC pc = new PC();
        //начало сюжета
        boolean endWhile = true;
        while (endWhile) {
            try {
                Beginning.ChoosingRace(mainHero, dwarfFortress, pc);
                endWhile = false;
            } catch (InputMismatchException e) {
                System.out.println("Нельзя писать что то другое кроме целочисленных цифер");
                endWhile = false;
            }
        }
        // концовка за рассу человека
        if (mainHero.getRassa() == Rassa.PERSON) {
            EndPerson.start(mainHero);
            if(mainHero.getHealth() > 0 && mainHero.isEndGame()) {
                System.out.println("""
                        Поздравляю вас с победой над главным злодеем этого мира
                        весь мир благодарна вам за то что вы смогли защитить
                        их земли и семьи.
                        Ваша победа навсегда останется в истории как спаситель земли.""");

            }
        }
        else if (mainHero.getRassa().equals(Rassa.DWARF)) {
            new DwarfPlot(dwarfFortress).start();

        }
        else if (mainHero.getRassa() == Rassa.ELF) {
            EndElf.start(mainHero);
            if (mainHero.getHealth() > 0 && mainHero.isEndGame()) {
                System.out.println("""
                        \u001B[93mПосле победы над Духом Леса свет медленно возвращается в Лихолесье.
                        Тьма рассеялась, и лес снова начинает дышать свободно.\n"""
                        + mainHero.getName() + "возвращается к своему народу как герой,\n" +
                        "но понимает, что это только начало его пути как защитника леса\u001B[0m");

            }
        }
        else if (mainHero.getRassa() == Rassa.ORC) {
            EndOrc.start(mainHero);
            if (mainHero.getHealth() > 0 && mainHero.isEndGame()) {
                System.out.println(
                        "\u001B[93mПобедив могучего Эльфа, " + mainHero.getName() + " чувствует могущество, \n" +
                        "Он теперь готов вернуться и собрать остатки орочьих кланов, чтобы начать новую эпоху завоеваний. \n" +
                        "Забытая Пустошь будет первым испытанием для его армии, и  " + mainHero.getName() + "  станет её новым хозяином. \u001B[0m");
            }
        }
        else if (mainHero.getRassa().equals(Rassa.MAGICAL_BEST)) {
            new PlotMagicalBest().start(pc);
        }

    }
}
