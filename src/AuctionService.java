import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionService {
    Map<String,Auction> activeAuctions;



    public AuctionService() {
        this.activeAuctions = new HashMap<>();
    }

    public void createAuction(String auctionId, String lowestBid, String highestBid, String participation_Cost, String seller){
        Auction a= new Auction(auctionId,lowestBid,highestBid,Integer.parseInt(participation_Cost),seller);
        activeAuctions.put(auctionId,a);
    }

    public void createBid(String userId, String auctionId, String Amount){
        Auction a= activeAuctions.get(auctionId);
        if(!UserService.isBuyerPresent(userId)){
            System.out.println("Not Present seller");
            return;
        }

        if(Integer.parseInt(Amount)<Integer.parseInt(a.getLowestBid()) || Integer.parseInt(Amount)>Integer.parseInt(a.getHighestBid()) ){
            return;
        }
        UserService.getBuyer(userId).incAuctionsCnt();
        Bid b = new Bid(userId,auctionId,Integer.parseInt(Amount));
        a.addBid(userId,b);
    }

    public void updateBid(String userId, String auctionId, String Amount){
        Auction a= activeAuctions.get(auctionId);
        if(!UserService.isBuyerPresent(userId)){
            System.out.print("Not Present seller");
            return;
        }
        if(Integer.parseInt(Amount)<Integer.parseInt(a.getLowestBid()) || Integer.parseInt(Amount)>Integer.parseInt(a.getHighestBid()) ){
            return;
        }
        Bid b = new Bid(userId,auctionId,Integer.parseInt(Amount));
        a.updateBid(userId,b);

    }

    public void withDrawBid(String buyer, String auctionId){
        Auction a= activeAuctions.get(auctionId);
        a.withdrawBid(buyer);
    }
    public void closeAuction(String auctionId){
        Auction a= activeAuctions.get(auctionId);
        if(a.isAuctionDone()){
            System.out.println("Already closed");
            return;
        }
        a.closeAuction();
        String buyer= a.getWinner();

        System.out.println(a.getWinner());
    }

    public void getProfit(String sellerId, String auctionId){
        Auction a= activeAuctions.get(auctionId);
        double share= a.getParticipation_Cost()*0.2*(a.getBidOfBuyer().size());
        System.out.println(share);
        double winnerAmount = a.getWinnerAmount();
        double averageBidAmount= a.getAverage();
        double ProfitByLoss=share+winnerAmount-averageBidAmount;
        System.out.println("Seller " +sellerId + " has ProfitByLoss "+ProfitByLoss);
    }
}
