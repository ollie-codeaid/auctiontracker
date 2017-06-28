package model;


public class Bid {
	
	private final long itemID;
	private final long userID;
	private final float price;

	public Bid(long itemID, long userID, float price) {
		this.itemID = itemID;
		this.userID = userID;
		this.price = price;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Bid)) {
			return false;
		}
		
		Bid bid = (Bid) o;
		
		return itemID == bid.getItemID() &&
				userID == bid.getUserID() &&
				price == bid.getPrice();
	}
	
	@Override
	public int hashCode() {
		
		int result = 17;
		result = 31 * result + Long.hashCode(itemID);
		result = 31 * result + Long.hashCode(userID);
		result = 31 * result + Float.hashCode(price);
		
		return result;
	}
	
	public long getItemID() {
		return itemID;
	}

	public long getUserID() {
		return userID;
	}
	
	public float getPrice() {
		return price;
	}
}
