package dao;

import model.Department;
import model.Employee;

import java.util.List;

public interface DepartmentDAO {
    List<Department> findAll();
    Department findById(int id);
    boolean insertDepartment(Department department);
    boolean updateDepartment(Department department);
    boolean deleteDepartment(int id);
}
