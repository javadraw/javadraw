package javadraw;

public interface DrawableObject {

    double x();
    double y();

    Location location();

    void move(double dx, double dy);
    void move(Location location);

    void moveTo(double x, double y);
    void moveTo(Location location);

    void front();
    void back();

    void remove();

}
