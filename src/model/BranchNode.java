package model;

import model.Tree.nodeType;

public class BranchNode implements Node {

    int value, alpha, beta, row, collumn, heuristicValue;
    nodeType type;
    Scaner scan = new Scaner();
    ValueAssignator assignator = new ValueAssignator(1, 5);
    int[][] tempBoard = new int[7][6];
    int[][] originalBoard = new int[7][6];
    boolean isFinal = false;
    boolean directFinal = false;
    int numberOfChildren = 0;

    BranchNode(Node parentNode) {
        this.alpha = parentNode.giveAlpha();
        this.beta = parentNode.giveBeta();
        if(parentNode.giveType() == nodeType.maximizer){
            this.type = nodeType.minimizer;
        } else {
            this.type = nodeType.maximizer;
        }
        this.collumn = parentNode.giveNumberOfChildren();
        parentNode.incrementNumberOfChildren();
        //this.isFinal = false;
        int numberOfRows = parentNode.giveTempBoard().length;
        int numberOfCollumns = parentNode.giveTempBoard()[0].length;
        for(int i = 0; i<numberOfRows; i++){
            for(int j = 0; j < numberOfCollumns; j++){
                 this.tempBoard[i][j]=parentNode.giveTempBoard()[i][j];
            }
        }
        for(int i = 0; i<numberOfRows; i++){
            for(int j = 0; j < numberOfCollumns; j++){
                 this.originalBoard[i][j]=parentNode.giveTempBoard()[i][j];
            }
        }
        //this.originalBoard = tempBoard;
        if (type == nodeType.maximizer) {
            this.value = -999;
        } else {
            this.value = 999;
        }
        this.heuristicValue = this.value;
        this.row = scan.searchForPossibleSlot(collumn, tempBoard);
        if(this.row  == 999){
            //this.isFinal = true;
            this.value = (-1)*value;
            if(value < 0){
            	value = value -1;
            } else {
            	value = value +1;
            }
        } else {
        ///System.out.print(row + "\n");
        //System.out.print(collumn + "\n");
        this.tempBoard[row][collumn] = this.giveCorrespondingPlayer();
        this.assignValue();
        if(!isFinal){
            this.getParentValue(parentNode.giveHeuristicValue());
        }
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
    }

    //@Override
    public int giveNumberOfChildren() {
        return numberOfChildren;
    }
    
    //@Override
    public void incrementNumberOfChildren(){
        numberOfChildren++;
    }
    
    //@Override
    public boolean isNextChildNodePossible(){
        if(numberOfChildren < tempBoard[0].length ){
            return true;
        }
        return false;
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
        return this.tempBoard;
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
            //System.out.print("         Final!\n");
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
    public void checkIfNewValueIsBetter(int childValue, boolean isFinal) {
        if (type == nodeType.maximizer) {
            if (childValue >= value) {
                value = childValue;
                alpha = value;
            }
        } else {
            if (childValue <= value) {
                value = childValue;
                beta = value;
            }
        }
        if(isFinal){
            directFinal = true;
        }
    }

   // @Override
    public boolean prune() {
        if (type == nodeType.maximizer) {
            if (beta <= value) {
                //System.out.print("pruned\n");
                return true;
            }
        } else {
            if (alpha >= value) {
                //System.out.print("pruned\n");
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
        //System.out.print("         heuristic with parent for " + this.giveCorrespondingPlayer() + " is                    " + heuristicValue + "\n\n");
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
    
    //@Override
    public boolean foundDirectFinal(){
        return directFinal;
    }
}