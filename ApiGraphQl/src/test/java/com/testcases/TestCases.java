package com.testcases;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.DataProviders.GraphQData;
import com.DataProviders.TestDataProvider;
import com.Payloads.Payload;
import com.Utilites.BaseUtilites;
import com.resources.APIResources;

import io.restassured.response.Response;

public class TestCases extends BaseUtilites {

	int characterId;
	int episodeId;
	APIResources api;

	@Test(dataProvider ="ExcelFile", dataProviderClass=TestDataProvider.class)
	public void mainTest(String characterName, String episodeName) throws IOException {

		createData(characterName, episodeName);
		getData(characterName, episodeName);
		deleteData();
	}

	public void createData(String characterName, String episodeName) throws IOException {

		api = APIResources.valueOf("createDataAPI");

		Response response = given().spec(requestSpecification()).body(GraphQData.postData(characterName, episodeName))
				.when().post(api.getResource());

		characterId = getJSONInt(response, "data.createCharacter.id");
		episodeId = getJSONInt(response, "data.createEpisode.id");

	}

	public void getData(String characterName, String episodeName) throws IOException {

		api = APIResources.valueOf("getDataAPI");
		Payload data = given().spec(requestSpecification()).body(GraphQData.getData(characterId, 1715, episodeId)).when()
				.get(api.getResource()).then().extract().response().as(Payload.class);

		String actCharacterName = data.getData().getCharacter().getName();
		String actEpisodeName = data.getData().getEpisode().getName();
		int actEpisodeId = data.getData().getEpisode().getId();
		int actCharacterId = data.getData().getCharacter().getId();

		Assert.assertEquals(actCharacterName, characterName);
		Assert.assertEquals(actEpisodeName, episodeName);
		Assert.assertEquals(actCharacterId, characterId);
		Assert.assertEquals(actEpisodeId, episodeId);
	}

	public void deleteData() throws IOException {

		api = APIResources.valueOf("deleteDataAPI");
		int[] characterIds = { characterId };
		int[] episodeIds = { episodeId };

		Response delResponse = given().spec(requestSpecification())
				.body(GraphQData.deleteData(characterIds[0], episodeIds[0])).when().post(api.getResource());

		int totalCharacterIds = getJSONInt(delResponse, "data.deleteCharacters.charactersDeleted");
		int totalepisodeIds = getJSONInt(delResponse, "data.deleteEpisodes.episodesDeleted");

		Assert.assertEquals(totalCharacterIds, characterIds.length);
		Assert.assertEquals(totalepisodeIds, episodeIds.length);

	}
}
