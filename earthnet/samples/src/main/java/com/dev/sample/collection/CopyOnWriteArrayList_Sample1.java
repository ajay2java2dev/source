package com.dev.sample.collection;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by AjayMenon on 12/22/2016.
 */
public class CopyOnWriteArrayList_Sample1 {

	int count = 0;

	public static void main(String[] args) {
		System.out.println("start execution ...");
		System.out.println(callCopyOnWriteArrayList());
	}

	public static String callCopyOnWriteArrayList () {

		List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<String>();
		copyOnWriteArrayList.add("ajay");
		copyOnWriteArrayList.add("amr");
		copyOnWriteArrayList.add("atur");
		copyOnWriteArrayList.add("deepak");

		int count = 0;

		for (String test : copyOnWriteArrayList) {
			test.concat(Math.random()+"");

			if (count < copyOnWriteArrayList.size())
				copyOnWriteArrayList.add("prardiva");
			System.out.println(test);
		}

		return copyOnWriteArrayList.toString();
	}


}
