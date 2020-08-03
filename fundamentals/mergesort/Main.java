/**
 * Implementation of merge sort
 * 
 * 1. Split array into 2
 * 2. Sort each array (using merge sort)
 * 3. Merge the now-sorted arrays
 */
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int length = 50;
        int[] input = new int[length];

        Random generator = new Random();
        for (int i = 0; i < length; i++) {
            input[i] = generator.nextInt() % 50;
        }
        printArray(input);
        printArray(mergesort(input, 0, 50));
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    private static int[] mergesort(int[] input, int start, int end) {
        // base case
        if (end - start == 1) {
            int[] ret = new int[1];
            ret[0] = input[start];
            return ret;
        } 

        // sort the first and second halves
        int midpoint = start + (end - start) / 2;
        int[] first = mergesort(input, start, midpoint);
        int[] second = mergesort(input, midpoint, end);

        // merge the two halves
        int[] sorted = new int[end - start];
        for(int i = 0, f = 0, s = 0; i < sorted.length; i++) {
            if (f == first.length) {
                sorted[i] = second[s];
                s++;
            } else if (s == second.length) {
                sorted[i] = first[f];
                f++;
            } else if (first[f] < second[s]) {
                sorted[i] = first[f];
                f++;
            } else {
                sorted[i] = second[s];
                s++;
            }
        }

        return sorted;
    }
}