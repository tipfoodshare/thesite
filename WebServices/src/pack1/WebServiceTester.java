package pack1;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class WebServiceTester  {

   private Client client;
   private String REST_SERVICE_URL = "http://localhost:1234/WebServices/rest/DbAccess/get";
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String PASS = "pass";
   private static final String FAIL = "fail";

   private void init(){
      this.client = ClientBuilder.newClient();
   }

   public static void main(String[] args){
      WebServiceTester tester = new WebServiceTester();
      //initialize the tester
      tester.init();
      //test get all users Web Service Method
      tester.testGetAllUsers();
   }
   //Test: Get list of all users
   //Test: Check if list is not empty
   private void testGetAllUsers(){
	   GenericType<String> list = new GenericType<String>() {};
	      String r = client
         .target(REST_SERVICE_URL)
         .request(MediaType.APPLICATION_JSON)
         .get(list);
      String result = PASS;
      System.out.println("Test case name: testGetAllUsers, Result: " + r );
   }
}