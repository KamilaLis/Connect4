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
public class Scaner {
    int searchForPossibleSlot(int collumn, int[][] board) {
        int freeSlot = 999;
        int j;
            for (j = 6; j >= 0; j--) {
                if (board[j][collumn] == 0) {
                    freeSlot = j;
                    break;
                }
            }
            //System.out.print("collumn: "+ collumn+" row: "+freeSlot+"\n");
        return freeSlot;
    }

    int[] scanVertical(int player, int w, int k, int[][] board) {
        int[] verticalArragement = {999, 999, 999, 999, 999, 999};
        for (int i = 1; i < 4; i++) {
            try {
                if (board[w + i][k] != player && board[w + i][k] != 0) {
                    break;
                } else {
                    if(board[w + i][k] == 0){
                        verticalArragement[3 - i] = 0;
                    } else {
                        verticalArragement[3 - i] = 1;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        for (int i = 1; i < 4; i++) {
            try {
                if (board[w - i][k] != player && board[w - i][k] != 0) {
                    break;
                } else {
                    if(board[w - i][k] == 0){
                        verticalArragement[i + 2] = 0;
                    } else {
                        verticalArragement[i + 2] = 1;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        return verticalArragement;
    }

    int[] scanHorizontal(int player, int w, int k, int[][] board) {
        int[] horizontalArragement = {999, 999, 999, 999, 999, 999};
        for (int i = 1; i < 4; i++) {
            try {
                if (board[w][k - i] != player && board[w][k - i] != 0) {
                    break;
                } else {
                    if(board[w][k - i] == 0){
                        horizontalArragement[3 - i] = 0;
                    } else {
                        horizontalArragement[3 - i] = 1;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        for (int i = 1; i < 4; i++) {
            try {
                if (board[w][k + i] != player && board[w][k + i] != 0) {
                    break;
                } else {
                     //System.out.print(board[w][k + i]+"\n");
                    if(board[w][k + i] == 0){
                        horizontalArragement[i + 2] = 0;
                    } else {
                        horizontalArragement[i + 2] = 1;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        //System.out.print(horizontalArragement[0]+" "+horizontalArragement[1]+" "+horizontalArragement[2]+" "+horizontalArragement[3]+" "+horizontalArragement[4]+" "+horizontalArragement[5]+"\n");
        return horizontalArragement;
    }

    int[] scanDiagonalLeft(int player, int w, int k, int[][] board) {
        int[] diagonalLeftArragement = {999, 999, 999, 999, 999, 999};
        for (int i = 1; i < 4; i++) {
            try {
                if (board[w + i][k - i] != player && board[w + i][k - i] != 0) {
                    break;
                } else {
                    if(board[w + i][k - i] == 0){
                        diagonalLeftArragement[3 - i] = 0;
                    } else {
                        diagonalLeftArragement[3 - i] = 1;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        for (int i = 1; i <= 3; i++) {
            try {
                if (board[w - i][k + i] != player && board[w - i][k + i] != 0) {
                    break;
                } else {
                    if(board[w - i][k + i] == 0){
                        diagonalLeftArragement[i + 2] = 0;
                    } else {
                        diagonalLeftArragement[i + 2] = 1;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        return diagonalLeftArragement;
    }

    int[] scanDiagonalRight(int player, int w, int k, int[][] board) {
        int[] diagonalRightArragement = {999, 999, 999, 999, 999, 999};
        for (int i = 1; i < 4; i++) {
            try {
                if (board[w - i][k - i] != player && board[w - i][k - i] != 0) {
                    break;
                } else {
                    if(board[w - i][k - i] == 0){
                        diagonalRightArragement[3 - i] = 0;
                    } else {
                        diagonalRightArragement[3 - i] = 1;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        for (int i = 1; i <= 3; i++) {
            try {
                if (board[w + i][k + i] != player && board[w + i][k + i] != 0) {
                    break;
                } else {
                    if(board[w + i][k + i] == 0){
                        diagonalRightArragement[i + 2] = 0;
                    } else {
                        diagonalRightArragement[i + 2] = 1;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        return diagonalRightArragement;
    }

    int[][] chceckArragements(int player, int w, int k, int[][] board) {
        int[][] arragements = new int[4][6];
        arragements[0] = scanDiagonalRight(player, w, k, board).clone();
        arragements[1] = scanDiagonalLeft(player, w, k, board).clone();
        arragements[2] = scanVertical(player, w, k, board).clone();
        arragements[3] = scanHorizontal(player, w, k, board).clone();
        return arragements;
    }

    boolean checkForFinalState(int player, int[][] arragements) {
        for (int i = 0; i < 4; i++) {
            int subsequentTrue = 0;
            for (int j = 0; j < 6; j++) {
                //System.out.print("i = "+i+" j = "+j+" arragements[i][j] = "+arragements[i][j]);
                if (arragements[i][j] == 1) {
                    subsequentTrue++;
                    //System.out.print("i = "+i+" j = "+j+" arragements[i][j] = "+arragements[i][j]);
                    if (subsequentTrue == 3) {
                        
                        return true;
                    }
                } else if (arragements[i][j] == 0) {
                    //System.out.print("i = "+i+" j = "+j+" arragements[i][j] = "+arragements[i][j]);
                    subsequentTrue = 0;
                }
            }
            //System.out.print("\n");
        }
        return false;
    }
}