package utils;

public class Utils {

    public static class Queries {
        public static final String GET_ALL = "SELECT * FROM employee";
        public static final String ADD_EMPLOYEE = "INSERT into employee values (?, ?, ?, ?)";
        public static String getDeleteQuery(int id) {
            return "DELETE employee FROM employee WHERE id=" + id;
        }
        public static String getEmployeeQuery(int id) {
            return "SELECT id, salary, name, company FROM employee WHERE id=" + id;
        }

        public static String updateSalaryQuery(int id, int salary) {
            return "UPDATE employee SET salary=" + salary +" WHERE id=" + id;
        }
    }

    public static class ENV {
        public static final String URL = "jdbc:mysql://localhost:3306/vampire";
        public static final String USER_NAME = "root";
        public static final String PASSWORD = "PASSWORD";
    }
}
