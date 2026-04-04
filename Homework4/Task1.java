package Homework4;

public class Task1 {
    public static boolean isRotation(String word, String newWord) {
        return word.length() == newWord.length() && (word + word).contains(newWord);
    }

    public static void main(String[] args) {
        System.out.println(isRotation("привет","ветпри"));
    }

}
