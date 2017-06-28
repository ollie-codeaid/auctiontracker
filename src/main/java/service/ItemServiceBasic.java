package service;

import java.util.HashMap;
import java.util.Map;

public class ItemServiceBasic implements ItemServiceInterface {

	private Map<String, Long> itemIDMap = new HashMap<String, Long>();
	private long nextItemID = 1; 
	
	@Override
	public long getItemID(String itemName) {
	
		if (itemIDMap.containsKey(itemName)) {
			return itemIDMap.get(itemName).longValue();
		}
		
		long itemID = getAndIncrementItemID(); 
		itemIDMap.put(itemName, Long.valueOf(itemID));
		
		return itemID;
	}

	private long getAndIncrementItemID() {
		
		long itemID = nextItemID;
		nextItemID++;
		return itemID;
	}
}
