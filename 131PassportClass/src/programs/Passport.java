package programs;

/**
 * This class represents a person's passport. It has three instance variables
 * representing the first, last and middle name (all are String variables). A
 * character instance variable representing a separator (to be used for
 * formatting purposes) is also part of the class. In addition, the class has a
 * StringBuffer instance variable that represents the passport stamps the person
 * has received.
 * 
 * For this class you need to define and use a private method called
 * validateAndFormat that takes a character as a parameter.
 * 
 * The class will keep track of the number of instances created by using a
 * private static field called objectCount.
 * 
 * @author CS
 *
 */

public class Passport {

	String firstname, middlename, lastname;
	char separator;
	StringBuffer stamps;
	private static int passportObjsCount = 0;

	public Passport(String firstname, String middlename, String lastname) {
		if (firstname == null || lastname == null || middlename == null)
			return;
		this.firstname = validateAndFormat(firstname);
		if (middlename.isBlank())
			this.middlename = middlename;
		else
			this.middlename = validateAndFormat(middlename);
		this.lastname = validateAndFormat(lastname);
		this.separator = ',';
		this.stamps = new StringBuffer();
		passportObjsCount++;
	}
	/*
	 * Generates a string with the last name, first name and middle name separated
	 * by the separator character with no spaces in between.
	 */

	@Override
	public String toString() {
		if (this.middlename.compareTo("") == 0)
			return this.lastname + separator + this.firstname;
		else
			return this.lastname + separator + this.firstname + separator + 
					this.middlename;
	}

	public Passport(String firstname, String lastname) {
		this(firstname, "", lastname);

	}

	public Passport() {
		this("SAMPLEFIRSTNAME", "SAMPLEMIDDLENAME", "SAMPLELASTNAME");

	}

	/*
	 * Initializes the current object with the information that is part of the
	 * parameter object.
	 */
	public Passport(Passport passport) {
		this.lastname = passport.lastname;
		this.firstname = passport.firstname;
		this.middlename = passport.middlename;
		this.separator = passport.separator;
		this.stamps = passport.stamps;
	}

	/*
	 * Adds a stamp by appending the string to the StringBuffer instance variable.
	 * If the parameter is null or is blank no stamp will be added. The method will
	 * always return a reference to the current object.
	 */
	@SuppressWarnings("null")
	public Passport addStamp(String stamp) {
		if (stamp == null && stamp.isBlank())
			return this;
		else {
			this.stamps.append(stamp);
			return this;
		}
	}

	// Returns a copy of the stamps instance variable.
	public StringBuffer getStamps() {
		return new StringBuffer(stamps);
	}

	// This method get method for separator
	public char getSeparator() {
		getSeparator();
		return this.separator;
	}

	/*
	 * This method set for a separator. If an invalid character is provided, the
	 * separator will not be changed. Returns true if separator changed and false
	 * otherwise.
	 */
	public boolean setSeparator(char separator) {
		if (separator == '@' || Character.isSpaceChar(separator) 
				|| Character.isLetter(separator)) {
			return false;
		} else {
			this.separator = separator;
			return true;
		}

	}

	/*
	 * Passport objects are equal if they have the same first name, last name and
	 * middle name
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj == this)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Passport p = (Passport) obj;

		/*
		 * Returns True if the parameter and the current object are equal 
		 * returns false otherwise.
		 */
		if (firstname.equals(p.firstname) && middlename.equals(p.middlename)
				&& lastname.equals(p.lastname))
			return true;
		else
			return false;

	}

	/*
	 * This method sorts Passport in alphabetical order based on their names it
	 * returns a negative value if the current object precedes the parameter in
	 * alphabetical order. It returns a positive value if current object follows the
	 * parameter in alphabetical order. It returns 0 if the current object and the
	 * parameter have the same name
	 */
	public int compareTo(Passport passport) {

		if (this.lastname.compareTo(passport.lastname) < 0)
			return -1;
		else if (this.lastname.compareTo(passport.lastname) > 0)
			return 1;
		else {
			if (this.firstname.compareTo(passport.firstname) < 0)
				return -1;
			else if (this.firstname.compareTo(passport.firstname) > 0)
				return 1;
			else {
				if (this.middlename.compareTo(passport.middlename) < 0)
					return -1;
				else if (this.middlename.compareTo(passport.middlename) > 0)
					return 1;
				else
					return 0;
			}
		}
	}

	// Returns the number of Passport objects created.

	public static int getNumberOfPassportObjects() {
		return passportObjsCount;
	}

	// Sets the number of Passport objects that have been created to 0

	public static void resetNumberOfPassportObjects() {
		passportObjsCount = 0;
	}

	/*
	 * If the uppercase parameter is true, it returns a new Passport object where
	 * the first name, last name and middle name have been capitalized. If not
	 * capitalized all the values will be in lowercase. The new object will have the
	 * same separator as the original object.
	 */
	public static Passport normalize(Passport passport, boolean uppercase) {

		// If the passport paramneter is null, the method will have no processing

		if (passport == null)
			return null;
		if (uppercase == true) {

			Passport passportNew = new Passport(passport.firstname, 
					passport.middlename, passport.lastname);
			passportNew.separator = passport.separator;
			passportNew.firstname = passport.firstname.toUpperCase();
			passportNew.lastname = passport.lastname.toUpperCase();
			passportNew.middlename = passport.middlename.toUpperCase();
			return passportNew;
		} else {
			Passport passportNew = new Passport(passport.firstname, 
					passport.middlename, passport.lastname);
			passportNew.separator = passport.separator;
			passportNew.firstname = passport.firstname.toLowerCase();
			passportNew.middlename = passport.middlename.toLowerCase();
			passportNew.lastname = passport.lastname.toLowerCase();
			return passportNew;
		}

	}

	/*
	 * The lastname will be changed if it is valid according to the
	 * validateAndFormat method; otherwise no change will take place.
	 */
	public boolean changeLastname(String lastname) {

		if (lastname == validateAndFormat(lastname))
			return false;
		else {
			this.lastname = lastname;
			return true;
		}

	}

	/*
	 * This method will generate and return a formatted string in lowercase with the
	 * first character in uppercase. The parameter is valid if it is not null and it
	 * is not blank according to the string method isBlank(). If the parameter is
	 * invalid, the method will return null and perform no further processing. If
	 * the parameter is valid, spaces surrounding the parameter will be removed, the
	 * string will be converted to lowercase, and the first character of the string
	 * (after spaces have been removed) will be in upper case. The following methods
	 * can be helpful during the implementation of this method:
	 * Character.toUpperCase, and the string methods charAt and substring.
	 * 
	 * You can test this method by initially defining it public; once you have
	 * tested it, make it private.
	 * 
	 */
	private static String validateAndFormat(String name) {
		if (name == null)
			return null;
		if (name.isBlank())
			return name;
		name = name.trim();
		String first = (name.charAt(0) + "").toUpperCase();
		String next = name.substring(1).toLowerCase();
		return first + next;
	}
}