package SEMESTROVKA_2;

public class Demo {
    public static void main(String[] args) {
        System.out.println("=" .repeat(60));
        System.out.println("ДЕМОНСТРАЦИЯ РАБОТЫ АЛГОРИТМА КНУТА-МОРРИСА-ПРАТТА");
        System.out.println("=" .repeat(60));

        String[][] examples = {
                {"abababcabab", "ababab"},
                {"hello world", "world"},
                {"aaaaaa", "aaa"},
                {"abcdefgh", "xyz"},
                {"abcabcabc", "abcabc"},
                {"", ""},
                {"a", "a"},
                {"ababa", "aba"},
        };

        System.out.println("\nРезультаты тестов:");
        System.out.println("-".repeat(60));
        System.out.printf("%-25s %-15s %-15s %-12s%n", "Текст -> Образец", "Найдено", "Итерации", "Время(нс)");
        System.out.println("-".repeat(60));

        for (String[] example : examples) {
            String text = example[0];
            String pattern = example[1];
            KMPAlgorithm.SearchResult result = KMPAlgorithm.kmpSearch(text, pattern);
            String displayText = text.length() > 20 ? text.substring(0, 17) + "..." : text;
            String displayPattern = pattern.length() > 15 ? pattern.substring(0, 12) + "..." : pattern;
            System.out.printf("%-25s %-15s %-15d %-12d%n",
                    displayText + " -> " + displayPattern,
                    result.isFound() ? "Да" : "Нет",
                    result.getIterations(),
                    result.getTimeNano());
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("ОСОБЕННОСТИ АЛГОРИТМА КМП:");
        System.out.println("-".repeat(60));
        System.out.println("1. Использует префикс-функцию для эффективного поиска");
        System.out.println("2. Сложность: O(n + m), где n - длина текста, m - длина образца");
        System.out.println("3. Не требует возврата в тексте (однопроходный)");
        System.out.println("4. Оптимален для поиска в длинных текстах");
        System.out.println("5. Хорошо работает с повторяющимися паттернами");
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ПРИМЕР ПРЕФИКС-ФУНКЦИИ:");
        System.out.println("-".repeat(60));
        String pattern = "abababca";
        System.out.println("Образец: " + pattern);
        int[] pi = computePrefixFunction(pattern);
        System.out.print("Префикс-функция: [");
        for (int i = 0; i < pi.length; i++) {
            System.out.print(pi[i]);
            if (i < pi.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("\nИнтерпретация: pi[i] = длина наибольшего собственного");
        System.out.println("префикса, который также является суффиксом для");
        System.out.println("подстроки pattern[0..i]");
    }

    //Вычисление префикс-функции для демонстрации
    private static int[] computePrefixFunction(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        if (m == 0) return pi;

        pi[0] = 0;
        int j = 0;
        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        return pi;
    }
}