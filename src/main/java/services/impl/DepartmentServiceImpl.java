package services.impl;

import com.google.gson.Gson;
import dao.DepartmentDAO;
import model.Department;
import services.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public List<Department> listDepartments(HttpServletRequest request, HttpServletResponse response) {
        List<Department> list = departmentDAO.findAll();
        return list;
    }

    @Override
    public void addDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        Department department = gson.fromJson(request.getReader(), Department.class);
        if (validateDepartment(department)) {
            departmentDAO.insertDepartment(department);
            response.getWriter().println("Test Gson: Insert Successfully ");
        }


    }

    public boolean validateDepartment(Department department) {

        boolean flag = true;
        Integer departmentId = department.getDepartmentId();
        Department departmentDB = departmentDAO.findById(departmentId);
        Integer locationId = department.getLocationId();
        Integer managerId = department.getManagerId();
        String departmentName = department.getDepartmentName();

        if (departmentDB.getDepartmentId() != 0) {
            Logger.getLogger("Department is existed");
            flag = false;
        } else if (0 > departmentId | departmentId < 100000) {
            Logger.getLogger("Department Id should be lower than 100000 and more than 0");
            flag = false;
        } else if (0 > locationId | locationId < 100000) {
            Logger.getLogger("Location Id should be lower than 100000 and more than 0");
            flag = false;
        } else if (0 > managerId | managerId < 100000) {
            Logger.getLogger("Manager Id should be lower than 100000 and more than 0");
            flag = false;
        } else if (departmentName.isEmpty()) {
            Logger.getLogger("Department name is empty");
            flag = false;
        }
        return flag;

    }

}
