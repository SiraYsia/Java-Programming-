package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import programs.Passport;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PublicTests {
	/* testName corresponds to the method's name */

	@Test
	public void pub1Cons() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";
		Passport passport1 = new Passport("Claudia", "Ivette", "Smith");

		answer += passport1 + "\n";
		
		char separator = '&';
		passport1.setSeparator(separator);
		answer += passport1 + "\n";

		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}

	@Test
	public void pub2Equals() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";
		Passport passport1 = new Passport("Claudia", "Ivette", "Smith");
		Passport passport2 = new Passport("   Claudia  ", "Ivette", "Smith");
		Passport passport3 = new Passport("Robert", "Johnson");

		answer += passport1 + "\n";
		answer += "Same #1: " + passport1.equals(passport2) + "\n";
		answer += "Same #2: " + passport1.equals(passport3) + "\n";

		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}

	@Test
	public void pub3Compare() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";
		Passport passport1 = new Passport("Albert", "John", "Rodriguez");
		Passport passport2 = new Passport("Denise", "Lisa", "Sanders");
		Passport passport3 = new Passport("Albert", "John", "Rodriguez");

		answer += passport1 + "\n";
		answer += "Compare #1: " + (passport1.compareTo(passport2) < 0) + "\n";
		answer += "Compare #2: " + (passport2.compareTo(passport1) > 0) + "\n";
		answer += "Compare #3: " + (passport1.compareTo(passport3) == 0) + "\n";
		
		Passport passport4 = new Passport("Albert", "", "Rodriguez");
		answer += "Compare #4: " + (passport4.compareTo(passport1) < 0) + "\n";
		
		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}
	
	@Test
	public void pub4ObjectCount() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";
		
		Passport.resetNumberOfPassportObjects();
		Passport passport1 = new Passport("A", "B", "C");
		Passport passport2 = new Passport("D", "E", "F");
		Passport passport3 = new Passport("G", "H");
		Passport passport4 = new Passport();
		
		answer += passport1 + "\n";
		answer += passport2 + "\n";
		answer += passport3 + "\n";
		answer += passport4 + "\n";
		answer += "Number of Objects Created: " + Passport.getNumberOfPassportObjects();
		
		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}
	
	@Test
	public void pub5Normalize() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";
		
		Passport passport1 = new Passport("Elizabeth", "IRIS", "ThomPSON");
		
		answer += passport1 + "\n";
		boolean uppercase = true;
		answer += Passport.normalize(passport1, uppercase) + "\n";
		uppercase = false;
		answer += Passport.normalize(passport1, uppercase) + "\n";
		passport1.setSeparator('<');
		answer += Passport.normalize(passport1, uppercase) + "\n";
			
		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}
	
	@Test
	public void pub6ChangingLastname() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";
		
		Passport passport1 = new Passport("Robert", "", "ThomPSON");
		answer += passport1 +"\n";
		boolean changed = passport1.changeLastname("Smith");
		answer += "Changed: " + changed + "\n";
		answer += passport1 +"\n";
		changed = passport1.changeLastname("     ");
		answer += "Changed: " + changed + "\n";
		answer += passport1 +"\n";
		
		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}
	
	@Test
	public void pub7Stamps() {
		String testName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		String answer = "";

		Passport passport1 = new Passport("Traveling", "Tom");
		passport1.addStamp("Spain");
		passport1.addStamp("London").addStamp("France");

		answer += passport1 + "\n";
		StringBuffer stamps = passport1.getStamps();
		answer += stamps + "\n";
		stamps.append("Error: object stamps modified");
		answer += passport1.getStamps();

		assertTrue(TestingSupport.isResultCorrect(testName, answer, true));
	}
}