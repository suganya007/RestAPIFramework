package com.test.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelper;
import com.test.models.UserPojo;
import com.test.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EndToEndScript extends UserServiceHelper{
	
	public static String userid;
	public static String id;
	@BeforeMethod
	public static void baseURI()
	{
		RestAssured.baseURI = getBaseURI();
	}
	
	@Test
	public static void TC1_validLogin()
	
	{
		String token = getToken();
		System.out.println("Token is ...... "+token);
		ReusableMethods.verifyStatusCode(resp,201);
	}
	
	@Test
	public static void TC1_invalidLogin()
	
	{
		InvalidLoginToApplication(); 
		System.out.println("Token is ...... "+token);
		ReusableMethods.verifyStatusCode(resp,401);
	}

	@Test
	public static void TC2_getUserData()
	{
		UserPojo[] listOfUsers = getUserData();
		id = listOfUsers[0].getId();
		userid = listOfUsers[0].getUserid();
		ReusableMethods.verifyStatusCode(resp,200);
		String expectedContentType = Utils.getConfigProperty("contentType");
		ReusableMethods.verifyContentType(resp,expectedContentType);
//		resp.body().prettyPrint();
		System.out.println("no. of records : "+listOfUsers.length);
//		System.out.println("userid & id : "+userid+" "+id);
	}
	
	@Test
	public static void TC3_addUserData()
	{
		Response res = addUserData();
		resp.body().prettyPrint();
		ReusableMethods.verifyStatusCode(res,201);
		ReusableMethods.getJsonPathData(res,"success");
	}
	
	@Test
	public static void TC4_updateUserData()
	{
		UserPojo[] listOfUsers = getUserData();
		id = listOfUsers[0].getId();
		userid = listOfUsers[0].getUserid();
		System.out.println("userid & id : "+userid+" "+id);
		Response res = updateUserData(userid,id);
		ReusableMethods.verifyStatusCode(res,200);
		ReusableMethods.getJsonPathData(res,"success");
		System.out.println(listOfUsers[0].getAccountno());
	}
	
	@Test
	public static void TC5_deleteUserData()
	{
		UserPojo[] listOfUsers = getUserData();
		id = listOfUsers[0].getId();
		userid = listOfUsers[0].getUserid();
		System.out.println("userid & id : "+userid+" "+id);
		Response res = deleteUserData(userid,id);
		ReusableMethods.verifyStatusCode(res,200);
		ReusableMethods.getJsonPathData(res,"success");
	}
	

}
