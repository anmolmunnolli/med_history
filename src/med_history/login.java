package med_history;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class login extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1016182789120502508L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField usertxt;
	private JPasswordField passtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
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
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(240, 240, 240));
		setTitle("Login");
		setBounds(100, 100, 268, 285);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(202, 202, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 72, 92, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 135, 92, 17);
		contentPane.add(lblNewLabel_1);
		
		usertxt = new JTextField();
		usertxt.setBounds(137, 81, 96, 19);
		contentPane.add(usertxt);
		usertxt.setColumns(10);
		
		passtxt = new JPasswordField();
		passtxt.setBounds(137, 136, 96, 19);
		contentPane.add(passtxt);
		
		final JButton loginbtn = new JButton("Login");
		loginbtn.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String url = "jdbc:sqlite:/C:\\java\\sqlite-tools-win32-x86-3390300\\sqlite-tools-win32-x86-3390300\\med.db";
				
				
				try{  
		            Connection conn = DriverManager.getConnection(url);
		            String query = "SELECT * FROM patient_details WHERE patientid = ? AND ppassword = ?";
		            String query2 = "SELECT * FROM doctor_details WHERE doctorid = ? AND dpassword = ?"; 
		            
		            PreparedStatement pstmt = conn.prepareStatement(query);
		            PreparedStatement pstmt2 = conn.prepareStatement(query2);
		            
		            pstmt.setInt(1,Integer.parseInt(usertxt.getText()));
		            String myPass=String.valueOf(passtxt.getPassword());
		            pstmt.setString(2,myPass);
		            ResultSet i=pstmt.executeQuery();
		            
		            pstmt2.setInt(1,Integer.parseInt(usertxt.getText()));
		            String myPass2=String.valueOf(passtxt.getPassword());
		            pstmt2.setString(2,myPass2);
		            ResultSet j=pstmt2.executeQuery();
		            
		            if (i.next()) {
                        dispose();
                        PatientHistory ah = new PatientHistory(Integer.parseInt(usertxt.getText()));
                        ah.setTitle("Welcome "+usertxt.getText());
                        ah.setVisible(true);
                        JOptionPane.showMessageDialog(loginbtn, "You have successfully logged in");
                    }
		            else if (j.next()) {
                        dispose();
                        DocDetails pg = new DocDetails(Integer.parseInt(usertxt.getText()));
                        pg.setTitle("Welcome Doctor ");
                        pg.setVisible(true);
                        JOptionPane.showMessageDialog(loginbtn, "You have successfully logged in");
                    }
		            else {
                        JOptionPane.showMessageDialog(loginbtn, "Wrong Username & Password");
                    }
		            
		            conn.close();
		        } catch (SQLException f) {  
		            System.out.println(f.getMessage());  
		        }  
				
			
			}
		});
		loginbtn.setBounds(69, 187, 107, 21);
		contentPane.add(loginbtn);
		
		JButton newuserbtn = new JButton("New user?");
		newuserbtn.setFont(new Font("Tahoma", Font.BOLD, 10));
		newuserbtn.setForeground(Color.BLUE);
		
		newuserbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new registerpatient().setVisible(true); 
				dispose();
			}
			
			
		});
		
		
		
		newuserbtn.setBounds(69, 218, 107, 21);
		contentPane.add(newuserbtn);
		
		JLabel heading = new JLabel("Personal Medical History");
		heading.setFont(new Font("Tahoma", Font.BOLD, 14));
		heading.setBounds(40, 26, 187, 26);
		contentPane.add(heading);
		
	}
}
