public class DigitalClock extends Clock {
    public enum Clocktype { //enum <- typ wyliczeniowy
        H24, H12;
    }

    Clocktype format;


    public DigitalClock(Clocktype format, int hh, int mm, int ss, City city) {
        this.format = format;
        setTime(hh, mm, ss, city);
    }

    @Override
    public String toString() {
        if (this.format == Clocktype.H12) {
            if(getHh() > 12){
                return getHh()-12 + ":" + getMm() + ":" + getSs() + " PM";
            }
            else {
                return getHh() + ":" + getMm() + ":" + getSs() + " AM";
            }
        }
        else {
            return super.toString(); // super <- wywołanie medoty nadrzędnej (ta po której dziedziczy)
        }
    }

}

