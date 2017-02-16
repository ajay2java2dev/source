package com.dev.sample.puzzles;

/**
 * Created by AjayMenon on 2/14/2017.
 * <p>
 * Purpose of this Rand7 is to get a random value less than 7 using a known method called Rand5
 */
public class Rand7 {

    public static Integer getRandomValues() {
        int countMax = 0;
        while (true) {
            if (countMax >= 10) {
                break;
            }
            int randomLess6 = Rand5.getRand5();
            if (randomLess6 == 0 || randomLess6 == 5) {
                return randomLess6;
            }

            int remains = 7 % randomLess6;
            countMax++;

            if (remains < 2) {
                return remains + randomLess6;
            } else if (remains == 2) {
                return remains;
            } else {
                return remains;
            }

        }
        return null;
    }

    public static void main(String[] args) {
        Rand7 rand7 = new Rand7();
        System.out.println(getRandomValues()); //max 7

    }
}

class Rand5 {

    public static Integer getRand5() {
        int randomVal = (int) (Math.random() * 100);
        if (randomVal > 5) {
            if (randomVal % 5 == 0) {
                return 5;
            } else {
                return randomVal % 5;
            }
        } else {
            return randomVal;
        }

    }
}
