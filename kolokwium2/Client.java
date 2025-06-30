import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private MyFrame myFrame;

    public Client(Socket socket,String username) {
        try{
            this.socket=socket;
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username=username;
        }
        catch(IOException e) {
            closeEverything(socket,bufferedWriter,bufferedReader);
        }

    }
    public void sendMessage() {
        try{
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner scanner=new Scanner(System.in);
            while(socket.isConnected())
            {
                String messageToSend=myFrame.tekst.getText();
                bufferedWriter.write(username+": "+messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch(IOException e){
            closeEverything(socket,bufferedWriter,bufferedReader);
        }
    }
    public void listenFromMessage()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
            while(socket.isConnected())
            {
                try{
                    msgFromGroupChat=bufferedReader.readLine();
                   myFrame.chat.append(msgFromGroupChat);
                }
                catch (IOException e){
                    closeEverything(socket,bufferedWriter,bufferedReader);
                }
            }
            }
        }).start();
    }
    public void closeEverything(Socket socket,BufferedWriter bufferedWriter,BufferedReader bufferedReader)
    {
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

    public static void main(String[] args) throws IOException {


        //Scanner scanner=new Scanner(System.in);
        //System.out.println("ENTER USERNAME: ");
        //String username= scanner.nextLine();

        Socket socket=new Socket("localhost",1709);
        MyFrame myFrame=new MyFrame(socket);
        myFrame.chat.append("ENTER USERNAME: ");
        String username =myFrame.tekst.getText();
        myFrame.chat.append("\nusername "+username);
        Client client=new Client(socket,username);
        client.listenFromMessage();
        client.sendMessage();
    }
}
