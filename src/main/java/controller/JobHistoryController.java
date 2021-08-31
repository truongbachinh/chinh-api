package controller;

import dao.EmployeeDAO;
import dao.JobHistoryDAO;
import dao.impl.EmployeeDaoImpl;
import dao.impl.JobHistoryDaoImpl;
import model.JobHistory;

import javax.inject.Inject;
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

@WebServlet( urlPatterns = "/api-job-history")
public class JobHistoryController extends HttpServlet {
    @Inject
    private JobHistoryDAO jobHistoryDAO = new JobHistoryDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setJsonResponse(request, response);
        if (getAction(request)) {
            String action = request.getParameter("action");
            String type = request.getParameter("type");
           if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("json")) {
                Type typeJson = TypeFactory.getTypeReq(DataType.JSON);
                try {
                    String jsonListEmp = typeJson.getType(listJob(request, response));
                    // print result
                    PrintWriter out = response.getWriter();
                    out.print(jsonListEmp);
                    out.flush();
                    out.close();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }else if(action.equalsIgnoreCase("list") && type.equalsIgnoreCase("xml"))
            {
                Type typeXML =  TypeFactory.getTypeReq(DataType.XML);
                try {
                    JobHistory jobHistorys = new JobHistory();
                    jobHistorys.setJobHistorys(listJob(request, response));
                    String xmlListJobHistory = typeXML.getType(jobHistorys);

                    // print result
                    PrintWriter out = response.getWriter();
                    out.print(xmlListJobHistory);
                    out.flush();
                    out.close();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }else if(action.equalsIgnoreCase("list") && type.equalsIgnoreCase("text"))
           {
               Type typeText = TypeFactory.getTypeReq(DataType.TEXT);
               try {
                   String textListJob =  typeText.getType(listJob(request, response));
                   // print result
                   PrintWriter out = response.getWriter();
                   out.print(textListJob);
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

    private List<JobHistory> listJob(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       List<JobHistory> list = jobHistoryDAO.findAll();
       return list;
    }
}
