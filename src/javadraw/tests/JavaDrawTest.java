package javadraw.tests;

import javadraw.*;

public class JavaDrawTest extends Window {

    public static void main(String[] args) {
        Window.open();
    }

    Rectangle rectangle;
    Polygon polygon;
    public void start() {
        rectangle = new Rectangle(screen, 150, 150, 50, 50, Color.BLUE);
        rectangle.border(Color.BLACK);

        polygon = new Polygon(screen, 5, 200, 150, 50, 50, Color.YELLOW);
        polygon.border(Color.RED);

        // javadraw.internal.Triangle triangle = new javadraw.internal.Triangle(0, 0, 150, 150, true, java.awt.Color.BLACK, screen.canvas);
        Triangle triangle = new Triangle(screen, 250, 150, 50, 50, Color.RED);
        // Polygon otherPoly = new Polygon(screen, 3, 200, 150, 50, 50);
    }

    public void mouseDown(int button, Location location) {
        if (button == 1)
            polygon.moveTo(location);
        else if (button == 3) {
            polygon.fill(!polygon.fill());
        } else
            polygon = (Polygon) polygon.clone();

        print(location);
    }

    public void keyDown(Key key) {
        print(key == Key.A);
        print(key.equals(Key.A));
        print("----------");

        print(key.character());
    }

}
