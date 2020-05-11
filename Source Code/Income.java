package sample;

/**
 * The class Income represents the tile for the income tax. The computation of the tax when someone lands in this tile
 * happens in this object.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */
public class Income extends Tax{

    /** This constructor receives the name of the tile to be instantiated as an Income Tax object.
     *
     * @param name is the name of the tax.
     */
    public Income(String name){
        super (name);
    }

    @Override
    public double computeTax (double cash){
        double val = (.10*cash);

        if (val > 200)
            return val;

        return 200;
    }
}