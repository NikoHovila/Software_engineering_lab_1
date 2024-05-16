package dev.m3s.programming2.homework3;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
public class Degree {
	private static final int MAX_COURSES = 50;
	private int count = 0;
	private String degreeTitle = ConstantValues.NO_TITLE;
	private String titleOfThesis = ConstantValues.NO_TITLE;
	private List<StudentCourse> myCourses = new ArrayList<>(MAX_COURSES);
	
	public List<StudentCourse> getCourses() {
        return myCourses;
	}
	
	public void addStudentCourses(List<StudentCourse> courses) {
	    if (courses != null) {
	        for (StudentCourse course : courses) {
	            addStudentCourse(course);
	        }
	    }
	}

	public boolean addStudentCourse(StudentCourse course) {
	    if (course != null && count < MAX_COURSES) {
	        myCourses.add(course);
	        count++;
	        return true;
	    }
	    return false;
	}

	public String getDegreeTitle() {
		return degreeTitle;
	}

	public void setDegreeTitle(String degreeTitle) {
		if (degreeTitle != null) {
			this.degreeTitle = degreeTitle;
		}
	}

	public String getTitleOfThesis() {
		return titleOfThesis;
	}

	public void setTitleOfThesis(String titleOfThesis) {
		if (titleOfThesis != null) {
			this.titleOfThesis = titleOfThesis;
		}
	}
	
	public double getCreditsByBase(char base) {
		double sum = 0.0;
		for (StudentCourse course : myCourses) {
            if (course != null){
                if (course.getCourse() != null){
                	if (course.getCourse().getCourseBase() == base && course.isPassed()){
                        sum += course.getCourse().getCredits();
                    }
                }
            }
        }
	    return sum;
	}
	
	public double getCreditsByType(final int courseType) {
		double sum = 0.0;
		for (StudentCourse course : myCourses) {
			if (course != null){
				if (course.getCourse() != null){
					if (course.getCourse().getCourseType() == courseType && isCourseCompleted(course)) {
			            sum += course.getCourse().getCredits();
					}
				}
			}
		}
	    return sum;
	}
	
	public double getCredits() {
		double creditCount = 0.0;
        for (StudentCourse course : myCourses) {
            if (course != null){
                if (course.getCourse() != null){
                    if (course.getCourse().getCredits() != 0.0 && course.isPassed()){
                        creditCount += course.getCourse().getCredits();
                    }
                }
            }
        }
	    return creditCount;
	}
	
	private boolean isCourseCompleted(StudentCourse c) {
		if (c != null && c.isPassed()) {
			return true;
		}
		return false;
	}
	
	public void printCourses() {
		for (StudentCourse course : myCourses) {
			if (course != null) {
				System.out.println(course);
			}
		}
	}
	
	public List<Double> getGPA(int type) {
	    List<Double> statistics = new ArrayList<>();
	    double numericGradeCount = 0.0;
	    double gradeSum = 0.0;
	    double average = 0.0;
	    if (type == ConstantValues.ALL) {
	        for (StudentCourse course : myCourses) {
	            if (course != null && course.getCourse() != null) {
	                if (course.getCourse().isNumericGrade()) {
	                	numericGradeCount += 1;
	                    gradeSum += course.getGradeNum();
	                }
	                else if (course.getGradeNum() == ConstantValues.MIN_GRADE && course.getCourse().isNumericGrade()) {
	                    numericGradeCount += 1;
	                }
	            }
	        }
	    } 
	    else {
	        for (StudentCourse course : myCourses) {
	            if (course != null && course.getCourse() != null && course.getCourse().getCourseType() == type) {
	                if (course.getCourse().isNumericGrade()) {
	                    numericGradeCount += 1;
	                    gradeSum += course.getGradeNum();
	                }
	                else if (course.getGradeNum() == ConstantValues.MIN_GRADE && course.getCourse().isNumericGrade()) {
	                    numericGradeCount += 1;
	                }
	            }
	        }
	    }
	    if (numericGradeCount != 0) {
	        DecimalFormat df = new DecimalFormat("#.##");
	        average = Double.parseDouble(df.format(gradeSum / numericGradeCount));
	    }
	    DecimalFormat df = new DecimalFormat("#.##");
	    gradeSum = Double.parseDouble(df.format(gradeSum));
	    numericGradeCount = Double.parseDouble(df.format(numericGradeCount));
	    statistics.add(gradeSum);
	    statistics.add(numericGradeCount);
	    statistics.add(average);
	    return statistics;
	}
	
	
	@Override
	public String toString() {
		String courseType = "";
		String courseText = "";
		for (int i = 0; i < myCourses.size(); i++) {
		    StudentCourse course = myCourses.get(i);
	        if (course.getCourse().getCourseType() == ConstantValues.OPTIONAL) {
		        courseType = "Optional";
		    } else if (course.getCourse().getCourseType() == ConstantValues.MANDATORY) {
		        courseType = "Mandatory";
		    }
	        String gradeText;
	        int gradeNum = course.getGradeNum();
	        if (!course.isPassed() && course.getCourse().isNumericGrade() == false) {
		        gradeText = "F";
		    }
	        else if (course.isPassed() && course.getCourse().isNumericGrade() == false) {
		        gradeText = "A";
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

	        courseText = courseText + "\n" + "	" + (i + 1) + ". " + "[" + course.getCourse().getCourseCode() + " ( " + course.getCourse().getCredits() + "0 cr), \"" + course.getCourse().getName() + "\". "
	    			+ courseType + ", period: " + course.getCourse().getPeriod() + ".] Year: " + course.getYear() + ", Grade: " + gradeText + "]";
		}
		return "Degree [Title: \"" + degreeTitle + "\" (courses: " + count + ")\n	Thesis title: \"" + titleOfThesis + "\"" + courseText + "]\n";
	}
}
