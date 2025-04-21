// In this problem, doing a binary search and if the mid word is "" than, we expand on both sides of mid and wherever non-empty word
// found we make that as the mid, and continue.

// Time Complexity : O(logn) in avg case, O(n) in worst case if all words are ""
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// "static void main" must be defined in a public class.
class Main {
    private static int BinarySearch(String[] words, String s) {
        // Base
        if (words == null || words.length == 0) {
            return -1;
        }
        // Low and high
        int low = 0;
        int high = words.length - 1;
        // Start binary search
        while (low <= high) {
            // Compute the mid
            int mid = low + (high - low) / 2;
            // If blank then take pointers, one on left of mid and one on right of mid
            if (words[mid] == "") {
                int l = mid - 1;
                int r = mid + 1;
                // Run a loop
                while (true) {
                    // Check if l and r are in bounds(that is valid index)
                    if (l < 0 && r > words.length - 1) {
                        return -1;
                    }
                    // If at any position, non empty word found, make that position as mid and break
                    if (l >= 0 && words[l] != "") {
                        mid = l;
                        break;
                    }
                    if (r <= words.length - 1 && words[r] != "") {
                        mid = r;
                        break;
                    }
                    // Otherwise expand
                    l--;
                    r++;
                }
            }
            // Now compare the word with word at mid
            if (s.compareTo(words[mid]) == 0) {
                // If equal, return mid position
                return mid;
            }
            // Else if word is lexicographically smaller then word at mid, move left
            else if (s.compareTo(words[mid]) < 0) {
                high = mid - 1;
            }
            // Else right
            else {
                low = mid + 1;
            }
        }
        // If not found, return -1
        return -1;
    }

    public static void main(String[] args) {
        String[] words = { "for", "geeks", "", "", "", "", "", "", "", "", "", "", "", "", "web" };
        String s = "zebra";
        System.out.println(BinarySearch(words, s));
    }
}