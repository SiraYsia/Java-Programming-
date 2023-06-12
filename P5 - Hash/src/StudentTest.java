import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTest {

	@Test
	public void testingContains() {
		MyHashSet<String> s = new MyHashSet<String>();
		s.add("hello");
		s.add("Juice");
		s.add("apple");
		s.add("cake");
		assertEquals(true, s.contains("apple"));
		assertEquals(8, s.getCapacity());
		assertEquals(4, s.size());
		assertEquals(true, s.remove("apple"));

	}

	@Test
	public void testingReHash() {
		MyHashSet<String> s = new MyHashSet<String>();
		for (int i = 0; i < 600; i++) {
			s.add("Entry " + i);
		}
		assertEquals(1024, s.getCapacity());
		assertEquals(600, s.size());
	}

}
