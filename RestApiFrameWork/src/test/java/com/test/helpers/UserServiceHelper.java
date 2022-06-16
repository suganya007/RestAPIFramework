package com.test.helpers;


import java.util.List;

import org.jsoup.select.Evaluator.Matches;

import com.test.constants.EndPoints;
import com.test.models.AddUserPojo;
import com.test.models.DeleteUserPojo;
import com.test.models.LoginPojo;
import com.test.models.UpdatePojo;
import com.test.models.UserPojo;
import com.test.utils.Utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class UserServiceHelper {
	
	public static Response resp;
	public static String token;
	public static AddUserPojo add = new AddUserPojo();
	public static UpdatePojo update = new UpdatePojo();
	public static DeleteUserPojo delete = new DeleteUserPojo();

	
	public static String getBaseURI()
	{
		String baseURI = Utils.getConfigProperty("baseURI");
		return baseURI;
	}
	
	public static String getToken()
	{
		resp = LoginToApplication();
		token = resp.jsonPath().get("[0].token");
		return token;
	}
	
	public static Response LoginToApplication()
	{
		String username = Utils.getConfigProperty("username");
		String password = Utils.getConfigProperty("password");
		LoginPojo obj = new LoginPojo(username,password);
		resp = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(obj)
				.log().all()
				.when()
				.post(EndPoints.LOGIN);
		
		System.out.println("Resp token in logintoapp is "+resp.jsonPath().get("[0].token"));
		return resp;
	}
	
	public static Response InvalidLoginToApplication()
	{
		String username = Utils.getConfigProperty("username");
		String password = Utils.getConfigProperty("invalidpassword");
		LoginPojo obj = new LoginPojo(username,password);
		resp = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(obj)
				.log().all()
				.when()
				.post(EndPoints.LOGIN);
		
		System.out.println("Resp token in logintoapp is "+resp.jsonPath().get("[0].token"));
		return resp;
	}

	public static UserPojo[] getUserData()
	{
		getToken();
		Header header = new Header("token",token);
		System.out.println("Extracted token is "+token);
		resp = RestAssured.given()
			.contentType(ContentType.JSON)
			.header(header)
			.when()
			.get(EndPoints.GETUSERDATA);
		UserPojo[] list = resp.as(UserPojo[].class);
		return list;
	}
	
	public static Response addUserData()
	{
		getToken();
		add = ReusableMethods.addUserDataPojo();
		Header header = new Header("token",token);
		System.out.println("Extracted token is "+token);
		resp = RestAssured.given()
			.contentType(ContentType.JSON)
			.header(header)
			.body(add)
			.when()
			.post(EndPoints.ADDUSERDATA);
		return resp;
	}

	public static Response updateUserData(String userid, String id)
	{
//		UserPojo[] listOfUsers = getUserData();
//		System.out.println(listOfUsers[0].getUserid());		
//		String userid = listOfUsers[0].getUserid();
//		String id = listOfUsers.get(0).getId();
		update = ReusableMethods.updateUserDataPojo(userid,id);
		Header header = new Header("token",token);
		System.out.println("Extracted token is "+token);
		resp = RestAssured.given()
			.contentType(ContentType.JSON)
			.header(header)
			.body(update)
			.when()
			.put(EndPoints.UPDATEUSERDATA);
		return resp;
	}
	
	public static Response deleteUserData(String userid, String id)
	{
//		UserPojo[] listOfUsers = getUserData();
//		System.out.println(listOfUsers[0].getUserid());		
//		String userid = listOfUsers[0].getUserid();
//		String id = listOfUsers.get(0).getId();
		delete = ReusableMethods.deleteUserDataPojo(userid,id);
		Header header = new Header("token",token);
		System.out.println("Extracted token is "+token);
		resp = RestAssured.given()
			.contentType(ContentType.JSON)
			.header(header)
			.body(delete)
			.when()
			.delete(EndPoints.DELETEUSERDATA);
		return resp;
	}	
}
