package Homework6;

import java.util.Arrays;



public class Task5 {

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 11, 14, 17, 20};
        int target = 25;


        Arrays.sort(arr);

        int result = threeSumClosest(arr, target);
        System.out.println("Ближайшая сумма: " + result);
    }

    public static int threeSumClosest(int[] arr, int target) {
        int n = arr.length;
        int closestSum = arr[0] + arr[1] + arr[2];

        for (int i = 0; i < n - 2; i++) {

            int minSum = arr[i] + arr[i+1] + arr[i+2];
            if (minSum > target && Math.abs(minSum - target) >= Math.abs(closestSum - target)) {
                continue;
            }

            int maxSum = arr[i] + arr[n-2] + arr[n-1];
            if (maxSum < target && Math.abs(maxSum - target) >= Math.abs(closestSum - target)) {
                continue;
            }

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int currentSum = arr[i] + arr[left] + arr[right];

                if (currentSum == target) {
                    return currentSum;
                }

                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return closestSum;
    }
}