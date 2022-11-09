package med_history;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbcreate {
	public static void createNewDatabase(String fileName) {  
		   
        String url = "jdbc:sqlite:/C:\\java\\sqlite-tools-win32-x86-3390300\\sqlite-tools-win32-x86-3390300\\" + fileName;  
   
        try {  
            Connection conn = DriverManager.getConnection(url);  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();  
                System.out.println("Driver used is " + meta.getDriverName());  
                System.out.println("Med Database created");  
            }  
   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
    }  
	
	
	
	public static void main(String[] args) {
		createNewDatabase("med.db");  

	}
}
