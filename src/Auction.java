import java.util.*;

public class Auction {
    String AuctionId;
    String lowestBid;
    String highestBid;
    int Participation_Cost;
    String seller;
    Map<String,Bid> BidOfBuyer;

    List<Bid> listOfBid;
    String Winner;
    boolean AuctionDone;


    public Auction(String auctionId, String lowestBid, String highestBid, int participation_Cost, String seller) {
        AuctionId = auctionId;
        this.lowestBid = lowestBid;
        this.highestBid = highestBid;
        Participation_Cost = participation_Cost;
        this.seller = seller;
        BidOfBuyer= new HashMap<>();

        listOfBid= new ArrayList<>();
        AuctionDone=false;
    }

    public String getAuctionId() {
        return AuctionId;
    }

    public void setAuctionId(String auctionId) {
        AuctionId = auctionId;
    }

    public String getLowestBid() {
        return lowestBid;
    }

    public void setLowestBid(String lowestBid) {
        this.lowestBid = lowestBid;
    }

    public String getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(String highestBid) {
        this.highestBid = highestBid;
    }

    public int getParticipation_Cost() {
        return Participation_Cost;
    }

    public void setParticipation_Cost(int participation_Cost) {
        Participation_Cost = participation_Cost;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void withdrawBid(String name){
        BidOfBuyer.remove(name);
    }

    public Map<String, Bid> getBidOfBuyer() {
        return BidOfBuyer;
    }

    public void setBidOfBuyer(Map<String, Bid> bidOfBuyer) {
        BidOfBuyer = bidOfBuyer;
    }

    public void addBid(String userId,Bid b){
        this.BidOfBuyer.put(userId,b);
        listOfBid.add(b);
    }
    public void updateBid(String userId,Bid b){
        this.BidOfBuyer.put(userId,b);
        listOfBid.add(b);
    }

    public List<Bid> getListOfBid() {
        return listOfBid;
    }

    public void setListOfBid(List<Bid> listOfBid) {
        this.listOfBid = listOfBid;
    }


    public String getWinner() {
        return Winner;
    }

    public void setWinner(String winner) {
        Winner = winner;
    }

    public double getWinnerAmount(){
        if(Winner.isEmpty() || Winner.equals("No Winner")){
            return 0;
        }
        return getBidOfBuyer().get(Winner).getAmount();
    }

    public double getAverage(){
        double Low= Integer.parseInt(lowestBid);
        double high= Integer.parseInt(highestBid);
       return (Low + high)/2.0;
    }

    public boolean isAuctionDone() {
        return AuctionDone;
    }
    public void closeAuction() {
        setAuctionDone(true);
        Map<Integer,List<String>> m2= new TreeMap<>();
        for(Map.Entry<String,Bid> m1: BidOfBuyer.entrySet()){
            int amount= m1.getValue().getAmount();
            if(!m2.containsKey(amount)) {
                m2.put(amount, new ArrayList<>());
                m2.get(amount).add(m1.getValue().getUserid());

            }
            else{
                m2.get(amount).add(m1.getValue().getUserid());

            }
        }
        int maxAmount=0;
        for(Map.Entry<Integer,List<String>>m1: m2.entrySet()){
            if(m1.getValue().size()==1){
                maxAmount=Math.max(maxAmount, m1.getKey());
            }else{
                List<String> buyers= m1.getValue();
                int cnt=0;
                for(String b: buyers){

                    if(UserService.getBuyer(b).getAuctionsParticipated()>=2){
                        cnt++;
                    }
                    if(cnt==2){
                        break;
                    }
                }
//
                if(cnt==1){
                    maxAmount=Math.max(maxAmount, m1.getKey());
                }
            }
//
        }


        for(Map.Entry<String,Bid> m1: BidOfBuyer.entrySet()){
            int amount= m1.getValue().getAmount();

            if(maxAmount==amount) {
                String b=m1.getValue().getUserid();

                if(UserService.getBuyer(b).getAuctionsParticipated()>1){
                    setWinner(b);
                    return;
                }else{
                    setWinner(b);
                }
            }

        }

        if(Winner==null || getWinner().isEmpty()) setWinner("No Winner");


    }

    public void setAuctionDone(boolean auctionDone) {
        AuctionDone = auctionDone;
    }
}

