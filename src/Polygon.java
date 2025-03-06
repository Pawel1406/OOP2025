import java.util.Arrays;

public class Polygon {
    private Point arr[];

    public Polygon(Point[] arr) {
        this.arr = new Point[arr.length];
        for(int i=0;i<arr.length;i++)
        {
            this.arr[i]=arr[i];
        }
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }
    public String SVG() {
        return "<Polygon>{" +
                "arr=" + Arrays.toString(arr) +
                "} </polygon>";
    }


}
