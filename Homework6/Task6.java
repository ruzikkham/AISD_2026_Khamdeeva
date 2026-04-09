package Homework6;

import java.util.HashSet;

public class Task6 {

    static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {4, 5, 6, 7, 8};
        int[] c = {5, 9, 10, 11};

        System.out.println("Общее число: " + findCommon(a, b, c));
    }

    static int findCommon(int[] first, int[] second, int[] third) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : first) set.add(num);

        HashSet<Integer> temp = new HashSet<>();
        for (int num : second) {
            if (set.contains(num)) temp.add(num);
        }

        for (int num : third) {
            if (temp.contains(num)) return num;
        }

        return -1;
    }
}