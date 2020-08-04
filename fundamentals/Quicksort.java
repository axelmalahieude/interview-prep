/**
 * Implementation of quicksort
 * 
 * 1. Select pivot
 * 2. Put smaller elements on left, larger elements on right
 * 3. Repeat with sub-arrays
 */
import java.util.Random;

public class Quicksort {
    public static void main(String[] args) {
        int length = 50;
        int[] input = new int[length];

        Random generator = new Random();
        for (int i = 0; i < length; i++) {
            input[i] = generator.nextInt() % 50;
        }
        printArray(input);
        quicksort(input, 0, 50);
        printArray(input);
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    private static void quicksort(int[] input, int start, int end) {
        // base case
        if (end - start < 2) {
            return;
        }

        // pick pivot
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += input[i];
        }
        int pivot = sum / (end - start); // average value

        // reorder elements around pivot
        int pivotPoint = start;
        for (int i = start; i < end; i++) {
            if (input[i] <= pivot) {
                swap(input, pivotPoint, i);
                pivotPoint++;
            }
        }

        // sort the two halves
        quicksort(input, start, pivotPoint);
        quicksort(input, pivotPoint, end);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}