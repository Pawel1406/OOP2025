import java.util.Arrays;

public class SvgScene {
    private Polygon[] poli_arr=new Polygon[3];
    public void addPolygon(Polygon []arr)
    {
        for (int i=0;i<arr.length;i++)
            poli_arr[i]=arr[i];
    }

    public String toSvg() {
        return "SvgScene{" +
                "poli_arr=" + Arrays.toString(poli_arr) +
                '}';
    }
}
