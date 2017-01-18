package connect4game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Model;


public class Controller implements MouseListener{

	private Model model;
	private Viewer viewer;
	Player player;
	private boolean isOver;

	
	public Controller(){
		this.model = new Model();
		this.viewer = new Viewer(this);
		this.player = Player.Human; // czlowiek zaczyna
		this.isOver = false;
		viewer.init(); //inicjalizacja GUI
		viewer.writeText("You go first");
		
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
		model.board[column][row]=(player==Player.Human)?1:2;
		//model.current = player;
	}
	
	/* odebranie ruchu komputera */
	public void update(int time) {
		int iaColumn = model.calculateIaMove(time);
		int iaRow = findRoom(iaColumn);
		if (iaRow>=0){
			updateModel(iaColumn, iaRow, player);
			updateViewer(iaColumn,iaRow, player);
			if (isConnect4(player)){
				isOver = true;
			}
			else{
				player = Player.Human;
				viewer.writeText("Your turn");
			}
		}
	}

	/* przyjecie klikniec od gracza */
	public void mouseClicked(MouseEvent e) {
		if (player == Player.Human && !isOver){ 
			if (e.getSource() instanceof Button){
				Button button = (Button)e.getSource();
				int row = findRoom(button.column);
				if (row>=0){
					updateModel(button.column, row,player);
					updateViewer(button.column,row,player);
					if (isConnect4(player)||isBoardFull()){
						isOver = true;
					}
					else {
						player = Player.Computer;
						viewer.writeText("Hmm...");
						update(1000);
					}
				}
			}
		}
	}

	/* szukanie miejsca w kolumnie, sprawdzenie poprawnosci ruchu */
	public int findRoom(int column){
		for (int i=model.board_height-1; i>=0;--i){
			if (model.board[column][i]==0) 
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

	/* sprawdzenie czy na planszy jest czworka */
	public boolean isConnect4(Player player){
		if (findStreak(player,4)>0){
			viewer.writeText(player + " wins!");
			return true;
		}
		return false;
	}
	
	/* sprawdzenie czy zostala zapelniona cala tablica = remis*/
	public boolean isBoardFull(){
		for (int i=0; i<model.board_width;++i){
			for (int j=0; j<model.board_height;++j){
				if (model.board[i][j] == 0){
					return false;
				}
			}
		}
		viewer.writeText("dead-heat");
		return true;
	}
	
	/* szukanie kilku kafli pod rząd dla określenia konca gry */
	public int findStreak(Player player, int lenght){
		/* Parametry: 
		 * 		player - ktorego gracza kafle bierzemy pod uwage
		 * 		lenght - jak dlugiego lancucha szukamy 
		 * 		count - liczba znalezionych lancuchów
		*/
		int count = 0;
		int value = (player==Player.Human)?1:2;
		for (int i=0; i<model.board_width;++i){
			for (int j=model.board_height-1; j>=0;--j){
				if (model.board[i][j] == value){
					count += this.findVerticalStreak(i,j,lenght);
					count += this.findHorizontalStreak(i,j,lenght);
					count += this.findDiagonalUpStreak(i,j,lenght);
					count += this.findDiagonalDownStreak(i,j,lenght);
				}
			}
		}
		return count;
	}
	
	public int findVerticalStreak(int column, int row, int lenght){
		int streak =0;
		if (row-lenght+1>=0){
			for (int i=1; i<=lenght-1; ++i){
				if (model.board[column][row]==model.board[column][row-i]){
					streak+=1;
				}
				else break;
			}
		}
		if (streak==lenght-1) return 1;
		else return 0;
	}
	
	public int findHorizontalStreak(int column, int row, int lenght){
		int streak =0;
		if (column+lenght<=model.board_width){
			for (int i=1; i<=lenght-1; ++i){
				if (model.board[column][row]==model.board[column+i][row]){
					streak+=1;
				}
				else break;
			}
		}
		if (streak==lenght-1) return 1;
		else return 0;
	}
	
	public int findDiagonalUpStreak(int column, int row, int lenght){
		int streak =0;
		if (row-lenght+1>=0 && column+lenght<=model.board_width){
			for (int i=1; i<=lenght-1; ++i){
				if (model.board[column][row]==model.board[column+i][row-i]){
					streak+=1;
				}
				else break;
			}
		}
		if (streak==lenght-1) return 1;
		else return 0;
	}
	
	public int findDiagonalDownStreak(int column, int row, int lenght){
		int streak =0;
		if (lenght-row-1>=0 && lenght-column-2>=0){
			for (int i=1; i<=lenght-1; ++i){
				if (model.board[column][row]==model.board[column+i][row+i]){
					streak+=1;
				}
				else break;
			}
		}
		if (streak==lenght-1) return 1;
		else return 0;
	}
	
	

}
