package connect4game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Controller implements MouseListener{

	private Model model;
	private Viewer viewer;
	Player player;

	
	public Controller(){
		this.model = new Model();
		this.viewer = new Viewer(this);
		this.player = Player.Human; // human goes first
		viewer.init(); //initialize GUI
		
	}
	
	public static void main(String[] args) {
		Controller game = new Controller();
	}

	/* update widoku, czyli wyrysowanie stany */
	public void updateViewer(){
		//bin2swing(model.gameState.board);
		//drawBoard();
	}
	
	/* update modelu po odebraniu ruchu */
	public void updateModel(int column, int line, Player player){
		model.gameState.board[column][line] = (player==Player.Human)?1:-1;
	}
	
	/* przyjecie klikniec od gracza */
	public void mouseClicked(MouseEvent e) {
		if (player == Player.Human){
			if (e.getSource() instanceof Button){
				Button button = (Button)e.getSource();
				if (model.findRoom(button.column)>=0){
					updateModel(button.column, model.findRoom(button.column),player);
				}
			}
			//Component source = e.getComponent();
			player = Player.Computer;
		}

	}


	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	
	

}
