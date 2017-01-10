package connect4game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;


public class Controller implements MouseListener, Observer{

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

	/* update widoku */
	public void updateViewer(int column, int row, Player player){
		viewer.drawCircle(column, row, player);
	}
	
	/* update modelu po odebraniu ruchu */
	public void updateModel(int column, int row, Player player){
		model.gameState.board[column][row]=(player==Player.Human)?1:-1;
		model.gameState.current = player;
	}
	
	/* odebranie ruchu komputera */
	public void update(Observable o, Object arg) {
		/* int iaColumn = ((Model) o).getColumnNumber();
		int iaRow = findRoom(iaColumn);
		if (row>=0){
			updateModel(iaColumn, iaRow, player);
			updateViewer(iaColumn,iaRow, player);
			player = Player.Human;
		}
		*/
	}

	/* przyjecie klikniec od gracza */
	public void mouseClicked(MouseEvent e) {
		if (player == Player.Human){ //nie przyjmuj klikniec jesli kolej IA
			if (e.getSource() instanceof Button){
				Button button = (Button)e.getSource();
				int row = findRoom(button.column);
				if (row>=0){
					updateModel(button.column, row,player);
					updateViewer(button.column,row,player);
					player = Player.Computer;
				}
			}
		}
	}

	/* szukanie miejsca w kolumnie, sprawdzenie poprawnosci ruchu */
	public int findRoom(int column){
		for (int i=model.board_height-1; i>=0;--i){
			System.out.println("findRoom-column: "+column);
			System.out.println("findRoom-row: "+i);
			if (model.gameState.board[column][i]==0) 
				return i;
		}
		return -1;
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
