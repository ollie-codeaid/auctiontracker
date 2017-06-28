package auctiontracker;

import java.util.List;

import model.Bid;

public interface AuctionTrackerInterface {
	
	public void recordBid(String itemName, String username, float price);
	
	public Bid getCurrentWinningBid(String itemName);
	
	public List<Bid> getAllBidsByItemName(String itemName);
	
	public List<Long> getAllItemsByUserID(String username);

}
