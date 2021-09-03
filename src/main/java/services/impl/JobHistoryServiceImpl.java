package services.impl;

import com.google.gson.Gson;
import dao.JobHistoryDAO;
import model.Employee;
import model.JobHistory;
import services.JobHistoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class JobHistoryServiceImpl implements JobHistoryService {
    @Override
    public List<JobHistory> listJobHis() {
        List<JobHistory> list = jobHistoryDAO.findAll();
        return list;
    }

    @Override
    public JobHistory getJobHistory(String id) {
        JobHistory jobHistory = jobHistoryDAO.findById(id);

        return jobHistory;
    }

    @Override
    public void addJobHis(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        //Employee employee = gson.fromJson(request.getParameter("para"), Employee.class);
        // test with ajax json api
        JobHistory jobHistory = gson.fromJson(request.getReader(), JobHistory.class);
        if (validateJobHis(jobHistory)) {
            jobHistoryDAO.insertJobHistory(jobHistory);
            response.getWriter().println("Test Gson: Insert Successfully ");
        }
    }

    private boolean validateJobHis(JobHistory jobHistory) {

        boolean flag = true;
        String jobId = jobHistory.getJobId();
        Date startDate = jobHistory.getStartDate();
        Date endDate = jobHistory.getEndDate();

        JobHistory jobHisDB = jobHistoryDAO.findById(jobId);
        if (jobHisDB.getEmployeeId() != 0) {
            Logger.getLogger("job HisDB is existed");
            flag = false;
        } else if (endDate.compareTo(startDate) < 0) {
            Logger.getLogger("Date erro");
            flag = false;
        }
        return flag;


    }


}
