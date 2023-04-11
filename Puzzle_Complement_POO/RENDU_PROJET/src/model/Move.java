package model;

public class Move {
	
	public Case cellule;
	public Coord finalCoord;
	
	public Move(Case cellule, Coord coord) {
		this.cellule = cellule;
		this.finalCoord = coord;
	}

	/**
	 * @return the cellule
	 */
	public Case getCellule() {
		return cellule;
	}

	/**
	 * @param cellule the cellule to set
	 */
	public void setCellule(Case cellule) {
		this.cellule = cellule;
	}

	/**
	 * @return the coord
	 */
	public Coord getCoord() {
		return finalCoord;
	}

	/**
	 * @param coord the coord to set
	 */
	public void setCoord(Coord coord) {
		this.finalCoord = coord;
	}

	
	@Override
	public String toString() {
		return "Move [cellule=" + cellule + ", coord=" + finalCoord.toString() + "]";
	}
	
	
	
	
}
