import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class MyFrame extends JFrame implements ActionListener {
    JTextArea chat;
    JTextField tekst;
    JButton button;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Socket socket1;

    public MyFrame(Socket socket)
    {
        this.socket1=socket;
        try
        {
            this.socket=socket;
            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,500));
        this.setLayout(new FlowLayout());
        button=new JButton("SUBMIT");
        chat=new JTextArea();
        chat.setPreferredSize(new Dimension(400,200));

        button.setSize(new Dimension(250,40));
        tekst=new JTextField();
        tekst.setPreferredSize(new Dimension(250,40));
        button.addActionListener(this);
        this.add(chat);
        this.add(tekst);
        this.add(button);
        this.pack();
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button)
        {
            //System.out.println(tekst.getText());

            chat.append(tekst.getText());
            chat.append("\n");
            tekst.setText("");
            try {
                bufferedWriter.write(tekst.getText());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
