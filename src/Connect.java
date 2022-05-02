import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {
    public static void main(String[] args) {

        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing", "root", "");
            Statement pst = (Statement) conn.createStatement();
            ResultSet rs = pst.executeQuery("select * from department order by dname desc");

            while(rs.next()){
                System.out.println(rs.getInt(1)+"  "+rs.getString(2));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
