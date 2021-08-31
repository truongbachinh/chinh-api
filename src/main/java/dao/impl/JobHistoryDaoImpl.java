package dao.impl;

import dao.DepartmentDAO;
import dao.JobHistoryDAO;
import model.Department;
import model.Employee;
import model.JobHistory;
import util.ConnectOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobHistoryDaoImpl implements JobHistoryDAO {
    private static ConnectOracle connectedOracle = ConnectOracle.getInstance();

    @Override
    public List<JobHistory> findAll() {
        List<JobHistory> list = new ArrayList<>();
        try {
            Connection conn = connectedOracle.connection();
            String viewAllQuery = "SELECT * from job_history";
            PreparedStatement pre = conn.prepareStatement(viewAllQuery);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                JobHistory jobHistory = new JobHistory();
                jobHistory.setEmployeeId(resultSet.getInt("employee_id"));
                jobHistory.setStartDate(resultSet.getString("start_date"));
                jobHistory.setEndDate(resultSet.getString("end_date"));
                jobHistory.setJobId(resultSet.getString("job_id"));
                jobHistory.setDepartmentId(resultSet.getInt("department_id"));
                list.add(jobHistory);
            }
            conn.isClosed();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public JobHistory findById(int id) {
        JobHistory jobHistory = new JobHistory();
        try {
            Connection conn = connectedOracle.connection();
            String viewAllQuery = "SELECT * from job_history where employee_id = ?";
            PreparedStatement pre = conn.prepareStatement(viewAllQuery);
            pre.setInt(1,id);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                jobHistory.setEmployeeId(resultSet.getInt("employee_id"));
                jobHistory.setStartDate(resultSet.getString("start_date"));
                jobHistory.setEndDate(resultSet.getString("end_date"));
                jobHistory.setJobId(resultSet.getString("job_id"));
                jobHistory.setDepartmentId(resultSet.getInt("department_id"));
            }
            conn.isClosed();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jobHistory;
    }

    @Override
    public boolean insertJobHistory(JobHistory jobHistory) {
        boolean flag = false;
        try {
            Connection conn = connectedOracle.connection();
            String insertQuery = "INSERT INTO JOB_HISTORY (employee_id, start_date, end_date, job_id, department_id)" +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(insertQuery);
            pre.setInt(1, jobHistory.getEmployeeId());
            pre.setDate(2,  jobHistory.getStartDate());
            pre.setDate(3, jobHistory.getEndDate());
            pre.setString(4, jobHistory.getJobId());
            pre.setInt(5, jobHistory.getDepartmentId());

            if (isProcess(pre)) {
                flag = true;
            }
            conn.isClosed();
        } catch (SQLException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
        return flag;
    }

    @Override
    public boolean updateJobHistory(JobHistory jobHistory) {
        boolean flag = false;
        try {
            Connection conn = connectedOracle.connection();
            String insertQuery = "UPDATE  JOB_HISTORY set  start_date = ?, end_date = ?, job_id = ?, department_id = ? where employee_id = ?" +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(insertQuery);
            pre.setDate(1,  jobHistory.getStartDate());
            pre.setDate(2, jobHistory.getEndDate());
            pre.setString(3, jobHistory.getJobId());
            pre.setInt(4, jobHistory.getDepartmentId());
            pre.setInt(5, jobHistory.getEmployeeId());

            if (isProcess(pre)) {
                flag = true;
            }
            conn.isClosed();
        } catch (SQLException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
        return flag;
    }

    @Override
    public boolean deleteJobHistory(int id) {
        boolean flag = false;
        try {
            Connection conn = connectedOracle.connection();
            String deletetQuery = "DELETE FROM job_history where employee_id = ?";
            PreparedStatement pre = conn.prepareStatement(deletetQuery);
            pre.setInt(1, id);
            if (isProcess(pre)) {
                flag = true;
            }
            conn.isClosed();
        } catch (SQLException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
        return flag;
    }

    private boolean isProcess(PreparedStatement pre) throws SQLException {
        return pre.executeUpdate() > 0;
    }
    public static void main(String[] args) {
        JobHistoryDAO d = new JobHistoryDaoImpl();

        JobHistory d1  =    d.findById(102);
        System.out.println(d1.toString());
    }

}
