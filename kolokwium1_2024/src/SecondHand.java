import java.time.LocalTime;

public class SecondHand extends ClockHand{
    int a;
    @Override
    public void setTime(LocalTime localTime) {
        a=localTime.getSecond()*6;
    }

    @Override
    public String toSvg() {
        return "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-80\" stroke=\"red\" stroke-width=\"1\" transform=\"rotate("+ a+")\"/>";
    }
}
