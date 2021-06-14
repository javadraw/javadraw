This is a simple graphics library designed to make graphics
and input simple and synchronized, and serves as a Java-based sister library to [pyDraw](https://github.com/pydraw/pydraw).


Designed to accompany pyDraw and to replace the long-revered ObjectDraw, javaDraw implements
a new and more modernized method system, along with a more controlled and property-based shape collection.

Indicators: (⭐ = Important, 🚀 = Awesome/Fun Feature, 😻 = Cat)

### Features
- Simple, One-Line Shape Construction
- Consistent Object Management and Manipulation
- Simplified and Automatated Input System 🚀
- Top-Left Anchored Coordinate System
- Special Shapes, Irregular Polygons
- Precise `.overlaps()` and `.contains()` methods for all Renderables!
    - Highly Optimized Algorithms for __0.01 * 10<sup>-16</sup>s__ runtime (avg).
- Separated Location (Vector2D) and Color classes!
- Support for Locations where (x, y) would be passed.
- Designed for LEARNING! 🚀😻

## Getting Started

### Recommended
In order to install javaDraw, you will need to download the JAR file from the [Releases Tab](https://github.com/javadraw/javadraw/releases).
Then, in your IDE or environment of choice, you can drag the JAR in and import javaDraw
to begin the journey!

#### After installation, you can import the library with: `import javadraw.*;`

## Basic Setup

---
After importing javaDraw, you can write a basic skeleton program like so:

You will notice that the elegance of enabling animation within the start method allows for previously complex
programs written with ObjectDraw or another library to be rewritten in a much more simple manner.

```java
import javadraw.*;

public class BasicProgram extends Window {
    
    public static void main(String[] args) {
        Window.open(800, 600, "My First Project!"); // Creates a Screen to draw on.
    }
    
    public void start() {
        // We can then being to code in this automatically called method.
        
        double fps = 30;
        boolean running = true;
        while (running) {
            screen.update(); // We want to update the screen if we make any changes!
            screen.sleep(1 / fps); // Limit our updates by a certain time delay, in this case 30fps.
                                   // The argument is the delay in seconds
        }
    }
}
```

We can create our first object with just one new line:
```java
import javadraw.*;

public class BasicProgram extends Window {
    
    public static void main(String[] args) {
        Window.open(800, 600, "My First Project!"); // Creates a Screen to draw on.
    }
    
    public void start() {
        // Here we create a rectangle at x=50, y=50 that is 50 pixels wide and 50 pixels tall.
        // It is top-left anchored. This means that the position is the location of the top left corner.
        // It's important to know that javaDraw's canvas has the origin in the top left, with
        // positive-y at the bottom of the screen, and positive-x to the right of the screen.
        Rectangle box = new Rectangle(screen, 50, 50, 50, 50);
        
        double fps = 30;
        boolean running = true;
        while (running) {
            screen.update();
            screen.sleep(1 / fps);
        }
    }
}
```


⭐ Input detection has gotten *even* better since last seen; with more methods and parameters expanding
to nearly every need.
```java
import javadraw.*;

public class BasicProgram extends Window {
    
    public static void main(String[] args) {
        Window.open(800, 600, "My First Project!"); // Creates a Screen to draw on.
    }
    
    public void start() {
        Rectangle box = new Rectangle(screen, 50, 50, 50, 50);
        
        double fps = 30;
        boolean running = true;
        while (running) {
            screen.update();
            screen.sleep(1 / fps);
        }
    }
    
    public void mouseDown(int button, Location location) {
        // Notice the easy integration of a "print" method to make console
        // usage even easier for beginners, and to reduce needless typing.
        // It also can accept multiple, even non-String arguments, automatically
        // separating them with a space.
        print("Wow! The", button, "was pressed at", location, "!");    
    }
    
    public void mouseUp(int button, Location location) {
        print("Mouse released at location:", location);
    }
    
    public void keyDown(Key key) {
        // Key input is now easily registered and has been greatly simplified
        print("A", key, "press was detected!");
        
        // Use easy conditionals to compare keys without complexity.
        if (key == Key.ENTER)
            print("Enter press detected!");
    }
    
    public void keyUp(Key key) {
        print("Some", key, "was released.");
    }
    
    // Automatically registered methods make it easier than ever to handle input as-needed.
}
```

This library supports many modifiers and methods for almost all objects:
```java
import javadraw.*;

public class BasicProgram extends Window {
    
    public static void main(String[] args) {
        Window.open(800, 600, "My First Project!"); // Creates a Screen to draw on.
    }
    
    public void start() {
        Rectangle box = new Rectangle(screen, 50, 50, 50, 50);
        
        box.x(box.y()); // Set's the box's x-coordinate to its y-coordinate
                        // Notice how getters and setters exist under the same method name.
                        // Allowing for less confusion and a more modern approach to properties.
        
        box.location(); // We can access the Location like so!
        
        box.move(-5, 100); // Move the box by -5 on the x-axis, 100 on the y-axis.
        box.moveTo(screen.center()); // Move the top-left anchor of the box to the center of the Screen.
        
        box.width(box.height()); // Again, showing off the usefulness of the ambiguity of javaDraw's method structure.
        
        box.color(Color.RED); // Set the box's Color using the Color wrapper class.
        
        box.border(Color.BLACK, false); // Set the box's border to Black and set its fill to false.
                                        // Essentially creating a "Framed Rectangle" with ease.
        
        box.rotate(14); // Rotate our box by 14 degrees, clockwise
        box.rotation(box.rotation() + 14); // This is the same as the line above, but using our accessors and setters.
        
        box.visible(false); // Everything in javaDraw follows the same structure. This hides the box.
        
        box.remove(); // Get rid of that old box. We can make a better one soon :)
                      // Note that this removes the Box from the Screen but not from memory.
    }
}
```

Lastly we can create some other objects and interact with them:

(It's important to note that all the Renderables below can use the methods listed
above (including `overlaps()` and `contains()`, which we see in the excerpt below).

```java
import javadraw.*;

public class BasicProgram extends Window {
    
    public static void main(String[] args) {
        Window.open(800, 600, "My First Project!"); // Creates a Screen to draw on.
    }
    
    public void start() {
        Oval notABox = new Oval(screen, 400, 50, 100, 100, Color.MAGENTA); // now we have a beautiful oval
        Triangle almostABox = new Triangle(screen, 200, 450, 100, 50, Color.YELLOW, /* Rotation */ 30); // uno dos tres
        // ^ Also note that we are setting the color, and also setting the rotation of the triangle,
        // but the other parameters are still in the usual format: (x, y, width, height).
        // IMPORTANT: Triangle's base is on the left, with the triangle's location as its top corner.
        
        // We can create a regular polygon by specifying a number of sides before the location.
        // The constructor is (screen, num_sides, x, y, width, height)!
        Polygon schrodingersBox = new Polygon(screen, 5, 250, 150, 50, 50, Color.BLACK);
        schrodingersBox.border(Color.RED);
        // Polygon, like Triangle, will also try to put a vertex as close to the top left as possible,
        // so usually you will end up with the base of the polygon at the top.
        
        // We can create an evil polygon like this (we can pass in a list of Locations):
        weirdEvilBox = CustomPolygon(screen, 
                new Location[] { new Location(500, 50), new Location(550, 50), 
                        new Location(550, 100), new Location(500, 50)});
        // ^ The real term for these is "Irregular Polygons". But irregular is hard to type so here we are.
        
        
        // We can interact with these objects with these methods:
        notABox.overlaps(almostABox); // Do these objects overlap?
        weirdEvilBox.contains(Location(525, 75)); // Is this point inside the shape?

        schrodingersBox.distance(notABox); // Gets the precise distance between the centers
    }
}
```

### Text, Images, and Lines

javaDraw has specific APIs for Text, Images, and Lines that will sometimes deviate from the
standard methods slightly.

#### Text
```java
// ... code above
Text text = new Text(screen, "Some Cool Text!", screen.width(), screen.height(), Color.PURPLE);
text.move(-text.width() / 2, -text.height() / 2);  // You can get the width and height to perfectly center
                                                  // any text easily!
text.font("Calibri");
text.rotate(45); // You can still rotate text if you want :)

text.bold(true);
text.underline(true);
text.strikethrough(false);

// code below ...
```

#### Images

Images are easier than ever to make and modify; just throw the path in a String and start playing around!

```java
// ... code above

Image image = new Image(screen, "path_to_image.png", screen.width() / 3, screen.height() / 3);

// ... code below
```

#### Lines
```java
// ... code above

// Let's create a nice line that goes across the screen with a beautiful blue color.
line = Line(screen, 150, 150, screen.width() - 50, screen.height() - 50, Color.BLUE);

// We can still rotate lines too!
line.rotate(35, point=1); // Note that here we are specifying which point to rotate AROUND!
                          // Be it either the "pos1" or "pos2" of the Line.

// We can use a special feature of lines to make them point at stuff!
line.lookAt(another_location); // SUPER NIFTY!

// code below ...
```

---

## API/Docs
The documentation for [javaDraw](https://javadraw.graphics) wis available at the main website: https://javadraw.graphics
The documentation is shipped with the package so code completion and method descriptors are
available for supporting IDEs.

---

## DIY
If you want to build your own version of javaDraw, just fork the "internal" branch 
and build an artifact in your favorite IDE of choice. The internal code is based off of ObjectDraw and a modified
version of ObjectDraw called CoffeeDraw that I never quite completed.

---

### A Big Thanks To:
- Barry Lindler (An incredible person with a brain that can bench 345lbs and a heart of gold)
    - Follow this man on Twitter: [@barrylindler](https://twitter.com/barrylindler)
- Whatever geniuses came up with the crossing number algorithm, and the line-segment orientation algorithm
- Schrödinger for his cat obsession
- My Dad. For being supportive of me throughout this long journey of mine.
