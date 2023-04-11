package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Case;
import model.Grid;
import util.Observer;

/**
 * Class representing the view part of the MVC Model Extends Jpanel and
 * implements Observer and Mouselistener Ensures that the view gets updated
 * everytime the model changes
 * 
 * @author toure215
 *
 */
public class ViewGrid extends JPanel implements Observer, MouseListener {

	private Grid grid;
	private int width;
	private int height;
	private static Map<Integer, Image> IMAGE = new HashMap<>();
	private static final Color newColor =  new Color(0,0,255);
	private String path;
	

	/**
	 * Constructor
	 * 
	 * @param grid
	 */
	public ViewGrid(Grid grid, String path) {
		this.grid = grid;
		this.path = path;
		int n = grid.getNbCols() * grid.getNbRows();
		this.init();
		grid.randomMoves(2 * n);
		grid.addObserver(this);
		setPreferredSize(new Dimension(width * grid.getNbCols(), height * grid.getNbRows()));
	}

	/**
	 * Void method that divides the image into subimages and stock them in a HashMap
	 */
	public void init() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
			width = img.getWidth(null) / grid.getNbCols();
			height = img.getHeight(null) / grid.getNbRows();
			for (int j = 0; j < grid.getNbRows(); j++) {
				for (int i = 0; i < grid.getNbCols(); i++) {
					int x = img.getWidth() * i / grid.getNbCols();
					int y = img.getHeight() * j / grid.getNbRows();
					IMAGE.put(grid.getMatrix()[j][i].getIndex(), img.getSubimage(x, y, width, height));
				}
			}
		} catch (IOException e) {
			System.out.println("No image found");
		}

	}

	@Override
	public void updateModel(Object s) {
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int y = 0; y < grid.getNbRows(); y++) {
			for (int x = 0; x < grid.getNbCols(); x++) {
				if (grid.getMatrix()[y][x].equals(grid.getVoidCase())) {
					Color black = new Color(255, 255, 255);
					g.setColor(black);
					g.fillRect(x * width, y * height, width, height);

				} else {
					g.drawImage(IMAGE.get(grid.getMatrix()[y][x].getIndex()), x * width, y * height, null);
					g.setColor(new Color(255,255,255,50));
					g.drawRect(x * width, y * height, width, height);
					if (grid.isAdjacent(grid.getMatrix()[y][x])) {
						g.setColor(newColor);
						Graphics2D g2 = (Graphics2D) g;
						g2.setStroke(new BasicStroke(5));
						g2.drawRect(x * width, y * height, width, height);
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Case[][] matrix = grid.getMatrix();
		if (!grid.isOver()) {
			int x = e.getX() / width;
			int y = (e.getY() - 40) / height;
			for (int j = 0; j < grid.getNbRows(); j++) {
				for (int i = 0; i < grid.getNbCols(); i++) {
					if (matrix[j][i].getCoord().getY() == x && matrix[j][i].getCoord().getX() == y) {
						grid.moveClick(matrix[j][i]);
					}
				}
			}
		}
	}

	

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}


}
