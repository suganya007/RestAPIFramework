package com.test.helpers;

import org.testng.Assert;

import com.test.models.AddUserPojo;
import com.test.models.DeleteUserPojo;
import com.test.models.UpdatePojo;

import io.restassured.response.Response;

public class ReusableMethods {
	static AddUserPojo add;
	static UpdatePojo update;
	static DeleteUserPojo delete;
	
	public static AddUserPojo addUserDataPojo()
	{
		add = new AddUserPojo();
		add.setAccountno("TA-0000991");
		add.setDepartmentno("9");
		add.setPincode("600122");
		add.setSalary("75000");
		return add;
	}
	public static UpdatePojo updateUserDataPojo(String userid , String id)
	{
		update = new UpdatePojo();
		update.setUserid(userid);
		update.setId(id);
		update.setAccountno("TA-9999991");
		update.setDepartmentno(8);
		update.setPincode(600122);
		update.setSalary(95000);
		return update;
	}
	
	
	public static DeleteUserPojo deleteUserDataPojo(String userid , String id)
	{
		delete = new DeleteUserPojo();
		delete.setUserid(userid);
		delete.setId(id);
		return delete;
	}
		
	public static void verifyStatusCode(Response res, int statusCode)
	{
		int actualstatusCode = res.getStatusCode();
		Assert.assertEquals(actualstatusCode, statusCode);
	}
	
	public static void getJsonPathData(Response res, String str)
	{
		String actualStatus = res.jsonPath().get("status");
		Assert.assertEquals(actualStatus, str);
	}
	
	public static void verifyContentType(Response res, String contentType)
	{
		String actualcontentType = res.getContentType();
		Assert.assertEquals(actualcontentType, contentType);
	}

}
