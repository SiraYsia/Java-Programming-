package tests;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import programs.Passport;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	/*
	 * Each method in a JUnit StudentTest class represents a test. You can write
	 * code in a method of this class as you do in the main method of a driver. As
	 * you write your code, define methods in this class that test each of the
	 * methods you need to implement. When you run a method you can have
	 * System.out.println statements to see the results of your code. Using this
	 * approach is simpler than defining driver classes.
	 * 
	 * For this project you don't need to worry about adding assertions (we will
	 * talk about them soon). If you don't add assertions, by default, every test
	 * will pass (so when you run your student tests you will see a green bar). We
	 * have left two examples of tests below so you can see how you can test your
	 * code.
	 * 
	 * You can run a single test (e.g., testingtoString() below) by double-clicking
	 * on the method's name and selecting Run-->Run As-->JUnit Test. You can also
	 * double-click on the method's name and select the white triangle that is
	 * inside of a green circle (under Navigate menu entry).
	 */

	@Test
	public void testingtoString() {
		Passport passport1 = new Passport("Rose", "Sanders");
		System.out.println(passport1);

	}

	@Test
	public void testingSetSeparator() {
		Passport passport1 = new Passport("Tom", "Johnson");
		System.out.println(passport1);

		passport1.setSeparator('#');
		System.out.println(passport1);

	}

	@Test
	public void testingresetNumberOfPassportObjects() {
		Passport passport1 = new Passport("Katie", "Yohanes");
		System.out.println(passport1);

		Passport.resetNumberOfPassportObjects();
		System.out.print(0);

	}

	@Test
	public void testingnormalize() {

		Passport passport1 = new Passport("Katie", "Yohanes");
		System.out.println(passport1);

		System.out.println("=============");
		System.out.println("Normalizing");

		Passport normalized1 = Passport.normalize(passport1, true);
		System.out.println("Normalize #1: " + normalized1);

	}

	@Test
	public void testingchangeLastname() {

		Passport passport1 = new Passport("Rob", "", "DeguFULE");
		System.out.print(passport1 + "\n");

		boolean changed = passport1.changeLastname("Tamiru");
		System.out.print("Changed: " + changed + "\n");
		System.out.print(passport1 + "\n");

		changed = passport1.changeLastname("     ");
		System.out.print("Changed: " + changed + "\n");
		System.out.print(passport1 + "\n");

	}

	@Test
	public void testinggetNumberOfPassportObjects() {
		Passport passport1 = new Passport("Katie", "Yohanes");
		System.out.println(passport1);

		System.out.println("Number of objects: " + Passport.getNumberOfPassportObjects());

	}

	@Test

	public void testingcompareTo() {
		Passport passport1 = new Passport("Albert", "John", "Rodriguez");
		Passport passport2 = new Passport("Denise", "Lisa", "Sanders");
		Passport passport3 = new Passport("Denise", "Lisa", "Sanders");

		System.out.print(passport1 + "\n");
		System.out.print("Compare #1: " + (passport1.compareTo(passport2) < 0));
		System.out.print("Compare #2: " + (passport2.compareTo(passport3) < 0));

	}

	@Test
	public void testingequals() {

		Passport passport1 = new Passport("Abebe", "Ivory", "Andrew");
		Passport passport2 = new Passport("   Abebe  ", "Ivory", "Andrew");

		System.out.println(passport1 + "\n");
		System.out.println("Same #1: " + passport1.equals(passport2) + "\n");
		System.out.println("Same #2: " + passport1.equals(passport2) + "\n");

	}

	@Test

	public void testinggetStamps() {
		Passport passport1 = new Passport("Happy", "Alaia");
		System.out.println(passport1 + "\n");
		StringBuffer stamps = passport1.getStamps();
		System.out.println(stamps + "\n");
		stamps.append("Error: object stamps modified");
		System.out.print(passport1.getStamps());

	}

	@Test

	public void testingaddStamp() {

		Passport passport1 = new Passport("Happy", "Alaia");
		passport1.addStamp("France");
		passport1.addStamp("Ethiopia").addStamp("Nigeria");

	}

	@Test
	public void testinggetSeparator() {
		Passport passport1 = new Passport("Happy", "Alaia");
		char separator = '&';
		System.out.print(separator);
		System.out.print(passport1);

		 
	}

	@Test
	public void testingPassport1() {
		Passport passport1 = new Passport("Katie", "Yohanes");
		System.out.println(passport1);
		Passport Passport2 = new Passport("Urere", "I.", "Smith");

		Passport Passport1 = new Passport("Race", "I.", "John");
		System.out.print(Passport1 + "\n");
		System.out.print("Same: " + Passport1.equals(Passport2) + "\n");

	}

	@Test
	public void testingPassport2() {

		Passport passport1 = new Passport("Katie", "Yohanes");
		System.out.println(passport1);
		Passport Passport3 = new Passport("Urere", "I.", "Smith");

		Passport Passport2 = new Passport("Urere", "I.", "Smith");
		System.out.print(Passport2 + "\n");
		System.out.print("Same: " + Passport2.equals(Passport3) + "\n");

	}

	@Test
	public void testingPassport3() {
		System.out.print("SAMPLEFIRSTNAME");

	}

	@Test
	public void testingPassport4() {
		Passport passport1 = new Passport("Katie", "Yohanes");
		System.out.println(passport1);

		Passport Passport4 = new Passport();
		System.out.print(Passport4 + "\n");

	}
}
