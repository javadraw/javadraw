package javadraw;

import javadraw.internal.DrawingCanvas;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Screen {

    protected DrawingCanvas canvas;
    protected Window window;

    protected Set<DrawableObject> objects = new HashSet<>();

    protected Screen(Window window, DrawingCanvas canvas) {
        this.window = window;
        this.canvas = canvas;
    }

    /**
     * Get the width of the Screen
     * @return a double
     */
    public double width() {
        return this.window.controller.getWidth();
    }

    /**
     * Get the height of the Screen
     * @return a double
     */
    public double height() {
        return this.window.controller.getHeight();
    }

    /**
     * Get the title of the Screen (Window)
     * @return a String
     */
    public String title() {
        return this.window.controller.getName();
    }

    /**
     * Get the Color of the Screen
     * @return a Color
     */
    public Color color() {
        return new Color(this.canvas.getBackground());
    }

    /**
     * Set the Color of the Screen
     * @param color the Color to set to
     * @return the new Color
     */
    public Color color(Color color) {
        this.canvas.setBackground(color.toAWT());
        return color;
    }

    /**
     * Get the center of the Screen
     * @return the center of the Screen as a Location
     */
    public Location center() {
        return new Location(this.width() / 2, this.height() / 2);
    }

    /**
     * Get the top left corner of the Screen
     * @return the top left corner of the Screen as a Location
     */
    public Location topLeft() {
        return new Location(0, 0);
    }

    /**
     * Get the top right corner of the Screen
     * @return the top right corner of the Screen as a Location
     */
    public Location topRight() {
        return new Location(this.width(), 0);
    }

    /**
     * Get the bottom left corner of the Screen
     * @return the bottom left corner of the Screen as a Location
     */
    public Location bottomLeft() {
        return new Location(0, this.height());
    }

    /**
     * Get the bottom right corner of the Screen
     * @return the bottom right corner of the Screen as a Location
     */
    public Location bottomRight() {
        return new Location(this.width(), this.height());
    }

    /**
     * Get the Location of the Mouse
     * @return the mouse Location
     */
    public Location mouse() {
        return new Location(this.window.controller.getMousePosition());
    }

    /**
     * Get all objects on the Screen
     * @return a List of DrawableObjects
     */
    public List<DrawableObject> objects() {
        return Arrays.asList(this.objects.toArray(new DrawableObject[0]));
    }

    /**
     * Get whether or not the Screen contains the passed DrawableObject
     * @param object the DrawableObject to check
     * @return a boolean
     */
    public boolean contains(DrawableObject object) {
        return this.objects.contains(object);
    }

    /**
     * Add an object to the Screen.
     */
    protected void add(DrawableObject object) {
        this.objects.add(object);
    }

    /**
     * Removes an object from the Screen's internal tracking.
     * @param object the object to remove
     */
    protected void remove(DrawableObject object) {
        this.objects.remove(object);
    }

    /**
     * Clear the Screen
     */
    public void clear() {
        this.canvas.clear();
    }

    /**
     * Cause the Screen thread to sleep for a specified duration of time in seconds
     * @param seconds time to wait in seconds
     */
    public void sleep(double seconds) {
        this.window.thread.pause(seconds * 1000);
    }

    /**
     * Update the Screen
     */
    public void update() {
        this.canvas.update();
    }



}
