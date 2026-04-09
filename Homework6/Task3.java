package Homework6;

public class Task3 {

    static void main(String[] args) {
        String text = "Мне нравится язык JAVA";
        String result = "";
        String word = "";

        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            word = word + symbol;

            if (symbol == ' ') {
                result = word + result;
                word = "";
            }

            if (i == text.length() - 1) {
                result = word + " " + result;
            }
        }

        System.out.println(result);
    }
}
