package sample;
/**
 * The class Bank represents a bank object with an amount depending on the number of players.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */
public class Bank {

    private double currAmount;

    /**
     *
     */
    public Bank (){
        this.currAmount = 0;
    }

    /** This method returns the current amount of the bank.
     *
     * @return the current amount on the bank in double.
     */
    public double getCurrAmount(){
        return currAmount;
    }

    /** This method adds cash to the bank depending on the amount.
     *
     * @param amt to be added to the current amount of the bank.
     */
    public void addCash (double amt){
        this.currAmount += amt;
    }

    /** This method deducts cash from the bank depending ont the amount.
     *
     * @param amt to be deducted to the current amount of the bank.
     */
    public void deductCash (double amt){
        this.currAmount -= amt;
    }

    /** This method checks if the bank is already bankrupt.
     *
     * @return true if the current amount of the bank is less than 0 or negative. Otherwise, return false.
     */
    public boolean isBankrupt(){ //checking for isOver() in Game class
            return this.currAmount < 0;
    }

    /** This method sets the current amount of the bank.
     *
     * @param amt to be set to the current amount of the bank.
     */
    public void setCurrAmount (int amt) {
        currAmount = amt;
    }

    @Override
    public String toString(){
        return "Current Bank amount: " + currAmount;
    }
}