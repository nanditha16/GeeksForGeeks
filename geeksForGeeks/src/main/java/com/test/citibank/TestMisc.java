package com.test.citibank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.yaml.snakeyaml.Yaml;

public class TestMisc {

	public static void main(String[] args) {
		
		// input : spec.yaml
		MethodOneFlatenedFile();
		
		// input : spec_list.yaml - nested LinkedHashMap
		MethodTwoListFile();
	}

	private static void MethodTwoListFile() {
		String specFilePath = "/Users/nanditha/Documents/Github/GeeksForGeeks/geeksForGeeks/src/main/resources/spec_list.yaml";
		Map<Integer, Object> specYMLToMap = getMapObjFromSpecYML(specFilePath);
		System.out.println(specYMLToMap);
		
		
		
	}


	private static void MethodOneFlatenedFile() {
		String specFilePath = "/Users/nanditha/Documents/Github/GeeksForGeeks/geeksForGeeks/src/main/resources/spec.yaml";
		Map<Integer, Object> specYMLToMap = getMapObjFromSpecYML(specFilePath);
		System.out.println(specYMLToMap);
		
		String dataFilePath = "/Users/nanditha/Documents/Github/GeeksForGeeks/geeksForGeeks/src/main/resources/data.txt";
		List<Map<String, Object>> specToDataMap = processDataFile(dataFilePath, specYMLToMap);

		System.out.println("specToDataMap");
		System.out.println(specToDataMap);
		
	}

	/*
	 * input 1 : 35=d,870=1,871=Nand,872=OK,873=2,33333=IN,33333=CA,
	 * input 2 : {35=data, 
	 * 			  870=dat_group.count, 
	 *            871=dat_group.Name, 
	 *            872=dat_group.TravelStatus, 
	 *            873=dat_group.Underlying_group.count, 
	 *            33333=dat_group.Underlying_group.Country_Name}
	 * 
	 * 
	 * output:
	 * [{	data=d,
	 * 		dat_group.1.count=1
	 * 		dat_group.1.Name=Nand, 
	 * 		dat_group.1.TravelStatus=OK, 
	 * 		dat_group.1.Underlying_group.1.count=2, 
	 * 		dat_group.1.Underlying_group.2.Country_Name=IN,  
	 * 		dat_group.1.Underlying_group.3.Country_Name=CA,
	 *  }]
	 */
	private static List<Map<String, Object>> processDataFile(String dataFilePath,Map<Integer, Object> specYMLToMap) {
		List<Map<String, Object>>  result = new ArrayList<Map<String, Object>>();
		List<String> fileFeeds;
		
		try(Stream<String> lines = 
				(Files.newBufferedReader(Paths.get(dataFilePath)).lines())){
			fileFeeds = lines.collect(Collectors.toList());
			
			for(String eachFeed : fileFeeds) {
				System.out.println(eachFeed);
				int count = 1, sub_count = 1;
				Map<String, Object> eachFeedMap = new HashMap<>();
				String[] tagValuePairs = eachFeed.split(",");
				
				for(String tagVal : tagValuePairs) {
					String[] tagValuePair = tagVal.split("=");
					String keyName = searchForTag(specYMLToMap, Integer.parseInt(tagValuePair[0]));
					String[] recurGroup = keyName.split("\\.");
					if(recurGroup.length==2) {
						// group
						System.out.println(recurGroup[0]+"."+count+"."+recurGroup[1]);
						keyName = recurGroup[0]+"."+count+"."+recurGroup[1];
					} else if(recurGroup.length==3) {
						// sub group
						System.out.println(recurGroup[0]+"."+count+"."+recurGroup[1]+"."+sub_count+"."+recurGroup[2]);
						keyName =recurGroup[0]+"."+count+"."+recurGroup[1]+"."+sub_count+"."+recurGroup[2];
						sub_count++;
					}
					eachFeedMap.put(keyName, tagValuePair[1]);
				}
				count++;
				sub_count++;
				result.add(eachFeedMap);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	private static String searchForTag(Map<Integer, Object> specYMLToMap, Integer tag) {
		// single entry
		if(specYMLToMap.containsKey(tag) && specYMLToMap.get(tag) instanceof String ){
			return (String) specYMLToMap.get(tag);
		} 
		return null;
	}

	public static <T> T getNestedValue(Map map, Integer... keys) {
	    Object value = map;

	    for (Integer key : keys) {
	        value = ((Map) value).get(key);
	    }

	    return (T) value;
	}
	
	private static Map<Integer, Object> getMapObjFromSpecYML(String filePath) {
		
		Yaml yaml = new Yaml();
		InputStream inputStream = null;
		try{
			inputStream = new FileInputStream(filePath);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return yaml.load(inputStream);
	}

}
