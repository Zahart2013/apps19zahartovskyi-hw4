
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;

import javax.swing.*;

/**
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        assertEquals(2, pm.load("abc", "abce"));
        assertEquals(2, pm.load("abcd abcde"));
        assertEquals(1, pm.load("abcdef"));
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);

        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};
        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));

        String pref1 = "ab";

        Iterable<String> result1 = pm.wordsWithPrefix(pref1, k);

        assertThat(result1, containsInAnyOrder(expResult));
    }

    @Test
    public void testContains() {
        assertTrue(pm.contains("abce"));
        assertFalse(pm.contains("ab"));
        assertFalse(pm.contains("abec"));
    }

    @Test
    public void testSize() {
        assertEquals(5, pm.size());
    }

    @Test
    public void testDelete() {
        assertFalse(pm.delete("ba"));
        assertTrue(pm.delete("abc"));

        assertFalse(pm.contains("abc"));
        assertEquals(4, pm.size());
    }
}
