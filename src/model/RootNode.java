
package model;

import model.Tree.nodeType;

public class RootNode implements Node {

    int value, alpha, beta, decision, newMove;
    int[][] tempBoard;
    Tree.nodeType type;

    RootNode(int[][] board) {
        this.value = -999;
        this.alpha = -999;
        this.beta = 999;
        this.type = nodeType.maximizer;
        this.decision = 0;
        this.newMove = 0;
        this.tempBoard = board;
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
    public int giveValue() {
        return value;
    }

    //@Override
    public void checkIfNewValueIsBetter(int newValue, int move) {
        if (newValue > value) {
            value = newValue;
            decision = newMove;
        }
    }
    
    //@Override
    public void assignValue() {
        //System.out.print("Root node doesn't represent any move, hence assignaton is imposible\n");
    }
    
    //@Override
    public void getParentValue(int parentValue){
        //System.out.print("Root doesn't have a parent\n");
    }
    
    //@Override
    public int giveHeuristicValue(){
        //System.out.print("Root node doesn't represent any move, hence it doesn't have a heuristic value\n");
        return 0;
    }
    
    //@Override
    public boolean isFinal(){
        //System.out.print("Root node doesn't represent any move, hence it can't be a final node\n");
        return false;
    }
    
    //@Override
    public boolean prune(){
        return false;
    }
    
    //@Override
    public int giveDecision(){
        return decision;
    }
}