/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Tree.nodeType;

/**
 *
 * @author Plk. Borkow
 */
public interface Node {
    boolean foundDirectFinal();
    void incrementNumberOfChildren();
    void getParentValue(int parentValue);
    void assignValue();
    void checkIfNewValueIsBetter(int newValue, boolean isFinal);
    int giveHeuristicValue();
    int giveValue();
    int giveNumberOfChildren();
    boolean isNextChildNodePossible();
    int giveCorrespondingPlayer();
    int[][] giveTempBoard();
    nodeType giveType();
    int giveBeta();
    int giveAlpha();
    boolean isFinal();
    boolean prune();
    int giveDecision();
}