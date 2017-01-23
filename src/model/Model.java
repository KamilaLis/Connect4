
package model;



public class Model{

	public int[][] board;
	public int board_height = 7;
	public int board_width = 6;
	boolean timePassed = false;
	public int decision;
	private int timeOfCalc;
	
	public Model(){
		this.board = new int[board_height][board_width];
	}
	
	
    public void calculateAIMove(int time) throws InterruptedException {
    	this.timeOfCalc = time;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                decision = method(board, timeOfCalc);
                //System.out.print("model: \n");
                System.out.print("decision:" + decision + "\n");
            }
        });
        thread.start();
        long endTimeMillis = System.currentTimeMillis() + time;
        while (thread.isAlive()) {
            if (System.currentTimeMillis() > endTimeMillis) {
                break;
            }
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException t) {}
        }
    }

    static int method(int[][] board, int time) {
        long endTimeMillis = System.currentTimeMillis() + time;
        int decision = 0;
        int depth = 3;
        while (true) {
            // method logic
            Tree t = new Tree(depth, board);
            decision = t.calculateDecision();
            depth++;
            //System.out.print("decision:" + decision + "\n");
            if (System.currentTimeMillis() > endTimeMillis) {
                // do some clean-up
                return decision;
            }
        }
    }
    
}


    


