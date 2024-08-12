package com.testcases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.DataProviders.DelData;
import com.DataProviders.GraphQData;
import com.DataProviders.PostData;
import com.Payloads.Payload;
import com.Payloads.Request.SetData;
import com.Utilites.BaseUtilites;
import com.resources.APIResources;

import io.restassured.response.Response;

public class TestCases2 extends BaseUtilites {

	static int locationId;
	static int characterId;
	static int episodeId;
	static String locationName;
	static String characterName;
	static String episodeName;
	APIResources api;

	
	@Test(dataProvider = "Location_data",dataProviderClass=SetData.class)
	public void createLocation(String locationName, String type, String dimesion) throws IOException {

		api = APIResources.valueOf("createDataAPI");

		Response response = given().spec(requestSpecification())
				.body(PostData.locationData(locationName, type, dimesion)).when().post(api.getResource());

		locationId = getJSONInt(response, "data.createLocation.id");
		TestCases2.locationName=locationName;
	}

	@Test(dataProvider = "Character_data",dependsOnMethods = "createLocation",dataProviderClass=SetData.class)
	public void createCharacter(String characterName, String type,String status,String species,String gender,String image) throws IOException {

		api = APIResources.valueOf("createDataAPI");

		Response response = given().spec(requestSpecification()).body(PostData.characterData(characterName, type,
				status, species, gender, image, locationId, locationId)).when()
				.post(api.getResource());
		characterId = getJSONInt(response, "data.createCharacter.id");	
		TestCases2.characterName=characterName;
	}

	@Test(dataProvider = "Episode_data",dependsOnMethods = "createCharacter",dataProviderClass=SetData.class)
	public void createEpisode(String episodeName, String air_date, String episodeNo) throws IOException {

		api = APIResources.valueOf("createDataAPI");

		Response response = given().spec(requestSpecification())
				.body(PostData.episodedata(episodeName, air_date, episodeNo)).when().post(api.getResource());

		
		episodeId = getJSONInt(response, "data.createEpisode.id");
		TestCases2.episodeName=episodeName;
		
	}

	@Test(dependsOnMethods = "createEpisode")
	public void getGraphQLData() throws IOException {

		api = APIResources.valueOf("getDataAPI");
		Payload data = given().spec(requestSpecification()).body(GraphQData.getData(characterId, locationId, episodeId))
				.when().get(api.getResource()).then().extract().response().as(Payload.class);

		String actCharacterName = data.getData().getCharacter().getName();
		String actEpisodeName = data.getData().getEpisode().getName();
		String actLocationName=data.getData().getLocation().getName();
		int actLocationId = data.getData().getLocation().getId();
		int actEpisodeId = data.getData().getEpisode().getId();
		int actCharacterId = data.getData().getCharacter().getId();

		Assert.assertEquals(actCharacterName, TestCases2.characterName);
		Assert.assertEquals(actEpisodeName, TestCases2.episodeName);
		Assert.assertEquals(actLocationName, TestCases2.locationName);
		Assert.assertEquals(actCharacterId, characterId);
		Assert.assertEquals(actEpisodeId, episodeId);
		Assert.assertEquals(actLocationId, locationId);
	}

	@Test(dependsOnMethods = "getGraphQLData")
	public void deleteData() throws IOException {

		api = APIResources.valueOf("deleteDataAPI");
		int[] characterIds = { characterId };
		int[] episodeIds = { episodeId };
		int[] locationIds = { locationId };

		Response delResponse = given().spec(requestSpecification())
				.body(DelData.deleteData( locationIds[0],characterIds[0], episodeIds[0])).when()
				.post(api.getResource());
		
		//int totalCharacterDeleted = getJSONInt(delResponse, "data.deleteCharacters.charactersDeleted");
		int totalepisodesDeleted = getJSONInt(delResponse, "data.deleteEpisodes.episodesDeleted");
		int totallocationDeleted = getJSONInt(delResponse, "data.deleteLocations.locationsDeleted");

		//Assert.assertEquals(totalCharacterDeleted, characterIds.length);
		Assert.assertEquals(totalepisodesDeleted, episodeIds.length);
		Assert.assertEquals(totallocationDeleted, locationIds.length);


	}
}
