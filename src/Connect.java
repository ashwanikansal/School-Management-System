import java.sql.*;

public class Connect {
 //Connection con = null;
   // PreparedStatement pst;
    public static Connection dbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/temp", "root", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
