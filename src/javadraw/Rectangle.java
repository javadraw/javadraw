package javadraw;

import javadraw.internal.FilledRect;
import javadraw.internal.FramedRect;

/**
 * Represents a classical rectangular Object on the Screen.
 */
public class Rectangle extends Renderable {

    private final Object[] parameterValues;

    public Rectangle(Screen screen, Location location, double width, double height, Color color, Color border, boolean fill, double rotation, boolean visible) {
        super(screen, location, width, height, color, border, fill, rotation, visible);
        this.parameterValues = new Object[] {screen, location, width,  height, color, border, fill, rotation, visible};

        if(fill) {
            this.object = new FilledRect(location.x(), location.y(), width, height, rotation, color.toAWT(), screen.canvas);
        } else {
            this.object = new FramedRect(location.x(), location.y(), width, height, rotation, color.toAWT(), screen.canvas);
        }

        this.visible(visible);
    }

    public Rectangle(Screen screen, double x, double y, double width, double height, Color color, Color border, boolean fill, double rotation, boolean visible) {
        this(screen, new Location(x, y), width, height, color, border, fill, rotation, visible);
    }

    public Rectangle(Screen screen, Location start, Location end, Color color, Color border, boolean fill, double rotation, boolean visible) {
        this(screen, start, end.x() - start.x(), end.y() - start.y(), color, border, fill, rotation, visible);
    }

    public Rectangle(Screen screen, double x, double y, double width, double height, Color color, double rotation) {
        this(screen, x, y, width, height, color, Color.NONE, true, rotation, true);
    }

    public Rectangle(Screen screen, double x, double y, double width, double height, double rotation) {
        this(screen, x, y, width, height, new Color("BLACK"), rotation);
    }

    public Rectangle(Screen screen, double x, double y, double width, double height, Color color) {
        this(screen, x, y, width, height, color, 0);
    }

    public Rectangle(Screen screen, double x, double y, double width, double height) {
        this(screen, x, y, width, height, new Color("BLACK"));
    }

    public Rectangle(Screen screen, Location location, double width, double height, Color color, double rotation) {
        this(screen, location.x(), location.y(), width, height, color, rotation);
    }

    public Rectangle(Screen screen, Location location, double width, double height, Color color) {
        this(screen, location, width, height, color, 0);
    }

    public Rectangle(Screen screen, Location location, double width, double height, double rotation) {
        this(screen, location, width, height, new Color("BLACK"), rotation);
    }

    public Rectangle(Screen screen, Location location, double width, double height) {
        this(screen, location, width, height, new Color("BLACK"), 0);
    }

    @Override
    protected Object[] getParameters() {
        return this.parameterValues;
    }
}
