/**
 * Implementation of merge sort
 * 
 * 1. Split array into 2
 * 2. Sort each array (using merge sort)
 * 3. Merge the now-sorted arrays
 */
import java.util.Random;

public class Mergesort {
    public static void main(String[] args) {
        int length = 50;
        int[] input = new int[length];

        Random generator = new Random();
        for (int i = 0; i < length; i++) {
            input[i] = generator.nextInt() % 50;
        }
        printArray(input);
        mergesort(input, 0, 50);
        printArray(input);
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    private static void mergesort(int[] input, int start, int end) {
        // base case
        if (end - start == 1) {
            return;
        } 

        // sort the first and second halves
        int midpoint = start + (end - start) / 2;
        mergesort(input, start, midpoint);
        mergesort(input, midpoint, end);

        // merge the two halves
        int[] sorted = new int[end - start];
        for(int i = 0, f = start, s = midpoint; i < sorted.length; i++) {
            if (f == midpoint) {
                sorted[i] = input[s];
                s++;
            } else if (s == end) {
                sorted[i] = input[f];
                f++;
            } else if (input[f] < input[s]) {
                sorted[i] = input[f];
                f++;
            } else {
                sorted[i] = input[s];
                s++;
            }
        }

        // for in-place sorting
        for (int i = start; i < end; i++) {
            input[i] = sorted[i - start];
        }
    }
}