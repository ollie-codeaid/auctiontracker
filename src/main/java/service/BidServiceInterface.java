package service;

import java.util.List;

import model.Bid;

public interface BidServiceInterface {

	public void recordBid(long itemID, long userID, float price);
	
	public List<Long> getItemIDsByUser(long userID);
	
	public List<Bid> getBidsByItem(long itemID);
}
