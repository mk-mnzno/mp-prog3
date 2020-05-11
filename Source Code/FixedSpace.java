package sample;

/**
 * The class FixedSpace represents a fixed space object with name and a method
 * in which when the player lands here, the method will be done to fulfill the action.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */
public abstract class FixedSpace extends Tile {

    //constructor
    /** This constructor receives the name of the fixed space.
     *
     * @param  name   String name of the fixed space
     */
    public FixedSpace (String name){
        super (name);
    }

    /** This method does not do anything. This serves as an abstract method for when a player lands on this tile, just skip a turn.
     *
     * @param p is the player object who landed on the tile.
     * @param b is the bank object of the game.
     */
    public abstract void landAction (Player p, Bank b);

}