package javadraw;

class JavaDrawTest extends Window {

    public static void main(String[] args) {
        Window.open();
    }

    Rectangle rectangle;
    Polygon polygon;
    public void start() {
        rectangle = new Rectangle(screen, 150, 150, 50, 50, Color.BLUE);
        rectangle.border(Color.BLACK);

        polygon = new Polygon(screen, 5, 300, 300, 50, 50, Color.YELLOW);
        polygon.border(Color.RED);
    }

    public void mouseDown(int button, Location location) {
        if (button == 1)
            polygon.moveTo(location);
        else if (button == 3) {
            polygon.fill(!polygon.fill());
        } else
            polygon = (Polygon) polygon.clone();
    }

    public void keyDown(Key key) {
        print(key == Key.A);
        print(key.equals(Key.A));
        print("----------");
    }

}
