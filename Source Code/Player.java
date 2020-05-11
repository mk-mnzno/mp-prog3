package sample;

import java.util.ArrayList;
import java.util.Random;

/**
 * The class Player represents a player object with name,
 * playerID, current amount of cash on hand, position on
 * current tile it is in, and number of properties,
 * railroad, and utilities it owns. It can purchase,
 * develop, pay rent, and can move.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 1.0
 */
public class Player {

    // attributes
    private final String name;
    private double cash;
    private int currTile;
    private boolean inJail;
    private boolean hasPassed;
    private double playerVal;
    private ArrayList<Property> ownedProperties;
    private ArrayList <Railroad> ownedRailroads;
    private ArrayList <Utility> ownedUtilities;
    private ArrayList <ChanceCard> storedCards;


    /** This constructor receives the name of the player. It initializes the name, cash, current tile, player value, hasPassed to false,
     * inJail to false and instantiates the space for the arraylist of ownedProperties, ownedRailroads, ownedUtilities, and storedCards.
     *
     * @param n is the name of the player to be instantiated as the player object.
     */
    public Player (String n){
        this.name = n;
        this.cash = 1500;
        this.currTile = 0;
        this.playerVal = 0;
        this.hasPassed = false;
        this.inJail = false;
        ownedProperties = new ArrayList<>();
        ownedRailroads = new ArrayList<>();
        ownedUtilities = new ArrayList<>();
        storedCards = new ArrayList<>();
    }

    //methods ---------------------------

    /** This method lets the player to roll the dice.
     *
     * @return the value of the dice.
     */
    public int rollDice()
    {
        Random rand = new Random();
        int dice = rand.nextInt(6);
        return dice + 1;
    }

    /** This method moves the players current tile value and sets the hasPassed to true when passes or lands on start.
     *
     * @param nDice is the value of the dice.
     */
    public void move(int nDice){
        this.currTile += nDice;

        if (this.currTile >= 32){

            setHasPassed(true);
            if(this.hasPassed == true)
                System.out.println("\tTRACING: hasPassed set to true!");

            this.currTile -= 32;
        }
    }

    /** This method returns true if the player is already bankrupt. Bankrupt means the player's cash is less than 0.
     *
     * @return true if bankrupt. Otherwise, return false.
     */
    public boolean isBankrupt (){ //checking for isOver() in Game class
        return this.cash < 0;
    }

    /** This method returns true when the player owns two or more colors. This is used to update the rent of the properties of the player.
     *
     * @return true if the player owns two or more colors. Otherwise, return false.
     */
    public boolean ownsTwoColors(){

        int i, j, maxColorCount = 0;
        int[] pCount;
        int[] pLimit;
        String[] pColor;

        pCount = new int[] {0, 0, 0, 0, 0, 0, 0};
        pLimit = new int[] {2, 3, 3, 3, 3, 2, 2};
        pColor =  new String[] {"Gray", "Violet", "Pink", "Green", "Blue", "Orange", "Yellow"};

        for (i = 0; i < ownedProperties.size(); i++){
            for (j = 0; j < pColor.length; j++)
                if (ownedProperties.get(i).getColor().compareTo(pColor[j]) == 0)
                    pCount[j]++;
        }

        for (i = 0; i < pLimit.length; i++){
            if (pLimit[i] == pCount[i])
                maxColorCount++;
        }

        return maxColorCount >= 2;
    }

    /** This method lets the player purchase a property. This also sets the owner of the property to the player who bought the
     * property tile. The bank adds cash depending on the property price, then the player will be deducted cash with regards
     * to how much the property is. Then, it adds the property to the list of the owned properties of the player and
     * it sets the property to isOwned.
     *
     * @param prop is the property to be bought.
     * @param bank is the bank object of the game.
     */
    public void purchase(Property prop, Bank bank){
        //Player is set as owner, cash deducted, Property is added to Player's ownedProperties
        prop.setOwner(this);
        bank.addCash(prop.getPrice());
        deductCash(prop.getPrice());
        ownedProperties.add(prop);
        prop.setIsOwned();
    }

    /** This method lets the player purchase a utility. This also sets the owner of the utility to the player who bought the utility.
     * The bank adds cash depending on the utlity price, the the player will be deducted cash with regards to how much the utility is.
     * Then it adds the utility object to the list of owned utilities of the player who bought it.
     *
     * @param util is the utility to be bought.
     * @param bank is the bank object of the game.
     */
    public void purchase(Utility util, Bank bank){
        //Player is set as owner, cash deducted, utility is added to the ownedUtility of the player.
        util.setOwner(this);
        bank.addCash(util.getPrice());
        deductCash(util.getPrice());
        ownedUtilities.add(util);
    }

