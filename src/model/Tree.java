package model;

class Tree implements GameTree{
    public static boolean loop = true;
    protected enum nodeType {
        minimizer, maximizer
    };
    int level = 0;
    int depth;
    int[][] originalBoard;

    Tree(int depth, int[][] originalBoard) {
        this.depth = depth;
        this.originalBoard = originalBoard;
    }

    //@Override
    public void createNode(Node parentNode) {
        
        if (level + 2 != depth) {
            //System.out.print("         Created BranchNode\n");
            Node newNode = new BranchNode(parentNode);
            level++;
            while (!newNode.prune() && newNode.isNextChildNodePossible() && !newNode.isFinal()) {
                createNode(newNode);
            }
            parentNode.checkIfNewValueIsBetter(newNode.giveValue(), newNode.isFinal());
            level--;

        } else {
            //System.out.print("         Created LeafNode\n");
            Node newNode = new LeafNode(parentNode);
            parentNode.checkIfNewValueIsBetter(newNode.giveValue(), newNode.isFinal());
        }
    }

    //@Override
    public int calculateDecision() {
        Node root = new RootNode(originalBoard);
        while(root.isNextChildNodePossible() && !root.foundDirectFinal()){
            createNode(root);
        }
        return root.giveDecision();
    }
}

/*class Tree implements GameTree {

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

    @Override
    public Node createChildNode(Node Parent, int level) {
        lol++;
        //System.out.print(level+" "+numberOfActiveNodes[level]+"\n");
        numberOfActiveNodes[level]++;
        System.out.print("lol= " + lol + "\n");
        System.out.print("level: " + level + " no. of active nodes " + numberOfActiveNodes[level] + "\n");
        if (level + 1 == depth) {
            if (Parent.giveType() == nodeType.maximizer) {
                System.out.print("         Created LeafNode, minimizer, no:" + (numberOfActiveNodes[level] - 1) + "\n");
                return new LeafNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.minimizer, (numberOfActiveNodes[level] - 1),
                        Parent.giveTempBoard());
            } else {
                System.out.print("         Created LeafNode, maximizer, no:" + (numberOfActiveNodes[level] - 1) + "\n");
                return new LeafNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.maximizer, (numberOfActiveNodes[level] - 1),
                        Parent.giveTempBoard());
            }
        } else {
            if (Parent.giveType() == nodeType.maximizer) {
                System.out.print("         Created BranchNode, minimizer, no:" + (numberOfActiveNodes[level] - 1) + "\n");
                return new BranchNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.minimizer, (numberOfActiveNodes[level] - 1),
                        Parent.giveTempBoard());
            } else {
                System.out.print("         Created BramchNode, maximizer, no:" + (numberOfActiveNodes[level] - 1) + "\n");
                return new BranchNode(Parent.giveAlpha(), Parent.giveBeta(),
                        nodeType.maximizer, (numberOfActiveNodes[level] - 1),
                        Parent.giveTempBoard());
            }

        }
    }

    @Override
    public RootNode createRootNode() {
        return new RootNode(originalBoard);
    }

    @Override
    public int calculateDecision() {
        int level = 0;
        Node[] nodeArray = new Node[depth];
        nodeArray[level] = createRootNode();
        ++level;
        while (level > 0) {
            
            System.out.print(level + "\n");
                if (level == (depth - 1) && this.numberOfActiveNodes[level] < 6) {

                    if (this.numberOfActiveNodes[level] > 0 && nodeArray[level - 1].prune()) {
                        nodeArray[level - 2].checkIfNewValueIsBetter(nodeArray[level - 1].giveValue(), this.numberOfActiveNodes[level - 1]);
                        this.numberOfActiveNodes[level] = 0;
                        level = level - 2;
                    } else {
                        nodeArray[level] = this.createChildNode(nodeArray[level - 1], level);
                        int[][] s = nodeArray[level - 1].giveTempBoard();
                        //System.out.print("Miejsce nefralgiczne: "+s[3][0]+"\n");
                        nodeArray[level].assignValue();
                        //System.out.print("Parent value = "+nodeArray[level - 1].giveHeuristicValue()+"\n");
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
                        //System.out.print("value: "+nodeArray[level].giveHeuristicValue()+"\n");
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
}*/