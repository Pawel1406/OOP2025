public class Text extends Shape{
    private String text;
    private Point punkt;

    public Text(Style style) {
        super(style);
    }

    public int textLength()
    {
        return text.length();
    }
    public int fontSize(int size)
    {
        return size;
    }

    @Override
    public BoundingBox boundingBox() {
        return new BoundingBox(punkt.getX(), punkt.getY(),textLength(), fontSize(200));
    }

    @Override
    public String toSvg()
    {
        return "<svg height=\"30\" width=\"200\" xmlns=\"http://www.w3.org/2000/svg\">"+
            "<text x="+punkt.getX()+" y="+punkt.getY()+" fill=\"red\">"+text+"</text>"+
            "</svg>";
    }
}
