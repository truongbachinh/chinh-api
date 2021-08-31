package dao;

import model.Employee;
import model.JobHistory;

import java.util.List;

public interface JobHistoryDAO {
    List<JobHistory> findAll();
    JobHistory findById(int id);
    boolean insertJobHistory(JobHistory jobHistory);
    boolean updateJobHistory(JobHistory jobHistory);
    boolean deleteJobHistory(int id);
}
