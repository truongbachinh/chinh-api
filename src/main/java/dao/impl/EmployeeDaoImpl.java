package dao.impl;

import controller.EmployeeController;
import dao.EmployeeDAO;
import dao.JobHistoryDAO;
import model.Employee;
import model.JobHistory;
import util.ConnectOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO {
    private static ConnectOracle connectedOracle = ConnectOracle.getInstance();


    @Override
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        try {
            Connection conn = connectedOracle.connection();
            String viewAllQuery = "SELECT * from EMPLOYEES";
            PreparedStatement pre = conn.prepareStatement(viewAllQuery);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setEmmail(resultSet.getString("email"));
                employee.setHireDate(resultSet.getString("hire_date"));
                employee.setJobId(resultSet.getString("job_id"));
                employee.setSalary(resultSet.getLong("salary"));
                list.add(employee);
            }
            conn.isClosed();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }


    @Override
    public Employee findById(int id) {
        Employee employee = new Employee();
        try {
            Connection conn = connectedOracle.connection();
            String viewAllQuery = "SELECT * from EMPLOYEES where employee_id = ?";
            PreparedStatement pre = conn.prepareStatement(viewAllQuery);
            pre.setInt(1, id);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setEmmail(resultSet.getString("email"));
                employee.setHireDate(resultSet.getString("hire_date"));
                employee.setJobId(resultSet.getString("job_id"));
                employee.setSalary(resultSet.getLong("salary"));

            }
            conn.isClosed();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }

    @Override
    public boolean insertEmployee(Employee employee) {
        boolean flag = false;
        try {
            Connection conn = connectedOracle.connection();
            PreparedStatement pre = null;
            String insertQuery = "INSERT INTO EMPLOYEES (employee_id, first_name, last_name, email, hire_date, job_id, salary)" +
                    "VALUES (?,?,?,?,?,?,?)";
             pre = conn.prepareStatement(insertQuery);
            pre.setInt(1, employee.getEmployeeId());
            pre.setString(2, employee.getFirstName());
            pre.setString(3, employee.getLastName());
            pre.setString(4, employee.getEmail());
            pre.setDate(5, employee.getHireDate());
            pre.setString(6, employee.getJobId());
            pre.setFloat(7, employee.getSalary());
            if (isProcess(pre)) {
                flag = true;
            }
            conn.isClosed();
        } catch (SQLException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
        return flag;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        boolean flag = false;
        try {
            Connection conn = connectedOracle.connection();
            PreparedStatement pre = null;
            String insertQuery = "UPDATE EMPLOYEES SET  first_name = ?, last_name = ?, email = ?, hire_date = ?, job_id = ?, salary =? when id = ?)" +
                    "VALUES (?,?,?,?,?,?,?)";
            pre = conn.prepareStatement(insertQuery);
            pre.setString(1, employee.getFirstName());
            pre.setString(2, employee.getLastName());
            pre.setString(3, employee.getEmail());
            pre.setDate(4, employee.getHireDate());
            pre.setString(5, employee.getJobId());
            pre.setFloat(6, employee.getSalary());
            pre.setInt(7, employee.getEmployeeId());
            if (isProcess(pre)) {
                flag = true;
            }
            conn.isClosed();
        } catch (SQLException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
        return flag;
    }

    @Override
    public boolean deleteEmployee(int id) {
        boolean flag = false;
        try {
            Connection conn = connectedOracle.connection();
            String deletetQuery = "DELETE FROM employees where employee_id = ?";
            PreparedStatement pre = conn.prepareStatement(deletetQuery);
            pre.setInt(1, id);
            if (isProcess(pre)) {
                flag = true;
            }
            conn.isClosed();
        } catch (SQLException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
        return flag;
    }

    private boolean isProcess(PreparedStatement pre) throws SQLException {
        return pre.executeUpdate() > 0;
    }


    public static void main(String[] args) {
        EmployeeDAO d = new EmployeeDaoImpl();
        Employee employee = new Employee();
        employee.setEmployeeId(381);
        employee.setFirstName("Truong");
        employee.setLastName("Ba Chinh");
        employee.setEmmail("chinhtb181@gmail.com");
        employee.setHireDate("2009-10-10");
        employee.setJobId("AD_VP");
        employee.setSalary(17000);

      boolean a =  d.insertEmployee(employee);
        System.out.println(a);
    }

}
