package sample;
/**
 * The class Property represents a property object with color, multiplier, property type,
 * rent, owner, foot traffic and corresponding prices upon purchase. The rent list depends
 * on the property type and number of houses owned by the player. It can determine if foot
 * traffic limit has been reached.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */

public class Property extends Ownable {

    //attributes
    private final String color;
    private final double multiplier;
    private final int propType;
    private final int pricePerHouse;
    private int footTrafficLimit;
    private double rentCollected;
    private double rent;
    private int numHouses;
    private int footCount;
    private boolean isFullyDeveloped;
    private boolean isDoubleRent;


 //   private boolean doubleRent = false;

    private static final int[][] RENT_LIST = { // row = properType; col = numHouses
            {2, 10, 30, 90, 160, 250},
            {4, 20, 60, 180, 320, 450},
            {6, 30, 90, 270, 400, 550},
            {6, 30, 90, 270, 400, 550},
            {6, 40, 100, 300, 450, 600},
            {10, 50, 150, 450, 625, 750},
            {10, 50, 150, 450, 625, 750},
            {12, 60, 180, 500, 700, 900},
            {14, 70, 200, 550, 750, 950},
            {14, 70, 200, 550, 750, 950},
            {16, 80, 220, 600, 800, 1000},
            {18, 90, 250, 700, 875, 1050},
            {18, 90, 250, 700, 875, 1050},
            {20, 100, 300, 750, 925, 1100},
            {22, 110, 330, 800, 975, 1150},
            {22, 110, 330, 800, 975, 1150},
            {26, 130, 390, 900, 1100, 1275},
            {28, 150, 450, 1000, 1200, 1400}
    };



    // constructor
    /**
     * This constructor receives the property's name to be instantiated as an object.
     *
     * @param  name of the property object
     */
    public Property (String name){
        super (name);

        this.numHouses = 0;
        this.footCount = 0;
        this.rentCollected = 0;
        this.isFullyDeveloped = false;
        this.isDoubleRent = false;

        switch (name){
            case "Almond Drive": this.color = "Gray";
                setPrice(60);
                this.pricePerHouse = 50;
                this.rent = 2;
                this.multiplier = 2.5;
                this.propType = 0;
                break;

            case "Kasoy Street": this.color = "Gray";
                setPrice(60);
                this.pricePerHouse = 50;
                this.rent = 4;
                this.multiplier = 3.0;
                this.propType = 1;
                break;

            case "Rodeo Drive": this.color = "Violet";
                setPrice(100);
                this.pricePerHouse = 50;
                this.rent = 6;
                this.multiplier = 3.5;
                this.propType = 2;
                break;

            case "Orange Street": this.color = "Violet";
                setPrice(100);
                this.pricePerHouse = 50;
                this.rent = 6;
                this.multiplier = 4.0;
                this.propType = 3;
                break;

            case "Ventura Street": this.color = "Violet";
                setPrice(120);
                this.pricePerHouse = 50;
                this.rent = 6;
                this.multiplier = 4.0;
                this.propType = 4;
                break;

            case "Juan Luna": 	 this.color = "Red";
                setPrice(140);
                this.pricePerHouse = 100;
                this.rent = 10;
                this.multiplier = 4.5;
                this.propType = 5;
                break;

            case "Ylaya": 		 this.color = "Red";
                setPrice(140);
                this.pricePerHouse = 100;
                this.rent = 10;
                this.multiplier = 4.5;
                this.propType = 6;
                break;

            case "J. Abad Santos": this.color = "Red";
                setPrice(160);
                this.pricePerHouse = 100;
                this.rent = 12;
                this.multiplier = 5.0;
                this.propType = 7;
                break;

            case "Madison":		 this.color = "Green";
                setPrice(180);
                this.pricePerHouse = 100;
                this.rent = 14;
                this.multiplier = 5.0;
                this.propType = 8;
                break;

            case "Annapolis": this.color = "Green";
                setPrice(180);
                this.pricePerHouse = 100;
                this.rent = 14;
                this.multiplier = 5.5;
                this.propType = 9;
                break;

            case "Connecticut":  this.color = "Green";
                setPrice(200);
                this.pricePerHouse = 100;
                this.rent = 16;
                this.multiplier = 5.5;
                this.propType = 10;
                break;

            case "Bougainvilla": this.color = "Blue";
                setPrice(220);
                this.pricePerHouse = 150;
                this.rent = 2;
                this.multiplier = 6.0;
                this.propType = 11;
                break;

            case "Dama de Noche": this.color = "Blue";
                setPrice(220);
                this.pricePerHouse = 150;
                this.rent = 2;
                this.multiplier = 6.0;
                this.propType = 12;
                break;

            case "Acacia": 		 this.color = "Blue";
                setPrice(240);
                this.pricePerHouse = 150;
                this.rent = 2;
                this.multiplier = 6.5;
                this.propType = 13;
                break;

            case "Solar Street": this.color = "Orange";
                setPrice(260);
                this.pricePerHouse = 150;
                this.rent = 22;
                this.multiplier = 6.5;
                this.propType = 14;
                break;

            case "Galaxy Street": this.color = "Orange";
                setPrice(260);
                this.pricePerHouse = 150;
                this.rent = 22;
                this.multiplier = 7.0;
                this.propType = 15;
                break;

            case "9th Street":   this.color = "Yellow";
                setPrice(300);
                this.pricePerHouse = 200;
                this.rent = 26;
                this.multiplier = 7.0;
                this.propType = 16;
                break;

            case "5th Avenue":   this.color = "Yellow";
                setPrice(320);
                this.pricePerHouse = 200;
                this.rent = 28;
                this.multiplier = 8.0;
                this.propType = 17;
                break;

            default:			 this.color = "NONE";
                setPrice(0);
                this.pricePerHouse = 0;
                this.rent = 0;
                this.multiplier = 0;
                this.propType = 18;
                break;
        }
        this.footTrafficLimit = 0;
//        this.footTrafficLimit = (int) (totalPlyrCount * multiplier);
    }

