import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.get;

public class AnalogClock extends Clock {
    private final List<ClockHand> lista = new ArrayList<>();

    {
        lista.add(0, new SecondHand());
        lista.add(1, new MinuteHand());
        lista.add(2, new HoursHand());


        //lista.get(1).toSvg();
    }
    String a="Alalallala";

    public AnalogClock(Object clock10) throws FileNotFoundException {
        if(clock10 instanceof AnalogClock){
            AnalogClock clock=(AnalogClock)clock10;
            setTime(clock.getHh(), clock.getMm(), clock.getSs(), null);
        }

    }


    public AnalogClock() {}

    public void toSvg(String pathFile) throws FileNotFoundException {
        PrintWriter zapis = new PrintWriter(pathFile);
        zapis.println("<svg width=\"200\" height=\"200\" viewBox=\"-100 -100 200 200\" xmlns=\"http://www.w3.org/2000/svg\">\n"
                + "<circle cx=\"0\" cy=\"0\" r=\"90\" fill=\"none\" stroke=\"black\" stroke-width=\"2\" />\n" +
                "<g text-anchor=\"middle\">\n" +
                "<text x=\"0\" y=\"-80\" dy=\"6\">12</text>\n" +
                "<text x=\"80\" y=\"0\" dy=\"4\">3</text>\n" +
                "<text x=\"0\" y=\"80\" dy=\"6\">6</text>\n" +
                "<text x=\"-80\" y=\"0\" dy=\"4\">9</text>\n" +
                "</g>\n");


        LocalTime czas = LocalTime.of(getHh(), getMm(), getSs());
        ((SecondHand) lista.get(0)).setTime(czas);
        zapis.println(((SecondHand) lista.get(0)).toSvg());
        ((MinuteHand) lista.get(1)).setTime(czas);
        zapis.println(((MinuteHand) lista.get(1)).toSvg());
        ((HoursHand) lista.get(2)).setTime(czas);
        zapis.println(((HoursHand) lista.get(2)).toSvg());
        zapis.println("</svg>");

        zapis.close();

    }
}

