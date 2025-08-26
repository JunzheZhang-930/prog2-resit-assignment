package resit.assignment.prog2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Task 2: Student class
class Student {
    private String studentID;
    private String studentName;
    private int age;
    private char gender;
    private int grade;
    private String yearOfAdmission;
    private String yearOfGraduation;

    public Student(String studentID, String studentName, int age, char gender, int grade, String yearOfAdmission, String yearOfGraduation) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.age = age;
        this.gender = gender;
        this.grade = grade;
        this.yearOfAdmission = yearOfAdmission;
        this.yearOfGraduation = yearOfGraduation;
    }

    // Getters and Setters
    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }

    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }

    public String getYearOfAdmission() { return yearOfAdmission; }
    public void setYearOfAdmission(String yearOfAdmission) { this.yearOfAdmission = yearOfAdmission; }

    public String getYearOfGraduation() { return yearOfGraduation; }
    public void setYearOfGraduation(String yearOfGraduation) { this.yearOfGraduation = yearOfGraduation; }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", grade=" + grade +
                ", yearOfAdmission='" + yearOfAdmission + '\'' +
                ", yearOfGraduation='" + yearOfGraduation + '\'' +
                '}';
    }
}

// Task 3: StudentCollection class
class StudentCollection {
    private ArrayList<Student> students;

    public StudentCollection() {
        this.students = new ArrayList<>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudents(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String studentID = data[0];
                String studentName = data[1];
                int age = Integer.parseInt(data[2]);
                char gender = data[3].charAt(0);
                int grade = Integer.parseInt(data[4]);
                String yearOfAdmission = data[5];
                String yearOfGraduation = data[6];
                students.add(new Student(studentID, studentName, age, gender, grade, yearOfAdmission, yearOfGraduation));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return students.toString();
    }
}

// Task 5: Graduates class
class Graduates {
    public static void printGraduates(ArrayList<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("GraduateStudents.txt"))) {
            bw.write("Following students have graduated so far.\n");
            bw.write(String.format("%-12s %-20s %-4s %-6s %-18s %-18s%n", 
                "Student ID", "Student Name", "Age", "Gender", "Year of Admission", "Year of Graduation"));
            for (Student s : students) {
                if (s.getYearOfGraduation() != null && !s.getYearOfGraduation().isEmpty()) {
                    bw.write(String.format("%-12s %-20s %-4d %-6c %-18s %-18s%n", 
                        s.getStudentID(), s.getStudentName(), s.getAge(), s.getGender(), 
                        s.getYearOfAdmission(), s.getYearOfGraduation()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}

// Task 4: StudentOperation class
class StudentOperation {
    private StudentCollection collection;
    private Scanner scanner;

    public StudentOperation(StudentCollection collection) {
        this.collection = collection;
        this.scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter gender (M/F): ");
        char gender = scanner.nextLine().charAt(0);
        System.out.print("Enter grade (1-5): ");
        int grade = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter year of admission: ");
        String yoa = scanner.nextLine();
        System.out.print("Enter year of graduation: ");
        String yog = scanner.nextLine();

        collection.getStudents().add(new Student(id, name, age, gender, grade, yoa, yog));
        System.out.println("Student added successfully.");
    }

    public void removeStudent() {
        System.out.print("Enter student ID to remove: ");
        String id = scanner.nextLine();
        ArrayList<Student> students = collection.getStudents();
        boolean found = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID().equals(id)) {
                students.remove(i);
                found = true;
                System.out.println("Student removed successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("This ID does not exist.");
        }
    }

    public void updateStudentDetails() {
        System.out.print("Enter student ID to update: ");
        String id = scanner.nextLine();
        ArrayList<Student> students = collection.getStudents();
        boolean found = false;
        for (Student s : students) {
            if (s.getStudentID().equals(id)) {
                found = true;
                System.out.print("Enter new name (or 'null' to skip): ");
                String name = scanner.nextLine();
                if (!name.equals("null")) s.setStudentName(name);

                System.out.print("Enter new age (or 0 to skip): ");
                int age = Integer.parseInt(scanner.nextLine());
                if (age != 0) s.setAge(age);

                System.out.print("Enter new gender (M/F, or 'null' to skip): ");
                String genderStr = scanner.nextLine();
                if (!genderStr.equals("null")) s.setGender(genderStr.charAt(0));

                System.out.print("Enter new grade (1-5, or 0 to skip): ");
                int grade = Integer.parseInt(scanner.nextLine());
                if (grade != 0) s.setGrade(grade);

                System.out.print("Enter new year of admission (or 'null' to skip): ");
                String yoa = scanner.nextLine();
                if (!yoa.equals("null")) s.setYearOfAdmission(yoa);

                System.out.print("Enter new year of graduation (or 'null' to skip): ");
                String yog = scanner.nextLine();
                if (!yog.equals("null")) s.setYearOfGraduation(yog);

                System.out.println("Student details updated successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("This ID does not exist.");
        }
    }

    public void searchStudentByID() {
        System.out.print("Enter student ID to search: ");
        String id = scanner.nextLine();
        ArrayList<Student> students = collection.getStudents();
        boolean found = false;
        for (Student s : students) {
            if (s.getStudentID().equals(id)) {
                System.out.println(s);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("This ID does not exist.");
        }
    }

    public void printGraduateStudents() {
        Graduates.printGraduates(collection.getStudents());
        System.out.println("Graduate students printed to file.");
    }
}
