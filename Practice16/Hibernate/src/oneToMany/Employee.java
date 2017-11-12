package oneToMany;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private int salgrade;
    private Department department;
    public Employee(){}
    public Employee(int id,String firstName,String lastName,int age,int salgrade){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.salgrade=salgrade;
    }
    //<editor-fold desc="Getters&Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSalgrade() {
        return salgrade;
    }

    public void setSalgrade(int salgrade) {
        this.salgrade = salgrade;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    //</editor-fold>
}
