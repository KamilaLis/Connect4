package model;

class Tree implements GameTree{

    protected enum nodeType {
        minimizer, maximizer
    };
    Scaner scaner;
    ValueAssignator assignator;
    int[][] originalBoard;
    int[] numberOfActiveNodes;
    int depth;
    
    Tree(int depth, int[][] originalBoard) {
        this.numberOfActiveNodes = new int[depth];
        for(int i=0; i<depth; i++){
            this.numberOfActiveNodes[i]=0;//System.out.print("d");
        }
        this.scaner = new Scaner();
        this.originalBoard = originalBoard;
        this.depth = depth;
        this.assignator = new ValueAssignator(1,3);
    }


    public Node createChildNode(Node Parent, int level) {
        //System.out.print(level+" "+numberOfActiveNodes[level]+"\n");
        numberOfActiveNodes[level]++;
        System.out.print(level+" "+numberOfActiveNodes[level]+"\n");
        
        if (level + 1 == depth) {
            if (Parent.giveType() == nodeType.maximizer) {
                System.out.print("Created LeafNode, minimizer, no:" + (numberOfActiveNodes[level]-1)+"\n");
                return new LeafNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.minimizer, numberOfActiveNodes[level]-1,
                        Parent.giveTempBoard());
            } else {
                System.out.print("Created LeafNode, maximizer, no:" + (numberOfActiveNodes[level]-1)+"\n");
                return new LeafNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.maximizer, numberOfActiveNodes[level]-1,
                        Parent.giveTempBoard());
            }
        } else {
            if (Parent.giveType() == nodeType.maximizer) {
                System.out.print("Created BranchNode, minimizer, no:" + (numberOfActiveNodes[level]-1)+"\n");
                return new BranchNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.minimizer, numberOfActiveNodes[level]-1,
                        Parent.giveTempBoard());
            } else {
                System.out.print("Created BramchNode, maximizer, no:" + (numberOfActiveNodes[level]-1)+"\n");
                return new BranchNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.maximizer, numberOfActiveNodes[level]-1,
                        Parent.giveTempBoard());
            }

        }
    }

    public RootNode createRootNode(){
        return new RootNode(originalBoard);
    }
    

    public int calculateDecision() {
        int level = 0;
        Node[] nodeArray = new Node[depth];
        nodeArray[level] = createRootNode();
        while (++level > 0) {
            if (level == (depth - 1) && this.numberOfActiveNodes[level] < 6) {
                
                if (this.numberOfActiveNodes[level] > 0 && nodeArray[level - 1].prune()) {
                    nodeArray[level - 2].checkIfNewValueIsBetter(nodeArray[level - 1].giveValue(),this.numberOfActiveNodes[level-1]);
                    level = level - 2;
                    this.numberOfActiveNodes[level] = 0;
                    break;
                }
                nodeArray[level] = this.createChildNode(nodeArray[level - 1], level);
                nodeArray[level].assignValue();
                nodeArray[level].getParentValue(nodeArray[level - 1].giveHeuristicValue());
                nodeArray[level - 1].checkIfNewValueIsBetter(nodeArray[level].giveHeuristicValue(),this.numberOfActiveNodes[level]);
                level--;
            } else if (this.numberOfActiveNodes[level] < 6) {
                if (this.numberOfActiveNodes[level] > 0 && nodeArray[level - 1].prune()) {
                    nodeArray[level - 1].checkIfNewValueIsBetter(nodeArray[level].giveValue(),this.numberOfActiveNodes[level]);
                    level = level - 2;
                    break;
                }
                nodeArray[level] = this.createChildNode(nodeArray[level - 1], level);
                nodeArray[level].assignValue();
                if (nodeArray[level].isFinal()) {
                    nodeArray[level - 1].checkIfNewValueIsBetter(nodeArray[level].giveHeuristicValue(),this.numberOfActiveNodes[level]);
                    level--;
                    break;
                }
                nodeArray[level].getParentValue(nodeArray[level - 1].giveHeuristicValue());
            } else {
                if(level - 2 > -1){
                    nodeArray[level - 2].checkIfNewValueIsBetter(nodeArray[level-1].giveValue(),this.numberOfActiveNodes[level-1]);
                }
                this.numberOfActiveNodes[level] = 0; 
                level = level - 2;
            }
        }
        return nodeArray[0].giveDecision();
    }
    

}
