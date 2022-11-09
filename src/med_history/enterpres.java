package med_history;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
public class enterpres extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1591514042673854017L;
	private JPanel contentPane;
	private JTextField billtext;
	static int Patid;
	static int Docid;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					enterpres frame = new enterpres(Patid,Docid);
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
	public enterpres(final Integer patid ,final Integer docid) {
		Patid=patid.intValue();
		Docid=docid.intValue();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(202, 202, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextArea pattxt = new JTextArea();
		pattxt.setBounds(10, 36, 416, 168);
		contentPane.add(pattxt);
		
		JLabel lblNewLabel = new JLabel("Add prescription");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(38, 14, 164, 13);
		contentPane.add(lblNewLabel);
		
		JLabel testlbl = new JLabel("Patient Id:"+Patid+ " Doctor Id: "+Docid);
		testlbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		testlbl.setBounds(193, 13, 233, 13);
		contentPane.add(testlbl);
		
		
		JLabel lblBilltxt = new JLabel("Bill Amt");
		lblBilltxt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBilltxt.setBounds(38, 224, 54, 13);
		contentPane.add(lblBilltxt);
		
		billtext = new JTextField();
		billtext.setBounds(98, 221, 96, 19);
		contentPane.add(billtext);
		billtext.setColumns(10);
		
//		JLabel testlbl = new JLabel("hello");
//		testlbl.setBounds(247, 13, 179, 13);
//		contentPane.add(testlbl);
		
		
		final JButton submitbtn = new JButton("submit");
		submitbtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		submitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Date date = new Date();
			      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
			       final String dateTime = formatter.format(date);
				
			
				final String url = "jdbc:sqlite:/C:\\java\\sqlite-tools-win32-x86-3390300\\sqlite-tools-win32-x86-3390300\\med.db";
				
				try{ 
					
					
					
		            Connection conn = DriverManager.getConnection(url);
//		            String query = "INSERT INTO patient_history values(9,103,'Headache','06-11-22','450')";
		            String query = "INSERT INTO patient_history values(?,?,?,?,?)";
		            PreparedStatement pstmt = conn.prepareStatement(query);
		            
		            pstmt.setInt(1,Patid);
		            pstmt.setInt(2, Docid);
		            pstmt.setString(3,pattxt.getText());
		            pstmt.setString(4, dateTime);
		            pstmt.setString(5, billtext.getText());
		            
		            
		            JOptionPane.showMessageDialog(submitbtn, "New"+Patid+" ,"+Docid+" ,"+pattxt.getText()+" ,"+dateTime+" ,"+billtext.getText());
		            
		            int i=pstmt.executeUpdate();
		            
		            JOptionPane.showMessageDialog(submitbtn, i+"prescription added");
		            

		            new DocPatHistory(Patid, Docid).setVisible(true); 
		            dispose();
		            
		            conn.close();
				
			}
				catch (SQLException h) {  
		            System.out.println(h.getMessage());  
		        }
				
				
			}
			
			
		});
		submitbtn.setBounds(305, 220, 85, 21);
		contentPane.add(submitbtn);
		
		
	}
}
