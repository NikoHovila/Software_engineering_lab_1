package dev.m3s.programming2.homework3;
import java.time.Year;
public class StudentCourse {
	private Course course;
	private int gradeNum;
	private int yearCompleted;
	
	public StudentCourse() {
		
	}
	public StudentCourse(Course course, final int gradeNum, final int yearCompleted) {
		setCourse(course);
		setGrade(gradeNum);
		setYear(yearCompleted);
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getGradeNum() {
		return gradeNum;
	}
	protected void setGrade(int gradeNum) {
		if (gradeNum == 'a' || gradeNum == 'f') {
			gradeNum = Character.toUpperCase(gradeNum);
		}
		if (checkGradeValidity(gradeNum)) {
	        this.gradeNum = gradeNum;
	        if (yearCompleted == 0) {
	            yearCompleted = Year.now().getValue();
	        }
	    }
	}
	private boolean checkGradeValidity(final int gradeNum) {
		if ((gradeNum >= ConstantValues.MIN_GRADE && gradeNum <= ConstantValues.MAX_GRADE) && course.isNumericGrade() == true) {
			return true;
		}
		if (course.isNumericGrade() == false) {
			int upperCasegradeNum = Character.toUpperCase(gradeNum);
			if (upperCasegradeNum == ConstantValues.GRADE_FAILED || upperCasegradeNum == ConstantValues.GRADE_ACCEPTED) {
				return true;
			}
			return false;
		}
		else {
			return false;
		}
	}
	public boolean isPassed() {
		if (gradeNum >= 1 && gradeNum <= 5 && course.isNumericGrade() == true) {
	        return true;
	    } 
		else if (gradeNum == ConstantValues.GRADE_ACCEPTED && course.isNumericGrade() == false) {
	        return true;
	    }
		else {
			return false;
		}
	}
	public int getYear() {
		return yearCompleted;
	}
	public void setYear(final int year) {
		if (year > 2000 && year <= Year.now().getValue()) {
			this.yearCompleted = year;
		}
	}
	@Override
	public String toString() {
	    String courseType = "";
	    if (course.getCourseType() == ConstantValues.OPTIONAL) {
	        courseType = "Optional";
	    } else if (course.getCourseType() == ConstantValues.MANDATORY) {
	        courseType = "Mandatory";
	    }
        String gradeText;
        if (!isPassed() && course.isNumericGrade() == false) {
	        gradeText = "F";
	    }
        else if (isPassed() && course.isNumericGrade() == false) {
	        gradeText = "A";
	    }
        else if (gradeNum == ConstantValues.MIN_GRADE && course.isNumericGrade() == true) {
	        gradeText = "\"Not graded\"";
        }
        else if (gradeNum > 0 && gradeNum <= 5) {
	    	gradeText = String.valueOf(gradeNum);
	    } 
        else {
        	gradeText = "";
        }
	    if (gradeNum == ConstantValues.MIN_GRADE) {
	    	return "[" + course.getCourseCode() + " ( " + course.getCredits() + "0 cr), \"" + course.getName() + "\". "
	    			+ courseType + ", period: " + course.getPeriod() + ".] Year: " + yearCompleted + ", Grade: \"Not graded\".]\n";
	    }
	    else {
	    	return "[" + course.getCourseCode() + " ( " + course.getCredits() + "0 cr), \"" + course.getName() + "\". "
	    			+ courseType + ", period: " + course.getPeriod() + ".] Year: " + yearCompleted + ", Grade: " + gradeText + ".]\n";
	    }
	}
}