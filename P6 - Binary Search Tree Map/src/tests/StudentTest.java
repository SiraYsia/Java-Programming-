package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import searchTree.SearchTreeMap;

public class StudentTest {

	@Test
	public void test() {
	
			SearchTreeMap<Integer,String> example = new SearchTreeMap<Integer,String>();
			assertEquals(0, example.size());
			example.put(9, "Nine");
			example.put(5, "Five");
			example.put(2, "Two");
			assertEquals(3, example.size());
			assertEquals("Two", example.get(2));
			assertEquals(null, example.get(8));
		}	
}
