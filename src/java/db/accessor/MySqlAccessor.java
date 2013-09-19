package db.accessor;

import java.sql.*;

public class MySqlAccessor {
	private Connection conn;
	private String driverClassName;
	private String url;
	private String userName;
	private String password;

	public static void main(String[] args) {

		MySqlAccessor db = new MySqlAccessor();
		db.driverClassName = "com.mysql.jdbc.Driver";
		db.url = "jdbc:mysql://localhost:3306/restaurant";
		db.userName = "root";
		db.password = "dawn00";

		try {
			  Class.forName (db.driverClassName);
			  db.conn = DriverManager.getConnection(db.url, db.userName, db.password);
		}
		catch ( ClassNotFoundException cnf ) {
		   System.err.println("Error: Failed to load JDBC driver." );
		   cnf.printStackTrace();
		   System.exit( 1 );  // terminate program
		}
		catch ( SQLException sql ) {
		   System.err.println( "Error: Unable to connect to database." );
		   sql.printStackTrace();
		   System.exit( 1 );  // terminate program
		}
                
		Statement stmt = null;
		ResultSet rs = null;
                
                //=======================================
                // Do a Query
                //=======================================

                String sql = "SELECT * FROM menu";

		try {
			stmt = db.conn.createStatement();
			rs = stmt.executeQuery(sql);
                        
                        System.out.println("============================");
                        System.out.println("Output from MySQL Server...");
                        System.out.println("============================");
                        
			int count = 0;
			while( rs.next() ) {
                      System.out.println("\nRecord No: " + (count + 1));
				System.out.println( "ID: " + rs.getInt("menu_id") ); // named field
				System.out.println( "Item Name: " + rs.getString("item_name") ); // named field
				System.out.println( "Item Price: " + rs.getString("item_price") );
				count++;
			}
			System.out.println( "==================\n" + count + " records found." );
                        
                //=======================================
                // UPDATE a record
                //=======================================
//                        
//                        int recId = 2; // pick an existing id from your search results
//                        sql = "UPDATE menu SET item_price = '35.95'"
//                                + " WHERE menu_id = " + recId;
//                        
//                        int updateCount = stmt.executeUpdate(sql);
                        
               
                        
		} catch (SQLException sqle) {
			System.out.println(sqle);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// Make sure we close the statement and connection objects no matter what.
			// Since these also throw checked exceptions, we need a nested try-catch
			try {
				stmt.close();
				db.conn.close();
			} catch(Exception e) {
				System.out.println(e);
			}
		}

	}
}
