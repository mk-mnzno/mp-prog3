package sample;

/**
 * The class FreeParking represents a fixed space object that has a name and a method
 * in which when a player lands in this object, the method will be done to fulfill the action.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */
public class FreeParking extends FixedSpace{

    /** This constructor receives the name of the fixed space.
     *
     * @param  name   String name of the free parking object.
     */
    public FreeParking (String name){
        super(name);
    }

    /** This method serves as the action to be done when a player lands on this object/tile. The player will just wait for his next turn.
     *
     * @param p is the player object who landed on the tile.
     * @param b is the bank object of the game.
     */
    @Override
    public void landAction (Player p, Bank b){
        System.out.println("\t\t\t FIXED [Free Parking]: Wait for next turn.");
    }
}