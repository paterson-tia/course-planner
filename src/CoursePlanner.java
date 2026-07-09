// ----------Application behavior

// import Java arrayList
import java.util.ArrayList;
// import Java scanner
import java.util.Scanner;
public class CoursePlanner {

    // define ArrayList and Scanner _ never reassign them later
    private final ArrayList<Course> courses;
    private final ArrayList<Student> students;
    private final Scanner input;

    // constructor of the list and scanner
    public CoursePlanner() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        input = new Scanner(System.in);
    }
/*=================================COURSE======================================*/
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

        // search course in courses and print it out
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
            System.out.println("6. Add Student");
            System.out.println("7. Show Students");
            System.out.println("8. Search Students");
            System.out.println("9. Delete Students");
            System.out.println("10. Enroll Student in Course");
            System.out.println("11. Exit");

            choice = intRange(
                    "Enter your choice: ",
                    1,
                    10,
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
                    addStudent();
                    break;
                case 7:
                    showStudents();
                    break;
                case 8:
                    searchStudent();
                    break;
                case 9:
                    removeStudent();
                    break;
                case 10:
                    enrollStudentInCourse();
                    break;
                case 11:
                    System.out.println("Goodbye!");
                    break;
            }

        } while (choice != 11);
    }

    /* Courses helper Methods*/

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

    /*=============================STUDENT=================================*/
    //----------------addStudent method
    public void addStudent() {

        // get parent's data
        String studentId;
        while(true) {
            studentId = nonEmptyString(
                    "Enter student ID: ",
                    "\nError: student ID cannot be empty."
            );

            // check duplicate
            if (!studentExists(studentId)) {
                break;
            }

            System.out.println("\nError: student ID already exists. Try again!");
        }

        String name = nonEmptyString(
                "Enter student name: ",
                "\nError: student name cannot be empty"
        );

        // choose student type
        int studentType = intRange (
                "\nSelect student type: \n1. Undergraduate \n2. Graduate \nEnter choice: ",
                1,
                2,
                "\nError: choice must be 1 or 2."
        );

        // collect specific student data
        Student student;
        switch (studentType) {
            // add undergraduate student
            case 1:
                String major = nonEmptyString(
                        "Enter student major: ",
                        "\nError: major cannot be empty."
                );

                int classificationChoice = intRange(
                        "Select classification: \n1. Freshman \n2. Sophomore \n3. Junior \n4. Senior \nEnter choice: ",
                        1,
                        4,
                        "\nError: classification must be between 1 and 4."
                );

                String classification = switch (classificationChoice) {
                    case 1 -> "Freshman";
                    case 2 -> "Sophomore";
                    case 3 -> "Junior";
                    case 4 -> "Senior";
                    default -> "";
                };

                // create new student and add to list
                student = new UndergraduateStudent(studentId, name, major, classification);

                break;

            case 2:
                // add graduate student
                String researchArea = nonEmptyString(
                        "Enter research area: ",
                        "\nError: research area cannot be empty."
                );

                String advisor = nonEmptyString(
                        "Enter advisor name: ",
                        "\nError: advisor name cannot be empty."
                );

                student = new GraduateStudent(studentId, name, researchArea, advisor);

                break;

            default:
                System.out.println("Invalid type.");
                return; // student not created/added
        }

        students.add(student);
        System.out.println("Student added successfully!");
    }

    //-------------------showStudent method
    public void showStudents() {

        if (students.isEmpty()) {
            System.out.println("\nNo students available.");
            return;
        }

        System.out.println("\n----- Students List -----");

        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }

    //------------searchStudent
    public void searchStudent() {

        System.out.print("Enter student ID to search: ");
        String searchId = input.nextLine();
        // search student in students and print it out
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(searchId)) {
                System.out.println("\nStudent found.");
                System.out.println(student);
                return;
            }
        }

        System.out.println("\nStudent not found.");
    }

    //------------removeStudent
    public void removeStudent() {

        System.out.print("Enter studentId to remove: ");
        String removeId = input.nextLine();

        for (int i = 0; i < students.size(); i++) {

            if(students.get(i).getStudentId().equalsIgnoreCase(removeId)) {
                students.remove(i);
                System.out.println("\nStudent removed successfully!");
                return;
            }
        }

        System.out.println("\nStudent not found.");
    }

    //-------- method for updating student
    public void updateStudent() {

        System.out.print("Enter the student ID to update: ");
        String updateId = input.nextLine();

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(updateId)) {

                // print student
                System.out.println("\nCurrent student:");
                System.out.println(student);

                // what data to update
                System.out.println("\nWhat do you want to update?");

                int choice;

                if (student instanceof UndergraduateStudent) {

                    // cast student into undergraduate student
                    UndergraduateStudent ugStudent =
                            (UndergraduateStudent) student;

                    // undergraduate student menu
                    System.out.println("1. Major");
                    System.out.println("2. Classification");

                    choice = intRange("Enter your choice: ",
                            1,
                            2,
                            "\nError: choice must be between 1 and 2. Try again!");

                    switch (choice) {

                        case 1:
                            String newMajor = nonEmptyString(
                                    "Enter new major: ",
                                    "Error: major cannot be empty. Try again!"
                            );

                            ugStudent.setMajor(newMajor);
                            break;

                        case 2:
                            int classificationChoice = intRange(
                                    "Select classification: \n1. Freshman \n2. Sophomore \n3. Junior \n4. Senior \nEnter choice: ",
                                    1,
                                    4,
                                    "\nError: classification must be between 1 and 4. Try again!"
                            );

                            String newClassification = switch (classificationChoice) {
                                case 1 -> "Freshman";
                                case 2 -> "Sophomore";
                                case 3 -> "Junior";
                                case 4 -> "Senior";
                                default -> "";
                            };

                            ugStudent.setClassification(newClassification);
                            break;
                    }
                }
                else if (student instanceof GraduateStudent) {
                    // cast student into graduate student
                    GraduateStudent gradStudent = (GraduateStudent) student;

                    // menu
                    System.out.println("1. Research area");
                    System.out.println("2. Advisor name");

                    choice = intRange("Enter your choice: ",
                            1,
                            2,
                            "\nError: choice must be between 1 and 2. Try again!");


                    switch (choice) {

                        case 1:
                            String newResearchArea = nonEmptyString(
                                    "Enter new research area: ",
                                    "Error: research area cannot be empty. Try again!"
                            );

                            gradStudent.setResearchArea(newResearchArea);
                            break;

                        case 2:
                            String newAdvisorName = nonEmptyString(
                                    "Enter new Advisor name: ",
                                    "Error: advisor name cannot be empty. Try it again!");

                            gradStudent.setAdvisorName(newAdvisorName);
                            break;
                    }
                }

                // display the updated student
                System.out.println("\nUpdated student:");
                System.out.println(student);
                return;
            }
        }

        System.out.println("Student not found.");
    }

    /*-----Student helper methods*/

    private boolean studentExists(String studentId) {

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return true;
            }
        }
        return false;
    }

