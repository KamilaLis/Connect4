package model;

import model.Tree.nodeType;

public class BranchNode implements Node {

    int value, alpha, beta, row, collumn, heuristicValue;
    nodeType type;
    Scaner scan = new Scaner();
    ValueAssignator assignator = new ValueAssignator(1, 3);
    int[][] tempBoard = new int[7][6];
    int[][] originalBoard = new int[7][6];
    boolean isFinal;

    BranchNode(int alpha, int beta, nodeType type, int move, int[][] tempBoard) {
        this.alpha = alpha;
        this.beta = beta;
        this.type = type;
        this.collumn = move;
        this.isFinal = false;
        for(int i = 0; i<7; i++){
            for(int j = 0; j < 6; j++){
                this.tempBoard[i][j]=tempBoard[i][j];
            }
        }
        //this.originalBoard = tempBoard;
        for(int i = 0; i<7; i++){
            for(int j = 0; j < 6; j++){
                originalBoard[i][j]=tempBoard[i][j];
            }
        }
        if (type == nodeType.maximizer) {
            this.value = -999;
        } else {
            this.value = 999;
        }
        this.heuristicValue = this.value;
        this.row = scan.searchForPossibleSlot(move, tempBoard);
        this.tempBoard[row][move] = this.giveCorrespondingPlayer();
        /*for(int i = 0; i<7; i++){
            for(int j = 0; j < 6; j++){
                System.out.print(originalBoard[i][j]+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        for(int i = 0; i<7; i++){
            for(int j = 0; j < 6; j++){
                System.out.print(this.tempBoard[i][j]+" ");
            }
            System.out.print("\n");
        }*/
    }

    //@Override
    public int giveCorrespondingPlayer() {
        if (this.type == nodeType.maximizer) {
            return 2;
        } else {
            return 1;
        }
    }

    //@Override
    public int[][] giveTempBoard() {
        return tempBoard;
    }

    //@Override
    public nodeType giveType() {
        return type;
    }

    //@Override
    public int giveBeta() {
        return beta;
    }

    //@Override
    public int giveAlpha() {
        return alpha;
    }

    //@Override
    public void assignValue() {
        int[][] arragement = scan.chceckArragements(this.giveCorrespondingPlayer(), row, collumn, originalBoard);
        if (scan.checkForFinalState(this.giveCorrespondingPlayer(), arragement)) {
            isFinal = true;
            System.out.print("         Final!\n");
        } else {
            if (this.giveCorrespondingPlayer() == 2) {
                heuristicValue = (-1) * this.assignator.calculateOverallValue(arragement);
            } else {
                heuristicValue = this.assignator.calculateOverallValue(arragement);
            }
        }
        //System.out.print("heuristic for "+this.giveCorrespondingPlayer()+" is "+heuristicValue+"\n");
    }

    //@Override
    public int giveValue() {
        return value;
    }

    //@Override
    public void checkIfNewValueIsBetter(int childValue, int move) {
        if (type == nodeType.maximizer) {
            if (childValue > value) {
                value = childValue;
                alpha = value;
            }
        } else {
            if (childValue < value) {
                value = childValue;
                beta = value;
            }
        }
    }

    //@Override
    public boolean prune() {
        if (type == nodeType.maximizer) {
            if (beta < value) {
                System.out.print("pruned\n");
                return true;
            }
        } else {
            if (alpha > value) {
                System.out.print("pruned\n");
                return true;
            }
        }
        return false;
    }

    //@Override
    public void getParentValue(int parentValue) {
        if (!isFinal) {
            heuristicValue = heuristicValue + parentValue;
        }
        System.out.print("         heuristic with parent for " + this.giveCorrespondingPlayer() + " is                    " + heuristicValue + "\n\n");
    }

    //@Override
    public int giveHeuristicValue() {
        return heuristicValue;
    }

    //@Override
    public boolean isFinal() {
        return this.isFinal;
    }
    
    //@Override
    public int giveDecision(){
        return -1;
    }
}