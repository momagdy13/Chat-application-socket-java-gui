import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame {
    JPanel server;
    JTextArea server_area;
    JTextField server_text;
    JButton sendButton;
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream dis;
    static DataOutputStream dos;

    public Server() {
        setContentPane(server);
        setTitle("Server");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(600, 500);


        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String mo = "";
                    mo = server_text.getText().trim();
                    dos.writeUTF(mo);
                    dos.flush();
                    server_area.append("\nServer: " + mo);
                    server_text.setText("");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        Start();


    }

    private void Start() {
        String msi = "";

        try {
            serverSocket = new ServerSocket(1201);
            Socket socket = serverSocket.accept();
            System.out.println("sever is connected");
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            while (!msi.equals("Exit")) {

                msi = dis.readUTF();
                server_area.setText(server_area.getText().trim() + "\nClient: \t " + msi);

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
