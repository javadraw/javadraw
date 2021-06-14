package javadraw;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Represents the "Transform" of an Object. It's "width", "height", and "angle" (or rotation).
 */
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Transform {

    protected double width, height, angle;

}
