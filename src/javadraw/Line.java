package javadraw;

import javadraw.internal.Drawable1DInterface;

public class Line implements DrawableObject {

    private Screen screen;
    private Drawable1DInterface object;

    protected Location location1;
    protected Location location2;

    protected Color color = Color.BLACK;

    protected int thickness = 1;
    protected boolean visible = true;

    public Line(Screen screen, Location location1, Location location2, Color color, int thickness, boolean visible) {
        this.screen = screen;
        this.object = new javadraw.internal.Line(location1.location, location2.location, screen.canvas);

        this.location1 = location1;
        this.location2 = location2;

        this.color = color;
        this.thickness = thickness;
        this.visible = visible;

        this.object.setColor(color.toAWT());
        // TODO: Thickness

        if(visible) {
            this.object.show();
        } else {
            this.object.hide();
        }
    }

    public Line(Screen screen, Location location1, Location location2, Color color, int thickness) {
        this(screen, location1, location2, color, thickness, true);
    }

    public Line(Screen screen, Location location1, Location location2, Color color) {
        this(screen, location1, location2, color, 1);
    }

    public Line(Screen screen, Location location1, Location location2) {
        this(screen, location1, location2, Color.BLACK);
    }

    public Line(Screen screen, double x1, double y1, double x2, double y2, Color color, int thickness, boolean visible) {
        this(screen, new Location(x1, y1), new Location(x2, y2), color, thickness, visible);
    }

    public Line(Screen screen, double x1, double y1, double x2, double y2, Color color, int thickness) {
        this(screen, x1, y1, x2, y2, color, thickness, true);
    }

    public Line(Screen screen, double x1, double y1, double x2, double y2, Color color) {
        this(screen, x1, y1, x2, y2, color, 1, true);
    }

    public Line(Screen screen, double x1, double y1, double x2, double y2) {
        this(screen, x1, y1, x2, y2, Color.BLACK);
    }

    public double x() {
        return this.pos1().x();
    }

    public double x(double x) {
        this.moveTo(x, this.pos1().y());

        return this.x();
    }

    public double y() {
        return this.pos1().y();
    }

    public double y(double y) {
        this.moveTo(this.pos1().x(), y);

        return this.y();
    }

    public Location location() {
        return new Location(this.x(), this.y());
    }

    /**
     * Get the first position of the Line
     * @return a Location
     */
    public Location pos1() {
        return this.location1;
    }

    /**
     * Set the first position of the Line
     * @param pos1 the new first position to set
     * @return the new first position as a Location
     */
    public Location pos1(Location pos1) {
        this.location1 = pos1;

        this.object.setStart(location1.location);
        return this.location1;
    }

    /**
     * Get the second position of the Line
     * @return a Location
     */
    public Location pos2() {
        return this.location2;
    }

    /**
     * Set the second position of the Line
     * @param pos2 the new second position to set
     * @return the new second position as a Location
     */
    public Location pos2(Location pos2) {
        this.location2 = pos2;

        this.object.setEnd(location2.location);
        return this.location2;
    }

    /**
     * Get the Color of the Line
     * @return the Color
     */
    public Color color() {
        return this.color;
    }

    /**
     * Set the Color of the Line
     * @param color the new Color to set
     * @return the new Color
     */
    public Color color(Color color) {
        this.color = color;
        this.object.setColor(color.toAWT());

        return this.color;
    }

    /**
     * Move the line (both first and second positions) by a specified dx and dy.
     * @param dx the distance on the x-axis to move as a double
     * @param dy the distance on the y-axis to move as a double
     */
    public void move(double dx, double dy) {
        this.location1.move(dx, dy);
        this.location2.move(dx, dy);
        this.object.move(dx, dy);
    }

    /**
     * Move the line (both first and second positions) by a dx and dy pulled from a Location's x and y values.
     * @param location the Location to pull dx and dy from
     */
    public void move(Location location) {
        this.move(location.x(), location.y());
    }

    /**
     * Move the line to a new Location. Moves the first position to this Location, bringing the second position to a relative position.
     * @param x the x-position to move to as a double
     * @param y the y-position to move to as a double
     */
    public void moveTo(double x, double y) {
        this.object.moveTo(x, y);
        this.location1 = new Location(this.object.getStart().getDoubleX(), this.object.getStart().getDoubleY());
        this.location2 = new Location(this.object.getEnd().getDoubleX(), this.object.getEnd().getDoubleY());
    }

    /**
     * Move the line to a new Location. Moves the first position to this Location, bringing the second position to a relative position.
     * @param location the Location to move to
     */
    public void moveTo(Location location) {
        this.moveTo(location.x(), location.y());
    }

    /**
     * Bring the Line to the front of the z-axis
     */
    public void front() {
        this.object.sendToFront();
    }

    /**
     * Send the Line to the back of the z-axis
     */
    public void back() {
        this.object.sendToBack();
    }

    /**
     * Get the visibility of the Line
     * @return a boolean
     */
    public boolean visible() {
        return visible;
    }

    /**
     * Set the visibility of the Line
     * @param visible the new visibility setting (boolean)
     * @return the new visibility setting
     */
    public boolean visible(boolean visible) {
        if(visible) {
            this.object.show();
        } else {
            this.object.hide();
        }

        return visible;
    }

    public void remove() {
        this.object.removeFromCanvas();
        this.screen.remove(this);
    }

}
