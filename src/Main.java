public class Main {
    public static void main(String[] args) {
        Point p1 = new Point();
        p1.x = 10;
        p1.y = 20;
        // System.out.println(p1.toSvg());// p1.toString()
        p1.translate(400,500);
        System.out.println(p1);
        Point p2=p1.translated(237,0);
        System.out.println(p2);
    }
}