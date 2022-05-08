import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {
    public static void main(String[] args) throws Exception {

        Connection con = Connect.dbConnect();
        ServerSocket server = new ServerSocket(9000);
        ArrayList<ServerThread> clients = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        int count=0;
        while(true) {
            System.out.println("Server waiting for connection...");
            Socket client = server.accept();
            System.out.println("Connected to Client "+(++count)+"...");
            ServerThread clientThread = new ServerThread(client, con);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }
}
