package javadraw;

import javadraw.errors.InvalidArgumentException;
import javadraw.internal.SneakyThrow;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * Represents a Key, allowing for easy comparison in keyEvent handlers.
 */
public class Key {

    public static final Key BACK_QUOTE = new Key("Back_quote");
    public static final Key ONE = new Key("1");
    public static final Key TWO = new Key("2");
    public static final Key THREE = new Key("3");
    public static final Key FOUR = new Key("4");
    public static final Key FIVE = new Key("5");
    public static final Key SIX = new Key("6");
    public static final Key SEVEN = new Key("7");
    public static final Key EIGHT = new Key("8");
    public static final Key NINE = new Key("9");
    public static final Key TEN = new Key("0");
    public static final Key MINUS = new Key("Minus");
    public static final Key EQUALS = new Key("Equals");
    public static final Key BACKSPACE = new Key("Back_space");
    public static final Key TAB = new Key("Tab");
    public static final Key Q = new Key("Q");
    public static final Key W = new Key("W");
    public static final Key E = new Key("E");
    public static final Key R = new Key("R");
    public static final Key T = new Key("T");
    public static final Key Y = new Key("Y");
    public static final Key U = new Key("U");
    public static final Key I = new Key("I");
    public static final Key O = new Key("O");
    public static final Key P = new Key("P");
    public static final Key OPEN_BRACKET = new Key("Open_bracket");
    public static final Key CLOSE_BRACKET = new Key("Close_bracket");
    public static final Key BACK_SLASH = new Key("Back_slash");
    public static final Key CAPS_LOCK = new Key("Caps_lock");
    public static final Key A = new Key("A");
    public static final Key S = new Key("S");
    public static final Key D = new Key("D");
    public static final Key F = new Key("F");
    public static final Key G = new Key("G");
    public static final Key H = new Key("H");
    public static final Key J = new Key("J");
    public static final Key K = new Key("K");
    public static final Key L = new Key("L");
    public static final Key SEMICOLON = new Key("Semicolon");
    public static final Key QUOTE = new Key("Quote");
    public static final Key ENTER = new Key("Enter");
    public static final Key SHIFT = new Key("Shift");
    public static final Key Z = new Key("Z");
    public static final Key X = new Key("X");
    public static final Key C = new Key("C");
    public static final Key V = new Key("V");
    public static final Key B = new Key("B");
    public static final Key N = new Key("N");
    public static final Key M = new Key("M");
    public static final Key COMMA = new Key("Comma");
    public static final Key PERIOD = new Key("Period");
    public static final Key SLASH = new Key("Slash");
    public static final Key CONTROL = new Key("Control");
    public static final Key ALT = new Key("Alt");
    public static final Key SPACE = new Key("Space");
    public static final Key ESCAPE = new Key("Escape");
    public static final Key F1 = new Key("F1");
    public static final Key F2 = new Key("F2");
    public static final Key F3 = new Key("F3");
    public static final Key F4 = new Key("F4");
    public static final Key F5 = new Key("F5");
    public static final Key F6 = new Key("F6");
    public static final Key F7 = new Key("F7");
    public static final Key F8 = new Key("F8");
    public static final Key F9 = new Key("F9");
    public static final Key F10 = new Key("F10");

    String character;
    public Key(String character) {
        this.character = character;
    }

    public static Key fromChar(String keyChar) {
        Key key;

        try {
            Field field = Key.class.getField(keyChar);
            key = (Key) field.get(null);
        } catch (Exception e) {
            key = null;
            SneakyThrow.sneakyThrow(new InvalidArgumentException("Invalid color name passed!"));
        }

        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Key) {
            Key key = (Key) o;
            return Objects.equals(character.toLowerCase(), key.character.toLowerCase());
        } else if(o instanceof String) {
            return Objects.equals(character.toLowerCase(), ((String) o).toLowerCase());
        }

        return false;
    }

    /**
     * Get the character/String represented by the Key
     * @return a String
     */
    public String character() {
        return character;
    }

    @Override
    public int hashCode() {
        return Objects.hash(character);
    }
}
