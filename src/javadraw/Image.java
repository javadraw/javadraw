package javadraw;

import javadraw.internal.VisibleImage;

public class Image extends Renderable implements CustomRenderable {
    private final Object[] parameterValues;

    private final String path;

    public Image(Screen screen, String path, Location location, double width, double height, Color color, Color border, boolean fill, double rotation, boolean visible) {
        super(screen, location, width, height, color, border, fill, rotation, visible);
        this.parameterValues = new Object[] {screen, path, location, width, height, color, border, fill, rotation, visible};

        this.path = path;

        this.object = new VisibleImage(screen.window.controller.getImage(path), location.location, width, height, screen.canvas);

    }

    public Image(Screen screen, String path, Location location, Color color, Color border, boolean fill, double rotation, boolean visible) {
        super(screen, location, -1, -1, color, border, fill, rotation, visible);
        this.parameterValues = new Object[] {screen, path, location, color, border, fill, rotation, visible};

        this.path = path;

        this.object = new VisibleImage(screen.window.controller.getImage(path), location.location, screen.canvas);

        // Grab the width and height of the loaded image.
        this.width = this.object.getDoubleWidth();
        this.height = this.object.getDoubleHeight();
    }

    public Image(Screen screen, String path, double x, double y) {
        this(screen, path, new Location(x, y), Color.NONE, Color.NONE, false, 0, true);
    }

    public Image(Screen screen, String path, double x, double y, double width, double height) {
        this(screen, path, new Location(x, y), width, height, Color.NONE, Color.NONE, false, 0, true);
    }

    public Image(Screen screen, String path, Location location) {
        this(screen, path, location.x(), location.y());
    }

    public Image(Screen screen, String path, Location location, double width, double height) {
        this(screen, path, location.x(), location.y(), width, height);
    }

    /**
     * Get the path registered to this Image.
     * @return a String representing the relative/absolute path originally passed to the constructor.
     */
    public String path() {
        return this.path;
    }

    @Override
    protected Object[] getParameters() {
        return this.parameterValues;
    }
}
