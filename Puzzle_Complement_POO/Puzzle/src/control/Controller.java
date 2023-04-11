package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Grid;
import util.Observer;

/**
 * The Controller part of the MVC model
 * Acts directly on the model
 * @author toure215
 *
 */
public class Controller implements KeyListener, Observer {
	
	private Grid grid;
	
	/**
	 * @param grid
	 */
	public Controller(Grid grid) {
		super();
		this.grid = grid;
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		if (!this.grid.isOver()){
			if (e.getKeyCode() == KeyEvent.VK_DOWN ){
				grid.moveDown();
			}
			if (e.getKeyCode() == KeyEvent.VK_UP ){
				grid.moveUp();
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT ){
				grid.moveLeft();
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT ){
				grid.moveRight();
			}
		}
		this.grid.toString();
		System.out.println("****************************");
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateModel(Object s) {
		// TODO Auto-generated method stub
		
	}

}
