package com.DataProviders;

public class DelData {

	public static String deleteData(int characterId, int locationId, int episodeId) {

		return "{\"query\":\"mutation($characterIds:[Int!],$locationIds:[Int!],$episodeIds:[Int!]){\\n  \\n  deleteLocations(locationIds:$locationIds){\\n    locationsDeleted\\n  }\\n  deleteCharacters(characterIds:$characterIds){\\n    charactersDeleted\\n  }\\n  deleteEpisodes(episodeIds:$episodeIds){\\n    episodesDeleted\\n  }\\n}\\n\",\"variables\":{\"characterIds\":"
				+ characterId + ",\"locationIds\":" + locationId + ",\"episodeIds\":" + episodeId + "}}\r\n" + "";
	}
	
	
}
