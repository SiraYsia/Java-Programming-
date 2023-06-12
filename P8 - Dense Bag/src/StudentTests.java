import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;


/**
 * Some test cases distributed as part of the project.
 */
public class StudentTests {

	
	/**
     * Utility function Given a String, return a list consisting of one
     * character Strings
     */
    public static List<String> makeListOfCharacters(String s) {
        List<String> lst = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++)
            lst.add(s.substring(i, i + 1));
        return lst;
    }

    /**
     * Test adding to a Bag
     * 
     */
    @Test
    public void testBagAddSizeUniqueElements() {
        List<String> lst = makeListOfCharacters("aaabbc");
        HeavyBag<String> b = new HeavyBag<String>();
        b.addAll(lst);
        assertEquals(6, b.size());
        assertEquals(3, b.uniqueElements().size());
    }

    /**
     * Test checking if a Bag contains a key, and the count for each element
     * 
     */
    @Test
    public void testBagContainsAndCount() {
        List<String> lst = makeListOfCharacters("aaabbc");
        HeavyBag<String> b = new HeavyBag<String>();
        b.addAll(lst);
        assertEquals(6, b.size());
        assertEquals(3, b.uniqueElements().size());
        assertTrue(b.contains("a"));
        assertTrue(b.contains("b"));
        assertTrue(b.contains("c"));
        assertFalse(b.contains("d"));
        assertEquals(3, b.getCount("a"));
        assertEquals(2, b.getCount("b"));
        assertEquals(1, b.getCount("c"));
        assertEquals(0, b.getCount("d"));
    }
    @Test 
    public void testIterator() {
    	List<String> list = makeListOfCharacters("ilovecoding");
    	HeavyBag<String> b = new HeavyBag<>();
    	b.addAll(list);
    	assertEquals(11, b.size());
    	assertEquals(9, b.uniqueElements().size());
    	Iterator<String> iterator = b.iterator();
    	assertEquals("c", iterator.next());
		System.out.println(iterator.next());
		System.out.println(iterator.next());
		System.out.println(iterator.next());
		System.out.println(iterator.next());
		System.out.println(iterator.next());
    	assertEquals(true, iterator.hasNext());
    	
    	
    	
    	
    }
    @Test
    public void Equals() {
    	List<String> list = makeListOfCharacters("ilovecoding");
    	List<String> lsts = makeListOfCharacters("codinglovei");
        HeavyBag<String> b = new HeavyBag<String>();
        HeavyBag<String>a=new HeavyBag<String>();
        b.addAll(list);
        a.addAll(lsts);
        assertTrue(a.equals(b));
        
        List<String> list2 = makeListOfCharacters("codingiseasy");
    	List<String> lsts2 = makeListOfCharacters("codingcanbeeasy");
        HeavyBag<String> b1 = new HeavyBag<String>();
        HeavyBag<String>a1=new HeavyBag<String>();
        b1.addAll(list2);
        a1.addAll(lsts2);
        assertFalse((a.equals(b1)));

    }
    
}