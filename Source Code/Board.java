package sample;

import java.util.*;

/** The class Board represents a board object with property, chance,
 * and fixed tiles capacities, can add tiles.
 *
 * @author Samantha Paulino and Ninna Manzano
 * @version 1.0
 */
public class Board {

    // Attributes
    private ArrayList <Tile> tiles;

    /** This constructor initializes the space for the tiles to be used in the game.
     *
     */
    public Board ()
    {
        tiles = new ArrayList <> ();
    }


    /** This method converts a string into a specific type of object, afterwhich, it adds the tile to the board.
     *
     * @param name of the tile to be instantiated. This will be used to convert the name into an object.
     */
    public void addTile (String name)
    {
        if (name == "Almond Drive" || name == "Kasoy Street" || name == "Rodeo Drive" || name == "Orange Street" ||
            name == "Ventura Street" || name == "Juan Luna" || name == "Ylaya" || name == "J. Abad Santos" || name == "Madison" ||
            name == "Annapolis" || name == "Connecticut" || name == "Bougainvilla" || name == "Dama de Noche" || name == "Acacia" ||
            name == "Solar Street" || name == "Galaxy Street" || name == "9th Street" || name == "5th Avenue")
        {
            Property p = new Property(name);
            tiles.add(p);
        }
        else if (name == "START" || name == "Community Service" || name == "Free Parking" || name == "JAIL")
        {
            if (name == "START") {
                Start s = new Start(name);
                tiles.add(s);
            }
            else if (name == "Community Service")
            {
                CommunityService c = new CommunityService(name);
                tiles.add(c);
            }
            else if (name == "Free Parking")
            {
                FreeParking p = new FreeParking(name);
                tiles.add(p);
            }
            else if (name == "JAIL")
            {
                Jail j = new Jail (name);
                tiles.add(j);
            }
        }

        else if (name == "North" || name == "South" || name == "Metro")
        {
            Railroad r = new Railroad(name);
            tiles.add(r);
        }

        else if (name == "Electric" || name == "Water")
        {
            Utility u = new Utility(name);
            tiles.add(u);
        }

        else if (name == "Luxury Tax" || name == "Income Tax")
        {
            if (name == "Luxury Tax") {
                Luxury l = new Luxury(name);
                tiles.add(l);
            }
            else if (name == "Income Tax") {
                Income i = new Income(name);
                tiles.add(i);
            }

        }

        else if (name == "Chance Space")
        {
            ChanceSpace c = new ChanceSpace(name);
            tiles.add(c);
        }

    }

    /** This method will return the tiles in the board.
     *
     * @return   the arraylist of tiles.
     */
    public ArrayList <Tile> getTiles()
    {
        return tiles;
    }



}