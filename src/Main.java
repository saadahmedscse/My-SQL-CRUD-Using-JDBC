import dao.EmployeeDao;
import model.Employee;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static EmployeeDao dao;

    public static void main(String[] args) throws Exception {
        scanner = new Scanner(System.in);
        dao = EmployeeDao.getInstance();

        System.out.println("\n-----------------------------------------EMPLOYEE MANAGEMENT SYSTEM-----------------------------------------");

        runSystem();
    }

    private static void runSystem() {
        System.out.println("\n\n1. Add Employee");
        System.out.println("2. Update Employee");
        System.out.println("3. Get Employee");
        System.out.println("4. Get All Employee");
        System.out.println("5. Delete Employee\n");

        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        while (true) {
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> updateEmployee();
                case 3 -> getEmployee();
                case 4 -> getAllEmployee();
                case 5 -> deleteEmployee();
            }
        }
    }

    private static void addEmployee() {
        System.out.println("\nEnter Employee Details");
        System.out.print("Enter employee id: ");
        int id = scanner.nextInt();
        System.out.print("Enter employee salary: ");
        int salary = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter employee company: ");
        String company = scanner.nextLine();

        Employee employee = new Employee(id, salary, name, company);
        System.out.println(employee);

        try {
            dao.addEmployee(new Employee(id, salary, name, company));
            runSystem();
        } catch (Exception ignored) {}
    }

    private static void updateEmployee() {
        try {
            System.out.print("\nEnter employee id: ");
            int id = scanner.nextInt();
            System.out.print("Enter new salary: ");
            int salary = scanner.nextInt();

            dao.updateEmployeeSalary(id, salary);

            runSystem();
        } catch (Exception ignored) {}
    }

    private static void getEmployee() {
        try {
            System.out.print("\nEnter employee id: ");
            int id = scanner.nextInt();
            System.out.println("Id\t\tSalary\t\tName\t\t\tCompany");
            Employee e = dao.getEmployee(id);
            System.out.println(e.getId() + "\t\t" + e.getSalary() + "\t\t" + e.getName() + "\t\t\t" + e.getCompany());

            runSystem();
        } catch (Exception ignored) {}
    }

    private static void getAllEmployee() {
        try {
            List<Employee> list = dao.getAllEmployee();

            if (list.size() > 0) {
                System.out.println("Id\t\tSalary\t\tName\t\t\tCompany");
                for (Employee e : list) {
                    System.out.println(e.getId() + "\t\t" + e.getSalary() + "\t\t" + e.getName() + "\t\t\t" + e.getCompany());
                }
            } else System.out.println("No employee found");

            System.out.println("\n0. Go Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            while (true) {
                if (choice == 0) {
                    runSystem();
                }
            }
        } catch (Exception ignored) {}
    }

    private static void deleteEmployee() {
        try {
            System.out.print("\nEnter employee id: ");
            int id = scanner.nextInt();
            dao.deleteEmployee(id);

            runSystem();
        } catch (Exception ignored) {}
    }
}
