package dev.m3s.programming2.homework3;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
public class AssistantTeacher extends Employee implements Teacher, Payment {
	private List<DesignatedCourse> courses;
	
	public AssistantTeacher(String lname, String fname) {
		super(lname, fname);
		this.courses = new ArrayList<>();
	}
	
	public String getEmployeeIdString() {
		return "OY_ASSISTANT_";
	}

	public String getCourses() {
        StringBuilder Text = new StringBuilder();
        for (DesignatedCourse course : courses) {
        	Text.append("	").append(course.toString()).append("\n");
        }
        return Text.toString();
    }

	public void setCourses(List<DesignatedCourse> courses) {
		if (courses != null) {
			this.courses = courses;
		}
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("#0.00");
		return "Teacher id: " + getIdString() + "\n" + 
				"	First name: " + getFirstName() + ", Last name: " + getLastName() + "\n" +
				"	Birthdate: " + getBirthDate() + "\n" +
				"	Salary: " + df.format(this.getPayment().calculatePayment())  + "\n" +
				"	Assistant for courses:" + "\n" + getCourses();	
	}
}