    /** This method lets the player purchase a railroad. This also sets the owner of the railroad to the player who bought the railroad.
     * The bank adds cash depending on the railroad price, the the player will be deducted cash with regards to how much the railroad is.
     * Then it adds the railroad object to the list of owned railroads of the player who bought it.
     *
     * @param rail is the railroad to be bought.
     * @param bank is the bank object of the game.
     */
    public void purchase(Railroad rail, Bank bank){
        //Player is set as owner, cash deducted, railroad is added to the ownedRailroads of the player.
        rail.setOwner(this);
        bank.addCash(rail.getPrice());
        deductCash(rail.getPrice());
        ownedRailroads.add(rail);
    }

    /** This method lets the player trade properties with another player.
     *
     * @param prop1 is the property of the current player who initiates the trade.
     * @param prop2 is the property of the player of who owns the landed tile.
     * @param propOwner is the player who owns the landed tile.
     */
    public void trade (Property prop1, Property prop2, Player propOwner) {
        ownedProperties.add(prop2);
        prop2.setOwner(this);
        ownedProperties.remove(prop1);
        propOwner.getOwnedProperties().add(prop1);
        prop1.setOwner(propOwner);
        propOwner.getOwnedProperties().remove(prop2);
    }

    /** This method lets the property to be developed when certain conditions are met. This method builds houses up to 4,
     * and then a hotel when fully developed.
     *
     * @param p is the property to be developed.
     * @param bank is the bank object of the game.
     */
    public void develop(Property p, Bank bank) {
        int ownedColorSize = 0;
        if (cash >= p.getPricePerHouse()) {
            if (p.getRentCollected() >= p.getPricePerHouse() || p.isFootReached()) {
                if (!p.getIsFullyDeveloped()) {
                    deductCash(p.getPricePerHouse());
                    p.setNumHouses();
                    p.setRent(); //rent needs to be updated since numHouses is increased
                    bank.addCash(p.getPricePerHouse());
                } else {
                    int i, fullCount = 0;
                    for (i = 0; i < ownedProperties.size(); i++) {
                        if (ownedProperties.get(i).getColor() == p.getColor()) {
                            ownedColorSize++;
                            if (ownedProperties.get(i).getIsFullyDeveloped())
                                fullCount++;
                        }
                    }
                    if (fullCount == ownedColorSize) {
                        deductCash(p.getPricePerHouse());
                        p.setNumHouses();
                        p.setRent(); //rent needs to be updated since numHouses is increased
                        bank.addCash(p.getPricePerHouse());
                    }
                }
                if (p.getNumHouses() == 4)
                {
                    p.setIsFullyDeveloped();
                }
            }
        }
    }

    /** This method lets the player pay rent when he lands in a property tile. It takes into consideration when to apply double rent.
     *
     * @param prop is the property to be paid rent to.
     */
    public void payRent(Property prop){
        // deducts cash from Player, adds cash to Owner, update rentCollected for Property
        double rent = prop.computeRent();

        //checks if Double Rent CARD is applied to this Property
        if (prop.isDoubleRent() == true){
            rent *= 2; //rent = rent*2;
            prop.setDoubleRent(false); //Double Rent loses effect after usage
        }

        deductCash(rent);
        prop.getOwner().addCash(rent);
        prop.setRentCollected(rent);
    }

    /** This method lets the player pay rent when he lands in a utility tile. It takes into consideration the value of the dice.
     *
     * @param util is the tile to be paid rent into.
     * @param nDice is the value of the dice.
     */
    public void payRent(Utility util, int nDice){

        double rent = util.computeRent(util.getOwner().getOwnedUtilities().size(), nDice); //replace with deductCash()

        deductCash(rent);
        System.out.println("TRACING IN PAY RENT UTIL: " + this.cash);
        util.getOwner().addCash(rent);

        System.out.println("\t\t << Rent of <" + rent + "> paid on UTILITY [" + util.getName() + "].");
    }

    /** This method lets the player pay rent when he lands in a railroad tile. It takes into consideration the value of the dice.
     *
     * @param rail is the tile to be paid rent into.
     * @param nDice is the value of the dice.
     */
    public void payRent(Railroad rail, int nDice){
        double rent = rail.computeRent(rail.getOwner().getOwnedRailroads().size(), nDice); //replace with deductCash()
        deductCash(rent);
        rail.getOwner().addCash(rent);
    }

    /** This method lets the player pay tax into the bank.
     *
     * @param tax is the amount of tax to be paid into the bank.
     * @param b is the bank object in the game.
     */
    public void payTax(double tax, Bank b){
        deductCash(tax);
        b.addCash(tax);
    }

    /** This method computes the player value of the players.
     *
     */
    public void computePlayerVal(){
        int i, propVal = 0;
        for (i = 0; i < ownedProperties.size(); i++){
            propVal += ownedProperties.get(i).getRent();
            // TO DO: add the Properties w/ increased rent based on nOwned
            // do method that computes propVal based on final list of ownedProperties
        }
        setPlayerVal(this.cash + propVal);

        if(isBankrupt())
            setPlayerVal(0);
    }

