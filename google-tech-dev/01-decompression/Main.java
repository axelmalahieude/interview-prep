/**
 * https://techdevguide.withgoogle.com/paths/advanced/compress-decompression#code-challenge
 * 
 * Thoughts and general approach:
 * Recursion-like approach since compression can have nesting
 * Iterate through string; whenever we get to a substring that is compressed, recurse to decompress
 *   that substring.
 * Requires being able to find the matching closing bracket
 * Requires converting numbers from string to int, including multiple-digit numbers
 */


public class Main {
    public static void main(String[] args) {
        // String input = "2[ab4[f2[c]a]]20[ab]c";
        String input = "2[ab4[f2[c]a]]1[ab]c";

        String output = decompress(1, input);
        System.out.println(output);
        // abfccafccafccafccaabfccafccafccafccaababababababababababababababababababababc
    }

    private static String decompress(int num, String s) {
        // loop through string, filling in string builder as we go along

        if (num == 0) return "";
        if (s == "") return "";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') { // numbers can't start with 0
                int lastDigit = s.indexOf('[', i); // take advantage of input correctness
                String number = s.substring(i, lastDigit);
                int indexOfClosingBracket = matchingClosingBracket(s, lastDigit + 1);
                String substring = decompress(Integer.valueOf(number), s.substring(lastDigit + 1, indexOfClosingBracket));
                sb.append(substring);
                i = indexOfClosingBracket;
            } else {
                sb.append(c);
            }
        }

        String currentSubstring = sb.toString();
        for (int j = 1; j < num; j++) {
            sb.append(currentSubstring);
        }
        return sb.toString();
    }

    private static int matchingClosingBracket(String s, int start) {
        int count = 1; // starting character is '['
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                count--;
            } else if (s.charAt(i) == '[') {
                count++;
            }

            if (count == 0) {
                return i;
            }
        }

        return s.length() - 1;
    }
}