/*======================================REGISTRATION=====================================*/
    // method for enrolling student

    private void enrollStudentInCourse() {
        // ask for student ID
        System.out.print("Enter student ID to enroll: ");
        String enrollId =  input.nextLine();

        // find student
        Student selectedStudent = null;

        for (Student student : students) {

            if (student.getStudentId().equalsIgnoreCase(enrollId)) {
                selectedStudent = student;
                break;
            }
        }

        // if student not added yet
        if (selectedStudent == null) {
            System.out.println("Student not found.");
            return;
        }

        // Course to register for
        Course selectedCourse = null;

        while(true) {
            // ask for course code
            System.out.print("Enter course code to register for: ");
            String enrollCode = input.nextLine();

            // find course
            for (Course course : courses) {

                if (course.getCourseCode().equalsIgnoreCase(enrollCode)) {
                    selectedCourse = course;
                    break;
                }
            }

            // if the course not in courses
            if (selectedCourse != null) {
                break;
            }

            System.out.println("\nCourse not found. Try again!");
        }

        // print Student & course before proceed

        System.out.println("\nSelected student:");
        System.out.println(selectedStudent);

        System.out.println("\nSelected course:");
        System.out.println(selectedCourse);

        // Check if student is already enrolled
        for (Course course : selectedStudent.getEnrolledCourses()) {
            if (course.getCourseCode().equalsIgnoreCase(selectedCourse.getCourseCode())) {
                System.out.println("\nStudent already enrolled.");
                return;
            }
        }

        // if no duplicate, enroll
        selectedStudent.enrollCourse(selectedCourse);

        // display confirmation
        System.out.println("\nStudent"
                + selectedStudent.getName()
                + " is now enrolled in "
                + selectedCourse.getCourseCode()
                + " - "
                + selectedCourse.getCourseName()
                + ".");
    }

    // method to show enrolled courses
    public void showEnrolledCourses() {

        // ask for student ID
        System.out.print("Enter student ID to view course: ");
        String enrollId =  input.nextLine();

        // find student
        Student selectedStudent = null;

        for (Student student : students) {

            if (student.getStudentId().equalsIgnoreCase(enrollId)) {
                selectedStudent = student;
                break;
            }
        }

        // if student not added yet
        if (selectedStudent == null) {
            System.out.println("Student not found.");
            return;
        }

        // get student's enrolled courses
        ArrayList<Course> studentEnrolledCourses = selectedStudent.getEnrolledCourses();

        if (studentEnrolledCourses.isEmpty()) {
             System.out.println("\nNo courses enrolled.");
             return;
        }

        // format and display enrolled courses
        System.out.println("\nCourses enrolled by " + selectedStudent.getName() + ":");

        for (int i = 0; i < studentEnrolledCourses.size(); i++) {
            System.out.println(i + 1 + ". " + studentEnrolledCourses.get(i));
        }
    }

    // method for dropping course
    public void dropCourse() {

        // ask for student ID
        System.out.print("Enter student ID: ");
        String dropId =  input.nextLine();

        // find student
        Student selectedStudent = null;

        for (Student student : students) {

            if (student.getStudentId().equalsIgnoreCase(dropId)) {
                selectedStudent = student;
                break;
            }
        }

        // if student not added yet
        if (selectedStudent == null) {
            System.out.println("Student not found.");
            return;
        }

        // show student's enrolled courses
        ArrayList<Course> studentEnrolledCourses = selectedStudent.getEnrolledCourses();

        if (studentEnrolledCourses.isEmpty()) {
            System.out.println("\nNo courses enrolled.");
            return;
        }

        // format and display enrolled courses
        System.out.println("\nCourses enrolled by " + selectedStudent.getName() + ":");

        for (int i = 0; i < studentEnrolledCourses.size(); i++) {
            System.out.println(i + 1 + ". " + studentEnrolledCourses.get(i));
        }

        // prompt user to enter course code to drop
        Course dropCourse = null;

        System.out.print("Enter course code to drop: ");
        String dropCode = input.nextLine();

        // find course
        for (Course course : studentEnrolledCourses) {

            if (course.getCourseCode().equalsIgnoreCase(dropCode)) {
                dropCourse = course;
                break;
            }
        }

        // remove course
        if (dropCourse != null) {
            studentEnrolledCourses.remove(dropCourse);

            System.out.println("\n"
                    + selectedStudent.getName()
                    + " dropped "
                    + dropCourse.getCourseCode()
                    + " - "
                    + dropCourse.getCourseName()
                    + " successfully!");
        }
        else {
            // otherwise
            System.out.println("\nStudent "
                    + selectedStudent.getName()
                    + " is not enrolled in this course.");
        }
    }

    // method to show all students
    public void showStudentsEnrolledInCourse() {

        // ask for course code
        System.out.print("Enter course code: ");
        String code =  input.nextLine();

        // find course
        Course selectedCourse = null;

        for (Course course : courses) {

            if (course.getCourseCode().equalsIgnoreCase(code)) {
                selectedCourse = course;
                break;
            }
        }

        // if course was not fund
        if (selectedCourse == null) {
            System.out.println("Course not found.");
            return;
        }
        // otherwise
        System.out.println("\nCourse selected:");
        System.out.println(selectedCourse);


        // loop through each student's enrolled courses
        int countStudent = 0;

        for (int i = 0; i < students.size(); i++) {
            for (int j = 0; j < students.get(i).getEnrolledCourses().size(); j++) {
                if (students.get(i).getEnrolledCourses().get(j).getCourseCode()
                        .equalsIgnoreCase(selectedCourse.getCourseCode())) {

                    if (countStudent == 0) {
                        System.out.println("Students enrolled in "
                                + selectedCourse.getCourseCode()
                                + " - "
                                + selectedCourse.getCourseName()
                                + ":");
                    }

                    System.out.println((countStudent + 1)
                            + ". "
                            + students
                            .get(i).getStudentId()
                            + " - "
                            + students.get(i).getName());

                    countStudent++;
                    break;
                }
            }
        }

        if (countStudent == 0) {
            System.out.println("No students are enrolled in "
                    + selectedCourse.getCourseCode()
                    + " - "
                    + selectedCourse.getCourseName());
        }
    }
}