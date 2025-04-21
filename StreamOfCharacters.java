// In this problem, we are trying to use trie as a suffix tree. Storing the words in reverse order. This helps because, for each 
// incoming char we have to check if that present in list, if not we check if any word with previous char and current char present,
// if not we check with previous to previous and so on. For eg. if incoming chars a-b-c-d, we check d present? cd present? bcd present?
// and so on. So to avoid n^2 time, we just simply store given list of words in reverse orders and then when chars come, we check
// also in reverse order, eg. d present? dc present? dcb present? This will make sure O(n) complexity.

// Time Complexity : O(nl) n = length of list and l = avg length of each word 
// Space Complexity : O(nl)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class StreamChecker {
    // Define trienode class
    class TrieNode {
        // Children array of length 26
        TrieNode[] children;
        // isEnd to determine the end of word
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    // Root node of trie
    TrieNode root;
    // sb to store all incoming chars as string
    StringBuilder sb;

    // Insert in trie method
    private void insert(String word) {
        // Take the curr node as root
        TrieNode curr = root;
        // Loop over the word in reverse manner, since we want to insert the word in
        // reverse order
        for (int i = word.length() - 1; i >= 0; i--) {
            // Take one char at a time
            char c = word.charAt(i);
            // Check in children of root, if at this char it is null
            if (curr.children[c - 'a'] == null) {
                // Then create new trienode at that position
                curr.children[c - 'a'] = new TrieNode();
            }
            // And move to that char, and repeat till inserted all chars of the word
            curr = curr.children[c - 'a'];
        }
        // Once whole word is inserted, make the last char isEnd flag true indicating
        // end of the word
        curr.isEnd = true;
    }

    // trie contains suffix method
    private boolean contains(String suffix) {
        // Take curr
        TrieNode curr = root;
        // Loop over suffix in reverse
        for (int i = suffix.length() - 1; i >= 0; i--) {
            // Take char
            char c = suffix.charAt(i);
            // If children of root at this char is null, return false, since we will not be
            // able to find any word
            if (curr.children[c - 'a'] == null) {
                return false;
            }
            // Else move curr to that char
            curr = curr.children[c - 'a'];
            // Check if isEnd true at any char, return true, because, we can return true if
            // d is present or dc or dcb, anything works
            if (curr.isEnd) {
                return true;
            }
        }
        // Else return false
        return false;
    }

    // Main method
    public StreamChecker(String[] words) {
        // Base
        if (words == null || words.length == 0) {
            return;
        }
        // Initialize root and sb
        root = new TrieNode();
        sb = new StringBuilder();
        // Insert all words in trie
        for (String word : words) {
            insert(word);
        }
    }

    // Sequence of incoming chars method
    public boolean query(char letter) {
        // Append the char to sb and check if trie contains any suffix
        sb = sb.append(letter);
        return contains(sb.toString());
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */