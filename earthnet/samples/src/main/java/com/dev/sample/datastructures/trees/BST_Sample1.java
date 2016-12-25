package com.dev.sample.datastructures.trees;

import com.dev.sample.datastructures.trees.intf.BSTComposite;
import com.dev.sample.filter.LabUtility;

import java.io.*;
import java.util.List;

/**
 * Created by AjayMenon on 12/24/2016.
 */
public class BST_Sample1 implements BSTComposite
{

	private static final String keyFile = "S:\\apps\\source\\files\\bst_test_files\\input-key.csv";
	private static final String valueFile = "S:\\apps\\source\\files\\bst_test_files\\input-value.csv";

	private LabUtility labUtility = LabUtility.getInstance();

	public static void main(String[] args) throws Exception{

		try {
			BST_Sample1 sample1 = new BST_Sample1();

			List<String> keys = LabUtility.getFileContent(keyFile);
			List<String> values = LabUtility.getFileContent(valueFile);

			int [] keysPrim = sample1.getIntPrimitives(keys.toArray());
			int [] valuesPrim = sample1.getIntPrimitives(values.toArray());

			for (int i =0 ; i< keysPrim.length ; i++) {
				int index = sample1.bstNumSearch(keysPrim[i], valuesPrim);
				if(index!=-1) {
					System.out.println("Key found at index position : " + index);
				} else {
					System.out.println("Index not found.");
				}
			}

		}catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public int [] validateObjectArrayToInt (Object [] objects) {
		int [] intArray = new int[objects.length];
		for (int i=0;i<objects.length;i++) {
			if ((objects[i] instanceof Integer)) {
				intArray[i] = (Integer)objects [i];
			} else if ((objects[i] instanceof String)) {
				intArray[i] = Integer.parseInt((String)objects [i]);
			}
		}
		return intArray;
	}
	/**
	 * Convert Object arrays to int primitives.
	 * @param objects
	 * @return
	 */
	public int [] getIntPrimitives (Object [] objects) {
		if (objects!=null && objects.length > 0) {
			return validateObjectArrayToInt (objects);
		}
		return null;
	}

	/**
	 * Primitive values are passed to avoid unnecessary casting based computation
	 * @param key
	 * @param args
	 * @return
	 */
	public int bstNumSearch (int key,int [] args) {
		if (args!=null && args.length>0) {
			int lowIndex = 0;
			int highIndex = args.length-1;

			int midIndex = (lowIndex + highIndex)/2;
			if (args[midIndex] == key) {
				return midIndex;
			}

			while (true) {
				if ((highIndex - lowIndex)  <= 1) { break; }

				if (key > args[midIndex]) {
					lowIndex = midIndex;
				} else if (key < args[midIndex]) {
					highIndex = midIndex;
				}
				midIndex = (lowIndex + highIndex)/2;

				//check left, mid and right and return the index.
				if (args[midIndex] == key) {
					return midIndex;
				}
				if (args[lowIndex] == key) {
					return lowIndex;
				}
				if (args [highIndex] == key) {
					return highIndex;
				}
			}
		}
		return -1;
	}

	public int bstStringSearch (String key, String [] args) {
		return 0;
	}
}
