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

    // --------------method to add courses
    public void addCourse() {

        // prompt the user to enter code
        // and code validation

        String code;

        while (true) {

            code = nonEmptyString(
                    "Enter course code: ",
                    "\nError: course code cannot be empty. Try again"
            );

            // duplicate check
            if (!courseExists(code)) {
                break;
            }

            System.out.println("\nError: course code already exists. Try again!");
        }

        // prompt the user to enter name
        // and name validation

        String name;

        name = nonEmptyString(
                "Enter course name: ",
                "\nError: course name cannot be empty. Try again!"
        );

        // prompt the user to enter credits
        // and credits validation

        int credits;

        credits = positiveInt(
                "Enter course credits: ",
                "\nError: course credits must be a positive number. Try again!"
        );

        // create course
        Course course = new Course(code, name, credits);

        // add course to courses
        courses.add(course);

        System.out.println("Course added successfully!");
    }

    // ---------------method to view courses

    public void viewCourses() {

        if (courses.isEmpty()) {
            System.out.println("\nNo courses available.");
            return;
        }

        System.out.println("\n----- Course List -----");

        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i));
        }

    }

    // ------------------------method to search courses

    public void searchCourse() {
        // prompt the user to enter code
        System.out.print("Enter course code to search: ");
        String searchCode = input.nextLine();

        // search course in courses ant print it out
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(searchCode)) {
                System.out.println("\nCourse found:");
                System.out.println(course);
                return;
            }
        }

        System.out.println("\nCourse not found.");
    }

    // ----------coursePlanner's method to remove courses

    public void removeCourse() {

        System.out.print("Enter course code to remove: ");
        String removeCode = input.nextLine();

        for (int i = 0; i < courses.size(); i++) {

            if (courses.get(i).getCourseCode().equalsIgnoreCase(removeCode)) {
                courses.remove(i);
                System.out.println("\nCourse removed successfully!");
                return;
            }
        }

        System.out.println("\nCourse not found.");
    }

    // --------------method to update course (new in v2)
    public void updateCourse() {

        System.out.print("Enter course code to update: ");
        String code = input.nextLine();

        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(code)) {

                // show course
                System.out.println("\nCurrent course:");
                System.out.println(course);

                // start update

                System.out.println("\nWhat do you want to update?");

                System.out.println("1. Course Name");
                System.out.println("2. Course Credits");
                System.out.println("3. Both");

                int choice = intRange("Enter your choice: ",
                            1,
                            3,
                            "Error: choice must be between 1 and 3. Try again!"
                );

                switch (choice) {
                    case 1:
                        String newName = nonEmptyString(
                                "Enter new course name: ",
                                "\nError: course name cannot be empty. Try again!"
                        );

                        course.setCourseName(newName);

                        System.out.println("\nCourse name updated successfully!");
                        break;

                    case 2:
                        int newCredits = positiveInt(
                                "Enter new course credits: ",
                                "\nError: credits must be positive number. Try again!"
                        );

                        course.setCredits(newCredits);

                        System.out.println("\nCredits updated successfully!");
                        break;

                    case 3:
                        String name = nonEmptyString(
                                "Enter new course name: ",
                                "\nError: course name cannot be empty. Try again!"
                        );

                        int credits = positiveInt(
                                "Enter new course credits: ",
                                "\nError: credits must be positive number. Try again!"
                        );

                        course.setCourseName(name);
                        course.setCredits(credits);

                        System.out.println("\nCourse updated successfully!");
                        break;

                    default:
                        System.out.println("\nInvalid choice.");
                }

                return;
            }
        }

        System.out.println("\nCourse not found.");
    }

    // -------------method run()
    public void run() {
        int choice;

        do {
            // menu
            System.out.println("\nCourse Planner Menu");
            System.out.println("1. Add Course");
            System.out.println("2. Show Courses");
            System.out.println("3. Search Course");
            System.out.println("4. Update Course");
            System.out.println("5. Remove Course");
            System.out.println("6. Exit");

            choice = intRange(
                    "Enter your choice: ",
                    1,
                    6,
                    "Error: choice must be between 1 and 6. Try again!"
            );

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
                    updateCourse();
                    break;
                case 5:
                    removeCourse();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    break;
            }

        } while (choice != 6);
    }

    /* Helper Methods*/

    private String nonEmptyString(String prompt, String errorMessage) {
        while(true) {
            System.out.print(prompt);
            String value = input.nextLine();

            if (!value.trim().isEmpty()) {
                return value;
            }

            System.out.println(errorMessage);
        }
    }

    private int positiveInt(String prompt, String errorMessage) {
        while(true) {
            System.out.print(prompt);
            String value = input.nextLine();

            try {
                int num = Integer.parseInt(value);

                if (num > 0) {
                    return num;
                }

                System.out.println(errorMessage);

            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private int intRange(String prompt, int min, int max, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String value = input.nextLine();

            try {
                int num = Integer.parseInt(value);

                if (num >= min && num <= max) {
                    return num;
                }

                System.out.println(errorMessage);

            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
     }

     private boolean courseExists(String code) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
     }
}