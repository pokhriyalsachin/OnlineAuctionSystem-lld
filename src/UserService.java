import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {


    private static Map<String,Buyer> buyers=new HashMap<>();
    private static Map<String,Seller> sellers=new HashMap<>();

//    public UserService() {
//        this.buyers = ;
//        this.sellers = ;
//    }

    public static void addBuyer(String buyer){
        Buyer b = new Buyer(buyer);
        buyers.put(buyer,b);
    }
    public static void addSeller(String seller){
        Seller b = new Seller(seller);
        sellers.put(seller,b);
    }

    public static boolean isBuyerPresent(String Buyer){
        return buyers.containsKey(Buyer);
    }
    public static boolean isSellerPresent(String seller){
        return sellers.containsKey(seller);
    }

    public static  Buyer getBuyer(String buyer){
        return buyers.get(buyer);
    }
    public static Seller getSeller(String seller){
        return sellers.get(seller);
    }

}
