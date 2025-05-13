import java.time.LocalTime;

public class HoursHand extends ClockHand{
    int a;
    @Override
    public void setTime(LocalTime localTime) {
        if(localTime.getHour()>=12)
        {
            a=(localTime.getHour()-12)*30;
        }
        else
        a=localTime.getHour()*6;
    }

    @Override
    public String toSvg() {
        return "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"-50\" stroke=\"white\" stroke-width=\"1\" transform=\"rotate("+ a+")\"/>";
    }
}
