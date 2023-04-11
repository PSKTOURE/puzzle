package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import control.Controller;
import model.Grid;

public class GridGUI extends JFrame implements ActionListener {
	
	private Grid grid;
	private ViewGrid panel;
	private Controller control;
	private JMenuItem image1;
	private JMenuItem image2;
	
	


	public GridGUI(Grid grid){
		super("PUZZLE");
		this.grid = grid;
		String path = "./src/Image/BlackHole.jpg";
		this.panel = new ViewGrid(grid, path);
		this.control = new Controller(grid);
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(panel, BorderLayout.CENTER);
		
		JMenu menu = new JMenu("Menu");
		JMenuBar menuBar = new JMenuBar();
		image1 = new JMenuItem("BlackHole");
		image2 = new JMenuItem("Nature");
		
		menu.add(image1);
		menu.add(image2);
		menuBar.add(menu);
		container.add(menuBar, BorderLayout.NORTH);
		
		this.addMouseListener(panel);
		this.addKeyListener(control);
		image1.addActionListener(this);
		image2.addActionListener(this);
	
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == image1) {
			this.remove(panel);
			String path = "./src/Image/BlackHole.jpg";
			panel = new ViewGrid(grid, path);
			this.getContentPane().add(panel, BorderLayout.CENTER);
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource() == image2) {
			this.remove(panel);
			String path = "./src/Image/nature.jpeg";
			panel = new ViewGrid(grid, path);
			this.getContentPane().add(panel, BorderLayout.CENTER);
			this.getContentPane().setSize(panel.getWidth(), panel.getHeight());
			SwingUtilities.updateComponentTreeUI(this);
		}
	}
}
