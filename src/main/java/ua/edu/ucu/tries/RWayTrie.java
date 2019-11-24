package ua.edu.ucu.tries;

import ua.edu.ucu.utils.Queue;

public class RWayTrie implements Trie {
    private static int R = 256;
    private Node root;

    @Override
    public void add(Tuple t) {
        root = put(root, t, 0);
    }

    private Node put(Node x, Tuple t, int d) { // Change value associated with key if in subtrie rooted at x.
        if (x == null) {
            x = new Node();
        }
        if (d == t.term.length()) {
            x.val = t.weight;
            return x;
        }
        char c = t.term.charAt(d); // Use dth key char to identify subtrie.
        x.next[c] = put(x.next[c], t, d + 1);
        return x;
    }

    @Override
    public boolean contains(String word) {
        Node x = contains(root, word, 0);
        return x != null;
    }

    private Node contains(Node x, String key, int d) { // Return value associated with key in the subtrie rooted at x.
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.val == null) {
                return null;
            }
            return x;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return contains(x.next[c], key, d + 1);
    }

    @Override
    public boolean delete(String word) {
        if (this.contains(word)) {
            root = delete(root, word, 0);
            return true;
        }
        return false;
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue q = new Queue();
        collect(get(root, s, 0), s, q);
        return q.iterable();
    }

    private void collect(Node x, String pre, Queue q) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            q.enqueue(pre);
        }
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    private Node get(Node x, String key, int d) { // Return value associated with key in the subtrie rooted at x.
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return get(x.next[c], key, d + 1);
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        int cnt = 0;
        if (x.val != null) {
            cnt++;
        }
        for (char c = 0; c < R; c++) {
            cnt += size(x.next[c]);
        }
        return cnt;
    }

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

}
