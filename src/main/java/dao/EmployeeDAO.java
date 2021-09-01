package dao;

import model.Employee;
import util.ConnectOracle;

import java.util.List;

public interface EmployeeDAO {
    ConnectOracle connectedOracle = ConnectOracle.getInstance();
    List<Employee> findAll();
    Employee findById(int id);
    boolean insertEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(int id);
}
