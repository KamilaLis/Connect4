package connect4game;


import java.awt.event.MouseListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.MenuListener;

import connect4game.Circle.col;

public class Viewer{
	private MouseListener listener;
	private MenuListener menulistener;
	private static int height = 7;
	private static int width = 6;
	Circle[][] tabOfCircle = new Circle[width][height]; 
	JFrame frame = new JFrame("Connect4");
	JLabel label;
	
	public Viewer(Controller ml) {
		listener = ml;
		menulistener = ml;
	}
	
	public void init(){
		//tworzenie gui
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation( (screensize.width - 800)/2,(screensize.height - 600 )/2);
		frame.setSize( new Dimension(500,600) );
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
		/* wyswietlanie tekstu */
		JLabel title = new JLabel("Connect4 game");
		frame.getContentPane().add(title);
		title.setFont(new Font("Serif", Font.PLAIN, 24));
		title.setSize(200,40);
		title.setLocation(140, 15);
		
        label = new JLabel(" ");
        frame.getContentPane().add(label);
        label.setSize(200,20);
        label.setLocation(50,530);
        
        /* menu */
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("New game");
        menu.addMenuListener(menulistener);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        /*JMenuItem submenu = new JMenu("New game");
        submenu.addActionListener(menulistener);
        //submenu.setMnemonic(KeyEvent.VK_S);
        /*JMenuItem menuItem = new JMenuItem("Easy",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addMouseListener(listener);
        submenu.add(menuItem);
        JMenuItem menuItem2 = new JMenuItem("Difficult",
                KeyEvent.VK_T);
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem2.addMouseListener(listener);
        submenu.add(menuItem2);*/
        //menu.add(submenu);
		frame.repaint();
		frame.revalidate();
		frame.doLayout();
		frame.setVisible(true);
	}
	
	public void drawCircle(int column, int row, Player player){
				if(player == Player.Human){
					Circle c = new Circle(col.Yellow);
					tabOfCircle[row][column].add(c);
					tabOfCircle[row][column] = c;
					c.setSize(new Dimension(60,60));
					c.setVisible(true);
				}
				else{
					Circle c = new Circle(col.Red);
					tabOfCircle[row][column].add(c);
					tabOfCircle[row][column] = c;
					c.setSize(new Dimension(60,60));
					c.setVisible(true);
				}	

				
	}
	
    public void writeText(String text){
        label.setText(text);
    }
	
}
