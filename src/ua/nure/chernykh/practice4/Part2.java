package ua.nure.chernykh.practice4;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Part2 {

    private static final int FIFTY = 50;
    private static final int TWO = 2;
    private static final int TEN = 10;

    public static void main(String[] args) {
        writeSortedNumbers("part2.txt", "part2_sorted.txt");
    }

    public static void writeSortedNumbers(String path, String sortedPath) {
        int[] numbers = generateNumbers(FIFTY);
        Util.writeToFile(path, arrayToString(numbers));
        mergeSort(numbers);
        Util.writeToFile(sortedPath, arrayToString(numbers));
        System.out.println("input  => " + Util.readFile(path));
        System.out.println("output => " + Util.readFile(sortedPath));
    }

    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int n : arr) {
            sb.append(n).append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    public static void mergeSort(int[] arr) {
        int n = arr.length;
        if (n < TWO) {
            return;
        }
        int mid = n / TWO;
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = 0; i < n - mid; i++) {
            right[i] = arr[i + mid];
        }
        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        int leftLength = left.length;
        int rightLength = right.length;
        while (i < leftLength && j < rightLength) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < leftLength) {
            arr[k++] = left[i++];
        }
        while (j < rightLength) {
            arr[k++] = right[j++];
        }
    }

    private static int[] generateNumbers(int max) {
        int[] numbers = new int[TEN];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = getRandomIntegerBetweenRange(0, max);
        }
        return numbers;
    }

    private static int getRandomIntegerBetweenRange(double min, double max) {
        try {
            double x = (SecureRandom.getInstance("SHA1PRNG").nextDouble() * ((max - min) + 1)) + min;
            return (int) x;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algorithm");
        }
        return 0;
    }


}
