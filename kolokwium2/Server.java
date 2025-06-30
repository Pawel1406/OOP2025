import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    MyFrame myFrame1;
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer()
    {
        try
        {
            while(!serverSocket.isClosed())
            {
                Socket socket=serverSocket.accept();
                myFrame1.chat.append("New client has connected");
                ClientHundler clientHandler=new ClientHundler(socket);
                Thread thread=new Thread(clientHandler);
                thread.start();

            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void closeServerSocket()
    {
        try
        {
            if(serverSocket!=null)
            {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket=new ServerSocket(1709);
        MyFrame myFrame1=new MyFrame(serverSocket.accept());

        Server server=new Server(serverSocket);
        server.startServer();
    }
}
