package dao;

import model.Department;
import model.Employee;
import util.ConnectOracle;

import java.util.List;

public interface DepartmentDAO {
    ConnectOracle connectedOracle = ConnectOracle.getInstance();
    List<Department> findAll();
    Department findById(int id);
    boolean insertDepartment(Department department);
    boolean updateDepartment(Department department);
    boolean deleteDepartment(int id);
}
