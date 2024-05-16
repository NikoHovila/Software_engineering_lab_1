package dev.m3s.programming2.homework3;
public class Course {
	private String name = ConstantValues.NO_TITLE;
	private String courseCode = ConstantValues.NOT_AVAILABLE;
	private char courseBase = ' ';
	private int courseType;
	private int period;
	private double credits;
	private boolean numericGrade;
	
	public Course() {
	}
	
	public Course(String name, final int code, char courseBase, final int type, final int period, final double credits, boolean numericGrade) {
		setName(name);
		setCourseCode(code, courseBase);
		setCourseType(type);
		setPeriod(period);
		setCredits(credits);
		setNumericGrade(numericGrade);
	}
	
	public Course(Course course) {
	    this.name = course.getName();
	    this.courseCode = course.getCourseCode();
	    this.courseBase = course.getCourseBase();
	    this.courseType = course.getCourseType();
	    this.period = course.getPeriod();
	    this.credits = course.getCredits();
	    this.numericGrade = course.isNumericGrade();
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		if (name != "" && name != null) {
			this.name = name;
		}
	}
	
	public String getCourseTypeString() {
		if (courseType == ConstantValues.OPTIONAL) {
			return "Optional";
		}
		else if (courseType == ConstantValues.MANDATORY) {
			return "Mandatory";
		}
		return "";
	}
	
	public int getCourseType() {
		return courseType;
	}
	
	public void setCourseType(final int courseType) {
		if (courseType == ConstantValues.OPTIONAL || courseType == ConstantValues.MANDATORY) {
			this.courseType = courseType;
		}
	}

	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(final int courseCode, char courseBase) {
	    courseBase = Character.toUpperCase(courseBase);
	    if ((courseCode > 0 && courseCode < 1000000) && (courseBase == 'A' || courseBase == 'P' || courseBase == 'S')) {
	        this.courseCode = String.valueOf(courseCode) + courseBase;
	        this.courseBase = courseBase;
	    }
	}
	
	public char getCourseBase() {
		return courseBase;
	}
	
	public int getPeriod() {
		return period;
	}
	
	public void setPeriod(final int period) {
		if (period >= ConstantValues.MIN_PERIOD && period <= ConstantValues.MAX_PERIOD) {
			this.period = period;
		}
	}
	
	public double getCredits() {
		return credits;
	}
	
	private void setCredits(final double credits) {
		if (credits >= ConstantValues.MIN_CREDITS && credits <= ConstantValues.MAX_COURSE_CREDITS) {
			this.credits = credits;
		}
	}
	
	public boolean isNumericGrade() {
		return numericGrade;
	}
	
	public void setNumericGrade(boolean numericGrade) {
		this.numericGrade = numericGrade;
	}
	
	@Override
	public String toString() {
		return "["+ courseCode + " ( " + credits + "0 cr), \"" + name + "\". " + getCourseTypeString() + ", period: " + period + ".]" + "\n";
	}
	
}
