package com.dev.sample.puzzles;

import java.util.Arrays;

/**
 * Created by AjayMenon on 2/18/2017.
 */
public class Anagram {

    public static boolean isAnagram1(String s1, String s2) {

        if (s1 == null && s2 == null) {
            return false;
        }
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();

        Arrays.sort(s1Array);
        Arrays.sort(s2Array);

        String a1String = new String(s1Array);
        String a2String = new String(s2Array);

        return a1String.trim().toLowerCase().equalsIgnoreCase(a2String.trim().toLowerCase());
    }

    public static void main(String[] args) {
        String s1 = "dsa";
        String s2 = "asd";

        if (s1.equalsIgnoreCase(s2)) {
            System.out.print("Not an anagram");
        }

        if (isAnagram1(s1, s2)) {
            System.out.print(s1 + " and " + s2 + "are anagrams");
        }
    }
}
