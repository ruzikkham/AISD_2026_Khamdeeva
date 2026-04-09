package Homework6;

public class Task1 {

    public static void main(String[] args) {
        int[] data = {2, 4, 8, 16, 32, 64};
        int key = 10;

        int answer = getNearestIndex(data, key);

        System.out.println("Массив: 2, 4, 8, 16, 32, 64");
        System.out.println("Ищем число: " + key);
        System.out.println("Ближайший индекс: " + answer);
    }

    static int getNearestIndex(int[] arr, int val) {
        if (val >= arr[arr.length - 1]) return arr.length - 1;
        if (val <= arr[0]) return 0;

        int pos = -1;

        for (int j = 0; j < arr.length - 1; j++) {
            if (arr[j] <= val && arr[j + 1] >= val) {
                pos = j;
                break;
            }
        }

        if (pos != -1) {
            int leftDiff = val - arr[pos];
            int rightDiff = arr[pos + 1] - val;
            return leftDiff <= rightDiff ? pos : pos + 1;
        }

        return 0;
    }
}