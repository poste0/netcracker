package oneToOne;

import oneToOne.Student;

public class Ticket {
    private int number;
    //private LocalDate date;
    private int course;
    private Student student;
    public Ticket(){}
    public Ticket(int number,int course,Student student){
        this.number=number;
        this.course=course;
        this.student=student;
    }
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
