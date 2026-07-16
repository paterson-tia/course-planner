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
/*=================================COURSE MANAGEMENT======================================*/
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

        Course course = findCourse(searchCode);

        if (course == null) {
            System.out.println("\nCourse not found.");
            return;
        }

        System.out.println("\nCourse found.");
        System.out.println(course);
    }

    // ----------method to remove courses

    public void removeCourse() {

        System.out.print("Enter course code to remove: ");
        String removeCode = input.nextLine();

        // check if the course exists before start remove process
        for (int i = 0; i < courses.size(); i++) {

            if (courses.get(i).getCourseCode().equalsIgnoreCase(removeCode)) {
                // check if there are any students enrolled in that course
                // remove the course only if no students are enrolled in it
                int studentCount = 0;

                for (Student student : students) {
                    for (Course course : student.getEnrolledCourses()) {
                        if (course.getCourseCode().equalsIgnoreCase(removeCode)) {
                            studentCount++;
                            break;
                        }
                    }
                }

                if (studentCount == 0) {
                    courses.remove(i);
                    System.out.println("\n" + removeCode + " removed successfully!");
                    return;
                }
                else {
                    boolean oneStudent = studentCount == 1;
                    String studentLabel = (oneStudent ? "student" : "students");
                    String verb = (oneStudent ? "is" : "are");

                    System.out.println("\nCannot remove "
                            + removeCode.toUpperCase()
                            + "."
                    );

                    System.out.println(studentCount
                            + " "
                            + studentLabel
                            + " "
                            + verb
                            + " still enrolled.");
                    return;
                }
            }
        }

        System.out.println("\nCourse "
                + removeCode.toUpperCase()
                + " not found."
        );
    }

    // --------------method to update course

    public void updateCourse() {

        System.out.print("Enter course code to update: ");
        String code = input.nextLine();

        Course course = findCourse(code);

        if (course == null) {
            System.out.println("\nCourse not found.");
            return;
        }

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

        System.out.println("\nUpdated course:");
        System.out.println(course);
    }

    // -------------method run()
    public void run() {
        int choice;

        do {
            // menu
            System.out.println("\nCourse Planner Menu\n");

            System.out.println("--- Course Management ---\n");

            System.out.println("1. Add Course");
            System.out.println("2. Show Courses");
            System.out.println("3. Search Course");
            System.out.println("4. Update Course");
            System.out.println("5. Remove Course\n");

            System.out.println("--- Student Management ---\n");

            System.out.println("6. Add Student");
            System.out.println("7. Show Students");
            System.out.println("8. Search Students");
            System.out.println("9. Update Student");
            System.out.println("10. Delete Student\n");

            System.out.println("--- Enrollment Management ---\n");

            System.out.println("11. Enroll Student in Course");
            System.out.println("12. Show Student Enrolled Courses");
            System.out.println("13. Drop Course");
            System.out.println("14. Show Students Enrolled in Course\n");

            System.out.println("--- Reports ---\n");

            System.out.println("15. Reports");

            System.out.println("\n16. Exit");

            choice = intRange(
                    "Enter your choice: ",
                    1,
                    16,
                    "Error: choice must be between 1 and 16. Try again!"
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
                    updateStudent();
                    break;
                case 10:
                    removeStudent();
                    break;
                case 11:
                    enrollStudentInCourse();
                    break;
                case 12:
                    showEnrolledCourses();
                    break;
                case 13:
                    dropCourse();
                    break;
                case 14:
                    showStudentsEnrolledInCourse();
                    break;

                case 15:
                    reports();
                    break;

                case 16:
                    System.out.println("Goodbye!");
                    break;


            }

        } while (choice != 16);
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

    /*=============================STUDENT MANAGEMENT=================================*/
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

        Student student = findStudent(searchId);

        if (student == null) {
            System.out.println("\nStudent not found.");
            return;
        }

        System.out.println("\nStudent found.");
        System.out.println(student);
    }

    //------------removeStudent
    public void removeStudent() {

        System.out.print("Enter studentId to remove: ");
        String removeId = input.nextLine();

        Student student = findStudent(removeId);

        if (student == null) {
            System.out.println("\nStudent not found.");
            return;
        }

        students.remove(student);
        System.out.println("\nStudent "
                +  student.getName()
                + " removed successfully!"
        );
    }

    //-------- method for updating student
    public void updateStudent() {

        System.out.print("Enter the student ID to update: ");
        String updateId = input.nextLine();

        Student student = findStudent(updateId);

        if (student == null) {
            System.out.println("\nStudent not found.");
        }

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

            System.out.println("\nUpdated student:");
            System.out.println(student);
    }

    /*-----Student management helper methods*/

    private boolean studentExists(String studentId) {

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return true;
            }
        }
        return false;
    }

