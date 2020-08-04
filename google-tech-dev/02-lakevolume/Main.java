/**
 * Imagine an island that is in the shape of a bar graph. 
 * When it rains, certain areas of the island fill up with 
 * rainwater to form lakes. Any excess rainwater the island 
 * cannot hold in lakes will run off the island to the west 
 * or east and drain into the ocean.
 * 
 * Given an array of positive integers representing 2-D bar heights, 
 * design an algorithm (or write a function) that can compute the 
 * total volume (capacity) of water that could be held in all lakes 
 * on such an island given an array of the heights of the bars. 
 * Assume an elevation map where the width of each bar is 1.
 * 
 * Example: Given [1,3,2,4,1,3,1,4,5,2,2,1,4,2,2], return 15 
 * (3 bodies of water with volumes of 1,7,7 yields total volume of 15)
 */

public class Main {
    public static void main(String[] args) {
        int[] input = { 1, 3, 2, 4, 1, 3, 1, 4, 5, 2, 2, 1, 4, 2, 2 };
        
        System.out.println(lakeVolume(input));
    }

    private static int lakeVolume(int[] island) {
        if (island.length == 0) return 0;
        int volume = 0;

        // for each element, volume of water held = min(highest element to left, highest element to right) - current element
        // calculate these highest elements as we go

        // brute force: calculate these each time 
        // for (int i = 0; i < island.length; i++) {
        //     int highestLeft = island[i];
        //     int highestRight = island[i];
        //     for (int j = 0; j < i; j++) {
        //         highestLeft = max(highestLeft, island[j]);
        //     }
        //     for (int j = island.length - 1; j > i; j--) {
        //         highestRight = max(highestRight, island[j]);
        //     }
        //     volume += min(highestLeft, highestRight) - island[i];
        // }

        // dynamic programming: precalculate them

        // left to right, going "up" stairs
        int[] highestLeft = new int[island.length];
        int currentHighest = island[0];
        for (int i = 0; i < island.length; i++) {
            currentHighest = max(currentHighest, island[i]);
            highestLeft[i] = currentHighest;
        }

        // right to left, going "up" stairs (arrays will be sorted as a result)
        int[] highestRight = new int[island.length];
        currentHighest = island[island.length - 1];
        for (int i = island.length - 1; i >= 0; i--) {
            currentHighest = max(currentHighest, island[i]);
            highestRight[i] = currentHighest;
        }

        // same calculation as before
        for (int i = 0; i < island.length; i++) {
            volume += min(highestLeft[i], highestRight[i]) - island[i];
        }

        return volume;
    }

    private static int min(int a, int b) {
        return a > b ? b : a;
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }
}