import java.sql.*;
import java.net.*;
import java.io.*;
public class Server
{

  public static void main(String[] args){
		try{
			ServerSocket welcomeSocket = new ServerSocket(9999);
			while(true){
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inputReader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream output = new DataOutputStream(connectionSocket.getOutputStream());
				output.writeBytes(getMessage());
				connectionSocket.close();
			}
		} catch (Exception e){

		}
	}

	public static String getMessage() throws Exception{

      // create our mysql database connection
      String myDriver = "org.mariadb.jdbc.Driver";
      String myUrl = "jdbc:mariadb://localhost/mdb";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "smd", "smd123");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select message from messages where id = (select max(id) from messages)");
			rs.next();
			String ret = rs.getString("message");
			stmt.close();
			conn.close();
			return ret;
	}
}
