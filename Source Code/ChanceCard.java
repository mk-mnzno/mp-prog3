package sample;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/** The class ChanceCard represents a a chance card that has a card number and a group type and a description.
 * The type of action depends on what card number is instantiated.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 19.0725
 */
public class ChanceCard {

    private final int cardNo;
    private final int groupType;
    private String desc;

    public ChanceCard(int cardNo, int groupType) {
        this.cardNo = cardNo;
        this.groupType = groupType;
        switch (this.cardNo){
            case 1: {
                desc = "GET OUT OF JAIL";
                break;
            }
            case 2: {
                /* Card2: Proceed to property, do not collect money when passed START */
                desc = "PROCEED TO PROPERTY";
                break;
            }

            case 3: {
			/* Card3: Go to nearest utility. If unowned, you may buy from the bank; otherwise throw dice and
			    pay owner 10 times the value of the dice. */
                desc = "GO TO NEAREST UTILITY!";
                break;
            }
            case 4: {
			/* Card4: Go to nearest railroad. If unowned, you may buy from the bank;
				otherwise pay rent.
			*/
                desc = "GO TO NEAREST RAILROAD";
                break;
            }
            case 5: {
                /* Card5: Congratulations! Bank pays dividend of $50 */
                desc = "CONGRATULATIONS! BANK PAYS DIVIDEND OF $50";
                break;
            }
            case 6: {
                /* Card6: Tax refund. Collect $100 from the bank.*/
                desc = "COLLECT $100 FROM THE BANK";
                break;
            }
            case 7: {
                /* Card7: Advance to START, collect $200 */
                desc = "ADVANCE TO START";
                break;
            }
            case 8: {
                /* Card8: Your birthday! Collect $300 gift money */
                desc = "IT'S YOUR BIRTHDAY! COLLECT $300 GIFT MONEY!";
                break;
            }
            case 9: {
                desc = "YOU WON THE COMPETITION, COLLECT $150 PRIZE MONEY";
                /* Card9: You won the competition, collect $150 prize money. */
                break;
            }
            case 10: {
                /* Card10: Go to jail. When passing START, do not collect $200 */
                desc = "GO TO JAIL";
                break;
            }
            case 11: {
                /* Card11: Take a trip to property, collect money when passing START */
                desc = "TAKE A TRIP TO PROPERTY, COLLECT MONEY WHEN PASSING START!";
                break;
            }
            case 12: {
			/* Card12: Double rent. Apply this card to a property you own, and you
				can collect double rent from the next player who lands on it.
				If you do not own any property, discard this card
			*/
			    desc = "DOUBLE RENT!";
                break;
            }
            case 13: {
			/* Card13: Apply this card to a property you own. Renovation costs $25
				per house, or $50 per hotel.  From now on, rent is increased by 50%.
				If you do not own any property, discard this card.
			*/
                desc = "APPLY RENOVATION TO A PROPERTY YOU OWN. INCREASE BY 50% THE RENT. $25 PER HOUSE OR $50 PER HOTEL! IF NO PROPERTY, DISCARD";
                break;
            }
            case 14: {
			/* Card14: Apply this card to a property you own. Dilapidated houses.
				From now on, rent is decreased by 10%.
				If you do not own any property, discard this card.
			*/
			    desc = "APPLY THIS CARD TO A PROPERTY YOU OWN. DILAPIDATED HOUSE. RENT IS DECREASED BY 10%. IF NO PROPERTY, DISCARD";
                break;
            }
            case 15: {
			/* Card15: Apply this card to a utility or railroad you own.
				Increase charge by 10%.
				If you do not own any utility or railroad, discard this card
			*/
                desc = "APPLY THIS CARD TO A UTILITY OR RAILROAD YOU OWN. INCREASE CHARGE BY 10%. IF YOU DO NOT OWN, DISCARD!";


                break;
            }
            case 16: {
			/* Card16: Apply this card to a utility or railroad you own.
				Decrease charge by 10%.
				If you do not own any utility or railroad, discard this card.
			*/
                desc = "APPLY THIS CARD TO A UTILITY OR RAILROAD YOU OWN. DECREASE CHARGE BY 10%. IF YOU DO NOT OWN, DISCARD!";
                break;
            }
            case 17: {
                /* Card17: Donate money for community development (random amount) */
                desc = "DONATE MONEY FOR COMMUNITY DEVELOPMENT!";
                break;
            }
            case 18: {
                /* Card18: Pay taxes (random amount) */
                desc = "PAY RANDOM TAX AMOUNT!";
                break;
            }
        }

    }

