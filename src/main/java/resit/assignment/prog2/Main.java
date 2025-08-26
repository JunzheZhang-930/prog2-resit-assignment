package resit.assignment.prog2;

import java.util.Scanner;

// Task 6: Main class
public class Main {
    public static void main(String[] args) {
        StudentCollection collection = new StudentCollection();
        collection.addStudents("StudentDetails.csv");
        StudentOperation operations = new StudentOperation(collection);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("Type 1 to view all students' details.");
            System.out.println("Type 2 to add new student to the list.");
            System.out.println("Type 3 to search student details.");
            System.out.println("Type 4 to update a student's details.");
            System.out.println("Type 5 to remove a student.");
            System.out.println("Type 6 to print graduate students.");
            System.out.println("Type 7 to QUIT.");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println(collection);
                    break;
                case 2:
                    operations.addStudent();
                    break;
                case 3:
                    operations.searchStudentByID();
                    break;
                case 4:
                    operations.updateStudentDetails();
                    break;
                case 5:
                    operations.removeStudent();
                    break;
                case 6:
                    operations.printGraduateStudents();
                    break;
                case 7:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
