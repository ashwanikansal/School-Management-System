import java.sql.*;

public class Connect {
    public static Connection dbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
