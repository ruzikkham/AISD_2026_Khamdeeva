package Homework6;

public class Task2{

    public static void main(String[] args) {
        int first = 7;
        int second = 13;

        long result = multiplyByShifts(first, second);

        System.out.println(first + " * " + second + " = " + result);
        System.out.println("Проверка: " + (first * second == result));
    }

    public static long multiplyByShifts(int a, int b) {

        boolean negative = (a < 0) ^ (b < 0);

        long x = Math.abs(a);
        long y = Math.abs(b);
        long result = 0;

        while (x != 0) {
            if ((x & 1) == 1) {
                result = result + y;
            }
            x = x >> 1;
            y = y << 1;
        }

        if (negative) {
            result = -result;
        }

        return result;
    }
}