package dao;

import model.Employee;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private final Connection connection;

    private EmployeeDao() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(Utils.ENV.URL, Utils.ENV.USER_NAME, Utils.ENV.PASSWORD);
    }

    public static EmployeeDao getInstance() throws Exception {
        return new EmployeeDao();
    }

    public List<Employee> getAllEmployee() throws Exception {
        List<Employee> list = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Utils.Queries.GET_ALL);

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int salary = resultSet.getInt(2);
            String name = resultSet.getString(3);
            String company = resultSet.getString(4);
            list.add(new Employee(id, salary, name, company));
        }
        statement.close();

        return list;
    }

    public void addEmployee(Employee employee) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(Utils.Queries.ADD_EMPLOYEE);

        preparedStatement.setInt(1, employee.getId());
        preparedStatement.setInt(2, employee.getSalary());
        preparedStatement.setString(3, employee.getName());
        preparedStatement.setString(4, employee.getCompany());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteEmployee(int id) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(Utils.Queries.getDeleteQuery(id));
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public Employee getEmployee(int id) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Utils.Queries.getEmployeeQuery(id));
        if (resultSet.next()) {
            return new Employee(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4));
        }

        statement.close();
        return null;
    }

    public void updateEmployeeSalary(int id, int salary) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(Utils.Queries.updateSalaryQuery(id, salary));
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
