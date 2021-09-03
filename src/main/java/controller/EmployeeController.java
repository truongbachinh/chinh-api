package controller;


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




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestContext requestContext = new RequestContext();
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("list")) {
            requestContext.getListRequest(employeeService.getListEmployee(), request, response);
        }
        else if(action.equalsIgnoreCase("el"))
        {
            String id = request.getParameter("id" );
            requestContext.getListRequest(employeeService.getEmployee(id), request, response);
        }
        else if(action.equalsIgnoreCase("insert"))
        {
            employeeService.addEmployee(request,response);
        }

    }


    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddEmployee.jsp");
        requestDispatcher.forward(request, response);
    }


}
