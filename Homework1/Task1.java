package Homework1;

public class Task1 {
    public static int nod(int a, int b) {
        // НОД(a, b) = 0 если b = 0
        if (b == 0) return 0;

        // НОД (a, b) = 0 если a = 0
        if (a == 0) return 0;

        // НОД (a, b) = НОД (b, a) если a < b
        if (a < b) {
            int c = a;
            a = b;
            b = c;
        }

        // НОД (a, b) = НОД (b, a mod b) если b != 0
        while (b != 0) {
            int c = b;
            b = a % b;
            a = c;
        }

        return a;
    }

    public static void main(String[] args) {

        System.out.println("НОД(7, 0) = " + nod(7, 0));
        System.out.println("НОД(0, 5) = " + nod(0, 5));
        System.out.println("НОД(16, 20) = " + nod(16, 20));
        System.out.println("НОД(48, 18) = " + nod(48, 18));

    }
}


