package ee.bcs.valiit.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Lesson2 {

    public static void main(String[] args) {
        //System.out.println(sum(2,3));
        //exercise1();
        // exercise2(4);
        // exercise3(2, 5);
        //fibonacci(0);
        //exercise5();
        //collatzLength(10);

/*
        try {
            exercise6();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //exercise6();

        try {
            exercise8();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            exercise9();

        } catch (IOException e) {
            e.printStackTrace();
        }

*/
    }

    public static int sum(int a,int b) {
        return a+b;
    }



    public static void exercise1() {
        // TODO loo 10 elemendile täisarvude massiv
        // TODO loe sisse konsoolist 10 täisarvu
        // TODO trüki arvud välja vastupidises järiekorras
        ArrayList<Integer> topTen = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sisesta 10 täisarvu!");
        for (int i = 0; i < 10; i++) {
            int a = scanner.nextInt();
            topTen.add(a);
        }
        System.out.println("10 täisarvu sisestatud");
        System.out.println(" Trükime need välja vastupidises järjekorras");
        for (int i = 9; i >= 0; i--) {
            System.out.println(topTen.get(i));
        }


    }

    public static void exercise2(int x) {
        // TODO prindi välja x esimest paaris arvu
        // Näide:
        // Sisend 5
        // Väljund 2 4 6 8 10

        for (int i = 1; i <= x; i++) {
            System.out.println(2 * i);
        }


    }

    public static void exercise3(int x, int y) {
        // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
        // TODO näiteks x = 3 y = 3
        // TODO väljund
        // 1 2 3
        // 2 4 6
        // 3 6 9
        for (int i = 1; i <= y; i++) {
            for (int j = 1; j <= x; j++) {
                System.out.print(i * j + " ");
            }
            System.out.println();
        }
    }

    public static int fibonacci(int n) {
        // TODO
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21
        // Tagasta fibonacci jada n element

        int f1 = 0;
        int f2 = 1;
        int f3;
        int fibo = 0;

        if (n == 0) {
            System.out.println("ära trolli!");
        } else if (n == 1) {
            System.out.println("0");
        } else if (n == 2) {
            System.out.println("0 1");
        } else {
            System.out.print(f1 + " " + f2);

            for (int i = 2; i < n; i++) {
                f3 = f1 + f2;
                fibo = f3;
                System.out.print(" " + f3);
                f1 = f2;
                f2 = f3;
            }
            System.out.println();
            System.out.println("otsitav fibonacci element on: " + fibo);
        }
        return 0;
    }


    public static void exercise5() {
        // https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=36
        /*
        Problems in Computer Science are often classified as belonging to a certain class of problems (e.g.,
NP, Unsolvable, Recursive). In this problem you will be analyzing a property of an algorithm whose
classification is not known for all possible inputs.
Consider the following algorithm:
1. input n
2. print n
3. if n = 1 then STOP
4. if n is odd then n ←− 3n + 1
5. else n ←− n/2
6. GOTO 2
Given the input 22, the following sequence of numbers will be printed
22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1
It is conjectured that the algorithm above will terminate (when a 1 is printed) for any integral input
value. Despite the simplicity of the algorithm, it is unknown whether this conjecture is true. It has
been verified, however, for all integers n such that 0 < n < 1, 000, 000 (and, in fact, for many more
numbers than this.)
Given an input n, it is possible to determine the number of numbers printed before and including
the 1 is printed. For a given n this is called the cycle-length of n. In the example above, the cycle
length of 22 is 16.
For any two numbers i and j you are to determine the maximum cycle length over all numbers
between and including both i and j.

        int n = 10;
        int m=20;
         */

        Scanner scanner = new Scanner(System.in);
        System.out.println("Sisesta jada algus!");
        int n = scanner.nextInt();
        System.out.println("Sisesta jada lõpp!");
        int m = scanner.nextInt();
        System.out.print(m);
        System.out.println("Leiame pikima Collatzi jada vahemikus " + n + " ... " + m);


        int max = 0;
        int maxID = 0;
        for (int i = n; i <= m; i++) {
            if (collatzLength(i) > max) {
                max = collatzLength(i);
                maxID = i;
            }
        }
        System.out.println();
        System.out.println(n + " " + m + " " + maxID);
    }

    public static int collatzLength(int n) {
        int counter = 0;
        int m = n;
        while (!(n == 1)) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            counter++;
        }
        System.out.println(m + "->" + counter);
        return counter;

    }


    public static void exercise6() throws FileNotFoundException {
        /*
            Kirjutada Java programm, mis loeb failist visits.txt sisse looduspargi külastajad erinevatel jaanuari päevadel ning
            a) sorteerib külastuspäevad külastajate arvu järgi kasvavalt ning prindib tulemuse konsoolile;
            b) prindib konsoolile päeva, mil külastajaid oli kõige rohkem.
            Faili asukoht tuleb programmile ette anda käsurea parameetrina.

2018-01-01, 456
2018-01-02, 243
2018-01-03, 731
2018-01-04, 416
2018-01-05, 123
2018-01-06, 542
2018-01-07, 697

String str = " Hello I'm your String";
String[] splitStr = str.trim().split("\\s+");
         */


        File file = new File("C:/Users/Raivo/vali-it/lesson2/resources/visits.txt");
        Map<String, Integer> map = new HashMap<>();
        TreeMap<Integer, String> sorted = new TreeMap<>();

        String key;
        int max = 0;
        String maxID = "";
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] splitStr = line.trim().split(",");
            map.put(splitStr[0], parseInt(splitStr[1].trim()));
            sorted.put(parseInt(splitStr[1].trim()), splitStr[0]);
        }
