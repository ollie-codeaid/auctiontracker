package auctiontracker;

import java.util.List;

import model.Bid;
import service.BidServiceBasic;
import service.BidServiceInterface;
import service.ItemServiceBasic;
import service.ItemServiceInterface;
import service.UserServiceBasic;
import service.UserServiceInterface;

public class AuctionTrackerBasic implements AuctionTrackerInterface {
	
	private final BidServiceInterface bidService;
	private final UserServiceInterface userService;
	private final ItemServiceInterface itemService;
	
	public AuctionTrackerBasic(BidServiceBasic bidService,
			UserServiceBasic userService,
			ItemServiceBasic itemService) {
		this.bidService = bidService;
		this.userService = userService;
		this.itemService = itemService;
	}

	@Override
	public void recordBid(String itemName, String username, float price) {

		long itemID = itemService.getItemID(itemName);
		long userID = userService.getUserID(username);
		
		bidService.recordBid(itemID, userID, price);
	}
	
	@Override
	public Bid getCurrentWinningBid(String itemName) {
		
		long itemID = itemService.getItemID(itemName);
		
		Bid highestBid = null;
		List<Bid> bids = bidService.getBidsByItem(itemID);
		for (Bid bid : bids) {
			if (highestBid != null) {
				if (highestBid.getPrice() < bid.getPrice()) {
					highestBid = bid;
				}
			} else {
				highestBid = bid;
			}
		}
		
		return highestBid;
	}

	@Override
	public List<Bid> getAllBidsByItemName(String itemName) {
		
		long itemID = itemService.getItemID(itemName);
		return bidService.getBidsByItem(itemID);
	}

	@Override
	public List<Long> getAllItemsByUserID(String username) {
		
		long userID = userService.getUserID(username);
		return bidService.getItemIDsByUser(userID);
	}

}
