package auctiontracker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import service.BidServiceBasic;
import service.ItemServiceBasic;
import service.UserServiceBasic;

public class AuctionTrackerBasicTest {

	private BidServiceBasic bidServiceMock = Mockito.mock(BidServiceBasic.class);
	private ItemServiceBasic itemServiceMock = Mockito.mock(ItemServiceBasic.class);
	private UserServiceBasic userServiceMock = Mockito.mock(UserServiceBasic.class);
	private AuctionTrackerBasic auctionTracker =
			new AuctionTrackerBasic(bidServiceMock, userServiceMock, itemServiceMock);
	
	private String itemName = "item";
	private String username = "user";
	private float price = 2.0f;
	
	@Before
	public void setUp() {
		when(itemServiceMock.getItemID(itemName)).thenReturn(1l);
		when(userServiceMock.getUserID(username)).thenReturn(2l);
	}
	
	@Test
	public void recordBidCallsBidServiceAsExpected() {

		auctionTracker.recordBid(itemName, username, price);
		
		Mockito.verify(itemServiceMock).getItemID(itemName);
		Mockito.verify(userServiceMock).getUserID(username);
		
		Mockito.verify(bidServiceMock).recordBid(1, 2, price);
	}

}
