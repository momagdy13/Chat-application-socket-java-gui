import javax.swing.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client extends JFrame {
    JPanel client;
    JTextArea client_area;
    JTextField client_text;
    JButton sendButton;
    static Socket socket;
    static DataInputStream dis;
    static DataOutputStream dos;

    public static void main(String[] args) {
       new Client();
    }
    public Client() {
        setContentPane(client);
        setTitle("Client");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(600, 500);


        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String st = client_text.getText();
                String mo = "";
                try {
                    mo = client_text.getText().trim();
                    dos.writeUTF(mo);
                    dos.flush();
                    client_area.append("\nClient: "+mo);
                    client_text.setText("");

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

            socket = new Socket("localhost", 1201);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            while (!msi.equals("Exit")) {

                msi = dis.readUTF();
                client_area.setText(client_area.getText().trim() + "\nServer: \t" + msi);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
