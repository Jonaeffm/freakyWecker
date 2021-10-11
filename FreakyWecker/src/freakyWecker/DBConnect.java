package freakyWecker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//this class connects to the database
public class DBConnect {
	public void connect (dBOptions DBO )
	{
		try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/jonaeffm", "jonaeffm", "jonaeffm")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            
            Statement stmt = conn.createStatement();    
            
            ResultSet rs = stmt.executeQuery( "select * from languages ;" );
            
            while ( rs.next() ) {

                int albumid = rs.getInt("id");

                String  german = rs.getString("german");
                
                String  english = rs.getString("english");

                String  norwegian = rs.getString("norwegian");

                System.out.printf( "Id = %s , german = %s, english = %s, norwegian= %s ", albumid,german, english,norwegian );

                System.out.println();

             }

             rs.close();
            stmt.close();

            
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
	}
	public void getTranslation (dBOptions DBO,int id,String language )
	{
		try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/"+DBO.getdBName(), DBO.getdBUser(), DBO.getdBPassword())) {

            if (conn != null) {
                //System.out.println("Connected to the database!");
            } else {
                //System.out.println("Failed to make connection!");
            }
            
            Statement stmt = conn.createStatement();    
            
            ResultSet rs = stmt.executeQuery( "select "+language+" from languages where id = "+String.valueOf(id)+";" );
            String translation = "";
            while ( rs.next() ) {

               // int albumid = rs.getInt("id");
            	
            	if (language.equals("g"))
            	{
            		translation = rs.getString("g");
            	}else if(language.equals("e"))
            	{
            		translation = rs.getString("e");
            	}
            	else if (language.equals("n"))
            	{
            		translation = rs.getString("n");
            	}
                System.out.printf( "%s",translation );

                System.out.println();

             }

             rs.close();
            stmt.close();

            
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}