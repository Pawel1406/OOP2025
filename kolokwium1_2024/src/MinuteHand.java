import java.time.LocalTime;

public class MinuteHand extends ClockHand{
    int a;
    @Override
    public void setTime(LocalTime localTime) {
        a=localTime.getMinute()*6;
    }

    @Override
    public String toSvg() {
        return "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-70\" stroke=\"blue\" stroke-width=\"1\" transform=\"rotate("+ a+")\"/>";
    }
}
