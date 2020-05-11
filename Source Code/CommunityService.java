package sample;

/** The class CommunityService represents community service object with
 * its corresponding action when landed on.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 1.0
 */
public class CommunityService extends FixedSpace{

    /** This constructor receives the name of the fixed space.
     *
     * @param  name   String name of the community service
     */
    public CommunityService (String name){
        super(name);
    }

    /**
     *
     * @param p is the player object that will be deducted $50 when he lands on this tile.
     * @param b is the bank object that will get an amount of $50 when someone lands on this tile.
     */
    @Override
    public void landAction (Player p, Bank b){
        b.addCash(50);
        p.deductCash(50);
    }
}