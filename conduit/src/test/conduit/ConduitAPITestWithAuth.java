package test.conduit;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ConduitAPITestWithAuth {
	private String baseUrl = "https://conduit.productionready.io/api";
	RequestSpecification request;
	String email = "zeroloose@gmail.com";
	String password = "testing123";
	String token;

	@DataProvider(name = "testDataForUserAPIWithValidToken")
	public Object[][] getUserDataForValidToken() {
		return new Object[][] { { 200, "zeroloose", 108664 }, };
	}

	@DataProvider(name = "testDataForUserAPIWithInValidToken")
	public Object[][] getUserDataForInvalidToken() {
		return new Object[][] { { 401 }, };
	}

	@BeforeClass
	public void beforeClass() {
		RestAssured.baseURI = baseUrl;
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request
				.body("{\"user\":{\"email\":\"" + "zeroloose@gmail.com" + "\", \"password\":\"" + "testing123" + "\"}}")
				.post("/users/login");
		Assert.assertEquals(response.getStatusCode(), 200);
		if (response.getStatusCode() == 200) {
			String jsonString = response.asString();
			Assert.assertTrue(jsonString.contains("token"));
			Assert.assertTrue(jsonString.contains("user"));
			token = JsonPath.from(jsonString).get("user.token");
			Assert.assertTrue(token.length() > 20);
		}
	}

	@Test(dataProvider = "testDataForUserAPIWithValidToken")
	public void getUserWithValidToken(int statusCode, String expectedUsername, int expectedID)
			throws InterruptedException {
		// TODO: These parameters of expected can be derived from database
		request = RestAssured.given();
		request.header("Authorization", "Token " + token).header("Content-Type", "application/json");
		Response response = request.get("/user");
		Assert.assertEquals(response.getStatusCode(), statusCode);
		String jsonString = response.asString();
		Assert.assertTrue(jsonString.contains("token"));
		Assert.assertTrue(jsonString.contains("user"));
		String token = JsonPath.from(jsonString).get("user.token");
		Assert.assertTrue(token.length() > 20);
		int id = JsonPath.from(jsonString).get("user.id");
		Assert.assertEquals(id, expectedID);
		String username = JsonPath.from(jsonString).get("user.username");
		Assert.assertEquals(username, expectedUsername);

	}

	@Test(dataProvider = "testDataForUserAPIWithInValidToken")
	public void getUserWithInValidToken(int statusCode) throws InterruptedException {
		// TODO: These parameters of expected can be derived from database
		request = RestAssured.given();
		request.header("Authorization", "Token " + "ABC").header("Content-Type", "application/json");
		Response response = request.get("/user");

		Assert.assertEquals(response.getStatusCode(), statusCode);

	}

}
