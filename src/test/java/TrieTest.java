import org.example.Trie;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void testInsertAndSearch() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        assertTrue(trie.search("apple"));
        assertTrue(trie.search("app"));
        assertFalse(trie.search("appl"));
        assertFalse(trie.search("orange"));
        assertFalse(trie.search("ban"));
        assertTrue(trie.search("banana"));
    }

    @Test
    public void testStartsWith() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        assertTrue(trie.startsWith("app"));
        assertTrue(trie.startsWith("apple"));
        assertTrue(trie.startsWith("banana"));
        assertFalse(trie.startsWith("orange"));
    }

    @Test
    public void testDelete() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        assertTrue(trie.search("apple"));
        assertTrue(trie.search("app"));
        assertTrue(trie.search("banana"));

        trie.delete("app");

        assertFalse(trie.search("app"));
        assertTrue(trie.search("apple"));
        assertTrue(trie.search("banana"));

        trie.delete("apple");

        assertFalse(trie.search("app"));
        assertFalse(trie.search("apple"));
        assertTrue(trie.search("banana"));
    }

    @Test
    public void testCountWords() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        assertEquals(3, trie.countWords());

        trie.delete("app");

        assertEquals(2, trie.countWords());
    }
}
