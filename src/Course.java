// ---------------data model

public class Course {
    // declare data
    private final String courseCode;
    private final String courseName;
    private final int credits;

    // define parameterized constructor
    public Course (String courseCode, String courseName, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
    }

    // define getters methods
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    // define toString method
    @Override
    public String toString() {
        return getCourseCode() + " - " + getCourseName() +
                " (" + getCredits() + " credits)";
    }
}
