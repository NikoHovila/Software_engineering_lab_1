package dev.m3s.programming2.homework3;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
public class Student extends Person {
	private int id;
	private int startYear = Year.now().getValue();
	private int graduationYear;
	private List<Degree> degrees = new ArrayList<>(3);
	
	public Student(String lname, String fname) {
		super(lname, fname);
		id = getRandomId(1,100);
		for (int i = 0; i < 3; i++) {
	        degrees.add(new Degree());
	    }
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		if (id >= ConstantValues.MIN_STUDENT_ID && id <= ConstantValues.MAX_STUDENT_ID) {
			this.id = id;
		}
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(final int startYear) {
		if (startYear > 2000 && startYear <= Year.now().getValue()) {
			this.startYear = startYear;
		}
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public String setGraduationYear(final int graduationYear) {
		if (!canGraduate()) {
			return "Check amount of required credits";
		}
	if (graduationYear <= 2000 || graduationYear > Year.now().getValue() || graduationYear < startYear) {
		return "Check graduation year";
	}
	this.graduationYear = graduationYear;
	return "Ok";
	}
	
	public void setDegreeTitle(final int i, String dName) {
		if (degrees.isEmpty()) {
	        for (int j = 0; j < 3; j++) {
	            degrees.add(new Degree());
	        }
	    }
	    if (i >= 0 && i < 3 && dName != null) {
	        this.degrees.get(i).setDegreeTitle(dName);
	    }
	}
	
	public boolean addCourse(final int i, StudentCourse course) {
		if (degrees.isEmpty()) {
	        for (int j = 0; j < 3; j++) {
	            degrees.add(new Degree());
	        }
	    }
	    if (i >= 0 && i < 3 && course != null) {
	        return degrees.get(i).addStudentCourse(course);
	    }
	    return false;
	}
	
	public int addCourses(final int i, List<StudentCourse> courses) {
	    int count = 0;
	    if (i >= 0 && i < 3 && courses != null) {
	        for (StudentCourse course : courses) {
	            if (course != null) {
	                boolean done = this.addCourse(i, course);
	                if (done) {
	                    count += 1;
	                }
	            }
	        }
	    }
	    return count;
	}
	
	public void printCourses() {
		for (int i = 0; i < 3; i++) {
			if (degrees.get(i) != null) {
				degrees.get(i).printCourses();
			}
		}
	}
	
	public void printDegrees() {
		for (int i = 0; i < 3; i++) {
			if (degrees.get(i) != null) {
				System.out.println(degrees.get(i).toString());
				degrees.get(i).printCourses();
			}
		}
	}
	
	public void setTitleOfThesis(final int i, String title) {
		if (degrees.isEmpty()) {
	        for (int j = 0; j < 3; j++) {
	            degrees.add(new Degree());
	        }
	    }
		if (i >= 0 && i < 3 && title != null) {
			this.degrees.get(i).setTitleOfThesis(title);
		}
	}
	
	public boolean hasGraduated() {
		if (graduationYear != 0 && canGraduate()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean canGraduate() {
		if (degrees.get(0).getCredits() < ConstantValues.BACHELOR_CREDITS || degrees.get(1).getCredits() < ConstantValues.MASTER_CREDITS) {
			return false;
		}
		if (degrees.get(0).getCreditsByType(1) < ConstantValues.BACHELOR_MANDATORY || degrees.get(1).getCreditsByType(1) < ConstantValues.MASTER_MANDATORY) {
			return false;
		}
		if (degrees.get(0).getTitleOfThesis() == ConstantValues.NO_TITLE || degrees.get(1).getTitleOfThesis() == ConstantValues.NO_TITLE) {
			return false;
		}
		return true;
	}
	
	public int getStudyYears() {
		if (hasGraduated()) {
	        return graduationYear - startYear;
	    } 
		else {
	        return Year.now().getValue() - startYear;
	    }
	}
	
	@Override
	public String toString() {
		String courseType = "";
		String courseText = "";
		String status = "";
		Degree degree = new Degree();
		List<StudentCourse> courses = degree.getCourses();
		for (StudentCourse course : courses)  {
	        if (course.getCourse().getCourseType() == ConstantValues.OPTIONAL) {
		        courseType = "Optional";
		    } else if (course.getCourse().getCourseType() == ConstantValues.MANDATORY) {
		        courseType = "Mandatory";
		    }
	        String gradeText;
	        int gradeNum = course.getGradeNum();
	        if (!course.isPassed() && course.getCourse().isNumericGrade() == false) {
		        gradeText = "\'f\'";
		    }
	        else if (course.isPassed() && course.getCourse().isNumericGrade() == false) {
		        gradeText = "\'A\'";
		    }
	        else if (gradeNum == ConstantValues.MIN_GRADE && course.getCourse().isNumericGrade() == true) {
		        gradeText = "\"Not graded\"";
	        }
	        else if (gradeNum > 0 && gradeNum <= 5) {
		    	gradeText = String.valueOf(gradeNum);
		    } 
	        else {
	        	gradeText = "";
	        }
	        courseText = courseText + "\n" + "[" + course.getCourse().getCourseCode() + " (" + course.getCourse().getCredits() + "0 cr), \"" + course.getCourse().getName() + "\". "
	    			+ courseType + ", period: " + course.getCourse().getPeriod() + ".] => grade for the student " + gradeText;
		}
		if (hasGraduated()) {
			status = "The student has graduated in " + getGraduationYear();
		}
		else {
			status = "The student has not graduated, yet";
		}
		String studyYears = " (studies lasted for " + getStudyYears() + " years)";
		double bachelorCredits = 0.0;
		double mandatoryBachelorCredits = 0.0;
		double bachelorGradeSum = 0.0;
	    double bachelorCourseCount = 0.0;
	    String titleOfBachelorThesis = ConstantValues.NO_TITLE;
	    if (degrees.get(0) != null && degrees.get(0).getCourses() != null) {
	        for (StudentCourse course : degrees.get(0).getCourses()) {
	            if (course != null && course.getCourse() != null && course.isPassed()) {
	                bachelorCredits += course.getCourse().getCredits();
	            }
	            if (course != null && course.getCourse() != null && course.isPassed() && course.getCourse().getCourseType() == ConstantValues.MANDATORY) {
	            	mandatoryBachelorCredits += course.getCourse().getCredits();
	            }
	            if (course != null && course.getCourse() != null && course.isPassed() && course.getCourse().isNumericGrade()) {
	            	bachelorGradeSum += course.getGradeNum();
	            	bachelorCourseCount += 1;
	            }
	        }
	    }
	    double bachelorGPA = bachelorGradeSum / bachelorCourseCount;
	    bachelorGPA = Math.round(bachelorGPA * 100.0) / 100.0;
	    if (degrees.get(0) != null) {
	        titleOfBachelorThesis = degrees.get(0).getTitleOfThesis();
	    }
	    double masterCredits = 0.0;
	    double mandatoryMasterCredits = 0.0;
	    double masterGradeSum = 0.0;
	    double masterCourseCount = 0.0;
	    if (degrees.get(1) != null && degrees.get(1).getCourses() != null) {
	        for (StudentCourse course : degrees.get(1).getCourses()) {
	            if (course != null && course.getCourse() != null && course.isPassed()) {
	                masterCredits += course.getCourse().getCredits();
	            }
	            if (course != null && course.getCourse() != null && course.isPassed() && course.getCourse().getCourseType() == ConstantValues.MANDATORY) {
	            	mandatoryMasterCredits += course.getCourse().getCredits();
	            }
	            if (course != null && course.getCourse() != null && course.isPassed() && course.getCourse().isNumericGrade()) {
	            	masterGradeSum += course.getGradeNum();
	            	masterCourseCount += 1;
	            }
	        }
	    }
	    
	    double masterGPA = masterGradeSum / masterCourseCount;
	    masterGPA = Math.round(masterGPA * 100.0) / 100.0;
	    String titleOfMastersThesis = ConstantValues.NO_TITLE;
	    if (degrees.get(1) != null) {
	        titleOfMastersThesis = degrees.get(1).getTitleOfThesis();
	    }
	    
	    double totalCredits = bachelorCredits + masterCredits;
	    double totalGPA = (bachelorGradeSum + masterGradeSum) / (bachelorCourseCount + masterCourseCount);
	    totalGPA = Math.round(totalGPA * 100.0) / 100.0;
	    String masterCreditText = "";
	    if (mandatoryMasterCredits < 50) {
	    	double masterCreditsMissing = 50.0 - mandatoryMasterCredits;
	    	masterCreditText = "		Total master's credits completed (" + masterCredits + "/120.0)" + "\n" +
	    			"		Missing mandatory master's credits " + masterCreditsMissing + " (" + mandatoryMasterCredits + "/50.0)" + "\n" +
	    			"		GPA of Master studies: " + masterGPA + "\n" + 
	    			"		Title of MSc Thesis: \"" + titleOfMastersThesis + "\"";
	    }
	    else {
	    	masterCreditText = "		Total master's credits completed (" + masterCredits + "/120.0)" + "\n" +
	    			"		All mandatory master credits completed (" + mandatoryMasterCredits + "/50.0)" + "\n" +
	    			"		GPA of Master studies: " + masterGPA + "\n" + 
	    			"		Title of MSc Thesis: \"" + titleOfMastersThesis + "\"";
	    }
	    
	    String bachelorCreditText = "";
	    if (mandatoryBachelorCredits < 150) {
	    	double bachelorCreditsMissing = 150.0 - mandatoryBachelorCredits;
	    	bachelorCreditText = "		Total bachelor credits completed (" + bachelorCredits + "/180.0)" + "\n" +
	    			"		Missing mandatory bachelor credits " + bachelorCreditsMissing + " (" + mandatoryBachelorCredits + "/150.0)" + "\n" +
	    			"		GPA of Bachelor studies: " + bachelorGPA + "\n" + 
	    			"		Title of BSc Thesis: \"" + titleOfBachelorThesis + "\"";
	    }
	    else {
	    	bachelorCreditText = "		Total bachelor credits completed (" + bachelorCredits + "/180.0)" + "\n" +
	    			"		All mandatory bachelor credits completed (" + mandatoryBachelorCredits + "/150.0)" + "\n" +
	    			"		GPA of Bachelor studies: " + bachelorGPA + "\n" + 
	    			"		Title of BSc Thesis: \"" + titleOfBachelorThesis + "\"";
	    }
	    
		return "Last name: " + getLastName() + "\n" + "\n" +
				"First name: " + getFirstName() + "\n" + "\n" +
				"Bachelor degree title: " + degrees.get(0).getDegreeTitle() + "\n" + "\n" +
				"Masters degree title: " + degrees.get(1).getDegreeTitle() + "\n" + "\n" +
				"Title of master’s thesis: " + degrees.get(1).getTitleOfThesis() + "\n" + "\n" +
				"Title of bachelor thesis: " + degrees.get(0).getTitleOfThesis() + "\n" + "\n" +
				"Start year: " + startYear + "\n" + "\n" +
				"Courses" + "\n" +
				courseText + "\n" + "\n" +
				"Student id: " + id + "\n" +
	            "    First name: " + getFirstName() + ", Last name: " + getLastName() + "\n" +
	            "    Date of birth: " + getBirthDate() + "\n" +
	            "    Status: " + status + "\n" +
	            "    Start year: " + startYear + studyYears + "\n" +
	            "    Total credits: " + totalCredits + " (GPA = " + totalGPA + ")" + "\n" +
	            "    Bachelor credits: " + bachelorCredits + "\n" + bachelorCreditText +
	            "    Master credits: " + masterCredits + "\n" + masterCreditText;
				
	}
	
	public String getIdString() {
		return "Student id: " + id;
	}
}
