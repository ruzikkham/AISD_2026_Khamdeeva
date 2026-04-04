package Homework5;

public class Task4 {
    public static void main(String[] args) {
        int[] a = new int[]{0, 10, 101, 1, 11, 110, 100};
        String[] b = new String[a.length];

        for (int ii = 0; ii < a.length; ii++) {
            b[ii] = "" + a[ii];
        }

        java.util.Arrays.sort(b, (s1, s2) -> {
            int i = 0;
            while (i < s1.length() && i < s2.length()) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return s2.charAt(i) - s1.charAt(i);
                }
                i++;
            }
            return s2.length() - s1.length();
        });

        String out = "[";
        for (int i = 0; i < b.length; i++) {
            out += b[i];
            if (i < b.length - 1) out += ", ";
        }
        out += "]";
        System.out.println(out);
    }
}
