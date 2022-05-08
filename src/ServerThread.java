import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ServerThread implements Runnable{

    Socket client;
    BufferedReader in;
    ObjectOutputStream out;
    Connection con;
    ArrayList<String> frs = new ArrayList<>();
    public ServerThread(Socket clientSocket, Connection conn) throws Exception{
        client = clientSocket;
        con = conn;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new ObjectOutputStream(client.getOutputStream());
    }
    @Override
    public void run() {
        try {
            while(true){
                String query = in.readLine();
                Statement st = con.createStatement();
                if(query.startsWith("teacherLogin")){
                    query = query.substring(query.indexOf(" ")+1);
                    ResultSet rs = st.executeQuery(query);
                    while(rs.next()){
                        frs.add(rs.getString("teacher_id"));
                        frs.add(rs.getString("first_name"));
                        frs.add(rs.getString("middle_name"));
                        frs.add(rs.getString("last_name"));
                        frs.add(rs.getString("class"));
                        frs.add(rs.getString("subject"));
                        frs.add(rs.getString("designation"));
                    }
                    out.writeObject(frs);
                }
                if(query.startsWith("studentLogin")){
                    query = query.substring(query.indexOf(" ")+1);
                    ResultSet rs = st.executeQuery(query);
                    if(rs.next()){
                        frs.add(rs.getString("student_id"));
                        frs.add(rs.getString("first_name"));
                        frs.add(rs.getString("middle_name"));
                        frs.add(rs.getString("last_name"));
                        frs.add(rs.getString("class"));
                        frs.add(rs.getString("dob"));
                        frs.add(rs.getString("Fees"));
                    }
                    out.writeObject(frs);

                } else if (query.startsWith("addTeacher")) {
                    query = query.substring(query.indexOf(" ")+1);
                    int i = st.executeUpdate(query);
                    out.writeObject(i!=0);

                } else if (query.startsWith("searchTeacher")) {
                    query = query.substring(query.indexOf(" ") + 1);
                    ResultSet rs = st.executeQuery(query);
                    while (rs.next()) {
                        frs.add(rs.getString("teacher_id"));
                        frs.add(rs.getString("first_name"));
                        frs.add(rs.getString("middle_name"));
                        frs.add(rs.getString("last_name"));
                        frs.add(rs.getString("class"));
                        frs.add(rs.getString("dob"));
                        frs.add(rs.getString("subject"));
                        frs.add(rs.getString("designation"));
                    }
                    out.writeObject(frs);
                } else if (query.startsWith("searchStudent")) {
                    query = query.substring(query.indexOf(" ") + 1);
                    ResultSet rs = st.executeQuery(query);
                    while (rs.next()) {
                        frs.add(rs.getString("student_id"));
                        frs.add(rs.getString("first_name"));
                        frs.add(rs.getString("middle_name"));
                        frs.add(rs.getString("last_name"));
                        frs.add(rs.getString("class"));
                        frs.add(rs.getString("dob"));
                    }
                    out.writeObject(frs);
                } else if (query.startsWith("deleteTeacher")) {
                    query = query.substring(query.indexOf(" ") + 1);
                    int i = st.executeUpdate(query);
                    out.writeObject(i!=0);

                } else if (query.startsWith("deleteStudent")) {
                    query = query.substring(query.indexOf(" ") + 1);
                    int i = st.executeUpdate(query);
                    out.writeObject(i!=0);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
