
package model;


public class ValueAssignator {

    private int pointsForFreeSpot;
    private int pointsForPlayersPuck;

    ValueAssignator(int pointsForFreeSpot, int pointsForPlayersPuck) {
        this.pointsForFreeSpot = pointsForFreeSpot;
        this.pointsForPlayersPuck = pointsForPlayersPuck;
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
        if (possibleCompletsCount > 2) {
            //assingning value of usefullness to the neighbouring slots
            int middle;
            int i = 0;
            int k = 0;
            while (arragement[i] > 1 && i<6) {
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
                middle = (possibleCompletsCount+ 1) / 2 ;
                //System.out.print("middle = " + middle + "\n");
                //System.out.print("odd number of possible complets\n");
                gauges[middle + i - 1] = possibleCompletsCount - 2;
                middle--;
                k++;
                for (int j = (middle + i - 1); middle > 0; middle--, k++, j = (middle + i - 1)) {
                    if (possibleCompletsCount > 3) {
                        gauges[j] = possibleCompletsCount - 2 - k;
                        //System.out.print("middle = "+middle+"\n");
                        //System.out.print("j = "+(j)+"\n");
                        //System.out.print(">3, j = "+j+" j2 = "+(j + 2 * k)+"\n");
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
            return (gauge * this.pointsForFreeSpot);
        } else {
            return (gauge * this.pointsForPlayersPuck);
        }
    }

    int calculateOverallValue(int[][] arragements) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int[] gauge = assignValuesOfGauges(arragements[i]);
            for (int j = 0; j < 6; j++) {
                value += assignPoints(gauge[j], arragements[i][j]);
                //System.out.print("gauge ="+gauge[j]+" slotUseage = "+arragements[i][j]+" value ="+value+"\n");
            }
        }
        return value;
    }
}