package javadraw.tests;

import javadraw.*;
import javadraw.internal.ActiveObject;

public class Tester extends Window {

    public static void main(String[] args) {
        Window.open(800, 600);
    }

    Rectangle rectangle;
    Triangle triangle;

    static class Funny extends ActiveObject {

        private Screen screen;
        private Rectangle bob;

        public Funny(Screen screen) {
            this.screen = screen;
            this.bob = new Rectangle(screen, 350, 350, 50, 50, Color.BLUE);
            start();
        }

        public void run() {
            while(true) {
                pause(1000);
                bob.rotate(45);
            }
        }

    }
    
    public void start() {
        print("Started, I guess!");

        rectangle = new Rectangle(screen, 50, 50, 50, 50);
        rectangle.height(300);
        triangle = new Triangle(screen, 150, 150, 50, 50, Color.BLUE);
        Polygon polygon = new Polygon(screen, 5, 250, 250, 50, 50);
        new Funny(screen);

        run();
    }

    public void run() {
        double fps = 360;
        boolean running = true;
        while(running) {
            rectangle.rotate(1);
            screen.update();
            screen.sleep(1 / fps);
        }
    }

    public void animate() {

    }

    public void mouseDown(int button, Location location) {
        print("Mousedown!", "Contains: " + rectangle.contains(location));

        rectangle.moveTo(location);
    }

    public void keyDown(Key key) {
        if(key.equals("F7")) {
            print("F press");
        }
    }

}
