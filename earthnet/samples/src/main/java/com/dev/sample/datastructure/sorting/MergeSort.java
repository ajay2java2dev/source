package com.dev.sample.datastructure.sorting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by AjayMenon on 2/10/2017.
 */
public class MergeSort {
    static int iterationCount = 0;

    private static void merge(Comparable [] first, Comparable [] second, Comparable [] comparables) {
        int firstVal = 0;
        int secondVal = 0;
        int mergeVal = 0;

        if (first!=null && second !=null) {
            while (firstVal < first.length && secondVal < second.length) {
                if (first[firstVal].compareTo(second[secondVal]) < 0) {
                    comparables[mergeVal] = first[firstVal];
                    firstVal++;
                } else {
                    comparables[mergeVal] = second[secondVal];
                    secondVal++;
                }
                mergeVal++;
            }
            //copy remaining of the array elements which were not compared.
            System.arraycopy(first,firstVal,comparables,mergeVal,first.length-firstVal);
            System.arraycopy(second,secondVal,comparables,mergeVal,second.length-secondVal);
        }
    }

    //split -> sort --> and merge
    public static Comparable [] sort (Comparable [] comparables) {

        if (comparables!=null && comparables.length > 1) {

            iterationCount++;

            try {
                Integer.parseInt(comparables[0].toString());
            }catch (Exception ex) {
                System.out.println("integer parsing failed");
            }

            Comparable [] first = new Comparable[comparables.length/2];
            Comparable [] second = new Comparable[comparables.length - first.length];

            System.arraycopy(comparables,0,first,0,first.length);
            System.arraycopy(comparables,first.length,second,0,second.length);

            System.out.println("\nIteration : " + iterationCount);
            System.out.println("First Array : " + Arrays.asList(first));
            System.out.println("Second Array : " + Arrays.asList(second));

            sort(first);
            sort(second);

            merge(first,second,comparables);

        }
        return comparables;
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String numberOfElements = reader.readLine();
            String[] comparableElementArray = null;
            Comparable[] comparables = null;

            try {
                if (Integer.parseInt(numberOfElements) == -1) {
                    comparables = SortUtil.getRandomComparableList(100);
                }
            } catch (Exception ex) {
                System.out.println("MergeSort.exception while reading first input.");
                comparableElementArray = reader.readLine().split(" ");
            }

            if (numberOfElements!=null && comparableElementArray!=null && comparableElementArray.length>0) {
                System.out.println("\n Final Sorted Array : " + Arrays.asList(sort(comparableElementArray)));
            } else {
                System.out.println("\n Final Sorted Array : " + Arrays.asList(sort(comparables)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
