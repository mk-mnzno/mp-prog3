package sample;

/**
 * The class Start represents the represents the Start tile which extends the fixed space class.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */
public class Start extends FixedSpace{

    /** This constructor receives the name of the start class to be instantiated.
     *
     * @param name of the tile
     */
    public Start(String name){
        super(name);
    }

    /** This method performs the action when a player lands in this tile.
     *
     * @param p is the player object who landed on the tile.
     * @param b is the bank object of the game.
     */
    @Override
    public void landAction (Player p, Bank b){
        b.deductCash(200);
        if (b.getCurrAmount() < 0)
        {
            p.addCash(200 - b.getCurrAmount());
        }
        else {
            p.addCash(200);
        }
        System.out.println("landAction() in FIXED [" + super.getName() + "]");
    }
}