package service;

import java.util.ArrayList;
import java.util.List;

import model.Bid;

public class BidServiceBasic implements BidServiceInterface {

	// using list here because no specification that bids
	// can't be made twice.
	List<Bid> bidList = new ArrayList<Bid>();
	
	@Override
	public void recordBid(long itemID, long userID, float price) {
		bidList.add(new Bid(itemID, userID, price));
	}

	@Override
	public List<Long> getItemIDsByUser(long userID) {

		List<Long> userItemList = new ArrayList<Long>();
		
		for (Bid bid : bidList) {
			if (bid.getUserID() == userID) {
				userItemList.add(bid.getItemID());
			}
		}
		
		return userItemList;
	}

	@Override
	public List<Bid> getBidsByItem(long itemID) {

		List<Bid> itemBidList = new ArrayList<Bid>();
		
		for (Bid bid : bidList) {
			if (bid.getItemID() == itemID) {
				itemBidList.add(bid);
			}
		}
		
		return itemBidList;
	}

}
