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
        RequestContext requestContext = new RequestContext();
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("list")) {
            requestContext.getListRequest(departmentService.listDepartments(), request, response);
        }
    }


}
