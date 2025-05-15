import java.time.LocalTime;

public abstract class ClockHand extends Clock{
    public abstract void setTime(LocalTime localTime);
    public abstract String toSvg();
}
