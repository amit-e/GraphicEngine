package main.primitives;

public class Coordinate implements Comparable<Coordinate>{

	private double _coordinate;
	
	// ***************** Constructors ********************** // 
	
	public Coordinate()                      { setCoordinate(0.0);                    }
	public Coordinate(double coordinate)     { setCoordinate(coordinate);             }
	public Coordinate(Coordinate coordinate) { setCoordinate(coordinate.getCoordinate()); }

	// ***************** Getters/Setters ********************** // 
	
	public double getCoordinate()                {	return _coordinate;	          }
	public void setCoordinate(double coordinate) { this._coordinate = coordinate; }

	// ***************** Administration  ******************** // 
	
	@Override
	public int compareTo(Coordinate coordinate) {
		return Double.compare(this._coordinate, coordinate._coordinate);
	}
	
	// ***************** Operations ******************** // 
	
	public void add (Coordinate coordinate ){
		this._coordinate += coordinate._coordinate;
	}

	public void subtract(Coordinate coordinate) {
		this._coordinate -= coordinate._coordinate;
	}
	
}
