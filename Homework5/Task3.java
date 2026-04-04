package Homework5;

public class Task3 {
    public static void main(String[] args) {
        int[] numbers = {2, 4, 6, 8, 10, 12, 14};
        int needed = 20;

        searchPair(numbers, needed);
    }

    private static void searchPair(int[] data, int goal) {
        int start = 0;
        int end = data.length - 1;

        while (start < end) {
            int result = data[start] + data[end];
            if (result == goal) {
                System.out.println(data[start] + " + " + data[end] + " = " + goal);
                return;
            }
            if (result < goal) {
                start++;
            } else {
                end--;
            }
        }
        System.out.println("Пара не найдена");
    }
}
