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
    int lol = 0;
    Tree(int depth, int[][] originalBoard) {
        this.numberOfActiveNodes = new int[depth];
        for (int i = 0; i < depth; i++) {
            this.numberOfActiveNodes[i] = 0;//System.out.print("d");
        }
        this.scaner = new Scaner();
        this.originalBoard = originalBoard;
        this.depth = depth;
        this.assignator = new ValueAssignator(1, 3);
    }

    //@Override
    public Node createChildNode(Node Parent, int level) {
        lol++;
        //System.out.print(level+" "+numberOfActiveNodes[level]+"\n");
        numberOfActiveNodes[level]++;
        //System.out.print("lol= " + lol + "\n");
        //System.out.print("level: " + level + " no. of active nodes " + numberOfActiveNodes[level] + "\n");
        if (level + 1 == depth) {
            if (Parent.giveType() == nodeType.maximizer) {
                //System.out.print("         Created LeafNode, minimizer, no:" + (numberOfActiveNodes[level] - 1) + "\n");
                return new LeafNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.minimizer, (numberOfActiveNodes[level] - 1),
                        Parent.giveTempBoard());
            } else {
                //System.out.print("         Created LeafNode, maximizer, no:" + (numberOfActiveNodes[level] - 1) + "\n");
                return new LeafNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.maximizer, (numberOfActiveNodes[level] - 1),
                        Parent.giveTempBoard());
            }
        } else {
            if (Parent.giveType() == nodeType.maximizer) {
                //System.out.print("         Created BranchNode, minimizer, no:" + (numberOfActiveNodes[level] - 1) + "\n");
                return new BranchNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.minimizer, (numberOfActiveNodes[level] - 1),
                        Parent.giveTempBoard());
            } else {
                //System.out.print("         Created BramchNode, maximizer, no:" + (numberOfActiveNodes[level] - 1) + "\n");
                return new BranchNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.maximizer, (numberOfActiveNodes[level] - 1),
                        Parent.giveTempBoard());
            }

        }
    }

    //@Override
    public RootNode createRootNode() {
        return new RootNode(originalBoard);
    }

    //@Override
    public int calculateDecision() {
        int level = 0;
        Node[] nodeArray = new Node[depth];
        nodeArray[level] = createRootNode();
        ++level;
        while (level > 0) {
            
                if (level == (depth - 1) && this.numberOfActiveNodes[level] < 6) {

                    if (this.numberOfActiveNodes[level] > 0 && nodeArray[level - 1].prune()) {
                        nodeArray[level - 2].checkIfNewValueIsBetter(nodeArray[level - 1].giveValue(), this.numberOfActiveNodes[level - 1]);
                        this.numberOfActiveNodes[level] = 0;
                        level = level - 2;
                    } else {
                        nodeArray[level] = this.createChildNode(nodeArray[level - 1], level);
                        //int[][] s = nodeArray[level - 1].giveTempBoard();
                        //System.out.print("Miejsce nefralgiczne: "+s[3][0]+"\n");
                        nodeArray[level].assignValue();
                        System.out.print("Parent value = "+nodeArray[level - 1].giveHeuristicValue()+"\n");
                        nodeArray[level].getParentValue(nodeArray[level - 1].giveHeuristicValue());
                        nodeArray[level - 1].checkIfNewValueIsBetter(nodeArray[level].giveHeuristicValue(), this.numberOfActiveNodes[level]);
                        level--;
                    }
                } else if (this.numberOfActiveNodes[level] < 6) {
                    if (this.numberOfActiveNodes[level] > 0 && nodeArray[level - 1].prune()) {
                        nodeArray[level - 1].checkIfNewValueIsBetter(nodeArray[level].giveValue(), this.numberOfActiveNodes[level]);
                        level = level - 2;
                    } else {
                        nodeArray[level] = this.createChildNode(nodeArray[level - 1], level);
                        //int[][] s = nodeArray[level - 1].giveTempBoard();
                        //System.out.print("Miejsce nefralgiczne: "+s[3][0]+"\n");
                        nodeArray[level].assignValue();
                        System.out.print("value: "+nodeArray[level].giveHeuristicValue()+"\n");
                        if (nodeArray[level].isFinal()) {
                            nodeArray[level - 1].checkIfNewValueIsBetter(nodeArray[level].giveHeuristicValue(), this.numberOfActiveNodes[level]);
                            level--;
                        } else {
                            nodeArray[level].getParentValue(nodeArray[level - 1].giveHeuristicValue());
                        }
                    }
                } else {
                    if (level - 2 > -1) {
                        nodeArray[level - 2].checkIfNewValueIsBetter(nodeArray[level - 1].giveValue(), this.numberOfActiveNodes[level - 1]);
                    }
                    this.numberOfActiveNodes[level] = 0;
                    level = level - 2;
                }
                ++level;
           }
        System.out.print("simsim\n\n");
        return nodeArray[0].giveDecision();
    }
}
    
    
    /*
01 function alphabeta(node, depth, α, β, maximizingPlayer)
02      if depth = 0 or node is a terminal node
03          return the heuristic value of node
04      if maximizingPlayer
05          v := -∞
06          for each child of node
07              v := max(v, alphabeta(child, depth – 1, α, β, FALSE))
08              α := max(α, v)
09              if β ≤ α
10                  break (* β cut-off *)
11          return v
12      else
13          v := ∞
14          for each child of node
15              v := min(v, alphabeta(child, depth – 1, α, β, TRUE))
16              β := min(β, v)
17              if β ≤ α
18                  break (* α cut-off *)
19          return v*/
    
/*    public int alphabeta(Node node, int depth, int alpha, int beta, nodeType type){
    	Node[] children = new Node[6];
    	for (int i=0; i<6; ++i){
    		children[i] = createChildNode(this, level);
    	}
    	if (depth==0 || node.isFinal()){
    		return node.giveHeuristicValue();
    	}
    	if (type==nodeType.maximizer){
    		int value = -999;
    		for (int i=0; i<6; ++i){
    			value = children[i].giveValue()>alphabeta(children[i],depth-1,alpha,beta,nodeType.minimizer)?children[i].giveValue():alphabeta(children[i],depth-1,alpha,beta,nodeType.minimizer);
    			//children[i].setValue(value);
    			alpha = alpha>value?alpha:value;
    			if (beta <= alpha){
    				break;
    			}
    		}
    		return value;
    	}
    	else{
    		int value = 999;
    		for (int i=0; i<6; ++i){
    			
    		}
    	}
    	return 1;
    }*/
    

