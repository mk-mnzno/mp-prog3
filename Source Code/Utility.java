package sample;

/** This class represents the utility object and has a method that computes for the rent of the tile.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */
public class Utility extends Ownable implements Rentable{

    /** This constructor receives the name of the utility to be instantiated.
     *
     * @param name of the tile
     */
    public Utility (String name) {
        super(name);
        setPrice(150);
    }

    /** This method computes for the rent of the utility object.
     *
     * @param nOwned number of owned utilities of the player.
     * @param nDice value of the dice.
     * @return rent.
     */
    @Override
    public double computeRent (int nOwned, int nDice){
        double rent = 0;

        if(nOwned == 1){
            rent = nDice * 4;
        }
        else if (nOwned == 2){
            rent = nDice * 10;
        }

        System.out.println("computeRent() in UTILITY: " + rent);
        return rent;
    }

}