package dev.m3s.programming2.homework3;
import java.util.Random;

public abstract class Person {
	private String firstName = ConstantValues.NO_NAME;
	private String lastName = ConstantValues.NO_NAME;
	private String birthDate = ConstantValues.NO_BIRTHDATE;
	
	public Person(String lname, String fname) {
		if (lname != null) {
			this.lastName = lname;
		}
		if (fname != null) {
			this.firstName = fname;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName != null) {
			this.firstName = firstName;
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName != null) {
			this.lastName = lastName;
		}
	}

	public String getBirthDate() {
		return birthDate;
	}

	public String setBirthDate(String personId) {
		if (personId == null) {
			return "No change";
		}
		PersonID personID = new PersonID();
	    String result = personID.setPersonID(personId);
	    if (result.equals("Ok")) {
	    	String day = personId.substring(0, 2);
			String month = personId.substring(2, 4);
			String year = personId.substring(4, 6);
			char seventhCharacter = personId.charAt(6);
			String fullYear = "";
			if (seventhCharacter == '+') {
				fullYear = "18" + year;
			}
			else if (seventhCharacter == '-') {
				fullYear = "19" + year;
			}
			else if (seventhCharacter == 'A') {
	    		fullYear = "20" + year;
	    	}
			birthDate = day + "." + month + "." + fullYear;
	        return birthDate;
	    } else {
	        return "No change";
	    }
	}
	
	protected int getRandomId(final int min, final int max) {
		Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
	}
	
	public abstract String getIdString();
}
