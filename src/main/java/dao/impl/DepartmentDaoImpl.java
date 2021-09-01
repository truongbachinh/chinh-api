package dao.impl;

import dao.DepartmentDAO;
import model.Department;
import model.Employee;
import util.ConnectOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDAO {
    Connection conn;
    {
        try {
            conn = connectedOracle.connection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public List<Department> findAll() {
        List<Department> list = new ArrayList<>();
        try {

            String viewAllQuery = "SELECT * from departments";
            PreparedStatement pre = conn.prepareStatement(viewAllQuery);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                department.setManagerId(resultSet.getInt("manager_id"));
                department.setLocationId(resultSet.getInt("location_id"));
                list.add(department);
            }
            conn.isClosed();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }



    @Override
    public Department findById(int id) {
        Department department = new Department();
        try {
            String viewAllQuery = "SELECT * from departments where department_id = ?";
            PreparedStatement pre = conn.prepareStatement(viewAllQuery);
            pre.setInt(1,id);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                department.setManagerId(resultSet.getInt("manager_id"));
                department.setLocationId(resultSet.getInt("location_id"));
            }
            conn.isClosed();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return department;
    }

    @Override
    public boolean insertDepartment(Department department) {
        boolean flag = false;
        try {
            String insertQuery = "INSERT INTO departments (department_id, department_name, manager_id, location_id)" +
                    "VALUES (?,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(insertQuery);
            pre.setInt(1, department.getDepartmentId());
            pre.setString(2, department.getDepartmentName());
            pre.setInt(3, department.getManagerId());
            pre.setInt(4, department.getLocationId());
            if (isProcess(pre)) {
                flag = true;
            }
            conn.isClosed();
        } catch (SQLException e) {
            e.getMessage();
        }
        return flag;
    }

    @Override
    public boolean updateDepartment(Department department) {
        boolean flag = false;
        try {

            String insertQuery = "UPDATE  departments set department_name = ?, manager_id = ?, location_id = ? where  department_id = ?" +
                    "VALUES (?,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(insertQuery);
            pre.setString(1, department.getDepartmentName());
            pre.setInt(2, department.getManagerId());
            pre.setInt(3, department.getLocationId());
            pre.setInt(4, department.getDepartmentId());
            if (isProcess(pre)) {
                flag = true;
            }
            conn.isClosed();
        } catch (SQLException e) {
            e.getMessage();
        }
        return flag;
    }

    @Override
    public boolean deleteDepartment(int id) {
        boolean flag = false;
        try {

            String deletetQuery = "DELETE FROM departments where department_id = ?";
            PreparedStatement pre = conn.prepareStatement(deletetQuery);
            pre.setInt(1, id);
            if (isProcess(pre)) {
                flag = true;
            }
            conn.isClosed();
        } catch (SQLException e) {
            e.getMessage();
        }
        return flag;
    }
    private boolean isProcess(PreparedStatement pre) throws SQLException {
        return pre.executeUpdate() > 0;
    }

    public static void main(String[] args) {
//        DepartmentDAO d = new DepartmentDaoImpl();
//        Department d1 = new Department();
//        d1.setDepartmentId(300);
//        d1.setDepartmentName("Shareholder");
//        d1.setDepartmentId(0);
//        d1.setDepartmentId(1700);
//        Boolean a  =    d.insertDepartment(d1);
//        System.out.println(a);

//        DepartmentDAO d = new DepartmentDaoImpl();
//        Department d1 = new Department();
//        d1 = d.findById(60);
//        System.out.println(d1.toString());

    }
}
