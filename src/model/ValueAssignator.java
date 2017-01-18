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
public class ValueAssignator {

    private int pointsForFreeSpot;
    private int pointsForPlayersPuck;

    ValueAssignator(int pointsForFreeSpot, int pointsForPlayersDot) {
        this.pointsForFreeSpot = pointsForFreeSpot;
        //System.out.print("Points for free slot = " + pointsForFreeSpot);
        this.pointsForFreeSpot = pointsForFreeSpot;
        //System.out.print("Points for player's puck = " + pointsForPlayersPuck);
    }

    int[] assignValuesOfGauges(int[] arragement) {
        int possibleCompletsCount = 0;
        int[] gauges = {0, 0, 0, 0, 0, 0};
        //calculating value os possible complets of fours in the given arragement
        for (int i = 0; i < 6; i++) {
            if (arragement[i] < 999) {
                possibleCompletsCount++;
            }
        }
        System.out.print("possibleCompletsCount = " + possibleCompletsCount + "\n");
        if (possibleCompletsCount > 1) {
            //assingning value of usefullness to the neighbouring slots
            int middle;
            int i = 0;
            int k = 0;
            while (arragement[i] > 1) {
                i++;
            }
            if ((possibleCompletsCount % 2) == 0) {
                middle = possibleCompletsCount / 2;
                for (int j = middle + i - 1; middle > 0; middle--, k++, j = (middle + i - 1)) {
                    if (possibleCompletsCount < 6) {
                        gauges[j] = possibleCompletsCount - 2 - k;
                        gauges[j + 1 + k * 2] = possibleCompletsCount - 2 - k;
                    } else {
                        gauges[j] = possibleCompletsCount - 3 - k;
                        gauges[j + 1 + k * 2] = possibleCompletsCount - 3 - k;
                    }
                }
            } else {
                middle = possibleCompletsCount / 2 + 1;
                System.out.print("middle = " + middle + "\n");
                gauges[middle + i - 1] = possibleCompletsCount - 2;
                middle--;
                k++;
                for (int j = (middle + i - 1); middle > 0; middle--, k++, j = (middle + i - 1)) {
                    if (possibleCompletsCount > 3) {
                        gauges[j] = possibleCompletsCount - 2 - k;
                        // System.out.print("middle = "+middle+"\n");
                        // System.out.print("j = "+(j)+"\n");
                        gauges[j + 2 * k] = possibleCompletsCount - 2 - k;
                    } else {
                        gauges[j] = possibleCompletsCount - 2;
                        gauges[j + 2 * k] = possibleCompletsCount - 2;
                    }
                }
            }
        }
        return gauges;
    }

    int assignPoints(int gauge, int slotUseage) {
        if (slotUseage == 0) {
            return gauge * this.pointsForFreeSpot;
        } else {
            return gauge * this.pointsForPlayersPuck;
        }
    }

    int calculateOverallValue(int[][] arragements) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int[] gauge = assignValuesOfGauges(arragements[i]);
            for (int j = 0; j < 6; j++) {
                value += assignPoints(gauge[j], arragements[i][j]);
            }
        }
        return value;
    }
}