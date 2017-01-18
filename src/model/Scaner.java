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
            for (j = 6; j >= 0; --j) {
                if (board[j][collumn] == 0) {
                    freeSlot = j;
                    break;
                }
            }
            System.out.print("collumn: "+ collumn+" row: "+freeSlot+"\n");
        return freeSlot;
    }

    int[] scanVertical(int player, int w, int k, int[][] board) {
        int[] verticalArragement = {999, 999, 999, 999, 999, 999};
        for (int i = 1; i < 4; i++) {
            try {
                if (board[w + i][k] != player && board[w + i][k] != 0) {
                    break;
                } else {
                    verticalArragement[3 - i] = board[w + i][k];
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        for (int i = 1; i <= 3; i++) {
            try {
                if (board[w - i][k] != player && board[w - i][k] != 0) {
                    break;
                } else {
                    verticalArragement[i + 2] = board[w - i][k];
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
                    horizontalArragement[3 - i] = board[w][k - i];
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        for (int i = 1; i <= 3; i++) {
            try {
                if (board[w][k + i] != player && board[w][k + i] != 0) {
                    break;
                } else {
                    horizontalArragement[i + 2] = board[w][k + i];
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
        return horizontalArragement;
    }

    int[] scanDiagonalLeft(int player, int w, int k, int[][] board) {
        int[] diagonalLeftArragement = {999, 999, 999, 999, 999, 999};
        for (int i = 1; i < 4; i++) {
            try {
                if (board[w + i][k - i] != player && board[w + i][k - i] != 0) {
                    break;
                } else {
                    diagonalLeftArragement[3 - i] = board[w + i][k - i];
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
                    diagonalLeftArragement[i + 2] = board[w - i][k + i];
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
                    diagonalRightArragement[3 - i] = board[w - i][k - i];
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
                    diagonalRightArragement[i + 2] = board[w + i][k + i];
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
                if (arragements[i][j] == player) {
                    subsequentTrue++;
                    if (subsequentTrue == 3) {
                        return true;
                    }
                } else if (arragements[i][j] == 0) {
                    subsequentTrue = 0;
                }
            }
        }
        return false;
    }
}