package test.conduit;

import java.io.StringReader;
import java.util.Random;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ConduitAPITestWithoutAuth {
	private String baseUrl = "https://conduit.productionready.io/api";
	RequestSpecification request;
	String email = "zeroloose@gmail.com";
	String password = "testing123";
	String token;

	@Test
	public void getAllArticles() {
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.get("/articles");
		Assert.assertEquals(response.getStatusCode(), 200);
		String jsonString = response.asString();
		JsonReader reader = Json.createReader(new StringReader(jsonString));
		JsonObject jsonObject = reader.readObject();

		int articlesResult = jsonObject.getJsonArray("articles").size();
		Assert.assertTrue(articlesResult > 0);
		int articleCount = jsonObject.getInt("articlesCount");
		Assert.assertTrue(articlesResult <= articleCount);
		if (articlesResult > 0) {
			String title = jsonObject.getJsonArray("articles").getJsonObject(0).getString("title");
			Assert.assertTrue(title.length() > 0);
		}
	}

	@DataProvider(name = "testDataForLogin")
	public Object[][] loginData() {
		return new Object[][] { { "zeroloose@gmail.com", "testing123", 200, "zeroloose", 108664 },
				{ "zeroloose@gmail.co", "testing123", 422, "", 0 }, { "zeroloose@gmail.com", "testing12", 422, "", 0 },
				{ "", "testing123", 422, "", 0 }, { "zeroloose@gmail.com", "", 422, "", 0 }, { "", "", 422, "", 0 }, };
	}

	@Test(dataProvider = "testDataForLogin")
	public void testLogin(String email, String password, int expectedStatusCode, String expectedUsername,
			int expectedID) {
		// TODO: These parameters of expected can be derived from database
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.body("{\"user\":{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}}")
				.post("/users/login");
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
		if (response.getStatusCode() == 200) {
			String jsonString = response.asString();
			Assert.assertTrue(jsonString.contains("token"));
			Assert.assertTrue(jsonString.contains("user"));
			token = JsonPath.from(jsonString).get("user.token");
			Assert.assertTrue(token.length() > 20);
		}

	}

	@DataProvider(name = "testDataForSignup")
	public Object[][] signupData() {
		return new Object[][] { { "zeroloose@gmail.com", "testing123", 200, "zeroloose" },
				{ "zeroloose", "testing123", 422, "zeroloose" }, { "", "testing123", 422, "zeroloose" },
				{ "zeroloose@gmail.com", "abc", 422, "zeroloose" }, { "zeroloose@gmail.com", "", 422, "zeroloose" },
				{ "", "", 422, "zeroloose" }, { "", "", 422, "" }, };
	}

	@Test(dataProvider = "testDataForSignup")
	public void testSignup(String email, String password, int expectedStatusCode, String username) {
		Random rand = new Random();

		// Generate random integers in range 0 to 999
		int rand_int1 = rand.nextInt(100000);
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		String emailResult = email;
		String usernameResult = rand_int1 + username;
		if (email == "zeroloose@gmail.com" && username == "zeroloose" && password == "testing123") {
			emailResult = rand_int1 + email;
			usernameResult = rand_int1 + username;
		}

		Response response = request.body("{\"user\":{\"email\":\"" + emailResult + "\", \"password\":\"" + password
				+ "\", \"username\":\"" + usernameResult + "\"}}").post("/users");
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
		if (response.getStatusCode() == 200) {
			String jsonString = response.asString();
			Assert.assertTrue(jsonString.contains("token"));
			Assert.assertTrue(jsonString.contains("id"));
			Assert.assertTrue(jsonString.contains("user"));
			token = JsonPath.from(jsonString).get("user.token");
			Assert.assertTrue(token.length() > 20);
		}
	}

	@BeforeClass
	public void beforeClass() {
		RestAssured.baseURI = baseUrl;
	}

}
