import java.util.Scanner;

class NotCorrectAgeException extends Exception {
    public NotCorrectAgeException(String message) {
        super(message);
    }
}

class Student {
    private int age;
    private String gender;
    private String firstName;
    private String lastName;
    private String middleName;
    private int course;
    private double gradePointAverage;

    public Student() {}

    public Student(int age, String gender, String firstName, String lastName,
                   String middleName, int course, double gradePointAverage) throws NotCorrectAgeException {
        setAge(age);
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.course = course;
        this.gradePointAverage = gradePointAverage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws NotCorrectAgeException {
        if (age < 16 || age > 60) {
            throw new NotCorrectAgeException("Age must be between 16 and 60.");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public double getGradePointAverage() {
        return gradePointAverage;
    }

    public void setGradePointAverage(double gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", course=" + course +
                ", gradePointAverage=" + gradePointAverage +
                '}';
    }
}

class Group {
    private Student[] students;
    private int count;

    public Group() {
        this.students = new Student[15];
        this.count = 0;
    }

    public void add() {
        if (count >= 15) {
            System.out.println("Group is full! Cannot add more students.");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter student details:");
            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Gender: ");
            String gender = scanner.nextLine();
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Middle Name: ");
            String middleName = scanner.nextLine();
            System.out.print("Course: ");
            int course = scanner.nextInt();
            System.out.print("Grade Point Average: ");
            double gpa = scanner.nextDouble();

            Student student = new Student(age, gender, firstName, lastName, middleName, course, gpa);
            students[count++] = student;
            System.out.println("Student added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void add(Student student) {
        if (count >= 15) {
            System.out.println("Group is full! Cannot add more students.");
            return;
        }
        students[count++] = student;
        System.out.println("Student added successfully!");
    }

    public void delete(int index) {
        if (count <= 3) {
            System.out.println("Cannot delete. Group must have at least 3 students.");
            return;
        }
        if (index < 0 || index >= count) {
            System.out.println("Invalid index! Cannot delete student.");
            return;
        }

        for (int i = index; i < count - 1; i++) {
            students[i] = students[i + 1];
        }
        students[--count] = null;
        System.out.println("Student deleted successfully.");
    }

    public Student getStudent(int index) {
        if (index < 0 || index >= count) {
            System.out.println("Invalid index! Cannot access student.");
            return null;
        }
        return students[index];
    }

    public void displayGroup() {
        System.out.println("Group details:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + students[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Group group = new Group();

        group.add();

        try {
            Student student = new Student(20, "Male", "John", "Doe", "Smith", 2, 3.5);
            group.add(student);
        } catch (NotCorrectAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        group.displayGroup();

        Student student = group.getStudent(0);
        if (student != null) {
            System.out.println("Accessed student: " + student);
        }

        group.delete(0);
        group.displayGroup();
    }
}
