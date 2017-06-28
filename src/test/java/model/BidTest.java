package model;


import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import model.Bid;

public class BidTest {

	private final long itemID = 1234;
	private final long userID = 4321;
	private final float bidPrice = 100.0f;
	
	private final Bid bidOne = new Bid(itemID, userID, bidPrice);
	private final Bid bidTwo = new Bid(itemID, userID, bidPrice);
	
	
	@Test
	public void bidsWithSameArgsAreEqual() {
		
		assertThat(bidOne, equalTo(bidTwo));
	}

	@Test
	public void hashCodesOfEqualBidsAreEqual() {
		
		assertThat(bidOne.hashCode(), equalTo(bidTwo.hashCode()));
	}
}