    //methods -----------------------------------------

    /**This method determines if foot traffic limit has been reached by the players.
     *
     * @return true if foot traffic limit is reached
     */
    public boolean isFootReached(){
        return this.footCount >= this.footTrafficLimit;
    }

    /** This method computes for the rent of the property.
     *
     * @return the computed rent of the property.
     */
    public double computeRent (){
        int i, nOwned = 0;
        Property ownerProp;

        for (i = 0; i < super.getOwner().getOwnedProperties().size(); i++){
            ownerProp = super.getOwner().getOwnedProperties().get(i);

            if (this.color.compareTo(ownerProp.getColor()) == 0)
                nOwned++;
        }

        //checks for an increase in Property value
        if (nOwned == 2)
            return getRent() + 10.0;
        else if (nOwned == 3)
            return getRent() + 20.0;
        else
            return getRent();
    }

    //getters
    /** This method returns the corresponding color of the property.
     *
     * @return String color of the property
     */
    public String getColor() {
        return color;
    }

    /** This method returns the corresponding multiplier of the property.
     *
     * @return double ineteger multiplier of the property
     */
    public double getMultiplier() {
        return multiplier;
    }

    /** This method returns the corresponding type of the property to
     * be used for accessing the RENT_LIST.
     *
     * @return whole number integer type of the property
     */
    public int getPropType() {
        return propType;
    }

    /** This method returns the corresponding price per house of the property.
     *
     * @return integer price per house of the property
     */
    public int getPricePerHouse() {
        return pricePerHouse;
    }

    /** This method returns the rent list of the properties.
     *
     * @return two-dimensional array rent list of the properties
     */
    public static int[][] getRENT_LIST() {
        return RENT_LIST;
    }

    /** This method returns the rent collected from the property.
     *
     * @return whole number integer rent collected from the property
     */
    public double getRentCollected() {
        return rentCollected;
    }

