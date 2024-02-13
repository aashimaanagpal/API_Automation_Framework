package api_Tests;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import api_Endpoints.UserEndPoints;
import api_Payloads.User;
import api_Utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	Logger logger ;
	
	@BeforeClass
	public void setup()
	{
		logger = LogManager.getLogger(this.getClass());
	}
	
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class )
	public void testPostuser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		logger.info("************* Creating Users ***********");
		
		User data=new User();
		
		data.setId(Integer.parseInt(userID));
		data.setUsername(userName);
		data.setFirstName(fname);
		data.setLastName(lname);
		data.setEmail(useremail);
		data.setPassword(pwd);
		data.setPhone(ph);
		
		Response response=UserEndPoints.createUser(data);
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		logger.info("************* Users created ***********");
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
			Response response=UserEndPoints.deleteUser(userName);
			AssertJUnit.assertEquals(response.getStatusCode(),200);	
	
	}
	
	
	
}
