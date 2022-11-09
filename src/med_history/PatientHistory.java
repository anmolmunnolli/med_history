package med_history;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class PatientHistory extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2703571911416627187L;
	static Integer Patid;
	private JTable table;
	/**
	 * 
	 */

//	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientHistory frame = new PatientHistory(Patid);
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
	public PatientHistory(final Integer patId ) {
		setBackground(new Color(202, 202, 255));
		Patid=patId;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 416, 208);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		
final String url = "jdbc:sqlite:/C:\\java\\sqlite-tools-win32-x86-3390300\\sqlite-tools-win32-x86-3390300\\med.db";
		
		
		try{  
            Connection conn = DriverManager.getConnection(url);

            String query = "SELECT * FROM Patient_history WHERE patientid=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,Patid);
            ResultSet rs=pstmt.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            int cols=rsmd.getColumnCount();
            String[] colName=new String[cols];
            for(int i=0;i<cols;i++) {
            	colName[i]=rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);
            String patientid,doctorid,pres,date,bill;
            while(rs.next()) {
            	patientid=rs.getString(1);
            	doctorid=rs.getString(2);
            	pres=rs.getString(3);
            	date=rs.getString(4);
            	bill=rs.getString(5);
            	String[] row= {patientid,doctorid,pres,date,bill};
            	model.addRow(row);
            }
            conn.close();
		}  
		catch (SQLException f) {  
            System.out.println(f.getMessage());  
        }

		scrollPane.setViewportView(table);
	}

}




















/*

package med_history;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;

public class DocPatHistory extends JFrame {

	private static final long serialVersionUID = 9002305904452848630L;
	static Integer Patid;
	static Integer Docid;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocPatHistory frame = new DocPatHistory(Patid,Docid);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	
	public DocPatHistory(final Integer patid,final Integer docid) {
		Patid=patid;
		Docid=docid;
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JButton presbtn = new JButton("Add Precription");
		presbtn.setBounds(0, 0, 436, 21);
		

		presbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new enterpres(patid,docid).setVisible(true); 
				dispose();
			}
		});
		getContentPane().setLayout(null);
		
		
		getContentPane().add(presbtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 416, 208);
		getContentPane().add(scrollPane);
		
		table = new JTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"doctorid", "Prescription", "Date", "Bill"
//			}
//		));
final String url = "jdbc:sqlite:/C:\\java\\sqlite-tools-win32-x86-3390300\\sqlite-tools-win32-x86-3390300\\med.db";
		
		
		try{  
            Connection conn = DriverManager.getConnection(url);

            String query = "SELECT * FROM Patient_history WHERE patientid=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,Patid);
            ResultSet rs=pstmt.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            int cols=rsmd.getColumnCount();
            String[] colName=new String[cols];
            for(int i=0;i<cols;i++) {
            	colName[i]=rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);
            String patientid,doctorid,pres,date,bill;
            while(rs.next()) {
            	patientid=rs.getString(1);
            	doctorid=rs.getString(2);
            	pres=rs.getString(3);
            	date=rs.getString(4);
            	bill=rs.getString(5);
            	String[] row= {patientid,doctorid,pres,date,bill};
            	model.addRow(row);
            }
            conn.close();
		}  
		catch (SQLException f) {  
            System.out.println(f.getMessage());  
        }

		scrollPane.setViewportView(table);
		
	}
}
*/



