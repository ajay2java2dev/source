package com.dev.sample.datastructure.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by AjayMenon on 2/21/2017.
 */
public class SortUtil {

    /**
     * Utility method to get a random Array list.
     * @param size
     * @return
     */
    public static Comparable[] getRandomComparableList(int size) {
        Set<Comparable> comparables = new HashSet<Comparable>();
<<<<<<< HEAD
        int count = 1;
        while (comparables.size() < size) {
            Double i = count++ + (Math.random() * 100);
=======

        while (comparables.size() < size) {
            Double i = Math.random() * 100;
>>>>>>> bb8043fd2cc07971866d807dcab51796aded6426
            int j = i.intValue() / 7;
            comparables.add(j * 13);
        }

        Collections.shuffle(Arrays.asList(comparables));

        Comparable[] comparables1 = new Comparable[size];
        int i = 0;
        for (Comparable comparable : comparables) {
            comparables1[i] = comparable;
            i++;
        }

        return comparables1;
    }
}
