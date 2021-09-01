package services.impl;

import com.google.gson.Gson;
import model.Department;
import model.Employee;
import services.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void addEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        //Employee employee = gson.fromJson(request.getParameter("para"), Employee.class);
        // test with ajax json api
        Employee employee = gson.fromJson(request.getReader(), Employee.class);
        if (validateEmployee(employee)) {
            employeeDAO.insertEmployee(employee);
            response.getWriter().println("Test Gson: Insert Successfully ");
        }
    }

    @Override
    public List<Employee> listEmployee(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> list = employeeDAO.findAll();
        return list;
    }

    private boolean validateEmployee(Employee employee) {
        boolean flag = true;
        Integer empId = employee.getEmployeeId();
        Employee employeeDB = employeeDAO.findById(empId);
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        String email = employee.getEmail();
        String jobId = employee.getJobId();

        Matcher matcher = getMatcherEmail(email);

        if (employeeDB.getEmployeeId() != 0) {
            Logger.getLogger("Employee is existed");
            flag = false;
        } else if (0 > empId | empId < 100000) {
            Logger.getLogger("empId Id should be lower than 100000 and more than 0");
            flag = false;
        } else if (firstName.isEmpty() | lastName.isEmpty()) {
            Logger.getLogger("Name is empty");
            flag = false;
        } else if (email.isEmpty() | matcher.matches() == false) {
            Logger.getLogger("Error Email");
            flag = false;
        } else if (jobId.isEmpty()) {
            Logger.getLogger("jobId is empty");
            flag = false;
        }
        return flag;

    }

    private Matcher getMatcherEmail(String email) {
        String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regexEmail);
        return pattern.matcher(email);
    }
}
