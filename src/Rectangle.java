public class Rectangle {
    private double width;
    private double height;
    private int id;
    private static int idGen = 1;

    public Rectangle() {
        this.width = 1.0;
        this.height = 1.0;
        this.id = idGen++;
    }

    public Rectangle(double width, double height) throws IllegalAccessException {
        this();
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) throws IllegalAccessException {
        if (width <= 0) {
            throw new IllegalAccessException("width should be > 0");
        }
        this.width = width;
    }

    public void setHeight(double height) throws IllegalAccessException {
        if (height <= 0) {
            throw new IllegalAccessException("height should be > 0");
        }
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    @Override // overriding toString means that toString already existed
    public String toString() {
       return "Rectangle id: " + id + " width: " + width + " height: " + height;
    }
}