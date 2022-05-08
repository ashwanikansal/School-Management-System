import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class MyClient {
    String query;
    Socket client;
    ArrayList<String> rs;
    ObjectInputStream in;
    PrintStream ps;
    MyClient() throws Exception{

        System.out.println("Client created and running...");
        client = new Socket("localhost", 9000);
        System.out.println("Client connected to server...");
        in = new ObjectInputStream(client.getInputStream());
        ps = new PrintStream(client.getOutputStream(), true);
    }

    public static void main(String[] args) throws Exception{
        MyClient mc = new MyClient();
        Main m = new Main(mc);
        m.setVisible(true);
    }

    public boolean teacherlogin(String q){
        try {
            query = q;
            ps.println("teacherLogin " + query);
            rs= (ArrayList<String>) in.readObject();
            return rs != null;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean studentlogin(String q){
        try {
            query = q;
            ps.println("studentLogin " + query);
            rs = (ArrayList<String>) in.readObject();
            return rs != null;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean addTeacher(String q) {
        try {
            query = q;
            ps.println("addTeacher " + query);
            return (Boolean) in.readObject();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean addStudent(String q) {
        try {
            query = q;
            ps.println("addStudent " + query);
            return (Integer) in.readObject() != 0;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }
    public void searchTeacher(String q) throws Exception{
        query = "searchTeacher "+q;
        ps.println(query);
        rs = (ArrayList<String>) in.readObject();
    }
    public void searchStudent(String q) throws Exception{
        query = "searchStudent "+q;
        ps.println(query);
        rs = (ArrayList<String>) in.readObject();
    }
    public boolean deleteTeacher(String q) {
        try {
            query = "deleteTeacher " + q;
            ps.println(query);
            return (Boolean)in.readObject();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean deleteStudent(String q){
        try {
            query = "deleteStudent " + q;
            ps.println(query);
            return (Boolean)in.readObject();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
