package ppms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * creating class of sqlite connection that gathers all needed methods concerning connection
 * @author f1awksG
 */

public class DBconnect {
     /***
      * creating static connection can be used wherever class 
      * @return
      */
     public static Connection get_connection(){
    	Connection connection=null;
    	 try { 
    		 Class.forName("org.sqlite.JDBC");
    		 connection=DriverManager.getConnection("jdbc:sqlite:datab.db");
    	 	}catch (Exception e) {
    	 		System.err.println("[Communication with server ERROR]"+e);
    	 	}
    	 System.out.println(connection);
    	 return connection;
	}
     
     /***
      * creating a method that generate the result of a query by setting up the connection and statement
      * @param C : connection
      * @param req : setting the string containing the query
      * @return the result set
      */
     public static ResultSet get_result(Connection C, String req) {
    	Statement st;
 		ResultSet rs = null;
 		try {
			st = C.createStatement();
			rs=st.executeQuery(req);
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
 		return rs;
     }
     
     public static void update(Connection C, String username, String newPwd) {
    	 String query = "update user set pwd = \""+newPwd+"\" where un = \""+username+"\"";
         Statement st;
		try {
			st = C.createStatement();
			int count = st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
     }
     
     
     /***
      * method to close connection when it is not null
      * @param C
      */
     public static void Close(Connection C) {
    	 if (C!=null) {
    		 try {
    			 C.close();
    		 }catch( Exception e) {
    			 e.printStackTrace();
    		 }
    	 }
    	 
     }
     
     /***
      * launching the written codes
      * @param args
      */
     public static void main(String[] args) {
        }
}