package connect4game;

public class Model {

	public State gameState;
	
	public int findRoom(int column){
		for (int item : gameState.board[column]){
			if (gameState.board[column][item]==0) //jezeli robimy board binarnie
				return item;
		}
		return -1;
	}
}
