// ---------------data model

public class Course {
    // declare data
    private final String courseCode;
    private String courseName; // change occurred in v2
    private int credits; // change occurred in v2

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

    // define setter/mutator methods (New in v2)
    public void setCourseName(String newName) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.courseName = newName;
        }
        else {
            System.out.println("Warning: invalid course name rejected.");
        }
    }

    public void setCredits(int newCredits) {
        if (newCredits >= 0) {
            this.credits = newCredits;
        }
        else {
            System.out.println("Warning: invalid credits rejected.");
        }
    }

    // define toString method
    @Override
    public String toString() {
        return getCourseCode() + " - " + getCourseName() +
                " (" + getCredits() + " credits)";
    }
}
