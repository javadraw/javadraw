package javadraw;

import javadraw.internal.FilledPolygon;
import javadraw.internal.FramedPolygon;

/**
 * A regular polygon with a specified "n" number of sides.
 */
public class Polygon extends Renderable {

    private final Object[] parameterValues;

    private final int numSides;

    public Polygon(Screen screen, int numSides, Location location, double width, double height, Color color, Color border, boolean fill, double rotation, boolean visible) {
        super(screen, location, width, height, color, border, fill, rotation, visible);
        this.parameterValues = new Object[] {screen, numSides, location, width, height, color, border, fill, rotation, visible};

        this.numSides = numSides;

        if(fill) {
            this.object = new FilledPolygon(numSides, location.x(), location.y(), width, height, rotation, color.toAWT(), screen.canvas);
        } else {
            this.object = new FramedPolygon(numSides, location.x(), location.y(), width, height, rotation, color.toAWT(), screen.canvas);
        }

        this.visible(visible);
    }

    public Polygon(Screen screen, int numSides, double x, double y, double width, double height, Color color, Color border, boolean fill, double rotation, boolean visible) {
        this(screen, numSides, new Location(x, y), width, height, color, border, fill, rotation, visible);
    }

    public Polygon(Screen screen, int numSides, Location start, Location end, Color color, Color border, boolean fill, double rotation, boolean visible) {
        this(screen, numSides, start, end.x() - start.x(), end.y() - start.y(), color, border, fill, rotation, visible);
    }

    public Polygon(Screen screen, int numSides, double x, double y, double width, double height, Color color, double rotation) {
        this(screen, numSides, x, y, width, height, color, Color.NONE, true, rotation, true);
    }

    public Polygon(Screen screen, int numSides, double x, double y, double width, double height, double rotation) {
        this(screen, numSides, x, y, width, height, new Color("BLACK"), rotation);
    }

    public Polygon(Screen screen, int numSides, double x, double y, double width, double height, Color color) {
        this(screen, numSides, x, y, width, height, color, 0);
    }

    public Polygon(Screen screen, int numSides, double x, double y, double width, double height) {
        this(screen, numSides, x, y, width, height, new Color("BLACK"));
    }

    public Polygon(Screen screen, int numSides, Location location, double width, double height, Color color, double rotation) {
        this(screen, numSides, location.x(), location.y(), width, height, color, rotation);
    }

    public Polygon(Screen screen, int numSides, Location location, double width, double height, Color color) {
        this(screen, numSides, location, width, height, color, 0);
    }

    public Polygon(Screen screen, int numSides, Location location, double width, double height, double rotation) {
        this(screen, numSides, location, width, height, new Color("BLACK"), rotation);
    }

    public Polygon(Screen screen, int numSides, Location location, double width, double height) {
        this(screen, numSides, location, width, height, new Color("BLACK"), 0);
    }

    /**
     * Get the number of sides that the Polygon was initialized with
     * @return an integer
     */
    public int numSides() {
        return this.numSides;
    }

    @Override
    protected Object[] getParameters() {
        return this.parameterValues;
    }
}
