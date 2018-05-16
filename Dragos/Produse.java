package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.util.*;

/**
 * Servlet implementation class Produse
 */
@WebServlet("/Produse")
public class Produse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8081/WebServices/rest/DbAccess/";
	private final String SUCCESS_RESULT="<result>success</result>";
	private final String PASS = "pass";
	private final String FAIL = "fail";
	
    public Produse() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub

	      this.client = ClientBuilder.newClient();
		   GenericType<String> list = new GenericType<String>() {};
		      String r = client
	         .target(REST_SERVICE_URL + "get")
	         .request(MediaType.APPLICATION_JSON)
	         .get(list);
	      String result = PASS;
	      String salavare="tata";
	      //System.out.println("Test case name: testGetAllUsers, Result: " + r );
	      
	     // response.getWriter().append(age);
	     JSONParser parser = new JSONParser();
	     String firstName="nu e bun";
	      
	      try{
	    	  Object obj = parser.parse(r);

	            JSONObject jsonObject = (JSONObject) obj;

	            //String name = (String) jsonObject.get("name");
	           // response.getWriter().append(name);

	           // int age = (int) jsonObject.get("age");
	           // response.getWriter().append(age);

	            // loop array
	            JSONArray msg = (JSONArray) jsonObject.get("produse");
	            for(int i=0; i<msg.size(); i++)
	            {
	            JSONObject jsonObject1 = (JSONObject) msg.get(i);
	            //response.getWriter().append(((String) jsonObject1.get("denumire"))+"\n");
	            
	            response.getWriter().append("<div class=\""
	            		+ (i%2==0?"lunch ":"dinner ")
	            		+ " menu-restaurant\">\r\n" + 
	            		"            <span class=\"clearfix\">\r\n" + 
	            		"              <a class=\"menu-title\" href=\"#\" data-meal-img=\"assets/img/restaurant/rib.jpg\">"
	            		+  ((String) jsonObject1.get("denumire"))
	            		+ "</a>\r\n" + 
	            		"              <span style=\"left: 166px; right: 44px;\" class=\"menu-line\"></span>\r\n" + 
	            		"              <span class=\"menu-price\">"
	            		+ ((String) jsonObject1.get("pret")) + " RON"
	            		+ "</span>\r\n" + 
	            		"            </span>\r\n" + 
	            		"            <span class=\"menu-subtitle\"> "+((String) jsonObject1.get("producator"))+" </span>\r\n" + 
	            		"          </div>\r\n" + 
	            		"");
	            }
	            /*
	            Iterator<String> iterator = msg.iterator(); 
	            while (iterator.hasNext()) {
	            	JSONObject jsonObject1 = (JSONObject) msg.get(0);
	            	
	            	String name = (String) jsonObject1.get("denumire");
		            response.getWriter().append(name);
	            }
	            */

	         
	       }catch(ParseException pe){
	 		
	          System.out.println("position: " + pe.getPosition());
	          System.out.println(pe);
	       }
	      
		
	}

	
	

}
