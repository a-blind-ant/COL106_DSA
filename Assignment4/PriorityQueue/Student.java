package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;
    //float priority; 

    public Student(String trim, int parseInt) {
        this.name = trim;
        this.marks = parseInt;
        //this.priority = (float)marks;
    }


    @Override
    public int compareTo(Student student) {
        return this.marks - student.getMarks();
    }

    public String getName() {
        return name;
    }

    public int getMarks(){
        return this.marks;
    }

    public String toString(){
        return "Student{name=" + "\'" + name + "\'" + ", marks=" + marks.toString() + "}";
    }
}
