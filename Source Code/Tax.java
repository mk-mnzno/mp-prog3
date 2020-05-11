package sample;

/**
 * The class Tax represents tax object and computes the tax.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */
public abstract class Tax extends Tile{

    /** This constructor receives the name of the tax object to be instantiated.
     *
     * @param name of the tile
     */
    public Tax (String name){
        super(name);
    }

    /** This method is an abstract method and does not do anything.
     *
     * @param cash of the player
     * @return nothing.
     */
    public abstract double computeTax (double cash);
}