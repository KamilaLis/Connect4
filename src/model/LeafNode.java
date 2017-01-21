
package model;

import model.Tree.nodeType;

public class LeafNode implements Node {

    int value, alpha, beta, row, collumn;
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
        this.row = scan.searchForPossibleSlot(move, tempBoard);
        //tempBoard[row][move] = this.giveCorrespondingPlayer();
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
    public int[][] giveTempBoard(){
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
        int[][] arragement = scan.chceckArragements(this.giveCorrespondingPlayer(), row, collumn, tempBoard);
        if (scan.checkForFinalState(this.giveCorrespondingPlayer(), arragement)) {
            isFinal = true;
            //System.out.print("         Final!\n");
        } else {
            if (this.giveCorrespondingPlayer() == 2) {
                value = (-1) * this.assignator.calculateOverallValue(arragement);
            } else {
                value = this.assignator.calculateOverallValue(arragement);
            }
        }
        //System.out.print("heuristic for " + this.giveCorrespondingPlayer() + " is " + value + "\n");
    }

    //@Override
    public int giveValue() {
        return value;
    }

    //@Override
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

    //@Override
    public void getParentValue(int parentValue) {
        if (!isFinal) {
            value = value + parentValue;
        }
        //System.out.print("         heuristic with parent for " + this.giveCorrespondingPlayer() + " is                    " + value + "\n\n");
    }

    //@Override
    public int giveHeuristicValue() {
        return value;
    }

    //@Override
    public boolean isFinal() {
        return this.isFinal;
    }

    //@Override
    public boolean prune() {
        return false;
    }

    //@Override
    public int giveDecision(){
        return -1;
    }
}
