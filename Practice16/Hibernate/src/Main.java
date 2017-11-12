import oneToMany.Department;
import oneToMany.Employee;
import oneToOne.Student;
import oneToOne.Ticket;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static Session session=HibernateUtil.getFactory().openSession();
    private static Transaction transaction=null;
    public static void main(String[] args){

        Scanner scanner=new Scanner(System.in);
        int choose=-1;
        while(choose!=6) {
             choose= scanner.nextInt();
            switch (choose) {
                case 0:
                    oneToOne();
                    break;
                case 1:

                    manyToOne();
                    break;
                case 2:

                    hql();
                    break;
                case 3:
                    clean();
                    break;
                case 4:
                    criteria();
                    break;
                case 5:
                    named();
                    break;
                default:
                    break;
            }
        }
        session.close();

    }
    public static void clean(){
        transaction=session.beginTransaction();
        Query query=session.createQuery("delete from Student ");
        query.executeUpdate();
        transaction.commit();
    }

    public static void oneToOne(){
        try {
            transaction= session.beginTransaction();
            Ticket ticket = new Ticket();
            ticket.setCourse(1);
            Student student = new Student();
            student.setFirstName("Vasiliyes");
            student.setLastName("Vasilieves");
            student.setAge(21);
            student.setGroup(1111);
            ticket.setStudent(student);
            student.setTicket(ticket);

            session.save(student);
            session.save(ticket);
            transaction.commit();
        }
        catch (HibernateException e){
            if(transaction==null){
                transaction.rollback();
            }
        }

    }
    public static void manyToOne(){
        transaction=session.beginTransaction();
        Employee employee=new Employee();
        employee.setFirstName("Alex");
        employee.setLastName("Some");
        employee.setAge(20);
        employee.setSalgrade(100);
        Employee employee1=new Employee();
        employee1.setFirstName("Max");
        employee1.setLastName("MAx");
        employee1.setAge(25);
        employee1.setSalgrade(231);
        Set<Employee> employees=new HashSet<>();
        employees.add(employee);
        employees.add(employee1);
        Department department=new Department();
        department.setName("First");
        department.setLocation("City");
        department.setEmployees(employees);
        Employee employee2=new Employee();
        employee2.setFirstName("s");
        employee2.setLastName("a");
        employee2.setAge(11);
        employee2.setSalgrade(1);
        Department department1=new Department();
        department1.setName("asd");
        department1.setLocation("asdq");
        department1.getEmployees().add(employee2);
        session.save(department);
        session.save(employee);
        session.save(employee1);
        session.save(department1);
        session.save(employee2);
        transaction.commit();

    }
    public static void hql(){
        Query query=session.createQuery("from Student student ");
        List<Student> students=query.list();
        for(Student student:students){
            System.out.println(student.getId()+" "+student.getFirstName()+" "+
            student.getLastName()+" "+student.getAge()+" "+student.getGroup()+""+student.getTicket().getNumber()+"\n");
        }
    }
    public static void criteria(){
        Criteria criteria=session.createCriteria(Student.class);
        List<Student> students=criteria.add(Restrictions.le("age",24)).addOrder(Order.asc("age")).list();
        for(Student student:students){
            System.out.println(student.getId()+" "+student.getFirstName()+" "+
                    student.getLastName()+" "+student.getAge()+" "+student.getGroup()+""+student.getTicket().getNumber()+"\n");
        }
    }
    public static void named(){
        List<Student> students=  session.getNamedQuery("selectAll").list();
        for(Student student:students){
            System.out.println(student.getId()+" "+student.getFirstName()+" "+
                    student.getLastName()+" "+student.getAge()+" "+student.getGroup()+""+student.getTicket().getNumber()+"\n");
        }
    }
}
