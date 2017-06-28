package service;

import java.util.HashMap;
import java.util.Map;

public class UserServiceBasic implements UserServiceInterface {

	private Map<String, Long> userIDMap = new HashMap<String, Long>();
	private long nextUserID = 1; 
	
	@Override
	public long getUserID(String username) {
	
		if (userIDMap.containsKey(username)) {
			return userIDMap.get(username).longValue();
		}
		
		long userID = getAndIncrementUserID(); 
		userIDMap.put(username, Long.valueOf(userID));
		
		return userID;
	}

	private long getAndIncrementUserID() {
		
		long userID = nextUserID;
		nextUserID++;
		return userID;
	}
}