/*======================================ENROLLMENT=====================================*/
    /*--------------------method for enrolling student-------------------*/

    private void enrollStudentInCourse() {
        // ask for student ID
        System.out.print("Enter student ID to enroll: ");
        String enrollId =  input.nextLine();

        // find student
        Student selectedStudent = findStudent(enrollId);

        // if student not exists
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

            selectedCourse = findCourse(enrollCode);

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
        System.out.println(selectedStudent.getName()
                + " has been enrolled in "
                + selectedCourse.getCourseCode()
                + " - "
                + selectedCourse.getCourseName()
                + ".");
    }

    /*------------------method to show student's enrolled courses----------------------*/
    public void showEnrolledCourses() {

        // ask for student ID
        System.out.print("Enter student ID to view course: ");
        String enrollId =  input.nextLine();

        // find student
        Student selectedStudent = findStudent(enrollId);

        // if student not exists
        if (selectedStudent == null) {
            System.out.println("Student not found.");
            return;
        }

        // get student's enrolled courses
        ArrayList<Course> studentEnrolledCourses = selectedStudent.getEnrolledCourses();

        if (studentEnrolledCourses.isEmpty()) {
             System.out.println("\nThis student is not enrolled in any courses.");
             return;
        }

        // format and display enrolled courses
        System.out.println("\nCourses enrolled by " + selectedStudent.getName() + ":");

        for (int i = 0; i < studentEnrolledCourses.size(); i++) {
            System.out.println(i + 1 + ". " + studentEnrolledCourses.get(i));
        }
    }

    /*---------------method for dropping course------------------*/
    public void dropCourse() {

        // ask for student ID
        System.out.print("Enter student ID: ");
        String dropId =  input.nextLine();

        // find student
        Student selectedStudent = findStudent(dropId);

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
            //studentEnrolledCourses.remove(dropCourse);
            selectedStudent.dropCourse(dropCourse);

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

    /*------------------method to show student enrolled in a course-----------------*/
    public void showStudentsEnrolledInCourse() {

        // ask for course code
        System.out.print("Enter course code: ");
        String code =  input.nextLine();

        Course selectedCourse = findCourse(code);

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
                            + students.get(i).getStudentId()
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

    /*-----search helper methods------*/

    private Student findStudent(String id) {

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(id)) {
                return student;
            }
        }

        return null;
    }

    private Course findCourse(String code) {

        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(code)) {
                return course;
            }
        }

        return null;
    }


    /*======================================REPORTS=====================================*/

    /*------Reports menu------*/

    private void reports() {

        int choice;

        do {
            System.out.println("--- Reports Menu ---");
            System.out.println("1. Total number of students");
            System.out.println("2. Total number of courses");
            System.out.println("3. Undergraduate / Graduate count");
            System.out.println("4. Student with most enrolled course");
            System.out.println("5. Most popular course");
            System.out.println("6. Average courses per student");
            System.out.println("7. Back\n");

            choice = intRange("Enter your choice: ",
                    1,
                    7,
                    "Error: choice must be between 1 and 7. Try again!");

            switch (choice) {

                case 1:
                    totalStudentsReport();
                    break;

                case 2:
                    totalCoursesReport();
                    break;

                case 3:
                    studentTypeReport();
                    break;

                case 4:
                    studentWithMostCoursesReport();
                    break;

                case 5:
                    mostPopularCourseReport();
                    break;

                case 6:
                    averageCoursesReport ();
                    break;

                case 7:
                    System.out.println("Returning to main menu....");
                    break;
            }

        } while (choice != 7);
    }

    /*----- Reports external private methods ---------*/

    private void totalStudentsReport() {
        // print report
        System.out.println("\n--- Total Students Report ---");
        System.out.println("Total number of students: " + students.size());
        System.out.println();
    }

    private void totalCoursesReport() {
        // print report
        System.out.println("\n--- Total Courses Report ---");
        System.out.println("Total number of courses: " + courses.size());
        System.out.println();
    }

    private void studentTypeReport() {

        int undergraduateCount = 0;
        int graduateCount = 0;

        for (Student student : students) {

            if (student instanceof UndergraduateStudent) {
                undergraduateCount++;
            }
            else if (student instanceof GraduateStudent) {
                graduateCount++;
            }
        }
        // print report
        System.out.println("\n--- Student Type Report ---");
        System.out.println("Total number of undergraduate students: " + undergraduateCount);
        System.out.println("Total number of graduate students: " + graduateCount);
        System.out.println();
    }

    private void averageCoursesReport () {

        if (students.isEmpty()) {
            System.out.println("\nNo students available.");
            return;
        }

        int enrolledCoursesCount = 0;

        // compute the total number of enrolled courses
        for (Student student : students) {
            enrolledCoursesCount += student.getEnrolledCourses().size();
        }
        // compute and display average courses
        double averageCourses = (double) enrolledCoursesCount / students.size();

        System.out.println("\n--- Average Courses Report ---");
        System.out.printf("Average courses per student: %.2f%n", averageCourses);
        System.out.println();
    }


    private void studentWithMostCoursesReport() {
        // check empty list
        if (students.isEmpty()) {
            System.out.println("\nNo students available.");
            return;
        }
        // initialize with the first student
        int maxEnrolledCourses = students.get(0).getEnrolledCourses().size();

        // first pass
        for (Student student : students) {
            // get number of course for each student
            int numberOfCourses = student.getEnrolledCourses().size();
            // compare to the current max number of courses
            if (numberOfCourses > maxEnrolledCourses) {
                maxEnrolledCourses = numberOfCourses;
            }
        }

        // second pass
        int studentsWithMostCoursesCount =0;

        for (int i = 0; i < students.size(); i++) {

            int numberOfCourses = students.get(i).getEnrolledCourses().size();

            if (numberOfCourses == maxEnrolledCourses) {

                // print each student with maxEnrolledCourses courses
                studentsWithMostCoursesCount++;

                if (studentsWithMostCoursesCount == 1) {
                    System.out.println("\n--- Students With Most Enrolled Courses Report ---");
                }

                System.out.println(studentsWithMostCoursesCount
                        + ". "
                        + students.get(i).getStudentId()
                        + " - "
                        + students.get(i).getName()
                );
            }
        }

        System.out.println("Maximum courses enrolled: " + maxEnrolledCourses);
        System.out.println();
    }

    private void mostPopularCourseReport() {
        // check empty list
        if (courses.isEmpty()) {
            System.out.println("\nNo courses available.");
            return;
        }

        Course mostPopularCourse = courses.get(0);
        int maxStudents = 0;

        // first pass
        for (Course course : courses) {

            int enrollmentCount = countStudentsEnrolled(course);

            if (enrollmentCount > maxStudents) {
                maxStudents = enrollmentCount;
            }
        }

        // second pass
        int courseCount = 0;

        for (Course course : courses) {

            int enrollmentCount = countStudentsEnrolled(course);

            if (enrollmentCount == maxStudents) {

                courseCount++;

                if (courseCount == 1) {
                    System.out.println("\n--- Most Popular Courses Report ---");
                    System.out.println("Maximum enrollment: "
                            + maxStudents
                            + " student"
                            + (maxStudents == 1 ? "" : "s"));
                    System.out.println();
                }

                // print each course with maxStudent enrollments
                System.out.println(courseCount
                        + ". "
                        + course.getCourseCode()
                        + " - "
                        + course.getCourseName()
                );
            }
        }

        System.out.println();
    }

    /*------Enrollment count helper------*/

    private int countStudentsEnrolled(final Course course) {

        int enrollmentCount = 0;

        for (Student student : students) {
            for (Course enrolledCourse : student.getEnrolledCourses()) {
                if (enrolledCourse.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
                    enrollmentCount++;
                    break;
                }
            }
        }

        return enrollmentCount;
    }
}