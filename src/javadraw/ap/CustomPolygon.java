package javadraw.ap;

import javadraw.internal.FilledPolygon;
import javadraw.internal.FramedPolygon;

/**
 * A custom polygon with user-defined vertices.
 */
public class CustomPolygon extends Renderable implements CustomRenderable {

    private final Object[] parameterValues;

    private Location[] vertices;

    public CustomPolygon(Screen screen, Location[] vertices, Color color, Color border, boolean fill, double rotation, boolean visible) {
        super(screen, vertices[0], -1, -1, color, border, fill, rotation, visible);
        this.parameterValues = new Object[] {screen, vertices, color, border, fill, rotation, visible};

        this.calculateLocation(vertices);
        javadraw.internal.Location[] trueVertices = convertLocations(vertices);

        this.vertices = vertices;

        if(fill) {
            this.object = new FilledPolygon(trueVertices, location.getX(), location.getY(), rotation, color.toAWT(), screen.canvas);
        } else {
            this.object = new FramedPolygon(trueVertices, location.getX(), location.getY(), rotation, color.toAWT(), screen.canvas);
        }

        this.setVisible(visible);
    }

    public CustomPolygon(Screen screen, Location[] vertices, Color color, double rotation) {
        this(screen, vertices, color, Color.NONE, true, rotation, true);
    }

    public CustomPolygon(Screen screen, Location[] vertices, double rotation) {
        this(screen, vertices, new Color("BLACK"), rotation);
    }

    public CustomPolygon(Screen screen, Location[] vertices, Color color) {
        this(screen, vertices, color, 0);
    }

    public CustomPolygon(Screen screen, Location[] vertices) {
        this(screen, vertices, new Color("BLACK"));
    }

    private void calculateLocation(Location[] vertices) {
        double xMin = vertices[0].getX();
        double xMax = vertices[0].getX();
        double yMin = vertices[0].getY();
        double yMax = vertices[0].getY();


        for (Location loc : vertices) {
            if (loc.getX() < xMin)
                xMin = loc.getX();

            if(loc.getX() > xMax)
                xMax = loc.getX();

            if (loc.getY() < yMin)
                yMin = loc.getY();

            if (loc.getY() > yMax)
                yMax = loc.getY();
        }

        this.location = new Location(xMin, yMin);
        this.width = xMax - xMin;
        this.height = yMax - yMin;
    }

    private javadraw.internal.Location[] convertLocations(Location[] locations) {
        javadraw.internal.Location[] newLocations = new javadraw.internal.Location[locations.length];
        for(int i = 0; i < newLocations.length; i++) {
            newLocations[i] = locations[i].location;
        }

        return newLocations;
    }

    @Override
    protected Object[] getParameters() {
        return parameterValues;
    }
}
