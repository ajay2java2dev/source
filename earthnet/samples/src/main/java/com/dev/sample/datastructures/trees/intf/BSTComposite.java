package com.dev.sample.datastructures.trees.intf;

/**
 * Created by AjayMenon on 12/24/2016.
 */
public interface BSTComposite {

	//return index position of the value if found
	int bstNumSearch (int key,int [] args);

	int bstStringSearch (String key, String [] args);

}