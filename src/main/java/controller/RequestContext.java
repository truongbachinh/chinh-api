package controller;


import controller.template.APIData;
import controller.template.DataTemplate;
import controller.util.DataType;
import controller.util.Type;
import controller.util.TypeFactory;
import model.Department;
import model.Employee;
import model.JobHistory;
import services.DepartmentService;
import services.EmployeeService;
import services.JobHistoryService;
import services.impl.DepartmentServiceImpl;
import services.impl.EmployeeServiceImpl;
import services.impl.JobHistoryServiceImpl;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.IOException;
import java.util.List;


public class RequestContext {
    private String type;
    private String _URI;
    @Inject
    private DataTemplate apiData = new APIData();

    @Inject
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Inject
    private JobHistoryService jobHistoryService = new JobHistoryServiceImpl();

    @Inject
    private DepartmentService departmentService = new DepartmentServiceImpl();

    private enum Data {
        json, xml, text;
    }

//    public static void main(String[] args) {
//        String str = "http://localhost:8888/Chinh_Intern_war_exploded/api-department?action=list&type=xml";
//        int index = str.indexOf("?");
//        if (index != -1) {
//            System.out.println(str.substring(48, str.indexOf("?")));
//        } else {
//            System.out.println("You dont have question mark in your url");
//        }
//    }

    public <T> void getListRequest(T object, HttpServletRequest request, HttpServletResponse response) throws IOException {
        type = request.getParameter("type");
        Data data = Data.valueOf(type);

        _URI = request.getRequestURI().substring(27);

        String result = null;

        switch (data) {
            case json:
                Type typeJSON = TypeFactory.getTypeReq(DataType.JSON);
                result = typeJSON.getType(object);
                break;
            case text:
                Type typeTEXT = TypeFactory.getTypeReq(DataType.TEXT);
                result = typeTEXT.getType(object);
                break;
            case xml:

                Type typeXML = TypeFactory.getTypeReq(DataType.XML);
                // không biết cách generic @XmlRootElemt anotaion;
                // [com.sun.istack.internal.SAXException2: unable to marshal type "java.util.ArrayList" as an element because it is missing an @XmlRootElement annotation]
                // => giải pháp tạm thời
                if (_URI.equalsIgnoreCase("api-employee")) {
                    Employee employees = new Employee();
                    employees.setEmployees(employeeService.listEmployee(request, response));
                    apiData.showData(response, typeXML.getType(employees));
                } else if (_URI.equalsIgnoreCase("api-department")) {
                    Department department = new Department();
                    department.setDepartments(departmentService.listDepartments(request, response));
                    apiData.showData(response, typeXML.getType(department));
                } else if (_URI.equalsIgnoreCase("api-department")) {
                    JobHistory jobHistorys = new JobHistory();
                    jobHistorys.setJobHistorys(jobHistoryService.listJobHis(request, response));
                    apiData.showData(response, typeXML.getType(jobHistorys));
                }
                break;

            default:
                throw new IllegalArgumentException("This Request is unsupported");
        }

        apiData.showData(response, result);
    }


}
