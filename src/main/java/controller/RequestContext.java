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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


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

    public <T> void getListRequest(T object, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Data data = getData(request);
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
                    employees.setEmployees(employeeService.getListEmployee());
                    result = typeXML.getType(employees);
                } else if (_URI.equalsIgnoreCase("api-department")) {
                    Department departments = new Department();
                    departments.setDepartments(departmentService.listDepartments());
                    result = typeXML.getType(departments);
                } else if (_URI.equalsIgnoreCase("api-department")) {
                    JobHistory jobHistorys = new JobHistory();
                    jobHistorys.setJobHistorys(jobHistoryService.listJobHis());
                    result = typeXML.getType(jobHistorys);
                }
                break;
            default:
                throw new IllegalArgumentException("This Request is unsupported");
        }

        apiData.showData(response, result);
    }

//    public <T> Object addElRequest(Object tClass, T object, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Data data = getData(request);
//        Object result = null;
//        switch (data) {
//            case json:
//                Type typeJSON = TypeFactory.getTypeReq(DataType.JSON);
//                result = typeJSON.addEl(tClass,object,request);
//                break;
//            default:
//                throw new IllegalArgumentException("This Request is unsupported");
//        }
//        return result;
//
//    }


    private Data getData(HttpServletRequest request) {
        type = request.getParameter("type");
        return Data.valueOf(type);
    }



}
