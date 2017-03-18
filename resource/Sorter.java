import java.io.*;
import java.util.*;

public class Sorter  {
	static int iterationCount1 = 0;
	static int iterationCount2 = 0;
	
	//method to do simple merge of the divided array
	public void doMerge(Comparable [] first, Comparable [] second, Comparable [] comparables) {
		int firstCount = 0;
		int secondCount= 0;
		int mergeCount= 0;
		System.out.println("\nmerging first: " + Arrays.toString(first));
		System.out.println("merging second : " + Arrays.toString(second));
		
		if (comparables!=null){
			while(firstCount < first.length && secondCount < second.length) {
				if (first[firstCount].compareTo(second [secondCount]) < 0) {
					comparables[mergeCount] = first[firstCount];
					firstCount++;
				} else {
					comparables[mergeCount] = second[secondCount];
					secondCount++;
				}
				mergeCount++;
			}
			
			System.arraycopy(first,firstCount,comparables,mergeCount,first.length-firstCount);
			System.arraycopy(second,secondCount,comparables,mergeCount,second.length-secondCount);
		}
		System.out.println("merged : " + Arrays.toString(comparables));
	}
	//method to divide the array.
	public Comparable [] sortByMergeSort(Comparable [] comparables) {
		//base condition is set here.
		if (comparables.length <= 1) {
			return comparables;
		}
		
		Comparable [] first = new Comparable[comparables.length/2];
		Comparable [] second = new Comparable[comparables.length-first.length];
		
		//Copy the halfs to two arrays.
		System.arraycopy(comparables,0,first,0,first.length);
		System.arraycopy(comparables,first.length,second,0,second.length);
		System.out.println("\nfirst array : " + Arrays.toString(first));
		System.out.println("second array : " + Arrays.toString(second));
		
		iterationCount1++;
		sortByMergeSort(first);
		iterationCount2++;
		sortByMergeSort(second);
		
		doMerge(first,second,comparables);
		
		return comparables;
	}
	
	public int sumValuesOfIntArray (Integer [] intArray) {
		int sum = 0; 
		for (int i = 0 ; i < intArray.length; i++ ) {
			sum = sum + intArray[i];
		}
		return sum;
	}
	
	public int factorial (int value) {
		if (value <=1 ) {
			return value;
		} else {
			value = value * factorial(value-1);
		}
		return value;
	}
	
	public static void main(String[] args) {
		//TODO : Implement your input mechanism here.
		Integer [] intArray = {10,1221,1331,21,22,44,5,35,664};	
		System.out.println("\n Start Array : " + Arrays.toString(intArray));
		Sorter sort = new Sorter();
		sort.sortByMergeSort(intArray);
		System.out.println("\n Sorted Array : " + Arrays.toString(intArray));
		System.out.println("Total number of iterationCount1 : "+iterationCount1);
		System.out.println("Total number of iterationCount2 : "+iterationCount2);
		
		int sum = sort.sumValuesOfIntArray(intArray);
		System.out.println("sum : "+sum);
		System.out.println(sort.factorial(sum));
	}
}