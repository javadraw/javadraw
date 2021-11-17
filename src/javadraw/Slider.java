package javadraw;

public class Slider extends Renderable {
    private final Object[] parameterValues;

    private final Rectangle slider;
    private final Rectangle filled;
    private final Oval handle;

    protected Color bgColor;
    private final double minValue;
    private final double maxValue;
    private double value;

    private boolean disabled;
    private boolean selected;

    /**
     * Returns the number bound to the given range.
     * @param value number to keep in range
     * @param min lower bound
     * @param max upper bound
     * @return the value if in range otherwise the min or max
     */
    private static double ensureRange(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Returns the percent of how close a number is to an upper bound from a lower bound.
     * @param value the number to check
     * @param min the number the checked value is coming from
     * @param max the number the checked value is headed towards
     * @return a number in range 0-1 showing how far the number is from min to max
     */
    private static double fractionOf(double value, double min, double max) {
        return (value - min) / (max - min);
    }

    public Slider(Screen screen, Location location, double width, double height, Color color, Color borderColor, boolean fill, double rotation, boolean visible, Color bgColor, double minValue, double maxValue, double startValue) {
        super(screen, location, width, height, color, null, fill, rotation, visible);
        parameterValues = new Object[] { screen, location, width, height, color, borderColor, fill, rotation, visible };

        this.borderColor = borderColor;
        this.bgColor = bgColor;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = startValue;
        this.disabled = false;

        slider = new Rectangle(screen, location.x(), location.y(), width, height, secondaryColor(), null, fill, 0, visible);
        slider.border(borderColor);
        filled = new Rectangle(screen, location.x(), location.y(), width, 0, color, null, fill, 0, visible);
        filled.border(borderColor);
        handle = new Oval(screen, location.x(), location.y(), width + 10, width + 10, color, null, fill, 0, visible);
        handle.center(slider.center()); // just to set the x, the y will be changed in the initial setValue
        handle.border(borderColor);

        setValue(startValue);
    }

    public Slider(Screen screen, Location location) {
        this(screen, location, new Color(227, 4, 37)); // https://material.io/components/sliders#theming
    }

    public Slider(Screen screen, Location location, Color color) {
        this(screen, location, color, 0, 1, .5, 100);
    }

    public Slider(Screen screen, Location location, Color color, double minValue, double maxValue, double startValue, double height) {
        this(screen, location, 5, height, color, Color.BLACK, true, 0, true, screen.color(), minValue, maxValue, startValue);
    }

    public Slider(Screen screen, double x, double y) {
        this(screen, new Location(x, y));
    }

    public Slider(Screen screen, double x, double y, Color color) {
        this(screen, new Location(x, y), color);
    }

    public Slider(Screen screen, double x, double y, Color color, double minValue, double maxValue, double startValue, double height) {
        this(screen, new Location(x, y), color, minValue, maxValue, startValue, height);
    }

    /**
     * Returns weather the slider is "disabled", making it not draggable.
     * @return weather the slider is "disabled"
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Set weather this slider should be disabled.
     * @param disabled weather the slider should be disabled
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * Returns weather the mouse has selected this slider, so when the mouse moves the slider will change.
     * @return weather the mouse has selected this slider
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Get the minimum value of the slider.
     * @return minimum value of the slider
     */
    public double getMinValue() {
        return minValue;
    }

    /**
     * Get the maximum value of the slider.
     * @return maximum value of the slider
     */
    public double getMaxValue() {
        return maxValue;
    }

    /**
     * Get the value of this slider, a double representing where the handle is on the slider.
     * @return the value of this slider
     */
    public double getValue() {
        return value;
    }

    /**
     * Get how much is let to go above the handle, so if the handle is at maximum this will be 0.
     * @return space above handle
     */
    public double getRemaining() {
        return maxValue - value;
    }

    /**
     * Get the slider's value rounded to the nearest integer.
     * @return rounded slider value
     */
    public int getInt() {
        return (int) Math.round(value);
    }

    /**
     * Get the space remaining above the slider handle rounded to the nearest integer.
     * @return rounded space above handle
     */
    public int getIntRemaining() {
        return (int) Math.round(getRemaining());
    }

    /**
     * Set the slider's value to a specific number and update the display.
     * If the entered value is not in range <code>minValue</code>-<code>maxValue</code> it will be placed at <code>minValue</code> or <code>maxValue</code>.
     * @param newValue desired value to set the slider to
     */
    public void setValue(double newValue) {
        value = ensureRange(newValue, minValue, maxValue);
        double ofSlider = fractionOf(getRemaining(), minValue, maxValue);
        handle.center(slider.center().x(), height * ofSlider + location.y());

        updateFilled();
    }

    /**
     * Adds a specified amount to add to the slider's value and updates the display.
     * This slides by value, NOT pixels.
     * @param amount how much to add to the slider value
     */
    public void slide(double amount) {
        setValue(value + amount);
    }

    /**
     * Slides to a specific y value on the slider and updates the value.
     * If y value is out of bounds it will be placed at <code>minValue</code> or <code>maxValue</code>.
     * @param y the y value to set the slider to
     */
    public void slideTo(double y) {
        handle.center(slider.center().x(), ensureRange(y, location.y(), location.y() + height));

        double ofSlider = 1 - fractionOf(handle.center().y(), location.y(), location.y() + height);
        value = ofSlider * (maxValue - minValue) + minValue;

        updateFilled();
    }

    /**
     * Slides to a specific y value (from a location) on the slider and updates the value.
     * If y value is out of bounds it will be placed at <code>minValue</code> or <code>maxValue</code>.
     * @param loc the location to get the y value from
     */
    public void slideTo(Location loc) {
        slideTo(loc.y()); // ignore x
    }

    /**
     * Slides the handle of the slider by a certain number of pixels and updates the value.
     * @param y the number of pixels to slide
     */
    public void slideY(double y) {
        slideTo(handle.center().y() - y);
    }

    /**
     * Update the space below the handle to be filled in with full opacity.
     */
    protected void updateFilled() {
        filled.y(handle.center().y());
        filled.height(location.y() + height - filled.y());
    }

    /**
     * Set the background color so the slider stick displays slightly translucent.
     * @param bgColor the new background color
     */
    public void setBackgroundColor(Color bgColor) {
        this.bgColor = bgColor;
        color(color);
    }

    /**
     * To be used in {@link Window#mouseDrag(Location, int) Window's mouseDrag} to automatically set up sliding.
     * @param location from {@link Window#mouseDrag(Location, int)}  Window's mouseDrag}
     * @see Window#mouseDrag(Location, int)
     * @see #mouseDown(Location, int)
     * @see #mouseUp(int)
     */
    public void mouseDrag(Location location) {
        if (selected && !disabled) slideTo(location);
    }

    /**
     * To be used in {@link Window#mouseDown(Location, int) Window's mouseDown} to automatically set up sliding.
     * @param location from {@link Window#mouseDown(Location, int) Window's mouseDown}
     * @param button from {@link Window#mouseDown(Location, int) Window's mouseDown}
     * @see Window#mouseDown(Location, int)
     * @see #mouseDrag(Location)
     * @see #mouseUp(int)
     */
    public void mouseDown(Location location, int button) {
        if (button == 1 && !selected && !disabled && handle.contains(location)) selected = true;
    }

    /**
     * To be used in {@link Window#mouseUp(Location, int) Window's mouseUp} to automatically set up sliding.
     * @param button from {@link Window#mouseUp(Location, int) Window's mouseUp}
     * @see Window#mouseUp(Location, int) 
     * @see #mouseDrag(Location)
     * @see #mouseDown(Location, int) 
     */
    public void mouseUp(int button) {
        if (button == 1 && selected) selected = false;
    }

    /**
     * @return Get the background color of the slider as suggested in <a href="https://material.io/components/sliders#theming">Material Design</a>.
     */
    protected Color secondaryColor() {
        return new Color(color.red(), color.green(), color.blue(), 38, bgColor);
    }

    @Override
    protected Object[] getParameters() {
        return parameterValues;
    }

    @Override
    public void move(double dx, double dy) {
        location.move(dx, dy);
        slider.move(dx, dy);
        filled.move(dx, dy);
        handle.move(dx, dy);
    }

    @Override
    public void move(Location location) {
        move(location.x(), location.y());
    }

    @Override
    public void moveTo(double x, double y) {
        location.moveTo(x, y);
        slider.moveTo(x, y);
        filled.moveTo(x, y);
        handle.moveTo(x, y);
        setValue(value);
    }

    @Override
    public double width(double width) {
        this.width = width;
        slider.width(width);
        filled.width(width);
        handle.width(width + 10);
        handle.height(width + 10);
        updateFilled();
        return width;
    }

    @Override
    public double height(double height) {
        this.height = height;
        slider.height(height);
        filled.height(height);
        setValue(value); // update the handle location
        return height;
    }

    @Override
    public double rotation(double rotation) {
        this.angle = rotation;
        return rotation;
    }

    @Override
    public void rotate(double angleChange) {
        angle += angleChange;
    }

    @Override
    public Color color(Color color) {
        this.color = color;
        slider.color(secondaryColor());
        filled.color(color);
        handle.color(color);
        return color;
    }

    @Override
    public boolean fill(boolean fill) {
        this.fill = fill;
        slider.fill(fill);
        filled.fill(fill);
        handle.fill(fill);
        return fill;
    }

    @Override
    public void front() {
        // TODO: removes border
        slider.front();
        filled.front();
        handle.front();
    }

    @Override
    public void back() {
        // TODO: puts handle border behind other borders
        handle.back();
        filled.back();
        slider.back();
    }

    @Override
    public boolean visible(boolean visible) {
        this.visible = visible;
        slider.visible(visible);
        filled.visible(visible);
        handle.visible(visible);
        return visible;
    }

    @Override
    public void remove() {
        slider.remove();
        filled.remove();
        handle.remove();
    }

    @Override
    public Location[] vertices() {
        return slider.vertices();
    }

    @Override
    public boolean contains(Location location) {
        return slider.contains(location) || filled.contains(location) || handle.contains(location);
    }

    @Override
    public boolean overlaps(Renderable renderable) {
        return slider.overlaps(renderable) || filled.overlaps(renderable) || handle.overlaps(renderable);
    }

    @Override
    protected void updateBorder() {
        slider.updateBorder();
        filled.updateBorder();
        handle.updateBorder();
    }

    @Override
    protected void updateBorder(Color color) {
        slider.updateBorder(color);
        filled.updateBorder(color);
        handle.updateBorder(color);
    }
}
