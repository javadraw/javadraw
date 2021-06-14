package javadraw;

import java.util.ArrayList;
import java.util.Arrays;

class SortingTest extends Window {

    public static void main(String[] args) {
        Window.open(800, 600, "Sorting");
    }

    ArrayList<Renderable> list = new ArrayList<>();

    public void start() {
        Text text = new Text(screen, "Some Text", 75, 75);

        // Initialize your shapes and add them to the list
        Rectangle box1 = new Rectangle(screen, 50, 50, 50, 50, Color.RED);
        Oval oval1 = new Oval(screen, 300, 350, 50, 50, Color.BLUE);
        Oval oval2 = new Oval(screen, 200, 250, 50, 75, Color.YELLOW);

        oval2.x(500);

        for(int i = 0; i < 10; i++) {
            list.add(new Rectangle(screen, i * 100, 400, 75, 75, Color.random()));
        }

        list.addAll(Arrays.asList(box1, oval1, oval2));



        // animate();
    }

    public void animate() {
        Location[] locations = new Location[list.size()];

        // Loop through the shapes in our list and move our shapes accordingly
        int x = 10; // Just offset so we can have it look okay
        int y = 10;

        double maxHeight = 0;
        for(int i = 0; i < list.size(); i++) {
            Renderable shape = list.get(i);

            if(shape.height() > maxHeight) {
                maxHeight = shape.height();
            }

            if(x + shape.width() > screen.width() - 10) {
                y += maxHeight + 10;
                x = 10;
            }

            // shape.moveTo(x, y);
            locations[i] = new Location(x, y);

            x += shape.width() + 10;
        }

        // Quick little Linear Animation
        int count = 0;
        int maxCount = 6000;

        double fps = 120;
        while(count < maxCount) {
            for(int i = 0; i < list.size(); i++) {
                Renderable shape = list.get(i);
                Location location = locations[i];

                if ((int) shape.x() == (int) location.x() && (int) shape.y() == (int) location.y()) {
                    continue;
                }

                double dx = (location.x() - shape.x()) * ((double) count / maxCount);
                double dy = (location.y() - shape.y()) * ((double) count / maxCount);
                System.out.println("Moving by: " + dx + ", " + dy + "; by percentage: " + (double) count / maxCount);

                count++;

                shape.move(dx, dy);
                screen.update();
                screen.sleep(1 / fps);
            }
        }
    }

}
