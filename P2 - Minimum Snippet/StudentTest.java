
import org.junit.Test;

import java.util.Arrays;

import junit.framework.TestCase;

public class StudentTest extends TestCase {

	static MinimumSnippet get(String[] doc, String[] terms) {
		return new MinimumSnippet(Arrays.asList(doc), Arrays.asList(terms));
	}

	@Test
	public void test1in123() {
		MinimumSnippet m = get(new String[] { "A", "C", "B", "D", "F", "G", "Z", "H" }, new String[] { "F", "G", "Z" });
		assertTrue(m.foundAllTerms());
		assertEquals(4, m.getStartingPos());
		assertEquals(6, m.getEndingPos());
		assertEquals(3, m.getLength());

	}

	@Test
	public void test1in1234() {
		MinimumSnippet m = get(new String[] { "B", "Z", "F", "F", "K", "O", "Y", "Z" },
				new String[] { "Z", "F", "O", "Y" });
		System.out.println(m.getStartingPos());
		System.out.println(m.getEndingPos());
		System.out.println(m.getLength());

		assertTrue(m.foundAllTerms());
		assertEquals(3, m.getStartingPos());
		assertEquals(7, m.getEndingPos());
		assertEquals(5, m.getLength());
	}

}