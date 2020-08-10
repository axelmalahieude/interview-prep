import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;

public class ThreeSum {
    public static void main(String[] args) {
        final int SIZE = 50;
        int[] input = new int[SIZE];

        Random generator = new Random();
        for (int i = 0; i < SIZE; i++) {
            input[i] = generator.nextInt() % SIZE;
        }

        solution(input, 30);
    }

    private static void solution(int[] array, int sum) {
        // idea: for each value, find the new sum by subtracting the value from the current sum
        // then run two sum on the remaining elements with the new sum => O(n^2)

        HashSet<List<Integer>> results = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int newSum = sum - array[i];
            for (int j = 0; j < array.length; j++) {
                if (i != j) {
                    Integer current = map.get(array[j]);
                    if (current == null) current = 0;
                    map.put(array[j], current + 1);
                }
            }

            for (int j = 0; j < array.length; j++) {
                if (i != j) {
                    int neededValue = newSum - array[j];
                    if (map.get(neededValue) != null && (neededValue != array[j] || map.get(neededValue) >= 2)) {
                        List<Integer> threesum = new ArrayList<>();
                        threesum.add(array[i]);
                        threesum.add(array[j]);
                        threesum.add(neededValue);
                        threesum.sort(null);
                        results.add(threesum);
                    }
                }
            }
        }

        // print results
        Iterator<List<Integer>> it = results.iterator();
        while(it.hasNext()) {
            List<Integer> list = it.next();
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}