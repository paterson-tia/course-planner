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

            System.out.print("Enter course code: ");
            code = input.nextLine();

            if (code.trim().isEmpty()) {
                System.out.println("\nError: course code cannot be empty. Try again!");
                continue;
            }

            // duplicate check
            boolean duplicate = false;

            for (Course course : courses) {
                if (course.getCourseCode().equalsIgnoreCase(code)) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                System.out.println("\nError: course code already exists. Try again!");
                continue;
            }

            break;
        }

        // prompt the user to enter name
        // and name validation

        String name;

        while (true) {

            System.out.print("Enter course name: ");
            name = input.nextLine();

            // course code validation
            if (!name.trim().isEmpty()) {
                break;
            }

            System.out.println("\nError: course name cannot be empty. Try again!");
        }

        // prompt the user to enter credits
        // and credits validation

        int credits;

        while (true) {
            System.out.print("Enter course credit: ");
            String creditStr = input.nextLine();

            try {
                credits = Integer.parseInt(creditStr);

                if (credits > 0) {
                    break;
                }

                System.out.println("\nError: credits must be greater than zero.");

            } catch (NumberFormatException e) {
                System.out.println("\nError: credits must be a number.");
            }
        }

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
                int choice;

                while (true) {

                    System.out.println("\nWhat do you want to update?");

                    System.out.println("1. Course Name");
                    System.out.println("2. Course Credits");
                    System.out.println("3. Both");

                    System.out.print("Enter your choice: ");
                    String choiceStr = input.nextLine();

                    // validate user choice
                    try {
                        choice = Integer.parseInt(choiceStr);
                        if (choice >= 1 && choice <= 3) {
                            break;
                        }

                        System.out.println("\nError: choice must be between 1 and 3.");

                    } catch (NumberFormatException e) {
                        System.out.println("\nError: please enter a number.");
                    }
                }

                switch (choice) {
                    case 1:
                        String newName;

                        while (true) {
                            System.out.print("Enter course new Name: ");
                            newName = input.nextLine();

                            // Validate user input name
                            if (!newName.trim().isEmpty()) {
                                break;
                            }

                            System.out.println("\nError: course name cannot be empty. Try it again!");
                        }

                        course.setCourseName(newName);
                        System.out.println("\nCourse name updated successfully!");
                        break;

                    case 2:
                        int newCredits;

                        while (true) {

                            System.out.print("Enter course new credits: ");
                            String userInput = input.nextLine();

                            //int newCredits;

                            try {
                                newCredits = Integer.parseInt(userInput);

                                if (newCredits > 0) {
                                    break;
                                }

                                System.out.println("\nError: credits must be greater than zero.");

                            } catch (NumberFormatException e) {
                                System.out.println("\nError: credits must be a number.");
                            }
                        }

                        course.setCredits(newCredits);
                        System.out.println("\nCredits updated successfully!");
                        break;

                    case 3:
                        String name;

                        while (true) {
                            System.out.print("Enter new course Name: ");
                            name = input.nextLine();

                            // Validate user input name
                            if (!name.trim().isEmpty()) {
                                break;
                            }

                            System.out.println("\nError: course name cannot be empty. Try it again!");
                        }

                        int credits;

                        while (true) {

                            System.out.print("Enter course new credits: ");
                            String userInputStr = input.nextLine();

                            //int newCredits;

                            try {
                                credits = Integer.parseInt(userInputStr);

                                if (credits > 0) {
                                    break;
                                }

                                System.out.println("\nError: credits must be greater than zero.");

                            } catch (NumberFormatException e) {
                                System.out.println("\nError: credits must be a number.");
                            }
                        }

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
        int choice = 0;

        do {
            // menu
            System.out.println("\nCourse Planner Menu");
            System.out.println("1. Add Course");
            System.out.println("2. Show Courses");
            System.out.println("3. Search Course");
            System.out.println("4. Update Course");
            System.out.println("5. Remove Course");
            System.out.println("6. Exit");

            // Prompt user to enter choice
            System.out.print("Enter your choice: ");
            String choiceStr = input.nextLine();

            // validate user choice
            try {
                choice = Integer.parseInt(choiceStr);
                if (choice < 1 || choice > 6) {
                    System.out.println("\nError: choice must be between 1 and 6.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError: please enter a number.");
                continue;
            }

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
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        } while (choice != 6);
    }
}