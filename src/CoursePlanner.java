// ----------Application behavior

// import Java arrayList
import java.util.ArrayList;
// import Java scanner
import java.util.Scanner;
public class CoursePlanner {

    // define ArrayList and Scanner _ never reassign them later
    private final ArrayList<Course> courses;
    private final Scanner input;

    // constructor of the list and scanner
    public CoursePlanner() {
        courses = new ArrayList<>();
        input = new Scanner(System.in);
    }

    // coursePlanner's method to add courses
    public void addCourse() {
        // prompt the user to enter data
        System.out.print("Enter course code: ");
        String code = input.nextLine();

        System.out.print("Enter course name: ");
        String name = input.nextLine();

        System.out.print("Enter course credit: ");
        int credits = input.nextInt();
        input.nextLine(); // clear buffer

        // check is data are valid
        if (code.trim().isEmpty()) {
            System.out.println("Error: course code " +
                    "cannot be empty.");
            return;
        }

        if (name.trim().isEmpty()) {
            System.out.println("Error: course name " +
                    "cannot be empty.");
            return;
        }

        if (credits <= 0) {
            System.out.println("Error: credits must be greater than zero.");
            return;
        }

        Course course = new Course(code, name, credits);

        courses.add(course);

        System.out.println("Course added successfully!");
    }

    // coursePlanner's method to view courses

    public void viewCourses() {

        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("\n----- Course List -----");

        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i));
        }

        /*for (Course course : courses) {
            System.out.println(course);
            }*/
        }

    // coursePlanner's method to search courses

    public void searchCourse() {
        // prompt the user to enter code
        System.out.print("Enter course code to search: ");
        String searchCode = input.nextLine();

        // search course in courses ant print it out
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(searchCode)) {
                System.out.println("Course found:");
                System.out.println(course);
                return;
            }
        }

        System.out.println("Course not found.");
    }

    // coursePlanner's method to remove courses

    public void removeCourse() {

        System.out.print("Enter course code to search: ");
        String removeCode = input.nextLine();

        for (int i = 0; i < courses.size(); i++) {

            if (courses.get(i).getCourseCode().equalsIgnoreCase(removeCode)) {
                courses.remove(i);
                System.out.println("Course removed successfully!");
                return;
            }
        }

        System.out.println("Course not found.");
    }

    // method run()
    public void run() {
        int choice;

        do {
            // menu
            System.out.println("\nCourse Planner Menu");
            System.out.println("1. Add Course");
            System.out.println("2. Show Courses");
            System.out.println("3. Search Course");
            System.out.println("4. Remove Course");
            System.out.println("5. Exit");

            // Prompt user to enter choice
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    viewCourses();
                    break;
                case 3:
                    searchCourse();
                    break;
                case 4:
                    removeCourse();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}