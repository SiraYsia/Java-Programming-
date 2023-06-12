package programs;

/**
 * Example of using the Passport class.
 * 
 * @author cmsc131
 *
 */
public class Driver {

	public static void main(String[] args) {
		Passport.resetNumberOfPassportObjects();

		Passport passport1 = new Passport("claudia", "I.", "smith");
		System.out.println(passport1);

		Passport passport2 = new Passport("John", "RoberTs");
		System.out.println(passport2);

		Passport passport3 = new Passport();
		System.out.println(passport3);
		System.out.println("=============");

		passport1.addStamp("Spain");
		passport1.addStamp("London");
		System.out.println("Stamps for " + passport1 + "-->" + passport1.getStamps());

		passport1.setSeparator('#');
		System.out.println(passport1);
		Passport passport4 = new Passport("CLAUDIA", "I.", "smith");
		System.out.println(passport1 + " same to " + passport4 + "? " + passport1.equals(passport4));

		System.out.println("=============");
		System.out.println("Comparing");
		System.out.println("Comp1: " + (passport1.compareTo(passport2) > 0));
		System.out.println("Comp2: " + (passport2.compareTo(passport1) < 0));
		System.out.println("Comp3: " + (passport1.compareTo(passport4) == 0));

		System.out.println("=============");
		System.out.println("Normalizing");
		Passport normalized1 = Passport.normalize(passport1, true);
		System.out.println("Normalize #1: " + normalized1);
		System.out.println("Normalize #2: " + Passport.normalize(passport1, false));
		System.out.println("Normalize #3: " + Passport.normalize(passport4, false));

		System.out.println("Number of objects: " + Passport.getNumberOfPassportObjects());
	}
}
