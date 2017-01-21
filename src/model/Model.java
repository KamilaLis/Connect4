
package model;
import java.time.*;



public class Model{

	public int[][] board;
	public int board_height = 7;
	public int board_width = 6;
	//public int decision;
	
	public Model(){
		this.board = new int[board_height][board_width];
	}
	
	
    public int calculateIaMove(int time){
    	int [][]board = this.board;
        Instant i1;
        i1 = Instant.now();
        int depth = 4;
        int decision = 0;
        while(Instant.now().toEpochMilli()<i1.toEpochMilli()+time){
            Tree t = new Tree(depth, board);
            decision = t.calculateDecision();
            depth++;
            Instant i3;
            System.out.print("decision:"+decision+"\n");
        }
        return decision;
    }
    

}

    


