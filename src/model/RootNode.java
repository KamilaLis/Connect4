
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


    public int giveCorrespondingPlayer() {
        if (this.type == nodeType.maximizer) {
            return 2;
        } else {
            return 1;
        }
    }


    public int[][] giveTempBoard() {
        return tempBoard;
    }


    public nodeType giveType() {
        return type;
    }


    public int giveBeta() {
        return beta;
    }

    public int giveAlpha() {
        return alpha;
    }


    public int giveValue() {
        return value;
    }


    public void checkIfNewValueIsBetter(int newValue, int move) {
        if (newValue > value) {
            value = newValue;
            decision = newMove;
        }
    }
    

    public void assignValue() {
        System.out.print("Root node doesn't represent any move, hence assignaton is imposible\n");
    }

    public void getParentValue(int parentValue){
        System.out.print("Root doesn't have a parent\n");
    }
    

    public int giveHeuristicValue(){
        System.out.print("Root node doesn't represent any move, hence it doesn't have a heuristic value\n");
        return 0;
    }
    

    public boolean isFinal(){
        System.out.print("Root node doesn't represent any move, hence it can't be a final node\n");
        return false;
    }
    

    public boolean prune(){
        return false;
    }
    

    public int giveDecision(){
        return decision;
    }
}