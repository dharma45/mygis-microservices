package com.gis.usl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

@RestController
public class Geocoder {

	GeoApiContext context = new GeoApiContext.Builder()
		    .apiKey("AIzaSyB-S86Fk7gDQiUjaXFHyVE-j3VnUnJqm5s")
		    .build();
	
	@RequestMapping(path="/find", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity getMyAddress(@RequestParam("address")String add) throws ApiException, Exception, Throwable{
		
		GeocodingResult[] results =  GeocodingApi.geocode(context, add).await();
		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(results.length> 0)
		{
			//System.out.println(gson.toJson(results[0].addressComponents));
			return ResponseEntity.ok(results[0].geometry.location.toString());
		}
		else 
		{
			return ResponseEntity.ok("");
		}
	}
	
	
}
