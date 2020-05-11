package sample;

/**
 * The class Luxury represents the tile for the Luxury object. The computation of the tax when landed in this tile
 * happens in this class.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */
public class Luxury extends Tax{

    /** This constructor
     *
     * @param name of the tile
     */
    public Luxury (String name){
        super(name);
    }

    /** This method computes for the tax when a player lands in this tile or object.
     *
     * @param cash of the current player.
     * @return 75 when this method is implemented.
     */
    @Override
    public double computeTax (double cash){
        return 75;
    }

}