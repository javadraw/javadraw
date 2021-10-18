package javadraw.ap;

import javadraw.internal.WindowController;
import javadraw.internal.WindowThread;

import java.lang.reflect.Constructor;

/**
 * The primary thread-controller for the application, the Window passes inputs and is meant to be the parent of any javaDraw program.
 */
public class Window {

    WindowThread thread;
    WindowController controller;
    protected Screen screen;

    // Default this to true.
    boolean oldInput = true;

    /**
     * Open the Window with a specified width, height and title
     * @param width in pixels
     * @param height in pixels
     * @param title shown in the titlebar
     * @return the Window opened
     */
    public static Window open(int width, int height, String title) {
        try {
            Class<?> clazz = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());

            Window window = (Window) clazz.newInstance();
            window._open(width, height, title);

            return window;
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("Class must be public in order to use javaDraw!");
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Open the Window with a specified width and height and the default title: "javaDraw"
     * @param width in pixels
     * @param height in pixels
     * @return the Window opened
     */
    public static Window open(int width, int height) {
        try {
            Class<?> clazz = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());

            Window window = (Window) clazz.newInstance();
            window._open(width, height, "javaDraw");

            return window;
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("Class must be public in order to use javaDraw!");
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Open the Window with the default width, height (800 x 600 pixels) and title ("javaDraw")
     * @return the Window opened
     */
    public static Window open() {
        try {
            Class<?> clazz = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());

            Window window = (Window) clazz.newInstance();
            window._open(800, 600, "javaDraw");
            return window;
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("Class must be public in order to use javaDraw!");
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Open the Window with a specified width, height and title
     * @param window the Window instance to open
     * @param width in pixels
     * @param height in pixels
     * @param title shown in the titlebar
     * @return the Window opened
     */
    public static Window open(Window window, int width, int height, String title) {
        window._open(width, height, title);
        return window;
    }

    /**
     * Open the Window with a specified width and height and the default title: "javaDraw"
     * @param window the Window instance to open
     * @param width in pixels
     * @param height in pixels
     * @return the Window opened
     */
    public static Window open(Window window, int width, int height) {
        window._open(width, height, "javaDraw");
        return window;
    }

    /**
     * Open the Window with the default width, height (800 x 600 pixels) and title ("javaDraw")
     * @param window the Window instance to open
     * @return the Window opened
     */
    public static Window open(Window window) {
        window._open(800, 600, "javaDraw");
        return window;
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
                Location location = new Location(point);

                Window.this.mouseDown(button, location);
                Window.this.mouseDown(location, button);
                Window.this.mouseDown(location);
            }

            @Override
            public void onMouseRelease(int button, javadraw.internal.Location point) {
                Location location = new Location(point);

                Window.this.mouseUp(button, location);
                Window.this.mouseUp(location, button);
                Window.this.mouseUp(location);
            }

            @Override
            public void onMouseClick(int button, javadraw.internal.Location point) {
            }

            @Override
            public void onMouseDrag(int button, javadraw.internal.Location point) {
                Location location = new Location(point);

                Window.this.mouseDrag(button, location);
                Window.this.mouseDrag(location, button);
                Window.this.mouseDrag(location);
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
     * @deprecated Please use argument order (Location, int) or (Location)
     *
     * @param button the button that was pressed (1-3), 1 for left, 2 middle, 3 right.
     * @param location the Location of the Mouse on the Screen when the event occurred.
     */
    @Deprecated
    public void mouseDown(int button, Location location) {}

    /**
     * MouseDown event, called when a button on the mouse is pressed
     * @param button the button that was pressed (1-3), 1 for left, 2 middle, 3 right.
     * @param location the Location of the Mouse on the Screen when the event occurred.
     */
    public void mouseDown(Location location, int button) {}

    /**
     * MouseDown event, called when a button on the mouse is pressed
     * @param location the Location of the Mouse on the Screen when the event occurred.
     */
    public void mouseDown(Location location) {}

    /**
     * MouseUp event, called when a button on the mouse is released
     * @deprecated Please use argument order (Location, int) or (Location)
     *
     * @param button the button that was released (1-3), 1 for left, 2 middle, 3 right.
     * @param location the Location of the Mouse on the Screen when the event occurred.
     */
    @Deprecated
    public void mouseUp(int button, Location location) {}

    /**
     * MouseUp event, called when a button on the mouse is released
     * @param button the button that was released (1-3), 1 for left, 2 middle, 3 right.
     * @param location the Location of the Mouse on the Screen when the event occurred.
     */
    public void mouseUp(Location location, int button) {}

    /**
     * MouseUp event, called when a button on the mouse is released
     * @param location the Location of the Mouse on the Screen when the event occurred.
     */
    public void mouseUp(Location location) {}

    /**
     * MouseDrag event, called when a button on the mouse is pressed and held while the mouse moves.
     * @deprecated Please use argument order (Location, int) or (Location)
     *
     * @param button the button that was held (1-3), 1 for left, 2 middle, 3 right.
     * @param location the Location of the Mouse on the Screen when the event occur(s).
     */
    @Deprecated
    public void mouseDrag(int button, Location location) {}

    /**
     * MouseDrag event, called when a button on the mouse is pressed and held while the mouse moves.
     * @param button the button that was held (1-3), 1 for left, 2 middle, 3 right.
     * @param location the Location of the Mouse on the Screen when the event occur(s).
     */
    public void mouseDrag(Location location, int button) {}

    /**
     * MouseDrag event, called when a button on the mouse is pressed and held while the mouse moves.
     * @param location the Location of the Mouse on the Screen when the event occur(s).
     */
    public void mouseDrag(Location location) {}

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
