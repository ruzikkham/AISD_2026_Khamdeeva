package SEMESTROVKA_2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class BenchmarkRunner {
    private static final String RESULTS_DIR = "results";
    private static final String KMP_RESULTS_FILE = "kmp_results.csv";
    private static final String NAIVE_RESULTS_FILE = "naive_results.csv";
    private static final String COMPARISON_FILE = "comparison.csv";

    public static void main(String[] args) throws IOException {
        Files.createDirectories(Paths.get(RESULTS_DIR));
        System.out.println("=" .repeat(80));
        System.out.println("   АЛГОРИТМ КНУТА-МОРРИСА-ПРАТТА - СЕМЕСТРОВАЯ РАБОТА");
        System.out.println("=" .repeat(80));

        int testCount = 100;
        int minLength = 100;
        int maxLength = 10000;

        System.out.println("\nПараметры тестирования:");
        System.out.println("  - Количество тестов: " + testCount);
        System.out.println("  - Диапазон длин строк: от " + minLength + " до " + maxLength);
        System.out.println("  - Измерение времени: System.nanoTime() (наносекунды)");
        System.out.println("  - Подсчёт итераций: количество сравнений символов");

        System.out.println("\nГенерация тестовых данных...");
        StringGenerator.TestData[] testSet = StringGenerator.generateTestSet(testCount, minLength, maxLength);
        System.out.println("Сгенерировано " + testSet.length + " тестовых наборов.");

        initResultFiles();

        System.out.println("\nЗапуск тестирования...");
        System.out.println("-".repeat(80));

        long totalKMPTime = 0;
        long totalNaiveTime = 0;
        long totalKMPIterations = 0;
        long totalNaiveIterations = 0;

        for (int i = 0; i < testSet.length; i++) {
            StringGenerator.TestData data = testSet[i];

            KMPAlgorithm.SearchResult kmpResult = KMPAlgorithm.kmpSearch(data.text, data.pattern);

            KMPAlgorithm.SearchResult naiveResult = KMPAlgorithm.naiveSearch(data.text, data.pattern);

            saveResult(data, kmpResult, naiveResult);

            totalKMPTime += kmpResult.getTimeNano();
            totalNaiveTime += naiveResult.getTimeNano();
            totalKMPIterations += kmpResult.getIterations();
            totalNaiveIterations += naiveResult.getIterations();

            // Выводим прогресс
            if ((i + 1) % 10 == 0) {
                System.out.printf("  Прогресс: %d/%d тестов завершено%n", i + 1, testSet.length);
            }
        }

        // Выводим итоговую статистику
        printSummary(testSet.length, totalKMPTime, totalNaiveTime,
                totalKMPIterations, totalNaiveIterations);
        System.out.println("\n" + "=".repeat(80));
        System.out.println("Результаты сохранены в папке: " + RESULTS_DIR);
        System.out.println("  - " + KMP_RESULTS_FILE + " - результаты КМП алгоритма");
        System.out.println("  - " + NAIVE_RESULTS_FILE + " - результаты наивного алгоритма");
        System.out.println("  - " + COMPARISON_FILE + " - сравнение алгоритмов");
        System.out.println("=".repeat(80));
    }

    //Инициализация CSV файлов с заголовками
    private static void initResultFiles() throws IOException {
        // Файл результатов КМП
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(RESULTS_DIR, KMP_RESULTS_FILE), StandardCharsets.UTF_8)) {
            writer.write("id,textLength,patternLength,testType,found,iterations,timeNano,timeMillis");
            writer.newLine();
        }

        // Файл результатов наивного алгоритма
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(RESULTS_DIR, NAIVE_RESULTS_FILE), StandardCharsets.UTF_8)) {
            writer.write("id,textLength,patternLength,testType,found,iterations,timeNano,timeMillis");
            writer.newLine();
        }

        // Файл сравнения
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(RESULTS_DIR, COMPARISON_FILE), StandardCharsets.UTF_8)) {
            writer.write("id,textLength,patternLength,testType,kmpFound,naiveFound," +
                    "kmpIterations,naiveIterations,kmpTimeNano,naiveTimeNano," +
                    "speedup,iterationsReduction");
            writer.newLine();
        }
    }

    //Сохранение результатов теста
    private static void saveResult(StringGenerator.TestData data,
                                   KMPAlgorithm.SearchResult kmpResult,
                                   KMPAlgorithm.SearchResult naiveResult) throws IOException {

        // Сохраняем результат КМП
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(RESULTS_DIR, KMP_RESULTS_FILE), StandardCharsets.UTF_8,
                StandardOpenOption.APPEND)) {
            writer.write(String.format("%d,%d,%d,%s,%b,%d,%d,%.3f",
                    data.id, data.textLength, data.patternLength, data.getTestTypeName(),
                    kmpResult.isFound(), kmpResult.getIterations(),
                    kmpResult.getTimeNano(), kmpResult.getTimeMillis()));
            writer.newLine();
        }

        // Сохраняем результат наивного алгоритма
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(RESULTS_DIR, NAIVE_RESULTS_FILE), StandardCharsets.UTF_8,
                StandardOpenOption.APPEND)) {
            writer.write(String.format("%d,%d,%d,%s,%b,%d,%d,%.3f",
                    data.id, data.textLength, data.patternLength, data.getTestTypeName(),
                    naiveResult.isFound(), naiveResult.getIterations(),
                    naiveResult.getTimeNano(), naiveResult.getTimeMillis()));
            writer.newLine();
        }

        // Сохраняем сравнение
        double speedup = (double) naiveResult.getTimeNano() / kmpResult.getTimeNano();
        double iterationsReduction = (double) naiveResult.getIterations() / kmpResult.getIterations();

        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(RESULTS_DIR, COMPARISON_FILE), StandardCharsets.UTF_8,
                StandardOpenOption.APPEND)) {
            writer.write(String.format("%d,%d,%d,%s,%b,%b,%d,%d,%d,%d,%.2f,%.2f",
                    data.id, data.textLength, data.patternLength, data.getTestTypeName(),
                    kmpResult.isFound(), naiveResult.isFound(),
                    kmpResult.getIterations(), naiveResult.getIterations(),
                    kmpResult.getTimeNano(), naiveResult.getTimeNano(),
                    speedup, iterationsReduction));
            writer.newLine();
        }
    }

    //Вывод итоговой статистики
    private static void printSummary(int testCount, long totalKMPTime, long totalNaiveTime,
                                     long totalKMPIterations, long totalNaiveIterations) {
        double avgKMPTime = totalKMPTime / (double) testCount;
        double avgNaiveTime = totalNaiveTime / (double) testCount;
        double avgKMPIterations = totalKMPIterations / (double) testCount;
        double avgNaiveIterations = totalNaiveIterations / (double) testCount;

        System.out.println("\n" + "-".repeat(80));
        System.out.println("ИТОГОВАЯ СТАТИСТИКА:");
        System.out.println("-".repeat(80));
        System.out.printf("%-30s %20s %20s%n", "Показатель", "КМП алгоритм", "Наивный алгоритм");
        System.out.println("-".repeat(80));
        System.out.printf("%-30s %20.2f %20.2f%n",
                "Среднее время (наносекунды):", avgKMPTime, avgNaiveTime);
        System.out.printf("%-30s %20.2f %20.2f%n",
                "Среднее время (миллисекунды):", avgKMPTime / 1_000_000, avgNaiveTime / 1_000_000);
        System.out.printf("%-30s %20.2f %20.2f%n",
                "Среднее количество итераций:", avgKMPIterations, avgNaiveIterations);
        System.out.println("-".repeat(80));
        System.out.printf("%-30s %20.2fx%n",
                "Ускорение (время):", (double) totalNaiveTime / totalKMPTime);
        System.out.printf("%-30s %20.2fx%n",
                "Ускорение (итерации):", (double) totalNaiveIterations / totalKMPIterations);
    }
}