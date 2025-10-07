package io.github.bluething.java;

import java.util.Random;

public class Main {
    public static void bubbleSort(int[] arr) {
        int i = 0, j = 0, temp = 0;
        boolean swapped = false;

        for (i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int temp = arr[i];  // Take the current element (the one to be inserted)
            int j = i - 1;      // Start comparing with the previous element
            // Shift elements that are greater than temp to one position ahead
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        mergeSortHelper(arr, 0, arr.length - 1);
    }
    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid);                 // sort left half
            mergeSortHelper(arr, mid + 1, right);       // sort right half
            merge(arr, left, mid, right);                   // merge sorted halves
        }
    }
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please specify one of: BS | SS | IS | MS | QS | HS | CS | BuS |ShS");
            System.exit(1);
        }

        int size = 100_000;
        int[] sources = generateDistinctShuffledArray(size);
        String algorithm = args[0];

        int[] arr;
        long start, end;
        switch (algorithm) {
            case "BS":
                arr = sources.clone();
                System.out.println("Sort using Bubble Sort algorithm");
                System.out.println("Is array sorted before sorting? " + isSorted(arr));
                start = System.nanoTime();
                bubbleSort(arr);
                end = System.nanoTime();
                System.out.println("Is array sorted after sorting? " + isSorted(arr));
                System.out.println("Elapsed time: " + (end - start) / 1_000_000 + " ms");
                break;
            case "SS":
                arr = sources.clone();
                System.out.println("Sort using Selection Sort algorithm");
                System.out.println("Is array sorted before sorting? " + isSorted(arr));
                start = System.nanoTime();
                selectionSort(arr);
                end = System.nanoTime();
                System.out.println("Is array sorted after sorting? " + isSorted(arr));
                System.out.println("Elapsed time: " + (end - start) / 1_000_000 + " ms");
                break;
            case "IS":
                arr = sources.clone();
                System.out.println("Sort using Insertion Sort algorithm");
                System.out.println("Is array sorted before sorting? " + isSorted(arr));
                start = System.nanoTime();
                insertionSort(arr);
                end = System.nanoTime();
                System.out.println("Is array sorted after sorting? " + isSorted(arr));
                System.out.println("Elapsed time: " + (end - start) / 1_000_000 + " ms");
                break;
            case "MS":
                arr = sources.clone();
                System.out.println("Sort using Merge Sort algorithm");
                System.out.println("Is array sorted before sorting? " + isSorted(arr));
                start = System.nanoTime();
                mergeSort(arr);
                end = System.nanoTime();
                System.out.println("Is array sorted after sorting? " + isSorted(arr));
                System.out.println("Elapsed time: " + (end - start) / 1_000_000 + " ms");
                break;
        }
    }

    public static int[] generateDistinctShuffledArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        Random random = new Random();
        for (int i = size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
