package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int counter = 0;
        if (strings != null) {
            for (String string : strings) {
                trie.add(new Tuple(string, string.length()));
                counter++;
            }
        }
        return counter;
    }

    public int load(String string) {
        int counter = 0;
        if (string.contains(" ")) {
            String[] strings = string.split(" ");
            for (String each : strings) {
                trie.add(new Tuple(each, each.length()));
                counter++;
            }
            return counter;
        }
        trie.add(new Tuple(string, string.length()));
        return 1;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() >= 2) {
            return trie.wordsWithPrefix(pref);
        } else {
            return null;
        }
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() < 2) {
            return null;
        }
        ArrayList<String> arr = new ArrayList<>();
        for (String each :
                trie.wordsWithPrefix(pref)) {
            arr.add(each);
        }
        arr.sort(Comparator.comparingInt(String::length));
        ArrayList<String> arr1 = new ArrayList<>();
        int index = 0;
        if (pref.length() > 2) {
            int currentK = pref.length();
            while (currentK < k + pref.length() - 1) {
                if (index != 0 && arr.get(index).length() !=
                        arr1.get(arr1.size() - 1).length()) {
                    currentK++;
                }
                arr1.add(arr.get(index));
                index++;
            }
        } else {
            int currentK = 3;
            while (currentK < k + 2) {
                if (index != 0 && arr.get(index).length() !=
                        arr1.get(arr1.size() - 1).length()) {
                    currentK++;
                }
                arr1.add(arr.get(index));
                index++;
            }
        }
        return arr1;
    }

    public int size() {
        return trie.size();
    }
}
