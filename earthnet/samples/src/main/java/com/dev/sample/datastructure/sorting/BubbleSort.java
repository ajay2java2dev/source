package com.dev.sample.datastructure.sorting;

/**
 * Created by AjayMenon on 2/21/2017.
 */
public class BubbleSort {

    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
        System.out.println("Printing numbers in Desc : ");
        Comparable[] comparables1 = sort.sort(SortUtil.getRandomComparableList(10), SortType.DESC);
        for (Comparable comparable : comparables1) {
            System.out.print(comparable + " ");
        }

        System.out.println("\nPrinting numbers in Asc : ");
        Comparable[] comparables2 = sort.sort(SortUtil.getRandomComparableList(10), SortType.ASC);
        for (Comparable comparable : comparables2) {
            System.out.print(comparable + " ");
        }
    }

    public Comparable[] sort(Comparable[] comparables, SortType sortType) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < comparables.length - 1; i++) {
                if (SortType.ASC.equals(sortType)) {
                    if (comparables[i].compareTo(comparables[i + 1]) > 0) {
                        Comparable tempComparable = comparables[i + 1];
                        comparables[i + 1] = comparables[i];
                        comparables[i] = tempComparable;
                        sorted = false;
                    }
                } else if (SortType.DESC.equals(sortType)) {
                    if (comparables[i].compareTo(comparables[i + 1]) < 0) {
                        Comparable tempComparable = comparables[i + 1];
                        comparables[i + 1] = comparables[i];
                        comparables[i] = tempComparable;
                        sorted = false;
                    }
                }
            }
        }
        return comparables;
    }
}
