package javadraw;

/**
 * An Object which is Drawable on the Screen (Renderables and non-Renderables).
 */
public interface DrawableObject {

    /**
     * Get the x-coordinate of the Object.
     * @return a double x-coordinate
     */
    double x();

    /**
     * Get the y-coordinate of the Object
     * @return a double y-coordinate
     */
    double y();

    /**
     * Set the x-coordinate of the Object
     * @param x the new x-coordinate to set to
     * @return the new x-coordinate
     */
    double x(double x);

    /**
     * Set thte y-coordinate of the Object
     * @param y the y-coordinate to set to
     * @return the new y-coordinate
     */
    double y(double y);

    /**
     * Get the Location of the Object
     * @return a Location
     */
    Location location();

    /**
     * Move the Object by a specified distance, (dx, dy).
     * @param dx the distance on the x-axis to move by
     * @param dy the distance on the y-axis to move by
     */
    void move(double dx, double dy);

    /**
     * Move the Object by a specified distance, (dx, dy), pulled from a Location's x and y fields.
     * @param location the Location to pull the dx and dy from.
     */
    void move(Location location);

    /**
     * Move the Object to a new Location on the Screen
     * @param x the x-coordinate to move to
     * @param y the y-coordinate to move to
     */
    void moveTo(double x, double y);

    /**
     * Move the Object to a new Location on the Screen
     * @param location the Location to move to
     */
    void moveTo(Location location);

    /**
     * Bring the Object to the front of the Screen
     */
    void front();

    /**
     * Bring the Object to the back of the Screen
     */
    void back();

    /**
     * Remove the Object from the Screen (but not from memory).
     */
    void remove();

}
