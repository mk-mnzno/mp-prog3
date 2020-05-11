package sample;

/**
 * The class Railroad represents the railroad tile that extends ownable and implements rentable. In this method, the computation of the rent
 * for the railroad rent.
 *
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */

public class Railroad extends Ownable implements Rentable{

    /** This constructor receives the name of the railroad to be instantiated.
     *
     * @param name of the tile
     */
    public Railroad (String name) {
        super(name);
        setPrice(200);
    }

    /** This method computes the rent of the railroad object.
     *
     * @param nOwned number of owned railroads of the player.
     * @param nDice value of the dice.
     * @return rent of the railroad
     */
    @Override
    public double computeRent (int nOwned, int nDice){
        double rent = 0;

        if(nOwned == 1){
            rent = 25;
        }
        else if (nOwned == 2){
            rent = 50;
        }
        else if (nOwned == 3){
            rent = 150;
        }
        return rent;
    }
}