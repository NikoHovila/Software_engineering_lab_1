package dev.m3s.programming2.homework3;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
public class ResponsibleTeacher extends Employee implements Teacher, Payment {
	private List<DesignatedCourse> courses;
	
	public ResponsibleTeacher(String lname, String fname) {
		super(lname, fname);
		this.courses = new ArrayList<>();
	}
	
	public String getEmployeeIdString() {
		return "OY_TEACHER_";
	}
	
	public String getCourses() {
		StringBuilder text = new StringBuilder();
	    for (int i = 0; i < courses.size(); i++) {
	        DesignatedCourse course = courses.get(i);
	        if (course.isResponsible()) {
                text.append("	").append("Responsible teacher: ").append(course.toString()).append("\n");
            } else {
                text.append("	").append("Teacher: ").append(course.toString()).append("\n");
            }
        }
        return text.toString();
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
				"	Salary: " + df.format(this.getPayment().calculatePayment()) + "\n" +
				"	Teacher for courses:" + "\n" + getCourses();	
	}
}
