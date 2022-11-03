package TestCases;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import javax.xml.ws.Response;

import org.testng.annotations.Test;

public class TestCase_VIdeoGameAPI {
	

	@Test
	public void test_getAllVideoGames(){
		
		given()
		.when()
		.get("http://localhost:8080/app/videogames")
		
		.then()
		.statusLine("200"); 
	}
		
	  //  @Test(priority = 2)
		public void test_postAllVideoGames(){
			HashMap<String, Comparable> data = new HashMap<String, Comparable>();

			data.put( "id", 100);
			data.put( "name", "Spiderman");
			data.put("releaseDate", "2022-07-26T01:55:21.694Z");
			data.put( "reviewScore", "7");
			data.put( "category", "Adventure");
			data.put( "rating", "Universal");
				
			Response res = 
			(Response) given().contentType("application/json")
			.body(data)
			.when()
			.post("http://localhost:8080/app/videogames")
			
			.then()
			.statusCode(200)
			.log().body()
			.extract().response();
			
			String jsonString = res.toString();           // This should be "asString"  
			
			assertEquals(jsonString.contains("Record Added Successfully"), true);
		}
		
		// @Test(priority = 3)
		public void test_getVideoGame() {
			given()
			.when()
			.get("http://localhost:8080/app/videogames/100")
			
			.then()
			.statusCode(200)
			.log().body()                        // We can see response in the console

			.body("videoGame.id",equalTo("100"))
			.body("videoGame.nmae",equalTo("Spiderman"))
			.body("videoGame.date",equalTo("2022-07-26T01:55:21.694Z"))
			.body("videoGame.reviewscore",equalTo("7"))
			.body("videoGame.id",equalTo("100"));
			
		
			 
		}

		private ResponseAwareMatcher<io.restassured.response.Response> equalTo(String string) {
			return null;  }
		
			
			// @Test(priority = 4)
			public void test_updateVideoGame() {
				
				HashMap<String, Comparable> data = new HashMap<String, Comparable>();
				data.put( "id", 100);
				data.put( "name", "Ironman");
				data.put("releaseDate", "2023-07-26T01:55:21.694Z");
				data.put( "reviewScore", "9");
				data.put( "category", "Adventure");
				data.put( "rating", "Universal");
				
				given().contentType("application/json")
						.body(data)
						.when()
						.put("http://localhost:8080/app/videogames/100")
						
						.then()
						.statusCode(200)
						.log().body()
						.body("videoGame.id",equalTo("100"))
						.body("videoGame.nmae",equalTo("Ironman"));
						}
			
			@Test(priority = 5)
			public void test_DeleteVideoGame() {
				
				Response res = given()      // Why "io.restassured.response"
				.when()
				.get("http://localhost:8080/app/videogames/100")
				
				.then()
				.statusCode(200)
				.log().body()	
				.extract().response();
				
				String jsonString = res.asString();
				assertEquals(jsonString.contains("Record Deleted Successfully"), true);

		}
}
