package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "JobHistory")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    private int employeeId;
    private Date startDate;
    private Date endDate;
    private String jobId;
    private int departmentId;

    @XmlElement(name = "employee")
    private List<JobHistory> jobHistorys = null;


    public List<JobHistory> getJobHistorys() {
        return jobHistorys;
    }

    public void setJobHistorys(List<JobHistory> jobHistorys) {
        this.jobHistorys = jobHistorys;
    }

    public JobHistory() {

    }

    public JobHistory(int employeeId, Date startDate, Date endDate, String jobId, int departmentId) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.jobId = jobId;
        this.departmentId = departmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public java.sql.Date getStartDate() {
        java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
        return sqlDate;

    }

    public void setStartDate(String startDate) {

        try {
            this.startDate = new SimpleDateFormat("YYYY-MM-DD").parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public java.sql.Date getEndDate() {
        java.sql.Date sqlDate = new java.sql.Date(endDate.getTime());
        return sqlDate;

    }

    public void setEndDate(String endDate) {
        try {
            this.endDate = new SimpleDateFormat("YYYY-MM-DD").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "JobHistory{" +
                "employeeId=" + employeeId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", jobId='" + jobId + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
