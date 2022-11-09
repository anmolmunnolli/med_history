package med_history;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.lang.Math;

public class RegisterDoctor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1328041452744261279L;
	private JPanel contentPane;
	private JTextField nametxt;
	private JTextField licnotxt;
	private JTextField phtxt;
	private JTextField emailtxt;
	private JTextField passtxt;
	private JTextField gentext;
	static Integer Docid;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterDoctor frame = new RegisterDoctor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterDoctor() {
		setTitle("Doctor Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(202, 202, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("full name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(22, 22, 92, 37);
		contentPane.add(lblNewLabel);
		
		JLabel licno = new JLabel("license no");
		licno.setFont(new Font("Tahoma", Font.BOLD, 12));
		licno.setBounds(22, 56, 92, 37);
		contentPane.add(licno);
		
		JLabel phnotxt = new JLabel("phone number");
		phnotxt.setFont(new Font("Tahoma", Font.BOLD, 12));
		phnotxt.setBounds(22, 93, 92, 37);
		contentPane.add(phnotxt);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(22, 126, 92, 37);
		contentPane.add(lblEmail);
		
		JLabel lblSetPassword = new JLabel("set password");
		lblSetPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSetPassword.setBounds(22, 193, 92, 37);
		contentPane.add(lblSetPassword);
		
		nametxt = new JTextField();
		nametxt.setBounds(176, 31, 96, 19);
		contentPane.add(nametxt);
		nametxt.setColumns(10);
		
		licnotxt = new JTextField();
		licnotxt.setBounds(176, 65, 96, 19);
		contentPane.add(licnotxt);
		licnotxt.setColumns(10);
		
		phtxt = new JTextField();
		phtxt.setBounds(176, 102, 96, 19);
		contentPane.add(phtxt);
		phtxt.setColumns(10);
		
		emailtxt = new JTextField();
		emailtxt.setBounds(176, 135, 96, 19);
		contentPane.add(emailtxt);
		emailtxt.setColumns(10);
		
		passtxt = new JTextField();
		passtxt.setBounds(176, 202, 96, 19);
		contentPane.add(passtxt);
		passtxt.setColumns(10);
		
		final JButton registerbtn = new JButton("Register");
		registerbtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		registerbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
String url = "jdbc:sqlite:/C:\\java\\sqlite-tools-win32-x86-3390300\\sqlite-tools-win32-x86-3390300\\med.db";
				
				
				try{  
		            Connection conn = DriverManager.getConnection(url);
		            String query = "INSERT INTO doctor_details values(?,?,?,?,?,?,?)"; 
		            PreparedStatement pstmt = conn.prepareStatement(query);
		            
		            
		            int min = 0;  
		            int max = 5000;  
		            
		            while(true) {
			            
			            Docid = (int)(Math.random()*(max-min+1)+min);
		
			            String query2=" SELECT Doctorid FROM Doctor_details WHERE Doctorid IN (?)";
			            PreparedStatement pstmt2= conn.prepareStatement(query2);
			            pstmt2.setInt(1, Docid);
			            ResultSet i= pstmt2.executeQuery();
			            
			            String query3=" SELECT Patientid FROM Patient_details WHERE Patientid IN (?)";
			            PreparedStatement pstmt3= conn.prepareStatement(query3);
			            pstmt3.setInt(1, Docid);
			            ResultSet j= pstmt3.executeQuery();
			              
			            if(!i.next() && !j.next()) {
			            	break;
			            } 
		            }  
			            
		            
		            pstmt.setInt(1,Docid);
		            pstmt.setString(2,nametxt.getText());
		            pstmt.setDouble(3,Double.parseDouble(phtxt.getText()));
		            pstmt.setString(4,gentext.getText());
		            pstmt.setString(5,emailtxt.getText());
		            pstmt.setString(6,passtxt.getText());
		            pstmt.setDouble(7,Double.parseDouble(licnotxt.getText()));
		            	
		            int i=pstmt.executeUpdate();
		            JOptionPane.showMessageDialog(registerbtn, i+ "registration successful and Doctor Id is "+ Docid);
		            new login().setVisible(true);
		            dispose();
		            conn.close();
		            }
		            
				
		         catch (SQLException g) {  
		            System.out.println(g.getMessage());  
		        }  
				
			}
		});
		registerbtn.setBounds(187, 232, 85, 21);
		contentPane.add(registerbtn);
		
		gentext = new JTextField();
		gentext.setBounds(176, 164, 96, 19);
		contentPane.add(gentext);
		gentext.setColumns(10);
		
		JLabel gentxt = new JLabel("gender");
		gentxt.setFont(new Font("Tahoma", Font.BOLD, 12));
		gentxt.setBounds(22, 170, 45, 13);
		contentPane.add(gentxt);
		
		JButton returnbtn = new JButton("Login");
		returnbtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		returnbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new login().setVisible(true); 
				dispose();
			}
		});
		returnbtn.setBounds(294, 126, 142, 29);
		contentPane.add(returnbtn);
		
		
	}
}
