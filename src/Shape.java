public abstract class Shape {
    // protected daje dostęp do pola w klasach dziedziczących
    protected Style style;
    public Shape(Style style) {
        this.style = style;
    }
    // metoda abstrakcyjna, bez implementacji
    public abstract String toSvg();
    public abstract BoundingBox boundingBox();
}