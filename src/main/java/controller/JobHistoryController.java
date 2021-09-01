package controller;

import controller.template.APIData;
import controller.template.DataTemplate;
import controller.util.DataType;
import controller.util.Type;
import controller.util.TypeFactory;
import dao.JobHistoryDAO;
import dao.impl.JobHistoryDaoImpl;
import model.JobHistory;
import services.JobHistoryService;
import services.impl.JobHistoryServiceImpl;

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

@WebServlet(name = "employee", urlPatterns = "/api-job-history")
public class JobHistoryController extends HttpServlet {
    @Inject
    private JobHistoryService jobHistoryService = new JobHistoryServiceImpl();

    @Inject
    private DataTemplate apiData = new APIData();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (getAction(request)) {
            String action = request.getParameter("action");
            String type = request.getParameter("type");
            if (action.equalsIgnoreCase("insert")) {
                jobHistoryService.addJobHis(request, response);
            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("json")) {
                Type typeJson = TypeFactory.getTypeReq(DataType.JSON);
                apiData.showData(response, typeJson.getType(jobHistoryService.listJobHis(request, response)));

            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("xml")) {
                Type typeXML = TypeFactory.getTypeReq(DataType.XML);
                JobHistory jobHistorys = new JobHistory();
                jobHistorys.setJobHistorys(jobHistoryService.listJobHis(request, response));
                apiData.showData(response, typeXML.getType(jobHistorys));

            } else if (action.equalsIgnoreCase("list") && type.equalsIgnoreCase("text")) {
                Type typeText = TypeFactory.getTypeReq(DataType.TEXT);
                apiData.showData(response, typeText.getType(jobHistoryService.listJobHis(request, response)));

            }
        } else {
            response.getWriter().print("get url = action=list&type={xml,json,text}");
        }

    }


    private boolean getAction(HttpServletRequest request) {
        return request.getParameter("action") != null;
    }


}
