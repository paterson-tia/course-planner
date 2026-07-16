import java.util.ArrayList;

public class Student {

    // declare data
    private final String studentId;
    private String name;
    private final ArrayList<Course> enrolledCourses;

    // parameterized constructor
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    // create getters
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }

    // create setters
    public void setName (String newName) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.name = newName;
        }
        else {
            System.out.println("Warning: Invalid name rejected.");
        }
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
    }

    // other methods

    public void dropCourse(Course course) {
        enrolledCourses.remove(course);
    }

    @Override
    public String toString() {
        return studentId + " - " + name;
    }
}
