package javadraw;

import javadraw.internal.Drawable2DInterface;
import javadraw.internal.ObjectDrawShape;
import javadraw.internal.Resizable2DInterface;
import javadraw.internal.SneakyThrow;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.lang.reflect.Constructor;
import java.util.*;

public abstract class Renderable implements DrawableObject {

    private static final Map<Class<?>, Class<?>> wrapperToPrimitive = new HashMap<>();
    static {
        wrapperToPrimitive.put(Void.class, void.class);
        wrapperToPrimitive.put(Boolean.class, boolean.class);
        wrapperToPrimitive.put(Byte.class, byte.class);
        wrapperToPrimitive.put(Character.class, char.class);
        wrapperToPrimitive.put(Short.class, short.class);
        wrapperToPrimitive.put(Integer.class, int.class);
        wrapperToPrimitive.put(Long.class, long.class);
        wrapperToPrimitive.put(Float.class, float.class);
        wrapperToPrimitive.put(Double.class, double.class);
    }

    protected Screen screen;
    protected Location location;

    protected double width, height, angle;

    protected Color color;
    protected Color borderColor;

    private Border border;

    protected boolean fill, visible;

    protected Drawable2DInterface object;

    public Renderable(Screen screen, Location location,
                      double width, double height,
                      Color color, Color borderColor, boolean fill, double rotation, boolean visible) {
        this.screen = screen;
        this.location = location;

        this.width = width;
        this.height = height;

        this.angle = rotation;

        this.color = color;
        this.borderColor = borderColor;

        if(borderColor != null && borderColor != Color.NONE) {
            this.border = new Border(this, borderColor, 1);
        }

        this.screen.add(this);

        this.fill = fill;
        this.visible = visible;
    }

    /**
     * Get the x-axis position
     * @return a double
     */
    public double x() {
        return location.x();
    }

    /**
     * Set the x-axis position
     * @param x the new x-value to set
     * @return the new x-value
     */
    double x(double x) {
        location.x(x);

        this.moveTo(location);

        return location.x();
    }

    /**
     * Get the y-axis position
     * @return a double
     */
    public double y() {
        return location.y();
    }

    /**
     * Set the y-axis position
     * @param y the new y-value to set
     * @return the new y-value
     */
    public double y(double y) {
        location.y(y);

        this.moveTo(location);

        return location.y();
    }

    /**
     * Get the location of the Renderable
     * @return a Location
     */
    public Location location() {
        return this.location;
    }

    /**
     * Get the centroid/center of the Renderable
     * @return a Location representing the centroid of the Renderable
     */
    public Location center() {
        double sumX = 0;
        double sumY = 0;

        for(int i = 0; i < this.vertices().length; i++) {
            double x = this.vertices()[i].x();
            double y = this.vertices()[i].y();

            sumX += x;
            sumY += y;
        }


        double centroid_x = sumX / this.vertices().length;
        double centroid_y = sumY / this.vertices().length;

        return new Location(centroid_x, centroid_y);
    }

    /**
     * Move the Renderable to a new position by repositioning its center.
     * @param newCenter the new Center Location to move to
     * @return the new center Location
     */
    public Location center(Location newCenter) {
        this.moveTo(newCenter);
        this.move(-this.width() / 2, -this.height() / 2);
        return newCenter;
    }

    /**
     * Move the Renderable to a new position by repositioning its center.
     * @param x the new x-value to move the center to
     * @param y the new y-value to move the center to
     * @return the new center Location
     */
    public Location center(double x, double y) {
        Location newCenter = new Location(x, y);

        this.moveTo(newCenter);
        this.move(-this.width() / 2, -this.height() / 2);

        return newCenter;
    }

    /**
     * Move the object by a specific distance on both the x and y axes
     * @param dx the distance along the x-axis to move
     * @param dy the distance along the y-axis to move
     */
    public void move(double dx, double dy) {
        this.location.move(dx, dy);
        this.object.move(dx, dy);
        updateBorder();
    }

    /**
     * Move by a passed Location's x and y values. (NOTE: Uses a Location's x and y values as dx and dy values).
     * @param location the Location to pull dx and dy from
     */
    public void move(Location location) {
        this.location.move(location.x(), location.y());
        this.object.move(location.x(), location.y());
        updateBorder();
    }

    /**
     * Move the Renderable to a new Location.
     * @param x the new position along the x-axis
     * @param y the new position along the y-axis
     */
    public void moveTo(double x, double y) {
        this.location.moveTo(x, y);
        this.object.moveTo(x, y);
        updateBorder();
    }

