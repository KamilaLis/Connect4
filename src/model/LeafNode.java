
package model;

import model.Tree.nodeType;

public class LeafNode implements Node {

    int value, alpha, beta, row, collumn, heuristicValue;
    nodeType type;
    Scaner scan = new Scaner();
    ValueAssignator assignator = new ValueAssignator(1,3);
    int[][] tempBoard;
    boolean isFinal;

    LeafNode(int alpha, int beta, nodeType type, int move, int[][] tempBoard) {
        this.alpha = alpha;
        this.beta = beta;
        this.type = type;
        this.collumn = move;
        this.isFinal = false;
        this.tempBoard = tempBoard;
        if (type == nodeType.maximizer) {
            this.value = -999;
        } else {
            this.value = 999;
        }
        this.heuristicValue = this.value;
        this.row = scan.searchForPossibleSlot(move, tempBoard);
        //tempBoard[row][move] = this.giveCorrespondingPlayer();
    }


    public int giveCorrespondingPlayer() {
        if (this.type == nodeType.maximizer) {
            return 2;
        } else {
            return 1;
        }
    }
    

    public int[][] giveTempBoard(){
        return tempBoard;
    }

    public nodeType giveType(){
        return type;
    }
    

    public int giveBeta(){
        return beta;
    }
    

    public int giveAlpha(){
        return alpha;
    }


    public void assignValue() {
        int[][] arragement = scan.chceckArragements(this.giveCorrespondingPlayer(), row, collumn, tempBoard);
        if (scan.checkForFinalState(this.giveCorrespondingPlayer(), arragement)) {
            heuristicValue = heuristicValue * (-1);
            isFinal = true;
        } else {
            heuristicValue = this.assignator.calculateOverallValue(arragement);
        }
    }


    public int giveValue() {
        return value;
    }


    public void checkIfNewValueIsBetter(int newValue, int move) {
        if (type == nodeType.maximizer) {
            if (newValue > value) {
                value = newValue;
            }
        } else {
            if (newValue < value) {
                value = newValue;
            }
        }
    }
    

    public void getParentValue(int parentValue){
        value = value + parentValue;
    }
    

    public int giveHeuristicValue(){
        return heuristicValue;
    }
    

    public boolean isFinal(){
        return this.isFinal;
    }
    

    public boolean prune(){
        return false;
    }
    

    public int giveDecision(){
        return -1;
    }
}
