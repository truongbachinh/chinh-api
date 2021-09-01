package services;

import dao.EmployeeDAO;
import dao.impl.EmployeeDaoImpl;
import model.Employee;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface EmployeeService {

    EmployeeDAO employeeDAO = new EmployeeDaoImpl();
    void addEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException;
    List<Employee> listEmployee(HttpServletRequest request, HttpServletResponse response);

}
