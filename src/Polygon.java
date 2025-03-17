import java.util.Arrays;

public class Polygon {
    private Point arr[];

    public Polygon(Point[] arr) {
        this.arr = new Point[arr.length];
        System.arraycopy(arr, 0, this.arr, 0, arr.length);
    }
    //ahahahhaha

    @Override
    public String toString() {
        return "Polygon{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }
    public String SVG() {
        return "<svg height=\"220\" width=\"500\" xmlns=\"http://www.w3.org/2000/svg\">"
                +"<polygon points="+Arrays.toString(arr)+"style=\"fill:lime;stroke:purple;stroke-width:3\" />"+
                "</svg>";
    }


}
