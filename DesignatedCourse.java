package dev.m3s.programming2.homework3;
import java.time.Year;

public class DesignatedCourse {
	private Course course;
	private boolean responsible;
	private int year;
	
	public DesignatedCourse() {
		
	}
	
	public DesignatedCourse(Course course, boolean resp, int year) {
		this.course = course;
        this.responsible = resp;
        this.year = year;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		if (course != null) {
			this.course = course;
		}
	}

	public boolean isResponsible() {
		return responsible;
	}

	public void setResponsible(boolean responsible) {
		this.responsible = responsible;
	}
	
	public boolean getResponsible() {
		return responsible;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year >= 2000 && year <= (Year.now().getValue() + 1)) {
			this.year = year;
		}
	}
	
	@Override
	public String toString() {
		return "[course=["+ course.getCourseCode() + " (" + course.getCredits() + "0 cr), \"" + course.getName() + "\". " + course.getCourseTypeString() + ", period: " + course.getPeriod() + ".], year=" + getYear() + "]" + "\n";
	}
}
