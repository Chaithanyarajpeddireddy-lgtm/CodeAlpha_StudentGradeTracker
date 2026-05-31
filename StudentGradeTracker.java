import java.util.*;
import java.io.*;

public class StudentGradeTracker {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        loadFromFile();

        int choice;

        do {

            System.out.println("\n================================");
            System.out.println(" STUDENT GRADE TRACKER");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Generate Report");
            System.out.println("4. Save Data");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    generateReport();
                    break;

                case 4:
                    saveToFile();
                    break;

                case 5:
                    saveToFile();
                    System.out.println("Data Saved Successfully.");
                    System.out.println("Exiting Program...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while(choice != 5);
    }

    public static void addStudent() {

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        double marks;

        while(true) {

            System.out.print("Enter Marks (0-100): ");
            marks = sc.nextDouble();

            if(marks >= 0 && marks <= 100)
                break;

            System.out.println("Invalid Marks! Enter between 0 and 100.");
        }

        sc.nextLine();

        students.add(new Student(name, marks));

        System.out.println("Student Added Successfully!");
    }

    public static void viewStudents() {

        if(students.isEmpty()) {

            System.out.println("No Records Found.");
            return;
        }

        System.out.println("\n--------------------------------------------");
        System.out.printf("%-20s %-10s %-10s%n",
                "Name","Marks","Grade");
        System.out.println("--------------------------------------------");

        for(Student s : students) {

            System.out.printf("%-20s %-10.2f %-10s%n",
                    s.getName(),
                    s.getMarks(),
                    s.getGrade());
        }
    }

    public static void generateReport() {

        if(students.isEmpty()) {

            System.out.println("No Data Available.");
            return;
        }

        double total = 0;

        Student highest = students.get(0);
        Student lowest = students.get(0);

        for(Student s : students) {

            total += s.getMarks();

            if(s.getMarks() > highest.getMarks())
                highest = s;

            if(s.getMarks() < lowest.getMarks())
                lowest = s;
        }

        double average = total / students.size();

        System.out.println("\n========== SUMMARY REPORT ==========");

        System.out.printf("Total Students : %d%n",
                students.size());

        System.out.printf("Average Score  : %.2f%n",
                average);

        System.out.printf("Highest Score  : %.2f (%s)%n",
                highest.getMarks(),
                highest.getName());

        System.out.printf("Lowest Score   : %.2f (%s)%n",
                lowest.getMarks(),
                lowest.getName());

        System.out.println("====================================");
    }

    public static void saveToFile() {

        try {

            FileWriter fw =
                    new FileWriter("student_records.txt");

            for(Student s : students) {

                fw.write(s.toString() + "\n");
            }

            fw.close();

        } catch(Exception e) {

            System.out.println(
                    "Error Saving File: "
                            + e.getMessage());
        }
    }

    public static void loadFromFile() {

        try {

            File file =
                    new File("student_records.txt");

            if(!file.exists())
                return;

            Scanner fileReader =
                    new Scanner(file);

            while(fileReader.hasNextLine()) {

                String line =
                        fileReader.nextLine();

                String[] data =
                        line.split(",");

                String name = data[0];
                double marks =
                        Double.parseDouble(data[1]);

                students.add(
                        new Student(name, marks));
            }

            fileReader.close();

        } catch(Exception e) {

            System.out.println(
                    "Error Loading File: "
                            + e.getMessage());
        }
    }
}