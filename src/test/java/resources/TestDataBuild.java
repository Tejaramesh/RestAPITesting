package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayLoad(String name, String language, String address)
	
	{

		AddPlace pl=new AddPlace();
		pl.setAccuracy(50);
		pl.setAddress(address);
		pl.setLanguage(language);
		pl.setName(name);
		pl.setPhone_number("(+91) 983 893 3937");
		pl.setWebsite("http://google.com");
		List<String> mytypes=new ArrayList<String>();
		mytypes.add("shoe park");
		mytypes.add("shop");
		pl.setTypes(mytypes);
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		pl.setLocation(l);
		return pl;
	}
	
	public String deletePayload(String placeId)
	{
		return "{\r\n  \"place_id\": \""+placeId+"\"\r\n}";
	}

}
