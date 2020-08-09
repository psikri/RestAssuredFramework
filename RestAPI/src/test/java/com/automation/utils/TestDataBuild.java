package com.automation.utils;

import java.util.ArrayList;
import java.util.List;

import com.automation.pojo.Location;
import com.automation.pojo.PlacePayload;

public class TestDataBuild {
	
	public PlacePayload addPlace(String name, String language, String address) {
		PlacePayload place = new PlacePayload();
		place.setAccuracy(100);
		place.setAddress(address);
		place.setLanguage(language);
		place.setName(name);
		place.setPhone_number("(+91) 983 893 3937");
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.383494);
		place.setTypes(types);
		place.setWebsite("http://google.com");
		place.setLocation(location);
		return place;
	}
	
	public String deletePlace(String placeId) {
		String payload = "{\r\n" + "    \"place_id\":\"" + placeId + "\"\r\n" + "}\r\n" + "";
		return payload;
	}
	
	
}
