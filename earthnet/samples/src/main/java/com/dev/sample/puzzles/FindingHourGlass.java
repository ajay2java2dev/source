package com.dev.sample.puzzles;

/**
 * Created by AjayMenon on 2/15/2017.
 */
public class FindingHourGlass {

    public static int[] convertLineToArray(String line) {
        String[] a = line.split(" ");
        int[] a1 = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            a1[i] = Integer.parseInt(a[i]);
        }
        return a1;
    }

    public static void main(String[] args) {

        //replace below with input from System console.
        /*
        1 1 1 0 0 0
        0 1 0 0 0 0
        1 1 1 0 0 0
        0 0 2 4 4 0
        0 0 0 2 0 0
        0 0 1 2 4 0
        */

        int[][] a = new int[6][6];

        a[0] = convertLineToArray(new String("1 1 1 0 0 0"));
        a[1] = convertLineToArray(new String("0 1 0 0 0 0"));
        a[2] = convertLineToArray(new String("1 1 1 0 0 0"));
        a[3] = convertLineToArray(new String("0 0 2 4 4 0"));
        a[4] = convertLineToArray(new String("0 0 0 2 0 0"));
        a[5] = convertLineToArray(new String("0 0 1 2 4 0"));


        FindingHourGlass hourGlass = new FindingHourGlass();
        System.out.println(hourGlass.getLargestHourGlassSum(a));
    }

    public int getLargestHourGlassSum(int[][] a) {
        int sum = 0;
        int maxSum = 0;

        int count = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (a[i][j] != 0) {
                    sum = sum + a[i][j];
                    count++;

                    if (count == 7) {
                        if (maxSum < sum) {
                            maxSum = sum;
                            sum = 0;
                        }
                        count = 0;
                    }
                }
            }

        }
        return maxSum;
    }

}

class CupModel {

    private int[] cupTop;
    private int cupMiddle;
    private int[] cupBottom;

    public int[] getCupTop() {
        return cupTop;
    }

    public void setCupTop(int[] cupTop) {
        this.cupTop = cupTop;
    }

    public int getCupMiddle() {
        return cupMiddle;
    }

    public void setCupMiddle(int cupMiddle) {
        this.cupMiddle = cupMiddle;
    }

    public int[] getCupBottom() {
        return cupBottom;
    }

    public void setCupBottom(int[] cupBottom) {
        this.cupBottom = cupBottom;
    }
}