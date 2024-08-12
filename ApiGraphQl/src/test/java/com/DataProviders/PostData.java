package com.DataProviders;

public class PostData {

	public static String locationData(String locationName,String type,String dimension) {

		return "{\"query\":\"mutation($locationName:String!,$type:String!,$dimension:String!){\\n  \\n  createLocation(location:{name:$locationName,type:$type,dimension:$dimension}){\\n    id\\n  }\\n}\",\"variables\":{\"locationName\":\""+locationName+"\",\"type\":\""+type+"\",\"dimension\":\""+dimension+"\"}}";
	}

	public static String characterData(String characterName,String type,String status,String species,String gender,String image,int originId,int locationId) {

		return "{\"query\":\"mutation($characterName:String!,$type:String!,$status:String!,$species:String!,$gender:String!,,$image:String!,$originId:Int!,$locationId:Int!){\\n  \\n  createCharacter(character:{name:$characterName,type:$type,status:$status,species:$species,gender:$gender,image:$image,originId:$originId,locationId:$locationId}){\\n    \\n    id\\n  }\\n}\",\"variables\":{\"characterName\":\""+characterName+"\",\"type\":\""+type+"\",\"status\":\""+status+"\",\"species\":\""+species+"\",\"gender\":\""+gender+"\",\"image\":\""+image+"\",\"originId\":"+originId+",\"locationId\":"+locationId+"}}";
	}

	public static String episodedata(String episodeName,String air_date, String episodeNo) {

		return "{\"query\":\"mutation ($episodeName: String!, $air_date: String!, $episode: String!) {\\n  createEpisode(episode: {name: $episodeName, air_date: $air_date, episode:  $episode}) {\\n    id\\n  }\\n}\\n\",\"variables\":{\"episodeName\":\""+episodeName+"\",\"air_date\":\""+air_date+"\",\"episode\":\""+episodeNo+"\"}}";
	}
	
	
}
