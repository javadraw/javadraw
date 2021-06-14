package javadraw;

class Drawing extends Window {

    public static void main(String[] args) {
        Window.open();
    }

    Location mouthLocation = new Location(400 - 50, 400);
    Oval mouth;

    public void start() {
        screen.color(Color.GRAY);

        Oval face = new Oval(screen, 100, 0, 600, 600, Color.YELLOW);

        Oval eye1 = new Oval(screen, face.x() + face.width() / 6, 200, face.width() / 7, face.height() / 6);
        Oval eye2 = new Oval(screen, (face.x() + face.width()) - (face.width() / 6) * 2, 200, face.width() / 7, face.height() / 6);

        Rectangle eyebrow1 = new Rectangle(screen, eye1.x() - eye1.width() / 2, 200 - 50, 100, 5);
        eyebrow1.rotation(-16);

        Rectangle eyebrow2 = new Rectangle(screen, eye2.x() - 25 + eye2.width() / 2, 200 - 50, 100, 5);
        eyebrow2.rotation(16);

        mouth = new Oval(screen, 400 - 50, 400, 85.71, face.height() / 5.5);

        double seconds = 0;
        double fps = 30;

        boolean running = true;
        while (running) {
            seconds += 1.0 / fps;

            if (1.9 < seconds && seconds < 2) {
                eye1.height(1);
                eye2.height(1);
            }

            if (seconds >= 2.1) {
                eye1.height(face.height() / 6);
                eye2.height(face.height() / 6);
                seconds = 0;
            }

            screen.update();
            screen.sleep(1 / fps);
        }
    }

    public void mouseMove(Location location) {
        mouth.moveTo(mouthLocation.x() + (location.x() - mouthLocation.x()) / 7,
                mouthLocation.y() + (location.y() - mouthLocation.y()) / 7);
    }

}