public class Point {
    public double x;
    public double y;

    public String toString(){
        return "x=" + x + " y=" + y;
    }
    public String toSvg(){
        return "<svg height=\"100\" width=\"100\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                "  <circle r=\"45\" cx=\"" + x +"\" cy=\"" + y+ "\" fill=\"red\" />\n" +
                "</svg>";
    }
    public void translate(double dx, double dy)
    {
        x+=dx;
        y+=dy;
    }
    public Point translated(double dx,double dy)
    {
        Point d=new Point();
        d.x=x+dx;
        d.y=y+dy;
        return d;
    }







}