package Main;

import control.Controller;
import model.Grid;
import view.GridGUI;

/**
 * Main Class
 * 
 * @author toure215
 *
 */
public class Demo {

	public static void main(String[] args) {
		if (args.length > 0) {
			Grid grid = new Grid(3, 3);
			GridGUI puzzleGui = new GridGUI(grid);
		} else {
			Grid grid = new Grid(3, 3, 0);
			System.out.println(grid);
			System.out.println("Chose direction: Press key z(up), s(down), q(left), d(right)");
			while (!grid.isOver()) {
				grid.move();
			}
		}
		/*Grid grid = new Grid(3, 3);
		GridGUI puzzleGui = new GridGUI(grid);*/
	}
	

}
