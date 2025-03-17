
public class Polygon extends Shape{
    private final Point[] vertices;

    public static Polygon square(Segment diagonal, Style style){
        Segment[] perpendiculars = diagonal.perpendicularSegments(
                diagonal.getCenter(), diagonal.length()/2
        );
        return new Polygon(new Point[]{
                diagonal.getA(),
                perpendiculars[0].getB(),
                diagonal.getB(),
                perpendiculars[1].getB()
        }, style);
    }

    public Polygon(Point[] vertices) {
        this(vertices, new Style("none", "black", 1));
    }

    public Polygon(Polygon other) {
        this(other.vertices, other.style);
    }

    public Polygon(Point[] vertices, Style style){
        super(style);
        this.vertices = new Point[vertices.length];
        for(int i=0; i< vertices.length; i++){
            this.vertices[i] = new Point(vertices[i]);
        }

    }

    public void setPoint(int ix, int x, int y){
        vertices[ix].setX(x);
        vertices[ix].setY(y);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Point p : vertices){
            s.append(p.getX()).append(",").append(p.getY()).append(" ");
        }
        return s.toString().trim();
    }

    @Override
    public String toSvg(){
        return "<polygon points=\""
                + this
                +"\" "+style.toSvg()+" />";
    }

    @Override
    public BoundingBox boundingBox(){
        if(vertices.length == 0){
            return new BoundingBox(0,0,0,0);
        }
        double minX = vertices[0].getX();
        double maxX = vertices[0].getX();
        double minY = vertices[0].getY();
        double maxY = vertices[0].getY();
        for(int i=1; i < vertices.length; i++){
            if(minX > vertices[i].getX()) minX = vertices[i].getX();
            if(maxX < vertices[i].getX()) maxX = vertices[i].getX();
            if(minY > vertices[i].getY()) minY = vertices[i].getY();
            if(maxY < vertices[i].getY()) maxY = vertices[i].getY();
        }
        return new BoundingBox(minX, minY, maxX-minX, maxY-minY);
    }
}