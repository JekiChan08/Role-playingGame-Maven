package org.example.plot.endings;

import org.example.Characters.MainHero;

//сюжет если гг человек
public class EndElf {
    public static void start(MainHero mainHero) {
        Mirkwood mirkwood = new Mirkwood();
        System.out.println("""
                \u001B[32mЗлой дух захватил лес
                Ты получил задание от своего клана: пройти через Лихолесье,
                чтобы найти источник тьмы, уничтожить его и вернуть мир в лес.
                Его путь полон опасностей и испытаний.\u001B[0m""");

        mirkwood.start(mainHero);

    }
}
