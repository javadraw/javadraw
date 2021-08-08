package javadraw.ap;

import javadraw.internal.Drawable1DInterface;

/**
 * Represents a Line on the Screen. Has two positions, referred to as "pos1" and "pos2".
 */
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

    public double getX() {
        return this.getPos1().getX();
    }

    public double setX(double x) {
        this.moveTo(x, this.getPos1().getY());

        return this.getX();
    }

    public double getY() {
        return this.getPos1().getY();
    }

    public double setY(double y) {
        this.moveTo(this.getPos1().getX(), y);

        return this.getY();
    }

    public Location getLocation() {
        return new Location(this.getX(), this.getY());
    }

    /**
     * Get the first position of the Line
     * @return a Location
     */
    public Location getPos1() {
        return this.location1;
    }

    /**
     * Set the first position of the Line
     * @param pos1 the new first position to set
     * @return the new first position as a Location
     */
    public Location setPos1(Location pos1) {
        this.location1 = pos1;

        this.object.setStart(location1.location);
        return this.location1;
    }

    /**
     * Get the second position of the Line
     * @return a Location
     */
    public Location getPos2() {
        return this.location2;
    }

    /**
     * Set the second position of the Line
     * @param pos2 the new second position to set
     * @return the new second position as a Location
     */
    public Location setPos2(Location pos2) {
        this.location2 = pos2;

        this.object.setEnd(location2.location);
        return this.location2;
    }

    /**
     * Get the Color of the Line
     * @return the Color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Set the Color of the Line
     * @param color the new Color to set
     * @return the new Color
     */
    public Color setColor(Color color) {
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
        this.move(location.getX(), location.getY());
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
        this.moveTo(location.getX(), location.getY());
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
    public boolean isVisible() {
        return visible;
    }

    /**
     * Set the visibility of the Line
     * @param visible the new visibility setting (boolean)
     * @return the new visibility setting
     */
    public boolean setVisible(boolean visible) {
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
