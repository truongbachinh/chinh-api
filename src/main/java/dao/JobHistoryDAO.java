package dao;

import model.JobHistory;
import util.ConnectOracle;

import java.util.List;

public interface JobHistoryDAO {
    ConnectOracle connectedOracle = ConnectOracle.getInstance();
    List<JobHistory> findAll();
    JobHistory findById(String id);
    boolean insertJobHistory(JobHistory jobHistory);
    boolean updateJobHistory(JobHistory jobHistory);
    boolean deleteJobHistory(int id);
}
