/**
 * Given a string S and a set of words D, find the longest word in D that is a subsequence of S.
 * Word W is a subsequence of S if some number of characters, possibly zero, can be deleted from S to form W, without reordering the remaining characters.
 * Note: D can appear in any format (list, hash table, prefix tree, etc.
 * For example, given the input of S = "abppplee" and D = {"able", "ale", "apple", "bale", "kangaroo"} the correct output would be "apple"
 * The words "able" and "ale" are both subsequences of S, but they are shorter than "apple".
 * The word "bale" is not a subsequence of S because even though S has all the right letters, they are not in the right order.
 * The word "kangaroo" is the longest word in D, but it isn't a subsequence of S.
 */
import java.util.HashMap;
import java.util.ArrayList;

 public class Main {
    public static void main(String[] args) {
        String[] d = { "able", "ale", "apple", "bale", "kangaroo" };
        String s = "abppplee";
        System.out.println(longestSubsequence2(d, s));
    } 

    private static String longestSubsequence2(String[] d, String s) {
        String longestWord = "";

        // preprocess base string

        // map chars in s to their index
        HashMap<Character, ArrayList<Integer>> indices = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            ArrayList<Integer> indicesForChar = indices.get(s.charAt(i));
            if (indicesForChar == null) {
                indicesForChar = new ArrayList<>();
            }
            indicesForChar.add(i); // appending to end forces list to be sorted
            indices.put(s.charAt(i), indicesForChar);
        }

        // check strings against base
        for (int i = 0; i < d.length; i++) {
            if (isSubsequence2(d[i], indices) && d[i].length() > longestWord.length()) {
                longestWord = d[i];
            }
        }
        
        return longestWord;
    }

    private static boolean isSubsequence2(String candidate, HashMap<Character, ArrayList<Integer>> map) {
        // subsequence if indices of all characters in candidate in the map are in ascending order
        int index = -1;
        for (int i = 0; i < candidate.length(); i++) {
            ArrayList<Integer> indicesForChar = map.get(candidate.charAt(i));
            if (indicesForChar == null) {
                return false;
            }

            // binary search this list for the first index larger than the current index
            if (indicesForChar.get(indicesForChar.size() - 1) < index) {
                return false; // out of characters
            } else if (indicesForChar.get(0) > index) {
                index = indicesForChar.get(0); // haven't used this character yet
                continue;
            }

            // after these edge cases, the character to use is somewhere in the middle
            // binary search for first element larger than index
            int start = 0, end = indicesForChar.size() - 1;
            while (end - start > 1) {
                int mid = start + (end - start) / 2;
                if (indicesForChar.get(mid) > index) { // search first half
                    end = mid;
                } else if (indicesForChar.get(mid) <= index) { // search second half
                    start = mid;
                }
            }

            // at this point we have an array of size 2; check that index fits in between
            // i.e. that a[start] <= index < a[end]
            if (indicesForChar.get(start) <= index && index < indicesForChar.get(end)) {
                index = indicesForChar.get(end);
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * O(S*D), S length of s, D length of d
     * If S is large, this is inefficient.
     */
    private static String longestSubsequence(String[] d, String s) {
        String longestWord = "";

        for (int i = 0; i < d.length; i++) {
            if (isSubsequence(d[i], s) && d[i].length() > longestWord.length()) {
                longestWord = d[i];
            }
        }

        return longestWord;
    }

    private static boolean isSubsequence(String candidate, String word) {
        int c = 0, w = 0;
        for (; c < candidate.length() && w < word.length(); w++) {
            if (candidate.charAt(c) == word.charAt(w)) {
                c++;
            }
        }

        return c == candidate.length();
    }
 }