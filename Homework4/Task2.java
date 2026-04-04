package Homework4;

public class Task2 {
    public static int findMisNum(int[] arr) {
        int max = arr[arr.length - 1];
        int expectedSum = max * (max + 1) / 2;
        int actualSum = 0;

        for (int num : arr) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int[] arr1 = {0, 1, 2, 4, 5};
        System.out.println(findMisNum(arr1));

        int[] arr2 = {0, 2, 3, 4, 5};
        System.out.println(findMisNum(arr2));

        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println(findMisNum(arr3));
    }
}
