package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.codehaus.jackson.JsonProcessingException;
import dao.EmployeeDAO;
import dao.impl.EmployeeDaoImpl;
import model.Employee;
import util.HttpUtil;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(urlPatterns = "/api-employee")
public class EmployeeController extends HttpServlet {


    @Inject
    private EmployeeDAO employeeDAO = new EmployeeDaoImpl();


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

            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("json")) {
                Type typeJson = TypeFactory.getTypeReq(DataType.JSON);
                try {
                    String jsonListEmp = typeJson.getType(listEmployee(request, response));

                    // print result
                    PrintWriter out = response.getWriter();
                    out.print(jsonListEmp);
                    out.flush();
                    out.close();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("xml")) {
                Type typeXML = TypeFactory.getTypeReq(DataType.XML);
                try {

                    Employee employees = new Employee();
                    employees.setEmployees(listEmployee(request, response));
                    String xmlListEmp = typeXML.getType(employees);

                    // print result
                    PrintWriter out = response.getWriter();
                    out.print(xmlListEmp);
                    out.flush();
                    out.close();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
            else if(action.equalsIgnoreCase("list") && type.equalsIgnoreCase("text"))
            {
                    Type typeText = TypeFactory.getTypeReq(DataType.TEXT);
                try {
                  String textListEmp =  typeText.getType(listEmployee(request, response));
                    // print result
                    PrintWriter out = response.getWriter();
                    out.print(textListEmp);
                    out.flush();
                    out.close();

                } catch (JAXBException e) {
                    e.printStackTrace();
                }
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

    public void addEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Gson gson = new Gson();
        //Employee employee = gson.fromJson(request.getParameter("para"), Employee.class);
        // test with ajax json api
        Employee employee = gson.fromJson(request.getReader(), Employee.class);
        boolean n = employeeDAO.insertEmployee(employee);
        System.out.println(n);
        response.getWriter().println("Test Gson: Number row effect : " + n);

    }

    public List<Employee> listEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> list = employeeDAO.findAll();
        return list;
    }
}
