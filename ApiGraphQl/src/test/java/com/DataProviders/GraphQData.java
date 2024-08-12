package com.DataProviders;

public class GraphQData {

	public static String getData(int characterId, int locationId, int episodeId) {

		return "{\"query\":\"query ($characterId: Int!, $locationId: Int!, $episodeId: Int!) {\\n  character(characterId: $characterId) {\\n    name\\n    type\\n    id\\n    status\\n    gender\\n  }\\n  location(locationId: $locationId) {\\n    id\\n    name\\n    type\\n    dimension\\n    created\\n  }\\n  episode(episodeId: $episodeId) {\\n    name\\n    id\\n    air_date\\n    episode\\n  }\\n}\",\"variables\":{\"characterId\":"
				+ characterId + ",\"locationId\":" + locationId + ",\"episodeId\":" + episodeId + "}}";
	}

	public static String postData(String characterName, String episodeName) {

		return "{\"query\":\"mutation($characterName:String!,$episodeName:String!){\\n  \\n\\n  \\n  createCharacter(character:{name:$characterName,type:\\\"Royal\\\",status:\\\"Alive\\\",species:\\\"Human\\\",gender:\\\"Male\\\",image:\\\"jpeg\\\",originId:1715,locationId:1715}){\\n    id\\n  }\\n  createEpisode(episode:{name:$episodeName,air_date:\\\"2014_Dec\\\",episode:\\\"No:2\\\"}){\\n    id\\n  }\\n  \\n  \\n}\",\"variables\":{\"characterName\":\""
				+ characterName + "\",\"episodeName\":\"" + episodeName + "\"}}";
	}

	public static String deleteData(int characterIds, int episodeIds) {

		return "{\"query\":\"mutation($characterIds:[Int!],$episodeIds:[Int!]){\\n  \\n \\n  deleteCharacters(characterIds:$characterIds){\\n    charactersDeleted\\n  }\\n  deleteEpisodes(episodeIds:$episodeIds){\\n    episodesDeleted\\n  }\\n}\",\"variables\":{\"characterIds\":"
				+ characterIds + ",\"episodeIds\":" + episodeIds + "}}";
	}
}
