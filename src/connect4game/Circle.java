package connect4game;

import java.awt.*;
import javax.swing.*;

public class Circle extends JPanel {

	public enum col {Red, Gray, Yellow};
	col c = col.Yellow;
	public Circle(col c){		
		this.setPreferredSize(new Dimension(60, 60));
		this.c = c;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(this.c == col.Red){
			g2d.setColor(Color.RED);
  		}
		if(this.c == col.Yellow){
	  		g2d.setColor(Color.YELLOW);
	  	}
		if(this.c == col.Gray){
	  		g2d.setColor(Color.GRAY);
	  	}
		g2d.fillOval(5, 5, 45, 45);
	    }
	
	public void setCol(col b){
		c = b;	
	}
	
}
