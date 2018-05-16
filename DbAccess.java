package pack1;

import java.io.IOException;
import java.sql.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/DbAccess") 

public class DbAccess {   

	private static final String SUCCESS_RESULT = "{\"result\": \"success\"}";
	private static final String FAILURE_RESULT = "{\"result\": \"failure\"}";
	   
	@GET 
	@Path("/get") 
	@Produces(MediaType.APPLICATION_JSON) 
	public String getProducts(){ 
		String result = "{\"produse\": [";
	
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tip","root","");
	        String sql = "select * from produse";
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(sql);

	    	while(rs.next())
	    	{
	    		result += "{\"id\": \"" + rs.getString(1) + "\",";
	    		result += "\"denumire\": \"" + rs.getString(2) + "\",";
	    		result += "\"pret\": \"" + rs.getString(3) + "\",";
	    		result += "\"producator\": \"" + rs.getString(4) + "\"}";
	    		if(rs.isLast() == false)
	    			result += ",";
	    	}
			result += "]}";
	    	
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return result;
	}
	
   }
	
	