package controller;

import com.google.gson.Gson;
import controller.template.APIData;
import controller.template.DataTemplate;
import controller.util.DataType;
import controller.util.Type;
import controller.util.TypeFactory;
import dao.EmployeeDAO;
import dao.impl.EmployeeDaoImpl;
import model.Employee;
import services.EmployeeService;
import services.impl.EmployeeServiceImpl;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;


@WebServlet(name = "employee", urlPatterns = "/api-employee")
public class EmployeeController extends HttpServlet {


    @Inject
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Inject
    private DataTemplate apiData = new APIData();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setJsonResponse(request, response);
        if (getAction(request)) {
            String action = request.getParameter("action");
            String type = request.getParameter("type");
            if (action.equalsIgnoreCase("new")) {
                showForm(request, response);
            } else if (action.equalsIgnoreCase("insert")) {
                employeeService.addEmployee(request, response);
            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("json")) {
                Type typeJson = TypeFactory.getTypeReq(DataType.JSON);
                String jsonListEmp = typeJson.getType(employeeService.listEmployee(request, response));
                apiData.showData(response, jsonListEmp);


            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("xml")) {
                Type typeXML = TypeFactory.getTypeReq(DataType.XML);
                Employee employees = new Employee();
                employees.setEmployees(employeeService.listEmployee(request, response));
                apiData.showData(response, typeXML.getType(employees));

            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("text")) {
                Type typeText = TypeFactory.getTypeReq(DataType.TEXT);
                String textListEmp = typeText.getType(employeeService.listEmployee(request, response));
                apiData.showData(response, textListEmp);

            }
        } else {
            response.getWriter().print("get url = action=list&type={xml,json,text}");
        }
    }

    private void setJsonResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
    }

    private boolean getAction(HttpServletRequest request) {
        return request.getParameter("action") != null;
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddEmployee.jsp");
        requestDispatcher.forward(request, response);
    }


}
