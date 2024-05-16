package dev.m3s.programming2.homework3;
public class PersonID {
	private String birthDate = ConstantValues.NO_BIRTHDATE;

	public String getBirthDate() {
		return birthDate;
	}

	public String setPersonID(final String personID) {
		if (!checkPersonIDNumber(personID)) {
			return ConstantValues.INVALID_BIRTHDAY;
		}
		String day = personID.substring(0, 2);
		String month = personID.substring(2, 4);
		String year = personID.substring(4, 6);
		char seventhCharacter = personID.charAt(6);
		String fullYear;
		if (seventhCharacter == '+') {
			fullYear = "18" + year;
		}
		else if (seventhCharacter == '-') {
			fullYear = "19" + year;
		}
		else if (seventhCharacter == 'A') {
    		fullYear = "20" + year;
    	}
		else {
    		return ConstantValues.INVALID_BIRTHDAY;
    	}
		String date = day + "." + month + "." + fullYear;
		if (!checkBirthdate(date)) {
			return ConstantValues.INVALID_BIRTHDAY;
		}
		if (!checkValidCharacter(personID)) {
			return ConstantValues.INCORRECT_CHECKMARK;
		}
		birthDate = day + "." + month + "." + fullYear;
		return "Ok";
	}
	
	private boolean checkPersonIDNumber(final String personID) {
		if (personID == null) {
	        return false;
	    }
		if (personID.length() != 11) {
	        return false;
	    }
		char seventhCharacter = personID.charAt(6);
		if  (seventhCharacter == '+' || seventhCharacter == '-' || seventhCharacter == 'A') {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean checkLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean checkValidCharacter(final String personID) {
		String string = personID.substring(0, 6) + personID.substring(7, 10);
		double number = Double.parseDouble(string);
		double number1 = number / 31;
		double remainder = number1 % 1;
		double number2 = remainder * 31;
		long number3 = Math.round(number2);
		char value;
        if (number3 < 10) {
        	value = (char) ('0' + number3);
        } 
        else if (number3 < 16){
            value = (char) ('A' + (number3 - 10));
        }
        else if (number3 == 16){
            value = ('H');
        }
        else if (number3 < 22 ){
            value = (char) ('J' + (number3 - 17));
        }
        else if (number3 == 22 ){
            value = ('P');
        }
        else if (number3 < 31 ){
        	value = (char) ('R' + (number3 - 23));
        }
        else {
        	value = ' ';
        }
		char lastCharacter = personID.charAt(10);
		if (lastCharacter == value) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean checkBirthdate(final String date) {
		String[] parts = date.split("\\.");
		String day = parts[0];
		String month = parts[1];
		String year = parts[2];
		int daynumber = Integer.parseInt(day);
		int monthnumber = Integer.parseInt(month);
		int yearnumber = Integer.parseInt(year);
		if (yearnumber < 0) {
			return false;
		}
		if (monthnumber < 1 || monthnumber > 12) {
			return false;
		}
		if (daynumber < 1 || daynumber > 31) {
			return false;
		}
		if ((monthnumber == 1 || monthnumber == 3 || monthnumber == 5 || monthnumber == 7 || monthnumber == 8
				 || monthnumber == 10 || monthnumber == 12) && (daynumber > 31)) {
			return false;
		}
		if ((monthnumber == 4 || monthnumber == 6 || monthnumber == 9 || monthnumber == 11) && daynumber > 30) {
			return false;
		}
		if ((monthnumber == 2 && checkLeapYear(yearnumber)) && (daynumber > 29)) {
			return false;
		}
		if ((monthnumber == 2 && !checkLeapYear(yearnumber)) && (daynumber > 28)) {
			return false;
		}
		return true;
	}
}
