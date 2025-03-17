import java.io.FileWriter;
import java.io.IOException;

public class SvgScene {
    private final Shape[] shapes;
    private int ix;

    public SvgScene() {
        this.shapes = new Shape[3];
        ix = 0;
    }

    // korzysta z polimorfizmu
    // czyli tutaj faktu, że Polygon jest też Shape
    public void addShape(Shape shape) {
        shapes[ix] = shape;
        ix = (ix+1) % shapes.length;
    }

    public String toSvg() {
        StringBuilder sb = new StringBuilder();
        for(Shape p: shapes){
            if(p != null)
                sb.append(p.toSvg()).append("\n");
        }
        return sb.toString();
    }

    public BoundingBox boundingBox() {
        double minX = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        for (Shape p: shapes){
            if (p!=null) {
                BoundingBox bb = p.boundingBox();
                minX = Math.min(minX, bb.x());
                minY = Math.min(minY, bb.y());
                maxX = Math.max(maxX, bb.x()+bb.width());
                maxY = Math.max(maxY, bb.y()+bb.height());
            }
        }
        return new BoundingBox(minX, minY, maxX-minX, maxY-minY);
    }

    public void save(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        BoundingBox bb = boundingBox();
        writer.write("<svg width=\""+bb.width()+"\" height=\""+bb.height());
        writer.write("\" viewBox=\""+bb.x()+" "+bb.y()+" "+bb.width()+" "+bb.height()+"\" xmlns=\"http://www.w3.org/2000/svg\">");
        for(Shape p : shapes){
            writer.write(p.toSvg()+"\n");
        }
        writer.write("</svg>");
        writer.close();
    }

}