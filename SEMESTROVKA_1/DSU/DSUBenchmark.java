package DSU;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Бенчмарк (измерение времени) для структуры данных DSU.
 */
public class DSUBenchmark {

    public static void main(String[] args) {
        System.out.println("Подготовка к тестированию (разогрев JVM)...");
        warmUp();

        System.out.println("\n--- РЕЗУЛЬТАТЫ ТЕСТИРОВАНИЯ (ДЛЯ КОПИРОВАНИЯ В EXCEL) ---");
        System.out.println("Размер(N);Добавление/Union(ns);Поиск(ns);Удаление(ns)");

        Random random = new Random(42);

        int minSize = 100;
        int maxSize = 10000;
        int step = 100;
        int iterationsPerSize = 5;

        for (int size = minSize; size <= maxSize; size += step) {
            long totalUnionTime = 0;
            long totalFindTime = 0;
            long totalDeleteTime = 0;

            for (int iter = 0; iter < iterationsPerSize; iter++) {
                ComputerNetworkDSU dsu = new ComputerNetworkDSU(size, size * 3);

                for (int i = 0; i < size; i++) {
                    dsu.makeSet(i);
                }

                int[] unionPairs1 = new int[size];
                int[] unionPairs2 = new int[size];
                for (int i = 0; i < size; i++) {
                    unionPairs1[i] = random.nextInt(size);
                    unionPairs2[i] = random.nextInt(size);
                }

                // 1. Тест Добавления
                long startUnion = System.nanoTime();
                for (int i = 0; i < size; i++) {
                    dsu.union(unionPairs1[i], unionPairs2[i]);
                }
                long endUnion = System.nanoTime();
                totalUnionTime += (endUnion - startUnion);

                // Подготовка к поиску
                List<Integer> searchQueries = new ArrayList<>();
                for (int i = 0; i < size; i++) searchQueries.add(i);
                Collections.shuffle(searchQueries, random);

                // 2. Тест Поиска
                long startFind = System.nanoTime();
                for (int x : searchQueries) {
                    dsu.find(x);
                }
                long endFind = System.nanoTime();
                totalFindTime += (endFind - startFind);

                // Подготовка к удалению
                List<Integer> deleteQueries = new ArrayList<>(searchQueries);
                Collections.shuffle(deleteQueries, random);

                // 3. Тест Удаления
                long startDelete = System.nanoTime();
                for (int x : deleteQueries) {
                    dsu.delete(x);
                }
                long endDelete = System.nanoTime();
                totalDeleteTime += (endDelete - startDelete);
            }

            // Среднее время на ОДНУ операцию
            long avgUnion = (totalUnionTime / iterationsPerSize) / size;
            long avgFind = (totalFindTime / iterationsPerSize) / size;
            long avgDelete = (totalDeleteTime / iterationsPerSize) / size;

            System.out.printf("%d;%d;%d;%d%n", size, avgUnion, avgFind, avgDelete);
        }
    }

    private static void warmUp() {
        ComputerNetworkDSU dummy = new ComputerNetworkDSU(10000, 30000);
        for (int i = 0; i < 10000; i++) dummy.makeSet(i);
        for (int i = 0; i < 9000; i++) dummy.union(i, i + 1);
        for (int i = 0; i < 10000; i++) dummy.find(i);
        for (int i = 0; i < 5000; i++) dummy.delete(i);
    }
}