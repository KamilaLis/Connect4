package connect4game;

public class Model {

	public State gameState;
	int board_height = 6;
	int board_width = 7;
	
	public Model(){
		this.gameState = new State();
	}
	
	
	public int findRoom(int column){
		for (int i=board_height-1; i>=0;--i){
			if (gameState.board[column][i]==0) 
				return i;
		}
		return -1;
	}
	
	/* szukanie kilku kafli pod rząd dla określenia funkcji heurystycznej */
	public int findStreak(Player player, int lenght){
		/* Parametry: 
		 * 		player - ktorego gracza kafle bierzemy pod uwagę
		 * 		lenght - jak długiego łańcucha szukamy 
		 * 		count - liczba znalezionych łańcuchów
		*/
		int count = 0;
		int value = (player==Player.Human)?1:-1;
		for (int i=0; i<board_width;++i){
			for (int j=0; j<board_height; ++j){
				if (this.gameState.board[i][j] == value){
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
		if (row+lenght<=this.board_height){
			for (int i=1; i<=lenght; ++i){
				if (gameState.board[column][row]==gameState.board[column][row+i]){
					streak+=1;
				}
				else break;
			}
		}
		if (streak==lenght) return 1;
		else return 0;
	}
	
	public int findHorizontalStreak(int column, int row, int lenght){
		int streak =0;
		if (column+lenght<=this.board_width){
			for (int i=1; i<=lenght; ++i){
				if (gameState.board[column][row]==gameState.board[column+i][row]){
					streak+=1;
				}
				else break;
			}
		}
		if (streak==lenght) return 1;
		else return 0;
	}
	
	public int findDiagonalUpStreak(int column, int row, int lenght){
		int streak =0;
		if (row+lenght<=this.board_height && column+lenght<=this.board_width){
			for (int i=1; i<=lenght; ++i){
				if (gameState.board[column][row]==gameState.board[column+i][row+i]){
					streak+=1;
				}
				else break;
			}
		}
		if (streak==lenght) return 1;
		else return 0;
	}
	
	public int findDiagonalDownStreak(int column, int row, int lenght){
		int streak =0;
		if (row+lenght<=this.board_height && column+lenght<=this.board_width){
			for (int i=1; i<=lenght; ++i){
				if (gameState.board[column][row]==gameState.board[column+i][row-i]){
					streak+=1;
				}
				else break;
			}
		}
		if (streak==lenght) return 1;
		else return 0;
	}
}
