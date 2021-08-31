package model;




import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@XmlRootElement(name = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private Date hireDate;
    private float salary;
    private String jobId;

    @XmlElement(name = "employee")
    private List<Employee> employees = null;

    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee() {

    }

    public Employee(int employeeId, String firstName, String lastName, String email, Date hireDate, float salary, String jobId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.salary = salary;
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", jobId='" + jobId + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmmail(String email) {
        this.email = email;
    }


    public Employee(int employeeId, String firstName, String lastName, float salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }


    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public java.sql.Date getHireDate() {
        java.sql.Date sqlDate = new java.sql.Date(hireDate.getTime());
        return sqlDate;
    }

    public void setHireDate(String hireDate) {

        try {
            this.hireDate = new SimpleDateFormat("YYYY-MM-DD").parse(hireDate);
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


    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Employee employee = new Employee(414, "Lokesh", "Gupta","chinh@gmail",date, 141200,"AD_VP");

        //Method which uses JAXB to convert object to XML
        jaxbObjectToXML(employee);
    }
    private static void jaxbObjectToXML(Employee employee)
    {
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(employee.getClass());

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            jaxbMarshaller.marshal(employee, sw);

            //Verify XML Content
            String xmlContent = sw.toString();
            System.out.println( xmlContent );

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
