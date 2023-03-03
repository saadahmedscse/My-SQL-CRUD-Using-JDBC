package model;

public class Employee {

    int id, salary;
    String name, company;

    public Employee(int id, int salary, String name, String company) {
        this.id = id;
        this.salary = salary;
        this.name = name;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }
}