    /** This method returns the number of houses the property has.
     *
     * @return integer number of houses the property has
     */
    public int getNumHouses() {
        return numHouses;
    }

    /** This method returns the rent computed from the property.
     *
     * @return whole number integer rent computed from the property
     */
    public double getRent() {
        return rent;
    }

    /** This method returns the foot traffic count of the property.
     *
     * @return whole number foot traffic count of the property
     */
    public int getFootCount() {
        return footCount;
    }

    /** This method returns the foot traffic limit of the property.
     *
     * @return whole number foot traffic limit of the property
     */
    public int getFootTrafficLimit() {
        return footTrafficLimit;
    }

    /** This method returns true if the property is FullyDeveloped.
     *
     * @return true if the property is fully developed.
     */
    public boolean getIsFullyDeveloped() {
        return isFullyDeveloped;
    }

    /** This method returns true if the property is set to double rent.
     *
     * @return true if the property is set to double rent. Otherwise, return false.
     */
    public boolean isDoubleRent() {
        return isDoubleRent;
    }

    //setters

    /** This method adds the amount collected from rent to the attribute rent collected.
     *
     * @param rent whole number integer rent payed by the player
     */
    public void setRentCollected(double rent) {
        this.rentCollected += rent;
    }


    public void setRent() {
        this.rent = RENT_LIST[propType][numHouses];
    }

    /** This method increments the number of houses by one in the property
     *  that the player owns.
     *
     */
    public void setNumHouses() {
        this.numHouses++;
    }

    /** This method sets the fully developed attribute to true when the property has 4 houses.
     *
     */
    public void setIsFullyDeveloped() {
        this.isFullyDeveloped = true;
    }

    /** This method increments the foot count of the property when landed.
     *
     */
    public void setFootCount(){
        this.footCount++;
    }

    /** This methodsets the foot traffic limit of the player with regards to the number of players.
     *
     * @param numPlayers in the game
     */
    public void setFootTrafficLimit (int numPlayers) {
        footTrafficLimit = (int) (numPlayers * multiplier);
    }

    /** This method sets the double rent state of the property.
     *
     * @param state of the property to be set.
     */
    public void setDoubleRent (boolean state) {
        this.isDoubleRent = state;
    }













    // setters



    /** This method sets the computed rent of the property that the player
     *	needs to pay.
     *
     */
    /*
    public void setRent ()
    {
        if (doubleRent == false)
            rent = RENT_LIST[propType][numHouses];
        else
        {
            rent = rent * rent;
            doubleRent = false;
        }
    }
    */


    /** This method sets the computed rent when double rent card is applied to the property.
     *
     */
    /*
    public void setDoubleRent ()
    {
        doubleRent = true;
    }
    */



    @Override
    public String toString ()
    {
        if (getOwner() == null) {
            String strData = "NAME: " + super.toString() + "\nCOLOR: " + color + "\nPRICE: " + getPrice() + "\nPRICE PER HOUSE: " + pricePerHouse + " " + "\nRENT: " + rent +
                    "\nOWNER: " + getOwner() + "\nFOOT COUNT: " + getFootCount() + "\nFOOT COUNT LIMIT: " + getFootTrafficLimit() + "\nNUM OF HOUSES: " + getNumHouses() + "\nRENT COLLECTED: " + getRentCollected();
            return strData;
        }
        else {
            String strData = "NAME: " + super.toString() + "\nCOLOR: " + color + "\nPRICE: " + getPrice() + "\nPRICE PER HOUSE: " + pricePerHouse + " " + "\nRENT: " + rent +
                    "\nOWNER: " + getOwner().getName() + "\nFOOT COUNT: " + getFootCount() + "\nFOOT COUNT LIMIT: " + getFootTrafficLimit() + "\nNUM OF HOUSES: " + getNumHouses() + "\nRENT COLLECTED: " + getRentCollected();
            return strData;
        }
    }

}