package Day_1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class httpRequest {
	
	int id;
	
	@Test (priority = 1)
	
	void getUsers() 
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
	
	@Test (priority = 2)
	
	void createUser()
	{
//		to create input data
		HashMap data = new HashMap();
		data.put("name", "Umair");
		data.put("job", "SQA");
		
		
		id=given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	@Test (priority = 3, dependsOnMethods = {"createUser"})
	
	void updateUser() {
		HashMap dataUpdate = new HashMap();
		dataUpdate.put("name", "Ali");
		dataUpdate.put("job", "Automation Engineer");
		
		
		given()
			.contentType("application/json")
			.body(dataUpdate)
			
		.when()
			.put("https://reqres.in/api/users/"+id)
			
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test (priority = 4)
	
	void deleteUser() {
		
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+id)
			
		.then()
			.statusCode(204)
			.log().all();
	}

}

