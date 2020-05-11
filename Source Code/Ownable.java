package sample;

public abstract class Ownable extends Tile{

    private double price;
    private Player owner;
    private boolean isOwned;

    public Ownable(String name) {
        super(name);
        this.price = 0;
        this.isOwned = false;
    }

    /** This method returns the corresponding price of the property.
     *
     * @return double price of the property
     */
    public double getPrice() {
        return price;
    }

    public Player getOwner (){
        return this.owner;
    }

    public boolean getIsOwned() {
        return isOwned;
    }

    public void setIsOwned() {
        this.isOwned = true;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    /** This method sets the player as the owner of the property if it
     * is unowned.
     *
     * @param p the current player
     */
    public void setOwner(Player p) {
      //  if(!isOwned) {
            this.owner = p;
            isOwned = true;
       // }

    }


}