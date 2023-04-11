package model;


public class Coord {
	
	private int x,y;
	
	public Coord(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the x value of the coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y value of the coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Set coordinate to x and y
	 * @param x
	 * @param y
	 */

	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @param other
	 * @return true if same coordinate else false
	 */
	public boolean isSameCoord(Coord other) {
		return (this.x == other.getX() && this.y == other.getY());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
	
	public String toString() {
		return "("+this.x+","+this.y+")";
	}

}
