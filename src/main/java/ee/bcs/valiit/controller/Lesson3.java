package ee.bcs.valiit.controller;

import java.util.TreeSet;

public class Lesson3 {

    public static void main(String[] args) {
        //System.out.println(factorial(0));
        System.out.println(summa(2,3));
        // System.out.println(reverseString("llort"));
        //System.out.println(isPrime(1));
        //int[] marks = {1, 2, 3, 4};
        //System.out.println(sum(sort(marks)));
    }




    public static int summa(int x, int y) {
        // TODO liida kokku ja tagasta x ja y väärtus
        return x + y;
    }

    public static int sumarray(int[] x) {
        // Todo liida kokku kõik numbrid massivis x
        int sum = 0;
        for (int i : x) {
            sum += i;
        }
        return sum;
    }

    public static int factorial(int x) {
        // TODO tagasta x faktoriaal.
        // Näiteks
        // x = 5
        // return 4*3*2*1 = 24
        if (x >= 1) {
            return x * factorial(x - 1);
        }
            return 1;

    }

    public static int[] sort(int[] a) {
        // TODO sorteeri massiiv suuruse järgi
        // Näiteks {2, 6, 8, 1}
        // Väljund {1, 2, 6, 8}

        int counter = 0;
        int[] result = new int[a.length];
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < a.length; i++) {
            treeSet.add(a[i]);
        }
        for (int x : treeSet) {
            result[counter] = x;
            counter++;
        }

        return result;
    }

    public static String reverseString(String a) {
        // TODO tagasta string tagurpidi
        // Näiteks:
        // a = "Test";
        // return tseT";
        String returnString = "";
        for (int i = 0; i < a.length(); i++) {
            returnString = a.substring(i, i + 1) + returnString;
        }
        return returnString;
    }

    public static boolean isPrime(int x) {
        // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)
        if (x < 2) {
            return false;

        } else {
            for (int i = 2; i < x / 3; i++)
                if (x % i == 0) {
                    return false;
                }
        }
        return true;
    }
}
