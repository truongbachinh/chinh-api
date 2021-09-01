package services;

import dao.JobHistoryDAO;
import dao.impl.JobHistoryDaoImpl;
import model.JobHistory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface JobHistoryService {
    JobHistoryDAO jobHistoryDAO = new JobHistoryDaoImpl();
    List<JobHistory> listJobHis(HttpServletRequest request, HttpServletResponse response);
    void addJobHis(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
