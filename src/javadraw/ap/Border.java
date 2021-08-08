package javadraw.ap;

import javadraw.internal.DrawableStrokeInterface;
import javadraw.internal.Location;
import javadraw.internal.ObjectDrawShape;

import java.awt.*;

/**
 * Utility class to manage borders, which at the moment create secondary objects around a primary object.
 */
public class Border {

    private final Renderable parent;
    private final Color color;
    private final double width;

    private BorderObject object;

    public Border(Renderable object, Color color, double width) {
        this.parent = object;
        this.color = color;
        this.width = width;

        this.object = new BorderObject(object, color, width);
    }

    public Border(Renderable object, Color color) {
        this(object, color, 1);
    }

    public Border(Renderable object) {
        this(object, Color.BLACK);
    }

    /**
     * Update the border with a new Color
     * @param color the new Color
     */
    public void update(Color color) {
        this.object.setColor(color.toAWT());
        this.object.update();
    }

    /**
     * Update a border to reshape and move itself to the parents transform.
     */
    public void update() {
        this.object.moveTo(parent.location);
        this.object.setRotation(parent.angle);
        this.object.updateShape();
    }

    /**
     * @deprecated for internal use only
     */
    private static class BorderObject extends ObjectDrawShape implements DrawableStrokeInterface {
        private BasicStroke stroke = DEFAULT_STROKE;


        private Renderable parent;
        private Shape shape;

        public BorderObject(Renderable parent, Color color, double width) {
            super(parent.getLocation().location, false, color.toAWT(), parent.screen.canvas);

            this.parent = parent;
            this.shape = parent.object.getShape();
            setLineWidth(width);

            ready();
        }

        public void moveTo(Location location) {
            super.moveTo(location);
        }

        public void moveTo(javadraw.ap.Location location) {
            this.moveTo(location.location);
        }

        public void updateShape() {
            this.shape = this.parent.object.getShape();
            update();
        }

        /* * * * Methods from DrawableStrokeInterface * * * */

        /**
         * Return the width of this object's lines.
         * @return the width of this object's lines
         */
        public double getLineWidth() {
            return stroke.getLineWidth();
        }

        /**
         * Return the stroke used for this object's lines.
         * @return the stroke used for this object's lines
         */
        public BasicStroke getStroke() {
            return stroke;
        }

        /**
         * Set the width of this object's lines
         * @param width the new width of this object's lines
         */
        public void setLineWidth(double width) {
            stroke = new BasicStroke((float)width, stroke.getEndCap(), stroke.getLineJoin(),
                    stroke.getMiterLimit(), stroke.getDashArray(), stroke.getDashPhase());
            update();
        }

        /**
         * Set the stroke used for this object's lines
         * @param stroke the new stroke for this object's lines
         */
        public void setStroke(BasicStroke stroke) {
            this.stroke = stroke;
            update();
        }

        @Override
        public boolean contains(Location point) {
            return this.shape.contains(point.getDoubleX(), point.getDoubleY());
        }

        @Override
        public Shape makeShape() {
            return this.shape;
        }
    }

}
