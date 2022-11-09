package med_history;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class DocDetails extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9038623593879621726L;
	private JPanel contentPane;
	private JTextField pattxt;
	static Integer Docid;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocDetails frame = new DocDetails(Docid);
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
	public DocDetails(final Integer docId) {
		Docid=docId;
		setTitle("Homepage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(202, 202, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		final String url = "jdbc:sqlite:/C:\\java\\sqlite-tools-win32-x86-3390300\\sqlite-tools-win32-x86-3390300\\med.db";

		try{  
            Connection conn = DriverManager.getConnection(url);
            String query = "SELECT fullname,licenseno FROM doctor_details WHERE doctorid = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,docId);
            ResultSet rs = pstmt.executeQuery();
            
            
            JLabel lblNewLabel = new JLabel("Doctor: "+ rs.getString("fullname") );
            lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
    		lblNewLabel.setBounds(58, 50, 258, 24);
    		contentPane.add(lblNewLabel);
    		
    		Double lno= rs.getDouble("licenseno");
    		JLabel lblLicenseNumber = new JLabel("License Number: "+String.format("%.0f", lno));
    		lblLicenseNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
    		lblLicenseNumber.setBounds(58, 84, 272, 24);
    		contentPane.add(lblLicenseNumber);
            conn.close();
		}
		catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }
		
		
		JLabel lblEnterPatientId = new JLabel("Enter Patient ID");
		lblEnterPatientId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterPatientId.setBounds(58, 146, 110, 24);
		contentPane.add(lblEnterPatientId);
		
		pattxt = new JTextField();
		pattxt.setBounds(165, 149, 96, 19);
		contentPane.add(pattxt);
		pattxt.setColumns(10);
		
		final JButton searchbtn = new JButton("Search");
		searchbtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{  
		            Connection conn = DriverManager.getConnection(url);
		            String query2 = "SELECT patientid FROM patient_details WHERE patientid = ?";
		            PreparedStatement pstmt = conn.prepareStatement(query2);
		            pstmt.setInt(1,Integer.parseInt(pattxt.getText()));
		            ResultSet i=pstmt.executeQuery();
		            
		            if (i.next()) {
                        dispose();
                        DocPatHistory ah = new DocPatHistory(Integer.parseInt(pattxt.getText()),docId);
                        ah.setTitle("Medical History of Patient  "+pattxt.getText());
                        ah.setVisible(true);
                        JOptionPane.showMessageDialog(searchbtn, "Patient account found");
                    }
		            else {
		            	JOptionPane.showMessageDialog(searchbtn, "Patient is incorrect or is not registered ");
		            }
		            conn.close();
				}  
				catch (SQLException f) {  
		            System.out.println(f.getMessage());  
		        } 
				}
		});
		searchbtn.setBounds(128, 218, 85, 21);
		contentPane.add(searchbtn);
	}
}
