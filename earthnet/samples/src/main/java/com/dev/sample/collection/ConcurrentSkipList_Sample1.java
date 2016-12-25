package com.dev.sample.collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by AjayMenon on 12/23/2016.
 */
public class ConcurrentSkipList_Sample1 {

	public static void main(String[] args) {
		checkListTest ();
	}

	public static void checkListTest() {
		String p[] = {"test1","test2","test3"};
		List<String> bestTest = Arrays.asList(p);

		Set<Integer> newSkipList = new ConcurrentSkipListSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return 0;
			}
		});


	}
}
