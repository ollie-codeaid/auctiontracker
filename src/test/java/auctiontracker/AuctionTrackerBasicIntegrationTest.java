package auctiontracker;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import auctiontracker.AuctionTrackerBasic;
import model.Bid;
import service.BidServiceBasic;
import service.ItemServiceBasic;
import service.UserServiceBasic;

public class AuctionTrackerBasicIntegrationTest {

	private final String usernameOne = "userOne";
	private final String usernameTwo = "userTwo";
	
	private final String itemNameOne = "itemOne";
	private final String itemNameTwo = "itemTwo";
		
	private final BidServiceBasic bidService = new BidServiceBasic();
	private final UserServiceBasic userService = new UserServiceBasic();
	private final ItemServiceBasic itemService = new ItemServiceBasic();

	private final AuctionTrackerInterface auctionTracker = 
			new AuctionTrackerBasic(bidService, userService, itemService);

	@Before
	public void setUp() {
		
		auctionTracker.recordBid(itemNameOne, usernameOne, 1.0f);
		auctionTracker.recordBid(itemNameOne, usernameTwo, 2.0f);
		auctionTracker.recordBid(itemNameOne, usernameOne, 3.0f);

		auctionTracker.recordBid(itemNameTwo, usernameOne, 7.0f);
		auctionTracker.recordBid(itemNameTwo, usernameTwo, 8.0f);
		auctionTracker.recordBid(itemNameTwo, usernameOne, 1.0f);
	}
	
	@Test
	public void highestBidsMatchExpectations() {
		
		Bid highestBidOne = auctionTracker.getCurrentWinningBid(itemNameOne);
		Bid highestBidTwo = auctionTracker.getCurrentWinningBid(itemNameTwo);
		
		assertEquals("Highest bid one item incorrect", 
				itemService.getItemID(itemNameOne), highestBidOne.getItemID());
		assertEquals("Highest bid one user incorrect",
				userService.getUserID(usernameOne), highestBidOne.getUserID());
		assertTrue("Highest bid one price incorrect", 3.0f == highestBidOne.getPrice());

		assertEquals("Highest bid two item incorrect", 
				itemService.getItemID(itemNameTwo), highestBidTwo.getItemID());
		assertEquals("Highest bid two user incorrect", 
				userService.getUserID(usernameTwo), highestBidTwo.getUserID());
		assertTrue("Highest bid two price incorrect", 8.0f == highestBidTwo.getPrice());
	}
	
	@Test
	public void bidsByItemMatchExpectations() {
		
		List<Bid> itemOneBids = auctionTracker.getAllBidsByItemName(itemNameOne);
		List<Bid> itemTwoBids = auctionTracker.getAllBidsByItemName(itemNameTwo);
		
		String errorOne = "Item one missing bid";
		assertEquals("Item one incorrect bid numbers", itemOneBids.size(), 3);
		assertBidListContainsBid(errorOne, itemOneBids, createBid(itemNameOne, usernameOne, 1.0f));
		assertBidListContainsBid(errorOne, itemOneBids, createBid(itemNameOne, usernameTwo, 2.0f));
		assertBidListContainsBid(errorOne, itemOneBids, createBid(itemNameOne, usernameOne, 3.0f));

		String errorTwo = "Item two missing bid";
		assertEquals("Item two incorrect bid numbers", itemOneBids.size(), 3);
		assertBidListContainsBid(errorTwo, itemTwoBids, createBid(itemNameTwo, usernameOne, 7.0f));
		assertBidListContainsBid(errorTwo, itemTwoBids, createBid(itemNameTwo, usernameTwo, 8.0f));
		assertBidListContainsBid(errorTwo, itemTwoBids, createBid(itemNameTwo, usernameOne, 1.0f));
	}
	
	@Test
	public void itemsByUserMatchExpectations() {
		List<Long> userOneItems = auctionTracker.getAllItemsByUserID(usernameOne);
		List<Long> userTwoItems = auctionTracker.getAllItemsByUserID(usernameTwo);
		
		assertTrue(userOneItems.contains(Long.valueOf(itemService.getItemID(itemNameOne))));
		assertTrue(userOneItems.contains(Long.valueOf(itemService.getItemID(itemNameTwo))));
		
		assertTrue(userTwoItems.contains(Long.valueOf(itemService.getItemID(itemNameOne))));
		assertTrue(userTwoItems.contains(Long.valueOf(itemService.getItemID(itemNameTwo))));
	}
	
	private void assertBidListContainsBid(String errorMessage, List<Bid> bidList, Bid bid) {
		assertTrue(errorMessage, bidList.contains(bid));
	}
	
	private Bid createBid(String itemName, String username, float price) {
		return new Bid(itemService.getItemID(itemName),
				userService.getUserID(username),
				price);
	}
}
