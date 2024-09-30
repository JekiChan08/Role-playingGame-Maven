package org.example.plot.endings;

import org.example.Characters.MainHero;

//сюжет если гг человек
public class EndOrc {
    public static void start(MainHero mainHero) {
        Wastelands wastelands = new Wastelands();
        System.out.println("""
            \u001B[93mВы бывший воин могучего клана, который после великой войны распался. 
            Воины клана разбрелись, а Громак оказался в одиночестве.\n"""
            + mainHero.getName() + " ищет своё предназначение, жаждет вернуть себе былую славу и доказать, \n" +
            "что он достоин стать вождём нового клана.\u001B[0m");

        wastelands.start(mainHero);

    }
}
