package controller;

import com.google.gson.Gson;
import dao.DepartmentDAO;
import dao.EmployeeDAO;
import dao.impl.DepartmentDaoImpl;
import dao.impl.EmployeeDaoImpl;
import model.Department;
import model.Employee;

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
import java.util.List;

@WebServlet(urlPatterns = "/api-department")
public class DepartmentController extends HttpServlet {

    @Inject
    private DepartmentDAO departmentDAO = new DepartmentDaoImpl();


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
                addDepartment(request, response);
            }
            if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("json")) {
                Type jsonDept = TypeFactory.getTypeReq(DataType.JSON);
                try {
                    String jsonListDept = jsonDept.getType(listDepartments(request, response));

                    PrintWriter out = response.getWriter();
                    out.print(jsonListDept);
                    out.flush();
                    out.close();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("xml")){
                Type xmlDept = TypeFactory.getTypeReq(DataType.XML);
               try {
                   Department departments = new Department();
                   departments.setDepartments(listDepartments(request, response));
                   String xmlListDept = xmlDept.getType(departments);

                   // print result
                   PrintWriter out = response.getWriter();
                   out.print(xmlListDept);
                   out.flush();
                   out.close();
               }catch (Exception e)
               {
                   System.out.println(e.getCause());
               }
            }
            else if(action.equalsIgnoreCase("list") && type.equalsIgnoreCase("text"))
            {
                Type typeText = TypeFactory.getTypeReq(DataType.TEXT);
                try {
                    String textListDept =  typeText.getType(listDepartments(request, response));
                    // print result
                    PrintWriter out = response.getWriter();
                    out.print(textListDept);
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

    private boolean getAction(HttpServletRequest request) {
        return request.getParameter("action") != null;
    }


    public List<Department> listDepartments(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Department> list = departmentDAO.findAll();
        return list;
    }

    public void addDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        Department department = gson.fromJson(request.getReader(), Department.class);
        boolean n = departmentDAO.insertDepartment(department);
        System.out.println(n);
        response.getWriter().println("Test Gson: Number row effect : " + n);
    }

}
