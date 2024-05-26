import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<String> registeredStudents;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean registerStudent(String studentId) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(studentId);
            return true;
        }
        return false;
    }

    public boolean removeStudent(String studentId) {
        return registeredStudents.remove(studentId);
    }
}

class Student {
    private String id;
    private String name;
    private List<String> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public boolean dropCourse(String courseCode) {
        return registeredCourses.remove(courseCode);
    }
}

public class CourseRegistrationSystem {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourses();
        displayCourseList();

        boolean continueRegistration = true;
        while (continueRegistration) {
            System.out.print("\nEnter student ID to register or drop a course (or 'exit' to quit): ");
            String studentId = scanner.nextLine().trim();
            if (studentId.equalsIgnoreCase("exit")) {
                continueRegistration = false;
            } else {
                Student student = findStudent(studentId);
                if (student == null) {
                    System.out.println("Student not found.");
                } else {
                    System.out.println("Student: " + student.getName());
                    System.out.print("Enter 'register' or 'drop' followed by the course code: ");
                    String[] input = scanner.nextLine().trim().split(" ");
                    if (input.length != 2) {
                        System.out.println("Invalid input format.");
                    } else {
                        String action = input[0];
                        String courseCode = input[1];
                        Course course = findCourse(courseCode);
                        if (course == null) {
                            System.out.println("Course not found.");
                        } else {
                            switch (action.toLowerCase()) {
                                case "register":
                                    if (course.registerStudent(studentId)) {
                                        student.registerCourse(courseCode);
                                        System.out.println("Registration successful.");
                                    } else {
                                        System.out.println("Course is full. Registration failed.");
                                    }
                                    break;
                                case "drop":
                                    if (course.removeStudent(studentId)) {
                                        student.dropCourse(courseCode);
                                        System.out.println("Course dropped successfully.");
                                    } else {
                                        System.out.println("Student is not registered in this course.");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid action.");
                            }
                        }
                    }
                }
            }
        }
        scanner.close();
    }

    private static void initializeCourses() {
        List<String> schedule1 = new ArrayList<>();
        schedule1.add("Monday, 9:00 AM");
        schedule1.add("Wednesday, 9:00 AM");
        Course course1 = new Course("CSE101", "Introduction to Computer Science", "Basic concepts of programming and algorithms.", 50, schedule1);
        courses.add(course1);

        List<String> schedule2 = new ArrayList<>();
        schedule2.add("Tuesday, 10:00 AM");
        schedule2.add("Thursday, 10:00 AM");
        Course course2 = new Course("MAT201", "Linear Algebra", "Study of vectors, matrices, and linear transformations.", 40, schedule2);
        courses.add(course2);

        List<String> schedule3 = new ArrayList<>();
        schedule3.add("Monday, 1:00 PM");
        schedule3.add("Wednesday, 1:00 PM");
        Course course3 = new Course("ENG101", "English Composition", "Developing writing skills and critical thinking.", 60, schedule3);
        courses.add(course3);
    }

    private static void displayCourseList() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + (course.getCapacity() - course.getRegisteredStudents().size()));
            System.out.println();
        }
    }

    private static Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private static Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }
}

