package com.dev.sample.puzzles;

import java.io.*;
import java.util.*;

public class Fibonacci {

    int startNum = 0;
    int sum = 0;
    static Set <Integer> fibNumberSet = null;

    // option 1. for large value of maxNum, this method is unbelievably slow.
    public void calculateFib(Long maxNum) {
        fibNumberSet =  new HashSet<Integer> ();
        int holdA = 0;
        int holdB=  1;
        int maxVal = -1;
        while (maxVal <= maxNum) {
            maxVal = holdB + holdA;
            holdA = holdB;
            holdB = maxVal;
            fibNumberSet.add(maxVal);
        }
    }

    boolean isPerfectSquare (Long x) {
        double s = Math.sqrt(x);
        return (s*s == x);
    }

    public boolean isFibonacci (Long number) {
        return isPerfectSquare(5*number*number + 4) || isPerfectSquare(5*number*number -4);
    }

    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Long numberOfTestCases = Long.parseLong(reader.readLine());
        int count = 0;
        Fibonacci sol = new Fibonacci ();
        while (count < numberOfTestCases) {
            Long val = Long.parseLong(reader.readLine());

            //sol.calculateFib(val); //very ver slow method
            if (sol.isFibonacci(val)) {
                System.out.println("IsFibo");
            } else {
                System.out.println("IsNotFibo");
            }

           /* if (fibNumberSet.contains(val)) {
                System.out.println("IsFibo");
            } else {
                System.out.println("IsNotFibo");
            }*/
            count++;
        }
    }
}