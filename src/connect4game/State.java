package connect4game;

public class State {

	int[][] board; 
	Player current; //czyja kolej teraz
	//wartosc heurystyczna

	public State(){
		this.board = new int[7][6];
	}
	
}
