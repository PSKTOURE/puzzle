package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import util.AbstractObservableModel;

/**
 * Class representing the model part of the MVC model
 * Extends AbstractObservableModel
 * Ensures the computation of all the methods affecting the 
 * model
 * @author toure215
 *
 */

public class Grid extends AbstractObservableModel {

	private Case voidCase; // la case vide du puzzle
	private int nbCols; // nombre de colonnes
	private int nbRows; // nombre de lignes
	private Case[][] matrix; // le puzzle: matrice de n lignes et m colonnes

	/**
	 * Construtor 
	 * @param nbCols
	 * @param nbRows
	 */

	public Grid(int nbRows, int nbCols) {
		matrix = new Case[nbRows][nbCols];
		this.nbRows = nbRows;
		this.nbCols = nbCols;
		int index = 1;
		voidCase = new Case((nbCols * nbRows)/2, new Coord((nbRows - 1)/2, (nbCols - 1)/2));
		for (int i = 0; i < nbRows; i++) {
			for (int j = 0; j < nbCols; j++) {
				matrix[i][j] = new Case(index, new Coord(i, j));
				index++;
			}
		}
		matrix[(int)(nbRows - 1)/2][(int)(nbCols - 1)/2] = voidCase;
	}
	
	/**
	 * 2ND Constructor
	 * @param nbRows
	 * @param nbCols
	 * @param n
	 * @Construct a shuffled grid
	 */
	public Grid(int nbRows, int nbCols, int n) {
		this(nbRows, nbCols);
		n = nbRows * nbCols;
		randomMoves(n);
	}
	
	/**
	 * 
	 * @return if a case can be moved or not
	 */
	public boolean isValid() {
		boolean ok = false;
		int x = voidCase.getCoord().getX();
		int y = voidCase.getCoord().getY();
		if (x > 0 && x < nbRows -1 && y > 0 && y < nbCols - 1) {
			ok = true;
		}
		return ok;
	}

	
	/**
	 * Moves the voidCase up if the move is
	 * possible else print "Invalid Move"
	 */
	public void moveUp() {
		int x = voidCase.getCoord().getX();
		int y = voidCase.getCoord().getY();
		if (x > 0) {
			Case upperCase = matrix[x - 1][y];
			matrix[x - 1][y] = voidCase;
			voidCase.getCoord().set(x - 1, y);
			upperCase.getCoord().set(x, y);
			matrix[x][y] = upperCase;
			fireChange();
		}
		else {
			System.out.println("Invalid Move");
		}
	}
	
	/**
	 * Moves the voidCase down if the move is
	 * possible else print "Invalid Move"
	 */
	
	public void moveDown() {
		int x = voidCase.getCoord().getX();
		int y = voidCase.getCoord().getY();
		if (x < nbRows -1) {
			Case lowerCase = matrix[x + 1][y];
			matrix[x + 1][y] = voidCase;
			voidCase.getCoord().set(x + 1, y);
			lowerCase.getCoord().set(x, y);
			matrix[x][y] = lowerCase;
			fireChange();
		}
		else {
			System.out.println("Invalid Move");
		}
	}
	
	/**
	 * Moves the voidCase left if the move is
	 * possible else print "Invalid Move"
	 */
	
	public void moveLeft() {
		int x = voidCase.getCoord().getX();
		int y = voidCase.getCoord().getY();
		if (y > 0) {
			Case leftCase = matrix[x][y - 1];
			matrix[x][y - 1] = voidCase;
			voidCase.getCoord().set(x, y-1);
			leftCase.getCoord().set(x, y);
			matrix[x][y] = leftCase;
			fireChange();
		}
		else {
			System.out.println("Invalid Move");
		}
	}
	
	/**
	 * Moves the voidCase right if the move is
	 * possible else print "Invalid Move"
	 */
	
	public void moveRight() {
		int x = voidCase.getCoord().getX();
		int y = voidCase.getCoord().getY();
		if (y < nbCols -1) {
			Case rightCase = matrix[x][y + 1];
			matrix[x][y + 1] = voidCase;
			voidCase.getCoord().set(x, y+1);
			rightCase.getCoord().set(x, y);
			matrix[x][y] = rightCase;
			fireChange();
		}
		else {
			System.out.println("Invalid Move");
		}
	}
	
	/**
	 * Method making a fix number of random moves
	 * @param numberOfMoves
	 * @Ensure a random grid
	 */
	public void randomMoves(int numberOfMoves) {
		int past=5;
		while(numberOfMoves > 0) {
			Random rand = new Random();
			int direction = rand.nextInt(4);
			int x = voidCase.getCoord().getX();
			int y = voidCase.getCoord().getY();
			if(direction == 0 && past !=1 && x>0) {
				this.moveUp();
				past = 0;
				numberOfMoves--;
			}
			if(direction == 1 && past!=0 &&  x<nbRows - 1) {
				this.moveDown();
				past = 1;
				numberOfMoves--;
			}
			if(direction == 2 && past!=3 && y>0) {
				this.moveLeft();
				past = 2;
				numberOfMoves--;
			}
			if(direction == 3 && past != 2 && y<nbCols -1) {
				this.moveRight();
				past = 3;
				numberOfMoves--;
			}
		}
		
	}
	
