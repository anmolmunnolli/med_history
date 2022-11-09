package med_history;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
	
	public static void patientDetailsTable() {  
		 
        String url = "jdbc:sqlite:/C:\\java\\sqlite-tools-win32-x86-3390300\\sqlite-tools-win32-x86-3390300\\med.db";  
           
        String sql = "CREATE TABLE IF NOT EXISTS Patient_details (\n"  
        		+ " patientid integer PRIMARY KEY,\n" 
                + " fullname text NOT NULL,\n"  
                + " age integer NOT NULL,\n"
                + " phoneno double NOT NULL,\n"
                + " email text NOT NULL,\n"
                + " gender text NOT NULL,\n"
                + " ppassword text NOT NULL\n"  
                + ");";  
          
        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
	
	
	
	public static void doctorDetailsTable() {  
		 
        String url = "jdbc:sqlite:/C:\\java\\sqlite-tools-win32-x86-3390300\\sqlite-tools-win32-x86-3390300\\med.db";  
           
        String sql = "CREATE TABLE IF NOT EXISTS Doctor_details (\n"  
        		+ " doctorid integer PRIMARY KEY,\n" 
                + " fullname text NOT NULL,\n"  
                + " phoneno double NOT NULL,\n"
                + " gender text NOT NULL,\n"
                + " email text NOT NULL,\n" 
                + " dpassword text NOT NULL,\n"
                + " licenseno double NOT NULL\n"  
                + ");"; 
          
        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }
	
	
	
	public static void patientHistoryTable() {  
		 
        String url = "jdbc:sqlite:/C:\\java\\sqlite-tools-win32-x86-3390300\\sqlite-tools-win32-x86-3390300\\med.db";  
           
        String sql = "CREATE TABLE IF NOT EXISTS Patient_history (\n"  
        		+ " patientid integer,\n"
        		+ " doctorid integer NOT NULL,\n"
                + " prescription text,\n"  
                + " date text ,\n"
                + " bill text ,\n"
                + " FOREIGN KEY(patientid) REFERENCES Patient_details(patientid)ON DELETE CASCADE \r\n"
//                + "      ON UPDATE NO ACTION,\n"
                + " FOREIGN KEY(doctorid) REFERENCES Doctor_details(doctorid)ON DELETE CASCADE \r\n"
//                + "      ON UPDATE NO ACTION\n"
                + ");";  
          
        try{  
            Connection conn = DriverManager.getConnection(url);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }



public static void main(String[] args) {
	
	patientDetailsTable();
	doctorDetailsTable();
	patientHistoryTable();
}

}
