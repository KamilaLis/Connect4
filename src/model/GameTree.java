/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Plk. Borkow
 */
public interface GameTree {
    //Node createRootNode();
    //Node createChildNode(Node Parent, int level);
    void createNode(Node parentNode);
    int calculateDecision();
}