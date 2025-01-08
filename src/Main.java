//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        Test case 1:
//• ADD_BUYER("buyer1")
//• ADD_BUYER("buyer2")
//• ADD_BUYER("buyer3")
//• ADD_SELLER("seller1")
//• CREATE_AUCTION ("A1", "10", "50", "1", "seller1")
//• CREATE_BID("buyer1", "A1", "17")
//• CREATE_BID("buyer2", "A1", "15")
//• UPDATE_BID("buyer2", "A1", "19")
//• CREATE_BID("buyer3", "A1", "19")
//• CLOSE_AUCTION("A1") // Should give Buyer1 as winner
//• GET_PROFIT("seller1", "A1") // (17 + (3 * 0.2 * 1) - 30) = -12.4
//
//
//        Test case 2:
//• ADD_SELLER("seller2")
//• CREATE_AUCTION("A2", "5", "20", "2", "seller2")
//• CREATE_BID("buyer3", "A2", 25) //This should fail as highest bid limit is 20 for A2
//• CREATE_BID("buyer2, "A2", 5)
//• WITHDRAW_BID("buyer2", "A2")
// • CLOSE_AUCTION("A2") // No winner
// • GET_PROFIT("seller2", "A2") // (1 * 0.2 * 2) = 0.4 only consider profit from participation cost
        UserService userService = new UserService();
        userService.addBuyer("buyer1");
        userService.addBuyer("buyer2");
        userService.addBuyer("buyer3");
        userService.addSeller("seller1");
        AuctionService auctionService = new AuctionService();
        auctionService.createAuction("A1", "10", "50", "1", "seller1");
        auctionService.createBid("buyer1", "A1", "17");
        auctionService.createBid("buyer2", "A1", "15");
        auctionService.updateBid("buyer2", "A1", "19");
        auctionService.createBid("buyer3", "A1", "19");
        auctionService.closeAuction("A1");
        auctionService.getProfit("seller1", "A1");

        userService.addSeller("seller2");
        auctionService.createAuction("A2", "5", "20", "2", "seller2");
        auctionService.createBid("buyer3", "A2", "25");
        auctionService.createBid("buyer2", "A2", "5");
        auctionService.withDrawBid("buyer2", "A2");
        auctionService.closeAuction("A2");
        auctionService.getProfit("seller2", "A2");
    }
}