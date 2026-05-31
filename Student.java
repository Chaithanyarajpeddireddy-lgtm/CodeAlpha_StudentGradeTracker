public class Student {

    private String name;
    private double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public String getGrade() {

        if(marks >= 90)
            return "A";

        else if(marks >= 80)
            return "B";

        else if(marks >= 70)
            return "C";

        else if(marks >= 60)
            return "D";

        else
            return "F";
    }

    @Override
    public String toString() {
        return name + "," + marks;
    }
}