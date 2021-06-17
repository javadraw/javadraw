package javadraw;

import java.awt.*;

/**
 * A class designed to store locations on the Cartesian plane. X-axis is left to right, and y-axis is top to bottom.
 */
public class Location {

    protected javadraw.internal.Location location;

    /**
     * Create a new Location using an x and y
     * @param x x-axis value
     * @param y y-axis value
     */
    public Location(double x, double y) {
        this.location = new javadraw.internal.Location(x, y);
    }

    protected Location(javadraw.internal.Location location) {
        this.location = location;
    }

    protected Location(Point point) {
        this.location = new javadraw.internal.Location(point.getX(), point.getY());
    }

    /**
     * Get the x-axis value of the Location
     * @return a double
     */
    public double x() {
        return this.location.getDoubleX();
    }

    /**
     * Set the x-axis value of the Location
     * @param x the new x-value to set
     * @return the new x value
     */
    public double x(double x) {
        this.location = new javadraw.internal.Location(x, this.y());
        return this.location.getDoubleX();
    }

    /**
     * Get the y-axis value of the Location
     * @return a double
     */
    public double y() {
        return this.location.getDoubleY();
    }

    /**
     * Set the y-axis value of the Location
     * @param y the new y-value to set
     * @return the new y value
     */
    public double y(double y) {
        this.location = new javadraw.internal.Location(this.x(), y);
        return this.location.getDoubleY();
    }

    /**
     * Move the Location by a specified distance on the x and y axes.
     * @param dx the distance on the x-axis to move
     * @param dy the distance on the y-axis to move
     */
    public void move(double dx, double dy) {
        this.location = new javadraw.internal.Location(this.location.getDoubleX() + dx,
                                                        this.location.getDoubleY() + dy);
    }

    /**
     * Move the Location to a new position altogether
     * @param x the new x-value
     * @param y the new y-value
     */
    public void moveTo(double x, double y) {
        this.location = new javadraw.internal.Location(x, y);
    }

    /**
     * Move the Location to a new position altogether
     * @param location the new Location to move to
     */
    public void moveTo(Location location) {
        this.location = new javadraw.internal.Location(location.x(), location.y());
    }

    /**
     * Get the distance between this Location and another point
     * @param x the x-coordinate of the position to check
     * @param y the y-coordinate of the position to check
     * @return the distance between this Location and the one passed.
     */
    public double distance(double x, double y) {
        return Math.sqrt(Math.pow((x - this.x()), 2) + Math.pow((y - this.y()), 2));
    }

    /**
     * Get the distance between this Location and another point
     * @param location the Location to check
     * @return thet distance between this Location and the one passed.
     */
    public double distance(Location location) {
        return this.distance(location.x(), location.y());
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x() + ", " +
                "y=" + y() +
                '}';
    }
}
