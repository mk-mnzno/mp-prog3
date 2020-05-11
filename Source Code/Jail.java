package sample;

/**
 * The class Jail represents the Jail tile. The action to be done when a player lands in this object happens
 * in this class.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */
public class Jail extends FixedSpace{

    private boolean nextTurn;

    /** This constructor receives the name to be instantiated as a jail object in the game.
     *
     * @param name is the name of the object to be instantiated as Jail.
     */
    public Jail (String name){
        super(name);
        this.nextTurn = true;
    }

    @Override
    /** This method provides the action when a player lands on this object or tile. The player will be deducted an amount
     * of $50, while the bank will gain an amount of $50.
     *
     */
    public void landAction (Player p, Bank b){
        //Player pays 50 at the start of next turn
        p.deductCash(50);
        b.addCash(50);
        System.out.println("\t\t\t <<< in FIXED [Jail] paid <50.0> to Bank!");
    }
}