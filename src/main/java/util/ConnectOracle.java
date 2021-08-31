package util;

import java.sql.*;

//Viết 1 project java với YC
//        + Thu thập dữ liệu từ các API REST service và insert vào DB.
//        - API services: Có 3 loại cấu trúc dữ liệu: Chú ý các struct có thể thay thêm bớt field mới theo YC
//        + API trả về dạng xml: Có 2 API tương ứng với 2 struct khác nhau (Ví dụ Employee và Department)
//        + API trả về dạng json: Có 2 API tương ứng với 2 struct khác nhau (Ví dụ Employee và Department)
//        + API trả về dạng text: mỗi field cách nhau bởi dấu |. Đây là các API về lịch sử login, và lịch sử giao dịch (Trading history)

public class ConnectOracle {
    private static ConnectOracle connectOracle;

    private ConnectOracle() {

    }

    public static ConnectOracle getInstance() {
        if (connectOracle == null) {
            connectOracle = new ConnectOracle();
        }
        return connectOracle;
    }

    public Connection connection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:@10.240.89.177:1521:hr";
        String username = "hr";
        String password = "hr";

        Connection conn = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        conn = DriverManager.getConnection(url, username, password);
        if (isActive(conn))
            System.out.println("Connected to Oracle Database");
        return conn;
    }

    private boolean isActive(Connection conn) {
        return conn != null;
    }

    public static void main(String[] args) {
        ConnectOracle connectDB = ConnectOracle.getInstance();

        try {
            Connection connection = connectDB.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from employees");
            while (rs.next()) {
                System.out.print("Id: " + rs.getString("employee_id") + " | ");
                System.out.println("Name: " + rs.getString("last_name"));

            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }


    }

}
