import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {

            //System.out.println(czas.toString());
            Map<String,City> mapa= City.parseFile("src/strefy.csv");
            DigitalClock czas = new DigitalClock(DigitalClock.Clocktype.H24, 13, 20, 20, mapa.get("Warszawa"));
            System.out.println(mapa);
           //System.out.println(czas.toString());
            //czas.setCity(mapa.get("Kijów"));

            //System.out.println((DigitalClock) czas.localmeantime(10,0,0));
            AnalogClock czasy=new AnalogClock();
            czasy.toSvg("src/zegar1.svg");
            List<City> lista_n=new ArrayList<>();
            mapa.forEach((k,v)->lista_n.add(v));
            System.out.println(lista_n);
            AnalogClock zegarek=new AnalogClock();
            zegarek.setTime(10,10,10,lista_n.get(2));
            City.generateAnalogClockSvg(lista_n,zegarek);

            


        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        try{
            AnalogClock zegarekSvg=new AnalogClock();
            zegarekSvg.toSvg("src/zegarek.svg");
        }
        catch(FileNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
    }

}
