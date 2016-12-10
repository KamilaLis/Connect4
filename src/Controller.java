import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Controller implements MouseListener{

	private Model model;
	private Viewer viewer;

	
	public Controller(){
		this.model = new Model();
		this.viewer = new Viewer();
		viewer.init(); //initialize GUI
		
		
	}
	
	public static void main(String[] args) {
		Controller game = new Controller();

	}

	
	public void setViewer(){
		
	}
	
	public void setModel(){
		
	}
	
	
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("wybralem kolumne");
		Object source = e.getSource();
		//Component source = e.getComponent();
	}


	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

//	public void run() {
		// TODO Auto-generated method stub
		
//	}
	
	

}
