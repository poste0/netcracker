package oneToOne;

public class Student {
    private int group;
    private String firstName,lastName;
    private int id;
    private int age;
    private Ticket ticket;
    public Student(){}
    public Student(int id, String firstName, String lastName, int group, int age, Ticket ticket){
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.group=group;
        this.ticket=ticket;
        this.id=id;
    }
    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
