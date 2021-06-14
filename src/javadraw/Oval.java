package javadraw;

import javadraw.internal.FilledOval;
import javadraw.internal.FramedOval;

/**
 * Any circular or oval-like Object on the Screen.
 */
public class Oval extends Renderable {

    private final Object[] parameterValues;

    public Oval(Screen screen, Location location, double width, double height, Color color, Color border, boolean fill, double rotation, boolean visible) {
        super(screen, location, width, height, color, border, fill, rotation, visible);
        this.parameterValues = new Object[] {screen, location, width, height, color, border, fill, rotation, visible};

        // System.out.println("Fill: " + fill);
        if(fill) {
            // System.out.println("Filled");
            this.object = new FilledOval(location.x(), location.y(), width, height, rotation, color.toAWT(), screen.canvas);
        } else {
            // System.out.println("Framed");
            this.object = new FramedOval(location.x(), location.y(), width, height, rotation, color.toAWT(), screen.canvas);
        }

        this.visible(visible);
    }

    public Oval(Screen screen, double x, double y, double width, double height, Color color, Color border, boolean fill, double rotation, boolean visible) {
        this(screen, new Location(x, y), width, height, color, border, fill, rotation, visible);
    }

    public Oval(Screen screen, Location start, Location end, Color color, Color border, boolean fill, double rotation, boolean visible) {
        this(screen, start, end.x() - start.x(), end.y() - start.y(), color, border, fill, rotation, visible);
    }

    public Oval(Screen screen, double x, double y, double width, double height, Color color, double rotation) {
        this(screen, x, y, width, height, color, Color.NONE, true, rotation, true);
    }

    public Oval(Screen screen, double x, double y, double width, double height, double rotation) {
        this(screen, x, y, width, height, new Color("BLACK"), rotation);
    }

    public Oval(Screen screen, double x, double y, double width, double height, Color color) {
        this(screen, x, y, width, height, color, 0);
    }

    public Oval(Screen screen, double x, double y, double width, double height) {
        this(screen, x, y, width, height, new Color("BLACK"));
    }

    public Oval(Screen screen, Location location, double width, double height, Color color, double rotation) {
        this(screen, location.x(), location.y(), width, height, color, rotation);
    }

    public Oval(Screen screen, Location location, double width, double height, double rotation) {
        this(screen, location, width, height, new Color("BLACK"), rotation);
    }

    public Oval(Screen screen, Location location, double width, double height, Color color) {
        this(screen, location, width, height, color, 0);
    }

    public Oval(Screen screen, Location location, double width, double height) {
        this(screen, location, width, height, new Color("BLACK"), 0);
    }

    @Override
    protected Object[] getParameters() {
        return new Object[0];
    }
}