	/**
	 * 
	 * @return the list of adjacent cases of the voidCase
	 */
	public List<Case> adjacentCases() {
		List<Case> list = new ArrayList<>();
		int x = voidCase.getCoord().getX();
		int y = voidCase.getCoord().getY();
		for(int k =-1, l = -1; k<=1 && l<=1; k+=2, l+=2) {
			if(x+k>=0 && x+k<=nbRows-1) {
				list.add(matrix[x+k][y]);
			}
			if(y+l>=0 && y+l<=nbCols-1) {
				list.add(matrix[x][y+l]);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param cell
	 * @return if a case is adjacent to the voidCase
	 */
	public boolean isAdjacent(Case cell) {
		boolean ok = false;
		if(this.adjacentCases().contains(cell)) {
			return true;
		}
		return ok;
	}
	
	/**
	 * Moves the voidCase to the location of the
	 * the adjacent case that the mouse is clicked on
	 * @param cell
	 */
	public void moveClick(Case cell) {
		int y = voidCase.getCoord().getX();
		int x = voidCase.getCoord().getY();
		int yc = cell.getCoord().getX();
		int xc = cell.getCoord().getY();
		if(isAdjacent(cell) && xc - x > 0) {
			this.moveRight();
		}
		else if(isAdjacent(cell) && xc - x < 0) {
			this.moveLeft();
		}
		else if(isAdjacent(cell) && yc - y > 0) {
			this.moveDown();
		}
		else if(isAdjacent(cell) && yc - y < 0) {
			this.moveUp();
		}
		else {
			System.out.println("Move Impossible");
		}
	}
	
	/**
	 * Method to move case accordind to system input
	 * z : to go up
	 * s : to go down
	 * q : to go left
	 * d : to go right
	 */
	public void move() {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		if(str.equals("z")) {
			moveUp();
		}
		else if(str.equals("s")) {
			moveDown();
		}
		else if(str.equals("q")) {
			moveLeft();
		}
		else if(str.equals("d")) {
			moveRight();
		}else {
			System.out.println("Input must be z, s, q, d");
		}
		this.toString();
		System.out.println("Chose direction: Press key z(up), s(down), q(left), d(right)");
		System.out.println("****************************");
	}

	/**
	 * Method that fully randomises the grid
	 * Not recommended
	 */
	public void randomGrid() {
		ArrayList<Case> list = new ArrayList<>();
		for (int i = 0; i < nbRows; i++) {
			for (int j = 0; j < nbCols; j++) {
				list.add(this.matrix[i][j]);
			}
		}
		Collections.shuffle(list);
		int k = 0;
		for (int i = 0; i < nbRows; i++) {
			for (int j = 0; j < nbCols; j++) {
				matrix[i][j] = list.get(k);
				k++;
				matrix[i][j].getCoord().set(i, j);
			}

		}

		this.setMatrix(matrix);
	}
	
	/**
	 * @return if all the differents cases of the grid
	 * are on there correct position
	 */
	public boolean isOver() {
		List<Case> list = new ArrayList<>();
		boolean ok = true;
		for(int i  = 0; i < nbRows; i++) {
			for(int j = 0; j < nbCols; j++) {
				list.add(matrix[i][j]);
			}
		}
		for(int k = 0; k < list.size(); k++) {
			if(k != list.get(k).getIndex()) {
				ok = false;
			}
		}
		return ok;
	}

	@Override
	public void addObserver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeObserver() {
		// TODO Auto-generated method stub

	}

	public String toString() {
		for (int i = 0; i < nbRows; i++) {
			for (int j = 0; j < nbCols; j++) {
				if (matrix[i][j] == voidCase) {
					System.out.print("[V]");
				} else {
					System.out.print("[" + matrix[i][j].getIndex() + "]");
				}
			}
			System.out.println();
		}
		return "";
	}
	
	
	// GETTERS AND SETTERS
	
	public Case getVoidCase() {
		return voidCase;
	}

	public void setVoidCase(Case voidCase) {
		this.voidCase = voidCase;
	}

	public int getNbCols() {
		return nbCols;
	}

	public void setNbCols(int nbCols) {
		this.nbCols = nbCols;
	}

	public int getNbRows() {
		return nbRows;
	}

	public void setNbRows(int nbRows) {
		this.nbRows = nbRows;
	}

	public Case[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Case[][] matrix) {
		this.matrix = matrix;
	}

}
