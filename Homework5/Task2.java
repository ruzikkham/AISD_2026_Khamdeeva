package Homework5;

import java.util.Arrays;
import java.util.Comparator;

public class Task2 {
    public static void main(String[] args) {
        String[] data = {"Адиля", "Амелия", "Рузиля", "Настя", "Алена", "Ильхам", "Тимур"};

        Arrays.sort(data, new Comparator<String>() {
            public int compare(String x, String y) {
                int lenX = x.length();
                int lenY = y.length();
                int minLen = Math.min(lenX, lenY);

                for (int j = 0; j < minLen; j++) {
                    char charX = x.charAt(j);
                    char charY = y.charAt(j);
                    if (charX != charY) {
                        return charX - charY;
                    }
                }

                return lenX - lenY;
            }
        });

        System.out.println(Arrays.toString(data));
    }
}
