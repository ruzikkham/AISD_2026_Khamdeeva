package SEMESTROVKA_2;

import java.util.Random;

public class StringGenerator {
    private static final Random random = new Random();

    //Генерация случайной строки заданной длины из строчных латинских букв
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        return sb.toString();
    }

    //Генерация строки, в которой гарантированно есть подстрока
    public static String[] generateWithSubstring(int textLength, int patternLength) {
        if (patternLength > textLength) {
            patternLength = textLength;
        }
        String pattern = generateRandomString(patternLength);
        String text = generateRandomString(textLength - patternLength);
        int position = random.nextInt(textLength - patternLength + 1);
        StringBuilder sb = new StringBuilder(text);
        sb.insert(position, pattern);
        return new String[]{sb.toString(), pattern};
    }

    //Генерация строки без подстроки (вероятность 70%)
    public static String[] generateWithoutSubstring(int textLength, int patternLength) {
        if (patternLength > textLength) {
            patternLength = textLength / 2;
        }
        String pattern;
        String text;
        boolean containsPattern;
        do {
            pattern = generateRandomString(patternLength);
            text = generateRandomString(textLength);
            containsPattern = text.contains(pattern);
        } while (containsPattern);

        return new String[]{text, pattern};
    }

    //Генерация строки для худшего случая (все символы одинаковые)
    public static String[] generateWorstCase(int textLength, int patternLength) {
        if (patternLength > textLength) {
            patternLength = textLength;
        }

        char ch = (char) ('a' + random.nextInt(26));
        String text = generateRepeatedString(textLength, ch);
        String pattern = generateRepeatedString(patternLength, ch);

        return new String[]{text, pattern};
    }

    //Генерация строки из повторяющихся символов
    public static String generateRepeatedString(int length, char ch) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    //Генерация тестового набора (100 наборов разных размеров)
    public static TestData[] generateTestSet(int count, int minLength, int maxLength) {
        TestData[] testSet = new TestData[count];
        int lengthStep = (maxLength - minLength) / count;

        for (int i = 0; i < count; i++) {
            int textLength = minLength + (i * (maxLength - minLength) / count);
            int patternLength = Math.max(3, textLength / (5 + random.nextInt(25)));
            if (patternLength > textLength) {
                patternLength = Math.min(textLength, 20);
            }

            // Тип теста: 0 - со случайной подстрокой, 1 - без подстроки, 2 - худший случай
            int testType = random.nextInt(3);
            String[] data;
            switch (testType) {
                case 0:
                    data = generateWithSubstring(textLength, patternLength);
                    break;
                case 1:
                    data = generateWithoutSubstring(textLength, patternLength);
                    break;
                default:
                    data = generateWorstCase(textLength, patternLength);
                    break;
            }

            testSet[i] = new TestData(i + 1, data[0], data[1], textLength, patternLength, testType);
        }

        return testSet;
    }

    // Класс для хранения тестовых данных
    public static class TestData {
        public final int id;
        public final String text;
        public final String pattern;
        public final int textLength;
        public final int patternLength;
        public final int testType;

        public TestData(int id, String text, String pattern, int textLength, int patternLength, int testType) {
            this.id = id;
            this.text = text;
            this.pattern = pattern;
            this.textLength = textLength;
            this.patternLength = patternLength;
            this.testType = testType;
        }

        public String getTestTypeName() {
            switch (testType) {
                case 0: return "contains";
                case 1: return "not_contains";
                case 2: return "worst_case";
                default: return "unknown";
            }
        }
    }
}
