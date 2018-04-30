package primitives;

public class Point2D {

    protected Coordinate _x;
    protected Coordinate _y;

    // ***************** Constructors ********************** //
    public Point2D() {
        _x = new Coordinate(0);
        _y = new Coordinate(0);
    }
    public Point2D(Coordinate x, Coordinate y) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
    }
    public Point2D(double x, double y) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
    }
    public Point2D(Point2D point2D) {
        _x = new Coordinate(point2D._x);
        _y = new Coordinate(point2D._y);
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate getX() {
        return _x;
    }
    public Coordinate getY() {
        return _y;
    }
    public void setX(Coordinate x) {
        _x = x;
    }
    public void setY(Coordinate y) {
        _y = y;
    }

    // ***************** Utilities ******************** //

//	public int compareTo(Point2D other) {
//		??? - Compilation Error when trying to implement here this method together with Point3D's compareTo.
//	}

    @Override
    public String toString() {
        return String.format("(%s, %s)", _x.toString(), _y.toString());
    }
}