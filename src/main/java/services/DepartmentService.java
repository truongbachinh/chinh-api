package services;

import dao.DepartmentDAO;
import dao.impl.DepartmentDaoImpl;
import model.Department;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface DepartmentService {
    DepartmentDAO departmentDAO =  new DepartmentDaoImpl();
    List<Department> listDepartments();
    Department getDepartment(String id);
    void addDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
