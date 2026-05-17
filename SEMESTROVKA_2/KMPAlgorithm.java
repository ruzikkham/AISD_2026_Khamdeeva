package SEMESTROVKA_2;

public class KMPAlgorithm{
    //Класс для хранения результата поиска
    public static class SearchResult {
        private final boolean found;
        private final long iterations;
        private final long timeNano;
        public SearchResult(boolean found, long iterations, long timeNano) {
            this.found = found;
            this.iterations = iterations;
            this.timeNano = timeNano;
        }

        public boolean isFound() {
            return found;
        }

        public long getIterations() {
            return iterations;
        }

        public long getTimeNano() {
            return timeNano;
        }

        public double getTimeMillis() {
            return timeNano / 1_000_000.0;
        }
    }

    //Алгоритм Кнута-Морриса-Пратта (КМП)
    public static SearchResult kmpSearch(String text, String pattern) {
        long startTime = System.nanoTime();

        long iterations = 0;

        if (pattern.isEmpty()) {
            return new SearchResult(true, iterations, System.nanoTime() - startTime);
        }

        int n = text.length();
        int m = pattern.length();

        if (m > n) {
            return new SearchResult(false, iterations, System.nanoTime() - startTime);
        }

        // Этап 1: построение префикс-функции (Pi-массива)
        int[] pi = new int[m];
        pi[0] = 0;
        int j = 0;

        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
                iterations++;
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                iterations++;
            }
            pi[i] = j;
            if (i == j && j != 0) {
                iterations++;
            }
        }

        // Этап 2: поиск образца в строке
        j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
                iterations++;
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
                iterations++;
            }
            if (j == m) {
                // Нашли образец
                return new SearchResult(true, iterations, System.nanoTime() - startTime);
            }
        }

        // Образец не найден
        return new SearchResult(false, iterations, System.nanoTime() - startTime);
    }

    //Наивный алгоритм поиска подстроки (для сравнения)
    public static SearchResult naiveSearch(String text, String pattern) {
        long startTime = System.nanoTime();

        long iterations = 0;

        if (pattern.isEmpty()) {
            return new SearchResult(true, iterations, System.nanoTime() - startTime);
        }

        int n = text.length();
        int m = pattern.length();

        if (m > n) {
            return new SearchResult(false, iterations, System.nanoTime() - startTime);
        }

        for (int i = 0; i <= n - m; i++) {
            boolean found = true;
            for (int j = 0; j < m; j++) {
                iterations++;
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return new SearchResult(true, iterations, System.nanoTime() - startTime);
            }
        }

        return new SearchResult(false, iterations, System.nanoTime() - startTime);
    }
}