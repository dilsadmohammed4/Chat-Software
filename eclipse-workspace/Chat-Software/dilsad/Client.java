import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import org.w3c.dom.events.EventTarget;

import com.sun.media.sound.Toolkit;

import jdk.nashorn.internal.codegen.CompilerConstants.Call;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Client {

	private JFrame frame;
	private static JTextField msg_text;
	private static JTextArea msg_area;
	
	 static  Socket s;
	 static DataInputStream din;
	 static DataOutputStream dout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		String msginput="";
		try {
			
			Client window = new Client();
			window.frame.setVisible(true);
			s=new Socket("127.0.0.1",1201);
                	din=new DataInputStream(s.getInputStream());
                	dout=new DataOutputStream(s.getOutputStream());
	                while (!msginput.equals("exit")) {msginput=din.readUTF();
                   	msg_area.setText(msg_area.getText()+"\n Dilsad: "+msginput);
					
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 479, 558);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		msg_text = new JTextField();
		msg_text.setBounds(10, 479, 346, 31);
		frame.getContentPane().add(msg_text);
		msg_text.setColumns(10);
		
		JButton msg_send = new JButton("Send");
		msg_send.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					
				
					
					
					
					
				}
			}
		});
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
		msg_send.setBounds(366, 479, 89, 33);
		frame.getContentPane().add(msg_send);
		
		JLabel lblNewLabel = new JLabel("ANKIT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel.setBounds(192, 11, 63, 31);
		frame.getContentPane().add(lblNewLabel);
		
		msg_area = new JTextArea();
		msg_area.setEditable(false);
		msg_area.setBounds(10, 90, 445, 378);
		frame.getContentPane().add(msg_area);
	}
}
