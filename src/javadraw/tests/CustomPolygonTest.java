package javadraw.tests;

import javadraw.Color;
import javadraw.CustomPolygon;
import javadraw.Location;
import javadraw.Window;

public class CustomPolygonTest extends Window {

    public static void main(String[] args) {
        Window.open();
    }

    public void start() {
        CustomPolygon customPolygon = new CustomPolygon(screen, new Location[] { new Location(100, 100), new Location(200, 100), new Location(250, 250), new Location(150, 200) }, new Color("black"));
    }

}
