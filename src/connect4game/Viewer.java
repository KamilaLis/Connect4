package connect4game;

import java.awt.event.MouseListener;
import java.awt.*;
import javax.swing.*;

import connect4game.Circle.col;

public class Viewer{
	private MouseListener listener;
	private static int height = 6;
	private static int width = 7;
	Circle[][] tabOfCircle = new Circle[width][height]; 
	JFrame frame = new JFrame("Connect4");
	
	public Viewer(Controller ml) {
		listener = ml;
	}
	
	public void init(){
		//tworzenie gui
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation( (screensize.width - 800)/2,(screensize.height - 600 )/2);
		frame.setSize( new Dimension(800,600) );
		frame.getContentPane().setBackground(new Color(172,172,172));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); 
		frame.setLayout(null);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setSize(new Dimension(60*width,60*height));
		panel.setLocation(60,100);
		panel.setBackground(new Color(99,104,102));
		panel.setLayout(null);
			
			
		for(int i=0; i<width; i++ ){
			for (int j=0; j<height; j++ ){
			Circle c = new Circle(col.Gray);
			tabOfCircle[i][j]=c;
			panel.add(c);
			c.setLocation(i*60,j*60);
			c.setSize(new Dimension(60,60));
		}
	}
				
		
		JPanel buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel);
		buttonPanel.setSize(new Dimension(60*width,30));
		buttonPanel.setLocation(60,70);
		buttonPanel.setBackground(new Color(99,104,102));
		buttonPanel.setLayout(new GridLayout(1,width));
		
		for(int i=0;i<width;i++){
			
			Button b = new Button();
			buttonPanel.add(b);
			b.addMouseListener(listener);
			b.column = i;
			
		}
		frame.setVisible(true);
	}
	
	public void drawCircle(int column, int row, Player player){
				if(player == Player.Human){
					Circle c = new Circle(col.Yellow);
					tabOfCircle[column][row].add(c);
					System.out.println("drawCircle-column: "+column);
					System.out.println("drawCircle-row: "+row);
					tabOfCircle[column][row] = c;
					c.setSize(new Dimension(60,60));
					c.setVisible(true);
				}
				else{
					Circle c = new Circle(col.Red);
					tabOfCircle[column][row].add(c);
					tabOfCircle[column][row] = c;
					c.setSize(new Dimension(60,60));
					c.setVisible(true);
				}	

				
	}
	
}