    /**
     * Move the Renderable to a new Location
     * @param location the new Location to move to
     */
    public void moveTo(Location location) {
        this.moveTo(location.x(), location.y());
        updateBorder();
    }

    /**
     * Get the width of the Renderable
     * @return a double
     */
    public double width() {
        return this.width;
    }

    /**
     * Set the width of the Renderable (if possible)
     * @param width the width to set to
     * @return the new width
     */
    public double width(double width) {
        this.width = width;
        if(this.object instanceof Resizable2DInterface) {
            ((Resizable2DInterface) this.object).setWidth(width);
        } else {
            throw new UnsupportedOperationException("Cannot resize the " + this.getClass().getSimpleName() + " class!");
        }

        updateBorder();

        return this.width;
    }

    /**
     * Get the height of the Renderable
     * @return a double
     */
    public double height() {
        return this.height;
    }

    /**
     * Set the height of the Renderable (if possible)
     * @param height the height to set to
     * @return the new height
     */
    public double height(double height) {
        this.height = height;
        if(this.object instanceof Resizable2DInterface) {
            ((Resizable2DInterface) this.object).setHeight(height);
        } else {
            throw new UnsupportedOperationException("Cannot resize the " + this.getClass().getSimpleName() + " class!");
        }

        updateBorder();

        return this.height;
    }

    /**
     * Get the rotation of the Renderable
     * @return a double (in degrees)
     */
    public double rotation() {
        return this.angle;
    }

    /**
     * Set the rotation of the Renderable
     * @param rotation the rotation to set to (in degrees)
     * @return the new rotation (in degrees)
     */
    public double rotation(double rotation) {
        this.angle = rotation;
        this.object.setRotation(rotation);

        updateBorder();

        return this.angle;
    }

    /**
     * Rotate the the Renderable by a specified amount (in degrees)
     * @param angleChange the amount to rotate (positive being clockwise and negative counter-clockwise).
     */
    public void rotate(double angleChange) {
        this.angle += angleChange;
        this.object.rotate(angleChange);
        updateBorder();
    }


    /**
     * Get the angle between the Renderable and another Location
     * @param location the Location to check
     * @return the angle (in degrees) as a double between the Renderable and the passed Location
     */
    public double angleTo(Location location) {
        double theta = Math.atan2(location.y() - this.y(), location.x() - this.x())
                - Math.toRadians(this.rotation()) + Math.PI / 2;

        return Math.toDegrees(theta);
    }

    /**
     * Get the angle between this Renderable and another
     * @param renderable the Renderable to get the angle to
     * @return the angle (in degrees) as a double between this Renderable and the passed Renderable
     */
    public double angleTo(Renderable renderable) {
        return this.angleTo(renderable.location());
    }

    /**
     * Rotate the Renderable to look towards a Location
     * @param location the Location to rotate towards
     */
    public void lookAt(Location location) {
        double angle = this.angleTo(location);
        this.rotate(angle);
    }

    /**
     * Rotate the Renderable to look towards a specified Renderable
     * @param renderable the Renderable to look towards
     */
    public void lookAt(Renderable renderable) {
        this.lookAt(renderable.location());
    }

    /**
     * Get the Color of the Renderable
     * @return a Color
     */
    public Color color() {
        return this.color;
    }

    /**
     * Set the Color of the Renderable
     * @param color the new Color to set to
     * @return the new Color
     */
    public Color color(Color color) {
        this.color = color;
        this.object.setColor(color.toAWT());

        return this.color;
    }

    /**
     * Get the Border Color of the Renderable
     * @return a Color
     */
    public Color border() {
        return this.borderColor;
    }

    /**
     * Set the Color of the Renderable's Border
     * @param color the Color to set to
     * @return the new Border Color value
     */
    public Color border(Color color) {
        this.borderColor = color;

        this.updateBorder(color);

        return this.borderColor;
    }

    /**
     * Set the Color of the Renderable's Border and set the fill value
     * @param color the Color to set
     * @param fill the new fill value
     * @return the new Border Color
     */
    public Color border(Color color, boolean fill) {
        this.borderColor = color;
        this.fill(fill);

        this.updateBorder(color);

        return this.borderColor;
    }

    /**
     * Get the fill value of the Renderable
     * @return whether or not the Renderable has fill (Filled vs Framed)
     */
    public boolean fill() {
        return this.fill;
    }