    /** This method does the action depending on the card number given. The action will be done through switch case.
     *
     * @param tiles of the board.
     * @param p is the current player of the game.
     * @param b is the bank object of the game.
     * @param g is the game object.
     * @param dice  the integer value of the dice.
     */
    public void doAction (ArrayList<Tile> tiles, Player p, Bank b, Game g, int dice){
        Scanner sc = new Scanner(System.in);
        int i;
        switch (this.cardNo){
            case 1: {
                /* Card1: Get out of Jail free. */
                p.setInJail(false); //inJail attribute is set to false, Player will not pay in JAIL
                break;
            }
            case 2: {
                /* Card2: Proceed to property, do not collect money when passed START */
                //search position of the tile in Board
                i = p.getCurrTile();
                while(!(tiles.get(i) instanceof Property)){
                    if (i == 31)
                    {
                        i = 0;
                    }
                    else {
                        i++;
                    }
                }
                p.setCurrTile(i);
                g.setCurrTile(tiles.get(i));

                if(p.getHasPassed()){
                    p.setHasPassed(false); //hasPassed is set to false, Player will not collect START money
                    System.out.println("\tTRACING doAction() in CARD: hasPassed set to false!");
                }
                break;
            }
            case 3: {
			/* Card3: Go to nearest utility. If unowned, you may buy from the bank;
				otherwise throw dice and pay owner 10 times the value of the dice.
			*/
                i = p.getCurrTile();
                while (!(tiles.get(i) instanceof Utility)) {
                    if (i == 31) {
                        i = 0;
                    } else {
                        i++;
                    }
                }
                p.setCurrTile(i);
                g.setCurrTile(tiles.get(i));
                if (((Utility)g.getCurrTile()).getIsOwned())
                {
                    p.deductCash(10 * dice);
                    ((Utility) g.getCurrTile()).getOwner().addCash(10 * dice);
                }
                break;
            }
            case 4: {
			/* Card4: Go to nearest railroad. If unowned, you may buy from the bank;
				otherwise pay rent.
			*/
                i = p.getCurrTile();
                while(!(tiles.get(i) instanceof Railroad)){
                    if (i == 31)
                    {
                        i = 0;
                    }
                    else {
                        i++;
                    }
                }
                p.setCurrTile(i);
                g.setCurrTile(tiles.get(i));

                if(p.getHasPassed()){
                    p.setHasPassed(false); //hasPassed is set to false, Player will not collect START money
                    System.out.println("\tTRACING doAction() in CARD: hasPassed set to false!");
                }
                break;
            }
            case 5: {
                /* Card5: Congratulations! Bank pays dividend of $50 */
                b.deductCash(50);
                p.addCash(50);
                g.setTurn();
                //NOTE: "dividend" means "share" NOT "divided by"
                break;
            }
            case 6: {
                /* Card6: Tax refund. Collect $100 from the bank.*/
                b.deductCash(100);
                p.addCash(100);
                g.setTurn();
                break;
            }
            case 7: {
                /* Card7: Advance to START, collect $200 */
                p.setCurrTile(0); //Player moves and lands on START tile
                g.setCurrTile(tiles.get(0));
                b.deductCash(200);
                p.addCash(200);
                break;
            }
            case 8: {
                /* Card8: Your birthday! Collect $300 gift money */
                p.addCash(300);
                b.deductCash(300);
                g.setTurn();
                break;
            }
            case 9: {
                /* Card9: You won the competition, collect $150 prize money. */
                p.addCash(150);
                b.deductCash(150);
                g.setTurn();
                break;
            }
            case 10: {
                /* Card10: Go to jail. When passing START, do not collect $200 */
                i = p.getCurrTile();
                while(!(tiles.get(i) instanceof Jail)){
                    if (i == 31)
                    {
                        i = 0;
                    }
                    else {
                        i++;
                    }
                }
                p.setCurrTile(i);
                g.setCurrTile(tiles.get(i));
                p.setInJail(true);

                if(p.getHasPassed()){
                    p.setHasPassed(false); //hasPassed is set to false, Player will not collect START money
                    System.out.println("\tTRACING doAction() in CARD: hasPassed set to false!");
                }
                break;
            }
            case 11: {
                /* Card11: Take a trip to property, collect money when passing START */
                i = p.getCurrTile();
                while(!(tiles.get(i) instanceof Property)){
                    if (i == 31)
                    {
                        i = 0;
                    }
                    else {
                        i++;
                    }
                }
                p.setCurrTile(i);
                g.setCurrTile(tiles.get(i));
                if(p.getHasPassed() == false){
                    p.setHasPassed(true); //hasPassed is set to false, Player will not collect START money
                }
                break;
            }
            case 12: {
			/* Card12: Double rent. Apply this card to a property you own, and you
				can collect double rent from the next player who lands on it.
				If you do not own any property, discard this card

				// NOTE: This card is temporary. After Player lands on your Prop, it loses effect.
			*/

			/*
                int choice = 0;
                System.out.println("Choose PROPERTY to apply CARD [Double Rent].");
                for (i = 0; i < p.getOwnedProperties().size(); i++) {
                    System.out.println("\t[" + (i+1) + "]: " + p.getOwnedProperties().get(i).getName());
                }
                System.out.print("Choice: ");
                choice = Integer.parseInt(sc.nextLine());

                Property prop = p.getOwnedProperties().get(choice-1);
    //            prop.setDoubleRent(true);
    */

                break;
            }
            case 13: {
			/* Card13: Apply this card to a property you own. Renovation costs $25
				per house, or $50 per hotel.  From now on, rent is increased by 50%.
				If you do not own any property, discard this card.
			*/
			/*
                if (p.getOwnedProperties().size() > 0){
                    int choice = 0;
                    System.out.println("Choose PROPERTY to apply CARD [Double Rent].");
                    for (i = 0; i < p.getOwnedProperties().size(); i++) {
                        System.out.println("\t[" + (i+1) + "]: " + p.getOwnedProperties().get(i).getName());
                    }
                    System.out.print("Choice: ");
                    choice = Integer.parseInt(sc.nextLine());

                    Property prop = p.getOwnedProperties().get(choice-1);

                    int renovPrice = 0;
                    double rentIncrease = 0;

                    if (prop.getNumHouses() <= 4)
                        renovPrice = prop.getNumHouses() * 25;
                    else if (prop.getNumHouses() == 5)
                        renovPrice = prop.getNumHouses() * 50;

                    System.out.println("\tTRACING Renovation Price for this PROP: " + renovPrice);

                    p.deductCash(renovPrice);
                    b.addCash(renovPrice);

                    rentIncrease = prop.getRent() * .50;
//                    prop.setRent(rentIncrease); //updating rent with increase due to Renovation

                    System.out.println("\tTESTING: Rent for PROP [" + prop.getName() + "] increased by <" + rentIncrease + ">.");
                }
                else{
                    System.out.println("You have no owned PROPERTIES! Discard this card.");
                    //TO DO: discard from storedCards of Player
                }

			 */

                break;
            }
            case 14: {
			/* Card14: Apply this card to a property you own. Dilapidated houses.
				From now on, rent is decreased by 10%.
				If you do not own any property, discard this card.
			*/
			/*
                if (p.getOwnedProperties().size() > 0){
                    int choice = 0;
                    System.out.println("Choose PROPERTY to apply CARD [Dilapidated houses].");
                    for (i = 0; i < p.getOwnedProperties().size(); i++) {
                        System.out.println("\t[" + (i+1) + "]: " + p.getOwnedProperties().get(i).getName());
                    }
                    System.out.print("Choice: ");
                    choice = Integer.parseInt(sc.nextLine());

                    Property prop = p.getOwnedProperties().get(choice-1);

                    double rentDecrease = 0;

                    rentDecrease = prop.getRent() * .10;
     //               prop.setRent(-rentDecrease); //updating rent with increase due to Renovation

                    System.out.println("\tTESTING: Rent for PROP [" + prop.getName() + "] decreased by <" + rentDecrease + ">.");
                }
                else{
                    System.out.println("You have no owned PROPERTIES! Discard this card.");
                    //TO DO: discard from storedCards of Player
                }
			 */
                break;
            }
            case 15: {
			/* Card15: Apply this card to a utility or railroad you own.
				Increase charge by 10%.
				If you do not own any utility or railroad, discard this card
			*/
			/*
                int opt1 = 0;
                System.out.println("Choose which to apply CARD [Increase charge 10%]. (1 - for UTIL, 2 - for RAIL");
                opt1 = Integer.parseInt(sc.nextLine());

                if(opt1 == 1){ //for owned UTILITIES
                    if (p.getOwnedUtilities().size() > 0){
                        int choice = 0;
                        System.out.println("Choose UTILITY to apply CARD [Increase charge 10%].");
                        for (i = 0; i < p.getOwnedUtilities().size(); i++) {
                            System.out.println("\t[" + (i+1) + "]: " + p.getOwnedUtilities().get(i).getName());
                        }
                        System.out.print("Choice: ");
                        choice = Integer.parseInt(sc.nextLine());

                        Utility util = p.getOwnedUtilities().get(choice-1);

          //              util.setRentIncrease(); //rentIncrease is set to true and to be used in payRent() of Players

                        System.out.println("\tTESTING: Rent for UTIL [" + util.getName() + "] has been increased by 10%.");
                    }
                    else{
                        System.out.println("You have no owned UTILITIES! Discard this card.");
                        //TO DO: discard from storedCards of Player
                    }
                }
                else if (opt1 == 2){ //for owned RAILROADS
                    if (p.getOwnedRailroads().size() > 0){
                        int choice = 0;
                        System.out.println("Choose RAILROAD to apply CARD [Increase charge 10%].");
                        for (i = 0; i < p.getOwnedRailroads().size(); i++) {
                            System.out.println("\t[" + (i+1) + "]: " + p.getOwnedRailroads().get(i).getName());
                        }
                        System.out.print("Choice: ");
                        choice = Integer.parseInt(sc.nextLine());

                        Railroad rail = p.getOwnedRailroads().get(choice-1);

          //              rail.setRentIncrease(); //rentIncrease is set to true and to be used in payRent() of Players

                        System.out.println("\tTESTING: Rent for RAIL [" + rail.getName() + "] has been increased by 10%.");
                    }
                    else{
                        System.out.println("You have no owned RAILROADS! Discard this card.");
                        //TO DO: discard from storedCards of Player
                    }
                }
			 */
                break;
            }
            case 16: {
			/* Card16: Apply this card to a utility or railroad you own.
				Decrease charge by 10%.
				If you do not own any utility or railroad, discard this card.
			*/



                break;
            }
            case 17: {
                /* Card17: Donate money for community development (random amount) */
                //TO DO: generate Random from 50-200
                Random rand = new Random();
                int n = rand.nextInt(151) + 50;
                p.deductCash(n);
                b.addCash(n);
                g.setTurn();
                break;
            }
            case 18: {
                /* Card18: Pay taxes (random amount) */
                //TO DO: generate Random from 50-200
                Random rand = new Random();
                int n = rand.nextInt(151) + 50;
                p.deductCash(n);
                b.addCash(n);
                g.setTurn();
                break;
            }
        }

    }

    /** This method returns the card number of a chance card.
     *
     * @return the card number of the chance card.
     */
    public int getCardNo() {
        return cardNo;
    }

    /** This method returns the group type of a chance card.
     *
     * @return the group type of the chance card.
     */
    public int getGroupType() {
        return groupType;
    }

    /** This method returns the description of the chance card.
     *
     * @return the description of the chance card.
     */
    public String getDesc () {
        return desc;
    }

    @Override
    public String toString(){
        return "Group: " + this.groupType + "(No." + this.cardNo + ")";
    }


}