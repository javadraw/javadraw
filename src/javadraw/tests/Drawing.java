package javadraw.tests;

import javadraw.ap.*;

class Drawing extends Window {

    public static void main(String[] args) {
        Window.open();
    }

    Location mouthLocation = new Location(400 - 50, 400);
    Oval mouth;

    public void start() {
        screen.setColor(Color.GRAY);

        Oval face = new Oval(screen, 100, 0, 600, 600, Color.YELLOW);

        Oval eye1 = new Oval(screen, face.getX() + face.getWidth() / 6, 200, face.getWidth() / 7, face.getHeight() / 6);
        Oval eye2 = new Oval(screen, (face.getX() + face.getWidth()) - (face.getWidth() / 6) * 2, 200, face.getWidth() / 7, face.getHeight() / 6);

        Rectangle eyebrow1 = new Rectangle(screen, eye1.getX() - eye1.getWidth() / 2, 200 - 50, 100, 5);
        eyebrow1.setRotation(-16);

        Rectangle eyebrow2 = new Rectangle(screen, eye2.getX() - 25 + eye2.getWidth() / 2, 200 - 50, 100, 5);
        eyebrow2.setRotation(16);

        mouth = new Oval(screen, 400 - 50, 400, 85.71, face.getHeight() / 5.5);

        double seconds = 0;
        double fps = 30;

        boolean running = true;
        while (running) {
            seconds += 1.0 / fps;

            if (1.9 < seconds && seconds < 2) {
                eye1.setHeight(1);
                eye2.setHeight(1);
            }

            if (seconds >= 2.1) {
                eye1.setHeight(face.getHeight() / 6);
                eye2.setHeight(face.getHeight() / 6);
                seconds = 0;
            }

            screen.update();
            screen.sleep(1 / fps);
        }
    }

    public void mouseMove(Location location) {
        mouth.moveTo(mouthLocation.getX() + (location.getX() - mouthLocation.getX()) / 7,
                mouthLocation.getY() + (location.getY() - mouthLocation.getY()) / 7);
    }

}