    /** This method allows the player to use the cards he picks from the deck when he lands in the chance cards.
     *
     * @param card is the card picked from the deck.
     * @param tiles is the list of tiles in the board.
     * @param b is the bank object in the game.
     * @param g is the game.
     * @param dice is the value of the rolled dice.
     */
    public void useCard (ChanceCard card, ArrayList<Tile> tiles, Bank b, Game g, int dice){

        card.doAction(tiles, this, b, g, dice);

        int i;
        for (i = 0; i < storedCards.size(); i++)
            if(card == storedCards.get(i))
                disCard(card);

    }

    /** This method stores a card into the storedCards of the player.
     *
     * @param card is the card to be stored.
     */
    public void storeCard(ChanceCard card){
        storedCards.add(card);
    }

    /** This method discards the card from the player.
     *
     * @param card to be discarded.
     */
    public void disCard(ChanceCard card){
        storedCards.remove(card);
    }


    //getters

    /** This method returns the name of the player.
     *
     * @return name of the player.
     */
    public String getName() {
        return name;
    }

    /** This method returns the current cash of the player.
     *
     * @return cash of the player.
     */
    public double getCash() {
        return cash;
    }

    /** This method returns the current tile of the player.
     *
     * @return the index of the current tile of the player.
     */
    public int getCurrTile() {
        return currTile;
    }

    /** This method returns the list of owned properties of the player.
     *
     * @return list of owned properties.
     */
    public ArrayList<Property> getOwnedProperties() {
        return ownedProperties;
    }

    /** This method returns the list of owned utilities of the player.
     *
     * @return list of owned utilities.
     */
    public ArrayList<Utility> getOwnedUtilities() {
        return ownedUtilities;
    }

    /** This method returns the list of owned railroads of the player.
     *
     * @return list of owned railroads.
     */
    public ArrayList<Railroad> getOwnedRailroads() {
        return ownedRailroads;
    }

    /** This method returns true if the player is in jail. Otherwise, false.
     *
     * @return true if player is in jail. Otherwise, return false.
     */
    public boolean getInJail() {
        return inJail;
    }

    /** This method returns true if the player is in passed start. Otherwise, false.
     *
     * @return true if player passed start. Otherwise, return false.
     */
    public boolean getHasPassed() {
        return hasPassed;
    }

    /** This method returns the player value of the player.
     *
     * @return player value of the player.
     */
    public double getPlayerVal() {
        return playerVal;
    }

    /** This method returns the stored cards of the player.
     *
     * @return stored cards of the player.
     */
    public ArrayList<ChanceCard> getStoredCards () {
        return storedCards;
    }

    //setters

    /** This method adds cash to the player.
     *
     * @param amt of cash to be added to the player.
     */
    public void addCash(double amt){
        this.cash += amt;
    }

    /** This method deducts cash to the player.
     *
     * @param amt of cash to be deducted from the player.
     */
    public void deductCash(double amt){
        this.cash -= amt;
    }

    /** This method sets the index of the current tile of the player.
     *
     * @param currTile is the index of the current tile to be set.
     */
    public void setCurrTile(int currTile) {
        this.currTile = currTile;
    }

    /** This method sets the hasPassed attribute of the player depending on the state.
     *
     * @param state to be set in the hasPassed attribute.
     */
    public void setHasPassed(boolean state) {
        this.hasPassed = state;
    }

    /** This method sets the inJail attribute of the player depending on the state.
     *
     * @param state to be set in the inJail attribute.
     */
    public void setInJail(boolean state) {
        this.inJail = state;
    }

    /** This method sets the player value of the player.
     *
     * @param pVal is the value to be set in the player value.
     */
    public void setPlayerVal(double pVal) {
        this.playerVal = pVal;
    }


    @Override
    public String toString ()
    {
        String strData = "";

        strData = "[PROPERTIES]\n";
        for (int i = 0; i < ownedProperties.size(); i++)
        {
            strData += ownedProperties.get(i).getName() + " [" + ownedProperties.get(i).getNumHouses() + "]\n";
        }

        strData += "\n[RAILROADS]\n";
        for (int i = 0; i < ownedRailroads.size(); i++)
        {
            strData += ownedRailroads.get(i).getName() + "\n";
        }

        strData += "\n[UTILITIES]\n";
        for (int i = 0; i < ownedUtilities.size(); i++)
        {
            strData += ownedUtilities.get(i).getName() + "\n";
        }

        return strData;
    }


    /** This method displays the player's current status in the game which
     * includes its cash on hand.
     *
     */
    public void displayPlayerStatus() {
        System.out.println("\t\t[ PLAYER " + "'s STATUS ]: " + name);
        System.out.println("\t\tCash on Hand: " + cash);
    }
}