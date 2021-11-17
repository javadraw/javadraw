package javadraw.tests;

import javadraw.*;

public class SliderTest extends Window {
    private Slider slider;
    private Text text;

    public static void main(String[] args) {
        Window.open();
    }

    @Override
    public void start() {
        slider = new Slider(screen, screen.center(), Color.GREEN, 4, 100, 32, 200);
        text = new Text(screen, slider.getInt(), 10, 0);
        text.size(60);
        while (true) {
            screen.update();
            screen.sleep(1. / 30);
        }
    }

    @Override
    public void mouseDown(Location loc, int btn) {
        slider.mouseDown(loc, btn);
        if (!slider.isSelected()) {
            // test method when click
//            slider.width(btn == 1 ? slider.width() + 50 : slider.width() - 50);
//            System.out.println(slider.getValue());
        }
    }

    @Override
    public void mouseDrag(Location loc, int btn) {
        slider.mouseDrag(loc);
        text.text(String.valueOf(slider.getInt()));
    }

    @Override
    public void mouseUp(Location loc, int btn) {
        slider.mouseUp(btn);
    }
}
