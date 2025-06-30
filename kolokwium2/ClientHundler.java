import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class ClientHundler implements Runnable{

    public static ArrayList<ClientHundler> clientHundlers=new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private MyFrame myFrame;

    public ClientHundler(Socket socket)
    {
        try
        {
            this.socket=socket;
            myFrame=new MyFrame(socket);
            this.bufferedWriter=new BufferedWriter( new OutputStreamWriter( socket.getOutputStream()));
            this.bufferedReader=new BufferedReader( new InputStreamReader( socket.getInputStream()));
            this.username=bufferedReader.readLine();
            clientHundlers.add(this);
            broadcastMsg("SERVER:" + username+" CLIENT HAS ENTERED :");

        } catch (IOException e) {
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }
    @Override
    public void run() {
        String messageFromClient;
        while(socket.isConnected())
        {
            try {
                messageFromClient=bufferedReader.readLine();
                broadcastMsg(messageFromClient);
                if(messageFromClient.equalsIgnoreCase("LIST")){
                    onlineOnChat();
                }
            } catch (IOException e) {
                closeEverything(socket,bufferedReader,bufferedWriter);
                break;
            }
        }

    }
    public void broadcastMsg(String msgToSend)
    {
        for(ClientHundler clientHundler:clientHundlers)
        {
            try {
                if(!clientHundler.username.equals(username))
                {
                    clientHundler.bufferedWriter.write(msgToSend);
                    clientHundler.bufferedWriter.newLine();
                    clientHundler.bufferedWriter.flush();
                }
            }
            catch(IOException e)
            {
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }
    public void remove_clientHundler()
    {
        clientHundlers.remove(this);
        broadcastMsg("SERVER: "+username+" HAS LEFT THE CHAT");
    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter)
    {
        remove_clientHundler();
        try {
            if(socket!=null)
            {
                socket.close();
            }
            if(bufferedReader!=null)
            {
                bufferedReader.close();
            }
            if(bufferedWriter!=null)
            {
                bufferedWriter.close();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void onlineOnChat()
    {
        for(ClientHundler clientHundler:clientHundlers)
        {
            if(!clientHundler.username.equals(this.username))
            {
                try {
                   clientHundler.bufferedWriter.write(clientHundler.username);
                   clientHundler.bufferedWriter.newLine();
                   clientHundler.bufferedWriter.flush();
                } catch (IOException e) {
                    closeEverything(socket,bufferedReader,bufferedWriter);
                }
            }
        }
    }
}
