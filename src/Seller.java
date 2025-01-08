public class Seller extends User {

    public Seller(String name) {
        super(name);
    }

    public void setSeller(String name){
        this.name=name;
    }
    public String setSeller(){
        return this.name;
    }

}
