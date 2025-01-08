public class Buyer extends User{
    int AuctionsParticipated=0;
    public Buyer(String name) {
        super(name);
    }
    public void incAuctionsCnt(){
        AuctionsParticipated++;
    }
    public int getAuctionsParticipated(){
        return AuctionsParticipated;
    }

    public void setBuyer(String name){
        this.name=name;
    }

    public String getBuyer(){
        return this.name;
    }
}
