package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import listClass.BasicLinkedList;

public class StudentTests {

	/* Write a lot of tests! */

	@Test
	public void addToFront1() {
		BasicLinkedList<String> answer = new BasicLinkedList<String>();
		answer.addToFront("Pizza").addToFront("Cake").addToFront("Juice");
		String result = "";
		for (String e : answer) {
			result += e;
			System.out.println(result);

		}
		System.out.println(result);
		assertEquals(result, "JuiceCakePizza");
	}

	@Test
	public void addToFront2() {
		BasicLinkedList<String> answer = new BasicLinkedList<String>();
		answer.addToFront("Pizza");
		String result = "";
		for (String e : answer) {
			result += e;
			System.out.println(result);

		}
		System.out.println(result);
		assertEquals(result, "Pizza");
	}

	@Test
	public void getFirst1() {
		BasicLinkedList<String> answer = new BasicLinkedList<String>();
		answer.addToFront("Pizza").addToFront("Cake").addToFront("Juice");

		System.out.print(answer.getFirst());
		assertEquals(answer.getFirst(), "Juice");
	}

	@Test
	public void test6() {
		BasicLinkedList<String> answer = new BasicLinkedList<String>();
		assertEquals(answer.getFirst(), null);
		System.out.print(answer.getFirst());
	}

	@Test
	public void getLast1() {
		BasicLinkedList<String> answer = new BasicLinkedList<String>();
		assertEquals(answer.getLast(), null);
		// System.out.print(answer.getLast());
	}

	@Test
	public void test9() {
		BasicLinkedList<String> answer = new BasicLinkedList<String>();
		answer.addToFront(null);

		assertEquals(answer.getSize(), 1);
		// System.out.print(answer.getSize());
	}

	@Test
	public void test10() {
		BasicLinkedList<String> answer = new BasicLinkedList<String>();
		answer.addToEnd("Cake");

		assertEquals(answer.retrieveFirstElement(), "Cake");
		// assertEquals(answer.retrieveLastElement() , "Cake");

	}

	@Test
	public void test10q() {
		BasicLinkedList<String> answer = new BasicLinkedList<String>();
		answer.addToFront("Cake").addToFront("Jake");

		System.out.print(answer.retrieveLastElement());
		System.out.print(answer.getSize());

		assertEquals(answer.retrieveFirstElement(), "Jake");
		// assertEquals(answer.retrieveLastElement() , "Cake");

	}

	@Test
	public void removeInstance() {
		BasicLinkedList<String> blist = new BasicLinkedList<String>();
		blist.addToEnd("jakw").addToEnd("lkp").addToEnd("jakw").addToEnd("lol");

		blist.removeAllInstances("jakw");
		System.out.print(blist.getLast());

		assertEquals(blist.getSize(), 2);
		assertEquals(blist.getFirst(), "lkp");
		assertEquals(blist.getLast(), "lol");

	}

	@Test
	public void test11() {
		BasicLinkedList<String> answer = new BasicLinkedList<String>();
		answer.addToEnd("Pizza").addToEnd("Cake").addToEnd("Juice").addToEnd("Deer").addToEnd("Zebra")
		.addToEnd("bottle");
		;
		assertEquals(answer.getSize(), 6);
		assertEquals(answer.retrieveFirstElement(), "Pizza");
		assertEquals(answer.retrieveLastElement(), "bottle");

	}

	@Test
	public void test12() {
		BasicLinkedList<String> answer = new BasicLinkedList<String>();
		answer.addToEnd("Pizza").addToEnd("jak").addToEnd("jak").addToEnd("jak").addToEnd("3");

		answer.removeAllInstances("jak");
		assertEquals(answer.getSize(), 2);

		assertEquals(answer.retrieveFirstElement(), "Pizza");
		assertEquals(answer.retrieveLastElement(), "3");
	}
}