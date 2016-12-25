package com.dev.sample.collection;

import java.util.*;

/**
 * Demonstrate Tree set ordering.
 * Access and retrieval times are as fast as O(log n), and has a red-black data structure implementation.
 * Who the hell cares right ?
 */
public class TreeSet_Sample1 {

	public static void main(String[] args) {
		System.out.println(callTreeSetByNaturalOrder());
	}

	/**
	 * Showcases natural orders of the given set of elements.
	 * @return
	 */
	public static String callTreeSetByNaturalOrder() {
		Set<String> treeSet = new TreeSet<String>();

		long startTime = System.currentTimeMillis();

		treeSet.add("amr");
		treeSet.add("atur");
		treeSet.add("ajay");

		System.out.println(System.currentTimeMillis() - startTime);

		return Arrays.asList(treeSet).toString();
	}


}
