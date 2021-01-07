import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Server {

	private JFrame frame;
	private static JTextField msg_text;
	private static JTextArea msg_area;

	
	 	static ServerSocket skt;
	    static Socket s;
	    static DataInputStream din;
	    static DataOutputStream dout;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					Server window = new Server();
					window.frame.setVisible(true);			
			}
		});

		String msginput="";
		try {
			
				skt=new ServerSocket(1201);
				s=skt.accept();
				din=new DataInputStream(s.getInputStream());
				dout=new DataOutputStream(s.getOutputStream());
				while (!msginput.equals("exit")) {

				msginput=din.readUTF();
				msg_area.setText(msg_area.getText()+"\n Ankit:"+msginput);
					
				}
				skt.close();
				s.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

	/**
	 * Create the application.
	 */
	public Server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 497, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		msg_text = new JTextField();
		msg_text.setBounds(10, 474, 339, 34);
		frame.getContentPane().add(msg_text);
		msg_text.setColumns(10);
		
		JButton msg_send = new JButton("Send");
		msg_send.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
				

					SwingUtilities.getRootPane(msg_send).setDefaultButton(msg_send);
					
				}
				
			}
		});
		msg_send.setBounds(359, 474, 89, 34);
		msg_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String out="";
					out=msg_text.getText();
					out = out.substring(0,1).toUpperCase() + out.substring(1).toLowerCase();
					msg_area.setText(msg_area.getText()+"\n Me: "+out);
					dout.writeUTF(out);
		            msg_text.setText("");
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
	           
			}
		});
		frame.getContentPane().add(msg_send);
		
		JLabel lblNewLabel = new JLabel("DILSAD");
		lblNewLabel.setBounds(178, 25, 74, 20);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		frame.getContentPane().add(lblNewLabel);
		
		msg_area = new JTextArea();
		msg_area.setEditable(false);
		msg_area.setBounds(10, 85, 463, 378);
		frame.getContentPane().add(msg_area);
	}
}
