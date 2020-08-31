package ee.bcs.valiit.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Enne kui seda tegema hakkad tee ära Lesson 2 (välja arvatud ülesanded 6, 8, 9)
public class Lesson3Hard {

    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println(fibonacci(10));
        //System.out.println(fibonacciArray(10));
        //System.out.println(evenFibonacci(34));
        //randomGame();
        // System.out.println(morseCode("Tere, Siim Rebane"));
        //System.out.println(fibonacci(8));
        //System.out.println(evenFibonacci(45));
        System.out.println(fibonacciOld(9));

    }

    public static int evenFibonacci(int x) {
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
        int result = 0;
        for (int i = 1; i < x; i++) {
            if (isEven(fibonacci(i))) {
                if ((fibonacci(i) + result) > x) {
                    return result;
                } else {
                    result = result + fibonacci(i);
                }
            }
        }
        return result;
    }

    public static void randomGame() {
        // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
        Random random = new Random();
        int randomInt = random.nextInt(100);
        System.out.println(randomInt);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Äraarvamismäng. Mõtlen ühele numbrile 0...100. Arva see number ära. Annan vihjeid");
        System.out.println("Sisesta oma panus siia, terminali aknasse!");
        int a;
        int count = 1;
        while (true) {
            a = scanner.nextInt();
            System.out.println("Katse nr:" + count + ". Sisestasid : " + a);
            if (a < randomInt) {
                System.out.println("suurenda!");
                count++;
            } else if (a > randomInt) {
                System.out.println("vähenda!");
                count++;
            } else {
                System.out.println("Arvasid ära! Sul kulus " + count + " katset.");
                break;
            }
        }
    }

    public static String morseCode(String text) throws FileNotFoundException {
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja -
        text = text.toUpperCase();
        HashMap<String, String> morse = new HashMap<>();
        String result = "";

        File file = new File("C:\\rescources\\morse.txt");

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            String[] splitStr = line.trim().split("\\t");
            morse.put(splitStr[0], splitStr[1].trim());
        }
        //System.out.println(morse.get("E"));
        for (int i = 0; i < text.length(); i++) {
            // Lahendame tühiku puudumise morse maailmas kahe tühikuga
            if (text.substring(i, i + 1).equals(" ")) {
                result = "   " + result;

            } else {
                result = (morse.get(text.substring(i, i + 1)) + "  ") + result;
            }
        }


        return result;
    }

    public static ArrayList<Integer> fibonacciArray(int n) {
        // TODO
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21
        // Tagasta fibonacci jada n element
        ArrayList<Integer> fibonacciArray = new ArrayList<Integer>();
        int f1 = 0;
        int f2 = 1;
        int f3;
        int fibonacci = 0;

        if (n == 1) {
            fibonacciArray.add(0);
        } else if (n == 2) {
            fibonacciArray.add(0);
            fibonacciArray.add(1);
        } else {
            fibonacciArray.add(0);
            fibonacciArray.add(1);
            for (int i = 2; i < n; i++) {
                f3 = f1 + f2;
                fibonacciArray.add(f3);
                f1 = f2;
                f2 = f3;
            }
        }
        return fibonacciArray;
    }




    public static boolean isEven(int a) {
        // TODO tagasta true, kui a on paaris arv
        // tagasta false kui a on paaritu arv
        return a % 2 <= 0;
    }


    public static int fibonacci(int x) {
        // TODO tagasta x fibonacci
        // Näiteks
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21

        if (x > 2) {
            return fibonacci(x - 1) + fibonacci(x - 2);
        } else if (x == 2) {
            return 1;
        } else {
            return 0;
        }

    }


    public static int factorial(int x) {
        // TODO tagasta x faktoriaal.
        // Näiteks
        // x = 5
        // return 5*4*3*2*1 = 120
        if (x >= 1)
            return x * factorial(x - 1);
        else
            return 1;
    }

    public static int fibonacciOld(int n) {
        // TODO
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21
        // Tagasta fibonacci jada n element

        int f1 = 0;
        int f2 = 1;
        int f3;
        int fibonacci = 0;

        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            for (int i = 2; i < n; i++) {
                f3 = f1 + f2;
                fibonacci = f3;
                f1 = f2;
                f2 = f3;
            }
        }
        return fibonacci;
    }

    public static int evenFibonacciOld(int x) {
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
        int result = 0;
        for (int i = 1; i < fibonacciArray(x).size(); i++) {
            if (isEven(fibonacciArray(x).get(i))) {
                if (fibonacciArray(x).get(i) <= x) {
                    result = result + fibonacciArray(x).get(i);
                }
            }
        }
        return result;
    }

}
