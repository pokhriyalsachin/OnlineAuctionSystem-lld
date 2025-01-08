public class Bid {
    private String userid;
    private String auctionid;
    private int amount;

    public Bid(String userid, String auctionid, int amount) {
        this.userid = userid;
        this.auctionid = auctionid;
        this.amount = amount;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(String auctionid) {
        this.auctionid = auctionid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
