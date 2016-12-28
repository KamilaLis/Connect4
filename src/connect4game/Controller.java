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
	public void updateModel(int column, int row, Player player){
		//zalozylam zapis 1 gracz, -1 ia, można zmienic!
		model.gameState.board[column][row] = (player==Player.Human)?1:-1;
		model.gameState.current = player;
	}
	
	/* przyjecie klikniec od gracza */
	public void mouseClicked(MouseEvent e) {
		if (player == Player.Human){ //nie przyjmuj klikniec jesli kolej IA
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
