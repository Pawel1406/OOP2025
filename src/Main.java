public class Main {
    public static void main(String[] args) {
        Point p = new Point();
        p.x = 10;
        p.y = 20;
        // System.out.println(p1.toSvg());// p1.toString()
        p.translate(400,500);
        System.out.println(p);
        Point p2=p.translated(237,0);
        System.out.println(p2);
        Segment s=new Segment();
        s.p1.x=12;
        s.p1.y=13;
        s.p2.x=14;
        s.p2.y=15;
        System.out.println(s.length_o());
        Segment []arr = new Segment[10];
        double licznik=1.0;

        for(int i=0;i<arr.length;i++)
        {
            arr[i]=new Segment();
           arr[i].p1.x=1.0;
           arr[i].p1.y=2.0;
           arr[i].p2.x=licznik+2.0;
           arr[i].p2.y=licznik+3.0;
           licznik+=4.0;
        }
        Segment maxi=arr[0].maximal(arr);
        System.out.println(maxi.length_o());
    }
}