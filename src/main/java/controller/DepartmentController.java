package controller;

import com.google.gson.Gson;
import controller.template.APIData;
import controller.template.DataTemplate;
import controller.util.DataType;
import controller.util.Type;
import controller.util.TypeFactory;
import dao.DepartmentDAO;
import dao.impl.DepartmentDaoImpl;
import model.Department;
import services.DepartmentService;
import services.impl.DepartmentServiceImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "department", urlPatterns = "/api-department")
public class DepartmentController extends HttpServlet {

    @Inject
    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Inject
    private DataTemplate apiData = new APIData();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (getAction(request)) {
            String action = request.getParameter("action");
            String type = request.getParameter("type");
            if (action.equals("insert")) {
                departmentService.addDepartment(request, response);
            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("json")) {
                Type jsonDept = TypeFactory.getTypeReq(DataType.JSON);
                String jsonListDept = jsonDept.getType(departmentService.listDepartments(request, response));
                apiData.showData(response, jsonListDept);

            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("xml")) {
                Type xmlDept = TypeFactory.getTypeReq(DataType.XML);
                Department departments = new Department();
                departments.setDepartments(departmentService.listDepartments(request, response));
                String xmlListDept = xmlDept.getType(departments);
                apiData.showData(response, xmlListDept);

            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("text")) {
                Type typeText = TypeFactory.getTypeReq(DataType.TEXT);
                String textListDept = typeText.getType(departmentService.listDepartments(request, response));
                apiData.showData(response, textListDept);
            }
        } else {
            response.getWriter().print("get url = action=list&type={xml,json,text}");
        }
    }

    private boolean getAction(HttpServletRequest request) {
        return request.getParameter("action") != null;
    }


}
