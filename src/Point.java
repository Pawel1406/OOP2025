public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // konstruktor pusty
    public Point(){
        x = 0;
        y = 0;
    }
    // konstruktor kopiujący
    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
    }


    // getter
    public double getX() {
        return this.x;
    }
    // setter
    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }

    public String toSvg(){
        return "<circle r=\"5\" cx=\""+x+"\" cy=\""+y+"\" fill=\"black\" />";
    }

    public void translate(double dx, double dy){
        this.x += dx;
        y += dy;
    }

    public Point translated(double dx, double dy){
        Point newPoint = new Point();
        newPoint.x = x+dx;
        newPoint.y = y+dy;
        return newPoint;
    }
}