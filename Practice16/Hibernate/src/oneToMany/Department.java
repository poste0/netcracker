package oneToMany;

import java.util.HashSet;
import java.util.Set;

public class Department {
    private int number;
    private String location;
    private String name;
    private Set<Employee> employees=new HashSet<>();
    public Department(){}
    public Department(int number, String location, String name){
        this.name=name;
        this.number=number;
        this.location=location;
    }
    //<editor-fold desc="Getters&Setters">
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    //</editor-fold>
}
