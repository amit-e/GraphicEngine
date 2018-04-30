package primitives;

public class Coordinate implements Comparable<Coordinate> {
    private double _coordinate;

    //***************** Constructors ********************** //
    public Coordinate() {
        _coordinate = 0;
    }
    public Coordinate(double coordinate) {
        _coordinate = coordinate;
    }
    public Coordinate(Coordinate coordinate) {
        _coordinate = coordinate._coordinate;
    }

    //***************** Getters/Setters ********************** //
    public double getCoordinate() {
        return _coordinate;
    }
    public void setCoordinate(double coordinate) {
        _coordinate = coordinate;
    }

    //***************** Utilities ******************** //
    @Override
    public int compareTo(Coordinate other) {
        return Double.compare(this._coordinate, other._coordinate);
    }

    @Override
    public String toString() {
        if(_coordinate == (long) _coordinate)
            return String.format("%1d",(long)_coordinate);
        else
            return String.format("%1$.2f", _coordinate);
    }

    //***************** Operations ******************** //
    public void add(Coordinate coordinate) {
        this._coordinate += coordinate._coordinate;
    }
    public void subtract(Coordinate coordinate) {
        this._coordinate -= coordinate._coordinate;
    }
}