// Leiame maksimaalse päeva

        for (int i = 1; i < 32; i++) {
            if (i < 10) {
                key = "2018-01-0" + i;
            } else {
                key = "2018-01-" + i;
            }

            if (map.get(key) > max) {
                max = map.get(key);
                maxID = key;
            }
        }
        System.out.println("Max külastajate arv oli " + maxID + ", kokku külastajaid: " + max);

        System.out.println("Külastuspäevad kasvavas jäljekorras: " + sorted.values());
    }

    public static void exercise7() {
        // TODO arvuta kasutades BigDecimali 1.89 * ((394486820340 / 15 ) - 4 )
        BigDecimal a = new BigDecimal(1.89);
        BigDecimal b = new BigDecimal("394486820345");
        BigDecimal c = new BigDecimal("15");
        BigDecimal d = new BigDecimal("4");
        //igDecimal result = b.divide(c);
        BigDecimal result = ((b.divide(c, RoundingMode.HALF_UP)).subtract(d)).multiply(a);
        System.out.println(result);


    }


    public static void exercise8() throws FileNotFoundException {
/*
/*
        Failis nums.txt on üksteise all 150 60-kohalist numbrit.
        Kirjuta programm, mis loeks antud numbrid failist sisse ja liidaks need arvud kokku ning kuvaks ekraanil summa.
        Faili nimi tuleb programmile ette anda käsurea parameetrina.
        VASTUS:
        Õige summa: 77378062799264987173249634924670947389130820063105651135266574
         */


        File file = new File("C:/Users/Raivo/vali-it/lesson2/resources/nums.txt");
        //List<String> numbers = new ArrayList<String>();
        BigInteger summa = new BigInteger(String.valueOf(0));
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            BigInteger rida = new BigInteger(String.valueOf(line));
            //numbers.add(scanner.nextLine());
            summa = summa.add(rida);


        }
        System.out.println(summa);
    }


    public static void exercise9() throws FileNotFoundException {
        /* TODO
        Sama mis eelmises ülesandes aga ära kasuta BigInt ega BigDecimal klassi
         */


        File file = new File("C:/Users/Raivo/vali-it/lesson2/resources/nums.txt");

        String summa = "";
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            summa = sumString(summa, line);
        }
        System.out.println("Summa: " + summa);
    }


    public static String sumString(String a, String b) {
        int lisa = 0;
        String sum = "";
        if (a.length() > b.length()) {
            b = stringEqualizer(b, a);
        }
        if (a.length() < b.length()) {
            a = stringEqualizer(a, b);
        }

        for (int i = a.length(); i > 0; i--) {
            int s = parseInt(a.substring(i - 1, i)) + parseInt(b.substring(i - 1, i)) + lisa;

            if (s >= 10) {
                s = s - 10;
                lisa = 1;
            } else {
                lisa = 0;
            }
            sum = String.valueOf(s) + sum;
        }
        if (lisa == 1) {
            sum = "1" + sum;
        }

        return sum;
    }

    public static String stringEqualizer(String a, String b) {
        if (a.length() < b.length()) {
            int addZero = b.length() - a.length();
            for (int i = 0; i < addZero; i++) {
                a = "0" + a;
            }
        }
        return a;
    }
}
