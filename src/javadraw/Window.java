package javadraw;

import javadraw.internal.WindowController;
import javadraw.internal.WindowThread;

public class Window {

    WindowThread thread;
    WindowController controller;
    protected Screen screen;

    public static Window open(int width, int height, String title) {
        try {
            Class<?> clazz = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());

            Window window = (Window) clazz.newInstance();
            window._open(width, height, title);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Window open(int width, int height) {
        try {
            Class<?> clazz = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());

            Window window = (Window) clazz.newInstance();
            window._open(width, height, "javaDraw");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Window open() {
        try {
            Class<?> clazz = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());

            Window window = (Window) clazz.newInstance();
            window._open(800, 600, "javaDraw");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Window() {
        this.thread = new WindowThread() {
            @Override
            public void run() {
                Window.this.start();
                Window.this.screen.update();
            }
        };

        this.controller = new WindowController() {
            @Override
            public void begin() {
                Window.this.thread.start();
            }

            @Override
            public void onMousePress(int button, javadraw.internal.Location point) {
                Window.this.mouseDown(button, new Location(point));
            }

            @Override
            public void onMouseRelease(int button, javadraw.internal.Location point) {
                Window.this.mouseUp(button, new Location(point));
            }

            @Override
            public void onMouseClick(int button, javadraw.internal.Location point) {
            }

            @Override
            public void onMouseDrag(int button, javadraw.internal.Location point) {
                Window.this.mouseDrag(button, new Location(point));
            }

            @Override
            public void onMouseMove(int button, javadraw.internal.Location point) {
                Window.this.mouseMove(new Location(point));
            }

            @Override
            public void onMouseEnter(int button, javadraw.internal.Location point) {
            }

            @Override
            public void onMouseExit(int button, javadraw.internal.Location point) {
            }

            public void onKeyPress(String keyChar) {
                Key key = Key.fromChar(keyChar); // Key key = new Key(keyChar);
                Window.this.keyDown(key);
            }

            public void onKeyRelease(String keyChar) {
                Key key = Key.fromChar(keyChar); // Key key = new Key(keyChar);
                Window.this.keyUp(key);
            }
        };

        this.screen = new Screen(this, controller.getCanvas());
    }

    public void _open(int width, int height, String title) {
        this.controller.startController(width, height, title);
    }

    /**
     * Print to the console, the easy way. Objects passed in are by default separated by spaces.
     * @param objects the objects to print, ex. print("some", "words", "are", "cool");
     */
    public void print(Object... objects) {
        StringBuilder builder = new StringBuilder();
        for(Object object : objects) {
            builder.append(object);
            builder.append(" ");
        }

        System.out.println(builder);
    }

    // ----- EVENTS ----- //

    /**
     * MouseDown event, called when a button on the mouse is pressed
     * @param button the button that was pressed (1-3), 1 for left, 2 middle, 3 right.
     * @param location the Location of the Mouse on the Screen when the event occurred.
     */
    public void mouseDown(int button, Location location) {

    }

    /**
     * MouseUp event, called when a button on the mouse is released
     * @param button the button that was released (1-3), 1 for left, 2 middle, 3 right.
     * @param location the Location of the Mouse on the Screen when the event occurred.
     */
    public void mouseUp(int button, Location location) {

    }

    /**
     * MouseDrag event, called when a button on the mouse is pressed and held while the mouse moves.
     * @param button the button that was held (1-3), 1 for left, 2 middle, 3 right.
     * @param location the Location of the Mouse on the Screen when the event occur(s).
     */
    public void mouseDrag(int button, Location location) {

    }

    /**
     * MouseMove event, called when the mouse moves on the Screen
     * @param location the Location of the Mouse on the Screen when the event occurred.
     */
    public void mouseMove(Location location) {

    }

    /**
     * KeyDown event, called when a key on the keyboard is pressed and the window is in focus.
     * @param key the Key that was pressed.
     */
    public void keyDown(Key key) {

    }

    /**
     * KeyDown event, called when a key on the keyboard is released and the window is in focus.
     * @param key the Key that was released.
     */
    public void keyUp(Key key) {

    }

    /**
     * Called when the Screen is ready to draw.
     */
    public void start() {

    }

    /**
     * @deprecated does nothing at the moment.
     */
    public void exit() {

    }

}