    /**
     * Set the fill value of the Renderable
     * @param fill (boolean) the new fill value
     * @return the new fill value
     */
    public boolean fill(boolean fill) {
        this.fill = fill;
        ((ObjectDrawShape) this.object).setFilled(fill);

        return this.fill;
    }

    /**
     * Bring the Renderable to the front of the z-axis
     */
    public void front() {
        this.object.sendToFront();
    }

    /**
     * Send the Renderable to the back of the z-axis
     */
    public void back() {
        this.object.sendToBack();
    }

    /**
     * Get the visibility value of the Renderable
     * @return whether or not the Renderable is visible
     */
    public boolean visible() {
        return this.visible;
    }

    /**
     * Set the visibility value of the Renderable
     * @param visible the visibility value
     * @return the new visibility value
     */
    public boolean visible(boolean visible) {
        this.visible = visible;

        if(visible) {
            this.object.show();
        } else {
            this.object.hide();
        }

        return this.visible;
    }

    /**
     * Remove the Renderable from the Screen
     */
    public void remove() {
        this.screen.remove(this);
        this.object.removeFromCanvas();
    }

    /**
     * Get the vertices of the Renderable
     * @return an array of Locations representing the vertices.
     */
    public Location[] vertices() {
        AffineTransform transform = AffineTransform.getRotateInstance(this.rotation(),
                this.x() + this.width() / 2, this.y() + this.height() / 2);

        PathIterator iterator = this.object.getShape().getPathIterator(transform);

        return compileLocations(iterator);
    }

    protected static Location[] compileLocations(PathIterator iterator) {
        List<Location> list = new ArrayList<Location>();
        while(!iterator.isDone()) {
            double[] coords = new double[2];
            iterator.currentSegment(coords);
            iterator.next();

            list.add(new Location(coords[0], coords[1]));
        }

        return list.toArray(new Location[] {});
    }

    /**
     * Get whether or not a Location is contained within the Renderable
     * @param location the Location to check
     * @return whether or not the passed Location is contained within the Renderable
     */
    public boolean contains(Location location) {
        return this.object.contains(location.location);
    }

    /**
     * Get whether or not this Renderable is overlapping with another
     * @param renderable the Renderable to check
     * @return whether or not the passed Renderable is overlapping with the Renderable
     */
    public boolean overlaps(Renderable renderable) {
        return this.object.overlaps(renderable.object);
    }

    protected void updateBorder(Color color) {
        if(this.border == null)
            this.border = new Border(this, color, 1);
        else
            this.border.update(color);
    }

    protected void updateBorder() {
        if(this.border != null) {
            this.border.update();
        }
    }

    /**
     * A utility method meant to be overridden to allow for simple cloning
     * @return a simple Tuple with the first object being parameter types and the second being the values.
     */
    protected abstract Object[] getParameters();

    /**
     * Clone the Renderable.
     * @return a new, cloned, Renderable
     */
    public Renderable clone() {
        Class<? extends Renderable> clazz = this.getClass();
        Renderable renderable = null;

        try {
            Class[] parameterTypes = Arrays.stream(this.getParameters()).map(
                    object -> {
                        if(wrapperToPrimitive.containsKey(object.getClass())) {
                            return wrapperToPrimitive.get(object.getClass());
                        } else return object.getClass();
                    }
            ).toArray(Class[]::new);

            Constructor constructor = clazz.getDeclaredConstructor(parameterTypes);
            renderable = (Renderable) constructor.newInstance(this.getParameters());
        } catch (Exception e) {
            SneakyThrow.sneakyThrow(e);
        }

        if(renderable == null) {
            throw new RuntimeException("Something happened while trying to execute Renderable.clone()!");
        }

        renderable.border(this.borderColor);

        return renderable;
    }

    /**
     * Get the Transform (width, height, and angle) of the Renderable
     * @return the Transform
     */
    public Transform transform() {
        return new Transform(this.width, this.height, this.angle);
    }

    /**
     * Set the Transform (width, height, and angle) of the Renderable
     * @param transform the Transform to set to
     * @return the new Transform
     */
    public Transform transform(Transform transform) {
        this.width(transform.width);
        this.height(transform.height);
        this.rotation(transform.angle);

        return this.transform();
    }

    /**
     * Set the transform (width, height, and angle) of the Renderable
     * @param width the width to set to
     * @param height the height to set to
     * @param angle the angle to set to
     * @return the new Transform created by the passed values.
     */
    public Transform transform(double width, double height, double angle) {
        return this.transform(new Transform(width, height, angle));
    }

}
