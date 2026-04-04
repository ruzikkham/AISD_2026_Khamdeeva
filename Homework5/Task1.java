package Homework5;

import java.util.HashMap;
import java.util.Map;

public class Task1 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 7, 9};
        int target = 6;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];

            if (map.containsKey(complement)) {
                int firstIndex = map.get(complement);
                System.out.println("Числа: " + complement + " + " + arr[i] + " = " + target);
                System.out.println("Индексы: " + firstIndex + " и " + i);
            }

            map.put(arr[i], i);
        }
    }
}
